package com.grephq.ot.obd;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;

import androidx.core.app.ActivityCompat;

import com.grephq.ot.obd.Command.AT;
import com.grephq.ot.obd.Command.Mode01;
import com.grephq.ot.obd.Command.Mode02;
import com.grephq.ot.obd.Command.Mode03;
import com.grephq.ot.obd.Command.Mode04;
import com.grephq.ot.obd.Command.Mode05;
import com.grephq.ot.obd.Command.Mode06;
import com.grephq.ot.obd.Command.Mode07;
import com.grephq.ot.obd.Command.Mode08;
import com.grephq.ot.obd.Command.Mode09;
import com.grephq.ot.obd.Command.Mode0A;
import com.grephq.ot.obd.Encoded.Decoder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * OBD module for reading vehicle diagnostics from ELM327 adapter
 */
public class Obd {

    /**
     * Headers status
     */
    private boolean displayHeaders;

    /**
     * The mode of connection
     */
    private ConnectionType connectionType;

    /**
     * Connection timeout
     */
    private final int TIMEOUT_MS = 30000;

    /**
     * Objects required for connection via bluetooth
     */
    private BluetoothSocket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private DataOutputStream dataOutputStream;
    private BufferedReader bufferedReader;

    /**
     * Objects required for connection via USB
     */
    private UsbDevice device;
    private UsbDeviceConnection connection;

    /**
     * All available OBD commands
     */
    public static Map<String, String> Commands = new HashMap<>();

    static {
        try {
            loadObdCommands(Mode01.class);
            loadObdCommands(Mode02.class);
            loadObdCommands(Mode03.class);
            loadObdCommands(Mode04.class);
            loadObdCommands(Mode05.class);
            loadObdCommands(Mode06.class);
            loadObdCommands(Mode07.class);
            loadObdCommands(Mode08.class);
            loadObdCommands(Mode09.class);
            loadObdCommands(Mode0A.class);
            loadObdCommands(AT.class);
        } catch (IllegalAccessException ignored) {}
    }

    /**
     * Load all OBD service PIDS into a map
     *
     * @param commandClass Service/mode to fetch commands from
     * @throws IllegalAccessException if field cannot be accessed
     */
    private static void loadObdCommands(Class<?> commandClass) throws IllegalAccessException {
        Field[] fields = commandClass.getFields();

        // Add field name and values to Commands collection
        for(Field field: fields) {
            Commands.put(field.getName(), Objects.requireNonNull(field.get(field)).toString());
        }
    }

    /**
     * Class constructor for bluetooth connections
     *
     * @param socket The active bluetooth connection with the ELM327 adapter
     * @param context The application context calling the library
     * @throws IOException If bluetooth is unable to establish connection
     */
    public Obd(BluetoothSocket socket, Context context) throws IOException {
        connectionType = ConnectionType.BLUETOOTH;
        this.socket = socket;

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED)
            throw new SecurityException("Bluetooth permission not granted");

        // Connect to adapter
        this.socket.connect();

        outputStream = this.socket.getOutputStream();
        inputStream = this.socket.getInputStream();

        dataOutputStream = new DataOutputStream(outputStream);
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        enableHeaders(true);
    }

    /**
     * Class constructor for USB connections
     *
     * @param context The application context calling the library
     * @throws Exception If USB device is not connected
     */
    public Obd(Context context) throws Exception {
        connectionType = ConnectionType.USB;

        UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);

        // Check if there is USB device is connected
        if (!isUsbConnected(usbManager, context))
            throw new Exception("ELM327 USB adapter is not connected");

        // Get connected USB device
        device = usbManager.getDeviceList().values().iterator().next();
        connection = usbManager.openDevice(device);

        if (connection == null)
            throw new Exception("Failed to establish connection with USB device");

        enableHeaders(true);
    }

    /**
     * Check is USB device is connected
     *
     * @param usbManager USB context
     * @param context The application context calling the library
     * @return <c>true</c> if connected via usb; otherwise <c>false</c>
     */
    private boolean isUsbConnected(UsbManager usbManager, Context context) {
        if (usbManager == null) {
            // USB manager is not available
            return false;
        }

        return usbManager.getDeviceList().size() > 0;
    }

    /**
     * Enable/disable headers
     *
     * @param status <c>true</c> to show headers; otherwise <c>false</c>
     */
    public void enableHeaders(boolean status) throws IOException {
        displayHeaders = status;

        if (status)
            sendCommand(AT.ENABLE_DISPLAY_HEADERS);
        else
            sendCommand(AT.DISABLE_DISPLAY_HEADERS);
    }

    /**
     * Check if headers are enabled or not
     *
     * @return <c>true</c> if headers are enabled; otherwise <c>false</c>
     */
    public boolean isHeaderEnabled() {
        return displayHeaders;
    }

    /**
     * Send command to ELM327 adapter
     *
     * @param command The command to send to the ELM327 adapter
     * @return Response gotten from the adapter
     * @throws IOException If something goes wrong when sending data over bluetooth
     */
    public String sendCommand(String command) throws IOException {
        if (connectionType == ConnectionType.BLUETOOTH) {
            dataOutputStream.writeBytes(command);
            dataOutputStream.flush();

            String line = "";
            StringBuilder builder = new StringBuilder();
            while (line != null) {
                line = bufferedReader.readLine();
                builder.append(line);
            }

            return builder.toString();
        } else if (connectionType == ConnectionType.USB) {
            byte[] commandInBytes = command.getBytes();

            UsbInterface usbInterface = device.getInterface(0);
            UsbEndpoint endpoint = usbInterface.getEndpoint(0);

            connection.claimInterface(usbInterface, true);

            // Send command to USB
            connection.bulkTransfer(endpoint, commandInBytes, commandInBytes.length, TIMEOUT_MS);

            // Receive response
            byte[] buffer = new byte[1024]; // Buffer to store the received data
            int bytesRead = connection.bulkTransfer(endpoint, buffer, buffer.length, TIMEOUT_MS);

            if (bytesRead > 0) {
                // Data received successfully
                byte[] responseData = Arrays.copyOf(buffer, bytesRead);
                return new String(responseData, Charset.defaultCharset());
            }

            return null;
        } else {
            throw new IllegalArgumentException("Unknown mode of connection");
        }

    }

    /**
     * Get engine coolant temperature
     *
     * @return engine coolant temperature °C
     * @throws IOException if something goes wrong when sending request
     */
    public double getEngineCoolantTemperature() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.ENGINE_COOLANT_TEMPERATURE);
        return Decoder.decodeCoolantTemperature(response);
    }

    /**
     * Get minimum engine coolant temperature
     *
     * @return minimum engine coolant temperature °C
     */
    public double getMinimumVehicleCoolantTemperature() {
        return -40;
    }

    /**
     * Get maximum engine coolant temperature
     *
     * @return maximum engine coolant temperature °C
     */
    public double getMaximumVehicleCoolantTemperature() {
        return 215;
    }

    /**
     * Get calculated engine load
     *
     * @return calculated engine load (%)
     * @throws IOException if something goes wrong when sending request
     */
    public double getEngineLoad() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.ENGINE_LOAD);
        return Decoder.decodeEngineLoad(response);
    }

    /**
     * Get minimum engine load
     *
     * @return minimum engine load (%)
     */
    public double getMinimumEngineLoad() {
        return 0;
    }

    /**
     * Get maximum engine load
     *
     * @return maximum engine load (%)
     */
    public double getMaximumEngineLoad() {
        return 100;
    }

    /**
     * Get engine oil temperature
     *
     * @return engine oil temperature in °C
     * @throws IOException if something goes wrong when sending request
     */
    public double getEngineOilTemperature() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.ENGINE_OIL_TEMPERATURE);
        return Decoder.decodeEngineOilTemperature(response);
    }

    /**
     * Get minimum oil temperature
     *
     * @return minimum oil temperature in °C
     */
    public double getMinimumEngineOilTemperature() {
        return -40;
    }

    /**
     * Get maximum oil temperature
     *
     * @return maximum oil temperature in °C
     */
    public double getMaximumEngineOilTemperature() {
        return 210;
    }

    /**
     * Get relative accelerator pedal position
     *
     * @return relative accelerator pedal position (%)
     * @throws IOException if something goes wrong when sending request
     */
    public double getRelativeAcceleratorPedalPosition() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.RELATIVE_ACCELERATOR_PEDAL_POSITION);
        return Decoder.decodeRelativeAcceleratorPedalPosition(response);
    }

    /**
     * Get minimum relative accelerator pedal position
     *
     * @return minimum relative accelerator pedal position (%)
     */
    public double getMinimumRelativeAcceleratorPedalPosition() {
        return 0;
    }

    /**
     * Get maximum relative accelerator pedal position
     *
     * @return maximum relative accelerator pedal position (%)
     */
    public double getMaximumRelativeAcceleratorPedalPosition() {
        return 100;
    }

    /**
     * Get hybrid battery pack remaining life
     *
     * @return hybrid battery pack remaining life (%)
     * @throws IOException if something goes wrong when sending request
     */
    public double getHybridBatteryPackRemainingLife() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.HYBRID_BATTERY_PACK_REMAINING_LIFE);
        return Decoder.decodeHybridBatteryPackRemainingLife(response);
    }

    /**
     * Get minimum hybrid battery pack remaining life
     *
     * @return minimum hybrid battery pack remaining life (%)
     */
    public double getMinimumHybridBatteryPackRemainingLife() {
        return 0;
    }

    /**
     * Get maximum hybrid battery pack remaining life
     *
     * @return maximum hybrid battery pack remaining life (%)
     */
    public double getMaximumHybridBatteryPackRemainingLife() {
        return 100;
    }

    /**
     * Get EGR error
     *
     * @return EGR error (%)
     * @throws IOException if something goes wrong when sending request
     */
    public double getEGRError() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.EGR_ERROR);
        return Decoder.decodeEGRError(response);
    }

    /**
     * Get minimum EGR error (%)
     *
     * @return minimum EGR error (%)
     * @throws IOException if something goes wrong when sending request
     */
    public double getMinimumEGRError() {
        return -100;
    }

    /**
     * Get maximum EGR error(%)
     *
     * @return maximum EGR error (%)
     */
    public double getMaximumEGRError() {
        return 99.2;
    }

    /**
     * Get fuel Tank Level Input
     *
     * @return Fuel Tank Level Input (%)
     * @throws IOException if something goes wrong when sending request
     */
    public double getFuelTankInputLevel() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.FUEL_TANK_LEVEL_INPUT);
        return Decoder.decodeFuelTankInputLevel(response);
    }

    /**
     * Get minimum fuel Tank Level Input
     *
     * @return Fuel Tank Level Input (%)
     */
    public double getMinimumFuelTankInputLevel() {
        return 0;
    }

    /**
     * Get maximum fuel Tank Level Input
     *
     * @return Fuel Tank Level Input (%)
     */
    public double getMaximumFuelTankInputLevel() {
        return 100;
    }

    /**
     * Get fuel rail pressure
     *
     * @return fuel rail pressure (kPa)
     * @throws IOException if something goes wrong when sending request
     */
    public double getFuelRailPressure() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.FUEL_RAIL_PRESSURE);
        return Decoder.decodeFuelRailPressure(response);
    }

    /**
     * Get minimum fuel rail pressure
     *
     * @return minimum fuel rail pressure (kPa)
     */
    public double getMinimumFuelRailPressure() {
        return 0;
    }

    /**
     * Get maximum fuel rail pressure
     *
     * @return maximum fuel rail pressure (kPa)
     */
    public double getMaximumFuelRailPressure() {
        return 5177.265;
    }

    /**
     * Get fuel rail gauge pressure
     *
     * @return fuel rail gauge pressure (kPa)
     * @throws IOException if something goes wrong when sending request
     */
    public double getFuelRailGaugePressure() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.FUEL_INJECTION_TIMING);
        return Decoder.decodeFuelGaugeRailPressure(response);
    }

    /**
     * Get minimum fuel rail gauge pressure
     *
     * @return minimum fuel rail gauge pressure (kPa)
     */
    public double getMinimumFuelRailGaugePressure() {
        return 0;
    }

    /**
     * Get maximum fuel rail gauge pressure
     *
     * @return maximum fuel rail gauge pressure (kPa)
     */
    public double getMaximumFuelRailGaugePressure() {
        return 655350;
    }

    /**
     * Get fuel injection timing
     *
     * @return fuel injection timing (°)
     * @throws IOException if something goes wrong when sending request
     */
    public double getFuelInjectionTiming() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.FUEL_INJECTION_TIMING);
        return Decoder.decodeFuelInjectionTiming(response);
    }

    /**
     * Get minimum fuel injection timing
     *
     * @return minimum fuel injection timing (°)
     */
    public double getMinimumFuelInjectionTiming() {
        return -210.00;
    }

    /**
     * Get maximum fuel injection timing
     *
     * @return maximum fuel injection timing (°)
     */
    public double getMaximumFuelInjectionTiming() {
        return 301.992;
    }

    /**
     * Get timing advance
     *
     * @return timing advance
     * @throws IOException if something goes wrong when sending request
     */
    public double getTimingAdvance() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.TIMING_ADVANCE);
        return Decoder.decodeTimingAdvance(response);
    }

    /**
     * Get minimum timing advance
     *
     * @return minimum timing advance
     */
    public double getMinimumTimingAdvance() {
        return -64;
    }

    /**
     * Get maximum timing advance
     *
     * @return maximum timing advance
     */
    public double getMaximumTimingAdvance() {
        return 63.5;
    }

    /**
     * Get fuel type
     *
     * @return fuel type
     * @throws IOException if something goes wrong when sending request
     */
    public AbstractMap.SimpleEntry<Integer, String> getFuelType() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.FUEL_TYPE);
        return Decoder.decodeFuelType(response);
    }

    /**
     * Get engine fuel rate
     *
     * @return engine fuel rate (L/h)
     * @throws IOException if something goes wrong when sending request
     */
    public double getEngineFuelRate() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.ENGINE_FUEL_RATE);
        return Decoder.decodeEngineFuelRate(response);
    }

    /**
     * Get minimum engine fuel rate
     *
     * @return minimum engine fuel rate (L/h)
     */
    public double getMinimumEngineFuelRate() {
        return 0;
    }

    /**
     * Get maximum engine fuel rate
     *
     * @return maximum engine fuel rate (L/h)
     */
    public double getMaximumEngineFuelRate() {
        return 3212.75;
    }

    /**
     * Get fuel pressure
     *
     * @return fuel pressure in kPa
     * @throws IOException if something goes wrong when sending request
     */
    public double getFuelPressure() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.FUEL_PRESSURE);
        return Decoder.decodeFuelPressure(response);
    }

    /**
     * Get minimum fuel pressure
     *
     * @return minimum fuel pressure in kPa
     */
    public double getMinimumFuelPressure() {
        return 0;
    }

    /**
     * Get maximum fuel pressure
     *
     * @return maximum fuel pressure in kPa
     */
    public double getMaximumFuelPressure() {
        return 765;
    }

    /**
     * Get vehicle intake air temperature
     *
     * @return vehicle intake air temperature (°C)
     * @throws IOException if something goes wrong when sending request
     */
    public double getVehicleIntakeAirTemperature() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.INTAKE_AIR_TEMPERATURE);
        return Decoder.decodeIntakeAirTemperature(response);
    }

    /**
     * Get minimum vehicle intake air temperature
     *
     * @return minimum vehicle intake air temperature (°C)
     */
    public double getMinimumVehicleIntakeAirTemperature() {
        return -40;
    }

    /**
     * Get maximum vehicle intake air temperature
     *
     * @return maximum vehicle intake air temperature (°C)
     */
    public double getMaximumVehicleIntakeAirTemperature() {
        return 215;
    }

    /**
     * Get throttle position
     *
     * @return throttle position (%)
     * @throws IOException if something goes wrong when sending request
     */
    public double getThrottlePosition() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.THROTTLE_POSITION);
        return Decoder.decodeThrottlePosition(response);
    }

    /**
     * Get minimum throttle position
     *
     * @return minimum throttle position (%)
     */
    public double getMinimumThrottlePosition() {
        return 0;
    }

    /**
     * Get maximum throttle position
     *
     * @return maximum throttle position (%)
     */
    public double getMaximumThrottlePosition() {
        return 100;
    }

    /**
     * Get vehicle speed
     *
     * @return vehicle speed in km/h
     * @throws IOException if something goes wrong when sending request
     */
    public double getVehicleSpeed() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.VEHICLE_SPEED);
        return Decoder.decodeVehicleSpeed(response);
    }

    /**
     * Get minimum vehicle speed
     *
     * @return minimum vehicle speed in km/h
     */
    public double getMinimumVehicleSpeed() {
        return 0;
    }

    /**
     * Get maximum vehicle speed
     *
     * @return maximum vehicle speed in km/h
     */
    public double getMaximumVehicleSpeed() {
        return 255;
    }

    /**
     * Get engine speed
     *
     * @return engine speed (rpm)
     * @throws IOException if something goes wrong when sending request
     */
    public double getEngineSpeed() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.ENGINE_SPEED);
        return Decoder.decodeEngineSpeed(response);
    }

    /**
     * Get exhaust pressure
     *
     * @return exhaust pressure
     * @throws IOException if something goes wrong when sending request
     */
    public String[] getExhaustPressure() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand( Mode01.EXHAUST_PRESSURE);
        String[] bytes = response.split("\\s+");
        return Arrays.copyOfRange(bytes, 2, bytes.length - 1);
    }

    /**
     * Get turbocharger rpm
     *
      * @return turbocharger rpm
     * @throws IOException if something goes wrong when sending request
     */
    public String[] getTurbochargerRPM() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.TURBOCHARGER_RPM);
        String[] bytes = response.split("\\s+");
        return Arrays.copyOfRange(bytes, 2, bytes.length - 1);
    }

    /**
     * Get minimum engine speed
     *
     * @return minimum engine speed (rpm)
     */
    public double getMinimumEngineSpeed() {
        return 0;
    }

    /**
     * Get maximum engine speed
     *
     * @return maximum engine speed (rpm)
     */
    public double getMaximumEngineSpeed() {
        return 16383.75 ;
    }

    /**
     * Get odometer
     *
     * @return odometer (km)
     * @throws IOException if something goes wrong when sending request
     */
    public double getOdometer() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode01.ODOMETER);
        return Decoder.decodeOdometer(response);
    }

    /**
     * Get minimum odometer
     *
     * @return minimum odometer (km)
     */
    public double getMinimumOdometer() {
        return 0;
    }

    /**
     * Get maximum odometer
     *
     * @return maximum odometer (km)
     */
    public double getMaximumOdometer() {
        return 429496729.5;
    }

    /**
     * Show stored Diagnostic Trouble Codes (DTCs)
     *
     * @return Diagnostic Trouble Codes
     * @throws IOException if something goes wrong when sending request
     */
    public String[] requestTroubleCodes() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode03.GET_DIAGNOSTIC_TROUBLE_CODES);
        String[] bytes = response.split("\\s+");
        return Arrays.copyOfRange(bytes, 2, bytes.length - 1);
    }

    /**
     * Clear trouble codes / Malfunction indicator lamp (MIL) / Check engine light
     *
     * @throws IOException if something goes wrong when sending request
     */
    public void clearDiagnosticTroubleCodes() throws IOException {
        sendCommand(Mode04.CLEAR_DIAGNOSTIC_TROUBLE_CODES);
    }

    /**
     * Get pending trouble codes
     *
     * @return pending trouble codes
     * @throws IOException if something goes wrong when sending request
     */
    public String[] getPendingTroubleCodes() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode07.GET_CURRENT_DTC);
        String[] bytes = response.split("\\s+");
        return Arrays.copyOfRange(bytes, 2, bytes.length - 1);
    }

    /**
     * Get calibration ID
     *
     * @return Get calibration ID
     * @throws IOException if something goes wrong when sending request
     */
    public String[] getCalibrationID() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode09.CALIBRATION_ID);
        String[] bytes = response.split("\\s+");
        return Arrays.copyOfRange(bytes, 2, bytes.length - 1);
    }

    /**
     * Get vehicle identification number
     *
     * @return vehicle identification number
     * @throws IOException if something goes wrong when sending request
     */
    public String[] getVehicleIdentificationNumber() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode09.VEHICLE_IDENTIFICATION_NUMBER);
        String[] bytes = response.split("\\s+");
        return Arrays.copyOfRange(bytes, 2, bytes.length - 1);
    }

    /**
     * Get ECU name
     *
     * @return ECU name
     * @throws IOException if something goes wrong when sending request
     */
    public String[] getECUName() throws IOException {
        // Disable headers from being in response
        if(isHeaderEnabled())
            enableHeaders(false);

        String response = sendCommand(Mode09.ECU_NAME);
        String[] bytes = response.split("\\s+");
        return Arrays.copyOfRange(bytes, 2, bytes.length - 1);
    }
}
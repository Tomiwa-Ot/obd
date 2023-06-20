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
import com.grephq.ot.obd.Command.Mode03;
import com.grephq.ot.obd.Command.Mode04;
import com.grephq.ot.obd.Command.Mode07;
import com.grephq.ot.obd.Command.Mode09;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

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
     */
    public double getEngineCoolantTemperature() throws IOException {
        String response = sendCommand(Mode01.ENGINE_COOLANT_TEMPERATURE);
        return 0;
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
     */
    public double getEngineLoad() throws IOException {
        String response = sendCommand(Mode01.ENGINE_LOAD);
        return 0;
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
     */
    public double getEngineOilTemperature() throws IOException {
        String response = sendCommand(Mode01.ENGINE_OIL_TEMPERATURE);
        return 0;
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
     */
    public double getRelativeAcceleratorPedalPosition() throws IOException {
        String response = sendCommand(Mode01.RELATIVE_ACCELERATOR_PEDAL_POSITION);
        return 0;
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
     */
    public double getHybridBatteryPackRemainingLife() throws IOException {
        String response = sendCommand(Mode01.HYBRID_BATTERY_PACK_REMAINING_LIFE);
        return 0;
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
     * Get fuel injection timing
     *
     * @return fuel injection timing (°)
     */
    public double getFuelInjectionTiming() throws IOException {
        String response = sendCommand(Mode01.FUEL_INJECTION_TIMING);
        return 0;
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
     * Get engine fuel rate
     *
     * @return engine fuel rate (L/h)
     */
    public double getEngineFuelRate() throws IOException {
        String response = sendCommand(Mode01.ENGINE_FUEL_RATE);
        return 0;
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
     */
    public double getFuelPressure() throws IOException {
        String response = sendCommand(Mode01.FUEL_PRESSURE);
        return 0;
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
     */
    public double getVehicleIntakeAirTemperature() throws IOException {
        String response = sendCommand(Mode01.INTAKE_AIR_TEMPERATURE);
        return 0;
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
     */
    public double getThrottlePosition() throws IOException {
        String response = sendCommand(Mode01.THROTTLE_POSITION);
        return 0;
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
     */
    public double getVehicleSpeed() throws IOException {
        String response = sendCommand(Mode01.VEHICLE_SPEED);
        return 0;
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
     */
    public double getEngineSpeed() throws IOException {
        String response = sendCommand(Mode01.ENGINE_SPEED);
        return 0;
    }

    /**
     * Get exhaust pressure
     *
     * @return exhaust pressure
     */
    public double getExhaustPressure() throws IOException {
        String response = sendCommand( Mode01.EXHAUST_PRESSURE);
        return 0;
    }

    /**
     * Get turbocharger rpm
     *
      * @return turbocharger rpm
     */
    public double getTurbochargerRPM() throws IOException {
        String response = sendCommand(Mode01.TURBOCHARGER_RPM);
        return 0;
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
     * Show stored Diagnostic Trouble Codes (DTCs)
     * @return Diagnostic Trouble Codes
     */
    public String requestTroubleCodes() throws IOException {
        String response = sendCommand(Mode03.GET_DIAGNOSTIC_TROUBLE_CODES);
        return "";
    }

    /**
     * Clear trouble codes / Malfunction indicator lamp (MIL) / Check engine light
     */
    public void clearDiagnosticTroubleCodes() throws IOException {
        sendCommand(Mode04.CLEAR_DIAGNOSTIC_TROUBLE_CODES);
    }

    /**
     * Get pending trouble codes
     *
     * @return pending trouble codes
     */
    public String getPendingTroubleCodes() throws IOException {
        String response = sendCommand(Mode07.GET_CURRENT_DTC);
        return "";
    }

    /**
     * Get calibration ID
     *
     * @return Get calibration ID
     */
    public String getCalibrationID() throws IOException {
        String response = sendCommand(Mode09.CALIBRATION_ID);
        return "";
    }

    /**
     * Get vehicle identification number
     *
     * @return vehicle identification number
     */
    public String getVehicleIdentificationNumber() throws IOException {
        String response = sendCommand(Mode09.VEHICLE_IDENTIFICATION_NUMBER);
        return "";
    }

    /**
     * Get ECU name
     *
     * @return ECU name
     */
    public String getECUName() throws IOException {
        String response = sendCommand(Mode09.ECU_NAME);
        return "";
    }
}
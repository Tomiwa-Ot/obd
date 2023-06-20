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

}
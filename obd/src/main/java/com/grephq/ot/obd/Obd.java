package com.grephq.ot.obd;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * OBD module for reading vehicle diagnostics from ELM327 adapter
 */
public class Obd {

    public Obd(ConnectionType type, Context context) throws Exception {
        if (type == ConnectionType.USB) {
            // Check if usb is connected to ELM327 adapter
            if (!isUsbConnected())
                throw new Exception("ELM327 usb adapter is not connected");
        }

        if (type == ConnectionType.BLUETOOTH) {
            // Check if device supports Bluetooth classic
            if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH))
                throw new SecurityException("Device doesn't support bluetooth");

            // Check if bluetooth is connected to ELM327 adapter
            if (!isBluetoothConnected())
                throw new Exception("ELM327 bluetooth adapter is not connected");
        }
    }

    /**
     * Check if bluetooth is connected
     *
     * @return <c>true</c> if connected via bluetooth; otherwise <c>false</c>
     */
    private boolean isBluetoothConnected() {
        return true;
    }

    /**
     * Check if USB is connected
     *
     * @return <c>true</c> if connected via usb; otherwise <c>false</c>
     */
    private boolean isUsbConnected() {
        return true;
    }

    /**
     * Send command to ELM327 adapter
     *
     * @param command The Command to send to the ELM327 adapter
     */
    public void sendCommand(String command) {

    }

    /**
     * Parse the data received from the ELM327 adapter
     *
     * @param response Data received from the ELM327 adapter
     * @return
     */
    private String parseResponse(String response) {
        return "";
    }

}
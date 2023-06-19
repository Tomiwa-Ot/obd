package com.grephq.ot.obd;

public class Obd {

    public Obd(ConnectionType type) {
        if (type == ConnectionType.USB) {

        }

        if (type == ConnectionType.BLUETOOTH) {
            // Check if device supports Bluetooth classic
            if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)) {
                throw new Exception("Device doesn't support bluetooth");
            }
        }
    }

}
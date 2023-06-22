package com.grephq.ot.obd.Encoded;

import java.util.AbstractMap;
import java.util.List;

/**
 * Decode OBD response
 */
public class Decoder {

    /**
     * Convert hexadecimal to decimal
     *
     * @param hex hexadecimal to convert
     * @return decimal representation
     */
    private static int hexToDec(String hex) {
        return Integer.parseInt(hex, 16);
    }

    /**
     * Extract engine load from OBD response
     *
     * @param data OBD response
     * @return engine load (%)
     */
    public static double decodeEngineLoad(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return (100.0 / 255.0) * a;
    }

    /**
     * Extract throttle position from OBD response
     *
     * @param data OBD response
     * @return throttle position (%)
     */
    public static double decodeThrottlePosition(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return (100.0 / 255.0) * a;
    }

    /**
     * Extract vehicle speed from OBD response
     *
     * @param data OBD response
     * @return vehicle speed (km/h)
     */
    public static double decodeVehicleSpeed(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return a;
    }

    /**
     * Extract engine coolant temperature from OBD response
     *
     * @param data OBD response
     * @return engine coolant temperature (°C)
     */
    public static double decodeCoolantTemperature(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return a - 40;
    }

    /**
     * Extract fuel pressure from OBD response
     *
     * @param data
     * @return fuel pressure (kPa)
     */
    public static double decodeFuelPressure(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return 3 * a;
    }

    /**
     * Extract engine speed from OBD response
     *
     * @param data OBD response
     * @return engine speed (rpm)
     */
    public static double decodeEngineSpeed(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return ((256.0 * a) + b) / 4;
    }

    /**
     * Extract timing advance from OBD response
     *
     * @param data OBD response
     * @return timing advance
     */
    public static double decodeTimingAdvance(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return (a / 2.0) - 64;
    }

    /**
     * Extract relative accelerator pedal position
     *
     * @param data OBD response
     * @return relative accelerator pedal position (%)
     */
    public static double decodeRelativeAcceleratorPedalPosition(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return (100.0 / 255.0) * a;
    }

    /**
     * Extract fuel rail pressure from OBD response
     *
     * @param data OBD response
     * @return fuel rail pressure (kPa)
     */
    public static double decodeFuelRailPressure(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return 0.079 * ((256 * a) + b);
    }

    /**
     * Extract fuel rail gauge pressure from OBD response
     *
     * @param data OBD response
     * @return kPa
     */
    public static double decodeFuelGaugeRailPressure(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return 10 * ((256 * a) + b);
    }

    /**
     * Extract EGR error from OBD response
     *
     * @param data OBD response
     * @return EGR error (%)
     */
    public static double decodeEGRError(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return ((100.0 / 128.0) * a)  - 100;
    }

    /**
     * Extract fuel tank input level from OBD response
     *
     * @param data OBD response
     * @return fuel tank input level (%)
     */
    public static double decodeFuelTankInputLevel(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return (100.0 / 255.0) * a;
    }

    /**
     * Extract intake air temperature from OBD response
     *
     * @param data OBD response
     * @return intake air temperature (°C)
     */
    public static double decodeIntakeAirTemperature(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return a - 40;
    }

    /**
     * Extract hybrid battery pack remaining life
     *
     * @param data OBD response
     * @return hybrid battery pack remaining life (%)
     */
    public static double decodeHybridBatteryPackRemainingLife(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return (100.0 / 255.0) * a;
    }

    /**
     * Extract odometer from OBD response
     *
     * @param data OBD response
     * @return odometer (km)
     */
    public static double decodeOdometer(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);
        int c = hexToDec(bytes[4]);
        int d = hexToDec(bytes[5]);

        return ((a * Math.pow(2, 24)) + (b * Math.pow(2, 16)) + (c * Math.pow(2, 8)) + d) / 10.0;
    }

    /**
     * Extract fuel injection timing from OBD response
     *
     * @param data OBD response
     * @return fuel injection timing (°)
     */
    public static double decodeFuelInjectionTiming(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return ((256 * a) + b / 128.0) - 210;
    }

    /**
     * Extract the fuel type from OBD response
     *
     * @param data OBD response
     * @return fuel type
     */
    public static AbstractMap.SimpleEntry<Integer, String> decodeFuelType(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return new AbstractMap.SimpleEntry<Integer, String>(a, EnumeratedPIDS.FuelType.get(a));
    }

    /**
     * Extract engine oil temperature from OBD response
     *
     * @param data OBD response
     * @return engine oil temperature (°C)
     */
    public static double decodeEngineOilTemperature(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return a - 40;
    }

    /**
     * Engine fuel rate
     *
     * @param data OBD response
     * @return Engine fuel rate (L/h)
     */
    public static double decodeEngineFuelRate(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return  ((256 * a) + b) / 20.0;
    }
}

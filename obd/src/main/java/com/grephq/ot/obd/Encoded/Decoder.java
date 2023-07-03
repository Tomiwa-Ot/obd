package com.grephq.ot.obd.Encoded;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * Extract engine fuel rate from OBD response
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

    /**
     * Extract fuel system status from OBD response
     *
     * @param data OBD response
     * @return Fuel system status
     */
    public static AbstractMap.SimpleEntry<Integer, String> decodeFuelSystemStatus(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return new AbstractMap.SimpleEntry<Integer, String>(a, EnumeratedPIDS.FuelSystemStatus.get(a));
    }

    /**
     * Extract fuel trim from OBD response
     *
     * @param data OBD response
     * @return fuel trim (%)
     */
    public static double decodeFuelTrim(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return ((100.0 / 128.0) * a) - 100;
    }

    /**
     * Extract intake manifold absolute pressure from OBD response
     *
     * @param data OBD response
     * @return intake manifold absolute pressure (kPa)
     */
    public static double decodeIntakeManifoldAbsolutePressure(String data) {
        String[] bytes = data.split("\\s+");

        return hexToDec(bytes[2]);
    }

    /**
     * Extract mass air flow sensor air flow rate from OBD response
     *
     * @param data OBD response
     * @return mass air flow sensor air flow rate (g/s)
     */
    public static double decodeMAFSensorAirFlowRate(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return ((256 * a) + b) / 100.0;
    }

    /**
     * Extract oxygen sensor voltage from OBD response
     *
     * @param data OBD response
     * @return oxygen sensor voltage (V)
     */
    public static double decodeOxygenSensorVoltage(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return a / 200.0;
    }

    /**
     * Extract oxygen sensor fuel trim from OBD response
     *
     * @param data OBD response
     * @return oxygen sensor fuel trim (%)
     */
    public static double decodeOxygenSensorFuelTrim(String data) {
        String[] bytes = data.split("\\s+");
        int b = hexToDec(bytes[3]);

        return ((100.0 / 128.0) * b) - 100;
    }

    /**
     * Extract run time since engine start from OBD response
     *
     * @param data OBD response
     * @return run time since engine start (s)
     */
    public static double decodeRunTimeSinceEngineStart(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return (256 * a) + b;
    }

    /**
     * Extract distance travelled with malfunction lamp on from OBD response
     *
     * @param data OBD response
     * @return distance travelled with malfunction lamp on (km)
     */
    public static double decodeDistanceTravelledWithMalfunctionIndicatorLampOn(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return (256 * a) + b;
    }

    /**
     * Extract commanded EGR from OBD response
     *
     * @param data OBD response
     * @return commanded EGR (%)
     */
    public static double decodeCommandedEGR(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return (100.0 / 255.0) * a;
    }

    /**
     * Extract commanded evaporative purge from OBD response
     *
     * @param data OBD response
     * @return Commanded evaporative purge (%)
     */
    public static double decodeCommandedEvaporativePurge(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return (100.0 / 255.0) * a;
    }

    /**
     * Extract catalyst temperature from OBD response
     *
     * @param data OBD response
     * @return catalyst temperature (°C)
     */
    public static double decodeCatalystTemperature(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return (((256 * a) + b) / 10.0) - 40.0;
    }

    /**
     * Extract commanded module voltage from OBD response
     *
     * @param data OBD response
     * @return commanded module voltage (V)
     */
    public static double decodeCommandedModuleVoltage(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return ((256 * a) + b) / 1000.0;
    }

    /**
     * Extract absolute load value from OBD response (%)
     * @param data OBD response
     * @return absolute load value (%)
     */
    public static double decodeAbsoluteLoadValue(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return (100.0 / 255.0) * ((256 * a) + b);
    }

    /**
     * Extract commanded air-fuel equivalence ratio from OBD response
     *
     * @param data OBD response
     * @return commanded air-fuel equivalence ratio
     */
    public static double decodeCommandedAirFuelEquivalenceRatio(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return (2.0 / 65536.0) * ((256 * a) + b);
    }

    /**
     * Extract relative throttle position from OBD response
     *
     * @param data OBD response
     * @return relative throttle position (%)
     */
    public static double decodeRelativeThrottlePosition(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return (100.0 / 255.0) * a;
    }

    /**
     * Extract time run with malfunction indicator lamp on from OBD response
     *
     * @param data OBD response
     * @return
     */
    public static double decodeTimeRunWithMalfunctionIndicatorLampOn(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return (256.0 * a) + b;
    }

    /**
     * Extract time since trouble codes cleared from OBD response
     *
     * @param data OBD response
     * @return time since trouble codes cleared (min)
     */
    public static double decodeTimeSinceTroubleCodesCleared(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return (256.0 * a) + b;
    }

    /**
     * Extract ethanol fuel from OBD response
     *
     * @param data OBD response
     * @return ethanol fuel (%)
     */
    public static double decodeEthanolFuel(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return (100.0 / 255.0) * a;
    }

    /**
     * Extrcat cylinder fuel rate from OBD response
     *
     * @param data OBD response
     * @return cylinder fuel rate (mg/stroke)
     */
    public static double decodeCylinderFuelRate(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return ((256.0 * a) + b) / 32.0;
    }

    /**
     * Extract engine friction percent torque from OBD response
     *
     * @param data OBD response
     * @return engine friction percent torque (%)
     */
    public static double decodeEngineFrictionPercentTorque(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);

        return a - 125;
    }

    /**
     * Extract diesel particulate filter temperature from OBD response
     *
     * @param data OBD response
     * @return diesel particulate filter temperature (°C)
     */
    public static double decodeDieselParticulateFilterTemperature(String data) {
        String[] bytes = data.split("\\s+");
        int a = hexToDec(bytes[2]);
        int b = hexToDec(bytes[3]);

        return (((256 * a) + b) / 10.0) - 40;
    }
}

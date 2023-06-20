package com.grephq.ot.obd.Command;

/**
 *  Show current data
 */
public class Mode01 {

    // OBD Mode
    private static final String MODE = "01";

    // PIDs supported [$01 - $20]
    public static final String SUPPORTED_PIDS = MODE + " 00";

    // Monitor status since DTCs cleared. (Includes malfunction indicator lamp (MIL), status and number of DTCs, components tests, DTC readiness checks)
    public static final String STATUS_SINCE_DTC_CLEARED = MODE + " 01";

    // DTC that caused freeze frame to be stored.
    public static final String FREEZE_DTC = MODE + " 02";

    // Fuel system status
    public static final String FUEL_SYSTEM_STATUS = MODE + " 03";

    // Calculated engine load
    public static final String ENGINE_LOAD = MODE + " 04";

    // Engine coolant temperature
    public static final String ENGINE_COOLANT_TEMPERATURE = MODE + " 05";

    // Short term fuel trim—Bank 1
    public static final String SHORT_TERM_FUEL_TRIM_BANK_1 = MODE + " 06";

    // Long term fuel trim—Bank 1
    public static final String LONG_TERM_FUEL_TRIM_BANK_1 = MODE + " 07";

    // Short term fuel trim—Bank 2
    public static final String SHORT_TERM_FUEL_TRIM_BANK_2 = MODE + " 08";

    // Long term fuel trim—Bank 2
    public static final String LONG_TERM_FUEL_TRIM_BANK_2 = MODE + " 09";

    // Fuel pressure (gauge pressure)
    public static final String FUEL_PRESSURE = MODE + " 0A";

    // Intake manifold absolute pressure
    public static final String INTAKE_MANIFOLD_ABSOLUTE_PRESSURE = MODE + " 0B";

    // Engine speed
    public static final String ENGINE_SPEED = MODE + " 0C";

    // Vehicle speed
    public static final String VEHICLE_SPEED = MODE + " 0D";

    // Timing advance
    public static final String TIMING_ADVANCE = MODE + " 0E";

    // Intake air temperature
    public static final String INTAKE_AIR_TEMPERATURE = MODE + " 0F";

    // Mass air flow (MAF) air flow rate
    public static final String MASS_AIR_FLOW = MODE + " 10";

    // Throttle position
    public static final String THROTTLE_POSITION = MODE + " 11";

    // Commanded secondary air status
    public static final String COMMANDED_SECONDARY_AIR_STATUS = MODE + " 12";

    // Oxygen sensors present (in 2 banks)
    public static final String OXYGEN_SENSORS_PRESENT = MODE + " 13";

    // Oxygen Sensor 1
    // A: Voltage
    // B: Short term fuel trim
    public static final String OXYGEN_SENSOR_1 = MODE + " 14";

    // Oxygen Sensor 2
    // A: Voltage
    // B: Short term fuel trim
    public static final String OXYGEN_SENSOR_2 = MODE + " 15";

    // Oxygen Sensor 3
    // A: Voltage
    // B: Short term fuel trim
    public static final String OXYGEN_SENSOR_3 = MODE + " 16";

    // Oxygen Sensor 4
    // A: Voltage
    // B: Short term fuel trim
    public static final String OXYGEN_SENSOR_4 = MODE + " 17";
}

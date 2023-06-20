package com.grephq.ot.obd.Command;

/**
 *  Show current data
 */
public class Mode01 {

    /**
     * OBD Mode
     */
    private static final String MODE = "01";

    /**
     * PIDs supported [$01 - $20]
     */
    public static final String SUPPORTED_PIDS_01_TO_20 = MODE + " 00";

    /**
     * Monitor status since DTCs cleared. (Includes malfunction indicator lamp (MIL), status and number of DTCs, components tests, DTC readiness checks)
     */
    public static final String STATUS_SINCE_DTC_CLEARED = MODE + " 01";

    /**
     * DTC that caused freeze frame to be stored.
     */
    public static final String FREEZE_DTC = MODE + " 02";

    /**
     * Fuel system status
     */
    public static final String FUEL_SYSTEM_STATUS = MODE + " 03";

    /**
     * Calculated engine load
     */
    public static final String ENGINE_LOAD = MODE + " 04";

    /**
     * Engine coolant temperature
     */
    public static final String ENGINE_COOLANT_TEMPERATURE = MODE + " 05";

    /**
     * Short term fuel trim—Bank 1
     */
    public static final String SHORT_TERM_FUEL_TRIM_BANK_1 = MODE + " 06";

    /**
     * Long term fuel trim—Bank 1
     */
    public static final String LONG_TERM_FUEL_TRIM_BANK_1 = MODE + " 07";

    /**
     * Short term fuel trim—Bank 2
     */
    public static final String SHORT_TERM_FUEL_TRIM_BANK_2 = MODE + " 08";

    /**
     * Long term fuel trim—Bank 2
     */
    public static final String LONG_TERM_FUEL_TRIM_BANK_2 = MODE + " 09";

    /**
     * Fuel pressure (gauge pressure)
     */
    public static final String FUEL_PRESSURE = MODE + " 0A";

    /**
     * Intake manifold absolute pressure
     */
    public static final String INTAKE_MANIFOLD_ABSOLUTE_PRESSURE = MODE + " 0B";

    /**
     * Engine speed
     */
    public static final String ENGINE_SPEED = MODE + " 0C";

    /**
     * Vehicle speed
     */
    public static final String VEHICLE_SPEED = MODE + " 0D";

    /**
     * Timing advance
     */
    public static final String TIMING_ADVANCE = MODE + " 0E";

    /**
     * Intake air temperature
     */
    public static final String INTAKE_AIR_TEMPERATURE = MODE + " 0F";

    /**
     * Mass air flow (MAF) air flow rate
     */
    public static final String MASS_AIR_FLOW = MODE + " 10";

    /**
     * Throttle position
     */
    public static final String THROTTLE_POSITION = MODE + " 11";

    /**
     * Commanded secondary air status
     */
    public static final String COMMANDED_SECONDARY_AIR_STATUS = MODE + " 12";

    /**
     * Oxygen sensors present (in 2 banks)
     */
    public static final String OXYGEN_SENSORS_PRESENT_IN_2_BANKS = MODE + " 13";

    /**
     * Oxygen Sensor 1
     * A: Voltage
     * B: Short term fuel trim
     */
    public static final String OXYGEN_SENSOR_1 = MODE + " 14";

    /**
     * Oxygen Sensor 2
     * A: Voltage
     * B: Short term fuel trim
     */
    public static final String OXYGEN_SENSOR_2 = MODE + " 15";

    /**
     * Oxygen Sensor 3
     * A: Voltage
     * B: Short term fuel trim
     */
    public static final String OXYGEN_SENSOR_3 = MODE + " 16";

    /**
     * Oxygen Sensor 4
     * A: Voltage
     * B: Short term fuel trim
     */
    public static final String OXYGEN_SENSOR_4 = MODE + " 17";

    /**
     * Oxygen Sensor 5
     * A: Voltage
     * B: Short term fuel trim
     */
    public static final String OXYGEN_SENSOR_5 = MODE + " 18";

    /**
     * Oxygen Sensor 6
     * A: Voltage
     * B: Short term fuel trim
     */
    public static final String OXYGEN_SENSOR_6 = MODE + " 19";

    /**
     * Oxygen Sensor 7
     * A: Voltage
     * B: Short term fuel trim
     */
    public static final String OXYGEN_SENSOR_7 = MODE + " 1A";

    /**
     * Oxygen Sensor 8
     * A: Voltage
     * B: Short term fuel trim
     */
    public static final String OXYGEN_SENSOR_8 = MODE + " 1B";

    /**
     * OBD standards this vehicle conforms to
     */
    public static final String OBD_STANDARDS = MODE + " 1C";

    /**
     * Oxygen sensors present (in 4 banks)
     */
    public static final String OXYGEN_SENSORS_PRESENT_IN_4_BANKS = MODE + " 1D";

    /**
     * Auxiliary input status
     */
    public static final String AUXILIARY_INPUT_STATUS = MODE + " 1E";

    /**
     * Run time since engine start
     */
    public static final String RUNTIME_SINCE_ENGINE_START = MODE + " 1F";

    /**
     * PIDs supported [$21 - $40]
     */
    public static final String SUPPORTED_PIDS_21_TO_40 = MODE + " 20";

    /**
     * Distance traveled with malfunction indicator lamp (MIL) on
     */
    public static final String DISTANCE_TRAVELED_WITH_MALFUNCTION_INDICATOR_LAMP = MODE + " 21";

    /**
     * Fuel Rail Pressure (relative to manifold vacuum)
     */
    public static final String FUEL_RAIL_PRESSURE = MODE + " 22";

    /**
     * Fuel Rail Gauge Pressure (diesel, or gasoline direct injection)
     */
    public static final String FUEL_RAIL_GAUGE_PRESSURE = MODE + " 23";

    /**
     * Fuel type
     */
    public static final String FUEL_TYPE = MODE + " 51";

    /**
     * Relative accelerator pedal position
     */
    public static final String RELATIVE_ACCELERATOR_PEDAL_POSITION = MODE + " 5A";

    /**
     * Hybrid battery pack remaining life
     */
    public static final String HYBRID_BATTERY_PACK_REMAINING_LIFE = MODE + " 5B";

    /**
     * Engine oil temperature
     */
    public static final String ENGINE_OIL_TEMPERATURE = MODE + " 5C";

    /**
     * Fuel injection timing
     */
    public static final String FUEL_INJECTION_TIMING = MODE + " 5D";

    /**
     * Engine fuel rate
     */
    public static final String ENGINE_FUEL_RATE = MODE + " 5E";

    /**
     * Actual EGR, Commanded EGR, and EGR Error
     */
    public static final String EGR = MODE + " 69";

    /**
     * Commanded Diesel intake air flow control and relative intake air flow position
     */
    public static final String COMMANDED_DIESEL_INTAKE_AIR_FLOW_CONTROL = MODE + " 6A";

    /**
     * Exhaust gas recirculation temperature
     */
    public static final String EXHAUST_GAS_RECIRCULATION_TEMPERATURE = MODE + " 6B";

    /**
     * Commanded throttle actuator control and relative throttle position
     */
    public static final String RELATIVE_THROTTLE_POSITION = MODE + " 6C";

    /**
     * Fuel pressure control system
     */
    public static final String FUEL_PRESSURE_CONTROL_SYSTEM = MODE + " 6D";

    /**
     * Injection pressure control system
     */
    public static final String INJECTION_PRESSURE_CONTROL_SYSTEM = MODE + " 6E";

    /**
     * Turbocharger compressor inlet pressure
     */
    public static final String TURBOCHARGER_COMPRESSOR_INLET_PRESSURE = MODE + " 6F";

    /**
     * Boost pressure control
     */
    public static final String BOOST_PRESSURE_CONTROL = MODE + " 70";

    /**
     * Variable Geometry turbo (VGT) control
     */
    public static final String VARIABLE_GEOMETRY_TURBO_CONTROL = MODE + " 71";

    /**
     * Wastegate control
     */
    public static final String WASTEGATE_CONTROL = MODE + " 72";

    /**
     * Exhaust pressure
     */
    public static final String EXHAUST_PRESSURE = MODE + " 73";

    /**
     * Turbocharger RPM
     */
    public static final String TURBOCHARGER_RPM = MODE + " 74";

    /**
     * Turbocharger temperature
     */
    public static final String TURBOCHARGER_TEMPERATURE_1 = MODE + " 75";

    /**
     * Turbocharger temperature
     */
    public static final String TURBOCHARGER_TEMPERATURE_2 = MODE + " 76";

    /**
     * Charge air cooler temperature (CACT)
     */
    public static final String CHARGE_AIR_COOLER_TEMPERATURE = MODE + " 77";

    /**
     * Odometer
     */
    public static final String ODOMETER = MODE + " A6";

    /**
     * NOx Sensor Concentration Sensors 3 and 4
     */
    public static final String NOX_SENSOR_CONCENTRATION_SENSORS_3_AND_4 = MODE + " A7";

    /**
     * NOx Sensor Corrected Concentration Sensors 3 and 4
     */
    public static final String NOX_SENSOR_CORRECTED_CONCENTRATION_SENSORS_3_AND_4 = MODE + " A8";

    /**
     * ABS Disable Switch State
     */
    public static final String ABS_DISABLE_SWITCH_STATE = MODE + " A9";
}

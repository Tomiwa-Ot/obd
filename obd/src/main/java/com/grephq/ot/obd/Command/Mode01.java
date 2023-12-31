package com.grephq.ot.obd.Command;

/**
 *  Show current data
 */
final public class Mode01 {

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
     * Commanded EGR
     */
    public static final String COMMANDED_EGR = MODE + " 2C";

    /**
     * EGR Error
     */
    public static final String EGR_ERROR = MODE + " 2D";

    /**
     * Commanded evaporative purge
     */
    public static final String COMMANDED_EVAPORATIVE_PURGE = MODE + " 2E";

    /**
     * Fuel Tank Level Input
     */
    public static final String FUEL_TANK_LEVEL_INPUT = MODE + " 2F";

    /**
     * Warm-ups since codes cleared
     */
    public static final String WARM_UPS_SINCE_CODES_CLEARED = MODE + " 30";

    /**
     * Distance traveled since codes cleared
     */
    public static final String DISTANCE_TRAVELED_SINCE_CODES_CLEARED = MODE + " 31";

    /**
     * Evap. System Vapor Pressure
     */
    public static final String EVAP_SYSTEM_VAPOR_PRESSURE = MODE + " 32";

    /**
     * Absolute Barometric Pressure
     */
    public static final String ABSOLUTE_BAROMETRIC_PRESSURE = MODE + " 33";

    /**
     * Catalyst Temperature: Bank 1, Sensor 1
     */
    public static final String CATALYST_TEMPERATURE_BANK_1_SENSOR_1 = MODE + " 3C";

    /**
     * Catalyst Temperature: Bank 2, Sensor 1
     */
    public static final String  CATALYST_TEMPERATURE_BANK_2_SENSOR_1 = MODE + " 3D";

    /**
     * Catalyst Temperature: Bank 1, Sensor 2
     */
    public static final String  CATALYST_TEMPERATURE_BANK_1_SENSOR_2 = MODE + " 3E";

    /**
     * Catalyst Temperature: Bank 2, Sensor 2
     */
    public static final String  CATALYST_TEMPERATURE_BANK_2_SENSOR_2 = MODE + " 3F";

    /**
     * PIDs supported [$41 - $60]
     */
    public static final String SUPPORTED_PIDS_41_TO_60 = MODE + " 40";

    /**
     * Monitor status this drive cycle
     */
    public static final String MONITOR_STATUS_THIS_DRIVE_CYCLE = MODE + " 41";

    /**
     * Control module voltage
     */
    public static final String CONTROL_MODULE_VOLTAGE = MODE + " 42";

    /**
     * Absolute load value
     */
    public static final String ABSOLUTE_LOAD_VALUE = MODE + " 43";

    /**
     * Commanded Air-Fuel Equivalence Ratio
     */
    public static final String COMMANDED_AIR_FUEL_EQUIVALENCE_RATIO = MODE + " 44";

    /**
     * Relative throttle position
     */
    public static final String RELATIVE_THROTTLE_POSITION = MODE + " 45";

    /**
     * Ambient air temperature
     */
    public static final String AMBIENT_AIR_TEMPERATURE = MODE + " 46";

    /**
     * Absolute throttle position B
     */
    public static final String ABSOLUTE_THROTTLE_POSITION_B = MODE + " 47";

    /**
     * Absolute throttle position C
     */
    public static final String ABSOLUTE_THROTTLE_POSITION_C = MODE +  " 48";

    /**
     * Accelerator pedal position D
     */
    public static final String ACCELERATOR_PEDAL_POSITION_D = MODE + " 49";

    /**
     * Accelerator pedal position E
     */
    public static final String ACCELERATOR_PEDAL_POSITION_E = MODE + " 4A";

    /**
     * Accelerator pedal position F
     */
    public static final String ACCELERATOR_PEDAL_POSITION_F = MODE + " 4B";

    /**
     * Commanded throttle actuator
     */
    public static final String COMMANDED_THROTTLE_ACTUATOR = MODE + " 4C";

    /**
     * Time run with MIL on
     */
    public static final String TIME_RUN_WITH_MIL_ON = MODE + " 4D";

    /**
     * Time since trouble codes cleared
     */
    public static final String TIME_SINCE_TROUBLE_CODES_CLEARED = MODE + " 4E";

    /**
     * Fuel type
     */
    public static final String FUEL_TYPE = MODE + " 51";

    /**
     * Ethanol fuel %
     */
    public static final String ETHANOL_FUEL = MODE + " 52";

    /**
     * Absolute Evap system Vapor Pressure
     */
    public static final String ABSOLUTE_EVAP_SYSTEM_VAPOR_PRESSURE = MODE + " 53";

    /**
     * Short term secondary oxygen sensor trim, A: bank 1, B: bank 3
     */
    public static final String SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_A_BANK_1_B_BANK_3 = MODE + " 55";

    /**
     * Long term secondary oxygen sensor trim, A: bank 1, B: bank 3
     */
    public static final String  LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_A_BANK_1_B_BANK_3 = MODE + " 56";

    /**
     * Short term secondary oxygen sensor trim, A: bank 2, B: bank 4
     */
    public static final String SHORT_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_A_BANK_2_B_BANK_4 = MODE + " 57";

    /**
     * Long term secondary oxygen sensor trim, A: bank 2, B: bank 4
     */
    public static final String LONG_TERM_SECONDARY_OXYGEN_SENSOR_TRIM_A_BANK_2_B_BANK_4 = MODE + " 58";

    /**
     * Fuel rail absolute pressure
     */
    public static final String FUEL_RAIL_ABSOLUTE_PRESSURE = MODE + " 59";

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
     * Emission requirements to which vehicle is designed
     */
    public static final String EMISSION_REQUIREMENTS = MODE + " 5F";

    /**
     * PIDs supported [$61 - $80]
     */
    public static final String SUPPORTED_PIDS_61_TO_80 = MODE + " 60";

    /**
     * Driver's demand engine - percent torque
     */
    public static final String DRIVERS_DEMAND_ENGINE_PERCENT_TORQUE = MODE + " 61";

    /**
     * Actual engine - percent torque
     */
    public static final String ACTUAL_ENGINE_PERCENT_TORQUE = MODE + " 62";

    /**
     * Engine reference torque
     */
    public static final String ENGINE_REFERENCE_TORQUE = MODE + " 63";

    /**
     * Engine percent torque data
     */
    public static final String ENGINE_PERCENT_TORQUE_DATA = MODE + " 64";

    /**
     * Auxiliary input / output supported
     */
    public static final String AUXILIARY_INPUT_OUTPUT_SUPPORTED = MODE + " 65";

    /**
     * Mass air flow sensor
     */
    public static final String MASS_AIR_FLOW_SENSOR = MODE + " 66";

    /**
     * Intake air temperature sensor
     */
    public static final String INTAKE_AIR_TEMPERATURE_SESNOR = MODE + " 68";

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
    public static final String COMMANDED_THROTTLE_ACTUATOR_CONTROL_AND_RELATIVE_THROTTLE_POSITION = MODE + " 6C";

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
     * Exhaust Gas temperature (EGT) Bank 1
     */
    public static final String EXHAUST_GAS_TEMPERATURE_BANK_1 = MODE + " 78";

    /**
     * Exhaust Gas temperature (EGT) Bank 2
     */
    public static final String EXHAUST_GAS_TEMPERATURE_BANK_2 = MODE + " 79";

    /**
     * Diesel particulate filter (DPF) differential pressure
     */
    public static final String DIESEL_PARTICULATE_FILTER_DIFFERENTIAL_PRESSURE = MODE + " 7A";

    /**
     * Diesel particulate filter (DPF)
     */
    public static final String DIESEL_PARTICULATE_FILTER = MODE + " 7B";

    /**
     * Diesel Particulate filter (DPF) temperature
     */
    public static final String DIESEL_PARTICULATE_FILTER_TEMPERATURE = MODE + " 7C";

    /**
     * NOx NTE (Not-To-Exceed) control area status
     */
    public static final String NOX_NTE_CONTROL_AREA_STATUS = MODE + " 7D";

    /**
     * PM NTE (Not-To-Exceed) control area status
     */
    public static final String PM_NTE_CONTROL_AREA_STATUS = MODE + " 7E";

    /**
     * Engine run time
     */
    public static final String ENGINE_RUN_TIME = MODE + " 7F";

    /**
     * PIDs supported [$81 - $A0]
     */
    public static final String SUPPORTED_PIDS_81_TO_A0 = MODE + " 80";

    /**
     * NOx sensor
     */
    public static final String NOX_SENSOR = MODE + " 83";

    /**
     * Manifold surface temperature
     */
    public static final String MANIFOLD_SURFACE_TEMPERATURE = MODE + " 84";

    /**
     * NOx reagent system
     */
    public static final String NOX_REAGENT_SYSTEM = MODE + " 85";

    /**
     * Particulate matter (PM) sensor
     */
    public static final String PARTICULATE_MATTER_SENSOR = MODE + " 86";

    /**
     * SCR Induce System
     */
    public static final String SCR_INDUCE_SYSTEM = MODE + " 88";

    /**
     * Diesel Aftertreatment
     */
    public static final String DIESEL_AFTERTREATMENT = MODE + " 8B";

    /**
     * O2 Sensor (Wide Range)
     */
    public static final String O2_SENSOR_WIDE_RANGE = MODE + " 8C";

    /**
     * Throttle Position G
     */
    public static final String THROTTLE_POSITION_G = MODE + " 8D";

    /**
     * Engine Friction - Percent Torque
     */
    public static final String ENGINE_FRICTION_PERCENT_TORQUE = MODE + " 8E";

    /**
     * PM Sensor Bank 1 & 2
     */
    public static final String PM_SENSOR_BANK_1_AND_2 = MODE + " 8F";

    /**
     * Fuel System Control
     */
    public static final String FUEL_SYSTEM_CONTROL = MODE + " 92";

    /**
     * WWH-OBD Vehicle OBD Counters support
     */
    public static final String WWH_OBD_VEHICLE_OBD_COUNTERS_SUPPORT = MODE + " 93";

    /**
     * NOx Warning And Inducement System
     */
    public static final String NOX_WARNING_AND_INDUCEMENT_SYSTEM = MODE + " 94";

    /**
     * Hybrid/EV Vehicle System Data, Battery, Voltage
     */
    public static final String HYBRID_VEHICLE_SYSTEM_DATA_BATTERY_VOLTAGE = MODE + " 9A";

    /**
     * Diesel Exhaust Fluid Sensor Data
     */
    public static final String DIESEL_EXHAUST_FLUID_SENSOR_DATA = MODE + " 9B";

    /**
     * O2 Sensor Data
     */
    public static final String O2_SENSOR_DATA = MODE + " 9C";

    /**
     * Engine Exhaust Flow Rate
     */
    public static final String ENGINE_EXHAUST_FLOW_RATE = MODE + " 9E";

    /**
     * Fuel System Percentage Use
     */
    public static final String FUEL_SYSTEM_PERCENTAGE_USE = MODE + " 9F";

    /**
     * PIDs supported [$A1 - $C0]
     */
    public static final String SUPPORTED_PIDS_A1_TO_C0 = MODE + " A0";

    /**
     * NOx Sensor Corrected Data
     */
    public static final String NOX_SENSOR_CORRECTED_DATA = MODE + " A1";

    /**
     * Cylinder fuel rate
     */
    public static final String CYLINDER_FUEL_RATE = MODE + " A2";

    /**
     * Transmission Actual Gear
     */
    public static final String TRANSMISSION_ACTUAL_GEAR = MODE + " A4";

    /**
     * Commanded Diesel Exhaust Fluid Dosing
     */
    public static final String COMMANDED_DIESEL_EXHAUST_FLUID_DOSING = MODE + " A5";

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

    /**
     * PIDs supported [$C1 - $E0]
     */
    public static final String SUPPORTED_PIDS_C1_TO_E0 = MODE + " C0";
}

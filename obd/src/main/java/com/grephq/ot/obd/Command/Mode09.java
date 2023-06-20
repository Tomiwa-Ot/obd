package com.grephq.ot.obd.Command;

/**
 * Request vehicle information
 */
public class Mode09 {

    // OBD Mode
    private static final String MODE = "09";

    // Service 9 supported PIDs ($01 to $20)
    public static final String SUPPORTED_PIDS = MODE + " 00";

    // VIN Message Count in PID 02. Only for ISO 9141-2, ISO 14230-4 and SAE J1850.
    public static final String VIN_MESSAGE_COUNT = MODE + " 01";

    // Vehicle Identification Number (VIN)
    public static final String VEHICLE_IDENTIFICATION_NUMBER = MODE + " 02";

    // Calibration ID message count for PID 04. Only for ISO 9141-2, ISO 14230-4 and SAE J1850
    public static final String CALIBRATION_ID_MESSAGE_COUNT = MODE + " 03";

    // Calibration ID
    public static final String CALIBRATION_ID = MODE + " 04";

    // Calibration verification numbers (CVN) message count for PID 06. Only for ISO 9141-2, ISO 14230-4 and SAE J1850.
    public static final String CALIBRATION_VERIFICATION_NUMBERS_MESSAGE_COUNT = MODE + " 05";

    // Calibration Verification Numbers (CVN) Several CVN can be output (4 bytes each) the number of CVN and CALID must match
    public static final String CALIBRATION_VERIFICATION_NUMBERS = MODE + " 06";

    // In-use performance tracking message count for PID 08 and 0B. Only for ISO 9141-2, ISO 14230-4 and SAE J1850.
    public static final String PERFORMANCE_TRACKING_MESSAGE_COUNT  = MODE + " 07";

    // In-use performance tracking for spark ignition vehicles
    public static final String PERFORMANCE_TRACKING_FOR_SPARK_IGNITION_VEHICLES = MODE + " 08";

    // ECU name message count for PID 0A
    public static final String ECU_NAME_MESSAGE_COUNT = MODE + " 09";

    // ECU Name
    public static final String ECU_NAME = MODE + " 0A";

    // In-use performance tracking for compression ignition vehicles
    public static final String PERFORMANCE_TRACKING_FOR_COMPRESSION_IGNITION_VEHICLES = MODE + " 0B";
}

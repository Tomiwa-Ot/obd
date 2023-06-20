package com.grephq.ot.obd.modes;

/**
 * Request vehicle information
 */
public class Mode09 {

    // OBD Mode
    private static final String MODE = "09";

    // Service 9 supported PIDs ($01 to $20)
    public static final String SUPPORTED_PIDS = MODE + "00";

    // VIN Message Count in PID 02. Only for ISO 9141-2, ISO 14230-4 and SAE J1850.
    public static final String VIN_MESSAGE_COUNT = MODE + "01";

    // Vehicle Identification Number (VIN)
    public static final String VEHICLE_IDENTIFICATION_NUMBER = MODE + "02";

    // Calibration ID message count for PID 04. Only for ISO 9141-2, ISO 14230-4 and SAE J1850
    public static final String CALIBRATION_ID_MESSAGE_COUNT = MODE + "03";

    // Calibration ID
    public static final String CALIBRATION_ID = MODE + "04";
}

package com.grephq.ot.obd.Command;

/**
 * AT Commands
 */
final public class AT {

    // -------------------------
    // Identification Commands |
    // -------------------------

    /**
     * Returns fixed device identification
     */
    public static final String DEVICE_IDENTIFICATION = "AT I";

    /**
     * Retrieve ELM-USB unique interface serial number (ELM-USB
     * specific, not part of ELM32x command set)
     */
    public static final String INTERFACE_SERIAL_NUMBER = "AT/N";

    // --------------------------
    // ELM-USB Control Commands |
    // --------------------------

    /**
     * Performs device reset and returns ELM-USB identification
     */
    public static final String RESET = "AT Z";

    /**
     * Perform soft reset and returns ELM-USB identification
     */
    public static final String WARM_START = "AT WS";

    /**
     * Terminates current diagnostic session
     */
    public static final String PROTOCOL_CLOSE = "AT PC";

    // ------------------------------
    // Communication Setup Commands |
    // ------------------------------

    /**
     * Set defaults
     */
    public static final String SET_DEFAULTS = "AT D";

    /**
     * Disable line feed
     */
    public static final String DISABLE_LINE_FEED = "AT L0";

    /**
     * Enable line feed
     */
    public static final String ENABLE_LINE_FEED = "AT L1";

    /**
     * Echo off
     */
    public static final String ECHO_OFF = "AT E0";

    /**
     * Echo on
     */
    public static final String ECHO_ON = "AT E1";

    /**
     * Disable display headers parameter
     */
    public static final String DISABLE_DISPLAY_HEADERS = "AT H0";

    /**
     * Enable display headers parameter
     */
    public static final String ENABLE_DISPLAY_HEADERS = "AT H1";

    // -------------------
    // Protocol Commands |
    // -------------------

    /**
     * Sets protocol timeout
     */
    public static final String SET_TIMEOUT = "AT ST";

    /**
     * Set communication protocol to automatic
     */
    public static final String PROTOCOL_AUTOMATIC = "AT SP0";

    /**
     * Set communication protocol to SAE J1850 PWM(41.6 kbaud)
     */
    public static final String PROTOCOL_SAE_J1850_PWM = "AT SP1";

    /**
     * Set communication protocol to SAE J1850 VPW(10.4 kbaud)
     */
    public static final String PROTOCOL_SAE_J1850_VPW = "AT SP2";

    /**
     * Set communication protocol to ISO 9141-2(5 baud init, 10.4 kbaud)
     */
    public static final String PROTOCOL_ISO_9141_2 = "AT SP3";

    /**
     * Set communication protocol to ISO 14230-4 KWP(5 baud init, 10.4 kbaud)
     */
    public static final String PROTOCOL_ISO_14230_4_KWP_5_BAUD_INIT = "AT SP4";

    /**
     * Set communication protocol to ISO 14230-4 KWP(fast init, 10.4 kbaud)
     */
    public static final String PROTOCOL_ISO_14230_4_KWP_FAST_INIT = "AT SP5";

    /**
     * Set communication protocol to ISO 15765-4 CAN(11 bit ID, 500 kbaud)
     */
    public static final String PROTOCOL_ISO_15765_4_CAN_11_BIT_500_KBAUD = "AT SP6";

    /**
     * Set communication protocol to ISO 15765-4 CAN(29 bit ID, 500 kbaud)
     */
    public static final String PROTOCOL_ISO_15765_4_CAN_29_BIT_500_KBAUD = "AT SP7";

    /**
     * Set communication protocol to ISO 15765-4 CAN(11 bit ID, 250 kbaud) - used mainly on utility vehicles and Volvo
     */
    public static final String PROTOCOL_ISO_15765_4_CAN_11_BIT_250_KBAUD = "AT SP8";

    /**
     * Set communication protocol to ISO 15765-4 CAN(29 bit ID, 250 kbaud) - used mainly on utility vehicles and Volvo
     */
    public static final String PROTOCOL_ISO_15765_4_CAN_29_BIT_250_KBAUD = "AT SP9";

    /**
     * Returns currently used diagnostic protocol ("AUTO, protocol", if auto-detection is set)
     */
    public static final String DISPLAY_PROTOCOL = "AT DP";

    /**
     * Display of the DLC off
     */
    public static final String DLC_DISPLAY_OFF = "AT D0";

    /**
     * Display of the DLC on
     */
    public static final String DLC_DISPLAY_ON = "AT D1";

    /**
     * (J1939) Monitor for DM1 messages
     */
    public static final String MONITOR_DM1_MESSAGES = "AT DM1";

    /**
     * Describe the protocol by number
     */
    public static final String DISPLAY_PROTOCOL_NUMBER = "At DPN";

    /**
     * Performs an OBD-II protocol buffer dump. Not to be used by applications
     */
    public static final String PROTOCOL_BUFFER_DUMP = "ARBD";

    /**
     * Use of variable DLC off
     */
    public static final String VARIABLE_DLC_OFF = "AT V0";

    /**
     * Use of variable DLC on
     */
    public static final String VARIABLE_DLC_ON = "AT V1";

    /**
     * Printing of space off
     */
    public static final String PRINT_SPACE_OFF = "AT S0";

    /**
     * Printing of space on
     */
    public static final String PRINT_SPACE_ON = "AT S1";

    /**
     * Read voltage
     */
    public static final String READ_VOLTAGE = "AT RV";

    /**
     * Memory off
     */
    public static final String MEMORY_OFF = "AT M0";

    /**
     * Memory on
     */
    public static final String MEMORY_ON = "AT M1";

    /**
     * Monitor all
     */
    public static final String MONITOR_ALL = "AT MA";

    /**
     * Responses off
     */
    public static final String RESPONSES_OFF = "AT R0";

    /**
     * Responses on
     */
    public static final String RESPONSES_ON = "AT R1";

    /**
     * Forget event
     */
    public static final String FORGET_EVENT = "AT FE";

    /**
     * Automatic receive
     */
    public static final String AUTOMATIC_RECEIVE = "AT AR";

    /**
     * Perform a buffer dump
     */
    public static final String BUFFER_DUMP = "AT BD";

    /**
     * Normal length
     */
    public static final String NORMAL_LENGTH = "AT NL";

    /**
     * Print a PP summary
     */
    public static final String PP_SUMMARY = "AT PPS";

    /**
     * Send an RTR message
     */
    public static final String SEND_RTR_MESSAGE = "AT RTR";

    /**
     * Show the CAN status
     */
    public static final String SHOW_CAN_STATUS = "AT CS";

    /**
     * Allow long (>7 byte) messages
     */
    public static final String ALLOW_LONG_MESSAGES = "AT AL";

    /**
     * Adaptive timing OFF
     */
    public static final String ADAPTIVE_TIMING_OFF = "AT AT0";

    /**
     * Adaptive timing Auto1
     */
    public static final String ADAPTIVE_TIMING_AUTO_1 = "AT AT1";

    /**
     * Adaptive timing Auto2
     */
    public static final String ADAPTIVE_TIMING_AUTO_2 = "AT AT2";

    /**
     * IFRs off
     */
    public static final String IFRS_OFF = "AT IFR0";

    /**
     * IFRs auto
     */
    public static final String IFRS_AUTO = "AT IFR1";

    /**
     * IFRs on
     */
    public static final String IFRS_ON = "AT IFR2";

    /**
     * Display keywords
     */
    public static final String DISPLAY_KEYWORDS = "AT KW";

    /**
     * Keywords checking off
     */
    public static final String KEYWORDS_CHECKING_OFF = "AT KW0";

    /**
     * Keywords checking on
     */
    public static final String KEYWORDS_CHECKING_ON = "AT KW1";

    /**
     * Use J1939 SAE data format
     */
    public static final String SAE_DATA_FORMAT = "AT JS";

    /**
     * Use J1939 ELM data format
     */
    public static final String ELM_DATA_FORMAT = "AT JE";

    /**
     * IFR value from source
     */
    public static final String IFR_VALUE_FROM_SOURCE = "AT IFR S";

    /**
     * IFR value from header
     */
    public static final String IFR_VALUE_FROM_HEADER = "AT IFR H";

    /**
     * Set the ISO baud rate to 10400
     */
    public static final String ISO_BAUD_RATE_10400 = "AT IB 10";

    /**
     * Set the ISO baud rate to 4800
     */
    public static final String ISO_BAUD_RATE_4800 = "AT IB 48";

    /**
     * Set the ISO baud rate to 9600
     */
    public static final String ISO_BAUD_RATE_9600 = "AT IB 96";

    /**
     * CAN flow control off
     */
    public static final String CAN_FLOW_CONTROL_OFF = "AT CFC0";

    /**
     * CAN flow control on
     */
    public static final String CAN_FLOW_CONTROL_ON = "AT CFC1";

    /**
     * Turn off CAN extended addressing
     */
    public static final String CAN_EXTENDED_ADDRESSING_OFF = "AT CEA";

    /**
     * CAN automatic formatting off
     */
    public static final String CAN_AUTOMATIC_FORMATTING_OFF = "AT CAF0";

    /**
     * CAN automatic formatting on
     */
    public static final String CAN_AUTOMATIC_FORMATTING_ON = "AT CAF1";

    /**
     * Repeat the last command
     */
    public static final String REPEAT_LAST_COMMAND = "AT <CR>";

    /**
     * Display the device description
     */
    public static final String DISPLAY_DEVICE_DESCRIPTION = "AT@1";

    /**
     * Display the device identifier
     */
    public static final String DISPLAY_DEVICE_IDENTIFIER = "AT @2";
}

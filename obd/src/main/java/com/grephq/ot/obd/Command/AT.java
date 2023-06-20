package com.grephq.ot.obd.Command;

/**
 * AT Commands
 */
public class AT {

    // -------------------------
    // Identification Commands |
    // -------------------------

    // Returns fixed device identification
    public static final String DEVICE_IDENTIFICATION = "AT I";

    // Retrieve ELM-USB unique interface serial number (ELM-USB
    // specific, not part of ELM32x command set)
    public static final String INTERFACE_SERIAL_NUMBER = "AT/N";

    // --------------------------
    // ELM-USB Control Commands |
    // --------------------------

    // Performs device reset and returns ELM-USB identification
    public static final String RESET = "AT Z";

    // Perform soft reset and returns ELM-USB identification
    public static final String WARM_START = "AT WS";

    // Terminates current diagnostic session
    public static final String PROTOCOL_CLOSE = "AT PC";

    // ------------------------------
    // Communication Setup Commands |
    // ------------------------------

    // Set defaults
    public static final String SET_DEFAULTS = "AT D";

    // Disable line feed
    public static final String DISABLE_LINE_FEED = "AT L0";

    // Enable line feed
    public static final String ENABLE_LINE_FEED = "AT L1";

    // Echo off
    public static final String ECHO_OFF = "AT E0";

    // Echo on
    public static final String ECHO_ON = "AT E1";

    // Disable display headers parameter
    public static final String DISABLE_DISPLAY_HEADERS = "AT H0";

    // Enable display headers parameter
    public static final String ENABLE_DISPLAY_HEADERS = "AT H1";

    // -------------------
    // Protocol Commands |
    // -------------------

    // Sets protocol timeout
    public static final String SET_TIMEOUT = "ATST";

    // Set communication protocol to automatic
    public static final String PROTOCOL_AUTOMATIC = "ATSP0";

    // Set communication protocol to SAE J1850 PWM(41.6 kbaud)
    public static final String PROTOCOL_SAE_J1850_PWM = "AT SP1";

    // Set communication protocol to SAE J1850 VPW(10.4 kbaud)
    public static final String PROTOCOL_SAE_J1850_VPW = "AT SP2";

    // Set communication protocol to ISO 9141-2(5 baud init, 10.4 kbaud)
    public static final String PROTOCOL_ISO_9141_2 = "AT SP3";

    // Set communication protocol to ISO 14230-4 KWP(5 baud init, 10.4 kbaud)
    public static final String PROTOCOL_ISO_14230_4_KWP_5_BAUD_INIT = "AT SP4";

    // Set communication protocol to ISO 14230-4 KWP(fast init, 10.4 kbaud)
    public static final String PROTOCOL_ISO_14230_4_KWP_FAST_INIT = "AT SP5";

    // Set communication protocol to ISO 15765-4 CAN(11 bit ID, 500 kbaud)
    public static final String PROTOCOL_ISO_15765_4_CAN_11_BIT_500_KBAUD = "AT SP6";

    // Set communication protocol to ISO 15765-4 CAN(29 bit ID, 500 kbaud)
    public static final String PROTOCOL_ISO_15765_4_CAN_29_BIT_500_KBAUD = "AT SP7";

    // Set communication protocol to ISO 15765-4 CAN(11 bit ID, 250 kbaud) - used mainly on utility vehicles and Volvo
    public static final String PROTOCOL_ISO_15765_4_CAN_11_BIT_250_KBAUD = "AT SP8";

    // Set communication protocol to ISO 15765-4 CAN(29 bit ID, 250 kbaud) - used mainly on utility vehicles and Volvo
    public static final String PROTOCOL_ISO_15765_4_CAN_29_BIT_250_KBAUD = "AT SP9";

    // Returns currently used diagnostic protocol ("AUTO, protocol", if auto-detection is set)
    public static final String DISPLAY_PROTOCOL = "AT DP";

    // Performs an OBD-II protocol buffer dump. Not to be used by applications
    public static final String PROTOCOL_BUFFER_DUMP = "ARBD";
}

package com.grephq.ot.obd.Encoded;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class EnumeratedPIDS {

    /**
     * Mode 01 PID 03 - Fuel system status
     * A request for this PID returns 2 bytes of data.
     * The first byte describes fuel system #1.
     * The second byte describes fuel system #2 (if it exists)
     * and is encoded identically to the first byte. The meaning assigned
     * to the value of each byte is as follows:
     */
    public Map<Integer, String> FuelSystemStatus = new HashMap<Integer, String>() {{
        put(0, "The motor is off");
        put(1, "Open loop due to insufficient engine temperature");
        put(2, "Closed loop, using oxygen sensor feedback to determine fuel mix");
        put(4, "Open loop due to engine load OR fuel cut due to deceleration");
        put(8, "Open loop due to system failure");
        put(16, "Closed loop, using at least one oxygen sensor but there is a fault in the feedback system");
    }};

    /**
     * Mode 01 PID 12 - Commanded secondary air status
     * A request for this PID returns a single byte of data
     * which describes the secondary air status.
     */
    public Map<Integer, String> CommandedSecondaryAirStatus = new HashMap<Integer, String>() {{
        put(1, "Upstream");
        put(2, "Downstream of catalytic converter");
        put(4, "From the outside atmosphere or off");
        put(8, "Pump commanded on for diagnostics");
    }};

    /**
     * Service 01 PID 1C - OBD standards this vehicle conforms to
     * A request for this PID returns a single byte of data which
     * describes which OBD standards this ECU was designed to comply
     * with. The different values the data byte can hold are shown below,
     * next to what they mean:
     */
    public Map<Integer, String> VehicleStandards = new HashMap<Integer, String>() {{
        put(1, "OBD-II as defined by the CARB");
        put(2, "OBD as defined by the EPA");
        put(3, "OBD and OBD-II");
        put(4, "OBD-I");
        put(5, "Not OBD compliant");
        put(6, "EOBD (Europe)");
        put(7, "EOBD and OBD-II");
        put(8, "EOBD and OBD");
        put(9, "EOBD, OBD and OBD II");
        put(10, "JOBD (Japan)");
        put(11, "JOBD and OBD II");
        put(12, "JOBD and EOBD");
        put(13, "JOBD, EOBD, and OBD II");
        put(14, "Reserved");
        put(15, "Reserved");
        put(16, "Reserved");
        put(17, "Engine Manufacturer Diagnostics (EMD)");
        put(18, "Engine Manufacturer Diagnostics Enhanced (EMD+)");
        put(19, "Heavy Duty On-Board Diagnostics (Child/Partial) (HD OBD-C)");
        put(20, "Heavy Duty On-Board Diagnostics (HD OBD)");
        put(21, "World Wide Harmonized OBD (WWH OBD)");
        put(22, "Reserved");
        put(23, "Heavy Duty Euro OBD Stage I without NOx control (HD EOBD-I)");
        put(24, "Heavy Duty Euro OBD Stage I with NOx control (HD EOBD-I N)");
        put(25, "Heavy Duty Euro OBD Stage II without NOx control (HD EOBD-II)");
        put(26, "Heavy Duty Euro OBD Stage II with NOx control (HD EOBD-II N)");
        put(27, "Reserved");
        put(28, "Brazil OBD Phase 1 (OBDBr-1)");
        put(29, "Brazil OBD Phase 2 (OBDBr-2)");
        put(30, "Korean OBD (KOBD)");
        put(31, "India OBD I (IOBD I)");
        put(32, "India OBD II (IOBD II)");
        put(33, "Heavy Duty Euro OBD Stage VI (HD EOBD-IV)");
    }};

    /**
     * Fuel type
     */
    public Map<Integer, String> FuelType = new HashMap<Integer, String>() {{
        put(0, "Not available");
        put(1, "Gasoline");
        put(2, "Methanol");
        put(3, "Ethanol");
        put(4, "Diesel");
        put(5, "LPG");
        put(6, "CNG");
        put(7, "Propane");
        put(8, "Electric");
        put(9, "Bifuel running gasoline");
        put(10, "Bifuel running methanol");
        put(11, "Bifuel running ethanol");
        put(12, "Bifuel running LPG");
        put(13, "Bifuel running CNG");
        put(14, "Bifuel running propane");
        put(15, "Bifuel running electricity");
        put(16, "Bifuel running electric and combustion engine");
        put(17, "Hybrid gasoline");
        put(18, "Hybrid ethanol");
        put(19, "Hybrid diesel");
        put(20, "Hybrid electric");
        put(21, "Hybrid running electric and combustion engine");
        put(22, "Hybrid regenerative");
        put(23, "Bifuel running diesel");
    }};
}

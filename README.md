# Java OBD
Android library for getting vehicle diagnostics from ELM327 (USB/Bluetooth) connector over OBD protocol

### Basic Usage
```java
// For USB connections
Obd obd = new Obd(getApplicationContext());

// For bluetooth connections
Obd obd - new Obd(, getApplicationContext());

// Enable/disable headers
obd.enableHeaders(true);

// Get device ID
String deviceId = obd.sendCommand(AT.DEVICE_IDENTIFICATION);

// Get engine coolant temperature
String engineCoolantTemperature = obd.sendCommand(Mode01.ENGINE_COOLANT_TEMPERATURE);
```

### Documentation

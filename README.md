# Java OBD
Android library for getting vehicle diagnostics from ELM327 (USB/Bluetooth) connector over OBD protocol.

### Basic Usage
```java
// For USB connections
Obd obd = new Obd(getApplicationContext());

// For bluetooth connections
final UUID SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
// NB: BluetoothDevice device
Obd obd - new Obd(device.createRfcommSocketToServiceRecord(SPP_UUID), getApplicationContext());

// Enable/disable headers
obd.enableHeaders(true);

// Get device ID
String deviceId = obd.sendCommand(AT.DEVICE_IDENTIFICATION);

// Get engine coolant temperature
String engineCoolantTemperature = obd.sendCommand(Mode01.ENGINE_COOLANT_TEMPERATURE);
```

### Documentation
For a list of all available commands, https://github.com/Tomiwa-Ot/obd/wiki

## TODO
- Add more AT commands
- Add Obd and Decoder methods to fetch Mode01 data and decode it
# Java OBD
Android library for getting vehicle diagnostics from ELM327 (USB/Bluetooth) connector over OBD protocol

### Basic Usage
```java
Obd obd = new Obd(ConnectionType.Bluetooth, getApplicationContext());
obd.enableHeaders(true);
```

### Documentation

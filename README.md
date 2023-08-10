# Java OBD
Android library for getting vehicle diagnostics from ELM327 (USB/Bluetooth) connector over OBD protocol.

### Basic Usage
```java
// For USB connections
Obd obd = new Obd(getApplicationContext());

// For bluetooth connections
final UUID SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
// NB: BluetoothDevice device
Obd obd = new Obd(device.createRfcommSocketToServiceRecord(SPP_UUID), getApplicationContext());

// Enable/disable headers
obd.enableHeaders(true);

// Get device ID
String deviceId = obd.sendCommand(AT.DEVICE_IDENTIFICATION);

// Get engine coolant temperature
String engineCoolantTemperature = obd.sendCommand(Mode01.ENGINE_COOLANT_TEMPERATURE);

// Get vehicle speed (km/h)
double speed = obd.getVehicleSpeed();

// Get engine oil temperature (°C)
double oilTemperature = obd.getEngineOilTemperature();

// Register asynchronous requests
Map<String, AsyncCommand<int>> commands = new HashMap<String, AsyncCommand<int>>();
commands.put("Ethanol fuel", new AsyncCommand(Mode01.ETHANOL_FUEL));

// Declare outside class or different file
class MyCallback<T> implements AsyncCallback {
    public T run(Object... params) {
        // Your implementation
    }
}

commands.put("ECU name", new AsyncCommand(Mode09.ECU_NAME, new MyCallback<int>(), new Object[]{}));

AsyncObd async = new AsyncObd(obd, commands, false);
Thread thread = new Thread(async);
thread.start();

String response = commads.get("ECU name").getResponse(); // Get OBD response
boolean isCallbackNull = commads.get("ECU name").isCallbackNull(); // Check if async command has callback
Object callbackResult = commads.get("ECU name").getCallbackResult(); // Get callback output
async.stopExecuting() // To terminate thread
```

### Installation
- Add it in your root build.gradle at the end of repositories
  
  ```
  allprojects {
  		repositories {
  			...
  			maven { url 'https://jitpack.io' }
  		}
   }
   ```
- Add dependency

  ```
  dependencies {
	        implementation 'com.github.Tomiwa-Ot:obd:Tag'
	}
  ```
  
### Documentation
https://github.com/Tomiwa-Ot/obd/wiki

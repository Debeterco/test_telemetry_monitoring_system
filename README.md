# Telemetry Monitoring System  
## Secure IoT Data Acquisition Using ESP32 and MQTT over TLS

Repository:  
https://github.com/Debeterco/test_telemetry_monitoring_system

---

## 1. Abstract

This project presents the design and implementation of a secure telemetry monitoring system based on an ESP32 microcontroller, integrated sensors, and a cloud-based MQTT broker. The system collects environmental and electrical parameters, displays them locally via an LCD interface, and transmits structured telemetry data to a remote backend using MQTT over TLS (Transport Layer Security).

The primary objective is to demonstrate secure IoT communication, real-time data acquisition, and backend integration using industry-standard protocols.

---

## 2. System Architecture

The architecture follows a layered IoT model:

### Data Acquisition Layer
- ESP32  
- DHT22 temperature sensor  
- Analog inputs (simulated vibration and current)

### Communication Layer
- Wi-Fi (IEEE 802.11)  
- MQTT over TLS (Port 8883)  
- HiveMQ Cloud broker  

### Application Layer
- Java MQTT Subscriber  
- Data processing and monitoring  

---

## 3. Hardware Components

- ESP32 Development Board  
- DHT22 Temperature Sensor  
- 2 Potentiometers (analog simulation inputs)  
- 16x2 LCD (I2C – address 0x27)  
- Status LED  

---

## 4. Software Stack

### Embedded System (ESP32)

Libraries used:

- `DHTesp`
- `WiFi.h`
- `LiquidCrystal_I2C`
- `PubSubClient`
- `WiFiClientSecure`

### Backend

- Java
- Eclipse Paho MQTT Client
- JSON / CSV parsing

---

## 5. Secure MQTT Communication

The ESP32 establishes a secure connection to HiveMQ Cloud using TLS.

Broker configuration:


Port: 8883
Protocol: MQTT over TLS
Authentication: Username + Password


The secure client is instantiated using:

```cpp
WiFiClientSecure espClient;
espClient.setInsecure(); // TLS enabled (no certificate validation)

For production systems, proper CA certificate validation is recommended.

6. Data Acquisition and Measurement Units

The system collects three parameters:

6.1 Temperature

Sensor: DHT22

Unit: °C

Scaled range: 0–100 °C

6.2 Vibration (Simulated)

Input: Analog potentiometer

Unit: % (0–100%)

Mapping: 0–4095 ADC → 0–100

6.3 Electrical Current (Simulated)

The analog value from the potentiometer is converted to milliamperes (mA) instead of raw ADC values.

Corrected Current Conversion Example
int rawCurrent = analogRead(PIN_POT2);
float current_mA = map(rawCurrent, 0, 4095, 0, 5000); // 0–5000 mA scale

This converts the 12-bit ADC range (0–4095) into a 0–5000 mA range.

Display Example (LCD)
Current: 1250 mA

This replaces the previous incorrect representation such as 1987A, which was merely a raw ADC value and not a calibrated electrical measurement.

7. MQTT Topic and Payload Structure
Topic
iot/esp32/room/data
Payload Format (CSV)
temperature,vibration,current_mA

Example:

27.5,43.2,1250

Where:

Temperature → °C

Vibration → %

Current → mA

8. Execution Flow

Initialize serial communication

Configure GPIO and peripherals

Connect to Wi-Fi network

Establish secure MQTT session

Read sensors

Convert ADC to calibrated units

Display values on LCD

Publish telemetry payload

Repeat cycle

9. LED Transmission Indicator

The green LED is activated during MQTT message publication to provide visual confirmation of successful transmission.

10. Limitations

Current measurement is simulated via potentiometer.

TLS certificate validation is disabled (setInsecure()).

Payload uses CSV instead of structured JSON.

No persistent data storage implemented.

11. Future Improvements

Replace potentiometer with a calibrated sensor (e.g., ACS712).

Implement JSON payload format.

Enable certificate-based TLS authentication.

Integrate database persistence (e.g., MariaDB or PostgreSQL).

Develop real-time dashboard interface (e.g., WebSocket + Next.js).

Implement QoS levels and Last Will configuration.

12. Educational Contributions

This project demonstrates:

Embedded system development with ESP32

Analog-to-digital conversion and unit calibration

Secure IoT communication using MQTT over TLS

Cloud broker integration

Backend subscriber implementation in Java

Real-time telemetry architecture

13. Conclusion

The telemetry monitoring system provides a practical implementation of secure IoT data acquisition and cloud-based communication. By integrating embedded hardware, TLS-secured MQTT transmission, and backend processing, the project reflects a scalable model applicable to industrial monitoring, smart environments, and distributed sensor networks.

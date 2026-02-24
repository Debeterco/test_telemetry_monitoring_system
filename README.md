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

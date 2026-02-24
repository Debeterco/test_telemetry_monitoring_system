package br.com.senai;

// Libraries of the protocol MQTT
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class CollectorApp {
    public static void main(String[] args) throws Exception {
        // Connection to broker MQTT variables
        String broker = "ssl://e0722a79b31148c9b57155e48211f476.s1.eu.hivemq.cloud:8883";
        String clientId = "JavaBackendClient_Debeterco";

        try { 
            // Declaring the client using the variables of the broker
            MqttClient client = new MqttClient(broker, clientId);

            // Setting the username and passwords
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("Kasteller");
            String password = System.getenv("MQTT_PASS");
            if (password == null) {
                throw new RuntimeException("Environment variable MQTT_PASS not defined.");
            }
            options.setPassword(password.toCharArray());
            options.setCleanSession(true);
            options.setAutomaticReconnect(true); // Protocol TLS/SSL security

            // Connection broker - ESP32 data
            System.out.println("Connecting to Secure HiveMQ Cloud...");
            client.connect(options);
            System.out.println("Connected at HiveMQ.");

            // Received message from the ESP32 to the console
            client.subscribe("iot/esp32/room/data", (topic, message) -> {
            String payload = new String(message.getPayload());

            System.out.println("Topic: " + topic);
            System.out.println("Payload: " + payload);});
        }   catch (MqttException e) {
            System.err.println("Error MQTT: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
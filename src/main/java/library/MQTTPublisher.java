package library;

import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * MQTTPublisher publishes messages to an MQTT topic.
 * @author Jack Ortega
 * @author Neeraja Beesetti
 * @author Saanvi Dua
 * @author Yayun Tan
 * @version 1.0
 */
public class MQTTPublisher implements PropertyChangeListener {
    private static final Logger logger = LoggerFactory.getLogger(MQTTPublisher.class);
    private static MQTTPublisher instance;
    private final String broker = "tcp://test.mosquitto.org:1883";
    private final String topic = "aquarium/commands";
    private static MqttClient client;

    public static synchronized MQTTPublisher getInstance() {
        if (instance == null) {
            instance = new MQTTPublisher();
        }
        return instance;
    }

    public synchronized void startMQTTPublisher() {
        if (client != null && client.isConnected()) {
            logger.info("MQTT publisher is already running");
            JOptionPane.showMessageDialog(null, "MQTT publisher is already running", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        new Thread(this::connectMQTTPublisher).start();
    }

    private void connectMQTTPublisher() {
        if (client != null && client.isConnected()) {
            logger.info("MQTT publisher is already connected");
            return;
        }
        try {
            client = new MqttClient(broker, MqttClient.generateClientId());

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            client.connect(options);
            logger.info("MQTTPublisher connected to broker and ready to publish on topic: {}", topic);
            JOptionPane.showMessageDialog(null, "Connected to MQTT server", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (MqttException e) {
            logger.error("Error starting MQTTPublisher", e);
        }
    }

    public synchronized void stopMQTTPublisher() {
        if (client != null && client.isConnected()) {
            logger.info("Stopping MQTT publisher");
            try {
                client.disconnect();
                client.close();
                JOptionPane.showMessageDialog(null, "MQTT Publisher stopped", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (MqttException e) {
                logger.error("Error while disconnecting MQTT client", e);
                JOptionPane.showMessageDialog(null, "Failed to stop MQTT Client", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private synchronized void sendMessage(String message) {
        try {
            if (client != null && client.isConnected()) {
                MqttMessage mqttMessage = new MqttMessage(message.getBytes());
                mqttMessage.setQos(1);
                client.publish(topic, mqttMessage);
                logger.info("Published message to topic {}: {}", topic, message);
            } else {
                logger.warn("MQTTPublisher is not connected. Unable to publish message.");
            }
        } catch (MqttException e) {
            logger.error("Error sending message from MQTTPublisher", e);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("command")) {
            if (client != null) {
                sendMessage((String) evt.getNewValue());
            } else {
                logger.warn("MQTT Publisher not started, cannot send command: " + evt.getNewValue());
            }
        }
    }
}

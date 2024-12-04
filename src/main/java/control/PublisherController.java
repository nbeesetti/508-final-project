package control;

import library.MQTTPublisher;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * PublisherController class to handle creating MQTTPublisher, stopping, and sending messages.
 * @author Jack Ortega
 * @author Neeraja Beesetti
 * @author Saanvi Dua
 * @author Yayun Tan
 * @version 1.0
 */
public class PublisherController implements PropertyChangeListener {
	private static final Logger logger = LoggerFactory.getLogger(PublisherController.class);
	private static MQTTPublisher mqttPublisher;

	public static void startMQTTPublisher() {
		if (mqttPublisher == null) {
			logger.info("Starting MQTT publisher");
			String brokerUrl = "tcp://test.mosquitto.org:1883";
			String topic = "aquarium/commands";
			mqttPublisher = new MQTTPublisher(brokerUrl, topic);
			mqttPublisher.start();
			JOptionPane.showMessageDialog(null, "Connected to MQTT server", "Success", JOptionPane.INFORMATION_MESSAGE);
		} else {
			logger.info("MQTT publisher is already running");
		}
	}

	public static void stopMQTTPublisher() {
		if (mqttPublisher != null) {
			logger.info("Stopping MQTT publisher");
			try {
				mqttPublisher.stop();
				mqttPublisher = null;
				JOptionPane.showMessageDialog(null, "MQTT Client stopped", "Success", JOptionPane.INFORMATION_MESSAGE);
			} catch (MqttException e) {
				logger.error("Error while disconnecting MQTT client", e);
				JOptionPane.showMessageDialog(null, "Failed to stop MQTT Client", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("command")) {
			if (mqttPublisher != null) {
				mqttPublisher.sendMessage((String) evt.getNewValue());
			} else {
				logger.warn("MQTT Publisher not started, cannot send command: " + evt.getNewValue());
			}
		}
	}
}
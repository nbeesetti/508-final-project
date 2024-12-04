package control;

import library.MQTTPublisher;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MenuController class that listens for button clicks and calls the appropriate methods in the Client class.
 * This version supports starting both traditional TCP and MQTT clients.
 * @author Jack Ortega
 * @author Neeraja Beesetti
 * @author Saanvi Dua
 * @author Yayun Tan
 * @version 1.0
 */
public class MenuController extends JMenuBar implements ActionListener {
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "Start MQTT publisher":
				startMQTTPublisher();
				break;
			case "Stop publisher":
				stopMQTTPublisher();
				break;
			case "Exit":
				System.exit(0);
				break;
		}
	}

	private void startMQTTPublisher() {
		PublisherController.startMQTTPublisher();
		firePropertyChange("status", null, "MQTT connected");
	}

	private void stopMQTTPublisher() {
		PublisherController.stopMQTTPublisher();
		firePropertyChange("status", null, "MQTT disconnected");
	}
}

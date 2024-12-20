package control;

import library.MQTTPublisher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MenuController class that listens for and handles starting/stopping MQTT Publisher
 * @author Jack Ortega
 * @author Neeraja Beesetti
 * @author Saanvi Dua
 * @author Yayun Tan
 * @version 1.0
 */
public class MenuController extends JMenuBar implements ActionListener {
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
		MQTTPublisher.getInstance().startMQTTPublisher();
		Blackboard.getInstance().addPropertyChangeListener(MQTTPublisher.getInstance());
		Blackboard.getInstance().updateStatusLabel("MQTT connected");
	}

	private void stopMQTTPublisher() {
		Blackboard.getInstance().updateStatusLabel("MQTT disconnected");
		MQTTPublisher.getInstance().stopMQTTPublisher();
	}
}

package control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeSupport;

/**
 * Blackboard class to store shared data between classes.
 * The Blackboard class is a singleton, meaning that only one instance of it can exist.
 * Processes messages and updates internal state accordingly.
 * @author Jack Ortega
 * @author Neeraja Beesetti
 * @author Saanvi Dua
 * @author Yayun Tan
 * @version 1.0
 */
public class Blackboard extends PropertyChangeSupport {
	private static final Logger logger = LoggerFactory.getLogger(Blackboard.class);

	private static Blackboard instance;

	private Blackboard() {
		super(new Object());
	}

	public static Blackboard getInstance() {
		if (instance == null) instance = new Blackboard();
		return instance;
	}

	public void updateStatusLabel(String status) {
		firePropertyChange("status", null, status);
	}

	public void sendCommand(String command) {
		logger.info("Command clicked: " + command);
		firePropertyChange("command", null, command);
		updateStatusLabel(command);
	}

}

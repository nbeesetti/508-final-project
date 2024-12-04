package control;

import library.MQTTPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Main class to start the application.
 * This class is responsible for setting up control panel GUI
 * @author Jack Ortega
 * @author Neeraja Beesetti
 * @author Saanvi Dua
 * @author Yayun Tan
 * @version 1.0
 */
public class Main extends JFrame {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public Main() {
		super("Aquarium Control Panel");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		MenuController menuController = new MenuController();
		setJMenuBar(createMenuBar(menuController));

		AquariumPanel aquariumPanel = new AquariumPanel();
		add(aquariumPanel, BorderLayout.CENTER);

		add(createStatusPanel(), BorderLayout.SOUTH);

		setSize(600, 400);
		setLocationRelativeTo(null);
	}

	private JMenuBar createMenuBar(MenuController menuController) {
		JMenu fileMenu = new JMenu("Options");

		JMenuItem mqttConnectItem = new JMenuItem("Start MQTT publisher");
		mqttConnectItem.addActionListener(menuController);
		fileMenu.add(mqttConnectItem);

		JMenuItem pauseItem = new JMenuItem("Stop publisher");
		pauseItem.addActionListener(menuController);
		fileMenu.add(pauseItem);

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(menuController);
		fileMenu.add(exitItem);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		return menuBar;
	}

	private JPanel createStatusPanel() {
		JPanel statusPanel = new JPanel();
		JLabel statusLabel = new JLabel("Status: Not connected");
		statusPanel.add(statusLabel);

		addPropertyChangeListener("status", evt -> statusLabel.setText("Status: " + evt.getNewValue()));

		return statusPanel;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Main mainFrame = new Main();
			mainFrame.setVisible(true);
		});
	}
}
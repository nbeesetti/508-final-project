package control;

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
	public Main() {
		super("Aquarium Control Panel");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(10, 10));

		MenuController menuController = new MenuController();
		setJMenuBar(createMenuBar(menuController));

		AquariumPanel aquariumPanel = new AquariumPanel();
		add(aquariumPanel, BorderLayout.CENTER);

		StatusBarPanel statusBarPanel = new StatusBarPanel("Not connected");
		add(statusBarPanel, BorderLayout.SOUTH);
		Blackboard.getInstance().addPropertyChangeListener(statusBarPanel);

		setSize(600, 500);
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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Main mainFrame = new Main();
			mainFrame.setVisible(true);
		});
	}
}
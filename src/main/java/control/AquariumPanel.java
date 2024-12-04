package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AquariumPanel provides a GUI to control the Unity aquarium environment.
 * @author Jack Ortega
 * @author Neeraja Beesetti
 * @author Saanvi Dua
 * @author Yayun Tan
 * @version 1.0
 */
public class AquariumPanel extends JPanel {
	private static final Logger logger = LoggerFactory.getLogger(AquariumPanel.class);

	public AquariumPanel() {
		setLayout(new GridLayout(6, 2, 10, 10));
		initUI();
	}

	private void initUI() {
		// Fish Type Selector
		add(new JLabel("Select Fish Type:"));
		JComboBox<String> fishTypeDropdown = new JComboBox<>(new String[]{"Fish 1", "Fish 2", "Fish 3"});
		fishTypeDropdown.addActionListener(e -> handleFishTypeChange(fishTypeDropdown.getSelectedItem().toString()));
		add(fishTypeDropdown);

		// Fish Quantity Slider
		add(new JLabel("Set Fish Quantity:"));
		JSlider fishQuantitySlider = new JSlider(1, 50, 10);
		fishQuantitySlider.setMajorTickSpacing(10);
		fishQuantitySlider.setPaintLabels(true);
		fishQuantitySlider.setPaintTicks(true);
		fishQuantitySlider.addChangeListener(e -> handleFishQuantityChange(fishQuantitySlider.getValue()));
		add(fishQuantitySlider);

		// Feed Fish Button
		add(new JLabel("Feed Fish:"));
		JButton feedFishButton = new JButton("Feed Now");
		feedFishButton.addActionListener(this::handleFeedFish);
		add(feedFishButton);

		// Music Selector
		add(new JLabel("Background Music:"));
		JComboBox<String> musicDropdown = new JComboBox<>(new String[]{"Relaxing", "Ambient", "None"});
		musicDropdown.addActionListener(e -> handleMusicSelection(musicDropdown.getSelectedItem().toString()));
		add(musicDropdown);

		// Add Fish Button
		add(new JLabel("Add New Fish:"));
		JButton addFishButton = new JButton("Add Fish");
		addFishButton.addActionListener(this::handleAddFish);
		add(addFishButton);

		// Remove Fish Button
		add(new JLabel("Remove Fish:"));
		JButton removeFishButton = new JButton("Remove Fish");
		removeFishButton.addActionListener(this::handleRemoveFish);
		add(removeFishButton);

		// Reset Aquarium Button
		add(new JLabel("Reset Aquarium:"));
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(this::handleResetAquarium);
		add(resetButton);
	}

	private void handleFishTypeChange(String type) {
		sendCommand("fishType:" + type);
		firePropertyChange("status", null, "Fish type changed to: " + type);
	}

	private void handleFishQuantityChange(int quantity) {
		sendCommand("fishQuantity:" + quantity);
		firePropertyChange("status", null, "Fish quantity set to: " + quantity);
	}

	private void handleFeedFish(ActionEvent e) {
		sendCommand("feedFish");
		firePropertyChange("status", null, "Fish fed");
	}

	private void handleMusicSelection(String music) {
		sendCommand("music:" + music);
		firePropertyChange("status", null, "Music changed to: " + music);
	}

	private void handleAddFish(ActionEvent e) {
		sendCommand("addFish");
		firePropertyChange("status", null, "Fish added");
	}

	private void handleRemoveFish(ActionEvent e) {
		sendCommand("removeFish");
		firePropertyChange("status", null, "Fish removed");
	}

	private void handleResetAquarium(ActionEvent e) {
		sendCommand("resetAquarium");
		firePropertyChange("status", null, "Aquarium reset");
	}

	private void sendCommand(String command) {
		logger.info("Command clicked: " + command);
		firePropertyChange("command", null, command);
	}
}
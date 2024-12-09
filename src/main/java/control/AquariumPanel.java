package control;

import javax.swing.*;
import java.awt.*;

/**
 * AquariumPanel provides a GUI to control the Unity aquarium environment.
 * @author Jack Ortega
 * @author Neeraja Beesetti
 * @author Saanvi Dua
 * @author Yayun Tan
 * @version 1.0
 */
public class AquariumPanel extends JPanel {

	public AquariumPanel() {
		setLayout(new GridLayout(6, 2, 10, 10));
		initUI();
	}

	private void initUI() {
		// Fish Type Selector
		add(new JLabel("Select Fish Type:"));
		JComboBox<String> fishTypeDropdown = new JComboBox<>(new String[]{"Fish 1", "Fish 2", "Fish 3"});
		fishTypeDropdown.addActionListener(e -> handleAction("fishType:" + fishTypeDropdown.getSelectedItem()));
		add(fishTypeDropdown);

		// Fish Quantity Slider
		add(new JLabel("Set Fish Quantity:"));
		JSlider fishQuantitySlider = new JSlider(1, 50, 10);
		fishQuantitySlider.setMajorTickSpacing(10);
		fishQuantitySlider.setPaintLabels(true);
		fishQuantitySlider.setPaintTicks(true);
		fishQuantitySlider.addChangeListener(e -> handleAction("fishQuantity:" + fishQuantitySlider.getValue()));
		add(fishQuantitySlider);

		// Feed Fish Button
		add(new JLabel("Feed Fish:"));
		JButton feedFishButton = new JButton("Feed Now");
		feedFishButton.addActionListener(e -> handleAction("feedFish"));
		add(feedFishButton);

		// Music Selector
		add(new JLabel("Background Music:"));
		JComboBox<String> musicDropdown = new JComboBox<>(new String[]{"Relaxing", "Ambient", "None"});
		musicDropdown.addActionListener(e -> handleAction("music:" + musicDropdown.getSelectedItem()));
		add(musicDropdown);

		// Add Fish Button
		add(new JLabel("Add New Fish:"));
		JButton addFishButton = new JButton("Add Fish");
		addFishButton.addActionListener(e -> handleAction("addFish"));
		add(addFishButton);

		// Remove Fish Button
		add(new JLabel("Remove Fish:"));
		JButton removeFishButton = new JButton("Remove Fish");
		removeFishButton.addActionListener(e -> handleAction("removeFish"));
		add(removeFishButton);

		// Reset Aquarium Button
		add(new JLabel("Reset Aquarium:"));
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(e -> handleAction("resetAquarium"));
		add(resetButton);
	}

	private void handleAction(String command) {
		Blackboard.getInstance().sendCommand(command);
	}
}

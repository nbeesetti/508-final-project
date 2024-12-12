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
		setLayout(new BorderLayout(10, 10));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		initUI();
	}

	private void initUI() {
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);

		// Fish Type Selector
		gbc.gridx = 0;
		gbc.gridy = 0;
		controlPanel.add(new JLabel("Select Fish Type:"), gbc);
		JComboBox<String> fishTypeDropdown = new JComboBox<>(new String[]{"Fish 1", "Fish 2", "Fish 3"});
		fishTypeDropdown.addActionListener(e -> handleAction("fishType:" + fishTypeDropdown.getSelectedItem()));
		gbc.gridx = 1;
		controlPanel.add(fishTypeDropdown, gbc);

		// Fish Quantity Slider
		gbc.gridx = 0;
		gbc.gridy = 1;
		controlPanel.add(new JLabel("Set Fish Quantity:"), gbc);
		JSlider fishQuantitySlider = new JSlider(1, 50, 10);
		fishQuantitySlider.setMajorTickSpacing(10);
		fishQuantitySlider.setPaintLabels(true);
		fishQuantitySlider.setPaintTicks(true);
		fishQuantitySlider.addChangeListener(e -> handleAction("fishQuantity:" + fishQuantitySlider.getValue()));
		gbc.gridx = 1;
		controlPanel.add(fishQuantitySlider, gbc);

		// Feed Fish Button
		gbc.gridx = 0;
		gbc.gridy = 2;
		controlPanel.add(new JLabel("Feed Fish:"), gbc);
		JButton feedFishButton = new JButton("Feed Now");
		feedFishButton.addActionListener(e -> handleAction("feedFish"));
		gbc.gridx = 1;
		controlPanel.add(feedFishButton, gbc);

		// Music Selector
		gbc.gridx = 0;
		gbc.gridy = 3;
		controlPanel.add(new JLabel("Background Music:"), gbc);
		JComboBox<String> musicDropdown = new JComboBox<>(new String[]{"Relaxing", "Ambient", "None"});
		musicDropdown.addActionListener(e -> handleAction("music:" + musicDropdown.getSelectedItem()));
		gbc.gridx = 1;
		controlPanel.add(musicDropdown, gbc);

		// Add Fish Button
		gbc.gridx = 0;
		gbc.gridy = 4;
		controlPanel.add(new JLabel("Add New Fish:"), gbc);
		JButton addFishButton = new JButton("Add Fish");
		addFishButton.addActionListener(e -> handleAction("addFish"));
		gbc.gridx = 1;
		controlPanel.add(addFishButton, gbc);

		// Remove Fish Button
		gbc.gridx = 0;
		gbc.gridy = 5;
		controlPanel.add(new JLabel("Remove Fish:"), gbc);
		JButton removeFishButton = new JButton("Remove Fish");
		removeFishButton.addActionListener(e -> handleAction("removeFish"));
		gbc.gridx = 1;
		controlPanel.add(removeFishButton, gbc);

		// Reset Aquarium Button
		gbc.gridx = 0;
		gbc.gridy = 6;
		controlPanel.add(new JLabel("Reset Aquarium:"), gbc);
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(e -> handleAction("resetAquarium"));
		gbc.gridx = 1;
		controlPanel.add(resetButton, gbc);

		add(controlPanel, BorderLayout.CENTER);
	}

	private void handleAction(String command) {
		Blackboard.getInstance().sendCommand(command);
	}
}

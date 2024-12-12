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
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		initUI();
	}

	private void initUI() {
		setLayout(new BorderLayout(10, 10));

		// Fish panels
		JPanel fishPanelsContainer = new JPanel();
		fishPanelsContainer.setLayout(new GridLayout(1, 3, 10, 10));

		// Create fish panels
		fishPanelsContainer.add(createFishPanel("Swarm 1", 0,"/fish1.png"));
		fishPanelsContainer.add(createFishPanel("Swarm 2", 1, "/fish2.png"));
		fishPanelsContainer.add(createFishPanel("Swarm 3", 2, "/fish3.png"));

		add(fishPanelsContainer, BorderLayout.CENTER);

		// Bottom controls
		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(new GridLayout(3, 1, 10, 10));

		// Background music selection
		JPanel musicPanel = new JPanel();
		musicPanel.add(new JLabel("Background Music:"));
		JComboBox<String> musicDropdown = new JComboBox<>(new String[]{"Bubbles", "Waves"});
		musicDropdown.addActionListener(e -> handleAction("music " + musicDropdown.getSelectedItem()));
		musicPanel.add(musicDropdown);
		controlsPanel.add(musicPanel);

		// Feed fish button
		JButton feedFishButton = new JButton("Feed Fish");
		feedFishButton.addActionListener(e -> handleAction("feed"));
		controlsPanel.add(feedFishButton);

		// Reset aquarium button
		JButton resetAquariumButton = new JButton("Reset Aquarium");
		resetAquariumButton.addActionListener(e -> handleAction("reset"));
		controlsPanel.add(resetAquariumButton);

		// Add controls to the bottom
		add(controlsPanel, BorderLayout.SOUTH);
	}

	private JPanel createFishPanel(String label, int swarmIndex, String imagePath) {
		JPanel fishPanel = new JPanel(new GridBagLayout());
		fishPanel.setBorder(BorderFactory.createTitledBorder(label));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(5, 5, 5, 5);

		// Fish image
		fishPanel.add(loadImage(imagePath), gbc);

		// Add Fish button
		JButton addFishButton = new JButton("Add a Fish");
		addFishButton.addActionListener(e -> handleAction("addFish " + swarmIndex));
		fishPanel.add(addFishButton, gbc);

		// Remove Fish button
		JButton removeFishButton = new JButton("Remove a Fish");
		removeFishButton.addActionListener(e -> handleAction("removeFish " + swarmIndex));
		fishPanel.add(removeFishButton, gbc);

		return fishPanel;
	}

	private JLabel loadImage(String imagePath) {
		JLabel fishImageLabel = new JLabel();
		ImageIcon fishIcon = new ImageIcon(getClass().getResource(imagePath));
		Image scaledImage = fishIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		fishImageLabel.setIcon(new ImageIcon(scaledImage));
		return fishImageLabel;
	}


	private void handleAction(String command) {
		Blackboard.getInstance().sendCommand(command);
	}
}
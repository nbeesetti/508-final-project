package control;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * StatusBarPanel class to display status messages.
 * @author Jack Ortega
 * @author Neeraja Beesetti
 * @author Saanvi Dua
 * @author Yayun Tan
 * @version 1.0
 */
public class StatusBarPanel extends JPanel implements PropertyChangeListener {
	private JLabel statusLabel;
	
	public StatusBarPanel(String status) {
		setPreferredSize(new Dimension(getWidth(), 35));
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Color.GRAY),
				new EmptyBorder(5, 5, 5, 5)
		));
		statusLabel = new JLabel("Status: " + status, JLabel.CENTER);
		add(statusLabel);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("status"))
			statusLabel.setText("Status: " + evt.getNewValue());
	}

}
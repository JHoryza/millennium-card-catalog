package dev.horyza.mcc.ui.catalog;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dev.horyza.mcc.ui.GUI;
import dev.horyza.mcc.util.WrapLayout;

public class CardPanel extends JPanel {

	public CardPanel() {
		setLayout(new WrapLayout(FlowLayout.CENTER, 5, 5));
		setBackground(Color.DARK_GRAY);

		// Load cards
		for (int i = 10000; i < 15000; i++) {
			try {
				JLabel lblNewLabel = new JLabel();
				Image image = new ImageIcon(GUI.class.getResource("/dev/horyza/mcc/resources/" + i + ".jpg"))
						.getImage();
				ImageIcon scaledImage = new ImageIcon(image.getScaledInstance(89, 127, Image.SCALE_SMOOTH));
				lblNewLabel.setIcon(scaledImage);
				add(lblNewLabel);
			} catch (Exception e) {
				continue;
			}
		}
	}
}

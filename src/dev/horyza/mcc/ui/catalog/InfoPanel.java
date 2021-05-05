package dev.horyza.mcc.ui.catalog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import dev.horyza.mcc.ui.GUI;

public class InfoPanel extends JPanel {

	public InfoPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(20, 20, 5, 20));
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(250, 400));

		// Card image
		JLabel cardImage = new JLabel();
		cardImage.setIcon(new ImageIcon(GUI.class.getResource("/dev/horyza/mcc/resources/10000.jpg")));
		cardImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(cardImage);

		// Card name
		JLabel cardName = new JLabel();
		cardName.setPreferredSize(new Dimension(250, 50));
		cardName.setFont(new Font(cardName.getFont().getName(), Font.PLAIN, Math.min(20, 20)));
		cardName.setText("Ten Thousand Dragon");
		cardName.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(cardName);

		// Card description
		JTextArea cardDesc = new JTextArea();
		cardDesc.setBackground(Color.GRAY);
		cardDesc.setWrapStyleWord(true);
		cardDesc.setLineWrap(true);
		cardDesc.setEditable(false);
		cardDesc.setText(
				"Cannot be Normal Summoned/Set. Must be Special Summoned by Tributing monsters you control whose combined ATK & DEF is 10,000 or more. If Summoned this way, the ATK/DEF of this card becomes 10,000.");
		cardDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(cardDesc);
	}
}

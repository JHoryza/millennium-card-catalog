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

	public String cardImageSrc = "10000";
	public String cardNameText = "Ten Thousand Dragon";
	public String cardDescText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam odio justo, mattis non facilisis ac, venenatis et risus. Nulla facilisi.";
	
	public InfoPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(20, 20, 5, 20));
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(250, 400));

		// Card image
		JLabel cardImage = new JLabel();
		cardImage.setIcon(new ImageIcon(GUI.class.getResource("/dev/horyza/mcc/resources/" + cardImageSrc + ".jpg")));
		cardImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(cardImage);

		// Card name
		JLabel cardName = new JLabel();
		cardName.setPreferredSize(new Dimension(250, 50));
		cardName.setFont(new Font(cardName.getFont().getName(), Font.PLAIN, Math.min(20, 20)));
		cardName.setText(cardNameText);
		cardName.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(cardName);

		// Card description
		JTextArea cardDesc = new JTextArea();
		cardDesc.setBackground(Color.GRAY);
		cardDesc.setWrapStyleWord(true);
		cardDesc.setLineWrap(true);
		cardDesc.setEditable(false);
		cardDesc.setText(
				cardDescText);
		cardDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(cardDesc);
	}
}

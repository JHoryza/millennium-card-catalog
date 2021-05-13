package dev.horyza.mcc.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Rectangle2D;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.util.Util;

public class InfoPanel extends JPanel {

	private JLabel cardImage = new JLabel();
	private JLabel cardName = new JLabel();
	private JTextArea cardDesc = new JTextArea();
	
	public InfoPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(20, 20, 5, 20));
		setBackground(Color.GRAY);
		setMinimumSize(new Dimension(250, 400));
		setPreferredSize(new Dimension(250, 400));

		// Card image
		cardImage.setIcon(new ImageIcon(MainFrame.class.getResource("/dev/horyza/mcc/resources/10000.jpg")));
		
		cardImage.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		add(cardImage);

		// Card name
		cardName.setPreferredSize(new Dimension(250, 50));
		cardName.setFont(new Font("Arial", Font.PLAIN, 20));
		cardName.setText("Ten Thousand Dragon");
		cardName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		add(cardName);

		// Card description
		cardDesc.setBackground(Color.GRAY);
		cardDesc.setWrapStyleWord(true);
		cardDesc.setLineWrap(true);
		cardDesc.setEditable(false);
		cardDesc.setText("");
		cardDesc.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		add(cardDesc);
	}
	
	public void updateInfo(Card card) {
		cardImage.setIcon(new ImageIcon(MainFrame.class.getResource("/dev/horyza/mcc/resources/" + card.getId() + ".jpg")));
		cardName.setText(card.getName());
		cardDesc.setText(card.getDescription());
		Util.scaleText(cardDesc, card.getDescription(), 8.0f, 12.0f);
	}
}

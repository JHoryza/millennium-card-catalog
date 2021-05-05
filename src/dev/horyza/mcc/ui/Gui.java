package dev.horyza.mcc.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.ui.catalog.CardPanel;
import dev.horyza.mcc.ui.catalog.DeckPanel;
import dev.horyza.mcc.ui.catalog.FilterPanel;
import dev.horyza.mcc.ui.catalog.InfoPanel;
import dev.horyza.mcc.util.WrapLayout;

public class GUI extends JFrame {

	private FilterPanel filterPanel = new FilterPanel();
	private InfoPanel infoPanel = new InfoPanel();
	private CardPanel cardPanel = new CardPanel(this);
	private DeckPanel deckPanel = new DeckPanel(this);
	
	public GUI() {
		setTitle("YU-GI-OH");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setMinimumSize(new Dimension(800, 600));
		setContentPane(createContentPane());
	}

	private JPanel createContentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setOpaque(true);

		// Filter panel
		contentPane.add(filterPanel, BorderLayout.NORTH);

		// Info panel
		contentPane.add(infoPanel, BorderLayout.WEST);

		// Card panel
		JScrollPane cardScrollPane = new JScrollPane(cardPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(cardScrollPane, BorderLayout.CENTER);
		
		// Deck panel
		contentPane.add(deckPanel, BorderLayout.SOUTH);

		return contentPane;
	}
	
	public InfoPanel getInfoPanel() {
		return infoPanel;
	}
}

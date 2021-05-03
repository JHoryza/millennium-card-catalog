package dev.horyza.mcc.ui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dev.horyza.mcc.util.WrapLayout;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import net.miginfocom.swing.MigLayout;

public class Gui extends JFrame {

	/**
	 * Launch the application.
	 */
	public void display() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel cardPanel = new JPanel();
		JScrollPane contentPane = new JScrollPane(cardPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		cardPanel.setLayout(new WrapLayout(FlowLayout.CENTER, 5, 5));
		contentPane.setBackground(Color.DARK_GRAY);
		cardPanel.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		for (int i = 12301; i < 12317; i++) {
			JLabel lblNewLabel = new JLabel();
			Image image = new ImageIcon(Gui.class.getResource("/dev/horyza/mcc/resources/" + i + ".jpg")).getImage();
			ImageIcon scaledImage = new ImageIcon(image.getScaledInstance(89, 127, Image.SCALE_SMOOTH));
			lblNewLabel.setIcon(scaledImage);
			cardPanel.add(lblNewLabel);
		}
	}
}

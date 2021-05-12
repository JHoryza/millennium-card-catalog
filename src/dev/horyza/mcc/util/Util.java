package dev.horyza.mcc.util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Util {

	public static void scaleText(JComponent component, String text, float minSize, float maxSize) {
		JComponent parent = (JComponent) component.getParent();
		Font myFont = new Font("Arial", Font.PLAIN, component.getFont().getSize());
		FontMetrics myMetrics = new FontMetrics(myFont) {};
		Rectangle2D boundsOfString = myMetrics.getStringBounds(text, null);
		int width = (int) boundsOfString.getWidth();
		int height = (int) boundsOfString.getHeight();
		double ratio = (int) (parent.getSize().getHeight() / parent.getPreferredSize().getHeight());
		
		component.setSize(new Dimension(250, (int) (component.getSize().getHeight() * ratio)));
		if ((width * height) > (component.getSize().getHeight() * component.getPreferredSize().getWidth() * 0.75)) {
			component.setFont(component.getFont().deriveFont(minSize));
		} else {
			component.setFont(component.getFont().deriveFont(maxSize));
		}
	}
	
	public static void setTextFit(JTextArea cardDesc, String text) {
	    Font originalFont = (Font)cardDesc.getClientProperty("originalfont"); // Get the original Font from client properties
	    if (originalFont == null) { // First time we call it: add it
	        originalFont = cardDesc.getFont();
	        cardDesc.putClientProperty("originalfont", originalFont);
	    }

	    int stringWidth = cardDesc.getFontMetrics(originalFont).stringWidth(text);
	    int componentWidth = cardDesc.getWidth();

	    if (stringWidth > componentWidth) { // Resize only if needed
	        // Find out how much the font can shrink in width.
	        double widthRatio = (double)componentWidth / (double)stringWidth;

	        int newFontSize = (int)Math.floor(originalFont.getSize() * widthRatio); // Keep the minimum size

	        // Set the label's font size to the newly determined size.
	        cardDesc.setFont(new Font(originalFont.getName(), originalFont.getStyle(), newFontSize));
	    } else
	        cardDesc.setFont(originalFont); // Text fits, do not change font size

	    cardDesc.setText(text);
	}
}

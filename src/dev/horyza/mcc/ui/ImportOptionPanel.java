package dev.horyza.mcc.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.services.DatabaseHandler;
import dev.horyza.mcc.ui.MainFrame.CardList;

public class ImportOptionPanel extends JOptionPane {

	public ImportOptionPanel(MainFrame frame) {
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JTextArea input = new JTextArea(20, 20);
		input.setWrapStyleWord(true);
		input.setLineWrap(true);
		
		contentPane.add(new JLabel("Enter card ID's (limit 1 per line):"));
		contentPane.add(new JScrollPane(input));
		
		int choice = showConfirmDialog(null, contentPane, "Import Collection", JOptionPane.OK_CANCEL_OPTION);
		
		if (choice == JOptionPane.OK_OPTION) {
			String ids[] = input.getText().split("\\r?\\n");
			List<Integer> list = new ArrayList<Integer>();
			for (String id : ids) {
				try {
					list.add(Integer.parseInt(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			DatabaseHandler db = new DatabaseHandler();
			List<Card> cards = db.selectFiltered(CardList.CATALOG.getTableName(), list);
			frame.getCollectionPanel().loadCards(cards);
		}
	}
}

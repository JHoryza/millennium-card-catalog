package dev.horyza.mcc.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
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
import dev.horyza.mcc.ui.MainFrame.CardType;

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
			try {
				String inputLine[] = input.getText().split("\\r?\\n");
				HashMap<Integer, Integer> idList = new HashMap<>();
				for (String line : inputLine) {
					try {
						String values[] = line.split("\t");
						int id = Integer.parseInt(values[0]);
						int quantity = values.length == 1 ? 1 : Integer.parseInt(values[1]);
						idList.put(id, quantity);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				DatabaseHandler db = new DatabaseHandler();
				List<Card> cardList = db.selectById(CardType.CATALOG.getTableName(), idList);
				String table = CardType.COLLECTION.getTableName();
				db.clear(table);
				db.add(table, cardList);
				frame.getCollectionPanel().getCardList().clear();
				frame.getCollectionPanel().getCardLabels().clear();
				frame.getCollectionPanel().addCards(cardList);
				frame.getCardScrollPane().setViewportView(frame.getCollectionPanel());
				frame.setActiveCardType(CardType.COLLECTION);
			} catch (Exception e) {

			}
		}
	}
}

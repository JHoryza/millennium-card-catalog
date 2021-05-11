package dev.horyza.mcc.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dev.horyza.mcc.services.DatabaseHandler;

public class MainFrame extends JFrame {
	
	private DatabaseHandler db = new DatabaseHandler();
	private MenuBar menuBar = new MenuBar(this);
	private FilterPanel filterPanel = new FilterPanel(this);
	private InfoPanel infoPanel = new InfoPanel();
	private CardPanel catalogPanel = new CardPanel(this, CardList.CATALOG);
	private CardPanel collectionPanel = new CardPanel(this, CardList.COLLECTION);
	private DeckPanel deckPanel = new DeckPanel(this);
	private JScrollPane cardScrollPane = new JScrollPane(catalogPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	public MainFrame() {
		setTitle("YU-GI-OH");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setMinimumSize(new Dimension(800, 600));
		setContentPane(createContentPane());
		setJMenuBar(menuBar);
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
		cardScrollPane.getVerticalScrollBar().setUnitIncrement(32);
		contentPane.add(cardScrollPane, BorderLayout.CENTER);
		
		// Deck panel
		contentPane.add(deckPanel, BorderLayout.SOUTH);

		return contentPane;
	}
	
	public InfoPanel getInfoPanel() {
		return infoPanel;
	}
	
	public CardPanel getCatalogPanel() {
		return catalogPanel;
	}
	
	public CardPanel getCollectionPanel() {
		return collectionPanel;
	}
	
	public JScrollPane getCardScrollPane() {
		return cardScrollPane;
	}
	
	protected enum CardList {
		
		CATALOG("catalog"),
		COLLECTION("collection"),
		DECK("deck");
		
		private final String tableName;
		
		CardList(String tableName) {
			this.tableName = tableName;
		}
		
		public String getTableName() {
			return tableName;
		}
	}
}

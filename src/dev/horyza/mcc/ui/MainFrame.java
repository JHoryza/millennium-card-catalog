package dev.horyza.mcc.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dev.horyza.mcc.services.DatabaseHandler;
import dev.horyza.mcc.util.OverlapLayout;
import dev.horyza.mcc.util.WrapLayout;

public class MainFrame extends JFrame {
	
	private DatabaseHandler db = new DatabaseHandler();
	private MenuBar menuBar = new MenuBar(this);
	private FilterPanel filterPanel;
	private InfoPanel infoPanel;
	private CardPanel catalogPanel;
	private CardPanel collectionPanel;
	private DeckPanel deckPanel;
	private JScrollPane cardScrollPane;
	private CardList actvCrdLst = CardList.CATALOG;
	
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
		filterPanel = new FilterPanel(this);
		contentPane.add(filterPanel, BorderLayout.NORTH);

		// Info panel
		infoPanel = new InfoPanel();
		contentPane.add(infoPanel, BorderLayout.WEST);

		// Card panel
		catalogPanel = new CardPanel(this, CardList.CATALOG);
		catalogPanel.setLayout(new WrapLayout(FlowLayout.CENTER, 5, 5));
		catalogPanel.setBackground(Color.DARK_GRAY);
		
		// Collection panel
		collectionPanel = new CardPanel(this, CardList.COLLECTION);
		collectionPanel.setLayout(new WrapLayout(FlowLayout.CENTER, 5, 5));
		collectionPanel.setBackground(Color.DARK_GRAY);
		
		// Card Scroll Pane
		cardScrollPane = new JScrollPane(catalogPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		cardScrollPane.getVerticalScrollBar().setUnitIncrement(32);
		contentPane.add(cardScrollPane, BorderLayout.CENTER);
		
		// Deck panel
		deckPanel = new DeckPanel(this);
		deckPanel.setVisible(false);
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
	
	
	
	public DeckPanel getDeckPanel() {
		return deckPanel;
	}
	
	public CardList getActvCrdLst() {
		return actvCrdLst;
	}
	
	public void setActvCrdLst(CardList cardList) {
		this.actvCrdLst = cardList;
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

package dev.horyza.mcc.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import dev.horyza.mcc.util.WrapLayout;

public class MainFrame extends JFrame {
	
	private MenuBar menuBar = new MenuBar(this);
	private FilterPanel filterPanel;
	private InfoPanel infoPanel;
	private CardPanel catalogPanel;
	private CardPanel collectionPanel;
	private DeckPanel deckPanel;
	private JScrollPane catalogScrollPane;
	private JScrollPane collectionScrollPane;
	private JTabbedPane cardTabbedPane;
	private JScrollPane infoScrollPane;
	private CardType activeCardType = CardType.CATALOG;
	
	public MainFrame() {
		setTitle("Millenium-Card-Catalog");
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
		infoScrollPane = new JScrollPane(infoPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		infoScrollPane.getVerticalScrollBar().setUnitIncrement(32);
		InputMap im = infoScrollPane.getVerticalScrollBar().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
		im.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");
		contentPane.add(infoScrollPane, BorderLayout.WEST);

		// Catalog panel
		catalogPanel = new CardPanel(this, CardType.CATALOG, new WrapLayout(FlowLayout.CENTER, 5, 5));
		catalogPanel.setBackground(Color.DARK_GRAY);
		catalogScrollPane = new JScrollPane(catalogPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		catalogScrollPane.getVerticalScrollBar().setUnitIncrement(32);
		
		// Collection panel
		collectionPanel = new CardPanel(this, CardType.COLLECTION, new WrapLayout(FlowLayout.CENTER, 5, 5));
		collectionPanel.setBackground(Color.DARK_GRAY);
		collectionScrollPane = new JScrollPane(collectionPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		collectionScrollPane.getVerticalScrollBar().setUnitIncrement(32);
		
		// Card Tabbed Pane
		cardTabbedPane = new JTabbedPane();
		cardTabbedPane.addTab("Catalog", catalogScrollPane);
		cardTabbedPane.addTab("Collection", collectionScrollPane);
		
		contentPane.add(cardTabbedPane, BorderLayout.CENTER);
		
		// Deck panel
		deckPanel = new DeckPanel(this);
		//deckPanel.setVisible(false);
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
	
	public DeckPanel getDeckPanel() {
		return deckPanel;
	}
	
	public JScrollPane getCatalogScrollPane() {
		return catalogScrollPane;
	}
	
	public JScrollPane getCollectionScrollPane() {
		return collectionScrollPane;
	}
	
	public JTabbedPane getCardTabbedPane() {
		return cardTabbedPane;
	}
	
	public JScrollPane getInfoScrollPane() {
		return infoScrollPane;
	}
	
	public CardType getActiveCardType() {
		return activeCardType;
	}
	
	public void setActiveCardType(CardType cardType) {
		this.activeCardType = cardType;
	}
	
	protected enum CardType {
		
		CATALOG("catalog"),
		COLLECTION("collection"),
		DECK("deck");
		
		private final String tableName;
		
		CardType(String tableName) {
			this.tableName = tableName;
		}
		
		public String getTableName() {
			return tableName;
		}
	}
}

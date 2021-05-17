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
	private FilterPanel filterPanel = new FilterPanel(this);
	private InfoPanel infoPanel = new InfoPanel();
	private CardPanel catalogPanel = new CardPanel(this, new WrapLayout(FlowLayout.CENTER, 5, 5), CardList.CATALOG);
	private CardPanel collectionPanel = new CardPanel(this, new WrapLayout(FlowLayout.CENTER, 5, 5), CardList.COLLECTION);
	private CardPanel deckPanel = new CardPanel(this, new OverlapLayout(new Point(20, 0)), CardList.DECK);
	private JScrollPane cardScrollPane = new JScrollPane(catalogPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
		contentPane.add(filterPanel, BorderLayout.NORTH);

		// Info panel
		contentPane.add(infoPanel, BorderLayout.WEST);

		// Card panel
		cardScrollPane.getVerticalScrollBar().setUnitIncrement(32);
		catalogPanel.setBackground(Color.DARK_GRAY);
		contentPane.add(cardScrollPane, BorderLayout.CENTER);
		
		// Deck panel
		JScrollPane deckScrollPane = new JScrollPane(deckPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		deckPanel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(deckScrollPane, BorderLayout.SOUTH);

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
	
	public CardPanel getDeckPanel() {
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

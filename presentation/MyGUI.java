package presentation;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.dnd.DnDConstants;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.TreeSelectionModel;

import listener.Listener;
import listener.TreeDragSource;
import listener.myChartChangeListener;

import org.jfree.chart.JFreeChart;

import testRuns.Constant;
import testRuns.Constant.MyChartType;

@SuppressWarnings("serial")
public class MyGUI extends JFrame  {
	
	 public static final double GOLDEN_RATIO = 1.6180339887498948482;
	 public static final double RELATIVE_LENGTH_OF_LONGER_SIDE = 1 / GOLDEN_RATIO;
	 public static final double RELATIVE_LENGTH_OF_SHORTER_SIDE = 1 - (1 / GOLDEN_RATIO);
	 private static final int screenHeight = 500;
	 private static final int LENGTH_OF_LONGER_SIDE_FOR_RATIO = (int) (screenHeight * RELATIVE_LENGTH_OF_LONGER_SIDE);
	 private static final int LENGTH_OF_SHORTER_SIDE_FOR_RATIO = (int) (screenHeight * RELATIVE_LENGTH_OF_SHORTER_SIDE);
	 private static final int MIN_LENGTH_OF_LONGER_SIDE_FOR_RATIO = (int) (50 * RELATIVE_LENGTH_OF_LONGER_SIDE);
	 private static final int MIN_LENGTH_OF_SHORTER_SIDE_FOR_RATIO = (int) (50 * RELATIVE_LENGTH_OF_SHORTER_SIDE);

	TreeDragSource ds;
	TreeDropTarget dt;
	
	JPanel main, q1code, q2display, q3tables, q4explorer, searchBar;
	JTextField searchField, coding;
	JButton searchButton, exportButton, test123;
	JLabel mylabel;
	TitledBorder consoleTitle, graphTitle, tableTitle, explorerTitle;
	
	JScrollPane scroll;
	GridBagConstraints c;
	JFreeChart myChart;
	JScrollPane treeScroll, tableScroll, consoleScroll;
	//JTree jtree;
	MyTreeType jtree;
	Listener myListener;
	myChartChangeListener charts;
	String StockName = "INTC";
	Border loweredetched;
	//ChartPanel CP = new ChartPanel(null);

	
	public MyGUI () {
		//Creating the explorer, top-left pane.
		main = new JPanel();
		
		//main.setLayout(new GridBagLayout());
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		consoleTitle = BorderFactory.createTitledBorder(loweredetched, "Console");
		consoleTitle.setTitleJustification(TitledBorder.CENTER);
		graphTitle = BorderFactory.createTitledBorder(loweredetched, "Graph");
		graphTitle.setTitleJustification(TitledBorder.CENTER);
		tableTitle = BorderFactory.createTitledBorder(loweredetched, "Table(s)");
		tableTitle.setTitleJustification(TitledBorder.CENTER);
		explorerTitle = BorderFactory.createTitledBorder(loweredetched, "Explorer");
		explorerTitle.setTitleJustification(TitledBorder.CENTER);

		//new
		GroupLayout layout = new GroupLayout(main);
		main.setLayout(layout);
		
		c = new GridBagConstraints();
		myListener = new Listener();
		q2display= new JPanel();
		
		//Q2Display
		q2display.setLayout(new BorderLayout());
		c.gridx = 1;
		c.gridy = 1;
		
		q2display.add(Constant.CP, BorderLayout.CENTER);
		q2display.revalidate();
		//main.add(q2display, c);

		//Q4 Explorer/
		//Q4 Explorer/Listener/Search Bar
		//Clicking on 
		Action search = new AbstractAction(){
			public void actionPerformed(ActionEvent arg0) {
				String input = searchField.getText();
				jtree.addStocktoFolder ("Other", input);
				Update.UpdateChart(input, MyChartType.LineChart);
				Update.UpdateTables(input);
			}
		};

		//Q4 Explorer/GUI
		searchBar = new JPanel();
		JLabel myTitle = new JLabel ("Manipulating Stock Data Program");
		//myTitle.setFont(new Font("Calibri", Font.PLAIN, myTitle.getSize());
	    c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
		searchBar.add(myTitle, c);
		
		searchField = new JTextField("Enter Stock Symbol", 8);
		searchField.addActionListener(search);
		searchBar.add(searchField);

		searchButton = new JButton("Go");
		searchButton.addActionListener(search);
		searchBar.add(searchButton);
		
		q4explorer = new JPanel(new BorderLayout());		
		q4explorer.add(BorderLayout.NORTH, searchBar);
		
		//Q4 Explorer/GUI/Tree -----------------------------------------------------------------------------
		MyTreeNode root = new MyTreeNode ("All Stocks");
		jtree = new MyTreeType(root);
		jtree.addFolder("New Folder");
		jtree.addFolder("Other");
		jtree.addStocktoFolder ("Oil and Gas", "XOM");
		jtree.addFolder("Banks");
		jtree.addFolder("Oil and Gas");
		
		jtree.addStocktoFolder ("Banks", "TD.TO");
		jtree.addStocktoFolder ("Banks", "RY.TO");
		jtree.addStocktoFolder ("Banks", "BMO.TO");
		jtree.addStocktoFolder ("Banks", "CM.TO");
		jtree.addStocktoFolder ("Banks", "NA.TO");
		jtree.addStocktoFolder ("Banks", "BNS.TO");

		jtree.addFolder("Tech");
		jtree.addStocktoFolder ("Tech", "GOOG");
		jtree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		jtree.addTreeSelectionListener(myListener);
		
		treeScroll = new JScrollPane(jtree);
		q4explorer.add(BorderLayout.CENTER, treeScroll);
		ds = new TreeDragSource(jtree, DnDConstants.ACTION_COPY_OR_MOVE);
		dt = new TreeDropTarget(jtree);
		setVisible(true);
		// Tree -----------------------------------------------------------------------------
		
		c.gridx = 0;
		c.gridy = 0;
		//main.add(q4explorer, c);
		
		q1code = new JPanel();
		c.gridx = 1;
		c.gridy = 0;
		//main.add(q1code, c);
				
		q3tables= new JPanel(new BorderLayout());

		//tableScroll = new JScrollPane(Constant.tabbedPane);
		//tableScroll.revalidate();
		Constant.tabbedPane.setVisible(true);
		q3tables.add(Constant.tabbedPane, BorderLayout.CENTER);
		Constant.tabbedPane.addMouseListener(new PopClickListener());
		q3tables.revalidate();
		c.gridx = 0;
		c.gridy = 1;
		//c.weightx = 1;
		//c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		//main.add(q3tables, c);
		
		//Q1 Explorer
		Action code = new AbstractAction(){
			public void actionPerformed(ActionEvent arg0) {
				String input = coding.getText();
				coding.setText("YourName>");
				try {
					Constant.doc.insertString(Constant.doc.getLength(), "\n" + input, null );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			}
		};
		q1code.setLayout(new BorderLayout());
		coding = new JTextField("YourName>");
		JTextPane Console = new JTextPane();
		Console.setText("Welcome");
		Constant.doc = Console.getStyledDocument();
		consoleScroll = new JScrollPane(Console);
		consoleScroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    });
		
		q1code.add(consoleScroll, BorderLayout.CENTER);
		q1code.add(coding, BorderLayout.SOUTH);
		coding.setPreferredSize(coding.getPreferredSize());
		coding.addActionListener(code);
		
		mylabel = new JLabel();
		main.add(mylabel);

		q1code.setBorder(consoleTitle);
		q2display.setBorder(graphTitle);
		q3tables.setBorder(tableTitle);
		q4explorer.setBorder(explorerTitle);
		
		//q1code.add

	    layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(q4explorer, MIN_LENGTH_OF_SHORTER_SIDE_FOR_RATIO, LENGTH_OF_SHORTER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                        .addComponent(q1code, MIN_LENGTH_OF_SHORTER_SIDE_FOR_RATIO, LENGTH_OF_SHORTER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(q3tables, MIN_LENGTH_OF_LONGER_SIDE_FOR_RATIO, LENGTH_OF_LONGER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                        .addComponent(q2display, MIN_LENGTH_OF_LONGER_SIDE_FOR_RATIO, LENGTH_OF_LONGER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                ).addGap(NORMAL));
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(q4explorer, MIN_LENGTH_OF_SHORTER_SIDE_FOR_RATIO, LENGTH_OF_SHORTER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                        .addComponent(q3tables, MIN_LENGTH_OF_SHORTER_SIDE_FOR_RATIO, LENGTH_OF_SHORTER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(q1code, MIN_LENGTH_OF_LONGER_SIDE_FOR_RATIO, LENGTH_OF_LONGER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                        .addComponent(q2display, MIN_LENGTH_OF_LONGER_SIDE_FOR_RATIO, LENGTH_OF_LONGER_SIDE_FOR_RATIO, Short.MAX_VALUE)
                ).addGap(NORMAL));		
		main.validate();
		main.repaint();
		
		this.add(main);
		this.setVisible(true);
		this.repaint();
	}
}

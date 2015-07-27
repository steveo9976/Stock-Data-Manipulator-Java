package presentation;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import manipulation.Transformation;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;

import testRuns.Constant;
import testRuns.Constant.MyChartType;
import testRuns.TSPoint;
import dataRetrieval.RetrieveData;

public class Update {
	public Update(){
	}
	private static TSPoint[] UpdateTS (String input){
		TSPoint TS [];
		TS = new TSPoint[Constant.NUMBER_OF_DAYS];
				
		//Retrieval
		RetrieveData stuff = new RetrieveData();
		TS = stuff.YahooAPIRetrieval(TS, input);
		
		//Transformation	
		Transformation myTransform = new Transformation(TS);
		TS = myTransform.calculateNightChange();
		TS = myTransform.extractDate();
		return TS;
	}
	public static void UpdateChart(String input, MyChartType graphType) {
		RetrieveData stuff = new RetrieveData();
		JFreeChart myChart = null;
		switch (graphType) {
        	case LineChart:
        		TSPoint TS [] = UpdateTS (input);			
        		DefaultCategoryDataset dataset = stuff.createCategoryDataset(TS);
        		myChart = ChartFactory.createLineChart(TS[0].getSymbol().concat(": Price over Days"), "Date","Closing Price($)", dataset, PlotOrientation.VERTICAL, true,true,false);
        		break;
        	case XYChart:
        		XYDataset XYdataset = stuff.createXYDataset(Constant.XVals, Constant.YVals);
        		myChart = ChartFactory.createXYLineChart("Price Over Days", "Date", "CLosing Price ($)", XYdataset);
        		break;
		}
		Constant.CP.setChart(myChart);
	}
	public static void UpdateTables(String input) {
		boolean existsAlready = false;
		for (JTable table:Constant.Tables){
			if (table.getValueAt(0, 0).equals(input)){
				existsAlready = true;
			}
		}
		if (existsAlready){
			return;
		} else {
			//Constant.mySQL.addTable(input);
			DefaultTableModel model = new DefaultTableModel (null, Constant.columnNames);
			TSPoint TS [] = UpdateTS (input);			
			for (int i = 0; i < Constant.NUMBER_OF_DAYS - 1; i ++){
				String[] row = {TS[i].stockSymbol, String.valueOf(TS[i].getFullDate()), TS[i].getOpen().toString(), TS[i].getClose().toString(), String.valueOf(TS[i].getVolume())};
				model.addRow(row);
				//Constant.mySQL.insertRowSQL(input, row);
			}
			JTable newTable = new JTable (model);
			newTable.addMouseListener(new PopClickListener());
			newTable.setRowSelectionAllowed(true);
			newTable.setCellSelectionEnabled(true);
			newTable.setColumnSelectionAllowed(true);
			Constant.Tables.add(newTable);
			UpdateTabbedPane();
	
		}		
	}
	public static void UpdateTabbedPane () {
		Constant.tabbedPane.removeAll();
		for (JTable table:Constant.Tables){
			table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			JScrollPane scrolltab = new JScrollPane(table);
			Constant.tabbedPane.addTab(table.getValueAt(0, 0).toString(), scrolltab);
		}

	}
}

/*package presentation;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import dataRetrieval.RetrieveData;
import testRuns.TSPoint;

//Creating a singleton chart class so only one chart is active at any time
@SuppressWarnings("serial")
public class MyChartType extends JFreeChart {

	private static JFreeChart singleton = null;
			
	MyChartType(TSPoint [] TS, RetrieveData stuff) {
	}

	public static JFreeChart getSingleton(TSPoint [] TS, RetrieveData stuff) {
		if(singleton == null){
		    singleton = ChartFactory.createLineChart("Price over Days", "Date","Closing Price($)", stuff.createDataset(TS),
			         PlotOrientation.VERTICAL,
			         true,true,false);
		} else {
			refreshMyChart(TS, stuff);
		}

		return singleton;
	}

	protected static void refreshMyChart (TSPoint [] TS, RetrieveData stuff) {
		singleton = ChartFactory.createLineChart("Price over Days", "Date","Closing Price($)", stuff.createDataset(TS),
		         PlotOrientation.VERTICAL,
		         true,true,false);
 
	}

}
*/
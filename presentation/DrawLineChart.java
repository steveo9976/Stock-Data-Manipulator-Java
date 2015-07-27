/*
package presentation;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import testRuns.Constant;
import testRuns.TSPoint;

public class DrawLineChart extends ApplicationFrame //extends JPanel 
{
	private static final long serialVersionUID = 1L;
			TSPoint[] TS = null;
			public DrawLineChart (String applicationTitle , String chartTitle, TSPoint[] TS)
			   {
				  //super(applicationTitle);
				  this.TS = TS;
			      
			      JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "Date","Closing Price($)", createDataset(),
			         PlotOrientation.VERTICAL,
			         true,true,false);
			         
			      //ChartPanel chartPanel = new ChartPanel( lineChart );
			      //chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
			      //setContentPane(chartPanel);
			   }

		   public DefaultCategoryDataset createDataset()
		   {
		      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		      for (int i = 0; i < Constant.NUMBER_OF_DAYS; i++){
		    	  dataset.addValue(TS[i].daychange, "dollars", TS[i].getRealTime());
		      }
		      return dataset;
		   }
}
*/
	

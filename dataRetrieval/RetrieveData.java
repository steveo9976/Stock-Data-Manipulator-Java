package dataRetrieval;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import testRuns.TSPoint;
import testRuns.Constant; 
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class RetrieveData {
	
	public RetrieveData() {
		
	}
	
	public TSPoint[] YahooAPIRetrieval(TSPoint[] TS, String symbol) {		
		
		//Make a constant
		Calendar from = Calendar.getInstance();
		from.add(Calendar.YEAR, -Constant.NUMBER_OF_YEARS); // from one month ago 
		
		Stock stockofinterest = YahooFinance.get(symbol, from, Constant.to, Interval.DAILY);
		
		Collection<HistoricalQuote> SOIData = stockofinterest.getHistory();
		
		//Build a complete time-series TS (list with key and value being yearmonthdate and TSPoint)
		
		//0 is most recent day, as index increases, you go further into the past
		//Need the size to not be arbitrary.

		int i = 0;
		
		for (Iterator<HistoricalQuote> iterator = SOIData.iterator(); iterator.hasNext();){
			//TSPI = TSPointInfo
			HistoricalQuote TSPI = iterator.next();
			Calendar rawDate = TSPI.getDate();
			TSPoint createdTSPoint = new TSPoint(rawDate, TSPI);
			TS[i] = createdTSPoint;
			i++;
		}
		
		return TS;
	}
	
	public DefaultCategoryDataset createCategoryDataset(TSPoint[] TS)
	   {
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	      for (int i = 0; i < Constant.NUMBER_OF_DAYS; i++){
	    	  dataset.addValue(TS[i].daychange, "dollars", TS[i].getRealTime());
	      }
	      return dataset;
	 }
	public XYDataset createXYDataset (double[] xVals, double[] yVals){
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        XYSeries series = new XYSeries("Random");
        for (int i = 0; i < xVals.length; i++) {
            series.add(xVals[i], yVals[i]);
        }
        xySeriesCollection.addSeries(series);
        return xySeriesCollection;
	}
}

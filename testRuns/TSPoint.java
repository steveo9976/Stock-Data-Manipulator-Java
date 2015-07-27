package testRuns;
import java.math.BigDecimal;
import java.util.Calendar;

import yahoofinance.histquotes.HistoricalQuote;

public class TSPoint {
	public Calendar realTime;
	public String stockSymbol;
	public int Month;
	public int Date;
	public int Year;
	public int FullDate;
	private BigDecimal Low;
	private BigDecimal High;
	private BigDecimal Open;
	private BigDecimal Close;
	private BigDecimal AdjClose;
	public long Volume;
	public double daychange;
	public double nightchange;
	//Initializing the Time-Series Point
	public TSPoint (Calendar realTime, HistoricalQuote TSPI){
		this.realTime = realTime;
		this.stockSymbol = TSPI.getSymbol();
		this.Low = TSPI.getLow();
		this.High = TSPI.getHigh();
		this.Open = TSPI.getOpen();
		this.Close = TSPI.getClose();
		this.AdjClose = TSPI.getAdjClose();
		this.Volume = TSPI.getVolume();
		double close = Close.doubleValue();
		double open = Open.doubleValue();
		this.daychange = (100*((close - open)/open));
		//100 * (close-open)/open
	}

	//Retrieving relevant pieces.
	public int getMonth() { return this.Month; }
	public int getDate() { return this.Date; }
	public int getYear() { return this.Year; }
	public String getSymbol() { return this.stockSymbol; }

	public BigDecimal getLow() { return this.Low; }
	public BigDecimal getHigh() { return this.High; }
	public BigDecimal getOpen() { return this.Open; }
	public BigDecimal getClose() { return this.Close; }
	public BigDecimal getAdjClose() { return this.AdjClose; }
	public long getVolume() { return this.Volume; }
	public double getDayChange() { return this.daychange; }
	public double getNightChange() { return this.nightchange; }
	public int getFullDate() { return this.FullDate; }
	public Calendar getRealTime() { return this.realTime; }
	
	//setting the nightchange
	public void setNightChange(double nightchange) {this.nightchange = nightchange;}
}

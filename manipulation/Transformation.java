package manipulation;
import java.util.Calendar;

import testRuns.Constant;
import testRuns.TSPoint;

public class Transformation {
	
	private TSPoint[] TS;
	public Transformation (TSPoint[] TS){
		this.TS = TS;
	}

	public TSPoint[] calculateNightChange(){
		TS[Constant.NUMBER_OF_DAYS-1].setNightChange(0); //Does not exist
		for (int j = 0; j < Constant.NUMBER_OF_DAYS-1; j++){
			double open = TS[j].getOpen().doubleValue();
			double prevVal = TS[j+1].getClose().doubleValue();			
			TS[j].setNightChange(100*((open - prevVal)/prevVal));
		}	
		return TS;
	}
	public TSPoint[] extractDate() {
		for (int i = 0; i < Constant.NUMBER_OF_DAYS; i++){
			TS[i].Month = TS[i].realTime.get(Calendar.MONTH);
			TS[i].Date = TS[i].realTime.get(Calendar.DATE);
			TS[i].Year = TS[i].realTime.get(Calendar.YEAR);
			TS[i].FullDate = (10000*TS[i].Year + 100*(TS[i].Month + 1) + (TS[i].Date + 1));
		}
		return TS;
	}
}

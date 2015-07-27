package testRuns;
import javax.swing.JFrame;

import presentation.MyGUI;

//Want to create DataSet from Yahoo
//Going to take historical data and store all values by day. Then display desired values over time.
//TS = TimeSeries
public class RunTime {
	public static <E> void main(String[] args) {		
		
		//Constant.mySQL.retrieveTablesSQL();
		//GUI
		MyGUI test = new MyGUI();
		test.setTitle("Time Series Data Manipulator");
		test.setSize(1200, 1200);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}
}

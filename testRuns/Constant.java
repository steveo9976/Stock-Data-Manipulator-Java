package testRuns;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.text.StyledDocument;

import mySQL.MySQLiteJDBC;

import org.jfree.chart.ChartPanel;

public final class Constant {
	public static int NUMBER_OF_DAYS = 251; //251 for one year
	public static final int NUMBER_OF_YEARS = 1;
	public static final Calendar to = Calendar.getInstance();
	public static ChartPanel CP = new ChartPanel(null);
	public static String[] columnNames = {"Symbol", "Date", "Opening Price", "Closing Price", "Volume"};
	public static ArrayList <JTable> Tables = new ArrayList <JTable> ();
	public static JTabbedPane tabbedPane = new JTabbedPane();
	
	public enum MyChartType {LineChart, XYChart};
	
	public static StyledDocument doc;

	//public static MySQLiteJDBC mySQL = new MySQLiteJDBC();
	
	public static double [] XVals = new double[NUMBER_OF_DAYS];
	public static double [] YVals = new double[NUMBER_OF_DAYS];
}

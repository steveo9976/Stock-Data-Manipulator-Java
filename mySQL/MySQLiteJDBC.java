package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import presentation.PopClickListener;
import presentation.Update;
import testRuns.Constant;

public class MySQLiteJDBC {
    private Connection c = null;
    private Statement stmt = null;
    private ArrayList <String> sqlTables = new ArrayList <String> ();

	public MySQLiteJDBC () {
	}
	
	public void addTable (String tableName){
		this.sqlTables.add(tableName);
	    try {
		    Class.forName("org.sqlite.JDBC");
			this.c = DriverManager.getConnection("jdbc:sqlite:stockdata.db");
		    this.stmt = this.c.createStatement();
		    String createSQL = "CREATE TABLE " + tableName + " " +
		            "(SYMBOL TEXT, DATE INT, OPEN REAL, CLOSE REAL, VOLUME INT)"; 
		    this.stmt.executeUpdate(createSQL);
		    this.stmt.close();
		    c.close();
	    } catch (Exception e) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
	    }
	}
	
	public void insertRowSQL (String tableName, String [] row) {
		try {
			this.c.setAutoCommit (false);
			System.out.println("Opened");
			this.stmt = this.c.createStatement();
		
			String insertSQL = "INSERT INTO " + tableName + " (SYMBOL,DATE,OPEN,CLOSE,VOLUME) " +
					"VALUES (" + row[0] + ", " + row[1] + ", " + row[2] +", " + row[3] + ", " + row[4] + ");"; 
		
			this.stmt.executeUpdate(insertSQL);
			this.stmt.close();
			this.c.commit();
			this.c.close();
		} catch (Exception e) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	}
	
	public void retrieveTablesSQL () {
		try {
		    this.c.setAutoCommit(false);
		    this.stmt = this.c.createStatement();
		    for (String tableName:this.sqlTables){
				DefaultTableModel model = new DefaultTableModel (null, Constant.columnNames);
			    ResultSet rs = this.stmt.executeQuery( "SELECT * FROM " + tableName + ";" );
			    while ( rs.next() ) {
			    	String symbol = rs.getString("smybol");
			    	int date = rs.getInt("date");
			    	float open = rs.getFloat("open");
			    	float close = rs.getFloat("close");
			    	int volume = rs.getInt("volume");
			    	String [] row = {symbol, String.valueOf(date), String.valueOf(open), String.valueOf(close), String.valueOf(volume)};
			    	model.addRow(row);
			    }
			    rs.close();	    	
				JTable newTable = new JTable (model);
				newTable.addMouseListener(new PopClickListener());
				newTable.setRowSelectionAllowed(true);
				newTable.setCellSelectionEnabled(true);
				newTable.setColumnSelectionAllowed(true);
				Constant.Tables.add(newTable);
				Update.UpdateTabbedPane();   
		    }
		    this.stmt.close();
		    this.c.close();
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
}


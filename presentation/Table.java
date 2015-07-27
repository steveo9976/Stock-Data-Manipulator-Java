package presentation;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import testRuns.Constant;
import testRuns.TSPoint;

@SuppressWarnings("serial")
public class Table extends JTable {
	public DefaultTableModel model;
	public Table (DefaultTableModel model) {
		super (model);
		this.model = model;
	}
	public void updateTable (TSPoint [] TS) {
		for (int i = 0; i < Constant.NUMBER_OF_DAYS - 1; i ++){
			this.model.removeRow(i);
		}
		for (int i = 0; i < Constant.NUMBER_OF_DAYS - 1; i ++){
			String[] row = {TS[i].getOpen().toString(), TS[i].getClose().toString(), String.valueOf(TS[i].getVolume()), String.valueOf(TS[i].getNightChange())};
			this.model.addRow(row);
		}
	}
}

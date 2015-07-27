package presentation;

import testRuns.Constant.MyChartType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import testRuns.Constant;

@SuppressWarnings("serial")
public class PopUpMenu extends JPopupMenu{
	JMenuItem XVals, YVals, GraphXY;
	public PopUpMenu() {
		XVals = new JMenuItem ("Set X values");
		XVals.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent event){
				int index = Constant.tabbedPane.getSelectedIndex();
				int colIndex = Constant.Tables.get(index).getSelectedColumn();
			
				for (int i = 0; i < Constant.NUMBER_OF_DAYS - 1; i++) {
					Constant.XVals[i] = Double.parseDouble((String) Constant.Tables.get(index).getValueAt(i, colIndex));
				}
			}
		});
		
		add(XVals);

		YVals = new JMenuItem ("Set Y values");
		YVals.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent event){
				int index = Constant.tabbedPane.getSelectedIndex();
				int colIndex = Constant.Tables.get(index).getSelectedColumn();
			
				for (int i = 0; i < Constant.NUMBER_OF_DAYS - 1; i++) {
					Constant.YVals[i] = Double.parseDouble((String) Constant.Tables.get(index).getValueAt(i, colIndex));
				}
			}
		});
		
		add(YVals);
		
		GraphXY = new JMenuItem ("Graph XY Data");
		GraphXY.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent event){
				Update.UpdateChart(null, MyChartType.XYChart);
			}
		});
		
		add(GraphXY);
	}
}

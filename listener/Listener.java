package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.event.MouseEvent;

import presentation.MyTreeType;
import presentation.Update;
import testRuns.Constant.MyChartType;


public class Listener implements ActionListener, TreeSelectionListener, MouseListener {
	public Listener() {
	}

	public void actionPerformed(ActionEvent event){
	}

	//Trying to double click
	public void mouseClicked(MouseEvent e) {
		MyTreeType tree = (MyTreeType) e.getSource();
		if (e.getClickCount() == 2){
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		    if (selectedNode.isLeaf()) {
		    	if (selectedNode.toString().equals("New Folder")){
		    		String newFolderName = JOptionPane.showInputDialog(null, "Enter new folder's name.", "Folder Name here");
		    		tree.addFolder(newFolderName);
		    	} else{
		    	String selectedNodeName = selectedNode.toString();
		    	Update.UpdateChart(selectedNodeName, MyChartType.LineChart);
		    	Update.UpdateTables(selectedNodeName);
		    	}
		    }
		}
		// TODO Auto-generated method stub
	}

	public void mouseEntered(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseExited(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mousePressed(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseReleased(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	
	public void valueChanged(TreeSelectionEvent e) {
		MyTreeType tree = (MyTreeType) e.getSource();
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
	    if (selectedNode.isLeaf()) {
	    	if (selectedNode.toString().equals("New Folder")){
	    		String newFolderName = JOptionPane.showInputDialog(null, "Enter new folder's name.", "Folder Name here");
	    		tree.addFolder(newFolderName);
	    	} else{
	    	String selectedNodeName = selectedNode.toString();
	    	Update.UpdateChart(selectedNodeName, MyChartType.LineChart);
	    	Update.UpdateTables(selectedNodeName);
	    	}
	    }
	}
	
}
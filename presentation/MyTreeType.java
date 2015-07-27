package presentation;

import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;



@SuppressWarnings("serial")
public class MyTreeType extends JTree{
	public DefaultTreeModel model;
	public MyTreeNode root;

	public MyTreeType (MyTreeNode root){
		super(root);
		this.model = (DefaultTreeModel) this.getModel();
		this.root = root;	
		this.root.setIsRoot(true);
	}
	
	//Folders can only have one level of depth
	public void addFolder (String folderName){
		//Check if folder exists
		boolean folderExistance = false;
		Enumeration<?> children = root.children();
		while(children.hasMoreElements()){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) children.nextElement();
			if (node.toString().equals(folderName)){
				System.out.println("Folder exists already.");
				folderExistance = true;
				break;
			}
		}
		if (folderExistance == false){
			MyTreeNode folderNode = new MyTreeNode(folderName);
			if (folderName != "New Folder"){folderNode.setIsFolder(true);}
			this.root.add(folderNode);
			this.model.nodesWereInserted(this.root, new int[]{this.root.getChildCount()-1});				
		}
	}
	public void addStocktoFolder (String destinationFolderName, String stockName){
		boolean alreadyExists = false;
		Enumeration<?> children = this.root.children();
		while(children.hasMoreElements()){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) children.nextElement();
			if (node.toString().equals(destinationFolderName)){
				DefaultMutableTreeNode folderNode = node;
				Enumeration<?> stocks = node.children();
				while(stocks.hasMoreElements()){
					node = (DefaultMutableTreeNode) stocks.nextElement();
					if (node.toString().equals(stockName)){
						alreadyExists = true;
					}
				}
				if (!alreadyExists){
					MyTreeNode newStock = new MyTreeNode (stockName);
					newStock.setIsStock(true);
					folderNode.add(newStock);
					this.model.nodesWereInserted(folderNode, new int[]{folderNode.getChildCount()-1});				
				}
				break;
			}
		}
	}
}

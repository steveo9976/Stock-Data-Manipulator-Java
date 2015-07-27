package presentation;

import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class MyTreeNode extends DefaultMutableTreeNode {
	public boolean isRoot = false;
	public boolean isFolder = false;
	public boolean isStock = false;
	
	public MyTreeNode (String Val){
		super(Val);
	}
	public void setIsRoot(boolean val) {this.isRoot = val;}
	public void setIsFolder(boolean val) {this.isFolder = val;}
	public void setIsStock(boolean val) {this.isStock = val;}
}

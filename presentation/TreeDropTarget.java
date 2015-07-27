package presentation;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

class TreeDropTarget implements DropTargetListener {
	  DropTarget target;
	  MyTreeType targetTree;

	  public TreeDropTarget(MyTreeType tree) {
	    targetTree = tree;
	    target = new DropTarget(targetTree, this);
	  }

	  /*
	   * Drop Event Handlers
	   */
	  private MyTreeNode getNodeForEvent(DropTargetDragEvent dtde) {
	    Point p = dtde.getLocation();
	    DropTargetContext dtc = dtde.getDropTargetContext();
	    MyTreeType tree = (MyTreeType) dtc.getComponent();
	    TreePath path = tree.getClosestPathForLocation(p.x, p.y);
	    return (MyTreeNode) path.getLastPathComponent();
	  }

	  public void dragEnter(DropTargetDragEvent dtde) {
	    MyTreeNode node = getNodeForEvent(dtde);
	    if (!node.isFolder){
	    	dtde.rejectDrag();
	    } else {
	    	// start by supporting move operations
	    	//dtde.acceptDrag(DnDConstants.ACTION_MOVE);
	    	dtde.acceptDrag(dtde.getDropAction());
	    }
	  }

	  public void dragOver(DropTargetDragEvent dtde) {
		    MyTreeNode node = getNodeForEvent(dtde);
		    if (!node.isFolder){
		    	dtde.rejectDrag();
		    } else {
		    	// start by supporting move operations
		    	//dtde.acceptDrag(DnDConstants.ACTION_MOVE);
		    	dtde.acceptDrag(dtde.getDropAction());
		    }
	  }

	  public void dragExit(DropTargetEvent dte) {
	  }

	  public void dropActionChanged(DropTargetDragEvent dtde) {
	  }

	  public void drop(DropTargetDropEvent dtde) {
	    Point pt = dtde.getLocation();
	    DropTargetContext dtc = dtde.getDropTargetContext();
	    MyTreeType tree = (MyTreeType) dtc.getComponent();
	    TreePath parentpath = tree.getClosestPathForLocation(pt.x, pt.y);
	    DefaultMutableTreeNode parent = (DefaultMutableTreeNode) parentpath
	        .getLastPathComponent();
	    
	    /*
	    if (parent.isLeaf()) {
	      dtde.rejectDrop();
	      return;
	    }
	    */

	    try {
	      Transferable tr = dtde.getTransferable();
	      DataFlavor[] flavors = tr.getTransferDataFlavors();
	      for (int i = 0; i < flavors.length; i++) {
	        if (tr.isDataFlavorSupported(flavors[i])) {
	          dtde.acceptDrop(dtde.getDropAction());
	          TreePath p = (TreePath) tr.getTransferData(flavors[i]);
	          DefaultMutableTreeNode node = (DefaultMutableTreeNode) p
	              .getLastPathComponent();
	          DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
	          model.insertNodeInto(node, parent, 0);
	          dtde.dropComplete(true);
	          return;
	        }
	      }
	      dtde.rejectDrop();
	    } catch (Exception e) {
	      e.printStackTrace();
	      dtde.rejectDrop();
	    }
	  }
	}
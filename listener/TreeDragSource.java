package listener;

import java.awt.Point;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import presentation.MyTreeNode;
import presentation.MyTreeType;


public class TreeDragSource implements DragSourceListener, DragGestureListener {

	  DragSource source;
	  DragGestureRecognizer recognizer;
	  TransferableTreeNode transferable;
	  DefaultMutableTreeNode oldNode;
	  MyTreeType sourceTree;

	  public TreeDragSource(MyTreeType tree, int actions) {
	    sourceTree = tree;
	    source = new DragSource();
	    recognizer = source.createDefaultDragGestureRecognizer(sourceTree,
	        actions, this);
	  }

	  /*
	   * Drag Gesture Handler
	   */
	  public void dragGestureRecognized(DragGestureEvent dge) {
	    TreePath path = sourceTree.getSelectionPath();
	    if ((path == null) || (path.getPathCount() <= 1)) {
	      // We can't move the root node or an empty selection
	      return;
	    }
	    oldNode = (DefaultMutableTreeNode) path.getLastPathComponent();
	    transferable = new TransferableTreeNode(path);
	    source.startDrag(dge, DragSource.DefaultMoveDrop, transferable, this);
	  }

	  /*
	   * Drag Event Handlers
	   */
	  public void dragEnter(DragSourceDragEvent dsde) {
	  }

	  public void dragExit(DragSourceEvent dse) {
	  }

	  public void dragOver(DragSourceDragEvent dsde) {
	  }

	  public void dropActionChanged(DragSourceDragEvent dsde) {
	    System.out.println("Action: " + dsde.getDropAction());
	    System.out.println("Target Action: " + dsde.getTargetActions());
	    System.out.println("User Action: " + dsde.getUserAction());
	  }

	  public void dragDropEnd(DragSourceDropEvent dsde) {
	    /*
	     * to support move or copy, we have to check which occurred:
	     */
	    System.out.println("Drop Action: " + dsde.getDropAction());

	    if (dsde.getDropSuccess()) {
	    ((DefaultTreeModel)sourceTree.getModel()).removeNodeFromParent(oldNode); 
	    }
	    
	  }
	}

	//TreeDropTarget.java
	//A quick DropTarget that's looking for drops from draggable MyTreeTypes.
	//

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
	    if (node.isLeaf() || node.isFolder == true) {
	      dtde.rejectDrag();
	    } else {
	      // start by supporting move operations
	      //dtde.acceptDrag(DnDConstants.ACTION_MOVE);
	      dtde.acceptDrag(dtde.getDropAction());
	    }
	  }

	  public void dragOver(DropTargetDragEvent dtde) {
	    MyTreeNode node = getNodeForEvent(dtde);
	    if (node.isLeaf() || node.isFolder == true) {
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
	    if (parent.isLeaf()) {
	      dtde.rejectDrop();
	      return;
	    }

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

package presentation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopClickListener extends MouseAdapter{

	public void mousePressed (MouseEvent e){
		if (e.isPopupTrigger()){
			doPop(e);
		}
	}
	
	public void mouseReleased (MouseEvent e){
		if (e.isPopupTrigger()){
			doPop(e);
		}
	}
	
	private void doPop (MouseEvent e){
		PopUpMenu menu = new PopUpMenu();
		menu.show(e.getComponent(), e.getX(), e.getY());
	}
	
	public PopClickListener() {
		super();
	}

}

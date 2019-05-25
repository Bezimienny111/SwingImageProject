package test1;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class RightCliclTableMouseListener extends MouseAdapter {

	
		private JTable table;
		
	    public RightCliclTableMouseListener(JTable table) {
	        this.table = table;
	    }
	     
	    @Override
	    public void mousePressed(MouseEvent event) {
	        // zaznacza w tabeli miejsce w którym klikneliœmy by nie by³o potrzeby najpierw naciskania gdy wyskoczy popMenu
	      
	    	
	    	try {
				Point point = event.getPoint();
					int currentRow = table.rowAtPoint(point);
					table.setRowSelectionInterval(currentRow, currentRow);
			} catch (java.lang.IllegalArgumentException  e) {
			
			}
		
			
	    }
	}
	
	



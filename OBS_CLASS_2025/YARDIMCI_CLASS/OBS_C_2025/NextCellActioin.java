package OBS_C_2025;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class NextCellActioin	extends AbstractAction {
        private JTable table;
         String nerden = "";
        public NextCellActioin(JTable table,String nerden) {
            this.table = table;
            this.nerden = nerden;
        }
        public void actionPerformed(ActionEvent e) {
        	
            int col = table.getSelectedColumn();
            int row = table.getSelectedRow();
            int colCount = table.getColumnCount();
            int rowCount = table.getRowCount();
            col++;
             if (nerden.equals("toplu"))
        	{
             	if (col > 3) 
            	{
            		  col = 1;
            		  row++;
            	}
        	}
             else
             {
            if (col >= colCount) {
            	if (nerden.equals("recete"))
            	{
                col = 1;
            	}
            	else
            	{
            		 col = 1;
            	}
                row++;
            }
             }
            if (row >= rowCount) 
            {
                row = 0;
            }
            
       
           if (col == 1 )
           {
        	      table.editCellAt(row == -1 ? 0:row, col);
                  table.transferFocus();
           }
           
           table.getSelectionModel().setSelectionInterval(row, row);
           table.getColumnModel().getSelectionModel().setSelectionInterval(col, col);
        }

  

}

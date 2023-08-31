package OBS_C_2025;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTable;

public class Next_Cell_Kereste extends AbstractAction {

	 private JTable table;
     String nerden = "";
    public Next_Cell_Kereste(JTable table,String nerden) {
        this.table = table;
        this.nerden = nerden;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		 int col = table.getSelectedColumn();
         int row = table.getSelectedRow();
         int colCount = table.getColumnCount();
         int rowCount = table.getRowCount();
         col++;
          if (nerden.equals("kereste_giris"))
     	{
        	  if (col >= colCount) {
                  col = 1;
                  row++;
              }
        	  if (col == 1 )
              {
           	      table.editCellAt(row == -1 ? 0:row, col);
                  table.transferFocus();
              }
     	}
          else  if (nerden.equals("kereste_cikis"))
        	  
       	{
        	  if (col >= colCount) {
                  col = 0;
                  row++;
              }
        	  if (col == 0 )
              {
           	      table.editCellAt(row == -1 ? 0:row, col);
                  table.transferFocus();
              }
        	 
       	}
          if (row >= rowCount) 
          {
              row = 0;
          }
          table.getCellEditor().stopCellEditing();
          table.getSelectionModel().setSelectionInterval(row, row);
          table.getColumnModel().getSelectionModel().setSelectionInterval(col, col);
		
	}

}
package OBS_2025;

import java.sql.ResultSet;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings({"serial" , "static-access" })
public class HESAP_PLANI_DETAY extends JInternalFrame {
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	private JTable table;
	private JTextField textField;
	public HESAP_PLANI_DETAY()
	{
		
		setTitle("HESAP PLANI DETAY");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 600, 463);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		splitPane.setRightComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				String status = (String)table.getModel().getValueAt(row,0);
				 if( table.getRowSorter() == null)
				 {
					if (status.length() == 3)
					{
						c.setBackground(oac.satBackColor);
						c.setForeground(oac.satForeColor);
						Font fnt = new Font(table.getFont().getFontName(),1 ,12);
						c.setFont(fnt);
					} else 
					{
						c.setBackground(table.getBackground());
						c.setForeground(table.getForeground());
					}   
					if (isRowSelected(row)) {
						c.setBackground(table.getSelectionBackground());
						c.setForeground(table.getSelectionForeground());
	                } 
				}
				 else 
				 {
					 int satt = table.getRowSorter().convertRowIndexToModel(row);
					 String statuss = (String)table.getModel().getValueAt(satt,0);
					 if (statuss.length() == 3)
						{
							c.setBackground(oac.satBackColor);
							c.setForeground(oac.satForeColor);
							Font fnt = new Font(table.getFont().getFontName(),1 ,12);
							c.setFont(fnt);
						} 
					 else
						 {
						 	c.setBackground(table.getBackground());
						 	c.setForeground(table.getForeground());
						 }
					 if (isRowSelected(row)) 
					 {
						 c.setBackground(table.getSelectionBackground());
						 c.setForeground(table.getSelectionForeground());
					 } 
				}
				return c;
			}
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 30));
		panel.setMaximumSize(new Dimension(0, 30));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Arama");
		lblNewLabel.setBounds(21, 7, 46, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    arama();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    arama();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    arama();
			  }
			});
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(83, 4, 321, 20);
		panel.add(textField);
		textField.setColumns(10);
		hisset();
		OBS_MAIN.btnYazici.setEnabled(true);
	}
	private void hisset() 
	{
		long startTime = System.currentTimeMillis(); 
		ResultSet rs = null ;
		try
		{
		rs = c_Access.hsp_pln("");
		if (!rs.isBeforeFirst() ) {  
		    return;
		}
		GRID_TEMIZLE.grid_temizle(table);
		table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(80);
			
			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(275);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);
			
			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(70);
			
			Dimension dd = th.getPreferredSize();
		    dd.height = 30;
		    th.setPreferredSize(dd); 
			th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);
			
			for (int i = 4; i<= 24; i++)
			{
				tc = tcm.getColumn(i);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
			}
			 long endTime = System.currentTimeMillis();
			 long estimatedTime = endTime - startTime;
			 double seconds = (double)estimatedTime/1000; 
			 OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
		}
		catch (Exception ex)
		{
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
			//JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	public void arama()  
	{
		if (textField.getText().equals(""))
		{
			table.setRowSorter(null);
		}
		else
		{
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
		RowFilter<TableModel, Object> rf = null;
		rf = RowFilter.regexFilter("(?iu)" +  textField.getText().toLowerCase() , 1);
		sorter.setRowFilter(rf);
	    table.setRowSorter(sorter);
	    table.revalidate();
	    table.repaint();
		}
	}
}

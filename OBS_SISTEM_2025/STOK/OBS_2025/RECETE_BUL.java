package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;


@SuppressWarnings({"serial","static-access"})
public class RECETE_BUL extends JDialog {
	private JTable table;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	private Obs_TextFIeld textField;


	/**
	 * Create the dialog.
	 */
	public RECETE_BUL() {
		setModal(true);
		setTitle("RECETE ARAMA");
		setBounds(100, 100, 880, 394);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(null));
		panel.setMinimumSize(new Dimension(0, 40));
		panel.setMaximumSize(new Dimension(0, 40));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Arama");
		lblNewLabel.setBounds(10, 11, 38, 14);
		panel.add(lblNewLabel);
		
		textField = new Obs_TextFIeld();
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
			    arama();
			    getContentPane().setCursor(oac.DEFAULT_CURSOR);
			  }
			  public void removeUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
			    arama();
			    getContentPane().setCursor(oac.DEFAULT_CURSOR);
			  }
			  public void insertUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
			    arama();
			    getContentPane().setCursor(oac.DEFAULT_CURSOR);
			  }
			});
		textField.setBounds(58, 8, 292, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		splitPane.setRightComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					int row = table.getSelectedRow();
					if (table.getRowSorter()!=null) {
					    row = table.getRowSorter().convertRowIndexToModel(row);
					    oac.stk_kodu = 	table.getModel().getValueAt(row, 0).toString() ;
						dispose();
					}
					else
					{
						oac.stk_kodu = 	table.getModel().getValueAt(table.getSelectedRow(), 0).toString() ;
						dispose();
					}
				}
			}
		});
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
	
		hisset();
}

public void hisset()
{
	try {
	ResultSet rs = null ;
	
		rs = f_Access.recete_arama();
	
	
	if (!rs.isBeforeFirst() ) {  
		
	    return;
	} 
	else
	{
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
				tc.setMinWidth(80);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(200);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(70);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(70);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(70);

				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				
				String deger;
				String[] parts;
				Font bigFont;
					try {
						deger = GLOBAL.setting_oku("STK_RAPORLAMA").toString();
						deger = deger.substring(1, deger.length()-1);
						parts = deger.split(",");
						bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
						table.setFont(bigFont);
					} catch (IOException e) {
						e.printStackTrace();
					}
				
	}
	} catch (Exception ex) {
		 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
	//	JOptionPane.showMessageDialog(null, ex.getMessage());
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
		sorter.setStringConverter(new TableStringConverter() {
	        @Override
	        public String toString(TableModel model, int row, int column) {
	            return model.getValueAt(row, column).toString().toLowerCase();
	        }
	    });
	    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText().toString().toLowerCase(),1,2));
	    table.setRowSorter(sorter);
	}
}
}

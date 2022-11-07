package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

import net.proteanit.sql.DbUtils;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class URUN_ARAMA extends JDialog {
	private JTable table;
	static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			URUN_ARAMA dialog = new URUN_ARAMA();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public URUN_ARAMA() {
		setModal(true);
		setTitle("URUN_ARAMA");
		setBounds(100, 100, 880, 394);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
	
			JSplitPane splitPane = new JSplitPane();
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane.setDividerSize(0);
			splitPane.setResizeWeight(0.0);
			getContentPane().add(splitPane, BorderLayout.CENTER);
			
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 191, 255)));
			panel.setMinimumSize(new Dimension(0, 40));
			panel.setMaximumSize(new Dimension(0, 40));
			splitPane.setLeftComponent(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Arama");
			lblNewLabel.setBounds(10, 11, 38, 14);
			panel.add(lblNewLabel);
			
			textField = new JTextField();
			textField.getDocument().addDocumentListener(new DocumentListener() {
				  public void changedUpdate(DocumentEvent e) {
					  getContentPane().setCursor(WAIT_CURSOR);
				    arama();
				    getContentPane().setCursor(DEFAULT_CURSOR);
				  }
				  public void removeUpdate(DocumentEvent e) {
					  getContentPane().setCursor(WAIT_CURSOR);
				    arama();
				    getContentPane().setCursor(DEFAULT_CURSOR);
				  }
				  public void insertUpdate(DocumentEvent e) {
					  getContentPane().setCursor(WAIT_CURSOR);
				    arama();
				    getContentPane().setCursor(DEFAULT_CURSOR);
				  }
				});
			textField.setBounds(58, 8, 292, 20);
			panel.add(textField);
			textField.setColumns(10);
			
			JScrollPane scrollPane = new JScrollPane();
			splitPane.setRightComponent(scrollPane);
			
			table = new JTable(){
				public boolean isCellEditable(int row, int column) {     return false;          }
			};
			table.setGridColor(oac.gridcolor);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) 
					{
						int row = table.getSelectedRow();
						if (table.getRowSorter()!=null) {
						    row = table.getRowSorter().convertRowIndexToModel(row);
						    GLOBAL.stk_kodu = 	table.getModel().getValueAt(row, 1).toString() ;
							dispose();
						}
						else
						{
							GLOBAL.stk_kodu = 	table.getModel().getValueAt(table.getSelectedRow(), 1).toString() ;
							dispose();
						}
					}
				}
			});
			table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			scrollPane.setViewportView(table);
		    GLOBAL.stk_kodu = "";
			hisset();
	}

	public void hisset()
	{
		try {
		ResultSet rs = null ;
		if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		{
			rs = oac.sTOK_MSSQL.urun_arama();
		}
		else
		{
			rs = oac.sTOK_MYSQL.urun_arama();
		}
		GRID_TEMIZLE.grid_temizle(table);
		if (!rs.isBeforeFirst() ) {  
			
		    return;
		} 
		else
		{
			
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
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(70);
					
					tc = tcm.getColumn(5);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(3,false));
					tc.setMinWidth(70);
					
					for ( int i = 6 ; i <13; i ++)
					{
						tc = tcm.getColumn(i);
						tc.setHeaderRenderer(new SOLA());
						tc.setMinWidth(70);
					}

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
			JOptionPane.showMessageDialog(null, ex.getMessage());
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
	    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText().toLowerCase(),0,1,2));
	    table.setRowSorter(sorter);
 		}
	}
}

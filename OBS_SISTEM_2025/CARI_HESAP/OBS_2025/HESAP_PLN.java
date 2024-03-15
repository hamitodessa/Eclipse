package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings({"serial" , "static-access" })
public class HESAP_PLN extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar ,OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	private JTable table;
	private Obs_TextFIeld textField;

	public HESAP_PLN() throws ClassNotFoundException, SQLException {
		setResizable(false);
		setTitle("HESAP PLANI");
		setModal(true);
		setBounds(100, 100, 493, 513);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		splitPane.setDividerSize(1);
		contentPanel.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 40));
		
		panel.setMaximumSize(new Dimension(0, 40));
		panel.setLayout(null);
		
		textField = new Obs_TextFIeld(12,"");
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Down" )
				{	
					table.requestFocus();
					table.setRowSelectionInterval(0, 0);
				}
			}
		});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oac.hsp_hsp_kodu = 	table.getModel().getValueAt(table.convertRowIndexToModel(0), 0).toString() ;
				dispose();
			}
		});
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(83, 11, 326, 20);
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
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oac.hsp_hsp_kodu = "";
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(HESAP_PLN.class.getResource("/ICONLAR/exit.png")));
		btnNewButton.setBounds(430, 8, 31, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Arama");
		lblNewLabel.setBounds(10, 14, 63, 14);
		panel.add(lblNewLabel);
		
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		splitPane.setRightComponent(scrollPane);
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {     return false;  	}
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
			table.setGridColor(oac.gridcolor);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
				{
					int row = table.getSelectedRow();
					if (table.getRowSorter()!=null) {
						row = table.getRowSorter().convertRowIndexToModel(row);
						oac.hsp_hsp_kodu = 	table.getModel().getValueAt(row, 0).toString() ;
						dispose();
					}
					else
					{
						oac.hsp_hsp_kodu = 	table.getModel().getValueAt(table.getSelectedRow(), 0).toString() ;
						dispose();
					}
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					int row = table.getSelectedRow();
					if (table.getRowSorter()!=null) {
					    row = table.getRowSorter().convertRowIndexToModel(row);
					    oac.hsp_hsp_kodu = 	table.getModel().getValueAt(row, 0).toString() ;
						dispose();
					}
					else
					{
						oac.hsp_hsp_kodu = 	table.getModel().getValueAt(table.getSelectedRow(), 0).toString() ;
						dispose();
					}
				}
			}
		});
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
		oac.hsp_hsp_kodu = "" ;
		hisset("");
	}
	public void hisset(String sirala) 
	{
		try {
		ResultSet	rs = null;
		rs = c_Access.hp_pln();
		if (!rs.isBeforeFirst() ) {  
		    return;
		}
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
		tc.setMinWidth(50);
		
		tc = tcm.getColumn(3);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(50);
		
		tc.setHeaderValue( "CINS" );
		th.repaint();
		th.repaint();
		table.setRowSelectionInterval(0, 0);
		table.setRowHeight(22);
		
	    Dimension dd = table.getPreferredSize();
	    dd.height = 30;
	    th.setPreferredSize(dd); 
	    th.repaint();
	    
	    table.repaint();

	    String deger;
	    String[] parts;
	    Font bigFont;
	    deger = GLOBAL.setting_oku("CARI_HSPPLN").toString();
	    deger = deger.substring(1, deger.length()-1);
	    parts = deger.split(",");
	    bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
	    table.setFont(bigFont);
		} 
		catch (Exception ex) 
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	public void arama()  
	{
		if (textField.getText().equals(""))
			table.setRowSorter(null);
		else
		{
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
		sorter.setStringConverter(new TableStringConverter() {
	        @Override
	        public String toString(TableModel model, int row, int column) {
	            return model.getValueAt(row, column).toString().toLowerCase();
	        }
	    });
	    sorter.setRowFilter(RowFilter.regexFilter("(?iu)" + textField.getText().toLowerCase()));
	    table.setRowSorter(sorter);
	    table.revalidate();
	    table.repaint();
		}
	}
}

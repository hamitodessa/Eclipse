package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.SOLA;
import net.proteanit.sql.DbUtils;

import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(oac._ICar , oac._ICari_Loger);
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HESAP_PLN dialog = new HESAP_PLN();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public HESAP_PLN() throws ClassNotFoundException, SQLException {
		//setAlwaysOnTop(true);
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
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 40));
		
		panel.setMaximumSize(new Dimension(0, 40));
		panel.setLayout(null);
		
		textField = new JTextField();
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
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
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
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
		    @Override
		    public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row, int col) {
		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
				if (table.getRowSorter()!=null) {
				    row = table.getRowSorter().convertRowIndexToModel(row);
		        String status = (String)table.getModel().getValueAt(row,0);
		        if (status.length() == 3)
		        {
		            setBackground(Color.PINK);
		            setForeground(Color.BLUE);
		            Font fnt = new Font(table.getFont().getFontName(),1 ,12);
		            setFont(fnt);
		        } else {
		            setBackground(table.getBackground());
		            setForeground(table.getForeground());
		        }   
				}
				else
				{
					String status = (String)table.getModel().getValueAt(row,0);
			        if (status.length() == 3)
			        {
			            setBackground(Color.PINK);
			            setForeground(Color.BLUE);
			            Font fnt = new Font(table.getFont().getFontName(),1 ,12);
			            setFont(fnt);
			        } else {
			            setBackground(table.getBackground());
			            setForeground(table.getForeground());
			        }   
					
				}
		        return this;
		    }   
		});
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
	    
		table.setSelectionBackground(Color.PINK);
		table.setSelectionForeground(Color.BLUE);
		
		
			 String deger;
			 String[] parts;
			Font bigFont;
			deger = GLOBAL.setting_oku("CARI_HSPPLN").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			table.setFont(bigFont);
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
	    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText().toLowerCase()));
	    table.setRowSorter(sorter);
		}
	}
}

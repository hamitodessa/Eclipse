package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
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
import javax.swing.ListSelectionModel;

public class ADRES_LISTE extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS() ;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ADRES_LISTE dialog = new ADRES_LISTE();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ADRES_LISTE() {setResizable(false);
	setTitle("ADRES  DOKUMU");
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
	splitPane.setLeftComponent(panel);
	panel.setBorder(new LineBorder(new Color(0, 191, 255)));
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
			GLOBAL.hsp_hsp_kodu = 	table.getModel().getValueAt(table.convertRowIndexToModel(0), 0).toString() ;
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
			GLOBAL.hsp_hsp_kodu = "";
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
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	table.setFont(new Font("Calibri", Font.PLAIN, 14));
	table.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
			{
			int row = table.getSelectedRow();
			if (table.getRowSorter()!=null) {
			    row = table.getRowSorter().convertRowIndexToModel(row);
			    GLOBAL.hsp_hsp_kodu = 	table.getModel().getValueAt(row, 0).toString() ;
				dispose();
			}
			else
			{
				GLOBAL.hsp_hsp_kodu = 	table.getModel().getValueAt(table.getSelectedRow(), 0).toString() ;
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
				    GLOBAL.hsp_hsp_kodu = 	table.getModel().getValueAt(row, 0).toString() ;
					dispose();
				}
				else
				{
					GLOBAL.hsp_hsp_kodu = 	table.getModel().getValueAt(table.getSelectedRow(), 0).toString() ;
					dispose();
				}
			}
		}
	});
	
	scrollPane.setViewportView(table);
	GLOBAL.hsp_hsp_kodu = "" ;
	hisset("");
}
	public void hisset(String sirala) 
	{
		try {
	         long startTime = System.currentTimeMillis(); 
	ResultSet	rs = null;
	if (CONNECTION.adrdizinbilgi.han_sql.equals("MS SQL"))
	{
		rs =  oac.aDRES_MSSQL.adr_hpl();
	}
	else
	{
		rs =  oac.aDRES_MYSQL.adr_hpl();
	}
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
  		 long endTime = System.currentTimeMillis();
  		 long estimatedTime = endTime - startTime; 
  		 double seconds = (double)estimatedTime/1000; 
  		 OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

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

package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SOLA;
import net.proteanit.sql.DbUtils;

@SuppressWarnings({"serial","static-access"})
public class ADRESLER extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(OBS_SIS_2025_ANA_CLASS._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	private JTable table;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADRESLER frame = new ADRESLER();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ADRESLER() { 
	setTitle("ADRES DETAY");
	setResizable(true);
	setMaximizable(true);
	setIconifiable(true);
	setClosable(true);
	setBounds(0, 0, 1000, 600);

	JSplitPane splitPane = new JSplitPane();
	splitPane.setDividerSize(1);
	splitPane.setResizeWeight(0.0);
	splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	getContentPane().add(splitPane, BorderLayout.CENTER);

	JScrollPane scrollPane = new JScrollPane();
	splitPane.setRightComponent(scrollPane);

	table = new JTable(){
		public boolean isCellEditable(int row, int column) {     return false;          }
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
	splitPane.setLeftComponent(panel);
	panel.setMinimumSize(new Dimension(0, 30));
	panel.setMaximumSize(new Dimension(0, 30));
	panel.setBorder(new LineBorder(new Color(0, 191, 255)));
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

	}
	private void hisset() 
	{
		long startTime = System.currentTimeMillis(); 
		ResultSet rs = null ;
		try
		{
			rs = a_Access.adres("M_Kodu","") ;
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
			tc.setMinWidth(100);

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
			//table.setSelectionBackground(Color.PINK);
			//table.setSelectionForeground(Color.BLUE);

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
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText()));
			table.setRowSorter(sorter);
		}
	}
}

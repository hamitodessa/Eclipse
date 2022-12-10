package OBS_2025;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class SQL_SORGULAMA extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static CARI_ACCESS c_Access = new CARI_ACCESS(oac._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	static STOK_ACCESS f_Access = new STOK_ACCESS(oac._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	private static JTable table;
	private static String modul = "" ;
	private static JTextArea textArea ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SQL_SORGULAMA frame = new SQL_SORGULAMA(modul);
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

	public SQL_SORGULAMA(String nerden) {
		setTitle("SQL SORGULAMA");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0,0, 900, 600);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		//table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setGridColor(oac.gridcolor);
		scrollPane.setViewportView(table);

		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 191, 255)));
		textArea.setFont(new Font("Monospaced", Font.BOLD, 16));
		textArea.setMinimumSize(new Dimension(0, 100));
		textArea.setMaximumSize(new Dimension(0, 100));
		textArea.setLineWrap(true);
		textArea.setDocument(new JTextFieldLimit(100));
		Border borderr = BorderFactory.createLineBorder(Color.GRAY);

		textArea.setBorder(BorderFactory.createCompoundBorder(borderr,
				BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		splitPane.setLeftComponent(textArea);
		modul = nerden ;
	}
	public static void hisset ()
	{
		try
		{
			if (textArea.getText().equals("")) return ;
			if (  textArea.getText().toUpperCase().contains("DROP") ) return;
			if (  textArea.getText().toUpperCase().contains("ALTER") ) return;
			if (  textArea.getText().toUpperCase(). contains("DELETE") ) return;  
			if (  textArea.getText().toUpperCase(). contains("INSERT") ) return;  
			if (  textArea.getText().toUpperCase(). contains("UPDATE") ) return;  

			ResultSet rs =null;
			if (modul.equals("cari"))
			{
				rs = c_Access.sql_sorgu(textArea.getText());

				if (!rs.isBeforeFirst() ) {  
					GRID_TEMIZLE.grid_temizle(table);
					return;
				} 
			}
			else if (modul.equals("stok"))
			{

				rs = f_Access.sql_sorgu(textArea.getText());

				if (!rs.isBeforeFirst() ) {  
					GRID_TEMIZLE.grid_temizle(table);
					return;
				} 

			}
			GRID_TEMIZLE.grid_temizle(table);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();

			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;


			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(100);
			tc.setMaxWidth(100);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());

			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Sql Sorgulama", JOptionPane.ERROR_MESSAGE);
		}
	}
}

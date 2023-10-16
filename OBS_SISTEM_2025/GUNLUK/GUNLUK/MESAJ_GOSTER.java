package GUNLUK;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;

import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_2025.EKSTRE;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SOLA;
import OBS_C_2025.TARIH;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class MESAJ_GOSTER extends JInternalFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MESAJ_GOSTER dialog = new MESAJ_GOSTER(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MESAJ_GOSTER(ResultSet rs) {
		setTitle("ANLIK GOREVLER");
		setBounds(100, 100, 700, 200);
		setResizable(true);
		setClosable(true);
		setIconifiable(true);
		setDefaultCloseOperation(EKSTRE.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		table = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {  
				return false;
			}
		};
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		doldur(rs);
	}
	private void doldur(ResultSet rs)
	{
		GRID_TEMIZLE.grid_temizle(table);
		table.setModel(DbUtils.resultSetToTableModel(rs));
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc;
		tc = tcm.getColumn(0);
		tc.setHeaderRenderer(new SOLA());
		tc.setCellRenderer(new TARIH());
		tc.setMinWidth(75);
		tc.setMaxWidth(75);
		
		tc = tcm.getColumn(1);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(60);
		tc.setMaxWidth(60);
		
		tc = tcm.getColumn(2);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(100);
		
		tc = tcm.getColumn(3);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(100);
		
		tc = tcm.getColumn(4);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(100);
		
		tc = tcm.getColumn(5);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(220);
		
		//table.setSelectionBackground(Color.PINK);
		//table.setSelectionForeground(Color.BLUE);
		
		Dimension dd = th.getPreferredSize();
		dd.height = 30;
		th.setPreferredSize(dd); 
		th.repaint();
		table.setRowSelectionInterval(0, 0);
		table.setRowHeight(21);
	}
}

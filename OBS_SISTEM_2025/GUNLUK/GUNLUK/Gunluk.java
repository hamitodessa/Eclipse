package GUNLUK;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import com.toedter.calendar.JCalendar;

import OBS_C_2025.COLUMN_RENDERER;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.ROW_RENDERER;
import OBS_C_2025.SAGA;
import OBS_C_2025.TABLO_RENDERER;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.Font;

@SuppressWarnings("serial")
public class Gunluk extends JInternalFrame {
	private JTable table;
	private JTable table_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gunluk frame = new Gunluk();
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
	public Gunluk() {
		setTitle("GUNLUK");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0,0, 1100, 650);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(200, 0));
		panel.setMaximumSize(new Dimension(200, 0));
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		panel.add(splitPane_1, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(0, 300));
		panel_1.setMaximumSize(new Dimension(0, 300));
		splitPane_1.setLeftComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JCalendar calendar = new JCalendar();
		panel_1.add(calendar, BorderLayout.CENTER);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane_2.setDividerSize(0);
		splitPane_2.setResizeWeight(1.0);
		splitPane.setRightComponent(splitPane_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setMinimumSize(new Dimension(200, 0));
		panel_2.setMaximumSize(new Dimension(200, 0));
		splitPane_2.setRightComponent(panel_2);
		
		JPanel panel_3 = new JPanel();
		splitPane_2.setLeftComponent(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_3 = new JSplitPane();
		
		splitPane_3.setDividerSize(0);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_3.add(splitPane_3, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane_3.setRightComponent(scrollPane);
		
		
		//*****************************************************
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {     return false;          }
			
		};
				
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"06:00", null, null, null, null, null, null, null},
				{"07:00", null, null, null, null, null, null, null},
				{"08:00", null, null, null, null, null, null, null},
				{"09:00", null, null, null, null, null, null, null},
				{"10:00", null, null, null, null, null, null, null},
				{"11:00", null, null, null, null, null, null, null},
				{"12:00", null, null, null, null, null, null, null},
				{"13:00", null, null, null, null, null, null, null},
				{"14:00", null, null, null, null, null, null, null},
				{"15:00", null, null, null, null, null, null, null},
				{"16:00", null, null, null, null, null, null, null},
				{"17:00", null, null, null, null, null, null, null},
				{"18:00", null, null, null, null, null, null, null},
				{"19:00", null, null, null, null, null, null, null},
				{"20:00", null, null, null, null, null, null, null},
				{"21:00", null, null, null, null, null, null, null},
				{"22:00", null, null, null, null, null, null, null},
				{"23:00", null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(100);
		for (int row = 0; row < table.getRowCount(); row++)
	    {
	        table.setRowHeight(row, 30);
	    }
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc;
		tc = tcm.getColumn(0);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(39, 45, 61),Color.WHITE));
		
		table.setTableHeader(null);

//***************************************************************************		
		scrollPane.setViewportView(table);
		
		table_1 = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
			
		};
		
		table_1.setRowSelectionAllowed(false);
		
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{"Saat", "Pazartesi","Sali", "Carsamba", "Persembe", "Cuma", "Cumartesi", "Pazar"},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table_1.setRowHeight(0, 30);
		table_1.setRowHeight(1, 30);
		table_1.getColumnModel().getColumn(0).setMaxWidth(100);
		table_1.getColumnModel().getColumn(0).setMinWidth(100);
		JTableHeader ttt = table_1.getTableHeader();
		TableColumnModel tcc = ttt.getColumnModel();
		TableColumn tc1;
		tc1 = tcc.getColumn(1);
		tc1.setCellRenderer(new ROW_RENDERER(new Color(39, 45, 61),Color.WHITE));
		
		table_1.setTableHeader(null);
//**************************************************************		
		splitPane_3.setLeftComponent(table_1);
		
		//updateRowHeights();
		
	}
	
	private void updateRowHeights()
	{
	    for (int row = 0; row < table.getRowCount(); row++)
	    {
	        table.setRowHeight(row, 30);
	    }
	}

}



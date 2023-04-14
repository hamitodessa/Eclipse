package GUNLUK;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import com.toedter.calendar.JCalendar;
import OBS_C_2025.COLUMN_RENDERER;
import OBS_C_2025.ROW_RENDERER;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Gunluk extends JInternalFrame {
	private JTable table;
	private JTable table_1;
	JCalendar calendar ;
	boolean kontrol = false;
	String trh1 = "" ;
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
		setBounds(0,0, 1100, 672);

		JToolBar jtbar = new JToolBar();
		jtbar.setFloatable(false);
		getContentPane().add(jtbar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("");
		jtbar.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon(Gunluk.class.getResource("/ICONLAR/icons_geri-24.png")));
		
		JLabel lblNewLabel = new JLabel("                        ");
		jtbar.add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("");
		jtbar.add(btnNewButton_1);
		btnNewButton_1.setIcon(new ImageIcon(Gunluk.class.getResource("/ICONLAR/icons_ileri-24.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ileri();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				geri();
			}
		});
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

		calendar = new JCalendar();
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getNewValue() == evt.getOldValue()) return;

				//doldur(e.getNewValue().toString());
				basla();
			}
		});
		calendar.setTodayButtonText("Bugun");
		calendar.setTodayButtonVisible(true);
		panel_1.add(calendar, BorderLayout.CENTER);
		//**********************************************************************************		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane_2.setDividerSize(0);
		splitPane_2.setResizeWeight(1.0);
		splitPane.setRightComponent(splitPane_2);

		JPanel panel_2 = new JPanel();
		panel_2.setMinimumSize(new Dimension(200, 0));
		panel_2.setMaximumSize(new Dimension(200, 0));
		splitPane_2.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

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
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(80, 92, 124),Color.WHITE));

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
					{"", "Pazartesi","Sali", "Carsamba", "Persembe", "Cuma", "Cumartesi", "Pazar"},
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
		tc1.setCellRenderer(new ROW_RENDERER(new Color(131, 143, 175),Color.WHITE));

		tc1 = tcc.getColumn(2);
		tc1.setCellRenderer(new ROW_RENDERER(new Color(131, 143, 175),Color.WHITE));

		tc1 = tcc.getColumn(3);
		tc1.setCellRenderer(new ROW_RENDERER(new Color(131, 143, 175),Color.WHITE));

		tc1 = tcc.getColumn(4);
		tc1.setCellRenderer(new ROW_RENDERER(new Color(131, 143, 175),Color.WHITE));

		tc1 = tcc.getColumn(5);
		tc1.setCellRenderer(new ROW_RENDERER(new Color(131, 143, 175),Color.WHITE));

		tc1 = tcc.getColumn(6);
		tc1.setCellRenderer(new ROW_RENDERER(new Color(131, 143, 175),Color.WHITE));

		tc1 = tcc.getColumn(7);
		tc1.setCellRenderer(new ROW_RENDERER(new Color(131, 143, 175),Color.WHITE));


		table_1.setTableHeader(null);

		//**************************************************************		
		splitPane_3.setLeftComponent(table_1);
	}
	private void basla()
	{
		SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
		trh1 = format1.format(calendar.getDate());
		DefaultTableModel mdl1 = (DefaultTableModel) table_1.getModel();
		mdl1.setValueAt(trh1,0,1);
		mdl1.setValueAt(getDayNumber(trh1),1,1);
		for (int i = 2 ;i <=7;i++)
		{
			mdl1.setValueAt(tarih_ileri_geri(trh1,"I"),0,i);
			mdl1.setValueAt(getDayNumber(trh1),1,i);
		}
	}
	private String getDayNumber(String tar) 
	{
		String tarr="";
		try
		{
			Date qwe = new SimpleDateFormat("dd.MM.yyyy").parse(tar);	
			Calendar cal = Calendar.getInstance();
			cal.setTime(qwe);
			if (cal.get(Calendar.DAY_OF_WEEK) ==1)
			{
				tarr=  "Pazar";
			}
			else if (cal.get(Calendar.DAY_OF_WEEK) ==2)
			{
				tarr=  "Pazartesi";
			}
			else if (cal.get(Calendar.DAY_OF_WEEK) ==3)
			{
				tarr=  "Sali";
			}
			else if (cal.get(Calendar.DAY_OF_WEEK) ==4)
			{
				tarr=  "Carsamba";
			}
			else if (cal.get(Calendar.DAY_OF_WEEK) ==5)
			{
				tarr=  "Persembe";
			}
			else if (cal.get(Calendar.DAY_OF_WEEK) ==6)
			{
				tarr=  "Cuma";
			}
			else if (cal.get(Calendar.DAY_OF_WEEK) ==7)
			{
				tarr=  "Cumartesi";
			}

		} catch (ParseException e) {
		}
		return tarr;
	}
	private String tarih_ileri_geri(String tarih , String nereye)
	{
		try
		{
			if (nereye == "G")
			{

			}
			else if (nereye == "I")
			{
				Date qwe = new SimpleDateFormat("dd.MM.yyyy").parse(tarih);	
				Calendar cal = Calendar.getInstance();
				cal.setTime(qwe);
				cal.add(Calendar.DAY_OF_MONTH, 1); 
				//System.out.println(cal.getTime());
				SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
				trh1 =  format1.format(cal.getTime());
			}
		} catch (ParseException e) {
		}
		return trh1;  
	}
	private void ileri() 
	{
		SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
		String formatted = format1.format(calendar.getDate());
		try {
			Date qwe = new SimpleDateFormat("dd.MM.yyyy").parse(formatted);
			Calendar cal = Calendar.getInstance();
			cal.setTime(qwe);
			cal.add(Calendar.DAY_OF_MONTH, 1); 
			calendar.setDate(new Date(cal.getTimeInMillis()));
		} catch (ParseException e) {
		}  
	}
	private void geri() 
	{
		SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
		String formatted = format1.format(calendar.getDate());
		try {
			Date qwe = new SimpleDateFormat("dd.MM.yyyy").parse(formatted);
			Calendar cal = Calendar.getInstance();
			cal.setTime(qwe);
			cal.add(Calendar.DAY_OF_MONTH, -1); 
			calendar.setDate(new Date(cal.getTimeInMillis()));
		} catch (ParseException e) {
		}  
	}
}



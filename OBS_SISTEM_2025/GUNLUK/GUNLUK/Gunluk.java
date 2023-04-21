package GUNLUK;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JPanel;
import com.toedter.calendar.JCalendar;
import OBS_C_2025.Gunluk_Bilgi;
import OBS_C_2025.MyTreeCellRenderer;
import OBS_2025.OBS_MAIN;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;

import OBS_C_2025.COLUMN_RENDERER;
import OBS_C_2025.GUNLUK_ACCESS;
import OBS_C_2025.ROW_RENDERER;
import OBS_C_2025.TARIH_CEVIR;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.apache.commons.compress.harmony.unpack200.bytecode.forms.ThisFieldRefForm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.lang.invoke.StringConcatFactory;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTree;
import java.awt.GridLayout;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class Gunluk extends JInternalFrame {
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	@SuppressWarnings({ "static-access" })
	private static GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(oac._IGunluk , oac._IGunluk_Loger);
	private JTable table;
	private JTable table_1;
	static JCalendar calendar ;
	boolean kontrol = false;
	String trh1 = "" ;
	boolean ilk = true;
	private static JComboBox<String> comboIsim;
	private static JTree treeGovev ;
	private JScrollPane scrolAylik;
	private JTabbedPane tabloTabbedPane;
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
	@SuppressWarnings("static-access")
	public Gunluk() {
		setTitle("GUNLUK");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0,0, 1100, 635);
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		//splitPane.setResizeWeight(0.0);
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
		panel_1.setMinimumSize(new Dimension(0, 400));
		panel_1.setMaximumSize(new Dimension(0, 400));
		splitPane_1.setLeftComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		////
		JSplitPane splitSolUst = new JSplitPane();
		splitSolUst.setDividerSize(0);
		splitSolUst.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitSolUst, BorderLayout.CENTER);
		
		JPanel panelToolbar= new JPanel();
		panelToolbar.setMinimumSize(new Dimension(0, 35));
		panelToolbar.setMaximumSize(new Dimension(0, 35));
		splitSolUst.setLeftComponent(panelToolbar);
		
		JButton btnNewButton = new JButton("");
		//btnNewButton.setMinimumSize(new Dimension(60, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int	activ_sayfa = tabloTabbedPane.getSelectedIndex();
					if (activ_sayfa == 0)
					{
						geri();
					}
					else
					{
						ay_geri();
					}

				
				} catch (ClassNotFoundException | SQLException e1) {
				
				}
			}
		});
		panelToolbar.setLayout(new GridLayout(1, 1, 0, 0));
		btnNewButton.setIcon(new ImageIcon(Gunluk.class.getResource("/ICONLAR/icons_geri-24.png")));
		panelToolbar.add(btnNewButton);
		
		
		

		JButton btnNewButton_1 = new JButton("");
		panelToolbar.add(btnNewButton_1);
		btnNewButton_1.setIcon(new ImageIcon(Gunluk.class.getResource("/ICONLAR/icons_ileri-24.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int	activ_sayfa = tabloTabbedPane.getSelectedIndex();
					if (activ_sayfa == 0)
					{
						ileri();
					}
					else 
					{
						ay_ileri();
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		///
		//Locale locale = Locale.forLanguageTag("tr-TR"); 
        //Calendar cal = Calendar.getInstance(locale);
        //cal.setTime(new Date());
        //set first day of week
        //int firstWeekDay = Calendar.MONDAY;
        //cal.setFirstDayOfWeek(firstWeekDay);
        
       
        
		calendar = new JCalendar();
		calendar.getYearChooser().getSpinner().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				
			}
		});
		
		calendar.getMonthChooser().getComboBox().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				
			}
		});
		calendar.getDayChooser().setDayBordersVisible(true);
		
		calendar.getMonthChooser().getComboBox().setFont(new Font("Tahoma", Font.BOLD, 11));
		
		calendar.getYearChooser().setFont(new Font("Tahoma", Font.BOLD, 11));
		calendar.getDayChooser().getDayPanel().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				
			}
		});
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getNewValue() == evt.getOldValue()) return;
				if (ilk == true)
				{
					return;
				}
				try {
					int	activ_sayfa = tabloTabbedPane.getSelectedIndex();
					if (activ_sayfa == 0)
					{
					basla();
					}
					else 
					{
						aylik_gorunum_doldur();
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		calendar.setTodayButtonText("Bugun");
		calendar.setTodayButtonVisible(true);
		//panel_1.add(calendar, BorderLayout.CENTER);
		splitSolUst.setRightComponent(calendar);
		//**********************************************************************************		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane_2.setDividerSize(0);
		splitPane_2.setResizeWeight(1.0);
		splitPane.setRightComponent(splitPane_2);

		
//**********************************************************************************************		
		JPanel panel_2 = new JPanel();
		panel_2.setMinimumSize(new Dimension(200, 0));
		panel_2.setMaximumSize(new Dimension(200, 0));
		splitPane_2.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_5 = new JSplitPane();
		splitPane_5.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_2.add(splitPane_5, BorderLayout.CENTER);

		comboIsim = new JComboBox<String>();
		
		comboIsim.setForeground(new Color(0, 0, 139));
		comboIsim.setMinimumSize(new Dimension(0, 30));
		comboIsim.setMaximumSize(new Dimension(0, 30));
		splitPane_5.setLeftComponent(comboIsim);
		comboIsim.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboIsim.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {

				if (table.getRowCount() != 0)
				{
					if (ilk == false)
					{
						try 
						{
							int	activ_sayfa = tabloTabbedPane.getSelectedIndex();
							if (activ_sayfa == 0)
							{
								basla();
							}
							else {
								aylik_gorunum_doldur();
							}
						} catch (ClassNotFoundException | SQLException e1) 
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}
			}
		});
		comboIsim.setModel(new DefaultComboBoxModel<String>(new String[] {"Hepsi"}));

		JSplitPane splitPane_6 = new JSplitPane();
		splitPane_6.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_5.setRightComponent(splitPane_6);

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_6.setLeftComponent(scrollPane_1);

		treeGovev = new JTree();
		treeGovev.setFont(new Font("Tahoma", Font.BOLD, 11));
		treeGovev.setCellRenderer(new MyTreeCellRenderer());
		treeGovev.setToggleClickCount(0);
		treeGovev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selRow = treeGovev.getRowForLocation(e.getX(), e.getY());
				//TreePath selPath = treeGovev.getPathForLocation(e.getX(), e.getY());
				if( selRow > 0) 
				{
					if(e.getClickCount() == 1) {
						// mySingleClick(selRow, selPath);
						//treeOgren();
						// System.out.println(selRow  +"=="+ selPath);
					}
					else if(e.getClickCount() == 2) {
						//myDoubleClick(selRow, selPath);
						try {
							treeOgren();
						} catch (ClassNotFoundException | SQLException  | PropertyVetoException e2) {
							// TODO Auto-generated catch block
						}
						//  System.out.println(selRow  +"=double="+ selPath);
					}
				}
			}
		});
		treeGovev.addTreeSelectionListener(new TreeSelectionListener(){
			@Override
			public void valueChanged(TreeSelectionEvent tse) {
				//1
			}
		});
		scrollPane_1.setViewportView(treeGovev);
		treeGovev.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Gorevler") {
				}
				));
		
		JPanel panel_5 = new JPanel();
		splitPane_6.setRightComponent(panel_5);
		treeGovev.getAutoscrolls();
		treeGovev.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		JPanel panel_3 = new JPanel();
		splitPane_2.setLeftComponent(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

//*************************************************TABLOLARIN TABBED PANE **********************************************
		tabloTabbedPane = new JTabbedPane();
		tabloTabbedPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_3.add(tabloTabbedPane, BorderLayout.CENTER);
//*************************************************Tablolarin Spliti
		JSplitPane splitPane_3 = new JSplitPane();
		tabloTabbedPane.addTab("Gunluk", null, splitPane_3, null);  // Gunluk tab paneli koy
		splitPane_3.setDividerSize(0);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		//panel_3.add(splitPane_3, BorderLayout.CENTER);
///*********************************************************************************************************************		
		table_1 = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }

		};
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null},
					{"", "Pazartesi","Sali", "Carsamba", "Persembe", "Cuma", "Cumartesi", "Pazar"},
				},
				new String[] {
						"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
				}
				));
		table_1.setRowSelectionAllowed(false);
		
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
		//*****************************************************************************************************************		
		splitPane_3.setLeftComponent(table_1);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane_3.setRightComponent(scrollPane);
		//*****************************************************************************************************************
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {     return false;          }

		};
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (table.getSelectedRow() < 0) return ;
				if (table.getSelectedColumn() == 0) return ;
				if (table.equals(e.getSource())) 
				{
					try 
					{
						table.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						detay_doldur(table.getSelectedRow(),table.getSelectedColumn());
						table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(	new Object[][] 
				{
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
		tc = tcm.getColumn(1);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(255, 177, 190),new Color(204, 0, 29)));
		tc = tcm.getColumn(2);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(86, 177, 220),new Color(171, 216, 237)));
		tc = tcm.getColumn(3);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(107, 173, 132),new Color(173, 209, 204)));
		tc = tcm.getColumn(4);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(226, 121, 28),new Color(249, 228, 209)));
		tc = tcm.getColumn(5);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(225, 207, 208),new Color(126, 78, 80)));
		tc = tcm.getColumn(6);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(198, 201, 234),new Color(55, 64, 149)));
		tc = tcm.getColumn(7);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(197, 235, 217),new Color(52, 152, 104)));

		table.setTableHeader(null);
		//****************************************Aylik***********************************
		scrolAylik = new JScrollPane();
		tabloTabbedPane.addTab("Aylik", null, scrolAylik, null);
		//********************************************************************************
		temizle();
		calendar.setDate(new Date());
		
		JPanel panel_4 = new JPanel();
		splitPane_1.setRightComponent(panel_4);
		///
		tabloTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int	activ_sayfa =tabloTabbedPane.getSelectedIndex();
				try {
					DefaultTreeModel model = (DefaultTreeModel)treeGovev.getModel();
					DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
					root.removeAllChildren(); //this removes all nodes
					model.reload(); //this notifies the listeners and changes the GUI
				if (activ_sayfa == 0)
				{
							basla();
				}
				else if (activ_sayfa == 1)  //Aylik Gorunum
				{
						aylik_gorunum_doldur();
				}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		try 
		{
			isim_doldur();
			basla();
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	private void basla() throws ClassNotFoundException, SQLException
	{
		temizle();
		SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
		trh1 = format1.format(calendar.getDate());
		DefaultTableModel mdl1 = (DefaultTableModel) table_1.getModel();
		mdl1.setValueAt(trh1,0,1);
		mdl1.setValueAt(getDayNumber(trh1),1,1);
		for (int i = 2 ;i <=7;i++)
		{
			mdl1.setValueAt(tarih_ileri_geri(trh1),0,i);
			mdl1.setValueAt(getDayNumber(trh1),1,i);
		}
		gorev_oku();
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
	private String tarih_ileri_geri(String tarih )
	{
		try
		{
			Date qwe = new SimpleDateFormat("dd.MM.yyyy").parse(tarih);	
			Calendar cal = Calendar.getInstance();
			cal.setTime(qwe);
			cal.add(Calendar.DAY_OF_MONTH, 1); 
			SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
			trh1 =  format1.format(cal.getTime());

		} catch (ParseException e) {
		}
		return trh1;  
	}
	private void ileri() throws ClassNotFoundException, SQLException 
	{
		temizle();
		SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
		String formatted = format1.format(calendar.getDate());
		try 
		{
			Date qwe = new SimpleDateFormat("dd.MM.yyyy").parse(formatted);
			Calendar cal = Calendar.getInstance();
			cal.setTime(qwe);
			cal.add(Calendar.DAY_OF_MONTH, 1); 
			calendar.setDate(new Date(cal.getTimeInMillis()));
		} catch (ParseException e) {
		}  
	}
	private void ay_ileri() throws ClassNotFoundException, SQLException 
	{
		temizle();
		SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
		String formatted = format1.format(calendar.getDate());
		try 
		{
			Date qwe = new SimpleDateFormat("dd.MM.yyyy").parse(formatted);
			Calendar cal = Calendar.getInstance();
			cal.setTime(qwe);
			cal.add(Calendar.MONTH, 1); 
			calendar.setDate(new Date(cal.getTimeInMillis()));
		} catch (ParseException e) {
		}  
	}
	private void geri() throws ClassNotFoundException, SQLException 
	{
		temizle();
		SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
		String formatted = format1.format(calendar.getDate());
		try {
			Date qwe = new SimpleDateFormat("dd.MM.yyyy").parse(formatted);
			Calendar cal = Calendar.getInstance();
			cal.setTime(qwe);
			cal.add(Calendar.DAY_OF_MONTH, -1); 
			calendar.setDate(new Date(cal.getTimeInMillis()));
		} 
		catch (ParseException e) 
		{
		}  
	}
	private void ay_geri() throws ClassNotFoundException, SQLException 
	{
		temizle();
		SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
		String formatted = format1.format(calendar.getDate());
		try {
			Date qwe = new SimpleDateFormat("dd.MM.yyyy").parse(formatted);
			Calendar cal = Calendar.getInstance();
			cal.setTime(qwe);
			cal.add(Calendar.MONTH, -1); 
			calendar.setDate(new Date(cal.getTimeInMillis()));
		} 
		catch (ParseException e) 
		{
		}  
	}
	private void temizle()
	{
		DefaultTableModel mdlGunluk = (DefaultTableModel) table.getModel();
		DefaultTableModel mdlBaslik = (DefaultTableModel) table_1.getModel();
		for (int qqq = 1; qqq <= mdlBaslik.getColumnCount() -1;qqq++)
		{
			for (int stt = 0 ; stt <= mdlGunluk.getRowCount() -1;stt ++)
			{
				mdlGunluk.setValueAt("",stt,qqq);
			}
		}
		DefaultTreeModel model = (DefaultTreeModel)treeGovev.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
		root.removeAllChildren(); //this removes all nodes
		model.reload(); //this notifies the listeners and changes the GUI
	}
	private void gorev_oku() throws ClassNotFoundException, SQLException
	{
		Gunluk_Bilgi gbilgi = new Gunluk_Bilgi();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		String formatted = format1.format(calendar.getDate());
		gbilgi.tarih1 = formatted ;
		if (comboIsim.getItemAt(comboIsim.getSelectedIndex()).toString().equals("Hepsi"))
		{
			gbilgi.isim = "" ;
		}
		else 
		{
			gbilgi.isim = " AND ISIM = '"+ comboIsim.getItemAt(comboIsim.getSelectedIndex()).toString() + "' " ;
		}
		ResultSet rs = g_Access.gorev_oku(gbilgi);

		if (!rs.isBeforeFirst() ) { 
			return; // Kayit Yok
		} 
		DefaultTableModel mdlBaslik = (DefaultTableModel) table_1.getModel();
		DefaultTableModel mdlGunluk = (DefaultTableModel) table.getModel();

		while(rs.next()){
			for (int qqq = 1; qqq <= mdlBaslik.getColumnCount() -1;qqq++)
			{
				if ( mdlBaslik.getValueAt(0,qqq).toString().equals(rs.getString("TARIH")))
				{
					for (int stt = 0 ; stt <= mdlGunluk.getRowCount() -1;stt ++)
					{
						if ( mdlGunluk.getValueAt(stt,0).toString().equals(rs.getString("SAAT")))
						{
							if(! mdlGunluk.getValueAt(stt, qqq).toString().equals(""))
							{
								mdlGunluk.setValueAt(mdlGunluk.getValueAt(stt, qqq).toString() + "{"+rs.getString("GOREV")+"},",stt,qqq);
							}
							else 
							{
								mdlGunluk.setValueAt("{" + rs.getString("GOREV")+"},",stt,qqq);
							}
						}
					}
				}
			}
		}
	}
	private void detay_doldur(int satir , int sutun) throws ClassNotFoundException, SQLException
	{
		DefaultTableModel mdlGunluk = (DefaultTableModel) table.getModel();
		DefaultTableModel mdlBaslik = (DefaultTableModel) table_1.getModel();
		DefaultTreeModel model = (DefaultTreeModel)treeGovev.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
		root.removeAllChildren(); //this removes all nodes
		model.reload(); //this notifies the listeners and changes the GUI
		if (mdlGunluk.getValueAt(satir, 0).toString().equals("") ) return;

		Gunluk_Bilgi gbilgi = new Gunluk_Bilgi();

		gbilgi.tarih1 = TARIH_CEVIR.tarih_sql(mdlBaslik.getValueAt(0, sutun).toString());
		gbilgi.saat1 = mdlGunluk.getValueAt(satir, 0).toString() ;

		ResultSet rSet = g_Access.gorev_oku_tarih(gbilgi);
		if (!rSet.isBeforeFirst() ) { 
			return; // Kayit Yok
		} 
		while (rSet.next())
		{
			DefaultMutableTreeNode iSIM = new DefaultMutableTreeNode(rSet.getString("ISIM"));
			DefaultMutableTreeNode gOREV = new DefaultMutableTreeNode(rSet.getString("GOREV"));
			DefaultMutableTreeNode yER = new DefaultMutableTreeNode(rSet.getString("YER")); 
			DefaultMutableTreeNode mESAJ = new DefaultMutableTreeNode(rSet.getString("MESAJ"));
			yER.add(mESAJ);
			gOREV.add(yER);
			iSIM.add(gOREV);
			root.add(iSIM);
			
		}
		model.reload(root);
	}
	private void isim_doldur() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = g_Access.isim_oku();
		if (!rs.isBeforeFirst() ) {  
		} 
		else
		{
			comboIsim.removeAllItems();
			comboIsim.addItem("Hepsi");
			while (rs.next()) 
			{
				comboIsim.addItem(rs.getString("ISIM"));
			}
			ilk= false;
		}
	}
	private void treeOgren() throws ClassNotFoundException, SQLException, PropertyVetoException
	{
		TreePath path = treeGovev.getSelectionPath();
		if (path == null) return;
		int count = path.getPathCount();
		String gOREV[] = new String[4];
		if (count== 5)
		{
			gOREV[0] = path.getPathComponent(count - 4).toString();
			gOREV[1] = path.getPathComponent(count - 3).toString();
			gOREV[2] = path.getPathComponent(count - 2).toString();
			gOREV[3] = path.getPathComponent(count -1 ).toString();
		}
		if (count== 4)
		{
			gOREV[0]= path.getPathComponent(count - 3).toString();
			gOREV[1] = path.getPathComponent(count - 2).toString();
			gOREV[2] = path.getPathComponent(count - 1).toString();
			DefaultMutableTreeNode node =  (DefaultMutableTreeNode) treeGovev.getLastSelectedPathComponent();
			Enumeration<TreeNode> en = node.depthFirstEnumeration();
			ArrayList<String> str = new ArrayList<String>();
			while (en.hasMoreElements()) 
			{
				DefaultMutableTreeNode nod1 = (DefaultMutableTreeNode) en.nextElement();
				str.add(nod1.toString()); //this adds an element to the list.
			}
			gOREV[3] =(String) str.get(0) ;
		}
		if (count== 3)
		{
			gOREV[0]= path.getPathComponent(count - 2).toString();
			gOREV[1] = path.getPathComponent(count - 1).toString();
			DefaultMutableTreeNode node =  (DefaultMutableTreeNode) treeGovev.getLastSelectedPathComponent();
			Enumeration<TreeNode> en = node.depthFirstEnumeration();
			ArrayList<String> str = new ArrayList<String>();
			while (en.hasMoreElements()) 
			{
				DefaultMutableTreeNode nod1 = (DefaultMutableTreeNode) en.nextElement();
				str.add(nod1.toString()); //this adds an element to the list.
			}
			gOREV[2] =(String) str.get(1) ;
			gOREV[3] =(String) str.get(0) ;
		}
		if (count== 2)
		{
			DefaultMutableTreeNode node =  (DefaultMutableTreeNode) treeGovev.getLastSelectedPathComponent();
			Enumeration<TreeNode> en = node.depthFirstEnumeration();
			ArrayList<String> str = new ArrayList<String>();
			while (en.hasMoreElements()) 
			{
				DefaultMutableTreeNode nod1 = (DefaultMutableTreeNode) en.nextElement();
				str.add(nod1.toString()); //this adds an element to the list.
			}
			gOREV[0] =(String) str.get(3);
			gOREV[1] = (String) str.get(2);
			gOREV[2] = (String) str.get(1);
			gOREV[3] = (String) str.get(0);
		}
		Gunluk_Bilgi gbilgi = new Gunluk_Bilgi();
		gbilgi.isim = gOREV[0];
		gbilgi.gorev = gOREV[1];
		gbilgi.yer = gOREV[2];
		gbilgi.mesaj = gOREV[3];
		
		int gid = g_Access.gorev_bul(gbilgi);
		boolean varmi = OBS_MAIN.pencere_bak("GOREV GIRIS");
		if (varmi  ) 
 		{
 		OBS_MAIN.pencere_aktiv_yap("GOREV GIRIS");
 		}
		else 
		{
		 JInternalFrame internalFrame;
		 internalFrame  = new GOREV_GIRIS();
		 OBS_MAIN.desktopPane.add(internalFrame);
		 internalFrame.setVisible(true);
		}
		GOREV_GIRIS.txtGID.setText(String.valueOf(gid));
		GOREV_GIRIS.gOKU();
	}
	private void aylik_gorunum_doldur() throws SQLException, ClassNotFoundException, ParseException
	{

		//int ay = calendar.getMonthChooser().getMonth();
		Date qwe = calendar.getDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(qwe);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		int hangiGUN =  cal.get(Calendar.DAY_OF_WEEK);
		int gunSAYISI = cal. getActualMaximum(Calendar. DATE);

		JPanel qweJPanel =  new Aylik_Gorunum(hangiGUN,gunSAYISI);
		scrolAylik.setViewportView(qweJPanel);

		///////////////
		ResultSet rSet = aylik_gorev_oku();

		if (!rSet.isBeforeFirst() ) { 
			return; // Kayit Yok
		} 

		////************************************************************************************************

		while(rSet.next())
		{

			String tarString= rSet.getString("TARIH").toString();

			Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(tarString);  
			DateFormat formatter = new SimpleDateFormat ("d");
			String ewqString = formatter.format(date1);
			Component[] comp = qweJPanel.getComponents();
			for (int i = 0;i<comp.length;i++) {
				if (comp[i] instanceof JButton) {
					if ( ((JButton)comp[i]).getName()!= null)
					{
						if ( ((JButton)comp[i]).getName().equals(ewqString))
						{
							if(((JButton)comp[i]).getText().equals("<html><p style=text-align:center;> <font color = #303A68 > <b> " + ewqString + "</b> <br> </p></html>"))
							{
								String qweString = "<html><p style=text-align:center;> <font color = #303A68 > <b> " + ewqString + " </b> <br> </p>" ;
								qweString = qweString + "<p style=text-align:left;> <font color = #9C487F > " +   rSet.getString("GOREV")  
								+  "</p></html>";
								((JButton)comp[i]).setText(qweString );
							}
							else
							{
								String dEVAM = ((JButton)comp[i]).getText().substring(0, ((JButton)comp[i]).getText().length() - 7);
								dEVAM = dEVAM + "<p style=text-align:left;> <font color = #9C487F > " +   rSet.getString("GOREV")  
								+ "</p></br></html>";
								((JButton)comp[i]).setText(dEVAM );
							}
						}
					}
				}
			}
		}
	}
	private ResultSet aylik_gorev_oku() throws ClassNotFoundException, SQLException
	{
		Gunluk_Bilgi gbilgi = new Gunluk_Bilgi();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		Date qwe = calendar.getDate();
		qwe.setDate(1);
		
		String formatted = format1.format(qwe);
		gbilgi.tarih1 = formatted ;
		if (comboIsim.getItemAt(comboIsim.getSelectedIndex()).toString().equals("Hepsi"))
		{
			gbilgi.isim = "" ;
		}
		else 
		{
			gbilgi.isim = " ISIM = '"+ comboIsim.getItemAt(comboIsim.getSelectedIndex()).toString() + "' AND " ;
		}
        LocalDate givenDate = LocalDate.parse(formatted, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        LocalDate lastDayOfMonthDateGivenDate  = givenDate.withDayOfMonth(
                                                 givenDate.getMonth().length(givenDate.isLeapYear()));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String formattedDate = lastDayOfMonthDateGivenDate.format(dateTimeFormatter);  //17-02-2022
        
        
        gbilgi.tarih2 =  formattedDate;
        //
		ResultSet rs = g_Access.gorev_oku_aylik_grup(gbilgi);
		return rs;
	}
	public static void aYLIK(String tarih) throws ClassNotFoundException, SQLException
	{
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		String formatted = format1.format(calendar.getDate());
		if (tarih.length() == 1)
		{
			formatted = formatted.substring(0, 8) + "0" +tarih;
		}
		else 
		{
			formatted = formatted.substring(0, 8) + tarih;
		}
		DefaultTreeModel model = (DefaultTreeModel)treeGovev.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
		root.removeAllChildren(); //this removes all nodes
		model.reload(); //this notifies the listeners and changes the GUI
		
		Gunluk_Bilgi gbilgi = new Gunluk_Bilgi();

		gbilgi.tarih1 = formatted;
		gbilgi.tarih2 = formatted;
		if (comboIsim.getItemAt(comboIsim.getSelectedIndex()).toString().equals("Hepsi"))
		{
			gbilgi.isim = "" ;
		}
		else 
		{
			gbilgi.isim = " ISIM = '"+ comboIsim.getItemAt(comboIsim.getSelectedIndex()).toString() + "' AND " ;
		}
		ResultSet rSet = g_Access.gorev_oku_aylik_grup(gbilgi);
		if (!rSet.isBeforeFirst() ) { 
			return; // Kayit Yok
		} 
		while (rSet.next())
		{
			DefaultMutableTreeNode iSIM = new DefaultMutableTreeNode(rSet.getString("ISIM"));
			DefaultMutableTreeNode gOREV = new DefaultMutableTreeNode(rSet.getString("GOREV"));
			DefaultMutableTreeNode yER = new DefaultMutableTreeNode(rSet.getString("YER")); 
			DefaultMutableTreeNode mESAJ = new DefaultMutableTreeNode(rSet.getString("MESAJ"));
			yER.add(mESAJ);
			gOREV.add(yER);
			iSIM.add(gOREV);
			root.add(iSIM);
		}
		model.reload(root);
	}
}



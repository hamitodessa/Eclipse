package GUNLUK;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JPanel;
import com.toedter.calendar.JCalendar;
import OBS_C_2025.Gunluk_Bilgi;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
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
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Gunluk extends JInternalFrame {
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	@SuppressWarnings({ "static-access" })
	private static GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(oac._IGunluk , oac._IGunluk_Loger);
	private JTable table;
	private JTable table_1;
	JCalendar calendar ;
	boolean kontrol = false;
	String trh1 = "" ;
	boolean ilk = true;
	private JComboBox<String> comboIsim;
	private JTree treeGovev ;
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

					geri();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
					ileri();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		///
		calendar = new JCalendar();
		
		calendar.getMonthChooser().getComboBox().setFont(new Font("Tahoma", Font.BOLD, 11));
		calendar.getYearChooser().getSpinner().setFont(new Font("Tahoma", Font.BOLD, 11));
		calendar.getMonthChooser().getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 11));
		calendar.getDayChooser().getDayPanel().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				try {
					basla();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getNewValue() == evt.getOldValue()) return;

				//doldur(e.getNewValue().toString());
				try {
					basla();
				} catch (ClassNotFoundException | SQLException e) {
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

		JPanel panel_2 = new JPanel();
		panel_2.setMinimumSize(new Dimension(200, 0));
		panel_2.setMaximumSize(new Dimension(200, 0));
		splitPane_2.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_5 = new JSplitPane();
		splitPane_5.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_2.add(splitPane_5, BorderLayout.CENTER);

		comboIsim = new JComboBox<String>();
		comboIsim.setMinimumSize(new Dimension(0, 30));
		comboIsim.setMaximumSize(new Dimension(0, 30));
		splitPane_5.setLeftComponent(comboIsim);
		comboIsim.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboIsim.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {

				if (table.getRowCount() != 0)
				{

					if (ilk == false)
					{
						try {
							basla();
						} catch (ClassNotFoundException | SQLException e1) {
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
		treeGovev.getAutoscrolls();
		treeGovev.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		JPanel panel_3 = new JPanel();
		splitPane_2.setLeftComponent(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_3 = new JSplitPane();

		splitPane_3.setDividerSize(0);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_3.add(splitPane_3, BorderLayout.CENTER);
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
		temizle();
		calendar.setDate(new Date());
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
		String gOREV[] = new String[3];
		if (count== 4)
		{
			gOREV[0] = path.getPathComponent(count - 3).toString();
			gOREV[1] =  path.getPathComponent(count - 2).toString();
			gOREV[2] = path.getPathComponent(count - 1).toString();
			//System.out.println(gOREV[0] + "/" + gOREV[1] + "/" + gOREV[2]);
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
			gOREV[2] =(String) str.get(0) ;
			//System.out.println(gOREV[0] + "/" + gOREV[1] + "/" + gOREV[2]);
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
			gOREV[0] =(String) str.get(2);
			gOREV[1] = (String) str.get(1);
			gOREV[2] = (String) str.get(0);
			//System.out.println(gOREV[0] + "/" + gOREV[1] + "/" + gOREV[2]);
		}
		
		Gunluk_Bilgi gbilgi = new Gunluk_Bilgi();
		gbilgi.isim = gOREV[0];
		gbilgi.gorev = gOREV[1];
		gbilgi.yer = gOREV[2];
		int gid = g_Access.gorev_bul(gbilgi);
		boolean varmi = OBS_MAIN.pencere_bak("GOREV_GIRIS");
		if (varmi  ) 
 		{
 		OBS_MAIN.pencere_aktiv_yap("DEKONT");
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
}



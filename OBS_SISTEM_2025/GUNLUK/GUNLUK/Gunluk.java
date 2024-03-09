package GUNLUK;
//The type java.text.Format cannot be resolved. It is indirectly referenced from required type java.text.DateFormat
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import OBS_C_2025.Gunluk_Bilgi;
import OBS_C_2025.MaterialTabbed;
import OBS_C_2025.MyTreeCellRenderer;

import OBS_2025.OBS_MAIN;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;

import OBS_C_2025.COLUMN_RENDERER;
import OBS_C_2025.COLUMN_RENDERER_YILLIK;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GUNLUK_ACCESS;
import OBS_C_2025.ROW_RENDERER;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TARIH_CEVIR;
import raven.toast.Notifications;

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

//import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;

import java.awt.GridLayout;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@SuppressWarnings({"serial" ,"deprecation" })
public class Gunluk extends JInternalFrame {
	private static GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(OBS_SIS_2025_ANA_CLASS._IGunluk , OBS_SIS_2025_ANA_CLASS._IGunluk_Loger);
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();

	private JTable table;
	private JTable table_1;
	private JTable table_3;
	static JCalendar calendar ;
	boolean kontrol = false;
	String trh1 = "" ;
	boolean ilk = true;
	private static JComboBox<String> comboIsim;
	private static JTree treeGovev ;
	private ScrollPaneWin11 scrolAylik;
	private MaterialTabbed tabloTabbedPane;
	

	
	@SuppressWarnings("static-access")
	public Gunluk() {
		setTitle("GUNLUK");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0,0, 1268, 675);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(Gunluk.class.getResource("/ICONLAR/icons8-schedule-30.png")), 16, 16));//
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(1);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(250, 0));
		try {
			if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("McWinLookAndFeel") ||  GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("MintLookAndFeel"))
			{
				panel.setMinimumSize(new Dimension(400, 0));
			}
			else
			{
				panel.setMinimumSize(new Dimension(250, 0));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);

		panel.add(splitPane_1, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(0, 525));
		panel_1.setMaximumSize(new Dimension(0, 525));
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
		panelToolbar.setLayout(new GridLayout(1, 1, 0, 0));
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setToolTipText("Yenile");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	 
					ilk = true;
					temizle();
					calendar.setDate(new Date());
					int	activ_sayfa = tabloTabbedPane.getSelectedIndex();
					if (activ_sayfa == 0)
					{
						isim_doldur();
						basla();
					}
					else if (activ_sayfa == 1)
					{
						isim_doldur();
						aylik_gorunum_doldur();
					}
					else if (activ_sayfa == 2)
					{
						isim_doldur();
						yillik_gorunum_doldur();
					}
					ilk= false;
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(Gunluk.class.getResource("/ICONLAR/refresh-24.png")));
		panelToolbar.add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Geri");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int	activ_sayfa = tabloTabbedPane.getSelectedIndex();
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	 
					if (activ_sayfa == 0)
					{
						geri();
					}
					else if (activ_sayfa == 1)
					{
						ay_geri();
					}
					else if (activ_sayfa == 2)
					{
						yil_geri();
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
				} catch (Exception ex ) 
				{
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(Gunluk.class.getResource("/ICONLAR/icons_geri-24.png")));
		panelToolbar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setToolTipText("Ileri");
		btnNewButton_1.setIcon(new ImageIcon(Gunluk.class.getResource("/ICONLAR/icons_ileri-24.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int	activ_sayfa = tabloTabbedPane.getSelectedIndex();
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	 
					if (activ_sayfa == 0)
					{
						ileri();
					}
					else if (activ_sayfa == 1)
					{
						ay_ileri();
					}
					else if (activ_sayfa == 2)
					{
						yil_ileri();
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panelToolbar.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setToolTipText("Sonraki ilk Gorev");
		btnNewButton_2.setMaximumSize(new Dimension(30,0));
		panelToolbar.add(btnNewButton_2);
		btnNewButton_2.setIcon(new ImageIcon(Gunluk.class.getResource("/ICONLAR/forwardson.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int	activ_sayfa = tabloTabbedPane.getSelectedIndex();
				if (activ_sayfa == 0 || activ_sayfa == 1)
				{
					sonraki_gorev_bul();
				}
				else if (activ_sayfa == 2)
				{
					sonraki_gorev_bul_yil();
				}
			}
		});
		
        calendar = new JCalendar(Locale.UK);
        calendar.getMonthChooser().addPropertyChangeListener(new PropertyChangeListener() {
        	public void propertyChange(PropertyChangeEvent evt) {
        		if (ilk == true)
				{
					return;
				}
				try {
					int	activ_sayfa = tabloTabbedPane.getSelectedIndex();
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	 
					if (activ_sayfa == 0)
					{
						basla();
					}
					else if (activ_sayfa == 1)
					{
						aylik_gorunum_doldur();
					}
					else 
					{
						yillik_gorunum_doldur();
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
				} catch (ClassNotFoundException | ParseException |SQLException e) {
					e.printStackTrace();
				} 
        	}
        });
        JDayChooser dayChooser = calendar.getDayChooser();
        dayChooser.setAlwaysFireDayProperty(true); // here is the key
		calendar.setForeground(new Color(0, 128, 128));
		calendar.getDayChooser().setDayBordersVisible(true);
		calendar.getMonthChooser().getComboBox().setFont(new Font("Tahoma", Font.BOLD, 11));
		calendar.getYearChooser().setFont(new Font("Tahoma", Font.BOLD, 11));
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getNewValue() == evt.getOldValue()) return;
				if (ilk == true)
				{
					return;
				}
				try {
					int	activ_sayfa = tabloTabbedPane.getSelectedIndex();
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	 
					if (activ_sayfa == 0)
					{
						basla();
					}
					else if (activ_sayfa == 1)
					{
						aylik_gorunum_doldur();
					}
					else 
					{
						yillik_gorunum_doldur();
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
		calendar.setTodayButtonText("Bugun");
		calendar.setTodayButtonVisible(true);
		splitSolUst.setRightComponent(calendar);
		//**********************************************************************************		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane_2.setDividerSize(0);
		splitPane_2.setResizeWeight(1.0);
		splitPane.setRightComponent(splitPane_2);
		
		//**********************************************************************************		
		JPanel panel_2 = new JPanel();
		try {
			if ( GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("McWinLookAndFeel") ||  GLOBAL.setting_oku("PRG_GORUNUM").toString().equals("MintLookAndFeel"))
			{
				panel_2.setMinimumSize(new Dimension(300, 0));
			}
			else
			{
				panel_2.setMinimumSize(new Dimension(250, 0));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		splitPane_2.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_5 = new JSplitPane();
		splitPane_5.setDividerSize(0);
		splitPane_5.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_2.add(splitPane_5, BorderLayout.CENTER);

		comboIsim = new JComboBox<String>();
		//comboIsim.setForeground(new Color(0, 0, 139));
		comboIsim.setMinimumSize(new Dimension(0, 30));
		comboIsim.setMaximumSize(new Dimension(0, 30));
		splitPane_5.setLeftComponent(comboIsim);
		comboIsim.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboIsim.addActionListener (new ActionListener () 
		{
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
							else if (activ_sayfa == 1)
							{
								tree_temizle();
								aylik_gorunum_doldur();
							}
							else  
							{
								tree_temizle();
								yillik_gorunum_doldur();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		comboIsim.setModel(new DefaultComboBoxModel<String>(new String[] {"Hepsi"}));

		JSplitPane splitPane_6 = new JSplitPane();
		splitPane_6.setDividerSize(0);
		splitPane_6.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_5.setRightComponent(splitPane_6);

		ScrollPaneWin11 scrollPane_1 = new ScrollPaneWin11();
		 scrollPane_1.setMinimumSize(new Dimension(0, 525));
		 scrollPane_1.setMaximumSize(new Dimension(0, 525));
		splitPane_6.setLeftComponent(scrollPane_1);

		///
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Gorevler");
		treeGovev = new JTree(top);
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
							setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	 
							treeOgren();
							//btnNewButton_3.doClick();
							setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
						} catch (ClassNotFoundException | SQLException  | PropertyVetoException e2)
						{
						}
					}
				}
			}
		});
		scrollPane_1.setViewportView(treeGovev);
		JPanel panel_5 = new JPanel();
		splitPane_6.setRightComponent(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		
		treeGovev.getAutoscrolls();
		treeGovev.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		JPanel panel_3 = new JPanel();
		splitPane_2.setLeftComponent(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		//*************************************************TABLOLARIN TABBED PANE **********************************************
		tabloTabbedPane = new MaterialTabbed();
		tabloTabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		//tabloTabbedPane.setForeground(new Color(0, 0, 128));
		panel_3.add(tabloTabbedPane, BorderLayout.CENTER);
		//*************************************************Tablolarin Spliti ***************************************************
		JSplitPane splitPane_3 = new JSplitPane();
		tabloTabbedPane.addTab("Gunluk", null, splitPane_3, null);  // Gunluk tab paneli koy
		splitPane_3.setDividerSize(0);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		//panel_3.add(splitPane_3, BorderLayout.CENTER);
		//*********************************************************************************************************************		
		table_1 = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }

		};
		table_1.setModel(new DefaultTableModel(
				new Object[][] 
				{
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
		table_1.setShowHorizontalLines(true);
		table_1.setShowVerticalLines(true);
		//*****************************************************************************************************************		
		splitPane_3.setLeftComponent(table_1);
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		JPanel p = new JPanel();
		p.setOpaque(false);
		scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
		
		splitPane_3.setRightComponent(scrollPane);
		//*****************************************************************************************************************
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {     return false;          }

		};
		table.setSurrendersFocusOnKeystroke(true);
		
		table.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (table.getSelectedRow() < 0) return ;
				if (table.getSelectedColumn() == 0) return ;
				if (table.equals(e.getSource())) 
				{
					if (e.getClickCount() == 2)
					{
						boolean varmi = OBS_MAIN.pencere_bak("GOREV GIRIS");
						if (varmi) 
						{
							try {
								OBS_MAIN.pencere_aktiv_yap("GOREV GIRIS");
							} catch (PropertyVetoException e1) {
								e1.printStackTrace();
							}
						}
						else
						{
							JInternalFrame internalFrame;
							internalFrame  = new GOREV_GIRIS();
							OBS_MAIN.desktopPane.add(internalFrame);
							internalFrame.setVisible(true);
						}
						Gunluk_Bilgi gbilgi = new Gunluk_Bilgi();
						DefaultTableModel mdlBaslik = (DefaultTableModel) table_1.getModel();
						DefaultTableModel mdlGunluk = (DefaultTableModel) table.getModel();
						gbilgi.tarih1 = TARIH_CEVIR.tarih_sql(mdlBaslik.getValueAt(0, table.getSelectedColumn()).toString());
						gbilgi.saat1 = mdlGunluk.getValueAt(table.getSelectedRow(), 0).toString() ;
						
						GOREV_GIRIS.cmbBaslamaSaat.setSelectedItem(gbilgi.saat1);
						GOREV_GIRIS.dtcBaslama.setDate(TARIH_CEVIR.tarih_ekstre(gbilgi.tarih1));
						GOREV_GIRIS.dtcBitis.setDate(TARIH_CEVIR.tarih_ekstre(gbilgi.tarih1));
						
					}
					else {
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
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(	new Object[][] 
				{
					{"06:00", "", "", "", "", "", "", ""},
					{"07:00", "", "", "", "", "", "", ""},
					{"08:00", "", "", "", "", "", "", ""},
					{"09:00", "", "", "", "", "", "", ""},
					{"10:00", "", "", "", "", "", "", ""},
					{"11:00", "", "", "", "", "", "", ""},
					{"12:00", "", "", "", "", "", "", ""},
					{"13:00", "", "", "", "", "", "", ""},
					{"14:00", "", "", "", "", "", "", ""},
					{"15:00", "", "", "", "", "", "", ""},
					{"16:00", "", "", "", "", "", "", ""},
					{"17:00", "", "", "", "", "", "", ""},
					{"18:00", "", "", "", "", "", "", ""},
					{"19:00", "", "", "", "", "", "", ""},
					{"20:00", "", "", "", "", "", "", ""},
					{"21:00", "", "", "", "", "", "", ""},
					{"22:00", "", "", "", "", "", "", ""},
					{"23:00", "", "", "", "", "", "", ""},
				},
				new String[] {
						"", "", "", "", "", "", "", ""
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
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(80, 92, 124),Color.WHITE,"gunluk"));
		tc = tcm.getColumn(1);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(255, 177, 190),new Color(204, 0, 29),"gunluk"));
		tc = tcm.getColumn(2);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(86, 177, 220),new Color(171, 216, 237),"gunluk"));
		tc = tcm.getColumn(3);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(107, 173, 132),new Color(173, 209, 204),"gunluk"));
		tc = tcm.getColumn(4);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(226, 121, 28),new Color(249, 228, 209),"gunluk"));
		tc = tcm.getColumn(5);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(225, 207, 208),new Color(126, 78, 80),"gunluk"));
		tc = tcm.getColumn(6);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(198, 201, 234),new Color(55, 64, 149),"gunluk"));
		tc = tcm.getColumn(7);
		tc.setCellRenderer(new COLUMN_RENDERER(new Color(197, 235, 217),new Color(52, 152, 104),"gunluk"));
		table.setTableHeader(null);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}
		scrollPane.setViewportView(table);
		//****************************************Aylik***********************************
		scrolAylik =  new ScrollPaneWin11();
		tabloTabbedPane.addTab("Aylik", null, scrolAylik, null);
		//****************************************YILLIK *********************************
		JPanel panel_6 = new JPanel();
		tabloTabbedPane.addTab("Yillik", null, panel_6, null);
		panel_6.setLayout(new BorderLayout(0, 0));
		ScrollPaneWin11 scrollPane_2 = new ScrollPaneWin11();
		p = new JPanel();
		    p.setOpaque(false);
		    scrollPane_2.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
		
		panel_6.add(scrollPane_2, BorderLayout.CENTER);

		table_3 = new JTable() {
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		
		table_3.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if (table_3.getSelectedRow() < 0) return ;
				if (table_3.getSelectedColumn() == 0) return ;
				if (table_3.equals(e.getSource())) 
				{
					if (e.getClickCount() == 2)
					{
						boolean varmi = OBS_MAIN.pencere_bak("GOREV GIRIS");
						if (varmi  ) 
						{
							try {
								OBS_MAIN.pencere_aktiv_yap("GOREV GIRIS");
							} catch (PropertyVetoException e1) {
								e1.printStackTrace();
							}
						}
						else
						{
							JInternalFrame internalFrame;
							internalFrame  = new GOREV_GIRIS();
							OBS_MAIN.desktopPane.add(internalFrame);
							internalFrame.setVisible(true);
						}
						DefaultTableModel mdlYillik = (DefaultTableModel) table_3.getModel();
						int ay = table_3.getSelectedColumn();
						String gun  = mdlYillik.getValueAt(table_3.getSelectedRow(), 0).toString() ;
						String ayyString ;
						if(String.valueOf(ay).toString().length() == 1)
						{
							ayyString = "0" + String.valueOf(ay).toString() ;
						}
						else {
							ayyString = String.valueOf(ay).toString() ;
						}
						String gunnString ;
						if(String.valueOf(gun).toString().length() == 1)
						{
							gunnString = "0" + String.valueOf(gun).toString() ;
						}
						else {
							gunnString = String.valueOf(gun).toString() ;
						}
						SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
						String formatted = format1.format(calendar.getDate());
						String yilString = formatted.substring(6);
						GOREV_GIRIS.dtcBaslama.setDate(TARIH_CEVIR.tarih(gunnString + "." + ayyString + "." + yilString));
						GOREV_GIRIS.dtcBitis.setDate(TARIH_CEVIR.tarih(gunnString + "." + ayyString + "." + yilString));
					}
					else {
						table_3.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						try {
							yillik_detay_doldur(table_3.getSelectedRow(),table_3.getSelectedColumn());
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
						table_3.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
						
				}
			}
		});
	
		table_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table_3.setRowSelectionAllowed(false);
		table_3.setModel(new DefaultTableModel(	new Object[][] 
				{
					{"1", "", "", "", "", "", "", "", "", "", "", "", ""},
					{"2", "", "", "", "", "", "", "", "", "", "", "", ""},
					{"3", "", "", "", "", "", "", "", "", "", "", "", ""},
					{"4", "", "", "", "", "", "", "", "", "", "", "", ""},
					{"5", "", "", "", "", "", "", "", "", "", "", "", ""},
					{"6", "", "", "", "", "", "", "", "", "", "", "", ""},
					{"7", "", "", "", "", "", "", "", "", "", "", "", ""},
					{"8", "", "", "", "", "", "", "", "", "", "", "", ""},
					{"9", "", "", "", "", "", "", "", "", "", "", "", ""},
					{"10","", "", "", "", "", "", "", "", "", "", "", ""},
					{"11","", "", "", "", "", "", "", "", "", "", "", ""},
					{"12","", "", "", "", "", "", "", "", "", "", "", ""},
					{"13","", "", "", "", "", "", "", "", "", "", "", ""},
					{"14","", "", "", "", "", "", "", "", "", "", "", ""},
					{"15","", "", "", "", "", "", "", "", "", "", "", ""},
					{"16","", "", "", "", "", "", "", "", "", "", "", ""},
					{"17","", "", "", "", "", "", "", "", "", "", "", ""},
					{"18","", "", "", "", "", "", "", "", "", "", "", ""},
					{"19","", "", "", "", "", "", "", "", "", "", "", ""},
					{"20","", "", "", "", "", "", "", "", "", "", "", ""},
					{"21","", "", "", "", "", "", "", "", "", "", "", ""},
					{"22","", "", "", "", "", "", "", "", "", "", "", ""},
					{"23","", "", "", "", "", "", "", "", "", "", "", ""},
					{"24","", "", "", "", "", "", "", "", "", "", "", ""},
					{"25","", "", "", "", "", "", "", "", "", "", "", ""},
					{"26","", "", "", "", "", "", "", "", "", "", "", ""},
					{"27","", "", "", "", "", "", "", "", "", "", "", ""},
					{"28","", "", "", "", "", "", "", "", "", "", "", ""},
					{"29","", "", "", "", "", "", "", "", "", "", "", ""},
					{"30","", "", "", "", "", "", "", "", "", "", "", ""},
					{"31","", "", "", "", "", "", "", "", "", "", "", ""},
				},
				new String[] {
						"", "Ocak","Subat", "Mart", "Nisan", "Mayis", "Haziran", "Temmuz","Agustos","Eylul","Ekim","Kasim","Aralik"
				}
				));
		table_3.getColumnModel().getColumn(0).setMinWidth(40);
		for (int col = 1; col <= table_3.getColumnCount() -1; col++)
		{
			table_3.getColumnModel().getColumn(col).setHeaderRenderer(new COLUMN_RENDERER_YILLIK(new Color(80, 92, 124),Color.WHITE));	
		}
		for (int row = 0; row < table_3.getRowCount(); row++)
		{
			table_3.setRowHeight(row, 25);
		}
		JTableHeader th3 = table_3.getTableHeader();
		Dimension dd = th3.getPreferredSize();
		dd.height = 30;
		th3.setPreferredSize(dd); 
		
		TableColumnModel tcm3 = th3.getColumnModel();
		TableColumn tc3;
		tc3 = tcm3.getColumn(0);
		tc3.setCellRenderer(new COLUMN_RENDERER(new Color(80, 92, 124),Color.WHITE,"yillik"));
		tc3 = tcm3.getColumn(1);
		tc3.setCellRenderer(new COLUMN_RENDERER(new Color(255, 177, 190),new Color(204, 0, 29),"yillik"));
		tc3 = tcm3.getColumn(2);
		tc3.setCellRenderer(new COLUMN_RENDERER(new Color(86, 177, 220),new Color(171, 216, 237),"yillik"));
		tc3 = tcm3.getColumn(3);
		tc3.setCellRenderer(new COLUMN_RENDERER(new Color(107, 173, 132),new Color(173, 209, 204),"yillik"));
		tc3 = tcm3.getColumn(4);
		tc3.setCellRenderer(new COLUMN_RENDERER(new Color(226, 121, 28),new Color(249, 228, 209),"yillik"));
		tc3 = tcm3.getColumn(5);
		tc3.setCellRenderer(new COLUMN_RENDERER(new Color(225, 207, 208),new Color(126, 78, 80),"yillik"));
		tc3 = tcm3.getColumn(6);
		tc3.setCellRenderer(new COLUMN_RENDERER(new Color(198, 201, 234),new Color(55, 64, 149),"yillik"));
		tc3 = tcm3.getColumn(7);
		tc3.setCellRenderer(new COLUMN_RENDERER(new Color(197, 235, 217),new Color(52, 152, 104),"yillik"));
		tc3 = tcm3.getColumn(8);
		tc3.setCellRenderer(new COLUMN_RENDERER(new Color(255, 177, 190),new Color(204, 0, 29),"yillik"));
		tc3 = tcm3.getColumn(9);
		tc3.setCellRenderer(new COLUMN_RENDERER(new Color(86, 177, 220),new Color(171, 216, 237),"yillik"));
		tc3 = tcm3.getColumn(10);
		tc3.setCellRenderer(new COLUMN_RENDERER(new Color(107, 173, 132),new Color(173, 209, 204),"yillik"));
		tc3 = tcm3.getColumn(11);
		tc3.setCellRenderer(new COLUMN_RENDERER(new Color(226, 121, 28),new Color(249, 228, 209),"yillik"));
		tc3 = tcm3.getColumn(12);
		tc3.setCellRenderer(new COLUMN_RENDERER(new Color(197, 235, 217),new Color(52, 152, 104),"yillik"));
		table_3.setShowHorizontalLines(true);
		table_3.setShowVerticalLines(true);
		scrollPane_2.setViewportView(table_3);
		//********************************************************************************
		temizle();
		calendar.setDate(new Date());
		JPanel panel_4 = new JPanel();
		splitPane_1.setRightComponent(panel_4);
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
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	 
					basla();
				
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
				}
				else if (activ_sayfa == 1)  //Aylik Gorunum
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					aylik_gorunum_doldur();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
				}
				else if (activ_sayfa == 2)  //Yillik Gorunum
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					yillik_gorunum_doldur();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
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
			ilk=false;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public void basla() throws ClassNotFoundException, SQLException
	{
		try 
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
		catch (Exception ex) 
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
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
	private void yil_ileri() 
	{
		temizle();
		SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
		String formatted = format1.format(calendar.getDate());
		try 
		{
			Date qwe = new SimpleDateFormat("dd.MM.yyyy").parse(formatted);
			Calendar cal = Calendar.getInstance();
			cal.setTime(qwe);
			cal.add(Calendar.YEAR, 1); 
			calendar.setDate(new Date(cal.getTimeInMillis()));
		} catch (ParseException e) {
		}  
	}
	private void geri() 
	{
		temizle();
		SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
		String formatted = format1.format(calendar.getDate());
		try 
		{
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
	private void ay_geri() 
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
	private void yil_geri() 
	{
		temizle();
		SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
		String formatted = format1.format(calendar.getDate());
		try {
			Date qwe = new SimpleDateFormat("dd.MM.yyyy").parse(formatted);
			Calendar cal = Calendar.getInstance();
			cal.setTime(qwe);
			cal.add(Calendar.YEAR, -1); 
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
		tree_temizle();
	}
	private void tree_temizle()
	{
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
			gbilgi.isim = " AND ISIM = N'"+ comboIsim.getItemAt(comboIsim.getSelectedIndex()).toString() + "' " ;
		}
		ResultSet rs = g_Access.gorev_oku(gbilgi);

		if (!rs.isBeforeFirst() ) { 
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
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
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
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
	private void yillik_detay_doldur(int satir , int sutun) throws ClassNotFoundException, SQLException
	{
		DefaultTreeModel model = (DefaultTreeModel)treeGovev.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
		root.removeAllChildren(); //this removes all nodes
		model.reload(); //this notifies the listeners and changes the GUI
		Gunluk_Bilgi gbilgi = new Gunluk_Bilgi();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy");
		Date qwe = calendar.getDate();
		String formatted = format1.format(qwe);
	
		gbilgi.tarih1 = formatted + "." + sutun + "." + (satir + 1) ;
		gbilgi.tarih2 = formatted + "." + sutun + "." + (satir + 1) ;
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
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
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
		try 
		{
			Date qwe = calendar.getDate();
			Calendar cal = Calendar.getInstance(Locale.UK);  
			cal.setTime(qwe);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			int hangiGUN =  cal.get(Calendar.DAY_OF_WEEK);
			if (hangiGUN != 1)
			{
				hangiGUN -= 1; 
			}
			else {
				hangiGUN = 7;
			}
			int gunSAYISI = cal. getActualMaximum(Calendar. DATE);
			JPanel qweJPanel =  new Aylik_Gorunum(hangiGUN,gunSAYISI);
			scrolAylik.setViewportView(qweJPanel);
			ResultSet rSet = aylik_gorev_oku();
			if (!rSet.isBeforeFirst() ) { 
				return; // Kayit Yok
			} 
			while(rSet.next())
			{
				String tarString= rSet.getString("TARIH").toString();
				Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(tarString);  
				SimpleDateFormat formatter = new SimpleDateFormat ("d");
				String ewqString = formatter.format(date1);
				Component[] comp = qweJPanel.getComponents();
				for (int i = 0;i<comp.length;i++) {
					if (comp[i] instanceof JButton) {
						if ( ((JButton)comp[i]).getName()!= null)
						{
							if ( ((JButton)comp[i]).getName().equals(ewqString))
							{
								if(((JButton)comp[i]).getToolTipText().equals("<html><p style=text-align:center;> <font color = #303A68 > <b> " + ewqString + "</b> <br> </p></html>"))
								{
									String qweString = "<html><p style=text-align:center;> <font color = #303A68 > <b> " + ewqString + " </b> <br> </p>" ;
									qweString = qweString + "<p style=text-align:left;> <font color = #9C487F > " + rSet.getString("ISIM") +" / " +  rSet.getString("GOREV")  
									+  "</p></html>";
									((JButton)comp[i]).setToolTipText(qweString);
									((JButton)comp[i]).setText("<html><p style=text-align:center;> <font color = #02BF5F size=12> <b><u> " + qweString + "</u> </b> <br> </p></html>");

								}
								else
								{
									String dEVAM = ((JButton)comp[i]).getToolTipText().substring(0, ((JButton)comp[i]).getToolTipText().length() - 7);
									dEVAM = dEVAM + "<p style=text-align:left;> <font color = #9C487F > "  + rSet.getString("ISIM") + " -  <font color = #81B1A1 >" +  rSet.getString("GOREV")  
									+ "</p></br></html>";
									((JButton)comp[i]).setToolTipText(dEVAM);
									((JButton)comp[i]).setText("<html><p style=text-align:center;> <font color = #02BF5F size=12> <b><u> " + ((JButton)comp[i]).getName() + "</u>  </b> <br> </p></html>");
								}
							}
						}
					}
				}
			}
		}
		catch (Exception ex) 
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	private void yillik_gorunum_doldur() throws SQLException, ClassNotFoundException, ParseException
	{
		try 
		{
			for(int sat =0; sat <=30;sat++)
			{
				for(int i = 2; i<=13;i++)
				{
					table_3.getModel().setValueAt("",sat, i-1) ;	
				}
			}
			tree_temizle();
			ResultSet rSet = yillik_gorev_oku();
			if (!rSet.isBeforeFirst() ) { 
				return; 
			} 
			while(rSet.next())
			{
				for(int i = 2; i<=13;i++)
				{
					if(rSet.getInt(i)!= 0)
					{
						table_3.getModel().setValueAt("{"+rSet.getInt(i)+"}",rSet.getInt(1)-1, i-1) ;	
					}
					else 
					{
						table_3.getModel().setValueAt("",rSet.getInt(1)-1, i-1) ;	
					}
				}
			}
		}
		catch (Exception ex) 
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	private ResultSet yillik_gorev_oku() throws ClassNotFoundException, SQLException
	{
		Gunluk_Bilgi gbilgi = new Gunluk_Bilgi();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy");
		Date qwe = calendar.getDate();
		String formatted = format1.format(qwe);

		gbilgi.tarih1 = formatted +".01.01" ;
		gbilgi.tarih2 = formatted +".12.31" ;
		if (comboIsim.getItemAt(comboIsim.getSelectedIndex()).toString().equals("Hepsi"))
		{
			gbilgi.isim = "" ;
		}
		else 
		{
			gbilgi.isim = " ISIM = N'"+ comboIsim.getItemAt(comboIsim.getSelectedIndex()).toString() + "' AND " ;
		}
		ResultSet rs =  g_Access. gorev_oku_yillik_pivot(gbilgi);
		return rs;
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
			gbilgi.isim = " ISIM = N'"+ comboIsim.getItemAt(comboIsim.getSelectedIndex()).toString() + "' AND " ;
		}
        LocalDate givenDate = LocalDate.parse(formatted,   DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        LocalDate lastDayOfMonthDateGivenDate  = givenDate.withDayOfMonth( givenDate.getMonth().length(givenDate.isLeapYear()));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String formattedDate = lastDayOfMonthDateGivenDate.format(dateTimeFormatter);  //17-02-2022
        gbilgi.tarih2 =  formattedDate;
		ResultSet rs = g_Access.gorev_oku_aylik_grup(gbilgi);
		return rs;
	}
	public static void aYLIK(String tarih) throws ClassNotFoundException, SQLException
	{
		try {
			
		
		SimpleDateFormat formatt1 = new SimpleDateFormat("dd.MM.yyyy");
		String formattedd = formatt1.format(calendar.getDate());
			String yilString = formattedd.substring(6);
			String ayyString = formattedd.substring(3,5);
			String gunString ;
			if(String.valueOf(tarih).toString().length() == 1)
			{
				gunString = "0" + String.valueOf(tarih).toString() ;
			}
			else {
				gunString = String.valueOf(tarih).toString() ;
			}
				calendar.setDate(TARIH_CEVIR.tarih(gunString + "." + ayyString + "." + yilString));
		///
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
		} catch (Exception e) {
			
		}
	}
	public static void ay_gorev_ac()
	{
		boolean varmi = OBS_MAIN.pencere_bak("GOREV GIRIS");
		if (varmi  ) 
		{
			try {
				OBS_MAIN.pencere_aktiv_yap("GOREV GIRIS");
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
		else
		{
			JInternalFrame internalFrame;
			internalFrame  = new GOREV_GIRIS();
			OBS_MAIN.desktopPane.add(internalFrame);
			internalFrame.setVisible(true);
		}
		GOREV_GIRIS.dtcBaslama.setDate(calendar.getDate());
		GOREV_GIRIS.dtcBitis.setDate(calendar.getDate());
	}
	private void sonraki_gorev_bul()
	{
		try 
		{
			SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
			trh1 = format1.format(calendar.getDate());
			Date qwe = new SimpleDateFormat("dd.MM.yyyy").parse(trh1);	
			Calendar cal = Calendar.getInstance();
			cal.setTime(qwe);
			cal.add(Calendar.DAY_OF_MONTH, 1); 
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy.MM.dd");
			trh1 =  format2.format(cal.getTime());
			Gunluk_Bilgi gbilgi = new Gunluk_Bilgi();
			if (comboIsim.getItemAt(comboIsim.getSelectedIndex()).toString().equals("Hepsi"))
			{
				gbilgi.isim = "" ;
			}
			else 
			{
				gbilgi.isim = " AND ISIM = N'"+ comboIsim.getItemAt(comboIsim.getSelectedIndex()).toString() + "' " ;
			}
			gbilgi.tarih1 = trh1 ;
			ResultSet rs = g_Access.gorev_oku_sonraki(gbilgi);
			if (!rs.isBeforeFirst() ) { 
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
				return; // Kayit Yok
			} 
			rs.next();
			Date qwee = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("TARIH"));
			cal = Calendar.getInstance();
			cal.setTime(qwee);
			calendar.setDate(new Date(cal.getTimeInMillis()));
		} 
		catch (Exception ex) 
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	private void sonraki_gorev_bul_yil()
	{
		try 
		{
			SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
			trh1 = format1.format(calendar.getDate());
			Date qwe = new SimpleDateFormat("dd.MM.yyyy").parse(trh1);	
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(qwe);
			cal.add(Calendar.YEAR, 1); 
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy.MM.dd");
			trh1 =  format2.format(cal.getTime());
			
			trh1 = trh1.substring(0,4) + ".01.01" ;
			Gunluk_Bilgi gbilgi = new Gunluk_Bilgi();
			if (comboIsim.getItemAt(comboIsim.getSelectedIndex()).toString().equals("Hepsi"))
			{
				gbilgi.isim = "" ;
			}
			else 
			{
				gbilgi.isim = " AND ISIM = N'"+ comboIsim.getItemAt(comboIsim.getSelectedIndex()).toString() + "' " ;
			}
			gbilgi.tarih1 = trh1 ;
			ResultSet rs = g_Access.gorev_oku_sonraki_yil(gbilgi);
			if (!rs.isBeforeFirst() ) { 
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	 
				return; // Kayit Yok
			} 
			rs.next();
			Date qwee = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("TARIH"));
			cal = Calendar.getInstance();
			cal.setTime(qwee);
			calendar.setDate(new Date(cal.getTimeInMillis()));
		} 
		catch (Exception ex) 
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
}
//	 ResultSetMetaData rsMetaData = rSet.getMetaData();
//table_3.setModel(DbUtils.resultSetToTableModel(rSet));
//	int count = rsMetaData.getColumnCount();
//		for(int i = 1; i<=count; i++) {
//	         System.out.println(rsMetaData.getColumnName(i));
//	      }



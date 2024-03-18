package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.CheckBoxRenderer;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.MaterialTabbed;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;
import OBS_C_2025.CheckBoxHeader;

import javax.swing.JPanel;

import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings({"serial","unused","removal","static-access"})
public class ETIKET extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(OBS_SIS_2025_ANA_CLASS._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	static ResultSet rs = null ;
	//private static final Vector<?> Boolean = null;
	public static JTable table;
	private JLabel lbladet;
	private Obs_TextFIeld textField;
	public static Obs_TextFIeld textField_1;
	public static Obs_TextFIeld textField_2;
	public static Obs_TextFIeld textField_3;
	public static Obs_TextFIeld textField_4;
	public static Obs_TextFIeld textField_5;
	public static Obs_TextFIeld textField_6;
	public static JSpinner spinner;
	public static MaterialTabbed orTabbedPane;
	private boolean hEPSI = false;

	public ETIKET() {
		setMaximizable(true);
		setTitle("ETIKET");

		setIconifiable(true);
		setResizable(true);

		setClosable(true);
		setBounds(0, 0, 1200, 600);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(ETIKET.class.getResource("/ICONLAR/icons8-print-address-label-30.png")), 16, 16));//
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(0);
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);
		//
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		orTabbedPane = new MaterialTabbed();
		orTabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		//orTabbedPane.setForeground(new Color(0, 0, 128));
		orTabbedPane.addTab("Genel", null,scrollPane , null);  //
		JPanel tekPanel = new JPanel();
		tekPanel.setLayout(null);
		orTabbedPane.addTab("Tek Etiket", null,tekPanel , null);  //

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Bilgiler", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		//panel_2.setBorder(new TitledBorder(null, "Bilgiler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(43, 37, 431, 207);
		tekPanel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Unvan");
		lblNewLabel_3.setBounds(10, 37, 91, 14);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Adres_1");
		lblNewLabel_4.setBounds(10, 62, 91, 14);
		panel_2.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Adres_2");
		lblNewLabel_5.setBounds(10, 87, 91, 14);
		panel_2.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Telefon");
		lblNewLabel_6.setBounds(10, 112, 91, 14);
		panel_2.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Semt");
		lblNewLabel_7.setBounds(10, 137, 91, 14);
		panel_2.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Sehir");
		lblNewLabel_8.setBounds(10, 162, 91, 14);
		panel_2.add(lblNewLabel_8);

		textField_1 = new Obs_TextFIeld(50,"");
		textField_1.setBounds(111, 34, 267, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new Obs_TextFIeld(50,"");
		textField_2.setBounds(111, 59, 267, 20);
		panel_2.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new Obs_TextFIeld(50,"");
		textField_3.setBounds(111, 84, 267, 20);
		panel_2.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new Obs_TextFIeld(50,"");
		textField_4.setBounds(111, 109, 267, 20);
		panel_2.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new Obs_TextFIeld(50,"");
		textField_5.setBounds(111, 134, 267, 20);
		panel_2.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new Obs_TextFIeld(50,"");
		textField_6.setBounds(111, 159, 267, 20);
		panel_2.add(textField_6);
		textField_6.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Yazdirma Adedi");
		lblNewLabel_2.setBounds(496, 47, 108, 14);
		tekPanel.add(lblNewLabel_2);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinner.setBounds(613, 44, 49, 20);
		tekPanel.add(spinner);

		splitPane_1.setLeftComponent(orTabbedPane);
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));


		splitPane_1.setRightComponent(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Secilen Satir :");
		lblNewLabel.setBounds(10, 5, 85, 14);
		panel.add(lblNewLabel);

		lbladet = new JLabel("0");
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setBounds(100, 5, 57, 14);
		//lbladet.setForeground(new Color(0, 0, 128));
		panel.add(lbladet);

		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(0, 50));
		panel_1.setMaximumSize(new Dimension(0, 50));
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Arama");
		lblNewLabel_1.setBounds(10, 14, 48, 14);
		panel_1.add(lblNewLabel_1);

		textField = new Obs_TextFIeld(30,"");
		textField.setBounds(87, 11, 259, 20);
		textField.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void changedUpdate(DocumentEvent e) 
			{
				try {
					arama();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
			public void removeUpdate(DocumentEvent e) 
			{
				try {
					arama();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
			public void insertUpdate(DocumentEvent e) 
			{
				try {
					arama();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
//		textField.addMouseListener(new MouseAdapter() {
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				if (e.getClickCount() == 2) 
//				{
//					ADRES_LISTE asp ;
//					asp = new ADRES_LISTE();
//					asp.setVisible(true);
//					textField.setText( oac.hsp_hsp_kodu);
//				}
//			}
//		});

		panel_1.add(textField);
		textField.setColumns(10);
		//

		table = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {  
				switch (column) {
				case 0:
					return true;
				default:
					return false;
				}
			}
		};
		hisset();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
	}
	public void hisset()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			rs = a_Access.adr_etiket("Adi");
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
				th.repaint();
				table.repaint();
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;

				tc = tcm.getColumn(0);
				JCheckBox checkBox = new JCheckBox();
				checkBox.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
				checkBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JTableHeader th = table.getTableHeader();
						TableColumnModel tcm = th.getColumnModel();
						TableColumn tc = tcm.getColumn(0);
						tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
						th.repaint();
						table.repaint();

					}
				});
				checkBox.setHorizontalAlignment(JCheckBox.CENTER);
				DefaultCellEditor dce = new DefaultCellEditor( checkBox );
				tc.setCellEditor(dce);
				tc.setCellRenderer(new CheckBoxRenderer());
				tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));

				tc.setMinWidth(50);
				tc.setMaxWidth(50);

				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);

				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);

				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);

				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);

				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);

				Dimension dd = table.getPreferredSize();
				dd.height = 30;
				th.setPreferredSize(dd); 

				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);

				int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				table.setRowSelectionInterval(lastRow, lastRow);
				table.getModel().addTableModelListener(	(TableModelListener) new TableModelListener() 
				{
					public void tableChanged(TableModelEvent e) 
					{
						TableModel model = (TableModel)e.getSource();
						if (model.getRowCount() > 0) {
							if(!hEPSI)
								secilen_satir();
						}
					}
				});
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

				String deger;
				String[] parts;
				Font bigFont;
				deger = GLOBAL.setting_oku("STK_RAPORLAMA").toString();
				deger = deger.substring(1, deger.length()-1);
				parts = deger.split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				table.setFont(bigFont);
			}
		} 
		catch (Exception ex) {
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
		}
	}
	private int satir_kontrol()
	{
		int satir = 0 ;
		DefaultTableModel modell = (DefaultTableModel)table.getModel();
		for ( int i = 0; i <=  modell.getRowCount() - 1;i++)
		{
			if ( modell.getValueAt(i,0) != null) 
			{
				if (  modell.getValueAt(i,0).toString().equals("true"))
					satir += 1 ;
			};
		}
		return satir ;
	}
	public void arama() throws ClassNotFoundException, SQLException  
	{
		if(ETIKET.orTabbedPane.getSelectedIndex() == 0)
		{
			if (textField.getText().equals(""))
				table.setRowSorter(null);
			else
			{
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText().toLowerCase()));
				table.setRowSorter(sorter);
			}
		}
		else
		{
			if(textField.getText().equals("")) 
			{
				temizle();
				return;
			}
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
			ResultSet rstResultSet = a_Access.adr_etiket_arama(textField.getText());
			if (!rstResultSet.isBeforeFirst() ) { 
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
				temizle();
				return; // Kayit Yok
			} 
			rstResultSet.next();
			textField_1.setText(rstResultSet.getString("Adi"));
			textField_2.setText(rstResultSet.getString("Adres_1"));
			textField_3.setText(rstResultSet.getString("Adres_2"));
			textField_4.setText(rstResultSet.getString("Tel_1"));
			textField_5.setText(rstResultSet.getString("Semt"));
			textField_6.setText(rstResultSet.getString("Sehir"));
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		}
	}
	private void temizle()
	{
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
	}
	private void secilen_satir()
	{
		lbladet.setText(FORMATLAMA.doub_0(satir_kontrol()));
	}
	///********
	class MyItemListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			//
			//Runnable runner = new Runnable()
			//{ public void run() {
			//
			Object source = e.getSource();
			if (source instanceof AbstractButton == false) return;
			boolean checked = e.getStateChange() == ItemEvent.SELECTED;
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
			//Progres_Bar_Temizle();  
			//OBS_MAIN.progressBar.setStringPainted(true);
			// OBS_MAIN.progressBar.setMaximum(table.getRowCount()-1); 
			hEPSI  = true ;
			for(int x = 0, y = table.getRowCount(); x < y; x++)
			{
				//Progres_Bar(table.getRowCount()-1, x);
				table.setValueAt(new Boolean(checked),x,0);
			}
			hEPSI = false;
			secilen_satir();
			//Progres_Bar_Temizle();
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			//// Progress Bar
			//}
			//};
			//Thread t = new Thread(runner, "Code Executer");
			//t.start();
			//
		}
		static void Progres_Bar(int max, int deger) throws InterruptedException
		{
			OBS_MAIN.progressBar.setValue(deger);
		}
		static void Progres_Bar_Temizle()
		{
			OBS_MAIN.progressBar.setMaximum(0);
			OBS_MAIN.progressBar.setValue(0);
			OBS_MAIN.progressBar.setStringPainted(false);
		}
	}
}


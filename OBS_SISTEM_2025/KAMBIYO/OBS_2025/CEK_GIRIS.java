package OBS_2025;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.COKLU_GIRIS_TARIH;
import OBS_C_2025.DoubleEditor;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JDateChooserEditor;

import OBS_C_2025.KAMBIYO_ACCESS;
import OBS_C_2025.KambiyoCombo;
import OBS_C_2025.MaterialTabbed;
import OBS_C_2025.Next_Cell_Kereste;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.dEKONT_BILGI;
import OBS_C_2025.lOG_BILGI;
import raven.toast.Notifications;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.apache.commons.lang.StringUtils;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.SwingConstants;
import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.InputMap;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({"serial","static-access","removal"})
public class CEK_GIRIS extends JInternalFrame {
	private static JTable table;
	public static Obs_TextFIeld textField;
	public static Obs_TextFIeld textField_1;
	private static Obs_TextFIeld textField_2;
	public static Obs_TextFIeld textField_4;
	private static Obs_TextFIeld textField_5;
	private static Obs_TextFIeld textField_6;
	public static JLabel lblNewLabel_2 ;
	public static JDateChooser dateChooser ;
	private static JComboBox<String> comboBox  ;
	public static JLabel label;
	public static JLabel lblNewLabel_12;
	private JLabel lblNewLabel_7;
	private JLabel lblOrtgun ;
	
	private static ArrayList<String> listBanka = new ArrayList<String>();
	private static ArrayList<String> listSube =  new ArrayList<String>();

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KAMBIYO_ACCESS ka_Access = new KAMBIYO_ACCESS(OBS_SIS_2025_ANA_CLASS._IKambiyo , OBS_SIS_2025_ANA_CLASS._IKambiyo_Loger);
	static CARI_ACCESS c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	public CEK_GIRIS() {

		setTitle("CEK GIRIS");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 1250, 600);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(CEK_GIRIS.class.getResource("/ICONLAR/icons8-check-book-30.png")), 16, 16));//
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		//splitPane.setDividerLocation(70);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOneTouchExpandable(true);
		splitPane_1.setResizeWeight(1.0);

		splitPane_1.setDividerSize(0);
		//splitPane_1.setDividerLocation(30);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);

		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		splitPane_1.setLeftComponent(scrollPane);

		DefaultTableModel model = new DefaultTableModel() ; 
		table = new JTable(model) {
			
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				String status = (String)table.getModel().getValueAt(row,0);
					if (col == 1)
					{
						if(status=="")
						{
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							model.setValueAt( "" , row,  1);
						}
					} 
				return c;
			}
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			table.setGridColor(oac.gridcolor);
		model.addColumn("Cek No", new String []{"0"});
		model.addColumn("Vade", new Date []{ new Date() });
		model.addColumn("Banka", new String []{""});
		model.addColumn("Sube", new String []{""});
		model.addColumn("Seri No", new String []{""});
		model.addColumn("Ilk Borclu", new String []{""});
		model.addColumn("Hesap", new String []{"" });
		model.addColumn("Cins", new String []{"" });
		model.addColumn("Tutar",new Double [] {new Double( 0 )});
		model.addColumn("Cikis_Bordro", new String []{"" });
		model.addColumn("Cikis_Tarihi", new Date []{ new Date() });
		model.addColumn("Cikis_Musteri", new String []{"" });
		model.addColumn("Durum", new String []{"" });
		model.addColumn("T_Tarih", new Date []{ new Date() });
		model.addColumn("Cikis_Ozel_Kod", new String []{"" });


		TableColumn col ;

		col = table.getColumnModel().getColumn(0);
		col.setCellEditor(new KAM_CEKNO(new Obs_TextFIeld(),"CG"));
		col.setCellRenderer(new KAM_CEKNO_REN());
		col.setMinWidth(100);
		col.setHeaderRenderer(new SOLA());

		col = table.getColumnModel().getColumn(1);
		col.setCellEditor(new JDateChooserEditor(new JCheckBox()));
		col.setHeaderRenderer(new SOLA());
		col.setCellRenderer(new COKLU_GIRIS_TARIH());
		col.setMinWidth(100);

		col = table.getColumnModel().getColumn(2);
		col.setMinWidth(250);
		listBanka = new ArrayList<String> () ;
		banka_sube_doldur("Banka") ;
		KambiyoCombo editorBanka = new KambiyoCombo(listBanka ,table,"banka",25);
		col.setCellEditor(editorBanka);
		col.setHeaderRenderer(new SOLA());

		col = table.getColumnModel().getColumn(3);
		col.setMinWidth(150);
		listSube = new ArrayList<String> () ;
		banka_sube_doldur("Sube") ;
		KambiyoCombo editorSube = new KambiyoCombo(listSube ,table,"sube",25);
		col.setCellEditor(editorSube);
		col.setHeaderRenderer(new SOLA());

		col = table.getColumnModel().getColumn(4);
		col.setMinWidth(150);
		Obs_TextFIeld seri = new Obs_TextFIeld(15);
		col.setCellEditor(new DefaultCellEditor(seri));
		col.setHeaderRenderer(new SOLA());

		col = table.getColumnModel().getColumn(5);
		col.setMinWidth(150);
		Obs_TextFIeld ilkb = new Obs_TextFIeld(30);
		col.setCellEditor(new DefaultCellEditor(ilkb));
		col.setHeaderRenderer(new SOLA());

		col = table.getColumnModel().getColumn(6);
		col.setMinWidth(150);
		Obs_TextFIeld hsp = new Obs_TextFIeld(15);
		col.setCellEditor(new DefaultCellEditor(hsp));
		col.setHeaderRenderer(new SOLA());

		col = table.getColumnModel().getColumn(7);
		col.setMinWidth(50);
		col.setMaxWidth(50);
		Obs_TextFIeld cins = new Obs_TextFIeld(3);
		col.setCellEditor(new DefaultCellEditor(cins));
		col.setHeaderRenderer(new SOLA());

		col = table.getColumnModel().getColumn(8);
		col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,true));
		col.setMinWidth(115);

		for (int i=0 ; i< 6;i++)
			table.removeColumn(table.getColumnModel().getColumn(9));
		JTableHeader th = table.getTableHeader();
		Dimension dd = table.getPreferredSize();
		dd.height = 30;
		th.setPreferredSize(dd); 
		table.setRowSelectionInterval(0, 0);
		table.setRowHeight(21);
		table.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				topla();
			}
		});
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setRowSelectionAllowed(false);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		InputMap im = table.getInputMap(JTable.WHEN_FOCUSED);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Action.NextCell");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Action.NextCell");
		ActionMap am = table.getActionMap();
		am.put("Action.NextCell", new Next_Cell_Kereste(table,"cek_gir"));
		//table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		
		th.repaint();
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		splitPane_1.setRightComponent(panel);
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		panel.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Evrak Sayisi :");
		lblNewLabel_6.setBounds(10, 5, 83, 14);
		panel.add(lblNewLabel_6);

		lblNewLabel_12 = new JLabel("0");
		lblNewLabel_12.setBounds(103, 5, 58, 14);
		panel.add(lblNewLabel_12);

		label = new JLabel("0.00");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(1073, 5, 136, 14);
		panel.add(label);

		MaterialTabbed tabbedPane = new MaterialTabbed();
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.setMinimumSize(new Dimension(0, 135));
		tabbedPane.setMaximumSize(new Dimension(0, 135));
		splitPane.setLeftComponent(tabbedPane);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Cek Giris", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Bordro No");
		lblNewLabel.setBounds(10, 11, 67, 14);
		panel_2.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Hesap");
		lblNewLabel_1.setBounds(10, 38, 46, 14);
		panel_2.add(lblNewLabel_1);

		textField = new Obs_TextFIeld(10);

		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					try {
						int sno = 0 ;
						sno  = ka_Access.kam_bordro_no_al("CEK_G") ;
						int kj = 0 ;
						kj = 10 - Integer.toString(sno).length() ;
						String str_ = StringUtils.repeat("0", kj)   + Integer.toString(sno);
						textField.setText(str_);
					}
					catch (Exception ex)
					{
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
					}
				}
			}
		});
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.setBounds(87, 8, 120, 20);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				kontrol();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			public void removeUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				kontrol();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			public void insertUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				kontrol();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		panel_2.add(textField);
		textField.setColumns(10);

		textField_1 = new Obs_TextFIeld(12);

		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textField_1.setText( oac.hsp_hsp_kodu);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_1.setBounds(87, 35, 120, 20);
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				lblNewLabel_2.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			public void removeUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				lblNewLabel_2.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			public void insertUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				lblNewLabel_2.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});

		panel_2.add(textField_1);
		textField_1.setColumns(10);

		lblNewLabel_2 = new JLabel(".....");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(87, 60, 273, 14);
		panel_2.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int int_1  = 0;
					String sts ="" ;
					//**************** EVRAK NO OKU ************
					getContentPane().setCursor(oac.WAIT_CURSOR);
					sts  = ka_Access.kam_son_bordro_no_al("CEK","Giris_Bordro") ;
					if ( sts.equals(""))
					{
						getContentPane().setCursor(oac.DEFAULT_CURSOR);
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, "Dosyada Hic Kayit Yok.....");
						return ;
					}
					//**********SIFIRLAR*************************
					int_1 = 10 - sts.toString().length() ;
					String str_ = StringUtils.repeat("0", int_1)   + sts.toString();
					textField .setText(str_) ;
					textField .requestFocus();
					int_1 = 0;
					//********************************************
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				}
				catch (Exception ex)
				{
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(CEK_GIRIS.class.getResource("/ICONLAR/icons8-view-16.png")));
		btnNewButton.setBounds(215, 7, 25, 22);
		panel_2.add(btnNewButton);

		JLabel lblNewLabel_3 = new JLabel("Tarih");
		lblNewLabel_3.setBounds(261, 11, 46, 14);
		panel_2.add(lblNewLabel_3);

		dateChooser = new JDateChooser();
		((JTextField)dateChooser.getDateEditor()).setBackground(oac.dtcColor);
		JCalendar qweCalendar =  dateChooser.getJCalendar();
		qweCalendar.getYearChooser().getSpinner().setBackground(oac.dtcColor);
		dateChooser.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser.setDate(new Date());
			}
		});
		dateChooser.setBounds(312, 8, 129, 22);
		dateChooser.setDateFormatString("dd.MM.yyyy");
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 14));
		dateChooser.setDate(new Date());

		dateChooser.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try 
					{
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, -1); 
						dateChooser.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
						dateChooser.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel_2.add(dateChooser);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Vade Farki", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(451, 5, 352, 53);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("%");
		lblNewLabel_4.setBounds(10, 27, 25, 14);
		panel_3.add(lblNewLabel_4);

		textField_2 = new Obs_TextFIeld();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_2.setBounds(39, 24, 50, 20);
		textField_2.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
			}
			public void removeUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				ort();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			public void insertUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				ort();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});

		panel_3.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Ort.Gun");
		lblNewLabel_5.setBounds(93, 27, 59, 14);
		panel_3.add(lblNewLabel_5);

		lblNewLabel_7 = new JLabel("0.00");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setBounds(228, 26, 101, 14);
		panel_3.add(lblNewLabel_7);
		
		lblOrtgun = new JLabel("0");
		lblOrtgun.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrtgun.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrtgun.setBounds(152, 27, 44, 14);
		panel_3.add(lblOrtgun);

		JLabel lblNewLabel_8 = new JLabel("Ozel Kod");
		lblNewLabel_8.setBounds(826, 12, 67, 14);
		panel_2.add(lblNewLabel_8);

		comboBox = new JComboBox<String>();
		comboBox.setForeground(new Color(0, 0, 128));
		comboBox.setEditable(true);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox.setBounds(918, 8, 129, 22);
		panel_2.add(comboBox);

		JLabel lblNewLabel_9 = new JLabel("Doviz Turu");
		lblNewLabel_9.setBounds(826, 39, 82, 14);
		panel_2.add(lblNewLabel_9);

		textField_4 = new Obs_TextFIeld(3);
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_4.setBounds(918, 35, 62, 20);
		panel_2.add(textField_4);
		textField_4.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		tabbedPane.addTab("Aciklama", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel_10 = new JLabel("Aciklama 1");
		lblNewLabel_10.setBounds(10, 11, 72, 14);
		panel_1.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Aciklama 2");
		lblNewLabel_11.setBounds(10, 36, 72, 14);
		panel_1.add(lblNewLabel_11);

		textField_5 = new Obs_TextFIeld(50);
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_5.setBounds(92, 8, 563, 20);
		panel_1.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new Obs_TextFIeld(50);
		textField_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_6.setBounds(92, 33, 563, 20);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		GRID_TEMIZLE.grid_temizle(table);
		String deger;
		Integer sat_sayi;
		try 
		{
			deger = GLOBAL.setting_oku("KAM_CEK_GIR").toString();
			sat_sayi =Integer.parseInt(deger);
			for (int i = 0; i <= sat_sayi; i ++)
				satir_ilave();
			textField_4.setText(GLOBAL.setting_oku("PRG_PARA").toString());
			textField.requestFocus();
		} catch (Exception ex) {
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}

	public static void satir_ilave()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		int satir = table.getSelectedRow();
		if ( satir  < 0 ) 
		{
			mdl.addRow(new Object[]{"", new Date(),"","","","","","",0.00,"",TARIH_CEVIR.tarih("01.01.1900"),"","",TARIH_CEVIR.tarih("01.01.1900"),""});
			satir = 0 ;
		}
		else
			mdl.insertRow(satir, new Object[]{"",  new Date(),"","","","","","",0.00,"",TARIH_CEVIR.tarih("01.01.1900"),"","",TARIH_CEVIR.tarih("01.01.1900"),""});
		table.isRowSelected(satir);
		table.repaint();
	}
	public static void satir_sil()
	{
		if (table.getSelectedRow() < 0 ) return ;
		DefaultTableModel mdll = (DefaultTableModel) table.getModel();
		mdll.removeRow(table.getSelectedRow());
		table.repaint();
		topla();
	}
	public static void kaydet() 
	{
		if (textField.getText().equals("")) return ;
		if(dateChooser.getDate() == null) return;
		DefaultTableModel mdll = (DefaultTableModel) table.getModel();
		if (mdll.getRowCount() == 0 ) return ;	
		try {
			satir_yaz_1() ;
			acik_yaz() ;
			textField.setText("");
			banka_sube_doldur("Banka") ;
			banka_sube_doldur("Sube") ;
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	private static void satir_yaz_1() throws ClassNotFoundException, SQLException
	{
		ka_Access.bordro_sil("CEK",textField.getText(),"Giris_Bordro");
		DefaultTableModel mdll = (DefaultTableModel) table.getModel();
		for (int i = 0 ; i < mdll.getRowCount() ; i ++)
		{
			if (! mdll.getValueAt(i,0).toString().equals(""))
				satir_yaz_2(i) ;
		}
	}
	private static void satir_yaz_2(int i) 
	{
		try {
			String cbo, cmus, cozk, drm ;
			cbo = "" ;
			cmus = "" ;
			cozk = "" ;
			String asd ;
			DefaultTableModel mdll = (DefaultTableModel) table.getModel();
			if ( mdll.getValueAt(i,9) !=null) // Cikis Bordro
			{
				cbo =  mdll.getValueAt(i,9).toString();  // Cikis Bordro
				asd = dateFormater(mdll.getValueAt(i , 10).toString() , "yyyy.MM.dd", "EEE MMM dd kk:mm:ss zzzz yyyy" ) ;        		// Cikis Tarihi
				cmus =  mdll.getValueAt(i,11).toString(); //Cikis Musteri
				cozk =  mdll.getValueAt(i,14).toString(); // Cikis Ozel Kod
			}
			else
				asd = dateFormater(mdll.getValueAt(i , 10).toString() , "yyyy.MM.dd", "EEE MMM dd kk:mm:ss zzzz yyyy" ) ;        		// Cikis Tarihi
			if (mdll.getValueAt(i,12) !=null)
				drm =  mdll.getValueAt(i,12).toString();
			else
				drm = "" ;
			String ttarr = dateFormater(mdll.getValueAt(i , 13).toString() , "yyyy.MM.dd", "EEE MMM dd kk:mm:ss zzzz yyyy" ) ;
			String vade = "";
			if (mdll.getValueAt(i , 1).toString().length() >  10)
				vade = dateFormater(mdll.getValueAt(i , 1).toString() , "yyyy.MM.dd", "EEE MMM dd kk:mm:ss zzzz yyyy" ) ;
			else
			{
				String qwe =dateFormater(mdll.getValueAt(i , 1).toString() , "yyyy.MM.dd", "dd.MM.yyyy" ) ;
				vade  = qwe;
			}
			String gtt = TARIH_CEVIR.tarih_geri(dateChooser);
			String cMB ="";
			if( comboBox.getSelectedItem() != null )
				cMB = comboBox.getSelectedItem().toString();
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ(textField.getText() + " Nolu Giris Bordro  " + mdll.getValueAt(i,0).toString() + " Nolu Cek " + " Tutar:" + (double) mdll.getValueAt(i,8));
			lBILGI.seteVRAK(textField.getText());
			ka_Access.cek_kayit(mdll.getValueAt(i,0).toString(), vade,
					textField.getText(), textField_1.getText(),
					gtt,    cMB,
					asd, cbo, cmus, cozk,mdll.getValueAt(i,2).toString()
					, mdll.getValueAt(i,3).toString(), (double) mdll.getValueAt(i,8),
					mdll.getValueAt(i,7).toString(),mdll.getValueAt(i,4).toString(), mdll.getValueAt(i,5).toString()
					, mdll.getValueAt(i,6).toString(), drm, ttarr, GLOBAL.KULL_ADI,
					lBILGI , BAGLAN_LOG.kamLogDizin);
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	private static void acik_yaz()
	{
		try {
			ka_Access.kam_aciklama_sil("CEK", textField.getText(), "G");
			ka_Access.kam_aciklama_yaz("CEK", 1, textField.getText(), textField_5.getText(), "G");
			ka_Access.kam_aciklama_yaz("CEK", 2, textField.getText(), textField_6.getText(), "G");
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	public static void sil()
	{
		if ( textField.getText().equals("")) return ;
		if (table.getRowCount() == 0) return ;
		int g = JOptionPane.showOptionDialog( null,  "Islem Dosyadan Silinecek ..?", "Cek Dosyasindan Evrak Silme",   JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,null, oac.options, oac.options[1]); 
		if(g != 0 ) { return;	}	
		try {
			ka_Access.bordro_sil("CEK",textField.getText(),"Giris_Bordro");
			ka_Access.kam_aciklama_sil("CEK", textField.getText(), "G");
			textField.setText("");
			banka_sube_doldur("Banka") ;
			banka_sube_doldur("Sube") ;
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	private void kontrol()
	{
		GRID_TEMIZLE.grid_temizle(table);
		if (textField.getText().toString().length() == 0)
		{
			textField_1.setText("");
			dateChooser.setDate(new Date());
			textField_2.setText("");
			lblOrtgun.setText("");
			comboBox.removeAllItems();
			topla();
			String deger;
			Integer sat_sayi;
			try {
				textField_4.setText(GLOBAL.setting_oku("PRG_PARA").toString());
				deger = GLOBAL.setting_oku("KAM_CEK_GIR").toString();
				sat_sayi =Integer.parseInt(deger);
				for (int i = 0; i <= sat_sayi; i ++)
					satir_ilave();
				textField.requestFocus();
			} catch (Exception ex) {
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
			}
			return;
		}
		try 
		{
			getContentPane().setCursor(oac.WAIT_CURSOR);
			ResultSet rs = null ;
			rs= ka_Access.kam_bordno("CEK",textField.getText(),"Giris_Bordro");
			int kayit_sayi = 0 ;
			if (!rs.isBeforeFirst() ) {  
				textField_1.setText("");
				dateChooser.setDate(new Date());
				textField_2.setText("");
				lblOrtgun.setText("");
				comboBox.removeAllItems();
				topla();
			} 
			else
			{
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				int ozk = 0 ;
				while (rs.next()) 
				{
					textField_1.setText(rs.getString("Giris_Musteri"));
					dateChooser.setDate(rs.getDate("Giris_Tarihi"));
					if (ozk == 0) comboBox.addItem(rs.getString("Giris_Ozel_Kod"));
					ozk=1 ;
					SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
					String vade1 =  format1.format(rs.getDate("Vade"));
					String sDate1= "" ;
					sDate1= rs.getDate("Cikis_Tarihi").toString(); 
					Date ct= new SimpleDateFormat("yyyy-MM-dd").parse(sDate1); 
					sDate1= rs.getDate("T_Tarih").toString(); 
					Date tt= new SimpleDateFormat("yyyy-MM-dd").parse(sDate1); 
					mdll.addRow(new Object[]{rs.getString("Cek_No"), vade1,
							rs.getString("Banka"),rs.getString("Sube"),rs.getString("Seri_No"),
							rs.getString("Ilk_Borclu"),rs.getString("Cek_Hesap_No"),rs.getString("Cins"),
							rs.getDouble("Tutar"),
							rs.getString("Cikis_Bordro"),
							ct,
							rs.getString("Cikis_Musteri"),rs.getString("Durum"),
							tt,rs.getString("Cikis_Ozel_Kod")});
					kayit_sayi += 1 ;
				}
				//'************ACIKLAMA OKU ***********************************************************
				textField_5.setText(ka_Access.kam_aciklama_oku("CEK","1",textField.getText(),"G"));
				textField_6.setText(ka_Access.kam_aciklama_oku("CEK","2",textField.getText(),"G"));
			}
			topla();
			getContentPane().setCursor(oac.WAIT_CURSOR);
			satir_tamamla(Integer.parseInt(GLOBAL.setting_oku("KAM_CEK_GIR").toString()) - kayit_sayi );
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	private void satir_tamamla(int satir)
	{
		DefaultTableModel mdll = (DefaultTableModel) table.getModel();
		for (int i = 0; i <= satir; i ++)
			mdll.addRow(new Object[]{"", new Date(),"","","","","","",0.00,"",TARIH_CEVIR.tarih("01.01.1900"),"","",TARIH_CEVIR.tarih("01.01.1900"),""});
	}
	private static void topla()
	{
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		double tutar = 0 ;
		int evr_sayi = 0 ;
		for (int i = 0; i <= table.getRowCount() - 1 ; i ++)
		{
			if (! model.getValueAt(i,0).toString().equals(""))
			{
				tutar  += (double) model.getValueAt(i , 8);
				evr_sayi += 1 ;
			}
		}
		label.setText(FORMATLAMA.doub_2(tutar));
		lblNewLabel_12.setText(FORMATLAMA.doub_0(evr_sayi));
	}
	private void ort()
	{
		try
		{
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			if (model.getRowCount()  == 0 ) return ;
			if (textField_2.getText().equals("")) 
			{
				lblNewLabel_7.setText(FORMATLAMA.doub_2(0.00));
				lblOrtgun.setText("");
				return;
			}
			getContentPane().setCursor(oac.WAIT_CURSOR);
			double tfaiz ,gun,faiz,orgun,toppara,double_0 ;
			tfaiz = 0;
			gun = 0;
			faiz = 0;
			orgun = 0;
			toppara = 0;
			for (int i =0 ; i<= model.getRowCount() -1 ; i ++) {
				gun = 0;
				faiz = 0;
				orgun = 0;
				if (!  model.getValueAt(i , 0).toString().equals("")) 
				{
					String vade = "";
					if (model.getValueAt(i , 1).toString().length() >  10)
						vade = dateFormater(model.getValueAt(i , 1).toString() , "yyyy.MM.dd", "EEE MMM dd kk:mm:ss zzzz yyyy" ) ;
					else
					{
						String qwe =dateFormater(model.getValueAt(i , 1).toString() , "yyyy.MM.dd", "dd.MM.yyyy" ) ;
						vade  = qwe;
					}
					String gtt = TARIH_CEVIR.tarih_geri(dateChooser);
					Date vadee =new SimpleDateFormat("yyyy.MM.dd").parse(vade); 
					Date bugun =new SimpleDateFormat("yyyy.MM.dd").parse(gtt);  
					gun  = daysBetween(bugun,vadee);
					double_0 = Double.parseDouble(textField_2.getText());
					faiz = ((((double) model.getValueAt(i , 8) * double_0) / 365) * gun) / 100;
					toppara = toppara + (double) model.getValueAt(i , 8);
					tfaiz = tfaiz + faiz ;
					orgun = ((toppara * double_0) / 365) / 100 ;
					lblOrtgun.setText(FORMATLAMA.doub_2(tfaiz / orgun));
					lblNewLabel_7.setText(FORMATLAMA.doub_2(tfaiz));
				}
			}
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	public static void cari_kaydet()
	{
		if (textField_1.getText().toString().equals("")) return;
		if (textField.getText().toString().equals("")) return;
		Runnable runner = new Runnable()
		{ public void run() {
			try 
			{
				Progres_Bar_Temizle();
				BORC_ALACAK hsp ;
				hsp = new BORC_ALACAK();
				hsp.setLocationRelativeTo(OBS_MAIN.desktopPane);
				oac.hsp_hsp_kodu = "" ;
				String bh="";
				hsp.lblNewLabel.setText("Alacakli Hesap");
				hsp.setVisible(true);
				bh = oac.hsp_hsp_kodu;
				if (bh.equals("")) return ;
				ResultSet rs ;
				rs = c_Access.hesap_adi_oku(bh);
				if (!rs.isBeforeFirst() ) {  
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Bu numarada hesaba rastlanmadi!!!!");
					return ;
				} 
				rs= null;
				rs = c_Access.hesap_adi_oku(textField_1.getText());
				if (!rs.isBeforeFirst() ) {  
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,  "Bu numarada hesaba rastlanmadi!!!!");
					return;
				} 
				if (bh.equals(textField_1.getText().toString()))
				{
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Borclu ve Alacakli Hesap Ayni...");
					return;
				}
				String  str_4  = "" ;
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				OBS_MAIN.progressBar.setMaximum(model.getRowCount() - 1);
				for (int i = 0; i <= model.getRowCount() - 1 ; i ++)
				{
					Progres_Bar(model.getRowCount() - 1, i);
					if (!  model.getValueAt(i , 0).toString().equals("")) 
					{
						int eno =0;
						eno = c_Access.cari_fisno_al();
						String vade = "";
						if (model.getValueAt(i , 1).toString().length() >  10)
							vade = dateFormater(model.getValueAt(i , 1).toString() , "dd.MM.yyyy", "EEE MMM dd kk:mm:ss zzzz yyyy" ) ;
						else
						{
							String qwe =model.getValueAt(i , 1).toString()  ;
							vade  = qwe;
						}
						str_4 = textField.getText()+  "'Bordro ile " + model.getValueAt(i , 0).toString() + " Nolu " + vade + " Vadeli Çek" ;
						dEKONT_BILGI dBilgi = new dEKONT_BILGI();
						dBilgi.setbHES(bh);
						dBilgi.settAR(TARIH_CEVIR.tarih_geri(dateChooser));
						dBilgi.seteVRAK(eno);
						dBilgi.setbCINS("");
						dBilgi.setbKUR(1);
						dBilgi.setbORC(Double.parseDouble( model.getValueAt(i, 8).toString()));
						dBilgi.setaHES(textField_1.getText());
						dBilgi.setaCINS("");
						dBilgi.setaKUR(1);
						dBilgi.setaLACAK(Double.parseDouble( model.getValueAt(i, 8).toString()));
						dBilgi.setiZAHAT(str_4);
						dBilgi.setkOD("");
						dBilgi.setuSER( GLOBAL.KULL_ADI);
						lOG_BILGI lBILGI = new lOG_BILGI();
						lBILGI.setmESAJ("Cek Giris   Borclu Hes:" + textField_1.getText() + " Tut:" + Double.parseDouble( model.getValueAt(i, 8).toString()));
						lBILGI.seteVRAK(textField.getText());
						c_Access.cari_dekont_kaydet(dBilgi ,
								lBILGI ,BAGLAN_LOG.cariLogDizin);
					}
				}
				Thread.currentThread().isInterrupted();
				Progres_Bar_Temizle();
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO,  "Girisler Cari Hesaba Basari ile Kaydedilmistir....");
			}
			catch (Exception ex)
			{
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
			}
		}
		};
		//// Progress Bar
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
	public static boolean cek_kontrol (String cekno,String tur)
	{
		boolean result = false ;
		try
		{
			ResultSet rs = null ;
			rs= ka_Access.cek_kontrol(cekno);
			if (!rs.isBeforeFirst() )
				result = false;
			else
			{
				rs.next();
				String bno = rs.getString("Giris_Bordro").toString() ;
				result = true;
				if (textField.getText().toString().equals(bno))
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,  "Bu Cek bu Bordroda Giris Yapilmis..");
				else if (! textField.getText().toString().equals(bno))
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,   "Bu Cek " + bno + " 'nolu Bordroda Giris Yapilmis..");
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
		}
		return result ;
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
	public int daysBetween(Date d1, Date d2){
		return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	private static String dateFormater(String dateFromJSON, String expectedFormat, String oldFormat) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
		Date date = null;
		String convertedDate = null;
		try {
			date = dateFormat.parse(dateFromJSON);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(expectedFormat);
			convertedDate = simpleDateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertedDate;
	}
	private static void banka_sube_doldur(String field) 
	{
		try {
			ResultSet rs = null;
			rs = ka_Access.banka_sube(field);
			if (!rs.isBeforeFirst() ) {  
				if (field.equals("Banka"))
					listBanka.add("");
				else
					listSube.add("");
			}
			else
			{
				if (field.equals("Sube"))
				{
					listSube.clear();
					listSube.add("");
					while (rs.next())
						listSube.add(rs.getString(field).toString());
				}
				else
				{
					listBanka.clear();
					listBanka.add("");
					while (rs.next())
						listBanka.add(rs.getString(field).toString());
				}
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
}

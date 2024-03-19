package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.util.ByteArrayDataSource;
import javax.management.loading.PrivateClassLoader;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.Degisken;
import OBS_C_2025.DoubleEditor;
import OBS_C_2025.FILE_UZANTI;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.KERESTE_KOD_KONTROL;
import OBS_C_2025.KER_BILGI;
import OBS_C_2025.KER_RAPOR_BILGI;
import OBS_C_2025.MaterialTabbed;
import OBS_C_2025.NextCellActioin;
import OBS_C_2025.Next_Cell_Kereste;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SAGA;
import OBS_C_2025.SAGA_BOLD;
import OBS_C_2025.SOLA;
import OBS_C_2025.SOLA_ORTA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.U_KODU_RENDERER;
import OBS_C_2025.dEKONT_BILGI;
import OBS_C_2025.lOG_BILGI;
import raven.toast.Notifications;

@SuppressWarnings({"serial","static-access","unused"})
public class KERESTE_GIRIS extends JInternalFrame {
	private static JSplitPane splitPane ;
	private static Obs_TextFIeld textField;
	private static Obs_TextFIeld txtcari;
	private static Obs_TextFIeld txtadres;
	private static Obs_TextFIeld txtdoviz;
	private static Obs_TextFIeld textField_5;
	private static Obs_TextFIeld textField_6;
	private static Obs_TextFIeld textField_7;
	private static Obs_TextFIeld textField_9;
	private static Obs_TextFIeld textField_10;
	
	private static JFormattedTextField txtkur ;
	private static JFormattedTextField txttev ;
	
	private static JComboBox<String> cmbanagrup ;
	private static JComboBox<String> cmbaltgrup ;
	private static JComboBox<String> cmbozkod ;
	private static JComboBox<String> cmbnakliyeci ;
	private static JComboBox<String> cmbdepo ;
	private static JComboBox<String> cmbmensei ;
	private static JDateChooser dtc ;
	private static JLabel label;
	private static JLabel label_1 ;
	private static JLabel label_2 ;
	private static JLabel label_3 ;
	private static JLabel label_6 ;
	private static JLabel label_7 ;
	private static JLabel label_8 ;
	private static JLabel label_9 ;
	private static JLabel label_8_1 ;
	private static JLabel lblNewLabel_20;
	private static JLabel lblPaket ;
	
	
	private static JLabel lblNewLabel_3;
	private static JLabel lblNewLabel_6 ;
	private static JLabel lblNewLabel_13;
	private JLabel lblkodAciklama ;
	private JLabel lblkONSIMENTO ;
	
	private static MaterialTabbed tabbedPane ;
	private static JTable table;
	
	private static  String tar = "" ;
	private static boolean yeni_fat = false;
	private JFileChooser chooser;
	private static boolean dOSYADAN = false;
	private static String strKonsimento = "";
	private static long startTimeG ;
	
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KERESTE_ACCESS ker_Access = new KERESTE_ACCESS(OBS_SIS_2025_ANA_CLASS._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(OBS_SIS_2025_ANA_CLASS._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	static CARI_ACCESS c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	public KERESTE_GIRIS() {
		setTitle("KERESTE GIRIS");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,1160,800);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(KERESTE_GIRIS.class.getResource("/ICONLAR/icons8-invoice-30.png")), 16, 16));//
		splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		ScrollPaneWin11 scrollPaneust = new ScrollPaneWin11();
		scrollPaneust.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		scrollPaneust.setMinimumSize(new Dimension(0, 135));
		scrollPaneust.setMaximumSize(new Dimension(0, 135));
		splitPane.setLeftComponent(scrollPaneust);
		
		
		JPanel panel = new JPanel();
		//panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel.setPreferredSize(new Dimension(1130,125));
		panel.setLayout(new BorderLayout(0, 0));
		scrollPaneust.setViewportView( panel);

		MaterialTabbed tabbedPane_1 = new MaterialTabbed();
		//tabbedPane_1.setForeground(new Color(0, 0, 128));
		tabbedPane_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(tabbedPane_1, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		tabbedPane_1.addTab("Urun Giris", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Evrak No");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 11, 69, 14);
		panel_2.add(lblNewLabel_1);

		textField = new Obs_TextFIeld(10);
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					try {
						setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
						int sno = 0 ;
						sno  = ker_Access.evrak_no_al("G") ;
						int kj = 0 ;
						kj = 10 - Integer.toString(sno).length() ;
						String str_ = StringUtils.repeat("0", kj)   + Integer.toString(sno);
						dOSYADAN = true ;
						textField.setText(str_.equals("0000000000") ? "0000000001":str_);
						dOSYADAN = false;
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
					}
					catch (Exception ex)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Kereste Numaralarinda onceden harf ve rakkam kullanildigindan otomatik numara verilemez...." );
					}
				}
			}
		});
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void removeUpdate(DocumentEvent e) {
				mWAIT();
				if (! textField.getText().toString().equals("")) {
					fiskont();
				}
				else {
					mDEFAULT();
					GRID_TEMIZLE.grid_temizle(table);
					sifirla();
				}
				mDEFAULT();
			}
			public void insertUpdate(DocumentEvent e) {
				mWAIT();
				if (! textField.getText().toString().equals("")) {
					fiskont();
				}
				else {
					mDEFAULT();
					GRID_TEMIZLE.grid_temizle(table);
					sifirla();
				}
				mDEFAULT();
			}
		});
		textField.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorRemoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorMoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorAdded(AncestorEvent pEvent) {
				// TextField is added to its parent => request focus in Event Dispatch Thread
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						textField.requestFocusInWindow();
					}
				});
			}
		});
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.setBounds(89, 8, 120, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				son_fisoku();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		button_1.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-view-16.png")));
		button_1.setToolTipText("Son Fis");
		button_1.setBounds(215, 7, 26, 23);
		panel_2.add(button_1);

		JLabel lblNewLabel_2 = new JLabel("Cari Hesap");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 36, 69, 14);
		panel_2.add(lblNewLabel_2);

		txtcari = new Obs_TextFIeld(12);
		InputMap inputMap = txtcari.getInputMap(txtcari.WHEN_FOCUSED);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK), "none");
		txtcari.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String[] parts;
				String deger ;
				try {
					deger = oac.glb.setting_oku("CARI_HSPPLN_CAG").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_DOWN_MASK : KeyEvent.ALT_DOWN_MASK) ) != 0))
						{
							HESAP_PLN hsp ;
							getContentPane().setCursor(oac.WAIT_CURSOR);
							hsp = new HESAP_PLN();
							hsp.setVisible(true);
							if (! oac.hsp_hsp_kodu.equals(""))
							{
								txtcari.setText(oac.hsp_hsp_kodu);
							}
							getContentPane().setCursor(oac.DEFAULT_CURSOR);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});


		txtcari.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				lblNewLabel_3.setText(CARI_ISIM_OKU.isim(txtcari.getText())[0]);
			}
			public void removeUpdate(DocumentEvent e) {
				lblNewLabel_3.setText(CARI_ISIM_OKU.isim(txtcari.getText())[0]);
			}
			public void insertUpdate(DocumentEvent e) {
				lblNewLabel_3.setText(CARI_ISIM_OKU.isim(txtcari.getText())[0]);
			}
		});
		txtcari.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						mWAIT();
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						mDEFAULT();
						if (! oac.hsp_hsp_kodu.equals(""))
						{
							txtcari.setText( oac.hsp_hsp_kodu);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		txtcari.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtcari.setBounds(89, 33, 120, 20);
		panel_2.add(txtcari);
		txtcari.setColumns(10);

		lblNewLabel_3 = new JLabel(".....");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		//lblNewLabel_3.setForeground(new Color(25, 25, 112));
		lblNewLabel_3.setBounds(89, 62, 252, 14);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Tarih");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(290, 11, 46, 14);
		panel_2.add(lblNewLabel_4);

		dtc = new JDateChooser();
		dtc.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dtc.setDate(new Date());
				}
			}
		});
		dtc.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); 
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, -1); 
						dtc.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); 
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
						dtc.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		dtc.setBounds(340, 8, 125, 20);
		dtc.setDateFormatString("dd.MM.yyyy");
		dtc.setFont(new Font("Tahoma", Font.BOLD, 12));
		dtc.setDate(new Date());
		panel_2.add(dtc);

		JLabel lblNewLabel_5 = new JLabel("Adres");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(290, 36, 46, 14);
		panel_2.add(lblNewLabel_5);

		txtadres = new Obs_TextFIeld(12);
		InputMap inputMapa = txtadres.getInputMap(txtadres.WHEN_FOCUSED);
        inputMapa.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK), "none");

		txtadres.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				if (txtadres.getText().equals(""))
				{
					lblNewLabel_6.setText("");
					return;
				}
				String sonuc = "";
				try 
				{
					sonuc = a_Access.kod_ismi(txtadres.getText());
					lblNewLabel_6.setText(sonuc);
				}
				catch (Exception ex)
				{	
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
				}
			}
			public void removeUpdate(DocumentEvent e) {
				if (txtadres.getText().equals(""))
				{
					lblNewLabel_6.setText("");
					return;
				}
				String sonuc = "";
				try 
				{
					sonuc = a_Access.kod_ismi(txtadres.getText());
					lblNewLabel_6.setText(sonuc);
				}
				catch (Exception ex)
				{	
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
				}
			}
			public void insertUpdate(DocumentEvent e) {
				if (txtadres.getText().equals(""))
				{
					lblNewLabel_6.setText("");
					return;
				}
				String sonuc = "";
				try 
				{
					sonuc = a_Access.kod_ismi(txtadres.getText());
					lblNewLabel_6.setText(sonuc);
				}
				catch (Exception ex)
				{	
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
				}
			}
		});
		txtadres.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					ADRES_LISTE asp ;
					asp = new ADRES_LISTE();
					asp.setVisible(true);
					txtadres.setText( oac.hsp_hsp_kodu);
				}
			}
		});
		txtadres.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String[] parts;
				String deger ;
				try {
					deger = oac.glb.setting_oku("CARI_HSPPLN_CAG").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_DOWN_MASK : KeyEvent.ALT_DOWN_MASK) ) != 0))
						{
							getContentPane().setCursor(oac.WAIT_CURSOR);
							ADRES_LISTE asp ;
							asp = new ADRES_LISTE();
							asp.setVisible(true);
							if (! oac.hsp_hsp_kodu.equals(""))
							{
								txtcari.setText(oac.hsp_hsp_kodu);
							}
							getContentPane().setCursor(oac.DEFAULT_CURSOR);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		txtadres.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtadres.setBounds(340, 33, 125, 20);
		panel_2.add(txtadres);
		txtadres.setColumns(10);

		lblNewLabel_6 = new JLabel(".....");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		//lblNewLabel_6.setForeground(new Color(139, 0, 0));
		lblNewLabel_6.setBounds(340, 62, 138, 14);
		panel_2.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Ozel Kod");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(480, 11, 56, 14);
		panel_2.add(lblNewLabel_7);

		cmbozkod = new JComboBox<String>();
		cmbozkod.setBounds(535, 7, 165, 22);
		//cmbozkod.setForeground(new Color(0, 0, 128));
		cmbozkod.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_2.add(cmbozkod);

		JLabel lblAnaGrup = new JLabel("Ana Grup");
		lblAnaGrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAnaGrup.setBounds(740, 10, 61, 14);
		panel_2.add(lblAnaGrup);

		cmbanagrup = new JComboBox<String>();
		//cmbanagrup.setForeground(new Color(0, 0, 128));
		cmbanagrup.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbanagrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur();
			}
		});
		cmbanagrup.setBounds(811, 7, 148, 22);
		panel_2.add(cmbanagrup);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ana_grup_doldur();
			}
		});
		button.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		button.setToolTipText("Yenile");
		button.setBounds(965, 7, 26, 23);
		panel_2.add(button);

		JLabel lblAltGrup = new JLabel("Alt Grup");
		lblAltGrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAltGrup.setBounds(740, 35, 69, 14);
		panel_2.add(lblAltGrup);

		cmbaltgrup = new JComboBox<String>();
		//cmbaltgrup.setForeground(new Color(0, 0, 128));
		cmbaltgrup.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbaltgrup.setBounds(811, 33, 148, 22);
		panel_2.add(cmbaltgrup);

		JLabel lblNewLabel_9 = new JLabel("Doviz");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_9.setBounds(995, 10, 46, 14);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Kur");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(995, 36, 46, 14);
		panel_2.add(lblNewLabel_10);

		txtdoviz = new Obs_TextFIeld();
		txtdoviz.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdoviz.setBounds(1050, 8, 73, 20);
		panel_2.add(txtdoviz);
		txtdoviz.setColumns(10);

		txtkur = new JFormattedTextField();
		txtkur.setHorizontalAlignment(SwingConstants.RIGHT);
		txtkur.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtkur.setText("0.0000");
		txtkur.setBounds(1050, 33, 73, 20);
		panel_2.add(txtkur);
		
		JLabel lblNewLabel_7_1 = new JLabel("Nakliyeci");
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7_1.setBounds(480, 37, 56, 14);
		panel_2.add(lblNewLabel_7_1);
		
		cmbnakliyeci = new JComboBox<String>();
		cmbnakliyeci.setBounds(535, 33, 165, 22);
		cmbnakliyeci.setFont(new Font("Dialog", Font.BOLD, 12));
		//cmbnakliyeci.setForeground(new Color(0, 0, 128));
		panel_2.add(cmbnakliyeci);
		
		cmbmensei = new JComboBox<String>();
		cmbmensei .setToolTipText("Mensei");
		cmbmensei.setFont(new Font("Dialog", Font.BOLD, 12));
		//cmbmensei.setForeground(new Color(0, 0, 128));
		cmbmensei .setBounds(535, 58, 165, 22);
		panel_2.add(cmbmensei );
		
		JLabel lblNewLabel_12 = new JLabel("Mensei");
		lblNewLabel_12.setBounds(480, 62, 48, 14);
		panel_2.add(lblNewLabel_12);
		
		JButton button_4_1 = new JButton("");
		button_4_1.setToolTipText("Yenile");
		button_4_1.setBounds(704, 58, 26, 23);
		button_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mensei_doldur();
			}
		});
		button_4_1.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		panel_2.add(button_4_1);
		
		cmbdepo = new JComboBox<String>();
		cmbdepo.setBounds(811, 59, 148, 22);
		cmbdepo.setFont(new Font("Dialog", Font.BOLD, 12));
		//cmbdepo.setForeground(new Color(0, 0, 128));
		panel_2.add(cmbdepo);
		
		JLabel lblNewLabel_11 = new JLabel("Depo");
		lblNewLabel_11.setBounds(740, 62, 61, 14);
		panel_2.add(lblNewLabel_11);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				depo_doldur();
			}
		});
		button_2.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		button_2.setToolTipText("Yenile");
		button_2.setBounds(965, 58, 26, 23);
		panel_2.add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ker_oz_kod();
			}
		});
		button_3.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		button_3.setToolTipText("Yenile");
		button_3.setBounds(704, 7, 26, 23);
		panel_2.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ker_nakliyeci();
			}
		});
		button_4.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-repeat-16.png")));

		button_4.setToolTipText("Yenile");
		button_4.setBounds(704, 32, 26, 23);
		panel_2.add(button_4);
		

		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		tabbedPane_1.addTab("Notlar", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel lblNewLabel_14 = new JLabel("Not 1");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_14.setBounds(10, 11, 46, 14);
		panel_4.add(lblNewLabel_14);

		textField_5 = new Obs_TextFIeld(40);
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_5.setBounds(81, 8, 312, 20);
		panel_4.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new Obs_TextFIeld(40);
		textField_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_6.setBounds(81, 35, 312, 20);
		panel_4.add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new Obs_TextFIeld();
		textField_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_7.setBounds(81, 62, 312, 20);
		panel_4.add(textField_7);
		textField_7.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Not 2");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_15.setBounds(10, 38, 46, 14);
		panel_4.add(lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel("Not 3");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_16.setBounds(10, 65, 46, 14);
		panel_4.add(lblNewLabel_16);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		tabbedPane_1.addTab("Ek Bilgi", null, panel_5, null);
		panel_5.setLayout(null);

		JLabel lblNewLabel_18 = new JLabel("1 -");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_18.setBounds(10, 11, 34, 14);
		panel_5.add(lblNewLabel_18);

		JLabel lblNewLabel_19 = new JLabel("2 -");
		lblNewLabel_19.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_19.setBounds(10, 38, 34, 14);
		panel_5.add(lblNewLabel_19);

		textField_9 = new Obs_TextFIeld(50);
		textField_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_9.setBounds(54, 8, 447, 20);
		panel_5.add(textField_9);
		textField_9.setColumns(10);

		textField_10 = new Obs_TextFIeld(50);
		textField_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_10.setBounds(54, 33, 447, 20);
		panel_5.add(textField_10);
		textField_10.setColumns(10);
		
		

		//****************ALT BOLUM*********************************************
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(0);
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);

		splitPane.setRightComponent(splitPane_1);
		
		JPanel panel_3 = new JPanel();
		splitPane_1.setRightComponent(panel_3);
		panel_3.setMinimumSize(new Dimension(0, 160));
		panel_3.setMaximumSize(new Dimension(0, 160));
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setDividerSize(0);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_3.add(splitPane_3);
		
		ScrollPaneWin11 scrollPanetoplam = new ScrollPaneWin11();
		scrollPanetoplam.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		splitPane_3.setRightComponent(scrollPanetoplam);
		
		JPanel panel_71 = new JPanel();
		//panel_71.setBorder(new LineBorder(null));
		//panel_71.setBounds(2, 1, 1158, 21);
		panel_71.setPreferredSize(new Dimension(1128, 25));
		scrollPanetoplam.setViewportView(panel_71);
		
		panel_71.setLayout(null);
		splitPane_3.setLeftComponent(scrollPanetoplam);
		
		
		JLabel lblNewLabel = new JLabel("Satir Sayisi :");
		lblNewLabel.setBounds(32, 5, 85, 14);
		panel_71.add(lblNewLabel);

		lblNewLabel_13 = new JLabel("0");
		//lblNewLabel_13.setForeground(new Color(0, 0, 128));
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_13.setBounds(122, 5, 40, 14);
		panel_71.add(lblNewLabel_13);

		label_8 = new JLabel("0.000");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		//label_8.setForeground(new Color(139, 0, 0));
		label_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_8.setBounds(415, 5, 77, 14);
		panel_71.add(label_8);

		label_9 = new JLabel("0.00");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		//label_9.setForeground(new Color(139, 0, 0));
		label_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_9.setBounds(820, 5, 146, 14);
		panel_71.add(label_9);
		
		
		lblPaket = new JLabel("0");
		lblPaket.setHorizontalAlignment(SwingConstants.RIGHT);
		//lblPaket.setForeground(new Color(139, 0, 0));
		lblPaket.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPaket.setBounds(499, 5, 66, 14);
		panel_71.add(lblPaket);
		
		label_8_1 = new JLabel("0");
		label_8_1.setHorizontalAlignment(SwingConstants.RIGHT);
		//label_8_1.setForeground(new Color(139, 0, 0));
		label_8_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_8_1.setBounds(330, 5, 85, 14);
		panel_71.add(label_8_1);
		
		JLabel lblNewLabel_8 = new JLabel("Paket");
		//lblNewLabel_8.setForeground(new Color(0, 0, 128));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(575, 5, 60, 14);
		panel_71.add(lblNewLabel_8);
		
		ScrollPaneWin11 scrollPanealt = new ScrollPaneWin11();
		scrollPanealt.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		splitPane_3.setRightComponent(scrollPanealt);
		
		MaterialTabbed tabbedPane_2 = new MaterialTabbed();
		tabbedPane_2.setPreferredSize(new Dimension(1128,105));
		scrollPanealt.setViewportView(tabbedPane_2);
		

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		tabbedPane_2.addTab("Toplamlar", null, panel_1, null);
		panel_1.setLayout(null);
		
		lblkodAciklama = new JLabel();
		lblkodAciklama.setFont(new Font("Dialog", Font.BOLD, 12));
		//lblkodAciklama.setForeground(new Color(0, 0, 128));
		lblkodAciklama.setBounds(10, 55,50, 14);
		panel_1.add(lblkodAciklama);
		
		lblkONSIMENTO = new JLabel();
		//lblkONSIMENTO.setForeground(new Color(0, 0, 128));
		lblkONSIMENTO.setFont(new Font("Dialog", Font.BOLD, 12));
		lblkONSIMENTO.setBounds(10, 35, 50, 14);
		panel_1.add(lblkONSIMENTO);
		
		JLabel lblIskonto = new JLabel("Iskonto");
		lblIskonto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblIskonto.setBounds(247, 11, 62, 14);
		panel_1.add(lblIskonto);
		
		label_6 = new JLabel("0.00");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		//label_6.setForeground(Color.BLUE);
		label_6.setFont(new Font("Dialog", Font.PLAIN, 13));
		label_6.setBounds(306, 11, 95, 14);
		panel_1.add(label_6);
		
		JLabel lblKdv = new JLabel("K.D.V.");
		lblKdv.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblKdv.setBounds(415, 11, 52, 14);
		panel_1.add(lblKdv);
		
		label_3 = new JLabel("0.00");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		//label_3.setForeground(Color.BLUE);
		label_3.setFont(new Font("Dialog", Font.PLAIN, 13));
		label_3.setBounds(480, 11, 99, 14);
		panel_1.add(label_3);
		
		JLabel lblBakiye = new JLabel("Bakiye");
		lblBakiye.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBakiye.setBounds(247, 39, 66, 14);
		panel_1.add(lblBakiye);
		
		label_7 = new JLabel("0.00");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		//label_7.setForeground(Color.BLUE);
		label_7.setFont(new Font("Dialog", Font.PLAIN, 13));
		label_7.setBounds(306, 39, 95, 14);
		panel_1.add(label_7);
		
		JLabel lblTevkifatOrani = new JLabel("Tevkifat Orani");
		lblTevkifatOrani.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTevkifatOrani.setBounds(415, 39, 82, 14);
		panel_1.add(lblTevkifatOrani);
		
		txttev = new JFormattedTextField();
		txttev.setText("0");
		txttev.setHorizontalAlignment(SwingConstants.RIGHT);
		txttev.setFont(new Font("Dialog", Font.BOLD, 13));
		//txttev.setBackground(Color.CYAN);
		txttev.setBounds(518, 35, 62, 20);
		txttev.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				if (txttev.getText().equals("")) return ;
				//toplam();
			}
			public void removeUpdate(DocumentEvent e) {
				if (txttev.getText().equals("")) return ;
				//toplam();
			}
			public void insertUpdate(DocumentEvent e) {
				if (txttev.getText().equals("")) return ;
				toplam();
			}
		});
		panel_1.add(txttev);
		
		JLabel lblTevedilenKdv = new JLabel("Tev.Edilen K.D.V.");
		lblTevedilenKdv.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTevedilenKdv.setBounds(595, 11, 109, 14);
		panel_1.add(lblTevedilenKdv);
		
		JLabel lblTevdahtoptutar = new JLabel("Tev.Dah.Top.Tutar");
		lblTevdahtoptutar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTevdahtoptutar.setBounds(595, 39, 109, 14);
		panel_1.add(lblTevdahtoptutar);
		
		label_2 = new JLabel("0.00");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		//label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 13));
		label_2.setBounds(705, 39, 99, 14);
		panel_1.add(label_2);
		
		label_1 = new JLabel("0.00");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		//label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		label_1.setBounds(705, 11, 99, 14);
		panel_1.add(label_1);
		
		JLabel lblNewLabel_21 = new JLabel("Beyan Edilen KDV");
		lblNewLabel_21.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_21.setBounds(828, 11, 109, 14);
		panel_1.add(lblNewLabel_21);
		
		lblNewLabel_20 = new JLabel("0.00");
		lblNewLabel_20.setHorizontalAlignment(SwingConstants.RIGHT);
		//lblNewLabel_20.setForeground(Color.BLUE);
		lblNewLabel_20.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblNewLabel_20.setBounds(927, 11, 109, 14);
		panel_1.add(lblNewLabel_20);
		
		JLabel lblNewLabel_22 = new JLabel("Tev.Har.Top.Tutar");
		lblNewLabel_22.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_22.setBounds(828, 39, 109, 14);
		panel_1.add(lblNewLabel_22);
		
		label = new JLabel("0.00");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		//label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Dialog", Font.BOLD, 13));
		label.setBounds(927, 39, 109, 14);
		panel_1.add(label);
		
		//** Sol Toolbar *****************************************************************
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setDividerSize(0);
		splitPane_1.setLeftComponent(splitPane_2);
		
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		toolBar_1.setFloatable(false);
		toolBar_1.setMinimumSize(new Dimension(30, 0));
		toolBar_1.setMaximumSize(new Dimension(30, 0));
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		splitPane_2.setLeftComponent(toolBar_1);
		

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabbedPane.getSelectedIndex() == 0)
				{
					satir_ilave();
					paketm3();
					DefaultTableModel mdll = (DefaultTableModel) table.getModel();
					mdll.removeRow(mdll.getRowCount() -1);
				}
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/yeni.png")));
		toolBar_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabbedPane.getSelectedIndex() == 0)
				{
					if (table.getSelectedRow() != -1) {
						satir_sil();
					}
					else {
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Ilk olarak Satir Seciniz........" );
						return ;
					}
				}
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-reduce-16.png")));
		toolBar_1.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tabbedPane.setSelectedIndex(0);
					strKonsimento = JOptionPane.showInputDialog(null,"Konsimento No Giriniz....", "Dosya Okuma",JOptionPane.QUESTION_MESSAGE);
					if(strKonsimento == null) return ;
					if(strKonsimento.equals("")) return ;
					if (strKonsimento.length() > 10)
					{
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Konsimento Numarasi 10 Karakter ile sinirlidir..." );
						return;
					}
					if(ker_Access.kons_kontrol(strKonsimento))
					{
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,  "Bu Numarada Konsimento Mevcut.." );
						return;
					}
					dosya_oku();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3_1.setIcon(new ImageIcon(KERESTE_GIRIS.class.getResource("/ICONLAR/excel-16.png")));
		toolBar_1.add(btnNewButton_3_1);
		//////////////////////////////ARA BOLUM********************************
		tabbedPane = new MaterialTabbed();
		//tabbedPane.setForeground(new Color(0, 0, 128));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		splitPane_2.setRightComponent(tabbedPane);
		
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		tabbedPane.addTab("Kereste", null, scrollPane, null);

		DefaultTableModel model = new DefaultTableModel() ; 
		table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {  
				switch (column) {
				case 0:
					return true;
				case 1:
					return true;
				case 2:
					return true;
				case 3:
					return true;
				case 6:
					return true;
				case 7:
					return true;
				case 8:
					return true;
				case 9:
					return true;
				case 11:
					return true;
				default:
					return false;
				}
			}
		};
		
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10)
				{
					if(table.getSelectedColumn() == 6)
					{
						if(table.getSelectedRow() > 0)
						{
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							if (! model.getValueAt(table.getSelectedRow(),1).toString().equals(""))
							{
								if ( model.getValueAt(table.getSelectedRow(),2).toString().equals(model.getValueAt(table.getSelectedRow()-1,2).toString()))
								model.setValueAt(model.getValueAt(table.getSelectedRow() -1, 6), table.getSelectedRow(), 6);
							}
							else {
								model.setValueAt("",table.getSelectedRow(),1);
							}
						}
					}
					if (table.isEditing())
						table.getCellEditor().stopCellEditing();
				}
				if (e.getKeyCode() == 127)
					satir_sil();
			}
		});
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			table.setGridColor(oac.gridcolor);
		table.setCellSelectionEnabled(true);
		model.addColumn("Barkod", new String []{""});
		model.addColumn("Urun Kodu", new String []{""});
		model.addColumn("Paket_No", new String []{""});
		model.addColumn("Miktar", new Double [] {( 0.000 )});
		model.addColumn("M3", new Double [] {( 0.000 )});
		model.addColumn("Paket_M3", new String []{""});
		model.addColumn("Kons.", new String []{""});
		model.addColumn("Fiat", new Double [] {( 0.00 )});
		model.addColumn("Iskonto", new Double [] {( 0.00 )});
		model.addColumn("KDV",new Double [] {( 0.00 )});
		model.addColumn("Tutar",new Double [] {( 0.00 )});
		model.addColumn("Izahat", new String []{"" });
		model.addColumn("Cikis_Evrak", new String []{"" });
		model.addColumn("CTarih", new String []{"" });
		model.addColumn("CKdv", new Double [] {( 0.000 )});
		model.addColumn("CDoviz", new String []{"" });
		model.addColumn("CFiat", new Double [] {( 0.000 )});
		model.addColumn("CTutar", new Double [] {( 0.000 )});
		model.addColumn("CKur", new Double [] {( 0.000 )});
		model.addColumn("CCari_Firma", new String []{"" });
		model.addColumn("CAdres_Firma", new String []{"" });
		model.addColumn("CIskonto", new Double [] {( 0.000 )});
		model.addColumn("CTevkifat", new Double [] {( 0.000 )});
		model.addColumn("CAna_Grup", new Integer []{( 0 )});
		model.addColumn("CAlt_Grup", new Integer []{( 0 )});
		model.addColumn("CDepo", new Integer []{( 0 )});
		model.addColumn("COzel_Kod", new Integer []{( 0 )});
		model.addColumn("CIzahat", new String []{"" });
		model.addColumn("CNakliyeci",new Integer []{( 0 )});
		model.addColumn("CUser", new String []{"" });
		model.addColumn("CSatir",new Integer []{( 0 )});
		TableColumn col ;
		col = table.getColumnModel().getColumn(0);
		col.setMinWidth(100);
		col.setHeaderRenderer(new SOLA());
		
		//******************************************************************KODU *********************************
		JFormattedTextField ftext = new JFormattedTextField();
		ftext.setFont(new Font(table.getFont().getFontName(),1 ,table.getFont().getSize()));
		ftext.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
			}
			public void removeUpdate(DocumentEvent e) {
			}
			public void insertUpdate(DocumentEvent e) {
				try {
					if (table.getSelectedRow() == -1 ) return ;
					getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
					mWAIT();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					double m3 = m3(ftext.getText() ,Double.parseDouble(model.getValueAt(table.getSelectedRow(), 3).toString()));
					model.setValueAt(  m3,table.getSelectedRow(), 4)  ;
					kod_ADI(ftext.getText(), model.getValueAt(table.getSelectedRow(), 6).toString());
					dOSYADAN = false ;
					toplam();
					mDEFAULT();
					getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
				} catch (Exception ex) {
					mDEFAULT();
					getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage() );
				}
			}
		});
		ftext.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				ftext.selectAll();
			}
		});
		MaskFormatter mask;
		try {
		    mask = new MaskFormatter("AA-###-####-####");
		    mask.install(ftext);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		col = table.getColumnModel().getColumn(1);
		col.setCellRenderer(new U_KODU_RENDERER());
		col.setMinWidth(120);
		col.setCellEditor(new DefaultCellEditor(ftext));
		
		col.setHeaderRenderer(new SOLA());
		
		JTextField pak_noField = new JTextField();
		pak_noField.setDocument(new JTextFieldLimit(10));
		pak_noField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				//Paket no Kontrol yap onceden varmi 
				ResultSet rSet;
				try {
					if(! model.getValueAt(table.getSelectedRow(), 6).toString().equals(""))
					{
						rSet = ker_Access.paket_oku(pak_noField.getText() + "-" + model.getValueAt(table.getSelectedRow(), 6).toString(),"G");
					}
					else {
						return ;
					}
					if (!rSet.isBeforeFirst() ) {  
						
					}
					else 
					{
						rSet.first();  
						if (! rSet.getString("Evrak_No").toString().equals(textField.getText()))
						{
							OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, rSet.getString("Evrak_No") + " Nolu Evrakta Giris Yapilmis.." );
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				//Paket no Kontrol yap onceden varmi 
				ResultSet rSet;
				try {
					if(! model.getValueAt(table.getSelectedRow(), 6).toString().equals(""))
					{
						rSet = ker_Access.paket_oku(pak_noField.getText() + "-" + model.getValueAt(table.getSelectedRow(), 6).toString(),"G");
					}
					else {
						return ;
					}
					if (!rSet.isBeforeFirst() ) {  
						
					}
					else 
					{
						rSet.first();  
						if (! rSet.getString("Evrak_No").toString().equals(textField.getText()))
						{
							OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,  rSet.getString("Evrak_No") + " Nolu Evrakta Giris Yapilmis.." );
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
			
			}
		});
		pak_noField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
					if(table.getSelectedColumn() != 2) return ;
					if(model.getValueAt(table.getSelectedRow(), 6).toString().equals("")) return ;
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int pak_no;
					try {
						pak_no = ker_Access.paket_no_al(model.getValueAt(table.getSelectedRow(), 6).toString());
						String[] sinifString =  model.getValueAt(table.getSelectedRow(), 1).toString().split("-");
						String sinString = sinifString[0].substring(1,2);
						int kj = 0 ;
						kj = 8 - Integer.toString(pak_no).length() ;
						String str_ = sinString + "Z" +  StringUtils.repeat("0", kj)   + Integer.toString(pak_no);
						pak_noField.setText(str_);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		col = table.getColumnModel().getColumn(2);
		col.setMinWidth(90);
		col.setCellEditor( new DefaultCellEditor(pak_noField) );
		col.setHeaderRenderer(new SOLA());
		col.setCellRenderer(new PathCellRenderer());
		
		col = table.getColumnModel().getColumn(3);
		col.setMinWidth(40);
		col.setCellRenderer(new TABLO_RENDERER(0,false));
		col.setCellEditor( new DoubleEditor(0) );
		col.setHeaderRenderer(new SAGA());
		
		col = table.getColumnModel().getColumn(4);
		col.setMinWidth(65);
		col.setCellRenderer(new TABLO_RENDERER(3,true));
		col.setCellEditor( new DoubleEditor(3) );
		col.setHeaderRenderer(new SAGA());
		
		col = table.getColumnModel().getColumn(5);
		col.setMinWidth(65);
		col.setCellRenderer(new SAGA_BOLD());
		col.setHeaderRenderer(new SAGA());
		
		col = table.getColumnModel().getColumn(6);
		col.setMinWidth(75);
		col.setCellRenderer(new SOLA_ORTA());
		col.setHeaderRenderer(new SOLA());
		
		col = table.getColumnModel().getColumn(7);
		col.setMinWidth(75);
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setHeaderRenderer(new SAGA());
		
		col = table.getColumnModel().getColumn(8);
		col.setMinWidth(50);
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setHeaderRenderer(new SAGA());
		
		col = table.getColumnModel().getColumn(9);
		col.setMinWidth(30);
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setHeaderRenderer(new SAGA());
		
		col = table.getColumnModel().getColumn(10);
		col.setMinWidth(100);
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,true));
		col.setHeaderRenderer(new SAGA());
		
		col = table.getColumnModel().getColumn(11);
		col.setMinWidth(178);
		JTextField atf = new JTextField(40);
		col.setCellRenderer(new SOLA_ORTA());
		col.setCellEditor(new DefaultCellEditor(atf));
		col.setHeaderRenderer(new SOLA());
		
		col = table.getColumnModel().getColumn(12);
		col.setCellRenderer(new SOLA_ORTA());
		col.setHeaderRenderer(new SOLA());
		col.setMinWidth(90);
		
		for (int i =0 ; i <=17;i++) {
			table.removeColumn(table.getColumnModel().getColumn(13));
		}
		JTableHeader th = table.getTableHeader();
		Dimension dd = table.getPreferredSize();
		dd.height = 30;
		th.setPreferredSize(dd); 
		table.setRowSelectionInterval(0, 0);
		table.setRowHeight(22);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		InputMap im = table.getInputMap(JTable.WHEN_FOCUSED);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Action.NextCell");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Action.NextCell");
		ActionMap am = table.getActionMap();
		am.put("Action.NextCell", new Next_Cell_Kereste(table,"kereste_giris"));
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		scrollPane.setViewportView(table);

		ana_grup_doldur();
		ker_oz_kod();
		ker_nakliyeci();
		depo_doldur();
		mensei_doldur();
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if (model.getRowCount() > 0) 
				{
					try {
						if ( table.getSelectedRow() != -1) {
							GuiUtil.setWaitCursor(table,true);
							kod_ADI(  model.getValueAt(table.getSelectedRow(), 1).toString(), model.getValueAt(table.getSelectedRow(), 6).toString());
							GuiUtil.setWaitCursor(table,false);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		table.getModel().addTableModelListener(	(TableModelListener) new TableModelListener() 
		{		
			@Override
			public void tableChanged(TableModelEvent e) {
				TableModel model = (TableModel)e.getSource();
				if (model.getRowCount() > 0) 
				{
					int row;
					row = table.getSelectedRow();   
					int column = e.getColumn();
					if (column == 1)  //
					{
						paketm3();
						toplam();
					}
					if (column == 2)  //
					{
						paketm3();
						toplam();
					}
					if (column == 3)  //MIKTAR
					{
						double m3 = m3(model.getValueAt(row, 1).toString() ,Double.parseDouble(model.getValueAt(row, 3).toString()));
						model.setValueAt(  m3,row, 4)  ;
						double fiat = 0 ;
						fiat =  Double.parseDouble(model.getValueAt(row, 7).toString());
						m3 = Double.parseDouble(model.getValueAt(row, 4).toString());
						model.setValueAt( fiat * m3,row, 10)  ;
						paketm3();
						toplam();
					}
					if (column == 4)  //m3
					{
						double fiat ,m3 = 0 ;
						fiat =  Double.parseDouble(model.getValueAt(row, 7).toString());
						m3 = Double.parseDouble(model.getValueAt(row, 4).toString());
						model.setValueAt( fiat * m3,row, 10)  ;
						toplam();
					}
					if (column == 7)  //FIAT
					{
						double fiat ,m3 = 0 ;
						fiat =  Double.parseDouble(model.getValueAt(row, 7).toString());
						m3 = Double.parseDouble(model.getValueAt(row, 4).toString());
						model.setValueAt( fiat * m3,row, 10)  ;
						toplam();
					}
					if (column == 8)  //ISKONTO
					{
						toplam();
					}
					if (column == 9)  //KDV
					{
						toplam();
					}
				}
			}
		});
		//***********
		String deger;
		Integer sat_sayi;
		try {
			deger = GLOBAL.setting_oku("KER_FAT_SATIR").toString();
			sat_sayi =Integer.parseInt(deger);
			for (int i = 0; i <= sat_sayi -1 ; i ++)
			{
				satir_ilave();
			}
			txtdoviz.setText(GLOBAL.setting_oku("PRG_PARA").toString());
			String[] parts;
			Font bigFont;
			deger = GLOBAL.setting_oku("KER_GIRIS").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			table.setFont(bigFont);

		} catch (Exception ex) 
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
		}

	}
	private static void satir_ilave() 
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		int satir = table.getSelectedRow();
		
		if ( satir  < 0 ) 
		{
			mdl.addRow(new Object[]{"","","",0.00,0.000,"","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,0,"",0,"",0});
			satir = 0 ;
		}
		else
		{
			mdl.insertRow(satir, new Object[]{"","","",0.00,0.000,"","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,0,"",0,"",0});
		}
		table.isRowSelected(satir);
		table.repaint();
	}
	private void ana_grup_doldur()
	{
		try {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
			cmbanagrup .removeAllItems();
			ResultSet rs=null;
			rs = ker_Access.ker_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
			if (!rs.isBeforeFirst() ) {  
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
				cmbaltgrup.setEnabled(false);
				cmbanagrup .addItem("");
				cmbanagrup.setSelectedItem("");
				return;
			} 
			cmbanagrup .addItem("");
			while (rs.next())
			{
				cmbanagrup .addItem(rs.getString("ANA_GRUP"));
			}
			cmbanagrup.setEnabled(true);
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		}
		catch (Exception ex)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void alt_grup_doldur()
	{
		try {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
			cmbaltgrup.removeAllItems();
			cmbaltgrup .addItem("");
			ResultSet rs=null;
			rs = ker_Access.ker_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				int in1 = rs.getInt("AGID_Y");
				rs =null;
				rs = ker_Access.ker_kod_alt_grup_degisken_oku(in1);
			}
			if (!rs.isBeforeFirst() ) {  
				cmbaltgrup.setSelectedItem("");
				cmbaltgrup.setEnabled(false);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			} 
			else
			{
				while (rs.next())
				{
					cmbaltgrup .addItem(rs.getString("ALT_GRUP"));
				}
				cmbaltgrup.setSelectedItem(0);
				cmbaltgrup.setEnabled(true);
			}
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		}
		catch (Exception ex)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void ker_oz_kod()
	{
		try {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
			cmbozkod .removeAllItems();
			ResultSet rs=null;
			rs = ker_Access.ker_kod_degisken_oku("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN");
			if (!rs.isBeforeFirst() ) {  
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
				cmbozkod  .setEnabled(false);
				cmbozkod  .addItem("");
				cmbozkod  .setSelectedItem("");
				return;
			} 
			cmbozkod .addItem("");
			while (rs.next())
			{
				cmbozkod .addItem(rs.getString("OZEL_KOD_1"));
			}
			cmbozkod.setEnabled(true);
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		}
		catch (Exception ex)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void ker_nakliyeci()
	{
		try {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
			cmbnakliyeci .removeAllItems();
			ResultSet rs=null;
			rs = ker_Access.ker_kod_degisken_oku("UNVAN", "NAKID_Y", "NAKLIYECI");
			if (!rs.isBeforeFirst() ) 
			{  
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
				cmbnakliyeci .setEnabled(false);
				cmbnakliyeci .addItem("");
				cmbnakliyeci .setSelectedItem("");
				return;
			} 
			cmbnakliyeci  .addItem("");
			while (rs.next())
			{
				cmbnakliyeci .addItem(rs.getString("UNVAN"));
			}
			cmbnakliyeci.setEnabled(true);
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		}
		catch (Exception ex)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void son_fisoku()
	{
		try
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
			textField.setText( ker_Access.son_no_al("G"));
			textField.requestFocus();
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private static void toplam()
	{
		try {
			if( dOSYADAN)
			{
				return;
			}
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			double  double_0, double_1 = 0, double_2 = 0, double_3 = 0, double_4, double_5=0,double_6 = 0  ,adetToplam=0   ;
			int urunsayi = 0 ,paketsayi = 0 ;
			for (int  i = 0 ; i <= table.getRowCount() -1 ; i ++)
			{
				double_5 += Double.parseDouble(model.getValueAt(i, 10).toString());
				double_1 += (Double.parseDouble(model.getValueAt(i, 10).toString()) * (Double.parseDouble(model.getValueAt(i, 8).toString()))) / 100 ; 
				double_2 += (( Double.parseDouble(model.getValueAt(i, 10).toString()) - ( Double.parseDouble(model.getValueAt(i, 10).toString()) *  Double.parseDouble(model.getValueAt(i, 8).toString())) / 100) *  Double.parseDouble(model.getValueAt(i, 9).toString())) / 100 ; // kdv
				double_3 +=  Double.parseDouble(model.getValueAt(i, 4).toString());
				adetToplam +=   Double.parseDouble(model.getValueAt(i, 3).toString());
				if (! model.getValueAt(i, 5).toString().trim().isEmpty()) 
				{
					//double_6 +=  Double.parseDouble(model.getValueAt(i, 5).toString().trim());
					paketsayi += 1 ;
				}
				if (! model.getValueAt(i,1).toString().equals(""))
				{
					urunsayi += 1;
				}

			}
			label_8.setText(FORMATLAMA.doub_3(double_3));
			//lblPaket.setText(FORMATLAMA.doub_3(double_6));
			lblPaket.setText(FORMATLAMA.doub_0(paketsayi));
			label_9.setText(FORMATLAMA.doub_2(double_5));
			lblNewLabel_13.setText( FORMATLAMA.doub_0(urunsayi));
			label_8_1.setText( FORMATLAMA.doub_0(adetToplam));
			label_6.setText(FORMATLAMA.doub_2(double_1));
			double_0 =double_5 - double_1 ;
			label_7.setText(FORMATLAMA.doub_2(double_0));
			label_3.setText(FORMATLAMA.doub_2(double_2));
			//**********Tevkif Islemi **********************************************************
			double_4 =DecimalFormat.getNumberInstance().parse( txttev.getText()).doubleValue();
			double_0 = (double_2 / 10) * double_4 ;
			label_1.setText(FORMATLAMA.doub_2(double_0));
			double_0 = (double_5 - double_1) + double_2;
			label_2.setText(FORMATLAMA.doub_2(double_0));
			double_0 = (double_2 - (double_2 / 10) * double_4);
			lblNewLabel_20.setText(FORMATLAMA.doub_2(double_0));
			double_0 = (double_5 - double_1) + (double_2 - (double_2 / 10) * double_4);
			label.setText(FORMATLAMA.doub_2(double_0));
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void depo_doldur()
	{
		try 
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
			cmbdepo .removeAllItems();
			ResultSet rs=null;
			rs = ker_Access.ker_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
			if (!rs.isBeforeFirst() ) {  
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
				cmbdepo .addItem("");
				cmbdepo.setSelectedItem("");
				return;
			} 
			cmbdepo .addItem("");
			while (rs.next())
			{
				cmbdepo .addItem(rs.getString("DEPO"));
			}
			cmbdepo.setEnabled(true);
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		}
		catch (Exception ex)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private  void mensei_doldur()
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			cmbmensei.removeAllItems();
			ResultSet rs=null;
			rs = ker_Access.ker_kod_degisken_oku("MENSEI", "MEID_Y", "MENSEI_DEGISKEN");
			if (!rs.isBeforeFirst() ) {  
				cmbmensei.addItem("");
				cmbmensei.setSelectedItem("");
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				return;
			} 
			cmbmensei.addItem("");
			while (rs.next())
			{
				cmbmensei.addItem(rs.getString("MENSEI"));
			}
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private static void paketm3()
	{
		double m3 =0.000 ;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for ( int i = 1 ;i <= model.getRowCount() -1 ;i++   ) 
		{
			String paketno = model.getValueAt(i-1, 2).toString().trim();
			double aram3 = Double.parseDouble(model.getValueAt(i-1, 4).toString()) ;
			if (! model.getValueAt(i, 2).toString().trim().equals(paketno.toString().trim()))
			{
				if (i == model.getRowCount() -1) 
				{
					double aram33 = Double.parseDouble(model.getValueAt(i, 4).toString()) ;
					model.setValueAt( m3 + aram3 ,i-1, 5)  ;
					if (aram33==0) 
					{
						model.setValueAt( "" ,i, 5)  ;
					}
					else 
					{
						model.setValueAt( FORMATLAMA.doub_3(aram33) ,i, 5)  ;
					}
				}
				else 
				{
					m3 = m3 + aram3 ;
					if (m3==0) {
						model.setValueAt("",i-1, 5)  ;
					}
					else 
					{
						model.setValueAt(FORMATLAMA.doub_3(m3),i-1, 5)  ;
					}
					m3 = 0.000;
				}
			}
			else if ( model.getValueAt(i, 2).toString().trim().equals(paketno.toString().trim())) {
				if (i == model.getRowCount() -1)
				{
					double aram33 = Double.parseDouble(model.getValueAt(i, 4).toString()) ;
					double aram1 = Double.parseDouble(model.getValueAt(i-1, 4).toString()) ;
					if (m3+ aram33+ aram1 == 0) 
					{
						model.setValueAt("" ,i, 5)  ;
					}
					else 
					{
						model.setValueAt(FORMATLAMA.doub_3(m3 + aram33 + aram1) ,i, 5)  ;
					}
					model.setValueAt("",i-1, 5)  ;
				}
				else 
				{
					model.setValueAt("",i-1, 5)  ;
					m3 = m3 + aram3 ;
				}
			}
		}
	}
	private static void satir_sil()
	{
		if (table.getSelectedRow() < 0 ) return ;
		DefaultTableModel mdll = (DefaultTableModel) table.getModel();
		mdll.removeRow(table.getSelectedRow());
		mdll.addRow(new Object[]{"","","",0.00,0.000,"","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,0,"",0,""});
		table.repaint();
		toplam();
	}
	public static void kaydet()
	{
		if (textField.getText().equals("")) return ;
		if(dtc.getDate() == null) return;
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		if (mdl.getRowCount() == 0)  return;
		//
		int bSATIR = 0;
		int dSATIR = 0;
		for (int  i = 0 ; i <=  mdl.getRowCount() - 1 ; i++)
		{
			if ( mdl.getValueAt(i,1).toString().length() == 0)
			{
				bSATIR  += 1 ;
			}
			else {
				dSATIR  += 1 ;
			}
		}
		if(dSATIR == 0) 
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,  "Bos Satirlar ..." );
			return;
		}
		for (int  i = 0 ; i <=  mdl.getRowCount() - 1 ; i++)
		{
			if (! mdl.getValueAt(i,1).toString().equals(""))
			{
				if (KERESTE_KOD_KONTROL.kontrol(mdl.getValueAt(i,1).toString()) == false) {
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  i + 1 + " Nolu Satirda Urun Kodu Gecersiz..." );
					return;
				}
			}
		}
		startTimeG = System.currentTimeMillis(); 
		tar = TARIH_CEVIR.tarih_geri_saatli(dtc) ;
		GuiUtil.setWaitCursor(KERESTE_GIRIS.splitPane,true);
		satir_yaz_1();
		dipnot_yaz();
		acik_yaz();
	}
	private static void satir_yaz_1 ()
	{
		Thread.currentThread().isInterrupted();
		Runnable runner1 = new Runnable()
		{ public void run() {
			try 
			{
				GuiUtil.setWaitCursor(splitPane,true);
				GuiUtil.setWaitCursor(textField,true);
				GuiUtil.setWaitCursor(txtcari,true);
				GuiUtil.setWaitCursor(txtadres,true);
				lOG_BILGI lBILGI = new lOG_BILGI();
				lBILGI.setmESAJ(textField.getText() + " Nolu Giris Kereste Silindi");
				lBILGI.seteVRAK(textField.getText());
				ker_Access.ker_giris_sil(textField.getText() ,lBILGI,BAGLAN_LOG.kerLogDizin);
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				Progres_Bar_Temizle();
				OBS_MAIN.progressBar.setStringPainted(true);
				int mAX = mdl.getRowCount() ;
				OBS_MAIN.progressBar.setMaximum(mAX+2);
				int degisken[] = degiskenler() ; 
				//
				if( mdl.getRowCount() >= 50)
				{
					Progres_Bar_Temizle();
					KER_RAPOR_BILGI keBilgi = new KER_RAPOR_BILGI() ;
					keBilgi.setEvrak_No1(textField.getText());
					keBilgi.setGCari_Firma1(txtcari.getText());
					keBilgi.setGCari_Firma2( txtadres.getText());
					keBilgi.setGKodu1( txtdoviz.getText());
					keBilgi.setCCari_Firma1(txtdoviz.getText());
					keBilgi.setGTarih1(TARIH_CEVIR.tarih_geri_saatli(dtc) );
					keBilgi.setdOUBLE1( DecimalFormat.getNumberInstance().parse(txtkur.getText()).doubleValue());
					keBilgi.setdOUBLE2( DecimalFormat.getNumberInstance().parse(txttev.getText()).doubleValue() ) ;
					ker_Access.ker_toplu_kaydet(table,degisken,keBilgi, GLOBAL.KULL_ADI	,lBILGI,BAGLAN_LOG.kerLogDizin);
				}
				else 
				{
					for (int  i = 0 ; i <=  mdl.getRowCount() - 1 ; i++)
					{
						if (! mdl.getValueAt(i,1).toString().equals(""))
						{
							Progres_Bar(mAX,i);
							sat_yaz_2(i,degisken);
						}
					}
				}
				GuiUtil.setWaitCursor(splitPane,false);
				GuiUtil.setWaitCursor(textField,false);
				GuiUtil.setWaitCursor(txtcari,false);
				GuiUtil.setWaitCursor(txtadres,false);
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTimeG;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Kayit Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
				dOSYADAN= true;
				textField.setText("");
				dOSYADAN= false;
				Thread.currentThread().isInterrupted();
				textField.requestFocus();
				Progres_Bar_Temizle();
			}
			catch (Exception ex)
			{
				Thread.currentThread().isInterrupted();
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
			}
		}
		};
		Thread q = new Thread(runner1, "Code Executer1");
		q.start();
	}
	private static void sat_yaz_2(int i,int degiske[])
	{
		try 
		{
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			String  izahat ="";
			double  miktar=0;
			miktar = Double.parseDouble( mdl.getValueAt(i,3).toString());
			double tutar ;
			tutar =Double.parseDouble(mdl.getValueAt(i,10).toString());
			izahat =  mdl.getValueAt(i,11) .toString();
			double kur =0.00 ;
			kur = DecimalFormat.getNumberInstance().parse(txtkur.getText()).doubleValue();
			double tevk = DecimalFormat.getNumberInstance().parse(txttev.getText()).doubleValue()  ;
			double fiat =0 ;
			fiat = Double.parseDouble( mdl.getValueAt(i,7).toString());
			double isk = 0 ;
			isk = Double.parseDouble( mdl.getValueAt(i,8).toString());
			double kdv = 0 ; 
			kdv =Double.parseDouble( mdl.getValueAt(i,9).toString());
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ( " Fatura Kayit" +  mdl.getValueAt(i,1).toString() + " Mik=" + miktar + " Tut=" + tutar);
			lBILGI.seteVRAK(textField.getText());
			//
			KER_BILGI ker_BILGI = new KER_BILGI();
			ker_BILGI.setEvrak_No(textField.getText());
			ker_BILGI.setCari_Firma(txtcari.getText());
			ker_BILGI.setAdres_Firma( txtadres.getText());
			ker_BILGI.setTarih(tar);
			ker_BILGI.setAna_Grup(degiske[0]);
			ker_BILGI.setAlt_Grup(degiske[1]);
			ker_BILGI.setNakliyeci(degiske[2]);
			ker_BILGI.setDepo(degiske[4]);
			ker_BILGI.setMensei(degiske[5]);
			ker_BILGI.setDoviz( txtdoviz.getText());
			ker_BILGI.setKur(kur);
			ker_BILGI.setOzel_Kod(degiske[3]);
			ker_BILGI.setBarkod( mdl.getValueAt(i,0).toString());
			ker_BILGI.setKodu( mdl.getValueAt(i,1).toString());
			ker_BILGI.setPaket_No( mdl.getValueAt(i,2).toString());
			ker_BILGI.setMiktar(miktar);
			ker_BILGI.setKonsimento( mdl.getValueAt(i,6).toString());
			ker_BILGI.setFiat(fiat);
			ker_BILGI.setIskonto(isk);
			ker_BILGI.setKdv(kdv);
			ker_BILGI.setTutar(tutar);
			ker_BILGI.setIzahat(izahat);
			ker_BILGI.setTevkifat(tevk);
			ker_BILGI.setCikis_Evrak(  mdl.getValueAt(i,12).toString());
			ker_BILGI.setCTarih( mdl.getValueAt(i,13).toString());
			ker_BILGI.setCKdv(Double.parseDouble( mdl.getValueAt(i,14).toString()));
			ker_BILGI.setCDoviz( mdl.getValueAt(i,15).toString());
			ker_BILGI.setCFiat(Double.parseDouble( mdl.getValueAt(i,16).toString()));
			ker_BILGI.setCTutar(Double.parseDouble( mdl.getValueAt(i,17).toString()));
			ker_BILGI.setCKur(Double.parseDouble( mdl.getValueAt(i,18).toString()));
			ker_BILGI.setCCari_Firma( mdl.getValueAt(i,19).toString());
			ker_BILGI.setCAdres_Firma( mdl.getValueAt(i,20).toString());
			ker_BILGI.setCIskonto(Double.parseDouble( mdl.getValueAt(i,21).toString()));
			ker_BILGI.setCTevkifat(Double.parseDouble( mdl.getValueAt(i,22).toString()));
			ker_BILGI.setCAna_Grup(Integer.parseInt(mdl.getValueAt(i,23).toString()));
			ker_BILGI.setCAlt_Grup(Integer.parseInt(mdl.getValueAt(i,24).toString()));
			ker_BILGI.setCDepo(Integer.parseInt(mdl.getValueAt(i,25).toString()));
			ker_BILGI.setCOzel_Kod( Integer.parseInt(mdl.getValueAt(i,26).toString()));
			ker_BILGI.setCIzahat( mdl.getValueAt(i,27).toString());
			ker_BILGI.setCNakliyeci(Integer.parseInt(mdl.getValueAt(i,28).toString()));
			ker_BILGI.setCUSER( mdl.getValueAt(i,29).toString());
			ker_BILGI.setCSatir(Integer.parseInt(mdl.getValueAt(i,30).toString()));
			ker_BILGI.setSatir(i);
			ker_Access.ker_kaydet(ker_BILGI, GLOBAL.KULL_ADI	,lBILGI,BAGLAN_LOG.kerLogDizin);
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	public static void evrak_sil()
	{
		try {
			if (textField.getText().equals("")) return ;
			if (table.getRowCount() == 0) return;
			int g =  JOptionPane.showOptionDialog( null, textField.getText() + " Nolu Evrak Dosyadan Silinecek ..?"  ,
					"Kereste Dosyasindan Evrak Silme",   JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,null, oac.options, oac.options[1]); 
			if(g != 0 ) { return;	}
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ(textField.getText() + " Nolu Giris Kereste Silindi");
			lBILGI.seteVRAK(textField.getText());
			ker_Access.ker_giris_sil(textField.getText() ,lBILGI,BAGLAN_LOG.kerLogDizin);
			dipnot_sil();
			acik_sil();
			textField.setText("");
			textField.requestFocus();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private static int[] degiskenler() throws ClassNotFoundException, SQLException
	{
		int degisken[] = {0,0,0,0,0,0} ;
		ResultSet rs =null ;
		if ( ! cmbanagrup.getSelectedItem().toString().equals("") ) 
		{
			rs = ker_Access.ker_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getSelectedItem().toString());
			if (!rs.isBeforeFirst() ) {      		
			}
			else
			{
				rs.next();
				degisken[0]  = rs.getInt("AGID_Y");
			}
		}
		///
		if ( ! cmbaltgrup.getSelectedItem().toString().equals("") ) {
			rs = ker_Access.ker_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN",  cmbaltgrup.getSelectedItem().toString());
			if (!rs.isBeforeFirst() ) {      		
			}
			else
			{
				rs.next();
				degisken[1]   = rs.getInt("ALID_Y");
			}
		}
		//*************nakliyeci
		if ( ! cmbnakliyeci.getSelectedItem().toString().equals("") ) {
			rs = ker_Access.ker_kod_degisken_ara("NAKID_Y", "UNVAN", "NAKLIYECI", cmbnakliyeci.getSelectedItem().toString());
			if (!rs.isBeforeFirst() ) {      		
			}
			else
			{
				rs.next();
				degisken[2]   = rs.getInt("NAKID_Y");
			}
		}
		//*************oz kod
		if ( ! cmbozkod.getSelectedItem().toString().equals("") ) {
			rs = ker_Access.ker_kod_degisken_ara("OZ1ID_Y", "OZEL_KOD_1", "OZ_KOD_1_DEGISKEN", cmbozkod.getSelectedItem().toString());
			if (!rs.isBeforeFirst() ) {      
			}
			else
			{
				rs.next();
				degisken[3]   = rs.getInt("OZ1ID_Y");
			}
		}
		//*****Mensei
		if (! cmbmensei.getSelectedItem().toString().equals(""))
		{
			rs = ker_Access.ker_kod_degisken_ara("MEID_Y", "MENSEI", "MENSEI_DEGISKEN",  cmbmensei.getSelectedItem().toString());
			if (!rs.isBeforeFirst() ) {      		
			}
			else
			{
				rs.next();
				degisken[5] = rs.getInt("MEID_Y");
			}
		}
		//*****Depo
		if (! cmbdepo.getSelectedItem().toString().equals(""))
		{
			rs = ker_Access.ker_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN",  cmbdepo.getSelectedItem().toString());
			if (!rs.isBeforeFirst() ) {      		
			}
			else
			{
				rs.next();
				degisken[4] = rs.getInt("DPID_Y");
			}
		}

		return degisken;
	}
	private void fiskont()
	{
		//Runnable runner = new Runnable()
		//{ public void run() {
			try {
				long startTime = System.currentTimeMillis();
				ResultSet rss = null;
				GuiUtil.setWaitCursor(KERESTE_GIRIS.splitPane,true);
				rss = ker_Access.ker_oku(textField.getText(), "G");
				if (!rss.isBeforeFirst() ) {  
					yeni_fat = true;
					dOSYADAN = true;
					GRID_TEMIZLE.grid_temizle(table);
					dOSYADAN = false;
					sifirla();
					GuiUtil.setWaitCursor(KERESTE_GIRIS.splitPane,false);
				}
				else
				{
					rss.next();
					dOSYADAN = true;
					GRID_TEMIZLE.grid_temizle(table);
					sifirla();
					dtc.setDate(rss.getDate("Tarih"));
					txtadres.setText(rss.getString("Adres_Firma"));
					txtcari.setText(rss.getString("Cari_Firma"));
					txtdoviz.setText(rss.getString("Doviz"));
					txtkur.setText(FORMATLAMA.doub_4(rss.getDouble("Kur")));
					//  '***********GRUP DOLDUR
					ResultSet rsa=null;
					rsa = ker_Access.ker_kod_degisken_ara("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN",String.valueOf(rss.getInt("Ana_Grup")));
					if (!rsa.isBeforeFirst() ) {  
						cmbaltgrup.setEnabled(false);
						cmbanagrup.setSelectedItem("");
					} 
					else
					{
						rsa.next();
						cmbanagrup.setSelectedItem(rsa.getString("ANA_GRUP"));
						cmbaltgrup.setEnabled(true);
					}
					//**Alt Grup
					rsa = null;
					rsa = ker_Access.ker_kod_degisken_ara("ALT_GRUP", "ALID_Y", "ALT_GRUP_DEGISKEN",String.valueOf(rss.getInt("Alt_Grup")));
					if (!rsa.isBeforeFirst() ) {  
						cmbaltgrup.setSelectedItem("");
					} 
					else
					{
						rsa.next();
						cmbaltgrup.setSelectedItem(rsa.getString("ALT_GRUP"));
					}
					//***Aciklama
					textField_9.setText(ker_Access.aciklama_oku("KER", 1, textField.getText(), "G"));
					textField_10.setText(ker_Access.aciklama_oku("KER", 2, textField.getText(), "G"));
					//**nakliye
					rsa = null;
					rsa = ker_Access.ker_kod_degisken_ara("UNVAN", "NAKID_Y", "NAKLIYECI",String.valueOf(rss.getInt("Nakliyeci")));
					if (!rsa.isBeforeFirst() ) {  
						cmbnakliyeci.setSelectedItem("");
					} 
					else
					{
						rsa.next();
						cmbnakliyeci.setSelectedItem(rsa.getString("UNVAN"));
					}		
					//**ozkod
					rsa = null;
					rsa = ker_Access.ker_kod_degisken_ara("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN",String.valueOf(rss.getInt("Ozel_Kod")));
					if (!rsa.isBeforeFirst() ) {  
						cmbozkod.setSelectedItem("");
					} 
					else
					{
						rsa.next();
						cmbozkod.setSelectedItem(rsa.getString("OZEL_KOD_1"));
					}		
					//**dpo
					rsa = null;
					rsa = ker_Access.ker_kod_degisken_ara("DEPO", "DPID_Y", "DEPO_DEGISKEN",String.valueOf(rss.getInt("Depo")));
					if (!rsa.isBeforeFirst() ) {  
						cmbdepo.setSelectedItem("");
					} 
					else
					{
						rsa.next();
						cmbdepo.setSelectedItem(rsa.getString("DEPO"));
					}	
					//**Mensei
					rsa = null;
					rsa = ker_Access.ker_kod_degisken_ara("MENSEI", "MEID_Y", "MENSEI_DEGISKEN",String.valueOf(rss.getInt("Mensei")));
					if (!rsa.isBeforeFirst() ) {  
						cmbmensei.setSelectedItem("");
					} 
					else
					{
						rsa.next();
						cmbmensei.setSelectedItem(rsa.getString("MENSEI"));
					}		
					rss.first();   
					DefaultTableModel mdl = (DefaultTableModel) table.getModel();
					int satir =0 ;
					do
					{
						mdl.insertRow(satir,new Object[]{
								rss.getString("Barkod"),
								rss.getString("Kodu"),
								rss.getString("Paket_No"),
								rss.getDouble("Miktar"), 
								m3(rss.getString("Kodu"),rss.getDouble("Miktar")),
								"" ,
								rss.getString("Konsimento"),
								rss.getDouble("Fiat"),
								rss.getDouble("Iskonto"),
								rss.getDouble("Kdv"),
								rss.getDouble("Tutar"),
								rss.getString("Izahat"),
								rss.getString("Cikis_Evrak"),
								rss.getString("CTarih"),
								rss.getDouble("CKdv"),
								rss.getString("CDoviz"),
								rss.getDouble("CFiat"),
								rss.getDouble("CTutar"),
								rss.getDouble("CKur"),
								rss.getString("CCari_Firma"),
								rss.getString("CAdres_Firma"),
								rss.getDouble("CIskonto"),
								rss.getDouble("CTevkifat"),
								rss.getInt("CAna_Grup"),
								rss.getInt("CAlt_Grup"),
								rss.getString("CDepo"),
								rss.getString("COzel_Kod"),
								rss.getString("CIzahat"),
								rss.getInt("CNakliyeci"),
								rss.getString("CUser"),
								rss.getInt("CSatir")});
						txttev.setText(FORMATLAMA.doub_0(rss.getDouble("Tevkifat")));
						satir +=1 ;

						if (satir == mdl.getRowCount())  
						{
							mdl.addRow(new Object[]{"","","",0.00,0.000,"","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,"","",0,"",0});
						}
						else  {
							mdl.removeRow(mdl.getRowCount() -1);	
						}
					}  while (rss.next()) ;
					mdl.addRow(new Object[]{"","","",0.00,0.000,"","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,"","",0,""});
					paketm3();
					dOSYADAN = false;
					toplam();
					dipnot_oku();
					kod_ADI( mdl.getValueAt(0,1).toString(), mdl.getValueAt(0,6).toString());
					long endTime = System.currentTimeMillis();
					long estimatedTime = endTime - startTime;
					double seconds = (double)estimatedTime/1000; 
					OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
					GuiUtil.setWaitCursor(KERESTE_GIRIS.splitPane,false);
				}
			}
			catch (Exception ex)
			{
				GuiUtil.setWaitCursor(KERESTE_GIRIS.splitPane,false);
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
			}
		//}
		//};
		//Thread t = new Thread(runner, "Code Executer");
		//t.start();
	}
	private void kod_ADI(String toke,String kons) throws ClassNotFoundException, SQLException 

	{
		String[] token = toke.toString().split("-");
		String aciklamaString;
		aciklamaString = ker_Access.kod_adi(token[0]);
		lblkodAciklama.setText(aciklamaString);
		Dimension size = lblkodAciklama.getPreferredSize();
		lblkodAciklama.setBounds(10, 55, size.width +10, 14);
		lblkONSIMENTO.setText(ker_Access.kons_adi(kons));
		size = lblkONSIMENTO.getPreferredSize();
		lblkONSIMENTO.setBounds(10, 35, size.width +10, 14);
	}
	private void sifirla()
	{
		String deger;
		Integer sat_sayi;
		try {
			deger = GLOBAL.setting_oku("KER_FAT_SATIR").toString();
			sat_sayi =Integer.parseInt(deger);
			for (int i = 0; i <= sat_sayi; i ++)
			{
				satir_ilave();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		table.isRowSelected(0);
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
		label.setText("0.00");
		lblNewLabel_20.setText("0.00");
		label_2.setText("0.00");
		txttev.setText("0") ;// ' tevkifat
		
		try {
			txtdoviz.setText(GLOBAL.setting_oku("PRG_PARA").toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		txtcari.setText("");
		txtadres.setText("");
		cmbanagrup.setSelectedItem("");
		cmbaltgrup.setSelectedItem("");
		cmbozkod.setSelectedItem("");
		cmbnakliyeci.setSelectedItem("");
		cmbdepo.setSelectedItem("");
		cmbmensei.setSelectedItem("");
		textField_9.setText("");
		textField_10.setText("");
		txtkur.setText("0.0000");
		lblkodAciklama.setText("");
		lblkONSIMENTO.setText("");
		dtc.setDate(new Date());
		lblPaket.setText(FORMATLAMA.doub_0(0));
		label_9.setText(FORMATLAMA.doub_2(0));
		lblNewLabel_13.setText( FORMATLAMA.doub_0(0));
		label.setText(FORMATLAMA.doub_2(0));
		label_1.setText(FORMATLAMA.doub_2(0));
		label_2.setText(FORMATLAMA.doub_2(0));
		label_3.setText(FORMATLAMA.doub_2(0));
		label_6.setText(FORMATLAMA.doub_2(0));
		label_7.setText(FORMATLAMA.doub_2(0));
		label_8.setText(FORMATLAMA.doub_3(0));
		label_8_1.setText( FORMATLAMA.doub_0(0));
		label_9.setText("0.00");
		lblNewLabel_20.setText(FORMATLAMA.doub_2(0));
	}
	private static void dipnot_yaz()
	{
		try {
			dipnot_sil();
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ( "Kereste Dip Not Kayit :"+ textField_5.getText()  );
			lBILGI.seteVRAK(textField.getText());
			ker_Access.dipnot_yaz(textField.getText(), textField_5.getText(),textField_6.getText(),textField_7.getText(), "K", "G",GLOBAL.KULL_ADI,
						lBILGI,BAGLAN_LOG.kerLogDizin);
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private static void dipnot_sil()
	{
		try {
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ("Kereste Dip Not Sil "  );
			lBILGI.seteVRAK(textField.getText());
			ker_Access.dipnot_sil(textField.getText(), "K", "G",lBILGI,BAGLAN_LOG.kerLogDizin);
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void dipnot_oku()
	{
		try {
			ResultSet rs =null ;
			rs =    ker_Access.dipnot_oku(textField.getText(), "K", "G");
			if (!rs.isBeforeFirst() ) {  
			} 
			else
			{
				rs.next();
				textField_5.setText(rs.getString("Bir"));
				textField_6.setText(rs.getString("Iki"));
				textField_7.setText(rs.getString("Uc"));
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private   double m3(String kod,double miktar)
	{
		String[] token = kod.toString().split("-");
		if(token.length == 1) return 0;
		double m3 = 0 ;
		if (! token[1].toString().trim().isEmpty() && ! token[2].toString().trim().isEmpty() && ! token[3].toString().trim().isEmpty()) {
			m3 = ((Double.parseDouble(token[1].toString().trim()) * Double.parseDouble(token[2].toString().trim()) * Double.parseDouble(token[3].toString().trim() )) * miktar)/1000000000 ;
		}
		return m3 ;
	}
	private static void acik_yaz()
	{
		try {
			acik_sil();
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ("Kereste Aciklama Yaz  Giris :" + textField_9.getText()  );
			lBILGI.seteVRAK(textField.getText());
			ker_Access.aciklama_yaz("KER", 1, textField.getText(),  textField_9.getText(), "G",
					lBILGI,BAGLAN_LOG.kerLogDizin);
			lBILGI.setmESAJ( "Kereste Aciklama Yaz  Giris :" + textField_10.getText() );
			ker_Access.aciklama_yaz("KER", 2, textField.getText(), textField_10.getText(), "G",
					lBILGI,BAGLAN_LOG.kerLogDizin);
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private static void acik_sil()
	{
		try {
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ( "Kereste Aciklama Sil  Giris "  );
			lBILGI.seteVRAK(textField.getText());
			ker_Access.aciklama_sil("KER", textField.getText(), "G",
					lBILGI,BAGLAN_LOG.kerLogDizin);

		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void dosya_oku() throws IOException
	{
		Runnable runner = new Runnable()
		{ public void run() {
			try
			{
				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Dosya Seciniz");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Dosya Sec");
				chooser.setApproveButtonToolTipText("Dosya Sec");
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("Excell Dosyalari", "xls", "xlsx"));
				chooser.setApproveButtonMnemonic('s');
				Workbook workbook = null ;
				FileInputStream fis = null ;
				Sheet sheet = null;
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					File excelFile = chooser.getSelectedFile();
					String path = excelFile.getAbsolutePath();
					if(path.endsWith("xls"))
					{
						fis = new FileInputStream(excelFile); 
						workbook = new HSSFWorkbook(fis );
						HSSFFont wbFont ;
						wbFont=  (HSSFFont) workbook.createFont();
						wbFont.setCharSet(HSSFFont.ANSI_CHARSET); //Your Character encoding goes in the parameter
						sheet = (HSSFSheet) workbook.getSheetAt(0);
					}
					else if(path.endsWith("xlsx"))
					{
						fis = new FileInputStream(excelFile); 
						workbook = new XSSFWorkbook(fis);
						XSSFFont wbFont ;
						wbFont=  (XSSFFont) workbook.createFont();
						wbFont.setCharSet(XSSFFont.ANSI_CHARSET); //Your Character encoding goes in the parameter
						sheet = (XSSFSheet) workbook.getSheetAt(0);
					}
				}
				else
				{
					return ;
				}
				getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
				long startTime = System.currentTimeMillis(); 
				GuiUtil.setWaitCursor(splitPane,true);
				Iterator<Row> rowItt = sheet.iterator();
				Row roww = rowItt.next();
				int say = 0 ;
				while(rowItt.hasNext()) 
				{
					if (  roww.getCell(1) != null)
					{
						say += 1;
					}
					roww = rowItt.next();
				}
				Iterator<Row> rowIt = sheet.iterator();
				String paketno = "" ;
				String arapaketno = "" ;
				String sonpaketno = "" ;
				String arasinif ="" ;
				String sonsinif ="" ;
				String kalinlik ="" ;
				String sonkalinlik ="" ;
				String genislik ="" ;
				String songenislik ="" ;
				String boy ="";
				String sonboy = "" ;
				int adet = 0 ;
				int sonadet = 0 ;
				dOSYADAN = true;
				GRID_TEMIZLE.grid_temizle(table);
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				Row row = rowIt.next();
				int satir = 0 ;
				Progres_Bar_Temizle();
				OBS_MAIN.progressBar.setMaximum(say);
				while(rowIt.hasNext()) 
				{
					if (  row.getCell(1) != null)
					{
						Progres_Bar(say, satir);
						paketno =  row.getCell(1).getStringCellValue();
						
						if ( ! paketno.equals("")) {
							arapaketno = paketno.substring(1, paketno.length()) ;
							sonpaketno =  arapaketno ;
							arasinif = Integer.toString((int) row.getCell(2).getNumericCellValue()) ;
							
							kalinlik =  String.valueOf( row.getCell(3).getNumericCellValue() ) ;
							kalinlik =   kalinlik.substring(2, kalinlik.length())  ;
							kalinlik =  kalinlik + StringUtils.repeat("0", 3- kalinlik.length())  ;

							genislik =  String.valueOf( row.getCell(4).getNumericCellValue() ) ;
						    
							int kjj = 4 - genislik.length() ;
							genislik = genislik +  StringUtils.repeat("0", kjj)  ;
							genislik =  "0" + genislik.substring(2, genislik.length()) +"0" ;
							songenislik = genislik ;
							
							boy =  FORMATLAMA.doub_0(row.getCell(5).getNumericCellValue()*10) ;
							int kj = 4 - boy.length() ;
							boy = boy +  StringUtils.repeat("0", kj)  ;
							
							
							adet =  (int) row.getCell(6).getNumericCellValue() ;
							String yeniSinif = arasinif ;
							if ( arasinif.equals("1")) 
							{  arasinif = "11" ;}
							else if ( arasinif.equals("2")) 
							{  arasinif = "12" ;}
							else  if ( arasinif.equals("3")) 
							{  arasinif = "13" ;}
							String kODU= arasinif + "-" +kalinlik + "-" + boy + "-" + genislik ;
							//
							int kjp = 10 - (yeniSinif + sonpaketno).length() ;
							String yeniPaket =  yeniSinif + StringUtils.repeat("0", kjp) + sonpaketno  ;
							//
						      int min = 600; // Minimum value of range
						      int max = 700; // Maximum value of range
						      double random_fiat = (double)Math.floor(Math.random() * (max - min + 1) + min);
							///
							mdl.addRow(new Object[]{"",kODU,yeniPaket,adet,
									m3(kODU,adet),"",strKonsimento,random_fiat,0.00,0.00,m3(kODU, adet) * random_fiat,"Izahat","","1900-01-01 00:00:00.0",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,0,"",0,"",0});
						}
						else {
							sonpaketno =  arapaketno ;
							sonsinif = arasinif ;
							
							kalinlik =  String.valueOf( row.getCell(3).getNumericCellValue() ) ;
							kalinlik =   kalinlik.substring(2, kalinlik.length())  ;
							kalinlik =  kalinlik + StringUtils.repeat("0", 3- kalinlik.length())  ;
							
							boy =  FORMATLAMA.doub_0(row.getCell(5).getNumericCellValue()*10) ;
							int kj = 4 - boy.length() ;
							boy = boy +  StringUtils.repeat("0", kj)  ;
							
							adet =  (int) row.getCell(6).getNumericCellValue() ;

							genislik =  String.valueOf( row.getCell(4).getNumericCellValue() ) ;
							int kjj = 4 - genislik.length() ;
							genislik = genislik +  StringUtils.repeat("0", kjj)  ;
							genislik =  "0" + genislik.substring(2, genislik.length()) +"0" ;
							
							String kODU= sonsinif + "-" + kalinlik + "-" + boy + "-" + genislik ;
							//
							int kjp = 10 - ( arasinif.substring(1, 2)   + sonpaketno).length() ;
							String yeniPaket =  arasinif.substring(1, 2) + StringUtils.repeat("0", kjp) + sonpaketno  ;
							//
							//
						      int min = 600; // Minimum value of range
						      int max = 700; // Maximum value of range
						      double random_fiat = (double)Math.floor(Math.random() * (max - min + 1) + min);
							///

							mdl.addRow(new Object[]{"",kODU,yeniPaket,adet,
									m3(kODU,adet),"",strKonsimento,random_fiat,0.00,0.00,m3(kODU, adet) * random_fiat,"Izahat","","1900-01-01 00:00:00.0",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,0,"",0,"",0});
						}
					}  
					satir += 1 ;
					//if (satir ==2000)  break;
					row = rowIt.next();
				}
				workbook.close();
				fis.close();
				paketm3();
				dOSYADAN = false;
				toplam();
				Thread.currentThread().isInterrupted();
				GuiUtil.setWaitCursor(splitPane,false);
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
				getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
				Progres_Bar_Temizle();
			}
			catch (Exception ex)
			{
				Thread.currentThread().isInterrupted();
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
			}
		}
		};
		Thread t = new Thread(runner, "Code Executer");
		t.start();
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
    public static void cari_kaydet()
    {
    	try {
    		BORC_ALACAK hsp ;
    		hsp = new BORC_ALACAK();
    		hsp.setLocationRelativeTo(OBS_MAIN.desktopPane);
    		oac.hsp_hsp_kodu = "" ;
    		String bh = "",alh="";
    		// '***************** hsp cinsleri ogren***********************BORCLU HESAP
    		hsp.lblNewLabel.setText("Borclu Hesap");
    		hsp.setVisible(true);
    		bh = oac.hsp_hsp_kodu;
    		alh = txtcari.getText();
    		if (bh.equals("")) return ;
    		ResultSet rs ;
    		//*******************************************************************************
    		rs = c_Access.hesap_adi_oku(bh);
    		if (!rs.isBeforeFirst() ) 
    		{
    			JOptionPane.showMessageDialog(null,  "Girilen Borclu Hesap Kodunda  bir  hesaba rastlanmadi!!!!",  "Kereste Cari Kaydetme", JOptionPane.ERROR_MESSAGE); 
    			return ;
    		} 
    		rs= null;
    		rs = c_Access.hesap_adi_oku(txtcari.getText());
    		if (!rs.isBeforeFirst() ) {  
    			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  textField.getText() +  " Bu numarada hesaba rastlanmadi!!!!" );
    			return;
    		} 
    		double sdf =  DecimalFormat.getNumberInstance().parse(label_8_1.getText()).intValue()  ;
    		String str_4  ="";
    		int e_number =0;
    		e_number = c_Access.cari_fisno_al();
    		DefaultTableModel model = (DefaultTableModel)table.getModel();
    		double tutar  = DecimalFormat.getNumberInstance().parse(label.getText()).doubleValue()  ;
    		lOG_BILGI lBILGI = new lOG_BILGI();
    		str_4 = textField.getText() + "'Evrak ile "  + FORMATLAMA.doub_0(DecimalFormat.getNumberInstance().parse(lblPaket.getText()).intValue())  + " Paket " +  FORMATLAMA.doub_0(sdf) + " Adet " + FORMATLAMA.doub_3(DecimalFormat.getNumberInstance().parse(label_8.getText()).doubleValue()) + " m3 Urun Girisi" ;
    		dEKONT_BILGI dBilgi = new dEKONT_BILGI();
    		dBilgi.setbHES(bh);
    		dBilgi.settAR(TARIH_CEVIR.tarih_geri_saatli(dtc));
    		dBilgi.seteVRAK(e_number);
    		dBilgi.setbCINS("");
    		dBilgi.setbKUR(1);
    		dBilgi.setbORC(tutar);
    		dBilgi.setaHES(alh);
    		dBilgi.setaCINS("");
    		dBilgi.setaKUR(1);
    		dBilgi.setaLACAK(tutar);
    		dBilgi.setiZAHAT(str_4);
    		dBilgi.setkOD("Alış");
    		dBilgi.setuSER( GLOBAL.KULL_ADI);
    		lBILGI.setmESAJ("Alacakli Hes:" +alh + " Tut:" + tutar +	" Borclu Hes:"+ bh );
    		lBILGI.seteVRAK(textField.getText());
    		c_Access.cari_dekont_kaydet(dBilgi,	lBILGI ,	BAGLAN_LOG.cariLogDizin);
    		
    		OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO,"Evrak Cari Hesaba Basari ile Kaydedilmistir...." );
    	}
    	catch (Exception ex)
    	{
    		OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
    	}
    }
    	public static void excell_aktar()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();

		if (mdl.getRowCount() == 0 )
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, "Aktarilacak Bilgi Yok....." );
		}
		else
		{
			write() ;	
		}
	}
	public static void write()
	{
		try
		{
			UIManager.put("FileChooser.cancelButtonText", "Vazgec");
			UIManager.put("FileChooser.saveButtonText", "Kaydet");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.resetChoosableFileFilters();
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileFilter xls = new FileNameExtensionFilter("Microsoft Excel 97-2003 Worksheet (.xls)", "xls");
			FileFilter xlxs = new FileNameExtensionFilter("Microsoft Excel Worksheet (.xlsx) ", "xlsx");
			fileChooser.addChoosableFileFilter(xls);
			fileChooser.addChoosableFileFilter(xlxs);
			fileChooser.setCurrentDirectory(new java.io.File("."));
			fileChooser.setApproveButtonText("Kaydet");
			fileChooser.setDialogTitle("Excell Kayit");   

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");  
			LocalDateTime now = LocalDateTime.now();  
			String zaman = dtf.format(now)  ;

			File outputfile = new File("Evrak_" + textField.getText());
			fileChooser.setSelectedFile(outputfile);
			int returnVal = fileChooser.showSaveDialog(null);
			if ( returnVal != JFileChooser.APPROVE_OPTION )
			{
				return;
			}
			GuiUtil.setWaitCursor(splitPane,true);
			String uzanti ="";
			File excelFile =  FILE_UZANTI. getSelectedFileWithExtension(fileChooser);
			uzanti  = excelFile.getName().substring(excelFile.getName().lastIndexOf("."));
			int sATIR = 0 ;
			if  (uzanti.equals(".xls") )
			{
				@SuppressWarnings("resource")
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Evrak_" + textField.getText());
				HSSFFont headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setColor(IndexedColors.BLUE.getIndex()); 
				HSSFCellStyle headerStyle = workbook.createCellStyle();
				HSSFCellStyle headerSolaStyle = workbook.createCellStyle();
				headerStyle.setFont(headerFont);
				headerStyle.setAlignment(HorizontalAlignment.RIGHT);

				HSSFFont solaFont = workbook.createFont();
				solaFont.setFontName("Arial Narrow");
				solaFont. setFontHeight((short)(10*20));
				HSSFCellStyle solaStyle = workbook.createCellStyle();
				solaStyle.setFont(solaFont);
				solaStyle.setAlignment(HorizontalAlignment.LEFT);

				HSSFFont headerSolaFont = workbook.createFont();
				headerSolaFont.setBold(true);
				headerSolaFont.setColor(IndexedColors.BLUE.getIndex()); 
				headerSolaStyle.setFont(headerSolaFont);
				headerSolaStyle.setAlignment(HorizontalAlignment.LEFT);

				HSSFCellStyle satirStyle = workbook.createCellStyle();
				HSSFCellStyle satirStylemik = workbook.createCellStyle();
				HSSFCellStyle satirStyle3 = workbook.createCellStyle();
				HSSFCellStyle satirStyle2 = workbook.createCellStyle();
				HSSFFont satirFont = workbook.createFont();
				satirFont.setFontName("Arial Narrow");
				satirFont. setFontHeight((short)(10*20));
				satirStyle.setFont(satirFont);
				satirStyle.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle3.setFont(satirFont);
				satirStyle2.setFont(satirFont);
				satirStylemik.setFont(satirFont);
				satirStyle3.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
				satirStyle2.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
				satirStylemik.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0"));
				satirStyle3.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle2.setAlignment(HorizontalAlignment.RIGHT);
				satirStylemik.setAlignment(HorizontalAlignment.RIGHT);
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				HSSFCellStyle acikStyle = workbook.createCellStyle();
				HSSFFont acikFont = workbook.createFont();
				acikFont.setColor(IndexedColors.RED.getIndex()); 
				acikFont.setBold(true);
				acikFont.setFontName("Arial");
				acikFont. setFontHeight((short)(22*20));
				acikStyle.setFont(acikFont);
				acikStyle.setAlignment(HorizontalAlignment.CENTER);

				HSSFCellStyle satirStyle2_ARA = workbook.createCellStyle();
				satirStyle2_ARA.setFont(satirFont);
				satirStyle2_ARA.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
				satirStyle2_ARA.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle2_ARA.setBorderTop(BorderStyle.MEDIUM);
				satirStyle2_ARA.setBorderBottom(BorderStyle.MEDIUM);
				HSSFCellStyle satirStyle3_ARA = workbook.createCellStyle();
				satirStyle3_ARA.setFont(satirFont);
				satirStyle3_ARA.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
				satirStyle3_ARA.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle3_ARA.setBorderTop(BorderStyle.MEDIUM);
				satirStyle3_ARA.setBorderBottom(BorderStyle.MEDIUM);
				HSSFCellStyle satirStylemik_ARA = workbook.createCellStyle();
				satirStylemik_ARA.setFont(satirFont);
				satirStylemik_ARA.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0"));
				satirStylemik_ARA.setBorderTop(BorderStyle.MEDIUM);
				satirStylemik_ARA.setBorderBottom(BorderStyle.MEDIUM);
				satirStylemik_ARA.setAlignment(HorizontalAlignment.RIGHT);
				HSSFCellStyle satirStyleBASLIK = workbook.createCellStyle();
				satirStyleBASLIK.setFont(satirFont);
				satirStyleBASLIK.setBorderTop(BorderStyle.MEDIUM);
				satirStyleBASLIK.setBorderBottom(BorderStyle.MEDIUM);
				satirStyleBASLIK.setAlignment(HorizontalAlignment.LEFT);
				HSSFCellStyle satirStyleBASLIK2 = workbook.createCellStyle();
				satirStyleBASLIK2.setFont(satirFont);
				satirStyleBASLIK2.setBorderTop(BorderStyle.MEDIUM);
				satirStyleBASLIK2.setBorderBottom(BorderStyle.MEDIUM);
				satirStyleBASLIK2.setAlignment(HorizontalAlignment.RIGHT);
				HSSFCellStyle satirStyleTOPTUT = workbook.createCellStyle();
				satirStyleTOPTUT.setFont(satirFont);
				satirStyleTOPTUT.setBorderTop(BorderStyle.MEDIUM);
				satirStyleTOPTUT.setAlignment(HorizontalAlignment.RIGHT);

				Cell cell ;
				Row bosRow = sheet.createRow(1);

				Row satir1 = sheet.createRow(2);
				cell = satir1.createCell(0);
				cell.setCellStyle(solaStyle);
				cell.setCellValue("Evrak No :");

				cell = satir1.createCell(1);
				cell.setCellStyle(solaStyle);
				cell.setCellValue(textField.getText());

				cell = satir1.createCell(9);
				cell.setCellValue(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc));
				cell.setCellStyle(satirStyle);


				Row satir2 = sheet.createRow(3);
				cell = satir2.createCell(0);
				cell.setCellStyle(solaStyle);
				cell.setCellValue("Musteri Kodu:");

				cell = satir2.createCell(1);
				cell.setCellStyle(solaStyle);
				cell.setCellValue(txtcari.getText());

				Row satir3 = sheet.createRow(4);
				sheet.addMergedRegion(new CellRangeAddress(4,4,1,3));


				cell = satir3.createCell(1);
				cell.setCellStyle(solaStyle);
				cell.setCellValue(lblNewLabel_3.getText());


				Row bosRow5 = sheet.createRow(5);

				Row aCIKLAMA = sheet.createRow(6);

				cell = aCIKLAMA.createCell(0);
				cell.setCellStyle(satirStyleBASLIK);
				cell.setCellValue("Barkod");

				cell = aCIKLAMA.createCell(1);
				cell.setCellStyle(satirStyleBASLIK);
				cell.setCellValue("Urun Kodu");

				cell = aCIKLAMA.createCell(2);
				cell.setCellStyle(satirStyleBASLIK);
				cell.setCellValue("Paket_No");

				cell = aCIKLAMA.createCell(3);
				cell.setCellValue("Miktar");
				cell.setCellStyle(satirStyleBASLIK2);

				cell = aCIKLAMA.createCell(4);
				cell.setCellValue("m3");
				cell.setCellStyle(satirStyleBASLIK2);

				cell = aCIKLAMA.createCell(5);
				cell.setCellValue("Paket m3");
				cell.setCellStyle(satirStyleBASLIK2);

				cell = aCIKLAMA.createCell(6);
				cell.setCellValue("Fiat");
				cell.setCellStyle(satirStyleBASLIK2);

				cell = aCIKLAMA.createCell(7);
				cell.setCellValue("Iskonto");
				cell.setCellStyle(satirStyleBASLIK2);

				cell = aCIKLAMA.createCell(8);
				cell.setCellValue("KDV");
				cell.setCellStyle(satirStyleBASLIK2);

				cell = aCIKLAMA.createCell(9);
				cell.setCellValue("Tutar");
				cell.setCellStyle(satirStyleBASLIK2);

				//******************SATIRLAR ***********************************************	
				int satir = 0 ;
				for (int i =0;i< mdl.getRowCount() ;i++)
				{
					if (! mdl.getValueAt(i, 1).equals("") )
					{
						Row satirRow = sheet.createRow(i+7);
						for (int s =0;s<= 10 ;s++)
						{

							if (s == 0 || s == 1 || s == 2)
							{
								cell = satirRow.createCell(s);
								cell.setCellValue( mdl.getValueAt(i,s).toString());
								cell.setCellStyle(solaStyle); 
							}
							else if (s == 3)
							{
								if (  GLOBAL.validCheck(mdl.getValueAt(i,s).toString()) == true )
								{
									cell = satirRow.createCell(s);
									cell.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									cell.setCellStyle(satirStylemik); 
								}
							}
							else if (s == 4 || s == 5)
							{
								if (  GLOBAL.validCheck(mdl.getValueAt(i,s).toString()) == true )
								{
									cell = satirRow.createCell(s);
									cell.setCellValue( Double.parseDouble( mdl.getValueAt(i,s).toString()));
									cell.setCellStyle(satirStyle3); 
								}
							}
							else if (s == 7 || s == 8 || s == 9 ||  s == 10 )
							{

								if (  GLOBAL.validCheck(mdl.getValueAt(i,s).toString()) == true )
								{
									cell = satirRow.createCell(s-1);
									cell.setCellStyle(satirStyle2);
									cell.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
								}
							}

						}
						satir += 1 ;
					}
				}

				Row toplam1  = sheet.createRow(satir + 7);
				cell = toplam1.createCell(3);
				cell.setCellValue( label_8_1.getText());
				cell.setCellStyle(satirStylemik_ARA); 

				cell = toplam1.createCell(4);
				cell.setCellValue(label_8.getText());
				cell.setCellStyle(satirStyle3_ARA); 

				cell = toplam1.createCell(9);
				cell.setCellValue( label_9.getText());
				cell.setCellStyle(satirStyleTOPTUT); 

				cell = toplam1.createCell(8);
				cell.setCellStyle(satirStyleTOPTUT); 

				cell = toplam1.createCell(7);
				cell.setCellStyle(satirStyleTOPTUT); 

				cell = toplam1.createCell(6);
				cell.setCellStyle(satirStyleTOPTUT); 

				cell = toplam1.createCell(5);
				cell.setCellValue(lblPaket.getText());
				cell.setCellStyle(satirStyleTOPTUT); 

				cell = toplam1.createCell(2);
				cell.setCellStyle(satirStyleTOPTUT); 

				cell = toplam1.createCell(1);
				cell.setCellStyle(satirStyleTOPTUT); 

				cell = toplam1.createCell(0);
				cell.setCellStyle(satirStyleTOPTUT); 

				//***********************************************************
				Row toplam2  = sheet.createRow(satir + 8);
				sheet.addMergedRegion(new CellRangeAddress(satir + 8,satir + 8,6,8));
				cell = toplam2.createCell(6);
				cell.setCellValue( "Iskonto");
				cell.setCellStyle(satirStyle);

				cell = toplam2.createCell(9);
				cell.setCellValue( label_6.getText());
				cell.setCellStyle(satirStyle2);

				Row toplam3  = sheet.createRow(satir + 9);
				sheet.addMergedRegion(new CellRangeAddress(satir + 9,satir + 9,6,8));
				cell = toplam3.createCell(6);
				cell.setCellValue( "Iskonto Tutar");
				cell.setCellStyle(satirStyle);

				cell = toplam3.createCell(9);
				cell.setCellValue( label_7.getText());
				cell.setCellStyle(satirStyle2);

				Row toplam4  = sheet.createRow(satir + 10);
				sheet.addMergedRegion(new CellRangeAddress(satir + 10,satir + 10,6,8));
				cell = toplam4.createCell(6);
				cell.setCellValue( "Kdv");
				cell.setCellStyle(satirStyle);

				cell = toplam4.createCell(9);
				cell.setCellValue( label_3.getText());
				cell.setCellStyle(satirStyle2);

				Row toplam5  = sheet.createRow(satir + 11);
				sheet.addMergedRegion(new CellRangeAddress(satir + 11,satir + 11,6,8));
				cell = toplam5.createCell(4);
				cell.setCellValue( "Tevkifat");
				cell.setCellStyle(satirStyle);

				cell = toplam5.createCell(5);
				cell.setCellValue( txttev.getText());
				cell.setCellStyle(satirStyle2);

				cell = toplam5.createCell(6);
				cell.setCellValue( "Tev.Edilen KDV");
				cell.setCellStyle(satirStyle);

				cell = toplam5.createCell(9);
				cell.setCellValue( label_1.getText());
				cell.setCellStyle(satirStyle2);

				Row toplam6  = sheet.createRow(satir + 12);
				sheet.addMergedRegion(new CellRangeAddress(satir + 12,satir + 12,6,8));
				cell = toplam6.createCell(6);
				cell.setCellValue( "Tev.Dah.Top.Tut");
				cell.setCellStyle(satirStyle);

				cell = toplam6.createCell(9);
				cell.setCellValue( label_2.getText());
				cell.setCellStyle(satirStyle2);

				Row toplam7  = sheet.createRow(satir + 13);
				sheet.addMergedRegion(new CellRangeAddress(satir + 13,satir + 13,6,8));
				cell = toplam7.createCell(6);
				cell.setCellValue( "Beyan Edilen KDV");
				cell.setCellStyle(satirStyle);

				cell = toplam7.createCell(9);
				cell.setCellValue( lblNewLabel_20.getText());
				cell.setCellStyle(satirStyle2);

				Row toplam8  = sheet.createRow(satir + 14);
				sheet.addMergedRegion(new CellRangeAddress(satir + 14,satir + 14,6,8));
				cell = toplam8.createCell(6);
				cell.setCellValue( "Tev.Har.Top.Tut");
				cell.setCellStyle(satirStyle);

				cell = toplam8.createCell(9);
				cell.setCellValue(label.getText());
				cell.setCellStyle(satirStyle2_ARA); 

				//
				List<String> uniqueDataList = u_kod_ogren() ;
				int ssatir = satir + 8 ;
				for (int iterator = 0;iterator <= uniqueDataList.size()-1;iterator ++) {
					Row row = sheet.getRow(ssatir);
					if (row == null)
						row = sheet.createRow(ssatir);
					cell = row.createCell(0);
					sheet.addMergedRegion(new CellRangeAddress(ssatir,ssatir,0,1));
					cell.setCellValue(uniqueDataList.get(iterator) + " -" + ker_Access.kod_adi(uniqueDataList.get(iterator)));
					cell.setCellStyle(solaStyle);
					ssatir +=1 ;
				}
				//**********
				for (int i=0; i<= mdl.getColumnCount()-1; i++)
				{
					sheet.autoSizeColumn(i);
				}
				FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile() + "_" + zaman + uzanti));
				workbook.write(out);
				out.close();
			}
			else {
				@SuppressWarnings("resource")
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("Evrak_" + textField.getText());
				XSSFFont headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setColor(IndexedColors.BLUE.getIndex()); 
				XSSFCellStyle headerStyle = workbook.createCellStyle();
				XSSFCellStyle headerSolaStyle = workbook.createCellStyle();
				headerStyle.setFont(headerFont);
				headerStyle.setAlignment(HorizontalAlignment.RIGHT);

				XSSFFont solaFont = workbook.createFont();
				solaFont.setFontName("Arial Narrow");
				solaFont. setFontHeight((short)(10*20));
				XSSFCellStyle solaStyle = workbook.createCellStyle();
				solaStyle.setFont(solaFont);
				solaStyle.setAlignment(HorizontalAlignment.LEFT);

				XSSFFont headerSolaFont = workbook.createFont();
				headerSolaFont.setBold(true);
				headerSolaFont.setColor(IndexedColors.BLUE.getIndex()); 
				headerSolaStyle.setFont(headerSolaFont);
				headerSolaStyle.setAlignment(HorizontalAlignment.LEFT);

				XSSFCellStyle satirStyle = workbook.createCellStyle();
				XSSFCellStyle satirStylemik = workbook.createCellStyle();
				XSSFCellStyle satirStyle3 = workbook.createCellStyle();
				XSSFCellStyle satirStyle2 = workbook.createCellStyle();
				XSSFFont satirFont = workbook.createFont();
				satirFont.setFontName("Arial Narrow");
				satirFont. setFontHeight((short)(10*20));
				satirStyle.setFont(satirFont);
				satirStyle.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle3.setFont(satirFont);
				satirStyle2.setFont(satirFont);
				satirStylemik.setFont(satirFont);
				satirStyle3.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
				satirStyle2.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
				satirStylemik.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0"));
				satirStyle3.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle2.setAlignment(HorizontalAlignment.RIGHT);
				satirStylemik.setAlignment(HorizontalAlignment.RIGHT);
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				XSSFCellStyle acikStyle = workbook.createCellStyle();
				XSSFFont acikFont = workbook.createFont();
				acikFont.setColor(IndexedColors.RED.getIndex()); 
				acikFont.setBold(true);
				acikFont.setFontName("Arial");
				acikFont. setFontHeight((short)(22*20));
				acikStyle.setFont(acikFont);
				acikStyle.setAlignment(HorizontalAlignment.CENTER);

				XSSFCellStyle satirStyle2_ARA = workbook.createCellStyle();
				satirStyle2_ARA.setFont(satirFont);
				satirStyle2_ARA.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
				satirStyle2_ARA.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle2_ARA.setBorderTop(BorderStyle.MEDIUM);
				satirStyle2_ARA.setBorderBottom(BorderStyle.MEDIUM);
				XSSFCellStyle satirStyle3_ARA = workbook.createCellStyle();
				satirStyle3_ARA.setFont(satirFont);
				satirStyle3_ARA.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
				satirStyle3_ARA.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle3_ARA.setBorderTop(BorderStyle.MEDIUM);
				satirStyle3_ARA.setBorderBottom(BorderStyle.MEDIUM);
				XSSFCellStyle satirStylemik_ARA = workbook.createCellStyle();
				satirStylemik_ARA.setFont(satirFont);
				satirStylemik_ARA.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0"));
				satirStylemik_ARA.setBorderTop(BorderStyle.MEDIUM);
				satirStylemik_ARA.setBorderBottom(BorderStyle.MEDIUM);
				satirStylemik_ARA.setAlignment(HorizontalAlignment.RIGHT);
				XSSFCellStyle satirStyleBASLIK = workbook.createCellStyle();
				satirStyleBASLIK.setFont(satirFont);
				satirStyleBASLIK.setBorderTop(BorderStyle.MEDIUM);
				satirStyleBASLIK.setBorderBottom(BorderStyle.MEDIUM);
				satirStyleBASLIK.setAlignment(HorizontalAlignment.LEFT);
				XSSFCellStyle satirStyleBASLIK2 = workbook.createCellStyle();
				satirStyleBASLIK2.setFont(satirFont);
				satirStyleBASLIK2.setBorderTop(BorderStyle.MEDIUM);
				satirStyleBASLIK2.setBorderBottom(BorderStyle.MEDIUM);
				satirStyleBASLIK2.setAlignment(HorizontalAlignment.RIGHT);
				XSSFCellStyle satirStyleTOPTUT = workbook.createCellStyle();
				satirStyleTOPTUT.setFont(satirFont);
				satirStyleTOPTUT.setBorderTop(BorderStyle.MEDIUM);
				satirStyleTOPTUT.setAlignment(HorizontalAlignment.RIGHT);

				Cell cell ;
				Row bosRow = sheet.createRow(1);

				Row satir1 = sheet.createRow(2);
				cell = satir1.createCell(0);
				cell.setCellStyle(solaStyle);
				cell.setCellValue("Evrak No :");

				cell = satir1.createCell(1);
				cell.setCellStyle(solaStyle);
				cell.setCellValue(textField.getText());

				cell = satir1.createCell(9);
				cell.setCellValue(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc));
				cell.setCellStyle(satirStyle);


				Row satir2 = sheet.createRow(3);
				cell = satir2.createCell(0);
				cell.setCellStyle(solaStyle);
				cell.setCellValue("Musteri Kodu:");

				cell = satir2.createCell(1);
				cell.setCellStyle(solaStyle);
				cell.setCellValue(txtcari.getText());

				Row satir3 = sheet.createRow(4);
				sheet.addMergedRegion(new CellRangeAddress(4,4,1,3));


				cell = satir3.createCell(1);
				cell.setCellStyle(solaStyle);
				cell.setCellValue(lblNewLabel_3.getText());


				Row bosRow5 = sheet.createRow(5);

				Row aCIKLAMA = sheet.createRow(6);

				cell = aCIKLAMA.createCell(0);
				cell.setCellStyle(satirStyleBASLIK);
				cell.setCellValue("Barkod");

				cell = aCIKLAMA.createCell(1);
				cell.setCellStyle(satirStyleBASLIK);
				cell.setCellValue("Urun Kodu");

				cell = aCIKLAMA.createCell(2);
				cell.setCellStyle(satirStyleBASLIK);
				cell.setCellValue("Paket_No");

				cell = aCIKLAMA.createCell(3);
				cell.setCellValue("Miktar");
				cell.setCellStyle(satirStyleBASLIK2);

				cell = aCIKLAMA.createCell(4);
				cell.setCellValue("m3");
				cell.setCellStyle(satirStyleBASLIK2);

				cell = aCIKLAMA.createCell(5);
				cell.setCellValue("Paket m3");
				cell.setCellStyle(satirStyleBASLIK2);

				cell = aCIKLAMA.createCell(6);
				cell.setCellValue("Fiat");
				cell.setCellStyle(satirStyleBASLIK2);

				cell = aCIKLAMA.createCell(7);
				cell.setCellValue("Iskonto");
				cell.setCellStyle(satirStyleBASLIK2);

				cell = aCIKLAMA.createCell(8);
				cell.setCellValue("KDV");
				cell.setCellStyle(satirStyleBASLIK2);

				cell = aCIKLAMA.createCell(9);
				cell.setCellValue("Tutar");
				cell.setCellStyle(satirStyleBASLIK2);

				//******************SATIRLAR ***********************************************	
				int satir = 0 ;
				for (int i =0;i< mdl.getRowCount() ;i++)
				{
					if (! mdl.getValueAt(i, 1).equals("") )
					{
						Row satirRow = sheet.createRow(i+7);
						for (int s =0;s<= 10 ;s++)
						{

							if (s == 0 || s == 1 || s == 2)
							{
								cell = satirRow.createCell(s);
								cell.setCellValue( mdl.getValueAt(i,s).toString());
								cell.setCellStyle(solaStyle); 
							}
							else if (s == 3)
							{
								if (  GLOBAL.validCheck(mdl.getValueAt(i,s).toString()) == true )
								{
									cell = satirRow.createCell(s);
									cell.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									cell.setCellStyle(satirStylemik); 
								}
							}
							else if (s == 4 || s == 5)
							{
								if (  GLOBAL.validCheck(mdl.getValueAt(i,s).toString()) == true )
								{
									cell = satirRow.createCell(s);
									cell.setCellValue( Double.parseDouble( mdl.getValueAt(i,s).toString()));
									cell.setCellStyle(satirStyle3); 
								}
							}
							else if (s == 7 || s == 8 || s == 9 ||  s == 10 )
							{

								if (  GLOBAL.validCheck(mdl.getValueAt(i,s).toString()) == true )
								{
									cell = satirRow.createCell(s-1);
									cell.setCellStyle(satirStyle2);
									cell.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
								}
							}

						}
						satir += 1 ;
					}
				}

				Row toplam1  = sheet.createRow(satir + 7);
				cell = toplam1.createCell(3);
				cell.setCellValue( label_8_1.getText());
				cell.setCellStyle(satirStylemik_ARA); 

				cell = toplam1.createCell(4);
				cell.setCellValue(label_8.getText());
				cell.setCellStyle(satirStyle3_ARA); 

				cell = toplam1.createCell(9);
				cell.setCellValue( label_9.getText());
				cell.setCellStyle(satirStyleTOPTUT); 

				cell = toplam1.createCell(8);
				cell.setCellStyle(satirStyleTOPTUT); 

				cell = toplam1.createCell(7);
				cell.setCellStyle(satirStyleTOPTUT); 

				cell = toplam1.createCell(6);
				cell.setCellStyle(satirStyleTOPTUT); 

				cell = toplam1.createCell(5);
				cell.setCellValue(lblPaket.getText());
				cell.setCellStyle(satirStyleTOPTUT); 

				cell = toplam1.createCell(2);
				cell.setCellStyle(satirStyleTOPTUT); 

				cell = toplam1.createCell(1);
				cell.setCellStyle(satirStyleTOPTUT); 

				cell = toplam1.createCell(0);
				cell.setCellStyle(satirStyleTOPTUT); 

				//***********************************************************
				Row toplam2  = sheet.createRow(satir + 8);
				sheet.addMergedRegion(new CellRangeAddress(satir + 8,satir + 8,6,8));
				cell = toplam2.createCell(6);
				cell.setCellValue( "Iskonto");
				cell.setCellStyle(satirStyle);

				cell = toplam2.createCell(9);
				cell.setCellValue( label_6.getText());
				cell.setCellStyle(satirStyle2);

				Row toplam3  = sheet.createRow(satir + 9);
				sheet.addMergedRegion(new CellRangeAddress(satir + 9,satir + 9,6,8));
				cell = toplam3.createCell(6);
				cell.setCellValue( "Iskonto Tutar");
				cell.setCellStyle(satirStyle);

				cell = toplam3.createCell(9);
				cell.setCellValue( label_7.getText());
				cell.setCellStyle(satirStyle2);

				Row toplam4  = sheet.createRow(satir + 10);
				sheet.addMergedRegion(new CellRangeAddress(satir + 10,satir + 10,6,8));
				cell = toplam4.createCell(6);
				cell.setCellValue( "Kdv");
				cell.setCellStyle(satirStyle);

				cell = toplam4.createCell(9);
				cell.setCellValue( label_3.getText());
				cell.setCellStyle(satirStyle2);

				Row toplam5  = sheet.createRow(satir + 11);
				sheet.addMergedRegion(new CellRangeAddress(satir + 11,satir + 11,6,8));
				cell = toplam5.createCell(4);
				cell.setCellValue( "Tevkifat");
				cell.setCellStyle(satirStyle);

				cell = toplam5.createCell(5);
				cell.setCellValue( txttev.getText());
				cell.setCellStyle(satirStyle2);

				cell = toplam5.createCell(6);
				cell.setCellValue( "Tev.Edilen KDV");
				cell.setCellStyle(satirStyle);

				cell = toplam5.createCell(9);
				cell.setCellValue( label_1.getText());
				cell.setCellStyle(satirStyle2);

				Row toplam6  = sheet.createRow(satir + 12);
				sheet.addMergedRegion(new CellRangeAddress(satir + 12,satir + 12,6,8));
				cell = toplam6.createCell(6);
				cell.setCellValue( "Tev.Dah.Top.Tut");
				cell.setCellStyle(satirStyle);

				cell = toplam6.createCell(9);
				cell.setCellValue( label_2.getText());
				cell.setCellStyle(satirStyle2);

				Row toplam7  = sheet.createRow(satir + 13);
				sheet.addMergedRegion(new CellRangeAddress(satir + 13,satir + 13,6,8));
				cell = toplam7.createCell(6);
				cell.setCellValue( "Beyan Edilen KDV");
				cell.setCellStyle(satirStyle);

				cell = toplam7.createCell(9);
				cell.setCellValue( lblNewLabel_20.getText());
				cell.setCellStyle(satirStyle2);

				Row toplam8  = sheet.createRow(satir + 14);
				sheet.addMergedRegion(new CellRangeAddress(satir + 14,satir + 14,6,8));
				cell = toplam8.createCell(6);
				cell.setCellValue( "Tev.Har.Top.Tut");
				cell.setCellStyle(satirStyle);

				cell = toplam8.createCell(9);
				cell.setCellValue(label.getText());
				cell.setCellStyle(satirStyle2_ARA); 

				//
				List<String> uniqueDataList = u_kod_ogren() ;
				int ssatir = satir + 8 ;
				for (int iterator = 0;iterator <= uniqueDataList.size()-1;iterator ++) {
					Row row = sheet.getRow(ssatir);
					if (row == null)
						row = sheet.createRow(ssatir);
					cell = row.createCell(0);
					sheet.addMergedRegion(new CellRangeAddress(ssatir,ssatir,0,1));
					cell.setCellValue(uniqueDataList.get(iterator) + " -" + ker_Access.kod_adi(uniqueDataList.get(iterator)));
					cell.setCellStyle(solaStyle);
					ssatir +=1 ;
				}
				//**********
				for (int i=0; i<= mdl.getColumnCount()-1; i++)
				{
					sheet.autoSizeColumn(i);
				}
				FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile() + "_" + zaman + uzanti));
				workbook.write(out);
				out.close();

			}
			GuiUtil.setWaitCursor(splitPane,false);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO,"Aktarma Islemi Tamamlandi....." );
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	@SuppressWarnings("resource")
	public static void  mail_at()
	{
		try 
		{
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Evrak_" + textField.getText());
			XSSFFont headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex()); 
			XSSFCellStyle headerStyle = workbook.createCellStyle();
			XSSFCellStyle headerSolaStyle = workbook.createCellStyle();
			headerStyle.setFont(headerFont);
			headerStyle.setAlignment(HorizontalAlignment.RIGHT);

			XSSFFont solaFont = workbook.createFont();
			solaFont.setFontName("Arial Narrow");
			solaFont. setFontHeight((short)(10*20));
			XSSFCellStyle solaStyle = workbook.createCellStyle();
			solaStyle.setFont(solaFont);
			solaStyle.setAlignment(HorizontalAlignment.LEFT);

			XSSFFont headerSolaFont = workbook.createFont();
			headerSolaFont.setBold(true);
			headerSolaFont.setColor(IndexedColors.BLUE.getIndex()); 
			headerSolaStyle.setFont(headerSolaFont);
			headerSolaStyle.setAlignment(HorizontalAlignment.LEFT);

			XSSFCellStyle satirStyle = workbook.createCellStyle();
			XSSFCellStyle satirStylemik = workbook.createCellStyle();
			XSSFCellStyle satirStyle3 = workbook.createCellStyle();
			XSSFCellStyle satirStyle2 = workbook.createCellStyle();
			XSSFFont satirFont = workbook.createFont();
			satirFont.setFontName("Arial Narrow");
			satirFont. setFontHeight((short)(10*20));
			satirStyle.setFont(satirFont);
			satirStyle.setAlignment(HorizontalAlignment.RIGHT);
			satirStyle3.setFont(satirFont);
			satirStyle2.setFont(satirFont);
			satirStylemik.setFont(satirFont);
			satirStyle3.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
			satirStyle2.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
			satirStylemik.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0"));
			satirStyle3.setAlignment(HorizontalAlignment.RIGHT);
			satirStyle2.setAlignment(HorizontalAlignment.RIGHT);
			satirStylemik.setAlignment(HorizontalAlignment.RIGHT);
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			XSSFCellStyle acikStyle = workbook.createCellStyle();
			XSSFFont acikFont = workbook.createFont();
			acikFont.setColor(IndexedColors.RED.getIndex()); 
			acikFont.setBold(true);
			acikFont.setFontName("Arial");
			acikFont. setFontHeight((short)(22*20));
			acikStyle.setFont(acikFont);
			acikStyle.setAlignment(HorizontalAlignment.CENTER);

			XSSFCellStyle satirStyle2_ARA = workbook.createCellStyle();
			satirStyle2_ARA.setFont(satirFont);
			satirStyle2_ARA.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
			satirStyle2_ARA.setAlignment(HorizontalAlignment.RIGHT);
			satirStyle2_ARA.setBorderTop(BorderStyle.MEDIUM);
			satirStyle2_ARA.setBorderBottom(BorderStyle.MEDIUM);
			XSSFCellStyle satirStyle3_ARA = workbook.createCellStyle();
			satirStyle3_ARA.setFont(satirFont);
			satirStyle3_ARA.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
			satirStyle3_ARA.setAlignment(HorizontalAlignment.RIGHT);
			satirStyle3_ARA.setBorderTop(BorderStyle.MEDIUM);
			satirStyle3_ARA.setBorderBottom(BorderStyle.MEDIUM);
			XSSFCellStyle satirStylemik_ARA = workbook.createCellStyle();
			satirStylemik_ARA.setFont(satirFont);
			satirStylemik_ARA.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0"));
			satirStylemik_ARA.setBorderTop(BorderStyle.MEDIUM);
			satirStylemik_ARA.setBorderBottom(BorderStyle.MEDIUM);
			satirStylemik_ARA.setAlignment(HorizontalAlignment.RIGHT);
			XSSFCellStyle satirStyleBASLIK = workbook.createCellStyle();
			satirStyleBASLIK.setFont(satirFont);
			satirStyleBASLIK.setBorderTop(BorderStyle.MEDIUM);
			satirStyleBASLIK.setBorderBottom(BorderStyle.MEDIUM);
			satirStyleBASLIK.setAlignment(HorizontalAlignment.LEFT);
			XSSFCellStyle satirStyleBASLIK2 = workbook.createCellStyle();
			satirStyleBASLIK2.setFont(satirFont);
			satirStyleBASLIK2.setBorderTop(BorderStyle.MEDIUM);
			satirStyleBASLIK2.setBorderBottom(BorderStyle.MEDIUM);
			satirStyleBASLIK2.setAlignment(HorizontalAlignment.RIGHT);
			XSSFCellStyle satirStyleTOPTUT = workbook.createCellStyle();
			satirStyleTOPTUT.setFont(satirFont);
			satirStyleTOPTUT.setBorderTop(BorderStyle.MEDIUM);
			satirStyleTOPTUT.setAlignment(HorizontalAlignment.RIGHT);

			Cell cell ;
			Row bosRow = sheet.createRow(1);

			Row satir1 = sheet.createRow(2);
			cell = satir1.createCell(0);
			cell.setCellStyle(solaStyle);
			cell.setCellValue("Evrak No :");

			cell = satir1.createCell(1);
			cell.setCellStyle(solaStyle);
			cell.setCellValue(textField.getText());

			cell = satir1.createCell(9);
			cell.setCellValue(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc));
			cell.setCellStyle(satirStyle);


			Row satir2 = sheet.createRow(3);
			cell = satir2.createCell(0);
			cell.setCellStyle(solaStyle);
			cell.setCellValue("Musteri Kodu:");

			cell = satir2.createCell(1);
			cell.setCellStyle(solaStyle);
			cell.setCellValue(txtcari.getText());

			Row satir3 = sheet.createRow(4);
			sheet.addMergedRegion(new CellRangeAddress(4,4,1,3));


			cell = satir3.createCell(1);
			cell.setCellStyle(solaStyle);
			cell.setCellValue(lblNewLabel_3.getText());


			Row bosRow5 = sheet.createRow(5);

			Row aCIKLAMA = sheet.createRow(6);

			cell = aCIKLAMA.createCell(0);
			cell.setCellStyle(satirStyleBASLIK);
			cell.setCellValue("Barkod");

			cell = aCIKLAMA.createCell(1);
			cell.setCellStyle(satirStyleBASLIK);
			cell.setCellValue("Urun Kodu");

			cell = aCIKLAMA.createCell(2);
			cell.setCellStyle(satirStyleBASLIK);
			cell.setCellValue("Paket_No");

			cell = aCIKLAMA.createCell(3);
			cell.setCellValue("Miktar");
			cell.setCellStyle(satirStyleBASLIK2);

			cell = aCIKLAMA.createCell(4);
			cell.setCellValue("m3");
			cell.setCellStyle(satirStyleBASLIK2);

			cell = aCIKLAMA.createCell(5);
			cell.setCellValue("Paket m3");
			cell.setCellStyle(satirStyleBASLIK2);

			cell = aCIKLAMA.createCell(6);
			cell.setCellValue("Fiat");
			cell.setCellStyle(satirStyleBASLIK2);

			cell = aCIKLAMA.createCell(7);
			cell.setCellValue("Iskonto");
			cell.setCellStyle(satirStyleBASLIK2);

			cell = aCIKLAMA.createCell(8);
			cell.setCellValue("KDV");
			cell.setCellStyle(satirStyleBASLIK2);

			cell = aCIKLAMA.createCell(9);
			cell.setCellValue("Tutar");
			cell.setCellStyle(satirStyleBASLIK2);

			//******************SATIRLAR ***********************************************	
			int satir = 0 ;
			for (int i =0;i< mdl.getRowCount() ;i++)
			{
				if (! mdl.getValueAt(i, 1).equals("") )
				{
					Row satirRow = sheet.createRow(i+7);
					for (int s =0;s<= 10 ;s++)
					{

						if (s == 0 || s == 1 || s == 2)
						{
							cell = satirRow.createCell(s);
							cell.setCellValue( mdl.getValueAt(i,s).toString());
							cell.setCellStyle(solaStyle); 
						}
						else if (s == 3)
						{
							if (  GLOBAL.validCheck(mdl.getValueAt(i,s).toString()) == true )
							{
								cell = satirRow.createCell(s);
								cell.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
								cell.setCellStyle(satirStylemik); 
							}
						}
						else if (s == 4 || s == 5)
						{
							if (  GLOBAL.validCheck(mdl.getValueAt(i,s).toString()) == true )
							{
								cell = satirRow.createCell(s);
								cell.setCellValue( Double.parseDouble( mdl.getValueAt(i,s).toString()));
								cell.setCellStyle(satirStyle3); 
							}
						}
						else if (s == 7 || s == 8 || s == 9 ||  s == 10 )
						{

							if (  GLOBAL.validCheck(mdl.getValueAt(i,s).toString()) == true )
							{
								cell = satirRow.createCell(s-1);
								cell.setCellStyle(satirStyle2);
								cell.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
							}
						}

					}
					satir += 1 ;
				}
			}

			Row toplam1  = sheet.createRow(satir + 7);
			cell = toplam1.createCell(3);
			cell.setCellValue( label_8_1.getText());
			cell.setCellStyle(satirStylemik_ARA); 

			cell = toplam1.createCell(4);
			cell.setCellValue(label_8.getText());
			cell.setCellStyle(satirStyle3_ARA); 

			cell = toplam1.createCell(9);
			cell.setCellValue( label_9.getText());
			cell.setCellStyle(satirStyleTOPTUT); 

			cell = toplam1.createCell(8);
			cell.setCellStyle(satirStyleTOPTUT); 

			cell = toplam1.createCell(7);
			cell.setCellStyle(satirStyleTOPTUT); 

			cell = toplam1.createCell(6);
			cell.setCellStyle(satirStyleTOPTUT); 

			cell = toplam1.createCell(5);
			cell.setCellValue(lblPaket.getText());
			cell.setCellStyle(satirStyleTOPTUT); 

			cell = toplam1.createCell(2);
			cell.setCellStyle(satirStyleTOPTUT); 

			cell = toplam1.createCell(1);
			cell.setCellStyle(satirStyleTOPTUT); 

			cell = toplam1.createCell(0);
			cell.setCellStyle(satirStyleTOPTUT); 

			//***********************************************************
			Row toplam2  = sheet.createRow(satir + 8);
			sheet.addMergedRegion(new CellRangeAddress(satir + 8,satir + 8,6,8));
			cell = toplam2.createCell(6);
			cell.setCellValue( "Iskonto");
			cell.setCellStyle(satirStyle);

			cell = toplam2.createCell(9);
			cell.setCellValue( label_6.getText());
			cell.setCellStyle(satirStyle2);

			Row toplam3  = sheet.createRow(satir + 9);
			sheet.addMergedRegion(new CellRangeAddress(satir + 9,satir + 9,6,8));
			cell = toplam3.createCell(6);
			cell.setCellValue( "Iskonto Tutar");
			cell.setCellStyle(satirStyle);

			cell = toplam3.createCell(9);
			cell.setCellValue( label_7.getText());
			cell.setCellStyle(satirStyle2);

			Row toplam4  = sheet.createRow(satir + 10);
			sheet.addMergedRegion(new CellRangeAddress(satir + 10,satir + 10,6,8));
			cell = toplam4.createCell(6);
			cell.setCellValue( "Kdv");
			cell.setCellStyle(satirStyle);

			cell = toplam4.createCell(9);
			cell.setCellValue( label_3.getText());
			cell.setCellStyle(satirStyle2);

			Row toplam5  = sheet.createRow(satir + 11);
			sheet.addMergedRegion(new CellRangeAddress(satir + 11,satir + 11,6,8));
			cell = toplam5.createCell(4);
			cell.setCellValue( "Tevkifat");
			cell.setCellStyle(satirStyle);

			cell = toplam5.createCell(5);
			cell.setCellValue( txttev.getText());
			cell.setCellStyle(satirStyle2);

			cell = toplam5.createCell(6);
			cell.setCellValue( "Tev.Edilen KDV");
			cell.setCellStyle(satirStyle);

			cell = toplam5.createCell(9);
			cell.setCellValue( label_1.getText());
			cell.setCellStyle(satirStyle2);

			Row toplam6  = sheet.createRow(satir + 12);
			sheet.addMergedRegion(new CellRangeAddress(satir + 12,satir + 12,6,8));
			cell = toplam6.createCell(6);
			cell.setCellValue( "Tev.Dah.Top.Tut");
			cell.setCellStyle(satirStyle);

			cell = toplam6.createCell(9);
			cell.setCellValue( label_2.getText());
			cell.setCellStyle(satirStyle2);

			Row toplam7  = sheet.createRow(satir + 13);
			sheet.addMergedRegion(new CellRangeAddress(satir + 13,satir + 13,6,8));
			cell = toplam7.createCell(6);
			cell.setCellValue( "Beyan Edilen KDV");
			cell.setCellStyle(satirStyle);

			cell = toplam7.createCell(9);
			cell.setCellValue( lblNewLabel_20.getText());
			cell.setCellStyle(satirStyle2);

			Row toplam8  = sheet.createRow(satir + 14);
			sheet.addMergedRegion(new CellRangeAddress(satir + 14,satir + 14,6,8));
			cell = toplam8.createCell(6);
			cell.setCellValue( "Tev.Har.Top.Tut");
			cell.setCellStyle(satirStyle);

			cell = toplam8.createCell(9);
			cell.setCellValue(label.getText());
			cell.setCellStyle(satirStyle2_ARA); 

			List<String> uniqueDataList = u_kod_ogren() ;
			int ssatir = satir + 8 ;
			for (int iterator = 0;iterator <= uniqueDataList.size()-1;iterator ++) {
				Row row = sheet.getRow(ssatir);
				if (row == null)
					row = sheet.createRow(ssatir);
				cell = row.createCell(0);
				sheet.addMergedRegion(new CellRangeAddress(ssatir,ssatir,0,1));
				cell.setCellValue(uniqueDataList.get(iterator) + " -" + ker_Access.kod_adi(uniqueDataList.get(iterator)));
				cell.setCellStyle(solaStyle);
				ssatir +=1 ;
			}
			for (int i=0; i<= mdl.getColumnCount()-1; i++)
			{
				sheet.autoSizeColumn(i);
			}
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(bos);
			byte[] byteArray= bos.toByteArray();
			InputStream in = new ByteArrayInputStream(byteArray);
			oac.ds = new ByteArrayDataSource(in, "application/x-any");
			bos.close();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private static List<String> u_kod_ogren()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		List<String> list = new ArrayList<String>();  
		for (int i =0;i< mdl.getRowCount() ;i++)
		{
			if (! mdl.getValueAt(i, 1).equals("") )
			{
				list.add( mdl.getValueAt(i,1).toString().substring(0, 2));	//;
			}
		}
		List<String> uniqueDataList = list.stream().distinct().collect(Collectors.toList());
		return uniqueDataList ;
	}
	private void mWAIT()
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
		GuiUtil.setWaitCursor(splitPane,true);
		GuiUtil.setWaitCursor(textField,true);
		GuiUtil.setWaitCursor(txtcari,true);
		GuiUtil.setWaitCursor(txtadres,true);
	}
	private void mDEFAULT()
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		GuiUtil.setWaitCursor(splitPane,false);
		GuiUtil.setWaitCursor(textField,false);
		GuiUtil.setWaitCursor(txtcari,false);
		GuiUtil.setWaitCursor(txtadres,false);
	}
	class PathCellRenderer extends DefaultTableCellRenderer {
	    public Component getTableCellRendererComponent(
	                        JTable table, Object value,
	                        boolean isSelected, boolean hasFocus,
	                        int row, int column) {
	        JLabel c = (JLabel)super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column) ;
	        String pathValue = "Cift Tiklamada otomatik Paket No verilir...Once Konsimento Degeri girilmelidir   "; 
	        setToolTipText(pathValue);
	        setVerticalAlignment(JLabel.BOTTOM);
	        return c;
	    }
	}
}

//		ComboBoxEditor jeditor = cmbozkod.getEditor();
//JTextField textField = (JTextField)jeditor.getEditorComponent();
//textField.setDocument(new JTextFieldLimit(15));



//     System.out.println(satir +" = " + sonpaketno+ "==" +  sonsinif + "-" +sonkalinlik + "-" + sonboy + "-" + songenislik);
//		mdl.insertRow(satir, new Object[]{"",sonsinif + "-" +sonkalinlik + "-" + sonboy + "-" + songenislik,"",0.00,0.000,"","","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,"","",0,""});

// 	mdl.insertRow(satir,new Object[]{
//			"",sonsinif + "-" +sonkalinlik + "-" + sonboy + "-" + songenislik ,
//			sonpaketno,sonadet, 
//			m3(sonsinif + "-" +sonkalinlik + "-" + sonboy + "-" + songenislik,sonadet),
//			"" ,	konsimento,	0, // depo
//			0,0,0,0,"Izahat","",
//			"1900-01-01 00:00:00.0", // ctar
//			0,"",0,	0,	0,	"",	"",	0,	0,	0,	0,	0,	"",	"",	"",	""});

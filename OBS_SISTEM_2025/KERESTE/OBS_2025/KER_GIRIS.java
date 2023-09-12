package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

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
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.Degisken;
import OBS_C_2025.DoubleEditor;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.KERESTE_KOD_KONTROL;
import OBS_C_2025.KER_BILGI;
import OBS_C_2025.NextCellActioin;
import OBS_C_2025.Next_Cell_Kereste;
import OBS_C_2025.SAGA;
import OBS_C_2025.SAGA_BOLD;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.U_KODU_RENDERER;
import OBS_C_2025.dEKONT_BILGI;
import OBS_C_2025.lOG_BILGI;

@SuppressWarnings({"serial","static-access","unused"})
public class KER_GIRIS extends JInternalFrame {
	private static JSplitPane splitPane ;
	private static JTextField textField;
	private static JTextField txtcari;
	private static JTextField txtadres;
	private static JTextField txtdoviz;
	private static JTextField textField_5;
	private static JTextField textField_6;
	private static JTextField textField_7;
	private static JTextField textField_8;
	private static JTextField textField_9;
	private static JTextField textField_10;
	
	private static JFormattedTextField txtkur ;
	private static JFormattedTextField txttev ;
	
	private static JComboBox<String> cmbanagrup ;
	private static JComboBox<String> cmbaltgrup ;
	private static JComboBox<String> cmbozkod ;
	private static JComboBox<String> cmbnakliyeci ;
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
	private static JLabel lblNewLabel_17;
	private static JLabel lblNewLabel_13;
	private JLabel lblkodAciklama ;
	private JLabel lblkONSIMENTO ;
	private ArrayList<String> listdepo = null ;
	
	
	private static JTabbedPane tabbedPane ;
	private static JTable table;
	
	private static  String tar = "" ;
	private static boolean yeni_fat = false;
	private JFileChooser chooser;
	private static boolean dOSYADAN = false;
	
	private static long startTimeG ;
	
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KERESTE_ACCESS ker_Access = new KERESTE_ACCESS(oac._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(oac._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	static CARI_ACCESS c_Access = new CARI_ACCESS(oac._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KER_GIRIS frame = new KER_GIRIS();
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
	public KER_GIRIS() {
		setTitle("KERESTE GIRIS");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,1185,800);
		
		splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 120));
		panel.setMaximumSize(new Dimension(0, 120));
		panel.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setForeground(new Color(0, 0, 128));
		tabbedPane_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(tabbedPane_1, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_1.addTab("Urun Giris", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Evrak No");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 11, 69, 14);
		panel_2.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setDocument(new JTextFieldLimit(10));
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
						JOptionPane.showMessageDialog(null,  "Kereste Numaralarinda onceden harf ve rakkam kullanildigindan otomatik numara verilemez...."); 	
					}
				}
			}
		});
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				if (! textField.getText().toString().equals("")) {
					fiskont();
				}
				else {
					GRID_TEMIZLE.grid_temizle(table);
					sifirla();
				}
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void removeUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				if (! textField.getText().toString().equals("")) {
					fiskont();
				}
				else {
					GRID_TEMIZLE.grid_temizle(table);
					sifirla();
				}
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void insertUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				if (! textField.getText().toString().equals("")) {
					fiskont();
				}
				else {
					GRID_TEMIZLE.grid_temizle(table);
					sifirla();
				}
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
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
		textField.setBounds(89, 8, 110, 20);
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
		button_1.setBounds(209, 7, 26, 23);
		panel_2.add(button_1);

		JLabel lblNewLabel_2 = new JLabel("Cari Hesap");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 36, 69, 14);
		panel_2.add(lblNewLabel_2);

		txtcari = new JTextField();

		txtcari.setDocument(new JTextFieldLimit(12));
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
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						if (! oac.hsp_hsp_kodu.equals(""))
						{
							txtcari.setText( oac.hsp_hsp_kodu);
						}
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		txtcari.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtcari.setBounds(89, 33, 110, 20);
		panel_2.add(txtcari);
		txtcari.setColumns(10);

		lblNewLabel_3 = new JLabel(".....");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setForeground(new Color(25, 25, 112));
		lblNewLabel_3.setBounds(89, 62, 262, 14);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Tarih");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(300, 11, 46, 14);
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
		dtc.setBounds(353, 8, 125, 20);
		dtc.setDateFormatString("dd.MM.yyyy");
		dtc.setFont(new Font("Tahoma", Font.BOLD, 12));
		dtc.setDate(new Date());
		panel_2.add(dtc);

		JLabel lblNewLabel_5 = new JLabel("Adres");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(300, 36, 46, 14);
		panel_2.add(lblNewLabel_5);

		txtadres = new JTextField();
		txtadres.setDocument(new JTextFieldLimit(12));
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
					JOptionPane.showMessageDialog(null, ex.getMessage(),  "Adres Hesap  Okuma", JOptionPane.ERROR_MESSAGE);   
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
					JOptionPane.showMessageDialog(null, ex.getMessage(),  "Adres Hesap  Okuma", JOptionPane.ERROR_MESSAGE);   
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
					JOptionPane.showMessageDialog(null, ex.getMessage(),  "Adres Hesap  Okuma", JOptionPane.ERROR_MESSAGE);   
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
		txtadres.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtadres.setBounds(353, 33, 125, 20);
		panel_2.add(txtadres);
		txtadres.setColumns(10);

		lblNewLabel_6 = new JLabel(".....");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setForeground(new Color(139, 0, 0));
		lblNewLabel_6.setBounds(353, 62, 237, 14);
		panel_2.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Ozel Kod");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(490, 11, 61, 14);
		panel_2.add(lblNewLabel_7);

		cmbozkod = new JComboBox<String>();
		cmbozkod.setBounds(560, 7, 156, 22);
		cmbozkod.setForeground(new Color(0, 0, 128));
		cmbozkod.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_2.add(cmbozkod);

		JLabel lblAnaGrup = new JLabel("Ana Grup");
		lblAnaGrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAnaGrup.setBounds(726, 10, 61, 14);
		panel_2.add(lblAnaGrup);

		cmbanagrup = new JComboBox<String>();
		cmbanagrup.setForeground(new Color(0, 0, 128));
		cmbanagrup.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbanagrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur();
			}
		});
		cmbanagrup.setBounds(797, 7, 148, 22);
		panel_2.add(cmbanagrup);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ana_grup_doldur();
			}
		});
		button.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		button.setToolTipText("Yenile");
		button.setBounds(955, 7, 26, 23);
		panel_2.add(button);

		JLabel lblAltGrup = new JLabel("Alt Grup");
		lblAltGrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAltGrup.setBounds(726, 35, 69, 14);
		panel_2.add(lblAltGrup);

		cmbaltgrup = new JComboBox<String>();
		cmbaltgrup.setForeground(new Color(0, 0, 128));
		cmbaltgrup.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbaltgrup.setBounds(797, 33, 148, 22);
		panel_2.add(cmbaltgrup);

		JLabel lblNewLabel_9 = new JLabel("Doviz");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_9.setBounds(995, 10, 46, 14);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Kur");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(995, 36, 46, 14);
		panel_2.add(lblNewLabel_10);

		txtdoviz = new JTextField();
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
		lblNewLabel_7_1.setBounds(490, 37, 61, 14);
		panel_2.add(lblNewLabel_7_1);
		
		cmbnakliyeci = new JComboBox<String>();
		cmbnakliyeci.setBounds(560, 33, 156, 22);
		panel_2.add(cmbnakliyeci);
		

		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_1.addTab("Yazici Bilgileri", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel lblNewLabel_14 = new JLabel("Not 1");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_14.setBounds(10, 11, 46, 14);
		panel_4.add(lblNewLabel_14);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_5.setBounds(81, 8, 312, 20);
		textField_5.setDocument(new JTextFieldLimit(40));
		panel_4.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_6.setBounds(81, 35, 312, 20);
		textField_6.setDocument(new JTextFieldLimit(40));
		panel_4.add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
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

		

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Gidecegi Yer Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));

		panel_7.setBounds(710, 5, 257, 70);
		panel_4.add(panel_7);
		panel_7.setLayout(null);

		textField_8 = new JTextField();
		textField_8.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				String sonuc = "";

				try {
					sonuc =a_Access.kod_ismi(textField_8.getText());
				} catch (Exception ex) {		}
				if ( ! sonuc.equals("") )
				{
					lblNewLabel_17.setText(sonuc);
				}
				else
				{
					lblNewLabel_17.setText("");
				}
			}
			public void removeUpdate(DocumentEvent e) {
				String sonuc = "";

				try {
					sonuc = a_Access.kod_ismi(textField_8.getText());
				} catch (Exception ex) {		}


				if ( ! sonuc.equals("") )
				{
					lblNewLabel_17.setText(sonuc);
				}
				else
				{
					lblNewLabel_17.setText("");
				}
			}
			public void insertUpdate(DocumentEvent e) {
				String sonuc = "";

				try {
					sonuc = a_Access.kod_ismi(textField_8.getText());
				} catch (Exception ex) {		}


				if ( ! sonuc.equals("") )
				{
					lblNewLabel_17.setText(sonuc);
				}
				else
				{
					lblNewLabel_17.setText("");
				}
			}
		});
		textField_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					ADRES_LISTE asp ;
					asp = new ADRES_LISTE();
					asp.setVisible(true);
					textField_8.setText( oac.hsp_hsp_kodu);
				}
			}
		});
		textField_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_8.setBounds(10, 20, 149, 20);
		panel_7.add(textField_8);
		textField_8.setColumns(10);

		lblNewLabel_17 = new JLabel(".....");
		lblNewLabel_17.setForeground(new Color(0, 0, 139));
		lblNewLabel_17.setBounds(10, 45, 237, 14);
		panel_7.add(lblNewLabel_17);

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setToolTipText("Gidecegi Yer Bilgileri");
		btnNewButton_4.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-truck-16.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean varmi = OBS_MAIN.pencere_bak("GIDECEGI YER");
				if (varmi  ) 
				{
					try {
						OBS_MAIN.pencere_aktiv_yap("GIDECEGI YER");
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
					return;
				}
				else
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
					JInternalFrame internalFrame;
					GLOBAL.nerden = "ker" ;
					internalFrame  = new GIDECEGI_YER();
					OBS_MAIN.desktopPane.add(internalFrame);
					internalFrame.setVisible(true);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
				}
			}
		});
		btnNewButton_4.setBounds(169, 18, 25, 23);
		panel_7.add(btnNewButton_4);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 191, 255)));
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

		textField_9 = new JTextField();
		textField_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_9.setBounds(54, 8, 447, 20);
		textField_9.setDocument(new JTextFieldLimit(50));
		panel_5.add(textField_9);
		textField_9.setColumns(10);

		textField_10 = new JTextField();
		textField_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_10.setBounds(54, 33, 447, 20);
		textField_10.setDocument(new JTextFieldLimit(50));
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
		panel_3.setMinimumSize(new Dimension(0, 140));
		panel_3.setMaximumSize(new Dimension(0, 140));
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setDividerSize(0);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_3.add(splitPane_3);
		
		JPanel panel_71 = new JPanel();
		panel_71.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_71.setBounds(2, 1, 1158, 21);
		panel_71.setMinimumSize(new Dimension(0, 25));
		panel_71.setMaximumSize(new Dimension(0,25));
		panel_71.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Satir Sayisi :");
		lblNewLabel.setBounds(32, 5, 85, 14);
		panel_71.add(lblNewLabel);

		lblNewLabel_13 = new JLabel("0");
		lblNewLabel_13.setForeground(new Color(0, 0, 128));
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_13.setBounds(122, 5, 40, 14);
		panel_71.add(lblNewLabel_13);

		label_8 = new JLabel("0.000");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setForeground(new Color(139, 0, 0));
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(400, 5, 77, 14);
		panel_71.add(label_8);

		label_9 = new JLabel("0.00");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setForeground(new Color(139, 0, 0));
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(956, 5, 102, 14);
		panel_71.add(label_9);
		splitPane_3.setLeftComponent(panel_71);
		
		lblPaket = new JLabel("0");
		lblPaket.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaket.setForeground(new Color(139, 0, 0));
		lblPaket.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPaket.setBounds(484, 5, 66, 14);
		panel_71.add(lblPaket);
		
		label_8_1 = new JLabel("0");
		label_8_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8_1.setForeground(new Color(139, 0, 0));
		label_8_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8_1.setBounds(315, 5, 85, 14);
		panel_71.add(label_8_1);
		
		JLabel lblNewLabel_8 = new JLabel("Paket");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(560, 5, 48, 14);
		panel_71.add(lblNewLabel_8);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(2, 22, 1158, 100);
		splitPane_3.setRightComponent(tabbedPane_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_2.addTab("Toplamlar", null, panel_1, null);
		panel_1.setLayout(null);
		
		lblkodAciklama = new JLabel();
		lblkodAciklama.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblkodAciklama.setForeground(new Color(0, 0, 128));
		lblkodAciklama.setBounds(10, 55,50, 14);
		panel_1.add(lblkodAciklama);
		
		lblkONSIMENTO = new JLabel();
		lblkONSIMENTO.setForeground(new Color(0, 0, 128));
		lblkONSIMENTO.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblkONSIMENTO.setBounds(10, 35, 50, 14);
		panel_1.add(lblkONSIMENTO);
		
		JLabel lblIskonto = new JLabel("Iskonto");
		lblIskonto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblIskonto.setBounds(247, 11, 62, 14);
		panel_1.add(lblIskonto);
		
		label_6 = new JLabel("0.00");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setForeground(Color.BLUE);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_6.setBounds(306, 11, 95, 14);
		panel_1.add(label_6);
		
		JLabel lblKdv = new JLabel("K.D.V.");
		lblKdv.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblKdv.setBounds(415, 11, 52, 14);
		panel_1.add(lblKdv);
		
		label_3 = new JLabel("0.00");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setForeground(Color.BLUE);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(480, 11, 99, 14);
		panel_1.add(label_3);
		
		JLabel lblBakiye = new JLabel("Bakiye");
		lblBakiye.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBakiye.setBounds(247, 39, 66, 14);
		panel_1.add(lblBakiye);
		
		label_7 = new JLabel("0.00");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setForeground(Color.BLUE);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_7.setBounds(306, 39, 95, 14);
		panel_1.add(label_7);
		
		JLabel lblTevkifatOrani = new JLabel("Tevkifat Orani");
		lblTevkifatOrani.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTevkifatOrani.setBounds(415, 39, 82, 14);
		panel_1.add(lblTevkifatOrani);
		
		txttev = new JFormattedTextField();
		txttev.setText("0");
		txttev.setHorizontalAlignment(SwingConstants.RIGHT);
		txttev.setFont(new Font("Tahoma", Font.BOLD, 12));
		txttev.setBackground(Color.CYAN);
		txttev.setBounds(518, 35, 62, 20);
		txttev.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				if (txttev.getText().equals("")) return ;
				toplam();
			}
			public void removeUpdate(DocumentEvent e) {
				if (txttev.getText().equals("")) return ;
				toplam();
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
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(705, 39, 99, 14);
		panel_1.add(label_2);
		
		label_1 = new JLabel("0.00");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(705, 11, 99, 14);
		panel_1.add(label_1);
		
		JLabel lblNewLabel_21 = new JLabel("Beyan Edilen KDV");
		lblNewLabel_21.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_21.setBounds(828, 11, 109, 14);
		panel_1.add(lblNewLabel_21);
		
		lblNewLabel_20 = new JLabel("0.00");
		lblNewLabel_20.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_20.setForeground(Color.BLUE);
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_20.setBounds(927, 11, 109, 14);
		panel_1.add(lblNewLabel_20);
		
		JLabel lblNewLabel_22 = new JLabel("Tev.Har.Top.Tutar");
		lblNewLabel_22.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_22.setBounds(828, 39, 109, 14);
		panel_1.add(lblNewLabel_22);
		
		label = new JLabel("0.00");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(927, 39, 109, 14);
		panel_1.add(label);
		
		//** Sol Toolbar *****************************************************************
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setDividerSize(0);
		splitPane_1.setLeftComponent(splitPane_2);
		
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		toolBar_1.setMinimumSize(new Dimension(30, 0));
		toolBar_1.setMaximumSize(new Dimension(30, 0));
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		splitPane_2.setLeftComponent(toolBar_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() < 0 ) return ;
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-view-16.png")));
		toolBar_1.add(btnNewButton);

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
					if (table.getSelectedRow() < 0 ) return ;
					satir_sil();
					DefaultTableModel mdll = (DefaultTableModel) table.getModel();
					mdll.addRow(new Object[]{"","","",0.00,0.000,"","","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,0,"",0,""});
					paketm3();
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
					dosya_oku();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3_1.setIcon(new ImageIcon(KER_GIRIS.class.getResource("/ICONLAR/excel-icon_16.png")));
		toolBar_1.add(btnNewButton_3_1);
		//////////////////////////////ARA BOLUM********************************
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(0, 0, 128));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		splitPane_2.setRightComponent(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();

		tabbedPane.addTab("Kereste", null, scrollPane, null);

		DefaultTableModel model = new DefaultTableModel() ; 
		table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {  
				switch (column) {
				case 5:
					return false;
				case 11:
					return false;
				default:
					return true;
				}
			}
			public void changeSelection(final int row, final int column, boolean toggle, boolean extend)
			{
				super.changeSelection(row, column, toggle, extend);
				if (column == 1)
				{
					//table.editCellAt(row, column);
					//table.transferFocus();
				}
			}	
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				try {
					kod_ADI(  model.getValueAt(table.getSelectedRow(), 1).toString(), model.getValueAt(table.getSelectedRow(), 6).toString());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10)
				{
					if (table.isEditing())
						table.getCellEditor().stopCellEditing();
				}
				if (e.getKeyCode() == 127)
				{
					satir_sil();
				}
			}
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					if (model.getRowCount() == 0) return ;
					if (table.getSelectedRow()  < 0) return;
					
				}
			}
		});
		table.setGridColor(oac.gridcolor);
		table.setCellSelectionEnabled(true);
		model.addColumn("Barkod", new String []{""});
		model.addColumn("Urun Kodu", new String []{""});
		model.addColumn("Paket_No", new String []{""});
		model.addColumn("Miktar", new Double [] {( 0.000 )});
		model.addColumn("M3", new Double [] {( 0.000 )});
		model.addColumn("Paket_M3", new String []{""});
		model.addColumn("Kons.", new String []{""});
		model.addColumn("Depo", new String []{""});
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
		TableColumn col ;
//{"","","",0.00,0.000,"","","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,"","",0,""}		
		col = table.getColumnModel().getColumn(0);
		col.setMinWidth(100);
		col.setHeaderRenderer(new SOLA());
		
		//******************************************************************KODU *********************************
		JFormattedTextField ftext = new JFormattedTextField();
		ftext.setFont(new Font(table.getFont().getFontName(),1 ,12));
		ftext.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				//System.out.println(ftext.getText());
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void removeUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				//System.out.println(ftext.getText());
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void insertUpdate(DocumentEvent e) {
				String[] token = ftext.getText().split("-");
				try {
					if (table.getSelectedRow() == -1 ) return ;
					getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
					kod_ADI(ftext.getText(), model.getValueAt(table.getSelectedRow(), 6).toString());
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					double m3 = 0 ;
					double miktar =  Double.parseDouble(model.getValueAt(table.getSelectedRow(), 3).toString());
					if (! token[1].toString().trim().isEmpty() && ! token[2].toString().trim().isEmpty() && ! token[3].toString().trim().isEmpty()) {
						m3 = ((Double.parseDouble(token[1].toString().trim()) * Double.parseDouble(token[2].toString().trim()) * Double.parseDouble(token[3].toString().trim() )) * miktar)/1000000000 ;
					}
					model.setValueAt(  m3,table.getSelectedRow(), 4)  ;
					dOSYADAN = false ;
					toplam();
				//	dOSYADAN = true ;
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,  ex.getMessage(),  "KOD ACIKLAMA", JOptionPane.ERROR_MESSAGE); 
				}
				
				getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
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
		    mask = new MaskFormatter("##-###-####-####");
		    mask.install(ftext);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		col = table.getColumnModel().getColumn(1);
		col.setCellRenderer(new U_KODU_RENDERER());
		col.setMinWidth(120);
		col.setCellEditor(new DefaultCellEditor(ftext));
		col.setHeaderRenderer(new SOLA());
		
		col = table.getColumnModel().getColumn(2);
		col.setMinWidth(65);
		col.setHeaderRenderer(new SOLA());
		
		col = table.getColumnModel().getColumn(3);
		col.setMinWidth(50);
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
		//col.setCellEditor( new DoubleEditor(3) );
		col.setHeaderRenderer(new SAGA());
		
		col = table.getColumnModel().getColumn(6);
		col.setMinWidth(75);
		col.setHeaderRenderer(new SOLA());
		
		col = table.getColumnModel().getColumn(7);
		listdepo = new ArrayList<String> () ;
		depo_auto();
		Java2sAutoComboBox combodp = new Java2sAutoComboBox( listdepo,"kereste");
		combodp.setDataList(listdepo);
		col.setCellEditor(new DefaultCellEditor(combodp));
		col.setMinWidth(100);
		col.setHeaderRenderer(new SOLA());
		
		col = table.getColumnModel().getColumn(8);
		col.setMinWidth(75);
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setHeaderRenderer(new SAGA());
		
		col = table.getColumnModel().getColumn(9);
		col.setMinWidth(50);
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setHeaderRenderer(new SAGA());
		
		
		col = table.getColumnModel().getColumn(10);
		col.setMinWidth(30);
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setHeaderRenderer(new SAGA());
		
		col = table.getColumnModel().getColumn(11);
		col.setMinWidth(100);
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,true));
		col.setHeaderRenderer(new SAGA());
		
		col = table.getColumnModel().getColumn(12);
		col.setMinWidth(175);
		JTextField atf = new JTextField(40);
		col.setCellEditor(new DefaultCellEditor(atf));
		col.setHeaderRenderer(new SOLA());
		
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
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kereste Giris", JOptionPane.ERROR_MESSAGE);   
		}
		table.getModel().addTableModelListener(	(TableModelListener) new TableModelListener() 
		{		
			@Override
			public void tableChanged(TableModelEvent e) {
				TableModel model = (TableModel)e.getSource();
				if (model.getRowCount() > 0) 
				{
					int row;
					row = table.getSelectedRow();     //e.getFirstRow();
					int column = e.getColumn();
					if (column == 1)  //Paket
					{
						paketm3();
					}
					if (column == 2)  //Paket
					{
						paketm3();
					}
					if (column == 3)  //MIKTAR
					{
						Double m3 = 0.00 ;
						if (! model.getValueAt(row, 1).toString().equals(""))
						{
							String[] token = model.getValueAt(row, 1).toString().split("-");
							Double miktar =  Double.parseDouble(model.getValueAt(table.getSelectedRow(), 3).toString());
							m3 = ((Double.parseDouble(token[1]) * Double.parseDouble(token[2] ) * Double.parseDouble(token[3] )) * miktar) /1000000000;
						}
						model.setValueAt(  m3,table.getSelectedRow(), 4)  ;
						double fiat = 0 ;
						fiat =  Double.parseDouble(model.getValueAt(row, 8).toString());
						m3 = Double.parseDouble(model.getValueAt(row, 4).toString());
						model.setValueAt( fiat * m3,row, 11)  ;
						paketm3();
						toplam();
					}
					if (column == 4)  //m3
					{
						double fiat ,m3 = 0 ;
						fiat =  Double.parseDouble(model.getValueAt(row, 8).toString());
						m3 = Double.parseDouble(model.getValueAt(row, 4).toString());
						model.setValueAt( fiat * m3,row, 11)  ;
						//toplam();
					}
					if (column == 8)  //FIAT
					{
						double fiat ,m3 = 0 ;
						fiat =  Double.parseDouble(model.getValueAt(row, 8).toString());
						m3 = Double.parseDouble(model.getValueAt(row, 4).toString());
						model.setValueAt( fiat * m3,row, 11)  ;
						toplam();
					}
					if (column == 9)  //ISKONTO
					{
						toplam();
					}
					if (column == 10)  //KDV
					{
						toplam();
					}
					toplam();
				}
			}
		});
	}
	private static void satir_ilave() 
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		int satir = table.getSelectedRow();
		
		if ( satir  < 0 ) 
		{
			mdl.addRow(new Object[]{"","","",0.00,0.000,"","","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,0,"",0,""});
			satir = 0 ;
		}
		else
		{
			mdl.insertRow(satir, new Object[]{"","","",0.00,0.000,"","","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,0,"",0,""});
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
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		}
		catch (Exception ex)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Ana Grup", JOptionPane.ERROR_MESSAGE);   
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
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Alt Grup", JOptionPane.ERROR_MESSAGE);    	
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
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		}
		catch (Exception ex)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Ozel Kod", JOptionPane.ERROR_MESSAGE);   
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
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		}
		catch (Exception ex)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Nakliyeci", JOptionPane.ERROR_MESSAGE);   
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
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Evrak Okuma", JOptionPane.ERROR_MESSAGE);   
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
				double_5 += Double.parseDouble(model.getValueAt(i, 11).toString());
				double_1 += (Double.parseDouble(model.getValueAt(i, 11).toString()) * (Double.parseDouble(model.getValueAt(i, 9).toString()))) / 100 ; 
				double_2 += (( Double.parseDouble(model.getValueAt(i, 11).toString()) - ( Double.parseDouble(model.getValueAt(i, 11).toString()) *  Double.parseDouble(model.getValueAt(i, 9).toString())) / 100) *  Double.parseDouble(model.getValueAt(i, 10).toString())) / 100 ; // kdv
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
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Toplam", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private  void depo_auto()
	{
		try {
			ResultSet rs = null;
			rs = ker_Access.ker_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
			if (!rs.isBeforeFirst() ) {  
				listdepo.add("");
			}
			else
			{
				listdepo.add("");
				while (rs.next())
				{
					listdepo.add(rs.getString("DEPO"));
				}
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Depo Doldur", JOptionPane.ERROR_MESSAGE);
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
				if (i == model.getRowCount() -1) {
					double aram33 = Double.parseDouble(model.getValueAt(i, 4).toString()) ;
					model.setValueAt( m3+ aram3 ,i-1, 5)  ;
					if (aram33==0) {
						model.setValueAt( "" ,i, 5)  ;
					}
					else {
						model.setValueAt( FORMATLAMA.doub_3(aram33) ,i, 5)  ;
					}
				}
				else {
					m3 = m3 + aram3 ;
					if (m3==0) {
						model.setValueAt("",i-1, 5)  ;
					}
					else {
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
					if (m3+ aram33+ aram1 ==0) {
						model.setValueAt("" ,i, 5)  ;
					}
					else {
						model.setValueAt(FORMATLAMA.doub_3(m3+ aram33+ aram1) ,i, 5)  ;
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
		table.repaint();
		//dOSYADAN = false;
		toplam();
		//dOSYADAN = true;
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
			JOptionPane.showMessageDialog(null,  "Bos Satirlar ...",  "Kereste Kayit", JOptionPane.ERROR_MESSAGE);   	
			return;
		}
		for (int  i = 0 ; i <=  mdl.getRowCount() - 1 ; i++)
		{
			if (! mdl.getValueAt(i,1).toString().equals(""))
			{
				if (KERESTE_KOD_KONTROL.kontrol(mdl.getValueAt(i,1).toString()) == false) {
					JOptionPane.showMessageDialog(null, i + 1 + " Nolu Satirda Urun Kodu Gecersiz...",  "Kereste Kayit", JOptionPane.ERROR_MESSAGE);   	
					return;
				}
			}
		}
		startTimeG = System.currentTimeMillis(); 
		tar = TARIH_CEVIR.tarih_geri_saatli(dtc) ;
		GuiUtil.setWaitCursor(KER_GIRIS.splitPane,true);
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
				GuiUtil.setWaitCursor(KER_GIRIS.splitPane,true);
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
				for (int  i = 0 ; i <=  mdl.getRowCount() - 1 ; i++)
				{
					if (! mdl.getValueAt(i,1).toString().equals(""))
					{
						Progres_Bar(mAX,i);
						sat_yaz_2(i,degisken);
					}
				}
				GuiUtil.setWaitCursor(KER_GIRIS.splitPane,false);
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
				JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kereste Satyaz 1", JOptionPane.ERROR_MESSAGE);             
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
			int depo = 0 ;
			ResultSet rs =null ;
			if (mdl.getValueAt(i,7) == null)
			{
				depo = 0 ;
			}
			else
			{
				rs = ker_Access.ker_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN",  mdl.getValueAt(i,7).toString());
				if (!rs.isBeforeFirst() ) {      		
				}
				else
				{
					rs.next();
					depo = rs.getInt("DPID_Y");
				}
			}
			String  izahat ="";
			double  miktar=0;
			miktar = Double.parseDouble( mdl.getValueAt(i,3).toString());
			double tutar ;
			tutar =Double.parseDouble(mdl.getValueAt(i,11).toString());
			izahat =  mdl.getValueAt(i,12) .toString();
			double kur =0.00 ;
			kur = DecimalFormat.getNumberInstance().parse(txtkur.getText()).doubleValue();
			double tevk = DecimalFormat.getNumberInstance().parse(txttev.getText()).doubleValue()  ;
			double fiat =0 ;
			fiat = Double.parseDouble( mdl.getValueAt(i,8).toString());
			double isk = 0 ;
			isk = Double.parseDouble( mdl.getValueAt(i,9).toString());
			double kdv = 0 ; 
			kdv =Double.parseDouble( mdl.getValueAt(i,10).toString());
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ( " Fatura Kayit" +  mdl.getValueAt(i,1).toString() + " Mik=" + miktar + " Tut=" + tutar);
			lBILGI.seteVRAK(textField.getText());
			//
			KER_BILGI ker_BILGI = new KER_BILGI();
			ker_BILGI.setEvrak_No(textField.getText());
			ker_BILGI.setCari_Firma(txtcari.getText());
			ker_BILGI.setAdres_Firma( txtadres.getText());
			ker_BILGI.setTarih(tar);
			ker_BILGI.setDepo(depo);
			ker_BILGI.setAna_Grup(degiske[0]);
			ker_BILGI.setAlt_Grup(degiske[1]);
			ker_BILGI.setNakliyeci(degiske[2]);
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
			ker_BILGI.setCikis_Evrak(  mdl.getValueAt(i,13).toString());
			ker_BILGI.setCTarih( mdl.getValueAt(i,14).toString());
			ker_BILGI.setCKdv(Double.parseDouble( mdl.getValueAt(i,15).toString()));
			ker_BILGI.setCDoviz( mdl.getValueAt(i,16).toString());
			ker_BILGI.setCFiat(Double.parseDouble( mdl.getValueAt(i,17).toString()));
			ker_BILGI.setCTutar(Double.parseDouble( mdl.getValueAt(i,18).toString()));
			ker_BILGI.setCKur(Double.parseDouble( mdl.getValueAt(i,19).toString()));
			ker_BILGI.setCCari_Firma( mdl.getValueAt(i,20).toString());
			ker_BILGI.setCAdres_Firma( mdl.getValueAt(i,21).toString());
			ker_BILGI.setCIskonto(Double.parseDouble( mdl.getValueAt(i,22).toString()));
			ker_BILGI.setCTevkifat(Double.parseDouble( mdl.getValueAt(i,23).toString()));
			ker_BILGI.setCAna_Grup(Integer.parseInt(mdl.getValueAt(i,24).toString()));
			ker_BILGI.setCAlt_Grup(Integer.parseInt(mdl.getValueAt(i,25).toString()));
			ker_BILGI.setCDepo(Integer.parseInt(mdl.getValueAt(i,26).toString()));
			ker_BILGI.setCOzel_Kod( Integer.parseInt(mdl.getValueAt(i,27).toString()));
			ker_BILGI.setCIzahat( mdl.getValueAt(i,28).toString());
			ker_BILGI.setCNakliyeci(Integer.parseInt(mdl.getValueAt(i,29).toString()));
			ker_BILGI.setCUSER( mdl.getValueAt(i,30).toString());
			ker_BILGI.setSatir(i);
			ker_Access.ker_kaydet(ker_BILGI, GLOBAL.KULL_ADI	,lBILGI,BAGLAN_LOG.kerLogDizin);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Satyaz 2", JOptionPane.ERROR_MESSAGE);     
		}
	}
	private static int[] degiskenler() throws ClassNotFoundException, SQLException
	{
		int degisken[] = {0,0,0,0} ;
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
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
		return degisken;
	}
	private void fiskont()
	{
		Runnable runner = new Runnable()
		{ public void run() {
			try {
				long startTime = System.currentTimeMillis();
				ResultSet rss = null;
				rss = ker_Access.ker_oku(textField.getText(), "G");
				if (!rss.isBeforeFirst() ) {  
					yeni_fat = true;
					dOSYADAN = true;
					GRID_TEMIZLE.grid_temizle(table);
					dOSYADAN = false;
					sifirla();
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
								rss.getString("Depo"),
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
								rss.getString("CUser")});
						txttev.setText(FORMATLAMA.doub_0(rss.getDouble("Tevkifat")));
						satir +=1 ;

						if (satir == mdl.getRowCount())  
						{
							mdl.addRow(new Object[]{"","","",0.00,0.000,"","","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,"","",0,""});
						}
						else  {
							mdl.removeRow(mdl.getRowCount() -1);	
						}
					}  while (rss.next()) ;
					Thread.currentThread().isInterrupted();
					mdl.addRow(new Object[]{"","","",0.00,0.000,"","","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,"","",0,""});
					paketm3();
					dOSYADAN = false;
					toplam();
					dipnot_oku();
					kod_ADI( mdl.getValueAt(0,1).toString(), mdl.getValueAt(0,6).toString());
					long endTime = System.currentTimeMillis();
					long estimatedTime = endTime - startTime;
					double seconds = (double)estimatedTime/1000; 
					OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
				}
			}
			catch (Exception ex)
			{
				GuiUtil.setWaitCursor(KER_GIRIS.splitPane,false);
				JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kereste Fis Kontrol", JOptionPane.ERROR_MESSAGE);   
			}
		}
		};
		Thread t = new Thread(runner, "Code Executer");
		t.start();
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
		textField_8.setText("");
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
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kereste Dipnot yaz", JOptionPane.ERROR_MESSAGE);   
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
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Dipnot", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private   double m3(String kod,double miktar)
	{
		String[] token = kod.toString().split("-");
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
					lBILGI,BAGLAN_LOG.fatLogDizin);
			lBILGI.setmESAJ( "Kereste Aciklama Yaz  Giris :" + textField_10.getText() );
			ker_Access.aciklama_yaz("KER", 2, textField.getText(), textField_10.getText(), "G",
					lBILGI,BAGLAN_LOG.fatLogDizin);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kereste Acikyz", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private static void acik_sil()
	{
		try {
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ( "Kereste Aciklama Sil  Giris "  );
			lBILGI.seteVRAK(textField.getText());
			ker_Access.aciklama_sil("KER", textField.getText(), "G",
					lBILGI,BAGLAN_LOG.fatLogDizin);

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Aciklama Silme", JOptionPane.ERROR_MESSAGE);   
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
				String konsimento = "5404" ;
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
						      int min = 300; // Minimum value of range
						      int max = 600; // Maximum value of range
						      double random_fiat = (double)Math.floor(Math.random() * (max - min + 1) + min);
							///
							mdl.addRow(new Object[]{"",kODU,yeniPaket,adet,
									m3(kODU,adet),"",konsimento,"Umraniye",random_fiat,0.00,0.00,m3(kODU, adet) * random_fiat,"Izahat","","1900-01-01 00:00:00.0",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,0,"",0,""});
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
						      int min = 300; // Minimum value of range
						      int max = 600; // Maximum value of range
						      double random_fiat = (double)Math.floor(Math.random() * (max - min + 1) + min);
							///

							mdl.addRow(new Object[]{"",kODU,yeniPaket,adet,
									m3(kODU,adet),"",konsimento,"Umraniye",random_fiat,0.00,0.00,m3(kODU, adet) * random_fiat,"Izahat","","1900-01-01 00:00:00.0",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,0,"",0,""});
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
				JOptionPane.showMessageDialog(null, ex.getMessage(),  "Distan Aktar", JOptionPane.ERROR_MESSAGE);   
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
    			JOptionPane.showMessageDialog(null, textField.getText() +  " Bu numarada hesaba rastlanmadi!!!!",  "Kereste Cari Kaydetme", JOptionPane.ERROR_MESSAGE); 
    			return;
    		} 
    		double sdf =  DecimalFormat.getNumberInstance().parse(label_8_1.getText()).intValue()  ;
    		String str_4  ="";
    		int e_number =0;
    		e_number = c_Access.cari_fisno_al();
    		DefaultTableModel model = (DefaultTableModel)table.getModel();
    		double tutar  = DecimalFormat.getNumberInstance().parse(label.getText()).doubleValue()  ;
    		lOG_BILGI lBILGI = new lOG_BILGI();
    		str_4 = textField.getText() + "'Evrak ile " + FORMATLAMA.doub_0(sdf) + " Adet " + DecimalFormat.getNumberInstance().parse(label_8.getText()).doubleValue() + " m3 Urun Girisi" ;
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
    		JOptionPane.showMessageDialog(null,  "Evrak Cari Hesaba Basari ile Kaydedilmistir....",  "Kereste Cari Kaydetme", JOptionPane.INFORMATION_MESSAGE);
    	}
    	catch (Exception ex)
    	{
    		JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Kereste Cari Kaydetme", JOptionPane.ERROR_MESSAGE);
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

package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;

import com.toedter.calendar.JDateChooser;

import LOGER_KAYIT.DOSYA_MSSQL;
import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.DoubleEditor;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.KER_BILGI;
import OBS_C_2025.Next_Cell_Kereste;
import OBS_C_2025.SAGA;
import OBS_C_2025.SAGA_BOLD;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.U_KODU_RENDERER;
import OBS_C_2025.dEKONT_BILGI;
import OBS_C_2025.lOG_BILGI;

@SuppressWarnings({"static-access","unused", "serial"})
public class KERESTE_CIKIS extends JInternalFrame {
	private static JSplitPane splitPane ;
	private static JTabbedPane tabbedPane ;

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
	private static JLabel label_8_1 ;
	private static JLabel label_9 ;
	private static JLabel lblNewLabel_20;
	private static JLabel lblPaket ;


	private static JLabel lblNewLabel_3;
	private static JLabel lblNewLabel_6 ;
	private static JLabel lblNewLabel_17;
	private static JLabel lblNewLabel_13;
	private static JLabel lblkodAciklama ;
	private static JLabel lblkONSIMENTO ;
	private ArrayList<String> listdepo = null ;
	
	
	private static JTable table;
	private ArrayList<String> listPaket = new ArrayList<String>();
	private ArrayList<String> listBarkod =  new ArrayList<String>();

	private static boolean yeni_fat = false;
	private static  String tar = "" ;
	
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
					KERESTE_CIKIS frame = new KERESTE_CIKIS();
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
	
	public KERESTE_CIKIS() {
		setTitle("KERESTE CIKIS");
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
						sno  = ker_Access.evrak_no_al("C") ;
						int kj = 0 ;
						kj = 10 - Integer.toString(sno).length() ;
						String str_ = StringUtils.repeat("0", kj)   + Integer.toString(sno);
						textField.setText(str_.equals("0000000000") ? "0000000001":str_);
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

		cmbozkod.setEditable(true);


		cmbozkod.setBounds(560, 7, 156, 22);
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
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setDividerSize(0);
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

		label_8_1 = new JLabel("0");
		label_8_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8_1.setForeground(new Color(139, 0, 0));
		label_8_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8_1.setBounds(353, 5, 73, 14);
		panel_71.add(label_8_1);
		
		label_8 = new JLabel("0.000");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setForeground(new Color(139, 0, 0));
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(433, 5, 67, 14);
		panel_71.add(label_8);

		label_9 = new JLabel("0.00");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setForeground(new Color(139, 0, 0));
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(975, 5, 102, 14);
		panel_71.add(label_9);
		splitPane_3.setLeftComponent(panel_71);
		
		lblPaket = new JLabel("0.000");
		lblPaket.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaket.setForeground(new Color(139, 0, 0));
		lblPaket.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPaket.setBounds(504, 5, 73, 14);
		panel_71.add(lblPaket);
		
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
		
		JLabel lblIskonto = new JLabel("Iskonto");
		lblIskonto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblIskonto.setBounds(247, 11, 62, 14);
		panel_1.add(lblIskonto);
		
		label_6 = new JLabel("0.00");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setForeground(Color.BLUE);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_6.setBounds(306, 11, 99, 14);
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
		label_7.setBounds(306, 39, 99, 14);
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
		
		lblkONSIMENTO = new JLabel();
		lblkONSIMENTO.setForeground(new Color(0, 0, 128));
		lblkONSIMENTO.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblkONSIMENTO.setBounds(10, 35, 50, 14);
		panel_1.add(lblkONSIMENTO);
		
		splitPane_3.setLeftComponent(panel_71);
		
		


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
					mdll.addRow(new Object[]{"","","",0.00,0.000,"","","",0.00,0.00,0.00,0.00,"",0});
					paketm3();
				}
				
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-reduce-16.png")));
		toolBar_1.add(btnNewButton_3);

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
				case 0:
					return true;
				case 7:
					return true;
				case 8:
					return true;
				case 9:
					return true;
				case 10:
					return true;
				case 12:
					return true;
				default:
					return false;
				}
			}
			public void changeSelection(final int row, final int column, boolean toggle, boolean extend)
			{
				super.changeSelection(row, column, toggle, extend);
				if (column == 0)
				{
					table.editCellAt(row, column);
					table.transferFocus();
				}
			}	
		};
		
		table.addKeyListener(new KeyAdapter() {         
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
		
		table.setGridColor(oac.gridcolor);
		table.setCellSelectionEnabled(true);
		model.addColumn("Paket_No", new String []{""});
		model.addColumn("Barkod", new String []{""});
		model.addColumn("Urun Kodu", new String []{""});
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
		model.addColumn("Satir", new  Integer []{( 0 )});
		TableColumn col ;
		
		//
		listPaket = new ArrayList<String> () ;
		stk_kodu_auto("Paket_No");
		ComboBoxTableCellEditor editor = new ComboBoxTableCellEditor( listPaket ,table,"ker_cikis");
		col = table.getColumnModel().getColumn(0);
		col.setMinWidth(120);
		col.setHeaderRenderer(new SOLA());
		col.setCellEditor(editor);
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
			Font font = new Font("Arial", 1, 12);
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
						row, column);
				setFont(font);
				setForeground(new Color(0, 0, 128));
				return this;
			}
		};
		col.setCellRenderer(r);
		
		
		col = table.getColumnModel().getColumn(1);
		col.setMinWidth(80);
		col.setHeaderRenderer(new SOLA());
		
		col = table.getColumnModel().getColumn(2);
		col.setMinWidth(120);
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
		
		table.removeColumn(table.getColumnModel().getColumn(13));
		JTableHeader th = table.getTableHeader();
		Dimension dd = table.getPreferredSize();
		dd.height = 30;
		th.setPreferredSize(dd); 
		table.setRowSelectionInterval(0, 0);
		table.setColumnSelectionAllowed(true);
	    table.setRowSelectionAllowed(true);
		table.setRowHeight(22);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.repaint();
		//table.setSurrendersFocusOnKeystroke(true);
		
		InputMap im = table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Action.NextCell");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Action.NextCell");
		ActionMap am = table.getActionMap();
		am.put("Action.NextCell", new  Next_Cell_Kereste(table,"kereste_cikis"));
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		
		scrollPane.setViewportView(table);
		ana_grup_doldur();
		ker_oz_kod();
		ker_nakliyeci();
		
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
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kereste Cikis", JOptionPane.ERROR_MESSAGE);   
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
				if (column == 4)  //m3
				{
					double fiat ,m3 = 0 ;
					fiat =  Double.parseDouble(model.getValueAt(row, 8).toString());
					m3 = Double.parseDouble(model.getValueAt(row, 4).toString());
					model.setValueAt( fiat * m3,row, 11)  ;
				}
				if (column == 8)  //FIAT
				{
					double fiat ,m3 = 0 ;
					fiat =  Double.parseDouble(model.getValueAt(row, 8).toString());
					m3 = Double.parseDouble(model.getValueAt(row, 4).toString());
					model.setValueAt( fiat * m3,row, 11)  ;
				}
				
			}
			toplam();
		}
		});
		ListSelectionModel selectionModel = table.getSelectionModel();

		selectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if (model.getRowCount() > 0) 
				{
					try {
						if ( table.getSelectedRow() != -1) {
							kod_ADI(model.getValueAt(table.getSelectedRow(), 2).toString(),model.getValueAt(table.getSelectedRow(), 6).toString());
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
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

			rs = ker_Access.ker_oz_kod( "G");

			if (!rs.isBeforeFirst() ) {  
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
				cmbozkod .addItem("");
				return;
			} 
			cmbozkod .addItem("");
			while (rs.next())
			{
				cmbozkod .addItem(rs.getString("Ozel_Kod"));
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

			if (!rs.isBeforeFirst() ) {  
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
				cmbnakliyeci .setEnabled(false);
				cmbnakliyeci  .addItem("");
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
			textField.setText( ker_Access.son_no_al("C"));
			textField.requestFocus();
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Evrak Okuma", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void fiskont()
	{
		try {
			long startTime = System.currentTimeMillis();
			ResultSet rss = null;
			rss = ker_Access.ker_oku(textField.getText(), "C");
			if (!rss.isBeforeFirst() ) {  
				txtadres.setText("");
				yeni_fat = true;
				GRID_TEMIZLE.grid_temizle(table);
				sifirla();
			}
			else
			{
				rss.next();
				yeni_fat = false;
				txtadres.setText("");
				GRID_TEMIZLE.grid_temizle(table);
				sifirla();
				dtc.setDate(rss.getDate("CTarih"));
				txtadres.setText(rss.getString("CAdres_Firma"));
				txtcari.setText(rss.getString("CCari_Firma"));
				txtdoviz.setText(rss.getString("CDoviz"));
				txtkur.setText(FORMATLAMA.doub_4(rss.getDouble("CKur")));
				cmbozkod.setSelectedItem(rss.getString("COzel_Kod"));
				//  '***********GRUP DOLDUR
				ResultSet rsa=null;
				rsa = ker_Access.ker_kod_degisken_ara("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN",String.valueOf(rss.getInt("CAna_Grup")));
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
				rsa = ker_Access.ker_kod_degisken_ara("ALT_GRUP", "ALID_Y", "ALT_GRUP_DEGISKEN",String.valueOf(rss.getInt("CAlt_Grup")));
				if (!rsa.isBeforeFirst() ) {  
					cmbaltgrup.setSelectedItem("");
				} 
				else
				{
					rsa.next();
					cmbaltgrup.setSelectedItem(rsa.getString("ALT_GRUP"));
				}
				//***Aciklama
				textField_9.setText(ker_Access.aciklama_oku("KER", 1, textField.getText(), "C"));
				textField_10.setText(ker_Access.aciklama_oku("KER", 2, textField.getText(), "C"));
				//**nakliye
				rsa = null;
				rsa = ker_Access.ker_kod_degisken_ara("UNVAN", "NAKID_Y", "NAKLIYECI",String.valueOf(rss.getInt("CNakliyeci")));
				if (!rsa.isBeforeFirst() ) {  
					cmbnakliyeci.setSelectedItem("");
				} 
				else
				{
					rsa.next();
					cmbnakliyeci.setSelectedItem(rsa.getString("UNVAN"));
				}			
				rss.first();   
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				int satir =0 ;
				do
				{
					mdl.insertRow(satir,new Object[]{rss.getString("Paket_No"),rss.getString("Barkod"),rss.getString("Kodu"),
					rss.getDouble("Miktar"), m3(rss.getString("Kodu"),rss.getDouble("Miktar")),"" ,rss.getString("Konsimento"),rss.getString("CDepo"),
					rss.getDouble("CFiat"),rss.getDouble("CIskonto"),
					rss.getDouble("CKdv"),rss.getDouble("CTutar"),rss.getString("CIzahat"),rss.getInt("Satir")});
					txttev.setText(FORMATLAMA.doub_0(rss.getDouble("CTevkifat")));
					satir +=1 ;
					if (satir +1 >= mdl.getRowCount())  
					{
						mdl.addRow(new Object[]{"","","",0.00,0.000,"","","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,"","",0,""});
					}
					else  {
						mdl.removeRow(mdl.getRowCount() -1);	
					}
				}  while (rss.next()) ;
				mdl.addRow(new Object[]{"","","",0.00,0.000,"","","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,"","",0,""});

				table.repaint();
				paketm3();
				toplam();
				dipnot_oku();
				kod_ADI( mdl.getValueAt(0,2).toString(),mdl.getValueAt(0, 6).toString());
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			}
		}
		catch (Exception ex)
		{
			GuiUtil.setWaitCursor(KERESTE_CIKIS.splitPane,false);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kereste Fis Kontrol", JOptionPane.ERROR_MESSAGE);   
		}
	}
	public static void kaydet()
	{
		if (textField.getText().equals("")) return ;
		if(dtc.getDate() == null) return;
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		if (mdl.getRowCount() == 0)  return;

		long startTime = System.currentTimeMillis(); 
		tar = TARIH_CEVIR.tarih_geri_saatli(dtc) ;
		GuiUtil.setWaitCursor(KERESTE_CIKIS.splitPane,true);
		satir_yaz_1();
		dipnot_yaz();
		acik_yaz();
		textField.setText("");
		textField.requestFocus();
		//************************************
		GuiUtil.setWaitCursor(KERESTE_CIKIS.splitPane,false);
		long endTime = System.currentTimeMillis();
		long estimatedTime = endTime - startTime;
		double seconds = (double)estimatedTime/1000; 
		OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
	}
	private static void satir_yaz_1 ()
	{
		try {
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ(textField.getText() + " Nolu Cikis Kereste Silindi");
			lBILGI.seteVRAK(textField.getText());
			ker_Access.ker_cikis_sil(textField.getText() ,lBILGI,BAGLAN_LOG.kerLogDizin);

			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			for (int  i = 0 ; i <=  mdl.getRowCount() - 1 ; i++)
			{
				if (! mdl.getValueAt(i,2).toString().equals(""))
				{
					sat_yaz_2(i);
				}
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kereste Satyaz 1", JOptionPane.ERROR_MESSAGE);             
		}
	}
	private static void sat_yaz_2(int i)
	{
		try {
			String  izahat ;
			double  miktar=0;
			
			int angrp, altgrp, depo, nakl;
			depo = 0 ;
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
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
			miktar = Double.parseDouble( mdl.getValueAt(i,3).toString());
			double tutar ;
			tutar =Double.parseDouble(mdl.getValueAt(i,11).toString());
			if ( mdl.getValueAt(i,12).toString().equals(""))
			{
				izahat = "" ;
			}
			else
			{
				izahat =  mdl.getValueAt(i,9) .toString();
			}
			double kur =0.00 ;
			kur = DecimalFormat.getNumberInstance().parse(txtkur.getText()).doubleValue();
			angrp = 0 ;
			if ( ! cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals("") ) {

				rs = ker_Access.ker_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString());

				if (!rs.isBeforeFirst() ) {      		
				}
				else
				{
					rs.next();
					angrp  = rs.getInt("AGID_Y");
				}
			}
			//*************nakliyeci
			nakl = 0 ;
			if ( ! cmbnakliyeci.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals("") ) {

				rs = ker_Access.ker_kod_degisken_ara("NAKID_Y", "UNVAN", "NAKLIYECI", cmbnakliyeci.getItemAt(cmbnakliyeci.getSelectedIndex()).toString());

				if (!rs.isBeforeFirst() ) {      		
				}
				else
				{
					rs.next();
					nakl  = rs.getInt("NAKID_Y");
				}
			}
			///
			altgrp = 0;
			if ( ! cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString().equals("") ) {
				rs = ker_Access.ker_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN",  cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString());
				if (!rs.isBeforeFirst() ) {      		
				}
				else
				{
					rs.next();
					altgrp  = rs.getInt("ALID_Y");
				}
			}
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
			ker_BILGI.setCikis_Evrak(textField.getText());
			ker_BILGI.setCCari_Firma(txtcari.getText());
			ker_BILGI.setCAdres_Firma( txtadres.getText());
			ker_BILGI.setCTarih(tar);
			ker_BILGI.setCDepo(depo);
			ker_BILGI.setCAna_Grup(angrp);
			ker_BILGI.setCAlt_Grup(altgrp);
			ker_BILGI.setCNakliyeci(nakl);
			ker_BILGI.setCDoviz( txtdoviz.getText());
			ker_BILGI.setCKur(kur);
			ker_BILGI.setCOzel_Kod(cmbozkod.getItemAt(cmbozkod.getSelectedIndex()).toString());
			ker_BILGI.setPaket_No( mdl.getValueAt(i,0).toString());
			ker_BILGI.setKodu( mdl.getValueAt(i,2).toString());
			ker_BILGI.setMiktar(Double.parseDouble( mdl.getValueAt(i,3).toString()));
			ker_BILGI.setKonsimento( mdl.getValueAt(i,6).toString());
			ker_BILGI.setCFiat(fiat);
			ker_BILGI.setCIskonto(isk);
			ker_BILGI.setCKdv(kdv);
			ker_BILGI.setCTutar(tutar);
			ker_BILGI.setCIzahat(izahat);
			ker_BILGI.setCTevkifat(tevk);
			ker_BILGI.setCUSER(GLOBAL.KULL_ADI);
			ker_BILGI.setSatir(Integer.parseInt(mdl.getValueAt(i,13).toString()));
			ker_Access.ker_cikis_kaydet(ker_BILGI,lBILGI,BAGLAN_LOG.kerLogDizin);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Satyz2", JOptionPane.ERROR_MESSAGE);     
		}
	}
	private static void kod_ADI(String toke,String kons ) throws ClassNotFoundException, SQLException 

	{
		String[] token = toke.toString().split("-");
		String aciklamaString ;
		aciklamaString = ker_Access.kod_adi(token[0]);
		lblkodAciklama.setText(aciklamaString);
		Dimension size = lblkodAciklama.getPreferredSize();
		lblkodAciklama.setBounds(10, 55, size.width +10, 14);
		
		lblkONSIMENTO.setText(ker_Access.kons_adi(kons));
		size = lblkONSIMENTO.getPreferredSize();
		lblkONSIMENTO.setBounds(10, 35, size.width +10, 14);
	}
	private static void dipnot_sil()
	{
		try {
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ("Kereste Dip Not Sil "  );
			lBILGI.seteVRAK(textField.getText());
			ker_Access.dipnot_sil(textField.getText(), "K", "C",lBILGI,BAGLAN_LOG.kerLogDizin);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Dipnot Silme", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private static void dipnot_yaz()
	{
		try {
			dipnot_sil();
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ( "Kereste Dip Not Kayit :"+ textField_5.getText()  );
			lBILGI.seteVRAK(textField.getText());
			ker_Access.dipnot_yaz(textField.getText(), textField_5.getText(),textField_6.getText(),textField_7.getText(), "K", "C",GLOBAL.KULL_ADI,
						lBILGI,BAGLAN_LOG.kerLogDizin);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kereste Dipnot yaz", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void dipnot_oku()
	{
		try {
			ResultSet rs =null ;
			rs =    ker_Access.dipnot_oku(textField.getText(), "K", "C");
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

	private static void sifirla()
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
		label_1.setText("0.00");
		label_3.setText("0.00");
		label_6.setText("0.00");
		label_7.setText("0.00");
		label_8.setText("0.000");
		label_9.setText("0.00");
		try {
			txtdoviz.setText(GLOBAL.setting_oku("PRG_PARA").toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		txtcari.setText("");
		cmbanagrup.setSelectedItem("");
		cmbaltgrup.setSelectedItem("");
		cmbozkod.setSelectedItem("");
		cmbnakliyeci.setSelectedItem("");
		textField_9.setText("");
		textField_10.setText("");
		txtkur.setText("0.0000");
		lblkodAciklama.setText("");
		dtc.setDate(new Date());
		
	}
	private static void satir_ilave()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		int satir = table.getSelectedRow();
		if ( satir  < 0 ) 
		{
			mdl.addRow(new Object[]{"","","",0.00,0.000,"","","",0.00,0.00,0.00,0.00,"",0});
			satir = 0 ;
		}
		else
		{
			mdl.insertRow(satir, new Object[]{"","","",0.00,0.000,"","","",0.00,0.00,0.00,0.00,"",0});
		}
		table.isRowSelected(satir);
		table.repaint();
	}
	private static void satir_sil()
	{
		if (table.getSelectedRow() < 0 ) return ;
		DefaultTableModel mdll = (DefaultTableModel) table.getModel();
		mdll.removeRow(table.getSelectedRow());
		table.repaint();
		paketm3();
		toplam();
	}
	private static void toplam()
	{
		try {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			double  double_0, double_1 = 0, double_2 = 0, double_3 = 0, double_4, double_5=0,double_6 = 0 , urunmiktar = 0;
			int urunsayi = 0  ;
			for (int  i = 0 ; i <= table.getRowCount() -1 ; i ++)
			{
				double_5 += Double.parseDouble(model.getValueAt(i, 11).toString());
				double_1 += (Double.parseDouble(model.getValueAt(i, 11).toString()) * (Double.parseDouble(model.getValueAt(i, 9).toString()))) / 100 ; 
				double_2 += (( Double.parseDouble(model.getValueAt(i, 11).toString()) - ( Double.parseDouble(model.getValueAt(i, 11).toString()) *  Double.parseDouble(model.getValueAt(i, 9).toString())) / 100) *  Double.parseDouble(model.getValueAt(i, 10).toString())) / 100 ; // kdv
				double_3 +=  Double.parseDouble(model.getValueAt(i, 4).toString());
				urunmiktar +=  Double.parseDouble(model.getValueAt(i, 3).toString());
				if (! model.getValueAt(i, 5).toString().trim().isEmpty()) 
				{
					double_6 +=  Double.parseDouble(model.getValueAt(i, 5).toString().trim());
				}
				
				if (! model.getValueAt(i,2).toString().equals(""))
				{
					urunsayi += 1;
				}
			}
			label_8.setText(FORMATLAMA.doub_3(double_3));
			label_8_1.setText(FORMATLAMA.doub_0(urunmiktar));
			lblPaket.setText(FORMATLAMA.doub_3(double_6));
			label_9.setText(FORMATLAMA.doub_2(double_5));
			lblNewLabel_13.setText( FORMATLAMA.doub_0(urunsayi));
			
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
	private static void paketm3()
	{
		double m3 =0.000 ;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for ( int i = 1 ;i <= model.getRowCount() -1 ;i++   ) 
		{
			String paketno = model.getValueAt(i-1, 0).toString().trim();
			double aram3 = Double.parseDouble(model.getValueAt(i-1, 4).toString()) ;
			if (! model.getValueAt(i, 0).toString().trim().equals(paketno.toString().trim()))
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
			else if ( model.getValueAt(i, 0).toString().trim().equals(paketno.toString().trim())) {
				if (i == model.getRowCount() -1)
				{
					double aram33 = Double.parseDouble(model.getValueAt(i, 4).toString()) ;
					double aram1 = Double.parseDouble(model.getValueAt(i-1, 4).toString()) ;
					if (m3+ aram33+ aram1 ==0) {
						model.setValueAt("" ,i, 5)  ;
					}
					else {
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
	private static void acik_yaz()
	{
		try {
			acik_sil();
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ("Kereste Aciklama Yaz  Cikis :" + textField_9.getText()  );
			lBILGI.seteVRAK(textField.getText());
			ker_Access.aciklama_yaz("KER", 1, textField.getText(),  textField_9.getText(), "C",
					lBILGI,BAGLAN_LOG.fatLogDizin);
			lBILGI.setmESAJ( "Kereste Aciklama Yaz  Cikis :" + textField_10.getText() );
			ker_Access.aciklama_yaz("KER", 2, textField.getText(), textField_10.getText(), "C",
					lBILGI,BAGLAN_LOG.fatLogDizin);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Aciklama Kaydet", JOptionPane.ERROR_MESSAGE);   			 
		}
	}
	private static void acik_sil()
	{
		try {
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ( "Kereste Aciklama Sil  Giris "  );
			lBILGI.seteVRAK(textField.getText());
			ker_Access.aciklama_sil("KER", textField.getText(), "C",lBILGI,BAGLAN_LOG.fatLogDizin);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Aciklama Silme", JOptionPane.ERROR_MESSAGE);   
		}
	}
	public static void pakkont(String pakno)
	{
		try {
			if (table.getSelectedRow() == -1 ) return ;

			ResultSet rSet = ker_Access.paket_oku(pakno);
			if (!rSet.isBeforeFirst() ) {  
			}
			else 
			{
				rSet.first();  
				if (! rSet.getString("Cikis_Evrak").toString().equals("")   && ! rSet.getString("Cikis_Evrak").toString().equals(textField.getText()))
				{
					JOptionPane.showMessageDialog(null, "Bu Paket daha Once " + rSet.getString("Cikis_Evrak").toString() + " da Cikis Yapilmis"  ,  "Urun Cikis", JOptionPane.ERROR_MESSAGE);  
				}
				else 
				{
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int satir = table.getSelectedRow() ;
					int ilks = satir;
					do {
						model.setValueAt( rSet.getString("Paket_No"),satir, 0)  ;
						model.setValueAt( rSet.getString("Barkod"),satir, 1)  ;
						model.setValueAt( rSet.getString("Kodu"),satir, 2)  ;
						model.setValueAt( rSet.getDouble("Miktar"),satir, 3)  ;
						double m3 = 0 ;
						double miktar =  rSet.getDouble("Miktar");
						String[] token = rSet.getString("Kodu").toString().split("-");
						if (! token[1].toString().trim().isEmpty() && ! token[2].toString().trim().isEmpty() && ! token[3].toString().trim().isEmpty()) {
							m3 = ((Double.parseDouble(token[1].toString().trim()) * Double.parseDouble(token[2].toString().trim()) * Double.parseDouble(token[3].toString().trim() )) * miktar)/1000000000 ;
						}
						model.setValueAt( m3,satir, 4)  ;
						model.setValueAt( rSet.getString("Konsimento"),satir, 6)  ;
						model.setValueAt( rSet.getString("CDepo"),satir, 7)  ;
						model.setValueAt( rSet.getDouble("CFiat"),satir, 8)  ;
						model.setValueAt( rSet.getDouble("CIskonto"),satir, 9)  ;
						model.setValueAt( rSet.getDouble("CKdv"),satir,10)  ;
						model.setValueAt( rSet.getDouble("CTutar"),satir,11)  ;
						model.setValueAt( rSet.getString("CIzahat"),satir, 12)  ;
						model.setValueAt( rSet.getInt("Satir"),satir, 13)  ;
						satir +=1 ;
						if (satir +1 >= model.getRowCount())  
						{
							model.addRow(new Object[]{"","","",0.00,0.000,"","","",0.00,0.00,0.00,0.00,"","","",0.00,"",0.00,0.00,0.00,"","",0.00,0.00,0,0,0,"","",0,""});
						}
					}  while (rSet.next()) ;
					paketm3();		
					kod_ADI(model.getValueAt(ilks,2).toString(),model.getValueAt(table.getSelectedRow(), 6).toString());
					table.getSelectionModel().setSelectionInterval(ilks, ilks);
					table.getColumnModel().getSelectionModel().setSelectionInterval(8, 8);			     
				}
			}
			toplam();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kereste Paket Kontrol", JOptionPane.ERROR_MESSAGE);  
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
	public static void cari_kaydet()
	{
		try {
			BORC_ALACAK hsp ;
			hsp = new BORC_ALACAK();
			hsp.setLocationRelativeTo(OBS_MAIN.desktopPane);
			oac.hsp_hsp_kodu = "" ;
			String bh = "",alh="";
			hsp.lblNewLabel.setText("Alacakli Hesap");
			hsp.setVisible(true);
			alh = oac.hsp_hsp_kodu;
			bh = txtcari.getText();
			if (alh.equals("")) return ;

			ResultSet rs ;
			rs = c_Access.hesap_adi_oku(alh);
			if (!rs.isBeforeFirst() ) {  
				JOptionPane.showMessageDialog(null,  "Girilen Alacakli Hesap Kodunda  bir  hesaba rastlanmadi!!!!",  "Kereste Cari Kaydetme", JOptionPane.ERROR_MESSAGE); 
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
			str_4 = textField.getText() + "'Evrak ile " + FORMATLAMA.doub_0(sdf) + " Adet " + DecimalFormat.getNumberInstance().parse(label_8.getText()).doubleValue() + " m3 Urun Satisi" ;
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
			dBilgi.setkOD("Satış");
			dBilgi.setuSER( GLOBAL.KULL_ADI);
			lBILGI.setmESAJ("Alacakli Hes:" +alh + " Tut:" + tutar +	" Borclu Hes:"+ bh  + " Evrak No:" + textField.getText());
			lBILGI.seteVRAK(String.valueOf(e_number));
			c_Access.cari_dekont_kaydet(dBilgi,lBILGI,BAGLAN_LOG.cariLogDizin);
			JOptionPane.showMessageDialog(null,  "Evrak  Cari Hesaba Basari ile Kaydedilmistir....",  "Kereste Cari Kaydetme", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Kereste Cari Kaydetme", JOptionPane.ERROR_MESSAGE);
		}
	}
	private  void stk_kodu_auto(String field)
	{
		try {
			ResultSet rs = null;
			rs = ker_Access.ker_barkod_kod_oku(field);
			if (!rs.isBeforeFirst() ) {  
					listPaket.add("");
			}
			else
			{
					listPaket.clear();
					//listPaket.add("");
					while (rs.next())
					{
						listPaket.add(rs.getString("Paket_No").toString());
					}
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Otomatik Doldurma", JOptionPane.ERROR_MESSAGE);
		}
	}
}

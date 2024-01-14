package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.DoubleEditor;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.ImagePanel;

import OBS_C_2025.MaterialTabbed;
import OBS_C_2025.NextCellActioin;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TABLO_TEXTBOX;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.dEKONT_BILGI;
import OBS_C_2025.lOG_BILGI;
import raven.toast.Notifications;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemColor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JCheckBox;
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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings({"serial","static-access"})
public class FATURA extends JInternalFrame {

	public static Obs_TextFIeld textField;
	public static Obs_TextFIeld txtcari;
	public static Obs_TextFIeld txtadres;
	public static Obs_TextFIeld txtdoviz;
	public static Obs_TextFIeld textField_5;
	public static Obs_TextFIeld textField_6;
	public static Obs_TextFIeld textField_7;
	public static Obs_TextFIeld textField_8;
	public static Obs_TextFIeld textField_11;
	public static Obs_TextFIeld textField_12;
	public static JTable table;
	public static JFormattedTextField txttev ;
	public static JDateChooser dtc ;
	public static JLabel lblNewLabel_3;
	public static JLabel lblNewLabel_6 ;
	public static JLabel label ;
	public static JLabel label_1 ;
	public static JLabel label_2 ;
	public static JLabel label_3 ;
	public static JLabel label_6 ;
	public static JLabel label_7 ;
	public static JLabel label_8 ;
	public static JLabel label_9 ;
	public static JLabel lblNewLabel_20;
	public static JSplitPane splitPane ;
	public static JCheckBox chckbxNewCheckBox_1 ;
	public static JCheckBox chckbxNewCheckBox;
	public static JComboBox<String> cmbcins;


	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(OBS_SIS_2025_ANA_CLASS._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	static CARI_ACCESS c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	private ArrayList<String> listSomeString = new ArrayList<String>();
	private ArrayList<String> listBarkod =  new ArrayList<String>();
	private ArrayList<String> listdepo = null ;
	private static JTable table_1;
	private static JFormattedTextField txtkur ;
	private static Obs_TextFIeld textField_9;
	private static Obs_TextFIeld textField_10;
	private Obs_TextFIeld textField_4;
	private static JLabel lblNewLabel_12 ;
	private static JLabel lblNewLabel_13 ;
	private static JLabel label_5 ;
	private static JLabel lblNewLabel_17;

	private static JComboBox<String> cmbanagrup ;
	private static JComboBox<String> cmbaltgrup ;
	private static JComboBox<String> cmbfiat ;
	private static JComboBox<String> cmbozkod ;
	private JPanel panel_8 ;
	private static  String tar = "" ;
	private static boolean yeni_fat = false;
	private static MaterialTabbed tabbedPane ;
	private static ImagePanel imagePanel ;
	/**
	 * Create the frame.
	 */
	public FATURA() {

		setTitle("FATURA		- SATIS");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,1175,780);

		splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 160));
		panel.setMaximumSize(new Dimension(0, 160));
		panel.setLayout(new BorderLayout(0, 0));

		MaterialTabbed tabbedPane_1 = new MaterialTabbed();
		//tabbedPane_1.setForeground(new Color(0, 0, 128));
		tabbedPane_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(tabbedPane_1, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_1.addTab("Fatura Bilgileri", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Fatura No");
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
						if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
						{

							sno  = f_Access.fatura_no_al("C") ;

						}
						else
						{

							sno  = f_Access.fatura_no_al("G") ;

						}

						int kj = 0 ;
						kj = 10 - Integer.toString(sno).length() ;
						String str_ = StringUtils.repeat("0", kj)   + Integer.toString(sno);

						textField.setText(str_.equals("0000000000") ? "0000000001":str_);
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
					}
					catch (Exception ex)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Fatura Numaralarinda onceden harf ve rakkam kullanildigindan otomatik numara verilemez....");
						//JOptionPane.showMessageDialog(null,  "Fatura Numaralarinda onceden harf ve rakkam kullanildigindan otomatik numara verilemez...."); 	
					}
				}
			}
		});
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				fiskont();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void removeUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				fiskont();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void insertUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				fiskont();
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

		JLabel lblNewLabel_2 = new JLabel("Cari Hesap");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 36, 69, 14);
		panel_2.add(lblNewLabel_2);

		txtcari = new Obs_TextFIeld(12);


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

		txtadres = new Obs_TextFIeld(12);
	
		txtadres.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
					try {
						lblNewLabel_6.setText(a_Access.kod_ismi(txtadres.getText()));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
			public void removeUpdate(DocumentEvent e) {
				try {
					lblNewLabel_6.setText(a_Access.kod_ismi(txtadres.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			public void insertUpdate(DocumentEvent e) {
				try {
					lblNewLabel_6.setText(a_Access.kod_ismi(txtadres.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
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
		

		lblNewLabel_6 = new JLabel(".....");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setForeground(new Color(139, 0, 0));
		lblNewLabel_6.setBounds(353, 62, 363, 14);
		panel_2.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Ozel Kod");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(490, 11, 61, 14);
		panel_2.add(lblNewLabel_7);

		cmbozkod = new JComboBox<String>();

		cmbozkod.setEditable(true);


		cmbozkod.setBounds(560, 7, 156, 22);
		panel_2.add(cmbozkod);

		JLabel lblNewLabel_8 = new JLabel("Varsayilan");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8.setBounds(490, 36, 69, 14);
		panel_2.add(lblNewLabel_8);

		cmbfiat = new JComboBox<String>();
		cmbfiat.setForeground(new Color(0, 0, 128));
		cmbfiat.setFont(new Font("Dialog", Font.BOLD, 12));
		cmbfiat.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Fiat_1", "Fiat_2", "Fiat_3", "Son Alis/Satis Fiati"}));
		cmbfiat.setBounds(560, 33, 156, 22);
		panel_2.add(cmbfiat);

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
		lblNewLabel_9.setBounds(726, 67, 52, 14);
		panel_2.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Kur");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(726, 88, 46, 14);
		panel_2.add(lblNewLabel_10);

		txtdoviz = new Obs_TextFIeld();
		txtdoviz.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdoviz.setBounds(797, 60, 73, 20);
		panel_2.add(txtdoviz);
		txtdoviz.setColumns(10);

		txtkur = new JFormattedTextField();
		txtkur.setHorizontalAlignment(SwingConstants.RIGHT);
		txtkur.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtkur.setText("0.0000");
		txtkur.setBounds(797, 83, 73, 20);
		panel_2.add(txtkur);

		imagePanel = new ImagePanel();
		imagePanel.setBounds(1015, 4, 125, 100);
		imagePanel.setBorder(new LineBorder(new Color(95, 158, 160), 2,true));
		panel_2.add( imagePanel);


		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_1.addTab("Yazici Bilgileri", null, panel_4, null);
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

		chckbxNewCheckBox = new JCheckBox("Fatura'da Toplam Miktarin Yazilmasi");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxNewCheckBox.setBounds(425, 7, 267, 23);
		panel_4.add(chckbxNewCheckBox);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Gidecegi Yer Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));

		panel_7.setBounds(710, 5, 257, 70);
		panel_4.add(panel_7);
		panel_7.setLayout(null);

		textField_8 = new Obs_TextFIeld();
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
					GLOBAL.nerden = "fat" ;
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

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(0);
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);

		splitPane.setRightComponent(splitPane_1);
		////
		JSplitPane splitPane_11 = new JSplitPane();
		splitPane_11.setDividerSize(0);
		splitPane_11.setResizeWeight(1.0);
		splitPane_11.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		
		splitPane_1.setRightComponent(splitPane_11);
		
	
		//***
		JPanel panel_71 = new JPanel();
		panel_71.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_71.setMinimumSize(new Dimension(0, 25));
		panel_71.setMaximumSize(new Dimension(0,25));

		panel_71.setLayout(null);
		splitPane_11.setLeftComponent( panel_71);

		JLabel lblNewLabel = new JLabel("Satir Sayisi :");
		lblNewLabel.setBounds(32, 3, 85, 14);
		panel_71.add(lblNewLabel);

		lblNewLabel_13 = new JLabel("0");
		lblNewLabel_13.setForeground(new Color(0, 0, 128));
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_13.setBounds(122, 3, 40, 14);
		panel_71.add(lblNewLabel_13);

		label_8 = new JLabel("0.000");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setForeground(new Color(139, 0, 0));
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(560, 3, 102, 14);
		panel_71.add(label_8);

		label_9 = new JLabel("0.00");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setForeground(new Color(139, 0, 0));
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(800, 3, 125, 14);
		panel_71.add(label_9);

		
		JPanel panell = new JPanel();
	
		panell.setMinimumSize(new Dimension(0, 120));
		panell.setMaximumSize(new Dimension(0, 120));
		panell.setLayout(new BorderLayout(0, 0));
		
		
		MaterialTabbed tabbedPane_2 = new MaterialTabbed();
		//tabbedPane_2.setForeground(new Color(0, 0, 128));
		tabbedPane_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		splitPane_11.setRightComponent(panell);
		panell.add(tabbedPane_2, BorderLayout.CENTER);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_2.addTab("Toplamlar", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel_11 = new JLabel("Barkod");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_11.setBounds(10, 11, 46, 14);
		panel_1.add(lblNewLabel_11);

		textField_4 = new Obs_TextFIeld();
		textField_4.setBounds(66, 8, 156, 20);
		panel_1.add(textField_4);
		textField_4.setColumns(10);

		lblNewLabel_12 = new JLabel(".....");
		lblNewLabel_12.setForeground(new Color(139, 0, 0));
		lblNewLabel_12.setBounds(66, 32, 270, 14);
		panel_1.add(lblNewLabel_12);

		lblNewLabel_20 = new JLabel("0.00");
		lblNewLabel_20.setForeground(new Color(0, 0, 255));
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_20.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_20.setBounds(1019, 11, 109, 14);
		panel_1.add(lblNewLabel_20);

		label = new JLabel("0.00");
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(1019, 39, 109, 14);
		panel_1.add(label);

		JLabel lblNewLabel_21 = new JLabel("Beyan Edilen KDV");
		lblNewLabel_21.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_21.setBounds(920, 11, 109, 14);
		panel_1.add(lblNewLabel_21);

		JLabel lblNewLabel_22 = new JLabel("Tev.Har.Top.Tutar");
		lblNewLabel_22.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_22.setBounds(920, 39, 109, 14);
		panel_1.add(lblNewLabel_22);

		label_1 = new JLabel("0.00");
		label_1.setForeground(new Color(0, 0, 255));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(810, 11, 99, 14);
		panel_1.add(label_1);

		label_2 = new JLabel("0.00");
		label_2.setForeground(new Color(0, 0, 255));
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(810, 39, 99, 14);
		panel_1.add(label_2);

		JLabel lblTevedilenKdv = new JLabel("Tev.Edilen K.D.V.");
		lblTevedilenKdv.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTevedilenKdv.setBounds(700, 11, 109, 14);
		panel_1.add(lblTevedilenKdv);

		JLabel lblTevdahtoptutar = new JLabel("Tev.Dah.Top.Tutar");
		lblTevdahtoptutar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTevdahtoptutar.setBounds(700, 39, 109, 14);
		panel_1.add(lblTevdahtoptutar);

		label_3 = new JLabel("0.00");
		label_3.setForeground(new Color(0, 0, 255));
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(585, 11, 99, 14);
		panel_1.add(label_3);

		JLabel lblKdv = new JLabel("K.D.V.");
		lblKdv.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblKdv.setBounds(520, 11, 52, 14);
		panel_1.add(lblKdv);

		JLabel lblTevkifatOrani = new JLabel("Tevkifat Orani");
		lblTevkifatOrani.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTevkifatOrani.setBounds(520, 39, 82, 14);
		panel_1.add(lblTevkifatOrani);

		JLabel lblIskonto = new JLabel("Iskonto");
		lblIskonto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblIskonto.setBounds(352, 11, 62, 14);
		panel_1.add(lblIskonto);

		label_6 = new JLabel("0.00");
		label_6.setForeground(new Color(0, 0, 255));
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(411, 11, 99, 14);
		panel_1.add(label_6);

		label_7 = new JLabel("0.00");
		label_7.setForeground(new Color(0, 0, 255));
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_7.setBounds(411, 39, 99, 14);
		panel_1.add(label_7);

		JLabel lblBakiye = new JLabel("Bakiye");
		lblBakiye.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBakiye.setBounds(352, 39, 66, 14);
		panel_1.add(lblBakiye);

		label_5 = new JLabel(".....");
		label_5.setForeground(new Color(0, 0, 128));
		label_5.setBounds(66, 50, 348, 14);
		panel_1.add(label_5);

		txttev = new JFormattedTextField();
		txttev.setBackground(Color.CYAN);
		txttev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txttev.getText().equals("")) return ;
				toplam();
			}
		});
		txttev.setText("0");
		txttev.setHorizontalAlignment(SwingConstants.RIGHT);
		txttev.setFont(new Font("Tahoma", Font.BOLD, 12));
		txttev.setBounds(623, 35, 62, 20);
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

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_2.addTab("Yazici Tevkifat Kasesi", null, panel_6, null);
		panel_6.setLayout(null);

		chckbxNewCheckBox_1 = new JCheckBox("Tevkifat Kasesi");
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_1.isSelected())
				{
					panel_8.setVisible(true);
				}
				else
				{
					panel_8.setVisible(false);
				}
			}
		});
		chckbxNewCheckBox_1.setBounds(6, 7, 155, 23);
		panel_6.add(chckbxNewCheckBox_1);

		panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Tevkifat Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(167, 7, 571, 57);
		panel_8.setVisible(false);
		panel_6.add(panel_8);
		panel_8.setLayout(null);

		textField_11 = new Obs_TextFIeld();
		textField_11.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_11.setBounds(10, 24, 200, 20);
		panel_8.add(textField_11);
		textField_11.setColumns(10);

		textField_12 = new Obs_TextFIeld();
		textField_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_12.setColumns(10);
		textField_12.setBounds(258, 24, 200, 20);
		panel_8.add(textField_12);

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
				URUN_ARAMA arm ;
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				arm = new URUN_ARAMA();
				arm.setVisible(true);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
				if ( !  oac.stk_kodu.equals(""))
				{
					table.getModel().setValueAt( oac.stk_kodu,table.getSelectedRow() , 1) ;
					bilgi_doldur(oac.stk_kodu) ;
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-view-16.png")));
		toolBar_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean varmi = OBS_MAIN.pencere_bak("URUN KARTI");
				try {
					if (varmi  ) 
					{
						OBS_MAIN.pencere_aktiv_yap("URUN KARTI");
					}
					else
					{
						JInternalFrame internalFrame;
						internalFrame  = new URUN_KART();
						OBS_MAIN.desktopPane.add(internalFrame);
						internalFrame.setVisible(true);
					}
					URUN_KART.textField_1.setText(table.getValueAt(table.getSelectedRow() == - 1 ? 0 :table.getSelectedRow(), 1).toString());
				}
				catch (Exception ex) 
				{
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
					//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Urun Karti", JOptionPane.ERROR_MESSAGE);   
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-product-16.png")));
		toolBar_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabbedPane.getSelectedIndex() == 0)
				{
					satir_ilave();
					DefaultTableModel mdll = (DefaultTableModel) table.getModel();
					mdll.removeRow(mdll.getRowCount() -1);
				}
				else
				{
					DefaultTableModel mdl1 = (DefaultTableModel) table_1.getModel();
					mdl1.addRow(new Object[]{"","","","gg.AA.yyyy"});
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
					mdll.addRow(new Object[]{"","","",0.00,0,0,"",0,0.00,""});
				}
				else
				{
					if (table_1.getSelectedRow() < 0 ) return ;
					DefaultTableModel mdll = (DefaultTableModel) table_1.getModel();
					mdll.removeRow(table_1.getSelectedRow());
					table_1.repaint();
				}
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-reduce-16.png")));
		toolBar_1.add(btnNewButton_3);

		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				irs_aktar();
			}
		});
		btnNewButton_5.setToolTipText("Irsaliye Ekle");
		btnNewButton_5.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-compose-16.png")));
		toolBar_1.add(btnNewButton_5);

		tabbedPane = new MaterialTabbed();
		//tabbedPane.setForeground(new Color(0, 0, 128));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		splitPane_2.setRightComponent(tabbedPane);

		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();

		tabbedPane.addTab("Fatura", null, scrollPane, null);

		DefaultTableModel model = new DefaultTableModel() ; 
		table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {  
				switch (column) {
				case 6:
					return false;
				default:
					return true;
				}
			}
			public void changeSelection(final int row, final int column, boolean toggle, boolean extend)
			{
				super.changeSelection(row, column, toggle, extend);
				if (column < 2)
				{
					table.editCellAt(row, column);
					table.transferFocus();
				}
			}	
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				urun_bilgi_doldur(table.getValueAt(table.getSelectedRow(),1).toString()); 
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
			}
		});
		table.setCellSelectionEnabled(true);
		model.addColumn("Barkod", new String []{""});
		model.addColumn("Urun Kodu", new String []{""});
		model.addColumn("Depo", new String []{""});
		model.addColumn("Fiat", new Double [] {( 0.00 )});
		model.addColumn("Iskonto", new Double [] {( 0.00 )});
		model.addColumn("Miktar", new Double [] {( 0.000 )});
		model.addColumn("Birim", new String []{"" });
		model.addColumn("KDV",new Double [] {( 0.00 )});
		model.addColumn("Tutar",new Double [] {( 0.00 )});
		model.addColumn("Izahat", new String []{"" });
		TableColumn col ;
		listBarkod = new ArrayList<String> () ;
		stk_kodu_auto("Barkod");
		ComboBoxTableCellEditor editor = new ComboBoxTableCellEditor( listBarkod ,table,"fatura");
		col = table.getColumnModel().getColumn(0);
		col.setCellEditor(editor);
		col.setMinWidth(100);
		col.setHeaderRenderer(new SOLA());

		listSomeString = new ArrayList<String> () ;
		stk_kodu_auto("Kodu");
		col = table.getColumnModel().getColumn(1);
		ComboBoxTableCellEditor editorkodu = new ComboBoxTableCellEditor(  listSomeString ,table,"fatura");
		col.setCellEditor(editorkodu);
		col.setMinWidth(100);
		col.setHeaderRenderer(new SOLA());
		/**
	    col = table.getColumnModel().getColumn(1);
        Java2sAutoComboBox comboBox1 = new Java2sAutoComboBox( listSomeString,"fatura");
       comboBox1.setDataList(listSomeString);
       comboBox1.setMaximumRowCount(10);
       comboBox1.setFont(new Font("Tahoma", Font.BOLD, 12));
       col.setCellEditor( new ComboBoxCellEditor(comboBox1));
  		col.setMinWidth(110);
	    col.setHeaderRenderer(new SOLA());
	    comboBox1.getEditor().getEditorComponent().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					if (table.getSelectedRow() < 0 ) return ;
					URUN_ARAMA arm ;
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
					arm = new URUN_ARAMA();
					arm.setVisible(true);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
					JTextField zaq = (  JTextField )  comboBox1.getEditor().getEditorComponent();
					zaq.setText(GLOBAL.stk_kodu);
					table.getCellEditor().stopCellEditing();
				}
			}});
		 */
		////
		//**************

		col = table.getColumnModel().getColumn(2);
		listdepo = new ArrayList<String> () ;
		depo_auto();
		Java2sAutoComboBox combodp = new Java2sAutoComboBox( listdepo,"fatura");
		combodp.setDataList(listdepo);
		col.setCellEditor(new DefaultCellEditor(combodp));
		col.setMinWidth(130);
		col.setHeaderRenderer(new SOLA());

		col = table.getColumnModel().getColumn(3);
		col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setMinWidth(100);

		col = table.getColumnModel().getColumn(4);
		col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setMinWidth(100);

		col = table.getColumnModel().getColumn(5);
		col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(3) );
		col.setCellRenderer(new TABLO_RENDERER(3,true));
		col.setMinWidth(100);

		col = table.getColumnModel().getColumn(6);
		col.setMinWidth(75);
		DefaultTableCellRenderer r6 = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
						row, column);
				setFont(new Font(table.getFont().getFontName(),1 ,table.getFont().getSize()));
				setForeground(Color.BLUE);
				return this;
			}

		};
		col.setCellRenderer( r6);
		col.setHeaderRenderer(new SOLA());

		col = table.getColumnModel().getColumn(7);
		col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setMaxWidth(50);
		col.setMinWidth(50);

		col = table.getColumnModel().getColumn(8);
		col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,true));
		col.setMinWidth(135);

		col = table.getColumnModel().getColumn(9);
		col.setMinWidth(200);
		Obs_TextFIeld atf = new Obs_TextFIeld(40);
		
		col.setCellEditor(new DefaultCellEditor(atf));
		col.setHeaderRenderer(new SOLA());

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
		am.put("Action.NextCell", new NextCellActioin(table,"fatura"));
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		////
		DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
			Font font = new Font("Arial", 1, 12);
			@Override
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
						row, column);
				setFont(font);
				setForeground(Color.BLUE);
				return this;
			}
		};
		table.getColumnModel().getColumn(0).setCellRenderer(r);
		table.getColumnModel().getColumn(1).setCellRenderer(r);
		table.repaint();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					if (model.getRowCount() == 0) return ;
					if (table.getSelectedRow()  < 0) return;
					urun_bilgi_doldur(model.getValueAt(table.getSelectedRow() , 1).toString());
				}
			}
		});
		scrollPane.setViewportView(table);

		ScrollPaneWin11 scrollPane_1 = new ScrollPaneWin11();
		tabbedPane.addTab("Ilgili Irsaliye Bilgileri", null, scrollPane_1, null);

		DefaultTableModel model1 = new DefaultTableModel() ; 
		table_1 = new JTable(model1);
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table_1.setGridColor(oac.gridcolor);
		}

		model1.addColumn("Irsaliye", new String []{""});
		model1.addColumn("Hesap Kodu", new String []{""});
		model1.addColumn("Unvan", new String []{""});
		model1.addColumn("Tarih", new String []{"gg.AA.yyyy"});

		col = table_1.getColumnModel().getColumn(0);
		col.setMinWidth(100);
		col.setCellEditor(new TABLO_TEXTBOX(new JTextField() ,10,new Font("Tahoma", Font.PLAIN, 12),JTextField.LEFT));
		col.setHeaderRenderer(new SOLA());

		col = table_1.getColumnModel().getColumn(1);
		col.setMinWidth(100);
		col.setCellEditor(new TABLO_TEXTBOX(new JTextField() ,12,new Font("Tahoma", Font.PLAIN, 12),JTextField.LEFT));
		col.setHeaderRenderer(new SOLA());

		col = table_1.getColumnModel().getColumn(2);
		col.setCellEditor(new TABLO_TEXTBOX(new JTextField() ,50,new Font("Tahoma", Font.PLAIN, 12),JTextField.LEFT));
		col.setMinWidth(300);
		col.setHeaderRenderer(new SOLA());

		col = table_1.getColumnModel().getColumn(3);
		col.setMinWidth(100);
		col.setCellEditor(new TABLO_TEXTBOX(new JTextField() ,10,new Font("Tahoma", Font.PLAIN, 12),JTextField.LEFT));
		col.setHeaderRenderer(new SOLA());

		table_1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		th = table_1.getTableHeader();
		dd = table_1.getPreferredSize();
		dd.height = 30;
		th.setPreferredSize(dd); 
		table_1.setRowSelectionInterval(0, 0);
		table_1.setRowHeight(22);
		table_1.setShowHorizontalLines(true);
		table_1.setShowVerticalLines(true);
		scrollPane_1.setViewportView(table_1);

		cmbcins = new JComboBox<String>();
		cmbcins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS"))
				{
					setTitle("FATURA		- SATIS");
					textField.setText(".");
					textField.setText("");
					textField.requestFocus();
				}
				else
				{
					setTitle("FATURA		- ALIS");
					textField.setText(".");
					textField.setText("");
					textField.requestFocus();
				}
			}
		});
		cmbcins.setBackground(Color.WHITE);
		cmbcins.setForeground(Color.RED);
		cmbcins.setFont(new Font("Tahoma", Font.BOLD, 16));
		cmbcins.setModel(new DefaultComboBoxModel<String>(new String[] {"SATIS", "ALIS"}));
		cmbcins.setBounds(497, 78, 182, 22);
		panel_2.add(cmbcins);
		//stk_kodu_auto();
		ana_grup_doldur();
		fat_oz_kod();
		GRID_TEMIZLE.grid_temizle(table_1);
		//***********
		String deger;
		Integer sat_sayi;
		try {
			deger = GLOBAL.setting_oku("STK_FAT_SATIR").toString();
			sat_sayi =Integer.parseInt(deger);
			for (int i = 0; i <= sat_sayi -1 ; i ++)
			{
				satir_ilave();
			}
			txtdoviz.setText(GLOBAL.setting_oku("PRG_PARA").toString());
			
			String[] parts;
			Font bigFont;
			deger = GLOBAL.setting_oku("STK_FAT_GIRIS").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			table.setFont(bigFont);

		} catch (Exception ex) {

		}
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

			JButton button_1 = new JButton("");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					//getContentPane().setCursor(oac.WAIT_CURSOR);
					son_fisoku();
					//getContentPane().setCursor(oac.DEFAULT_CURSOR);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
			button_1.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-view-16.png")));
			button_1.setToolTipText("Son Fis");
			button_1.setBounds(209, 7, 26, 23);
			panel_2.add(button_1);

		
		//***********
		table.getModel().addTableModelListener(	(TableModelListener) new TableModelListener() 
		{		@SuppressWarnings("unused")
		@Override
			public void tableChanged(TableModelEvent e) {
			TableModel model = (TableModel)e.getSource();
			if (model.getRowCount() > 0) {
				int row;
				row = table.getSelectedRow();     //e.getFirstRow();
				int column = e.getColumn();
				//      Object data = model.getValueAt(row, column);
				if (column == 3)  //FIAT
				{
					double fiat ,miktar = 0 ;
					fiat =  Double.parseDouble(model.getValueAt(row, 3).toString());
					miktar = Double.parseDouble(model.getValueAt(row, 5).toString());
					model.setValueAt( fiat * miktar,row, 8)  ;
				}
				if (column == 5)  //MIKTAR
				{
					double fiat ,miktar = 0 ;
					fiat =  Double.parseDouble(model.getValueAt(row, 3).toString());
					miktar = Double.parseDouble(model.getValueAt(row, 5).toString());
					model.setValueAt( fiat * miktar,row, 8)  ;
				}
				//** Bakiye Kontrol
				String deger ="";
				Integer sat_sayi = 0;
				if (column == 1)
				{
					try {
						deger = GLOBAL.setting_oku("STK_STOK_KONTROL").toString();
						sat_sayi = Integer.parseInt(deger);
					} catch (Exception ex) {		} 
					if (! deger.equals("-1"))
					{
						if ( table.getValueAt(row,1).toString().equals(""))
						{	 return ;		 }
						boolean durum = false ;

						try {
							durum = f_Access.stok_bak_kontrol(table.getValueAt(row,1).toString());
						} catch (Exception ex) {		} 

						if (durum)
						{
						
							OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Bu Urun Bakiyesi Eksi Konumdadir....");
							//JOptionPane.showMessageDialog(null, "Bu Urun Bakiyesi Eksi Konumdadir....",   "Stok Bakiye Kontrol", JOptionPane.ERROR_MESSAGE);              
						}
						else
						{
							//  ' Stokta - Bakiye Degil
							//Cursor = System.Windows.Forms.Cursors.Default
						}
					}
				}
				//*** barkod
				if (column == 0)
				{

					try {
						deger = GLOBAL.setting_oku("STK_STOK_KONTROL").toString();
						sat_sayi = Integer.parseInt(deger);
					} catch (Exception ex) {		} 
					if (! deger.equals("-1"))
					{
						if ( table.getValueAt(row,1).toString().equals(""))
						{
							return ;
						}
						boolean durum = false ;

						try {
							durum = f_Access.stok_bak_kontrol_barcode(table.getValueAt(row,0).toString());
						} catch (Exception ex) {		} 

						if (durum)
						{
						
							OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Bu Urun Bakiyesi Eksi Konumdadir....");
							//JOptionPane.showMessageDialog(null, "Bu Urun Bakiyesi Eksi Konumdadir....",   "Stok Bakiye Kontrol", JOptionPane.ERROR_MESSAGE);              
						}
						else
						{
							//  ' Stokta - Bakiye Degil
							//Cursor = System.Windows.Forms.Cursors.Default
						}
					}
				}			
				//**
			}
			toplam();
		}
		});
		textField.requestFocus();
	}
	//******************************************************************************************************
	public static void kaydet()
	{
		if (textField.getText().equals("")) return ;
		if(dtc.getDate() == null) return;
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		if (mdl.getRowCount() == 0)  return;
		try
		{
			long startTime = System.currentTimeMillis(); 
			tar = TARIH_CEVIR.tarih_geri_saatli(dtc) ;
			GuiUtil.setWaitCursor(FATURA.splitPane,true);
			satir_yaz_1();
			dipnot_yaz();
			irsaliye_yaz();
			acik_yaz();
			//************* STOK YAZ *************
			stok_isle();
			//************************************
			GuiUtil.setWaitCursor(FATURA.splitPane,false);
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime;
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			//**** Cari Dekont Kontrol ***********
			if ( yeni_fat == false ) // ' eski kayit
			{
				ResultSet rss = null;

				rss = c_Access.evrak_ogren(textField.getText());

				boolean result ;
				if (!rss.isBeforeFirst() ) {
					result = false;
				}
				else
				{
					rss.next();
					String sonuc = rss.getString("EVRAK");
					if ( sonuc.equals(""))
					{
						result = false;
					}
					else
					{
						result = true;
					}
				}
				if  (result)
				{
					int g = JOptionPane.showOptionDialog( null, "Cariye onceden Kayit Yapilmis.. Dekont Acilsin mi ? ", "Cari Fatura Kayit",   JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,null, oac.options, oac.options[1]); 
					if(g != 0 ) { 
						textField.setText("");
						textField.requestFocus();
						return;
					}	
					else
					{
						cari_kontrol() ;
					}
				}
			}
			//'************************************
			textField.setText("");
			textField.requestFocus();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Kayit", JOptionPane.ERROR_MESSAGE);             
		}
	}
	private void fiskont()
	{
		try {
			long startTime = System.currentTimeMillis();
			ResultSet rss = null;
			if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			{

				rss = f_Access.fatura_oku(textField.getText(), "C");

			}
			else
			{

				rss = f_Access.fatura_oku(textField.getText(), "G");

			}
			if (!rss.isBeforeFirst() ) {  
				txtadres.setText("");
				yeni_fat = true;
				GRID_TEMIZLE.grid_temizle(table);
				GRID_TEMIZLE.grid_temizle(table_1);
				sifirla();
			}
			else
			{
				rss.next();
				yeni_fat = false;
				//txtadres.setText("");
				GRID_TEMIZLE.grid_temizle(table);
				GRID_TEMIZLE.grid_temizle(table_1);
				sifirla();
				dtc.setDate(rss.getDate("Tarih"));
				txtadres.setText(rss.getString("Adres_Firma"));
				txtcari.setText(rss.getString("Cari_Firma"));
				txtdoviz.setText(rss.getString("Doviz"));
				txtkur.setText(FORMATLAMA.doub_4(rss.getDouble("Kur")));
				cmbozkod.setSelectedItem(rss.getString("Ozel_Kod"));
				//  '***********GRUP DOLDUR
				ResultSet rsa=null;

				rsa = f_Access.urun_kod_degisken_ara("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN",String.valueOf(rss.getInt("Ana_Grup")));

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

				rsa = f_Access.urun_kod_degisken_ara("ALT_GRUP", "ALID_Y", "ALT_GRUP_DEGISKEN",String.valueOf(rss.getInt("Alt_Grup")));

				if (!rsa.isBeforeFirst() ) {  
					cmbaltgrup.setSelectedItem("");
				} 
				else
				{
					rsa.next();
					cmbaltgrup.setSelectedItem(rsa.getString("ALT_GRUP"));
				}
				//  '************ACIKLAMA OKU ***********************************************************
				if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
				{

					textField_9.setText( f_Access.aciklama_oku("FAT", 1, textField.getText(), "C"));
					textField_10.setText(f_Access.aciklama_oku("FAT", 2, textField.getText(), "C"));

				}
				else
				{

					textField_9.setText(f_Access.aciklama_oku("FAT", 1, textField.getText(), "G"));
					textField_10.setText(f_Access.aciklama_oku("FAT", 2, textField.getText(), "G"));

				}
				// '***************** IRSALIYE NOLARI DOLDUR ********************************************
				rsa = null;
				if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
				{

					rsa =   f_Access.irsaliye_no_doldur( textField.getText(), "C");

				}
				else
				{

					rsa =   f_Access.irsaliye_no_doldur( textField.getText(), "G");

				}
				if (!rsa.isBeforeFirst() ) 
				{  
					DefaultTableModel mdl1 = (DefaultTableModel) table_1.getModel();
					mdl1.addRow(new Object[]{});
				} 
				else
				{
					ResultSet rsh =null;
					DefaultTableModel mdl2 = (DefaultTableModel) table_1.getModel();
					while (rsa.next())
					{

						rsh = c_Access.hesap_adi_oku(rsa.getString("Cari_Hesap_Kodu"));

						rsh.next();
						mdl2.addRow(new Object [] {rsa.getString("Irsaliye_No"), rsa.getString("Cari_Hesap_Kodu"), rsh.getString("UNVAN") + " / " + rsh.getString("HESAP_CINSI"), rsh.getDate("Tarih")});
					}
				}
				// '*************************************************************************************
				rss.first();   
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				int satir =0 ;
				do
				{
					mdl.insertRow(satir,new Object[]{rss.getString("Barkod"),rss.getString("Kodu"),rss.getString("Depo"),
							rss.getDouble("Fiat"),rss.getDouble("Iskonto"),rss.getDouble("Miktar"),
							rss.getString("Birim"),rss.getDouble("Kdv"),  Math.abs(rss.getDouble("Tutar")),rss.getString("Izahat")});
					txttev.setText(FORMATLAMA.doub_0(rss.getDouble("Tevkifat")));
					satir +=1 ;
					mdl.removeRow(mdl.getRowCount() -1);
				}  while (rss.next()) ;
				dipnot_oku();
				urun_bilgi_doldur(mdl.getValueAt(0,1).toString()); 
				toplam();
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			}
		}
		catch (Exception ex)
		{
			GuiUtil.setWaitCursor(FATURA.splitPane,false);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Fis Kontrol", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void sifirla()
	{
		String deger;
		Integer sat_sayi;
		try {
			deger = GLOBAL.setting_oku("STK_FAT_SATIR").toString();
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
		textField_9.setText("");
		textField_10.setText("");
		txtkur.setText("0.0000");
		lblNewLabel_12.setText("....");
		label_5.setText("");
		dtc.setDate(new Date());
		imagePanel.setImage(null);
	}
	private static void cari_kontrol() 
	{
		try {
			boolean varmi = OBS_MAIN.pencere_bak("DEKONT");
			if (varmi  ) 
			{
				OBS_MAIN.pencere_aktiv_yap("DEKONT");
			}
			else
			{
				JInternalFrame internalFrame;
				internalFrame  = new DEKONT();
				OBS_MAIN.desktopPane.add(internalFrame);
				internalFrame.setVisible(true);
			}
			ResultSet rss = null;
			rss = c_Access.evrak_ogren(textField.getText());
			String sonuc = "" ;
			if (!rss.isBeforeFirst() ) {
			}
			else
			{
				rss.next();
				sonuc = rss.getString("EVRAK");
				DEKONT.txtevrak.setText(sonuc);
				DEKONT.fiskont();
			}
		} 
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Cari Kontrol", JOptionPane.ERROR_MESSAGE);            
		}
	}
	private static void satir_yaz_1 ()
	{
		try {
			lOG_BILGI lBILGI = new lOG_BILGI();
			
			
			if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			{
				lBILGI.setmESAJ(textField.getText() + " Nolu Cikis Fatura Silindi");
				lBILGI.seteVRAK(textField.getText());
				
				f_Access.fat_giris_sil(textField.getText(), "C" ,lBILGI,BAGLAN_LOG.fatLogDizin);
			}
			else
			{
				lBILGI.setmESAJ(textField.getText() + " Nolu Giris Fatura Silindi");
				lBILGI.seteVRAK(textField.getText());
				f_Access.fat_giris_sil(textField.getText(), "G" ,lBILGI,BAGLAN_LOG.fatLogDizin);
			}
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			for (int  i = 0 ; i <=  mdl.getRowCount() - 1 ; i++)
			{
				//  Progres_Bar(RG1.Rows.Count - 1, i)
				if (! mdl.getValueAt(i,1).toString().equals(""))
				{
					sat_yaz_2(i);
				}
			}
			// Progres_Bar_Temizle()
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Satyz1", JOptionPane.ERROR_MESSAGE);             
		}
	}
	private static void sat_yaz_2(int i)
	{
		try {
			String gircik, izahat ;
			double  miktar, kur ;
			int angrp, altgrp, depo;
			depo = 0 ;
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			ResultSet rs =null ;
			if (mdl.getValueAt(i,2) == null)
			{
				depo = 0 ;
			}
			else
			{
				rs = f_Access.urun_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN",  mdl.getValueAt(i,2).toString());
				if (!rs.isBeforeFirst() ) {      		
				}
				else
				{
					rs.next();
					depo = rs.getInt("DPID_Y");
				}
			}
			gircik = "";
			if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			{
				miktar = Double.parseDouble( mdl.getValueAt(i,5).toString());
				miktar = miktar * -1;
				gircik = "C" ;
			}
			else
			{
				miktar = Double.parseDouble( mdl.getValueAt(i,5).toString());
				gircik = "G";
			}
			double tutar ;
			tutar =Double.parseDouble(mdl.getValueAt(i,8).toString());
			if ( mdl.getValueAt(i,9).toString().equals(""))
			{
				izahat = "" ;
			}
			else
			{
				izahat =  mdl.getValueAt(i,9) .toString();
			}
			kur = DecimalFormat.getNumberInstance().parse(txtkur.getText()).doubleValue();
			angrp = 0 ;
			if ( ! cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals("") ) {

				rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString());

				if (!rs.isBeforeFirst() ) {      		
				}
				else
				{
					rs.next();
					angrp  = rs.getInt("AGID_Y");
				}
			}
			altgrp = 0;
			if ( ! cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString().equals("") ) {

				rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN",  cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString());

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
			fiat = Double.parseDouble( mdl.getValueAt(i,3).toString());
			double isk = 0 ;
			isk = Double.parseDouble( mdl.getValueAt(i,4).toString());
			double kdv = 0 ; 
			kdv =Double.parseDouble( mdl.getValueAt(i,7).toString());

			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ(gircik + " Fatura Kayit" +  mdl.getValueAt(i,1).toString() + " Mik=" + miktar + " Tut=" + tutar);
			lBILGI.seteVRAK(textField.getText());
			
			f_Access.fat_kaydet(textField.getText(),  mdl.getValueAt(i,1).toString(), depo,fiat , tevk,
					miktar, gircik, tutar ,isk,kdv,
					tar, izahat, txtdoviz.getText(), txtadres.getText(), txtcari.getText(), 
					cmbozkod.getItemAt(cmbozkod.getSelectedIndex()).toString(), kur, "", angrp, altgrp, GLOBAL.KULL_ADI
					,lBILGI,BAGLAN_LOG.fatLogDizin);

		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Satyz2", JOptionPane.ERROR_MESSAGE);     
		}
	}
	private static void stok_isle() 
	{
		try {
			lOG_BILGI lBILGI = new lOG_BILGI();
			
			
			if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			{
				lBILGI.setmESAJ("Fatura Stok Silme");
				lBILGI.seteVRAK(textField.getText());
				f_Access.stok_sil(textField.getText(), "FAT", "C",lBILGI, BAGLAN_LOG.fatLogDizin);
			}
			else
			{
				lBILGI.setmESAJ("Fatura Stok Silme");
				lBILGI.seteVRAK(textField.getText());
				f_Access.stok_sil(textField.getText(), "FAT", "G",lBILGI, BAGLAN_LOG.fatLogDizin);
			}
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			for (int  i = 0 ; i <=  mdl.getRowCount() - 1 ; i++)
				if (! mdl.getValueAt(i,1).toString().equals(""))
				{
					stk_yaz_2(i);
				}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Stokisl", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private static void stk_yaz_2(int sat)
	{
		try {
			double miktar ,tutar,isk,kdvlitut,kur ;
			String  har, izah ;
			int anagrp, altgrp, depo ;
			depo = 0 ;
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			ResultSet rs =null ;
			if (mdl.getValueAt(sat,2).toString().equals(""))
			{
				depo = 0 ;
			}
			else
			{
				rs = f_Access.urun_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN",  mdl.getValueAt(sat,2).toString());

				if (!rs.isBeforeFirst() ) {      		
				}
				else
				{
					rs.next();
					depo = rs.getInt("DPID_Y");
				}
			}
			har = "";
			izah = "";
			if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			{
				miktar = Double.parseDouble( mdl.getValueAt(sat,5).toString());
				miktar = miktar * -1;
				tutar =  Double.parseDouble( mdl.getValueAt(sat,8).toString());
				isk =   Double.parseDouble( mdl.getValueAt(sat,4).toString());
				tutar = tutar - ((tutar * isk) / 100);
				tutar =  tutar * -1;
				kdvlitut = sat_toplam( Double.parseDouble( mdl.getValueAt(sat,8).toString()), Double.parseDouble( mdl.getValueAt(sat,4).toString())
						, Double.parseDouble( mdl.getValueAt(sat,7).toString()), DecimalFormat.getNumberInstance().parse(txttev.getText()).doubleValue() );
				kdvlitut =  kdvlitut * -1;
				har = "C";
				izah = textField.getText() + " Nolu Satis Faturasi...";
			}
			else 
			{
				miktar =  Double.parseDouble( mdl.getValueAt(sat,5).toString());
				tutar =  Double.parseDouble( mdl.getValueAt(sat,8).toString());
				isk =  Double.parseDouble( mdl.getValueAt(sat,4).toString());
				kdvlitut = sat_toplam( Double.parseDouble( mdl.getValueAt(sat,8).toString()), Double.parseDouble( mdl.getValueAt(sat,4).toString())
						, Double.parseDouble( mdl.getValueAt(sat,7).toString()), DecimalFormat.getNumberInstance().parse(txttev.getText()).doubleValue() );	           
				har = "G" ;
				izah = textField.getText() + " Nolu Giris Faturasi...";
			}
			anagrp = 0 ;
			if ( ! cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals("") ) {

				rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",  cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString());

				if (!rs.isBeforeFirst() ) {      		
				}
				else
				{
					rs.next();
					anagrp  = rs.getInt("AGID_Y");	
				}

			}
			altgrp = 0;
			if ( ! cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString().equals("") ) {

				rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN",  cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString());

				if (!rs.isBeforeFirst() ) {      		
				}
				else
				{
					rs.next();
					altgrp  = rs.getInt("ALID_Y");
				}
			}
			kur = 0;
			kur = DecimalFormat.getNumberInstance().parse(txtkur.getText()).doubleValue();
			double fiat =0 ;
			fiat = Double.parseDouble( mdl.getValueAt(sat,3).toString());

			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ( "Fatura Stok Kayit  H:"+ har + "   Kod:" + mdl.getValueAt(sat,1).toString() + " Miktar:" + miktar + " Fiat:" + fiat );
			lBILGI.seteVRAK(textField.getText());
			
			f_Access.stk_kaydet(textField.getText(), "FAT", tar, depo,  mdl.getValueAt(sat,1).toString(), miktar, fiat
					,(double) Math.round(tutar), kdvlitut, har, izah, anagrp, altgrp, kur, "", txtdoviz.getText(), txtcari.getText(),GLOBAL.KULL_ADI,
				 lBILGI,BAGLAN_LOG.fatLogDizin);

		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Stkyz2", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private static double sat_toplam(double tutar,double isk ,double kdv ,double tev )
	{
		double double_0, double_1, double_2;
		double_1 = (tutar * isk) / 100  ; //' iskonto
		double_2 = ((tutar - (tutar * isk) / 100) * kdv) / 100 ; //' kdv
		//'**********Tevkif Islemi **********************************************************
		double_0 = ((double) Math.round(tutar) - (double) Math.round(double_1)) + ((double) Math.round(double_2) - ((double) Math.round(double_2) / 10) * (double) Math.round(tev));
		return double_0;

	}
	private static void dipnot_yaz()
	{
		try {
			dipnot_sil();
			lOG_BILGI lBILGI = new lOG_BILGI();
			
			
			if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			{
				lBILGI.setmESAJ( "Fatura Dip Not Yaz : "  +textField_5.getText() );
				lBILGI.seteVRAK(textField.getText());
				
				f_Access.dipnot_yaz(textField.getText(), textField_5.getText(),textField_6.getText(),textField_8.getText(), "F", "C",GLOBAL.KULL_ADI,
						lBILGI,BAGLAN_LOG.fatLogDizin);
			}
			else
			{
				lBILGI.setmESAJ( "Fatura Dip Not Kayit :"+ textField_5.getText()  );
				lBILGI.seteVRAK(textField.getText());
				f_Access.dipnot_yaz(textField.getText(), textField_5.getText(),textField_6.getText(),textField_8.getText(), "F", "G",GLOBAL.KULL_ADI,
						lBILGI,BAGLAN_LOG.fatLogDizin);
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Dipnotyz", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private static void dipnot_sil()
	{
		try {
			lOG_BILGI lBILGI = new lOG_BILGI();
			if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			{
				lBILGI.setmESAJ("Fatura Dip Not Sil "  );
				lBILGI.seteVRAK(textField.getText());
				
				f_Access.dipnot_sil(textField.getText(), "F", "C",
						lBILGI,BAGLAN_LOG.fatLogDizin);
			}
			else
			{
				lBILGI.setmESAJ("Fatura Dip Not Sil "  );
				lBILGI.seteVRAK(textField.getText());
				f_Access.dipnot_sil(textField.getText(), "F", "G",
					lBILGI,BAGLAN_LOG.fatLogDizin);
			}
		}
		catch (Exception ex)
		{

		}
	}
	private static void acik_yaz()
	{
		try {
			acik_sil();
			lOG_BILGI lBILGI = new lOG_BILGI();
			if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			{
				lBILGI.setmESAJ("Fatura Aciklama Yaz  C : "  +  textField_9.getText() );
				lBILGI.seteVRAK(textField.getText());
				
				f_Access.aciklama_yaz("FAT", 1, textField.getText(),  textField_9.getText(), "C" ,
						 lBILGI,BAGLAN_LOG.fatLogDizin);
				
				lBILGI.setmESAJ("Fatura Aciklama Yaz  C : "  +  textField_10.getText()  );
				
				f_Access.aciklama_yaz("FAT", 2, textField.getText(), textField_10.getText(), "C",
						 lBILGI,BAGLAN_LOG.fatLogDizin);

			}
			else
			{
				lBILGI.setmESAJ("Fatura Aciklama Yaz  Giris :" + textField_9.getText()  );
				lBILGI.seteVRAK(textField.getText());
				f_Access.aciklama_yaz("FAT", 1, textField.getText(),  textField_9.getText(), "G",
						lBILGI,BAGLAN_LOG.fatLogDizin);
				lBILGI.setmESAJ( "Fatura Aciklama Yaz  Giris :" + textField_10.getText() );
				f_Access.aciklama_yaz("FAT", 2, textField.getText(), textField_10.getText(), "G",
						lBILGI,BAGLAN_LOG.fatLogDizin);

			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Acikyz", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private static void acik_sil()
	{
		try {
			lOG_BILGI lBILGI = new lOG_BILGI();
			if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			{
				lBILGI.setmESAJ( "Fatura Aciklama Sil  Cikis " );
				lBILGI.seteVRAK(textField.getText());
				
				f_Access.aciklama_sil("FAT", textField.getText(), "C",
						lBILGI,BAGLAN_LOG.fatLogDizin);
			}
			else
			{
				lBILGI.setmESAJ( "Fatura Aciklama Sil  Giris "  );
				lBILGI.seteVRAK(textField.getText());
				f_Access.aciklama_sil("FAT", textField.getText(), "G",
						lBILGI,BAGLAN_LOG.fatLogDizin);
			}
		}
		catch (Exception ex)
		{

		}
	}
	private static void  irsaliye_yaz()
	{
		try {
			for (int i = 0 ; i <= table_1.getRowCount() -1 ; i++)
			{
				DefaultTableModel mdl = (DefaultTableModel) table_1.getModel();
				if ( mdl.getValueAt(i,0) != null)
				{

					f_Access.fat_no_yaz( mdl.getValueAt(i,0).toString(), textField.getText());  // Irsaliyeye Fat No yazma

				}
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Irsyaz", JOptionPane.ERROR_MESSAGE);   
		}
	}
	public static void satir_ilave()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		int satir = table.getSelectedRow();
		if ( satir  < 0 ) 
		{
			mdl.addRow(new Object[]{"","","",0.00,0,0.000,"",0,0.00,""});
			satir = 0 ;
		}
		else
		{
			mdl.insertRow(satir, new Object[]{"","","",0.00,0,0.000,"",0,0.00,""});
		}

		table.isRowSelected(satir);
		table.repaint();
	}
	public static void satir_sil()
	{
		if (table.getSelectedRow() < 0 ) return ;
		DefaultTableModel mdll = (DefaultTableModel) table.getModel();
		mdll.removeRow(table.getSelectedRow());
		table.repaint();
		toplam();
	}
	public static void bilgi_doldur(String cins) 
	{
		try {
			ResultSet rs = null;
			if (table.getSelectedColumn() == 0)  // Barkod
			{
				if (cins.equals(""))
				{
					lblNewLabel_12.setText("");
					label_5.setText("");
					table.getModel().setValueAt("",table.getSelectedRow(), 1) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 3) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 4) ;
					table.getModel().setValueAt(0.000,table.getSelectedRow(), 5) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 6) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 7) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 8) ;
					toplam();
					return;
				}

				rs = f_Access.urun_adi_oku(cins,"Barkod");

				if (!rs.isBeforeFirst() ) {  
					lblNewLabel_12.setText("");
					label_5.setText("");
					table.getModel().setValueAt("",table.getSelectedRow(), 1) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 3) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 4) ;
					table.getModel().setValueAt(0.000,table.getSelectedRow(), 5) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 6) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 7) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 8) ;
				}
				else
				{
					
					rs.next();
					lblNewLabel_12.setText(rs.getString("Adi"));
					label_5.setText(rs.getString("Ana_Grup") + "  /  " + rs.getString("Alt_Grup") );

					table.getModel().setValueAt(rs.getString("Kodu"),table.getSelectedRow(), 1) ;
					table.getModel().setValueAt(rs.getString("Birim"),table.getSelectedRow(), 6) ;
					//
					resim_doldur( rs.getBytes("Resim"));
					//
					if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_1"))
					{
						table.getModel().setValueAt(rs.getDouble("Fiat"),table.getSelectedRow(), 3) ;
					}
					else if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_2"))
					{
						table.getModel().setValueAt(rs.getDouble("Fiat_2"),table.getSelectedRow(),3) ;
					}
					if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_3"))
					{
						table.getModel().setValueAt(rs.getDouble("Fiat_3"),table.getSelectedRow(),3) ;
					}
					if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Son Alis/Satis Fiati"))
					{
						ResultSet rss = null;

						rss = f_Access.son_satis_fiati_oku(rs.getString("Kodu"),txtcari.getText(), cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") ? "C" : "G");

						if (!rss.isBeforeFirst() ) {  
							table.getModel().setValueAt(0.00,table.getSelectedRow(),3) ;
						}
						else
						{
							rss.next();
							table.getModel().setValueAt(rss.getDouble("Fiat"),table.getSelectedRow(),3) ;
						}
					}
				}
				toplam();
			}
			/////////////////////////////////////////////////////////
			else if (table.getSelectedColumn() == 1)  // URUN KODU
			{
				if (cins.equals(""))
				{
					lblNewLabel_12.setText("");
					label_5.setText("");
					table.getModel().setValueAt("",table.getSelectedRow(), 0) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 3) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 4) ;
					table.getModel().setValueAt(0.000,table.getSelectedRow(), 5) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 6) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 7) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 8) ;
					toplam();
					return;
				}

				rs = f_Access.urun_adi_oku(cins,"Kodu");

				if (!rs.isBeforeFirst() ) {  
					lblNewLabel_12.setText("");
					label_5.setText("");
					table.getModel().setValueAt("",table.getSelectedRow(), 0) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 3) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 4) ;
					table.getModel().setValueAt(0.000,table.getSelectedRow(), 5) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 6) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 7) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 8) ;
				}
				else
				{
					rs.next();
					lblNewLabel_12.setText(rs.getString("Adi"));
					label_5.setText(rs.getString("Ana_Grup") == null ? "":rs.getString("Ana_Grup")  + "  /  " + 
							rs.getString("Alt_Grup") == null ? "":rs.getString("Alt_Grup"));
					table.getModel().setValueAt(rs.getString("Barkod"),table.getSelectedRow(), 0) ;
					table.getModel().setValueAt(rs.getString("Birim"),table.getSelectedRow(), 6) ;
					//
					resim_doldur( rs.getBytes("Resim"));
					//
					if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_1"))
					{
						table.getModel().setValueAt(rs.getDouble("Fiat"),table.getSelectedRow(), 3) ;
					}
					else if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_2"))
					{
						table.getModel().setValueAt(rs.getDouble("Fiat_2"),table.getSelectedRow(),3) ;
					}
					if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_3"))
					{
						table.getModel().setValueAt(rs.getDouble("Fiat_3"),table.getSelectedRow(),3) ;
					}
					if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Son Alis/Satis Fiati"))
					{
						ResultSet rss = null;

						rss = f_Access.son_satis_fiati_oku(rs.getString("Kodu"),txtcari.getText(), cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") ? "C" : "G");

						if (!rss.isBeforeFirst() ) {  
							table.getModel().setValueAt(0.00,table.getSelectedRow(),3) ;
						}
						else
						{
							rss.next();
							table.getModel().setValueAt(rss.getDouble("Fiat"),table.getSelectedRow(),3) ;
						}
					}
				}
				toplam();
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null,  ex.getMessage(), "Bilgi Doldur", JOptionPane.ERROR_MESSAGE);
		}
	}
	private  void stk_kodu_auto(String field)
	{
		try {
			ResultSet rs = null;

			rs = f_Access.stk_barkod_kod_oku(field);

			if (!rs.isBeforeFirst() ) {  
				if (field.equals("Kodu"))
				{
					listSomeString.add("");
				}
				else
				{
					listBarkod.add("");
				}
			}
			else
			{
				if (field.equals("Kodu"))
				{
					listSomeString.clear();
					listSomeString.add("");
					while (rs.next())
					{
						listSomeString.add(rs.getString("Kodu").toString());
					}
				}
				else
				{
					listBarkod.clear();
					listBarkod.add("");
					while (rs.next())
					{
						listBarkod.add(rs.getString("Barkod").toString());
					}
				}
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null,  ex.getMessage(), "Otomatik Doldurma", JOptionPane.ERROR_MESSAGE);
		}
	}

	private  void depo_auto()
	{
		try {
			ResultSet rs = null;

			rs = f_Access.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");

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
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null,  ex.getMessage(), "Depo Doldur", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void ana_grup_doldur()
	{
		try {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
			cmbanagrup .removeAllItems();
			ResultSet rs=null;

			rs = f_Access.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");

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
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Ana Grup", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void alt_grup_doldur()
	{
		try {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
			cmbaltgrup.removeAllItems();
			cmbaltgrup .addItem("");
			ResultSet rs=null;

			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				int in1 = rs.getInt("AGID_Y");
				rs =null;
				rs = f_Access.stk_kod_alt_grup_degisken_oku(in1);
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
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Alt Grup", JOptionPane.ERROR_MESSAGE);    	
		}
	}
	private void fat_oz_kod()
	{
		try {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
			cmbozkod .removeAllItems();
			ResultSet rs=null;

			rs = f_Access.fat_oz_kod(cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") ? "C" : "G");

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
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Ozel Kod", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private static void toplam()
	{
		try {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			double  double_0, double_1 = 0, double_2 = 0, double_3 = 0, double_4, double_5 = 0   ;
			int urunsayi = 0 ;
			for (int  i = 0 ; i <= table.getRowCount() -1 ; i ++)
			{
				double_5 += Double.parseDouble(model.getValueAt(i, 8).toString());
				double_1 += (Double.parseDouble(model.getValueAt(i, 8).toString()) * (Double.parseDouble(model.getValueAt(i, 4).toString()))) / 100 ; 
				double_2 += (( Double.parseDouble(model.getValueAt(i, 8).toString()) - ( Double.parseDouble(model.getValueAt(i, 8).toString()) *  Double.parseDouble(model.getValueAt(i, 4).toString())) / 100) *  Double.parseDouble(model.getValueAt(i, 7).toString())) / 100 ; // kdv
				double_3 +=  Double.parseDouble(model.getValueAt(i, 5).toString());
				if (! model.getValueAt(i,1).toString().equals(""))
				{
					urunsayi += 1;
				}
			}
			label_8.setText(FORMATLAMA.doub_3(double_3));
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
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Toplam", JOptionPane.ERROR_MESSAGE);   
		}

	}
	private void dipnot_oku()
	{
		try {
			ResultSet rs =null ;
			if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			{

				rs =    f_Access.dipnot_oku(textField.getText(), "F", "C");

			}
			else
			{

				rs =    f_Access.dipnot_oku(textField.getText(), "F", "G");

			}
			if (!rs.isBeforeFirst() ) {  
			} 
			else
			{
				rs.next();
				textField_5.setText(rs.getString("Bir"));
				textField_6.setText(rs.getString("Iki"));
				textField_8.setText(rs.getString("Uc"));
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Dipnot", JOptionPane.ERROR_MESSAGE);   
		}
	}
	public static void fatura_sil()
	{
		try {
			if (textField.getText().equals("")) return ;
			if (table.getRowCount() == 0) return;
			int g =  JOptionPane.showOptionDialog( null,  "Kayit Dosyadan Silinecek ..?"  ,
					"Fatura Dosyasindan Evrak Silme",   JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,     //no custom icon
					oac.options,  //button titles
					oac.options[1]); //default button
			if(g != 0 ) { return;	}
			long startTime = System.currentTimeMillis();
			lOG_BILGI lBILGI = new lOG_BILGI();
			
			
			if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			{

				lBILGI.setmESAJ(textField.getText() + " Nolu Cikis Fatura Silindi");
				lBILGI.seteVRAK(textField.getText());
				
				f_Access.fat_giris_sil(textField.getText(), "C" ,lBILGI,BAGLAN_LOG.fatLogDizin);

			}
			else
			{
				lBILGI.setmESAJ(textField.getText() + " Nolu Giris Fatura Silindi");
				lBILGI.seteVRAK(textField.getText());
				f_Access.fat_giris_sil(textField.getText(), "G" ,lBILGI,BAGLAN_LOG.fatLogDizin);

			}

			dipnot_sil();
			acik_sil();
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime;
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			// '**** Cari Dekont Kontrol ***********
			if ( yeni_fat == false ) // ' eski kayit
			{
				ResultSet rss = null;

				rss = c_Access.evrak_ogren(textField.getText());

				boolean result ;
				if (!rss.isBeforeFirst() ) {
					result = false;
				}
				else
				{
					rss.next();
					String sonuc = rss.getString("EVRAK");
					if ( sonuc.equals(""))
					{
						result = false;
					}
					else
					{
						result = true;
					}
				}
				if  (result)
				{
					int gg = JOptionPane.showOptionDialog( null, "Cariye onceden Kayit Yapilmis.. Dekont Acilsin mi ? ", "Cari Fatura Kayit",   JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,null, oac.options, oac.options[1]); 
					if(gg != 0 ) { 
						textField.setText("");
						textField.requestFocus();
						return;
					}	
					else
					{
						cari_kontrol() ;
					}
				}
			}
			//  '************************************
			textField.setText("");
			textField.requestFocus();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Silme", JOptionPane.ERROR_MESSAGE);   
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
			// '***************** hsp cinsleri ogren***********************BORCLU HESAP
			if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			{
				hsp.lblNewLabel.setText("Alacakli Hesap");
				hsp.setVisible(true);
				alh = oac.hsp_hsp_kodu;
				bh = txtcari.getText();
				if (alh.equals("")) return ;
			}
			else if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("ALIS") )
			{
				hsp.lblNewLabel.setText("Borclu Hesap");
				hsp.setVisible(true);
				bh = oac.hsp_hsp_kodu;
				alh = txtcari.getText();
				if (bh.equals("")) return ;
			}
			ResultSet rs ;
			if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			{
				//*******************************************************************************

				rs = c_Access.hesap_adi_oku(alh);

				if (!rs.isBeforeFirst() ) {  
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Girilen Alacakli Hesap Kodunda  bir  hesaba rastlanmadi!!!!");
					//JOptionPane.showMessageDialog(null,  "Girilen Alacakli Hesap Kodunda  bir  hesaba rastlanmadi!!!!",  "Fatura Cari Kaydetme", JOptionPane.ERROR_MESSAGE); 
					return ;
				} 
				//********************************************************************************
			}
			else
			{
				//*******************************************************************************

				rs = c_Access.hesap_adi_oku(bh);

				if (!rs.isBeforeFirst() ) {  
				
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Girilen Borclu Hesap Kodunda  bir  hesaba rastlanmadi!!!!");
					//JOptionPane.showMessageDialog(null,  "Girilen Borclu Hesap Kodunda  bir  hesaba rastlanmadi!!!!",  "Fatura Cari Kaydetme", JOptionPane.ERROR_MESSAGE); 
					return ;
				} 
				//********************************************************************************
			}
			//********************************************************************************
			rs= null;

			rs = c_Access.hesap_adi_oku(txtcari.getText());

			if (!rs.isBeforeFirst() ) {  
			
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,textField.getText() +  " Bu numarada hesaba rastlanmadi!!!!");
				//JOptionPane.showMessageDialog(null, textField.getText() +  " Bu numarada hesaba rastlanmadi!!!!",  "Fatura Cari Kaydetme", JOptionPane.ERROR_MESSAGE); 
				return;
			} 
			//*************************************
			double sdf =  DecimalFormat.getNumberInstance().parse(label_8.getText()).doubleValue()  ;
			String str_4  ="";
			int e_number =0;
			e_number = c_Access.cari_fisno_al();
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			double tutar  = DecimalFormat.getNumberInstance().parse(label.getText()).doubleValue()  ;
			lOG_BILGI lBILGI = new lOG_BILGI();
			if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			{
				str_4 = textField.getText() + "'Fatura ile " + FORMATLAMA.doub_0(sdf) + " " +  model.getValueAt(0 , 6).toString() + " Urun Satisi" ;
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
				
				lBILGI.setmESAJ("Alacakli Hes:" +alh + " Tut:" +tutar+
						" Borclu Hes:"+ bh  + " Evrak No:" + textField.getText());
				lBILGI.seteVRAK(String.valueOf(e_number));
				
				c_Access.cari_dekont_kaydet(dBilgi,
						lBILGI,
						BAGLAN_LOG.cariLogDizin);
			}
			else if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("ALIS") )
			{
				str_4 = textField.getText() + "'Fatura ile " + FORMATLAMA.doub_0(sdf) + " " +  model.getValueAt(0 , 6).toString() + " Urun Girisi" ;
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
				
				lBILGI.setmESAJ("Alacakli Hes:" +alh + " Tut:" +tutar+
						" Borclu Hes:"+ bh );
				lBILGI.seteVRAK(textField.getText());
				
				c_Access.cari_dekont_kaydet(dBilgi,
						lBILGI ,
						BAGLAN_LOG.cariLogDizin);

			}
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO, "Fatura Cari Hesaba Basari ile Kaydedilmistir....");
			//JOptionPane.showMessageDialog(null,  "Fatura Cari Hesaba Basari ile Kaydedilmistir....",  "Fatura Cari Kaydetme", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Fatura Cari Kaydetme", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void urun_bilgi_doldur(String cins) 
	{
		try {
			ResultSet rs = null;

			rs = f_Access.urun_adi_oku(cins,"Kodu");

			if (!rs.isBeforeFirst() ) {  
				lblNewLabel_12.setText("");
				label_5.setText("");
			}
			else
			{
				rs.next();
				lblNewLabel_12.setText(rs.getString("Adi"));
				label_5.setText(rs.getString("Ana_Grup") == null ? "": rs.getString("Ana_Grup") + "  /  " +
							rs.getString("Alt_Grup") == null ? "" : rs.getString("Alt_Grup"));
				resim_doldur( rs.getBytes("Resim"));
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
	private static void resim_doldur( byte[] img)
	{
		if ( img != null)
		{
			ImageIcon image = new ImageIcon(img);
			Image im = image.getImage();
			Image myImg = im.getScaledInstance(125, 100,Image.SCALE_DEFAULT);
			ImageIcon newImage = new ImageIcon(myImg);
			BufferedImage bi = new BufferedImage(newImage .getIconWidth(), newImage .getIconHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bi.createGraphics();
			newImage.paintIcon(null, g, 0, 0);
			g.setColor(Color.WHITE);
			g.dispose();
			imagePanel.setImage(bi);
		}
		else
		{
			imagePanel.setImage(null);
		}
	}
	private void son_fisoku()
	{
		try
		{
			//setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
			textField.setText( f_Access.son_no_al(cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") ? "C":"G"));
			//setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Okuma", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void irs_aktar()
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
		try {
			oac.irs_no = "" ;
			IRSALIYE_ARA iara ;
			iara = new IRSALIYE_ARA();
			iara.setVisible(true);
			ResultSet rs=null;
			//GRID_TEMIZLE.grid_temizle(table);
			//GRID_TEMIZLE.grid_temizle(table_1);
			if (oac.irs_no.equals("")) {	setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	  return;}
			//

			rs = f_Access.irsaliye_oku(oac.irs_no,cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") ? "C":"G");

			if (!rs.isBeforeFirst() ) {  
				
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Bu Numarada Irsaliye Bulunamadi ");
				//JOptionPane.showMessageDialog(null,"Bu Numarada Irsaliye Bulunamadi ",  "Irsaliye Okuma", JOptionPane.ERROR_MESSAGE);   
				return ;
			} 
			else
			{
				rs.next();
				if ( ! rs.getString("Fatura_No").equals("")) 
				{
					if ( rs.getString("Fatura_No").toString() !=  textField.getText())
					{
						
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Bu Irsaliye Daha Onceden "  + rs.getString("Fatura_No").toString() + " Faturda Islem Gormus...!");
						//JOptionPane.showMessageDialog(null,"Bu Irsaliye Daha Onceden "  + rs.getString("Fatura_No").toString() + " Faturda Islem Gormus...!",  "Irsaliye Okuma", JOptionPane.ERROR_MESSAGE);   		
						return ;
					}
				}
			}
			rs.first();   
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			int satir =0 ;
			do
			{
				mdl.insertRow(satir,new Object[]{rs.getString("Barkod"),rs.getString("Kodu"),rs.getString("Depo"),
						rs.getDouble("Fiat"),rs.getDouble("Iskonto"),rs.getDouble("Miktar"),
						rs.getString("Birim"),rs.getDouble("Kdv"),  Math.abs(rs.getDouble("Tutar")),rs.getString("Izahat")});

				satir +=1 ;
				mdl.removeRow(mdl.getRowCount() -1);
			}  while (rs.next()) ;
			//'******************Irsaliye Numaralarini Yaz
			rs.first();   
			//GRID_TEMIZLE.grid_temizle(table_1);
			DefaultTableModel mdll = (DefaultTableModel) table_1.getModel();
			String tar = rs.getString("Tarih").toString().substring(8,10);
			tar = tar + "." + rs.getString("Tarih").toString().substring(5,7);
			tar = tar + "." + rs.getString("Tarih").toString().substring(0,4);
			//
			String unv = "" ;
			ResultSet rss=null;

			rss = c_Access.hesap_adi_oku(rs.getString("Cari_Hesap_Kodu"));

			if (!rss.isBeforeFirst() ) {  

			} 
			else
			{
				while (rss.next()) 
				{
					unv = rss.getString("UNVAN");
				}
			}
			//
			mdll.insertRow(0,new Object[]{oac.irs_no,rs.getString("Cari_Hesap_Kodu"),unv,tar});
			toplam()  ;
			//

		} catch (Exception ex) 
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage(),  "Irsaliye  Okuma", JOptionPane.ERROR_MESSAGE);   
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
	}
}




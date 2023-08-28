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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
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
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import org.apache.commons.lang.StringUtils;

import com.toedter.calendar.JDateChooser;

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.DoubleEditor;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.NextCellActioin;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.U_KODU_RENDERER;

@SuppressWarnings({"serial","static-access"})
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
	private static JComboBox<String> cmbcins;
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
	private static JLabel lblNewLabel_20;
	
	
	private static JLabel lblNewLabel_3;
	private static JLabel lblNewLabel_6 ;
	private static JLabel lblNewLabel_17;
	private static JLabel lblNewLabel_13;
	private JLabel lblkodAciklama ;
	private ArrayList<String> listdepo = null ;
	
	
	private static JTabbedPane tabbedPane ;
	private static JTable table;
	
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
						if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
						{

							//sno  = ker_Access.fatura_no_al("C") ;

						}
						else
						{

							//sno  = f_Access.fatura_no_al("G") ;

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
						JOptionPane.showMessageDialog(null,  "Fatura Numaralarinda onceden harf ve rakkam kullanildigindan otomatik numara verilemez...."); 	
					}
				}
			}
		});
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				//fiskont();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void removeUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				//fiskont();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void insertUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				//fiskont();
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
		label_8.setBounds(375, 5, 102, 14);
		panel_71.add(label_8);

		label_9 = new JLabel("0.00");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setForeground(new Color(139, 0, 0));
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(850, 5, 125, 14);
		panel_71.add(label_9);
		splitPane_3.setLeftComponent(panel_71);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(2, 22, 1158, 100);
		splitPane_3.setRightComponent(tabbedPane_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_2.addTab("Toplamlar", null, panel_1, null);
		panel_1.setLayout(null);
		
		lblkodAciklama = new JLabel();
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
				//case 6:
				//	return false;
				default:
					return true;
				}
			}
			public void changeSelection(final int row, final int column, boolean toggle, boolean extend)
			{
				super.changeSelection(row, column, toggle, extend);
				if (column < 1)
				{
					//table.editCellAt(row, column);
					//table.transferFocus();
				}
			}	
		};
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
		table.setGridColor(oac.gridcolor);
		table.setCellSelectionEnabled(true);
		model.addColumn("Barkod", new String []{""});
		model.addColumn("Urun Kodu", new String []{""});
		model.addColumn("Paket_No", new String []{""});
		model.addColumn("Miktar", new Double [] {( 0.000 )});
		model.addColumn("M3", new Double [] {( 0.000 )});
		model.addColumn("Paket_M3", new Double [] {( 0.000 )});
		model.addColumn("Kons.", new String []{""});
		model.addColumn("Depo", new String []{""});
		model.addColumn("Fiat", new Double [] {( 0.00 )});
		model.addColumn("Iskonto", new Double [] {( 0.00 )});
		model.addColumn("KDV",new Double [] {( 0.00 )});
		model.addColumn("Tutar",new Double [] {( 0.00 )});
		model.addColumn("Izahat", new String []{"" });
			
		TableColumn col ;
		
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
					String aciklamaString = ker_Access.kod_adi(token[0]);
					lblkodAciklama.setText(aciklamaString);
					Dimension size = lblkodAciklama.getPreferredSize();
					lblkodAciklama.setBounds(10, 55, size.width +10, 14);
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					double m3 = 0 ;
					double miktar =  Double.parseDouble(model.getValueAt(table.getSelectedRow(), 3).toString());
					if (! token[1].toString().trim().isEmpty() && ! token[2].toString().trim().isEmpty() && ! token[3].toString().trim().isEmpty()) {
						m3 = ((Double.parseDouble(token[1].toString().trim()) * Double.parseDouble(token[2].toString().trim()) * Double.parseDouble(token[3].toString().trim() )) * miktar)/1000000000 ;
					}
					model.setValueAt(  m3,table.getSelectedRow(), 4)  ;
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,  ex.getMessage(),  "KOD ACIKLAMA", JOptionPane.ERROR_MESSAGE); 
				}
				
				getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
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
		col.setCellRenderer(new TABLO_RENDERER(3,true));
		col.setCellEditor( new DoubleEditor(3) );
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
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setHeaderRenderer(new SAGA());
		
		col = table.getColumnModel().getColumn(12);
		col.setMinWidth(175);
		JTextField atf = new JTextField(40);
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
		
		InputMap im = table.getInputMap(JTable.WHEN_FOCUSED);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Action.NextCell");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Action.NextCell");
		ActionMap am = table.getActionMap();
		am.put("Action.NextCell", new NextCellActioin(table,"fatura"));
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		
		scrollPane.setViewportView(table);

		ana_grup_doldur();
		ker_oz_kod();
		ker_nakliyeci();
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
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kereste Giris", JOptionPane.ERROR_MESSAGE);   
		}
		//***********
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
					//
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
						}
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
		
	}
	private static void satir_ilave()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		int satir = table.getSelectedRow();
		if ( satir  < 0 ) 
		{
			mdl.addRow(new Object[]{"","","",0.00,0.000,0.000,"","",0.00,0.00,0.00,0.00,""});
			satir = 0 ;
		}
		else
		{
			mdl.insertRow(satir, new Object[]{"","","",0.000,0.00,0.000,"","",0.00,0.00,0.00,0.00,""});
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
				cmbanagrup .addItem(rs.getString("UNVAN"));
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
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			double  double_0, double_1 = 0, double_2 = 0, double_3 = 0, double_4, double_5 = 0   ;
			int urunsayi = 0 ;
			for (int  i = 0 ; i <= table.getRowCount() -1 ; i ++)
			{
				double_5 += Double.parseDouble(model.getValueAt(i, 11).toString());
				double_1 += (Double.parseDouble(model.getValueAt(i, 11).toString()) * (Double.parseDouble(model.getValueAt(i, 9).toString()))) / 100 ; 
				double_2 += (( Double.parseDouble(model.getValueAt(i, 11).toString()) - ( Double.parseDouble(model.getValueAt(i, 11).toString()) *  Double.parseDouble(model.getValueAt(i, 9).toString())) / 100) *  Double.parseDouble(model.getValueAt(i, 10).toString())) / 100 ; // kdv
				double_3 +=  Double.parseDouble(model.getValueAt(i, 4).toString());
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
		double m3 =0.00;
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for ( int i = 1 ;i <= model.getRowCount() -1 ;i++   ) 
		{
			String paketno = model.getValueAt(i-1, 2).toString().trim();
			double aram3 = Double.parseDouble(model.getValueAt(i-1, 4).toString()) ;
			//if (i !=model.getRowCount()) {
				if (! model.getValueAt(i, 2).toString().trim().equals(paketno.toString().trim()))
				{
					
					if (i == model.getRowCount() -1) {
						double sonm3 = Double.parseDouble(model.getValueAt(i, 4).toString()) ;
						model.setValueAt(  sonm3,i, 5)  ;
						
					}
					else {
						m3 = m3 + aram3 ;
						model.setValueAt(  m3,i-1, 5)  ;
						
						m3 = 0.00;
					}
					
				}
				else if ( model.getValueAt(i, 2).toString().trim().equals(paketno.toString().trim())) {
					
					//if (i == model.getRowCount() -1)
					//{
					//	model.setValueAt(m3 ,i, 5)  ;
					//}
					//else {
						model.setValueAt(0,i-1, 5)  ;
						m3 = m3 + aram3 ;
					//}
						
					
					
				}
				else {
					System.out.println("i="+ 1);
				}
				
			//}
		}
	}
}

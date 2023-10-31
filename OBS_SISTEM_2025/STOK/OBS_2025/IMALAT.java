package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
 
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.DoubleEditor;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.ImagePanel;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.NextCellActioin;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TABLO_TEXTBOX;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.lOG_BILGI;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({"serial","static-access"})
public class IMALAT extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	//static ADRES_ACCESS a_Access = new ADRES_ACCESS(oac._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);


	private static JTable table;
	public static JTextField textField;
	public static JTextField textField_1;
	private static JTextField txtrecete;
	private static JTextField txtdoviz;
	public static JTextArea textArea;

	private ArrayList<String> listkodlar = null ;
	private ArrayList<String> listdepo = null ;

	public static JComboBox<String> cmbaltgrup;
	public static JComboBox<String> cmbanagrup;
	public static JComboBox<String> cmbdepo;
	private static JComboBox<String> cmbfiat ;

	public static JDateChooser dtc ;


	private static JLabel label_6 ;
	private static JLabel label_5 ;
	private static JLabel label_10 ;
	private static JLabel label_9 ;
	private static JLabel label_12 ;
	private static JLabel label_15 ;
	private static JLabel label_16 ;
	private JLabel lblNewLabel_3  ;
	private static JLabel label_17 ;
	private static JLabel lblNewLabel_6 ;

	public static JFormattedTextField txtmiktar ;

	private static  String tar = "" ;
	private static Date result_tar;
	public static JButton button ;
	public static JButton btnNewButton_1 ;
	public static JButton btnNewButton;
	private static 	 ImagePanel imagePanel ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IMALAT frame = new IMALAT();
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
	@SuppressWarnings("removal")
	public IMALAT() {
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		setTitle("IMALAT");
		setBounds(0, 0, 1130, 650);
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 200));
		panel.setMaximumSize(new Dimension(0, 200));
		panel.setLayout(new BorderLayout(0, 0));

		MaterialTabbed tabbedPane_1 = new MaterialTabbed();
		tabbedPane_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane_1.setForeground(new Color(0, 0, 128));

		panel.add(tabbedPane_1, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_1.addTab("Imalat Bilgileri", null, panel_2, null);
		tabbedPane_1.setEnabledAt(0, true);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Fis No");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(20, 11, 46, 14);
		panel_2.add(lblNewLabel_1);

		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					try {
						setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						int sno = 0 ;

						sno  = f_Access.uretim_fisno_al() ;

						int kj = 0 ;
						kj = 10 - Integer.toString(sno).length() ;
						String str_ = StringUtils.repeat("0", kj)   + Integer.toString(sno);
						textField.setText(str_.equals("0000000000") ? "0000000001":str_);
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
					catch (Exception ex)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						JOptionPane.showMessageDialog(null,  "Numaralarda onceden harf ve rakkam kullanildigindan otomatik numara verilemez...."); 	
					}
				}
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
		textField.setDocument(new JTextFieldLimit(10));
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				kontrol();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				kontrol();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				kontrol();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		textField.setBounds(96, 8, 90, 20);
		panel_2.add(textField);
		textField.setColumns(10);

		button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					textField.setText("");
					//sifirla();

					textField.setText( f_Access.uret_son_bordro_no_al());


					if ( textField.getText().equals("0") )
					{
						textField.setText("");
						JOptionPane.showMessageDialog(null,  "Hic Kayit Yok...", "Imalat Okuma", JOptionPane.ERROR_MESSAGE);
						textField.requestFocus();
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
				catch (Exception ex)
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					JOptionPane.showMessageDialog(null,  ex.getMessage(), "Imalat Okuma", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(190, 7, 28, 23);
		button.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-view-16.png")));
		panel_2.add(button);

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
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
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
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
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
		dtc.setBounds(275, 8, 125, 20);
		dtc.setDateFormatString("dd.MM.yyyy");
		dtc.setFont(new Font("Tahoma", Font.BOLD, 12));
		dtc.setDate(new Date());
		panel_2.add(dtc);

		cmbdepo = new JComboBox<String>();
		cmbdepo.setForeground(new Color(0, 0, 128));
		cmbdepo.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbdepo.setBounds(492, 8, 137, 22);
		panel_2.add(cmbdepo);

		JLabel label = new JLabel("Ana Grup");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(655, 12, 59, 14);
		panel_2.add(label);

		JLabel label_1 = new JLabel("Alt Grup");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(655, 41, 61, 14);
		panel_2.add(label_1);

		cmbaltgrup = new JComboBox<String>();
		cmbaltgrup.setForeground(new Color(0, 0, 128));
		cmbaltgrup.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbaltgrup.setEnabled(false);
		cmbaltgrup.setBounds(726, 37, 142, 22);
		panel_2.add(cmbaltgrup);

		cmbanagrup = new JComboBox<String>();
		cmbanagrup.setForeground(new Color(0, 0, 128));
		cmbanagrup.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbanagrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur();
			}
		});
		cmbanagrup.setBounds(726, 8, 142, 22);
		panel_2.add(cmbanagrup);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ana_grup_doldur();
			}
		});
		button_1.setBounds(878, 8, 28, 23);
		button_1.setIcon(new ImageIcon(IMALAT.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		panel_2.add(button_1);



		imagePanel = new ImagePanel();
		imagePanel.setBounds(920, 5, 180, 140);
		imagePanel.setBorder(new LineBorder(new Color(95, 158, 160), 2,true));
		panel_2.add( imagePanel);


		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 0), new Color(160, 160, 160)), "Uretimi Yapilacak Yeni Urun  (GIREN)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel_5.setBounds(10, 66, 898, 52);
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblUrunKodu = new JLabel("Urun Kodu");
		lblUrunKodu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUrunKodu.setBounds(10, 21, 67, 14);
		panel_5.add(lblUrunKodu);

		textField_1 = new JTextField();
		textField_1.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					URUN_ARAMA arm ;
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					arm = new URUN_ARAMA();
					arm.show();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					textField_1.setText( oac.stk_kodu);
				}
			}
		});
		textField_1.setDocument(new JTextFieldLimit(12));
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				isimoku();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				isimoku();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				isimoku();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_1.setColumns(10);
		textField_1.setBounds(87, 19, 110, 20);
		panel_5.add(textField_1);

		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				URUN_ARAMA arm ;
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				arm = new URUN_ARAMA();
				arm.show();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				textField_1.setText( oac.stk_kodu) ;
			}
		});
		button_3.setBounds(200, 18, 25, 23);
		button_3.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-view-16.png")));
		panel_5.add(button_3);

		JLabel lblReceteKodu = new JLabel("Recete Kodu");
		lblReceteKodu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblReceteKodu.setBounds(258, 22, 73, 14);
		panel_5.add(lblReceteKodu);

		txtrecete = new JTextField();
		txtrecete.setDocument(new JTextFieldLimit(10));
		txtrecete.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtrecete.setColumns(10);
		txtrecete.setBounds(341, 19, 109, 20);
		panel_5.add(txtrecete);

		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				RECETE_BUL rct ;
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				rct = new RECETE_BUL();
				rct.show();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				txtrecete.setText( oac.stk_kodu) ;
			}
		});
		button_4.setBounds(460, 18, 25, 23);
		button_4.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-view-16.png")));
		panel_5.add(button_4);

		JLabel lblNewLabel_2 = new JLabel("Uretim Miktari");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(500, 21, 80, 14);
		panel_5.add(lblNewLabel_2);

		txtmiktar = new JFormattedTextField();
		txtmiktar .getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				toplam();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				toplam();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				toplam();
			}
		});
		txtmiktar.setText("0");
		txtmiktar.setHorizontalAlignment(SwingConstants.RIGHT);
		txtmiktar.setForeground(Color.BLUE);
		txtmiktar.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtmiktar.setBounds(590, 15, 101, 25);
		panel_5.add(txtmiktar);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(701, 21, 80, 14);
		panel_5.add(lblNewLabel_3);

		txtdoviz = new JTextField();
		txtdoviz.setDocument(new JTextFieldLimit(3));
		txtdoviz.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtdoviz.setBounds(791, 18, 86, 20);
		panel_5.add(txtdoviz);
		txtdoviz.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Uygulanacak Fiat");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(10, 125, 106, 14);
		panel_2.add(lblNewLabel_4);

		cmbfiat = new JComboBox<String>();
		cmbfiat.setForeground(new Color(0, 0, 128));
		cmbfiat.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbfiat.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Ortalama_Giris_Fiati", "Son_Imalat", "Fiat_1", "Fiat_2", "Fiat_3"}));
		cmbfiat.setBounds(130, 121, 186, 22);
		panel_2.add(cmbfiat);

		JLabel lblNewLabel_5 = new JLabel("Uretim Birim Fiati");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(600, 129, 114, 14);
		panel_2.add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("0.00");
		lblNewLabel_6.setForeground(Color.BLUE);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(726, 124, 106, 22);
		panel_2.add(lblNewLabel_6);

		JLabel lblNewLabel = new JLabel("Depo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(420, 11, 46, 14);
		panel_2.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_1.addTab("Aciklama", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel lblNewLabel_14 = new JLabel("Aciklama");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_14.setBounds(10, 26, 66, 14);
		panel_4.add(lblNewLabel_14);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(86, 21, 375, 63);
		textArea.setDocument(new JTextFieldLimit(40));
		textArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		Border border1 = BorderFactory.createLineBorder(Color.GRAY);
		textArea.setBorder(BorderFactory.createCompoundBorder(border1,
				BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		panel_4.add(textArea);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(0);
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);

		splitPane.setRightComponent(splitPane_1);

		JPanel panel_3 = new JPanel();

		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "Giren Urun Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		splitPane_1.setRightComponent(panel_3);
		panel_3.setMinimumSize(new Dimension(0, 80));
		panel_3.setMaximumSize(new Dimension(0, 80));
		panel_3.setLayout(null);

		JLabel label_3 = new JLabel("Adi");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(10, 23, 46, 14);
		panel_3.add(label_3);

		JLabel label_4 = new JLabel("Barkod");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(10, 48, 46, 14);
		panel_3.add(label_4);

		label_5 = new JLabel("...");
		label_5.setForeground(new Color(128, 0, 0));
		label_5.setBounds(75, 48, 149, 14);
		panel_3.add(label_5);

		label_6 = new JLabel("...");
		label_6.setForeground(new Color(128, 0, 0));
		label_6.setBounds(77, 23, 221, 14);
		panel_3.add(label_6);

		JLabel label_7 = new JLabel("Birim");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(326, 23, 46, 14);
		panel_3.add(label_7);

		JLabel label_8 = new JLabel("Sinif");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_8.setBounds(326, 48, 46, 14);
		panel_3.add(label_8);

		label_9 = new JLabel("...");
		label_9.setForeground(new Color(128, 0, 0));
		label_9.setBounds(393, 48, 71, 14);
		panel_3.add(label_9);

		label_10 = new JLabel("...");
		label_10.setForeground(new Color(128, 0, 0));
		label_10.setBounds(393, 23, 71, 14);
		panel_3.add(label_10);

		JLabel label_11 = new JLabel("Agirlik");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_11.setBounds(486, 23, 57, 14);
		panel_3.add(label_11);

		label_12 = new JLabel("...");
		label_12.setForeground(new Color(128, 0, 0));
		label_12.setBounds(553, 23, 71, 14);
		panel_3.add(label_12);

		JLabel label_13 = new JLabel("Ana Grup");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_13.setBounds(634, 23, 63, 14);
		panel_3.add(label_13);

		JLabel label_14 = new JLabel("Alt Grup");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_14.setBounds(634, 48, 63, 14);
		panel_3.add(label_14);

		label_15 = new JLabel("...");
		label_15.setForeground(new Color(128, 0, 0));
		label_15.setBounds(701, 48, 183, 14);
		panel_3.add(label_15);

		label_16 = new JLabel("...");
		label_16.setForeground(new Color(128, 0, 0));
		label_16.setBounds(701, 23, 183, 14);
		panel_3.add(label_16);

		label_17 = new JLabel("0.00");
		label_17.setHorizontalAlignment(SwingConstants.RIGHT);
		label_17.setForeground(Color.BLUE);
		label_17.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_17.setBounds(917, 23, 176, 14);
		panel_3.add(label_17);

		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setDividerSize(0);
		splitPane_1.setLeftComponent(splitPane_2);

		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		toolBar_1.setMinimumSize(new Dimension(30, 0));
		toolBar_1.setMaximumSize(new Dimension(30, 0));
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		splitPane_2.setLeftComponent(toolBar_1);

		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hesapla();
			}
		});
		btnNewButton.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-view-16.png")));
		toolBar_1.add(btnNewButton);

		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				satir_ilave();
			}
		});
		button_2.setIcon(new ImageIcon(IMALAT.class.getResource("/ICONLAR/yeni.png")));
		toolBar_1.add(button_2);

		JButton button_5 = new JButton("");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				satir_sil();
			}
		});

		button_5.setIcon(new ImageIcon(IMALAT.class.getResource("/ICONLAR/icons8-reduce-16.png")));
		toolBar_1.add(button_5);

		JScrollPane scrollPane = new JScrollPane();
		splitPane_2.setRightComponent(scrollPane);

		DefaultTableModel model = new DefaultTableModel() ; 
		table = new JTable(model){
			@Override
			public boolean isCellEditable(int row, int column) {  
				switch (column) {
				case 0:
					return false;
				case  2:
					return false;
				case 6:
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
					table.editCellAt(row, column);
					table.transferFocus();
				}
			}	
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

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
		////
		table.setCellSelectionEnabled(true);
		model.addColumn("Tur", new String []{""});
		model.addColumn("Urun Kodu", new String []{""});
		model.addColumn("Adi", new String []{""});
		model.addColumn("Izahat", new String []{""});
		model.addColumn("Depo", new String []{""});
		model.addColumn("Miktar", new Double [] {new Double( 0.000 )});
		model.addColumn("Birim", new String []{"" });
		model.addColumn("Fiat", new Double [] {new Double( 0.00 )});
		model.addColumn("Tutar", new Double [] {new Double( 0.00 )});

		TableColumn col ;

		col = table.getColumnModel().getColumn(0);
		col.setMinWidth(50);
		col.setMaxWidth(50);
		col.setHeaderRenderer(new SOLA());
		//**
		col = table.getColumnModel().getColumn(1);
		listkodlar = new ArrayList<String> () ;
		stk_kodu_auto();
		col = table.getColumnModel().getColumn(1);
		ComboBoxTableCellEditor editorkodu = new ComboBoxTableCellEditor( listkodlar ,table,"imalat");
		col.setCellEditor(editorkodu);
		col.setMinWidth(100);
		col.setHeaderRenderer(new SOLA());
		/*
        Java2sAutoComboBox comboBox1 = new Java2sAutoComboBox(listkodlar,"imalat");
        comboBox1.setDataList(listkodlar);
        comboBox1.setMaximumRowCount(10);
        comboBox1.setFont(new Font("Tahoma", Font.BOLD, 12));
        col.setCellEditor(new DefaultCellEditor(comboBox1));
		col.setMinWidth(100);
	    col.setHeaderRenderer(new SOLA());
		 */
		/**
		Object child =   comboBox1 .getAccessibleContext().getAccessibleChild(0);
		BasicComboPopup popup = (BasicComboPopup)child;
		JList list = popup.getList();
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent listSelectionEvent) {
		JList list = (JList) listSelectionEvent.getSource();
		         int selections[] = list.getSelectedIndices();
		         Object selectionValues[] = list.getSelectedValues();
		        for (int i = 0, n = selections.length; i < n; i++) {
		      IMALAT.bilgi_doldur(selectionValues[i].toString());
		        }
		     }
		    };
		  list.addListSelectionListener(listSelectionListener);
		 */
		col = table.getColumnModel().getColumn(2);
		col.setMinWidth(200);
		col.setHeaderRenderer(new SOLA());

		col = table.getColumnModel().getColumn(3);  //Izahat
		col.setCellEditor(new TABLO_TEXTBOX(new JTextField() ,40,table.getFont(),JTextField.LEFT));
		col.setMinWidth(200);
		col.setHeaderRenderer(new SOLA());

		col = table.getColumnModel().getColumn(4);
		listdepo = new ArrayList<String> () ;
		depo_auto();
		Java2sAutoComboBox combodp = new Java2sAutoComboBox( listdepo,"imalat");
		combodp.setDataList(listdepo);
		col.setCellEditor(new DefaultCellEditor(combodp));
		col.setMinWidth(130);
		col.setHeaderRenderer(new SOLA());

		col = table.getColumnModel().getColumn(5);
		col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(3) );
		col.setCellRenderer(new TABLO_RENDERER(3,true));
		col.setMinWidth(90);

		col = table.getColumnModel().getColumn(6);
		col.setMinWidth(60);
		col.setHeaderRenderer(new SOLA());

		col = table.getColumnModel().getColumn(7);
		col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setMinWidth(95);

		col = table.getColumnModel().getColumn(8);
		col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,true));
		col.setMinWidth(120);

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

		//

		//
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

		table.getColumnModel().getColumn(1).setCellRenderer(r);
		table.repaint();
		scrollPane.setViewportView(table);

		stk_kodu_auto();
		ana_grup_doldur();
		GRID_TEMIZLE.grid_temizle(table);
		try {
			for (int i = 0; i <= 13-1 ; i ++)
			{
				satir_ilave();
			}
			txtdoviz.setText(GLOBAL.setting_oku("PRG_PARA").toString());

			btnNewButton_1 = new JButton("New button");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						int sno = 0 ;

						sno  = f_Access.uretim_fisno_al() ;

						int kj = 0 ;
						kj = 10 - Integer.toString(sno).length() ;
						String str_ = StringUtils.repeat("0", kj)   + Integer.toString(sno);
						textField.setText(str_.equals("0000000000") ? "0000000001":str_);
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
					catch (Exception ex)
					{
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						JOptionPane.showMessageDialog(null,  "Numaralarda onceden harf ve rakkam kullanildigindan otomatik numara verilemez...."); 	
					}
				}
			});
			btnNewButton_1.setBounds(10, 36, 38, 23);
			btnNewButton_1.setVisible(false);
			panel_2.add(btnNewButton_1);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Imalat", JOptionPane.ERROR_MESSAGE);
		}
		//***********
		table.getModel().addTableModelListener(	(TableModelListener) new TableModelListener() 
		{		@Override
			public void tableChanged(TableModelEvent e) {
			TableModel model = (TableModel)e.getSource();
			if (model.getRowCount() > 0) {
				int row;
				row = table.getSelectedRow();     //e.getFirstRow();
				int column = e.getColumn();
				double double_0,double_1 ;
				if (column == 5)
				{
					double_0 = Double.parseDouble(table.getValueAt(row,7).toString()) ;
					double_1 =Double.parseDouble(table.getValueAt(row,5).toString()) ;
					model.setValueAt(  double_0 * double_1,row, 8)  ;   
				}
				else if (column == 7)
				{
					double_0 = Double.parseDouble(table.getValueAt(row,7).toString()) ;
					double_1 =Double.parseDouble(table.getValueAt(row,5).toString()) ;
					model.setValueAt(  double_0 * double_1,row, 8)  ;   
				}
				else if (column == 8)
				{
					double_0 = 0 ;
					double_1 = 0 ;
					double_0 = Double.parseDouble(table.getValueAt(row,8).toString()) ;
					double_1 =Double.parseDouble(table.getValueAt(row,5).toString()) ;
					if (double_1 != 0)
					{
						//model.setValueAt(  double_0 / double_1,row, 7)  ;   
					}
					else
					{
						//model.setValueAt( 0,row, 7)  ;   
					}
				}

			}
			toplam();
		}
		});
		//****
		//ana_grup_doldur();
		depo_doldur();
		textField.requestFocus();
	}
	private  void stk_kodu_auto()
	{
		try {
			ResultSet rs = null;

			rs = f_Access.stk_barkod_kod_oku("Kodu");

			if (!rs.isBeforeFirst() ) {  
				listkodlar.add("");
			}
			else
			{
				listkodlar.add("");
				while (rs.next())
				{
					listkodlar.add(rs.getString("Kodu"));
				}
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Stok Kodu", JOptionPane.ERROR_MESSAGE);
		}
	}
	private  void depo_auto()
	{
		try {
			ResultSet rs = null;
			cmbdepo.removeAllItems();

			rs = f_Access.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");

			if (!rs.isBeforeFirst() ) {  
				cmbdepo.addItem("");
				listdepo.add("");
			}
			else
			{
				listdepo.add("");
				cmbdepo.addItem("");
				while (rs.next())
				{
					cmbdepo.addItem(rs.getString("DEPO"));
					listdepo.add(rs.getString("DEPO"));
				}
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Depo Doldur", JOptionPane.ERROR_MESSAGE);
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
				cmbaltgrup.setSelectedItem("");
				return;
			} 
			cmbanagrup .addItem("");
			while (rs.next())
			{
				cmbanagrup .addItem(rs.getString("ANA_GRUP"));
			}
			cmbanagrup.setSelectedItem("");
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
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Alt Grup", JOptionPane.ERROR_MESSAGE);    	
		}
	}
	public static void satir_ilave()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		int satir = table.getSelectedRow();
		if ( satir  < 0 ) 
		{
			mdl.addRow(new Object[]{"","","","","",0.000,"",0.00,0.00});
			satir = 0 ;
		}
		else
		{
			mdl.insertRow(satir, new Object[]{"","","","","",0.000,"",0.00,0.00});
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
	private static void toplam() 
	{
		try {
			double  dbl=0, dbl1=0;

			if (   table == null) return ;
			DefaultTableModel mdll = (DefaultTableModel) table.getModel();
			for (int  i = 0 ; i < mdll.getRowCount()- 1;i++)
			{
				dbl += Double.parseDouble(table.getValueAt(i,8).toString()) ;
			}

			label_17.setText(FORMATLAMA.doub_2(dbl));
			Double dbmik=0.00;
			if (! txtmiktar.getText().equals(""))
			{
				dbmik = DecimalFormat.getNumberInstance().parse(txtmiktar.getText()).doubleValue();
				dbl1 = dbl /   (dbmik == 0 ? 1:dbmik);
				lblNewLabel_6.setText(FORMATLAMA.doub_2(dbl1));
			}
			else
			{
				lblNewLabel_6.setText(FORMATLAMA.doub_2(dbl1));
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Toplam", JOptionPane.ERROR_MESSAGE);   
		}
	}
	public static void bilgi_doldur(String cins)
	{
		try {
			ResultSet rs = null;

			rs = f_Access.urun_adi_oku(cins,"Kodu");

			if (!rs.isBeforeFirst() ) {  
				table.getModel().setValueAt("Cikan",table.getSelectedRow(), 0) ;
				table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
				table.getModel().setValueAt("",table.getSelectedRow(), 3) ;
				table.getModel().setValueAt("",table.getSelectedRow(), 6) ;
				table.getModel().setValueAt(0.000,table.getSelectedRow(), 5) ;
			}
			else
			{
				rs.next();
				table.getModel().setValueAt("Cikan",table.getSelectedRow(), 0) ;
				table.getModel().setValueAt(rs.getString("Adi"),table.getSelectedRow(), 2) ;
				table.getModel().setValueAt(rs.getString("Birim"),table.getSelectedRow(), 6) ;
				//***
				//'******************Fiat Ekle
				if (! cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals(""))
				{
					if ( cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_1"))
					{
						table.getModel().setValueAt(rs.getDouble("Fiat"),table.getSelectedRow(), 7) ;
					}
					else  if ( cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_2"))
					{
						table.getModel().setValueAt(rs.getDouble("Fiat_2"),table.getSelectedRow(), 7) ;
					}
					else  if ( cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_3"))
					{
						table.getModel().setValueAt(rs.getDouble("Fiat_3"),table.getSelectedRow(), 7) ;
					}
					else  if ( cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Son_Imalat"))
					{

						table.getModel().setValueAt(f_Access.son_imalat_fiati_oku(cins),table.getSelectedRow(), 7) ;

					}
					else  if ( cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Ortalama_Giris_Fiati"))
					{
						Date  i_tar ;

						i_tar = ilk_tarih(cins, TARIH_CEVIR.tarih_geri(dtc));
						//System.out.println(i_tar.toString());
						//System.out.println( dateFormater(i_tar.toString(), "yyyy.MM.dd", "EEE MMM dd kk:mm:ss zzzz yyyy" ));
						String qwe =  TARIH_CEVIR.dateFormater(i_tar.toString(), "yyyy.MM.dd", "EEE MMM dd kk:mm:ss zzzz yyyy" );
						if (qwe.equals("1900.01.01"))
						{
							table.getModel().setValueAt(0.00,table.getSelectedRow(), 7) ;
						}
						else
						{

							table.getModel().setValueAt(f_Access.gir_ort_fiati_oku(cins,qwe,TARIH_CEVIR.tarih_geri(dtc)),table.getSelectedRow(), 7) ;

						}
					}
				}
				else
				{
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 7) ;
				}
				//***
				table.setCellSelectionEnabled(true);
				int col = 5;
				table.changeSelection(table.getSelectedRow(),col,false,false);
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Bilgi Doldur", JOptionPane.ERROR_MESSAGE);
		}
	}		
	private static Date ilk_tarih(String kodu,String tarq)
	{
		try {
			ResultSet rs = null;
			result_tar = TARIH_CEVIR.tarih("01.01.1900");
			String deger = GLOBAL.setting_oku("STK_IMA_BAS_TAR").toString();
			String tar =  TARIH_CEVIR.dateFormater(deger, "yyyy.MM.dd", "EEE MMM dd kk:mm:ss zzzz yyyy" );

			rs = f_Access.uret_ilk_tarih(tar,tarq,kodu);


			if (!rs.isBeforeFirst() ) {  
				result_tar = TARIH_CEVIR.tarih("01.01.1900");
			}
			else
			{
				rs.last();
				int kayit_sayi = rs.getRow();
				double  dbbl  = 0;
				for (int i = kayit_sayi-1; i == 0; i--)
				{
					dbbl = rs.getDouble("Miktar_Bakiye");
					if (dbbl == 0 )
					{
						result_tar = rs.getDate("Tarih");
						break ;
					}
					rs.previous();
				}
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Imalat Ort Fiat Okuma", JOptionPane.ERROR_MESSAGE);
		}
		return result_tar;
	}
	private void isimoku ()
	{
		try {
			ResultSet rs = null;

			rs = f_Access.imalat_urun_ara(textField_1.getText());

			if (!rs.isBeforeFirst() ) {  
				label_6.setText("") ;
				label_5.setText("") ;
				label_9.setText("") ;
				label_10.setText("") ;
				label_12.setText("0.000");
				lblNewLabel_3.setText("") ;
				txtrecete.setText("");
				imagePanel.setImage(null);
				label_16.setText("") ;
				label_15.setText("") ;
			}
			else
			{
				rs.next();
				label_5.setText(rs.getString("Barkod")) ;
				label_6.setText(rs.getString("Adi")) ;
				label_9.setText(rs.getString("Sinif")) ;
				label_10.setText(rs.getString("Birim")) ;
				lblNewLabel_3.setText(rs.getString("Birim")) ;
				txtrecete.setText(rs.getString("Recete").toString());
				label_12.setText(FORMATLAMA.doub_3(rs.getDouble("Agirlik"))) ;

				ResultSet rss=null;

				rss = f_Access.urun_kod_degisken_ara("ANA_GRUP" ,"AGID_Y", "ANA_GRUP_DEGISKEN",rs.getString("Ana_Grup"));
				if (!rss.isBeforeFirst() ) {
					label_16.setText("") ;
				}
				else
				{
					rss.next();
					label_16.setText(rss.getString("ANA_GRUP")) ;
				}


				//**Alt Grp
				rss=null;

				rss = f_Access.urun_kod_degisken_ara( "ALT_GRUP","ALID_Y", "ALT_GRUP_DEGISKEN",rs.getString("Alt_Grup"));
				if (!rss.isBeforeFirst() ) {
					label_15.setText("") ;
				}
				else
				{
					rss.next();
					label_15.setText(rss.getString("ALT_GRUP")) ;
				}

				rs = null;

				rs = f_Access.resim_oku(textField_1.getText());


				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					if (  rs.getBytes("Resim") != null)
					{
						byte[] img = rs.getBytes("Resim");
						ImageIcon image = new ImageIcon(img);
						Image im = image.getImage();
						Image myImg = im.getScaledInstance(180, 140,Image.SCALE_DEFAULT);
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
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Urun Kodu Deger Okuma", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void hesapla() 
	{
		try {
			Double dbmik = DecimalFormat.getNumberInstance().parse(txtmiktar.getText()).doubleValue();
			if (dbmik == 0)
			{
				JOptionPane.showMessageDialog(null,  "Ilk Olarak Uretilecek Urun Miktarini Girmelisiniz...", "Hesaplama", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			if ( txtrecete.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Recete No Bos...", "Hesaplama", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			ResultSet rs = null;

			rs = f_Access.recete_oku(txtrecete.getText());

			if (!rs.isBeforeFirst() ) {
				JOptionPane.showMessageDialog(null, "Bu Numarada Kayitli Recete Bilgisi Yok ", "Hesaplama", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				GRID_TEMIZLE.grid_temizle(table);
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				int satir =0 ;
				while (rs.next())
				{
					if (rs.getString("Tur").equals("Cikan"))
					{
						mdl.addRow(new Object [] {rs.getString("Tur"), rs.getString("Kodu"), rs.getString("Adi") ,"","",  rs.getDouble("Miktar") * dbmik 
								,rs.getString("Birim") ,0.00,0.00 });
						satir +=1 ;
					}
					else
					{
						lblNewLabel_3.setText(rs.getString("Birim"));
					}
				}
				table.changeSelection(satir,1,false,false);
				for (int i = satir; i <= 12  ; i ++)
				{
					satir_ilave();
				}
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Urun Kodu Deger Okuma", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void kaydet()
	{
		if (textField.getText().equals("") ) return ;
		if (table.getRowCount()  == 0 ) return ;
		if(dtc.getDate() == null) return;
		long startTime = System.currentTimeMillis();
		try
		{
			tar = TARIH_CEVIR.tarih_geri_saatli(dtc) ;
			stok_isle();
			acik_yaz();
			textField.setText("");
			textField_1.setText("");
			sifirla();
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime;
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			textField.requestFocus();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Imalat Kayit", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void stok_isle()
	{
		try {

			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ("Imalat Stok Silme");
			lBILGI.seteVRAK(textField.getText());
			
			f_Access.stok_sil(textField.getText(), "URE", "C",lBILGI, BAGLAN_LOG.fatLogDizin);

			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			for (int  i = 0 ; i <=  mdl.getRowCount() - 1 ; i++)
			{
				if (! mdl.getValueAt(i,1).toString().equals(""))
				{
					sat_yaz_2(i);
				}
			}
			stk_gir_yaz();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Satyz1", JOptionPane.ERROR_MESSAGE);             
		}
	}
	public static void sat_yaz_2(int i)
	{
		try {
			String  izahat ;
			double  miktar, tutar,fiat ;
			int angrp, altgrp, depo;
			depo = 0 ;
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			ResultSet rs =null ;
			if ( mdl.getValueAt(i,4) == null)
			{
				depo = 0 ;
			}
			else
			{

				rs = f_Access.urun_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN",  mdl.getValueAt(i,4).toString());

				if (!rs.isBeforeFirst() ) {      		
				}
				else
				{
					rs.next();
					depo = rs.getInt("DPID_Y");
				}
			}
			miktar = Double.parseDouble( mdl.getValueAt(i,5).toString());
			miktar = miktar *-1 ;
			fiat = Double.parseDouble( mdl.getValueAt(i,7).toString());
			tutar = Double.parseDouble( mdl.getValueAt(i,8).toString());
			tutar = tutar * -1 ;
			izahat =  mdl.getValueAt(i,3).toString();
			if (izahat.equals(""))
			{
				izahat =  textField.getText() + " Nolu Uretimde Cikan " ;
			}
			angrp = 0 ;
			if ( ! cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals("") ) {

				rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",  cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString());

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

			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ( "Imalat Stok Cikan Kayit  Kod:" + mdl.getValueAt(i,1).toString() + " Miktar:" + miktar + " Fiat:" + fiat );
			lBILGI.seteVRAK(textField.getText());
			
			f_Access.stk_kaydet(textField.getText(), "URE", tar, depo,  mdl.getValueAt(i,1).toString(), miktar, fiat
					,KUSUR_YUVARLA. round(tutar,2),KUSUR_YUVARLA. round(tutar,2), "C", izahat, angrp, altgrp,0, "", txtdoviz.getText(),"",GLOBAL.KULL_ADI,
					lBILGI,BAGLAN_LOG.fatLogDizin);

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Imalat Satyz2", JOptionPane.ERROR_MESSAGE);             
		}
	}
	public static void stk_gir_yaz()
	{
		try {

			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ("Imalat Stok Silme");
			lBILGI.seteVRAK(textField.getText());
			
			f_Access.stok_sil(textField.getText(), "URE", "G",lBILGI, BAGLAN_LOG.fatLogDizin);

			double  miktar, tutar,fiat ;
			int angrp, altgrp, depo;
			depo = 0 ;
			//DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			ResultSet rs =null ;
			depo = 0 ;

			rs = f_Access.urun_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN",   cmbdepo.getItemAt(cmbdepo.getSelectedIndex()).toString());

			if (!rs.isBeforeFirst() ) {      		
			}
			else
			{
				rs.next();
				depo = rs.getInt("DPID_Y");
			}
			miktar =DecimalFormat.getNumberInstance().parse(txtmiktar.getText()).doubleValue();
			tutar = DecimalFormat.getNumberInstance().parse(label_17.getText()).doubleValue()  ;
			fiat =tutar  / (miktar == 0 ? 1 :miktar);
			angrp = 0 ;
			if ( ! cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals("") ) {

				rs =f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",  cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString());

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

			lBILGI.setmESAJ( "Imalat Stok Giren Kayit  Kod:" + textField_1.getText()  + " Miktar:" + miktar + " Fiat:" + fiat );
			lBILGI.seteVRAK(textField.getText());
			
			f_Access.stk_kaydet(textField.getText(), "URE", tar, depo,textField_1.getText()  , miktar, fiat
					, KUSUR_YUVARLA.round(tutar,2),KUSUR_YUVARLA.round(tutar,2), "G", textField.getText() + " Nolu Fis Ile Uretim ", angrp, altgrp,0, "", txtdoviz.getText(),"",GLOBAL.KULL_ADI,
					lBILGI,BAGLAN_LOG.fatLogDizin);

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Imalat Satgiryz", JOptionPane.ERROR_MESSAGE);             
		}
	}
	public static void acik_yaz()
	{
		try {
			acik_sil();
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ( "Imalat Acik Yaz G :"  + textArea.getText());
			lBILGI.seteVRAK(textField.getText());
			
			f_Access.aciklama_yaz("URE", 1, textField.getText(),  textArea.getText(), "G",
					lBILGI ,BAGLAN_LOG.fatLogDizin);

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Imalat Acikyz", JOptionPane.ERROR_MESSAGE);   		
		}
	}
	private static void acik_sil()
	{
		try {

			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ( "Imalat Acik Sil  G "   );
			lBILGI.seteVRAK(textField.getText());
			
			f_Access.aciklama_sil("URE", textField.getText(), "G",
					lBILGI  ,BAGLAN_LOG.fatLogDizin);

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Imalat Aciksl", JOptionPane.ERROR_MESSAGE);   		
		}
	}
	private static void sifirla()
	{
		GRID_TEMIZLE.grid_temizle(table);
		for (int i = 0; i <= 12; i ++)
		{
			satir_ilave();
		}
		label_6.setText("") ;
		label_5.setText("") ;
		label_9.setText("") ;
		label_10.setText("") ;
		label_12.setText("0.000");
		label_15.setText("");
		label_16.setText("");
		label_17.setText("0.00");
		txtrecete.setText("");
		imagePanel.setImage(null);
		dtc.setDate(new Date());
		lblNewLabel_6.setText("0.00");
		txtmiktar.setText("0");
		cmbanagrup.setSelectedItem("");
		cmbaltgrup.setSelectedItem("");
		cmbdepo.setSelectedItem("");
		textArea.setText("");
	}
	public static void imalat_sil()
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
			lBILGI.setmESAJ("Imalat Stok Silme");
			lBILGI.seteVRAK(textField.getText());
			
			f_Access.stok_sil(textField.getText(),  "URE", "G",lBILGI, BAGLAN_LOG.fatLogDizin);
			f_Access.stok_sil(textField.getText(),  "URE", "C",lBILGI, BAGLAN_LOG.fatLogDizin);

			acik_sil();
			textField.setText("");
			sifirla();
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime;
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

			textField.requestFocus();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Imalat Silme", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void kontrol ()
	{
		try {
			long startTime = System.currentTimeMillis();
			ResultSet rss =null;

			rss = f_Access.stok_oku(textField.getText(), "URE");

			if (!rss.isBeforeFirst() ) 
			{  
				textField_1.setText("");
				sifirla();
				textField.requestFocus();
			}
			else
			{
				DefaultTableModel mdl2 = (DefaultTableModel) table.getModel();
				int satir =0 ;
				while (rss.next())
				{
					if (rss.getString("Hareket").equals("C"))
					{
						mdl2.insertRow(satir,new Object [] {"Cikan", rss.getString("Urun_Kodu"), rss.getString("Adi") , rss.getString("Izahat"), rss.getString("Depo") == null ? "":rss.getString("Depo"),
								rss.getDouble("Miktar") *-1, rss.getString("Birim"),rss.getDouble("Fiat"), Math.abs(rss.getDouble("Tutar"))});
						satir +=1 ;
						mdl2.removeRow(mdl2.getRowCount() -1);
					}
					else  //' Giren Yaz
					{
						cmbdepo.setSelectedItem( rss.getString("Depo") == null ? "":rss.getString("Depo"));
						dtc.setDate(rss.getDate("Tarih"));
						txtmiktar.setText(FORMATLAMA.doub_0(rss.getDouble("Miktar")));
						textField_1.setText(rss.getString("Urun_Kodu"));
						txtdoviz.setText(rss.getString("Doviz"));
						textArea.setText(rss.getString("Izahat"));
						// '************ACIKLAMA OKU ***********************************************************

						textArea.setText(f_Access.aciklama_oku("URE", 1, textField.getText(), "G"));

						// '*********************GRUP DOLDUR ****************************************************
						cmbanagrup.setSelectedItem(rss.getString("Ana_Grup") == null ? "" :rss.getString("Ana_Grup"));
						cmbaltgrup.setSelectedItem(rss.getString("Alt_Grup") == null ? "" :rss.getString("Alt_Grup"));
						// '*************************************************************************************
					}
				}
				table.setCellSelectionEnabled(true);
				table.changeSelection(satir,1,false,false);
				toplam();
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Imalat", JOptionPane.ERROR_MESSAGE);              
		}
	}
	private void depo_doldur()
	{
		try {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			cmbdepo .removeAllItems();
			ResultSet rs = null;

			rs = f_Access.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");

			if (!rs.isBeforeFirst() ) {  
				cmbdepo.addItem("");
				cmbdepo.setSelectedItem("");
			}
			else
			{
				cmbdepo.addItem("");
				while (rs.next())
				{
					cmbdepo.addItem(rs.getString("DEPO"));
				}
			}
			cmbdepo.setSelectedItem("");
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Depo Doldur", JOptionPane.ERROR_MESSAGE);
		}
	}
}


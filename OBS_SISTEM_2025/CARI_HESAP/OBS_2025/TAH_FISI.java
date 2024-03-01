package OBS_2025;

import javax.swing.JInternalFrame;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;

import OBS_C_2025.MaterialTabbed;
import OBS_C_2025.Next_Cell_Kereste;
import OBS_C_2025.Obs_TextFIeld;

import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.dEKONT_BILGI;
import OBS_C_2025.lOG_BILGI;
import raven.toast.Notifications;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.COKLU_GIRIS_TARIH;
import OBS_C_2025.DoubleEditor;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.ImagePanel;
import OBS_C_2025.JDateChooserEditor;
import OBS_C_2025.JTextFieldRegularPopupMenu;

import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.JTextComponent;
import javax.swing.text.NumberFormatter;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.imageio.ImageIO;
import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.border.TitledBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.KeyStroke;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings({ "unchecked", "rawtypes" ,"deprecation","static-access","removal"})
public class TAH_FISI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static CARI_ACCESS c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(OBS_SIS_2025_ANA_CLASS._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	
	private static Obs_TextFIeld textAdi;
	private static Obs_TextFIeld textAdres1;
	private static Obs_TextFIeld textAdres2;
	private static Obs_TextFIeld textVdVn;
	private static Obs_TextFIeld textMail;
	private static Obs_TextFIeld textDiger;
	public static Obs_TextFIeld textCKodu;
	public static Obs_TextFIeld textAKodu;
	public static Obs_TextFIeld textEvrakNo;
	
	private static JLabel lblCAdi ;
	public static JLabel lblCekSayi;

	private static JLabel lblAAdi ;
	public static JFormattedTextField formattedTutar ;
	public static JComboBox<String> cmbCins ;
	public static JComboBox<String> cmbTur ;
	private static MaterialTabbed tabbedPane;
	public static ImagePanel imagePanel;
	private static ImagePanel imageKase;
	public static JDateChooser dtc ;
	public static JComboBox combCins ;
	
	private JPanel panel_4;
	private JPanel panel_5;
	private JScrollPane scrollPane;
	private static JTable tableCek;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	
	public TAH_FISI() {
		FlatLaf.registerCustomDefaultsSource("OBS_2025");
		setResizable(true);

		setTitle("TAHSILAT");
		setClosable(true);
		setBounds(100, 100, 800, 460);
		
		tabbedPane = new MaterialTabbed();
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(tabbedPane.getSelectedIndex()==0)
				{
					
				}
				else if(tabbedPane.getSelectedIndex() == 1)
				{
					ayar_doldur();
				}
			}
		});
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Tahsilat Fisi", null, panel, null);
		panel.setLayout(null);
		
		DecimalFormat df = new DecimalFormat(); // And here..
		NumberFormatter dnff = new NumberFormatter(df);
		DefaultFormatterFactory f_dob = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(29, 30, 750, 75);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		cmbCins = new JComboBox<String>();
		cmbCins.setBounds(10, 11, 149, 22);
		panel_2.add(cmbCins);
		cmbCins.setModel(new DefaultComboBoxModel(new String[] {"Tahsilat", "Tediye"}));
		
		cmbTur = new JComboBox<String>();
		cmbTur.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(cmbTur.getSelectedIndex()==1)
				{
					panel_4.setVisible(true);
					tabbedPane.setEnabledAt(2, true);
				}
				else {
					panel_4.setVisible(false);
					tabbedPane.setEnabledAt(2, false);
				}
				
			}
		});
		cmbTur.setBounds(10, 44, 149, 22);
		panel_2.add(cmbTur);
		cmbTur.setModel(new DefaultComboBoxModel(new String[] {"Nakit", "Cek", "Kredi Karti"}));
		
		dtc = new JDateChooser();
		dtc.setBounds(281, 11, 150, 24);
		panel_2.add(dtc);
		dtc.getDateEditor().getUiComponent().addFocusListener(new FocusAdapter()    {
			@Override
			public void focusGained(FocusEvent evt) {
				final JTextComponent textComponent=((JTextComponent)evt.getSource());
				SwingUtilities.invokeLater(new Runnable(){
					public void run() {
						textComponent.selectAll();
					}});
			}
		});
		dtc.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dtc.setDate(new Date());
				}
			}
		});
		dtc.setDateFormatString("dd.MM.yyyy");
		dtc.setFont(new Font("Tahoma", Font.BOLD, 14));
		dtc.setDate(new Date());
		dtc.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					if (TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc) == null)
					{
						return;
					}

					final JTextComponent textComponent=((JTextComponent)e.getSource());
					int currentCaretPosition = textComponent.getCaretPosition();
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						if (currentCaretPosition >=0 && currentCaretPosition <3)
						{
							cal.add(Calendar.DAY_OF_MONTH, -1); 
						}
						else if (currentCaretPosition >=3 && currentCaretPosition <=5)
						{
							cal.add(Calendar.MONTH,-1);
						}
						else if (currentCaretPosition >=6 )
						{
							cal.add(Calendar.YEAR, -1); 
						}
						dtc.setDate(new Date(cal.getTimeInMillis()));
						textComponent.setCaretPosition(currentCaretPosition);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					if (TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc) == null)
					{
						return;
					}
					final JTextComponent textComponent1=((JTextComponent)e.getSource());
					int currentCaretPosition = textComponent1.getCaretPosition();
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						if (currentCaretPosition >=0 && currentCaretPosition <3)
						{
							cal.add(Calendar.DAY_OF_MONTH, 1); 
						}
						else if (currentCaretPosition >=3 && currentCaretPosition <=5)
						{
							cal.add(Calendar.MONTH,1);
						}
						else if (currentCaretPosition >=6 )
						{
							cal.add(Calendar.YEAR, 1); 
						}
						dtc.setDate(new Date(cal.getTimeInMillis()));
						textComponent1.setCaretPosition(currentCaretPosition);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});


		textEvrakNo = new Obs_TextFIeld();
		textEvrakNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					String[] parts;
					String deger ;
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_DOWN_MASK : KeyEvent.ALT_DOWN_MASK) ) != 0))
						{
							kaydet();
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		textEvrakNo.setText("0");
		textEvrakNo.setHorizontalAlignment(SwingConstants.RIGHT);
		textEvrakNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		textEvrakNo.setColumns(10);
		textEvrakNo.setBounds(598, 11, 96, 24);
		textEvrakNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				fiskont();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		textEvrakNo.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorRemoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorMoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorAdded(AncestorEvent pEvent) {

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						textEvrakNo.requestFocusInWindow();
					}
				});
			}
		});
		textEvrakNo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textEvrakNo.selectAll();
			}
		});

		panel_2.add(textEvrakNo);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeni();
			}
		});
		btnNewButton.setToolTipText("Yeni Fis");
		btnNewButton.setBounds(704, 11, 24, 24);
		btnNewButton.setIcon(new ImageIcon(DEKONT.class.getResource("/ICONLAR/yeni.png")));
		panel_2.add(btnNewButton);

		JLabel lblNewLabel_4 = new JLabel("Fis No");
		lblNewLabel_4.setBounds(540, 15, 48, 14);
		panel_2.add(lblNewLabel_4);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(29, 120, 750, 92);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Cari Kod");
		lblNewLabel_1.setBounds(10, 11, 48, 14);
		panel_1.add(lblNewLabel_1);
		
		textCKodu = new Obs_TextFIeld(12);
		textCKodu.setBounds(10, 33, 150, 22);
		panel_1.add(textCKodu);
		textCKodu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textCKodu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					String[] parts;
					String deger ;
					
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_DOWN_MASK : KeyEvent.ALT_DOWN_MASK) ) != 0))
						{
							kaydet();
						}
					}
					
					deger = GLOBAL.setting_oku("CARI_HSPPLN_CAG").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c = parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_DOWN_MASK  : KeyEvent.ALT_DOWN_MASK ) ) != 0))
						{
							HESAP_PLN hsp ;
							getContentPane().setCursor(oac.WAIT_CURSOR);
							hsp = new HESAP_PLN();
							hsp.show();
							textCKodu.setText(oac.hsp_hsp_kodu);
							String[] bilgiStrings = CARI_ISIM_OKU.isim(textCKodu.getText());
							lblCAdi.setText(bilgiStrings[0]);
							getContentPane().setCursor(oac.DEFAULT_CURSOR);
						}
					}
				}
				catch (Exception ex)
				{

				}
			}
		});
		textCKodu.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.show(); 
						textCKodu.setText( oac.hsp_hsp_kodu);
						getContentPane().setCursor(oac.WAIT_CURSOR);
						String[] bilgiStrings = CARI_ISIM_OKU.isim(textCKodu.getText());
						lblCAdi.setText(bilgiStrings[0]);
						getContentPane().setCursor(oac.DEFAULT_CURSOR);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textCKodu.getDocument().addDocumentListener(new DocumentListener() {
		 public void changedUpdate(DocumentEvent e) {
		  
			    	getContentPane().setCursor(oac.WAIT_CURSOR);
			    	String[] bilgiStrings = CARI_ISIM_OKU.isim(textCKodu.getText());
					lblCAdi.setText(bilgiStrings[0]);
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				
		  }
		  public void removeUpdate(DocumentEvent e) {
		    
			    	getContentPane().setCursor(oac.WAIT_CURSOR);
			    	String[] bilgiStrings = CARI_ISIM_OKU.isim(textCKodu.getText());
					lblCAdi.setText(bilgiStrings[0]);
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
			  }
		  public void insertUpdate(DocumentEvent e) {
		    
			    	getContentPane().setCursor(oac.WAIT_CURSOR);
			    	String[] bilgiStrings = CARI_ISIM_OKU.isim(textCKodu.getText());
					lblCAdi.setText(bilgiStrings[0]);
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
			  }
			});
		textCKodu.setColumns(10);
		
		lblCAdi = new JLabel("...");
		lblCAdi.setBounds(10, 64, 261, 14);
		panel_1.add(lblCAdi);
		
		
		JLabel lblNewLabel_2 = new JLabel("Adres Kod");
		lblNewLabel_2.setBounds(281, 11, 68, 14);
		panel_1.add(lblNewLabel_2);
		
		textAKodu = new Obs_TextFIeld(12);
		textAKodu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					String[] parts;
					String deger ;
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_DOWN_MASK  : KeyEvent.ALT_DOWN_MASK ) ) != 0))
							kaydet();
					}
				} catch (Exception e2) {
				}
			}
		});
		textAKodu.setBounds(281, 33, 150, 22);
		panel_1.add(textAKodu);
		textAKodu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAKodu.setColumns(10);
		textAKodu.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				try {
					lblAAdi.setText(a_Access.kod_ismi(textAKodu.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			public void removeUpdate(DocumentEvent e) {
				try {
					lblAAdi.setText(a_Access.kod_ismi(textAKodu.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			public void insertUpdate(DocumentEvent e) {
				try {
					lblAAdi.setText(a_Access.kod_ismi(textAKodu.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		textAKodu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					ADRES_LISTE asp ;
					asp = new ADRES_LISTE();
					asp.setVisible(true);
					textAKodu.setText( oac.hsp_hsp_kodu);
				}
			}
		});
		
		lblAAdi = new JLabel("...");
		lblAAdi.setBounds(281, 63, 308, 14);
		panel_1.add(lblAAdi);
		
		JLabel lblNewLabel_3 = new JLabel("Tutar");
		lblNewLabel_3.setBounds(692, 11, 48, 14);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		formattedTutar = new JFormattedTextField();
		formattedTutar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					String[] parts;
					String deger ;
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_DOWN_MASK : KeyEvent.ALT_DOWN_MASK) ) != 0))
							kaydet();
					}
				} catch (Exception e2) {
				}
			}
		});
		formattedTutar.setBounds(567, 31, 173, 24);
		panel_1.add(formattedTutar);
		JTextFieldRegularPopupMenu.addTo(formattedTutar);
		formattedTutar.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		formattedTutar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				formattedTutar.selectAll();
			}
		});
		formattedTutar.setFont(new Font("Tahoma", Font.BOLD, 14));
		formattedTutar.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTutar.setFormatterFactory(f_dob);
		formattedTutar.setText("0.00");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Doviz Cinsi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(29, 223, 750, 66);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		combCins = new JComboBox();
		combCins.setBounds(50, 25, 77, 22);
		panel_3.add(combCins);
		combCins.setModel(new DefaultComboBoxModel(new String[] {"TL", "USD", "EUR"}));
		
		panel_4 = new JPanel();
		panel_4.setVisible(false);
		panel_4.setBorder(new TitledBorder(null, "Cek Bilgisi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(29, 295, 750, 75);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		
		JLabel lblNewLabel_5 = new JLabel("Cek Sayisi");
		lblNewLabel_5.setBounds(50, 29, 59, 14);
		panel_4.add(lblNewLabel_5);
		
		lblCekSayi = new JLabel("0");
		lblCekSayi.setBounds(122, 29, 46, 14);
		panel_4.add(lblCekSayi);
		
		JPanel panel_Ayarlar = new JPanel();
		tabbedPane.addTab("Ayarlar", null, panel_Ayarlar, null);
		panel_Ayarlar.setLayout(null);

		imagePanel = new ImagePanel();
		imagePanel.setBorder(new LineBorder(new Color(95, 158, 160), 1,true));
		imagePanel.setBounds(101, 186, 315, 150);
		panel_Ayarlar.add(imagePanel);

		textAdi = new Obs_TextFIeld(50);
		textAdi.setBounds(101, 25, 500, 20);
		panel_Ayarlar.add(textAdi);
		textAdi.setColumns(10);

		textAdres1 = new Obs_TextFIeld(50);
		textAdres1.setColumns(10);
		textAdres1.setBounds(101, 50, 500, 20);
		panel_Ayarlar.add(textAdres1);

		textAdres2 = new Obs_TextFIeld(50);
		textAdres2.setColumns(10);
		textAdres2.setBounds(101, 75, 500, 20);
		panel_Ayarlar.add(textAdres2);

		textVdVn = new Obs_TextFIeld(60);
		textVdVn.setColumns(10);
		textVdVn.setBounds(101, 100, 500, 20);
		panel_Ayarlar.add(textVdVn);

		textMail = new Obs_TextFIeld(60);
		textMail.setColumns(10);
		textMail.setBounds(101, 125, 500, 20);
		panel_Ayarlar.add(textMail);

		textDiger = new Obs_TextFIeld(50);
		textDiger.setColumns(10);
		textDiger.setBounds(101, 150, 500, 20);
		panel_Ayarlar.add(textDiger);

		JLabel lblNewLabel = new JLabel("FIRMA ADI");
		lblNewLabel.setBounds(10, 28, 81, 14);
		panel_Ayarlar.add(lblNewLabel);

		JLabel lblAdres = new JLabel("ADRES 1");
		lblAdres.setBounds(10, 53, 81, 14);
		panel_Ayarlar.add(lblAdres);

		JLabel lblAdres_1 = new JLabel("ADRES 2");
		lblAdres_1.setBounds(10, 78, 81, 14);
		panel_Ayarlar.add(lblAdres_1);

		JLabel lblVdVn = new JLabel("VD - VN");
		lblVdVn.setBounds(10, 103, 81, 14);
		panel_Ayarlar.add(lblVdVn);

		JLabel lblEMail = new JLabel("E MAIL");
		lblEMail.setBounds(10, 128, 81, 14);
		panel_Ayarlar.add(lblEMail);

		JLabel lblDiger = new JLabel("DIGER");
		lblDiger.setBounds(10, 153, 81, 14);
		panel_Ayarlar.add(lblDiger);
		
		JButton btnNewButton_4 = new JButton("Logo Sec");
		btnNewButton_4.setBounds(101, 347, 100, 23);
		btnNewButton_4.setMargin(new Insets(2, 1, 2, 14));
		btnNewButton_4.setIcon(new ImageIcon(H_PLANI.class.getResource("/ICONLAR/icons8-camera-16.png")));
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);

				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Resim Seciniz");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Resim Sec");
				chooser.setApproveButtonToolTipText("Logo Sec");
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("Resim Dosyalari", "jpg", "png", "gif", "bmp"));
				chooser.setApproveButtonMnemonic('s');
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					File file = chooser.getSelectedFile();
					ImageIcon icon = new ImageIcon(file.getPath());
					ImageIcon imageIcon = new ImageIcon(FIT_IMAGE.image(icon.getImage(), 315, 150));
					BufferedImage bi = new BufferedImage(imageIcon .getIconWidth(), imageIcon .getIconHeight(), BufferedImage.TYPE_INT_RGB);
					Graphics2D g = bi.createGraphics();
					imageIcon.paintIcon(null, g, 0, 0);
					g.setColor(Color.WHITE);
					g.dispose();
					imagePanel.setImage(bi);
				}
				else {
				}
			}
		});
		panel_Ayarlar.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Temizle");
		btnNewButton_5.setMargin(new Insets(2, 5, 2, 14));
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_5.setBounds(344, 347, 73, 23);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imagePanel.setImage(null);
			}
		});
		panel_Ayarlar.add(btnNewButton_5);
		
		imageKase = new ImagePanel();
		imageKase.setBorder(new LineBorder(new Color(95, 158, 160), 1,true));
		imageKase.setBounds(440, 186, 315, 150);
		panel_Ayarlar.add(imageKase);
		
		JButton btnNewButton_4_1 = new JButton("Imza Sec");
		btnNewButton_4_1.setMargin(new Insets(2, 1, 2, 14));
		btnNewButton_4_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_4_1.setBounds(440, 347, 100, 23);
		
		btnNewButton_4_1.setIcon(new ImageIcon(H_PLANI.class.getResource("/ICONLAR/icons8-camera-16.png")));
		
		btnNewButton_4_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);

				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Resim Seciniz");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Resim Sec");
				chooser.setApproveButtonToolTipText("Kase Sec");
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("Resim Dosyalari", "jpg", "png", "gif", "bmp"));
				chooser.setApproveButtonMnemonic('s');
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					File file = chooser.getSelectedFile();
					ImageIcon icon = new ImageIcon(file.getPath());
					ImageIcon imageIcon = new ImageIcon(FIT_IMAGE.image(icon.getImage(), 315, 150));
					BufferedImage bi = new BufferedImage(imageIcon .getIconWidth(), imageIcon .getIconHeight(), BufferedImage.TYPE_INT_RGB);
					Graphics2D g = bi.createGraphics();
					imageIcon.paintIcon(null, g, 0, 0);
					g.setColor(Color.WHITE);
					g.dispose();
					imageKase.setImage(bi);
				}
				else {
				}
			}
		});
		
		panel_Ayarlar.add(btnNewButton_4_1);
		
		JButton btnNewButton_5_1 = new JButton("Temizle");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imageKase.setImage(null);
			}
		});
		btnNewButton_5_1.setMargin(new Insets(2, 5, 2, 14));
		btnNewButton_5_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_5_1.setBounds(680, 347, 73, 23);
		panel_Ayarlar.add(btnNewButton_5_1);
		
		panel_5 = new JPanel();
		tabbedPane.addTab("Cek Dokumu", null, panel_5, null);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_5.add(scrollPane, BorderLayout.CENTER);
		
		
		DefaultTableModel model = new DefaultTableModel() ; 
		
		tableCek = new JTable(model);
		
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			tableCek.setGridColor(oac.gridcolor);
		model.addColumn("Banka", new String []{""});
		model.addColumn("Sube", new String []{""});
		model.addColumn("Seri No", new String []{""});
		model.addColumn("Hesap", new String []{"" });
		model.addColumn("Borclu", new String []{""});
		model.addColumn("Vade", new Date []{ new Date() });
		model.addColumn("Tutar",new Double [] {new Double( 0 )});
		
		TableColumn col ;
		col = tableCek.getColumnModel().getColumn(0);
		col.setMinWidth(250);
		Obs_TextFIeld bnk = new Obs_TextFIeld(40);
		col.setCellEditor(new DefaultCellEditor(bnk));
		col.setHeaderRenderer(new SOLA());

		col = tableCek.getColumnModel().getColumn(1);
		col.setMinWidth(150);
		Obs_TextFIeld sb = new Obs_TextFIeld(40);
		col.setCellEditor(new DefaultCellEditor(sb));
		col.setHeaderRenderer(new SOLA());
		
		
		col = tableCek.getColumnModel().getColumn(2);
		col.setMinWidth(150);
		Obs_TextFIeld ser = new Obs_TextFIeld(20);
		col.setCellEditor(new DefaultCellEditor(ser));
		col.setHeaderRenderer(new SOLA());
		
		col = tableCek.getColumnModel().getColumn(3);
		col.setMinWidth(150);
		Obs_TextFIeld hsp = new Obs_TextFIeld(20);
		col.setCellEditor(new DefaultCellEditor(hsp));
		col.setHeaderRenderer(new SOLA());
		
		col = tableCek.getColumnModel().getColumn(4);
		col.setMinWidth(150);
		Obs_TextFIeld brc = new Obs_TextFIeld(40);
		col.setCellEditor(new DefaultCellEditor(brc));
		col.setHeaderRenderer(new SOLA());
		
		col = tableCek.getColumnModel().getColumn(5);
		col.setCellEditor(new JDateChooserEditor(new JCheckBox()));
		col.setHeaderRenderer(new SOLA());
		col.setCellRenderer(new COKLU_GIRIS_TARIH());
		col.setMinWidth(100);
		
		col = tableCek.getColumnModel().getColumn(6);
		col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,true));
		col.setMinWidth(115);
		
		JTableHeader th = tableCek.getTableHeader();
		Dimension dd = tableCek.getPreferredSize();
		dd.height = 30;
		th.setPreferredSize(dd); 
		
		tableCek.setRowHeight(22);
		tableCek.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		tableCek.setRowSelectionAllowed(false);
		tableCek.setShowHorizontalLines(true);
		tableCek.setShowVerticalLines(true);
		th.repaint();
		InputMap im = tableCek.getInputMap(JTable.WHEN_FOCUSED);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Action.NextCell");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Action.NextCell");
		ActionMap am = tableCek.getActionMap();
		am.put("Action.NextCell", new Next_Cell_Kereste(tableCek,"tahsil"));
		tableCek.addFocusListener(new FocusListener()
	      {
	         @Override
	         public void focusGained(FocusEvent e)
	         {
	           
	         }
	 
	         @Override
	         public void focusLost(FocusEvent e)
	         {
	        	 if(tableCek.getSelectedColumn() == -1)
	        	 {
	        	 if (tableCek.isEditing())
				     tableCek.getCellEditor().stopCellEditing();
	        	 }
	         }
	      });
		tableCek.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				cek_toplami();
			}
		});
		scrollPane.setViewportView(tableCek);
		tabbedPane.setEnabledAt(2, false);
		fis_temizle();
	}
	public static void kaydet()
	{
		if(tabbedPane.getSelectedIndex() == 1) // ayarlar
			ayar_kayit();
		else 
		{
			fis_kayit();
			fis_temizle();
		}
		tabbedPane.setSelectedIndex(0);
	}
	private static void fis_kayit()
	{
		if (textEvrakNo.getText() == null ) return ;
		if (textEvrakNo.getText().equals("0") ) return ;
		if (textEvrakNo.getText().equals("")  ) return ;
		try 
		{
		c_Access.tah_kayit(cmbCins.getSelectedIndex(), cmbTur.getSelectedIndex(),textEvrakNo.getText(), 
				TARIH_CEVIR.tarih_geri_saatli(dtc) ,textCKodu.getText(), textAKodu.getText(), "", 
				DecimalFormat.getNumberInstance().parse(formattedTutar.getText()).doubleValue(),combCins.getSelectedItem().toString());
		c_Access.tah_cek_sil(textEvrakNo.getText(), cmbCins.getSelectedIndex());
		if(cmbTur.getSelectedIndex() == 1)
		{
			DefaultTableModel model = (DefaultTableModel)tableCek.getModel();
			for (int i = 0; i <= tableCek.getRowCount() - 1 ; i ++)
			{
				if ( model.getValueAt(i,0).toString() != null )
				{
					if ( ! model.getValueAt(i,0).toString().equals(""))
					{
						if ( model.getValueAt(i,6).toString() != null )
						{
							String vade = "";
							if (model.getValueAt(i , 5).toString().length() >  10)
								vade = dateFormater(model.getValueAt(i , 5).toString() , "yyyy.MM.dd", "EEE MMM dd kk:mm:ss zzzz yyyy" ) ;
							else
							{
								String qwe =dateFormater(model.getValueAt(i , 5).toString() , "yyyy.MM.dd", "dd.MM.yyyy" ) ;
								vade  = qwe;
							}
							c_Access.tah_cek_kayit(textEvrakNo.getText(), cmbCins.getSelectedIndex(),
									tableCek.getValueAt(i , 0).toString(), tableCek.getValueAt(i , 1).toString(), 
									tableCek.getValueAt(i , 2).toString(),tableCek.getValueAt(i , 3).toString(), 
									tableCek.getValueAt(i , 4).toString(),
									vade,
									(double) tableCek.getValueAt(i , 6));
						}
					}
				}
			}
		}
		
		} catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private static void ayar_kayit()
	{
		try {
		InputStream fis = null;
		if ( imagePanel.getImage()) 
		{
			BufferedImage bi = new BufferedImage( imagePanel.getWidth(), imagePanel.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.createGraphics();
			imagePanel.paintComponent(g);
			g.drawImage(bi, 0, 0, null);
			g.setColor(Color.WHITE);
			g.dispose();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", os);
			fis = new ByteArrayInputStream(os.toByteArray());
			os.flush();
			os.close();
		}
		InputStream fis1 = null;
		if ( imageKase.getImage()) 
		{
			BufferedImage bi = new BufferedImage( imageKase.getWidth(), imageKase.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.createGraphics();
			imageKase.paintComponent(g);
			g.drawImage(bi, 0, 0, null);
			g.setColor(Color.WHITE);
			g.dispose();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", os);
			fis1 = new ByteArrayInputStream(os.toByteArray());
			os.flush();
			os.close();
		}
		c_Access.tah_ayar_sil();
		c_Access.tah_ayar_kayit(textAdi.getText(), textAdres1.getText(), textAdres2.getText(),textVdVn.getText(), textMail.getText(), textDiger.getText(), fis,fis1);

		} catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void ayar_doldur()
	{
		try {
		ayar_temizle();
	 	ResultSet rs = c_Access.tah_ayar_oku();
	 	if (!rs.isBeforeFirst() ) {  
			return;
		} 
	 	rs.next();
		if (  rs.getBytes("LOGO") != null)
		{
			byte[] img = rs.getBytes("LOGO");
			ImageIcon image = new ImageIcon(img);
			Image im = image.getImage();
			ImageIcon newImage = new ImageIcon(im);
			BufferedImage bi = new BufferedImage(newImage .getIconWidth(), newImage .getIconHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bi.createGraphics();
			newImage.paintIcon(null, g, 0, 0);
			g.setColor(Color.WHITE);
			g.dispose();
			imagePanel.setImage(bi);
		}
		if (  rs.getBytes("KASE") != null)
		{
			byte[] img = rs.getBytes("KASE");
			ImageIcon image = new ImageIcon(img);
			Image im = image.getImage();
			ImageIcon newImage = new ImageIcon(im);
			BufferedImage bi = new BufferedImage(newImage .getIconWidth(), newImage .getIconHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bi.createGraphics();
			newImage.paintIcon(null, g, 0, 0);
			g.setColor(Color.WHITE);
			g.dispose();
			imageKase.setImage(bi);
		}
		textAdi.setText(rs.getString("FIR_ISMI"));
		textAdres1.setText(rs.getString("ADR_1"));
		textAdres2.setText(rs.getString("ADR_2"));
		textVdVn.setText(rs.getString("VD_VN"));
		textMail.setText(rs.getString("MAIL"));
		textDiger.setText(rs.getString("DIGER"));
		} catch (Exception ex) {
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
		}
	}
	private void fiskont()
	{
		try {
		if (textEvrakNo.getText() == null) 
		{
			textEvrakNo.requestFocus();
			return;
		}
		if (Long.parseLong(textEvrakNo.getText())  > 2147483647 )
		{
			textEvrakNo.requestFocus();
			return;
		}
		ResultSet rs =null;
		rs = c_Access.tah_oku(textEvrakNo.getText(),cmbCins.getSelectedIndex());
		fis_temizle();
		if (!rs.isBeforeFirst() ) { 
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Bu Numarada Kayitli Fis Yok......" );
			return; // Kayit Yok
		} 
		rs.next();
		textEvrakNo.setText(rs.getString("EVRAK"));
		cmbTur.setSelectedIndex(rs.getInt("TUR"));
		combCins.setSelectedItem(rs.getString("DVZ_CINS"));
		textCKodu.setText(rs.getString("C_HES"));
		textAKodu.setText(rs.getString("A_HES"));
		formattedTutar.setText(FORMATLAMA.doub_2(rs.getDouble("TUTAR")));
		dtc.setDate(rs.getDate("TARIH"));
		if(cmbTur.getSelectedIndex() == 1 )
			cek_doldur();
		} catch (Exception ex) {
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
		}
	}
	public static  void yoket() 
	{
		if (textEvrakNo.getText() == null ) return ;
		if (textEvrakNo.getText().equals("0") ) return ;
		if (textEvrakNo.getText().equals("")  ) return ;
		try 
		{
			int g =  JOptionPane.showOptionDialog( null,  "Islem Dosyadan Silinecek ..?", "Cari Dosyasindan Evrak Silme",   JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,	   			 	null,   	oac.options,   	oac.options[1]); 
			if(g != 0 ) { return;	}
			c_Access.tah_sil(textEvrakNo.getText(),cmbCins.getSelectedIndex());
			fis_temizle();
			textEvrakNo.requestFocus();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage()  );
		}
	}
	public static void cari_kaydet()
	{
		try 
		{
			if (textEvrakNo.getText() == null ) return ;
			if (textEvrakNo.getText().equals("0") ) return ;
			if (textEvrakNo.getText().equals("")  ) return ;
			oac.hsp_hsp_kodu = "" ;
			BORC_ALACAK hsp ;
			hsp = new BORC_ALACAK();
			hsp.setLocationRelativeTo(OBS_MAIN.desktopPane);

			String bh = "",alh="";
			if(cmbCins.getSelectedIndex()==0)
				hsp.lblNewLabel.setText("Borclu Hesap");
			else  if(cmbCins.getSelectedIndex()==1)
				hsp.lblNewLabel.setText("Alacakli Hesap");
			hsp.setVisible(true);
			if(cmbCins.getSelectedIndex()==0)
			{
				bh = oac.hsp_hsp_kodu;
				alh = textCKodu.getText();
			}
			else  if(cmbCins.getSelectedIndex()==1)
			{
				alh = oac.hsp_hsp_kodu;
				bh = textCKodu.getText();
			}
			if (alh.equals("")) return ;
			if (bh.equals("")) return ;

			GuiUtil.setWaitCursor(tabbedPane,true);
			ResultSet rs ;
			rs = c_Access.hesap_adi_oku(alh);
			if (!rs.isBeforeFirst() ) {  
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, alh + " Bu numarada hesaba rastlanmadi!!!!" );
				return ;
			} 
			rs= null;
			rs = c_Access.hesap_adi_oku(bh);
			if (!rs.isBeforeFirst() ) {  
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,  bh +  " Bu numarada hesaba rastlanmadi!!!!" );
				return;
			} 
			int e_number = 0;
			e_number = c_Access.cari_fisno_al();
			dEKONT_BILGI dBilgi = new dEKONT_BILGI();
			dBilgi.setbHES(bh);
			dBilgi.settAR(TARIH_CEVIR.tarih_geri_saatli(dtc));
			dBilgi.seteVRAK(e_number);
			dBilgi.setbCINS("");
			dBilgi.setbKUR(1);
			dBilgi.setbORC(DecimalFormat.getNumberInstance().parse(formattedTutar.getText()).doubleValue());
			dBilgi.setaHES(alh);
			dBilgi.setaCINS("");
			dBilgi.setaKUR(1);
			dBilgi.setaLACAK(DecimalFormat.getNumberInstance().parse(formattedTutar.getText()).doubleValue());
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.seteVRAK(Integer.toString(e_number));
			if(cmbTur.getSelectedIndex()==0)
			{
				dBilgi.setiZAHAT(textEvrakNo.getText() + " Nolu Tah.Fisi ile Nakit ");
				lBILGI.setmESAJ(textEvrakNo.getText() + " Nolu Tah.Fisi ile Nakit ");
			}
			else if(cmbTur.getSelectedIndex()==1)
			{
				dBilgi.setiZAHAT(textEvrakNo.getText() + " Nolu Tah.Fisi ile Cek  ");
				lBILGI.setmESAJ(textEvrakNo.getText() + " Nolu Tah.Fisi ile Cek  ");
			}
			else if(cmbTur.getSelectedIndex()==2)
			{
				dBilgi.setiZAHAT(textEvrakNo.getText() + " Nolu Tah.Fisi ile Kredi Karti ");
				lBILGI.setmESAJ(textEvrakNo.getText() + " Nolu Tah.Fisi ile Kredi Karti ");
			}
			if(cmbCins.getSelectedIndex()==0)
				dBilgi.setkOD("Tahs.");
			else if(cmbCins.getSelectedIndex()==1)
				dBilgi.setkOD("Tedi.");
			dBilgi.setuSER( GLOBAL.KULL_ADI);
			c_Access.cari_dekont_kaydet(dBilgi,	lBILGI ,BAGLAN_LOG.cariLogDizin	);
			GuiUtil.setWaitCursor(tabbedPane,false);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO,  "Cari Hesaba Basari ile Kaydedilmistir....");
		} catch (Exception ex) {
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage()  );
		}
	}
	private void yeni()
	{
		try
		{
			int evr = 0;
			if(cmbCins.getSelectedItem().equals("Tahsilat"))
				evr =  c_Access.cari_tah_fisno_al("GIR");
			else
				evr =  c_Access.cari_tah_fisno_al("CIK");
			textEvrakNo.setText(Integer.toString(evr));
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
		}
	}

	private void ayar_temizle()
	{
		textAdi.setText("");
		textAdres1.setText("");
		textAdres2.setText("");
		textVdVn.setText("");
		textMail.setText("");
		textDiger.setText("");
		imagePanel.setImage(null);
		imageKase.setImage(null);
	}
	private static void fis_temizle()
	{
		textEvrakNo.setText("0");
		cmbCins.setSelectedIndex(0);
		cmbTur.setSelectedIndex(0);
		textCKodu.setText("");
		textAKodu.setText("");
		formattedTutar.setText("0.00");
		lblCAdi.setText("");
		lblAAdi.setText("");
		combCins.setSelectedIndex(0);
		dtc.setDate(new Date());
		GRID_TEMIZLE.grid_temizle(tableCek);
		for (int i = 0; i <= 15; i ++)
			satir_ilave();
	}
	public static void satir_ilave()
	{
		DefaultTableModel mdl = (DefaultTableModel) tableCek.getModel();
		int satir = tableCek.getSelectedRow();
		if ( satir  < 0 ) 
		{
			mdl.addRow(new Object[]{"", "","","","",new Date(),0.00});
			satir = 0 ;
		}
		else if ( satir  >= 0 )
			mdl.insertRow(satir, new Object[]{"", "","","","",new Date(),0.00});
		tableCek.isRowSelected(satir);
		tableCek.repaint();
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
	private void cek_toplami()
	{
		double tutar = 0 ;
		int evr_sayi = 0 ;
		DefaultTableModel model = (DefaultTableModel)tableCek.getModel();
		for (int i = 0; i <= tableCek.getRowCount() - 1 ; i ++)
		{
			if ( model.getValueAt(i,0).toString() != null )
			{
				if ( ! model.getValueAt(i,0).toString().equals(""))
				{
					if ( model.getValueAt(i,6).toString() != null )
					{
						tutar  += (double) tableCek.getValueAt(i , 6);
						evr_sayi += 1 ;
					}
				}
			}
		}
		lblCekSayi.setText(FORMATLAMA.doub_0(evr_sayi));
		formattedTutar.setText(FORMATLAMA.doub_2(tutar));
	}
	private void cek_doldur()
	{
		try {
		ResultSet rs = c_Access.tah_cek_doldur(textEvrakNo.getText(),cmbCins.getSelectedIndex());
		if (!rs.isBeforeFirst() ) {  
			return;
		} 
		DefaultTableModel mdll = (DefaultTableModel) tableCek.getModel();
		int satir =0 ;
		while (rs.next()) 
		{
			SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
			String vade1 =  format1.format(rs.getDate("TARIH"));
			mdll.insertRow(satir,new Object[]{rs.getString("BANKA"),
					rs.getString("SUBE"),rs.getString("SERI"),rs.getString("HESAP"),
					rs.getString("BORCLU"), vade1,	rs.getDouble("TUTAR")});
			satir +=1 ;
			if (satir == mdll.getRowCount())
				mdll.addRow(new Object[]{"", "","","","",new Date(),0.00});
			else
				mdll.removeRow(mdll.getRowCount() -1);	
		}
		} catch (Exception e) {
			
		}
	}
}

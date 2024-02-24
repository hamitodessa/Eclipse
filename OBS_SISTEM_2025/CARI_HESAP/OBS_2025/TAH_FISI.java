package OBS_2025;

import javax.swing.JInternalFrame;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;

import OBS_C_2025.MaterialTabbed;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.lOG_BILGI;
import raven.toast.Notifications;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.ImagePanel;
import OBS_C_2025.JTextFieldRegularPopupMenu;

import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.JTextComponent;
import javax.swing.text.NumberFormatter;

import org.bridj.dyncall.DyncallLibrary.DCCallVM;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

@SuppressWarnings({ "unchecked", "rawtypes" ,"deprecation","static-access"})
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
	private static Obs_TextFIeld textEvrakNo;
	private static Obs_TextFIeld textCKodu;
	private static Obs_TextFIeld textAKodu;
	
	private static JLabel lblCAdi ;
	private static JLabel lblAAdi ;
	private static JFormattedTextField formattedTutar ;
	private static JComboBox<String> cmbCins ;
	private static JComboBox<String> cmbTur ;
	private static MaterialTabbed tabbedPane;
	private static ImagePanel imagePanel;
	private static ImagePanel imageKase;
	private static JDateChooser dtc ;
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
		
		cmbCins = new JComboBox<String>();
		cmbCins.setModel(new DefaultComboBoxModel(new String[] {"Tahsilat", "Tediye"}));
		cmbCins.setBounds(29, 43, 149, 22);
		panel.add(cmbCins);
		
		cmbTur = new JComboBox<String>();
		cmbTur.setModel(new DefaultComboBoxModel(new String[] {"Nakit", "Cek", "Kredi Karti"}));
		cmbTur.setBounds(29, 76, 149, 22);
		panel.add(cmbTur);
		
		dtc = new JDateChooser();
		dtc.setBounds(300, 43, 128, 20);
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
		dtc.setFont(new Font("Tahoma", Font.BOLD, 16));
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

		panel.add(dtc);
		
		textEvrakNo = new Obs_TextFIeld();
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

		textEvrakNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		textEvrakNo.setHorizontalAlignment(SwingConstants.RIGHT);
		textEvrakNo.setText("0");
		textEvrakNo.setBounds(631, 44, 96, 24);
		panel.add(textEvrakNo);
		textEvrakNo.setColumns(10);
		
		textCKodu = new Obs_TextFIeld(12);
		textCKodu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textCKodu.setBounds(29, 152, 130, 20);
		textCKodu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					String[] parts;
					String deger ;
					deger = GLOBAL.setting_oku("CARI_HSPPLN_CAG").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
							HESAP_PLN hsp ;
							getContentPane().setCursor(oac.WAIT_CURSOR);
							hsp = new HESAP_PLN();
							hsp.show();
							textCKodu.setText(oac.hsp_hsp_kodu);
							lblCAdi.setText(CARI_ISIM_OKU.isim(textCKodu.getText())[0]);
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
						lblCAdi.setText(CARI_ISIM_OKU.isim(textCKodu.getText())[0]);
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
			    	lblCAdi.setText(CARI_ISIM_OKU.isim(textCKodu.getText())[0]);
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				
		  }
		  public void removeUpdate(DocumentEvent e) {
		    
			    	getContentPane().setCursor(oac.WAIT_CURSOR);
			    	lblCAdi.setText(CARI_ISIM_OKU.isim(textCKodu.getText())[0]);
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				
			  }
		  public void insertUpdate(DocumentEvent e) {
		    
			    	getContentPane().setCursor(oac.WAIT_CURSOR);
			    	lblCAdi.setText(CARI_ISIM_OKU.isim(textCKodu.getText())[0]);
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				
			  }
			});

		panel.add(textCKodu);
		textCKodu.setColumns(10);
		
		textAKodu = new Obs_TextFIeld(12);
		textAKodu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAKodu.setColumns(10);
		textAKodu.setBounds(300, 152, 130, 20);
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

		panel.add(textAKodu);
		
		JLabel lblNewLabel_1 = new JLabel("Cari Kod");
		lblNewLabel_1.setBounds(29, 130, 48, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Adres Kod");
		lblNewLabel_2.setBounds(300, 130, 68, 14);
		panel.add(lblNewLabel_2);
		
		lblAAdi = new JLabel("...");
		lblAAdi.setBounds(300, 182, 308, 14);
		panel.add(lblAAdi);
		
		lblCAdi = new JLabel("...");
		lblCAdi.setBounds(29, 183, 261, 14);
		panel.add(lblCAdi);
		
		formattedTutar = new JFormattedTextField();
		formattedTutar.setBounds(586, 150, 173, 24);
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
		
		DecimalFormat df = new DecimalFormat(); // And here..
		NumberFormatter dnff = new NumberFormatter(df);
		DefaultFormatterFactory f_dob = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		formattedTutar.setFormatterFactory(f_dob);
		formattedTutar.setText("0.00");
		panel.add(formattedTutar);
		
		JLabel lblNewLabel_3 = new JLabel("Tutar");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(711, 130, 48, 14);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeni();
			}
		});
		btnNewButton.setToolTipText("Yeni Fis");
		btnNewButton.setIcon(new ImageIcon(DEKONT.class.getResource("/ICONLAR/yeni.png")));
		btnNewButton.setBounds(737, 43, 24, 24);
		panel.add(btnNewButton);
		
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
		fis_temizle();
	}
	public static void kaydet()
	{
		if(tabbedPane.getSelectedIndex() == 0) // Fis
		{
			fis_kayit();
			fis_temizle();
		}
		else if(tabbedPane.getSelectedIndex() == 1) // ayarlar
		{
			ayar_kayit();
		}
	}
	private static void fis_kayit()
	{
		if(textEvrakNo.getText().equals("0")) return;
		try 
		{
		c_Access.tah_kayit(cmbCins.getSelectedIndex(), cmbTur.getSelectedIndex(),textEvrakNo.getText(), 
				TARIH_CEVIR.tarih_geri_saatli(dtc) ,textCKodu.getText(), textAKodu.getText(), "", 
				DecimalFormat.getNumberInstance().parse(formattedTutar.getText()).doubleValue());
		
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
	private void yeni()
	{
		try
		{
			int evr=0;
			if(cmbCins.getSelectedItem().equals("Tahsilat"))
			{
				evr =  c_Access.cari_tah_fisno_al("GIR");
			}
			else {
				evr =  c_Access.cari_tah_fisno_al("CIK");
			}
			textEvrakNo.setText(Integer.toString(evr));
			//kutu_ac();
			//sifirla();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
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
		textCKodu.setText(rs.getString("C_HES"));
		textAKodu.setText(rs.getString("A_HES"));
		formattedTutar.setText(FORMATLAMA.doub_2(rs.getDouble("TUTAR")));
		dtc.setDate(rs.getDate("TARIH"));
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
			//JOptionPane.showMessageDialog(null,  ex.getMessage(), "Dekont Silme", JOptionPane.ERROR_MESSAGE);
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
		cmbCins.setSelectedIndex(0);
		cmbTur.setSelectedIndex(0);
		textCKodu.setText("");
		textAKodu.setText("");
		formattedTutar.setText("0.00");
		textEvrakNo.setText("0");
		lblCAdi.setText("");
		lblAAdi.setText("");
		dtc.setDate(new Date());
	}
}
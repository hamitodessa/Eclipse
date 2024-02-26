package OBS_2025;

import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.JTextComponent;
import javax.swing.text.NumberFormatter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import raven.toast.Notifications;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;

import OBS_C_2025.ScrollBarWin11UI;
import OBS_C_2025.ScrollPaneWin11;

import com.businessobjects.visualization.dataexchange.consumer.DataHandler;
import com.crystaldecisions.reports.queryengine.EnumValueToNameMaps;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.toedter.calendar.JDateChooser;

import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.JTextFieldRegularPopupMenu;
import OBS_C_2025.KUR_ACCESS;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.dEKONT_BILGI;
import OBS_C_2025.lOG_BILGI;


import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.DefaultComboBoxModel;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.UIManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JList;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseMotionAdapter;


@SuppressWarnings({"serial","static-access" ,"deprecation","unused"} )
public class DEKONT extends JInternalFrame {

	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	
	private static String [] hpl = {"","",""};
	NumberFormat nf = NumberFormat.getIntegerInstance();
	NumberFormatter nff = new NumberFormatter(nf);
	DefaultFormatterFactory f_int = new DefaultFormatterFactory(nff);
	public static  Obs_TextFIeld txtevrak;
	private static JTextArea txtaciklama ;
	private static Obs_TextFIeld txtkod;
	private static JDateChooser dtc ;
	private static JFormattedTextField txtbkur ;
	private static JFormattedTextField txtbtutar  ;
	private static JFormattedTextField txtakur ;
	private static JFormattedTextField txtatutar ;
	private static boolean borc_kutu = false ;
	private static boolean alacak_kutu = false ;
	private static boolean bakiye_goster = false ;
	private static JComboBox<String> cmbb ;
	private static JComboBox<String> cmba ;
	private static JComboBox<String> cmbbhes;
	private static JComboBox<String> cmbahes;
	private static JButton btnbh ;
	private static JButton btnah ;
	private static JButton btnHYenileB;
	private static JButton btnHYenileA;
	private JButton btnAciksil ;
	private JButton btnNewButton;
	private JLabel lblNewLabel_2_1  ;
	private JLabel lblNewLabel_2 ;
	private static JLabel lblNewLabel ;
	private static JLabel lblb  ;
	private static JLabel lbla  ;
	private static JLabel lblbb ;
	private static JLabel lblba ;
	private static JLabel lblbba ;
	private static JLabel lblab ;
	private static JLabel lblaa ;
	private static JLabel lblaba ;
	private JPanel pnlb ;
	private JPanel pnla;

	public DEKONT() {
		FlatLaf.registerCustomDefaultsSource("OBS_2025");
		setResizable(true);

		setTitle("DEKONT");
		setClosable(true);
		setBounds(0, 0, 740, 415);
		
		ScrollPaneWin11 scrollPaneust = new ScrollPaneWin11();
		getContentPane().add(scrollPaneust, BorderLayout.CENTER);
		JPanel panelANA = new JPanel();
		panelANA.setPreferredSize(new Dimension(720,370));
		panelANA.setLayout(null);
		scrollPaneust.setViewportView(panelANA);
		
		JPanel panel = new JPanel();
		panelANA.add(panel);
		panel.setBounds(10, 11, 710, 77);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Evrak No", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		
		panel.setLayout(null);

		dtc = new JDateChooser();
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
		dtc.setBounds(141, 25, 147, 30);
		dtc.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
				{	
					cmbbhes.requestFocus();
				}
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
				try {
				String[] parts;
				String deger ;
				deger = oac.glb.setting_oku("PRG_KAYDET").toString();
				parts = deger.split(",");
				if ( ! parts[2].equals(" ")) 
				{
					char c=parts[2].charAt(0);
					if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
					{
						kaydet();
					}
				}
				deger = oac.glb.setting_oku("CARI_DEK_Y_FIS").toString();
				parts = deger.split(",");
				if ( ! parts[2].equals(" ")) 
				{
					char c=parts[2].charAt(0);
					if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
					{
						yeni();
					}
				}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel.add(dtc);

		btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Yeni Dekont");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yeni();
			}
		});
		btnNewButton.setIcon(new ImageIcon(DEKONT.class.getResource("/ICONLAR/yeni.png")));
		btnNewButton.setBounds(650, 25, 38, 30);
		btnNewButton.addAncestorListener(new AncestorListener() {
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
						btnNewButton.requestFocusInWindow();
					}
				});
			}
		});

		panel.add(btnNewButton);

		txtevrak = new Obs_TextFIeld(20,"");
		txtevrak.setText("0");
		txtevrak.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
					{	
						cmbbhes.requestFocus();
					}
					else if (KeyEvent.getKeyText(e.getKeyCode()) == "Page Up" )
					{	
						getContentPane().setCursor(oac.WAIT_CURSOR);
						int evr = Integer.parseInt(txtevrak.getText());
						evr +=1 ;
						txtevrak.setText(Integer.toString(evr));
						fiskont();
						getContentPane().setCursor(oac.DEFAULT_CURSOR);
					}
					else if (KeyEvent.getKeyText(e.getKeyCode()) == "Page Down" )
					{	
						getContentPane().setCursor(oac.WAIT_CURSOR);
						int evr = Integer.parseInt(txtevrak.getText());
						evr -=1 ;
						txtevrak.setText(Integer.toString(evr));
						fiskont();
						getContentPane().setCursor(oac.DEFAULT_CURSOR);
					}
					else
					{
						String[] parts;
						String deger ;
						deger = oac.glb.setting_oku("PRG_KAYDET").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								kaydet();
							}
						}
						deger = oac.glb.setting_oku("CARI_DEK_Y_FIS").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								yeni();
							}
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) ) 
				{  e.consume();  }
			}
		});
		txtevrak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getContentPane().setCursor(oac.WAIT_CURSOR);
					fiskont();
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				} catch (NumberFormatException e1) {
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
					e1.printStackTrace();
				}
			}
		});
		txtevrak.setHorizontalAlignment(SwingConstants.RIGHT);
		txtevrak.setFont(new Font("Tahoma", Font.BOLD, 22));
		txtevrak.setBounds(511, 25, 127, 30);
		txtevrak.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					btnNewButton.doClick();
				}
			}
		});

		panel.add(txtevrak);
		txtevrak.setColumns(10);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(DEKONT.class.getResource("/ICONLAR/redo-20.png")));
		btnNewButton_1.setToolTipText("Ileri");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getContentPane().setCursor(oac.WAIT_CURSOR);
					int evr = Integer.parseInt(txtevrak.getText());
					evr += 1 ;
					txtevrak.setText(Integer.toString(evr));
					fiskont();
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				} catch (NumberFormatException e1) {
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(435, 25, 55, 30);

		panel.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setToolTipText("Kayitli Son Dekont");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				try {
					getContentPane().setCursor(oac.WAIT_CURSOR);
					rs =  c_Access.cari_sonfisno();
					rs.next();
					if ( rs.getInt("MAX_NO") == 0 ) { 
						kutu_kapa();
						getContentPane().setCursor(oac.DEFAULT_CURSOR);
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Dosyada Hic Kayit Yok" );	
						return; // Kayit Yok
					} 
					int evr = rs.getInt("MAX_NO");
					txtevrak.setText(Integer.toString(evr));
					fiskont();
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				} catch (Exception ex) {
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				}
			}
		});
		btnNewButton_1_1.setIcon(new ImageIcon(DEKONT.class.getResource("/ICONLAR/icons8-open-view-24.png")));
		btnNewButton_1_1.setBounds(381, 25, 55, 30);
		panel.add(btnNewButton_1_1);

		JButton btnNewButton_1_2 = new JButton("");
		btnNewButton_1_2.setToolTipText("Geri");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				try {

					int evr = Integer.parseInt(txtevrak.getText());
					evr -= 1 ;
					if (evr < 1)
					{
						getContentPane().setCursor(oac.DEFAULT_CURSOR);
						return;
					}
					txtevrak.setText(Integer.toString(evr));
					fiskont();
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				} catch (NumberFormatException e1) {
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1_2.setIcon(new ImageIcon(DEKONT.class.getResource("/ICONLAR/undo-20.png")));
		btnNewButton_1_2.setBounds(327, 25, 55, 30);
		panel.add(btnNewButton_1_2);

		JLabel lblNewLabel_1 = new JLabel("Tarih");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(76, 33, 55, 14);
		panel.add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		panelANA.add(panel_1);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Borclu Hesap", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		panel_1.setBounds(10, 99, 710, 87);
		//getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnbh = new JButton("");
		btnbh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbbhes.getItemAt(cmbbhes.getSelectedIndex()).toString() == "") return;
				getContentPane().setCursor(oac.WAIT_CURSOR);
				boolean varmi = OBS_MAIN.pencere_bak("EKSTRE");
				if (varmi  ) 
				{
					try {
						OBS_MAIN.pencere_aktiv_yap("EKSTRE");
					} catch (PropertyVetoException e1) {
						getContentPane().setCursor(oac.DEFAULT_CURSOR);
						e1.printStackTrace();
					}
				}
				else
				{
					JInternalFrame internalFrame;
					internalFrame  = new EKSTRE();
					// yer bul
					int xx= 0 ;
					int yy = 0;
					for(int i=0;i<OBS_MAIN.desktopPane.getAllFrames().length;i++)
					{   
						JInternalFrame frame=(JInternalFrame) OBS_MAIN.desktopPane.getComponent(i);
						String tit=frame.getTitle();
						if (tit.equals("DEKONT") )
						{
							xx =(int) frame.getLocation().getX() + frame.getWidth();
							yy =(int) frame.getLocation().getY();
							break; 
						}
					}
					//yerbul
					internalFrame.setLocation(xx ,yy);
					OBS_MAIN.desktopPane.add(internalFrame);
					internalFrame.setVisible(true);
				}
				try 
				{
					FILTRE intFrame = new FILTRE();
					FILTRE.txtkodu.setText(cmbbhes.getItemAt(cmbbhes.getSelectedIndex()).toString());
					EKSTRE.hisset();
				} 
				catch (NumberFormatException e1) 
				{
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
					e1.printStackTrace();
				}
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		btnbh.setEnabled(false);
		btnbh.setIcon(new ImageIcon(DEKONT.class.getResource("/ICONLAR/eks16.png")));
		btnbh.setBounds(190, 45, 30, 25);
		panel_1.add(btnbh);

		lblb = new JLabel("...");
		lblb.setForeground(new Color(128, 0, 0));
		lblb.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblb.setBounds(230, 50, 46, 20);
		panel_1.add(lblb);

		cmbb = new JComboBox<String>();
		cmbb.setModel(new DefaultComboBoxModel<String>(new String[] {"", "MA", "MS", "SA", "SS", "BA", "BS"}));
		cmbb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double bkur = 1 ;
				if (! cmbb.getItemAt(cmbb.getSelectedIndex()).equals("")) 
				{
					bkur = kur_oku(lblb.getText(),cmbb.getItemAt(cmbb.getSelectedIndex()));
				}
				txtbkur.setText(FORMATLAMA.doub_4(bkur));
			}
		});
		cmbb.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
					{	
						txtbkur.requestFocus();
					}
					else
					{
						String[] parts;
						String deger ;
						deger = oac.glb.setting_oku("PRG_KAYDET").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								kaydet();
							}
						}
						deger = oac.glb.setting_oku("CARI_DEK_Y_FIS").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								yeni();
							}
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		cmbb.setEnabled(false);
		cmbb.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbb.setBounds(290, 45, 70, 25);
		panel_1.add(cmbb);

		txtbtutar = new JFormattedTextField();
		JTextFieldRegularPopupMenu.addTo(txtbtutar);
		txtbtutar.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		txtbtutar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
						txtbtutar.selectAll();
			}
		});

		txtbtutar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					if (txtbtutar.isEnabled() == false) return;
					try 
					{
						Double dbal = DecimalFormat.getNumberInstance().parse(txtatutar.getText()).doubleValue();
						Double kusur = Double.parseDouble(txtbkur.getText());
						Double kusur2 = Double.parseDouble(txtakur.getText());
						if (kusur !=  1)
						{
							if (kusur2 != 1 ) // ' capraz kur
							{
								double	db1 = (kusur2 * dbal) / kusur;
								//txtbtutar.setText(Double.toString(db1));
								txtbtutar.setText(FORMATLAMA.doub_2(db1));
							}
							else  //' carpraz kur degil 
							{
								double d1 =  dbal  / kusur ;
								//txtbtutar.setText(Double.toString(d1));
								txtbtutar.setText(FORMATLAMA.doub_2(d1));
							}
						}
						else
						{
							double d2 = kusur2 * dbal;
							txtbtutar.setText(FORMATLAMA.doub_2(d2));
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		txtbtutar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try
				{
					if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
					{	
						cmbahes.requestFocus();
					}
					else
					{
						String[] parts;
						String deger ;
						deger = oac.glb.setting_oku("PRG_KAYDET").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								kaydet();
							}
						}
						deger = oac.glb.setting_oku("CARI_DEK_Y_FIS").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								yeni();
							}
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char cc =  e.getKeyChar();
				//System.out.println((int)e.getKeyChar());
				if ( Character.isDigit(cc)  ) 
				{
					txtbtutar.setEditable(true);
				}
				else {
					if ( cc == '.' || cc == ',' || (int)e.getKeyChar() ==8 || (int)e.getKeyChar() ==127   ) {
						txtbtutar.setEditable(true);
					}
					else {
						txtbtutar.setEditable(false);
					}
				}
			}
		});
		txtbtutar.setEnabled(false);
		txtbtutar.setFont(new Font("Tahoma", Font.BOLD, 22));
		txtbtutar.setHorizontalAlignment(SwingConstants.RIGHT);
		txtbtutar.setText("0.00");
		DecimalFormat df = new DecimalFormat(); // And here..
		NumberFormatter dnff = new NumberFormatter(df);
		DefaultFormatterFactory f_dob = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		txtbtutar.setFormatterFactory(f_dob);
		txtbtutar.setBounds(512, 40, 190, 30);
		panel_1.add(txtbtutar);

		txtbkur = new JFormattedTextField();
		txtbkur.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		txtbkur.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtbkur.selectAll();
			}
		});
		txtbkur.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				try {
					if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
					{	
						txtbtutar.requestFocus();
					}
					else
					{
						String[] parts;
						String deger ;
						deger = oac.glb.setting_oku("PRG_KAYDET").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								kaydet();
							}
						}
						deger = oac.glb.setting_oku("CARI_DEK_Y_FIS").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								yeni();
							}
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char cc =  e.getKeyChar();
				if ( Character.isDigit(cc)  ) 
				{
					txtbkur.setEditable(true);
				}
				else {
					if ( cc == '.' || cc == ','  ) {
						txtbkur.setEditable(true);
					}
					else {
						txtbkur.setEditable(false);
					}
				}
			}
		});
		txtbkur.setEnabled(false);
		txtbkur.setText("1.0000");
		DecimalFormat df1 = new DecimalFormat(); 
		NumberFormatter dnff1 = new NumberFormatter(df1);
		DefaultFormatterFactory f_dob1 = new DefaultFormatterFactory(dnff1); 

		df1.setMinimumFractionDigits(4);
		df1.setMaximumFractionDigits(4);
		txtbkur.setFormatterFactory(f_dob1);
		txtbkur.setHorizontalAlignment(SwingConstants.RIGHT);
		txtbkur.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtbkur.setBounds(390, 45, 96, 25);
		panel_1.add(txtbkur);

		lblNewLabel_2 = new JLabel("...");

		lblNewLabel_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 21, 370, 16);
		panel_1.add(lblNewLabel_2);


		pnlb = new JPanel();
		pnlb.setBorder(UIManager.getBorder("ToolTip.border"));
		pnlb.setBounds(390, 15, 312, 20);
		pnlb.setVisible(false);
		panel_1.add(pnlb);
		pnlb.setLayout(null);

		lblba = new JLabel("0.00");
		lblba.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		lblba.setBounds(102, 2, 100, 14);
		pnlb.add(lblba);
		lblba.setHorizontalAlignment(SwingConstants.RIGHT);

		lblbba = new JLabel("0.00");
		lblbba.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblbba.setForeground(new Color(255, 0, 0));
		lblbba.setBounds(205, 2, 100, 14);
		pnlb.add(lblbba);
		lblbba.setHorizontalAlignment(SwingConstants.RIGHT);

		lblbb = new JLabel("0.00");
		lblbb.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		lblbb.setBounds(0, 2, 100, 14);
		pnlb.add(lblbb);
		lblbb.setHorizontalAlignment(SwingConstants.RIGHT);

		
		cmbbhes = new JComboBox<String>();  //******************************BORCLU HESAP **********************
		cmbbhes.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		cmbbhes.setFont(new Font("Tahoma", Font.BOLD, 15));
		cmbbhes.setEnabled(false);
       
		cmbbhes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				hpl = new String[3];
				hpl = isim(cmbbhes.getSelectedItem() == null ? "":cmbbhes.getSelectedItem().toString());
				lblNewLabel_2.setText(hpl[0]);
				lblb.setText(hpl[1]);
				if (hpl[2].toString().equals("F"))
				{borc_kutu = false ; } else { borc_kutu = true ;}
				if (bakiye_goster) 
				{
					bakiye_doldur("B");
				}
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		
        
		cmbbhes.getEditor().getEditorComponent().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
					try {
						if (cmbbhes.isEnabled() == false) return;
						HESAP_PLN hsp ;
						getContentPane().setCursor(oac.WAIT_CURSOR);
						hsp = new HESAP_PLN();
						hsp.show();
						if (! oac.hsp_hsp_kodu.equals(""))
						{
							cmbbhes.setSelectedItem( oac.hsp_hsp_kodu);
							//cmbbhes.getEditor().setItem(oac.hsp_hsp_kodu);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		cmbbhes.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
					{	
						cmbb.requestFocus();
					}
					else
					{
						String[] parts;
						String deger ;
						deger = oac.glb.setting_oku("PRG_KAYDET").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								kaydet();
							}
						}
						deger = oac.glb.setting_oku("CARI_DEK_Y_FIS").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								yeni();
							}
						}
						deger = oac.glb.setting_oku("CARI_HSPPLN_CAG").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								try {
									HESAP_PLN hsp ;
									getContentPane().setCursor(oac.WAIT_CURSOR);
									hsp = new HESAP_PLN();
									hsp.show();
									if (! oac.hsp_hsp_kodu.equals(""))
									{
										//cmbbhes.getEditor().setItem(oac.hsp_hsp_kodu);
										cmbbhes.setSelectedItem( oac.hsp_hsp_kodu);
										cmbb.requestFocus();
										
									}
									getContentPane().setCursor(oac.DEFAULT_CURSOR);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JTextField editorComponent = (JTextField)  cmbbhes.getEditor().getEditorComponent();
		InputMap txtbhesMap = editorComponent.getInputMap(editorComponent.WHEN_FOCUSED);
		txtbhesMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK), "none");
		
		cmbbhes.getEditor().getEditorComponent().setForeground(new Color(0, 0, 128));
		cmbbhes.setBounds(10, 45, 147, 25);
		AutoCompleteDecorator.decorate(cmbbhes);
		panel_1.add(cmbbhes);


		lblbb.setVisible(false);
		lblbba.setVisible(false);
		lblba.setVisible(false);

		JPanel panel_3 = new JPanel();
		panelANA.add(panel_3);
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Aciklama", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12),null));
		panel_3.setBounds(10, 275, 548, 95);
		//getContentPane().add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		txtaciklama =   new JTextArea();
		txtaciklama.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		txtaciklama.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtaciklama.selectAll();
			}
		});
		txtaciklama.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtaciklama.setEnabled(false);
		txtaciklama.setLineWrap(true);
		txtaciklama.setDocument(new JTextFieldLimit(100));
		JTextFieldRegularPopupMenu.addTo(txtaciklama,100);
		Border borderr = BorderFactory.createLineBorder(Color.GRAY);
		txtaciklama.setBorder(BorderFactory.createCompoundBorder(borderr, BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		txtaciklama.addKeyListener(new KeyAdapter() {
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
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								kaydet();
							}
						}
						deger = oac.glb.setting_oku("CARI_DEK_Y_FIS").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								yeni();
							}
						}
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		txtaciklama.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  if(txtaciklama.getText().equals("")) 
					{
						btnAciksil.setVisible(false);
					}
					else {
						btnAciksil.setVisible(true);
					}
			  }
			  public void removeUpdate(DocumentEvent e) {
				  if(txtaciklama.getText().equals("")) 
					{
						btnAciksil.setVisible(false);
					}
					else {
						btnAciksil.setVisible(true);
					}
			  }
			  public void insertUpdate(DocumentEvent e) {
				  if(txtaciklama.getText().equals("")) 
					{
						btnAciksil.setVisible(false);
					}
					else {
						btnAciksil.setVisible(true);
					}
			  }
			});

		panel_3.add(txtaciklama, BorderLayout.CENTER);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Alacakli Hesap", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBounds(10, 187, 710, 87);
		//getContentPane().add(panel_1_1);
		panelANA.add(panel_1_1);

		btnah = new JButton("");
		btnah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbbhes.getItemAt(cmbahes.getSelectedIndex()).toString() == "") return;
				getContentPane().setCursor(oac.WAIT_CURSOR);
				boolean varmi = OBS_MAIN.pencere_bak("EKSTRE");
				if (varmi  ) 
				{
					try {
						OBS_MAIN.pencere_aktiv_yap("EKSTRE");
					} catch (PropertyVetoException e1) {
						getContentPane().setCursor(oac.DEFAULT_CURSOR);
						e1.printStackTrace();
					}
				}
				else
				{
					JInternalFrame internalFrame;
					internalFrame  = new EKSTRE();
					int xx= 0 ;
					int yy = 0;
					for(int i=0;i<OBS_MAIN.desktopPane.getAllFrames().length;i++)
					{   
						JInternalFrame frame=(JInternalFrame) OBS_MAIN.desktopPane.getComponent(i);
						String tit=frame.getTitle();
						if (tit.equals("DEKONT") )
						{
							xx =(int) frame.getLocation().getX() + frame.getWidth();
							yy =(int) frame.getLocation().getY();
							break; 
						}
					}
					internalFrame.setLocation(xx ,yy);
					OBS_MAIN.desktopPane.add(internalFrame);
					internalFrame.setVisible(true);
				}
				try 
				{
					FILTRE intFrame = new FILTRE();
					FILTRE.txtkodu.setText(cmbahes.getSelectedItem().toString());
					EKSTRE.hisset();
				} 
				catch (NumberFormatException e1) 
				{
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
					e1.printStackTrace();
				}
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		btnah.setEnabled(false);
		btnah.setIcon(new ImageIcon(DEKONT.class.getResource("/ICONLAR/eks16.png")));
		btnah.setBounds(190, 45, 30, 25);
		panel_1_1.add(btnah);

		lbla = new JLabel("...");
		lbla.setForeground(new Color(0, 128, 128));
		lbla.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbla.setBounds(230, 50, 46, 20);
		panel_1_1.add(lbla);

		cmba = new JComboBox<String>();
		cmba.setModel(new DefaultComboBoxModel<String>(new String[] {"", "MA", "MS", "SA", "SS", "BA", "BS"}));
		cmba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double akur = 1 ;
				if (!cmba.getItemAt(cmba.getSelectedIndex()).equals("")) 
				{
					akur = kur_oku(lbla.getText(),cmba.getItemAt(cmba.getSelectedIndex()));
				}
				txtakur.setText(FORMATLAMA.doub_4(akur));
			}
		});
		cmba.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				try {
					if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
					{	
						txtakur.requestFocus();
					}
					else
					{
						String[] parts;
						String deger ;
						deger = oac.glb.setting_oku("PRG_KAYDET").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								kaydet();
							}
						}
						deger = oac.glb.setting_oku("CARI_DEK_Y_FIS").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								yeni();
							}
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		cmba.setEnabled(false);
		cmba.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmba.setBounds(290, 45, 70, 25);
		panel_1_1.add(cmba);

		txtatutar = new JFormattedTextField();
		JTextFieldRegularPopupMenu.addTo(txtatutar);
		txtatutar.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		txtatutar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtatutar.selectAll();
			}
		});
		txtatutar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					if (txtatutar.isEnabled() == false) return;
					try {
						Double dbborc = DecimalFormat.getNumberInstance().parse(txtbtutar.getText()).doubleValue();
						Double kusur = Double.parseDouble(txtakur.getText());
						Double kusur2 = Double.parseDouble(txtbkur.getText());
						if (kusur !=  1)
						{
							if (kusur2 != 1 ) // ' capraz kur
							{
								double	db1 = (kusur2 * dbborc) / kusur;
								//txtatutar.setText(Double.toString(db1));
								txtatutar.setText(FORMATLAMA.doub_2(db1));
							}
							else  //' carpraz kur degil 
							{
								double d1 =  dbborc  / kusur ;
								//txtatutar.setText(Double.toString(d1));
								txtatutar.setText(FORMATLAMA.doub_2(d1));
							}
						}
						else
						{
							double d2 = kusur2 * dbborc;
							//txtatutar.setText(Double.toString(d2));
							txtatutar.setText(FORMATLAMA.doub_2(d2));
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		txtatutar.setEnabled(false);
		txtatutar.setText("0.00");
		txtatutar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
					{	
						if (txtatutar.isEnabled() == false) return;
						
							Double dbborc = DecimalFormat.getNumberInstance().parse(txtbtutar.getText()).doubleValue();
							Double kusur = Double.parseDouble(txtakur.getText());
							Double kusur2 = Double.parseDouble(txtbkur.getText());
							if (kusur !=  1)
							{
								if (kusur2 != 1 ) // ' capraz kur
								{
									double	db1 = (kusur2 * dbborc) / kusur;
									txtatutar.setText(FORMATLAMA.doub_2(db1));
								}
								else  //' carpraz kur degil 
								{
									double d1 =  dbborc  / kusur ;
									txtatutar.setText(FORMATLAMA.doub_2(d1));
								}
							}
							else
							{
								double d2 = kusur2 * dbborc;
								txtatutar.setText(FORMATLAMA.doub_2(d2));
							}
						txtaciklama.requestFocus();
					}
					else
					{
						String[] parts;
						String deger ;
						deger = oac.glb.setting_oku("PRG_KAYDET").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								kaydet();
							}
						}
						deger = oac.glb.setting_oku("CARI_DEK_Y_FIS").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								yeni();
							}
						}
					}
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}

			}
			@Override
			public void keyTyped(KeyEvent e) {
				char cc =  e.getKeyChar();
				if ( Character.isDigit(cc)  ) 
				{
					txtatutar.setEditable(true);
				}
				else {
					if ( cc == '.' || cc == ',' || (int)e.getKeyChar() ==8 || (int)e.getKeyChar() ==127   ) {
						txtatutar.setEditable(true);
					}
					else {
						txtatutar.setEditable(false);
					}
				}
			}
		});
		DecimalFormat dfat = new DecimalFormat(); // And here..
		NumberFormatter dnffat = new NumberFormatter(dfat);
		DefaultFormatterFactory f_dobat = new DefaultFormatterFactory(dnffat); 

		dfat.setMinimumFractionDigits(2);
		dfat.setMaximumFractionDigits(2);
		txtatutar.setFormatterFactory(f_dobat);
		txtatutar.setHorizontalAlignment(SwingConstants.RIGHT);
		//txtatutar.setForeground(new Color(0, 0, 139));
		txtatutar.setFont(new Font("Tahoma", Font.BOLD, 22));
		txtatutar.setBounds(512, 40, 190, 30);
		panel_1_1.add(txtatutar);

		txtakur = new JFormattedTextField();
		txtakur.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		txtakur.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtakur.selectAll();
			}
		});
		txtakur.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {

				try {
					if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
					{	
						txtatutar.requestFocus();
					}
					else
					{
						String[] parts;
						String deger ;
						deger = oac.glb.setting_oku("PRG_KAYDET").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								kaydet();
							}
						}
						deger = oac.glb.setting_oku("CARI_DEK_Y_FIS").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								yeni();
							}
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
			@Override
			public void keyTyped(KeyEvent e) {
				char cc =  e.getKeyChar();
				if ( Character.isDigit(cc)  ) 
				{
					txtakur.setEditable(true);
				}
				else {
					if ( cc == '.' || cc == ','  ) {
						txtakur.setEditable(true);
					}
					else {
						txtakur.setEditable(false);
					}
				}
			}
		});
		txtakur.setEnabled(false);
		txtakur.setText("1.0000");
		DecimalFormat dfa = new DecimalFormat(); // And here..
		NumberFormatter dnffa = new NumberFormatter(dfa);
		DefaultFormatterFactory f_doba = new DefaultFormatterFactory(dnffa); 
		dfa.setMinimumFractionDigits(4);
		dfa.setMaximumFractionDigits(4);
		txtakur.setFormatterFactory(f_doba);
		txtakur.setHorizontalAlignment(SwingConstants.RIGHT);
		txtakur.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtakur.setBounds(390, 45, 96, 25);
		panel_1_1.add(txtakur);

		lblNewLabel_2_1 = new JLabel("...");
		lblNewLabel_2_1.setForeground(new Color(0, 128, 128));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2_1.setBounds(10, 21, 370, 16);
		panel_1_1.add(lblNewLabel_2_1);

		pnla = new JPanel();
		pnla.setBorder(UIManager.getBorder("ToolTip.border"));
		pnla.setBounds(390, 15, 312, 20);
		pnla.setVisible(false);
		panel_1_1.add(pnla);
		pnla.setLayout(null);

		lblab = new JLabel("0.00");
		lblab.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		lblab.setBounds(0, 2, 100, 14);
		pnla.add(lblab);
		lblab.setHorizontalAlignment(SwingConstants.RIGHT);

		lblaa = new JLabel("0.00");
		lblaa.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		lblaa.setBounds(102, 2, 100, 14);
		pnla.add(lblaa);
		lblaa.setHorizontalAlignment(SwingConstants.RIGHT);

		lblaba = new JLabel("0.00");
		lblaba.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		lblaba.setBounds(205, 2, 100, 14);
		lblaba.setForeground(new Color(255, 0, 0));

		lblaba.setHorizontalAlignment(SwingConstants.RIGHT);
		pnla.add(lblaba);

		cmbahes = new JComboBox<String>();
		cmbahes.getEditor().getEditorComponent().setForeground(new Color(0, 0, 128));
		cmbahes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				hpl = new String[3];
				hpl = isim(cmbahes.getSelectedItem() == null ? "":cmbahes.getSelectedItem().toString());
				lblNewLabel_2_1.setText(hpl[0]);
				lbla.setText(hpl[1]);
				if (hpl[2].toString().equals("F"))
				{alacak_kutu = false ; } else { alacak_kutu = true ;}
				if (bakiye_goster) 
				{
					bakiye_doldur("A");
				}
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		cmbahes.getEditor().getEditorComponent().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
					try {
						if (cmbahes.isEnabled() == false) return;
						HESAP_PLN hsp ;
						getContentPane().setCursor(oac.WAIT_CURSOR);
						hsp = new HESAP_PLN();
						hsp.show();
						if (! oac.hsp_hsp_kodu.equals(""))
						{
							cmbahes.setSelectedItem( oac.hsp_hsp_kodu);
						}
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		cmbahes.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
					{	
						cmba.requestFocus();
					}
					else
					{
						String[] parts;
						String deger ;
						deger = oac.glb.setting_oku("PRG_KAYDET").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								kaydet();
							}
						}
						deger = oac.glb.setting_oku("CARI_DEK_Y_FIS").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								yeni();
							}
						}
						deger = oac.glb.setting_oku("CARI_HSPPLN_CAG").toString();
						parts = deger.split(",");
						if ( ! parts[2].equals(" ")) 
						{
							char c=parts[2].charAt(0);
							if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
							{
								try {
									HESAP_PLN hsp ;
									getContentPane().setCursor(oac.WAIT_CURSOR);
									hsp = new HESAP_PLN();
									hsp.show();
									if (! oac.hsp_hsp_kodu.equals(""))
									{
										cmbahes.setSelectedItem( oac.hsp_hsp_kodu);
										cmba.requestFocus();
									}
									getContentPane().setCursor(oac.DEFAULT_CURSOR);
								} catch (ClassNotFoundException e1) {
									e1.printStackTrace();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		JTextField editorComponenta = (JTextField)  cmbahes.getEditor().getEditorComponent();
		InputMap txtbhesMapa = editorComponenta.getInputMap(editorComponenta.WHEN_FOCUSED);
		txtbhesMapa.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK), "none");
		cmbahes.setFont(new Font("Tahoma", Font.BOLD, 15));
		cmbahes.setEnabled(false);
		cmbahes.setBounds(10, 45, 147, 25);
		AutoCompleteDecorator.decorate(cmbahes);
		panel_1_1.add(cmbahes);

		lblaba.setVisible(false);
		lblaa.setVisible(false);
		lblab.setVisible(false);

		JPanel panel_3_1 = new JPanel();
		panelANA.add(panel_3_1);
		panel_3_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Kod", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		panel_3_1.setBounds(585, 275, 135, 48);
		//getContentPane().add(panel_3_1);
		panel_3_1.setLayout(new BorderLayout(0, 0));

		txtkod = new Obs_TextFIeld(5,"");
		txtkod.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		txtkod.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtkod.selectAll();
			}
		});
		txtkod.setEnabled(false);
		txtkod.setFont(new Font("Tahoma", Font.BOLD, 12));
		//txtkod.setDocument(new JTextFieldLimit(5));
		//JTextFieldRegularPopupMenu.addTo(txtkod);
		txtkod.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String[] parts;
				String deger ;
				try {
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
							kaydet();
						}
					}
					deger = oac.glb.setting_oku("CARI_DEK_Y_FIS").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
							yeni();
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel_3_1.add(txtkod, BorderLayout.CENTER);

		sifirla();
		AUTO_HESAP_KODU.auto_doldur(cmbbhes);
		
		btnHYenileB = new JButton("");
		btnHYenileB.setEnabled(false);
		btnHYenileB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				String kODString = "" ;
				if(cmbbhes.getSelectedItem() != null)
				{
					kODString = cmbbhes.getSelectedItem().toString();
				}
				AUTO_HESAP_KODU.auto_doldur(cmbbhes);
				cmbbhes.setSelectedItem(kODString);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		btnHYenileB.setToolTipText("Hesap Plani Yenile");
		btnHYenileB.setBounds(160, 45, 30, 25);
		btnHYenileB.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		panel_1.add(btnHYenileB);
		AUTO_HESAP_KODU.auto_doldur(cmbahes);
		
		btnHYenileA = new JButton("");
		btnHYenileA.setEnabled(false);
		btnHYenileA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				String kODString = "" ;
				if(cmbahes.getSelectedItem() != null)
				{
					kODString = cmbahes.getSelectedItem().toString();
				}
				AUTO_HESAP_KODU.auto_doldur(cmbahes);
				cmbahes.setSelectedItem(kODString);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		btnHYenileA.setToolTipText("Hesap Plani Yenile");
		btnHYenileA.setBounds(160, 45, 30, 25);
		btnHYenileA.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		panel_1_1.add(btnHYenileA);

		lblNewLabel = new JLabel(".");
		//lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(568, 350, 152, 14);
		panelANA.add(lblNewLabel);
		
		btnAciksil = new JButton("X");
		btnAciksil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtaciklama.setText("");
				txtaciklama.requestFocus();
			}
		});
		btnAciksil.setVisible(false);
		btnAciksil.setBounds(557, 320, 19, 19);
		panelANA.add(btnAciksil);
		try {
			String deger;
			deger = oac.glb.setting_oku("CARI_DEKONT_BAKIYE_GOSTER").toString();
			if (deger.equals("-1"))
			{
				bakiye_goster =false;
			}
			else
			{
				bakiye_goster();
				bakiye_goster =true;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public static void fiskont() 
	{
		try {
			if (txtevrak.getText() == null) 
			{
				txtevrak.requestFocus();
				return;
			}
			if (Long.parseLong(txtevrak.getText())  > 2147483647 )
			{
				txtevrak.requestFocus();
				return;
			}
			long startTime = System.currentTimeMillis(); 
			ResultSet rs =null;

			rs = c_Access.fiskon(Integer.parseInt(txtevrak.getText()));
			if (!rs.isBeforeFirst() ) { 
				sifirla();
				kutu_kapa();
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Bu Numarada Kayitli Fis Yok......" );
				return; // Kayit Yok
			} 
			sifirla();
			rs.next();
			dtc.setDate(rs.getDate("TARIH"));
			txtaciklama.setText(rs.getString("IZAHAT"));
			txtkod.setText(rs.getString("KOD"));
			cmbbhes.setSelectedItem(rs.getString("HESAP"));
			cmbb.setSelectedItem(rs.getString("CINS"));
			txtbkur.setText(FORMATLAMA.doub_4(rs.getDouble("KUR")));
			txtbtutar.setText(FORMATLAMA.doub_2(rs.getDouble("BORC")));
			rs.next();
			cmbahes.setSelectedItem(rs.getString("HESAP"));
			cmba.setSelectedItem(rs.getString("CINS"));
			txtakur.setText(FORMATLAMA.doub_4(rs.getDouble("KUR")));
			txtatutar.setText(FORMATLAMA.doub_2(rs.getDouble("ALACAK")));
			lblNewLabel.setText(rs.getString("USER"));
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime;
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			kutu_ac();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage()  );
		}
	}
	public static  void yoket() 
	{
		if (txtevrak.getText() == null ) return ;
		if (txtevrak.getText().equals("0") ) return ;
		if (txtevrak.getText().equals("")  ) return ;
		try 
		{
			int g =  JOptionPane.showOptionDialog( null,  "Islem Dosyadan Silinecek ..?", "Cari Dosyasindan Evrak Silme",   JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,	   			 	null,   	oac.options,   	oac.options[1]); 
			if(g != 0 ) { return;	}
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ(txtevrak.getText() + " Dekont Silme");
			lBILGI.seteVRAK(txtevrak.getText());
			c_Access.evrak_yoket(Integer.parseInt(txtevrak.getText()),lBILGI, BAGLAN_LOG.cariLogDizin);
			sifirla();
			kutu_kapa();
			txtevrak.setText("");
			txtevrak.requestFocus();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage()  );
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void kaydet () 
	{
		if (txtevrak.getText() == null ) return ;
		if (txtevrak.getText().equals("0") ) return ;
		if (txtevrak.getText().equals("")  ) return ;
		if (cmbbhes.getSelectedItem().toString().equals("")  ) return ;
		if (txtbkur.getText().equals("")  ) return ;
		if (cmbahes.getSelectedItem().toString().equals("")  ) return ;
		if (txtakur.getText().equals("")  ) return ;
		if (txtaciklama.getText().equals("")  ) return ;
		if(dtc.getDate() == null) return;
		if (cmbbhes.getSelectedItem().toString().equals(cmbahes.getSelectedItem().toString())) {
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Alacak ve Borclu Hesap Ayni...."  );
			return ;
		}
		try 
		{
			if ( borc_kutu == false )
			{
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Borclu Hesap Kodu  Hesap Planinda Bulunmamaktadir...."  );
				return ;
			}
			if ( alacak_kutu == false )
			{
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Alacakli Hesap Kodu Hesap Planinda Bulunmamaktadir...."  );
				return ;
			}

			if( lblb.getText().equals(lbla.getText()))
			{
				if ( ! txtbtutar.getText().equals(txtatutar.getText()))
				{
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,  "Alacakli ve Borclu Tutar Esit Degil...." );
					return ;
				}
			}
			if (c_Access.cari_fino_bak(Integer.parseInt(txtevrak.getText()))) 
			{
				JOptionPane optionPane = new JOptionPane("Islem Dosyada mevcut Fis eskisi ile degisecek ..", JOptionPane.QUESTION_MESSAGE,
						JOptionPane.YES_NO_OPTION, null,oac.options,  oac.options[1]);
				JDialog	dialog = optionPane.createDialog("Dekont Kaydet");
				Set focusTraversalKeys = new HashSet(dialog.getFocusTraversalKeys(0));
				focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.VK_UNDEFINED));
				focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_LEFT, KeyEvent.VK_UNDEFINED));
				dialog.setFocusTraversalKeys(0, focusTraversalKeys);
				dialog.setVisible(true);
				dialog.dispose();
				if(optionPane.getValue() == null)
				{
					return;
				}
				else {
					if(oac.mesajDeger(optionPane.getValue().toString()) ==0) return;
				}
			}
			long startTime = System.currentTimeMillis(); 
			String str = TARIH_CEVIR.tarih_geri_saatli(dtc) ;
			String mesaj = "A. Hes:" + cmbahes.getSelectedItem().toString() + " Tut:" +txtatutar.getText() +
					" B. Hes:"+ cmbbhes.getItemAt(cmbbhes.getSelectedIndex()).toString() + " Tut:" + txtbtutar.getText();
			String mesaj1 = txtaciklama.getText();
			if( mesaj.length() + mesaj1.length() <= 95)
			{
				mesaj = mesaj + " Msj:" + mesaj1 ;
			}
			else
			{
				mesaj = mesaj + " Msj:" + mesaj1.substring(0, 95  -(mesaj.length())) ;
			}
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ(txtevrak.getText() + " Dekont Silme");
			lBILGI.seteVRAK(txtevrak.getText());
			c_Access.evrak_yoket(Integer.parseInt(txtevrak.getText()),lBILGI, BAGLAN_LOG.cariLogDizin);
			dEKONT_BILGI dBilgi = new dEKONT_BILGI();
			dBilgi.settAR(str);
			dBilgi.seteVRAK(Integer.parseInt(txtevrak.getText()));
			
			dBilgi.setbHES(cmbbhes.getSelectedItem().toString());
			dBilgi.setbCINS(cmbb.getSelectedItem().toString());
			dBilgi.setbKUR(Double.parseDouble(txtbkur.getText()));
			dBilgi.setbORC(DecimalFormat.getNumberInstance().parse(txtbtutar.getText()).doubleValue());
			
			dBilgi.setaHES(cmbahes.getSelectedItem().toString());
			dBilgi.setaCINS(cmba.getSelectedItem().toString());
			dBilgi.setaKUR(Double.parseDouble(txtakur.getText()));
			dBilgi.setaLACAK(DecimalFormat.getNumberInstance().parse(txtatutar.getText()).doubleValue());
			
			dBilgi.setiZAHAT(txtaciklama.getText());
			dBilgi.setkOD(txtkod.getText());
			dBilgi.setuSER(GLOBAL.KULL_ADI);
			lBILGI.setmESAJ(mesaj);
			
			c_Access.cari_dekont_kaydet(dBilgi,	lBILGI ,BAGLAN_LOG.cariLogDizin	);

			sifirla();
			
			kutu_kapa();
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime;
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			txtevrak.setText("0");
			txtevrak.requestFocus();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
		}
	}
	private static void kutu_ac()
	{
		cmbbhes.setEnabled(true);
		txtbkur.setEnabled(true);
		txtbtutar.setEnabled(true);
		cmbb.setEnabled(true);
		btnbh.setEnabled(true);
		cmbahes.setEnabled(true);
		txtakur.setEnabled(true);
		txtatutar.setEnabled(true);
		cmba.setEnabled(true);
		btnah.setEnabled(true);
		txtaciklama.setEnabled(true);
		txtkod.setEnabled(true);
		btnHYenileB.setEnabled(true);
		btnHYenileA.setEnabled(true);
	}
	private static void kutu_kapa()
	{
		cmbbhes.setEnabled(false);
		txtbkur.setEnabled(false);
		txtbtutar.setEnabled(false);
		cmbb.setEnabled(false);
		btnbh.setEnabled(false);
		cmbahes.setEnabled(false);
		txtakur.setEnabled(false);
		txtatutar.setEnabled(false);
		cmba.setEnabled(false);
		btnah.setEnabled(false);
		txtaciklama.setEnabled(false);
		txtkod.setEnabled(false);
		btnHYenileB.setEnabled(false);
		btnHYenileA.setEnabled(false);
	}
	private static void sifirla()
	{
		txtbkur.setText("1.0000");
		txtbtutar.setText("0.00");
		cmbb.setSelectedItem("");
		cmbbhes.setSelectedItem("");
		cmbahes.setSelectedItem("");
		txtakur.setText("1.0000");
		txtatutar.setText("0.00");
		cmba.setSelectedItem("");
		txtaciklama.setText("");
		txtkod.setText("");
		if (bakiye_goster)
		{
			lblbb.setText("0.00");
			lblba.setText("0.00");
			lblbba.setText("0.00");
			lblab.setText("0.00");
			lblaa.setText("0.00");
			lblaba.setText("0.00");
		}
	}
	private static void yeni()
	{
		try
		{
			int evr=0;
			evr =  c_Access.cari_fisno_al();
			txtevrak.setText(Integer.toString(evr));
			kutu_ac();
			sifirla();
			cmbbhes.requestFocus();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
		}
	}
	private double kur_oku(String cins,String tur)
	{
		double kur =1 ;
		try
		{
			ResultSet rs ;
			KUR_ACCESS  k_Access = new KUR_ACCESS(oac._IKur , oac._IKur_Loger);
			rs =  k_Access.kur_oku(TARIH_CEVIR.tarih_geri_SQL(dtc),cins);
			if (!rs.isBeforeFirst() ) {  
				kur =1 ;
			} 
			else
			{
				rs.next();
				kur  = rs.getDouble(tur);
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
		}
		return kur;
	}
	private void bakiye_goster()
	{
		lblbb.setVisible(true);
		lblba.setVisible(true);
		lblbba.setVisible(true);
		lblab.setVisible(true);
		lblaa.setVisible(true);
		lblaba.setVisible(true);
		pnlb.setVisible(true);
		pnla.setVisible(true);
	}
	private void bakiye_doldur(String cins)
	{
		try
		{
			ResultSet	rs = null;
			String kod = "";
			if (cins.equals("B"))
			{
				kod = cmbbhes.getSelectedItem() == null ? "":cmbbhes.getSelectedItem().toString();
			}
			else
			{
				kod = cmbahes.getSelectedItem() == null ? "":cmbahes.getSelectedItem().toString();
			}
			rs =  c_Access.dek_mizan(kod );
			if (!rs.isBeforeFirst() ) {  
				if (cins.equals("B"))
				{
					lblbb.setText("0.00");
					lblba.setText("0.00");
					lblbba.setText("0.00");
				}
				else
				{
					lblab.setText("0.00");
					lblaa.setText("0.00");
					lblaba.setText("0.00");
				}
			} 
			else
			{
				rs.next();
				if (cins.equals("B"))
				{
					lblbb.setText(FORMATLAMA.doub_2(rs.getDouble("borc")));
					lblba.setText(FORMATLAMA.doub_2(rs.getDouble("alacak")));
					lblbba.setText(FORMATLAMA.doub_2(rs.getDouble("bakiye")));
				}
				else
				{
					lblab.setText(FORMATLAMA.doub_2(rs.getDouble("borc")));
					lblaa.setText(FORMATLAMA.doub_2(rs.getDouble("alacak")));
					lblaba.setText(FORMATLAMA.doub_2(rs.getDouble("bakiye")));
				}
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
		}
	}
	private static String[] isim(String kod)  {
		String [] sonuc = {"","",""}  ;
		try
		{
			ResultSet	rs = null;
			rs = c_Access.hesap_adi_oku(kod);
			if (!rs.isBeforeFirst() ) {  
				sonuc [0]= "" ;
				sonuc [1]= "" ;
				sonuc [2]= "F" ;
			} 
			else
			{
				rs.next();
				sonuc [0] = rs.getString("UNVAN");
				sonuc [1]=rs.getString("HESAP_CINSI");
				sonuc [2]= "T" ;
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
		}
		return sonuc;
	}
}



package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import LOGER_KAYIT.DOSYA_MSSQL;
import LOGER_KAYIT.DOSYA_MYSQL;
import LOGER_KAYIT.SQLITE_LOG;
import LOGER_KAYIT.TXT_LOG;
import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.ADRES_MSSQL;
import OBS_C_2025.ADRES_MYSQL;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.CARI_HESAP_MSSQL;
import OBS_C_2025.CARI_HESAP_MYSQL;
import OBS_C_2025.CONNECT;
import OBS_C_2025.DOSYA_YAZ;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.GUNLUK_ACCESS;
import OBS_C_2025.GUNLUK_MSSQL;
import OBS_C_2025.GUNLUK_MYSQL;
import OBS_C_2025.ILOGGER;
import OBS_C_2025.KAMBIYO_ACCESS;
import OBS_C_2025.KAMBIYO_MSSQL;
import OBS_C_2025.KAMBIYO_MYSQL;
import OBS_C_2025.KUR_ACCESS;
import OBS_C_2025.KUR_MSSQL;
import OBS_C_2025.KUR_MYSQL;
import OBS_C_2025.MAIL_AT;
import OBS_C_2025.OBS_ORTAK_MSSQL;
import OBS_C_2025.OBS_ORTAK_MYSQL;
import OBS_C_2025.SMS_ACCESS;
import OBS_C_2025.SMS_MSSQL;
import OBS_C_2025.SMS_MYSQL;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.KERESTE_MSSQL;
import OBS_C_2025.KERESTE_MYSQL;
import OBS_C_2025.STOK_MSSQL;
import OBS_C_2025.STOK_MYSQL;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.Server_Bilgi;
import OBS_C_2025.StayOpenCheckBoxMenuItemUI;
import OBS_C_2025.USER_ISLEMLERI;
import OBS_C_2025.lOG_BILGI;
import net.proteanit.sql.DbUtils;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JSeparator;

@SuppressWarnings({"static-access","serial"})
public class CAL_DIZIN extends JFrame {


	OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JTextField txtKodu;
	private static JTextField txtIp;
	private static JTextField txtkul;
	private static JPasswordField txtsifr;
	private static JTextField txtdiz;
	private static JLabel lblysif;
	private static JTextField txtyenisif;
	private static JComboBox<String> comboBox;
	private static JComboBox<String> cmbip;
	private static JComboBox<String> cmbhangisql;
	private static JButton btndizsec;
	private static JButton btnNewButton_2_1_1 ;
	private static JButton btnNewButton_1;
	private static JCheckBox chckbxL ;
	private static JCheckBox chckbxS ;
	private static JCheckBox chckbxO ;
	private static JCheckBox chckbxD ;
	private static JCheckBox chckbxL_1;
	private static JTable tblCari;
	private static JTable tblFatura;
	private static JTable tblAdres;
	private static JTable tblKur;
	private static JTable tblKambiyo;
	private static JTable tblSms;
	private static JTable tblGunluk;
	private static JTable tblKereste;
	private static JPasswordField txtsif;
	private static	JTextField txt_Lmaill ;
	private static JComboBox<String>  cmb_maillist;
	private static MaterialTabbed tabbedPane;
	private static int activ_sayfa =0;
	static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	private static JTextField txtcdid;
	private JLabel lblNewLabel_2;
	boolean vt = false;
	boolean ds = false;
	boolean tx = false;
	boolean em = false;
	static JCheckBoxMenuItem cbVeritabani ;
	static JCheckBoxMenuItem cbDosya;
	static JCheckBoxMenuItem cbText;
	static JCheckBoxMenuItem cbMail;
	private JSeparator separator;
	private JPopupMenu menu;
	private JButton btnNewButton_2_2;
	int x ,y ;
	
	public CAL_DIZIN() {
		
		  addMouseMotionListener(new MouseMotionAdapter() {
		  
		  @Override public void mouseDragged(MouseEvent e) { int xx = e.getXOnScreen();
		  int yy = e.getYOnScreen(); setLocation(xx-x,yy-y);
		  
		  } }); addMouseListener(new MouseAdapter() {
		  
		  @Override public void mousePressed(MouseEvent e) { x = e.getX(); y = e.getY()
		  ; } });
		 		//setBorder(null);

		setUndecorated(true);
		setTitle("CALISMA DIZINI");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1050, 425);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setBounds(725, 10, 316, 381);
		contentPane.add(panel);
		panel.setLayout(null);

		txtKodu = new JTextField();
		txtKodu.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtKodu.setBounds(102, 71, 78, 20);
		txtKodu.setEnabled(false);
		panel.add(txtKodu);
		txtKodu.setColumns(10);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(100, 5, 138, 27);
		panel.add(toolBar);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					if (txtKodu.getText().equals(""))
						return;
					if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()).toString().equals("MS SQL"))
					{
						if (comboBox.getSelectedItem().toString() == null)
							return;
					}
					try {
						server_control();
					} catch  (Exception ex)
					{
						contentPane.setCursor(DEFAULT_CURSOR);
						JOptionPane.showMessageDialog(null, ex.getMessage().toString(),  "Server Kontrol", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setToolTipText("Server Kontrol");
		btnNewButton.setIcon(new ImageIcon(CAL_DIZIN.class.getResource("/ICONLAR/server.png")));
		toolBar.add(btnNewButton);

		btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {  // VERITABANI KONTROL
			public void actionPerformed(ActionEvent e) {
				try
				{
					contentPane.setCursor(WAIT_CURSOR);
					database_kontrol();
					btnNewButton_1.setEnabled(false);
					contentPane.setCursor(DEFAULT_CURSOR);
				}
				catch  (Exception ex)
				{
					contentPane.setCursor(DEFAULT_CURSOR);
					JOptionPane.showMessageDialog(null, ex.getMessage().toString(),  "Veritabani Kontrol", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnNewButton_1.setToolTipText("Veritabani Kontrol");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setIcon(new ImageIcon(CAL_DIZIN.class.getResource("/ICONLAR/db.png")));
		toolBar.add(btnNewButton_1);
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (activ_sayfa != 8)
				{
					if (txtcdid.toString().equals("")) return ;
					int g = JOptionPane.showOptionDialog(null, "Kayit Silinecek..........?" ,
							"Calisma Dizini ", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, 	new String[] {"Yes", "No"}, "No");
					if(g ==  1) {
						return;
					}
					contentPane.setCursor(WAIT_CURSOR);
					try {
						oac.uSER_ISL.cd_sil(Integer.parseInt(txtcdid.getText()) );
						grid_doldur();
						switch(activ_sayfa) 
						{
						case 0:
							doldur_kutu(tblCari, 0);
							break;
						case 1:
							doldur_kutu(tblFatura, 0);
							break;
						case 2:
							doldur_kutu(tblAdres, 0) ;
							break;
						case 3:
							doldur_kutu(tblKur, 0);
							break;
						case 4:
							doldur_kutu(tblKambiyo, 0);
							break;
						case 5:
							doldur_kutu(tblSms, 0);
							break;
						case 6:
							doldur_kutu(tblGunluk, 0);
							break;
						case 7:
							doldur_kutu(tblKereste, 0);
							break;
						}
					} catch (Exception ex)
					{
						JOptionPane.showMessageDialog(null,  ex.getMessage().toString(),  "Calisma Dizini Dosya Silme ", JOptionPane.ERROR_MESSAGE);   
					}
				}
				if (activ_sayfa == 8)
				{
					try {
						int g = JOptionPane.showOptionDialog(null, "E Mail  Silinecek ?" ,
								"Calisma Dizini ", JOptionPane.YES_NO_OPTION,	JOptionPane.QUESTION_MESSAGE, null, 	new String[] {"Yes", "No"}, "No");
						if(g ==  1) {
							return;
						}
						contentPane.setCursor(WAIT_CURSOR);
						USER_ISLEMLERI usr = new USER_ISLEMLERI();
						usr.log_mail_sil(GLOBAL.KULL_ADI , txt_Lmaill.getText());
						mail_doldur();
					}
					catch (Exception ex)
					{
						JOptionPane.showMessageDialog(null,  ex.getMessage().toString(),  "E_Mail Silme", JOptionPane.ERROR_MESSAGE);   
					}
				}
				contentPane.setCursor(DEFAULT_CURSOR);
			}
		});
		btnNewButton_2.setToolTipText("Sil");
		btnNewButton_2.setIcon(new ImageIcon(CAL_DIZIN.class.getResource("/ICONLAR/sil.png")));
		toolBar.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kutu_temizle();
					ip_doldur();
					txtKodu.setEnabled(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setToolTipText("Yeni");
		btnNewButton_3.setIcon(new ImageIcon(CAL_DIZIN.class.getResource("/ICONLAR/yeni.png")));
		toolBar.add(btnNewButton_3);

		

		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setToolTipText("Kapat");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cIKIS();			}
		});
		btnNewButton_5.setIcon(new ImageIcon(CAL_DIZIN.class.getResource("/ICONLAR/exit.png")));
		toolBar.add(btnNewButton_5);

		JLabel lblNewLabel_1 = new JLabel("Kodu");
		lblNewLabel_1.setBounds(24, 76, 68, 14);

		panel.add(lblNewLabel_1);
		chckbxL = new JCheckBox("Lokal");
		chckbxL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxL.isSelected())
				{
					chckbxS.setSelected(false);
					chckbxD.setEnabled(true);
					chckbxO.setEnabled(true);
				}
				else
				{
					chckbxS.setSelected(true);
					chckbxD.setEnabled(false);
					chckbxO.setEnabled(false);
				}
				String hangi = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex())  ;
				if (hangi == "MS SQL")
				{
					chckbxD.setEnabled(true);
					chckbxO.setEnabled(true);
				}
				else
				{
					chckbxD.setEnabled(false);
					chckbxO.setEnabled(false);
				}
				
			}
		});
		chckbxL.setSelected(true);
		chckbxL.setBounds(102, 95, 65, 23);
		panel.add(chckbxL);
		chckbxS = new JCheckBox("Server");
		chckbxS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (chckbxS.isSelected())
				{
					chckbxL.setSelected(false);
					chckbxD.setEnabled(false);
					chckbxO.setEnabled(false);
				}
				else
				{
					chckbxL.setSelected(true);
					chckbxD.setEnabled(true);
					chckbxO.setEnabled(true);
				}
			}
		});
		chckbxS.setBounds(181, 95, 78, 23);
		panel.add(chckbxS);

		txtIp = new JTextField();
		txtIp.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtIp.setBounds(102, 180, 157, 20);
		panel.add(txtIp);
		txtIp.setColumns(10);

		cmbip = new JComboBox<String>();
		cmbip.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cmbip.getItemCount() != 0)
					txtIp.setText(cmbip.getSelectedItem().toString());
			}
		});
		cmbip.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbip.setEditable(true);
		cmbip.setBounds(102, 205, 157, 22);
		panel.add(cmbip);

		txtkul = new JTextField();
		txtkul.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtkul.setBounds(102, 229, 157, 20);
		panel.add(txtkul);
		txtkul.setColumns(10);

		txtsifr = new JPasswordField();
		txtsifr.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtsifr.setBounds(102, 252, 157, 20);

		panel.add(txtsifr);


		chckbxD = new JCheckBox("Varsayilan");
		chckbxD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxD.isSelected())
				{
					chckbxO.setSelected(false);
					txtdiz.setText("");
					txtdiz.setEnabled(false);
					btndizsec.setEnabled(false);
				}
				else
				{
					chckbxO.setSelected(true);
					txtdiz.setText("");
					txtdiz.setEnabled(true);
					btndizsec.setEnabled(true);
				}
			}
		});
		chckbxD.setSelected(true);
		chckbxD.setBounds(51, 283, 97, 23);
		panel.add(chckbxD);

		chckbxO = new JCheckBox("Ozel");
		chckbxO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxO.isSelected())
				{
					chckbxD.setSelected(false);
					txtdiz.setEnabled(true);
					btndizsec.setEnabled(true);
				}
				else
				{
					chckbxD.setSelected(true);
					txtdiz.setEnabled(false);
					btndizsec.setEnabled(false);
					txtdiz.setText("");
				}
			}
		});
		chckbxO.setBounds(162, 283, 97, 23);
		panel.add(chckbxO);
		txtdiz = new JTextField();
		txtdiz.setEnabled(false);
		txtdiz.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtdiz.setBounds(51, 313,255,20);
		panel.add(txtdiz);
		btndizsec = new JButton("Surucu Sec");
		btndizsec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(WAIT_CURSOR);
				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Surucu Seciniz");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Surucu Sec");
				chooser.setApproveButtonToolTipText("Surucu Sec");
				chooser.setApproveButtonMnemonic('s');
				contentPane.setCursor(DEFAULT_CURSOR);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					txtdiz.setText(chooser.getSelectedFile().toString());
				}
				else {
					// System.out.println("No Selection ");
				}
			}
		});
		btndizsec.setEnabled(false);

		btndizsec.setBounds(51, 340, 107, 23);
		panel.add(btndizsec);

		JLabel lblInstance = new JLabel("Instance");
		lblInstance.setBounds(24, 160, 68, 14);
		panel.add(lblInstance);

		JLabel lblServer = new JLabel("Server / Port");
		lblServer.setBounds(24, 185, 78, 14);
		panel.add(lblServer);

		JLabel lblKullanici = new JLabel("Sifre");
		lblKullanici.setBounds(24, 257, 68, 14);
		panel.add(lblKullanici);

		JLabel lblSifre = new JLabel("Kullanici");
		lblSifre.setBounds(24, 234, 68, 14);
		panel.add(lblSifre);

		JLabel lblUser = new JLabel("....");
		lblUser.setForeground(new Color(128, 0, 0));
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUser.setBounds(10, 384, 46, 14);
		panel.add(lblUser);

		tabbedPane = new MaterialTabbed();
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				activ_sayfa =tabbedPane.getSelectedIndex();
				try {
					if (txtcdid== null) return;
					contentPane.setCursor(WAIT_CURSOR);
					grid_doldur();
					sIFRE_KAPA();
					txtKodu.setEnabled(false);
					panel.setVisible(true);
					switch(activ_sayfa) 
					{
					case 0:
						doldur_kutu(tblCari, 0);
						break;
					case 1:
						doldur_kutu(tblFatura, 0);
						break;
					case 2:
						doldur_kutu(tblAdres, 0);
						break;
					case 3:
						doldur_kutu(tblKur, 0);
						break;
					case 4:
						doldur_kutu(tblKambiyo, 0);
						break;
					case 5:
						doldur_kutu(tblSms, 0);
						break;
					case 6:
						doldur_kutu(tblGunluk, 0);
						break;
					case 7:
						doldur_kutu(tblKereste, 0);
						break;
					case  8:
						panel.setVisible(false);
						txtsif.requestFocus()	;
						break;
					case 9:
						panel.setVisible(false);
						mail_doldur();
						break;
					}
					contentPane.setCursor(DEFAULT_CURSOR);

				} catch (Exception e1) 
				{
					contentPane.setCursor(DEFAULT_CURSOR);
					e1.printStackTrace();
				}
			}
		});
		tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
		tabbedPane.setBounds(10, 10, 705, 381);
		tabbedPane.setForeground(new Color(25, 25, 112));
		tabbedPane.setTabLayoutPolicy(MaterialTabbed.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(new LineBorder(new Color(0, 191, 255)));
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		tabbedPane.addTab("Cari Hesap", null, scrollPane, null);
		tblCari = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {     return false;          }};
		tblCari.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					if (tblCari.getRowCount() == 0) return ;
					if (tblCari.getSelectedRow()  < 0) return;
					contentPane.setCursor(WAIT_CURSOR);
					try {
						kutu_temizle();
						doldur_kutu(tblCari,tblCari.getSelectedRow());
						contentPane.setCursor(DEFAULT_CURSOR);
					} catch (Exception e1) {
						contentPane.setCursor(DEFAULT_CURSOR);
						e1.printStackTrace();
					}
					contentPane.setCursor(DEFAULT_CURSOR);
				}
			}
		});
		
		tblCari.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCari.setFont(new Font("Dialog", Font.PLAIN, 12));
		tblCari.setRowHeight(22);
		tblCari.setBounds(224, 188, 114, 173);
		tblCari.setShowHorizontalLines(true);
		tblCari.setShowVerticalLines(true);
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			tblCari.setGridColor(oac.gridcolor);
		}

		tablo_baslik(tblCari);

		scrollPane.setViewportView(tblCari);

		ScrollPaneWin11 scrollPane_1 = new ScrollPaneWin11();
		tabbedPane.addTab("Fatura", null, scrollPane_1, null);

		tblFatura = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {     return false;          }};
		tblFatura.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
				if (tblFatura.getRowCount() == 0) return ;
				if (tblFatura.getSelectedRow()  < 0) return;
				contentPane.setCursor(WAIT_CURSOR);
				try {
						kutu_temizle();
						doldur_kutu(tblFatura,tblFatura.getSelectedRow());
						contentPane.setCursor(DEFAULT_CURSOR);
					} catch (Exception e1) {
						contentPane.setCursor(DEFAULT_CURSOR);
						e1.printStackTrace();
					} 
					contentPane.setCursor(DEFAULT_CURSOR);
				}
				}
			});
		tblFatura.setRowHeight(22);
		tblFatura.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblFatura.setShowHorizontalLines(true);
		tblFatura.setShowVerticalLines(true);
		tblFatura.setFont(new Font("Dialog", Font.PLAIN, 12));
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			tblFatura.setGridColor(oac.gridcolor);
		}
		tablo_baslik(tblFatura);
		scrollPane_1.setViewportView(tblFatura);

		ScrollPaneWin11 scrollPane_2 = new ScrollPaneWin11();
		
		tabbedPane.addTab("Adres", null, scrollPane_2, null);
		tblAdres = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {     return false;          }};
		tblAdres.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
				if (tblAdres.getRowCount() == 0) return ;
				if (tblAdres.getSelectedRow()  < 0) return;
				contentPane.setCursor(WAIT_CURSOR);
				try {
					kutu_temizle();
					doldur_kutu(tblAdres,tblAdres.getSelectedRow());
					contentPane.setCursor(DEFAULT_CURSOR);
				} catch (Exception e1) {
					contentPane.setCursor(DEFAULT_CURSOR);
					e1.printStackTrace();
				} 
				contentPane.setCursor(DEFAULT_CURSOR);
				}
			}
		});
		tblAdres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblAdres.setRowHeight(22);
		tblAdres.setShowHorizontalLines(true);
		tblAdres.setShowVerticalLines(true);
		tblAdres.setFont(new Font("Dialog", Font.PLAIN, 12));
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			tblAdres.setGridColor(oac.gridcolor);
		}
		tablo_baslik(tblAdres);
		scrollPane_2.setViewportView(tblAdres);

		ScrollPaneWin11 scrollPane_3 = new ScrollPaneWin11();
		tabbedPane.addTab("Kur", null, scrollPane_3, null);
		tblKur = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {     return false;          }};
		tblKur.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
				if (tblKur.getRowCount() == 0) return ;
				if (tblKur.getSelectedRow()  < 0) return;
				contentPane.setCursor(WAIT_CURSOR);
				try {
					kutu_temizle();
					doldur_kutu(tblKur,tblKur.getSelectedRow());
					
				} catch (Exception e1) {
					contentPane.setCursor(DEFAULT_CURSOR);
					e1.printStackTrace();
				} 
				contentPane.setCursor(DEFAULT_CURSOR);
				}
			}
		});
		tblKur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblKur.setRowHeight(22);
		tblKur.setShowHorizontalLines(true);
		tblKur.setShowVerticalLines(true);
		tblKur.setFont(new Font("Dialog", Font.PLAIN, 12));
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			tblKur.setGridColor(oac.gridcolor);
		}
		tablo_baslik(tblKur);
		scrollPane_3.setViewportView(tblKur);

		ScrollPaneWin11 scrollPane_4 = new ScrollPaneWin11();
		tabbedPane.addTab("Kambiyo", null, scrollPane_4, null);

		tblKambiyo = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {     return false;          }};
		tblKambiyo.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
				if (tblKambiyo.getRowCount() == 0) return ;
				if (tblKambiyo.getSelectedRow()  < 0) return;
				contentPane.setCursor(WAIT_CURSOR);
				try 
				{
					kutu_temizle();
					doldur_kutu(tblKambiyo,tblKambiyo.getSelectedRow());
					contentPane.setCursor(DEFAULT_CURSOR);
				} catch (Exception e1) {
					contentPane.setCursor(DEFAULT_CURSOR);
					e1.printStackTrace();
				}
				contentPane.setCursor(DEFAULT_CURSOR);
				}
			}
		});
		tblKambiyo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblKambiyo.setRowHeight(22);
		tblKambiyo.setShowHorizontalLines(true);
		tblKambiyo.setShowVerticalLines(true);
		tblKambiyo.setFont(new Font("Dialog", Font.PLAIN, 12));
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			tblKambiyo.setGridColor(oac.gridcolor);
		}
		tablo_baslik(tblKambiyo);
		scrollPane_4.setViewportView(tblKambiyo);

		ScrollPaneWin11 scrollPane_5 = new ScrollPaneWin11();
		tabbedPane.addTab("Sms", null, scrollPane_5, null);

		tblSms = new JTable(){
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int row, int column) {     return false;          }};
		tblSms.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
				if (tblSms.getRowCount() == 0) return ;
				if (tblSms.getSelectedRow()  < 0) return;
				contentPane.setCursor(WAIT_CURSOR);
				try {
					kutu_temizle();
					doldur_kutu(tblSms,tblSms.getSelectedRow());
					contentPane.setCursor(DEFAULT_CURSOR);
				} catch (Exception e1) {
					contentPane.setCursor(DEFAULT_CURSOR);
					e1.printStackTrace();
				} 
				contentPane.setCursor(DEFAULT_CURSOR);
				}
			}
		});
		tblSms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblSms.setRowHeight(22);
		tblSms.setShowHorizontalLines(true);
		tblSms.setShowVerticalLines(true);
		tblSms.setFont(new Font("Dialog", Font.PLAIN, 12));
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			tblSms.setGridColor(oac.gridcolor);
		}
		tablo_baslik(tblSms);
		scrollPane_5.setViewportView(tblSms);

		ScrollPaneWin11 scrollPane_6 = new ScrollPaneWin11();
		tabbedPane.addTab("Gunluk", null, scrollPane_6, null);
		tblGunluk = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {     return false;          }};
		tblGunluk.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
				if (tblGunluk.getRowCount() == 0) return ;
				if (tblGunluk.getSelectedRow()  < 0) return;
				contentPane.setCursor(WAIT_CURSOR);
				try {
					kutu_temizle();
					doldur_kutu(tblGunluk,tblGunluk.getSelectedRow());
					contentPane.setCursor(DEFAULT_CURSOR);
				} catch (Exception e1) {
					contentPane.setCursor(DEFAULT_CURSOR);
					e1.printStackTrace();
				} 
				contentPane.setCursor(DEFAULT_CURSOR);
				}
			}
		});
		tblGunluk.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblGunluk.setRowHeight(22);
		tblGunluk.setShowHorizontalLines(true);
		tblGunluk.setShowVerticalLines(true);
		tblGunluk.setFont(new Font("Dialog", Font.PLAIN, 12));
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			tblGunluk.setGridColor(oac.gridcolor);
		}
		tablo_baslik(tblGunluk);
		scrollPane_6.setViewportView(tblGunluk);
		
		//*****************************************************************
		ScrollPaneWin11 scrollPane_9 = new ScrollPaneWin11();
		tabbedPane.addTab("Kereste", null, scrollPane_9, null);
		tblKereste = new JTable(){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {     return false;          }};
			tblKereste.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent lse) {
					if (!lse.getValueIsAdjusting()) {
					if (tblKereste.getRowCount() == 0) return ;
					if (tblKereste.getSelectedRow()  < 0) return;
					contentPane.setCursor(WAIT_CURSOR);
					try {
						kutu_temizle();
						doldur_kutu(tblKereste,tblKereste.getSelectedRow());
						contentPane.setCursor(DEFAULT_CURSOR);
					} catch (Exception e1) {
						e1.printStackTrace();
						contentPane.setCursor(DEFAULT_CURSOR);
					} 
					contentPane.setCursor(DEFAULT_CURSOR);
					}
				}
			});
		tblKereste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblKereste.setRowHeight(22);
		tblKereste.setShowHorizontalLines(true);
		tblKereste.setShowVerticalLines(true);
		tblKereste.setFont(new Font("Dialog", Font.PLAIN, 12));
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			tblKereste.setGridColor(oac.gridcolor);
		}
		tablo_baslik(tblKereste);
		scrollPane_9.setViewportView(tblKereste);
		//******************************************************************
		ScrollPaneWin11 scrollPane_7 = new ScrollPaneWin11();
		
		tabbedPane.addTab("Sifre", null, scrollPane_7, null);
		JPanel panel_1 = new JPanel();
		scrollPane_7.setViewportView(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sifreniz");
		lblNewLabel.setBounds(102, 76, 84, 14);
		panel_1.add(lblNewLabel);

		txtsif = new JPasswordField();
		txtsif.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					contentPane.setCursor(WAIT_CURSOR);
					boolean varmi;
					try {
						byte[]  qaz =	ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(oac.sDONDUR.sDONDUR(txtsif)) ;
						varmi = oac.uSER_ISL.user_var(GLOBAL.KULL_ADI, Arrays.toString(qaz));
						if (varmi == true)
						{
							lblysif.setVisible(true);
							txtyenisif.setVisible(true);
							btnNewButton_2_1_1.setVisible(true);
							contentPane.setCursor(DEFAULT_CURSOR);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Sifre Yanlis", "Sifre Degistirme", JOptionPane.PLAIN_MESSAGE);
							lblysif.setVisible(false);
							txtyenisif.setVisible(false);
							btnNewButton_2_1_1.setVisible(false);
							contentPane.setCursor(DEFAULT_CURSOR);
						}
						} catch (Exception e1) {
							e1.printStackTrace();
						} 
					}
				}
		});
		txtsif.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtsif.setBounds(196, 73, 147, 20);
		panel_1.add(txtsif);
		txtsif.setColumns(10);

		txtyenisif = new JTextField();
		txtyenisif.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtyenisif.setColumns(10);
		txtyenisif.setBounds(196, 125, 147, 20);
		txtyenisif.setVisible(false);
		txtyenisif.setText("");
		panel_1.add(txtyenisif);

		lblysif = new JLabel("Yeni Sifreniz");
		lblysif.setBounds(102, 128, 84, 14);
		lblysif.setVisible(false);
		panel_1.add(lblysif);
		
		btnNewButton_2_1_1 = new JButton();
		btnNewButton_2_1_1.setVisible(false);
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					contentPane.setCursor(WAIT_CURSOR);
					String oku = new String(txtyenisif.getText());
					if (oku.equals(""))
					{
						txtsif.requestFocus();
						contentPane.setCursor(DEFAULT_CURSOR);
						return;
					}
					contentPane.setCursor(WAIT_CURSOR);
					oac.uSER_ISL.sifre_degis(GLOBAL.KULL_ADI, txtyenisif.getText());
					txtyenisif.setText("");
					txtsif.setText("");
					txtyenisif.setVisible(false);
					lblysif.setVisible(false); 
					txtsif.requestFocus();
					//
					byte[]  qaz =	ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(txtyenisif.getText()) ;
					String response = Arrays.toString(qaz);
					GLOBAL.setting_yaz("SIFRE", response);
					contentPane.setCursor(DEFAULT_CURSOR);
					//
				} catch (Exception ex)
				{
					contentPane.setCursor(DEFAULT_CURSOR);
					JOptionPane.showMessageDialog(null,  ex.getMessage(), "Sifre Kaydet", JOptionPane.PLAIN_MESSAGE);
					txtsif.requestFocus();		
				}		
			}
		});
		btnNewButton_2_1_1.setToolTipText("Kaydet");
		btnNewButton_2_1_1.setIcon(new ImageIcon(CAL_DIZIN.class.getResource("/ICONLAR/save.png")));
		btnNewButton_2_1_1.setBounds(362, 124, 25, 23);
		panel_1.add(btnNewButton_2_1_1);

		tabbedPane.setEnabledAt(7, true);

		ScrollPaneWin11 scrollPane_8 = new ScrollPaneWin11();
		tabbedPane.addTab("Loglama", null, scrollPane_8, null);

		JPanel panel_2 = new JPanel();
		scrollPane_8.setViewportView(panel_2);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "E Mail Loglama", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 99, 177)));
		panel_3.setBounds(10, 11, 545, 111);
		panel_3.setLayout(null);
		panel_2.add(panel_3);

		lblNewLabel_2 = new JLabel("E Mail Adresi");
		lblNewLabel_2.setBounds(10, 39, 78, 14);
		panel_3.add(lblNewLabel_2);

		txt_Lmaill = new JTextField();
		txt_Lmaill.setBounds(98, 36, 307, 20);
		panel_3.add(txt_Lmaill);
		txt_Lmaill.setColumns(10);

		cmb_maillist = new JComboBox<String>();
		cmb_maillist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmb_maillist.getItemCount() != 0)
				{
					txt_Lmaill.setText(cmb_maillist.getItemAt(cmb_maillist.getSelectedIndex()));
				}
				else if (cmb_maillist.getItemCount() == 0) 
				{
					txt_Lmaill.setText(cmb_maillist.getItemAt(1));
				}
				}
		});
		cmb_maillist.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cmb_maillist.getItemCount() != 0)
				{
					txt_Lmaill.setText(cmb_maillist.getItemAt(cmb_maillist.getSelectedIndex()));
				}
				else if (cmb_maillist.getItemCount() == 0) 
				{
					txt_Lmaill.setText(cmb_maillist.getItemAt(1));
				}
			}
		});
		cmb_maillist.setBounds(98, 67, 307, 22);
		panel_3.add(cmb_maillist);
			
		JButton btnNewButton_7 = new JButton("Mail Gonder");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_Lmaill.getText().equals("")) return ;
				MAIL_AT mAT = new MAIL_AT();
				try 
				{
					contentPane.setCursor(WAIT_CURSOR);
					oac.uSER_ISL.mail_bak();
					GLOBAL.Log_Mail = txt_Lmaill.getText();
					lOG_BILGI lBILGI = new lOG_BILGI();
					lBILGI.setmESAJ("Loglama Deneme E Mail ");
					lBILGI.seteVRAK("");
					mAT.Logla(lBILGI,BAGLAN_LOG.cariLogDizin);
					contentPane.setCursor(DEFAULT_CURSOR);
					JOptionPane.showMessageDialog(null,  "Mail Gonderildi........", "OBS SISTEM", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					contentPane.setCursor(DEFAULT_CURSOR);
					JOptionPane.showMessageDialog(null,  e1.getMessage(), "OBS SISTEM", JOptionPane.WARNING_MESSAGE);
				} 
			}
		});
		btnNewButton_7.setBounds(415, 35, 120, 23);
		panel_3.add(btnNewButton_7);
		
		btnNewButton_2_2 = new JButton("");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					contentPane.setCursor(WAIT_CURSOR);
					String oku = txt_Lmaill.getText();
					if (! oku.equals(""))
					{
						ResultSet	rs = null;
						oac.uSER_ISL.log_mail_kont_kayit(GLOBAL.KULL_ADI , oku);
						rs = oac.uSER_ISL.log_mail_oku(GLOBAL.KULL_ADI);
						if (!rs.isBeforeFirst() ) {  
							return;
						} 
						else
						{
							cmb_maillist.removeAllItems();
							while (rs.next()) 
							{
								cmb_maillist.addItem(rs.getString("E_MAIL"));
							}
						}
						txt_Lmaill.setText(oac.uSER_ISL.log_mail_aktiv_oku(GLOBAL.KULL_ADI));
					}
					contentPane.setCursor(DEFAULT_CURSOR);
				}
				catch (Exception ex)
				{
					contentPane.setCursor(DEFAULT_CURSOR);
					JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Kaydet", JOptionPane.PLAIN_MESSAGE);
					txtsif.requestFocus();		
				}				

			}
		});
		btnNewButton_2_2.setToolTipText("Kaydet");
		btnNewButton_2_2.setIcon(new ImageIcon(CAL_DIZIN.class.getResource("/ICONLAR/save.png")));
		btnNewButton_2_2.setBounds(415, 67, 25, 23);
		panel_3.add(btnNewButton_2_2);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//**************
		lblUser.setText(GLOBAL.KULL_ADI);
		comboBox = new JComboBox<String>();
		comboBox.setEditable(true);
		comboBox.setBounds(102, 155, 157, 22);
		panel.add(comboBox);

		txtcdid = new JTextField();
		txtcdid.setBounds(34, 99, 38, 20);
		txtcdid.setVisible(false);
		panel.add(txtcdid);
		txtcdid.setColumns(10);

		cmbhangisql = new JComboBox<String>();
		cmbhangisql.setForeground(new Color(0, 0, 139));
		cmbhangisql.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbhangisql.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent e) {
				String hangi = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex())  ;
				if (hangi == "MS SQL")
				{
					comboBox.removeAllItems();
					comboBox.addItem("");
					comboBox.setEnabled(true);
					chckbxD.setEnabled(true);
					chckbxO.setEnabled(true);
				}
				else
				{
					comboBox.removeAllItems();
					comboBox.addItem("");
					comboBox.setEnabled(false);
					chckbxD.setEnabled(false);
					chckbxO.setEnabled(false);
				}
			}
		});
		cmbhangisql.setModel(new DefaultComboBoxModel<String>(new String[] {"MS SQL", "MY SQL"}));
		cmbhangisql.setBounds(102, 43, 157, 22);
		panel.add(cmbhangisql);

		JLabel lblLoglama = new JLabel("Loglama");
		lblLoglama.setBounds(24, 130, 68, 14);
		panel.add(lblLoglama);

		chckbxL_1 = new JCheckBox("");
		chckbxL_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		chckbxL_1.setBounds(102, 126, 25, 23);
		panel.add(chckbxL_1);

		JLabel lblKayitserver = new JLabel("Serverler");
		lblKayitserver.setBounds(24, 210, 78, 14);
		panel.add(lblKayitserver);

		/////////////////////////////////////////////////LOGLAMA BUTTON /////////////////////

		menu = new JPopupMenu("");
		menu.addSeparator();
		cbVeritabani = new JCheckBoxMenuItem("Veritabani Kayit");
		cbVeritabani.setForeground(new Color(0, 128, 128));
		cbVeritabani.setUI(new StayOpenCheckBoxMenuItemUI());
		cbVeritabani.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				vt = (e.getStateChange() == 1 ? true:false);    	
			}
		});
		cbVeritabani.setSelected(true);
		menu.add(cbVeritabani);

		cbDosya = new JCheckBoxMenuItem("Dosya");
		cbDosya.setForeground(new Color(0, 128, 128));
		cbDosya.setUI(new StayOpenCheckBoxMenuItemUI());
		cbDosya.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ds = (e.getStateChange() == 1 ? true:false);    	
			}
		});
		menu.add(cbDosya);
		cbText = new JCheckBoxMenuItem("Text Dosya");
		cbText.setForeground(new Color(0, 128, 128));
		cbText.setUI(new StayOpenCheckBoxMenuItemUI());
		cbText.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				tx = (e.getStateChange() == 1 ? true:false);    	
			}
		});
		menu.add(cbText);

		cbMail = new JCheckBoxMenuItem("Email Atma");
		cbMail.setForeground(new Color(0, 128, 128));
		cbMail.setUI(new StayOpenCheckBoxMenuItemUI());
		cbMail.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				em = (e.getStateChange() == 1 ? true:false);    	
			}
		});
		menu.add(cbMail);
		JButton btnNewButton_6 = new JButton();
		btnNewButton_6.setForeground(new Color(0, 0, 128));

		btnNewButton_6.setText("Loglama Secimi");
		btnNewButton_6.setBounds(130, 125, 129, 23);
		btnNewButton_6.setAction(new AbstractAction("Loglama Secimi") {
			@Override
			public void actionPerformed(ActionEvent e) {
				menu.show(btnNewButton_6, 0, btnNewButton_6.getHeight());
			}
		});
		panel.add(btnNewButton_6);

		separator = new JSeparator();
		separator.setForeground(new Color(0, 191, 255));
		separator.setBounds(24, 32, 260, 2);
		panel.add(separator);
		
		JButton btnNewButton_2_1 = new JButton("");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				USER_ISLEMLERI usrIslemleri = new USER_ISLEMLERI();
				try {
					usrIslemleri.ip_sil(cmbip.getSelectedItem().toString());
					ip_doldur();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "Silme Isleminde Hata Olustu........", "Ip Silme.....", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_2_1.setToolTipText("Sil");
		btnNewButton_2_1.setBounds(265, 205, 25, 23);
		btnNewButton_2_1.setIcon(new ImageIcon(CAL_DIZIN.class.getResource("/ICONLAR/sil.png")));
		panel.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_2 = new JButton("");
		btnNewButton_2_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtKodu.getText().equals("")) return ;
					if(txtkul.getText().equals("")) return ;
					if(txtcdid.getText().equals("")) return ;
					String loglama = (vt == true ? "true," : "false,") + (ds == true ? "true," : "false,") + (tx ==true ? "true," : "false,") + (em == true ? "true":"false");
					oac.uSER_ISL.loglama_kayit(txtcdid.getText(),chckbxL_1.isSelected() ? 1 : 0, loglama);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1_2.setToolTipText("Loglama Kaydet");
		btnNewButton_2_1_2.setToolTipText("Kaydet");
		btnNewButton_2_1_2.setIcon(new ImageIcon(CAL_DIZIN.class.getResource("/ICONLAR/save.png")));
		btnNewButton_2_1_2.setBounds(265, 125, 25, 23);
		panel.add(btnNewButton_2_1_2);
		
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try {

		activ_sayfa =0;
		
			grid_doldur();
			doldur_kutu(tblCari,0);
			tblCari.requestFocus();
			tblCari.setRowSelectionInterval(0, 0);
			contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		} catch (Exception e1) {
	
			e1.printStackTrace();
		}
		

	}
	private void grid_doldur() throws ClassNotFoundException, SQLException
	{
		kutu_temizle();
		switch(activ_sayfa) 
		{
		case 0:
			GRID_TEMIZLE.grid_temizle(tblCari);
			grid_ortak_doldur(tblCari, "Cari Hesap");
			break;
		case 1:
			GRID_TEMIZLE.grid_temizle(tblFatura);
			grid_ortak_doldur(tblFatura, "Fatura");
			break;
		case 2:
			GRID_TEMIZLE.grid_temizle(tblAdres);
			grid_ortak_doldur(tblAdres, "Adres");
			break;
		case 3:
			GRID_TEMIZLE.grid_temizle(tblKur);
			grid_ortak_doldur(tblKur, "Kur");
			break;
		case 4:
			GRID_TEMIZLE.grid_temizle(tblKambiyo);
			grid_ortak_doldur(tblKambiyo, "Kambiyo");
			break;
		case 5:
			GRID_TEMIZLE.grid_temizle(tblSms);
			grid_ortak_doldur(tblSms, "Sms");
			break;
		case 6:
			GRID_TEMIZLE.grid_temizle(tblGunluk);
			grid_ortak_doldur(tblGunluk, "Gunluk");
			break;
		case 7:
			GRID_TEMIZLE.grid_temizle(tblKereste);
			grid_ortak_doldur(tblKereste, "Kereste");
			break;
		}
	}
	private void grid_ortak_doldur( JTable grd,String prg) throws ClassNotFoundException, SQLException 
	{
		ResultSet	rs = null;
		rs = oac.uSER_ISL.user_db_izinleri(GLOBAL.KULL_ADI, prg);
		if (!rs.isBeforeFirst() ) {  
			
			return;
		} 
		grd.setModel(DbUtils.resultSetToTableModel(rs));
		grd.removeColumn(grd.getColumnModel().getColumn(0));
		grd.removeColumn(grd.getColumnModel().getColumn(1));
		grd.removeColumn(grd.getColumnModel().getColumn(1));
		grd.removeColumn(grd.getColumnModel().getColumn(1));
		grd.removeColumn(grd.getColumnModel().getColumn(1));
		grd.removeColumn(grd.getColumnModel().getColumn(3));
		grd.removeColumn(grd.getColumnModel().getColumn(4));
		grd.removeColumn(grd.getColumnModel().getColumn(4));
		grd.removeColumn(grd.getColumnModel().getColumn(4));
		grd.removeColumn(grd.getColumnModel().getColumn(5));
		grd.removeColumn(grd.getColumnModel().getColumn(5));
		//grd.removeColumn(grd.getColumnModel().getColumn(5));
		JTableHeader th = grd.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue( "Kodu" );
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(70);
		tc.setMaxWidth(70);
		
		tc = tcm.getColumn(1);
		tc.setHeaderRenderer(new SOLA());
		tc.setHeaderValue( "Ip" );
		tc.setMinWidth(200);
		//tc.setMaxWidth(200);
		
		tc = tcm.getColumn(2);
		tc.setHeaderRenderer(new SOLA());
		tc.setHeaderValue( "Modul" );
		
		tc = tcm.getColumn(3);
		tc.setHeaderRenderer(new SOLA());
		tc.setHeaderValue( "Yer" );
		tc.setMinWidth(50);
		tc.setMaxWidth(50);
		
		
		tc = tcm.getColumn(4);
		tc.setHeaderRenderer(new SOLA());
		tc.setHeaderValue( "SQL" );
		tc.setMinWidth(80);
		tc.setMaxWidth(80);
		
		th.repaint();
		grd.setRowSelectionInterval(0, 0);
		
		if (chckbxS.isSelected())
		{
			chckbxD.setEnabled(false);
			chckbxO.setEnabled(false);
		}
		else
		{
			chckbxD.setEnabled(true);
			chckbxO.setEnabled(true);
		}
	}
	public static void HeaderRenderer(JTable table) {
		DefaultTableCellRenderer renderer;
		renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.LEFT);
	}
	private static  void doldur_kutu( JTable grd,int satir) throws ClassNotFoundException, SQLException 
	{
		if (grd.getRowCount()== 0 ) {  
			kutu_temizle();
			return;
		} 
		ip_doldur();
		txtKodu.setText(grd.getModel().getValueAt(satir, 1).toString());
		txtIp.setText(grd.getModel().getValueAt(satir, 6).toString());
		txtkul.setText(grd.getModel().getValueAt(satir, 3).toString());
		
		String decodedString = grd.getModel().getValueAt(satir, 4).toString();
		String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
		byte[] bytes = new byte[byteValues.length];
		for (int i=0, len=bytes.length; i<len; i++) {
		   bytes[i] = Byte.parseByte(byteValues[i].trim());     
		}
		try {
			txtsifr.setText( ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		txtcdid.setText(grd.getModel().getValueAt(satir, 0).toString());
		cmbhangisql.setSelectedItem(grd.getModel().getValueAt(satir, 13).toString());
		comboBox.removeAllItems();
		comboBox.addItem(grd.getModel().getValueAt(satir, 5).toString());
		if (grd.getModel().getValueAt(satir, 9).equals("L"))
		{
			chckbxL.setSelected(true);
			chckbxS.setSelected(false);
		}
		else
		{
			chckbxS.setSelected(true);
			chckbxL.setSelected(false);
		}
		int say = Integer.parseInt( grd.getModel().getValueAt(satir,14).toString());
		if (say == 1)
		{
			chckbxL_1.setSelected(true);
		}
		else
		{
			chckbxL_1.setSelected(false);
		}
		String[] token = grd.getModel().getValueAt(satir, 15).toString().split(",");
		cbVeritabani.setSelected( (token[0].equals("true") ? true:false));	
		cbDosya.setSelected( (token[1].equals("true") ? true:false));
		cbText.setSelected( (token[2].equals("true") ? true:false));
		cbMail.setSelected( (token[3].equals("true") ? true:false));
		if (grd.getModel().getValueAt(satir, 10).equals("D"))
		{
			chckbxD.setSelected(true);
			chckbxO.setSelected(false);
			txtdiz.setText("");
		}
		else
		{
			chckbxO.setSelected(true);
			chckbxD.setSelected(false);
			txtdiz.setEnabled(true);
			btndizsec.setEnabled(true);
			txtdiz.setText(grd.getModel().getValueAt(satir, 8).toString());
		}

		contentPane.setCursor(DEFAULT_CURSOR);
	}
	private void  tablo_baslik(JTable table)
	{
		JTableHeader th = table.getTableHeader();
		Dimension dd = th.getPreferredSize();
		dd.height = 30;
		th.setPreferredSize(dd); 
		th.repaint();
	}
	private static void kutu_temizle() throws ClassNotFoundException, SQLException
	{
		txtKodu.setText("");
		txtIp.setText("");
		txtkul.setText("");
		txtsifr.setText("");
		txtcdid.setText("");
		comboBox.removeAllItems();
		chckbxL.setSelected(true);
		chckbxS.setSelected(false);
		chckbxD.setSelected(true);
		chckbxO.setSelected(false);
		txtdiz.setEnabled(false);
		btndizsec.setEnabled(false);
		txtdiz.setText("");
		chckbxL_1.setSelected(false);
		cbVeritabani.setSelected(false);	
		cbDosya.setSelected(false);
		cbText.setSelected(false);
		cbMail.setSelected(false);
		cmbhangisql.setSelectedItem("MS SQL");
		ip_doldur();
	}
	private static void mail_doldur() throws ClassNotFoundException, SQLException
	{
		cmb_maillist.removeAllItems();
		ResultSet	rs = null;
		USER_ISLEMLERI usr = new USER_ISLEMLERI();
		rs = usr.log_mail_oku(GLOBAL.KULL_ADI );
		if (!rs.isBeforeFirst() ) {  
			return;
		} 
		else
		{
			while (rs.next()) 
			{
				cmb_maillist.addItem(rs.getString("E_MAIL"));
			}
			txt_Lmaill.setText(usr.log_mail_aktiv_oku(GLOBAL.KULL_ADI));
		}
	}
	private static  void ip_doldur() throws ClassNotFoundException, SQLException
	{
		cmbip.removeAllItems();
		ResultSet	rs = null;
		USER_ISLEMLERI usr = new USER_ISLEMLERI();
		rs = usr.ipp(GLOBAL.KULL_ADI);
		if (!rs.isBeforeFirst() ) {  
			return;
		} 
		else
		{
			cmbip.addItem("");
			while (rs.next())
			{
				cmbip.addItem(rs.getString("IP"));
			}
		}
	}
	private  void server_control() throws HeadlessException, ClassNotFoundException
	{
		contentPane.setCursor(WAIT_CURSOR);
		cONN_AKTAR();
		CONNECT s_CONN = new CONNECT(oac._IConn);
		if (chckbxL.isSelected() )
		{
			Server_Bilgi sBilgi = new Server_Bilgi() ;
			sBilgi.setIns(comboBox.getSelectedItem().toString()); 
			sBilgi.setKull(txtkul.getText());  ;
			sBilgi.setSifre( oac.sDONDUR.sDONDUR(txtsifr)); ;
			sBilgi.setPort(txtIp.getText()); ;
			contentPane.setCursor(WAIT_CURSOR);
			if ( s_CONN.Server_kontrol_L( sBilgi) == true  )
			{
				contentPane.setCursor(DEFAULT_CURSOR);
				btnNewButton_1.setEnabled(true);
			}
			else
			{
				contentPane.setCursor(DEFAULT_CURSOR);
				JOptionPane.showMessageDialog(null, "Baglanti Saglanamadi........", "Server Baglanti", JOptionPane.ERROR_MESSAGE);
				btnNewButton_1.setEnabled(false);
			}
		}
		else
		{
			contentPane.setCursor(WAIT_CURSOR);
			Server_Bilgi sBilgi = new Server_Bilgi() ;
			sBilgi.setServer(txtIp.getText());;
			sBilgi.setIns(comboBox.getSelectedItem().toString() );;
			sBilgi.setKull(txtkul.getText()); ;
			sBilgi.setSifre( oac.sDONDUR.sDONDUR(txtsifr));;
			if (s_CONN.Server_kontrol_S(sBilgi ) == true)
			{
				contentPane.setCursor(DEFAULT_CURSOR);
				btnNewButton_1.setEnabled(true);
			}
			else
			{
				contentPane.setCursor(DEFAULT_CURSOR);
				JOptionPane.showMessageDialog(null, "Baglanti Saglanamadi........", "Server Baglanti", JOptionPane.ERROR_MESSAGE);
				btnNewButton_1.setEnabled(false);
			}
		}
	}
	private  void database_kontrol() throws ClassNotFoundException, HeadlessException, SQLException, IOException
	{
		contentPane.setCursor(WAIT_CURSOR);
		cONN_AKTAR();	 
		CONNECT s_CONN = new CONNECT(oac._IConn);
		String program = "";
		String modul = "";
		switch(activ_sayfa) 
		{
		case 0:
			modul = "Cari Hesap";
			program = "OK_Car" + txtKodu.getText();
			break;
		case 1:
			modul = "Fatura";
			program = "OK_Fat" + txtKodu.getText();
			break;
		case 2:
			modul = "Adres";
			program = "OK_Adr" + txtKodu.getText();
			break;
		case 3:
			modul = "Kur";
			program = "OK_Kur" + txtKodu.getText();
			break;
		case 4:
			modul = "Kambiyo";
			program = "OK_Kam" + txtKodu.getText();
			break;
		case 5:
			modul = "Sms";
			program = "OK_Sms" + txtKodu.getText();
			break;
		case 6:
			modul = "Gunluk";
			program = "OK_Gun" + txtKodu.getText();
			break;
		case 7:
			modul = "Kereste";
			program = "OK_Ker" + txtKodu.getText();
			break;
		}
		if (chckbxL.isSelected())
		{
			lokal_dosya(s_CONN,program,modul);//LOCAL DOSYA KONTROL \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		}
		else  // Server
		{
			server_dosya(s_CONN,program,modul);//SERVER DOSYA KONTROL \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		}
	}
	private void lokal_dosya(CONNECT s_CONN,String program,String modul) throws HeadlessException, ClassNotFoundException, SQLException, IOException
	{
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setDb(program); ;
		sBilgi.setKull(txtkul.getText()); ;
		sBilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr));;
		sBilgi.setIns(comboBox.getSelectedItem().toString()); ;
		sBilgi.setPort(txtIp.getText());;
		if ( s_CONN.Dosya_kontrol_L(sBilgi) == true)
		{
			boolean izinli = true;
			if ( !GLOBAL.KULL_ADI.equals("Admin") )
			{
				ResultSet	rs = null;
				rs = oac.uSER_ISL.user_details_izinleri(GLOBAL.KULL_ADI, modul, "YER = 'L'");
				if (!rs.isBeforeFirst() ) {  
					izinli = false;
				} 
			}
			if (izinli == false)
			{
				contentPane.setCursor(DEFAULT_CURSOR);
				JOptionPane.showMessageDialog(null, "Bu Dosyayi Kullanmaya Izniniz Yok --- Admin den yetki almaniz lazim" + System.lineSeparator()  + System.lineSeparator()+"'Admin' den izin verilmesi gereklidir...", "Dosya Olusturma", JOptionPane.ERROR_MESSAGE);
				btnNewButton_1.setEnabled(false);
				txtKodu.requestFocus();
				return;
			}
			mdb_yaz();
			grid_doldur();
			switch(activ_sayfa) 
			{
			case 0:
				doldur_kutu(tblCari, 0);
				break;
			case 1:
				doldur_kutu(tblFatura, 0);
				break;
			case 2:
				doldur_kutu(tblAdres, 0);
				break;
			case 3:
				doldur_kutu(tblKur, 0);
				break;
			case 4:
				doldur_kutu(tblKambiyo, 0);
				break;
			case 5:
				doldur_kutu(tblSms, 0);
				break;
			case 6:
				doldur_kutu(tblGunluk, 0);
				break;
			case 7:
				doldur_kutu(tblKereste, 0);
				break;
			}
			contentPane.setCursor(DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, "Veritabani Baglantisi gerceklestirildi", "Dosya Baglanti", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		else
		{
			contentPane.setCursor(DEFAULT_CURSOR);
			int g =  JOptionPane.showOptionDialog( null,  "Yeni Dosya Olusturulsunmu............?", "Dosya Olusturma",   JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,	 	null,    	oac.options,  	 	oac.options[1]); 
			if(g != 0 ) { return;	}
			Thread.yield();
			dosya_olustur_L();
			mdb_yaz();
			grid_doldur();
			switch(activ_sayfa) 
			{
			case 0:
				doldur_kutu(tblCari, 0);
				break;
			case 1:
				doldur_kutu(tblFatura, 0);
				break;
			case 2:
				doldur_kutu(tblAdres, 0);
				break;
			case 3:
				doldur_kutu(tblKur, 0);
				break;
			case 4:
				doldur_kutu(tblKambiyo, 0);
				break;
			case 5:
				doldur_kutu(tblSms, 0);
				break;
			case 6:
				doldur_kutu(tblGunluk, 0);
				break;
			case 7:
				doldur_kutu(tblKereste, 0);
				break;
			}
			contentPane.setCursor(DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null,  "Dosya Olusturuldu ...", "Dosya Olusturma", JOptionPane.PLAIN_MESSAGE);
			return;
		}
	}
	private void server_dosya(CONNECT s_CONN,String program,String modul) throws HeadlessException, ClassNotFoundException, SQLException, IOException
	{
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setServer(txtIp.getText());;
		sBilgi.setIns(comboBox.getSelectedItem().toString()); ;
		sBilgi.setKull(txtkul.getText()); ;
		sBilgi.setSifre( oac.sDONDUR.sDONDUR(txtsifr));;
		sBilgi.setDb( program); ;
		sBilgi.setPort(txtIp.getText());;
		if (	 s_CONN.Dosya_kontrol_S(sBilgi) ==true)
		{
			boolean izinli = true;
			if ( !GLOBAL.KULL_ADI.equals("Admin") )
			{
				ResultSet rs = oac.uSER_ISL.user_details_izinleri(GLOBAL.KULL_ADI, modul, "YER = 'S'");
				if (!rs.isBeforeFirst() ) {  
					izinli = false;
				} 
			}
			if (izinli == false)
			{
				contentPane.setCursor(DEFAULT_CURSOR);
				JOptionPane.showMessageDialog(null, "Bu Dosyayi Kullanmaya Izniniz Yok --- Admin den yetki almaniz lazim" + System.lineSeparator()  + System.lineSeparator()+"'Admin' den izin verilmesi gereklidir...", "Dosya Olusturma", JOptionPane.ERROR_MESSAGE);
				btnNewButton_1.setEnabled(false);
				txtKodu.requestFocus();
				return;
			}
			mdb_yaz();
			grid_doldur();
			switch(activ_sayfa) 
			{
			case 0:
				doldur_kutu(tblCari, 0);
				break;
			case 1:
				doldur_kutu(tblFatura, 0);
				break;
			case 2:
				doldur_kutu(tblAdres, 0);
				break;
			case 3:
				doldur_kutu(tblKur, 0);
				break;
			case 4:
				doldur_kutu(tblKambiyo, 0);
				break;
			case 5:
				doldur_kutu(tblSms, 0);
				break;
			case 6:
				doldur_kutu(tblGunluk, 0);
				break;
			case 7:
				doldur_kutu(tblKereste, 0);
				break;
			}
			contentPane.setCursor(DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, "Veritabani Baglantisi gerceklestirildi", "Dosya Baglanti", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		else
		{
			contentPane.setCursor(DEFAULT_CURSOR);
			int g =  JOptionPane.showOptionDialog( null,  "Yeni Dosya Olusturulsunmu............?", "Dosya Olusturma",   JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,null, oac.options, 	oac.options[1]); 
			if(g != 0 ) { return;	}
			{
				contentPane.setCursor(WAIT_CURSOR);
				Thread.yield();
				dosya_olustur_S();
				mdb_yaz();
				grid_doldur();
				switch(activ_sayfa) 
				{
				case 0:
					doldur_kutu(tblCari, 0);
					break;
				case 1:
					doldur_kutu(tblFatura, 0);
					break;
				case 2:
					doldur_kutu(tblAdres, 0);
					break;
				case 3:
					doldur_kutu(tblKur, 0);
					break;
				case 4:
					doldur_kutu(tblKambiyo, 0);
					break;
				case 5:
					doldur_kutu(tblSms, 0);
					break;
				case 6:
					doldur_kutu(tblGunluk, 0);
					break;
				case 7:
					doldur_kutu(tblKereste, 0);
					break;
				}
				contentPane.setCursor(DEFAULT_CURSOR);
				JOptionPane.showMessageDialog(null,  "Dosya Olusturuldu ..........", "Dosya Olusturma", JOptionPane.PLAIN_MESSAGE);
				return;
			}
		}
	}
	private  void mdb_yaz() throws ClassNotFoundException, SQLException
	{
		switch(activ_sayfa) {
		  case 0:
			  mdb_yaz_2("Cari Hesap");
			  break;
		  case 1:
			  mdb_yaz_2("Fatura");
			  break;
		  case 2:
			  mdb_yaz_2("Adres");
			  break;
		  case 3:
			  mdb_yaz_2("Kur");
			  break;
		  case 4:
			  mdb_yaz_2("Kambiyo");
			  break;
		  case 5:
			  mdb_yaz_2("Sms");
			  break;
		  case 6:
			  mdb_yaz_2("Gunluk");
			  break;
		  case 7:
			  mdb_yaz_2("Kereste");
			  break;
		}
		if(! txtIp.getText().equals(""))
		{
		oac.uSER_ISL.ip_dos_kont(txtIp.getText());
		}
	}
	private void mdb_yaz_2(String modul) throws ClassNotFoundException, SQLException
	{
		String loglama = (vt == true ? "true," : "false,") + (ds == true ? "true," : "false,") + (tx ==true ? "true," : "false,") + (em == true ? "true":"false");
		oac.uSER_ISL.calisanmi_degis(GLOBAL.KULL_ADI, modul,chckbxL.isSelected() ? "L" : "S"); // CaLISANMI DOSYA KONTROLU
		oac.uSER_ISL.details_yaz(txtKodu.getText(),GLOBAL.KULL_ADI, txtkul.getText(), oac.sDONDUR.sDONDUR(txtsifr), comboBox.getSelectedItem().toString() , txtIp.getText(), modul,txtdiz.getText(), chckbxL.isSelected() ? "L" : "S", chckbxD.isSelected() ? "D" : "O", "E", "E",cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()),  txtcdid.getText(),chckbxL_1.isSelected() ? 1 : 0, loglama);
	}
	private  void dosya_olustur_L() throws IOException, ClassNotFoundException, SQLException
	{
		switch(activ_sayfa) {
		case 0:
			car_olustur();
			break;
		case 1:
			stok_olustur();
			break;
		case 2:
			adr_olustur();
			break;
		case 3:
			kur_olustur();
			break;
		case 4:
			kam_olustur();
			break;
		case 5:
			sms_olustur();
			break;
		case 6:
			gun_olustur();
			break;
		case 7:
			ker_olustur();
			break;
		}
	}
	void car_olustur() throws ClassNotFoundException, SQLException
	{
		String strAdmin = "";
		strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz..........", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
		contentPane.setCursor(WAIT_CURSOR);
		cONN_AKTAR();
		lOGG_AKTAR("Cari Hesap");
		mODUL_AKTAR("Cari Hesap");
		CARI_ACCESS  c_Access = new CARI_ACCESS(oac._ICar,oac._ICari_Loger);
		BAGLAN.cariDizin.kULLANICI = txtkul.getText();
		BAGLAN.cariDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.cariDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.cariDizin.sERVER = txtIp.getText();
		BAGLAN.cariDizin.kOD = txtKodu.getText();
		BAGLAN.cariDizin.yER = "L";
		BAGLAN.cariDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());
		sbilgi.setFir_adi(strAdmin);
		sbilgi.setIns(comboBox.getSelectedItem().toString());
		sbilgi.setKull(txtkul.getText());
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr));
		sbilgi.setPort(txtIp.getText()); 
		if (chckbxD.isSelected())
		{
			sbilgi.setDizin_yeri("default");
			sbilgi.setDizin("");
			c_Access.cari_sifirdan_L(sbilgi,lBILGI,BAGLAN_LOG.cariLogDizin);
		}
		else
		{
			sbilgi.setDizin_yeri("");
			sbilgi.setDizin(txtdiz.getText());
			c_Access.cari_sifirdan_L(sbilgi,lBILGI,BAGLAN_LOG.cariLogDizin);
		}
	}
	void stok_olustur() throws ClassNotFoundException, SQLException
	{
		String strAdmin = "";
		strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz..........", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
		contentPane.setCursor(WAIT_CURSOR);
		cONN_AKTAR();
		lOGG_AKTAR("Stok");
		mODUL_AKTAR("Stok");
		STOK_ACCESS  s_Access = new STOK_ACCESS(oac._IStok,oac._IFatura_Loger);
		BAGLAN.fatDizin.kULLANICI = txtkul.getText();
		BAGLAN.fatDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.fatDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.fatDizin.sERVER = txtIp.getText();
		BAGLAN.fatDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.fatDizin.kOD = txtKodu.getText();
		BAGLAN.fatDizin.yER = "L";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());
		sbilgi.setFir_adi(strAdmin);
		sbilgi.setIns(comboBox.getSelectedItem().toString());
		sbilgi.setKull(txtkul.getText());
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr));
		sbilgi.setPort(txtIp.getText()); 
		if (chckbxD.isSelected())
		{
			sbilgi.setDizin_yeri("default");
			sbilgi.setDizin("");
			s_Access.fAT_SIFIR_L(sbilgi,lBILGI,BAGLAN_LOG.fatLogDizin);
		}
		else
		{
			sbilgi.setDizin_yeri("");
			sbilgi.setDizin(txtdiz.getText());
			s_Access.fAT_SIFIR_L(sbilgi,lBILGI,BAGLAN_LOG.fatLogDizin);
		}
	}
	void adr_olustur() throws ClassNotFoundException, SQLException
	{
		String strAdmin = "";
		strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz..........", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
		contentPane.setCursor(WAIT_CURSOR);
		cONN_AKTAR();
		lOGG_AKTAR("Adres");
		mODUL_AKTAR("Adres");
		ADRES_ACCESS  a_Access = new ADRES_ACCESS(oac._IAdres,oac._IAdres_Loger);
		BAGLAN.adrDizin.kULLANICI = txtkul.getText();
		BAGLAN.adrDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.adrDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.adrDizin.sERVER = txtIp.getText();
		BAGLAN.adrDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.adrDizin.kOD = txtKodu.getText();
		BAGLAN.adrDizin.yER = "L";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());;
		sbilgi.setFir_adi(strAdmin);;
		sbilgi.setIns(comboBox.getSelectedItem().toString());;
		sbilgi.setKull(txtkul.getText()); ;
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr)); ;
		sbilgi.setPort(txtIp.getText()); ;
		if (chckbxD.isSelected())
		{
			sbilgi.setDizin_yeri("default");
			sbilgi.setDizin("");
			a_Access.aDR_SIF_L(sbilgi,lBILGI,BAGLAN_LOG.adrLogDizin);
		}
		else
		{
			sbilgi.setDizin_yeri("");
			sbilgi.setDizin(txtdiz.getText());
			a_Access.aDR_SIF_L(sbilgi,lBILGI,BAGLAN_LOG.adrLogDizin);
		}
	}
	void kur_olustur() throws ClassNotFoundException, SQLException
	{
		contentPane.setCursor(WAIT_CURSOR);
		cONN_AKTAR();
		lOGG_AKTAR("Kur");
		mODUL_AKTAR("Kur");
		KUR_ACCESS  k_Access = new KUR_ACCESS(oac._IKur,oac._IKur_Loger);
		BAGLAN.kurDizin.kULLANICI = txtkul.getText();
		BAGLAN.kurDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.kurDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.kurDizin.sERVER = txtIp.getText();
		BAGLAN.kurDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.kurDizin.kOD = txtKodu.getText();
		BAGLAN.kurDizin.yER = "L";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());;
		sbilgi.setIns(comboBox.getSelectedItem().toString());;
		sbilgi.setKull(txtkul.getText()); ;
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr)); ;
		sbilgi.setPort(txtIp.getText()); ;
		if (chckbxD.isSelected())
		{
			sbilgi.setDizin_yeri("default");
			sbilgi.setDizin("");
			k_Access.kUR_SIFIR_L(sbilgi,lBILGI,BAGLAN_LOG.kurLogDizin);
		}
		else
		{
			sbilgi.setDizin_yeri("");
			sbilgi.setDizin(txtdiz.getText());
			k_Access.kUR_SIFIR_L(sbilgi,lBILGI,BAGLAN_LOG.kurLogDizin);
		}
	}
	void kam_olustur() throws ClassNotFoundException, SQLException
	{
		String strAdmin = "";
		strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz..........", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
		contentPane.setCursor(WAIT_CURSOR);
		cONN_AKTAR();
		lOGG_AKTAR("Kambiyo");
		mODUL_AKTAR("Kambiyo");
		KAMBIYO_ACCESS  ka_Access = new KAMBIYO_ACCESS(oac._IKambiyo,oac._IKambiyo_Loger);
		BAGLAN.kamDizin.kULLANICI = txtkul.getText();
		BAGLAN.kamDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.kamDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.kamDizin.sERVER = txtIp.getText();
		BAGLAN.kamDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.kamDizin.kOD = txtKodu.getText();
		BAGLAN.kamDizin.yER = "L";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());;
		sbilgi.setFir_adi(strAdmin);;
		sbilgi.setIns(comboBox.getSelectedItem().toString());;
		sbilgi.setKull(txtkul.getText()); ;
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr)); ;
		sbilgi.setPort(txtIp.getText()); ;
		if (chckbxD.isSelected())
		{
			sbilgi.setDizin_yeri("default");
			sbilgi.setDizin("");
			ka_Access.kAM_SIFIR_L(sbilgi,lBILGI,BAGLAN_LOG.kamLogDizin);
		}  
		else
		{
			sbilgi.setDizin_yeri("");
			sbilgi.setDizin(txtdiz.getText());
			ka_Access.kAM_SIFIR_L(sbilgi,lBILGI,BAGLAN_LOG.kamLogDizin);
		}
	}
	void sms_olustur() throws ClassNotFoundException, SQLException
	{
		contentPane.setCursor(WAIT_CURSOR);
		cONN_AKTAR();
		lOGG_AKTAR("Sms");
		mODUL_AKTAR("Sms");
		SMS_ACCESS  sms_Access = new SMS_ACCESS(oac._ISms,oac._ISms_Loger);
		BAGLAN.smsDizin.kULLANICI = txtkul.getText();
		BAGLAN.smsDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.smsDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.smsDizin.sERVER = txtIp.getText();
		BAGLAN.smsDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.smsDizin.kOD = txtKodu.getText();
		BAGLAN.smsDizin.yER = "L";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());
		sbilgi.setIns(comboBox.getSelectedItem().toString());
		sbilgi.setKull(txtkul.getText()); 
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr)); 
		sbilgi.setPort(txtIp.getText()); 
		if (chckbxD.isSelected())
		{
			sbilgi.setDizin_yeri("default");
			sbilgi.setDizin("");
			sms_Access.sMS_SIFIR_L(sbilgi,lBILGI,BAGLAN_LOG.smsLogDizin);
		}
		else
		{
			sbilgi.setDizin_yeri("");
			sbilgi.setDizin(txtdiz.getText());
			sms_Access.sMS_SIFIR_L(sbilgi,lBILGI,BAGLAN_LOG.smsLogDizin);

		}
	}
	void gun_olustur() throws ClassNotFoundException, SQLException
	{
		String strAdmin = "";
		strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz..........", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
		contentPane.setCursor(WAIT_CURSOR);
		cONN_AKTAR();
		lOGG_AKTAR("Gunluk");
		mODUL_AKTAR("Gunluk");
		GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(oac._IGunluk,oac._IGunluk_Loger);
		BAGLAN.gunDizin.kULLANICI = txtkul.getText();
		BAGLAN.gunDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.gunDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.gunDizin.sERVER = txtIp.getText();
		BAGLAN.gunDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.gunDizin.kOD = txtKodu.getText();
		BAGLAN.gunDizin.yER = "L";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());;
		sbilgi.setFir_adi(strAdmin);;
		sbilgi.setIns(comboBox.getSelectedItem().toString());;
		sbilgi.setKull(txtkul.getText()); ;
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr)); ;
		sbilgi.setPort(txtIp.getText()); ;
		if (chckbxD.isSelected())
		{
			sbilgi.setDizin_yeri("default");
			sbilgi.setDizin("");
			g_Access.gUN_SIFIR_L(sbilgi ,lBILGI,BAGLAN_LOG.gunLogDizin);
		}
		else
		{
			sbilgi.setDizin_yeri("");
			sbilgi.setDizin(txtdiz.getText());
			g_Access.gUN_SIFIR_L(sbilgi,lBILGI,BAGLAN_LOG.gunLogDizin);
		}
	}
	void ker_olustur() throws ClassNotFoundException, SQLException
	{
		String strAdmin = "";
		strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz..........", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
		contentPane.setCursor(WAIT_CURSOR);
		cONN_AKTAR();
		lOGG_AKTAR("Kereste");
		mODUL_AKTAR("Kereste");
		KERESTE_ACCESS  ker_Access = new KERESTE_ACCESS(oac._IKereste,oac._IKereste_Loger);
		BAGLAN.kerDizin.kULLANICI = txtkul.getText();
		BAGLAN.kerDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.kerDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.kerDizin.sERVER = txtIp.getText();
		BAGLAN.kerDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.kerDizin.kOD = txtKodu.getText();
		BAGLAN.kerDizin.yER = "L";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());;
		sbilgi.setFir_adi(strAdmin);;
		sbilgi.setIns(comboBox.getSelectedItem().toString());;
		sbilgi.setKull(txtkul.getText()); ;
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr)); ;
		sbilgi.setPort(txtIp.getText()); ;
		if (chckbxD.isSelected())
		{
			sbilgi.setDizin_yeri("default");
			sbilgi.setDizin("");
			ker_Access.kER_SIFIR_L(sbilgi ,lBILGI,BAGLAN_LOG.kerLogDizin);
		
		}
		else
		{
			sbilgi.setDizin_yeri("");
			sbilgi.setDizin(txtdiz.getText());
			ker_Access.kER_SIFIR_L(sbilgi,lBILGI,BAGLAN_LOG.kerLogDizin);
		}
	}
	void cari_s_olustur() throws ClassNotFoundException, SQLException
	{
		String strAdmin = "";
		strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz..........", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
		cONN_AKTAR();
		lOGG_AKTAR("Cari Hesap");
		mODUL_AKTAR("Cari Hesap");
		CARI_ACCESS  c_Access = new CARI_ACCESS(oac._ICar,oac._ICari_Loger);
		BAGLAN.cariDizin.kULLANICI = txtkul.getText();
		BAGLAN.cariDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.cariDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.cariDizin.sERVER = txtIp.getText();
		BAGLAN.cariDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.cariDizin.kOD = txtKodu.getText();
		BAGLAN.cariDizin.yER = "S";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());;
		sbilgi.setFir_adi(strAdmin);;
		sbilgi.setIns(comboBox.getSelectedItem().toString());;
		sbilgi.setKull(txtkul.getText()); ;
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr)); ;
		sbilgi.setServer(txtIp.getText()); ;
		sbilgi.setDizin_yeri("default");;
		sbilgi.setDizin("");;
		if (chckbxD.isSelected())
		{
			c_Access.cARI_SIFIR_S(sbilgi,lBILGI,BAGLAN_LOG.cariLogDizin);
		}
	}
	void stok_s_olustur() throws ClassNotFoundException, SQLException
	{
		String strAdmin = "";
		strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz..........", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
		cONN_AKTAR();
		lOGG_AKTAR("Stok");
		mODUL_AKTAR("Stok");
		STOK_ACCESS  s_Access = new STOK_ACCESS(oac._IStok,oac._IFatura_Loger);
		BAGLAN.fatDizin.kULLANICI = txtkul.getText();
		BAGLAN.fatDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.fatDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.fatDizin.sERVER = txtIp.getText();
		BAGLAN.fatDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.fatDizin.kOD = txtKodu.getText();
		BAGLAN.fatDizin.yER = "S";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());;
		sbilgi.setFir_adi(strAdmin);;
		sbilgi.setIns(comboBox.getSelectedItem().toString());;
		sbilgi.setKull(txtkul.getText()); ;
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr)); ;
		sbilgi.setServer(txtIp.getText()); ;
		sbilgi.setDizin_yeri("default");;
		sbilgi.setDizin("");;
		if (chckbxD.isSelected())
		{
			s_Access.fAT_SIFIR_S(sbilgi,lBILGI,BAGLAN_LOG.fatLogDizin);
		}
	}
	void adr_s_olustur() throws ClassNotFoundException, SQLException
	{
		String strAdmin = "";
		strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz..........", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
		cONN_AKTAR();
		lOGG_AKTAR("Adres");
		mODUL_AKTAR("Adres");
		ADRES_ACCESS  a_Access = new ADRES_ACCESS(oac._IAdres,oac._IAdres_Loger);
		BAGLAN.adrDizin.kULLANICI = txtkul.getText();
		BAGLAN.adrDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.adrDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.adrDizin.sERVER = txtIp.getText();
		BAGLAN.adrDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.adrDizin.kOD = txtKodu.getText();
		BAGLAN.adrDizin.yER = "S";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());;
		sbilgi.setFir_adi(strAdmin);;
		sbilgi.setIns(comboBox.getSelectedItem().toString());;
		sbilgi.setKull(txtkul.getText()); ;
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr)); ;
		sbilgi.setServer(txtIp.getText()); ;
		sbilgi.setDizin_yeri("default");;
		sbilgi.setDizin("");;
		
		if (chckbxD.isSelected())
		{
			a_Access.aDR_SIFIR_S(sbilgi,lBILGI,BAGLAN_LOG.adrLogDizin);
		}
	}
	void kur_s_olustur() throws ClassNotFoundException, SQLException
	{
		cONN_AKTAR();
		lOGG_AKTAR("Kur");
		mODUL_AKTAR("Kur");
		KUR_ACCESS  k_Access = new KUR_ACCESS(oac._IKur,oac._IKur_Loger);
		BAGLAN.kurDizin.kULLANICI = txtkul.getText();
		BAGLAN.kurDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.kurDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.kurDizin.sERVER = txtIp.getText();
		BAGLAN.kurDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.kurDizin.kOD = txtKodu.getText();
		BAGLAN.kurDizin.yER = "S";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());;
		sbilgi.setIns(comboBox.getSelectedItem().toString());;
		sbilgi.setKull(txtkul.getText()); ;
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr)); ;
		sbilgi.setServer(txtIp.getText()); ;
		sbilgi.setDizin_yeri("default");;
		sbilgi.setDizin("");;
		if (chckbxD.isSelected())
		{
			k_Access.kUR_SIFIR_S(sbilgi,lBILGI,BAGLAN_LOG.kurLogDizin);
		}
	}
	void kam_s_olustur() throws ClassNotFoundException, SQLException
	{
		String strAdmin = "";
		strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz..........", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
		cONN_AKTAR();
		lOGG_AKTAR("Kambiyo");
		mODUL_AKTAR("Kambiyo");
		KAMBIYO_ACCESS  ka_Access = new KAMBIYO_ACCESS(oac._IKambiyo,oac._IKambiyo_Loger);
		BAGLAN.kamDizin.kULLANICI = txtkul.getText();
		BAGLAN.kamDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.kamDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.kamDizin.sERVER = txtIp.getText();
		BAGLAN.kamDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.kamDizin.kOD = txtKodu.getText();
		BAGLAN.kamDizin.yER = "S";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());;
		sbilgi.setFir_adi(strAdmin);;
		sbilgi.setIns(comboBox.getSelectedItem().toString());;
		sbilgi.setKull(txtkul.getText()); ;
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr)); ;
		sbilgi.setServer(txtIp.getText()); ;
		sbilgi.setDizin_yeri("default");;
		sbilgi.setDizin("");;
		if (chckbxD.isSelected())
		{
			ka_Access.kAM_SIFIR_S(sbilgi,lBILGI,BAGLAN_LOG.kamLogDizin);
		}
	}
	void sms_s_olustur() throws ClassNotFoundException, SQLException
	{
		cONN_AKTAR();
		lOGG_AKTAR("Sms");
		mODUL_AKTAR("Sms");	
		SMS_ACCESS  sms_Access = new SMS_ACCESS(oac._ISms,oac._ISms_Loger);
		BAGLAN.smsDizin.kULLANICI = txtkul.getText();
		BAGLAN.smsDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.smsDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.smsDizin.sERVER = txtIp.getText();
		BAGLAN.smsDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.smsDizin.kOD = txtKodu.getText();
		BAGLAN.smsDizin.yER = "S";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());
		sbilgi.setIns(comboBox.getSelectedItem().toString());
		sbilgi.setKull(txtkul.getText()); 
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr)); 
		sbilgi.setServer(txtIp.getText()); 
		sbilgi.setDizin_yeri("default");
		sbilgi.setDizin("");;
		if (chckbxD.isSelected())
		{
			sms_Access.sMS_SIFIR_S(sbilgi,lBILGI,BAGLAN_LOG.smsLogDizin);
		}
	}
	void gun_s_olustur() throws ClassNotFoundException, SQLException
	{
		String strAdmin = "";
		strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz..........", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
		cONN_AKTAR();
		lOGG_AKTAR("Gunluk");
		mODUL_AKTAR("Gunluk");
		GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(oac._IGunluk,oac._IGunluk_Loger);
		BAGLAN.gunDizin.kULLANICI = txtkul.getText();
		BAGLAN.gunDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.gunDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.gunDizin.sERVER = txtIp.getText();
		BAGLAN.gunDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.gunDizin.kOD = txtKodu.getText();
		BAGLAN.gunDizin.yER = "S";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());;
		sbilgi.setFir_adi(strAdmin);;
		sbilgi.setIns(comboBox.getSelectedItem().toString());;
		sbilgi.setKull(txtkul.getText()); ;
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr)); ;
		sbilgi.setServer(txtIp.getText()); ;
		sbilgi.setDizin_yeri("default");;
		sbilgi.setDizin("");;
		if (chckbxD.isSelected())
		{
			g_Access.gUN_SIFIR_S(sbilgi, lBILGI,BAGLAN_LOG.gunLogDizin);
		}    
	}
	void ker_s_olustur() throws ClassNotFoundException, SQLException
	{
		String strAdmin = "";
		strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz..........", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
		cONN_AKTAR();
		lOGG_AKTAR("Kereste");
		mODUL_AKTAR("Kereste");
		KERESTE_ACCESS  ker_Access = new KERESTE_ACCESS(oac._IKereste,oac._IKereste_Loger);
		BAGLAN.kerDizin.kULLANICI = txtkul.getText();
		BAGLAN.kerDizin.sIFRESI = oac.sDONDUR.sDONDUR(txtsifr) ;
		BAGLAN.kerDizin.hAN_SQL = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) ;
		BAGLAN.kerDizin.sERVER = txtIp.getText();
		BAGLAN.kerDizin.iNSTANCE =comboBox.getSelectedItem().toString();
		BAGLAN.kerDizin.kOD = txtKodu.getText();
		BAGLAN.kerDizin.yER = "S";
		BAGLAN_LOG bLog = new BAGLAN_LOG();
		bLog.cONNECT();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());;
		sbilgi.setFir_adi(strAdmin);;
		sbilgi.setIns(comboBox.getSelectedItem().toString());;
		sbilgi.setKull(txtkul.getText()); ;
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtsifr)); ;
		sbilgi.setServer(txtIp.getText()); ;
		sbilgi.setDizin_yeri("default");;
		sbilgi.setDizin("");;
		if (chckbxD.isSelected())
		{
			ker_Access.kER_SIFIR_S(sbilgi, lBILGI,BAGLAN_LOG.kerLogDizin);
		}    
	}
	private  void dosya_olustur_S() throws IOException, ClassNotFoundException, SQLException
	{
		switch(activ_sayfa) {
		case 0:
			cari_s_olustur();
			break;
		case 1:
			stok_s_olustur();
			break;
		case 2:
			adr_s_olustur();
			break;
		case 3:
			kur_s_olustur();
			break;
		case 4:
			kam_s_olustur();
			break;
		case 5:
			sms_s_olustur();
			break;
		case 6:
			gun_s_olustur();
			break;
		case 7:
			ker_s_olustur();
			break;
		}
	}
	private void cONN_AKTAR()
	{
		String hangi = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex())  ;
		oac._IConn = hangi.equals("MS SQL") ? new OBS_ORTAK_MSSQL() : new OBS_ORTAK_MYSQL() ;
	}
	private void mODUL_AKTAR(String mODUL)
	{
		String hangi = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex())  ;
		if (hangi.equals("MS SQL"))
		{
			switch(mODUL) {
			case "Cari Hesap":
				oac._ICar = new CARI_HESAP_MSSQL();
				break;
			case "Stok":
				oac._IStok = new STOK_MSSQL();
				break;
			case "Adres":
				oac._IAdres = new ADRES_MSSQL();
				break;
			case "Kur":
				oac._IKur = new KUR_MSSQL();
				break;
			case "Kambiyo":
				oac._IKambiyo = new KAMBIYO_MSSQL();
				break;
			case "Gunluk":
				oac._IGunluk = new GUNLUK_MSSQL();
				break;
			case "Sms":
				oac._ISms = new SMS_MSSQL();
				break;
			case "Kereste":
				oac._IKereste = new KERESTE_MSSQL();
				break;
			}
		}
		else if (hangi.equals("MY SQL"))
		{
			switch(mODUL) {
			case "Cari Hesap":
				oac._ICar = new CARI_HESAP_MYSQL();
				break;
			case "Stok":
				oac._IStok = new STOK_MYSQL();
				break;
			case "Adres":
				oac._IAdres = new ADRES_MYSQL();
				break;
			case "Kur":
				oac._IKur = new KUR_MYSQL();
				break;
			case "Kambiyo":
				oac._IKambiyo = new KAMBIYO_MYSQL();
				break;
			case "Gunluk":
				oac._IGunluk = new GUNLUK_MYSQL();
				break;
			case "Sms":
				oac._ISms = new SMS_MYSQL();
				break;
			case "Kereste":
				oac._IKereste = new KERESTE_MYSQL();
				break;
			}
		}
	}
	private void lOGG_AKTAR(String mODUL)
	{
		String hangi = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex())  ;
		if (vt )
		{
			if (hangi == "MS SQL")
			{
				if (ds)
				{
					if(tx)
					{
						if(em)
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL()),new DOSYA_YAZ(new SQLITE_LOG()),new DOSYA_YAZ(new TXT_LOG()),new MAIL_AT()};
							lAktar(mODUL , ilogg);
						}
						else
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL()),new DOSYA_YAZ(new SQLITE_LOG()),new DOSYA_YAZ(new TXT_LOG())};
							lAktar(mODUL , ilogg);
						}
					}
					else
					{
						if(em)
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL()),new DOSYA_YAZ(new SQLITE_LOG()),new MAIL_AT()};
							lAktar(mODUL , ilogg);
						}
						else
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL()),new DOSYA_YAZ(new SQLITE_LOG())};
							lAktar(mODUL , ilogg);
						}
					}
				}
				else // DOSYA YOK 
				{
					if(tx)
					{
						if(em)
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL()),new DOSYA_YAZ(new TXT_LOG()),new MAIL_AT()};
							lAktar(mODUL , ilogg);
						}
						else
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL()),new DOSYA_YAZ(new TXT_LOG())};
							lAktar(mODUL , ilogg);
						}
					}
					else
					{
						if(em)
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL()),new MAIL_AT()};
							lAktar(mODUL , ilogg);
						}
						else
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MSSQL())};
							lAktar(mODUL , ilogg);
						}
					}
				}
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////
			else  // MYSQL
			{
				if (ds)
				{
					if(tx)
					{
						if(em)
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL()),new DOSYA_YAZ(new SQLITE_LOG()),new DOSYA_YAZ(new TXT_LOG()),new MAIL_AT()};
							lAktar(mODUL , ilogg);
						}
						else
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL()),new DOSYA_YAZ(new SQLITE_LOG()),new DOSYA_YAZ(new TXT_LOG())};
							lAktar(mODUL , ilogg);
						}
					}
					else
					{
						if(em)
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL()),new DOSYA_YAZ(new SQLITE_LOG()),new MAIL_AT()};
							lAktar(mODUL , ilogg);
						}
						else
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL()),new DOSYA_YAZ(new SQLITE_LOG())};
							lAktar(mODUL , ilogg);
						}
					}
				}
				else // DOSYA YOK 
				{
					if(tx)
					{
						if(em)
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL()),new DOSYA_YAZ(new TXT_LOG()),new MAIL_AT()};
							lAktar(mODUL , ilogg);
						}
						else
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL()),new DOSYA_YAZ(new TXT_LOG())};
							lAktar(mODUL , ilogg);
						}
					}
					else
					{
						if(em)
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL()),new MAIL_AT()};
							lAktar(mODUL , ilogg);
						}
						else
						{
							ILOGGER[] ilogg = {new DOSYA_YAZ(new DOSYA_MYSQL())};
							lAktar(mODUL , ilogg);
						}
					}
				}
			}
		}
		////////////////////////////////////////////////////////////////////////////////
		else // VT YOK
		{
			if (ds)
			{
				if(tx)
				{
					if(em)
					{
						ILOGGER[] ilogg = {new DOSYA_YAZ(new SQLITE_LOG()),new DOSYA_YAZ(new TXT_LOG()),new MAIL_AT()};
						lAktar(mODUL , ilogg);
					}
					else
					{
						ILOGGER[] ilogg = {new DOSYA_YAZ(new SQLITE_LOG()),new DOSYA_YAZ(new TXT_LOG())};
						lAktar(mODUL , ilogg);
					}
				}
				else
				{
					if(em)
					{
						ILOGGER[] ilogg = {new DOSYA_YAZ(new SQLITE_LOG()),new MAIL_AT()};
						lAktar(mODUL , ilogg);
					}
					else
					{
						ILOGGER[] ilogg = {new DOSYA_YAZ(new SQLITE_LOG())};
						lAktar(mODUL , ilogg);
					}
				}
			}
			else // DOSYA YOK 
			{
				if(tx)
				{
					if(em)
					{
						ILOGGER[] ilogg = {new DOSYA_YAZ(new TXT_LOG()),new MAIL_AT()};
						lAktar(mODUL , ilogg);
					}
					else
					{
						ILOGGER[] ilogg = {new DOSYA_YAZ(new TXT_LOG())};
						lAktar(mODUL , ilogg);
					}
				}
				else
				{
					if(em)
					{
						ILOGGER[] ilogg = {new MAIL_AT()};
						lAktar(mODUL , ilogg);
					}
					else
					{
						ILOGGER[] ilogg = {};
						lAktar(mODUL , ilogg);
					}
				}
			}
		}
	}
	private void lAktar(String mODUL , ILOGGER[] ilogg)
	{
		switch(mODUL) {
		case "Cari Hesap":
			oac._ICari_Loger = ilogg;
			break;
		case "Kur":
			oac._IKur_Loger = ilogg;
			break;
		case "Adres":
			oac._IAdres_Loger = ilogg;
			break;
		case "Fatura":
			oac._IFatura_Loger = ilogg;
			break;
		case "Sms":
			oac._ISms_Loger = ilogg;
			break;
		case "Gunluk":
			oac._IGunluk_Loger = ilogg;
			break;
		case "Kambiyo":
			oac._IKambiyo_Loger = ilogg;
			break;
		case "Kereste":
			oac._IKereste_Loger = ilogg;
			break;
		}
	}
	private void cIKIS()
	{
		try {
			contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			LOGIN frame = new LOGIN();
			frame.setVisible(true);
			dispose();
			contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void sIFRE_KAPA()
	{
		lblysif.setVisible(false);
		txtyenisif.setVisible(false);
		txtyenisif.setText("");
		txtsif.setText("");
		btnNewButton_2_1_1.setVisible(false);
	}
	
}

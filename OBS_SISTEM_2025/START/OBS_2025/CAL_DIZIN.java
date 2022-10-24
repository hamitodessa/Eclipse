package OBS_2025;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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
import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.CONNECT;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.GUNLUK_ACCESS;
import OBS_C_2025.KAMBIYO_ACCESS;
import OBS_C_2025.KUR_ACCESS;
import OBS_C_2025.OBS_ORTAK_MSSQL;
import OBS_C_2025.OBS_ORTAK_MYSQL;
import OBS_C_2025.SMS_ACCESS;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.USER_ISLEMLERI;
import net.proteanit.sql.DbUtils;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;


public class CAL_DIZIN extends JFrame {

	
	OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JTextField txtKodu;
	private static JTextField txtIp;
	private static JTextField txtkul;
	private static JTextField txtsifr;
	private static JTextField txtdiz;
	private static JLabel lblysif;
	private static JTextField txtyenisif;
		
	private static JComboBox<String> comboBox;
	private static JComboBox<String> cmbip;
	private static JComboBox<String> cmbhangisql;
	private static JComboBox<String> cmblog ;
	private static JButton btndizsec;
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
	private JPasswordField txtsif;
	
	private static	JTextField txt_Lmaill ;
	private static JComboBox<String>  cmb_maillist;
	
	private static JTabbedPane tabbedPane;
	private static int activ_sayfa =0;
	
	
	static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	private static JTextField txtcdid;
	private JLabel lblNewLabel_2;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGIN lgn = new LOGIN();
					lgn.setVisible(false);
					lgn.dispose();
					CAL_DIZIN frame = new CAL_DIZIN();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public CAL_DIZIN() {
		setTitle("CALISMA DIZINI");
		//setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CAL_DIZIN.class.getResource("/ICONLAR/obs_p.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				LOGIN frame;
				try {
					frame = new LOGIN();
					frame.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		}
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					contentPane.setCursor(WAIT_CURSOR);
					activ_sayfa =0;
					grid_doldur();
					doldur_kutu(tblCari,0);
				
					contentPane.setCursor(DEFAULT_CURSOR);
				} catch (ClassNotFoundException | SQLException e1) {
					contentPane.setCursor(DEFAULT_CURSOR);
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 930, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setBounds(590, 11, 316, 409);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtKodu = new JTextField();
		txtKodu.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtKodu.setBounds(102, 71, 157, 20);
		panel.add(txtKodu);
		txtKodu.setColumns(10);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(5, 5, 274, 27);
		panel.add(toolBar);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
		            if (txtKodu.getText().equals(""))
		                return;
		            if (comboBox.getSelectedItem().toString().equals(""))
		                return;
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
				
				 if (txtcdid.equals("")) return;
				 
				 int g = JOptionPane.showOptionDialog(null, "Kayit Silinecek ?" ,
						   "Calisma Dizini ", JOptionPane.YES_NO_OPTION,
						   JOptionPane.QUESTION_MESSAGE, null, 
						   new String[] {"Yes", "No"}, "No");
				 // 1 = No   0 = Yes
	         	  	if(g ==  1) {
	         	  	    return;
	         	  	}
					        contentPane.setCursor(WAIT_CURSOR);
					        try {
								oac.uSER_ISL.cd_sil(Integer.parseInt(txtcdid.getText()) );
								grid_doldur();
						        if (activ_sayfa == 0)						        {
						            doldur_kutu(tblCari, 0);}
						        else if (activ_sayfa == 1)
						            doldur_kutu(tblFatura, 0);
						        else if (activ_sayfa == 2)
						            doldur_kutu(tblAdres, 0) ;  
						        else if (activ_sayfa == 3)
						            doldur_kutu(tblKur, 0);
						        else if (activ_sayfa == 4)
						            doldur_kutu(tblKambiyo, 0);
						        else if (activ_sayfa == 5)
						            doldur_kutu(tblSms, 0);
						        else if (activ_sayfa == 6)
						            doldur_kutu(tblGunluk, 0);
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
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
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setToolTipText("Yeni");
		btnNewButton_3.setIcon(new ImageIcon(CAL_DIZIN.class.getResource("/ICONLAR/yeni.png")));
		toolBar.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	            try
	            {
	            	if (activ_sayfa == 7)
	            	{
	            	 
	            		String oku = new String(txtyenisif.getText());
						if (oku.equals(""))
						{
							txtsif.requestFocus();
			                return;
						}
		            contentPane.setCursor(WAIT_CURSOR);
		            oac.uSER_ISL.sifre_degis(GLOBAL.KULL_ADI, txtyenisif.getText());
		            txtyenisif.setText("");
		            txtsif.setText("");
		            txtyenisif.setVisible(false);
		            lblysif.setVisible(false); 
		            txtsif.requestFocus();
		      
	            	}
	            	else if (activ_sayfa == 8)
	            	{
	            		String oku = new String(txt_Lmaill .getText());
						if (oku.equals(""))
						{
							cmb_maillist.removeAllItems();
						     ResultSet	rs = null;
							oac.uSER_ISL.log_mail_kont_kayit(GLOBAL.KULL_ADI , oku);
							if (!rs.isBeforeFirst() ) {  
							    return;
							} 
							else
							{
								cmb_maillist.addItem("");
								 while (rs.next()) {
									 cmb_maillist.addItem(rs.getString("E_MAIL"));
							        }
							}
							///
						}
	            	}
	                contentPane.setCursor(DEFAULT_CURSOR);
	            }
	            catch (Exception ex)
	            {
	            contentPane.setCursor(DEFAULT_CURSOR);
	            txtsif.requestFocus();		
}				}
			
		});
		btnNewButton_4.setToolTipText("Kaydet");
		btnNewButton_4.setIcon(new ImageIcon(CAL_DIZIN.class.getResource("/ICONLAR/save.png")));
		toolBar.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setToolTipText("Kapat");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			//	LOGIN frame;
				try {
					LOGIN frame = new LOGIN();
					frame.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_5.setIcon(new ImageIcon(CAL_DIZIN.class.getResource("/ICONLAR/exit.png")));
		toolBar.add(btnNewButton_5);
		
		JLabel lblNewLabel_1 = new JLabel("Kodu");
		lblNewLabel_1.setBounds(24, 74, 68, 14);
		
		panel.add(lblNewLabel_1);
		chckbxL = new JCheckBox("Lokal");
		chckbxL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxL.isSelected())
				{
					chckbxS.setSelected(false);
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
				}
			}
		});
		chckbxS.setBounds(181, 95, 78, 23);
		panel.add(chckbxS);
		
		txtIp = new JTextField();
		txtIp.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtIp.setBounds(102, 199, 157, 20);
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
		cmbip.setBounds(102, 230, 157, 22);
		panel.add(cmbip);
		
		txtkul = new JTextField();
		txtkul.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtkul.setBounds(102, 263, 157, 20);
		panel.add(txtkul);
		txtkul.setColumns(10);
		
		txtsifr = new JPasswordField();
		txtsifr.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtsifr.setBounds(102, 294, 157, 20);
	
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
		chckbxD.setBounds(51, 318, 97, 23);
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
		chckbxO.setBounds(162, 318, 97, 23);
		panel.add(chckbxO);
		txtdiz = new JTextField();
		txtdiz.setEnabled(false);
		txtdiz.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtdiz.setBounds(51, 348,255,20);
		panel.add(txtdiz);
		btndizsec = new JButton("Surucu Sec");
		btndizsec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(WAIT_CURSOR);
				//CAL_DIZIN cdz;
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
		
		btndizsec.setBounds(51, 375, 107, 23);
		panel.add(btndizsec);
		
		JLabel lblInstance = new JLabel("Instance");
		lblInstance.setBounds(24, 170, 68, 14);
		panel.add(lblInstance);
		
		JLabel lblServer = new JLabel("Server");
		lblServer.setBounds(24, 202, 68, 14);
		panel.add(lblServer);
		
		JLabel lblKullanici = new JLabel("Kullanici");
		lblKullanici.setBounds(24, 266, 68, 14);
		panel.add(lblKullanici);
		
		JLabel lblSifre = new JLabel("Sifre");
		lblSifre.setBounds(24, 297, 68, 14);
		panel.add(lblSifre);
		
		JLabel lblUser = new JLabel("....");
		lblUser.setForeground(new Color(128, 0, 0));
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUser.setBounds(10, 384, 46, 14);
		panel.add(lblUser);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				activ_sayfa =tabbedPane.getSelectedIndex();
				try {
					contentPane.setCursor(WAIT_CURSOR);
					grid_doldur();
					if (activ_sayfa == 0)
					{
						doldur_kutu(tblCari,0);	
					}
					else if (activ_sayfa == 1)
					{
						doldur_kutu(tblFatura,0);	
					}
					else if (activ_sayfa == 2)
					{
						doldur_kutu(tblAdres,0);	
					}
					else if (activ_sayfa == 3)
					{
						doldur_kutu(tblKur,0);	
					}
					else if (activ_sayfa == 4)
					{
						doldur_kutu(tblKambiyo,0);	
					}
					else if (activ_sayfa == 5)
					{
						doldur_kutu(tblSms,0);	
					}
					else if (activ_sayfa == 6)
					{
						doldur_kutu(tblGunluk,0);	
					}
					contentPane.setCursor(DEFAULT_CURSOR);

				} catch (ClassNotFoundException | SQLException e1) {
					contentPane.setCursor(DEFAULT_CURSOR);
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
		tabbedPane.setBounds(0, 0, 574, 430);
		tabbedPane.setForeground(new Color(25, 25, 112));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(UIManager.getBorder("ToolBar.border"));
		tabbedPane.setBorder(new LineBorder(new Color(0, 191, 255)));
		
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		tabbedPane.addTab("Cari Hesap", null, scrollPane, null);
		tblCari = new JTable() {
	        private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {     return false;          }};
			tblCari.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.setCursor(WAIT_CURSOR);
				try {
					kutu_temizle();
					doldur_kutu(tblCari,tblCari.getSelectedRow());
					contentPane.setCursor(DEFAULT_CURSOR);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				contentPane.setCursor(DEFAULT_CURSOR);
			}
		});
		tblCari.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCari.setFont(new Font("Tahoma", Font.BOLD, 14));
		tblCari.setRowHeight(22);
		tblCari.setBounds(224, 188, 114, 173);
		tablo_baslik(tblCari);
		
		scrollPane.setViewportView(tblCari);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Fatura", null, scrollPane_1, null);
		
		tblFatura = new JTable(){
	        private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {     return false;          }};
		tblFatura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.setCursor(WAIT_CURSOR);
				try {
					kutu_temizle();
					doldur_kutu(tblFatura,tblFatura.getSelectedRow());
					contentPane.setCursor(DEFAULT_CURSOR);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				contentPane.setCursor(DEFAULT_CURSOR);
			}
		});
		tblFatura.setRowHeight(22);
		tblFatura.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblFatura.setFont(new Font("Tahoma", Font.BOLD, 14));
		tablo_baslik(tblFatura);
		scrollPane_1.setViewportView(tblFatura);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("Adres", null, scrollPane_2, null);
		tblAdres = new JTable(){
	        private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {     return false;          }};
		tblAdres.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.setCursor(WAIT_CURSOR);
				try {
					kutu_temizle();
					doldur_kutu(tblAdres,tblAdres.getSelectedRow());
					contentPane.setCursor(DEFAULT_CURSOR);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				contentPane.setCursor(DEFAULT_CURSOR);
			}
		});
		tblAdres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblAdres.setFont(new Font("Tahoma", Font.BOLD, 14));
		tblAdres.setRowHeight(22);
		tablo_baslik(tblAdres);
		scrollPane_2.setViewportView(tblAdres);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane.addTab("Kur", null, scrollPane_3, null);
		
		tblKur = new JTable(){
	        private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {     return false;          }};
		tblKur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.setCursor(WAIT_CURSOR);
				try {
					kutu_temizle();
					doldur_kutu(tblKur,tblKur.getSelectedRow());
				
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				contentPane.setCursor(DEFAULT_CURSOR);
			}
		});
		tblKur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblKur.setFont(new Font("Tahoma", Font.BOLD, 14));
		tblKur.setRowHeight(22);
		tablo_baslik(tblKur);
		scrollPane_3.setViewportView(tblKur);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		tabbedPane.addTab("Kambiyo", null, scrollPane_4, null);
		
		tblKambiyo = new JTable(){
	        private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {     return false;          }};
		tblKambiyo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.setCursor(WAIT_CURSOR);
				try {
					kutu_temizle();
					doldur_kutu(tblKambiyo,tblKambiyo.getSelectedRow());
					contentPane.setCursor(DEFAULT_CURSOR);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				contentPane.setCursor(DEFAULT_CURSOR);
			}
		});
		tblKambiyo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblKambiyo.setFont(new Font("Tahoma", Font.BOLD, 14));
		tblKambiyo.setRowHeight(22);
		tablo_baslik(tblKambiyo);
		scrollPane_4.setViewportView(tblKambiyo);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		tabbedPane.addTab("Sms", null, scrollPane_5, null);
		
		tblSms = new JTable(){
	        private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {     return false;          }};
		tblSms.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.setCursor(WAIT_CURSOR);
				try {
					kutu_temizle();
					doldur_kutu(tblSms,tblSms.getSelectedRow());
					contentPane.setCursor(DEFAULT_CURSOR);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				contentPane.setCursor(DEFAULT_CURSOR);
			}
		});
		tblSms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblSms.setFont(new Font("Tahoma", Font.BOLD, 14));
		tblSms.setRowHeight(22);
		tablo_baslik(tblSms);
		scrollPane_5.setViewportView(tblSms);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		tabbedPane.addTab("Gunluk", null, scrollPane_6, null);
		
		tblGunluk = new JTable(){
	        private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {     return false;          }};
		tblGunluk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentPane.setCursor(WAIT_CURSOR);
				try {
					kutu_temizle();
					doldur_kutu(tblGunluk,tblGunluk.getSelectedRow());
					contentPane.setCursor(DEFAULT_CURSOR);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				contentPane.setCursor(DEFAULT_CURSOR);
			}
		});
		tblGunluk.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblGunluk.setFont(new Font("Tahoma", Font.BOLD, 14));
		tblGunluk.setRowHeight(22);
		tablo_baslik(tblGunluk);
		scrollPane_6.setViewportView(tblGunluk);
		
		JScrollPane scrollPane_7 = new JScrollPane();
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
							String passText = new String(txtsif.getPassword());
							String encodedString = Base64.getEncoder().encodeToString(passText.getBytes());
							varmi = oac.uSER_ISL.user_var(GLOBAL.KULL_ADI,encodedString);
							if (varmi == true)
				            {
								lblysif.setVisible(true);
						        txtyenisif.setVisible(true);
				                contentPane.setCursor(DEFAULT_CURSOR);
				            }
				            else
				            {
				                JOptionPane.showMessageDialog(null, "Sifre Yanlis", "Sifre DEgistirme", JOptionPane.PLAIN_MESSAGE);
				            	lblysif.setVisible(false);
				                txtyenisif.setVisible(false);
				                contentPane.setCursor(DEFAULT_CURSOR);
				            }
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
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
		
		tabbedPane.setEnabledAt(7, true);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		tabbedPane.addTab("Loglama", null, scrollPane_8, null);
		
		JPanel panel_2 = new JPanel();
		scrollPane_8.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "E Mail Loglama", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 99, 177)));
		panel_3.setBounds(10, 42, 545, 111);
		panel_3.setLayout(null);
		panel_2.add(panel_3);
		
		lblNewLabel_2 = new JLabel("E Mail Adresi");
		lblNewLabel_2.setBounds(10, 39, 78, 14);
		panel_3.add(lblNewLabel_2);
		
		txt_Lmaill = new JTextField();
		txt_Lmaill.setBounds(98, 36, 307, 20);
		panel_3.add(textField);
		txt_Lmaill.setColumns(10);
		
		cmb_maillist = new JComboBox<String>();
		cmb_maillist.setBounds(98, 67, 307, 22);
		panel_3.add(cmb_maillist);
	
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//**************
		lblUser.setText(GLOBAL.KULL_ADI);
		comboBox = new JComboBox<String>();
		comboBox.setEditable(true);
		comboBox.setBounds(102, 169, 157, 22);
		panel.add(comboBox);
		
		txtcdid = new JTextField();
		txtcdid.setBounds(34, 99, 38, 20);
		txtcdid.setVisible(false);
		panel.add(txtcdid);
		txtcdid.setColumns(10);
		
		cmbhangisql = new JComboBox<String>();
		cmbhangisql.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String hangi = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex())  ;
				if (hangi == "MS SQL")
						{
					comboBox.setEnabled(true);
						}
				else
				{
					comboBox.setEnabled(false);
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
				if (chckbxL_1.isSelected())
				{
					cmblog.setEnabled(true);
				}
				else
				{
					cmblog.setEnabled(false);
				}
			}
		});
		chckbxL_1.setBounds(102, 126, 30, 23);
		panel.add(chckbxL_1);
		
		cmblog = new JComboBox<String>();
		cmblog.setModel(new DefaultComboBoxModel(new String[] {"Dosyaya Kayit", "Email Atma", "Hepsi"}));
		cmblog.setEnabled(false);
		cmblog.setBounds(133, 130, 126, 22);
		panel.add(cmblog);
		
		
	}
	
	private   void grid_doldur() throws ClassNotFoundException, SQLException
	{
		
		kutu_temizle();
		if ( activ_sayfa == 0)
			{
		
			GRID_TEMIZLE.grid_temizle(tblCari);
		
			grid_ortak_doldur(tblCari, "Cari Hesap");
       		}
		else if ( activ_sayfa == 1)
			{
			GRID_TEMIZLE.grid_temizle(tblFatura);
			grid_ortak_doldur(tblFatura, "Fatura");
			}
		else if ( activ_sayfa == 2)
		   {
			GRID_TEMIZLE.grid_temizle(tblAdres);
	       grid_ortak_doldur(tblAdres, "Adres");
	       }
		else if ( activ_sayfa == 3)
		   {
			GRID_TEMIZLE.grid_temizle(tblKur);
	       grid_ortak_doldur(tblKur, "Kur");
	       }
		else if ( activ_sayfa == 4)
		   {
			GRID_TEMIZLE.grid_temizle(tblKambiyo);
	       grid_ortak_doldur(tblKambiyo, "Kambiyo");
	       }
		else if ( activ_sayfa == 5)
		   {
			GRID_TEMIZLE.grid_temizle(tblSms);
	       grid_ortak_doldur(tblSms, "Sms");
	       }
		else if ( activ_sayfa == 6)
		   {
			GRID_TEMIZLE.grid_temizle(tblGunluk);
	       grid_ortak_doldur(tblGunluk, "Gunluk");
	       }
	
	}
	private   void grid_ortak_doldur( JTable grd,String prg) throws ClassNotFoundException, SQLException 
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
			grd.removeColumn(grd.getColumnModel().getColumn(4));
			grd.removeColumn(grd.getColumnModel().getColumn(4));
			grd.removeColumn(grd.getColumnModel().getColumn(4));

			JTableHeader th = grd.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc = tcm.getColumn(0);
			tc.setHeaderValue( "Kodu" );
			tc.setHeaderRenderer(new SOLA());
			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setHeaderValue( "Ip" );
			tc.setMinWidth(200);
			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setHeaderValue( "Modul" );
			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setHeaderValue( "Yer" );
			th.repaint();
			grd.setRowSelectionInterval(0, 0);
			grd.setSelectionBackground(Color.PINK);
			grd.setSelectionForeground(Color.BLUE);
	
	}
	public static void HeaderRenderer(JTable table) {
		DefaultTableCellRenderer renderer;
        renderer = (DefaultTableCellRenderer)
        table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.LEFT);
    }
	private static  void doldur_kutu( JTable grd,int satir) throws ClassNotFoundException, SQLException 
	{
		if (grd.getRowCount()== 0 ) {  
			kutu_temizle();
		    return;
		} 
		txtKodu.setText(grd.getModel().getValueAt(satir, 1).toString());
		txtIp.setText(grd.getModel().getValueAt(satir, 6).toString());
		txtkul.setText(grd.getModel().getValueAt(satir, 3).toString());
		txtsifr.setText(grd.getModel().getValueAt(satir, 4).toString());
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
			cmblog.setEnabled(true);
		}
		else
		{
			chckbxL_1.setSelected(false);
			cmblog.setEnabled(false);
		}
		 cmblog.setSelectedItem(grd.getModel().getValueAt(satir, 15).toString());
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
		
		ip_doldur();
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
			cmblog.setSelectedItem("Dosyaya Kayit");
			cmbhangisql.setSelectedItem("MS SQL");
			ip_doldur();
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
			 while (rs.next()) {
				 cmbip.addItem(rs.getString("IP"));
		        }
		}
}
	private  void server_control() throws HeadlessException, ClassNotFoundException
		{
			 contentPane.setCursor(WAIT_CURSOR);
			 String hangi = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex())  ;
				if (hangi == "MS SQL")
						{
					oac._IConn = new OBS_ORTAK_MSSQL();
						}
				else
				{
					oac._IConn = new OBS_ORTAK_MYSQL();
				}
			 			 
			 CONNECT s_CONN = new CONNECT(oac._IConn);
             if (chckbxL.isSelected() )
                {
                	contentPane.setCursor(WAIT_CURSOR);
                	   
                   if ( s_CONN.Server_kontrol_L(comboBox.getSelectedItem().toString(),txtkul.getText(), txtsifr.getText()) == true  )
                    {
                	
                	 contentPane.setCursor(DEFAULT_CURSOR);
                     JOptionPane.showMessageDialog(null, "Baglanti Saglandi........".toString(), "Server Baglanti", JOptionPane.PLAIN_MESSAGE);
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
                    if (s_CONN.Server_kontrol_S(txtIp.getText(),comboBox.getSelectedItem().toString(),txtkul.getText(), txtsifr.getText() ) == true)
                    {
                    	contentPane.setCursor(DEFAULT_CURSOR);
                    	 JOptionPane.showMessageDialog(null, "Baglanti Saglandi........".toString(), "Server Baglanti", JOptionPane.PLAIN_MESSAGE);
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
		 CONNECT s_CONN = new CONNECT(oac._IConn);
         String program = "";
          if (activ_sayfa == 0)
             program = "OK_Car" + txtKodu.getText();
         else if (activ_sayfa == 1)
             program = "OK_Fat" + txtKodu.getText();
         else if (activ_sayfa == 2)
             program = "OK_Adr" + txtKodu.getText();
         else if (activ_sayfa == 3)
             program = "OK_Kur" + txtKodu.getText();
         else if (activ_sayfa == 4)
             program = "OK_Kam" + txtKodu.getText();
         else if (activ_sayfa == 5)
             program = "OK_Sms" + txtKodu.getText();
         else if (activ_sayfa == 6)
             program = "OK_Gun" + txtKodu.getText();
         if (chckbxL.isSelected())
         {
  
            if ( s_CONN.Dosya_kontrol_L(program, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText()) == true)
             {
     
                 if (activ_sayfa == 0)
                     program = "Cari Hesap";
                 else if (activ_sayfa == 1)
                     program = "Fatura";
                 else if (activ_sayfa == 2)
                     program = "Adres";
                 else if (activ_sayfa == 3)
                     program = "Kur";
                 else if (activ_sayfa == 4)
                     program = "Kambiyo";
                 else if (activ_sayfa == 5)
                     program = "Sms";
                 else if (activ_sayfa == 6)
                     program = "Gunluk";
                 boolean izinli = true;
                 if ( !GLOBAL.KULL_ADI.equals("Admin") )
                 		{
                	 ResultSet	rs = null;
                	 rs = oac.uSER_ISL.user_details_izinleri(GLOBAL.KULL_ADI, program, "YER = 'L'");
                	 if (!rs.isBeforeFirst() ) {  
                		 izinli = false;
                	 	} 
                 }
               
                 
                 if (izinli == false)
                 {
                	 contentPane.setCursor(DEFAULT_CURSOR);
                      JOptionPane.showMessageDialog(null, "Bu Dosyayi Kullanmaya Izniniz Yok" + System.lineSeparator()  + System.lineSeparator()+"'Admin' den izin verilmesi gereklidir...", "Dosya Olusturma", JOptionPane.ERROR_MESSAGE);
                     btnNewButton_1.setEnabled(false);
                     txtKodu.requestFocus();
                     return;
                 }
                
                 mdb_yaz();
                 grid_doldur();
                 if (activ_sayfa == 0)
                	 doldur_kutu(tblCari, 0);
                 else if (activ_sayfa == 1)
                	 doldur_kutu(tblFatura, 0);
                 else if (activ_sayfa == 2)
                	 doldur_kutu(tblAdres, 0);
                 else if (activ_sayfa == 3)
                	 doldur_kutu(tblKur, 0);
                 else if (activ_sayfa == 4)
                	 doldur_kutu(tblKambiyo, 0);
                 else if (activ_sayfa == 5)
                	 doldur_kutu(tblSms, 0);
                 else if (activ_sayfa == 6)
                	 doldur_kutu(tblGunluk, 0);
                 contentPane.setCursor(DEFAULT_CURSOR);
                 JOptionPane.showMessageDialog(null, "Veritabani Baglantisi gerceklestirildi", "Dosya Baglanti", JOptionPane.PLAIN_MESSAGE);
                 return;
             }
             else
             {
            	 contentPane.setCursor(DEFAULT_CURSOR);
     		   	 int g =  JOptionPane.showOptionDialog( null,  "Yeni Dosya Olusturulsunmu.?", "Dosya Olusturma",   JOptionPane.YES_NO_OPTION,
     		   			 	JOptionPane.QUESTION_MESSAGE,
     		   			 	null,     //no custom icon
     		   			 	oac.options,  //button titles
     		   			 	oac.options[1]); //default button
     		 	 if(g != 0 ) { return;	}
            	  	Thread.yield();
                     dosya_olustur_L();
                     mdb_yaz();
                     grid_doldur();
                     if (activ_sayfa == 0)
                    	 doldur_kutu(tblCari, 0);
                     else if (activ_sayfa == 1)
                    	 doldur_kutu(tblFatura, 0);
                     else if (activ_sayfa == 2)
                    	 doldur_kutu(tblAdres, 0);
                     else if (activ_sayfa == 3)
                    	 doldur_kutu(tblKur, 0);
                     else if (activ_sayfa == 4)
                    	 doldur_kutu(tblKambiyo, 0);
                     else if (activ_sayfa == 5)
                    	 doldur_kutu(tblSms, 0);
                     else if (activ_sayfa == 6)
                    	 doldur_kutu(tblGunluk, 0);
                     contentPane.setCursor(DEFAULT_CURSOR);
                     JOptionPane.showMessageDialog(null,  "Dosya Olusturuldu ...", "Dosya Olusturma", JOptionPane.PLAIN_MESSAGE);
                     return;
             }
         }
         else  // Server
         {
        
             if (	 s_CONN.Dosya_kontrol_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(),txtsifr.getText(), program) ==true)
             {
     
                 if (activ_sayfa == 0)
                     program = "Cari Hesap";
                 else if (activ_sayfa == 1)
                     program = "Fatura";
                 else if (activ_sayfa == 2)
                     program = "Adres";
                 else if (activ_sayfa == 3)
                     program = "Kur";
                 else if (activ_sayfa == 4)
                     program = "Kambiyo";
                 else if (activ_sayfa == 5)
                     program = "Sms";
                 else if (activ_sayfa == 6)
                     program = "Gunluk";
                 boolean izinli = true;
                 if ( !GLOBAL.KULL_ADI.equals("Admin") )
                 {
                	 ResultSet rs = oac.uSER_ISL.user_details_izinleri(GLOBAL.KULL_ADI, program, "YER = 'S'");
                	 if (!rs.isBeforeFirst() ) {  
                		 izinli = false;
                	 } 
                 }
                if (izinli == false)
                 {
                	 contentPane.setCursor(DEFAULT_CURSOR);
                	 JOptionPane.showMessageDialog(null, "Bu Dosyayi Kullanmaya Izniniz Yok" + System.lineSeparator()  + System.lineSeparator()+"'Admin' den izin verilmesi gereklidir...", "Dosya Olusturma", JOptionPane.ERROR_MESSAGE);
                     btnNewButton_1.setEnabled(false);
                     txtKodu.requestFocus();
                     return;
                 }
                 mdb_yaz();
                 grid_doldur();
                 if (activ_sayfa == 0)
                	 doldur_kutu(tblCari, 0);
                 else if (activ_sayfa == 1)
                	 doldur_kutu(tblFatura, 0);
                 else if (activ_sayfa == 2)
                	 doldur_kutu(tblAdres, 0);
                 else if (activ_sayfa == 3)
                	 doldur_kutu(tblKur, 0);
                 else if (activ_sayfa == 4)
                	 doldur_kutu(tblKambiyo, 0);
                 else if (activ_sayfa == 5)
                	 doldur_kutu(tblSms, 0);
                 else if (activ_sayfa == 6)
                	 doldur_kutu(tblGunluk, 0);
                 contentPane.setCursor(DEFAULT_CURSOR);
                 JOptionPane.showMessageDialog(null, "Veritabani Baglantisi gerceklestirildi", "Dosya Baglanti", JOptionPane.PLAIN_MESSAGE);
                 return;
             }
             else
             {
            	 contentPane.setCursor(DEFAULT_CURSOR);
            	     		   	 int g =  JOptionPane.showOptionDialog( null,  "Yeni Dosya Olusturulsunmu.?", "Dosya Olusturma",   JOptionPane.YES_NO_OPTION,
      							JOptionPane.QUESTION_MESSAGE,
      			   				null,     //no custom icon
      			   				oac.options,  //button titles
      			   				oac.options[1]); //default button
      		   if(g != 0 ) { return;	}
                 {
                	 contentPane.setCursor(WAIT_CURSOR);
                	 Thread.yield();
                     dosya_olustur_S();
                     mdb_yaz();
                     grid_doldur();
                     if (activ_sayfa == 0)
                    	 doldur_kutu(tblCari, 0);
                     else if (activ_sayfa == 1)
                    	 doldur_kutu(tblFatura, 0);
                     else if (activ_sayfa == 2)
                    	 doldur_kutu(tblAdres, 0);
                     else if (activ_sayfa == 3)
                    	 doldur_kutu(tblKur, 0);
                     else if (activ_sayfa == 4)
                    	 doldur_kutu(tblKambiyo, 0);
                     else if (activ_sayfa == 5)
                    	 doldur_kutu(tblSms, 0);
                     else if (activ_sayfa == 6)
                    	 doldur_kutu(tblGunluk, 0);
                     contentPane.setCursor(DEFAULT_CURSOR);
                      JOptionPane.showMessageDialog(null,  "Dosya Olusturuldu ...", "Dosya Olusturma", JOptionPane.PLAIN_MESSAGE);
                     return;
                 }
             }
         }
	}
	private  void mdb_yaz() throws ClassNotFoundException, SQLException
{
	if (activ_sayfa == 0)
    {
		mdb_yaz_2("Cari Hesap");    }
    else if (activ_sayfa == 1)
    {
    	mdb_yaz_2("Fatura");    }
    else if (activ_sayfa == 2)
    {
    	mdb_yaz_2("Adres");    }
    else if (activ_sayfa == 3)
    {
    	mdb_yaz_2("Kur");
    	}
     else if (activ_sayfa == 4)
    {
    	 mdb_yaz_2("Kambiyo");    }
    else if (activ_sayfa == 5)
    {
    	mdb_yaz_2("Sms");    }
    else if (activ_sayfa == 6)
    {
    	mdb_yaz_2("Gunluk");
     }

    oac.uSER_ISL.ip_dos_kont(txtIp.getText());
}
	private void mdb_yaz_2(String modul) throws ClassNotFoundException, SQLException
	{
		 
		 oac.uSER_ISL.calisanmi_degis(GLOBAL.KULL_ADI, modul,chckbxL.isSelected() ? "L" : "S"); // CaLISANMI DOSYA KONTROLU
	     oac.uSER_ISL.details_yaz(txtKodu.getText(),GLOBAL.KULL_ADI, txtkul.getText(), txtsifr.getText(), comboBox.getSelectedItem().toString(), txtIp.getText(), modul,txtdiz.getText(), chckbxL.isSelected() ? "L" : "S", chckbxD.isSelected() ? "D" : "O", "E", "E",cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()),  txtcdid.getText(),chckbxL_1.isSelected() ? 1 : 0, cmblog.getItemAt(cmblog.getSelectedIndex()));

	}
	private  void dosya_olustur_L() throws IOException, ClassNotFoundException, SQLException
{
	 if (activ_sayfa == 0)
     {
		 String strAdmin = "";
         strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
         contentPane.setCursor(WAIT_CURSOR);
         CARI_ACCESS  c_Access = new CARI_ACCESS(oac._ICar,oac._ILogger);
         if (chckbxD.isSelected())
         	{
             	c_Access.cari_sifirdan_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 }
        	 else
        	 {
        		  c_Access.cari_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 }
     }
     else if (activ_sayfa == 1)
     {
    	 String strAdmin = "";
         strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
         contentPane.setCursor(WAIT_CURSOR);
         STOK_ACCESS  s_Access = new STOK_ACCESS(oac._Istok);
         if (chckbxD.isSelected())
         {
        	 s_Access.fAT_SIFIR_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
         }
        	else
        	{
        		 s_Access.fAT_SIFIR_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	}
     }
     else if (activ_sayfa == 2)
     {
    	 String strAdmin = "";
         strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
        contentPane.setCursor(WAIT_CURSOR);
        ADRES_ACCESS  a_Access = new ADRES_ACCESS(oac._IAdres);
         if (chckbxD.isSelected())
         {
        	 a_Access.aDR_SIF_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
         }
        	 else
        	{
        		 a_Access.aDR_SIF_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
         	}
     }
     else if (activ_sayfa == 3)
     {
    	 contentPane.setCursor(WAIT_CURSOR);
    	 KUR_ACCESS  k_Access = new KUR_ACCESS(oac._IKur);
         if (chckbxD.isSelected())
          	 {
        		 k_Access.kUR_SIFIR_L(txtKodu.getText(), "default", "", comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 }
        	  	 else
         	{
        	  		 k_Access.kUR_SIFIR_L(txtKodu.getText(), "", txtdiz.getText(), comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
            	}
     }
     else if (activ_sayfa == 4)
     {
    	 String strAdmin = "";
         strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
         contentPane.setCursor(WAIT_CURSOR);
         KAMBIYO_ACCESS  ka_Access = new KAMBIYO_ACCESS(oac._IKambiyo);
         if (chckbxD.isSelected())
         {
        	 ka_Access.kAM_SIFIR_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
         }  
         else
        	 {
        	 ka_Access.kAM_SIFIR_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
     
        	 }
     }
     else if (activ_sayfa == 5)
     {
    	 contentPane.setCursor(WAIT_CURSOR);
    	  SMS_ACCESS  sms_Access = new SMS_ACCESS(oac._ISms);
         if (chckbxD.isSelected())
         {
        	 sms_Access.sMS_SIFIR_L(txtKodu.getText(), "default", "", comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
         }
        	 else
        	 {
       		 sms_Access.sMS_SIFIR_L(txtKodu.getText(), "", txtdiz.getText(), comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
      
        	 }
     }
     else if (activ_sayfa == 6)
     {
    	 String strAdmin = "";
         strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
         contentPane.setCursor(WAIT_CURSOR);
         GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(oac._IGunluk);
         if (chckbxD.isSelected())
         {
        	 g_Access.gUN_SIFIR_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
         }
        	 else
        	 {
        	 g_Access.gUN_SIFIR_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());

        	 }
     }
 }
	private  void dosya_olustur_S() throws IOException, ClassNotFoundException, SQLException
{
	if (activ_sayfa == 0)
    {
		String strAdmin = "";
        strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
        CARI_ACCESS  c_Access = new CARI_ACCESS(oac._ICar,oac._ILogger);
        if (chckbxD.isSelected())
        	{
        		c_Access.cARI_SIFIR_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText(),  strAdmin);
          }
    }
    else if (activ_sayfa == 1)
    {
    	String strAdmin = "";
        strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
        STOK_ACCESS  s_Access = new STOK_ACCESS(oac._Istok);
        if (chckbxD.isSelected())
        	
        	{
        		s_Access.fAT_SIFIR_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText(),  strAdmin);
        
        	}
    }
    else if (activ_sayfa == 2)
    {
    	String strAdmin = "";
        strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
        ADRES_ACCESS  a_Access = new ADRES_ACCESS(oac._IAdres);
        if (chckbxD.isSelected())
    	{
    		a_Access.aDR_SIFIR_S( txtIp.getText(), comboBox.getSelectedItem().toString(),txtkul.getText(), txtsifr.getText(), txtKodu.getText(),  strAdmin);
    
    	}
          
    }
    else if (activ_sayfa == 3)
    {
    	
    	 KUR_ACCESS  k_Access = new KUR_ACCESS(oac._IKur);
        if (chckbxD.isSelected())
        {
      		k_Access.kUR_SIFIR_S( txtIp.getText(),  comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText());
      	  
        	  	}
    }
    
    else if (activ_sayfa == 4)
    {
    	String strAdmin = "";
        strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
        KAMBIYO_ACCESS  ka_Access = new KAMBIYO_ACCESS(oac._IKambiyo);
        if (chckbxD.isSelected())
        {
        	ka_Access.kAM_SIFIR_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText(),  strAdmin);
        	}
    }
    else if (activ_sayfa == 5)
    {
    	
    	  SMS_ACCESS  sms_Access = new SMS_ACCESS(oac._ISms);
        if (chckbxD.isSelected())
        {
        	sms_Access.sMS_SIFIR_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText());
    	
        	}
    }
    
    else if (activ_sayfa == 6)
    {
    	String strAdmin = "";
        strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
        GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(oac._IGunluk);
        if (chckbxD.isSelected())
        {
        	g_Access.gUN_SIFIR_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText(), "default", "", strAdmin);
        }    
        }
}
}
	class ComboItem
{
    private String key;
    private String value;
    public ComboItem(String key, String value)
    {
        this.key = key;
        this.value = value;
    }
    @Override
    public String toString()
    {
        return key;
    }
    public String getKey()
    {
        return key;
    }
    public String getValue()
    {
        return value;
    }
}
	
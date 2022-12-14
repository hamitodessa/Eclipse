package OBS_PACKAGE;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;

import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;


public class CAL_DIZIN extends JFrame {

	/**
	 * 
	 */
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	
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
	private static JButton btndizsec;
	private static JButton btnNewButton_1;
	private static JCheckBox chckbxL ;
	private static JCheckBox chckbxS ;
	private static JCheckBox chckbxO ;
	private static JCheckBox chckbxD ;
	
	
	private static JTable tblCari;
	
	private static JTable tblFatura;
	private static JTable tblAdres;
	private static JTable tblKur;
	private static JTable tblKambiyo;
	private static JTable tblSms;
	private static JTable tblGunluk;
	private JTextField txtsif;
	
	private static JTabbedPane tabbedPane;
	private static int activ_sayfa =0;
	
	
	static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	private static JTextField txtcdid;

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
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
								oac.glb.cd_sil(Integer.parseInt(txtcdid.getText()) );
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
	            	String oku = new String(txtyenisif.getText());
					if (oku.equals(""))
					{
						txtsif.requestFocus();
		                return;
					}
	            contentPane.setCursor(WAIT_CURSOR);
	            oac.glb.sifre_degis(GLOBAL.KULL_ADI, txtyenisif.getText());
	            txtyenisif.setText("");
	            txtsif.setText("");
	            txtyenisif.setVisible(false);
	            lblysif.setVisible(false); 
	            txtsif.requestFocus();
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
		txtIp.setBounds(102, 174, 157, 20);
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
		txtkul.setBounds(102, 238, 157, 20);
		panel.add(txtkul);
		txtkul.setColumns(10);
		
		txtsifr = new JTextField();
		txtsifr.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtsifr.setBounds(102, 269, 157, 20);
	
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
		chckbxD.setBounds(51, 293, 97, 23);
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
		chckbxO.setBounds(162, 293, 97, 23);
		panel.add(chckbxO);
		txtdiz = new JTextField();
		txtdiz.setEnabled(false);
		txtdiz.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtdiz.setBounds(51, 323,255,20);
		panel.add(txtdiz);
		btndizsec = new JButton("Surucu Sec");
		btndizsec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(WAIT_CURSOR);
				CAL_DIZIN cdz;
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
		
		btndizsec.setBounds(51, 350, 107, 23);
		panel.add(btndizsec);
		
		JLabel lblInstance = new JLabel("Instance");
		lblInstance.setBounds(24, 145, 68, 14);
		panel.add(lblInstance);
		
		JLabel lblServer = new JLabel("Server");
		lblServer.setBounds(24, 177, 68, 14);
		panel.add(lblServer);
		
		JLabel lblKullanici = new JLabel("Kullanici");
		lblKullanici.setBounds(24, 241, 68, 14);
		panel.add(lblKullanici);
		
		JLabel lblSifre = new JLabel("Sifre");
		lblSifre.setBounds(24, 272, 68, 14);
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
		
		txtsif = new JTextField();
		txtsif.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode() == KeyEvent.VK_ENTER)
				 {
					 contentPane.setCursor(WAIT_CURSOR);
						boolean varmi;
						try {
							varmi = oac.glb.user_var(GLOBAL.KULL_ADI,txtsif.getText());
							if (varmi == true)
				            {
								lblysif.setVisible(true);
						        txtyenisif.setVisible(true);
				                contentPane.setCursor(DEFAULT_CURSOR);
				            }
				            else
				            {
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
	
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//**************
		lblUser.setText(GLOBAL.KULL_ADI);
		comboBox = new JComboBox<String>();
		comboBox.setEditable(true);
		comboBox.setBounds(102, 144, 157, 22);
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
		
		//JOptionPane.showMessageDialog(cdz, "Kullanici veya Sifresi Yanlis......!" );
		//System.out.println("Altta");		
	}
	
	private static  void grid_doldur() throws ClassNotFoundException, SQLException
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
	private static  void grid_ortak_doldur( JTable grd,String prg) 
	{
		try {
			ResultSet	rs = null;
			rs = oac.glb.user_db_izinleri(GLOBAL.KULL_ADI, prg);
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			cmbhangisql.setSelectedItem("MS SQL");
			ip_doldur();
		}
    private static void ip_doldur() throws ClassNotFoundException, SQLException
	 {
     cmbip.removeAllItems();
     ResultSet	rs = null;
		rs = GLOBAL.ipp(GLOBAL.KULL_ADI);
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
	private static void server_control() throws HeadlessException, ClassNotFoundException
		{
			 contentPane.setCursor(WAIT_CURSOR);
			 String hangi_sql = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex());
             if (chckbxL.isSelected() )
                {
                	contentPane.setCursor(WAIT_CURSOR);
                   if ( hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_L(comboBox.getSelectedItem().toString(),txtkul.getText(), txtsifr.getText() ) : oac.obsort_mysql.Server_kontrol_L(comboBox.getSelectedItem().toString(),txtkul.getText(), txtsifr.getText()  ) == true)
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
                    if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.Server_kontrol_S(txtIp.getText(),comboBox.getSelectedItem().toString(),txtkul.getText(), txtsifr.getText() )  :oac.obsort_mysql.Server_kontrol_S(txtIp.getText(),"",txtkul.getText(), txtsifr.getText() ) == true)
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
	@SuppressWarnings({ "unused", "static-access" })
	private static void database_kontrol() throws ClassNotFoundException, HeadlessException, SQLException, IOException
	{
		contentPane.setCursor(WAIT_CURSOR);
         String program = "";
         String hangi_sql = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex());
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
            if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_L(program, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText()) :oac.obsort_mysql.dosyakontrol_L(program, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText()) == true)
             {
                 String modul = "";
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
                	 rs = oac.glb.user_details_izinleri(GLOBAL.KULL_ADI, program, "YER = 'L'");
                	 if (!rs.isBeforeFirst() ) {  
                		 izinli = false;
                	 	} 
                 }
                 oac.glb.con.close();
                 
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
             if (hangi_sql.equals("MS SQL") ? oac.obsort_mssql.dosyakontrol_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(),txtsifr.getText(), program):oac.obsort_mysql.dosyakontrol_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(),txtsifr.getText(), program) == true)
             {
                 String modul = "";
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
                	 ResultSet rs = oac.glb.user_details_izinleri(GLOBAL.KULL_ADI, program, "YER = 'S'");
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
	private static void mdb_yaz() throws ClassNotFoundException, SQLException
{
	if (activ_sayfa == 0)
    {
       oac.glb.calisanmi_degis(GLOBAL.KULL_ADI , "Cari Hesap",chckbxL.isSelected() ? "L" : "S"  ) ; // CaLISANMI DOSYA KONTROLU
       oac.glb.details_yaz(txtKodu.getText(),GLOBAL.KULL_ADI, txtkul.getText(), txtsifr.getText(), comboBox.getSelectedItem().toString(), txtIp.getText(), "Cari Hesap", txtdiz.getText() , chckbxL.isSelected() ? "L" : "S", chckbxD.isSelected() ? "D" : "O", "E", "E",cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()), txtcdid.getText());
    }
    else if (activ_sayfa == 1)
    {
       oac.glb.calisanmi_degis(GLOBAL.KULL_ADI, "Fatura", chckbxL.isSelected() ? "L" : "S"); // CaLISANMI DOSYA KONTROLU
       oac.glb.details_yaz(txtKodu.getText(),GLOBAL.KULL_ADI, txtkul.getText(), txtsifr.getText(), comboBox.getSelectedItem().toString(), txtIp.getText(), "Fatura", txtdiz.getText(), chckbxL.isSelected() ? "L" : "S", chckbxD.isSelected() ? "D" : "O", "E", "E", cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()),  txtcdid.getText());
    }
    else if (activ_sayfa == 2)
    {
       oac.glb.calisanmi_degis(GLOBAL.KULL_ADI, "Adres", chckbxL.isSelected() ? "L" : "S"); // CaLISANMI DOSYA KONTROLU
       oac.glb.details_yaz(txtKodu.getText(),GLOBAL.KULL_ADI, txtkul.getText(), txtsifr.getText(), comboBox.getSelectedItem().toString(), txtIp.getText(), "Adres", txtdiz.getText(), chckbxL.isSelected() ? "L" : "S", chckbxD.isSelected() ? "D" : "O", "E", "E", cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()),  txtcdid.getText());
    }
    else if (activ_sayfa == 3)
    {
       oac.glb.calisanmi_degis(GLOBAL.KULL_ADI, "Kur", chckbxL.isSelected() ? "L" : "S"); // CaLISANMI DOSYA KONTROLU
       oac.glb.details_yaz(txtKodu.getText(),GLOBAL.KULL_ADI, txtkul.getText(), txtsifr.getText(), comboBox.getSelectedItem().toString(), txtIp.getText(), "Kur", txtdiz.getText(), chckbxL.isSelected() ? "L" : "S", chckbxD.isSelected() ? "D" : "O", "E", "E", cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()), txtcdid.getText());
    }
     else if (activ_sayfa == 4)
    {
       oac.glb.calisanmi_degis(GLOBAL.KULL_ADI, "Kambiyo", chckbxL.isSelected() ? "L" : "S"); // CaLISANMI DOSYA KONTROLU
       oac.glb.details_yaz(txtKodu.getText(),GLOBAL.KULL_ADI, txtkul.getText(), txtsifr.getText(), comboBox.getSelectedItem().toString(), txtIp.getText(), "Kambiyo",txtdiz.getText(), chckbxL.isSelected() ? "L" : "S", chckbxD.isSelected() ? "D" : "O", "E", "E", cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()),  txtcdid.getText());
    }
    else if (activ_sayfa == 5)
    {
       oac.glb.calisanmi_degis(GLOBAL.KULL_ADI, "Sms",chckbxL.isSelected() ? "L" : "S"); // CaLISANMI DOSYA KONTROLU
       oac.glb.details_yaz(txtKodu.getText(),GLOBAL.KULL_ADI, txtkul.getText(), txtsifr.getText(), comboBox.getSelectedItem().toString(), txtIp.getText(), "Sms", txtdiz.getText(), chckbxL.isSelected() ? "L" : "S", chckbxD.isSelected() ? "D" : "O", "E", "E", cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()),  txtcdid.getText());
    }
    else if (activ_sayfa == 6)
    {
        oac.glb.calisanmi_degis(GLOBAL.KULL_ADI, "Gunluk",chckbxL.isSelected() ? "L" : "S"); // CaLISANMI DOSYA KONTROLU
        oac.glb.details_yaz(txtKodu.getText(),GLOBAL.KULL_ADI, txtkul.getText(), txtsifr.getText(), comboBox.getSelectedItem().toString(), txtIp.getText(), "Gunluk",txtdiz.getText(), chckbxL.isSelected() ? "L" : "S", chckbxD.isSelected() ? "D" : "O", "E", "E",cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()),  txtcdid.getText());
     }
    oac.glb.ip_dos_kont(txtIp.getText());
}
	private static void dosya_olustur_L() throws IOException, ClassNotFoundException, SQLException
{
	 if (activ_sayfa == 0)
     {
		 String strAdmin = "";
         strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
         contentPane.setCursor(WAIT_CURSOR);
         if (chckbxD.isSelected())
        	 if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL") 
            oac.cARI_HESAP_MSSQL.cari_sifirdan_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 else
        	 {
            oac.cARI_HESAP_MYSQL.cari_sifirdan_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
       		 
        	 }
        	 else
        	 if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL") 	 
            oac.cARI_HESAP_MSSQL.cari_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	else
        	{
        	oac.cARI_HESAP_MYSQL.cari_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	}
     }
     else if (activ_sayfa == 1)
     {
    	 String strAdmin = "";
         strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
         contentPane.setCursor(WAIT_CURSOR);
         if (chckbxD.isSelected())
        	 if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL") 
            oac.sTOK_MSSQL.fat_sifirdan_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 else
        	 {
            oac.sTOK_MYSQL.fat_sifirdan_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
       		 
        	 }
        	 else
       		 if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL")  
            oac.sTOK_MSSQL.fat_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	else
        	{
            oac.sTOK_MYSQL.fat_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	}
     }
     else if (activ_sayfa == 2)
     {
    	 String strAdmin = "";
         strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
        contentPane.setCursor(WAIT_CURSOR);
         if (chckbxD.isSelected())
        	 if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL") 
            oac.aDRES_MSSQL.adr_sifirdan_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 else
         	{
            oac.aDRES_MYSQL.adr_sifirdan_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
         	}
        	 else
       		 if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL") 
            oac.aDRES_MSSQL.adr_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	else
        	{
        	oac.aDRES_MYSQL.adr_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	}
     }
     else if (activ_sayfa == 3)
     {
    	 contentPane.setCursor(WAIT_CURSOR);
         if (chckbxD.isSelected())
        	 if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL") 
            oac.kUR_MSSQL .kur_sifirdan_L(txtKodu.getText(), "default", "", comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 else
        	 {
            oac.kUR_MYSQL .kur_sifirdan_L(txtKodu.getText(), "default", "", comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 }
        	 else
       		 if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL") 
            oac.kUR_MSSQL.kur_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	else
         	{
            oac.kUR_MYSQL.kur_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
         	}
     }
     else if (activ_sayfa == 4)
     {
    	 String strAdmin = "";
         strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
         contentPane.setCursor(WAIT_CURSOR);
         if (chckbxD.isSelected())
        	 if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL") 
            oac.kAMBIYO_MSSQL.kam_sifirdan_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 else
        	 {
            oac.kAMBIYO_MYSQL.kam_sifirdan_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 }
        	 else
        	 if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL") 
            oac.kAMBIYO_MSSQL.kam_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 else
        	 {
        	oac.kAMBIYO_MYSQL.kam_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 }
     }
     else if (activ_sayfa == 5)
     {
    	 contentPane.setCursor(WAIT_CURSOR);
         if (chckbxD.isSelected())
        	 if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL") 
            oac.sMS_MSSQL.sms_sifirdan_L(txtKodu.getText(), "default", "", comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 else
        	 {
            oac.sMS_MYSQL.sms_sifirdan_L(txtKodu.getText(), "default", "", comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 }
        	 else
        	 if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL") 	 
            oac.sMS_MSSQL.sms_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 else
        	 {
        	oac.sMS_MYSQL.sms_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 }
     }
     else if (activ_sayfa == 6)
     {
    	 String strAdmin = "";
         strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
         contentPane.setCursor(WAIT_CURSOR);
        
         if (chckbxD.isSelected())
        	 if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL")
            oac.gUNLUK_MSSQL.gun_sifirdan_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 else
        	 {
            oac.gUNLUK_MYSQL.gun_sifirdan_L(txtKodu.getText(), "default", "", strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 }
        	 else
       		 if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL") 
            oac.gUNLUK_MSSQL.gun_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 else
        	 {
        	oac.gUNLUK_MYSQL.gun_sifirdan_L(txtKodu.getText(), "", txtdiz.getText(), strAdmin, comboBox.getSelectedItem().toString(),txtkul.getText(),txtsifr.getText());
        	 }
     }
 }
	private static void dosya_olustur_S() throws IOException, ClassNotFoundException, SQLException
{
	if (activ_sayfa == 0)
    {
		String strAdmin = "";
        strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);

        if (chckbxD.isSelected())
        	if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL")
           oac.cARI_HESAP_MSSQL.cari_sifirdan_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText(),  strAdmin);
        	else
        	{
           oac.cARI_HESAP_MYSQL.cari_sifirdan_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText(),  strAdmin);
       		
        	}
    }
    else if (activ_sayfa == 1)
    {
    	String strAdmin = "";
        strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);

        if (chckbxD.isSelected())
        	if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL")
           oac.sTOK_MSSQL.fat_sifirdan_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText(),  strAdmin);
        	else
        	{
           oac.sTOK_MYSQL.fat_sifirdan_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText(),  strAdmin);
       		
        	}
    }
    else if (activ_sayfa == 2)
    {
    	String strAdmin = "";
        strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
        if (chckbxD.isSelected())
        	if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex())== "MS SQL")
           oac.aDRES_MSSQL.adr_sifirdan_S( txtIp.getText(), comboBox.getSelectedItem().toString(),txtkul.getText(), txtsifr.getText(), txtKodu.getText(),  strAdmin);
        	else
        	{
           oac.aDRES_MYSQL.adr_sifirdan_S( txtIp.getText(), comboBox.getSelectedItem().toString(),txtkul.getText(), txtsifr.getText(), txtKodu.getText(),  strAdmin);
        	}
    }
    else if (activ_sayfa == 3)
    {
        if (chckbxD.isSelected())
        	if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL")
           oac.kUR_MSSQL.kur_sifirdan_S(txtIp.getText(),  comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText());
        	else
        	{
           oac.kUR_MYSQL.kur_sifirdan_S(txtIp.getText(),  comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText());
        	}
    }
    
    else if (activ_sayfa == 4)
    {
    	String strAdmin = "";
        strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
        if (chckbxD.isSelected())
        	if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL")
           oac.kAMBIYO_MSSQL.kam_sifirdan_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText(),  strAdmin);
        	else
        	{
           oac.kAMBIYO_MYSQL.kam_sifirdan_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText(),  strAdmin);
       		
        	}
    }
    else if (activ_sayfa == 5)
    {
        if (chckbxD.isSelected())
        	if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL")
           oac.sMS_MSSQL.sms_sifirdan_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText());
        	else
        	{
        	oac.sMS_MYSQL.sms_sifirdan_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText());
        	}
    }
    
    else if (activ_sayfa == 6)
    {
    	String strAdmin = "";
        strAdmin = JOptionPane.showInputDialog(null,"Firma Ismini Giriniz....", "Yeni Firma",JOptionPane.QUESTION_MESSAGE);
        if (chckbxD.isSelected())
        	if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()) == "MS SQL")
           oac.gUNLUK_MSSQL.gun_sifirdan_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText(), "default", "", strAdmin);
        	else
        	{
        	oac.gUNLUK_MYSQL.gun_sifirdan_S(txtIp.getText(), comboBox.getSelectedItem().toString(), txtkul.getText(), txtsifr.getText(), txtKodu.getText(), "default", "", strAdmin);
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
	
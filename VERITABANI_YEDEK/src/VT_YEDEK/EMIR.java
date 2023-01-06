package VT_YEDEK;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.net.ftp.FTPClient;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.OBS_ORTAK_MSSQL;
import OBS_C_2025.OBS_ORTAK_MYSQL;
import OBS_C_2025.SQL_BACKUP;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class EMIR extends JFrame {
	VT_ANA_CLASS oac = new VT_ANA_CLASS();
	private JPanel contentPane;
	private static JTextField txtEMIR_ISMI;
	private static JTextField txtHOST;
	private static JTextField txtKULL;
	private static JTextField txtSUNUCU;
	private static JTextField txtZMNASIMI;
	private static JTextField txtPORT;
	private static JTextField txtSURUCU;
	private static JTextField txtESKIYEDEK;
	private static JTextField txtGON_ISIM;
	private static JTextField txtGON_ADRES;
	private static JTextField txtALICI;
	private static JTextField txtKONU;
	private static JTextField txtSMTP;
	private static JTextField txtSMTP_PORT;
	private static JTextField txtSMTP_KULL;
	private static JCheckBox chckbxDRM ;
	private static JTextField txtHER;
	private static boolean kontrol;
	private static JList<CheckListItem> list ;
	public static JComboBox<String> cmbSQL;
	static SQL_BACKUP sqll = new SQL_BACKUP();
	private static JCheckBox chckbxDURUM  ;
	private static JTextArea txtAciklama ;
	private static JCheckBox chckbxFTP;
	private static JPasswordField txtPWD;
	private static JPasswordField txtSMTP_PWD;
	private static JCheckBox chckbxGON;
	private static JCheckBox chckbxHATA;
	private static JCheckBox chckbxSSL;
	private static JCheckBox chckbxTSL;
	private static JCheckBox chckbxPTESI;
	private static JCheckBox chckbxSALI;
	private static JCheckBox chckbxCAR;
	private static JCheckBox chckbxPER;
	private static JCheckBox chckbxCUM;
	private static JCheckBox chckbxCTESI;
	private static JCheckBox chckbxPAZ;
	private static JSpinner spinBAS ;
	private static JSpinner spinBIT ;
	private static JCheckBox chckbxHANGI;
	private static JCheckBox chckbxYEREL;
	private JSpinner.DateEditor de_spinBAS;
	private JSpinner.DateEditor de_spinBIT;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BASLA bsl = new BASLA();
					bsl.setVisible(false);
					bsl.dispose();
					EMIR frame = new EMIR();
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
	@SuppressWarnings({ "unchecked", "deprecation" })
	public EMIR() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				try {
					BASLA frame = new BASLA();
					frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@SuppressWarnings("static-access")
			@Override
			public void windowOpened(WindowEvent e) {
				
					contentPane.setCursor(oac.WAIT_CURSOR);
					try {
						
						if(oac.yENI_EMIR == true)
						{
							DefaultListModel<CheckListItem> listModel = (DefaultListModel<CheckListItem>) list.getModel();
							listModel.removeAllElements();
						}
						else
						{
							serBILGILERI();
						}
						
//						if (! oac.EMIR_ADI.equals("") )
//						{
//							serBILGILERI();
//						}
					
					} catch (InvalidKeyException | ClassNotFoundException | NoSuchAlgorithmException
							| NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException
							| BadPaddingException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//activ_sayfa =0;
					//grid_doldur();
					//doldur_kutu(tblCari,0);
					contentPane.setCursor(oac.DEFAULT_CURSOR);
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JSplitPane splitPanesol = new JSplitPane();
		splitPanesol.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setLeftComponent(splitPanesol);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(175,40));
		panel.setLayout(null);
		splitPanesol.setLeftComponent(panel);
		
		cmbSQL = new JComboBox<String>();
		cmbSQL.setEnabled(false);
		cmbSQL.setForeground(new Color(0, 0, 139));
		cmbSQL.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbSQL.setModel(new DefaultComboBoxModel<String>(new String[] {"MS SQL", "MY SQL"}));
		cmbSQL.setBounds(1, 1, 1, 22);
		panel.add(cmbSQL);
		
		JButton btnNewButton = new JButton(".....");
		btnNewButton.setToolTipText("Server Baglanti");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtEMIR_ISMI.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Emir Ismi Bos........", "Server Baglanti", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					SQL_BILGI hsp = new SQL_BILGI();
					hsp.show();
					contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}

			}
		});
		btnNewButton.setBounds(10, 11, 155, 23);
		panel.add(btnNewButton);
		
		list = new JList<CheckListItem>();
		
	    list.setCellRenderer(new CheckListRenderer());
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			      if (!e.getValueIsAdjusting()) {
			        System.out.println(Arrays.toString(list.getSelectedValues()));
			      }
			    }
	    	
	    });
	    list.addMouseListener(new MouseAdapter() {
	      @SuppressWarnings("rawtypes")
		@Override
	      public void mouseClicked(MouseEvent event) {
	        JList list = (JList) event.getSource();
	        int index = list.locationToIndex(event.getPoint());// Get index of item
	                                                           // clicked
	        CheckListItem item = (CheckListItem) list.getModel()
	            .getElementAt(index);
	        item.setSelected(!item.isSelected()); // Toggle selected state
	        list.repaint(list.getCellBounds(index, index));// Repaint cell
	        System.out.println(list.getSelectedValue() + " = " + item.isSelected());
	      }
	    });

		splitPanesol.setRightComponent(list);
			
		///
		
		DefaultListModel<CheckListItem> demoList = new DefaultListModel<CheckListItem>();
		 demoList.addElement( new CheckListItem("mango"));
		 demoList.addElement( new CheckListItem("elma"));
		 list.setModel(demoList);
		//
	
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Genel", null, panel_1, null);
		
		JLabel lblNewLabel = new JLabel("Durum");
		lblNewLabel.setBounds(29, 52, 46, 14);
		panel_1.add(lblNewLabel);
		
		chckbxDURUM = new JCheckBox("Aktif / Pasif");
		chckbxDURUM.setBounds(100, 48, 156, 23);
		panel_1.add(chckbxDURUM);
		
		JLabel lblNewLabel_1 = new JLabel("Emir Ismi");
		lblNewLabel_1.setBounds(29, 100, 64, 14);
		panel_1.add(lblNewLabel_1);
		
		txtEMIR_ISMI = new JTextField();
		txtEMIR_ISMI.setBounds(103, 97, 322, 20);
		txtEMIR_ISMI.setDocument(new JTextFieldLimit(30));
		txtEMIR_ISMI.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  VT_ANA_CLASS.EMIR_ADI = txtEMIR_ISMI.getText();
			  }
			  public void removeUpdate(DocumentEvent e) {
				  VT_ANA_CLASS.EMIR_ADI = txtEMIR_ISMI.getText();
			  }
			  public void insertUpdate(DocumentEvent e) {
				  VT_ANA_CLASS.EMIR_ADI = txtEMIR_ISMI.getText();
			  }
			});
		panel_1.add(txtEMIR_ISMI);
		txtEMIR_ISMI.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Aciklama");
		lblNewLabel_2.setBounds(29, 149, 64, 14);
		panel_1.add(lblNewLabel_2);
		
		txtAciklama = new JTextArea();
		txtAciklama.setDocument(new JTextFieldLimit(50));
		txtAciklama.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Border borderr = BorderFactory.createLineBorder(Color.GRAY);
		txtAciklama.setBorder(BorderFactory.createCompoundBorder(borderr, BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		txtAciklama.setBounds(103, 143, 322, 70);
		panel_1.add(txtAciklama);
		
		JLabel lblNewLabel_3 = new JLabel("Dosya Sayisi");
		lblNewLabel_3.setBounds(22, 456, 81, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("0");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(113, 456, 38, 14);
		panel_1.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("Kaydet");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtEMIR_ISMI.getText().equals("")) return ;
					kayDET();
				} catch (InvalidKeyException | ClassNotFoundException | NoSuchAlgorithmException
						| NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException
						| BadPaddingException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(381, 456, 89, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Vazgec");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					BASLA frame = new BASLA();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(483, 456, 89, 23);
		panel_1.add(btnNewButton_2);
		
		chckbxHANGI = new JCheckBox("Veritabani Yedekleme");
		chckbxHANGI.setSelected(true);
		chckbxHANGI.setBounds(29, 340, 166, 23);
		panel_1.add(chckbxHANGI);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		tabbedPane.addTab("Surucu Ayarlari", null, panel_2, null);
		
		chckbxFTP = new JCheckBox("FTP");
		chckbxFTP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxFTP.isSelected())
				{
					 chckbxYEREL.setSelected(false);
				}
			}
		
		});
		chckbxFTP.setBounds(40, 18, 97, 23);
		panel_2.add(chckbxFTP);
		
		chckbxYEREL = new JCheckBox("Yerel");
		chckbxYEREL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxYEREL.isSelected())
				{
					 chckbxFTP.setSelected(false);
				}
			}
		});
		chckbxYEREL.setBounds(235, 18, 97, 23);
		panel_2.add(chckbxYEREL);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "FTP Ayarlari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setLayout(null);
		panel_6.setBounds(10, 62, 537, 239);
		panel_2.add(panel_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "FTP Ayar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setLayout(null);
		panel_7.setBounds(10, 24, 517, 107);
		panel_6.add(panel_7);
		
		JLabel lblNewLabel_5 = new JLabel("Host");
		lblNewLabel_5.setBounds(10, 25, 46, 14);
		panel_7.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Kullanici");
		lblNewLabel_6.setBounds(10, 52, 46, 14);
		panel_7.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Sifre");
		lblNewLabel_7.setBounds(10, 78, 46, 14);
		panel_7.add(lblNewLabel_7);
		
		txtHOST = new JTextField();
		txtHOST.setBounds(88, 22, 158, 20);
		panel_7.add(txtHOST);
		txtHOST.setColumns(10);
		
		txtKULL = new JTextField();
		txtKULL.setBounds(88, 50, 158, 20);
		panel_7.add(txtKULL);
		txtKULL.setColumns(10);
		
		txtPWD = new JPasswordField();
		txtPWD.setBounds(88, 75, 158, 20);
		panel_7.add(txtPWD);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Diger Ayarlar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(10, 142, 517, 90);
		panel_8.setLayout(null);
		panel_6.add(panel_8);
		
		JLabel lblNewLabel_8 = new JLabel("Sunucu");
		lblNewLabel_8.setBounds(10, 24, 65, 14);
		panel_8.add(lblNewLabel_8);
		
		txtSUNUCU = new JTextField();
		txtSUNUCU.setBounds(88, 21, 222, 20);
		panel_8.add(txtSUNUCU);
		txtSUNUCU.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Zaman Asimi");
		lblNewLabel_9.setBounds(10, 56, 75, 14);
		panel_8.add(lblNewLabel_9);
		
		txtZMNASIMI = new JTextField();
		txtZMNASIMI.setBounds(88, 53, 55, 20);
		panel_8.add(txtZMNASIMI);
		txtZMNASIMI.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Port");
		lblNewLabel_10.setBounds(166, 56, 46, 14);
		panel_8.add(lblNewLabel_10);
		
		txtPORT = new JTextField();
		txtPORT.setBounds(222, 52, 86, 20);
		panel_8.add(txtPORT);
		txtPORT.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("Surucu Kontrol");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FTPClient ftp = new FTPClient();
				try {
					ftp.connect(txtHOST.getText(),Integer.parseInt(txtPORT.getText()));
					if(ftp.login(txtKULL.getText(),oac.sDONDUR.sDONDUR(txtPWD)))            
						if(ftp.cwd(txtSUNUCU.getText()) == 250)
						{
							JOptionPane.showMessageDialog(null, "Surucu Bulundu..........",  "OBS Indirme", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Surucu Bulunamadi.......",  "OBS Indirme", JOptionPane.ERROR_MESSAGE);   
						}
				} 
				catch (Exception e1) 
				{
					JOptionPane.showMessageDialog(null, "Baglanti Hatasi ....." + e1.getMessage() ,  "OBS Indirme", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_5.setBounds(385, 52, 122, 23);
		panel_8.add(btnNewButton_5);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Yerel Surucu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(10, 305, 537, 75);
		panel_9.setLayout(null);
		panel_2.add(panel_9);
		
		JLabel lblNewLabel_11 = new JLabel("Surucu");
		lblNewLabel_11.setBounds(10, 21, 46, 14);
		panel_9.add(lblNewLabel_11);
		
		txtSURUCU = new JTextField();
		txtSURUCU.setBounds(100, 18, 345, 20);
		panel_9.add(txtSURUCU);
		txtSURUCU.setColumns(10);
		
		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.setBounds(481, 17, 46, 23);
		panel_9.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Surucu Sec");
		btnNewButton_7.setBounds(100, 44, 110, 23);
		panel_9.add(btnNewButton_7);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(null, "Eski Yedek", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBounds(10, 391, 537, 49);
		panel_10.setLayout(null);
		panel_2.add(panel_10);
		
		JLabel lblNewLabel_12 = new JLabel("Eski Yedekleri Silme");
		lblNewLabel_12.setBounds(10, 24, 139, 14);
		panel_10.add(lblNewLabel_12);
		
		txtESKIYEDEK = new JTextField();
		txtESKIYEDEK.setBounds(159, 21, 55, 20);
		panel_10.add(txtESKIYEDEK);
		txtESKIYEDEK.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("gunden eski olanlar(0 Silinmez)");
		lblNewLabel_13.setBounds(224, 24, 209, 14);
		panel_10.add(lblNewLabel_13);
		
		JButton btnNewButton_3 = new JButton("Baglanti Test");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					ftpKONTROL();
				} catch (HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(20, 452, 136, 23);
		panel_2.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Kaydet");
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtEMIR_ISMI.getText().equals("")) return ;
				try {
					ftpKAYDET();
				} catch (InvalidKeyException | NumberFormatException | ClassNotFoundException | NoSuchAlgorithmException
						| NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException
						| BadPaddingException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(455, 451, 89, 23);
		panel_2.add(btnNewButton_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		tabbedPane.addTab("Bilgilendirme", null, panel_3, null);
		
		JLabel lblNewLabel_14 = new JLabel("Durum");
		lblNewLabel_14.setBounds(30, 22, 46, 14);
		panel_3.add(lblNewLabel_14);
		
		chckbxDRM = new JCheckBox("Aktif / Pasif");
		chckbxDRM.setBounds(100, 18, 153, 23);
		panel_3.add(chckbxDRM);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(null, "Gonderme Durumu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_11.setBounds(30, 60, 530, 61);
		panel_11.setLayout(null);
		panel_3.add(panel_11);
		
		chckbxGON = new JCheckBox("Gonderildiginde");
		chckbxGON.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxGON.isSelected())
				{
					 chckbxHATA.setSelected(false);
				}
			}
		});
		chckbxGON.setBounds(119, 26, 152, 23);
		panel_11.add(chckbxGON);
		
		chckbxHATA = new JCheckBox("Hata Durumunda");
		chckbxHATA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxHATA.isSelected())
				{
					 chckbxGON.setSelected(false);
				}
			}
		});
		chckbxHATA.setBounds(286, 26, 152, 23);
		panel_11.add(chckbxHATA);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(null, "Mail Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_12.setBounds(30, 132, 530, 144);
		panel_12.setLayout(null);
		panel_3.add(panel_12);
		
		JLabel lblNewLabel_15 = new JLabel("Gonderen Isim");
		lblNewLabel_15.setBounds(10, 23, 101, 14);
		panel_12.add(lblNewLabel_15);
		
		txtGON_ISIM = new JTextField();
		txtGON_ISIM.setBounds(121, 20, 310, 20);
		panel_12.add(txtGON_ISIM);
		txtGON_ISIM.setColumns(10);
		
		txtGON_ADRES = new JTextField();
		txtGON_ADRES.setBounds(121, 51, 310, 20);
		panel_12.add(txtGON_ADRES);
		txtGON_ADRES.setColumns(10);
		
		txtALICI = new JTextField();
		txtALICI.setBounds(121, 82, 310, 20);
		panel_12.add(txtALICI);
		txtALICI.setColumns(10);
		
		txtKONU = new JTextField();
		txtKONU.setBounds(121, 113, 310, 20);
		panel_12.add(txtKONU);
		txtKONU.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Gonderen Adres");
		lblNewLabel_16.setBounds(10, 54, 101, 14);
		panel_12.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("Alici");
		lblNewLabel_17.setBounds(10, 85, 46, 14);
		panel_12.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("Konu");
		lblNewLabel_18.setBounds(10, 116, 46, 14);
		panel_12.add(lblNewLabel_18);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(null, "Server Ayarlari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_13.setBounds(30, 287, 530, 144);
		panel_13.setLayout(null);
		panel_3.add(panel_13);
		
		txtSMTP = new JTextField();
		txtSMTP.setBounds(126, 22, 185, 20);
		panel_13.add(txtSMTP);
		txtSMTP.setColumns(10);
		
		txtSMTP_PORT = new JTextField();
		txtSMTP_PORT.setBounds(388, 22, 44, 20);
		panel_13.add(txtSMTP_PORT);
		txtSMTP_PORT.setColumns(10);
		
		txtSMTP_KULL = new JTextField();
		txtSMTP_KULL.setBounds(126, 53, 306, 20);
		panel_13.add(txtSMTP_KULL);
		txtSMTP_KULL.setColumns(10);
		
		chckbxSSL = new JCheckBox("SSL");
		chckbxSSL.setBounds(122, 111, 86, 23);
		panel_13.add(chckbxSSL);
		
		chckbxTSL = new JCheckBox("TSL");
		chckbxTSL.setBounds(248, 111, 63, 23);
		panel_13.add(chckbxTSL);
		
		JLabel lblNewLabel_19 = new JLabel("SMTP Server");
		lblNewLabel_19.setBounds(10, 25, 89, 14);
		panel_13.add(lblNewLabel_19);
		
		JLabel lblNewLabel_20 = new JLabel("Kullanici");
		lblNewLabel_20.setBounds(10, 56, 89, 14);
		panel_13.add(lblNewLabel_20);
		
		JLabel lblNewLabel_21 = new JLabel("Sifre");
		lblNewLabel_21.setBounds(10, 87, 89, 14);
		panel_13.add(lblNewLabel_21);
		
		JLabel lblNewLabel_22 = new JLabel("SMTP Port");
		lblNewLabel_22.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_22.setBounds(318, 25, 71, 14);
		panel_13.add(lblNewLabel_22);
		
		JButton btnNewButton_8 = new JButton("Deneme");
		btnNewButton_8.setBounds(346, 111, 86, 23);
		panel_13.add(btnNewButton_8);
		
		txtSMTP_PWD = new JPasswordField();
		txtSMTP_PWD.setBounds(126, 84, 149, 20);
		panel_13.add(txtSMTP_PWD);
		
		JButton btnNewButton_9 = new JButton("Kayit");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtEMIR_ISMI.getText().equals("")) return ;
					bilgiKAYIT();
				} catch (InvalidKeyException | ClassNotFoundException | NoSuchAlgorithmException
						| NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException
						| BadPaddingException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_9.setBounds(470, 452, 89, 23);
		panel_3.add(btnNewButton_9);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		tabbedPane.addTab("Yedekleme Araligi", null, panel_4, null);
		
		JLabel lblNewLabel_23 = new JLabel("Her");
		lblNewLabel_23.setBounds(36, 32, 46, 14);
		panel_4.add(lblNewLabel_23);
		
		txtHER = new JTextField();
		txtHER.setBounds(118, 29, 46, 20);
		panel_4.add(txtHER);
		txtHER.setColumns(10);
		
		JLabel lblNewLabel_24 = new JLabel("Dakkada bir");
		lblNewLabel_24.setBounds(189, 32, 105, 14);
		panel_4.add(lblNewLabel_24);
		
		JLabel lblNewLabel_25 = new JLabel("Gunler");
		lblNewLabel_25.setBounds(36, 79, 46, 14);
		panel_4.add(lblNewLabel_25);
		
		chckbxPTESI = new JCheckBox("Pazartesi");
		chckbxPTESI.setBounds(118, 75, 97, 23);
		panel_4.add(chckbxPTESI);
		
		chckbxSALI = new JCheckBox("Sali");
		chckbxSALI.setBounds(118, 101, 97, 23);
		panel_4.add(chckbxSALI);
		
		chckbxCAR = new JCheckBox("Carsamba");
		chckbxCAR.setBounds(118, 127, 97, 23);
		panel_4.add(chckbxCAR);
		
		chckbxPER = new JCheckBox("Persembe");
		chckbxPER.setBounds(118, 153, 97, 23);
		panel_4.add(chckbxPER);
		
		chckbxCUM = new JCheckBox("Cuma");
		chckbxCUM.setBounds(118, 179, 97, 23);
		panel_4.add(chckbxCUM);
		
		chckbxCTESI = new JCheckBox("Cumartesi");
		chckbxCTESI.setBounds(118, 205, 97, 23);
		panel_4.add(chckbxCTESI);
		
		chckbxPAZ = new JCheckBox("Pazar");
		chckbxPAZ.setBounds(118, 231, 97, 23);
		panel_4.add(chckbxPAZ);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new TitledBorder(null, "Yedekleme Araligi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_14.setBounds(36, 299, 383, 122);
		panel_14.setLayout(null);
		panel_4.add(panel_14);
		
		JLabel lblNewLabel_26 = new JLabel("Baslangic");
		lblNewLabel_26.setBounds(10, 31, 76, 14);
		panel_14.add(lblNewLabel_26);
		
		JLabel lblNewLabel_27 = new JLabel("Bitis");
		lblNewLabel_27.setBounds(10, 56, 46, 14);
		panel_14.add(lblNewLabel_27);
		
		spinBAS = new JSpinner( new SpinnerDateModel() );
		de_spinBAS = new JSpinner.DateEditor(spinBAS, "HH:mm");
		spinBAS.setEditor(de_spinBAS);
		Date d = new Date();
		 d.setHours(0);
		  d.setMinutes(0);
		spinBAS.setValue(d); 
		spinBAS.setBounds(119, 28, 63, 20);
		panel_14.add(spinBAS);
		
		spinBIT = new JSpinner( new SpinnerDateModel() );
		 de_spinBIT = new JSpinner.DateEditor(spinBIT, "HH:mm");
		spinBIT.setEditor(de_spinBIT);
		  d.setHours(0);
		  d.setMinutes(0);
		spinBIT.setValue(d); 
		spinBIT.setBounds(119, 53, 63, 20);
		panel_14.add(spinBIT);
		
		JButton btnNewButton_10 = new JButton("Kayit");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtEMIR_ISMI.getText().equals("")) return ;
					yedeklemeKAYIT();
				} catch (InvalidKeyException | ClassNotFoundException | NoSuchAlgorithmException
						| NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException
						| BadPaddingException | SQLException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_10.setBounds(471, 452, 89, 23);
		panel_4.add(btnNewButton_10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		tabbedPane.addTab("Emir Kopyala", null, panel_5, null);

		
		
	}
	private void kayDET() throws SQLException, ClassNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		try
		{
			
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		ResultSet rs = null;
		rs = sqll.emirBILGI(txtEMIR_ISMI.getText());
   
		Boolean sondurum = false ; 
		Date sonyuk = null  ;
		if (!rs.isBeforeFirst() ) 
		{ 
			kontrol = false ;
		}
		else
		{
			 sondurum = rs.getBoolean("SON_DURUM");
		     sonyuk = rs.getDate("SON_YUKLEME");
		             kontrol = true;
		}
	
       sqll.genel_kayit_sil(txtEMIR_ISMI.getText());
       sqll.genel_kayit(txtEMIR_ISMI.getText(), chckbxDURUM.isSelected(), txtAciklama.getText(),cmbSQL.getSelectedItem().toString(), chckbxHANGI.isSelected());
       sqll.genel_kayit_durum(txtEMIR_ISMI.getText(), false, sonyuk);
       
       if ( kontrol )
       {
    	   sqll.genel_kayit_durum(txtEMIR_ISMI.getText(), sondurum, sonyuk);
       }
       sqll.db_adi_kayit_sil(txtEMIR_ISMI.getText());
       for (int i = 0; i < list.getModel().getSize(); i++)
       {
    	   CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
    	   if(item.isSelected())
    	   {
    		   sqll.db_ismi_kayit(txtEMIR_ISMI.getText(), item.toString());
    	   }
       }
   		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	} 
	catch (Exception e1)
	{
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		e1.printStackTrace();
	}	
	}
	private void ftpKAYDET() throws InvalidKeyException, NumberFormatException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, SQLException
	{
		sqll.ftp_kayit_sil(txtEMIR_ISMI.getText());
		String neresi ;
         if(chckbxFTP.isSelected())
         {
              neresi = "FTP";
         }
          else
          {
              neresi = "SUR";
          }
         sqll.ftp_ismi_kayit(txtEMIR_ISMI.getText(), txtHOST.getText(),txtKULL.getText(),oac.sDONDUR.sDONDUR(txtPWD),txtSUNUCU.getText(), txtPORT.getText(),
        		 			Integer.parseInt(txtZMNASIMI.getText()),txtZMNASIMI.getText(), neresi,txtSURUCU.getText());
 	}
	@SuppressWarnings("rawtypes")
	public static void dbDOLDUR (String ipp , String user , String pwd) throws ClassNotFoundException, SQLException
	{
		try
		{
			ResultSet rss = null;
			DefaultListModel<CheckListItem> demoList = new DefaultListModel<CheckListItem>();
			demoList.removeAllElements();
			String hangisql = cmbSQL.getSelectedItem().toString();
			if(hangisql.toString().equals("MS SQL"))
			{
				VT_ANA_CLASS._IConn = new OBS_ORTAK_MSSQL();
				rss = sqll.msSQLDB(ipp,user,pwd);
				while (rss.next()) 
				{
					demoList.addElement( new CheckListItem(rss.getString("name")));
				}
			}
			else
			{
				VT_ANA_CLASS._IConn = new OBS_ORTAK_MYSQL();
				rss = sqll.mySQLDB(ipp,user,pwd);
				while (rss.next()) 
				{
					demoList.addElement( new CheckListItem(rss.getString("Database")));
				}
			}
			list.setModel(demoList);
		} 
		catch (Exception e1)
		{
			DefaultListModel listModel = (DefaultListModel) list.getModel();
	        listModel.removeAllElements();
			e1.printStackTrace();
		}	
	}
	@SuppressWarnings("rawtypes")
	public static void serBILGILERI() throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, SQLException
	{
		
		try
		{
			ResultSet rss = sqll.serBILGI(VT_ANA_CLASS.EMIR_ADI);
			if (!rss.isBeforeFirst() ) {  
				DefaultListModel listModel = (DefaultListModel) list.getModel();
				listModel.removeAllElements();
			}
			else
			{
				cmbSQL.setSelectedItem(rss.getString("HANGI_SQL").toString());
				dbDOLDUR(rss.getString("INSTANCE") , rss.getString("KULLANICI"), ENCRYPT_DECRYPT_STRING.dCRYPT_manual(rss.getBytes("SIFRE")));
			}
			rss = sqll.emirBILGI(VT_ANA_CLASS.EMIR_ADI);
			genel_DOLDUR(rss);
			rss = sqll.dbLISTE(VT_ANA_CLASS.EMIR_ADI);
			db_DOLDUR(rss);
			rss = sqll.surBILGI(VT_ANA_CLASS.EMIR_ADI);
			sur_DOLDUR(rss);
			rss = sqll.bilgilendirmeBILGI(VT_ANA_CLASS.EMIR_ADI);
			bilgi_DOLDUR(rss);
			rss = sqll.yedeklemeBILGI(VT_ANA_CLASS.EMIR_ADI);
			yedek_DOLDUR(rss);
		} 
	catch (Exception e1)
	{
		DefaultListModel listModel = (DefaultListModel) list.getModel();
        listModel.removeAllElements();
		e1.printStackTrace();
	}	
	}
	private static void genel_DOLDUR(ResultSet rss) throws SQLException
	{
		if (!rss.isBeforeFirst() ) {  
			return;
		} 
		if(rss.getBoolean("SQL_YEDEK"))
		{
			chckbxHANGI.setSelected(true);
		}
		else
		{
			chckbxHANGI.setSelected(false);
		}
		if(rss.getBoolean("DURUM"))
		{
			chckbxDURUM.setSelected(true);
		}
		else
		{
			chckbxDURUM.setSelected(false);
		}
		txtEMIR_ISMI.setText(rss.getString("EMIR_ISMI"));
		txtAciklama.setText("EMIR_ACIKLAMA");
	}
	private static void db_DOLDUR(ResultSet rss) throws SQLException
	{
		
		if (!rss.isBeforeFirst() ) {  
			return;
		} 
		while(rss.next())
		{
			for (int i = 0;  i < list.getModel().getSize(); i++) {
				  
				if (rss.getString("DB_ADI").equals(list.getModel().getElementAt(i).toString()))
				{
					  CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
				        item.setSelected(true); // Toggle
				}
				
				}
		}
	}
	private static void sur_DOLDUR(ResultSet rss) throws SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		if (!rss.isBeforeFirst() ) {  
			return;
		} 
		if(rss.getString("NERESI").equals("FTP"))
		{
			chckbxFTP.isSelected();
		}
		else
		{
			chckbxYEREL.isSelected();
		}
		txtHOST.setText(rss.getString("HOST"));
		txtKULL.setText(rss.getString("KULLANICI"));
		txtPWD.setText(ENCRYPT_DECRYPT_STRING.dCRYPT_manual(rss.getBytes("SIFRE"))) ;
		txtSUNUCU.setText(rss.getString("SURUCU"));
		txtZMNASIMI.setText(rss.getString("ZMN_ASIMI"));
		txtPORT.setText(rss.getString("PORT"));
		txtSURUCU.setText(rss.getString("SURUCU_YER"));
		txtESKIYEDEK.setText(rss.getString("ESKI_YEDEK"));
	}
	private void ftpKONTROL() throws HeadlessException, IOException
	{
		try
		{
		FTPClient ftp = new FTPClient();
		ftp.connect(txtHOST.getText(),Integer.parseInt(txtPORT.getText()));
		if(!ftp.login(txtKULL.getText(),oac.sDONDUR.sDONDUR(txtPWD)))
		{
			ftp.logout();
			JOptionPane.showMessageDialog(null, "Baglanti Hatasi.......",  "OBS Indirme", JOptionPane.ERROR_MESSAGE);   
		}
		else
		{
			ftp.logout();
			JOptionPane.showMessageDialog(null, "Baglanti Gerceklesti.......",  "OBS Indirme", JOptionPane.ERROR_MESSAGE);  
		}
	} 
	catch (Exception e1)
	{
		JOptionPane.showMessageDialog(null, e1.getMessage(),  "OBS Backup", JOptionPane.ERROR_MESSAGE);  
	}	
		
	}
	private static void bilgi_DOLDUR(ResultSet rss) throws SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		if (!rss.isBeforeFirst() ) {  
			return;
		} 
		if(rss.getBoolean("DURUM"))
		{
			chckbxDRM.setSelected(true);
		}
		else
		{
			chckbxDRM.setSelected(false);
		}
		if(rss.getBoolean("GONDERILDIGINDE"))
		{
			chckbxGON.setSelected(true);
		}
		else
		{
			chckbxHATA.setSelected(false);
		}
		
		
		txtGON_ISIM.setText(rss.getString("GON_ISIM"));
		txtGON_ADRES.setText(rss.getString("GON_HESAP"));
		txtALICI.setText(rss.getString("ALICI"));
		txtKONU.setText(rss.getString("KONU"));
		txtSMTP.setText(rss.getString("SMTP"));
		txtSMTP_PORT.setText(rss.getString("SMTP_PORT"));
		txtSMTP_KULL.setText(rss.getString("KULLANICI"));
		txtSMTP_PWD.setText(ENCRYPT_DECRYPT_STRING.dCRYPT_manual(rss.getBytes("SIFRE"))) ;
		chckbxSSL.setSelected(rss.getBoolean("SSL"));
		chckbxTSL.setSelected(rss.getBoolean("TSL"));
	}
	private static void yedek_DOLDUR(ResultSet rss) throws SQLException
	{
		if (!rss.isBeforeFirst() ) {  
			return;
		} 
		txtHER.setText(rss.getString("SAAT"));
		chckbxPTESI.setSelected(rss.getBoolean("P_TESI"));
		chckbxSALI.setSelected(rss.getBoolean("SALI"));
		chckbxCAR.setSelected(rss.getBoolean("CARS"));
		chckbxPER.setSelected(rss.getBoolean("PERS"));
		chckbxCUM.setSelected(rss.getBoolean("CUMA"));
		chckbxCTESI.setSelected(rss.getBoolean("C_TESI"));
		chckbxPAZ.setSelected(rss.getBoolean("PAZAR"));
		
		spinBAS.setValue(rss.getDate("BASLAMA")); 
		spinBIT.setValue(rss.getDate("BITIS")); 

	}
	private void bilgiKAYIT() throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, SQLException
	{
		 sqll.bilgilendirme_kayit_sil(txtEMIR_ISMI.getText());
         sqll.bilgilendirme_ismi_kayit(txtEMIR_ISMI.getText(), chckbxDURUM.isSelected(), chckbxGON.isSelected(), chckbxHATA.isSelected(), txtGON_ISIM.getText(),
        		 txtGON_ADRES.getText(),txtALICI.getText(), txtKONU.getText(), txtSMTP.getText(), txtSMTP_PORT.getText(), 
        		 				txtKULL.getText(),oac.sDONDUR.sDONDUR(txtSMTP_PWD), chckbxSSL.isSelected(), chckbxTSL.isSelected());
	}
	private void yedeklemeKAYIT() throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, SQLException, ParseException
	{
		 boolean  drm  = false ;
		           if(chckbxPTESI.isSelected())
		           {
		        	   drm = true;
		           }
		           if(chckbxSALI.isSelected())
		           {
		        	   drm = true;
		           }
		           if(chckbxCAR.isSelected())
		           {
		        	   drm = true;
		           }
		           if(chckbxPER.isSelected())
		           {
		        	   drm = true;
		           }
		           if(chckbxCUM.isSelected())
		           {
		        	   drm = true;
		           }
		           if(chckbxCTESI.isSelected())
		           {
		        	   drm =true;
		           }
		           if(chckbxPAZ.isSelected())
		           {
		        	   drm = true;
		           }
		          if( drm == false)
		          {
		        	  sqll.durum_kayit_durum(txtEMIR_ISMI.getText(), false);
		          }
		          else
		          {
		        	  sqll.durum_kayit_durum(txtEMIR_ISMI.getText(), true);
		          }
		          
		        //  If RTBaslama.Value > RTBitis.Value Then
	           //     RadMessageBox.Show(Me, "Bitis Zamani Baslangic Zamanindan Kucuk olamaz", "Yedekleme Bilgileri", MessageBoxButtons.OK, RadMessageIcon.Exclamation, MessageBoxDefaultButton.Button1, RightToLeft.No)
	         //       Exit Sub
	          //  End If
	          
	           sqll.yedekleme_kayit_sil(txtEMIR_ISMI.getText());
	           // Dim bir, iki As TimeSpan
	          //  bir = New TimeSpan(RTBaslama.Value.Value.Hour, RTBaslama.Value.Value.Minute, 0)
	          //  iki = New TimeSpan(RTBitis.Value.Value.Hour, RTBitis.Value.Value.Minute, 0)
	
	           Object value = spinBAS.getValue();
	           Object value2 = spinBIT.getValue();
	           Date dateBAS = null;
	           Date dateBIT = null;
               SimpleDateFormat format = new SimpleDateFormat("HH:mm");
               
               if (value instanceof Date) {
                   Date date = (Date)value;
                    String time = format.format(date);
                    dateBAS = (Date) format.parse(time);      
                  
                   Date date2 = (Date)value2;
                   String time2 = format.format(date2);
                    dateBIT = (Date) format.parse(time2);     
               }
         
             
	          sqll.yedekleme_ismi_kayit(txtEMIR_ISMI.getText(),txtHER.getText(),	chckbxPTESI.isSelected(), chckbxSALI.isSelected(), chckbxCAR.isSelected(),
	        		  chckbxPER.isSelected(), chckbxCUM.isSelected(),chckbxCTESI.isSelected(), chckbxPAZ.isSelected(), dateBAS,dateBIT);

		
	}
}
@SuppressWarnings({ "serial", "rawtypes" })
class CheckListRenderer extends JCheckBox implements ListCellRenderer {
	  public Component getListCellRendererComponent(JList list, Object value,
	      int index, boolean isSelected, boolean hasFocus) {
	    setEnabled(list.isEnabled());
	    setSelected(((CheckListItem) value).isSelected());
	    setFont(list.getFont());
	    setBackground(list.getBackground());
	    setForeground(list.getForeground());
	    setText(value.toString());
	    return this;
	  }
	}
class CheckListItem {

	  private String label;
	  private boolean isSelected = false;

	  public CheckListItem(String label) {
	    this.label = label;
	  }

	  public boolean isSelected() {
	    return isSelected;
	  }

	  public void setSelected(boolean isSelected) {
	    this.isSelected = isSelected;
	  }

	  @Override
	  public String toString() {
	    return label;
	  }
	}

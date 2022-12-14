package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import OBS_C_2025.ADRESS_DEGISKENLER;
import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.ImagePanel;
import OBS_C_2025.JTextFieldLimit;

public class ADRES_GIRIS extends JInternalFrame {

	private static JTextField txtkayit;
	private static JTextField txtkodu;
	private static JTextField txtunvan;
	private static JTextField txtyetkili;
	private static JTextField txtadres1;
	private static JTextField txtadres2;
	private static JTextField txtvd;
	private static JTextField txttel1;
	private static JTextField txttel3;
	private static JTextField txtn1;
	private static JTextField txtweb;
	private static JTextField txtoz1;
	private static JTextField txtaciklama;
	private static JTextField txtsemt;
	private static JTextField txtsehir;
	private static JTextField txtvn;
	private static JTextField txttel2;
	private static JTextField txtfax;
	private static JTextField txtn2;
	private static JTextField txtmail;
	private static JTextField txtn3;
	private static JCheckBox chcsms ;
	private static JCheckBox chcmail ;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(oac._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	static ResultSet rs = null ;
	private static int kayit_sayi = 0 ;
	private static JTextField txtpkodu;
	private static JTextField txtozel;
	private static JTextField txtoz2;
	private static JTextField txtarama;

	private static JCheckBox chcbas ;
	private static JCheckBox chcici ;
	private static 	 ImagePanel imagePanel ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADRES_GIRIS frame = new ADRES_GIRIS();
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
	public ADRES_GIRIS() {
		setTitle("ADRES GIRISI");
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 846, 550);


		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(28, 58, 787, 2);
		separator.setForeground(new Color(0, 191, 255));
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(28, 103, 787, 2);
		separator_1.setForeground(new Color(0, 191, 255));
		panel.add(separator_1);

		JButton btnNewButton = new JButton("|<<");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doldur("ILK");
			}
		});
		btnNewButton.setBounds(102, 69, 64, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("<<");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doldur("G");
			}
		});
		btnNewButton_1.setBounds(162, 69, 54, 23);
		panel.add(btnNewButton_1);

		txtkayit = new JTextField();
		txtkayit.setForeground(new Color(0, 0, 205));
		txtkayit.setEditable(false);
		txtkayit.setHorizontalAlignment(SwingConstants.CENTER);
		txtkayit.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtkayit.setBounds(222, 70, 74, 20);
		panel.add(txtkayit);
		txtkayit.setColumns(10);

		JButton btnNewButton_2 = new JButton(">>");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doldur("I");
			}
		});
		btnNewButton_2.setBounds(301, 69, 54, 23);
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton(">>|");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doldur("SON");
			}
		});
		btnNewButton_3.setBounds(351, 69, 64, 23);
		panel.add(btnNewButton_3);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(28, 141, 787, 2);
		separator_2.setForeground(new Color(0, 191, 255));
		panel.add(separator_2);

		JLabel lblNewLabel_1 = new JLabel("Kodu");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(28, 116, 46, 14);
		panel.add(lblNewLabel_1);

		txtkodu = new JTextField();
		txtkodu.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String sonuc = "";

				try {
					sonuc = a_Access.kod_ismi(txtkodu.getText());
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				if ( ! sonuc.equals("") )
				{
					txtunvan.setText(sonuc);
					txtunvan.setForeground(Color.red);
				}
				else
				{
					txtunvan.setForeground(Color.black);
					txtunvan.setText("");
				}
			}
		});
		txtkodu.setEnabled(false);
		txtkodu.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtkodu.setBounds(102, 114, 137, 20);
		txtkodu.setDocument(new JTextFieldLimit(12));
		panel.add(txtkodu);
		txtkodu.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Unvan");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(276, 116, 46, 14);
		panel.add(lblNewLabel_3);

		txtunvan = new JTextField();
		txtunvan.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtunvan.setBounds(354, 114, 301, 20);
		txtunvan.setDocument(new JTextFieldLimit(35));
		panel.add(txtunvan);
		txtunvan.setColumns(10);

		txtyetkili = new JTextField();
		txtyetkili.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtyetkili.setBounds(102, 322, 313, 20);
		txtyetkili.setDocument(new JTextFieldLimit(30));
		panel.add(txtyetkili);
		txtyetkili.setColumns(10);

		txtadres1 = new JTextField();
		txtadres1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtadres1.setBounds(102, 160, 313, 20);
		txtadres1.setDocument(new JTextFieldLimit(35));
		panel.add(txtadres1);
		txtadres1.setColumns(10);

		txtadres2 = new JTextField();
		txtadres2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtadres2.setBounds(102, 187, 313, 20);
		txtadres2.setDocument(new JTextFieldLimit(35));
		panel.add(txtadres2);
		txtadres2.setColumns(10);

		txtvd = new JTextField();
		txtvd.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtvd.setBounds(102, 241, 194, 20);
		txtvd.setDocument(new JTextFieldLimit(25));
		panel.add(txtvd);
		txtvd.setColumns(10);

		txttel1 = new JTextField();
		txttel1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txttel1.setColumns(10);
		txttel1.setBounds(102, 268, 194, 20);
		txttel1.setDocument(new JTextFieldLimit(25));
		panel.add(txttel1);

		txttel3 = new JTextField();
		txttel3.setFont(new Font("Tahoma", Font.BOLD, 11));
		txttel3.setColumns(10);
		txttel3.setBounds(102, 295, 194, 20);
		txttel3.setDocument(new JTextFieldLimit(25));
		panel.add(txttel3);

		txtn1 = new JTextField();
		txtn1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtn1.setColumns(10);
		txtn1.setBounds(102, 349, 313, 20);
		txtn1.setDocument(new JTextFieldLimit(30));
		panel.add(txtn1);

		txtweb = new JTextField();
		txtweb.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtweb.setColumns(10);
		txtweb.setBounds(102, 457, 313, 20);
		txtweb.setDocument(new JTextFieldLimit(50));
		panel.add(txtweb);

		txtoz1 = new JTextField();
		txtoz1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtoz1.setColumns(10);
		txtoz1.setBounds(505, 322, 150, 20);
		txtoz1.setDocument(new JTextFieldLimit(15));
		panel.add(txtoz1);

		txtaciklama = new JTextField();
		txtaciklama.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtaciklama.setColumns(10);
		txtaciklama.setBounds(102, 484, 553, 20);
		txtaciklama.setDocument(new JTextFieldLimit(50));
		panel.add(txtaciklama);

		imagePanel = new ImagePanel();
		imagePanel.setBounds(665, 272, 155, 175);
		imagePanel.setBorder(new LineBorder(new Color(95, 158, 160), 2,true));
		panel.add( imagePanel);

		chcsms = new JCheckBox("Sms Gonder");
		chcsms.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chcsms.setSelected(true);
		chcsms.setBounds(505, 411, 124, 23);
		panel.add(chcsms);

		JLabel lblNewLabel_6 = new JLabel("Yetkili");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_6.setBounds(28, 327, 46, 14);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Adres 1");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(28, 165, 46, 14);
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Adres 2");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8.setBounds(28, 192, 46, 14);
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Vergi D.");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_9.setBounds(28, 246, 64, 14);
		panel.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Tel 1");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(28, 273, 46, 14);
		panel.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Tel 3");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_11.setBounds(28, 300, 46, 14);
		panel.add(lblNewLabel_11);

		JLabel lblNewLabel_8_1 = new JLabel("Not 1");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8_1.setBounds(28, 354, 64, 14);
		panel.add(lblNewLabel_8_1);

		JLabel lblNewLabel_9_1 = new JLabel("Web");
		lblNewLabel_9_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_9_1.setBounds(28, 462, 46, 14);
		panel.add(lblNewLabel_9_1);

		JLabel lblNewLabel_10_1 = new JLabel("Oz Kod 1");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10_1.setBounds(432, 327, 54, 14);
		panel.add(lblNewLabel_10_1);

		JLabel lblNewLabel_11_1 = new JLabel("Aciklama");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_11_1.setBounds(28, 489, 64, 14);
		panel.add(lblNewLabel_11_1);

		JLabel lblNewLabel_7_1 = new JLabel("Semt");
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7_1.setBounds(432, 165, 46, 14);
		panel.add(lblNewLabel_7_1);

		JLabel lblNewLabel_8_2 = new JLabel("Sehir");
		lblNewLabel_8_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8_2.setBounds(432, 192, 46, 14);
		panel.add(lblNewLabel_8_2);

		JLabel lblNewLabel_9_2 = new JLabel("Vergi No.");
		lblNewLabel_9_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_9_2.setBounds(432, 246, 64, 14);
		panel.add(lblNewLabel_9_2);

		JLabel lblNewLabel_10_2 = new JLabel("Tel 2");
		lblNewLabel_10_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10_2.setBounds(432, 273, 46, 14);
		panel.add(lblNewLabel_10_2);

		JLabel lblNewLabel_11_2 = new JLabel("Fax");
		lblNewLabel_11_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_11_2.setBounds(432, 300, 46, 14);
		panel.add(lblNewLabel_11_2);

		JLabel lblNewLabel_8_1_1 = new JLabel("Not 2");
		lblNewLabel_8_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8_1_1.setBounds(28, 381, 64, 14);
		panel.add(lblNewLabel_8_1_1);

		JLabel lblNewLabel_9_1_1 = new JLabel("E Mail");
		lblNewLabel_9_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_9_1_1.setBounds(28, 435, 46, 14);
		panel.add(lblNewLabel_9_1_1);

		txtsemt = new JTextField();
		txtsemt.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtsemt.setBounds(506, 160, 202, 20);
		txtsemt.setDocument(new JTextFieldLimit(25));
		panel.add(txtsemt);
		txtsemt.setColumns(10);

		txtsehir = new JTextField();
		txtsehir.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtsehir.setBounds(506, 187, 202, 20);
		txtsehir.setDocument(new JTextFieldLimit(25));
		panel.add(txtsehir);
		txtsehir.setColumns(10);

		txtvn = new JTextField();
		txtvn.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtvn.setBounds(506, 241, 202, 20);
		txtvn.setDocument(new JTextFieldLimit(15));
		panel.add(txtvn);
		txtvn.setColumns(10);

		txttel2 = new JTextField();
		txttel2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txttel2.setBounds(506, 268, 150, 20);
		txttel2.setDocument(new JTextFieldLimit(25));
		panel.add(txttel2);
		txttel2.setColumns(10);

		txtfax = new JTextField();
		txtfax.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtfax.setBounds(506, 295, 150, 20);
		txtfax.setDocument(new JTextFieldLimit(25));
		panel.add(txtfax);
		txtfax.setColumns(10);

		txtn2 = new JTextField();
		txtn2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtn2.setBounds(102, 376, 313, 20);
		txtn2.setDocument(new JTextFieldLimit(30));
		panel.add(txtn2);
		txtn2.setColumns(10);

		txtmail = new JTextField();
		txtmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtmail.setBounds(102, 430, 313, 20);
		txtmail.setDocument(new JTextFieldLimit(50));
		panel.add(txtmail);
		txtmail.setColumns(10);

		txtn3 = new JTextField();
		txtn3.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtn3.setColumns(10);
		txtn3.setBounds(102, 403, 313, 20);
		txtn3.setDocument(new JTextFieldLimit(30));
		panel.add(txtn3);

		JLabel lblNewLabel_8_1_2 = new JLabel("Not 3");
		lblNewLabel_8_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8_1_2.setBounds(28, 408, 64, 14);
		panel.add(lblNewLabel_8_1_2);

		JButton btnNewButton_4 = new JButton("Sec");
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
				chooser.setApproveButtonToolTipText("Resim Sec");
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("Resim Dosyalari", "jpg", "png", "gif", "bmp"));
				chooser.setApproveButtonMnemonic('s');
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					File file = chooser.getSelectedFile();
					ImageIcon icon = new ImageIcon(file.getPath());
					ImageIcon imageIcon = new ImageIcon(FIT_IMAGE.image(icon.getImage(), 155, 175));
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
		btnNewButton_4.setBounds(665, 480, 73, 23);
		panel.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Temizle");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lblNewLabel_5.setIcon(null);
				imagePanel.setImage(null);
			}
		});
		btnNewButton_5.setBounds(746, 480, 73, 23);
		panel.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				ADRES_LISTE asp ;
				asp = new ADRES_LISTE();
				asp.show();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		btnNewButton_6.setToolTipText("Hesap Plani");
		btnNewButton_6.setIcon(new ImageIcon(H_PLANI.class.getResource("/ICONLAR/user-16.png")));
		btnNewButton_6.setBounds(665, 114, 22, 23);
		panel.add(btnNewButton_6);

		JLabel lblNewLabel_8_2_1 = new JLabel("Posta Kodu");
		lblNewLabel_8_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8_2_1.setBounds(28, 219, 74, 14);
		panel.add(lblNewLabel_8_2_1);

		txtpkodu = new JTextField();
		txtpkodu.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpkodu.setColumns(10);
		txtpkodu.setBounds(102, 214, 194, 20);
		txtpkodu.setDocument(new JTextFieldLimit(10));
		panel.add(txtpkodu);

		JLabel lblNewLabel_11_2_1 = new JLabel("Ozel");
		lblNewLabel_11_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_11_2_1.setBounds(431, 219, 46, 14);
		panel.add(lblNewLabel_11_2_1);

		txtozel = new JTextField();
		txtozel.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtozel.setColumns(10);
		txtozel.setBounds(505, 214, 203, 20);
		txtozel.setDocument(new JTextFieldLimit(30));
		panel.add(txtozel);

		txtoz2 = new JTextField();
		txtoz2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtoz2.setColumns(10);
		txtoz2.setBounds(505, 349, 150, 20);
		txtoz2.setDocument(new JTextFieldLimit(15));
		panel.add(txtoz2);

		JLabel lblNewLabel_10_1_1 = new JLabel("Oz Kod 2");
		lblNewLabel_10_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10_1_1.setBounds(432, 354, 56, 14);
		panel.add(lblNewLabel_10_1_1);

		chcmail = new JCheckBox("Mail Gonder");
		chcmail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chcmail.setSelected(true);
		chcmail.setBounds(505, 437, 124, 23);
		panel.add(chcmail);

		txtarama = new JTextField();
		txtarama.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtarama.setBounds(102, 27, 288, 20);
		txtarama.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				if (txtarama.getText().equals(""))   { hisset("M_Kodu", ""); return ;};
				arama();
			}
			public void removeUpdate(DocumentEvent e) {
				if (txtarama.getText().equals(""))   { hisset("M_Kodu", ""); return ;};
				arama();
			}
			public void insertUpdate(DocumentEvent e) {
				if (txtarama.getText().equals(""))   { hisset("M_Kodu", ""); return ;};
				arama();
			}
		});
		panel.add(txtarama);
		txtarama.setColumns(10);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(28, 11, 787, 2);
		separator_3.setForeground(new Color(0, 191, 255));
		panel.add(separator_3);

		chcbas = new JCheckBox("Ile Baslayan");
		chcbas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chcbas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chcbas.isSelected())
				{
					chcici.setSelected(false);
				}
				else
				{
					chcici.setSelected(true);	
				}
			}
		});
		chcbas.setSelected(true);
		chcbas.setBounds(464, 26, 97, 23);
		panel.add(chcbas);

		chcici = new JCheckBox("Icinde");
		chcici.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chcici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chcici.isSelected())
				{
					chcbas.setSelected(false);
				}
				else
				{
					chcbas.setSelected(true);	
				}
			}
		});
		chcici.setBounds(595, 26, 97, 23);
		panel.add(chcici);

		JLabel lblNewLabel = new JLabel("Arama");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(28, 31, 64, 14);
		panel.add(lblNewLabel);

		long startTime = System.currentTimeMillis(); 
		hisset("M_Kodu", "");
		long endTime = System.currentTimeMillis();
		long estimatedTime = endTime - startTime; 
		double seconds = (double)estimatedTime/1000; 
		OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

	}
	private static void hisset(String siralama,String arama) 
	{
		try {
			temizle();
			rs = a_Access.adres(siralama,arama);
			if (!rs.isBeforeFirst() ) {  
				txtkayit.setText(rs.getRow() + "/" +   String.valueOf(kayit_sayi));
				return;
			} 
			else
			{
				rs.last();
				kayit_sayi = rs.getRow();
				rs.first();
				doldur("ILK");
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Hesap Plani", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private static void doldur(String nereye)
	{
		try {
			long startTime = System.currentTimeMillis(); 
			if (txtkodu.isEnabled()) { 
				txtkodu.setEnabled(false);
				hisset("M_Kodu", "");
			};
			txtkodu.setEnabled(false);
			if ( rs.getRow()  == 0) return ;
			if (nereye.equals("I"))
			{
				if (rs.getRow() == kayit_sayi)
				{ return ; }
				rs.next();
			}
			else if (nereye.equals("G"))
			{
				if (rs.getRow() == 1)
				{ return ;}
				rs.previous();
			}
			else if (nereye.equals("ILK"))
			{
				rs.first();
			}
			else if (nereye.equals("SON"))
			{
				rs.last();
			}

			txtkayit.setText(rs.getRow() + "/" +   String.valueOf(kayit_sayi));
			txtkodu.setText(rs.getString("M_Kodu"));
			txtunvan.setText(rs.getString("Adi"));
			txtyetkili.setText(rs.getString("Yetkili"));		       
			txtadres1.setText(rs.getString("Adres_1"));
			txtadres2.setText(rs.getString("Adres_2"));
			txtsemt.setText(rs.getString("Semt"));
			txtsehir.setText(rs.getString("Sehir"));
			txtvd.setText(rs.getString("Vergi_Dairesi"));
			txtvn.setText(rs.getString("Vergi_No"));
			txttel1.setText(rs.getString("Tel_1"));
			txttel2.setText(rs.getString("Tel_2"));
			txttel3.setText(rs.getString("Tel_3"));
			txtfax.setText(rs.getString("Fax"));
			txtoz1.setText(rs.getString("Ozel_Kod_1"));
			txtoz2.setText(rs.getString("Ozel_Kod_2"));
			txtpkodu.setText(rs.getString("Posta_Kodu"));
			txtweb.setText(rs.getString("Web"));
			txtmail.setText(rs.getString("E_Mail"));
			txtozel.setText(rs.getString("Ozel"));
			txtaciklama.setText(rs.getString("Aciklama"));
			txtn1.setText(rs.getString("Not_1"));
			txtn2.setText(rs.getString("Not_2"));
			txtn3.setText(rs.getString("Not_3"));
			chcsms.setSelected(rs.getBoolean("Sms_Gonder"));
			chcmail.setSelected(rs.getBoolean("Mail_Gonder"));
			if (  rs.getBytes("Resim") != null)
			{
				byte[] img = rs.getBytes("Resim");
				ImageIcon image = new ImageIcon(img);
				Image im = image.getImage();
				//Image myImg = im.getScaledInstance(155, 175,Image.SCALE_SMOOTH);
				ImageIcon newImage = new ImageIcon(im);
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
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Hesap Plani", JOptionPane.PLAIN_MESSAGE);
		}
	}
	public static void yeni()
	{
		temizle();
		txtkodu.setEnabled(true);
		txtkodu.requestFocus();
	}
	private static void temizle()
	{
		txtkodu.setText("");
		txtunvan.setText("");
		txtyetkili.setText("");	       
		txtadres1.setText("");
		txtadres2.setText("");
		txtsemt.setText("");
		txtsehir.setText("");
		txtvd.setText("");
		txtvn.setText("");
		txttel1.setText("");
		txttel2.setText("");
		txttel3.setText("");
		txtfax.setText("");
		txtn1.setText("");
		txtn3.setText("");
		txtn2.setText("");
		txtweb.setText("");
		txtmail.setText("");
		txtoz1.setText("");
		txtoz2.setText("");
		txtozel.setText("");
		txtpkodu.setText("");
		txtaciklama.setText("");
		chcsms.setSelected(true);
		chcmail.setSelected(true);
		// lblNewLabel_5.setIcon(null);
		imagePanel.setImage(null);
		kayit_sayi =0 ;
	}
	private void arama()
	{
		long startTime = System.currentTimeMillis(); 
		if (chcbas.isSelected())
		{
			hisset("M_Kodu", "WHERE  M_Kodu like  '" + txtarama.getText() + "%' OR  Adi Like '" + txtarama.getText() + "%'");
		}
		else
		{
			hisset("M_Kodu", "WHERE  M_Kodu like  '%" + txtarama.getText() + "%' OR  Adi Like '%" + txtarama.getText() + "%'");
		}
		long endTime = System.currentTimeMillis();
		long estimatedTime = endTime - startTime; 
		double seconds = (double)estimatedTime/1000; 
		OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
	}
	public static void kayit()
	{
		if (  txtkodu.getText().equals("") &&  txtunvan.getText().equals("") ) return ;
		try
		{
			InputStream   fis =null;
			long startTime = System.currentTimeMillis(); 
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

			a_Access.sil(txtkodu.getText(),txtunvan.getText(),
					"Hesap Kodu:" + txtkodu.getText() + " Hesap Silme","", BAGLAN_LOG.adrLogDizin);

			ADRESS_DEGISKENLER aDEG = new ADRESS_DEGISKENLER();
			aDEG.kodu = txtkodu.getText();
			aDEG.adi = txtunvan.getText();
			aDEG.adr1 = txtadres1.getText();
			aDEG.adr2 =  txtadres2.getText();
			aDEG.semt =  txtsemt.getText();
			aDEG.sehir =  txtsehir.getText();
			aDEG.pkodu = txtpkodu.getText() ;
			aDEG.vd = txtvd.getText() ;
			aDEG.vn = txtvn.getText();
			aDEG.fax = txtfax.getText() ;
			aDEG.tel1 = txttel1.getText();
			aDEG.tel2 =txttel2.getText() ;
			aDEG.tel3 = txttel3.getText();
			aDEG.ozel = txtozel.getText() ;
			aDEG.yet = txtyetkili.getText();
			aDEG.e_ma =txtmail.getText();
			aDEG.n1 =txtn1.getText();
			aDEG.n2 = txtn2.getText();
			aDEG.n3 =  txtn3.getText();
			aDEG.acik = txtaciklama.getText();
			aDEG.sms = chcsms.isSelected();
			aDEG.mailg =  chcmail.isSelected();
			aDEG.ok1 =txtoz1.getText();
			aDEG.ok2 =txtoz2.getText();
			aDEG.web = txtweb.getText();
			aDEG.usr = GLOBAL.KULL_ADI;
			aDEG.resim = fis ;
			a_Access.adres_kayit(aDEG,
					"Hesap Kodu:" + txtkodu.getText() + " Hesap Unvan:" + txtunvan.getText(),"", BAGLAN_LOG.adrLogDizin);

			temizle();
			hisset("M_Kodu", "");
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Adres Kayit", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void sil()
	{
		if ( txtkodu.getText().equals("") &&  txtunvan.getText().equals("")) return ;
		int g =  JOptionPane.showOptionDialog( null,  "Kayit Dosyadan Silinecek ..?"  ,
				"Adres Silme",   JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,     //no custom icon
				oac.options,  //button titles
				oac.options[1]); //default button
		if(g != 0 ) { return;	}
		try
		{
			a_Access.sil(txtkodu.getText(),txtunvan.getText(),
					"Hesap Kodu:" + txtkodu.getText() + " Hesap Silme","", BAGLAN_LOG.adrLogDizin);
			temizle();
			hisset("M_Kodu", "");
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kayit Silme", JOptionPane.PLAIN_MESSAGE);
		}
	}
}

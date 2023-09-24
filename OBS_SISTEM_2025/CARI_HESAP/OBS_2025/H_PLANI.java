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

import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.ImagePanel;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.lOG_BILGI;

@SuppressWarnings({"serial" , "static-access" , "deprecation"})
public class H_PLANI extends JInternalFrame {
	private static JTextField txtkayit;
	private static JTextField txtkodu;
	private static JTextField txtunvan;
	private static JTextField txtkarton;
	private static JTextField txthcinsi;
	private static JTextField txtyetkili;
	private static JTextField txtadres1;
	private static JTextField txtadres2;
	private static JTextField txtvd;
	private static JTextField txttel1;
	private static JTextField txttel3;
	private static JTextField txto1;
	private static JTextField txtweb;
	private static JTextField txtkimlik;
	private static JTextField txtaciklama;
	private static JTextField txtsemt;
	private static JTextField txtsehir;
	private static JTextField txtvn;
	private static JTextField txttel2;
	private static JTextField txtfax;
	private static JTextField txto3;
	private static JTextField txtmail;
	private static JTextField txto2;
	private static JCheckBox chcsms ;
	private static ImagePanel imagePanel ;
	private static JCheckBox chcbas ;
	private static JCheckBox chcicin ;

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static CARI_ACCESS c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	//private static CARI_ACCESS  c_Access ;
	static ResultSet rs = null ;
	private static int kayit_sayi = 0 ;
	private JTextField txtarama;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					H_PLANI frame = new H_PLANI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public H_PLANI()  {
		setTitle("HESAP GIRISI");
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 846, 488);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(28, 52, 791, 2);
		separator.setForeground(new Color(0, 191, 255));
		panel.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(28, 97, 791, 2);
		separator_1.setForeground(new Color(0, 191, 255));
		panel.add(separator_1);

		JButton btnNewButton = new JButton("|<<");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doldur("ILK");
			}
		});
		btnNewButton.setBounds(102, 63, 60, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("<<");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doldur("G");
			}
		});
		btnNewButton_1.setBounds(162, 63, 54, 23);
		panel.add(btnNewButton_1);

		txtkayit = new JTextField();
		txtkayit.setForeground(new Color(0, 0, 205));
		txtkayit.setEditable(false);
		txtkayit.setHorizontalAlignment(SwingConstants.CENTER);
		txtkayit.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtkayit.setBounds(222, 64, 60, 20);
		panel.add(txtkayit);
		txtkayit.setColumns(10);

		JButton btnNewButton_2 = new JButton(">>");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doldur("I");
			}
		});
		btnNewButton_2.setBounds(292, 63, 54, 23);
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton(">>|");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doldur("SON");
			}
		});
		btnNewButton_3.setBounds(342, 63, 60, 23);
		panel.add(btnNewButton_3);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(28, 169, 791, 2);
		separator_2.setForeground(new Color(0, 191, 255));
		panel.add(separator_2);

		JLabel lblNewLabel_1 = new JLabel("Kodu");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(28, 112, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Karton");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(28, 144, 46, 14);
		panel.add(lblNewLabel_2);

		txtkodu = new JTextField();
		txtkodu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sonuc = "";
				try {
					sonuc = c_Access.kod_ismi(txtkodu.getText());
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
		txtkodu.setBounds(102, 110, 137, 20);
		txtkodu.setDocument(new JTextFieldLimit(12));
		panel.add(txtkodu);
		

		JLabel lblNewLabel_3 = new JLabel("Unvan");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(276, 112, 46, 14);
		panel.add(lblNewLabel_3);

		txtunvan = new JTextField();
		txtunvan.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtunvan.setBounds(354, 110, 301, 20);
		txtunvan.setDocument(new JTextFieldLimit(50));
		panel.add(txtunvan);
		

		txtkarton = new JTextField();
		txtkarton.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtkarton.setBounds(102, 141, 86, 20);
		panel.add(txtkarton);
		txtkarton.setDocument(new JTextFieldLimit(5));

		JLabel lblNewLabel_4 = new JLabel("Hesap Cins");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(276, 144, 68, 14);
		panel.add(lblNewLabel_4);

		txthcinsi = new JTextField();
		txthcinsi.setFont(new Font("Tahoma", Font.BOLD, 12));
		txthcinsi.setBounds(354, 141, 68, 20);
		panel.add(txthcinsi);
		txthcinsi.setDocument(new JTextFieldLimit(3));

		txtyetkili = new JTextField();
		txtyetkili.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtyetkili.setBounds(102, 182, 254, 20);
		panel.add(txtyetkili);
		txtyetkili.setDocument(new JTextFieldLimit(30));

		txtadres1 = new JTextField();
		txtadres1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtadres1.setBounds(102, 209, 320, 20);
		panel.add(txtadres1);
		txtadres1.setDocument(new JTextFieldLimit(35));

		txtadres2 = new JTextField();
		txtadres2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtadres2.setBounds(102, 236, 320, 20);
		panel.add(txtadres2);
		txtadres2.setDocument(new JTextFieldLimit(35));

		txtvd = new JTextField();
		txtvd.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtvd.setBounds(102, 263, 196, 20);
		panel.add(txtvd);
		txtvd.setDocument(new JTextFieldLimit(25));

		txttel1 = new JTextField();
		txttel1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txttel1.setColumns(10);
		txttel1.setBounds(102, 290, 150, 20);
		txttel1.setDocument(new JTextFieldLimit(25));
		panel.add(txttel1);

		txttel3 = new JTextField();
		txttel3.setFont(new Font("Tahoma", Font.BOLD, 11));
		txttel3.setColumns(10);
		txttel3.setBounds(102, 317, 86, 20);
		txttel3.setDocument(new JTextFieldLimit(25));
		panel.add(txttel3);

		txto1 = new JTextField();
		txto1.setFont(new Font("Tahoma", Font.BOLD, 11));
		txto1.setColumns(10);
		txto1.setBounds(102, 344, 114, 20);
		txto1.setDocument(new JTextFieldLimit(15));
		panel.add(txto1);

		txtweb = new JTextField();
		txtweb.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtweb.setColumns(10);
		txtweb.setBounds(102, 371, 320, 20);
		txtweb.setDocument(new JTextFieldLimit(50));
		panel.add(txtweb);

		txtkimlik = new JTextField();
		txtkimlik.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtkimlik.setColumns(10);
		txtkimlik.setBounds(506, 371, 150, 20);
		txtkimlik.setDocument(new JTextFieldLimit(15));
		panel.add(txtkimlik);

		txtaciklama = new JTextField();
		txtaciklama.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtaciklama.setColumns(10);
		txtaciklama.setBounds(102, 425, 553, 20);
		txtaciklama.setDocument(new JTextFieldLimit(30));
		panel.add(txtaciklama);

		imagePanel = new ImagePanel();
		imagePanel.setBounds(664, 182, 155, 175);
		imagePanel.setBorder(new LineBorder(new Color(95, 158, 160), 2,true));
		panel.add( imagePanel);

		chcsms = new JCheckBox("Sms Gonder");
		chcsms.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chcsms.setSelected(true);
		chcsms.setBounds(503, 181, 124, 23);
		panel.add(chcsms);

		JLabel lblNewLabel_6 = new JLabel("Yetkili");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_6.setBounds(28, 187, 46, 14);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Adres 1");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(28, 214, 46, 14);
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Adres 2");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8.setBounds(28, 241, 46, 14);
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Vergi D.");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_9.setBounds(28, 268, 64, 14);
		panel.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Tel 1");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(28, 295, 46, 14);
		panel.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Tel 3");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_11.setBounds(28, 322, 46, 14);
		panel.add(lblNewLabel_11);

		JLabel lblNewLabel_8_1 = new JLabel("Oz Kod 1");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8_1.setBounds(28, 349, 64, 14);
		panel.add(lblNewLabel_8_1);

		JLabel lblNewLabel_9_1 = new JLabel("Web");
		lblNewLabel_9_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_9_1.setBounds(28, 376, 46, 14);
		panel.add(lblNewLabel_9_1);

		JLabel lblNewLabel_10_1 = new JLabel("TC Kimlik");
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10_1.setBounds(432, 376, 64, 14);
		panel.add(lblNewLabel_10_1);

		JLabel lblNewLabel_11_1 = new JLabel("Aciklama");
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_11_1.setBounds(28, 430, 64, 14);
		panel.add(lblNewLabel_11_1);

		JLabel lblNewLabel_7_1 = new JLabel("Semt");
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7_1.setBounds(432, 214, 46, 14);
		panel.add(lblNewLabel_7_1);

		JLabel lblNewLabel_8_2 = new JLabel("Sehir");
		lblNewLabel_8_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8_2.setBounds(432, 241, 46, 14);
		panel.add(lblNewLabel_8_2);

		JLabel lblNewLabel_9_2 = new JLabel("Vergi No.");
		lblNewLabel_9_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_9_2.setBounds(432, 267, 64, 14);
		panel.add(lblNewLabel_9_2);

		JLabel lblNewLabel_10_2 = new JLabel("Tel 2");
		lblNewLabel_10_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10_2.setBounds(432, 295, 46, 14);
		panel.add(lblNewLabel_10_2);

		JLabel lblNewLabel_11_2 = new JLabel("Fax");
		lblNewLabel_11_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_11_2.setBounds(432, 322, 46, 14);
		panel.add(lblNewLabel_11_2);

		JLabel lblNewLabel_8_1_1 = new JLabel("Oz Kod 3");
		lblNewLabel_8_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8_1_1.setBounds(432, 349, 64, 14);
		panel.add(lblNewLabel_8_1_1);

		JLabel lblNewLabel_9_1_1 = new JLabel("E Mail");
		lblNewLabel_9_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_9_1_1.setBounds(28, 403, 46, 14);
		panel.add(lblNewLabel_9_1_1);

		txtsemt = new JTextField();
		txtsemt.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtsemt.setBounds(506, 209, 150, 20);
		txtsemt.setDocument(new JTextFieldLimit(15));
		panel.add(txtsemt);
		

		txtsehir = new JTextField();
		txtsehir.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtsehir.setBounds(506, 236, 150, 20);
		panel.add(txtsehir);
		txtsehir.setDocument(new JTextFieldLimit(15));

		txtvn = new JTextField();
		txtvn.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtvn.setBounds(506, 263, 150, 20);
		panel.add(txtvn);
		txtvn.setDocument(new JTextFieldLimit(15));

		txttel2 = new JTextField();
		txttel2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txttel2.setBounds(506, 290, 150, 20);
		panel.add(txttel2);
		txttel2.setDocument(new JTextFieldLimit(25));

		txtfax = new JTextField();
		txtfax.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtfax.setBounds(506, 317, 150, 20);
		panel.add(txtfax);
		txtfax.setDocument(new JTextFieldLimit(25));

		txto3 = new JTextField();
		txto3.setFont(new Font("Tahoma", Font.BOLD, 11));
		txto3.setBounds(506, 344, 114, 20);
		panel.add(txto3);
		txto3.setDocument(new JTextFieldLimit(15));

		txtmail = new JTextField();
		txtmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtmail.setBounds(102, 398, 320, 20);
		panel.add(txtmail);
		txtmail.setDocument(new JTextFieldLimit(30));

		txto2 = new JTextField();
		txto2.setFont(new Font("Tahoma", Font.BOLD, 11));
		txto2.setColumns(10);
		txto2.setBounds(308, 344, 114, 20);
		txto3.setDocument(new JTextFieldLimit(15));
		panel.add(txto2);

		JLabel lblNewLabel_8_1_2 = new JLabel("Oz Kod 2");
		lblNewLabel_8_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8_1_2.setBounds(232, 349, 64, 14);
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
		btnNewButton_4.setBounds(666, 371, 73, 23);
		panel.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Temizle");
		btnNewButton_5.setMargin(new Insets(2, 5, 2, 14));
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imagePanel.setImage(null);
			}
		});
		btnNewButton_5.setBounds(742, 371, 73, 23);
		panel.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				try {
					HESAP_PLN hsp ;
					hsp = new HESAP_PLN();
					hsp.setLocation(853 ,191);
					hsp.show();
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null,  ex.getMessage(), "Hesap Plani", JOptionPane.ERROR_MESSAGE);
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				}

			}
		});
		btnNewButton_6.setToolTipText("Hesap Plani");
		btnNewButton_6.setIcon(new ImageIcon(H_PLANI.class.getResource("/ICONLAR/user-16.png")));
		btnNewButton_6.setBounds(665, 110, 22, 23);
		panel.add(btnNewButton_6);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(28, 11, 791, 2);
		separator_3.setForeground(new Color(0, 191, 255));
		panel.add(separator_3);

		txtarama = new JTextField();
		txtarama.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtarama.setColumns(10);
		txtarama.setBounds(102, 22, 288, 20);
		txtarama.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				if (txtarama.getText().equals(""))   { hisset( ""); return ;};
				arama();
			}
			public void removeUpdate(DocumentEvent e) {
				if (txtarama.getText().equals(""))   { hisset( ""); return ;};
				arama();
			}
			public void insertUpdate(DocumentEvent e) {
				if (txtarama.getText().equals(""))   { hisset( ""); return ;};
				arama();
			}
		});
		panel.add(txtarama);

		chcbas = new JCheckBox("Ile Baslayan");
		chcbas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chcbas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chcbas.isSelected())
				{
					chcicin.setSelected(false);
				}
				else
				{
					chcicin.setSelected(true);	
				}
			}
		});
		chcbas.setSelected(true);
		chcbas.setBounds(464, 22, 97, 23);
		panel.add(chcbas);

		chcicin = new JCheckBox("Icinde");
		chcicin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chcicin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chcicin.isSelected())
				{
					chcbas.setSelected(false);
				}
				else
				{
					chcbas.setSelected(true);	
				}
			}
		});
		chcicin.setBounds(595, 22, 97, 23);
		panel.add(chcicin);

		JLabel lblNewLabel = new JLabel("Arama");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(28, 27, 64, 14);
		panel.add(lblNewLabel);

		hisset("");

	}

	private static void hisset(String arama) 
	{
		try {
			rs = c_Access.hsp_pln(arama);
			if (!rs.isBeforeFirst() ) {  
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
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Hesap Plani", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void doldur(String nereye) 
	{
		try {
			if (txtkodu.isEnabled()) { 
				txtkodu.setEnabled(false);
				hisset("");
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
			txtkodu.setText(rs.getString("HESAP"));
			txtunvan.setText(rs.getString("UNVAN"));
			txtkarton.setText(rs.getString("KARTON"));
			txthcinsi.setText(rs.getString("HESAP_CINSI"));
			txtyetkili.setText(rs.getString("YETKILI"));		       
			txtadres1.setText(rs.getString("ADRES_1"));
			txtadres2.setText(rs.getString("ADRES_2"));
			txtsemt.setText(rs.getString("SEMT"));
			txtsehir.setText(rs.getString("SEHIR"));
			txtvd.setText(rs.getString("VERGI_DAIRESI"));
			txtvn.setText(rs.getString("VERGI_NO"));
			txttel1.setText(rs.getString("TEL_1"));
			txttel2.setText(rs.getString("TEL_2"));
			txttel3.setText(rs.getString("TEL_3"));
			txtfax.setText(rs.getString("FAX"));
			txto1.setText(rs.getString("OZEL_KOD_1"));
			txto2.setText(rs.getString("OZEL_KOD_2"));
			txto3.setText(rs.getString("OZEL_KOD_3"));
			txtweb.setText(rs.getString("WEB"));
			txtmail.setText(rs.getString("E_MAIL"));
			txtkimlik.setText(rs.getString("TC_KIMLIK"));
			txtaciklama.setText(rs.getString("ACIKLAMA"));
			chcsms.setSelected(rs.getBoolean("SMS_GONDER"));
			if (  rs.getBytes("RESIM") != null)
			{
				byte[] img = rs.getBytes("RESIM");
				ImageIcon image = new ImageIcon(img);
				Image im = image.getImage();
				// Image myImg = im.getScaledInstance(150, 175,Image.SCALE_SMOOTH);
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
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Hesap Plani", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void yeni()
	{
		temizle();
		txtkodu.setEnabled(true);
		txtkodu.requestFocus();
	}
	public static void kayit () 
	{
		if (  txtkodu.getText().equals("") ||  txtunvan.getText().equals("") ) return ;
		try
		{
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
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ(txtkodu.getText() + " Nolu Hesap Silme , Unvan:" + txtunvan.getText());
			lBILGI.seteVRAK("");
			
			c_Access.hsp_sil(txtkodu.getText(), lBILGI,  BAGLAN_LOG.cariLogDizin);
			lBILGI.setmESAJ(txtkodu.getText() + " Nolu Hesap Kayit , Unvan:" + txtunvan.getText());
			c_Access.hpln_kayit(txtkodu.getText(), txtunvan.getText(), txtkarton.getText(), txthcinsi.getText(),GLOBAL.KULL_ADI
					, lBILGI,  BAGLAN_LOG.cariLogDizin);
			c_Access.hpln_detay_kayit(txtkodu.getText()
					, txtyetkili.getText(), txtadres1.getText(), txtadres2.getText()
					, txtsemt.getText(), txtsehir.getText(), txtvd.getText(), txtvn.getText()
					, txttel1.getText(), txttel2.getText(), txttel3.getText(), txtfax.getText(), txto1.getText()
					, txto2.getText(), txto3.getText(), txtweb.getText(), txtmail.getText()
					, txtkimlik.getText(), txtaciklama.getText(),chcsms.isSelected() ,fis);

			temizle();
			hisset("");
			// Cursor = System.Windows.Forms.Cursors.Default
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Hesap Plani", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void sil()
	{
		if ( txtkodu.getText().equals("")) return ;

		int g =  JOptionPane.showOptionDialog( null,  "Kayit Dosyadan Silinecek ..?" + System.lineSeparator() + System.lineSeparator()  + 
				"Oncelikle Bu Hesaba Ait Fisleri Silmeniz" + System.lineSeparator() + System.lineSeparator() +"Tavsiye Olunur ...." ,
				"Cari Hesap Plani Silme",   JOptionPane.YES_NO_OPTION,	JOptionPane.QUESTION_MESSAGE,
				null,  	oac.options, 	oac.options[1]); //default button

		if(g != 0 ) { return;	}
		try
		{
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ(txtkodu.getText() + " Nolu Hesap Silme , Unvan:" + txtunvan.getText());
			lBILGI.seteVRAK("");
			
			c_Access.hsp_sil(txtkodu.getText(), lBILGI,  BAGLAN_LOG.cariLogDizin);
			temizle();
			hisset("");
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kayit Silme", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void temizle()
	{
		txtkodu.setText("");
		txtunvan.setText("");
		txtkarton.setText("");
		txthcinsi.setText("");
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
		txto1.setText("");
		txto2.setText("");
		txto3.setText("");
		txtweb.setText("");
		txtmail.setText("");
		txtkimlik.setText("");
		txtaciklama.setText("");
		chcsms.setSelected(true);
		imagePanel.setImage(null);
	}
	private void arama()
	{
		if (chcbas.isSelected())
		{
			hisset( "WHERE  HESAP like  '" + txtarama.getText() + "%' OR  UNVAN Like '" + txtarama.getText() + "%'");
		}
		else
		{
			hisset( "WHERE  HESAP like  '%" + txtarama.getText() + "%' OR  UNVAN Like '%" + txtarama.getText() + "%'");
		}
	}
}

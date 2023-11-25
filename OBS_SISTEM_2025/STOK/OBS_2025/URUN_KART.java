package OBS_2025;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.ImagePanel;
import OBS_C_2025.STOK_ACCESS;

import javax.swing.border.EtchedBorder;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JFormattedTextField;

@SuppressWarnings({"serial","static-access"})
public class URUN_KART extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);

	private static ResultSet rss = null ;
	private static int kayit_sayi = 0 ;
	private static JLabel lbluser ;
	private static JTextField txtkayit;
	public static  JTextField textField_1;
	private static JTextField txtkodu;
	private static JTextField txtadi;
	private static JTextField txtbirim;
	private static JTextField txtsinif;
	private static JTextField txtacik1;
	private static JTextField txtacik2;
	private static JTextField txtrecete;
	private static JTextField txtbarkod;
	private static JComboBox<String> cmbmensei ;
	private static JComboBox<String> cmbanagrup ;
	private static JComboBox<String> cmbaltgrup ;
	private static JComboBox<String> cmboz1 ;
	private static JComboBox<String> cmboz2; 
	public static ImagePanel imagePanel ;
	private JCheckBox chcbas ;
	private JCheckBox chcicin ;

	private JCheckBox chckbxKod ;
	private JCheckBox chckbxAdi ;
	private JCheckBox chckbxBarkod ;

	private static JFormattedTextField txtkusurat ;
	private static JFormattedTextField txtagirlik ;
	private static JFormattedTextField txtfiat1;
	private static JFormattedTextField txtfiat2;
	private static JFormattedTextField txtfiat3;

	/**
	 * Create the frame.
	 */
	public URUN_KART() {
		setTitle("URUN KARTI");
		setClosable(true);
		setBounds(0, 0,900, 580);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 92, 864, 2);
		separator.setForeground(new Color(0, 191, 255));
		panel.add(separator);

		JButton button = new JButton("|<<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				doldur("ILK");
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(88, 17, 64, 30);
		panel.add(button);

		JButton button_1 = new JButton("<<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				doldur("G");
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_1.setBounds(151, 17, 54, 30);
		panel.add(button_1);

		txtkayit = new JTextField();
		txtkayit.setHorizontalAlignment(SwingConstants.CENTER);
		txtkayit.setForeground(new Color(0, 0, 205));
		txtkayit.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtkayit.setEditable(false);
		txtkayit.setColumns(10);
		txtkayit.setBounds(208, 18, 74, 28);
		panel.add(txtkayit);

		JButton button_2 = new JButton(">>");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				doldur("I");
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_2.setBounds(285, 17, 54, 30);
		panel.add(button_2);

		JButton button_3 = new JButton(">>|");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				doldur("SON");
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		button_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_3.setBounds(338, 17, 64, 30);
		panel.add(button_3);

		textField_1 = new JTextField();
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				if (textField_1.getText().equals(""))   { hisset("Kodu",""); return ;};
				arama();
			}
			public void removeUpdate(DocumentEvent e) {
				if (textField_1.getText().equals(""))   { hisset("Kodu",""); return ;};
				arama();
			}
			public void insertUpdate(DocumentEvent e) {
				if (textField_1.getText().equals(""))   { hisset("Kodu",""); return ;};
				arama();
			}
		});
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_1.setBounds(88, 58, 314, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Siralama", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1.setBounds(490, 11, 175, 70);
		panel.add(panel_1);
		panel_1.setLayout(null);

		chckbxKod = new JCheckBox("Kod");
		chckbxKod.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxKod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxKod.isSelected())
				{
					chckbxAdi .setSelected(false);
					chckbxBarkod .setSelected(false);
				}
				else
				{
					chckbxAdi .setSelected(true);
					chckbxBarkod .setSelected(true);
				}
			}
		});
		chckbxKod.setSelected(true);
		chckbxKod.setBounds(6, 18, 63, 23);
		panel_1.add(chckbxKod);

		chckbxAdi = new JCheckBox("Adi");
		chckbxAdi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxAdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxAdi.isSelected())
				{
					chckbxKod .setSelected(false);
					chckbxBarkod .setSelected(false);
				}
				else
				{
					chckbxKod .setSelected(true);
					chckbxBarkod .setSelected(true);
				}
			}
		});
		chckbxAdi.setBounds(6, 40, 70, 23);
		panel_1.add(chckbxAdi);

		chckbxBarkod = new JCheckBox("Barkod");
		chckbxBarkod.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxBarkod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxBarkod.isSelected())
				{
					chckbxAdi .setSelected(false);
					chckbxKod .setSelected(false);
				}
				else
				{
					chckbxAdi .setSelected(true);
					chckbxKod .setSelected(true);
				}
			}
		});
		chckbxBarkod.setBounds(81, 18, 66, 23);
		panel_1.add(chckbxBarkod);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Arama", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_2.setBounds(690, 11, 175, 70);
		panel.add(panel_2);
		panel_2.setLayout(null);

		chcbas = new JCheckBox("Ile Baslayan");
		chcbas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chcbas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chcbas.isSelected())
				{
					chcicin .setSelected(false);
				}
				else
				{
					chcicin.setSelected(true);
				}
			}
		});
		chcbas.setBounds(18, 17, 97, 23);
		panel_2.add(chcbas);
		chcbas.setSelected(true);

		chcicin = new JCheckBox("Icinde");
		chcicin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chcicin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chcicin.isSelected())
				{
					chcbas .setSelected(false);
				}
				else
				{
					chcbas.setSelected(true);
				}
			}
		});
		chcicin.setBounds(18, 40, 97, 23);
		panel_2.add(chcicin);

		JLabel lblNewLabel = new JLabel("Kodu / Adi");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 61, 74, 14);
		panel.add(lblNewLabel);

		lbluser = new JLabel("....");
		lbluser.setForeground(new Color(165, 42, 42));
		lbluser.setBounds(650, 523, 160, 14);
		panel.add(lbluser);

		imagePanel = new ImagePanel();
		imagePanel.setBounds(650, 105, 220, 175);
		imagePanel.setBorder(new LineBorder(new Color(95, 158, 160), 2,true));
		panel.add( imagePanel);

		JButton button_4 = new JButton("");
		button_4.setToolTipText("Sec");
		button_4.setIcon(new ImageIcon(URUN_KART.class.getResource("/ICONLAR/icons8-add-folder-16.png")));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);

				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Surucu Seciniz");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Resim Sec");
				chooser.setApproveButtonToolTipText("Resim Sec");
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("Resim Dosyalari", "jpg", "png", "gif", "bmp"));
				chooser.setApproveButtonMnemonic('s');
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 

					try {
						File file = chooser.getSelectedFile();
						ImageIcon icon = new ImageIcon(file.getPath());
						ImageIcon imageIcon = new ImageIcon(FIT_IMAGE.image(icon.getImage(), 225, 175));
						BufferedImage bi = new BufferedImage(imageIcon .getIconWidth(), imageIcon .getIconHeight(), BufferedImage.TYPE_INT_RGB);
						Graphics2D g = bi.createGraphics();
						imageIcon.paintIcon(null, g, 0, 0);
						g.setColor(Color.WHITE);
						g.dispose();
						imagePanel.setImage(bi);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					}
				}
				else {
					imagePanel.setImage(null);
				}
			}
		});
		button_4.setBounds(730, 291, 60, 23);
		panel.add(button_4);

		JButton button_5 = new JButton("");
		button_5.setToolTipText("Sil");
		button_5.setIcon(new ImageIcon(URUN_KART.class.getResource("/ICONLAR/sil.png")));
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imagePanel.setImage(null);
			}
		});
		button_5.setBounds(809, 291, 60, 23);
		panel.add(button_5);

		JLabel lblNewLabel_2 = new JLabel("Kodu");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 110, 54, 14);
		panel.add(lblNewLabel_2);

		txtkodu = new JTextField();
		txtkodu.setForeground(Color.BLACK);
		txtkodu.setEnabled(false);
		txtkodu.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				ResultSet sonuc =null;
				try {

					sonuc = f_Access.ur_kod_bak(txtkodu.getText());

					if (!sonuc.isBeforeFirst() ) {  
						txtadi.setForeground(Color.black);
						txtadi.setText("");
					} 
					else
					{
						sonuc.next();
						txtadi.setText(sonuc.getString("Adi"));
						txtadi.setForeground(Color.red);
					}
				}
				catch (Exception ex)
				{

				}
			}
		});
		txtkodu.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtkodu.setBounds(88, 105, 153, 20);
		panel.add(txtkodu);
		txtkodu.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Adi");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(251, 110, 46, 14);
		panel.add(lblNewLabel_3);

		txtadi = new JTextField();
		txtadi.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtadi.setBounds(314, 105, 287, 20);
		panel.add(txtadi);
		txtadi.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Birim");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(10, 136, 46, 14);
		panel.add(lblNewLabel_4);

		txtbirim = new JTextField();
		txtbirim.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtbirim.setBounds(88, 132, 86, 20);
		panel.add(txtbirim);
		txtbirim.setColumns(10);

		txtsinif = new JTextField();
		txtsinif.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtsinif.setBounds(88, 159, 86, 20);
		panel.add(txtsinif);
		txtsinif.setColumns(10);

		cmbmensei = new JComboBox<String>();
		cmbmensei.setBounds(314, 158, 160, 22);
		panel.add(cmbmensei);

		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Yenile");
		btnNewButton.setIcon(new ImageIcon(URUN_KART.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mensei_doldur();
			}
		});
		btnNewButton.setBounds(481, 158, 26, 23);
		panel.add(btnNewButton);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "Grup", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 185, 624, 58);
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Ana");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(10, 26, 46, 14);
		panel_3.add(lblNewLabel_5);

		cmbanagrup = new JComboBox<String>();

		cmbanagrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//	if (cmbanagrup.getItemCount() == 0 ) return ;
				//if (	cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals("") ) return ;
				alt_grup_doldur();
			}
		});
		cmbanagrup.setBounds(78, 22, 160, 22);
		panel_3.add(cmbanagrup);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setToolTipText("Yenile");
		btnNewButton_1.setIcon(new ImageIcon(URUN_KART.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ana_grup_doldur();
			}
		});
		btnNewButton_1.setBounds(247, 22, 26, 23);
		panel_3.add(btnNewButton_1);

		JLabel lblNewLabel_6 = new JLabel("Alt");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_6.setBounds(304, 26, 33, 14);
		panel_3.add(lblNewLabel_6);

		cmbaltgrup = new JComboBox<String>();
		cmbaltgrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbanagrup.getItemCount() == 0 ) return ;
			}
		});
		cmbaltgrup.setBounds(350, 22, 160, 22);
		cmbaltgrup.setEnabled(false);
		panel_3.add(cmbaltgrup);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "Aciklama", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(10, 254, 624, 81);
		panel.add(panel_4);

		JLabel label_1 = new JLabel("1 -");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(10, 26, 46, 14);
		panel_4.add(label_1);

		JLabel label_2 = new JLabel("2 -");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(10, 51, 46, 14);
		panel_4.add(label_2);

		txtacik1 = new JTextField();
		txtacik1.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtacik1.setBounds(78, 23, 397, 20);
		panel_4.add(txtacik1);
		txtacik1.setColumns(10);

		txtacik2 = new JTextField();
		txtacik2.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtacik2.setBounds(78, 48, 397, 20);
		panel_4.add(txtacik2);
		txtacik2.setColumns(10);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "Ozel Kod", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(10, 346, 624, 58);
		panel.add(panel_5);

		JLabel label_3 = new JLabel("1 -");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(10, 26, 58, 14);
		panel_5.add(label_3);

		cmboz1 = new JComboBox<String>();
		cmboz1.setBounds(78, 22, 160, 22);
		panel_5.add(cmboz1);

		JButton button_6 = new JButton("");
		button_6.setIcon(new ImageIcon(URUN_KART.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		button_6.setToolTipText("Yenile");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oz1_doldur();
			}
		});
		button_6.setBounds(247, 22, 26, 23);
		panel_5.add(button_6);

		JLabel label_4 = new JLabel("2 -");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(350, 26, 20, 14);
		panel_5.add(label_4);

		cmboz2 = new JComboBox<String>();
		cmboz2.setBounds(380, 23, 160, 22);
		panel_5.add(cmboz2);

		JButton button_7 = new JButton("");
		button_7.setIcon(new ImageIcon(URUN_KART.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		button_7.setToolTipText("Yenile");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oz2_doldur();
			}
		});
		button_7.setBounds(548, 22, 26, 23);
		panel_5.add(button_7);

		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "Fiat", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(10, 415, 624, 53);
		panel.add(panel_6);

		JLabel label_5 = new JLabel("1 -");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setBounds(10, 23, 20, 14);
		panel_6.add(label_5);

		JLabel label_6 = new JLabel("2 -");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(200, 23, 20, 14);
		panel_6.add(label_6);

		JLabel label_8 = new JLabel("3 -");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_8.setBounds(350, 23, 20, 14);
		panel_6.add(label_8);

		txtfiat1 = new JFormattedTextField();
		txtfiat1.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfiat1.setText("0.00");
		txtfiat1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtfiat1.setBounds(78, 20, 100, 20);
		panel_6.add(txtfiat1);

		txtfiat2 = new JFormattedTextField();
		txtfiat2.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfiat2.setText("0.00");
		txtfiat2.setHorizontalAlignment(SwingConstants.RIGHT);
		txtfiat2.setBounds(230, 20, 100, 20);
		panel_6.add(txtfiat2);

		txtfiat3 = new JFormattedTextField();
		txtfiat3.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtfiat3.setText("0.00");
		txtfiat3.setHorizontalAlignment(SwingConstants.RIGHT);
		txtfiat3.setBounds(380, 20, 100, 20);
		panel_6.add(txtfiat3);

		JLabel lblNewLabel_7 = new JLabel("Kusurat");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(481, 136, 46, 14);
		panel.add(lblNewLabel_7);

		JPanel panel_7 = new JPanel();
		panel_7.setBounds(650, 346, 221, 58);
		panel.add(panel_7);
		panel_7.setLayout(null);
		panel_7.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "Recete", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		txtrecete = new JTextField();
		txtrecete.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtrecete.setColumns(10);
		txtrecete.setBounds(50, 20, 126, 20);
		panel_7.add(txtrecete);

		JButton button_7_1 = new JButton("");
		button_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUtil.setWaitCursor(getContentPane(),true);
				boolean varmi = OBS_MAIN.pencere_bak("RECETE");
				if (varmi  ) 
				{
					try {
						OBS_MAIN.pencere_aktiv_yap("RECETE");
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					JInternalFrame internalFrame;
					internalFrame  = new RECETE();
					OBS_MAIN.desktopPane.add(internalFrame);
					internalFrame.setVisible(true);
				}
				try 
				{
					RECETE.textField.setText(txtrecete.getText());
					RECETE.kontrol();
					GuiUtil.setWaitCursor(getContentPane(),false);
				} 
				catch (NumberFormatException e1) 
				{
					GuiUtil.setWaitCursor(getContentPane(),false);
					e1.printStackTrace();
				}
			}
		});
		button_7_1.setIcon(new ImageIcon(URUN_KART.class.getResource("/ICONLAR/rect.png")));
		button_7_1.setToolTipText("Recete Goruntule");
		button_7_1.setBounds(185, 17, 24, 24);
		panel_7.add(button_7_1);

		JLabel lblNewLabel_8 = new JLabel("Agirlik");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8.setBounds(251, 136, 46, 14);
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Sinif");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_9.setBounds(10, 162, 46, 14);
		panel.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Mensei");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(251, 162, 46, 14);
		panel.add(lblNewLabel_10);

		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "Barkod", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_8.setBounds(10, 481, 624, 58);
		panel.add(panel_8);

		txtbarkod = new JTextField();
		txtbarkod.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtbarkod.setColumns(10);
		txtbarkod.setBounds(78, 20, 172, 20);
		panel_8.add(txtbarkod);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setToolTipText("Web Kamera");
		btnNewButton_3.setIcon(new ImageIcon(URUN_KART.class.getResource("/ICONLAR/icons8-camera-16.png")));
		btnNewButton_3.setBounds(262, 19, 89, 23);
		panel_8.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setToolTipText("Sec");
		btnNewButton_4.setIcon(new ImageIcon(URUN_KART.class.getResource("/ICONLAR/icons8-add-folder-16.png")));
		btnNewButton_4.setBounds(361, 19, 89, 23);
		panel_8.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setToolTipText("Web Kamera");
		btnNewButton_5.setIcon(new ImageIcon(URUN_KART.class.getResource("/ICONLAR/icons8-camera-16.png")));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getContentPane().setCursor(oac.WAIT_CURSOR);
					JInternalFrame internalFrame;
					internalFrame  = new KAMERA();
					OBS_MAIN.desktopPane.add(internalFrame );
					internalFrame.setVisible(true);
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				}
				catch (Exception ex)
				{
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
					JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kamera", JOptionPane.ERROR_MESSAGE);        
				}
			}
		});
		btnNewButton_5.setBounds(650, 291, 60, 23);
		panel.add(btnNewButton_5);

		txtagirlik = new JFormattedTextField();
		txtagirlik.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtagirlik.setHorizontalAlignment(SwingConstants.RIGHT);
		txtagirlik.setText("0.000");
		txtagirlik.setBounds(314, 132, 88, 20);
		panel.add(txtagirlik);

		txtkusurat = new JFormattedTextField();
		txtkusurat.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtkusurat.setHorizontalAlignment(SwingConstants.RIGHT);
		txtkusurat.setText("0");
		txtkusurat.setBounds(566, 132, 68, 20);
		panel.add(txtkusurat);

		JButton button_8 = new JButton("");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				URUN_ARAMA arm ;
				getContentPane().setCursor(oac.WAIT_CURSOR);
				arm = new URUN_ARAMA();
				arm.setVisible(true);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});

		button_8.setIcon(new ImageIcon(URUN_KART.class.getResource("/ICONLAR/icons8-product-16.png")));
		button_8.setToolTipText("Hesap Plani");
		button_8.setBounds(611, 103, 24, 24);
		panel.add(button_8);

		rd_yenile();
		hisset("Kodu","");
	}
	private  void rd_yenile()
	{
		mensei_doldur();
		ana_grup_doldur();
		oz1_doldur();
		oz2_doldur();

	}
	private static void hisset(String siralama,String arama) 
	{
		try {
			temizle();

			rss = f_Access.stk_urun(siralama,arama);


			if (!rss.isBeforeFirst() ) {  
				txtkayit.setText(rss.getRow() + "/" +   String.valueOf(kayit_sayi));
				return;
			} 
			else
			{
				rss.last();
				kayit_sayi = rss.getRow();
				rss.first();

				doldur("ILK");
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Hesap Plani", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static  void doldur(String nereye)
	{
		try {

			if (txtkodu.isEnabled()) { 
				txtkodu.setEnabled(false);
				hisset("Kodu","");
			};
			txtkodu.setEnabled(false);
			if ( rss.getRow()  == 0) return ;
			if (nereye.equals("I"))
			{
				if (rss.getRow() == kayit_sayi)
				{ return ; }
				rss.next();
			}
			else if (nereye.equals("G"))
			{
				if (rss.getRow() == 1)
				{ return ;}
				rss.previous();
			}
			else if (nereye.equals("ILK"))
			{
				rss.first();
			}
			else if (nereye.equals("SON"))
			{
				rss.last();
			}

			txtkayit.setText(rss.getRow() + "/" +   String.valueOf(kayit_sayi));
			txtkodu.setText(rss.getString("Kodu"));
			txtadi.setText(rss.getString("Adi"));
			txtbirim.setText(rss.getString("Birim"));		       
			txtkusurat.setText(rss.getString("Kusurat"));
			txtsinif.setText(rss.getString("Sinif"));
			txtacik2.setText(rss.getString("Aciklama_2"));
			txtacik1.setText(rss.getString("Aciklama_1"));
			txtrecete.setText(rss.getString("Recete"));
			txtfiat1.setText(FORMATLAMA.doub_2(rss.getDouble("Fiat")));
			txtfiat2.setText(FORMATLAMA.doub_2(rss.getDouble("Fiat_2")));
			txtfiat3.setText(FORMATLAMA.doub_2(rss.getDouble("Fiat_3")));
			txtagirlik.setText(FORMATLAMA.doub_3(rss.getDouble("Agirlik")));
			txtbarkod.setText(rss.getString("Barkod"));



			ResultSet qrs = null ;
			qrs = f_Access.urun_kod_degisken_ara("MENSEI","MEID_Y",  "MENSEI_DEGISKEN",String.valueOf(rss.getInt("Mensei")));
			if (!qrs.isBeforeFirst() ) {  
				cmbmensei.setSelectedItem("");
			}
			else {
				qrs.next();
				if (! qrs.getString("MENSEI").toString().equals("0"))
				{
					cmbmensei.setSelectedItem(qrs.getString("MENSEI"));
				}
				else
				{
					cmbmensei.setSelectedItem("");
				}
			}

			//***** ANA GRUP 

			qrs = null ;
			qrs = f_Access.urun_kod_degisken_ara("ANA_GRUP",  "AGID_Y","ANA_GRUP_DEGISKEN",String.valueOf(rss.getInt("Ana_Grup")));
			if (!qrs.isBeforeFirst() ) {  
				cmbanagrup.setSelectedItem("");
			}
			else {
				qrs.next();
				if (! qrs.getString("ANA_GRUP").toString().equals("0"))
				{
					cmbanagrup.setSelectedItem(qrs.getString("ANA_GRUP"));
				}
				else
				{
					cmbanagrup.setSelectedItem("");
				}
			}

			//***** ALT GRUP 

			qrs = null ;
			qrs = f_Access.urun_kod_degisken_ara("ALT_GRUP","ALID_Y",  "ALT_GRUP_DEGISKEN",String.valueOf(rss.getInt("Alt_Grup")));
			if (!qrs.isBeforeFirst() ) {  
				cmbaltgrup.setSelectedItem("");
			}
			else {
				qrs.next();
				if (! qrs.getString("ALT_GRUP").toString().equals("0"))
				{
					cmbaltgrup.setSelectedItem(qrs.getString("ALT_GRUP"));
				}
				else
				{
					cmbaltgrup.setSelectedItem("");
				}
			}


			//***** OZK 1

			qrs = null ;
			qrs = f_Access.urun_kod_degisken_ara("OZEL_KOD_1","OZ1ID",  "OZ_KOD_1_DEGISKEN",String.valueOf(rss.getInt("Ozel_Kod_1")));
			if (!qrs.isBeforeFirst() ) {  
				cmboz1.setSelectedItem("");
			}
			else {
				qrs.next();
				if (! qrs.getString("OZEL_KOD_1").toString().equals("0"))
				{
					cmboz1.setSelectedItem(qrs.getString("OZEL_KOD_1"));
				}
				else
				{
					cmboz1.setSelectedItem("");
				}
			}

			//***** OZ2

			qrs = null ;
			qrs = f_Access.urun_kod_degisken_ara("OZEL_KOD_2","OZ2ID",  "OZ_KOD_2_DEGISKEN",String.valueOf(rss.getInt("Ozel_Kod_2")));
			if (!qrs.isBeforeFirst() ) {  
				cmboz2.setSelectedItem("");
			}
			else {
				qrs.next();
				if (! qrs.getString("OZEL_KOD_2").toString().equals("0"))
				{
					cmboz2.setSelectedItem(qrs.getString("OZEL_KOD_2"));
				}
				else
				{
					cmboz2.setSelectedItem("");
				}
			}

			if (  rss.getBytes("Resim") != null)
			{
				byte[] img = rss.getBytes("Resim");
				ImageIcon image = new ImageIcon(img);
				Image im = image.getImage();
				// Image myImg = im.getScaledInstance(230, 175,Image.SCALE_DEFAULT);
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
			lbluser.setText(rss.getString("USER"));
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
	private static void temizle()
	{
		txtkayit.setText("");
		txtkodu.setText("");
		txtadi.setText("");
		txtbirim.setText("");
		txtsinif.setText("");
		txtkusurat.setText("0");
		txtacik2.setText("");
		txtacik1.setText("");
		txtrecete.setText("");
		txtfiat1.setText("0.00");
		txtfiat2.setText("0.00");
		txtfiat3.setText("0.00");
		txtagirlik.setText("0.000");
		txtbarkod.setText("");
		cmbmensei.setSelectedItem("");
		cmbanagrup.setSelectedItem("");
		cmbaltgrup.setSelectedItem("");
		cmboz1.setSelectedItem("");
		cmboz2.setSelectedItem("");
		imagePanel.setImage(null);
	}
	private  void mensei_doldur()
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			cmbmensei.removeAllItems();
			ResultSet rs=null;

			rs = f_Access.stk_kod_degisken_oku("MENSEI", "MEID_Y", "MENSEI_DEGISKEN");

			if (!rs.isBeforeFirst() ) {  
				cmbmensei.addItem("");
				cmbmensei.setSelectedItem("");
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				return;
			} 
			cmbmensei.addItem("");
			while (rs.next())
			{
				cmbmensei.addItem(rs.getString("MENSEI"));
			}
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Alt Grup", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void ana_grup_doldur()
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			cmbanagrup .removeAllItems();
			ResultSet rs=null;

			rs =f_Access.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");

			if (!rs.isBeforeFirst() ) {  
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				cmbaltgrup.setEnabled(false);
				cmbanagrup .addItem("");
				cmbanagrup.setSelectedItem("");
				return;
			} 
			cmbanagrup .addItem("");
			while (rs.next())
			{
				cmbanagrup .addItem(rs.getString("ANA_GRUP"));
			}
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Ana Grup", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void oz1_doldur()
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			cmboz1 .removeAllItems();
			ResultSet rs=null;

			rs = f_Access.stk_kod_degisken_oku("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN");

			if (!rs.isBeforeFirst() ) {  
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				cmboz1.addItem("");
				cmboz1.setSelectedItem("");
				return;
			} 
			cmboz1.addItem("");
			while (rs.next())
			{
				cmboz1.addItem(rs.getString("OZEL_KOD_1"));
			}
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Alt Grup", JOptionPane.ERROR_MESSAGE);    	
		}
	}
	private void oz2_doldur()
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			cmboz2 .removeAllItems();
			ResultSet rs=null;

			rs = f_Access.stk_kod_degisken_oku("OZEL_KOD_2", "OZ2ID_Y", "OZ_KOD_2_DEGISKEN");

			if (!rs.isBeforeFirst() ) {  
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				cmboz2.addItem("");
				cmboz2.setSelectedItem("");
				return;
			} 
			cmboz2.addItem("");
			while (rs.next())
			{
				cmboz2.addItem(rs.getString("OZEL_KOD_2"));
			}
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Ozel Kod", JOptionPane.ERROR_MESSAGE);    	
		}
	}
	private void alt_grup_doldur()
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
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
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
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
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Alt Grup", JOptionPane.ERROR_MESSAGE);    	
		}

	}
	private void arama()
	{
		String ara = "";
		if (chckbxKod.isSelected())
		{
			ara= "Kodu";
		}
		else if (chckbxAdi.isSelected())
		{
			ara= "Adi";
		}
		else if (chckbxBarkod.isSelected())
		{
			ara= "Barkod";
		}
		if (chcbas.isSelected())
		{
			hisset(ara, "WHERE  Kodu like  N'" + textField_1.getText() + "%' OR  Adi Like N'" + textField_1.getText() + "%'");
		}
		else
		{
			hisset(ara, "WHERE  Kodu like  N'%" + textField_1.getText() + "%' OR  Adi Like N'%" + textField_1.getText() + "%'");
		}
	}
	public static void sil()
	{
		if ( txtkodu.getText().equals("")) return ;
		int g =  JOptionPane.showOptionDialog( null,  "Kayit Dosyadan Silinecek ..?"  ,
				"Urun Silme",   JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,     //no custom icon
				oac.options,  //button titles
				oac.options[1]); //default button
		if(g != 0 ) { return;	}
		try {

			f_Access.stk_ur_sil(txtkodu.getText());

			temizle();
			txtkodu.setEnabled(false);
			// rd_yenile();
			hisset("Kodu","");
		}
		catch (Exception ex)
		{

			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Urun Silme", JOptionPane.ERROR_MESSAGE);    	
		}
	}
	public static void kayit()
	{
		if ( txtkodu.getText().equals("") ) return ;
		try {


			f_Access.stk_ur_sil(txtkodu.getText());


			double dbl1 = 0 ;
			int int2 = 0 ;
			int int3 = 0 ;
			int int4 = 0 ;
			int int6 = 0 ;
			int int7 = 0 ;
			double dbl3 = 0 ;
			double dbl2 = 0 ;
			double dbl4 = 0 ;
			double dbl5 = 0 ;
			ResultSet qaz = null;

			if (! cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals(""))
			{
				qaz = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
				qaz.next();
				int2 = qaz.getInt("AGID_Y");
			}


			//*** ALT GRUP

			if (! cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString().equals(""))
			{
				qaz = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()));
				qaz.next();
				int3 = qaz.getInt("ALID_Y");
			}


			//*** MENSEI 

			if (! cmbmensei.getItemAt(cmbmensei.getSelectedIndex()).toString().equals(""))
			{
				qaz = f_Access.urun_kod_degisken_ara("MEID_Y", "MENSEI", "MENSEI_DEGISKEN", cmbmensei.getItemAt(cmbmensei.getSelectedIndex()));
				qaz.next();
				int4 = qaz.getInt("MEID_Y");
			}


			//*** OZEL 1

			if (! cmboz1.getItemAt(cmboz1.getSelectedIndex()).toString().equals(""))
			{
				qaz = f_Access.urun_kod_degisken_ara("OZ1ID_Y", "OZEL_KOD_1", "OZ_KOD_1_DEGISKEN", cmboz1.getItemAt(cmboz1.getSelectedIndex()));
				qaz.next();
				int6 = qaz.getInt("OZ1ID_Y");
			}


			//*** OZEL 2

			if (! cmboz2.getItemAt(cmboz2.getSelectedIndex()).toString().equals(""))
			{
				qaz =f_Access.urun_kod_degisken_ara("OZ2ID_Y", "OZEL_KOD_2", "OZ_KOD_2_DEGISKEN", cmboz2.getItemAt(cmboz2.getSelectedIndex()));
				qaz.next();
				int7 = qaz.getInt("OZ2ID_Y");
			}

			if (! txtkusurat.getText().isEmpty())
			{
				dbl1 = DecimalFormat.getNumberInstance().parse(txtkusurat.getText()).doubleValue();
			}
			if (! txtfiat1.getText().isEmpty())
			{
				dbl2 = DecimalFormat.getNumberInstance().parse(txtfiat1.getText()).doubleValue();
			}
			if (! txtfiat2.getText().isEmpty())
			{
				dbl4 = DecimalFormat.getNumberInstance().parse(txtfiat2.getText()).doubleValue();
			}
			if (! txtfiat3.getText().isEmpty())
			{
				dbl5 = DecimalFormat.getNumberInstance().parse(txtfiat3.getText()).doubleValue();
			}
			if (! txtagirlik.getText().isEmpty())
			{
				dbl3 = DecimalFormat.getNumberInstance().parse(txtagirlik.getText()).doubleValue();
			}

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


			f_Access.stk_ur_kayit(txtkodu.getText(), txtadi.getText(), txtbirim.getText(), dbl1, txtsinif.getText(), int2, int3, txtacik1.getText(), txtacik2.getText(), int6,
					int7,  txtbarkod.getText(), int4, dbl3,  fis, dbl2, dbl4, txtrecete.getText(), GLOBAL.KULL_ADI, dbl5);


			temizle();
			txtkodu.setEnabled(false);

			hisset("Kodu","");

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Urun Kayit", JOptionPane.ERROR_MESSAGE);   
		}

	}
}

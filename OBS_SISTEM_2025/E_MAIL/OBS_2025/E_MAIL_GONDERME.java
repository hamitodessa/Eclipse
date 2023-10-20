package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.crystaldecisions.sdk.occa.report.exportoptions.ReportExportFormat;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;

import KER_RAPOR.KER_DETAY;
import KER_RAPOR.KER_GRUP_RAPOR;
import KER_RAPOR.KER_ORT_SATIS;
import OBS_2025_RAPORLAR.GRUP_RAPOR;
import OBS_2025_RAPORLAR.IMALAT_GRUP_RAPOR;
import OBS_2025_RAPORLAR.IMALAT_RAPORLAMA;
import OBS_2025_RAPORLAR.ORTALAMA_FIAT;
import OBS_2025_RAPORLAR.STOK_DETAY;
import OBS_2025_RAPORLAR.STOK_RAPOR;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.MAIL_SETTINGS;
import OBS_C_2025.ValidEmailAddress;

import javax.swing.SwingConstants;

@SuppressWarnings({"serial","static-access","deprecation"})
public class E_MAIL_GONDERME extends JInternalFrame {
	private JTextField txtkonu;
	private JTextField txtgonhesap;
	private JTextField txtgonisim;
	private JTextArea txtaciklama ;
	private JList<String> list ;
	private JComboBox<String> comboBox ;
	private JComboBox<String> cmbalici ;
	private JLabel lblNewLabel_3 ;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private boolean etiketten = false;
	private boolean ekstreden = false;
	Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E_MAIL_GONDERME frame = new E_MAIL_GONDERME();
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
	public E_MAIL_GONDERME() {

		setTitle("E MAIL GONDERME");
		setClosable(true);
		setBounds(100, 100, 598, 446);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Gonderen", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1.setBounds(10, 11, 324, 77);
		panel.add(panel_1);
		panel_1.setLayout(null);

		txtgonhesap = new JTextField();

		txtgonhesap.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtgonhesap.setBounds(75, 22, 240, 20);
		panel_1.add(txtgonhesap);
		txtgonhesap.setColumns(10);

		txtgonisim = new JTextField();
		txtgonisim.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtgonisim.setBounds(75, 46, 240, 20);
		panel_1.add(txtgonisim);
		txtgonisim.setColumns(10);

		JLabel lblNewLabel = new JLabel("Hesap");
		lblNewLabel.setBounds(10, 25, 46, 14);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Isim");
		lblNewLabel_1.setBounds(10, 49, 46, 14);
		panel_1.add(lblNewLabel_1);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Alici", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1_1.setBounds(10, 99, 324, 55);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);

		cmbalici = new JComboBox<String>();
		cmbalici.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbalici.setEditable(true);
		cmbalici.setBounds(75, 20, 235, 22);
		panel_1_1.add(cmbalici);

		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Format Turu", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1_2.setBounds(10, 166, 324, 55);
		panel.add(panel_1_2);
		panel_1_2.setLayout(null);

		comboBox = new JComboBox<String>();
		comboBox.setForeground(new Color(0, 0, 128));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"PDF", "EXCELL", "WORD"}));
		comboBox.setBounds(75, 22, 134, 22);
		panel_1_2.add(comboBox);



		JPanel panel_1_3 = new JPanel();
		panel_1_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Konu", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1_3.setBounds(10, 232, 324, 55);
		panel.add(panel_1_3);
		panel_1_3.setLayout(null);

		txtkonu = new JTextField();
		txtkonu.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtkonu.setBounds(75, 23, 240, 20);
		panel_1_3.add(txtkonu);
		txtkonu.setColumns(10);


		JPanel panel_1_3_1 = new JPanel();
		panel_1_3_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Aciklama", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1_3_1.setBounds(10, 293, 562, 87);
		panel.add(panel_1_3_1);
		panel_1_3_1.setLayout(new BorderLayout(0, 0));

		txtaciklama = new JTextArea();
		txtaciklama.setFont(new Font("Monospaced", Font.BOLD, 13));
		txtaciklama.setLineWrap(true);
		txtaciklama.setDocument(new JTextFieldLimit(100));
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		txtaciklama.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		panel_1_3_1.add(txtaciklama, BorderLayout.CENTER);

		JButton btnNewButton = new JButton("Gonder");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (oac.glb.internet_kontrol() == false)
				{
					JOptionPane.showMessageDialog(null,  "Internet Baglantisi Yok ",  "Mail Gonderme", JOptionPane.ERROR_MESSAGE);	
					return ;
				}
				if (txtgonhesap.getText().equals("") || cmbalici.getSelectedItem().toString() .equals("")) return ;
				getContentPane().setCursor(WAIT_CURSOR);

				if (ValidEmailAddress.isValid( txtgonhesap.getText()  ) == false )
				{
					JOptionPane.showMessageDialog(null,  "Gonderen Hesap , Mail formatina uymamaktadir", "Mail Gonderme", JOptionPane.ERROR_MESSAGE);
					getContentPane().setCursor(DEFAULT_CURSOR);
					txtgonhesap.requestFocus();
					return;
				}
				if (ValidEmailAddress.isValid(cmbalici.getSelectedItem().toString()) == false)
				{
					JOptionPane.showMessageDialog(null,  "Alici Hesap , Mail formatina uymamaktadir", "Mail Gonderme", JOptionPane.ERROR_MESSAGE);
					getContentPane().setCursor(DEFAULT_CURSOR);
					cmbalici.requestFocus();
					return;
				}
				if (txtkonu.getText().equals(""))
				{	
					getContentPane().setCursor(DEFAULT_CURSOR);
					JOptionPane.showMessageDialog(null,  "Konu Bos...",  "Mail Gonderme", JOptionPane.ERROR_MESSAGE);	
					txtkonu.requestFocus();
					return ;
				}
				if (txtaciklama.getText().equals(""))
				{
					getContentPane().setCursor(DEFAULT_CURSOR);
					JOptionPane.showMessageDialog(null,  "Aciklama Bos...",  "Mail Gonderme", JOptionPane.ERROR_MESSAGE);	
					txtaciklama.requestFocus();
					return ;
				}
				if ( OBS_MAIN.pencere_bak("RAPOR YAZDIRMA") == true  || etiketten ==true || ekstreden == true) 
				{
					gonder();
				}
				else if ( OBS_MAIN.pencere_bak("GRAFIK") == true ) 
				{
					grf_gonder();
				}
				else if ( OBS_MAIN.pencere_bak("GRUP RAPOR") == true ) 
				{
					grup_gonder();
				}
				else if ( OBS_MAIN.pencere_bak("KERESTE GRUP RAPOR") == true ) 
				{
					ker_grup_gonder();
				}
				else if ( OBS_MAIN.pencere_bak("KERESTE DETAY RAPOR") == true ) 
				{
					ker_detay_gonder();
				}
				else if ( OBS_MAIN.pencere_bak("KERESTE ORTALAMA SATIS") == true ) 
				{
					ker_ortfiat_gonder();
				}
				else if ( OBS_MAIN.pencere_bak("IMALAT GRUP RAPOR") == true ) 
				{
					ima_grup_gonder();
				}
				else if ( OBS_MAIN.pencere_bak("STOK DETAY") == true ) 
				{
					stk_gonder();
				}
				else if ( OBS_MAIN.pencere_bak("IMALAT RAPORLAMA") == true ) 
				{
					ima_gonder();
				}
				else if ( OBS_MAIN.pencere_bak("STOK_RAPOR") == true ) 
				{
					stk_rpr_gonder();
				}
				else if ( OBS_MAIN.pencere_bak("ORTALAMA SATIS") == true ) 
				{
					ort_satis();
				}
				else if ( OBS_MAIN.pencere_bak("CARI OZEL MIZAN") == true ) 
				{
					ozmiz_gonder();
				}
				else if ( OBS_MAIN.pencere_bak("KERESTE CIKIS") == true ) 
				{
					ker_cikis();
				}
				else if ( OBS_MAIN.pencere_bak("KERESTE GIRIS") == true ) 
				{
					ker_giris();
				}
				else
				{
					duz_gonder();
				}
				dispose();
				getContentPane().setCursor(DEFAULT_CURSOR);
			}
		});
		btnNewButton.setIcon(new ImageIcon(E_MAIL_GONDERME.class.getResource("/ICONLAR/mail-16.png")));
		btnNewButton.setBounds(10, 384, 449, 25);
		panel.add(btnNewButton);

		DefaultListModel<String> model = new DefaultListModel<>();
		list = new JList<String>(model);
		list.setForeground(new Color(0, 0, 128));
		list.setFont(new Font("Tahoma", Font.BOLD, 11));
		list.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ek Dosya Secimi", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 120, 215)));
		list.setBounds(338, 11, 239, 218);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setDropTarget(new DropTarget() {
			@SuppressWarnings("unchecked")
			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					List<File> droppedFiles = (List<File>) evt .getTransferable().getTransferData(  DataFlavor.javaFileListFlavor);
					if(droppedFiles.size() > 1){
						JOptionPane.showMessageDialog(null,  "Tek Seferde 1 Dosya Ekleyebilirsiniz.....!!", "Dosya Ekleme", JOptionPane.PLAIN_MESSAGE);
					}
					else{
						File droppedFile = (File) droppedFiles.get(0);
						model.addElement(droppedFile.getName());
						// JOptionPane.showMessageDialog(null, droppedFile.getName());
						// if(droppedFile.getName().endsWith(".txt")){
						//  char[] contentBytes = readFile(droppedFile);
						// if(contentBytes == null){
						//    JOptionPane.showMessageDialog(null, "Sorry...file size is too long.");
						// }
						// else if(contentBytes.length == 0){
						//  JOptionPane.showMessageDialog(null, "Sorry...file is empty.");
						//    }
						//  else{
						//.setText(new String(contentBytes));
						//    }
						//  }
						//   else{
						//        JOptionPane.showMessageDialog(null, "Sorry...not a text file.");
						//     }
					
						lblNewLabel_3.setText(Integer.toString(list.getModel().getSize()));
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,  ex.getMessage(), "Dosya Ekleme", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		panel.add(list);

		JButton btnNewButton_1 = new JButton("Ekle");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(WAIT_CURSOR);
				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Dosya Seciniz");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Dosya Sec");
				chooser.setApproveButtonToolTipText("Dosya Sec");
				chooser.setApproveButtonMnemonic('s');
				getContentPane().setCursor(DEFAULT_CURSOR);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					model.addElement(chooser.getSelectedFile().toString());
				}
				else {
				}
				lblNewLabel_3.setText(Integer.toString(list.getModel().getSize()));
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(E_MAIL_GONDERME.class.getResource("/ICONLAR/icons8-add-folder-16.png")));
		btnNewButton_1.setBounds(344, 259, 84, 23);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Cikar");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getModel().getSize() != 0 )
					model.remove(list.getSelectedIndex());
				lblNewLabel_3.setText(Integer.toString(list.getModel().getSize()));
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(E_MAIL_GONDERME.class.getResource("/ICONLAR/sil.png")));
		btnNewButton_2.setBounds(484, 259, 84, 23);
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Vazgec");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_3.setIcon(new ImageIcon(E_MAIL_GONDERME.class.getResource("/ICONLAR/exit.png")));
		btnNewButton_3.setBounds(466, 384, 106, 25);
		panel.add(btnNewButton_3);

		JLabel lblNewLabel_2 = new JLabel(".....");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setBounds(344, 235, 195, 14);
		panel.add(lblNewLabel_2);

		doldur_setting();
		txtaciklama.setText("");
		txtkonu.setText("");
		lblNewLabel_3 = new JLabel("0");
		lblNewLabel_3.setForeground(new Color(0, 0, 139));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(551, 235, 21, 14);
		panel.add(lblNewLabel_3);
		try {
			lblNewLabel_2.setText("");
			if (rapor_kontrol() )
			{
				lblNewLabel_2.setText("1 Rapor Ekli");
			}
			else if (grf_kontrol() )
			{
				comboBox.enable(false);
				lblNewLabel_2.setText(GLOBAL.g_baslik);
			}
			else if (grup_kontrol() )
			{
				comboBox.enable(false);
				lblNewLabel_2.setText("Grup Rapor");
			}
			else if (ker_grup_kontrol() )
			{
				comboBox.enable(false);
				lblNewLabel_2.setText("Kereste Grup Rapor");
			}
			else if (ker_detay_kontrol() )
			{
				comboBox.enable(false);
				lblNewLabel_2.setText("Kereste Detay Rapor");
			}
			else if (ker_ortfiat_kontrol() )
			{
				comboBox.enable(false);
				lblNewLabel_2.setText("Kereste Ortalama Fiat");
			}
			else if (ima_grup_kontrol() )
			{
				comboBox.enable(false);
				lblNewLabel_2.setText("Imalat Grup Rapor");
			}
			else if (stk_grup_kontrol() )
			{
				comboBox.enable(false);
				lblNewLabel_2.setText("Stok Detay");
			}
			else if (ima_kontrol() )
			{
				comboBox.enable(false);
				lblNewLabel_2.setText("Imalat Rapor");
			}
			else if (stk_rpr_kontrol() )
			{
				comboBox.enable(false);
				lblNewLabel_2.setText("Stok Rapor");
			}
			else if (ort_fiyatlama() )
			{
				comboBox.enable(false);
				lblNewLabel_2.setText("Ortalama Fiyat");
			}
			else if (ozmiz_kontrol() )
			{
				comboBox.enable(false);
				lblNewLabel_2.setText("Ozel Mizan");
			}
			else if (ker_cikis_kontrol() )
			{
				comboBox.enable(false);
				lblNewLabel_2.setText("Kereste Cikis");
			}
			else if (ker_giris_kontrol() )
			{
				comboBox.enable(false);
				lblNewLabel_2.setText("Kereste Giris");
			}
			else if (etiket_kontrol() )
			{
				comboBox.enable(true);
				etiketten = true ;
				lblNewLabel_2.setText("Etiket");
			}
			else if (ekstre_kontrol() )
			{
				comboBox.enable(true);
				ekstreden = true ;
				lblNewLabel_2.setText("Ekstre");
			}
			else 
			{
				comboBox.enable(false);
				lblNewLabel_2.setText("");
			}
		} catch (ReportSDKException e1) {
			e1.printStackTrace();
		}
	}
	private void doldur_setting() 
	{
		try
		{
			oac.uSER_ISL.mail_bak();
			txtgonhesap.setText(MAIL_SETTINGS.GHESAP);
			txtgonisim.setText(MAIL_SETTINGS.GADI);
			cmbalici.removeAllItems();
			cmbalici.addItem("");
			ResultSet rss = oac.uSER_ISL.alici_oku(GLOBAL.KULL_ADI);
			if (!rss.isBeforeFirst() ) {  
				return;
			} 
			while(rss.next())
			{
				cmbalici.addItem(rss.getString("ALICI"));
			}
			cmbalici.setSelectedItem("");
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Bilgi Okuma", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void duz_gonder() 
	{
		try
		{
			String rapor_dos_adi = "" ;
			String[] to = { cmbalici.getSelectedItem().toString() };
			MimeBodyPart messagePart = null ;
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", MAIL_SETTINGS.TSL);
			if (MAIL_SETTINGS.SSL)
			{
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
			}
			props.put("mail.smtp.host", MAIL_SETTINGS.HOST);
			props.put("mail.smtp.user", MAIL_SETTINGS.HESAP);
			props.put("mail.smtp.password", MAIL_SETTINGS.PWD);
			props.put("mail.smtp.port", MAIL_SETTINGS.PORT);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(MAIL_SETTINGS.HESAP,MAIL_SETTINGS.PWD);
				}
			});
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(txtgonhesap.getText(),txtgonisim.getText()));
			InternetAddress[] toAddress = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}
			for (int i = 0; i < toAddress.length; i++) {
				message.setRecipient(RecipientType.TO,  toAddress[i]);
			}
			messagePart = new MimeBodyPart();
			messagePart.setText(txtaciklama.getText().toString(),"UTF-8");
			Multipart multipart = new MimeMultipart();
			//*****
			if (list.getModel().getSize() != 0) 
			{
				for (int i =0; i< list.getModel().getSize(); i++)
				{
					String dosya =  list.getModel().getElementAt(i);
					MimeBodyPart att = new MimeBodyPart();
					att.attachFile(dosya.toString());
					multipart.addBodyPart(att);
				} 
			}
			//*****
			multipart.addBodyPart(messagePart);
			message.setSubject(txtkonu.getText().toString(), "UTF-8");
			message.setContent(multipart);
			message.setSentDate(new Date());
			Transport.send(message);
			message= null;
			session = null;
			//**********************Raporlama Dosyasina Yaz ***************************
			oac.uSER_ISL.giden_rapor_yaz(new java.sql.Date(Calendar.getInstance().getTime().getTime())  ,txtkonu.getText(), rapor_dos_adi,  cmbalici.getSelectedItem().toString()  ,
					MAIL_SETTINGS.HESAP,txtaciklama.getText(), GLOBAL.KULL_ADI) ;
			//*************************************************************************
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void gonder() 
	{
		Runnable runner = new Runnable()
		{ public void run() {
		try
		{
			long startTime = System.currentTimeMillis(); 
			String rapor_dos_adi = "" ;
			//****************************************RAPOR AKTARMA********************************************
			ByteArrayOutputStream byteArrayOutputStream = null  ;
			ByteArrayInputStream byteArrayInputStream = null  ;
			ByteArrayDataSource ds = null ;
			MimeBodyPart messagePart = null ;
			InputStream inputStream = null ;
			if (etiketten)
			{
				if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("PDF") )
				{
					ds = PRINT_JASPER.export_to();
				}
				else if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("EXCELL") )
				{
					ds = PRINT_JASPER.export_xls();
					File outputFile = new File("etiket.xls");
					boolean exists = outputFile.exists();
					if (exists)
					{    
						outputFile.delete();
					}
				}
				else if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("WORD") )
				{
					ds = PRINT_JASPER.export_docx();
					File outputFile = new File("etiket.doc");
					boolean exists = outputFile.exists();
					if (exists)
					{    
						outputFile.delete();
					}
				}
			}
			else if (ekstreden)
			{
				if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("PDF") )
				{
					ds = PRINT_JASPER.export_to();
				}
				else if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("EXCELL") )
				{
					ds = PRINT_JASPER.export_xls();
					File outputFile = new File("ekstre.xls");
					boolean exists = outputFile.exists();
					if (exists)
					{    
						outputFile.delete();
					}
				}
				else if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("WORD") )
				{
					ds = PRINT_JASPER.export_docx();
					File outputFile = new File("ekstre.doc");
					boolean exists = outputFile.exists();
					if (exists)
					{    
						outputFile.delete();
					}
				}
			}
			else
			{
				if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("PDF") )
				{
					byteArrayInputStream = (ByteArrayInputStream) PRINT_YAPMA.clientDoc
							.getPrintOutputController().export(ReportExportFormat.PDF);
				}
				else if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("EXCELL") )
				{
					byteArrayInputStream = (ByteArrayInputStream) PRINT_YAPMA.clientDoc
							.getPrintOutputController().export(ReportExportFormat.MSExcel);
				}
				else if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("WORD") )
				{
					byteArrayInputStream = (ByteArrayInputStream) PRINT_YAPMA.clientDoc
							.getPrintOutputController().export(ReportExportFormat.MSWord);
				}
				byte byteArray[] = new byte[byteArrayInputStream.available()];
				byteArrayOutputStream = new ByteArrayOutputStream(byteArrayInputStream.available());
				int x = byteArrayInputStream.read(byteArray, 0, byteArrayInputStream.available());
				byteArrayOutputStream.write(byteArray, 0, x);
				byteArrayInputStream.close();
				byteArrayOutputStream.close();
				inputStream = new ByteArrayInputStream(byteArrayOutputStream .toByteArray());
				ds = new ByteArrayDataSource(inputStream, "application/x-any");
			}
			//*************************************************************************************************
			String[] to = { cmbalici.getSelectedItem().toString()  };
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", MAIL_SETTINGS.TSL);
			if (MAIL_SETTINGS.SSL)
			{
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
				//props.put("mail.smtp.startsls.enable", SSL);
			}
			props.put("mail.smtp.host", MAIL_SETTINGS.HOST);
			props.put("mail.smtp.user", MAIL_SETTINGS.HESAP);
			props.put("mail.smtp.password",MAIL_SETTINGS.PWD);
			props.put("mail.smtp.port", MAIL_SETTINGS.PORT);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(MAIL_SETTINGS.HESAP, MAIL_SETTINGS.PWD);
				}
			});
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(MAIL_SETTINGS.HESAP,MAIL_SETTINGS.GADI));
			InternetAddress[] toAddress = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}
			for (int i = 0; i < toAddress.length; i++) {
				message.setRecipient(RecipientType.TO,  toAddress[i]);
			}
			messagePart = new MimeBodyPart();
			messagePart.setText(txtaciklama.getText(), "UTF-8");

			MimeBodyPart attachment = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			attachment.setDataHandler(new DataHandler(ds));

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");  
			LocalDateTime now = LocalDateTime.now();  
			String zaman = dtf.format(now)  ;
			if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("PDF") )
			{
				attachment.setFileName("OBS_SISTEM_" + zaman +".pdf");
				rapor_dos_adi ="OBS_SISTEM_" + zaman +".pdf";
			}
			else if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("EXCELL") )
			{
				attachment.setFileName("OBS_SISTEM_" + zaman +".xls");
				rapor_dos_adi ="OBS_SISTEM_" + zaman +".xls" ;
			}
			else if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("WORD") )
			{
				attachment.setFileName("OBS_SISTEM_" + zaman +".doc");
				rapor_dos_adi ="OBS_SISTEM_" + zaman +".doc" ;
			}
			multipart.addBodyPart(attachment);
			//*****
			for (int i =0; i< list.getModel().getSize(); i++)
			{
				try {
					Object item = list.getModel().getElementAt(i);
					MimeBodyPart att = new MimeBodyPart();
					att.attachFile(item.toString());
					multipart.addBodyPart(att);
				} catch (IOException ex) {
					throw ex;
				}
			} 
			//*****
			multipart.addBodyPart(messagePart);
			message.setSubject(txtkonu.getText(), "UTF-8");
			message.setContent(multipart);
			message.setSentDate(new Date());
			Transport.send(message);
			message= null;
			session = null;
			//**********************Raporlama Dosyasina Yaz ***************************
			oac.uSER_ISL.giden_rapor_yaz(new java.sql.Date(Calendar.getInstance().getTime().getTime())  ,txtkonu.getText(), rapor_dos_adi, cmbalici.getSelectedItem().toString() ,
					MAIL_SETTINGS.HESAP,txtaciklama.getText(), GLOBAL.KULL_ADI) ;
			//*************************************************************************
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			Thread.currentThread().isInterrupted();
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
		}
		catch (Exception ex)
		{
			Thread.currentThread().isInterrupted();
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
		}};
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
	private void grf_gonder() 
	{
		try
		{
			long startTime = System.currentTimeMillis(); 
			String rapor_dos_adi = GLOBAL.g_baslik ;
			MimeBodyPart messagePart = null ;
			String[] to = { cmbalici.getSelectedItem().toString()  };
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", MAIL_SETTINGS.TSL);
			if (MAIL_SETTINGS.SSL)
			{
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
			}
			props.put("mail.smtp.host",MAIL_SETTINGS.HOST);
			props.put("mail.smtp.user", MAIL_SETTINGS.HESAP);
			props.put("mail.smtp.password", MAIL_SETTINGS.PWD);
			props.put("mail.smtp.port", MAIL_SETTINGS.PORT);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(MAIL_SETTINGS.HESAP, MAIL_SETTINGS.PWD);
				}
			});
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(MAIL_SETTINGS.HESAP,MAIL_SETTINGS.GADI));
			InternetAddress[] toAddress = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}
			for (int i = 0; i < toAddress.length; i++) {
				message.setRecipient(RecipientType.TO,  toAddress[i]);
			}
			messagePart = new MimeBodyPart();
			messagePart.setText(txtaciklama.getText(), "UTF-8");

			ByteArrayDataSource ds = null ;
			BufferedImage objBufferedImage= GRAFIK.chart.createBufferedImage(1000,500);
			ByteArrayOutputStream bas = new ByteArrayOutputStream();
			ImageIO.write(objBufferedImage, "png", bas);
			byte[] byteArray= bas.toByteArray();
			InputStream in = new ByteArrayInputStream(byteArray);
			ds = new ByteArrayDataSource(in, "application/x-any");


			MimeBodyPart attachment = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			attachment.setDataHandler(new DataHandler(ds));

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");  
			LocalDateTime now = LocalDateTime.now();  
			String zaman = dtf.format(now)  ;

			attachment.setFileName(GLOBAL.g_baslik + zaman +".png");
			rapor_dos_adi =GLOBAL.g_baslik + zaman +".png";

			multipart.addBodyPart(attachment);
			//*****
			for (int i =0; i< list.getModel().getSize(); i++)
			{
				try {
					Object item = list.getModel().getElementAt(i);
					MimeBodyPart att = new MimeBodyPart();
					att.attachFile(item.toString());
					multipart.addBodyPart(att);
				} catch (IOException ex) {
					throw ex;
				}
			} 
			//*****
			multipart.addBodyPart(messagePart);
			message.setSubject(txtkonu.getText(), "UTF-8");
			message.setContent(multipart);
			message.setSentDate(new Date());
			Transport.send(message);
			message= null;
			session = null;
			//**********************Raporlama Dosyasina Yaz ***************************
			oac.uSER_ISL.giden_rapor_yaz(new java.sql.Date(Calendar.getInstance().getTime().getTime())  ,txtkonu.getText(), rapor_dos_adi, cmbalici.getSelectedItem().toString() ,
					MAIL_SETTINGS.HESAP,txtaciklama.getText(), GLOBAL.KULL_ADI) ;
			//*************************************************************************
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void grup_gonder() 
	{
		try
		{
			GRUP_RAPOR. mail_at();
			xl_gonder("Grup_Rapor" );
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void ker_grup_gonder() 
	{
		try
		{
			KER_GRUP_RAPOR. mail_at();
			xl_gonder("Kereste_Grup_Rapor" );
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void ker_detay_gonder() 
	{
		try
		{
			KER_DETAY. mail_at();
			xl_gonder("Kereste_Detay_Rapor" );
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void ker_ortfiat_gonder() 
	{
		try
		{
			KER_ORT_SATIS. mail_at();
			xl_gonder("Kereste_Ortalama_Fiat" );
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void stk_gonder() 
	{
		try
		{
			STOK_DETAY. mail_at();
			xl_gonder("Stok_Detay" );
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void stk_rpr_gonder() 
	{
		try
		{
			STOK_RAPOR. mail_at();
			xl_gonder("Stok_Rapor" );
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void ort_satis() 
	{
		try
		{
			ORTALAMA_FIAT.mail_at();
			xl_gonder("Ortalama_Fiyat" );
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void ima_grup_gonder() 
	{
		try
		{
			IMALAT_GRUP_RAPOR. mail_at();
			xl_gonder("Imalat_Grup_Rapor" );
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void ima_gonder() 
	{
		try
		{
			IMALAT_RAPORLAMA. mail_at();
			xl_gonder("Imalat_Rapor" );
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void ozmiz_gonder() 
	{
		try
		{
			OZEL_MIZAN. mail_at();
			xl_gonder("Ozel_Mizan" );
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void ker_cikis() 
	{
		try
		{
			KERESTE_CIKIS. mail_at();
			xl_gonder("Kereste_Cikis" );
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void ker_giris() 
	{
		try
		{
			KERESTE_GIRIS. mail_at();
			xl_gonder("Kereste_Giris" );
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void xl_gonder(String dadi)
	{
		try
		{
			long startTime = System.currentTimeMillis(); 
			String rapor_dos_adi = GLOBAL.g_baslik ;
			MimeBodyPart messagePart = null ;
			String[] to = { cmbalici.getSelectedItem().toString()  };
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", MAIL_SETTINGS.TSL);
			if (MAIL_SETTINGS.SSL)
			{
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
			}
			props.put("mail.smtp.host",MAIL_SETTINGS.HOST);
			props.put("mail.smtp.user", MAIL_SETTINGS.HESAP);
			props.put("mail.smtp.password", MAIL_SETTINGS.PWD);
			props.put("mail.smtp.port", MAIL_SETTINGS.PORT);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(MAIL_SETTINGS.HESAP, MAIL_SETTINGS.PWD);
				}
			});
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(MAIL_SETTINGS.HESAP,MAIL_SETTINGS.GADI));
			InternetAddress[] toAddress = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}
			for (int i = 0; i < toAddress.length; i++) {
				message.setRecipient(RecipientType.TO,  toAddress[i]);
			}
			messagePart = new MimeBodyPart();
			messagePart.setText(txtaciklama.getText(), "UTF-8");

			MimeBodyPart attachment = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			attachment.setDataHandler(new DataHandler( oac.ds));

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");  
			LocalDateTime now = LocalDateTime.now();  
			String zaman = dtf.format(now)  ;

			attachment.setFileName(dadi  + zaman +".xlsx");
			rapor_dos_adi =dadi + zaman +".xlsx";

			multipart.addBodyPart(attachment);
			//*****
			for (int i =0; i< list.getModel().getSize(); i++)
			{
				try {
					Object item = list.getModel().getElementAt(i);
					MimeBodyPart att = new MimeBodyPart();
					att.attachFile(item.toString());
					multipart.addBodyPart(att);
				} catch (IOException ex) {
					throw ex;
				}
			} 
			//*****
			multipart.addBodyPart(messagePart);
			message.setSubject(txtkonu.getText(), "UTF-8");
			message.setContent(multipart);
			message.setSentDate(new Date());
			Transport.send(message);
			message= null;
			session = null;
			//**********************Raporlama Dosyasina Yaz ***************************
			oac.uSER_ISL.giden_rapor_yaz(new java.sql.Date(Calendar.getInstance().getTime().getTime())  ,txtkonu.getText(), rapor_dos_adi, cmbalici.getSelectedItem().toString() ,
					MAIL_SETTINGS.HESAP,txtaciklama.getText(), GLOBAL.KULL_ADI) ;
			//*************************************************************************
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private boolean  rapor_kontrol() throws ReportSDKException
	{
		boolean result  = false;

		if (OBS_MAIN.pencere_bak("RAPOR YAZDIRMA"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  grf_kontrol() throws ReportSDKException
	{
		boolean result  = false;
		if (OBS_MAIN.pencere_bak("GRAFIK"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  grup_kontrol() throws ReportSDKException
	{
		boolean result  = false;

		if (OBS_MAIN.pencere_bak("GRUP RAPOR"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  ker_grup_kontrol() throws ReportSDKException
	{
		boolean result  = false;

		if (OBS_MAIN.pencere_bak("KERESTE GRUP RAPOR"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  ker_detay_kontrol() throws ReportSDKException
	{
		boolean result  = false;

		if (OBS_MAIN.pencere_bak("KERESTE DETAY RAPOR"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  ker_ortfiat_kontrol() throws ReportSDKException
	{
		boolean result  = false;

		if (OBS_MAIN.pencere_bak("KERESTE ORTALAMA SATIS"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  ker_cikis_kontrol() throws ReportSDKException
	{
		boolean result  = false;

		if (OBS_MAIN.pencere_bak("KERESTE CIKIS"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  ker_giris_kontrol() throws ReportSDKException
	{
		boolean result  = false;

		if (OBS_MAIN.pencere_bak("KERESTE GIRIS"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  ima_grup_kontrol() throws ReportSDKException
	{
		boolean result  = false;
		if (OBS_MAIN.pencere_bak("IMALAT GRUP RAPOR"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  ima_kontrol() throws ReportSDKException
	{
		boolean result  = false;
		if (OBS_MAIN.pencere_bak("IMALAT RAPORLAMA"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  stk_grup_kontrol() throws ReportSDKException
	{
		boolean result  = false;

		if (OBS_MAIN.pencere_bak("STOK DETAY"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  stk_rpr_kontrol() throws ReportSDKException
	{
		boolean result  = false;

		if (OBS_MAIN.pencere_bak("STOK_RAPOR"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  ort_fiyatlama() throws ReportSDKException
	{
		boolean result  = false;

		if (OBS_MAIN.pencere_bak("ORTALAMA SATIS"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  ozmiz_kontrol() throws ReportSDKException
	{
		boolean result  = false;
		if (OBS_MAIN.pencere_bak("CARI OZEL MIZAN"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  etiket_kontrol() throws ReportSDKException
	{
		boolean result  = false;
		if (oac.nerden.equals("etiket"))
		{
			result = true ;
		}
		return result;
	}
	private boolean  ekstre_kontrol() throws ReportSDKException
	{
		boolean result  = false;
		if (oac.nerden.equals("ekstre"))
		{
			result = true ;
		}
		return result;
	}
}

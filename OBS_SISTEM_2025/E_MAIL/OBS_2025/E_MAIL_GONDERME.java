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

import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.ValidEmailAddress;

import javax.swing.SwingConstants;

public class E_MAIL_GONDERME extends JInternalFrame {
	private JTextField txtkonu;
	private JTextField txtgonhesap;
	private JTextField txtgonisim;
	private JTextArea txtaciklama ;
	private JList<String> list ;
	private JComboBox<String> comboBox ;
	private JComboBox<String> cmbalici ;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	
	Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	
	private String HESAP;
	private String HOST;
	private String  PORT;
	private String  SIFR;
	private boolean  SSL;
	private boolean  TSL;
	private String GHESAP;
	private String GADI;
	

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
				//
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
				//
				
						 if ( OBS_MAIN.pencere_bak("RAPOR YAZDIRMA") == true ) 
		             		{
							 gonder();
		             		}
						 else if ( OBS_MAIN.pencere_bak("GRAFIK") == true ) 
		             		{
							 grf_gonder();
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
	            public synchronized void drop(DropTargetDropEvent evt) {
	                try {
	                    evt.acceptDrop(DnDConstants.ACTION_COPY);
	                    List droppedFiles = (List) evt .getTransferable().getTransferData(  DataFlavor.javaFileListFlavor);
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
		try {
			lblNewLabel_2.setText("");
			if (rapor_kontrol() )
			{
				lblNewLabel_2.setText("1 Rapor Ekli");
			}
			else if (grf_kontrol() )
			{
				lblNewLabel_2.setText(GLOBAL.g_baslik);
			}
			else 
			{
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
		ResultSet rs = oac.uSER_ISL.mail_bak(GLOBAL.KULL_ADI);
		int count=0;
		rs.next();
		count = rs.getRow();
		if (count  == 0) return;
		HESAP= rs.getString("HESAP").toString();
        HOST =rs.getString("HOST").toString() ;
        PORT =rs.getString("PORT").toString() ;
        SIFR = rs.getString("SIFR").toString() ;
        SSL = rs.getBoolean("SSL");
        TSL = rs.getBoolean("TSL");
        GHESAP = rs.getString("GON_MAIL").toString();
        GADI = rs.getString("GON_ISIM").toString();
        txtgonhesap.setText(GHESAP);
        txtgonisim.setText(GADI);
        cmbalici.removeAllItems();
        cmbalici.addItem("");
         //
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
		   props.put("mail.smtp.starttls.enable", TSL);
		   if (SSL)
		   {
			   props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
			   //props.put("mail.smtp.startsls.enable", SSL);
		   }
		   props.put("mail.smtp.host", HOST);
		   props.put("mail.smtp.user", HESAP);
		   props.put("mail.smtp.password", SIFR);
		   props.put("mail.smtp.port", PORT);
		   props.put("mail.smtp.auth", "true");
		   
		   //
	//	  
		   //
		   Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication(HESAP, SIFR);
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
		   Transport.send(message);
		   message= null;
		   session = null;
		   //**********************Raporlama Dosyasina Yaz ***************************
		   oac.uSER_ISL.giden_rapor_yaz(new java.sql.Date(Calendar.getInstance().getTime().getTime())  ,txtkonu.getText(), rapor_dos_adi,  cmbalici.getSelectedItem().toString()  ,
				   						HESAP,txtaciklama.getText(), GLOBAL.KULL_ADI) ;
		   //*************************************************************************
	}
	catch (Exception ex)
	{
		 JOptionPane.showMessageDialog(null,  ex.getMessage(), "Mail Gonderme", JOptionPane.ERROR_MESSAGE);
	}
	}
	private void gonder() 
	{
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
		       //*************************************************************************************************
		       String[] to = { cmbalici.getSelectedItem().toString()  };
			   Properties props = System.getProperties();
			   props.put("mail.smtp.starttls.enable", TSL);
			   if (SSL)
			   {
				   props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
				   //props.put("mail.smtp.startsls.enable", SSL);
			   }
			   props.put("mail.smtp.host", HOST);
			   props.put("mail.smtp.user", HESAP);
			   props.put("mail.smtp.password", SIFR);
			   props.put("mail.smtp.port", PORT);
			   props.put("mail.smtp.auth", "true");
			   Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(HESAP, SIFR);
                   }
               });
			   MimeMessage message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(HESAP,GADI));
			   InternetAddress[] toAddress = new InternetAddress[to.length];
			   for (int i = 0; i < to.length; i++) {
			    toAddress[i] = new InternetAddress(to[i]);
			   }
			   for (int i = 0; i < toAddress.length; i++) {
			    message.setRecipient(RecipientType.TO,  toAddress[i]);
			   }
			   messagePart = new MimeBodyPart();
	           messagePart.setText(txtaciklama.getText(), "UTF-8");
		       inputStream = new ByteArrayInputStream(byteArrayOutputStream .toByteArray());
		       ds = new ByteArrayDataSource(inputStream, "application/x-any");
			   
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
			   Transport.send(message);
			   message= null;
			   session = null;
			   //**********************Raporlama Dosyasina Yaz ***************************
	            oac.uSER_ISL.giden_rapor_yaz(new java.sql.Date(Calendar.getInstance().getTime().getTime())  ,txtkonu.getText(), rapor_dos_adi, cmbalici.getSelectedItem().toString() ,
	            						HESAP,txtaciklama.getText(), GLOBAL.KULL_ADI) ;
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
	private void grf_gonder() 
	{
		try
		{
	         long startTime = System.currentTimeMillis(); 
		String rapor_dos_adi = GLOBAL.g_baslik ;
			   //****************************************RAPOR AKTARMA********************************************
			 ByteArrayOutputStream byteArrayOutputStream = null  ;
			   //ByteArrayInputStream byteArrayInputStream = null  ;
			
			   MimeBodyPart messagePart = null ;
			   InputStream inputStream = null ;
			   
		       //*************************************************************************************************
		       String[] to = { cmbalici.getSelectedItem().toString()  };
			   Properties props = System.getProperties();
			   props.put("mail.smtp.starttls.enable", TSL);
			   if (SSL)
			   {
				   props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
				   //props.put("mail.smtp.startsls.enable", SSL);
			   }
			   props.put("mail.smtp.host", HOST);
			   props.put("mail.smtp.user", HESAP);
			   props.put("mail.smtp.password", SIFR);
			   props.put("mail.smtp.port", PORT);
			   props.put("mail.smtp.auth", "true");
			   Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(HESAP, SIFR);
                   }
               });
			   MimeMessage message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(HESAP,GADI));
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
			   BufferedImage objBufferedImage= GRAFIK.chart.createBufferedImage(600,800);
			   ByteArrayOutputStream bas = new ByteArrayOutputStream();
			   ImageIO.write(objBufferedImage, "png", bas);
			   byte[] byteArray= bas.toByteArray();
			   InputStream in = new ByteArrayInputStream(byteArray);
			   ds = new ByteArrayDataSource(in, "application/x-any");
		       inputStream = new ByteArrayInputStream(byteArrayOutputStream .toByteArray());
		       ds = new ByteArrayDataSource(inputStream, "application/x-any");
			   
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
			   Transport.send(message);
			   message= null;
			   session = null;
			   //**********************Raporlama Dosyasina Yaz ***************************
	            oac.uSER_ISL.giden_rapor_yaz(new java.sql.Date(Calendar.getInstance().getTime().getTime())  ,txtkonu.getText(), rapor_dos_adi, cmbalici.getSelectedItem().toString() ,
	            						HESAP,txtaciklama.getText(), GLOBAL.KULL_ADI) ;
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
}

package OBS_C_2025;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MAIL_AT implements ILOGGER{

	
	@Override
	public void Logla(String mesaj, String evrak, DIZIN_BILGILERI dBILGI)
			throws ClassNotFoundException, SQLException {
		   String rapor_dos_adi = "" ;
		   String[] to = { GLOBAL.Log_Mail };
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
		   props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		   
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

}

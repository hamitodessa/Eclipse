package OBS_C_2025;

import java.util.Date;
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
import javax.swing.JOptionPane;

public class MAIL_AT implements ILOGGER{
	@Override
	public void Logla(lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI)
	{
		if ( GLOBAL.Log_Mail.toString().equals("") ) return ;
		Runnable runner = new Runnable()
		{ public void run() {
		try
		{
			String[] to = { GLOBAL.Log_Mail };
			MimeBodyPart messagePart = null ;
			Properties props = System.getProperties();
			props.put("mail.smtp.starttls.enable", MAIL_SETTINGS.TSL);
			if (MAIL_SETTINGS.SSL)
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
			props.put("mail.smtp.host", MAIL_SETTINGS.HOST);
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
			message.setFrom(new InternetAddress(MAIL_SETTINGS.GHESAP ,MAIL_SETTINGS.GADI));
			InternetAddress[] toAddress = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++)
				toAddress[i] = new InternetAddress(to[i]);
			for (int i = 0; i < toAddress.length; i++)
				message.setRecipient(RecipientType.TO,  toAddress[i]);
			messagePart = new MimeBodyPart();
			messagePart.setText(lBILGI.getmESAJ()  + "  EvrakNo:" + lBILGI.geteVRAK(),"UTF-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messagePart);
			message.setSubject("Loglama", "UTF-8");
			message.setContent(multipart);
			message.setSentDate(new Date());
			Transport.send(message);
			message= null;
			session = null;
			Thread.currentThread().isInterrupted();
		}
		catch (Exception ex)
		{
			Thread.currentThread().isInterrupted();
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Loglama Mail Gonderme", JOptionPane.ERROR_MESSAGE);
		}
		}		};
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
}

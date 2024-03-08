package OBS_2025;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import raven.toast.Notifications;
import OBS_C_2025.DIZIN_DETAY;
import java.awt.Font;
@SuppressWarnings("static-access")
public class USER_KOPYALA extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();

	private static JComboBox<String> comboBox_1;
	private static JComboBox<String> comboBox;
	private static JPanel panel ;
	private static ResultSet rss ;


	public USER_KOPYALA() {
		setTitle("KULLANICI KOPYALA");
		setClosable(true);
		setBounds(0, 0, 321, 154);

		getContentPane().setLayout(new BorderLayout(0, 0));
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Yeni Kullanici");
		lblNewLabel_1.setBounds(28, 71, 89, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Kullanici");
		lblNewLabel.setBounds(28, 35, 89, 14);
		panel.add(lblNewLabel);

		comboBox = new JComboBox<String>();
		//comboBox.setForeground(new Color(0, 0, 128));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox.setBounds(127, 31, 157, 22);
		panel.add(comboBox);

		comboBox_1 = new JComboBox<String>();
		//comboBox_1.setForeground(new Color(0, 0, 128));
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox_1.setBounds(127, 67, 157, 22);
		panel.add(comboBox_1);

		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			doldur();
			getContentPane().setCursor(oac.DEFAULT_CURSOR);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void doldur() throws ClassNotFoundException, SQLException
	{
		ResultSet	rs = null;
		rs = oac.uSER_ISL.user_isim_doldur();
		if (!rs.isBeforeFirst() )  return;
		while(rs.next())
		{
			comboBox.addItem(rs.getString("USER_NAME"));
			comboBox_1.addItem(rs.getString("USER_NAME"));
		}  
	}
	public static void kaydet() 
	{
		try 
		{
			int count=0;
			rss =   oac.uSER_ISL.user_db_izinleri(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString(), "Cari Hesap");          
			panel.setCursor(oac.WAIT_CURSOR);
			count = 0 ;
			rss.next();
			count = rss.getRow();
			if (count  != 0) 
			{
				panel.setCursor(oac.DEFAULT_CURSOR);
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Bu Kullanici isminde Cari Hesap Bilgiler Mevcut");
				return ;
			}
			panel.setCursor(oac.WAIT_CURSOR);
			rss =   oac.uSER_ISL.user_db_izinleri(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString(), "Adres");
			panel.setCursor(oac.WAIT_CURSOR);
			count=0;
			rss.next();
			count = rss.getRow();
			if (count  != 0) 
			{
				panel.setCursor(oac.DEFAULT_CURSOR);
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Bu Kullanici isminde Adres Bilgiler Mevcut");
				return ;
			}
			panel.setCursor(oac.WAIT_CURSOR);
			rss = oac.uSER_ISL.user_db_izinleri(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString(), "Kur");
			panel.setCursor(oac.WAIT_CURSOR);
			count=0;
			rss.next();
			count = rss.getRow();
			if (count  != 0) 
			{
				panel.setCursor(oac.DEFAULT_CURSOR);
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Bu Kullanici isminde Kur Bilgiler Mevcut");
				return ;
			}
			panel.setCursor(oac.WAIT_CURSOR);
			rss =  oac.uSER_ISL.user_db_izinleri(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString(), "Fatura");
			panel.setCursor(oac.WAIT_CURSOR);
			count=0;
			rss.next();
			count = rss.getRow();
			if (count  != 0) 
			{
				panel.setCursor(oac.DEFAULT_CURSOR);
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Bu Kullanici isminde Fatura Bilgiler Mevcut");
				return ;
			}
			panel.setCursor(oac.WAIT_CURSOR);
			rss =   oac.uSER_ISL.user_db_izinleri(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString(), "Sms");
			panel.setCursor(oac.WAIT_CURSOR);
			count=0;
			rss.next();
			count = rss.getRow();
			if (count  != 0) 
			{
				panel.setCursor(oac.DEFAULT_CURSOR);
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Bu Kullanici isminde Sms Bilgiler Mevcut");
				return ;
			}
			panel.setCursor(oac.WAIT_CURSOR);
			rss =   oac.uSER_ISL.user_db_izinleri(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString(), "Gunluk");
			panel.setCursor(oac.WAIT_CURSOR);
			count=0;
			rss.next();
			count = rss.getRow();
			if (count  != 0) 
			{
				panel.setCursor(oac.DEFAULT_CURSOR);
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Bu Kullanici isminde Gunluk Bilgiler Mevcut");
				return ;
			}
			panel.setCursor(oac.WAIT_CURSOR);
			rss =   oac.uSER_ISL.user_db_izinleri(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString(), "Kambiyo");
			panel.setCursor(oac.WAIT_CURSOR);
			count=0;
			rss.next();
			count = rss.getRow();
			if (count  != 0) 
			{
				panel.setCursor(oac.DEFAULT_CURSOR);
				return ;
			}
			panel.setCursor(oac.WAIT_CURSOR);
			rss =   oac.uSER_ISL.user_db_izinleri(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString(), "Kereste");
			panel.setCursor(oac.WAIT_CURSOR);
			count=0;
			rss.next();
			count = rss.getRow();
			if (count  != 0) 
			{
				panel.setCursor(oac.DEFAULT_CURSOR);
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Bu Kullanici isminde Kereste Bilgiler Mevcut");
				return ;
			}
			bILGILER("Cari Hesap");
			bILGILER("Adres");
			bILGILER("Kur");
			bILGILER("Fatura");    
			bILGILER("Sms");
			bILGILER("Gunluk");
			bILGILER("Kambiyo");
			bILGILER("Kereste");
			panel.setCursor(oac.DEFAULT_CURSOR);   
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO, "Kullanici Kopyalandi");
		}
		catch (Exception ex)
		{
			panel.setCursor(oac.DEFAULT_CURSOR);   
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
		}
	}
	private static void bILGILER (String mODUL) throws ClassNotFoundException, SQLException
	{
		rss =  oac.uSER_ISL.user_aktarma_oku(comboBox.getItemAt(comboBox.getSelectedIndex()).toString(), mODUL);
		if (!rss.isBeforeFirst() )  return;
		List<DIZIN_DETAY> cDIZIN = new ArrayList<DIZIN_DETAY>();

		while (rss.next())
		{
			String decodedString = rss.getString("USER_PWD_SERVER").toString ();
			String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];
			for (int i=0, len=bytes.length; i<len; i++) {
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			}
			try {
				decodedString=  ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes);
			} catch (Exception e) {
				e.printStackTrace();
			}

			DIZIN_DETAY ets1  = new DIZIN_DETAY(rss.getString("USER_PROG_KODU").toString() 
					, comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString()
					, rss.getString("USER_SERVER").toString ()
					, decodedString
					, rss.getString("USER_INSTANCE_OBS").toString ()
					, rss.getString("USER_IP_OBS").toString ()
					, rss.getString("USER_PROG_OBS").toString ()
					, rss.getString("DIZIN").toString ()
					, rss.getString("YER").toString ()
					, rss.getString("DIZIN_CINS").toString ()
					, rss.getString("IZINLI_MI").toString ()
					, rss.getString("CALISAN_MI").toString ()
					, rss.getString("HANGI_SQL").toString ()
					, ""
					, rss.getInt("LOG") 
					, rss.getString("LOG_YERI").toString());
			cDIZIN.add(ets1);
		}
		for (int i = 0; i < cDIZIN.size(); i++) {
			oac.uSER_ISL.details_aktarma_yaz(cDIZIN.get(i).getUSER_PROG_KODU()
					, cDIZIN.get(i).getUSERSA()
					, cDIZIN.get(i).getUSER_SERVER ()
					, cDIZIN.get(i).getSIFRE()
					, cDIZIN.get(i).getUSER_INSTANCE_OBS ()
					, cDIZIN.get(i).getUSER_IP_OBS ()
					, cDIZIN.get(i).getUSER_PROG_OBS ()
					, cDIZIN.get(i).getDIZIN ()
					, cDIZIN.get(i).getYER ()
					, cDIZIN.get(i).getDIZIN_CINS ()
					, cDIZIN.get(i).getIZINLI_MI ()
					, cDIZIN.get(i).getCALISAN_MI ()
					, cDIZIN.get(i).getHANGI_SQL ()
					, cDIZIN.get(i).getCDID()
					, cDIZIN.get(i).getLOG()
					, cDIZIN.get(i).getLOG_YERI());
		}
	}
}

package OBS_2025;


import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					USER_KOPYALA frame = new USER_KOPYALA();
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
	@SuppressWarnings("static-access")
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
		comboBox.setForeground(new Color(0, 0, 128));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox.setBounds(127, 31, 157, 22);
		panel.add(comboBox);

		comboBox_1 = new JComboBox<String>();
		comboBox_1.setForeground(new Color(0, 0, 128));
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox_1.setBounds(127, 67, 157, 22);
		panel.add(comboBox_1);

		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			doldur();
			getContentPane().setCursor(oac.DEFAULT_CURSOR);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
	@SuppressWarnings("static-access")
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
				JOptionPane.showMessageDialog(null, "Bu Kullanici isminde Cari Hesap Bilgiler Mevcurt", "Kullanici Kopyalama", JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "Bu Kullanici isminde Adres Bilgiler Mevcut", "Kullanici Kopyalama", JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "Bu Kullanici isminde Kur Bilgiler Mevcut", "Kullanici Kopyalama", JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "Bu Kullanici isminde Fatura Bilgiler Mevcurt", "Kullanici Kopyalama", JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "Bu Kullanici isminde Sms Bilgiler Mevcurt", "Kullanici Kopyalama", JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "Bu Kullanici isminde Gunluk Bilgiler Mevcurt", "Kullanici Kopyalama", JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "Bu Kullanici isminde Kambiyo Bilgiler Mevcurt", "Kullanici Kopyalama", JOptionPane.WARNING_MESSAGE);
				return ;
			}
			//*********CARI KOPYALA
			bILGILER("Cari Hesap");
			//*********Adres KOPYALA
			bILGILER("Adres");
			//*********KUR KOPYALA
			bILGILER("Kur");
			//*********Fatura KOPYALA
			bILGILER("Fatura");    
			//*********Sms KOPYALA
			bILGILER("Sms");
			//*********Gunluk KOPYALA
			bILGILER("Gunluk");
			//*********KAMBIYO KOPYALA
			bILGILER("Kambiyo");
			//		            
			panel.setCursor(oac.DEFAULT_CURSOR);   
			JOptionPane.showMessageDialog(null, "Kullanici Kopyalandi", "Kullanici Kopyalama", JOptionPane.WARNING_MESSAGE);
		}
		catch (Exception ex)
		{
			panel.setCursor(oac.DEFAULT_CURSOR);   
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Kullanici Kopyalama", JOptionPane.WARNING_MESSAGE);
		}
	}
	private static void bILGILER (String mODUL) throws ClassNotFoundException, SQLException
	{
		rss =  oac.uSER_ISL.user_db_izinleri(comboBox.getItemAt(comboBox.getSelectedIndex()).toString(), mODUL);
		rss.next();
		byte[] decodedBytes = Base64.getDecoder().decode( rss.getString("USER_PWD_SERVER").toString ());
		String decodedString = new String(decodedBytes);
		oac.uSER_ISL.details_yaz(rss.getString("USER_PROG_KODU").toString() 
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
	}
}

package obs.backup.gorev;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import OBS_C_2025.BACKUP_GLOBAL;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class gOREV_TAKIP extends JPanel implements Runnable{
	JLabel lblNewLabel;
	private String eADI="";
	Timer timerr = new Timer();  
	public JLabel lblemirISMI ;
	private static JLabel lblDosyaSayisi;
	private static JLabel lblSurucu;
	private static JLabel lblSonYedek;
	private static JLabel lblAciklama;
	private static JLabel lblDurum;
	private static JLabel lblBASLAMA;
	private static JLabel lblBITIS;
	private static JLabel lblKACDAKKA;
	private static JLabel lblSonDurum;
	
	BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	public gOREV_TAKIP(String emirADI) {

		eADI = emirADI;
		 System.out.println("50=" + eADI);
		setBorder(new CompoundBorder(new EmptyBorder(5, 5, 0, 5), BorderFactory.createLineBorder(new Color(235, 235, 235))));
		setName(emirADI); 
		setPreferredSize(new Dimension(700,185));
		setLayout(null);

		JButton btnDuzelt = new JButton("Emir Duzelt");
		btnDuzelt.setName("btnDuzelt");
		btnDuzelt.setBounds(586, 26, 110, 23);
		add(btnDuzelt);

		JButton btnSil = new JButton("Sil");
		btnSil.setBounds(586, 69, 110, 23);
		add(btnSil);

		JButton btnYedekle = new JButton("Yedekle");
		btnYedekle.setBounds(586, 104, 110, 23);
		add(btnYedekle);

		JButton btnIndir = new JButton("Indir");
		btnIndir.setBounds(586, 133, 110, 23);
		add(btnIndir);

		lblemirISMI = new JLabel("New label");
		lblemirISMI.setForeground(new Color(0, 0, 128));
		lblemirISMI.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblemirISMI.setBounds(240, 19, 221, 16);
		lblemirISMI.setText(eADI);
		add(lblemirISMI);

		lblSonDurum = new JLabel(".....");
		lblSonDurum.setBounds(240, 47, 336, 14);
		add(lblSonDurum);

		lblDosyaSayisi = new JLabel("0");
		lblDosyaSayisi.setBounds(240, 68, 336, 14);
		add(lblDosyaSayisi);

		lblSurucu = new JLabel(".....");
		lblSurucu.setBounds(240, 89, 336, 14);
		add(lblSurucu);

		lblSonYedek = new JLabel(".....");
		lblSonYedek.setBounds(240, 111, 336, 14);
		add(lblSonYedek);

		JLabel lblNewLabel10 = new JLabel("New label");
		lblNewLabel10.setBounds(240, 132, 148, 14);
		add(lblNewLabel10);

		lblAciklama = new JLabel(".....");
		lblAciklama.setBounds(240, 153, 336, 14);
		add(lblAciklama);

		JLabel lblNewLabel_6 = new JLabel("~");
		lblNewLabel_6.setBounds(397, 132, 20, 14);
		add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("0");
		lblNewLabel_7.setBounds(412, 132, 164, 14);
		add(lblNewLabel_7);

		JLabel lblNewLabel = new JLabel("Son Durum");
		lblNewLabel.setBounds(131, 47, 83, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Dosya Sayisi");
		lblNewLabel_1.setBounds(131, 68, 99, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Surucu");
		lblNewLabel_1_1.setBounds(131, 89, 99, 14);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Son Yedekleme");
		lblNewLabel_1_2.setBounds(131, 111, 99, 14);
		add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Gel.Yedekleme");
		lblNewLabel_1_3.setBounds(131, 132, 99, 14);
		add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Aciklama");
		lblNewLabel_1_4.setBounds(131, 153, 99, 14);
		add(lblNewLabel_1_4);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(gOREV_TAKIP.class.getResource("/obs/backup/icons/icons8-backup-100.png")));
		lblNewLabel_2.setBounds(10, 66, 100, 100);
		add(lblNewLabel_2);

		lblDurum = new JLabel(".....");
		lblDurum.setBounds(35, 16, 195, 14);
		add(lblDurum);

		lblBASLAMA = new JLabel(".....");
		lblBASLAMA.setVisible(false);
		lblBASLAMA.setBounds(43, 39, 18, 18);
		add(lblBASLAMA);

		lblBITIS = new JLabel(".....");
		lblBITIS.setVisible(false);
		lblBITIS.setBounds(69, 39, 18, 18);
		add(lblBITIS);

		lblKACDAKKA = new JLabel(".....");
		lblKACDAKKA.setVisible(false);
		lblKACDAKKA.setBounds(97, 39, 18, 18);
		add(lblKACDAKKA);

		JButton btn1 = new JButton(".....");
		btn1.setVisible(false);
		btn1.setBounds(0, 11, 23, 23);
		add(btn1);

		JButton btn2 = new JButton(".....");
		btn2.setVisible(false);
		btn2.setBounds(0, 31, 23, 23);
		add(btn2);

		JButton btnyenidenBASLAT = new JButton(".....");
		btnyenidenBASLAT.setBounds(20, 31, 23, 23);
		btnyenidenBASLAT.setVisible(false);
		add(btnyenidenBASLAT);
		//  run();
		try {
			ilkBasla();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}


	@Override
	public void run() {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");


		TimerTask tt = new TimerTask() {  
			@Override  
			public void run() {  
				//lblNewLabel.setText(gadiString +"=="+ df.format(new Date()));
				//System.out.println(gadiString +"=="+ df.format(new Date()));
				try {
					
				} catch (Exception e) {
					
					  OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, e.getMessage());
				}
			};  
		};
		timerr.schedule(tt,0,  100);;  

	}
	private void ilkBasla() throws ClassNotFoundException, SQLException
	{
		 try
		 {
		    
		     bckp.log_kayit(eADI, new Date(), "Emir ilk Load....");
		     emirBILGIYUKLE();
		   //  basla();
		 }
		 catch (Exception ex)
		 {
			 setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		   
		     bckp.log_kayit(eADI, new Date(), ex.getMessage());
		     OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
		     //hataDURUMU();
		 }
	}
	private void emirBILGIYUKLE() throws SQLException, ClassNotFoundException
	{
		if (eADI.equals(""))
		{
			Component[] components = OBS_BACKUP.container.getComponents();
			for (Component component : components) {
				if (component.getName().toString().equals(eADI)) {
					OBS_BACKUP.container.remove(component);
				}
			}
		}
		ResultSet dtss = bckp.emir_tek(eADI);
		dtss.next();
		System.out.println(dtss.getString("SON_YUKLEME"));  //1900-01-01 00:00:00
		if(dtss.getString("SON_YUKLEME").equals("1900-01-01 00:00:00"))
		{
			lblSonYedek.setText("");
		}
		else {
			lblSonYedek.setText(dtss.getString("SON_YUKLEME"));
		}
		
		lblAciklama.setText(dtss.getString("EMIR_ACIKLAMA"));
		if ((Boolean) dtss.getBoolean("DURUM") == false)
		{
			lblDurum.setVisible(true);
			lblDurum.setText("Pasiv Durumda");
		}
		else
		{
			lblDurum.setVisible(false);
			lblDurum.setText(".....");
		}
		Boolean kontrol = (Boolean)   dtss.getBoolean("SON_DURUM");
		if (kontrol)
		{
			lblSonDurum.setForeground(Color.GREEN);
			lblSonDurum.setText("Yedeklendi");
		}
		else
		{
			lblSonDurum.setText(dtss.getString("MESAJ"));
			lblSonDurum.setForeground(Color.RED);
		}
		String insTANCE = dtss.getString("INSTANCE") ;
		boolean SQL_YEDEK_MI = dtss.getBoolean("SQL_YEDEK");
		if (SQL_YEDEK_MI)
		{
			if (bckp.ftp_neresi(eADI).equals("FTP"))
			{
				// btnINDIR.Enabled = true;
				System.out.println("248");
				ResultSet dtds ;
				dtds = bckp.db_liste(eADI);
				dtds.next();
				int count = dtds .getRow();
				if (count == 0 ) {  
					lblDosyaSayisi.setText("0 Adet Dosya");
				}
				else {
					lblDosyaSayisi.setText(count + " Adet Dosya - " + insTANCE);
					lblSurucu.setText(bckp.surucu_bilgi(eADI, "HOST") + "\\" + bckp.surucu_bilgi(eADI, "SURUCU").replace("/", "\\"));
				}
			}
			else
			{
				// btnINDIR.Enabled = false;
				ResultSet dtds ;
				dtds = bckp.db_liste(eADI);
				dtds.next();
				int count = dtds .getRow();

				if (count == 0 ) {  
					lblDosyaSayisi.setText("0 Adet Dosya");
					lblSurucu.setText(bckp.surucu_bilgi(eADI, "SURUCU_YER"));
				}
				else
				{
					lblDosyaSayisi.setText(count +  " Adet Dosya...");
					lblSurucu.setText(bckp.surucu_bilgi(eADI, "SURUCU_YER"));
				}
			}
		}
		else  //' Diger Dosya
		{
			if (bckp.ftp_neresi(eADI) == "FTP")
			{
				//btnINDIR.Enabled = true;
				ResultSet dtds ;
				dtds = bckp.diger_dosya_liste(lblemirISMI.getText());
				dtds.next();
				int count = dtds .getRow();
				if (count == 0 ) {  
					lblDosyaSayisi.setText("0 Adet Dosya");
				}
				else
				{
					lblDosyaSayisi.setText(count +  " Adet Dosya...");
					lblSurucu.setText(bckp.surucu_bilgi(eADI,"HOST") + "\\" + bckp.surucu_bilgi(eADI, "SURUCU").replace("/", "\\"));
				}
			}
			else
			{
				//btnINDIR.Enabled = false;
				ResultSet dtds = bckp.diger_dosya_liste(eADI);
				dtds.next();
				int count = dtds .getRow();
				if (count == 0 ) {  
					lblDosyaSayisi.setText("0 Adet Dosya");
					lblSurucu.setText(bckp.surucu_bilgi(eADI, "SURUCU_YER"));
				}
				else
				{
					lblDosyaSayisi.setText(count +  " Adet Dosya...");
					lblSurucu.setText(bckp.surucu_bilgi(lblemirISMI.getText(), "SURUCU_YER"));
				}
			}
		}
		ResultSet dysss = bckp.yedekleme_bilgi(lblemirISMI.getText());
		dysss .next();
		int count = dysss .getRow();
		if (count ==0 ) {  

		}

		else
		{
			lblBASLAMA.setText(dysss.getString("BASLAMA"));
			lblBITIS.setText(dysss.getString("BITIS"));
			lblKACDAKKA.setText(dysss.getString("SAAT"));
		}

	}
}
package OBS_2025;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.lOG_BILGI;
import raven.toast.Notifications;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({"serial"})
public class ORN_HSP_PLN extends JInternalFrame {

	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);


	private static JLabel lblNewLabel ;
	JPanel panel;
	public ORN_HSP_PLN() {
		setTitle("ORNEK HESAP PLANI");
		setClosable(true);
		setBounds(0,0, 422, 168);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dosyadaki Kayit Sayisi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(71, 11, 274, 63);
		panel.add(panel_1);

		lblNewLabel = new JLabel("0");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_1.add(lblNewLabel);

		JButton btnNewButton = new JButton("Aktar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kaydet();
			}
		});
		btnNewButton.setIcon(new ImageIcon(ORN_HSP_PLN.class.getResource("/ICONLAR/save.png")));
		btnNewButton.setBounds(71, 85, 274, 42);
		panel.add(btnNewButton);

		oku();
	}
	private void oku ()
	{
		try
		{
			panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			lblNewLabel.setText(Integer.toString(c_Access.hesap_plani_kayit_adedi()));
			panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		catch (Exception ex)
		{
			panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void kaydet() 
	{
		Runnable runner = new Runnable()
		{ @SuppressWarnings("resource")
		public void run() {
			try
			{
				if (! lblNewLabel.getText().equals("0"))
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Aktarilacak Dosya Bos Degil......." );
				InputStream stream = ORN_HSP_PLN.class.getClassLoader().getResourceAsStream("DOSYA/Hesap_Plani_Ornek.txt");
				InputStreamReader streamReader = new InputStreamReader(stream,StandardCharsets.UTF_16);
				BufferedReader bReader = new BufferedReader(streamReader);

				int g = JOptionPane.showOptionDialog( null,  "Aktarma Islemi Baslatilacak.....?", "Ornek Hesap Plani Aktarma",   JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,null, oac.options, oac.options[1]); 
				if(g != 0 ) { return;	}	
				Progres_Bar_Temizle();   
				OBS_MAIN.progressBar.setMaximum(358); 
				int i = 0 ;
				panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Scanner sc = new Scanner(bReader); 
				while (sc.hasNextLine()) 
				{
					Progres_Bar(358, i);
					String l =  sc.nextLine();  
					String[] token = l.split("\t");
					lOG_BILGI lBILGI = new lOG_BILGI();
					lBILGI.setmESAJ( token[0] + " Nolu Hesap Kayit , Unvan:" + token[1]);
					lBILGI.seteVRAK("");
					c_Access.hpln_kayit(token[0], token[1], token[3], token[2],GLOBAL.KULL_ADI
							,lBILGI,  BAGLAN_LOG.cariLogDizin);
					c_Access.hpln_ilk_detay_kayit(token[0]);
					i += 1 ;
				}
				bReader.close();
				Thread.currentThread().isInterrupted();
				Progres_Bar_Temizle();
				panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO, "Aktarma Islemi Tamamlandi ....Hesap Kodu Sayisi =" + i );
				lblNewLabel.setText(Integer.toString(i));
			}
			catch (Exception ex)
			{
				panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
			}
		}
		};
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
	static void Progres_Bar(int max, int deger) throws InterruptedException
	{
		OBS_MAIN.progressBar.setValue(deger);
	}
	static void Progres_Bar_Temizle()
	{
		OBS_MAIN.progressBar.setMaximum(0);
		OBS_MAIN.progressBar.setValue(0);
		OBS_MAIN.progressBar.setStringPainted(false);
	}
}

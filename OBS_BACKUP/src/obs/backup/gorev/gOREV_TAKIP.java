package obs.backup.gorev;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	   private String gadiString="";
		Timer timerr = new Timer();  
	   public gOREV_TAKIP(String gadi) {
		   
		   gadiString = gadi;
		   setBorder(new CompoundBorder(new EmptyBorder(5, 5, 0, 5), BorderFactory.createLineBorder(new Color(235, 235, 235))));
		  setName(gadi); 
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
		
		JLabel lblNewLabel1 = new JLabel("New label");
		lblNewLabel1.setForeground(new Color(0, 0, 128));
		lblNewLabel1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel1.setBounds(240, 19, 221, 16);
		add(lblNewLabel1);
		
		JLabel lblNewLabel13 = new JLabel("New label");
		lblNewLabel13.setBounds(240, 47, 336, 14);
		add(lblNewLabel13);
		
		JLabel lblNewLabel12 = new JLabel("New label");
		lblNewLabel12.setBounds(240, 68, 336, 14);
		add(lblNewLabel12);
		
		JLabel lblNewLabel18 = new JLabel("New label");
		lblNewLabel18.setBounds(240, 89, 336, 14);
		add(lblNewLabel18);
		
		JLabel lblNewLabel11 = new JLabel("New label");
		lblNewLabel11.setBounds(240, 111, 336, 14);
		add(lblNewLabel11);
		
		JLabel lblNewLabel10 = new JLabel("New label");
		lblNewLabel10.setBounds(240, 132, 148, 14);
		add(lblNewLabel10);
		
		JLabel lblNewLabel8 = new JLabel("New label");
		lblNewLabel8.setBounds(240, 153, 336, 14);
		add(lblNewLabel8);
		
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
		lblNewLabel_1_2.setBounds(131, 108, 99, 14);
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
		
		JLabel lblNewLabel_3 = new JLabel("Aciklama");
		lblNewLabel_3.setBounds(35, 16, 195, 14);
		add(lblNewLabel_3);
		
		JLabel lblBASLAMA = new JLabel(".....");
		lblBASLAMA.setVisible(false);
		lblBASLAMA.setBounds(43, 39, 18, 18);
		add(lblBASLAMA);
		
		JLabel lblBITIS = new JLabel(".....");
		lblBITIS.setVisible(false);
		lblBITIS.setBounds(69, 39, 18, 18);
		add(lblBITIS);
		
		JLabel lblKACDAKKA = new JLabel(".....");
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
		//   run();
	   }

	
	@Override
	public void run() {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	
		TimerTask tt = new TimerTask() {  
		    @Override  
		    public void run() {  
		    	//lblNewLabel.setText(gadiString +"=="+ df.format(new Date()));
				System.out.println(gadiString +"=="+ df.format(new Date()));
		    };  
		};
		timerr.schedule(tt,0,  100);;  
		
	}
	}
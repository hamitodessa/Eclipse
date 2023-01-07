package VT_YEDEK;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class GOREV {

	
	
	public static  JPanel getShowRoomPanel(String emirAdi ,String sonDurum,int dosyaSayisi,String sonYEDEK , String gelYEDEK,
			String acikLAMA,String durUM,String surUCU) throws InterruptedException
	{
	        JPanel p = new JPanel(new GridBagLayout());
	        p.setBorder(new TitledBorder(emirAdi));
	        ((javax.swing.border.TitledBorder) p.getBorder()).setTitleFont(new Font("Arial", Font.BOLD, 14));
	         ((TitledBorder) p.getBorder()).setTitleColor(Color.BLUE);
	  //  jPanel1.repaint();
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5, 5, 5, 5);
	        gbc.anchor = GridBagConstraints.WEST;

	        p.add(new JLabel("Son Durum:" ), gbc);
	        gbc.gridx = 1 ;
	        p.add(new JLabel(sonDurum), gbc);
	
	        gbc.gridx = 2;
	        p.add(new JLabel("          Son Yedekleme :" ), gbc);
	        
	        gbc.gridx = 3;
	        JLabel lblson = new JLabel(sonYEDEK );
	        lblson.setForeground(Color.green.darker());
	        p.add(lblson, gbc);

	        gbc.gridy = 1;
	        gbc.gridx = 0;
	        p.add(new JLabel("Dosya Sayisi   :"), gbc);

	        gbc.gridx = 1;
	        p.add(new JLabel(Integer.toString(dosyaSayisi) + " Adet Dosya" ), gbc);

	        gbc.gridx = 2;
	        p.add(new JLabel("          Gelecek Yedekleme :" ), gbc);
	        gbc.gridx = 3;
	        
	        JLabel lblgel = new JLabel(gelYEDEK );
	        lblgel.setForeground(Color.RED);
	        p.add(lblgel, gbc);

	        gbc.gridy = 2;
	        gbc.gridx = 0;
	        gbc.weightx = 0.0;
	        gbc.gridwidth = 2;
	        p.add(new JLabel("Surucu / FTP  :" ), gbc);
	        
	        gbc.gridx = 1;
	        p.add(new JLabel(surUCU), gbc);
	        
	        gbc.gridy = 3;
	        gbc.gridx = 0;
	        gbc.weightx = 0.0;
	        gbc.gridwidth = 3;
	        p.add(new JLabel("Aciklama:" ), gbc);
	        
	        gbc.gridx = 1;
	        p.add(new JLabel(acikLAMA ), gbc);
	        
	        
	        gbc.gridy = 4;
	        gbc.gridx = 0;
	        gbc.weightx = 0.0;
	        gbc.gridwidth = 3;
	        p.add(new JLabel( ), gbc);
	        
	        gbc.gridx = 0;
	        p.add(new JLabel("Durum" ), gbc);
	        gbc.gridx = 1;
	        p.add(new JLabel(durUM), gbc);
	        
	        gbc.gridy = 6;
	        gbc.gridx = 0;
	        JButton btnNewButton = new JButton("Emir Duzelt");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
				
					VT_ANA_CLASS.yENI_EMIR = false ;
					VT_ANA_CLASS.EMIR_ADI= emirAdi;
					EMIR emr = new EMIR();
				
					emr.setVisible(true);
			
					
					//   System.out.println("="+BASLA.gorevLER.size());
				//	BASLA.gorevLER.add(emirAdi);
					//   System.out.println("==" +BASLA.gorevLER.size());
				}
			});
	        p.add(btnNewButton, gbc);
	        basla();
	        return p;
	    }
	public static void basla() throws InterruptedException
	{
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
			System.out.println(new Date());
			}
			};
			Timer timer = new Timer();
			timer.schedule(timerTask, 0, 1000);
				
	}
}

package obs.backup.gorev;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import obs.backup.main.OBS_BACKUP;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class gOREV_TAKIP extends JPanel implements Runnable{
	   JLabel lblNewLabel;
	   private String gadiString="";
		Timer timerr = new Timer();  
	   public gOREV_TAKIP(String gadi) {
		   setBorder(BorderFactory.createTitledBorder("Single Client"));
		   
		  // setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 5, 0, 5),  new EtchedBorder()));
		   
		   Border border = getBorder();
		    Border margin = new EmptyBorder(10,5,0,20);
		   setBorder(new CompoundBorder(border, margin));
		    
		   setLayout(null);
		   setPreferredSize(new Dimension(0,100));
		  
		   gadiString = gadi ;
		   lblNewLabel = new JLabel("New label");
		   lblNewLabel.setBounds(0, 30, 146, 14);
		   add(lblNewLabel);
		   
		   JButton btnButton = new JButton("yazdir");
		   btnButton.setBounds(0,50, 100,30);
		   btnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					timerr.cancel();
					OBS_BACKUP.okuma(gadi);
				}
			});
		   add(btnButton);
		   run();
	   }

	
	@Override
	public void run() {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	
		TimerTask tt = new TimerTask() {  
		    @Override  
		    public void run() {  
		    	lblNewLabel.setText(gadiString +"=="+ df.format(new Date()));
				System.out.println(gadiString +"=="+ df.format(new Date()));
		    };  
		};
		timerr.schedule(tt,0,  100);;  
		
	}
	}
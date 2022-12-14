package OBS_PACKAGE;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ORN_HSP_PLN extends JInternalFrame {

	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);

	
	private static JLabel lblNewLabel ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ORN_HSP_PLN frame = new ORN_HSP_PLN();
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
	public ORN_HSP_PLN() {
		setTitle("ORNEK HESAP PLANI");
		setClosable(true);
		setBounds(0,0, 422, 168);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dosyadaki Kayit Sayisi", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
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
		if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
		{
			lblNewLabel.setText(Integer.toString(oac.cARI_HESAP_MSSQL.hesap_plani_kayit_adedi()));
		}
		else
		{
			lblNewLabel.setText(Integer.toString(oac.cARI_HESAP_MYSQL.hesap_plani_kayit_adedi()));

		}
		}
		catch (Exception ex)
		{
			
		}
	}
	private void kaydet() 
	{
		Runnable runner = new Runnable()
	    { public void run() {
		try
		{
		
		InputStream stream = PRINT_YAPMA.class.getClassLoader().getResourceAsStream("DOSYA/Hesap_Plani_Ornek.txt");
		InputStreamReader streamReader = new InputStreamReader(stream,StandardCharsets.UTF_16);
        BufferedReader bReader = new BufferedReader(streamReader);
         
        int g = JOptionPane.showOptionDialog( null,  "Aktarma Islemi Baslatilacak.....?", "Ornek Hesap Plani Aktarma",   JOptionPane.YES_NO_OPTION,
    	   		JOptionPane.QUESTION_MESSAGE,null, oac.options, oac.options[1]); 
    	        if(g != 0 ) { return;	}	
    	        Progres_Bar_Temizle();   
    	        OBS_MAIN.progressBar.setMaximum(358); 
    	        int i = 0 ;
    	        getContentPane().setCursor(WAIT_CURSOR);
    	        Scanner sc = new Scanner(bReader); 
        while (sc.hasNextLine()) 
    	  {
    		  Progres_Bar(358, i);
	        	String l =  sc.nextLine();  
	        	
	            String[] token = l.split("\t");
	                if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
		    		{
		    		  oac.cARI_HESAP_MSSQL.hpln_kayit(token[0], token[1], token[2], token[3],GLOBAL.KULL_ADI);
		    		  oac.cARI_HESAP_MSSQL.hpln_ilk_detay_kayit(token[0]);
				    }
				    else
				    {
				    	oac.cARI_HESAP_MYSQL.hpln_kayit(token[0], token[1], token[2], token[3],GLOBAL.KULL_ADI);
			    		oac.cARI_HESAP_MYSQL.hpln_ilk_detay_kayit(token[0]);					   
				    }
	           i += 1 ;
        }
	        bReader.close();
	        Thread.currentThread().isInterrupted();
	        Progres_Bar_Temizle();
	        getContentPane().setCursor(DEFAULT_CURSOR);
	        JOptionPane.showMessageDialog(null, "Aktarma Islemi Tamamlandi ....Hesap Kodu Sayisi =" + i ); 
	        lblNewLabel.setText(Integer.toString(i));

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage()); 
		}
	    }
	    };
	    //// Progress Bar
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

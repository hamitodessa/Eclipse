package OBS_2025;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class HAKKINDA extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private JLabel lbljava;
	private JLabel lblver;
	private JLabel lbltar ;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HAKKINDA frame = new HAKKINDA();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public HAKKINDA()  {
		setTitle("HAKKINDA");
		setClosable(true);
		setBounds(100, 100, 450, 250);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Java Version");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(26, 11, 99, 14);
		panel.add(lblNewLabel);
		
		lbljava = new JLabel("...");
		lbljava.setForeground(new Color(0, 128, 128));
		lbljava.setBounds(144, 11, 76, 14);
		panel.add(lbljava);
		
		JLabel lblProgramVersion = new JLabel("Program Version");
		lblProgramVersion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblProgramVersion.setBounds(26, 36, 99, 14);
		panel.add(lblProgramVersion);
		
		lblver = new JLabel("...");
		lblver.setForeground(new Color(0, 0, 128));
		lblver.setBounds(144, 36, 44, 14);
		panel.add(lblver);
		
		lbljava.setText(Integer.toString(getVersion()));
		
		lbltar = new JLabel("...");
		lbltar.setForeground(new Color(0, 0, 128));
		lbltar.setBounds(198, 36, 67, 14);
		panel.add(lbltar);
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				        open("http://www.okumus.gen.tr");
				     
			}
		});
		lblNewLabel_1.setText("<html><font color=\"#0000CF\"><u>www.okumus.gen.tr</u></font></html>"); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setBounds(144, 75, 261, 14);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setText("<html><font color=\"#0000CF\"><u>info@okumus.gen.tr</u></font></html>");
		lblNewLabel_2.setBounds(144, 101, 261, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblinfookumus = new JLabel("@info_okumus");
		lblinfookumus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblinfookumus.setBounds(144, 166, 99, 14);
		panel.add(lblinfookumus);
		
		JLabel lblInfookumusgentr = new JLabel("info@okumus.gen.tr");
		lblInfookumusgentr.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblInfookumusgentr.setBounds(144, 190, 99, 14);
		panel.add(lblInfookumusgentr);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(26, 61, 379, 2);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(26, 151, 379, 2);
		panel.add(separator_1);
		
		JLabel lblNewLabel_1_1 = new JLabel();
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        open("http://www.obs-web.com");
			}
		});
		lblNewLabel_1_1.setText("<html><font color=\"#0000CF\"><u>www.obs-web.com</u></font></html>");
		lblNewLabel_1_1.setBounds(144, 126, 261, 14);
		lblNewLabel_1_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(HAKKINDA.class.getResource("/ICONLAR/twitter.png")));
		lblNewLabel_3.setBounds(101, 160, 19, 22);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon(HAKKINDA.class.getResource("/ICONLAR/skype.png")));
		lblNewLabel_3_1.setBounds(101, 184, 19, 22);
		panel.add(lblNewLabel_3_1);

		versiyon_oku();
	}
	private static void open(String sayfa) {
	
	    if (Desktop.isDesktopSupported()) {
	      try {
	    		URI uri = new URI(sayfa);
	        Desktop.getDesktop().browse(uri);
	      } catch (IOException | URISyntaxException e) { /* TODO: error handling */ }
	    } else { /* TODO: error handling */ }
	  }
	 private static int getVersion() {
	        String version = System.getProperty("java.version");
	        if(version.startsWith("1.")) {
	            version = version.substring(2, 3);
	        } else {
	            int dot = version.indexOf(".");
	            if(dot != -1) { version = version.substring(0, dot); }
	        } return Integer.parseInt(version);
	    }
	 private void versiyon_oku()
	    {
		 try {
	    	String fileName = "C:/OBS_SISTEM" + "/OBS_VERSION.txt";
	    	String line = null;
	    	FileReader fileReader = null;
	    	 int counter = 0;
	    	fileReader =  new FileReader(fileName);
            BufferedReader bufferedReader =    new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) 
            {
                counter++;
                if(counter == 1)
                {
            		lbltar.setText( line.toString());
                }
                else if(counter == 2)
                {
            		lblver.setText( line.toString());
               }
	        }   
	        	bufferedReader.close();
		 }catch (Exception ex)
		 {
			 JOptionPane.showMessageDialog(null,  "Versiyon Dosyasi Okunamadi.....",  "Versiyon Okuma", JOptionPane.ERROR_MESSAGE);    
		 }
	     
	    }
}

package obs.backup.other;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;



@SuppressWarnings("serial")
public class Hakkinda extends JPanel {
	
	private JLabel lbljava;
	private JLabel lblver;
	private JLabel lbltar ;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	
	public Hakkinda ()
	{
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Java Version");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(173, 56, 99, 14);
		add(lblNewLabel);

		lbljava = new JLabel("...");
		lbljava.setFont(new Font("Tahoma", Font.PLAIN, 14));
		//lbljava.setForeground(new Color(0, 128, 128));
		lbljava.setBounds(291, 56, 76, 14);
		add(lbljava);

		JLabel lblProgramVersion = new JLabel("Program Version");
		lblProgramVersion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblProgramVersion.setBounds(173, 81, 99, 14);
		add(lblProgramVersion);

		lblver = new JLabel("...");
		//lblver.setForeground(new Color(0, 0, 128));
		lblver.setBounds(291, 81, 44, 14);
		add(lblver);

		lbljava.setText(Integer.toString(getVersion()));

		lbltar = new JLabel("...");
		//lbltar.setForeground(new Color(0, 0, 128));
		lbltar.setBounds(345, 81, 67, 14);
		add(lbltar);

		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				open("http://www.okumus.gen.tr");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		lblNewLabel_1.setText("<html><font ><u>www.okumus.gen.tr</u></font></html>"); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_1.setCursor(new Cursor(Cursor.HAND_CURSOR));

		lblNewLabel_1.setBounds(173, 129, 261, 14);
		add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setText("<html><font ><u>info@okumus.gen.tr</u></font></html>");
		lblNewLabel_2.setBounds(173, 155, 261, 14);
		add(lblNewLabel_2);

		JLabel lblinfookumus = new JLabel("@info_okumus");
		lblinfookumus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblinfookumus.setBounds(216, 236, 99, 14);
		add(lblinfookumus);

		JLabel lblInfookumusgentr = new JLabel("info@okumus.gen.tr");
		lblInfookumusgentr.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblInfookumusgentr.setBounds(216, 260, 99, 14);
		add(lblInfookumusgentr);

		JSeparator separator = new JSeparator();
		separator.setBounds(173, 116, 379, 2);
		add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(173, 217, 379, 2);
		add(separator_1);

		JLabel lblNewLabel_1_1 = new JLabel();
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				open("http://www.obs-web.com");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

		});
		lblNewLabel_1_1.setText("<html><font ><u>www.obs-web.com</u></font></html>");
		lblNewLabel_1_1.setBounds(173, 180, 261, 14);
		lblNewLabel_1_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/ICONLAR/twitter.png")));
		lblNewLabel_3.setBounds(173, 230, 19, 22);
		add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/ICONLAR/skype.png")));
		lblNewLabel_3_1.setBounds(173, 254, 19, 22);
		add(lblNewLabel_3_1);

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
			OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() ); 
		}

	}
}

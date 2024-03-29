package OBS_2025;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

import OBS_C_2025.FIT_IMAGE;
import raven.toast.Notifications;

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
	private JLabel lblPid;
	public HAKKINDA()  {
		setTitle("HAKKINDA");
		setClosable(true);
		setBounds(100, 100, 450, 274);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(HAKKINDA.class.getResource("/ICONLAR/icons8-about-30.png")), 16, 16));//
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Java Version");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(26, 11, 99, 14);
		panel.add(lblNewLabel);

		lbljava = new JLabel("...");
		lbljava.setBounds(144, 11, 76, 14);
		panel.add(lbljava);

		JLabel lblProgramVersion = new JLabel("Program Version");
		lblProgramVersion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblProgramVersion.setBounds(26, 36, 99, 14);
		panel.add(lblProgramVersion);

		lblver = new JLabel("...");
		lblver.setBounds(144, 36, 44, 14);
		panel.add(lblver);

		lbljava.setText(Integer.toString(getVersion()));

		lbltar = new JLabel("...");
		lbltar.setBounds(198, 36, 67, 14);
		panel.add(lbltar);

		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				open("http://www.okumus.gen.tr");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

		});
		lblNewLabel_1.setText("<html><u>www.okumus.gen.tr</u></html>"); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel_1.setCursor(new Cursor(Cursor.HAND_CURSOR));

		lblNewLabel_1.setBounds(144, 75, 261, 14);
		panel.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setText("<html><u>info@okumus.gen.tr</u></html>");
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
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

		});
		lblNewLabel_1_1.setText("<html><u>www.obs-web.com</u></html>");
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
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(26, 215, 379, 2);
		panel.add(separator_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("PID");
		lblNewLabel_4.setBounds(26, 220, 46, 14);
		panel.add(lblNewLabel_4);
		
		lblPid = new JLabel("...");
		lblPid.setBounds(144, 220, 121, 14);
		panel.add(lblPid);

		versiyon_oku();
	}
	private static void open(String sayfa) {

		if (Desktop.isDesktopSupported()) {
			try {
				URI uri = new URI(sayfa);
				Desktop.getDesktop().browse(uri);
			} catch (IOException | URISyntaxException e) { }
		} else { }
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
					lbltar.setText( line.toString());
				else if(counter == 2)
					lblver.setText( line.toString());
			}   
			bufferedReader.close();
			int dosyaPID = (int) ProcessHandle.current().pid();
			lblPid.setText(String.valueOf(dosyaPID));
		}catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() ); 
		}

	}
}

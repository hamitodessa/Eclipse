package obs.backup.other;

import javax.swing.JPanel;


import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.SIFRE_DONDUR;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Date;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

import com.formdev.flatlaf.FlatClientProperties;



import javax.swing.border.TitledBorder;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@SuppressWarnings({"serial","deprecation"})
public class Ayarlar extends JPanel {

	public JButton btnKaydet;
	public JLabel lblNewLabel;
	public JLabel lblNewLabel_1;
	public JLabel lblNewLabel_2 ;
	public JLabel lblNewLabel_3;
	public JComboBox<String> comboBox;
	public JComboBox<String> comboBox_1;
	public JCheckBox chckbxSifrele;
	public JCheckBox chckbxPrgSifre;
	static BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	final boolean showTabsHeader = false;
	public JPasswordField passwordText;
	public Ayarlar()
	{
		setLayout(null);

		lblNewLabel = new JLabel("Gorunum");
		lblNewLabel.setBounds(42, 87, 85, 14);
		add(lblNewLabel);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"FlatCarbonIJ", "FlatMonocaiIJ", "FlatMacDarkLaf", "FlatNordIJ", "FlatHighContrastIJ", "FlatMaterialPalenightIJ", "FlatMaterialDeepOceanIJ","FlatArcDarkIJ","FlatGradiantoNatureGreenIJ","FlatGrayIJ","FlatMaterial","FlatArcOrangeIJ","Java"}));
		comboBox.setBounds(160, 83, 254, 22);
		add(comboBox);

		lblNewLabel_1 = new JLabel("Dil");
		
		lblNewLabel_1.setBounds(42, 24, 85, 14);
		add(lblNewLabel_1);

		comboBox_1 = new JComboBox<String>();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setText(dilSecenek.dil(comboBox_1.getSelectedItem().toString(),"Tema"));
				lblNewLabel_1.setText(dilSecenek.dil(comboBox_1.getSelectedItem().toString(),"Dil"));
				btnKaydet.setText(dilSecenek.dil(comboBox_1.getSelectedItem().toString(),"Kaydet"));
				lblNewLabel_2.setText(dilSecenek.dil(comboBox_1.getSelectedItem().toString(),"ZIP Sifrele"));
				lblNewLabel_3.setText(dilSecenek.dil(comboBox_1.getSelectedItem().toString(),"Acilis Sifre Sor"));
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Turkce", "English"}));
		comboBox_1.setBounds(160, 20, 105, 22);
		add(comboBox_1);

		btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					if(chckbxSifrele.isSelected())
					{
						if(passwordText.getText().equals(""))
						{
							passwordText.requestFocus();
							return;
						}
					}
					else {
						passwordText.setText("");
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					bckp.ayar_sil();
					SIFRE_DONDUR sDondur = new SIFRE_DONDUR();
					bckp.ayar_kayit(comboBox_1.getSelectedItem().toString(), comboBox.getSelectedItem().toString(),chckbxSifrele.isSelected() ? 1:0,
							sDondur.sDONDUR(passwordText) , chckbxPrgSifre.isSelected() ? 1:0);
					OBS_BACKUP.emirleriSTOPYAP();
					OBS_BACKUP.btnfont_tema.doClick();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnKaydet.setBounds(160, 347, 89, 23);
		add(btnKaydet);
		
		chckbxSifrele = new JCheckBox("");
		chckbxSifrele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxSifrele.isSelected())
				{
					passwordText.setVisible(true);
				}
				else {
					passwordText.setVisible(false);
				}
			}
		});
		chckbxSifrele.setBounds(160, 161, 99, 23);
		add(chckbxSifrele);
		
		lblNewLabel_2 = new JLabel("ZIP Sifrele");
		lblNewLabel_2.setBounds(42, 165, 105, 14);
		add(lblNewLabel_2);
		
		passwordText = new JPasswordField();
		passwordText.setVisible(false);
		passwordText.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
		passwordText.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		
		passwordText.setBounds(160, 201, 189, 20);
		add(passwordText);
		
		lblNewLabel_3 = new JLabel("Acilis Sifre Sor");
		lblNewLabel_3.setBounds(42, 271, 120, 14);
		add(lblNewLabel_3);
		
		chckbxPrgSifre = new JCheckBox("");
		chckbxPrgSifre.setBounds(160, 267, 99, 23);
		add(chckbxPrgSifre);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Windows Scheduler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(42, 432, 336, 53);
		add(panel);
		panel.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Windows ile Baslat");
		chckbxNewCheckBox.setBounds(115, 17, 157, 23);
		panel.add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (chckbxNewCheckBox.isSelected())  // Secili
					{
						createSchedulerStartup();
						//taskRunStartup();
						//createSchedulerLogin();
						//taskRunLogin();
					}
					else {
						taskDeleteStartUp();
						taskDeleteLogin();
					}
				} catch (Exception e2) {
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/save.png")));
		btnNewButton.setBounds(293, 17, 25, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Path path = Paths.get("C:\\OBS_SISTEM\\OBS_BACKUP.xml");
				Charset charset = StandardCharsets.UTF_8;

				String content = new String(Files.readAllBytes(path), charset);
				content = content.replaceAll("encoding=\"UTF-8\"", "");
					Files.write(path, content.getBytes(charset));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(388, 446, 89, 23);
		add(btnNewButton_1);
	}

	private String kopyaYaz(String dosyaAdi)
	{
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		s= "C:\\OBS_SISTEM" ;
		try {
			
		InputStream iss = this.getClass().getClassLoader().getResourceAsStream("obs/backup/scheduler/" + dosyaAdi + ".xml");
		
		String inputFile = s + "\\" + dosyaAdi + ".xml";
		Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(new InputSource(iss));
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodes = (NodeList)xpath
			.evaluate("//Command[text()='C:\\OBS_SISTEM\\OBS_BACKUP.exe']", doc, XPathConstants.NODESET);
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			nodes.item(idx).setTextContent(s + "\\OBS_BACKUP.exe");
		}
		Transformer xformer = TransformerFactory.newInstance().newTransformer();
		StreamResult sr = new StreamResult(new FileOutputStream(new File(inputFile),false));
		xformer.transform(new DOMSource(doc), sr);
		sr.getOutputStream().close();
		  
		} catch (Exception e2) {
			System.out.println("==: " + e2.getMessage());
		}
		return s ;
	}
	private void createSchedulerStartup() throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		String patHString =  kopyaYaz("OBS_BACKUP");
		//InputStream iss = this.getClass().getClassLoader().getResourceAsStream("obs/backup/scheduler/OBS_BACKUP.xml");
		//Files.copy(iss, Paths.get(GLOBAL.SURUCU + "\\OBS_BACKUP.xml"),StandardCopyOption.REPLACE_EXISTING);
		
		Path path = Paths.get(patHString + "\\OBS_BACKUP.xml");
		Charset charset = StandardCharsets.UTF_8;

		String content = new String(Files.readAllBytes(path), charset);
		content = content.replaceAll("encoding=\"UTF-8\"", "");
		Files.write(path, content.getBytes(charset));
			
			
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec("schtasks.exe /Create /XML C:\\OBS_SISTEM\\OBS_BACKUP.xml /TN OBS_BACKUP /RU SYSTEM");
	
		// stdout
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null) 
		{
			if(! line.equals("")) 
			{
				bckp.log_kayit("System", new Date(), line);
				OBS_BACKUP.mesajGoster(10000,Notifications.Type.INFO, line); 
			}  
		}
		//stderr
		is = p.getErrorStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		while ((line = br.readLine()) != null) {
			if(! line.equals("")) 
			{
				bckp.log_kayit("System", new Date(), line);
				OBS_BACKUP.mesajGoster(10000,Notifications.Type.ERROR, line); 
			}
			
		}
		//Files.delete( Paths.get(GLOBAL.SURUCU + "\\OBS_BACKUP.xml"));
		
		
	}
	private void createSchedulerLogin() throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		InputStream iss = this.getClass().getClassLoader().getResourceAsStream("obs/backup/scheduler/OBS_BACKUP_LOGIN.xml");
		Files.copy(iss, Paths.get(GLOBAL.SURUCU + "\\OBS_BACKUP_LOGIN.xml"),StandardCopyOption.REPLACE_EXISTING);
		
		iss.close();
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec("schtasks.exe /Create /XML C:\\OBS_SISTEM\\OBS_BACKUP_LOGIN.xml /TN OBS_BACKUP_LOGIN ");
	
		// stdout
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null) {
			bckp.log_kayit("System", new Date(), line);
		OBS_BACKUP.	mesajGoster(10000,Notifications.Type.INFO, line);     
		}
		//stderr
		is = p.getErrorStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		while ((line = br.readLine()) != null) {
			bckp.log_kayit("System", new Date(), line);
			OBS_BACKUP.mesajGoster(10000,Notifications.Type.ERROR, line); 
		}
		Files.delete( Paths.get(GLOBAL.SURUCU + "\\OBS_BACKUP_LOGIN.xml"));
		
		
	}
	private void taskRunStartup() throws IOException, ClassNotFoundException, SQLException 
	{
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec("schtasks.exe /RUN /TN OBS_BACKUP");

		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		is = p.getInputStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);

		while ((line = br.readLine()) != null) {
			bckp.log_kayit("System", new Date(), line);
			OBS_BACKUP.mesajGoster(10000,Notifications.Type.INFO, line); 
		}
		//stderr
		is = p.getErrorStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		while ((line = br.readLine()) != null) {
			bckp.log_kayit("System", new Date(), line);
			OBS_BACKUP.mesajGoster(10000,Notifications.Type.ERROR, line); 
		}
	}
	private void taskRunLogin() throws IOException, ClassNotFoundException, SQLException 
	{
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec("schtasks.exe /RUN /TN OBS_BACKUP_LOGIN");

		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		is = p.getInputStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);

		while ((line = br.readLine()) != null) {
			bckp.log_kayit("System", new Date(), line);
			OBS_BACKUP.mesajGoster(10000,Notifications.Type.INFO, line); 
		}
		//stderr
		is = p.getErrorStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		while ((line = br.readLine()) != null) {
			bckp.log_kayit("System", new Date(), line);
			OBS_BACKUP.mesajGoster(10000,Notifications.Type.ERROR, line); 
		}
	}
	private void taskDeleteStartUp() throws IOException, ClassNotFoundException, SQLException
	{
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec("schtasks.exe /Delete /TN OBS_BACKUP /F");
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null) {
			bckp.log_kayit("System", new Date(), line);
			OBS_BACKUP.mesajGoster(7500,Notifications.Type.INFO, line); 
		}

		//stderr
		is = p.getErrorStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		while ((line = br.readLine()) != null) {
			bckp.log_kayit("System", new Date(), line);
			OBS_BACKUP.mesajGoster(10000,Notifications.Type.ERROR, line); 
		}

	}
	private void taskDeleteLogin() throws IOException, ClassNotFoundException, SQLException
	{
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec("schtasks.exe /Delete /TN OBS_BACKUP_LOGIN /F");
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null) {
			bckp.log_kayit("System", new Date(), line);
			OBS_BACKUP.mesajGoster(10000,Notifications.Type.INFO, line); 
		}

		//stderr
		is = p.getErrorStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		while ((line = br.readLine()) != null) {
			bckp.log_kayit("System", new Date(), line);
			OBS_BACKUP.mesajGoster(10000,Notifications.Type.ERROR, line); 
		}

	}
}

//			SIFRE_DONDUR sDondur = new SIFRE_DONDUR();
//System.out.println(sDondur.sDONDUR(ayarlarPanel.passwordText));

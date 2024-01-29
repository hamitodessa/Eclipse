package obs.backup.other;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatClientProperties;

import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.JTextFieldRegularPopupMenu;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SIFRE_DONDUR;
import obs.backup.ayarlar.dilAciklamalar;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerBilgileri extends JPanel {

	private static final long serialVersionUID = 1L;
	public Obs_TextFIeld textMSServer;
	public Obs_TextFIeld textMSkull;
	public JPasswordField textMSsifre;
	public Obs_TextFIeld textMykull;
	public JPasswordField textMySifre;
	public Obs_TextFIeld textMYPort;
	public Obs_TextFIeld textMyDump;
	public Obs_TextFIeld textMSPort;
	public JTabbedPane tabbedPane ;
	
	public JPanel panel_2;
	public JPanel panel_2_2 ;
	
	public JButton btnMSkaydet;
	public JButton btnMSTest;
	public JButton btnDumpSec;
	public JButton btnMyTest;
	public JButton btnMyKaydet;
	
	public static JLabel lblNewLabel_1;
	public static JLabel lblNewLabel_2 ;
	public static JLabel lblNewLabel_2_2;
	public static JLabel lblNewLabel_1_2;
	/**
	 * Create the panel.
	 */
	public ServerBilgileri() {
		setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("MS Sql", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Instance");
		lblNewLabel.setBounds(37, 38, 83, 14);
		panel.add(lblNewLabel);
		
		textMSServer = new Obs_TextFIeld(50,"");
		JTextFieldRegularPopupMenu.addTo(textMSServer);
		textMSServer.setBounds(140, 35, 185, 20);
		panel.add(textMSServer);
		textMSServer.setColumns(10);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Baglanti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(37, 94, 339, 104);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Kullanici");
		lblNewLabel_1.setBounds(10, 30, 93, 14);
		panel_2.add(lblNewLabel_1);
		
		textMSkull = new Obs_TextFIeld(50,"");
		JTextFieldRegularPopupMenu.addTo(textMSkull);
		textMSkull.setBounds(103, 27, 188, 20);
		panel_2.add(textMSkull);
		textMSkull.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Sifre");
		lblNewLabel_2.setBounds(10, 66, 83, 14);
		panel_2.add(lblNewLabel_2);
		
		textMSsifre = new JPasswordField();
		//textMSsifre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Sifre");
		textMSsifre.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		JTextFieldRegularPopupMenu.addTo(textMSsifre);
		textMSsifre.setBounds(103, 63, 188, 20);
		panel_2.add(textMSsifre);
		
		btnMSTest = new JButton("Baglanti Test");
		btnMSTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(textMSServer.getText().equals(""))
				{
					textMSServer.requestFocus();
					return;
				}
				if(textMSkull.getText().equals("")) 
				{
					textMSkull.requestFocus();
					return;
				}
				if(textMSServer.getText().equals(""))
				{
					textMSServer.requestFocus();
					return;
				}
				SIFRE_DONDUR sdon = new SIFRE_DONDUR();
				String response =sdon.sDONDUR(textMSsifre);
				BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
			try {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Boolean result =	bckp.MsSql_server_test( textMSServer.getText() ,textMSkull.getText(),response,textMSPort.getText());
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if(result)
			{
				OBS_BACKUP.mesajGoster(5000,Notifications.Type.INFO, dilSecenek.dil(OBS_BACKUP.dILS,"Baglanti Saglandi"));
			}
			else {
				OBS_BACKUP.mesajGoster(5000,Notifications.Type.ERROR, dilSecenek.dil(OBS_BACKUP.dILS,"Baglanti Saglanamadi"));
			}
			} catch (Exception e1) {
			
				e1.printStackTrace();
			}

			}
		});
		btnMSTest.setBounds(37, 517, 129, 23);
		panel.add(btnMSTest);
		
		btnMSkaydet = new JButton("Kaydet");
		btnMSkaydet.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					if(textMSServer.getText().equals(""))
					{
						textMSServer.requestFocus();
						return ;
					}
					if(textMSkull.getText().equals(""))
					{
						textMSkull.requestFocus();
						return ;
					}
					if(textMSsifre.getText().equals(""))
					{
						textMSsifre.requestFocus();
						return ;
					}
					if(OBS_BACKUP.emirAnaGirisPanel.txtEmir.getText().equals(""))
					{
						OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING, dilAciklamalar.dilAciklama(OBS_BACKUP.dILS,"Emir Adi Bos Olamaz"));  
						return;
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					OBS_BACKUP.msServerKayit();
					OBS_BACKUP.tabbedPane_1.setSelectedIndex(0);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
				
					OBS_BACKUP.mesajGoster(5000,Notifications.Type.ERROR, e1.getMessage());
				}
			}
		});
	
		btnMSkaydet.setBounds(624, 517, 100, 23);
		panel.add(btnMSkaydet);
		
		JLabel lblNewLabel_4_1 = new JLabel("Port");
		lblNewLabel_4_1.setBounds(358, 38, 48, 14);
		panel.add(lblNewLabel_4_1);
		
		textMSPort = new Obs_TextFIeld(10,"");
		JTextFieldRegularPopupMenu.addTo(textMSPort);
		textMSPort.setColumns(10);
		textMSPort.setBounds(415, 32, 61, 20);
		panel.add(textMSPort);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("MY Sql", null, panel_1, null);
		panel_1.setLayout(null);
		

		panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBorder(new TitledBorder(null, "Baglanti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2_2.setBounds(37, 94, 440, 104);
		panel_1.add(panel_2_2);
		
		lblNewLabel_1_2 = new JLabel("Kullanici");
		lblNewLabel_1_2.setBounds(10, 30, 82, 14);
		panel_2_2.add(lblNewLabel_1_2);
		
		textMykull = new Obs_TextFIeld(50,"");
		JTextFieldRegularPopupMenu.addTo(textMykull);
		textMykull.setColumns(10);
		textMykull.setBounds(103, 27, 209, 20);
		panel_2_2.add(textMykull);
		
		lblNewLabel_2_2 = new JLabel("Sifre");
		lblNewLabel_2_2.setBounds(10, 66, 71, 14);
		panel_2_2.add(lblNewLabel_2_2);
		
		textMySifre = new JPasswordField();
		//textMySifre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Sifre");
		textMySifre.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		JTextFieldRegularPopupMenu.addTo(textMySifre);
		textMySifre.setBounds(103, 63, 209, 20);
		panel_2_2.add(textMySifre);
		
		btnMyTest = new JButton("Baglanti Test");
		btnMyTest.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(textMYPort.getText().equals(""))
				{
					textMYPort.requestFocus();
					return;
				}
				if(textMykull.getText().equals(""))
				{
					textMykull.requestFocus();
					return;
				}
				if(textMySifre.getText().equals(""))
				{
					textMySifre.requestFocus();
					return;
				}
				SIFRE_DONDUR sdon = new SIFRE_DONDUR();
				String response =sdon.sDONDUR(textMySifre);
				BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
			try {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Boolean result =	bckp.MySql_server_test( textMykull.getText(),response,textMYPort.getText());
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			if(result)
			{
				OBS_BACKUP.mesajGoster(5000,Notifications.Type.INFO,dilSecenek.dil(OBS_BACKUP.dILS,"Baglanti Saglandi") );
			}
			else {
				OBS_BACKUP.mesajGoster(5000,Notifications.Type.ERROR, dilSecenek.dil(OBS_BACKUP.dILS,"Baglanti Saglanamadi"));
			}
			} catch (Exception e1) {
			
				e1.printStackTrace();
			}

			}
		});
		btnMyTest.setBounds(37, 517, 129, 23);
		panel_1.add(btnMyTest);
		
		btnMyKaydet = new JButton("Kaydet");
		btnMyKaydet.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					if(textMYPort.getText().equals(""))
					{
						textMYPort.requestFocus();
						return ;
					}
					if(textMykull.getText().equals(""))
					{
						textMykull.requestFocus();
						return ;
					}
					if(textMySifre.getText().equals(""))
					{
						textMySifre.requestFocus();
						return ;
					}
					if(textMyDump.getText().equals(""))
					{
						OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING,dilSecenek.dil(OBS_BACKUP.dILS,"Backup islemi icin  'mysqldump.exe'  surucusu belirtilmelidir") );
						return;
					}
					if(OBS_BACKUP.emirAnaGirisPanel.txtEmir.getText().equals(""))
					{
						OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING, dilAciklamalar.dilAciklama(OBS_BACKUP.dILS,"Emir Adi Bos Olamaz"));  
						return;
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					OBS_BACKUP.myServerKayit();
					OBS_BACKUP.tabbedPane_1.setSelectedIndex(0);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
				
					OBS_BACKUP.mesajGoster(5000,Notifications.Type.ERROR, e1.getMessage());
				}
			}
		});
		btnMyKaydet.setBounds(624, 517, 100, 23);
		panel_1.add(btnMyKaydet);
		
		JLabel lblNewLabel_4 = new JLabel("Port");
		lblNewLabel_4.setBounds(37, 38, 48, 14);
		panel_1.add(lblNewLabel_4);
		
		textMYPort = new Obs_TextFIeld(10,"");
		JTextFieldRegularPopupMenu.addTo(textMYPort);
		textMYPort.setBounds(140, 35, 73, 20);
		panel_1.add(textMYPort);
		textMYPort.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("My Sql Dump");
		lblNewLabel_5.setBounds(47, 219, 89, 14);
		panel_1.add(lblNewLabel_5);
		
		textMyDump = new Obs_TextFIeld(200,"C:\\Program Files\\MySQL\\MySQL Workbench 8.0\\");
		JTextFieldRegularPopupMenu.addTo(textMyDump);
		textMyDump.setBounds(137, 213, 587, 20);
		panel_1.add(textMyDump);
		textMyDump.setColumns(10);
		
		btnDumpSec = new JButton( dilSecenek.dil(OBS_BACKUP.dILS,"Sec"));
		btnDumpSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				UIManager.put("FileChooser.cancelButtonText", dilSecenek.dil(OBS_BACKUP.dILS,"Vazgec"));
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle(dilSecenek.dil(OBS_BACKUP.dILS,"Surucu Sec"));
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText( dilSecenek.dil(OBS_BACKUP.dILS,"Surucu Sec"));
				chooser.setApproveButtonToolTipText(dilSecenek.dil(OBS_BACKUP.dILS,"Surucu Sec"));
				chooser.setApproveButtonMnemonic('s');
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					textMyDump.setText(chooser.getSelectedFile().toString());
				}
				else {
					// System.out.println("No Selection ");
				}
			}
		});
		btnDumpSec.setBounds(587, 245, 137, 23);
		panel_1.add(btnDumpSec);

	}
}

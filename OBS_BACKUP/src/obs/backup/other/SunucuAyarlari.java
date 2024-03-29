package obs.backup.other;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatClientProperties;

import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SIFRE_DONDUR;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class SunucuAyarlari extends JPanel {

	static BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private static final long serialVersionUID = 1L;
	public Obs_TextFIeld textHost;
	public Obs_TextFIeld textKull;
	public JPasswordField textSifre;
	public Obs_TextFIeld textFtpSurucu;
	public Obs_TextFIeld textPort;
	public Obs_TextFIeld textZmnasm;
	
	public Obs_TextFIeld textSurucu;
	public Obs_TextFIeld textEskisilme;
	
	public JCheckBox chckbxFtp;
	public JCheckBox chckbxYerel ;
	private JLabel lblNewLabel;
	public JLabel lblNewLabel_1;
	public JLabel lblNewLabel_2;
	public JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	public JLabel lblNewLabel_5;
	public JLabel lblNewLabel_6;
	public JLabel lblNewLabel_7;
	public JLabel lblNewLabel_8;
	public JLabel lblNewLabel_9;
	
	public JButton btnNewButton_6 ;
	public JButton btnNewButton_7 ;
	public JButton btnNewButton_9;
	public static JButton btnftpkont;
	
	public JPanel panel_12 ;
	public JPanel panel_12_1;
	public JPanel panel_12_1_1;
	public JPanel panel_12_1_1_1;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("deprecation")
	public SunucuAyarlari() {

		setLayout(null);
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		chckbxFtp = new JCheckBox("FTP");
		chckbxFtp.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxFtp.setSelected(true);
		chckbxFtp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxFtp.isSelected())
				{
					chckbxYerel.setSelected(false);
					btnNewButton_6.setEnabled(true);
					btnNewButton_7.setEnabled(false);
					btnftpkont.setEnabled(true);
					textHost.setEnabled(true);
					textKull.setEnabled(true);
					textSifre.setEnabled(true);
					textSurucu.setEnabled(false);
					textFtpSurucu.setEnabled(true);
					textZmnasm.setEnabled(true);
					textPort.setEnabled(true);
				}
				else
					chckbxYerel.setSelected(true);
			}
		});
		chckbxFtp.setBounds(135, 22, 99, 23);
		 add(chckbxFtp);
		
		chckbxYerel = new JCheckBox("Yerel Surucu");
		chckbxYerel.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxYerel.setBounds(276, 22, 134, 23);
		chckbxYerel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxYerel.isSelected())
				{
					chckbxFtp.setSelected(false);
					btnNewButton_6.setEnabled(false);
					btnNewButton_7.setEnabled(true);
					btnftpkont.setEnabled(false);
					textHost.setEnabled(false);
					textKull.setEnabled(false);
					textSifre.setEnabled(false);
					textSurucu.setEnabled(true);
					textFtpSurucu.setEnabled(false);
					textZmnasm.setEnabled(false);
					textPort.setEnabled(false);
				}
				else
					chckbxFtp.setSelected(true);
			}
		});
		 add(chckbxYerel);
		
		panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "FTP Ayarlari", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		panel_12.setBounds(37, 75, 627, 100);
		add(panel_12);
		panel_12.setLayout(null);
		
		textHost = new Obs_TextFIeld(30,"");
		textHost.setBounds(135, 22, 300, 20);
		panel_12.add(textHost);
		textHost.setColumns(10);
		
		textKull = new Obs_TextFIeld(30,"");
		textKull.setBounds(135, 46, 213, 20);
		panel_12.add(textKull);
		textKull.setColumns(10);
		
		textSifre = new JPasswordField();
		//textSifre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Sifre");
		textSifre.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		textSifre.setToolTipText("");
		textSifre.setBounds(135, 69, 213, 20);
		panel_12.add(textSifre);
		textSifre.setColumns(10);
		
		lblNewLabel = new JLabel("Host");
		lblNewLabel.setBounds(20, 25, 48, 14);
		panel_12.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Kullanici");
		lblNewLabel_1.setBounds(20, 49, 105, 14);
		panel_12.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Sifre");
		lblNewLabel_2.setBounds(20, 72, 105, 14);
		panel_12.add(lblNewLabel_2);
		
		panel_12_1 = new JPanel();
		panel_12_1.setLayout(null);
		panel_12_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "FTP Diger Ayarlar", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		panel_12_1.setBounds(37, 180, 627, 100);
		 add(panel_12_1);
		
		textFtpSurucu = new Obs_TextFIeld(200,"");
		textFtpSurucu.setColumns(10);
		textFtpSurucu.setBounds(135, 22, 482, 20);
		panel_12_1.add(textFtpSurucu);
		
		textZmnasm = new Obs_TextFIeld(10,"");
		textZmnasm.setText("120");
		textZmnasm.setColumns(10);
		textZmnasm.setBounds(135, 46, 75, 20);
		panel_12_1.add(textZmnasm);
		
		textPort = new Obs_TextFIeld(3,"");
		textPort.setText("21");
		textPort.setColumns(10);
		textPort.setBounds(378, 46, 57, 20);
		panel_12_1.add(textPort);
		
		btnNewButton_6 = new JButton("Surucu Kontrol");
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FtpKontrol();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_6.setBounds(497, 68, 120, 23);
		panel_12_1.add(btnNewButton_6);
		
		lblNewLabel_3 = new JLabel("Surucu");
		lblNewLabel_3.setBounds(25, 25, 100, 14);
		panel_12_1.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Port");
		lblNewLabel_4.setBounds(311, 49, 57, 14);
		panel_12_1.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Zaman Asimi");
		lblNewLabel_5.setBounds(25, 49, 110, 14);
		panel_12_1.add(lblNewLabel_5);
		
		lblNewLabel_9 = new JLabel("sn.");
		lblNewLabel_9.setBounds(215, 49, 70, 14);
		panel_12_1.add(lblNewLabel_9);
		
		panel_12_1_1 = new JPanel();
		panel_12_1_1.setLayout(null);
		panel_12_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Yerel Surucu", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		panel_12_1_1.setBounds(37, 285, 627, 75);
		add(panel_12_1_1);
		
		textSurucu = new Obs_TextFIeld(200,"");
		textSurucu.setEnabled(false);
		textSurucu.setToolTipText("");
		textSurucu.setColumns(10);
		textSurucu.setBounds(135, 22, 482, 20);
		panel_12_1_1.add(textSurucu);
		
		btnNewButton_7 = new JButton( dilSecenek.dil(OBS_BACKUP.dILS, "Surucu Sec"));
		btnNewButton_7.setEnabled(false);
		btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				UIManager.put("FileChooser.cancelButtonText",  dilSecenek.dil(OBS_BACKUP.dILS, "Vazgec"));
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle(dilSecenek.dil(OBS_BACKUP.dILS, "Surucu Sec"));
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText(dilSecenek.dil(OBS_BACKUP.dILS, "Surucu Sec"));
				chooser.setApproveButtonToolTipText(dilSecenek.dil(OBS_BACKUP.dILS, "Surucu Sec"));
				chooser.setApproveButtonMnemonic('s');
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
					textSurucu.setText(chooser.getSelectedFile().toString());
			}
		});
		btnNewButton_7.setBounds(497, 45, 120, 23);
		panel_12_1_1.add(btnNewButton_7);
		
		lblNewLabel_8 = new JLabel("Surucu");
		lblNewLabel_8.setBounds(22, 25, 103, 14);
		panel_12_1_1.add(lblNewLabel_8);
		
		panel_12_1_1_1 = new JPanel();
		panel_12_1_1_1.setLayout(null);
		panel_12_1_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Eski Yedek", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		panel_12_1_1_1.setBounds(37, 365, 627, 75);
		 add(panel_12_1_1_1);
		
		textEskisilme = new Obs_TextFIeld(3,"");
		textEskisilme.setText("0");
		textEskisilme.setColumns(10);
		textEskisilme.setBounds(135, 22, 68, 20);
		panel_12_1_1_1.add(textEskisilme);
		
		lblNewLabel_6 = new JLabel("Eski Yed.Silme");
		lblNewLabel_6.setBounds(10, 25, 115, 14);
		panel_12_1_1_1.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("gunden eski olanari silme (0 Silinmez)");
		lblNewLabel_7.setBounds(210, 25, 324, 14);
		panel_12_1_1_1.add(lblNewLabel_7);


		btnNewButton_9 = new JButton("Kaydet");
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_9.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					if (OBS_BACKUP.backupTime ) return;
					if(chckbxYerel.isSelected())
					{
						if(textSurucu.getText().equals(""))
						{
							OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING,dilSecenek.dil(OBS_BACKUP.dILS, "Surucu Secilmemis") );
							return;
						}
					}
					else if(chckbxFtp.isSelected())
					{
						if(textHost.getText().equals("") || textKull.getText().equals("") || textSifre.getText().equals(""))
						{
							OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING,dilSecenek.dil(OBS_BACKUP.dILS, "FTP Baglanti Bilgileri Eksik") );
							return;
						}
						else if(textFtpSurucu.getText().equals(""))
						{
							OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING,dilSecenek.dil(OBS_BACKUP.dILS, "FTP Surucu Secilmemis") );
							return;
						}
					}
					if(OBS_BACKUP.emirAnaGirisPanel.txtEmir.getText().equals(""))
					{
						OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING, dilSecenek.dil(OBS_BACKUP.dILS,"Emir Adi Bos Olamaz"));  
						return;
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					OBS_BACKUP.sunucuKaydet();
					OBS_BACKUP.tabbedPane_1.setSelectedIndex(0);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
					OBS_BACKUP.mesajGoster(5000,Notifications.Type.ERROR, e1.getMessage());
				}
			}
		});
		btnNewButton_9.setBounds(624, 550, 100, 23);
		add(btnNewButton_9);

		btnftpkont = new JButton("Ftp Kontrol");
		btnftpkont.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnftpkont.setBounds(37, 550, 100, 23);
		btnftpkont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AnaFtpKontrol();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		add(btnftpkont);
	}
	private void AnaFtpKontrol() throws SocketException, IOException 
	{
		if (chckbxYerel.isSelected()) return;
		if (textHost.getText().equals("")) 
		{
			textHost.requestFocus();
			return;
		}
		if (textKull.getText().equals("")) 
		{
			textKull.requestFocus();
			return;
		}
		if (textSifre.getPassword().length == 0) 
		{
			textSifre.requestFocus();
			return;
		}
		if (textPort.getText().equals("")) 
		{
			textPort.requestFocus();
			return;
		}
		int portt = 21 ;
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if(!  textPort.getText().equals("21"))
			portt = Integer.parseInt(textPort.getText());
	
		SIFRE_DONDUR sdon = new SIFRE_DONDUR();
		String response =sdon.sDONDUR(textSifre);

		boolean result =   bckp.DoesFtpExist(textHost.getText() ,portt,textKull.getText(), response);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		if(result)
			OBS_BACKUP.mesajGoster(5000,Notifications.Type.INFO,dilSecenek.dil(OBS_BACKUP.dILS, "Baglanti Gerceklesti") );
		else
			OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING, dilSecenek.dil(OBS_BACKUP.dILS,"Baglanti Hata"));
	}
	private void FtpKontrol() throws SocketException, IOException 
	{
		if (chckbxYerel.isSelected()) return;
		if (textHost.getText().equals("")) 
		{
			textHost.requestFocus();
			return;
		}
		if (textKull.getText().equals(""))
		{
			textKull.requestFocus();
			return;
		}
		if (textSifre.getPassword().length == 0) 
		{
			textSifre.requestFocus();
			return;
		}
		if (textFtpSurucu.getText().equals("")) 
		{
			textFtpSurucu.requestFocus();
			return;
		}
		if (textPort.getText().equals("")) 
		{
			textPort.requestFocus();
			return;
		}
		int portt = 21 ;
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		if(! textPort.getText().equals("21"))
			portt = Integer.parseInt(textPort.getText());
		SIFRE_DONDUR sdon = new SIFRE_DONDUR();
		String response =sdon.sDONDUR(textSifre);
		
		boolean result =   bckp.DoesFtpDirectoryExist(textHost.getText() ,textFtpSurucu.getText(),portt,textKull.getText(), response);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		if(result)
			OBS_BACKUP.mesajGoster(5000,Notifications.Type.INFO, dilSecenek.dil(OBS_BACKUP.dILS, "Baglanti Gerceklesti"));
		else
			OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING, dilSecenek.dil(OBS_BACKUP.dILS,"Baglanti Hata"));
	}
}

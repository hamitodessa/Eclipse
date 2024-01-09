package obs.backup.other;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatClientProperties;

import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.JTextFieldRegularPopupMenu;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SIFRE_DONDUR;
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
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	
	public JButton btnNewButton_6 ;
	public JButton btnNewButton_7 ;
	private static JButton btnftpkont;
	/**
	 * Create the panel.
	 */
	public SunucuAyarlari() {

		setLayout(null);
		
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
				}
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
				}
			}
		});
		 add(chckbxYerel);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(null, "FTP Ayarlari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_12.setBounds(10, 75, 627, 100);
		 add(panel_12);
		panel_12.setLayout(null);
		
		textHost = new Obs_TextFIeld(30,"Host");
		JTextFieldRegularPopupMenu.addTo(textHost);
		textHost.setBounds(135, 22, 300, 20);
		panel_12.add(textHost);
		textHost.setColumns(10);
		
		textKull = new Obs_TextFIeld(30,"Kullanici");
		JTextFieldRegularPopupMenu.addTo(textKull);
		textKull.setBounds(135, 46, 213, 20);
		panel_12.add(textKull);
		textKull.setColumns(10);
		
		textSifre = new JPasswordField();
		textSifre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Sifre");
		textSifre.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		JTextFieldRegularPopupMenu.addTo(textSifre);
		textSifre.setToolTipText("");
		textSifre.setBounds(135, 69, 213, 20);
		panel_12.add(textSifre);
		textSifre.setColumns(10);
		
		lblNewLabel = new JLabel("Host");
		lblNewLabel.setBounds(20, 25, 48, 14);
		panel_12.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Kullanici");
		lblNewLabel_1.setBounds(20, 49, 48, 14);
		panel_12.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Sifre");
		lblNewLabel_2.setBounds(20, 72, 48, 14);
		panel_12.add(lblNewLabel_2);
		
		JPanel panel_12_1 = new JPanel();
		panel_12_1.setLayout(null);
		panel_12_1.setBorder(new TitledBorder(null, "FTP Diger Ayarlar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_12_1.setBounds(10, 180, 627, 100);
		 add(panel_12_1);
		
		textFtpSurucu = new Obs_TextFIeld(200, "Ftp Surucu");
		JTextFieldRegularPopupMenu.addTo(textFtpSurucu);
		textFtpSurucu.setColumns(10);
		textFtpSurucu.setBounds(135, 22, 482, 20);
		panel_12_1.add(textFtpSurucu);
		
		textZmnasm = new Obs_TextFIeld(10,"Saniye");
		JTextFieldRegularPopupMenu.addTo(textZmnasm);
		textZmnasm.setText("120");
		textZmnasm.setColumns(10);
		textZmnasm.setBounds(135, 46, 75, 20);
		panel_12_1.add(textZmnasm);
		
		textPort = new Obs_TextFIeld(3,"Port");
		JTextFieldRegularPopupMenu.addTo(textPort);
		textPort.setText("21");
		textPort.setColumns(10);
		textPort.setBounds(378, 46, 57, 20);
		panel_12_1.add(textPort);
		
		btnNewButton_6 = new JButton("Surucu Kontrol");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FtpKontrol();
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_6.setBounds(502, 68, 115, 23);
		panel_12_1.add(btnNewButton_6);
		
		lblNewLabel_3 = new JLabel("Surucu");
		lblNewLabel_3.setBounds(25, 25, 48, 14);
		panel_12_1.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Port");
		lblNewLabel_4.setBounds(311, 49, 57, 14);
		panel_12_1.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Zaman Asimi");
		lblNewLabel_5.setBounds(25, 49, 89, 14);
		panel_12_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_9 = new JLabel("sn.");
		lblNewLabel_9.setBounds(209, 49, 48, 14);
		panel_12_1.add(lblNewLabel_9);
		
		JPanel panel_12_1_1 = new JPanel();
		panel_12_1_1.setLayout(null);
		panel_12_1_1.setBorder(new TitledBorder(null, "Yerel Surucu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		panel_12_1_1.setBounds(10, 285, 627, 75);
		 add(panel_12_1_1);
		
		textSurucu = new Obs_TextFIeld(200,"Surucu");
		JTextFieldRegularPopupMenu.addTo(textSurucu);
		textSurucu.setToolTipText("");
		textSurucu.setColumns(10);
		textSurucu.setBounds(135, 22, 482, 20);
		panel_12_1_1.add(textSurucu);
		
		btnNewButton_7 = new JButton("Surucu Sec");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Surucu Seciniz");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Surucu Sec");
				chooser.setApproveButtonToolTipText("Surucu Sec");
				chooser.setApproveButtonMnemonic('s');
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					textSurucu.setText(chooser.getSelectedFile().toString());
				}
				else {
					// System.out.println("No Selection ");
				}
			}
		});
		btnNewButton_7.setBounds(502, 45, 115, 23);
		panel_12_1_1.add(btnNewButton_7);
		
		lblNewLabel_8 = new JLabel("Surucu");
		lblNewLabel_8.setBounds(22, 25, 92, 14);
		panel_12_1_1.add(lblNewLabel_8);
		
		JPanel panel_12_1_1_1 = new JPanel();
		panel_12_1_1_1.setLayout(null);
		panel_12_1_1_1.setBorder(new TitledBorder(null, "Eski Yedek", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		panel_12_1_1_1.setBounds(10, 365, 627, 75);
		 add(panel_12_1_1_1);
		
		textEskisilme = new Obs_TextFIeld(3,"Gun");
		JTextFieldRegularPopupMenu.addTo(textEskisilme);
		textEskisilme.setText("0");
		textEskisilme.setColumns(10);
		textEskisilme.setBounds(135, 22, 68, 20);
		panel_12_1_1_1.add(textEskisilme);
		
		lblNewLabel_6 = new JLabel("Eski Yed.Silme");
		lblNewLabel_6.setBounds(10, 25, 104, 14);
		panel_12_1_1_1.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("gunden eski olanari silme (0 Silinmez)");
		lblNewLabel_7.setBounds(210, 25, 252, 14);
		panel_12_1_1_1.add(lblNewLabel_7);
		
	
		JButton btnNewButton_9 = new JButton("Kaydet");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					OBS_BACKUP.sunucuKaydet();
					OBS_BACKUP.tabbedPane_1.setSelectedIndex(0);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
				
					OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, e1.getMessage());
				}
			}
		});
		btnNewButton_9.setBounds(624, 581, 100, 23);
		 add(btnNewButton_9);
		 
		 btnftpkont = new JButton("Ftp Kontrol");
		 btnftpkont.setBounds(10, 581, 100, 23);
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
		if (textHost.getText().equals("")) return;
		if (textKull.getText().equals("")) return;
		if (textSifre.getPassword().length == 0) return;
		
		if (textPort.getText().equals("")) return;
		int portt = 21 ;
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		if(!  textPort.getText().equals("21"))
		{
			portt = Integer.parseInt(textPort.getText());
		}
	
		SIFRE_DONDUR sdon = new SIFRE_DONDUR();
		String response =sdon.sDONDUR(textSifre);
		
		boolean result =   bckp.DoesFtpExist(textHost.getText() ,portt,textKull.getText(), response);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		if(result)
		{
		OBS_BACKUP.mesaj_goster(5000,Notifications.Type.INFO, "Baglanti Gerceklesti  ");
		}
		else {
			OBS_BACKUP.mesaj_goster(5000,Notifications.Type.WARNING, "Baglanti Hata ");
		}
	}
	private void FtpKontrol() throws SocketException, IOException 
	{
		if (chckbxYerel.isSelected()) return;
		if (textHost.getText().equals("")) return;
		if (textKull.getText().equals("")) return;
		if (textSifre.getPassword().length == 0) return;
		if (textFtpSurucu.getText().equals("")) return;
		if (textPort.getText().equals("")) return;
		int portt = 21 ;
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		if(!  textPort.getText().equals("21"))
		{
			portt = Integer.parseInt(textPort.getText());
		}
		SIFRE_DONDUR sdon = new SIFRE_DONDUR();
		String response =sdon.sDONDUR(textSifre);
		
		boolean result =   bckp.DoesFtpDirectoryExist(textHost.getText() ,textFtpSurucu.getText(),portt,textKull.getText(), response);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		if(result)
		{
		OBS_BACKUP.mesaj_goster(5000,Notifications.Type.INFO, "Baglanti Gerceklesti  ");
		}
		else {
			OBS_BACKUP.mesaj_goster(5000,Notifications.Type.WARNING, "Baglanti Hata ");
		}
	}
}

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
		lblNewLabel.setBounds(26, 38, 71, 14);
		panel.add(lblNewLabel);
		
		textMSServer = new Obs_TextFIeld(50,"");
		JTextFieldRegularPopupMenu.addTo(textMSServer);
		textMSServer.setBounds(107, 35, 216, 20);
		panel.add(textMSServer);
		textMSServer.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Baglanti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(26, 94, 339, 104);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Kullanici");
		lblNewLabel_1.setBounds(10, 30, 71, 14);
		panel_2.add(lblNewLabel_1);
		
		textMSkull = new Obs_TextFIeld(50,"");
		JTextFieldRegularPopupMenu.addTo(textMSkull);
		textMSkull.setBounds(91, 27, 162, 20);
		panel_2.add(textMSkull);
		textMSkull.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Sifre");
		lblNewLabel_2.setBounds(10, 66, 48, 14);
		panel_2.add(lblNewLabel_2);
		
		textMSsifre = new JPasswordField();
		//textMSsifre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Sifre");
		textMSsifre.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		JTextFieldRegularPopupMenu.addTo(textMSsifre);
		textMSsifre.setBounds(91, 63, 162, 20);
		panel_2.add(textMSsifre);
		
		JButton btnMSTest = new JButton("Baglanti Test");
		btnMSTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textMSServer.getText().equals("")) return;
				if(textMSkull.getText().equals("")) return;
				if(textMSServer.getText().equals("")) return;
				SIFRE_DONDUR sdon = new SIFRE_DONDUR();
				String response =sdon.sDONDUR(textMSsifre);
				BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
			try {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Boolean result =	bckp.MsSql_server_test( textMSServer.getText() ,textMSkull.getText(),response,textMSPort.getText());
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if(result)
			{
				OBS_BACKUP.mesaj_goster(5000,Notifications.Type.INFO, "Baglanti Saglandi");
			}
			else {
				OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, "Baglanti Saglanmadi");
			}
			} catch (Exception e1) {
			
				e1.printStackTrace();
			}

			}
		});
		btnMSTest.setBounds(10, 517, 129, 23);
		panel.add(btnMSTest);
		
		JButton btnMSkaydet = new JButton("Kaydet");
		btnMSkaydet.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					if(textMSServer.getText().equals("")) return ;
					if(textMSkull.getText().equals("")) return ;
					if(textMSsifre.getText().equals("")) return ;
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					OBS_BACKUP.MS_Server_Kayit();
					OBS_BACKUP.tabbedPane_1.setSelectedIndex(0);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
				
					OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, e1.getMessage());
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
		

		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBorder(new TitledBorder(null, "Baglanti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2_2.setBounds(26, 70, 339, 104);
		panel_1.add(panel_2_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Kullanici");
		lblNewLabel_1_2.setBounds(10, 30, 71, 14);
		panel_2_2.add(lblNewLabel_1_2);
		
		textMykull = new Obs_TextFIeld(50,"");
		JTextFieldRegularPopupMenu.addTo(textMykull);
		textMykull.setColumns(10);
		textMykull.setBounds(91, 27, 209, 20);
		panel_2_2.add(textMykull);
		
		JLabel lblNewLabel_2_2 = new JLabel("Sifre");
		lblNewLabel_2_2.setBounds(10, 66, 48, 14);
		panel_2_2.add(lblNewLabel_2_2);
		
		textMySifre = new JPasswordField();
		//textMySifre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Sifre");
		textMySifre.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		JTextFieldRegularPopupMenu.addTo(textMySifre);
		textMySifre.setBounds(91, 63, 209, 20);
		panel_2_2.add(textMySifre);
		
		JButton btnMyTest = new JButton("Baglanti Test");
		btnMyTest.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(textMYPort.getText().equals("")) return;
				if(textMykull.getText().equals("")) return;
				if(textMySifre.getText().equals("")) return;
				SIFRE_DONDUR sdon = new SIFRE_DONDUR();
				String response =sdon.sDONDUR(textMySifre);
				BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
			try {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Boolean result =	bckp.MySql_server_test( textMykull.getText(),response,textMYPort.getText());
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			if(result)
			{
				OBS_BACKUP.mesaj_goster(5000,Notifications.Type.INFO, "Baglanti Saglandi");
			}
			else {
				OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, "Baglanti Saglanmadi");
			}
			} catch (Exception e1) {
			
				e1.printStackTrace();
			}

			}
		});
		btnMyTest.setBounds(10, 517, 129, 23);
		panel_1.add(btnMyTest);
		
		JButton btnMyKaydet = new JButton("Kaydet");
		btnMyKaydet.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					if(textMYPort.getText().equals("")) return ;
					if(textMykull.getText().equals("")) return ;
					if(textMySifre.getText().equals("")) return ;
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					OBS_BACKUP.MY_Server_Kayit();
					OBS_BACKUP.tabbedPane_1.setSelectedIndex(0);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
				
					OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, e1.getMessage());
				}
			}
		});
		btnMyKaydet.setBounds(624, 517, 100, 23);
		panel_1.add(btnMyKaydet);
		
		JLabel lblNewLabel_4 = new JLabel("Port");
		lblNewLabel_4.setBounds(26, 38, 48, 14);
		panel_1.add(lblNewLabel_4);
		
		textMYPort = new Obs_TextFIeld(10,"");
		JTextFieldRegularPopupMenu.addTo(textMYPort);
		textMYPort.setBounds(120, 38, 73, 20);
		panel_1.add(textMYPort);
		textMYPort.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("My Sql Dump");
		lblNewLabel_5.setBounds(26, 189, 96, 14);
		panel_1.add(lblNewLabel_5);
		
		textMyDump = new Obs_TextFIeld(200,"");
		JTextFieldRegularPopupMenu.addTo(textMyDump);
		textMyDump.setBounds(120, 183, 450, 20);
		panel_1.add(textMyDump);
		textMyDump.setColumns(10);
		
		JButton btnDumpSec = new JButton("Sec");
		btnDumpSec.addActionListener(new ActionListener() {
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
					textMyDump.setText(chooser.getSelectedFile().toString());
				}
				else {
					// System.out.println("No Selection ");
				}
			}
		});
		btnDumpSec.setBounds(470, 214, 100, 23);
		panel_1.add(btnDumpSec);

	}
}

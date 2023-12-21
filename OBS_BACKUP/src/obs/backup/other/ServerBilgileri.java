package obs.backup.other;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import OBS_C_2025.BACKUP_GLOBAL;
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
	public static JTextField textMSServer;
	public static JTextField textMSkull;
	public static JPasswordField textMSsifre;
	public static JTextField textField_3;
	public static JPasswordField passwordField_1;
	public static JTextField textMykull;
	public static JPasswordField textMySifre;
	public static JTextField textMYPort;
	public static JTextField textMyDump;
	public static JTextField textMSPort;

	/**
	 * Create the panel.
	 */
	public ServerBilgileri() {
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("MS Sql", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Instance");
		lblNewLabel.setBounds(26, 38, 71, 14);
		panel.add(lblNewLabel);
		
		textMSServer = new JTextField();
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
		
		textMSkull = new JTextField();
		textMSkull.setBounds(91, 27, 162, 20);
		panel_2.add(textMSkull);
		textMSkull.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Sifre");
		lblNewLabel_2.setBounds(10, 66, 48, 14);
		panel_2.add(lblNewLabel_2);
		
		textMSsifre = new JPasswordField();
		textMSsifre.setBounds(91, 63, 162, 20);
		panel_2.add(textMSsifre);
		
		JButton btnMSTest = new JButton("Baglanti Test");
		btnMSTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		btnMSTest.setBounds(10, 400, 129, 23);
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
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
				
					OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, e1.getMessage());
				}
			}
		});
		btnMSkaydet.setBounds(330, 400, 89, 23);
		panel.add(btnMSkaydet);
		
		JLabel lblNewLabel_4_1 = new JLabel("Port");
		lblNewLabel_4_1.setBounds(358, 38, 48, 14);
		panel.add(lblNewLabel_4_1);
		
		textMSPort = new JTextField();
		textMSPort.setColumns(10);
		textMSPort.setBounds(415, 32, 61, 20);
		panel.add(textMSPort);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("MY Sql", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(204, 16, 1, 1);
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(null, "Baglanti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_2_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Kullanici");
		lblNewLabel_1_1.setBounds(10, 30, 71, 14);
		panel_2_1.add(lblNewLabel_1_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(91, 27, 162, 20);
		panel_2_1.add(textField_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("Sifre");
		lblNewLabel_2_1.setBounds(10, 66, 48, 14);
		panel_2_1.add(lblNewLabel_2_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(91, 63, 162, 20);
		panel_2_1.add(passwordField_1);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBorder(new TitledBorder(null, "Baglanti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2_2.setBounds(26, 70, 339, 104);
		panel_1.add(panel_2_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Kullanici");
		lblNewLabel_1_2.setBounds(10, 30, 71, 14);
		panel_2_2.add(lblNewLabel_1_2);
		
		textMykull = new JTextField();
		textMykull.setColumns(10);
		textMykull.setBounds(91, 27, 162, 20);
		panel_2_2.add(textMykull);
		
		JLabel lblNewLabel_2_2 = new JLabel("Sifre");
		lblNewLabel_2_2.setBounds(10, 66, 48, 14);
		panel_2_2.add(lblNewLabel_2_2);
		
		textMySifre = new JPasswordField();
		textMySifre.setBounds(91, 63, 162, 20);
		panel_2_2.add(textMySifre);
		
		JButton btnMyTest = new JButton("Baglanti Test");
		btnMyTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnMyTest.setBounds(10, 400, 129, 23);
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
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
				
					OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, e1.getMessage());
				}
			}
		});
		btnMyKaydet.setBounds(330, 400, 89, 23);
		panel_1.add(btnMyKaydet);
		
		JLabel lblNewLabel_4 = new JLabel("Port");
		lblNewLabel_4.setBounds(26, 38, 48, 14);
		panel_1.add(lblNewLabel_4);
		
		textMYPort = new JTextField();
		textMYPort.setBounds(100, 38, 61, 20);
		panel_1.add(textMYPort);
		textMYPort.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("My Sql Dump");
		lblNewLabel_5.setBounds(26, 189, 96, 14);
		panel_1.add(lblNewLabel_5);
		
		textMyDump = new JTextField();
		textMyDump.setBounds(120, 183, 299, 20);
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
		btnDumpSec.setBounds(447, 181, 89, 23);
		panel_1.add(btnDumpSec);

	}
}

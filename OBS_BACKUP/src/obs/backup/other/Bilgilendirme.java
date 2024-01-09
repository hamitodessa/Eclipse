package obs.backup.other;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatClientProperties;

import OBS_C_2025.JTextFieldRegularPopupMenu;
import OBS_C_2025.Obs_TextFIeld;
import obs.backup.main.OBS_BACKUP;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Bilgilendirme extends JPanel {

	private static final long serialVersionUID = 1L;
	public Obs_TextFIeld textGonIsim;
	public Obs_TextFIeld textGonHesap;
	public Obs_TextFIeld textAlici;
	public Obs_TextFIeld textKonu;
	public Obs_TextFIeld textSmtp;
	public Obs_TextFIeld textPort;
	public Obs_TextFIeld textKull;
	public JPasswordField textSifre;
	
	public JCheckBox chckbxIslem;
	public JCheckBox chckbxHata;
	public JCheckBox chckbxAktifPasif;
	public JCheckBox chckbxSSL ;
	public JCheckBox chckbxTSL ;

	/**
	 * Create the panel.
	 */
	public Bilgilendirme() {
		
		setLayout(null);
		setName("bilgilendirmePanel");
		JLabel lblNewLabel = new JLabel("Durum");
		lblNewLabel.setBounds(37, 28, 48, 14);
		add(lblNewLabel);
		
		chckbxAktifPasif = new JCheckBox("Aktif / Pasif");
		chckbxAktifPasif.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxAktifPasif.setBounds(172, 24, 157, 23);
		add(chckbxAktifPasif);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Gonderme Durumu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(37, 70, 580, 71);
		add(panel);
		panel.setLayout(null);
		
		chckbxIslem = new JCheckBox("Islem Gerceklestiginde");
		chckbxIslem.setSelected(true);
		chckbxIslem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxIslem.isSelected())
				{
					chckbxHata.setSelected(false);
				}
			}
		});
		chckbxIslem.setBounds(135, 28, 184, 23);
		panel.add(chckbxIslem);
		
		chckbxHata = new JCheckBox("Hata Durumunda");
		chckbxHata.setBounds(325, 28, 153, 23);
		chckbxHata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxHata.isSelected())
				{
					chckbxIslem.setSelected(false);
			
				}
		}
		});
		panel.add(chckbxHata);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Mail Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(37, 153, 580, 125);
		add(panel_1);
		
		textGonIsim = new Obs_TextFIeld(50,"Gonderen Isim");
		JTextFieldRegularPopupMenu.addTo(textGonIsim);
		textGonIsim.setBounds(135, 23, 329, 20);
		panel_1.add(textGonIsim);
		textGonIsim.setColumns(10);
		
		textGonHesap = new Obs_TextFIeld(50,"Gonderen Hesap");
		JTextFieldRegularPopupMenu.addTo(textGonHesap);
		textGonHesap.setColumns(10);
		textGonHesap.setBounds(135, 46, 329, 20);
		panel_1.add(textGonHesap);
		
		textAlici = new Obs_TextFIeld(50,"Alici Hesap");
		JTextFieldRegularPopupMenu.addTo(textAlici);
		textAlici.setColumns(10);
		textAlici.setBounds(135, 69, 329, 20);
		panel_1.add(textAlici);
		
		textKonu = new Obs_TextFIeld(50,"Konu");
		JTextFieldRegularPopupMenu.addTo(textKonu);
		textKonu.setColumns(10);
		textKonu.setBounds(135, 92, 329, 20);
		panel_1.add(textKonu);
		
		JLabel lblNewLabel_1 = new JLabel("Gonderen Isim");
		lblNewLabel_1.setBounds(21, 26, 88, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gonderen Hesap");
		lblNewLabel_2.setBounds(21, 49, 98, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Alici");
		lblNewLabel_3.setBounds(21, 72, 48, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Konu");
		lblNewLabel_4.setBounds(21, 95, 48, 14);
		panel_1.add(lblNewLabel_4);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "Server Ayarlari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBounds(37, 285, 580, 132);
		add(panel_1_1);
		
		textSmtp = new Obs_TextFIeld(30,"Smtp");
		JTextFieldRegularPopupMenu.addTo(textSmtp);
		textSmtp.setColumns(10);
		textSmtp.setBounds(135, 23, 216, 20);
		panel_1_1.add(textSmtp);
		
		textPort = new Obs_TextFIeld(3,"Port");
		JTextFieldRegularPopupMenu.addTo(textPort);
		textPort.setColumns(10);
		textPort.setBounds(404, 23, 60, 20);
		panel_1_1.add(textPort);
		
		textKull = new Obs_TextFIeld(50,"Kullanici");
		JTextFieldRegularPopupMenu.addTo(textKull);
		textKull.setColumns(10);
		textKull.setBounds(135, 46, 329, 20);
		panel_1_1.add(textKull);
		
		textSifre = new JPasswordField();
		textSifre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Sifre");
		textSifre.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		JTextFieldRegularPopupMenu.addTo(textSifre);
		textSifre.setColumns(10);
		textSifre.setBounds(135, 69, 329, 20);
		panel_1_1.add(textSifre);
		
		chckbxSSL = new JCheckBox("SSL");
		chckbxSSL.setBounds(135, 100, 99, 23);
		panel_1_1.add(chckbxSSL);
		
		chckbxTSL = new JCheckBox("TSL");
		chckbxTSL.setBounds(252, 100, 99, 23);
		panel_1_1.add(chckbxTSL);
		
		JButton btnDenemeMail = new JButton("Deneme Maili");
		btnDenemeMail.setBounds(364, 100, 100, 23);
		panel_1_1.add(btnDenemeMail);
		
		JLabel lblNewLabel_5 = new JLabel("SMTP Server");
		lblNewLabel_5.setBounds(22, 26, 87, 14);
		panel_1_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Port");
		lblNewLabel_6.setBounds(357, 26, 49, 14);
		panel_1_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Kullanici");
		lblNewLabel_7.setBounds(22, 49, 87, 14);
		panel_1_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Sifre");
		lblNewLabel_8.setBounds(22, 72, 87, 14);
		panel_1_1.add(lblNewLabel_8);
		
		JButton btnNewButton_9 = new JButton("Kaydet");
		btnNewButton_9.setBounds(624, 581, 100, 23);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					OBS_BACKUP.BilgilendirmeKaydet();
					OBS_BACKUP.tabbedPane_1.setSelectedIndex(0);
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
				}
			}
		});
		 add(btnNewButton_9);
	}
	
}

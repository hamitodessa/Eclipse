package obs.backup.other;

import javax.swing.JPanel;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.JTextFieldRegularPopupMenu;
import OBS_C_2025.Obs_TextFIeld;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

public class EmirKopyala extends JPanel {
	 BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private static final long serialVersionUID = 1L;
	public Obs_TextFIeld textField;

	public static JLabel lblNewLabel;
	public JButton btnNewButton_9;
	public static JLabel lblEmirIsmi;
	/**
	 * Create the panel.
	 */
	public EmirKopyala() {
		setLayout(null);
		
		lblNewLabel = new JLabel("Yeni Emir Ismi");
		lblNewLabel.setBounds(56, 129, 107, 14);
		add(lblNewLabel);
		
		textField = new Obs_TextFIeld(30,"");
		JTextFieldRegularPopupMenu.addTo(textField);
		textField.setBounds(185, 126, 300, 20);
		add(textField);
		textField.setColumns(10);
		
		btnNewButton_9 = new JButton("Kaydet");
		btnNewButton_9.setBounds(624, 550, 100, 23);
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(OBS_BACKUP.emirAnaGirisPanel.txtEmir.getText().equals(""))
					{
						OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING, dilSecenek.dil(OBS_BACKUP.dILS, "Kopyalanacak Emir ismi Bos") );
						return;
					}
					if(! bckp. emir_kontrol(OBS_BACKUP.emirAnaGirisPanel.txtEmir.getText()))
					{
						OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING, OBS_BACKUP.emirAnaGirisPanel.txtEmir.getText() + System.lineSeparator() +
								dilSecenek.dil(OBS_BACKUP.dILS, "Kayitli Emir Yok") );
						return;
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					kopyala();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					OBS_BACKUP.mesajGoster(5000,Notifications.Type.ERROR, e1.getMessage());
				}
			}
		});
		 add(btnNewButton_9);
		 
		 lblEmirIsmi = new JLabel("");
		 lblEmirIsmi.setFont(new Font("Tahoma", Font.BOLD, 12));
		 lblEmirIsmi.setBounds(185, 71, 300, 14);
		 add(lblEmirIsmi);
	}
	private void kopyala() throws ClassNotFoundException, SQLException
	{
		if (OBS_BACKUP.emirAnaGirisPanel.txtEmir.getText().equals("")) return;
		try
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			bckp.kopyala(OBS_BACKUP.emirAnaGirisPanel.txtEmir.getText(), textField.getText());
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			OBS_BACKUP.mesajGoster(5000,Notifications.Type.INFO,dilSecenek.dil(OBS_BACKUP.dILS, "Emir Kopyalama Gerceklestirildi")  );
			OBS_BACKUP.btnGorevler.doClick();
		}
		catch (Exception ex)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			bckp.log_kayit(OBS_BACKUP.emirAnaGirisPanel.txtEmir.getText(), new Date(), ex.getMessage());
			OBS_BACKUP.mesajGoster(5000,Notifications.Type.ERROR, ex.getMessage());
		}
	}
}

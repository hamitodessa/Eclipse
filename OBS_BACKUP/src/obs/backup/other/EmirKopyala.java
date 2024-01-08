package obs.backup.other;

import javax.swing.JPanel;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.JTextFieldRegularPopupMenu;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

public class EmirKopyala extends JPanel {
	 BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private static final long serialVersionUID = 1L;
	public JTextField textField;

	/**
	 * Create the panel.
	 */
	public EmirKopyala() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Yeni Emir Ismi");
		lblNewLabel.setBounds(56, 86, 107, 14);
		add(lblNewLabel);
		
		textField = new JTextField();
		JTextFieldRegularPopupMenu.addTo(textField);
		textField.setBounds(185, 83, 250, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_9 = new JButton("Kaydet");
		btnNewButton_9.setBounds(624, 581, 100, 23);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(OBS_BACKUP.emirAnaGirisPanel.txtEmir.getText().equals(""))
					{
						OBS_BACKUP.mesaj_goster(5000,Notifications.Type.WARNING, "Kopyalanacak Emir ismi Bos.....");
						return;
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					kopyala();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
					OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, e1.getMessage());
				}
			}
		});
		 add(btnNewButton_9);
	}
	private void kopyala() throws ClassNotFoundException, SQLException
	{
		if (OBS_BACKUP.emirAnaGirisPanel.txtEmir.getText().equals("")) return;
		try
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			bckp.kopyala(OBS_BACKUP.emirAnaGirisPanel.txtEmir.getText(), textField.getText());
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			OBS_BACKUP.mesaj_goster(5000,Notifications.Type.INFO, "Emir Kopyalama Gerceklestirildi....");
			OBS_BACKUP.btnGorevler.doClick();
		}
		catch (Exception ex)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			bckp.log_kayit(OBS_BACKUP.emirAnaGirisPanel.txtEmir.getText(), new Date(), "Emir Kopyalama " + ex.getMessage());
			OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
		}
	}
}

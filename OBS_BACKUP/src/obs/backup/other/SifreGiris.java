package obs.backup.other;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.SIFRE_DONDUR;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.awt.Font;

public class SifreGiris extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPasswordField passwordField;
	BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	/**
	 * Create the panel.
	 */
	public SifreGiris() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sifre");
		lblNewLabel.setBounds(217, 285, 86, 20);
		add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordField.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorRemoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorMoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorAdded(AncestorEvent pEvent) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						passwordField.requestFocusInWindow();
					}
				});
			}
		});

		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
				{	
					SIFRE_DONDUR sDondur = new SIFRE_DONDUR();
					byte[] qaz;
					try {
						qaz = ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(sDondur.sDONDUR(passwordField));
					String response = Arrays.toString(qaz);
					String varmi =	bckp.backup_sifre_oku(response);
					if (varmi.equals(sDondur.sDONDUR(passwordField)) == true)
					{
						OBS_BACKUP.tabbedPane.setSelectedIndex(0);
						OBS_BACKUP.btnGorevler.setEnabled(true);
						OBS_BACKUP.btnYeni_Gorev.setEnabled(true);
						OBS_BACKUP. btnLoglama.setEnabled(true);
						OBS_BACKUP.btnKayitliEmirler.setEnabled(true);
						OBS_BACKUP. btnHepsiYukari.setEnabled(true);
						OBS_BACKUP.btnHepsiAsagi.setEnabled(true);
						OBS_BACKUP.btnYeniSifre.setEnabled(true);
					}
					else {
						OBS_BACKUP.mesaj_goster(5000,Notifications.Type.WARNING,"Sifre Yanlis");
					}
					} catch (Exception e1) {
						OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, e1.getMessage());
					}
				}
			}
		});
		passwordField.setBounds(329, 282, 248, 25);
		add(passwordField);
	}
}

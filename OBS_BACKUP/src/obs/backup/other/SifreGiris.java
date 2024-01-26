package obs.backup.other;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.formdev.flatlaf.FlatClientProperties;

import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.SIFRE_DONDUR;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;

public class SifreGiris extends JPanel {

	private static final long serialVersionUID = 1L;
	public JPasswordField passwordField;
	BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	public JLabel lblDefaultpwd;
	public JLabel lblNewLabel ;
	/**
	 * Create the panel.
	 */
	public SifreGiris() {
		setLayout(null);
		
		lblNewLabel = new JLabel("Sifre");
		lblNewLabel.setBounds(217, 285, 86, 20);
		add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordField.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
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
					try {
					String varmi =	bckp.backup_sifre_oku();
					if (varmi.equals(sDondur.sDONDUR(passwordField)) == true)
					{
						OBS_BACKUP.tabbedPane.setSelectedIndex(0);
						OBS_BACKUP.btnGorevler.setEnabled(true);
						OBS_BACKUP.btnYeni_Gorev.setEnabled(true);
						OBS_BACKUP.btnLoglama.setEnabled(true);
						OBS_BACKUP.btnKayitliEmirler.setEnabled(true);
						OBS_BACKUP.btnHepsiYukari.setEnabled(true);
						OBS_BACKUP.btnHepsiAsagi.setEnabled(true);
						OBS_BACKUP.btnYeniSifre.setEnabled(true);
						OBS_BACKUP.btnUploadAll.setEnabled(true);
						OBS_BACKUP.btnStartAll.setEnabled(true);
						OBS_BACKUP.btnStopAll.setEnabled(true);
						OBS_BACKUP.btnFileIndir.setEnabled(true);
						OBS_BACKUP.btnSifreEkrani.setVisible(true);
						OBS_BACKUP.btnHepsiAktiv.setEnabled(true);
						OBS_BACKUP.btnHepsiPasiv.setEnabled(true);
						OBS_BACKUP.btnHakkinda.setEnabled(true);
						OBS_BACKUP.btnAyarlar.setEnabled(true);
						OBS_BACKUP.btnHepsiYukari.doClick();
						
					}
					else {
						OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING,  dilSecenek.dil(OBS_BACKUP.dILS, "Sifre Yanlis"));
					}
					} catch (Exception e1) {
						OBS_BACKUP.mesajGoster(5000,Notifications.Type.ERROR, e1.getMessage());
					}
				}
			}
		});
		passwordField.setBounds(329, 282, 248, 25);
		add(passwordField);
		
		lblDefaultpwd = new JLabel("Varsayilan Sifre :       obs");
		//lblDefaultpwd.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDefaultpwd.setVisible(false);
		lblDefaultpwd.setBounds(329, 241, 248, 14);
		add(lblDefaultpwd);
		try {
			if(! GLOBAL.dos_kontrol(GLOBAL.SURUCU + GLOBAL.BACKUP_DOSYA)) return ;
			String varmi =	bckp.backup_sifre_oku();
			if(varmi.equals("obs")) 
				lblDefaultpwd.setVisible(true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

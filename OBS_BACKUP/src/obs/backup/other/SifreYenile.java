package obs.backup.other;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.SIFRE_DONDUR;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SifreYenile extends JPanel {

	private static final long serialVersionUID = 1L;
	public static JPasswordField txtsif;
	BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private static JLabel lblysif;
	private static JTextField txtyenisif;
	private JButton btnNewButton;
	/**
	 * Create the panel.
	 */
	public SifreYenile() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Gecerli Sifreniz");
		lblNewLabel.setBounds(217, 288, 102, 20);
		add(lblNewLabel);

		txtsif = new JPasswordField();
		txtsif.addAncestorListener(new AncestorListener() {
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
						txtsif.requestFocusInWindow();
					}
				});
			}
		});

		txtsif.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					SIFRE_DONDUR sDondur = new SIFRE_DONDUR();
					byte[] qaz;
					try {
						qaz = ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(sDondur.sDONDUR(txtsif));
						String response = Arrays.toString(qaz);
						String varmi =	bckp.backup_sifre_oku(response);
						if (varmi.equals(sDondur.sDONDUR(txtsif)) == true)
						{
							lblysif.setVisible(true);
							txtyenisif.setVisible(true);
							btnNewButton.setVisible(true);
						}
						else {
							OBS_BACKUP.mesaj_goster(5000,Notifications.Type.WARNING,"Sifre Yanlis");
							lblysif.setVisible(false);
							txtyenisif.setVisible(false);
							btnNewButton.setVisible(false);
						}
					} catch (Exception e1) {
						OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, e1.getMessage());
					}
				}
			}
		});
		txtsif.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtsif.setBounds(329, 282, 248, 25);
		add(txtsif);
		txtsif.setColumns(10);

		txtyenisif = new JTextField();
		txtyenisif.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtyenisif.setColumns(10);
		txtyenisif.setBounds(329,315, 248, 25);
		txtyenisif.setVisible(false);
		txtyenisif.setText("");
		add(txtyenisif);

		lblysif = new JLabel("Yeni Sifreniz");
		lblysif.setBounds(217,318, 86, 20);
		
		lblysif.setVisible(false);
		add(lblysif);

		btnNewButton = new JButton();
		btnNewButton.setVisible(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					String oku = new String(txtyenisif.getText());
					if (oku.equals(""))
					{
						txtsif.requestFocus();
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						return;
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					bckp.sifre_degis(txtyenisif.getText());
					txtyenisif.setText("");
					txtsif.setText("");
					txtyenisif.setVisible(false);
					lblysif.setVisible(false); 
					btnNewButton.setVisible(false);
					txtsif.requestFocus();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception ex)
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage() );
					txtsif.requestFocus();		
				}		
			}
		});
		btnNewButton.setBounds(600, 315, 25, 25);
		btnNewButton.setIcon(new ImageIcon(OBS_BACKUP.class.getResource("/obs/backup/icons/save.png")));
		add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Sifre Yenileme");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(329, 202, 248, 14);
		add(lblNewLabel_1);
	}
}

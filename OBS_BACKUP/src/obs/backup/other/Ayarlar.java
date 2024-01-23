package obs.backup.other;

import javax.swing.JPanel;


import OBS_C_2025.BACKUP_GLOBAL;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class Ayarlar extends JPanel {

	public JButton btnKaydet;
	public JLabel lblNewLabel;
	public JLabel lblNewLabel_1;
	public JComboBox<String> comboBox;
	public JComboBox<String> comboBox_1;
	static BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	final boolean showTabsHeader = false;
	public Ayarlar()
	{
		setLayout(null);

		lblNewLabel = new JLabel("Gorunum");
		lblNewLabel.setBounds(42, 87, 85, 14);
		add(lblNewLabel);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"FlatCarbonIJ", "FlatMonocaiIJ", "FlatMacDarkLaf", "FlatNordIJ", "FlatHighContrastIJ", "FlatMaterialPalenightIJ", "FlatMaterialDeepOceanIJ","FlatArcDarkIJ","FlatGradiantoNatureGreenIJ","FlatGrayIJ","FlatMaterial","FlatArcOrangeIJ","Java"}));
		comboBox.setBounds(137, 83, 254, 22);
		add(comboBox);

		lblNewLabel_1 = new JLabel("Dil");
		
		lblNewLabel_1.setBounds(42, 24, 85, 14);
		add(lblNewLabel_1);

		comboBox_1 = new JComboBox<String>();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setText(dilSecenek.dil(comboBox_1.getSelectedItem().toString(),"Tema"));
				lblNewLabel_1.setText(dilSecenek.dil(comboBox_1.getSelectedItem().toString(),"Dil"));
				btnKaydet.setText(dilSecenek.dil(comboBox_1.getSelectedItem().toString(),"Kaydet"));
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Turkce", "English"}));
		comboBox_1.setBounds(137, 20, 105, 22);
		add(comboBox_1);

		btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					bckp.ayar_sil();
					bckp.ayar_kayit(comboBox_1.getSelectedItem().toString(), comboBox.getSelectedItem().toString());
					OBS_BACKUP.dil();
					OBS_BACKUP.emirleriSTOPYAP();
					OBS_BACKUP.btnfont_tema.doClick();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnKaydet.setBounds(137, 471, 89, 23);
		add(btnKaydet);
	}
}

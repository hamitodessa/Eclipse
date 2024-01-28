package obs.backup.other;

import javax.swing.JPanel;


import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.SIFRE_DONDUR;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

import com.formdev.flatlaf.FlatClientProperties;

@SuppressWarnings({"serial","deprecation"})
public class Ayarlar extends JPanel {

	public JButton btnKaydet;
	public JLabel lblNewLabel;
	public JLabel lblNewLabel_1;
	public JLabel lblNewLabel_2 ;
	public JLabel lblNewLabel_3;
	public JComboBox<String> comboBox;
	public JComboBox<String> comboBox_1;
	public JCheckBox chckbxSifrele;
	public JCheckBox chckbxPrgSifre;
	static BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	final boolean showTabsHeader = false;
	public JPasswordField passwordText;
	public Ayarlar()
	{
		setLayout(null);

		lblNewLabel = new JLabel("Gorunum");
		lblNewLabel.setBounds(42, 87, 85, 14);
		add(lblNewLabel);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"FlatCarbonIJ", "FlatMonocaiIJ", "FlatMacDarkLaf", "FlatNordIJ", "FlatHighContrastIJ", "FlatMaterialPalenightIJ", "FlatMaterialDeepOceanIJ","FlatArcDarkIJ","FlatGradiantoNatureGreenIJ","FlatGrayIJ","FlatMaterial","FlatArcOrangeIJ","Java"}));
		comboBox.setBounds(160, 83, 254, 22);
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
				lblNewLabel_2.setText(dilSecenek.dil(comboBox_1.getSelectedItem().toString(),"ZIP Sifrele"));
				lblNewLabel_3.setText(dilSecenek.dil(comboBox_1.getSelectedItem().toString(),"Acilis Sifre Sor"));
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Turkce", "English"}));
		comboBox_1.setBounds(160, 20, 105, 22);
		add(comboBox_1);

		btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					if(chckbxSifrele.isSelected())
					{
						if(passwordText.getText().equals(""))
						{
							passwordText.requestFocus();
							return;
						}
					}
					else {
						passwordText.setText("");
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					bckp.ayar_sil();
					SIFRE_DONDUR sDondur = new SIFRE_DONDUR();
					bckp.ayar_kayit(comboBox_1.getSelectedItem().toString(), comboBox.getSelectedItem().toString(),chckbxSifrele.isSelected() ? 1:0,
							sDondur.sDONDUR(passwordText) , chckbxPrgSifre.isSelected() ? 1:0);
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
		
		chckbxSifrele = new JCheckBox("");
		chckbxSifrele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxSifrele.isSelected())
				{
					passwordText.setVisible(true);
				}
				else {
					passwordText.setVisible(false);
				}
			}
		});
		chckbxSifrele.setBounds(160, 161, 99, 23);
		add(chckbxSifrele);
		
		lblNewLabel_2 = new JLabel("ZIP Sifrele");
		lblNewLabel_2.setBounds(42, 165, 105, 14);
		add(lblNewLabel_2);
		
		passwordText = new JPasswordField();
		passwordText.setVisible(false);
		passwordText.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
		passwordText.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		
		passwordText.setBounds(160, 201, 189, 20);
		add(passwordText);
		
		lblNewLabel_3 = new JLabel("Acilis Sifre Sor");
		lblNewLabel_3.setBounds(42, 310, 123, 14);
		add(lblNewLabel_3);
		
		chckbxPrgSifre = new JCheckBox("");
		chckbxPrgSifre.setBounds(160, 306, 99, 23);
		add(chckbxPrgSifre);
	}
}

//			SIFRE_DONDUR sDondur = new SIFRE_DONDUR();
//System.out.println(sDondur.sDONDUR(ayarlarPanel.passwordText));

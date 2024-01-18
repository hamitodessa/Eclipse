package obs.backup.other;

import javax.swing.JPanel;

import obs.backup.ayarlar.dilSecenek;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Ayarlar extends JPanel {

	public Ayarlar()
	{
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gorunum");
		lblNewLabel.setBounds(42, 87, 85, 14);
		add(lblNewLabel);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"FlatCarbonIJ", "FlatMonocaiIJ", "FlatMacDarkLaf", "FlatNordIJ", "FlatHighContrastIJ", "FlatMaterialPalenightIJ", "FlatMaterialDeepOceanIJ"}));
		comboBox.setBounds(137, 83, 180, 22);
		add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Dil");
		lblNewLabel_1.setBounds(42, 24, 85, 14);
		add(lblNewLabel_1);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_1.getSelectedIndex() == 1)
				{
				lblNewLabel.setText(dilSecenek.dil("Tema"));
				lblNewLabel_1.setText(dilSecenek.dil("Dil"));
				}
				else {
					lblNewLabel.setText("Gorunum");
					lblNewLabel_1.setText("Dil");
				}
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Turkce", "English"}));
		comboBox_1.setBounds(137, 20, 105, 22);
		add(comboBox_1);
	}
}

package obs.backup.other;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Bilgilendirme extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Create the panel.
	 */
	public Bilgilendirme() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Durum");
		lblNewLabel.setBounds(37, 28, 48, 14);
		add(lblNewLabel);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Aktif / Pasif");
		chckbxNewCheckBox.setBounds(141, 24, 99, 23);
		add(chckbxNewCheckBox);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Gonderme Durumu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(37, 71, 504, 71);
		add(panel);
		panel.setLayout(null);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Islem Gerceklestiginde");
		chckbxNewCheckBox_1.setBounds(63, 28, 184, 23);
		panel.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Hata Durumunda");
		chckbxNewCheckBox_2.setBounds(299, 28, 153, 23);
		panel.add(chckbxNewCheckBox_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Mail Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Mail Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(37, 153, 504, 125);
		add(panel_1);
		
		textField = new JTextField();
		textField.setBounds(119, 23, 264, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(119, 46, 264, 20);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(119, 71, 264, 20);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(119, 94, 264, 20);
		panel_1.add(textField_3);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(null, "Server Ayarlari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Server Ayarlari", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1.setBounds(37, 285, 504, 132);
		add(panel_1_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(119, 23, 173, 20);
		panel_1_1.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(341, 23, 69, 20);
		panel_1_1.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(119, 54, 264, 20);
		panel_1_1.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(119, 77, 264, 20);
		panel_1_1.add(textField_7);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("SSL");
		chckbxNewCheckBox_3.setBounds(119, 100, 99, 23);
		panel_1_1.add(chckbxNewCheckBox_3);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("TSL");
		chckbxNewCheckBox_4.setBounds(252, 100, 99, 23);
		panel_1_1.add(chckbxNewCheckBox_4);
		
		JButton btnNewButton = new JButton("Deneme Maili");
		btnNewButton.setBounds(393, 100, 101, 23);
		panel_1_1.add(btnNewButton);
		
		JButton btnNewButton_9 = new JButton("Kaydet");
		btnNewButton_9.setBounds(624, 581, 89, 23);
		 add(btnNewButton_9);


	}
}

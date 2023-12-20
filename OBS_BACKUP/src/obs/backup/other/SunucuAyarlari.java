package obs.backup.other;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class SunucuAyarlari extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	/**
	 * Create the panel.
	 */
	public SunucuAyarlari() {

 setLayout(null);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("FTP");
		chckbxNewCheckBox_2.setBounds(67, 22, 99, 23);
		 add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Yerel Surucu");
		chckbxNewCheckBox_3.setBounds(215, 22, 150, 23);
		 add(chckbxNewCheckBox_3);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(null, "FTP Ayarlari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_12.setBounds(10, 73, 500, 100);
		 add(panel_12);
		panel_12.setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setBounds(124, 22, 300, 20);
		panel_12.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(124, 46, 96, 20);
		panel_12.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(124, 69, 96, 20);
		panel_12.add(textField_4);
		textField_4.setColumns(10);
		
		JPanel panel_12_1 = new JPanel();
		panel_12_1.setLayout(null);
		panel_12_1.setBorder(new TitledBorder(null, "Diger Ayarlar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		//, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_12_1.setBounds(10, 174, 627, 100);
		 add(panel_12_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(124, 22, 300, 20);
		panel_12_1.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(124, 46, 96, 20);
		panel_12_1.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(124, 69, 96, 20);
		panel_12_1.add(textField_7);
		
		JButton btnNewButton_6 = new JButton("Surucu Kontrol");
		btnNewButton_6.setBounds(502, 68, 115, 23);
		panel_12_1.add(btnNewButton_6);
		
		JPanel panel_12_1_1 = new JPanel();
		panel_12_1_1.setLayout(null);
		panel_12_1_1.setBorder(new TitledBorder(null, "Yerel Surucu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		panel_12_1_1.setBounds(10, 276, 627, 75);
		 add(panel_12_1_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(124, 22, 300, 20);
		panel_12_1_1.add(textField_8);
		
		JButton btnNewButton_7 = new JButton("Surucu Sec");
		btnNewButton_7.setBounds(502, 41, 115, 23);
		panel_12_1_1.add(btnNewButton_7);
		
		JPanel panel_12_1_1_1 = new JPanel();
		panel_12_1_1_1.setLayout(null);
		panel_12_1_1_1.setBorder(new TitledBorder(null, "Eski Yedek", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		panel_12_1_1_1.setBounds(10, 367, 627, 75);
		 add(panel_12_1_1_1);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(124, 22, 300, 20);
		panel_12_1_1_1.add(textField_9);
		
		JButton btnNewButton_8 = new JButton("Cikis");
		btnNewButton_8.setBounds(10, 581, 89, 23);
		 add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Kaydet");
		btnNewButton_9.setBounds(624, 581, 89, 23);
		 add(btnNewButton_9);
	}

}

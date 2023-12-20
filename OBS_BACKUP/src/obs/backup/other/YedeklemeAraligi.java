package obs.backup.other;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.JSpinner;

public class YedeklemeAraligi extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField textHerDakka;
	
	public JCheckBox chckbxPtesi;
	public JCheckBox chckbxSali ;
	public JCheckBox chckbxCarsamba ;
	public JCheckBox chckbxPersembe ;
	public JCheckBox chckbxCuma ;
	public JCheckBox chckbxCumartesi ;
	public JCheckBox chckbxPazar ;
	/**
	 * Create the panel.
	 */
	public YedeklemeAraligi() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Her");
		lblNewLabel.setBounds(43, 35, 48, 14);
		add(lblNewLabel);
		
		textHerDakka = new JTextField();
		textHerDakka.setBounds(118, 32, 48, 20);
		add(textHerDakka);
		textHerDakka.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("dakkada bir");
		lblNewLabel_1.setBounds(205, 35, 81, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gunler");
		lblNewLabel_2.setBounds(43, 100, 48, 14);
		add(lblNewLabel_2);
		
		chckbxPtesi = new JCheckBox("Pazartesi");
		chckbxPtesi.setBounds(118, 96, 99, 23);
		add(chckbxPtesi);
		
		chckbxSali = new JCheckBox("Sali");
		chckbxSali.setBounds(118, 124, 99, 23);
		add(chckbxSali);
		
		chckbxCarsamba = new JCheckBox("Carsamba");
		chckbxCarsamba.setBounds(118, 150, 99, 23);
		add(chckbxCarsamba);
		
		chckbxPersembe = new JCheckBox("Persembe");
		chckbxPersembe.setBounds(118, 178, 99, 23);
		add(chckbxPersembe);
		
		chckbxCuma = new JCheckBox("Cuma");
		chckbxCuma.setBounds(118, 204, 99, 23);
		add(chckbxCuma);
		
		chckbxCumartesi = new JCheckBox("Cumartesi");
		chckbxCumartesi.setBounds(118, 232, 99, 23);
		add(chckbxCumartesi);
		
		chckbxPazar = new JCheckBox("Pazar");
		chckbxPazar.setBounds(118, 258, 99, 23);
		add(chckbxPazar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Yedekleme Araligi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(43, 322, 380, 112);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Baslangic");
		lblNewLabel_3.setBounds(30, 34, 71, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Bitis");
		lblNewLabel_3_1.setBounds(30, 67, 71, 14);
		panel.add(lblNewLabel_3_1);
		
		

		JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
		timeSpinner.setBounds(111, 29, 75, 25);
		panel.add(timeSpinner);
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
		timeSpinner.setEditor(timeEditor);
		Date qweDate = new Date();
		qweDate.setHours(00);
		qweDate.setMinutes(00);
		timeSpinner.setValue(qweDate);
		
		JSpinner timeSpinnerBitis = new JSpinner( new SpinnerDateModel() );
		timeSpinnerBitis.setBounds(111, 62, 75, 25);
		panel.add(timeSpinnerBitis);
		JSpinner.DateEditor timeEditorBitis = new JSpinner.DateEditor(timeSpinnerBitis, "HH:mm");
		timeSpinnerBitis.setEditor(timeEditorBitis);
		
		qweDate = new Date();
		qweDate.setHours(00);
		qweDate.setMinutes(00);
		timeSpinnerBitis.setValue(qweDate);
			
		JButton btnNewButton_9 = new JButton("Kaydet");
		btnNewButton_9.setBounds(624, 581, 89, 23);
		 add(btnNewButton_9);

	}
}

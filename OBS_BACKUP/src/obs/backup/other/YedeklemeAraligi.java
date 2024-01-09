package obs.backup.other;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpinnerDateModel;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;

import OBS_C_2025.JTextFieldRegularPopupMenu;
import OBS_C_2025.Obs_TextFIeld;
import obs.backup.main.OBS_BACKUP;

import javax.swing.JSpinner;

public class YedeklemeAraligi extends JPanel {

	private static final long serialVersionUID = 1L;
	public Obs_TextFIeld textHerDakka;
	
	public JCheckBox chckbxPtesi;
	public JCheckBox chckbxSali ;
	public JCheckBox chckbxCarsamba ;
	public JCheckBox chckbxPersembe ;
	public JCheckBox chckbxCuma ;
	public JCheckBox chckbxCumartesi ;
	public JCheckBox chckbxPazar ;
	
	public JSpinner timeBaslangic;
	public JSpinner timeBitis;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("deprecation")
	public YedeklemeAraligi() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Her");
		lblNewLabel.setBounds(43, 35, 48, 14);
		add(lblNewLabel);
		
		textHerDakka = new Obs_TextFIeld(2, "Dakka");
		JTextFieldRegularPopupMenu.addTo(textHerDakka);
		textHerDakka.setBounds(118, 32, 60, 20);
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
		
		

		timeBaslangic = new JSpinner( new SpinnerDateModel() );
		timeBaslangic.setBounds(111, 29, 75, 25);
		panel.add(timeBaslangic);
		JSpinner.DateEditor de_timeBaslangic = new JSpinner.DateEditor(timeBaslangic, "HH:mm");
		timeBaslangic.setEditor(de_timeBaslangic);
		Date qweDate = new Date();
		qweDate.setHours(00);
		qweDate.setMinutes(00);
		timeBaslangic.setValue(qweDate);
		
		timeBitis = new JSpinner( new SpinnerDateModel() );
		timeBitis.setBounds(111, 62, 75, 25);
		panel.add(timeBitis);
		JSpinner.DateEditor de_timeBitis = new JSpinner.DateEditor(timeBitis, "HH:mm");
		timeBitis.setEditor(de_timeBitis);
		
		qweDate = new Date();
		qweDate.setHours(00);
		qweDate.setMinutes(00);
		timeBitis.setValue(qweDate);
			
		JButton btnNewButton_9 = new JButton("Kaydet");
		btnNewButton_9.setBounds(624, 581, 100, 23);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				try {
					OBS_BACKUP.	yedeklemeKaydet();
					OBS_BACKUP.tabbedPane_1.setSelectedIndex(0);
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
		});
		 add(btnNewButton_9);

	}
	
}

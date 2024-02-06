package obs.backup.other;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;

public class UploadPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JLabel lblEmirAdi;
	public JLabel lblDosAdet;
	public JLabel lblSurucu;
	public static JLabel lblHiz ;
	public static JLabel lblDosyaAdi;
	public JLabel lblAciklamaJLabel;
	public JLabel lblAciklama;
	
	public static JProgressBar RPB1;
	public static JProgressBar RPB2;
	/**
	 * Create the panel.
	 */
	public UploadPanel() {

		setLayout(new BorderLayout(0, 0));
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0,40));
		splitPane.setRightComponent(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		RPB1 = new JProgressBar();
		RPB1.setBorder(new LineBorder(new Color(0, 191, 255)));
		RPB1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		RPB1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(RPB1);
		
		RPB2 = new JProgressBar();
		RPB2.setBorder(new LineBorder(new Color(0, 191, 255)));
		RPB2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		RPB2.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(RPB2);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS,"Dosya Adet:"));
		lblNewLabel.setBounds(690, 11, 69, 14);
		panel_1.add(lblNewLabel);
		
		lblDosAdet = new JLabel("0");
		lblDosAdet.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDosAdet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDosAdet.setBounds(786, 11, 48, 14);
		panel_1.add(lblDosAdet);
		
		JLabel lblNewLabel_2 = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS,"Emir Ismi"));
		lblNewLabel_2.setBounds(10, 11, 58, 14);
		panel_1.add(lblNewLabel_2);
		
		lblEmirAdi = new JLabel("");
		lblEmirAdi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmirAdi.setBounds(78, 11, 220, 14);
		panel_1.add(lblEmirAdi);
		
		JLabel lblNewLabel_4 = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS,"Hiz"));
		lblNewLabel_4.setBounds(690, 31, 40, 14);
		panel_1.add(lblNewLabel_4);
		
		lblHiz = new JLabel("0.00 kb/sec");
		lblHiz.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHiz.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHiz.setBounds(729, 31, 105, 14);
		panel_1.add(lblHiz);
		
		JLabel lblNewLabel_6 = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS,"Aciklama"));
		lblNewLabel_6.setBounds(300, 31, 70, 14);
		panel_1.add(lblNewLabel_6);
		
		lblAciklama = new JLabel("");
		lblAciklama.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAciklama.setBounds(368, 31, 312, 14);
		panel_1.add(lblAciklama);
		
		JLabel lblNewLabel_8 = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS,"Surucu"));
		lblNewLabel_8.setBounds(300, 11, 58, 14);
		panel_1.add(lblNewLabel_8);
		
		lblSurucu = new JLabel("");
		lblSurucu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSurucu.setBounds(368, 11, 317, 14);
		panel_1.add(lblSurucu);
		
		lblDosyaAdi = new JLabel("");
		lblDosyaAdi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDosyaAdi.setBounds(10, 31, 280, 14);
		panel_1.add(lblDosyaAdi);

	}
	public void temizLE()
	{
		lblDosAdet.setText("");
		lblEmirAdi.setText("");
		lblHiz.setText("");
		lblAciklama.setText("");
		lblSurucu.setText("");
		RPB1.setStringPainted(false);
		RPB2.setStringPainted(false);
		RPB1.setMaximum(0);
		RPB1.setValue(0);
		RPB2.setMaximum(0);
		RPB2.setValue(0);
	}
	public static void Progres_Bar_1( int deger) throws InterruptedException
	{
		RPB1.setValue(deger);
	}
 	public static void Progres_Bar_2( int deger) throws InterruptedException
	{
		RPB2.setValue(deger);
	}
	public static void Progres_Bar_Temizle_1()
	{
		RPB1.setMaximum(0);
		RPB1.setValue(0);
		RPB1.setStringPainted(false);
	}
	public static void Progres_Bar_Temizle_2()
	{
		RPB2.setMaximum(0);
		RPB2.setValue(0);
		RPB2.setStringPainted(false);
	}
}

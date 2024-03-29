package obs.backup.other;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;

public class DownloadPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JLabel lblEmirAdi;
	public JLabel lblDosBoyut;
	public JLabel lblSurucu;
	public JLabel lblInen;
	public JLabel lblKalan;
	public JLabel lblHiz ;
	
	public JProgressBar RPB1;
	public JProgressBar RPB2;
	/**
	 * Create the panel.
	 */
	public DownloadPanel() {
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
		RPB1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		RPB1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		RPB1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(RPB1);
		
		RPB2 = new JProgressBar();
		RPB2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		RPB2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		RPB2.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(RPB2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel( dilSecenek.dil(OBS_BACKUP.dILS, "Dos.Boyut"));
		lblNewLabel.setBounds(10, 31, 69, 14);
		panel_1.add(lblNewLabel);
		
		lblDosBoyut = new JLabel("0");
		lblDosBoyut.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDosBoyut.setBounds(78, 31, 118, 14);
		panel_1.add(lblDosBoyut);
		
		JLabel lblNewLabel_2 = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS, "Dosya Adi") );
		lblNewLabel_2.setBounds(10, 11, 58, 14);
		panel_1.add(lblNewLabel_2);
		
		lblEmirAdi = new JLabel(".....");
		lblEmirAdi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmirAdi.setBounds(78, 11, 281, 14);
		panel_1.add(lblEmirAdi);
		
		JLabel lblNewLabel_4 = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS, "Hiz") );
		lblNewLabel_4.setBounds(632, 31, 58, 14);
		panel_1.add(lblNewLabel_4);
		
		lblHiz = new JLabel("0.00 kb/sec");
		lblHiz.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHiz.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHiz.setBounds(700, 31, 124, 14);
		panel_1.add(lblHiz);
		
		JLabel lblNewLabel_8 = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS, "Surucu"));
		lblNewLabel_8.setBounds(386, 11, 58, 14);
		panel_1.add(lblNewLabel_8);
		
		lblSurucu = new JLabel(".....");
		lblSurucu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSurucu.setBounds(454, 11, 370, 14);
		panel_1.add(lblSurucu);
		
		JLabel lblInenn = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS, "Inen") );
		lblInenn.setBounds(206, 31, 58, 14);
		panel_1.add(lblInenn);
		
		lblInen = new JLabel("0");
		lblInen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInen.setBounds(269, 31, 117, 14);
		panel_1.add(lblInen);
		
		JLabel lblKalann = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS, "Kalan") );
		lblKalann.setBounds(386, 31, 58, 14);
		panel_1.add(lblKalann);
		
		lblKalan = new JLabel("0");
		lblKalan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKalan.setBounds(454, 31, 168, 14);
		panel_1.add(lblKalan);
	}
	public void temizLE()
	{
		lblDosBoyut.setText("");
		lblEmirAdi.setText("");
		lblHiz.setText("");
		lblSurucu.setText("");
		lblInen.setText("");
		lblKalan.setText("");
		RPB1.setStringPainted(false);
		RPB2.setStringPainted(false);
		RPB1.setValue(0);
		RPB1.setMaximum(0);
		RPB2.setValue(0);
		RPB2.setMaximum(0);
	}
}

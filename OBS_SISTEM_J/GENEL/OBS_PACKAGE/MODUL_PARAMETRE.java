package OBS_PACKAGE;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Font;

public class MODUL_PARAMETRE extends JInternalFrame {

	private  OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_4;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;

	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel label_12;
	private JLabel label_13;
	private JLabel label_14;
	private JLabel label_55;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MODUL_PARAMETRE frame = new MODUL_PARAMETRE();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MODUL_PARAMETRE() {
		
		setTitle("CALISMA DIZINLERI");
		setClosable(true);
		setBounds(100, 100, 854, 361);
		getContentPane().setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(135, 206, 250)), "Cari Hesap", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 10, 791, 50);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		lblNewLabel = new JLabel("...");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 20, 90, 14);
		lblNewLabel.setForeground(Color.BLUE);
		panel.add(lblNewLabel);
		
		lblNewLabel_2 = new JLabel("...");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(113, 20, 100, 14);
		lblNewLabel_2.setForeground(new Color(0, 128, 128));
		panel.add(lblNewLabel_2);
		
		lblNewLabel_4 = new JLabel("...");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(250, 20, 404, 14);
		lblNewLabel_4.setForeground(Color.RED);
		panel.add(lblNewLabel_4);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(135, 206, 250)), "Stok", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(25, 60, 791, 50);

		panel_1.setLayout(null);
		getContentPane().add(panel_1);
		
		label = new JLabel("...");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 20, 90, 14);
		label.setForeground(Color.BLUE);
		panel_1.add(label);
		
		label_1 = new JLabel("...");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(113, 20, 100, 14);
		label_1.setForeground(new Color(0, 128, 128));
		panel_1.add(label_1);
		
		label_2 = new JLabel("...");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(250, 20, 404, 14);
		label_2.setForeground(Color.RED);
		panel_1.add(label_2);
		
	
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(135, 206, 250)), "Kambiyo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(25, 110, 791, 50);
		panel_2.setLayout(null);
		getContentPane().add(panel_2);
		
		label_3 = new JLabel("...");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(10, 20, 90, 14);
		label_3.setForeground(Color.BLUE);
		panel_2.add(label_3);
		
		label_4 = new JLabel("...");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(113, 20, 100, 14);
		label_4.setForeground(new Color(0, 128, 128));
		panel_2.add(label_4);
		
		label_55 = new JLabel("...");
		label_55.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_55.setForeground(Color.RED);
		label_55.setBounds(250, 20, 404, 14);
		panel_2.add(label_55);
		
	
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(135, 206, 250)), "Adres", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(25, 160, 791, 50);

		panel_3.setLayout(null);
		getContentPane().add(panel_3);
		
		label_6 = new JLabel("...");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(10, 20, 90, 14);
		label_6.setForeground(Color.BLUE);
		panel_3.add(label_6);
		
		label_7 = new JLabel("...");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setBounds(113, 20, 100, 14);
		label_7.setForeground(new Color(0, 128, 128));
		panel_3.add(label_7);
		
		label_8 = new JLabel("...");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(250, 20, 404, 14);
		label_8.setForeground(Color.RED);
		panel_3.add(label_8);
		
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(135, 206, 250)), "Kur", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(25, 210, 791, 50);

		panel_4.setLayout(null);
		getContentPane().add(panel_4);
		
		label_9 = new JLabel("...");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(10, 20, 90, 14);
		label_9.setForeground(Color.BLUE);
		panel_4.add(label_9);
		
		label_10 = new JLabel("...");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_10.setBounds(113, 20, 100, 14);
		label_10.setForeground(new Color(0, 128, 128));
		panel_4.add(label_10);
		
		label_11 = new JLabel("...");
		label_11.setBounds(250, 11, 404, 14);
		label_11.setForeground(Color.RED);
		panel_4.add(label_11);
		
	
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(135, 206, 250)), "Gunluk", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(25, 260, 791, 50);
		panel_5.setLayout(null);
		getContentPane().add(panel_5);
		
		label_12 = new JLabel("...");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_12.setBounds(10, 20, 90, 14);
		label_12.setForeground(Color.BLUE);
		panel_5.add(label_12);
		
		label_13 = new JLabel("...");
		label_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_13.setBounds(113, 20, 100, 14);
		label_13.setForeground(new Color(0, 128, 128));
		panel_5.add(label_13);
		
		label_14 = new JLabel("...");
		label_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_14.setBounds(250, 20, 404, 14);
		label_14.setForeground(Color.RED);
		panel_5.add(label_14);
		
		try {
			yukle();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

private void yukle() throws ClassNotFoundException, SQLException
{
			// Cari
			lblNewLabel.setText(CONNECTION.caridizinbilgi.kod);
			if (CONNECTION.caridizinbilgi.yer.equals("L"))
			{
				lblNewLabel_2.setText(CONNECTION.caridizinbilgi.yer);
			}
			else
			{
				lblNewLabel_2.setText(CONNECTION.caridizinbilgi.server);	
			}
			lblNewLabel_4.setText( CONNECTION.caridizinbilgi.han_sql.equals("MS SQL") ? oac.cARI_HESAP_MSSQL.cari_firma_adi() : oac.cARI_HESAP_MYSQL.cari_firma_adi());

			// Stok
			label.setText(CONNECTION.fatdizinbilgi.kod);
			if (CONNECTION.fatdizinbilgi.yer.equals("L"))
			{
				label_1.setText(CONNECTION.fatdizinbilgi.yer);
			}
			else
			{
				label_1.setText(CONNECTION.fatdizinbilgi.server);	
			}
			label_2.setText( CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL") ? oac.sTOK_MSSQL.fat_firma_adi() : oac.sTOK_MYSQL.fat_firma_adi());
			// Kambiyo 
			label_3.setText(CONNECTION.kamdizinbilgi.kod);
			if (CONNECTION.kamdizinbilgi.yer.equals("L"))
			{
				label_4.setText(CONNECTION.kamdizinbilgi.yer);
			}
			else
			{
				label_4.setText(CONNECTION.kamdizinbilgi.server);	
			}
			//label_55.setText( CONNECTION.kamdizinbilgi.han_sql.equals("MS SQL") ? oac.kAMBIYO_MSSQL.kam_firma_adi(): oac.kAMBIYO_MYSQL.kam_firma_adi());

			// Adres
			label_6.setText(CONNECTION.adrdizinbilgi.kod);
			if (CONNECTION.adrdizinbilgi.yer.equals("L"))
			{
				label_7.setText(CONNECTION.adrdizinbilgi.yer);
			}
			else
			{
				label_7.setText(CONNECTION.adrdizinbilgi.server);	
			}
			label_8.setText( CONNECTION.adrdizinbilgi.han_sql.equals("MS SQL") ? oac.aDRES_MSSQL.adr_firma_adi() : oac.aDRES_MYSQL.adr_firma_adi());
			// Kur
			label_9.setText(CONNECTION.kurdizinbilgi.kod);
			if (CONNECTION.kurdizinbilgi.yer.equals("L"))
			{
				label_10.setText(CONNECTION.kurdizinbilgi.yer);
			}
			else
			{
				label_10.setText(CONNECTION.kurdizinbilgi.server);	
			}
			label_11.setText("");
			// Gunluk
			label_12.setText(CONNECTION.gundizinbilgi.kod);
			if (CONNECTION.gundizinbilgi.yer.equals("L"))
			{
				label_13.setText(CONNECTION.gundizinbilgi.yer);
			}
			else
			{
				label_13.setText(CONNECTION.gundizinbilgi.server);	
			}
			label_14.setText( CONNECTION.gundizinbilgi.han_sql.equals("MS SQL") ? oac.gUNLUK_MSSQL.gun_firma_adi() : oac.gUNLUK_MYSQL.gun_firma_adi());

			}
}

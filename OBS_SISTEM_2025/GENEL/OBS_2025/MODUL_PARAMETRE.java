package OBS_2025;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.GUNLUK_ACCESS;
import OBS_C_2025.KAMBIYO_ACCESS;
import OBS_C_2025.KUR_ACCESS;
import OBS_C_2025.SMS_ACCESS;
import OBS_C_2025.STOK_ACCESS;

import javax.swing.border.EtchedBorder;
import java.awt.Font;

public class MODUL_PARAMETRE extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static CARI_ACCESS c_Access = new CARI_ACCESS(oac._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	static STOK_ACCESS f_Access = new STOK_ACCESS(oac._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	static KUR_ACCESS k_Access = new KUR_ACCESS(oac._IKur , OBS_SIS_2025_ANA_CLASS._IKur_Loger);
	static KAMBIYO_ACCESS ka_Access = new KAMBIYO_ACCESS(oac._IKambiyo , OBS_SIS_2025_ANA_CLASS._IKambiyo_Loger);
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(oac._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	static SMS_ACCESS sms_Access = new SMS_ACCESS(oac._ISms , OBS_SIS_2025_ANA_CLASS._ISms_Loger);
	static GUNLUK_ACCESS g_Access = new GUNLUK_ACCESS(oac._IGunluk , OBS_SIS_2025_ANA_CLASS._IGunluk_Loger);
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_1 = new JLabel(".....");
	private JLabel lblNewLabel_1_1 = new JLabel(".....");
	private JLabel lblNewLabel_1_2 = new JLabel(".....");
	private JLabel lblNewLabel_1_3 = new JLabel(".....");
	private JLabel lblNewLabel_1_4 = new JLabel(".....");
	private JLabel lblNewLabel_1_5 = new JLabel(".....");
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
		lblNewLabel.setBounds(10, 20, 55, 14);
		lblNewLabel.setForeground(Color.BLUE);
		panel.add(lblNewLabel);

		lblNewLabel_2 = new JLabel("...");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(63, 20, 170, 14);
		lblNewLabel_2.setForeground(new Color(0, 128, 128));
		panel.add(lblNewLabel_2);

		lblNewLabel_4 = new JLabel("...");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(250, 20, 404, 14);
		lblNewLabel_4.setForeground(Color.RED);
		panel.add(lblNewLabel_4);

		lblNewLabel_1 = new JLabel(".....");
		lblNewLabel_1.setForeground(new Color(0, 0, 205));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(730, 20, 50, 14);
		panel.add(lblNewLabel_1);



		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(135, 206, 250)), "Stok", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(25, 60, 791, 50);

		panel_1.setLayout(null);
		getContentPane().add(panel_1);

		label = new JLabel("...");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 20, 55, 14);
		label.setForeground(Color.BLUE);
		panel_1.add(label);

		label_1 = new JLabel("...");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(63, 20, 170, 14);
		label_1.setForeground(new Color(0, 128, 128));
		panel_1.add(label_1);

		label_2 = new JLabel("...");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(250, 20, 404, 14);
		label_2.setForeground(Color.RED);
		panel_1.add(label_2);

		lblNewLabel_1_1 = new JLabel(".....");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 205));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(730, 20, 50, 14);
		panel_1.add(lblNewLabel_1_1);



		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(135, 206, 250)), "Kambiyo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(25, 110, 791, 50);
		panel_2.setLayout(null);
		getContentPane().add(panel_2);

		label_3 = new JLabel("...");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(10, 20, 55, 14);
		label_3.setForeground(Color.BLUE);
		panel_2.add(label_3);

		label_4 = new JLabel("...");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(63, 20, 170, 14);
		label_4.setForeground(new Color(0, 128, 128));
		panel_2.add(label_4);

		label_55 = new JLabel("...");
		label_55.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_55.setForeground(Color.RED);
		label_55.setBounds(250, 20, 404, 14);
		panel_2.add(label_55);

		lblNewLabel_1_2 = new JLabel(".....");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 205));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(730, 20, 50, 14);
		panel_2.add(lblNewLabel_1_2);



		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(135, 206, 250)), "Adres", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(25, 160, 791, 50);

		panel_3.setLayout(null);
		getContentPane().add(panel_3);

		label_6 = new JLabel("...");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(10, 20, 55, 14);
		label_6.setForeground(Color.BLUE);
		panel_3.add(label_6);

		label_7 = new JLabel("...");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setBounds(63, 20, 170, 14);
		label_7.setForeground(new Color(0, 128, 128));
		panel_3.add(label_7);

		label_8 = new JLabel("...");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(250, 20, 404, 14);
		label_8.setForeground(Color.RED);
		panel_3.add(label_8);

		lblNewLabel_1_3 = new JLabel(".....");
		lblNewLabel_1_3.setForeground(new Color(0, 0, 205));
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_3.setBounds(730, 20, 50, 14);
		panel_3.add(lblNewLabel_1_3);



		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(135, 206, 250)), "Kur", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(25, 210, 791, 50);

		panel_4.setLayout(null);
		getContentPane().add(panel_4);

		label_9 = new JLabel("...");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(10, 20, 55, 14);
		label_9.setForeground(Color.BLUE);
		panel_4.add(label_9);

		label_10 = new JLabel("...");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_10.setBounds(63, 20, 170, 14);
		label_10.setForeground(new Color(0, 128, 128));
		panel_4.add(label_10);

		label_11 = new JLabel("...");
		label_11.setBounds(250, 11, 404, 14);
		label_11.setForeground(Color.RED);
		panel_4.add(label_11);

		lblNewLabel_1_4 = new JLabel(".....");
		lblNewLabel_1_4.setForeground(new Color(0, 0, 205));
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_4.setBounds(730, 20, 50, 14);
		panel_4.add(lblNewLabel_1_4);


		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(135, 206, 250)), "Gunluk", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(25, 260, 791, 50);
		panel_5.setLayout(null);
		getContentPane().add(panel_5);

		label_12 = new JLabel("...");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_12.setBounds(10, 20, 55, 14);
		label_12.setForeground(Color.BLUE);
		panel_5.add(label_12);

		label_13 = new JLabel("...");
		label_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_13.setBounds(63, 20, 170, 14);
		label_13.setForeground(new Color(0, 128, 128));
		panel_5.add(label_13);

		label_14 = new JLabel("...");
		label_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_14.setBounds(250, 20, 404, 14);
		label_14.setForeground(Color.RED);
		panel_5.add(label_14);

		lblNewLabel_1_5 = new JLabel(".....");
		lblNewLabel_1_5.setForeground(new Color(0, 0, 205));
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_5.setBounds(730, 20, 50, 14);
		panel_5.add(lblNewLabel_1_5);

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
		lblNewLabel.setText(BAGLAN.cariDizin.kOD);
		lblNewLabel_1.setText(BAGLAN.cariDizin.hAN_SQL);
		if (BAGLAN.cariDizin.yER.equals("L"))
		{
			lblNewLabel_2.setText(BAGLAN.cariDizin.yER);
		}
		else
		{
			lblNewLabel_2.setText(BAGLAN.cariDizin.sERVER);	
		}
		lblNewLabel_4.setText( c_Access.cari_firma_adi() );

		// Stok
		label.setText(BAGLAN.fatDizin.kOD);
		lblNewLabel_1_1.setText(BAGLAN.fatDizin.hAN_SQL);
		if (BAGLAN.fatDizin.yER.equals("L"))
		{
			label_1.setText(BAGLAN.fatDizin.yER);
		}
		else
		{
			label_1.setText(BAGLAN.fatDizin.sERVER);	
		}
		label_2.setText( f_Access.fat_firma_adi() );
		// Kambiyo 
		label_3.setText(BAGLAN.kamDizin.kOD);
		lblNewLabel_1_2.setText(BAGLAN.kamDizin.hAN_SQL);
		if (BAGLAN.kamDizin.yER.equals("L"))
		{
			label_4.setText(BAGLAN.kamDizin.yER);
		}
		else
		{
			label_4.setText(BAGLAN.kamDizin.sERVER);	
		}
		label_55.setText( ka_Access.kam_firma_adi());

		// Adres
		label_6.setText(BAGLAN.adrDizin.kOD);
		lblNewLabel_1_3.setText(BAGLAN.adrDizin.hAN_SQL);
		if (BAGLAN.adrDizin.yER.equals("L"))
		{
			label_7.setText(BAGLAN.adrDizin.yER);
		}
		else
		{
			label_7.setText(BAGLAN.adrDizin.sERVER);	
		}
		label_8.setText( a_Access.adr_firma_adi() );
		// Kur
		label_9.setText(BAGLAN.kurDizin.kOD);
		lblNewLabel_1_4.setText(BAGLAN.kurDizin.hAN_SQL);
		if (BAGLAN.kurDizin.yER.equals("L"))
		{
			label_10.setText(BAGLAN.kurDizin.yER);
		}
		else
		{
			label_10.setText(BAGLAN.kurDizin.sERVER);	
		}
		label_11.setText("");
		// Gunluk
		label_12.setText(BAGLAN.gunDizin.kOD);
		lblNewLabel_1_5.setText(BAGLAN.gunDizin.hAN_SQL);
		if (BAGLAN.gunDizin.yER.equals("L"))
		{
			label_13.setText(BAGLAN.gunDizin.yER);
		}
		else
		{
			label_13.setText(BAGLAN.gunDizin.sERVER);	
		}
		label_14.setText(g_Access.gun_firma_adi() );

	}
}

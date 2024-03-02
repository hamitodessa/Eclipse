package OBS_2025;

import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.GUNLUK_ACCESS;
import OBS_C_2025.KAMBIYO_ACCESS;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.KUR_ACCESS;
import OBS_C_2025.SMS_ACCESS;
import OBS_C_2025.STOK_ACCESS;
import raven.toast.Notifications;

import javax.swing.border.EtchedBorder;
import java.awt.Font;


@SuppressWarnings({"serial"})
public class MODUL_PARAMETRE extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static CARI_ACCESS c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	static KUR_ACCESS k_Access = new KUR_ACCESS(OBS_SIS_2025_ANA_CLASS._IKur , OBS_SIS_2025_ANA_CLASS._IKur_Loger);
	static KAMBIYO_ACCESS ka_Access = new KAMBIYO_ACCESS(OBS_SIS_2025_ANA_CLASS._IKambiyo , OBS_SIS_2025_ANA_CLASS._IKambiyo_Loger);
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(OBS_SIS_2025_ANA_CLASS._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	static SMS_ACCESS sms_Access = new SMS_ACCESS(OBS_SIS_2025_ANA_CLASS._ISms , OBS_SIS_2025_ANA_CLASS._ISms_Loger);
	static GUNLUK_ACCESS g_Access = new GUNLUK_ACCESS(OBS_SIS_2025_ANA_CLASS._IGunluk , OBS_SIS_2025_ANA_CLASS._IGunluk_Loger);
	static KERESTE_ACCESS ker_Access = new KERESTE_ACCESS(OBS_SIS_2025_ANA_CLASS._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_1 = new JLabel(".....");
	private JLabel lblNewLabel_1_1 = new JLabel(".....");
	private JLabel lblNewLabel_1_2 = new JLabel(".....");
	private JLabel lblNewLabel_1_3 = new JLabel(".....");
	private JLabel lblNewLabel_1_4 = new JLabel(".....");
	private JLabel lblNewLabel_1_5 = new JLabel(".....");
	private JLabel lblNewLabel_1_6 = new JLabel(".....");
	private JLabel lblClog ;
	private JLabel lblAdrlog ;
	private JLabel lblStoklog ;
	private JLabel lblGunlog ;
	private JLabel lblSmslog ;
	private JLabel lblKerlog ;
	private JLabel lblKurlog ;
	private JLabel lblKamlog ;
	
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
	private JLabel label_12_1;
	private JLabel label_13_1;
	private JLabel label_14_1;
	private JLabel label_12_1_1 ;
	private JLabel lblNewLabel_1_6_1; 
	private JLabel label_14_1_1;
	private JLabel label_13_1_1 ;
	
	//int x ,y ;
	
	public MODUL_PARAMETRE() {
		/*
		 * addMouseMotionListener(new MouseMotionAdapter() {
		 * 
		 * @Override public void mouseDragged(MouseEvent e) { int xx = e.getXOnScreen();
		 * int yy = e.getYOnScreen(); setLocation(xx-x,yy-y);
		 * 
		 * } }); addMouseListener(new MouseAdapter() {
		 * 
		 * @Override public void mousePressed(MouseEvent e) { x = e.getX(); y = e.getY()
		 * ; } });
		 */		//setBorder(null);
		//BasicInternalFrameUI bUi =(BasicInternalFrameUI) this.getUI();
		//bUi.setNorthPane(null);
		//getRootPane().setWindowDecorationStyle(5);
		//************************************************************************
		setTitle("CALISMA DIZINLERI");
		setClosable(true);
		setBounds(100, 100, 854, 456);
		getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cari Hesap", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel.setBounds(25, 10, 791, 50);
		panel.setLayout(null);
		getContentPane().add(panel);

		lblNewLabel = new JLabel("...");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 20, 55, 14);
		//lblNewLabel.setForeground(Color.BLUE);
		panel.add(lblNewLabel);

		lblNewLabel_2 = new JLabel("...");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(63, 20, 170, 14);
		//lblNewLabel_2.setForeground(new Color(0, 128, 128));
		panel.add(lblNewLabel_2);

		lblNewLabel_4 = new JLabel("...");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(250, 20, 350, 14);
		//lblNewLabel_4.setForeground(Color.RED);
		panel.add(lblNewLabel_4);
		
		lblClog = new JLabel("");
		lblClog.setFont(new Font("Tahoma", Font.BOLD, 11));
		//lblClog.setForeground(new Color(128, 0, 0));
		lblClog.setBounds(610, 20, 80, 14);
		panel.add(lblClog);

		lblNewLabel_1 = new JLabel(".....");
		//lblNewLabel_1.setForeground(Color.decode("#075985"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(730, 20, 50, 14);
		panel.add(lblNewLabel_1);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Stok", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(25, 60, 791, 50);
		panel_1.setLayout(null);
		getContentPane().add(panel_1);

		label = new JLabel("...");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 20, 55, 14);
		//label.setForeground(Color.BLUE);
		panel_1.add(label);

		label_1 = new JLabel("...");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(63, 20, 170, 14);
		//label_1.setForeground(new Color(0, 128, 128));
		panel_1.add(label_1);

		label_2 = new JLabel("...");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(250, 20, 350, 14);
		//label_2.setForeground(Color.RED);
		panel_1.add(label_2);

		lblStoklog = new JLabel("");
		lblStoklog.setFont(new Font("Tahoma", Font.BOLD, 11));
		//lblStoklog.setForeground(new Color(128, 0, 0));
		lblStoklog .setBounds(610, 20, 80, 14);
		panel_1.add(lblStoklog );

		lblNewLabel_1_1 = new JLabel(".....");
		//lblNewLabel_1_1.setForeground(Color.decode("#075985"));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(730, 20, 50, 14);
		panel_1.add(lblNewLabel_1_1);
		
		



		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Kambiyo", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel_2.setBounds(25, 210, 791, 50);
		panel_2.setLayout(null);
		getContentPane().add(panel_2);

		label_3 = new JLabel("...");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(10, 20, 55, 14);
		//label_3.setForeground(Color.BLUE);
		panel_2.add(label_3);

		label_4 = new JLabel("...");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(63, 20, 170, 14);
		//label_4.setForeground(new Color(0, 128, 128));
		panel_2.add(label_4);

		label_55 = new JLabel("...");
		label_55.setFont(new Font("Tahoma", Font.BOLD, 11));
		//label_55.setForeground(Color.RED);
		label_55.setBounds(250, 20, 350, 14);
		panel_2.add(label_55);
		
		lblKamlog = new JLabel("");
		lblKamlog.setFont(new Font("Tahoma", Font.BOLD, 11));
		//lblKamlog.setForeground(new Color(128, 0, 0));
		lblKamlog .setBounds(610, 20, 80, 14);
		panel_2.add(lblKamlog );


		lblNewLabel_1_2 = new JLabel(".....");
		//lblNewLabel_1_2.setForeground(Color.decode("#075985"));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(730, 20, 50, 14);
		panel_2.add(lblNewLabel_1_2);



		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Adres", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(25, 160, 791, 50);

		panel_3.setLayout(null);
		getContentPane().add(panel_3);

		label_6 = new JLabel("...");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(10, 20, 55, 14);
		//label_6.setForeground(Color.BLUE);
		panel_3.add(label_6);

		label_7 = new JLabel("...");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setBounds(63, 20, 170, 14);
		//label_7.setForeground(new Color(0, 128, 128));
		panel_3.add(label_7);

		label_8 = new JLabel("...");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(250, 20, 350, 14);
		//label_8.setForeground(Color.RED);
		panel_3.add(label_8);
		
		lblAdrlog = new JLabel("");
		lblAdrlog.setFont(new Font("Tahoma", Font.BOLD, 11));
		//lblAdrlog.setForeground(new Color(128, 0, 0));
		lblAdrlog .setBounds(610, 20, 80, 14);
		panel_3.add(lblAdrlog );

		lblNewLabel_1_3 = new JLabel(".....");
		//lblNewLabel_1_3.setForeground(Color.decode("#075985"));
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_3.setBounds(730, 20, 50, 14);
		panel_3.add(lblNewLabel_1_3);



		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Kur", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(25, 110, 791, 50);  //panel_2.setBounds(25, 110, 791, 50);setBounds(25, 210, 791, 50)

		panel_4.setLayout(null);
		getContentPane().add(panel_4);

		label_9 = new JLabel("...");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(10, 20, 55, 14);
		//label_9.setForeground(Color.BLUE);
		panel_4.add(label_9);

		label_10 = new JLabel("...");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_10.setBounds(63, 20, 170, 14);
		//label_10.setForeground(new Color(0, 128, 128));
		panel_4.add(label_10);

		label_11 = new JLabel("...");
		label_11.setBounds(250, 20, 350, 14);
		//label_11.setForeground(Color.RED);
		panel_4.add(label_11);

		lblNewLabel_1_4 = new JLabel(".....");
		//lblNewLabel_1_4.setForeground(Color.decode("#075985"));
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_4.setBounds(730, 20, 50, 14);
		panel_4.add(lblNewLabel_1_4);
		

		
		lblKurlog = new JLabel("");
		lblKurlog.setFont(new Font("Tahoma", Font.BOLD, 11));
		//lblKurlog.setForeground(new Color(128, 0, 0));
		lblKurlog .setBounds(610, 20, 80, 14);
		panel_4.add(lblKurlog );


		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Gunluk", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(25, 260, 791, 50);
		panel_5.setLayout(null);
		getContentPane().add(panel_5);

		label_12 = new JLabel("...");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_12.setBounds(10, 20, 55, 14);
		//label_12.setForeground(Color.BLUE);
		panel_5.add(label_12);

		label_13 = new JLabel("...");
		label_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_13.setBounds(63, 20, 170, 14);
		//label_13.setForeground(new Color(0, 128, 128));
		panel_5.add(label_13);

		label_14 = new JLabel("...");
		label_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_14.setBounds(250, 20, 350, 14);
		//label_14.setForeground(Color.RED);
		panel_5.add(label_14);
		
		lblGunlog = new JLabel("");
		lblGunlog.setFont(new Font("Tahoma", Font.BOLD, 11));
		//lblGunlog.setForeground(new Color(128, 0, 0));
		lblGunlog .setBounds(610, 20, 80, 14);
		panel_5.add(lblGunlog );

		lblNewLabel_1_5 = new JLabel(".....");
		//lblNewLabel_1_5.setForeground(Color.decode("#075985"));
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_5.setBounds(730, 20, 50, 14);
		panel_5.add(lblNewLabel_1_5);
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setLayout(null);
		panel_5_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Kereste", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel_5_1.setBounds(25, 310, 791, 50);
		getContentPane().add(panel_5_1);
		
		label_12_1 = new JLabel("");
		//label_12_1.setForeground(Color.BLUE);
		label_12_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_12_1.setBounds(10, 20, 55, 14);
		panel_5_1.add(label_12_1);
		
		label_13_1 = new JLabel("...");
		//label_13_1.setForeground(new Color(0, 128, 128));
		label_13_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_13_1.setBounds(63, 20, 170, 14);
		panel_5_1.add(label_13_1);
		
		label_14_1 = new JLabel((String) null);
		//label_14_1.setForeground(Color.RED);
		label_14_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_14_1.setBounds(250, 20, 350, 14);
		panel_5_1.add(label_14_1);
		
		lblKerlog = new JLabel("");
		lblKerlog.setFont(new Font("Tahoma", Font.BOLD, 11));
		//lblKerlog.setForeground(new Color(128, 0, 0));
		lblKerlog .setBounds(610, 20, 80, 14);
		panel_5_1.add(lblKerlog );
		
		lblNewLabel_1_6 = new JLabel("");
		//lblNewLabel_1_6.setForeground(Color.decode("#075985"));
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_6.setBounds(730, 20, 50, 14);
		panel_5_1.add(lblNewLabel_1_6);
		
		JPanel panel_5_1_1 = new JPanel();
		panel_5_1_1.setLayout(null);
		panel_5_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Sms", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5_1_1.setBounds(25, 366, 791, 50);
		getContentPane().add(panel_5_1_1);
		
		label_12_1_1 = new JLabel("");
		//label_12_1_1.setForeground(Color.BLUE);
		label_12_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_12_1_1.setBounds(10, 20, 55, 14);
		panel_5_1_1.add(label_12_1_1);
		
		label_13_1_1 = new JLabel("...");
		//label_13_1_1.setForeground(new Color(0, 128, 128));
		label_13_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_13_1_1.setBounds(63, 20, 170, 14);
		panel_5_1_1.add(label_13_1_1);
		
		label_14_1_1 = new JLabel((String) null);
		//label_14_1_1.setForeground(Color.RED);
		label_14_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_14_1_1.setBounds(250, 20, 350, 14);
		panel_5_1_1.add(label_14_1_1);
		
		lblSmslog = new JLabel("");
		lblSmslog.setFont(new Font("Tahoma", Font.BOLD, 11));
		//lblSmslog.setForeground(new Color(128, 0, 0));
		lblSmslog.setBounds(610, 20, 80, 14);
		panel_5_1_1.add(lblSmslog);
		
		lblNewLabel_1_6_1 = new JLabel("");
		//lblNewLabel_1_6_1.setForeground(Color.decode("#075985"));
		lblNewLabel_1_6_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_6_1.setBounds(730, 20, 50, 14);
		panel_5_1_1.add(lblNewLabel_1_6_1);

		yukle();
	}

	private void yukle() 
	{
		try {
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
		lblClog.setText(BAGLAN.cariDizin.lOG == true ? "Loglama" : "");
		lblNewLabel_4.setText( c_Access.cari_firma_adi()  );

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
		lblStoklog.setText(BAGLAN.fatDizin.lOG == true ? "Loglama" : "");
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
		lblKamlog.setText(BAGLAN.kamDizin.lOG == true ? "Loglama" : "");
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
		lblAdrlog.setText(BAGLAN.adrDizin.lOG == true ? "Loglama" : "");
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
		lblKurlog.setText(BAGLAN.kurDizin.lOG == true ? "Loglama" : "");
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
		lblGunlog.setText(BAGLAN.gunDizin.lOG == true ? "Loglama" : "");
		label_14.setText(g_Access.gun_firma_adi() );
		// Kereste
		label_12_1.setText(BAGLAN.kerDizin.kOD);
		lblNewLabel_1_6.setText(BAGLAN.kerDizin.hAN_SQL);
		if (BAGLAN.kerDizin.yER.equals("L"))
		{
			label_13_1.setText(BAGLAN.kerDizin.yER);
		}
		else
		{
			label_13_1.setText(BAGLAN.kerDizin.sERVER);	
		}
		lblKerlog.setText(BAGLAN.kerDizin.lOG == true ? "Loglama" : "");
		label_14_1.setText(ker_Access.ker_firma_adi() );
		// Sms
		label_12_1_1.setText(BAGLAN.smsDizin.kOD);
		lblNewLabel_1_6_1.setText(BAGLAN.smsDizin.hAN_SQL);
		if (BAGLAN.smsDizin.yER.equals("L"))
		{
			label_13_1_1.setText(BAGLAN.smsDizin.yER);
		}
		else
		{
			label_13_1_1.setText(BAGLAN.smsDizin.sERVER);	
		}
		lblSmslog.setText(BAGLAN.smsDizin.lOG == true ? "Loglama" : "");
		
		} 
		catch (Exception e) 
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, e.getMessage() );
		}
	}
}

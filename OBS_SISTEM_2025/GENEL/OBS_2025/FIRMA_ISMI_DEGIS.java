package OBS_2025;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.GUNLUK_ACCESS;
import OBS_C_2025.KAMBIYO_ACCESS;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.lOG_BILGI;
import raven.toast.Notifications;

import java.awt.Font;

@SuppressWarnings({"serial","static-access"})
public class FIRMA_ISMI_DEGIS extends JInternalFrame {

	private static String modul ;
	private static Obs_TextFIeld textField;
	private static JPanel panel_1;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS() ;
	static CARI_ACCESS c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	static KAMBIYO_ACCESS ka_Access = new KAMBIYO_ACCESS(OBS_SIS_2025_ANA_CLASS._IKambiyo , OBS_SIS_2025_ANA_CLASS._IKambiyo_Loger);
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(OBS_SIS_2025_ANA_CLASS._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	static GUNLUK_ACCESS g_Access = new GUNLUK_ACCESS(OBS_SIS_2025_ANA_CLASS._IGunluk , OBS_SIS_2025_ANA_CLASS._IGunluk_Loger);
	static KERESTE_ACCESS ker_Access = new KERESTE_ACCESS(OBS_SIS_2025_ANA_CLASS._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FIRMA_ISMI_DEGIS() {
		setClosable(true);
		setTitle("FIRMA ISMI DEGISTIRME");
		setBounds(100, 100, 663, 126);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(FIRMA_ISMI_DEGIS.class.getResource("/ICONLAR/icons8-organization-30.png")), 16, 16));//
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		panel_1 = new JPanel();
		//panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Baslik", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		panel_1.setBounds(52, 11, 529, 64);
		panel.add(panel_1);
		panel_1.setLayout(null);

		textField = new Obs_TextFIeld(50);
		//textField.setForeground(new Color(0, 0, 128));
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(53, 25, 423, 20);
		panel_1.add(textField);
		textField.setColumns(10);

	}
	public static void modul(String mod)
	{
		modul = mod ;
		if (modul.equals("cari"))
		{
			panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Cari Hesap", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

			isim_oku(mod);
		}
		else if (modul.equals("kambiyo"))
		{
			panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Kambiyo", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

			isim_oku(mod);
		}
		else if (modul.equals("stok"))
		{
			panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Stok", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

			isim_oku(mod);
		}
		else if (modul.equals("adres"))
		{
			panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Adres", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

			isim_oku(mod);
		}
		else if (modul.equals("gunluk"))
		{
			panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Gunluk", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

			isim_oku(mod);
		}
		else if (modul.equals("kereste"))
		{
			panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Kereste", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

			isim_oku(mod);
		}
	}
	private static void  isim_oku(String modul)
	{
		try
		{
			if (modul.equals("cari"))
			{
				textField.setText(oac._ICar.cari_firma_adi() );
			}
			else if (modul.equals("kambiyo"))
			{
				textField.setText(oac._IKambiyo.kam_firma_adi());
			}
			else if (modul.equals("stok"))
			{
				textField.setText(oac._IStok.fat_firma_adi());
			}
			else if (modul.equals("adres"))
			{
				textField.setText(oac._IAdres.adr_firma_adi());
			}
			else if (modul.equals("gunluk"))
			{
				textField.setText(oac._IGunluk.gun_firma_adi());
			}
			else if (modul.equals("kereste"))
			{
				textField.setText(oac._IKereste.ker_firma_adi());
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
		}
	}
	public static void kaydet()
	{
		try
		{
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ("Firma Ismi :" +textField.getText() );
			lBILGI.seteVRAK("");
			if (modul.equals("cari"))
			{
				c_Access.cari_firma_adi_kayit(textField.getText(), lBILGI, BAGLAN_LOG.cariLogDizin);
				BAGLAN.cariDizin.fIRMA_ADI = textField.getText();
				OBS_MAIN.lblCariBilgi.setText (BAGLAN.cariDizin.kOD + "  /  " + BAGLAN.cariDizin.fIRMA_ADI + "  /  " + (BAGLAN.cariDizin.yER.toString().equals("S") ?  BAGLAN.cariDizin.sERVER : "Lokal"));
			}
			else if (modul.equals("kambiyo"))
			{
				ka_Access.kam_firma_adi_kayit(textField.getText(),lBILGI, BAGLAN_LOG.kamLogDizin);
				BAGLAN.kamDizin.fIRMA_ADI = textField.getText();
				OBS_MAIN.lblKambiyoBilgi.setText (BAGLAN.kamDizin.kOD + "  /  " + BAGLAN.kamDizin.fIRMA_ADI + "  /  " + (BAGLAN.kamDizin.yER.toString().equals("S") ?  BAGLAN.kamDizin.sERVER : "Lokal"));
			}
			else if (modul.equals("stok"))
			{
				f_Access.stk_firma_adi_kayit(textField.getText(), lBILGI, BAGLAN_LOG.fatLogDizin);
				BAGLAN.fatDizin.fIRMA_ADI = textField.getText();
				OBS_MAIN.lblStokBilgi.setText (BAGLAN.fatDizin.kOD + "  /  " + BAGLAN.fatDizin.fIRMA_ADI + "  /  " + (BAGLAN.fatDizin.yER.toString().equals("S") ?  BAGLAN.fatDizin.sERVER : "Lokal"));
			}
			else if (modul.equals("adres"))
			{
				a_Access.adr_firma_adi_kayit(textField.getText(), lBILGI, BAGLAN_LOG.adrLogDizin);
				BAGLAN.adrDizin.fIRMA_ADI = textField.getText();
				OBS_MAIN.lblAdresBilgi.setText (BAGLAN.adrDizin.kOD + "  /  " + 	BAGLAN.adrDizin.fIRMA_ADI + "  /  " + (	BAGLAN.adrDizin.yER.toString().equals("S") ?  	BAGLAN.adrDizin.sERVER : "Lokal"));
			}
			else if (modul.equals("gunluk"))
			{
				g_Access.gun_firma_adi_kayit(textField.getText(),lBILGI, BAGLAN_LOG.gunLogDizin);
				BAGLAN.gunDizin.fIRMA_ADI = textField.getText();
				OBS_MAIN.lblGunlukBilgi.setText (BAGLAN.gunDizin.kOD + "  / " + 	BAGLAN.gunDizin.fIRMA_ADI + "  /  " + (	BAGLAN.gunDizin.yER.toString().equals("S") ?  	BAGLAN.gunDizin.sERVER : "Lokal"));
			}
			else if (modul.equals("kereste"))
			{
				ker_Access.ker_firma_adi_kayit(textField.getText(),lBILGI, BAGLAN_LOG.kerLogDizin);
				BAGLAN.kerDizin.fIRMA_ADI = textField.getText();
				OBS_MAIN.lblKeresteBilgi.setText (BAGLAN.kerDizin.kOD + "  /  " + 	BAGLAN.kerDizin.fIRMA_ADI + "  /  " + (	BAGLAN.kerDizin.yER.toString().equals("S") ?  	BAGLAN.kerDizin.sERVER : "Lokal"));
			}
			kapat();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
		}
	}
	private static void kapat()
	{
		for(int i=0;i<OBS_MAIN.desktopPane.getAllFrames().length;i++)
		{   
			JInternalFrame frame=(JInternalFrame) OBS_MAIN.desktopPane.getComponent(i);
			String tit = frame.getTitle();
			if (tit.equals("FIRMA ISMI DEGISTIRME") )
			{
				OBS_MAIN.desktopPane.remove(i);
				OBS_MAIN.desktopPane.repaint();
			}
		}
	}
}

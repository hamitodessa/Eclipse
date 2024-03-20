package OBS_2025;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import javax.mail.util.ByteArrayDataSource;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.IADRES;
import OBS_C_2025.ICARI_HESAP;
import OBS_C_2025.IConnection;
import OBS_C_2025.IGUNLUK;
import OBS_C_2025.IKAMBIYO;
import OBS_C_2025.IKERESTE;
import OBS_C_2025.IKUR;
import OBS_C_2025.ILOGGER;
import OBS_C_2025.ISMS;
import OBS_C_2025.ISTOK;
import OBS_C_2025.SIFRE_DONDUR;
import OBS_C_2025.USER_ISLEMLERI;

public class OBS_SIS_2025_ANA_CLASS 
{
	public static boolean CARI_CONN;
	public static boolean KUR_CONN;
	public static boolean SMS_CONN;
	public static boolean ADR_CONN;
	public static boolean FAT_CONN;
	public static boolean KAM_CONN;
	public static boolean GUN_CONN;
	public static boolean KER_CONN;

	public static ICARI_HESAP  _ICar ;
	public static ISTOK _IStok;
	public static IKUR _IKur;
	public static IADRES _IAdres;
	public static IKAMBIYO _IKambiyo;
	public static IGUNLUK _IGunluk;
	public static ISMS _ISms;
	public static IKERESTE _IKereste;
	
	public static ILOGGER[] _ICari_Loger = {};//new DOSYA_YAZ(new DOSYA_MSSQL()), new MAIL_AT()
	public static ILOGGER[] _IKur_Loger = {};
	public static ILOGGER[] _IAdres_Loger = {};
	public static ILOGGER[] _IFatura_Loger = {};
	public static ILOGGER[] _IKambiyo_Loger = {};
	public static ILOGGER[] _ISms_Loger = {};
	public static ILOGGER[] _IGunluk_Loger = {};
	public static ILOGGER[] _IKereste_Loger = {};

	
	public IConnection _IConn ;
	public IConnection _ICariCon ;
	public IConnection _IStokCon ;
	public IConnection _IKurCon ;
	public IConnection _IAdresCon ;
	public IConnection _IKambiyoCon ;
	public IConnection _IGunlukCon ;
	public IConnection _ISmsCon ;
	public IConnection _IKeresteCon ;

	public static ByteArrayDataSource ds = null ;
	public static Color gridcolor =  new Color(0, 191, 255);      //   0,153,204   java.awt.Color[r=204,g=0,b=102]
	public Color dtcColor = new Color(224,224,224);
	public static Color satBackColor ;
	public static Color satForeColor ;
	public static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	public static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	public GLOBAL glb  = new GLOBAL();
	public USER_ISLEMLERI uSER_ISL = new USER_ISLEMLERI();
	public Tema_Cari tCR = new Tema_Cari();
	public SIFRE_DONDUR sDONDUR = new     SIFRE_DONDUR();
	public static String hsp_hsp_kodu = ""; 
	public static String stk_kodu = ""; 
	public BufferedImage kam_resmi ;
	public static String irs_no = ""; 
	public static String nerden = "";
	public String[] options = {"Tamam......       		!	", "Vazgec......       		!	"}; 
	public int mesajDeger(String mesaj)
	{
		int result = 0;
		if(mesaj.equals(options[0]))
			result = 1;
		else if(mesaj.equals(options[1]))
			result = 0;
		return result;
	}
}

package OBS_2025;


import OBS_C_2025.GLOBAL;
import OBS_C_2025.IADRES;
import OBS_C_2025.ICARI_HESAP;
import OBS_C_2025.IConnection;
import OBS_C_2025.IGUNLUK;
import OBS_C_2025.IKAMBIYO;
import OBS_C_2025.IKUR;
import OBS_C_2025.ILOGGER;
import OBS_C_2025.ISMS;
import OBS_C_2025.ISTOK;
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
    
    public ICARI_HESAP  _ICar ;
    public ISTOK _Istok;
    public IKUR _IKur;
    public IADRES _IAdres;
    public IKAMBIYO _IKambiyo;
    public IGUNLUK _IGunluk;
    public ISMS _ISms;
    public ILOGGER[] _ILogger;
   
    public String[] options = {"Tamam......       		!", "Vazgec.....       		!"}; 
    public IConnection _IConn ;
	 public int txt_yukseklik =20;
	 public GLOBAL glb  = new GLOBAL();
	 public USER_ISLEMLERI uSER_ISL = new USER_ISLEMLERI();
	 

}

package OBS_2025;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.image.BufferedImage;

import LOGER_KAYIT.DOSYA_MSSQL;
import OBS_C_2025.DOSYA_YAZ;
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
import OBS_C_2025.MAIL_AT;
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
    
    public static ICARI_HESAP  _ICar ;
    public static ISTOK _IStok;
    public static IKUR _IKur;
    public static IADRES _IAdres;
    public static IKAMBIYO _IKambiyo;
    public static IGUNLUK _IGunluk;
    public static ISMS _ISms;
   // public static ILOGGER[] _ILogger;
    public static ILOGGER[] _ICari_Loger = {new DOSYA_YAZ(new DOSYA_MSSQL()), new MAIL_AT()};
    public static ILOGGER[] _IKur_Loger = {new DOSYA_YAZ(new DOSYA_MSSQL()), new MAIL_AT()};
    public static ILOGGER[] _IAdres_Loger = {new DOSYA_YAZ(new DOSYA_MSSQL()), new MAIL_AT()};
    public static ILOGGER[] _IFatura_Loger = {new DOSYA_YAZ(new DOSYA_MSSQL()), new MAIL_AT()};
    public static ILOGGER[] _IKambiyo_Loger = {new DOSYA_YAZ(new DOSYA_MSSQL()), new MAIL_AT()};
    public static ILOGGER[] _ISms_Loger = {new DOSYA_YAZ(new DOSYA_MSSQL()), new MAIL_AT()};
    public static ILOGGER[] _IGunluk_Loger = {new DOSYA_YAZ(new DOSYA_MSSQL()), new MAIL_AT()};
    
   
    public String[] options = {"Tamam......       		!", "Vazgec.....       		!"}; 
    public IConnection _IConn ;
	public int txt_yukseklik =20;
	public static Color gridcolor ; //=  new Color(0, 191, 255);      //   0,153,204   java.awt.Color[r=204,g=0,b=102]
	public static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	public static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	public GLOBAL glb  = new GLOBAL();
	public USER_ISLEMLERI uSER_ISL = new USER_ISLEMLERI();
	public Tema_Cari tCR = new Tema_Cari();
	
	public static String hsp_hsp_kodu =""; 
	public static String stk_kodu =""; 
	public BufferedImage kam_resmi ;
	 public static String irs_no =""; 
	 public static String nerden ="";
	 
   
}

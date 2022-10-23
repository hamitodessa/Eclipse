package OBS_PACKAGE;

import java.awt.Color;
import java.awt.image.BufferedImage;
public class OBS_SIS_ANA_CLAS {
	public static boolean CARI_CONN;
    public static boolean KUR_CONN;
    public static boolean SMS_CONN;
    public static boolean ADR_CONN;
    public static boolean FAT_CONN;
    public static boolean KAM_CONN;
    public static boolean GUN_CONN;
    public GLOBAL glb  = new GLOBAL();
    public OBS_ORTAK_MSSQL obsort_mssql = new OBS_ORTAK_MSSQL();
    public OBS_ORTAK_MYSQL obsort_mysql = new OBS_ORTAK_MYSQL();
    public CARI_HESAP_MSSQL cARI_HESAP_MSSQL = new CARI_HESAP_MSSQL();
    public CARI_HESAP_MYSQL cARI_HESAP_MYSQL = new CARI_HESAP_MYSQL();
    public STOK_MSSQL sTOK_MSSQL = new STOK_MSSQL();
    public STOK_MYSQL sTOK_MYSQL = new STOK_MYSQL();
    public ADRES_MSSQL aDRES_MSSQL = new ADRES_MSSQL();
    public ADRES_MYSQL aDRES_MYSQL = new ADRES_MYSQL();
    public KAMBIYO_MSSQL kAMBIYO_MSSQL = new KAMBIYO_MSSQL();
    public KAMBIYO_MYSQL kAMBIYO_MYSQL = new KAMBIYO_MYSQL();
    public GUNLUK_MSSQL gUNLUK_MSSQL = new GUNLUK_MSSQL();
    public GUNLUK_MYSQL gUNLUK_MYSQL = new GUNLUK_MYSQL();
    public KUR_MSSQL kUR_MSSQL = new KUR_MSSQL();
    public KUR_MYSQL kUR_MYSQL = new KUR_MYSQL();
    public SMS_MSSQL sMS_MSSQL = new SMS_MSSQL();
    public SMS_MYSQL sMS_MYSQL = new SMS_MYSQL();
    public String[] options = {"Tamam......       		!", "Vazgec.....       		!"}; 
    public BufferedImage kam_resmi ;
    public static Color gridcolor ; //=  new Color(0, 191, 255);      //   0,153,204   java.awt.Color[r=204,g=0,b=102]
    public int txt_yukseklik =20;
 }

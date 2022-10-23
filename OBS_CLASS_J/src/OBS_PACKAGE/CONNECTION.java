package OBS_PACKAGE;

import java.sql.Connection;
import java.sql.SQLException;

public class CONNECTION {
	public static Connection kurConn ;
    public static DIZIN_BILGILERI kurdizinbilgi = new DIZIN_BILGILERI();  // Kur
    public static Connection cariConn  ;
    public static DIZIN_BILGILERI caridizinbilgi = new DIZIN_BILGILERI();  // Cari
    public static Connection fatConn ;
    public static DIZIN_BILGILERI fatdizinbilgi = new DIZIN_BILGILERI();  // Fatura
    public static Connection adrConn ;
    public static DIZIN_BILGILERI adrdizinbilgi = new DIZIN_BILGILERI();  // Adres
    public static Connection gunConn ;
    public static DIZIN_BILGILERI gundizinbilgi = new DIZIN_BILGILERI();  // Gunluk
    public static Connection kamConn ;
    public static DIZIN_BILGILERI kamdizinbilgi = new DIZIN_BILGILERI();  // Kambiyo
    public static Connection smsConn ;
    public static DIZIN_BILGILERI smsdizinbilgi = new DIZIN_BILGILERI();  // Kambiyo
    public static Connection fihAConn ;
    public static Connection fihConn ;
    public static DIZIN_BILGILERI fihdizinbilgi = new DIZIN_BILGILERI();  // Fihrist

    public CONNECTION(String user, String prog) throws ClassNotFoundException, SQLException //Kurucu Metot
    {
        @SuppressWarnings("unused")
		CALISMA_DIZINI cdzn = new CALISMA_DIZINI(user,prog);
        if (new String(prog.toString()).equals("Kur") == true) 
        {
            kurdizinbilgi = CALISMA_DIZINI.kcdb;
        }
        else if (new String(prog.toString()).equals("Cari Hesap") == true) 
        {
            caridizinbilgi = CALISMA_DIZINI.ccdb;
        }
        else if (new String(prog.toString()).equals("Fatura") == true) 
        {
            fatdizinbilgi = CALISMA_DIZINI.fcdb;
        }
        else if (new String(prog.toString()).equals("Adres") == true) 
        {
            adrdizinbilgi = CALISMA_DIZINI.acdb;
        }
        else if (new String(prog.toString()).equals("Gunluk") == true) 
        {
            gundizinbilgi = CALISMA_DIZINI.gcdb;
        }
        else if (new String(prog.toString()).equals("Kambiyo") == true) 
        {
            kamdizinbilgi = CALISMA_DIZINI.kacdb;
        }
        else if (new String(prog.toString()).equals("Sms") == true) 
        {
            smsdizinbilgi = CALISMA_DIZINI.smscdb;
        }
        else if (new String(prog.toString()).equals("Fihrist") == true) 
        {
            fihdizinbilgi = CALISMA_DIZINI.ficdb;
            if (CALISMA_DIZINI.ficdb.han_sql.toString() == "SQL")
            {
               // fihConn = DriverManager.getConnection("jdbc:sqlserver://" + CALISMA_DIZINI.ficdb.conn_str.toString() ,CALISMA_DIZINI.ficdb.kullanici,CALISMA_DIZINI.ficdb.sifresi);
                //fihAConn = null;
            }
            else if (CALISMA_DIZINI.ficdb.han_sql.toString() == "Access")
            {
           //     fihAconn = new OleDbConnection(CALISMA_DIZINI.ficdb.conn_str.toString());
           //     fihConn = null;
            }
        }

    }

}

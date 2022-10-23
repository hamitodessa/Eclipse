package OBS_C_2025;

import java.sql.Connection;
import java.sql.SQLException;

public class BAGLAN {
	
	public static Connection kurConn ;
    public static DIZIN_BILGILERI kurDizin = new DIZIN_BILGILERI();  // Kur
    public static Connection cariConn  ;
    public static DIZIN_BILGILERI cariDizin = new DIZIN_BILGILERI();  // Cari
    public static Connection fatConn ;
    public static DIZIN_BILGILERI fatDizin = new DIZIN_BILGILERI();  // Fatura
    public static Connection adrConn ;
    public static DIZIN_BILGILERI adrDizin = new DIZIN_BILGILERI();  // Adres
    public static Connection gunConn ;
    public static DIZIN_BILGILERI gunDizin = new DIZIN_BILGILERI();  // Gunluk
    public static Connection kamConn ;
    public static DIZIN_BILGILERI kamDizin = new DIZIN_BILGILERI();  // Kambiyo
    public static Connection smsConn ;
    public static DIZIN_BILGILERI smsDizin = new DIZIN_BILGILERI();  // SMS
    public static Connection fihAConn ;
    public static Connection fihConn ;
    public static DIZIN_BILGILERI fihDizin = new DIZIN_BILGILERI();  // Fihrist
    
    public  void Connect(String uSER) throws ClassNotFoundException, SQLException
    {
    	BILGI_OKU b_OKU = new BILGI_OKU();
    	
    	b_OKU.bILGI_OKU(uSER, "Kur",kurDizin,"OK_Kur");
    	b_OKU.bILGI_OKU(uSER, "Cari Hesap",cariDizin,"OK_Car");
    	b_OKU.bILGI_OKU(uSER, "Fatura",fatDizin,"OK_Fat");
    	b_OKU.bILGI_OKU(uSER, "Adres",adrDizin,"OK_Adr");
    	b_OKU.bILGI_OKU(uSER, "Gunluk",gunDizin,"OK_Gun");
    	b_OKU.bILGI_OKU(uSER, "Kambiyo",kamDizin,"OK_Kam");
    	b_OKU.bILGI_OKU(uSER, "Fihrist",fihDizin,"OK_Fih");
    	b_OKU.bILGI_OKU(uSER, "Sms",smsDizin,"OK_Sms");
    }
   

}

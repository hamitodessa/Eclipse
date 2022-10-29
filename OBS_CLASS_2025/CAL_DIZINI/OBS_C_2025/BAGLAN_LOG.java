package OBS_C_2025;

import java.sql.Connection;

public class BAGLAN_LOG {
	public static Connection cariConn  ;
    public static DIZIN_BILGILERI cariLogDizin = new DIZIN_BILGILERI();  // Cari

    public void baglan()
    {
    	BILGI_OKU_LOG b_OKU = new BILGI_OKU_LOG();
    	
    	
    	b_OKU.bILGI_OKU(BAGLAN.cariDizin,"OK_Car");
    }
}

package OBS_C_2025;



public class BAGLAN_LOG {
	
    public static DIZIN_BILGILERI cariLogDizin = new DIZIN_BILGILERI();  // Cari

    public void cONNECT()
    {
    	BILGI_OKU_LOG b_OKU = new BILGI_OKU_LOG();
    	b_OKU.bILGI_OKU(cariLogDizin,"OK_Car", BAGLAN.cariDizin);
    	
    }
}

package OBS_C_2025;



public class BAGLAN_LOG {
	
    public static DIZIN_BILGILERI cariLogDizin = new DIZIN_BILGILERI();  // Cari
    public static DIZIN_BILGILERI kurLogDizin = new DIZIN_BILGILERI();  // Kur
    public static DIZIN_BILGILERI fatLogDizin = new DIZIN_BILGILERI();  // Fatura
    public static DIZIN_BILGILERI adrLogDizin = new DIZIN_BILGILERI();  // Adres
    public static DIZIN_BILGILERI gunLogDizin = new DIZIN_BILGILERI();  // Gunluj
    public static DIZIN_BILGILERI kamLogDizin = new DIZIN_BILGILERI();  // Kambiyo
    public static DIZIN_BILGILERI smsLogDizin = new DIZIN_BILGILERI();  // Sms
    
    
    public void cONNECT()
    {
    	BILGI_OKU_LOG b_OKU = new BILGI_OKU_LOG();
    	b_OKU.bILGI_OKU(cariLogDizin,"OK_Car", BAGLAN.cariDizin);
    	b_OKU.bILGI_OKU(kurLogDizin,"OK_Kur", BAGLAN.kurDizin);
     	b_OKU.bILGI_OKU(fatLogDizin,"OK_Fat", BAGLAN.fatDizin);
    	b_OKU.bILGI_OKU(adrLogDizin,"OK_Adr", BAGLAN.adrDizin);
    	b_OKU.bILGI_OKU(gunLogDizin,"OK_Gun", BAGLAN.gunDizin);
    	b_OKU.bILGI_OKU(kamLogDizin,"OK_Kam", BAGLAN.kamDizin);
    	b_OKU.bILGI_OKU(smsLogDizin,"OK_Sms", BAGLAN.smsDizin);
    }
}

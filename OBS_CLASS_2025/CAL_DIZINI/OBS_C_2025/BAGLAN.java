package OBS_C_2025;

public class BAGLAN {
	
    public static DIZIN_BILGILERI kurDizin = new DIZIN_BILGILERI();  // Kur
    public static DIZIN_BILGILERI cariDizin = new DIZIN_BILGILERI(); // Cari
    public static DIZIN_BILGILERI fatDizin = new DIZIN_BILGILERI();  // Fatura
    public static DIZIN_BILGILERI adrDizin = new DIZIN_BILGILERI();  // Adres
    public static DIZIN_BILGILERI gunDizin = new DIZIN_BILGILERI();  // Gunluk
    public static DIZIN_BILGILERI kamDizin = new DIZIN_BILGILERI();  // Kambiyo
    public static DIZIN_BILGILERI smsDizin = new DIZIN_BILGILERI();  // SMS
    public static DIZIN_BILGILERI kerDizin = new DIZIN_BILGILERI();  // Kereste
    public static DIZIN_BILGILERI fihDizin = new DIZIN_BILGILERI();  // Fihrist
    
    public  void cONNECT(String uSER) 
    {
    	BILGI_OKU b_OKU = new BILGI_OKU();
    	try {
			b_OKU.bILGI_OKU(uSER, "Kur",kurDizin,"OK_Kur");
			b_OKU.bILGI_OKU(uSER, "Cari Hesap",cariDizin,"OK_Car");
	    	b_OKU.bILGI_OKU(uSER, "Fatura",fatDizin,"OK_Fat");
	    	b_OKU.bILGI_OKU(uSER, "Adres",adrDizin,"OK_Adr");
	    	b_OKU.bILGI_OKU(uSER, "Gunluk",gunDizin,"OK_Gun");
	    	b_OKU.bILGI_OKU(uSER, "Kambiyo",kamDizin,"OK_Kam");
	    	b_OKU.bILGI_OKU(uSER, "Sms",smsDizin,"OK_Sms");
	    	b_OKU.bILGI_OKU(uSER, "Kereste",kerDizin,"OK_Ker");
	    	b_OKU.bILGI_OKU(uSER, "Fihrist",fihDizin,"OK_Fih");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

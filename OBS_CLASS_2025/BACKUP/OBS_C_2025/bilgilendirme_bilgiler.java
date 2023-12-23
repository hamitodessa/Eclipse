package OBS_C_2025;

public class bilgilendirme_bilgiler {
	String EMIR_ISMI ;
	boolean DURUM ;
	boolean GONDERILDIGINDE ;
	boolean HATA_DURUMUNDA ;
	String GON_ISIM ;
	String GON_HESAP ;
	String ALICI ;
	String KONU ;
	String SMTP ;
	String SMTP_PORT ;
	String KULLANICI ;
	String SIFRE ;
	boolean SSL ;
	boolean TSL ;
	public bilgilendirme_bilgiler(String eMIR_ISMI, boolean dURUM, boolean gONDERILDIGINDE, boolean hATA_DURUMUNDA,
			String gON_ISIM, String gON_HESAP, String aLICI, String kONU, String sMTP, String sMTP_PORT,
			String kULLANICI, String sIFRE, boolean sSL, boolean tSL) {
		super();
		EMIR_ISMI = eMIR_ISMI;
		DURUM = dURUM;
		GONDERILDIGINDE = gONDERILDIGINDE;
		HATA_DURUMUNDA = hATA_DURUMUNDA;
		GON_ISIM = gON_ISIM;
		GON_HESAP = gON_HESAP;
		ALICI = aLICI;
		KONU = kONU;
		SMTP = sMTP;
		SMTP_PORT = sMTP_PORT;
		KULLANICI = kULLANICI;
		SIFRE = sIFRE;
		SSL = sSL;
		TSL = tSL;
	}
	public String getEMIR_ISMI() {
		return EMIR_ISMI;
	}
	public void setEMIR_ISMI(String eMIR_ISMI) {
		EMIR_ISMI = eMIR_ISMI;
	}
	public boolean isDURUM() {
		return DURUM;
	}
	public void setDURUM(boolean dURUM) {
		DURUM = dURUM;
	}
	public boolean isGONDERILDIGINDE() {
		return GONDERILDIGINDE;
	}
	public void setGONDERILDIGINDE(boolean gONDERILDIGINDE) {
		GONDERILDIGINDE = gONDERILDIGINDE;
	}
	public boolean isHATA_DURUMUNDA() {
		return HATA_DURUMUNDA;
	}
	public void setHATA_DURUMUNDA(boolean hATA_DURUMUNDA) {
		HATA_DURUMUNDA = hATA_DURUMUNDA;
	}
	public String getGON_ISIM() {
		return GON_ISIM;
	}
	public void setGON_ISIM(String gON_ISIM) {
		GON_ISIM = gON_ISIM;
	}
	public String getGON_HESAP() {
		return GON_HESAP;
	}
	public void setGON_HESAP(String gON_HESAP) {
		GON_HESAP = gON_HESAP;
	}
	public String getALICI() {
		return ALICI;
	}
	public void setALICI(String aLICI) {
		ALICI = aLICI;
	}
	public String getKONU() {
		return KONU;
	}
	public void setKONU(String kONU) {
		KONU = kONU;
	}
	public String getSMTP() {
		return SMTP;
	}
	public void setSMTP(String sMTP) {
		SMTP = sMTP;
	}
	public String getSMTP_PORT() {
		return SMTP_PORT;
	}
	public void setSMTP_PORT(String sMTP_PORT) {
		SMTP_PORT = sMTP_PORT;
	}
	public String getKULLANICI() {
		return KULLANICI;
	}
	public void setKULLANICI(String kULLANICI) {
		KULLANICI = kULLANICI;
	}
	public String getSIFRE() {
		return SIFRE;
	}
	public void setSIFRE(String sIFRE) {
		SIFRE = sIFRE;
	}
	public boolean isSSL() {
		return SSL;
	}
	public void setSSL(boolean sSL) {
		SSL = sSL;
	}
	public boolean isTSL() {
		return TSL;
	}
	public void setTSL(boolean tSL) {
		TSL = tSL;
	}
	
}

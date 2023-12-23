package OBS_C_2025;

public class ftp_bilgiler {
	String EMIR_ISMI ;
	String NERESI ;
	String HOST ;
	String KULLANICI ;
	String SIFRE ;
	String SURUCU ;
	String PORT ;
	String ZMN_ASIMI ;
	String ESKI_YEDEK ;
	String SURUCU_YER ;
	public ftp_bilgiler(String eMIR_ISMI, String nERESI, String hOST, String kULLANICI, String sIFRE, String sURUCU,
			String pORT, String zMN_ASIMI, String eSKI_YEDEK, String sURUCU_YER) {
		super();
		EMIR_ISMI = eMIR_ISMI;
		NERESI = nERESI;
		HOST = hOST;
		KULLANICI = kULLANICI;
		SIFRE = sIFRE;
		SURUCU = sURUCU;
		PORT = pORT;
		ZMN_ASIMI = zMN_ASIMI;
		ESKI_YEDEK = eSKI_YEDEK;
		SURUCU_YER = sURUCU_YER;
	}
	public String getEMIR_ISMI() {
		return EMIR_ISMI;
	}
	public void setEMIR_ISMI(String eMIR_ISMI) {
		EMIR_ISMI = eMIR_ISMI;
	}
	public String getNERESI() {
		return NERESI;
	}
	public void setNERESI(String nERESI) {
		NERESI = nERESI;
	}
	public String getHOST() {
		return HOST;
	}
	public void setHOST(String hOST) {
		HOST = hOST;
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
	public String getSURUCU() {
		return SURUCU;
	}
	public void setSURUCU(String sURUCU) {
		SURUCU = sURUCU;
	}
	public String getPORT() {
		return PORT;
	}
	public void setPORT(String pORT) {
		PORT = pORT;
	}
	public String getZMN_ASIMI() {
		return ZMN_ASIMI;
	}
	public void setZMN_ASIMI(String zMN_ASIMI) {
		ZMN_ASIMI = zMN_ASIMI;
	}
	public String getESKI_YEDEK() {
		return ESKI_YEDEK;
	}
	public void setESKI_YEDEK(String eSKI_YEDEK) {
		ESKI_YEDEK = eSKI_YEDEK;
	}
	public String getSURUCU_YER() {
		return SURUCU_YER;
	}
	public void setSURUCU_YER(String sURUCU_YER) {
		SURUCU_YER = sURUCU_YER;
	}
	
}

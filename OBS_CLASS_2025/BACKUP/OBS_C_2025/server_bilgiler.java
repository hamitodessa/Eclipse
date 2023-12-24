package OBS_C_2025;

public class server_bilgiler {
	String EMIR_ISMI ;
	String HANGI_SQL ;
	String INSTANCE ;
	boolean WIN ;
	boolean SERV ;
	String KULLANICI ;
	String SIFRE ;
	String PORT;
	String MY_DUMP ;
	public server_bilgiler(String eMIR_ISMI, String hANGI_SQL, String iNSTANCE, boolean wIN, boolean sERV,
			String kULLANICI, String sIFRE, String pORT, String mY_DUMP) {
		super();
		EMIR_ISMI = eMIR_ISMI;
		HANGI_SQL = hANGI_SQL;
		INSTANCE = iNSTANCE;
		WIN = wIN;
		SERV = sERV;
		KULLANICI = kULLANICI;
		SIFRE = sIFRE;
		PORT = pORT;
		MY_DUMP = mY_DUMP;
	}
	public String getEMIR_ISMI() {
		return EMIR_ISMI;
	}
	public void setEMIR_ISMI(String eMIR_ISMI) {
		EMIR_ISMI = eMIR_ISMI;
	}
	public String getHANGI_SQL() {
		return HANGI_SQL;
	}
	public void setHANGI_SQL(String hANGI_SQL) {
		HANGI_SQL = hANGI_SQL;
	}
	public String getINSTANCE() {
		return INSTANCE;
	}
	public void setINSTANCE(String iNSTANCE) {
		INSTANCE = iNSTANCE;
	}
	public boolean isWIN() {
		return WIN;
	}
	public void setWIN(boolean wIN) {
		WIN = wIN;
	}
	public boolean isSERV() {
		return SERV;
	}
	public void setSERV(boolean sERV) {
		SERV = sERV;
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
	public String getPORT() {
		return PORT;
	}
	public void setPORT(String pORT) {
		PORT = pORT;
	}
	public String getMY_DUMP() {
		return MY_DUMP;
	}
	public void setMY_DUMP(String mY_DUMP) {
		MY_DUMP = mY_DUMP;
	}

}

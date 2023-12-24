package OBS_C_2025;

import java.util.Date;

public class emir_bilgiler {
	String EMIR_ISMI ;
	boolean DURUM ;
	String EMIR_ACIKLAMA ;
	String INSTANCE ;
	boolean SON_DURUM ;
	Date SON_YUKLEME ;
	boolean SQL_YEDEK ;
	String MESAJ ;
	Date OLUSTURMA ;
	public emir_bilgiler(String eMIR_ISMI, boolean dURUM, String eMIR_ACIKLAMA, String iNSTANCE, boolean sON_DURUM,
			Date sON_YUKLEME, boolean sQL_YEDEK, String mESAJ, Date oLUSTURMA) {
		
		EMIR_ISMI = eMIR_ISMI;
		DURUM = dURUM;
		EMIR_ACIKLAMA = eMIR_ACIKLAMA;
		INSTANCE = iNSTANCE;
		SON_DURUM = sON_DURUM;
		SON_YUKLEME = sON_YUKLEME;
		SQL_YEDEK = sQL_YEDEK;
		MESAJ = mESAJ;
		OLUSTURMA = oLUSTURMA;
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
	public String getEMIR_ACIKLAMA() {
		return EMIR_ACIKLAMA;
	}
	public void setEMIR_ACIKLAMA(String eMIR_ACIKLAMA) {
		EMIR_ACIKLAMA = eMIR_ACIKLAMA;
	}
	public String getINSTANCE() {
		return INSTANCE;
	}
	public void setINSTANCE(String iNSTANCE) {
		INSTANCE = iNSTANCE;
	}
	public boolean isSON_DURUM() {
		return SON_DURUM;
	}
	public void setSON_DURUM(boolean sON_DURUM) {
		SON_DURUM = sON_DURUM;
	}
	public Date getSON_YUKLEME() {
		return SON_YUKLEME;
	}
	public void setSON_YUKLEME(Date sON_YUKLEME) {

		SON_YUKLEME = sON_YUKLEME;
	}
	public boolean isSQL_YEDEK() {
		return SQL_YEDEK;
	}
	public void setSQL_YEDEK(boolean sQL_YEDEK) {
		SQL_YEDEK = sQL_YEDEK;
	}
	public String getMESAJ() {
		return MESAJ;
	}
	public void setMESAJ(String mESAJ) {
		MESAJ = mESAJ;
	}
	public Date getOLUSTURMA() {
		return OLUSTURMA;
	}
	public void setOLUSTURMA(Date oLUSTURMA) {
		OLUSTURMA = oLUSTURMA;
	}
	
}

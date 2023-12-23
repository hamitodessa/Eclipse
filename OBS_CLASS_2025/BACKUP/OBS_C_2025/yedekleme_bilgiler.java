package OBS_C_2025;

import java.util.Date;

public class yedekleme_bilgiler {
	String EMIR_ISMI ;
	String SAAT ;
	boolean P_TESI ;
	boolean SALI ;
	boolean CARS ;
	boolean PERS ;
	boolean CUMA ;
	boolean C_TESI ;
	boolean PAZAR ;
	Date BASLAMA ;
	Date BITIS ;
	public yedekleme_bilgiler(String eMIR_ISMI, String sAAT, boolean p_TESI, boolean sALI, boolean cARS, boolean pERS,
			boolean cUMA, boolean c_TESI, boolean pAZAR, Date bASLAMA, Date bITIS) {
		super();
		EMIR_ISMI = eMIR_ISMI;
		SAAT = sAAT;
		P_TESI = p_TESI;
		SALI = sALI;
		CARS = cARS;
		PERS = pERS;
		CUMA = cUMA;
		C_TESI = c_TESI;
		PAZAR = pAZAR;
		BASLAMA = bASLAMA;
		BITIS = bITIS;
	}
	public String getEMIR_ISMI() {
		return EMIR_ISMI;
	}
	public void setEMIR_ISMI(String eMIR_ISMI) {
		EMIR_ISMI = eMIR_ISMI;
	}
	public String getSAAT() {
		return SAAT;
	}
	public void setSAAT(String sAAT) {
		SAAT = sAAT;
	}
	public boolean isP_TESI() {
		return P_TESI;
	}
	public void setP_TESI(boolean p_TESI) {
		P_TESI = p_TESI;
	}
	public boolean isSALI() {
		return SALI;
	}
	public void setSALI(boolean sALI) {
		SALI = sALI;
	}
	public boolean isCARS() {
		return CARS;
	}
	public void setCARS(boolean cARS) {
		CARS = cARS;
	}
	public boolean isPERS() {
		return PERS;
	}
	public void setPERS(boolean pERS) {
		PERS = pERS;
	}
	public boolean isCUMA() {
		return CUMA;
	}
	public void setCUMA(boolean cUMA) {
		CUMA = cUMA;
	}
	public boolean isC_TESI() {
		return C_TESI;
	}
	public void setC_TESI(boolean c_TESI) {
		C_TESI = c_TESI;
	}
	public boolean isPAZAR() {
		return PAZAR;
	}
	public void setPAZAR(boolean pAZAR) {
		PAZAR = pAZAR;
	}
	public Date getBASLAMA() {
		return BASLAMA;
	}
	public void setBASLAMA(Date bASLAMA) {
		BASLAMA = bASLAMA;
	}
	public Date getBITIS() {
		return BITIS;
	}
	public void setBITIS(Date bITIS) {
		BITIS = bITIS;
	}
	
}

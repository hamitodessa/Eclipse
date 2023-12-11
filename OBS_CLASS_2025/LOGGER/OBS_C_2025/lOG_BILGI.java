package OBS_C_2025;

public class lOG_BILGI {
	private String mODUL;
	private String mESAJ;
	private String eVRAK;
	public String getmODUL() {
		return mODUL;
	}
	public void setmODUL(String mODUL) {
		this.mODUL = mODUL;
	}
	public String getmESAJ() {
		return mESAJ;
	}
	public void setmESAJ(String mESAJ) {
		this.mESAJ = mESAJ.replace("\n"," "); // Mesaj icinde Enter varsa Kaldirma
	}
	public String geteVRAK() {
		return eVRAK;
	}
	public void seteVRAK(String eVRAK) {
		this.eVRAK = eVRAK;
	}
}

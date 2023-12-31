package OBS_C_2025;

import java.util.Date;

public class g_bilgiler {
	String kur;
	Date baslangicDate;
	String obs_kullanici;
	boolean durum;
	public g_bilgiler(String kur, Date baslangicDate, String obs_kullanici, boolean durum) {
		super();
		this.kur = kur;
		this.baslangicDate = baslangicDate;
		this.obs_kullanici = obs_kullanici;
		this.durum = durum;
	}
	public String getKur() {
		return kur;
	}
	public void setKur(String kur) {
		this.kur = kur;
	}
	public Date getBaslangicDate() {
		return baslangicDate;
	}
	public void setBaslangicDate(Date baslangicDate) {
		this.baslangicDate = baslangicDate;
	}
	public String getObs_kullanici() {
		return obs_kullanici;
	}
	public void setObs_kullanici(String obs_kullanici) {
		this.obs_kullanici = obs_kullanici;
	}
	public boolean isDurum() {
		return durum;
	}
	public void setDurum(boolean durum) {
		this.durum = durum;
	}

	
}

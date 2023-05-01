package OBS_2025;

public class ETIKET_ISIM {

	private String adi ;
	private String adres1;
	private String adres2;
	private String semt;
	private String sehir;
	
	public ETIKET_ISIM(String adi, String adres1, String adres2, String semt, String sehir) {
		this.adi = adi;
		this.adres1 = adres1;
		this.adres2 = adres2;
		this.semt = semt;
		this.sehir = sehir;
	}
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	public String getAdres1() {
		return adres1;
	}
	public void setAdres1(String adres1) {
		this.adres1 = adres1;
	}
	public String getAdres2() {
		return adres2;
	}
	public void setAdres2(String adres2) {
		this.adres2 = adres2;
	}
	public String getSemt() {
		return semt;
	}
	public void setSemt(String semt) {
		this.semt = semt;
	}
	public String getSehir() {
		return sehir;
	}
	public void setSehir(String sehir) {
		this.sehir = sehir;
	}
	

}

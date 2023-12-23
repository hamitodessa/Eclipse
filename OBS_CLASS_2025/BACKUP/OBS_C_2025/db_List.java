package OBS_C_2025;

public class db_List {
	private String adi ;
	private String path;
	
	public db_List(String adi, String path) {
		this.adi = adi;
		this.path = path;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	

}

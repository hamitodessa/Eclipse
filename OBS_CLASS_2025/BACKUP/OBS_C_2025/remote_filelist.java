package OBS_C_2025;

public class remote_filelist {

	String dosyaADI ;
	int fileSIZE;
	String taRIH;
	String filePATH;
	public remote_filelist(String dosyaADI, int fileSIZE, String taRIH, String filePATH) {
		super();
		this.dosyaADI = dosyaADI;
		this.fileSIZE = fileSIZE;
		this.taRIH = taRIH;
		this.filePATH = filePATH;
	}
	public String getDosyaADI() {
		return dosyaADI;
	}
	public void setDosyaADI(String dosyaADI) {
		this.dosyaADI = dosyaADI;
	}
	public int getFileSIZE() {
		return fileSIZE;
	}
	public void setFileSIZE(int fileSIZE) {
		this.fileSIZE = fileSIZE;
	}
	public String getTaRIH() {
		return taRIH;
	}
	public void setTaRIH(String taRIH) {
		this.taRIH = taRIH;
	}
	public String getFilePATH() {
		return filePATH;
	}
	public void setFilePATH(String filePATH) {
		this.filePATH = filePATH;
	}
	
	}

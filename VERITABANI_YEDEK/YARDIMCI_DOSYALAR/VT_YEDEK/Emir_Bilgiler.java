package VT_YEDEK;

public class Emir_Bilgiler {

	private String emirAdi ;
    private String sondurUM;
    private int dbSAYI ;
    private String tarIH ;
    private String xxXX ;
    private String acikLAMA ;
    private String durUM;
    private String ftpNERESI ;

    public String getEmirAdi() {
		return emirAdi;
	}

	public void setEmirAdi(String emirAdi) {
		this.emirAdi = emirAdi;
	}

	public String getSondurUM() {
		return sondurUM;
	}

	public void setSondurUM(String sondurUM) {
		this.sondurUM = sondurUM;
	}

	public int getDbSAYI() {
		return dbSAYI;
	}

	public void setDbSAYI(int dbSAYI) {
		this.dbSAYI = dbSAYI;
	}

	public String getTarIH() {
		return tarIH;
	}

	public void setTarIH(String tarIH) {
		this.tarIH = tarIH;
	}

	public String getXxXX() {
		return xxXX;
	}

	public void setXxXX(String xxXX) {
		this.xxXX = xxXX;
	}

	public String getAcikLAMA() {
		return acikLAMA;
	}

	public void setAcikLAMA(String acikLAMA) {
		this.acikLAMA = acikLAMA;
	}

	public String getDurUM() {
		return durUM;
	}

	public void setDurUM(String durUM) {
		this.durUM = durUM;
	}

	public String getFtpNERESI() {
		return ftpNERESI;
	}

	public void setFtpNERESI(String ftpNERESI) {
		this.ftpNERESI = ftpNERESI;
	}

	public Emir_Bilgiler(String  emirAdi, String sondurUM, int dbSAYI,String tarIH,String xxXX,String acikLAMA,String durUM,String ftpNERESI) {
        this.emirAdi = emirAdi;
        this.sondurUM = sondurUM;
        this.dbSAYI = dbSAYI;
        this.tarIH = tarIH;
        this.xxXX = xxXX;
        this.acikLAMA = acikLAMA;
        this.durUM = durUM;
        this.ftpNERESI  = ftpNERESI;
    }
}

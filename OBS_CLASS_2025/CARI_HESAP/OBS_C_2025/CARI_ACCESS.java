package OBS_C_2025;


import java.sql.SQLException;

public class CARI_ACCESS {
	private static  ICARI_HESAP _ICari;
	public CARI_ACCESS(ICARI_HESAP _ICari)
	{
		this._ICari = _ICari;
	}
	public static void baglan() throws SQLException
	
	{
	
	 _ICari.baglan();
	}
	public String cari_firma_adi() throws ClassNotFoundException, SQLException {
		return  _ICari.cari_firma_adi();
		
	}
	public void cari_sifirdan_L (String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,
			String sifre) throws ClassNotFoundException, SQLException 
	{
		 _ICari.cari_sifirdan_L( kod,dizin_yeri, dizin,  fir_adi, ins, kull, sifre);
	}
	public void cARI_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi) throws ClassNotFoundException, SQLException
	{
		 _ICari.cARI_SIFIR_S(server, kull, sifre, kod, fir_adi, ins);
	}
}

package OBS_C_2025;

import java.sql.SQLException;

public class STOK_ACCESS {
	private static  ISTOK _IStok;
	public STOK_ACCESS(ISTOK _IStok)
	{
		this._IStok = _IStok;
	}
	
	public static void baglan() throws SQLException
	{
	
		_IStok.baglan();
	}
	public void fAT_SIFIR_L (String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,
			String sifre) throws ClassNotFoundException, SQLException 
	{
		 _IStok.fAT_SIFIR_L( kod,dizin_yeri, dizin,  fir_adi, ins, kull, sifre);
	}
	public void fAT_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi) throws ClassNotFoundException, SQLException
	{
		_IStok.fAT_SIFIR_S(server, ins, kull, sifre, kod, fir_adi);
	}
}

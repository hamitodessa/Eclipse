package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;

public class STOK_ACCESS {
	private static  ISTOK _IStok;
	private static ILOGGER[] _Logger;

	public STOK_ACCESS(ISTOK _IStok, ILOGGER[] _Logger)
	{
		this._IStok = _IStok;
		this._Logger = _Logger;;
	}
	
	public static void baglan() throws SQLException
	{
	
		_IStok.baglan();
	}
	public void fAT_SIFIR_L (String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,
			String sifre, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException 
	{
		 _IStok.fAT_SIFIR_L( kod,dizin_yeri, dizin,  fir_adi, ins, kull, sifre);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
			// _Logger.Logla(mesaj);

	}
	public void fAT_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IStok.fAT_SIFIR_S(server, ins, kull, sifre, kod, fir_adi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public ResultSet envanter_rapor_u_kodu(String t1 , String t2 , String k1 ,String k2 , String f1 ,String f2,
			String m1 ,String m2 ,String anagrup,String altgrup ,String depo, String depohar ,String ure,
			String uanagrp,String ualtgrp) throws ClassNotFoundException, SQLException
	{
		return _IStok.envanter_rapor_u_kodu(t1, t2, k1, k2, f1, f2, m1, m2, anagrup, altgrup, depo, depohar, ure, uanagrp, ualtgrp);
	}
	public ResultSet fatura_oku_printer(String fno,String cins)
	{
		
	}
}

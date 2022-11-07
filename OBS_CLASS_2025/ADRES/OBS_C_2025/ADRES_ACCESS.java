package OBS_C_2025;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ADRES_ACCESS {

	private static IADRES  _IAdres;
	private static ILOGGER[] _Logger;
	
	public ADRES_ACCESS(IADRES _IAdres, ILOGGER[] _Logger)
	{
		this._IAdres = _IAdres;
		this._Logger = _Logger;
	}
	public static void baglan() throws SQLException
	{
	_IAdres.baglan();
	}
	public void aDR_SIF_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,String sifre
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IAdres.aDR_SIF_L( kod,dizin_yeri,  dizin,  fir_adi,  ins,  kull, sifre);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void aDR_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IAdres.aDR_SIFIR_S(server, ins, kull, sifre, kod, fir_adi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public String[] adres_oku (String kodu) throws ClassNotFoundException, SQLException 
	{
		return _IAdres.adres_oku(kodu);
	}
	public String kod_ismi(String kodu) throws ClassNotFoundException, SQLException
	{
		return _IAdres.kod_ismi(kodu);
	}
	public ResultSet adres(String sira,String arama) throws ClassNotFoundException, SQLException 
	{
		return _IAdres.adres(sira, arama);
	}
	public void sil(String kod ,String adi, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IAdres.sil(kod, adi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void adres_kayit(String kodu ,String adi ,String adr1 ,String adr2 ,String semt,String sehir  , String vd , String vn ,
			String fax, String tel1 ,String tel2 ,String ozel ,String yet ,String e_ma,String n1 ,String n2 ,String n3 ,  InputStream  resim   
			,String tel3 ,String acik   ,boolean sms  ,boolean mailg,String ok1 ,String ok2,
			String web ,String pkodu ,String usr
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException, IOException
	{
		_IAdres.adres_kayit(kodu, adi, adr1, adr2, semt, sehir, vd, vn, fax, tel1, tel2, ozel, yet, e_ma, n1, n2, n3, resim, 
				tel3, acik, sms, mailg, ok1, ok2, web, pkodu, usr);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public ResultSet adr_hpl() throws ClassNotFoundException, SQLException
	{
		return _IAdres.adr_hpl();
	}
	public ResultSet sms_adr_hpl(String nerden) throws ClassNotFoundException, SQLException
	{
		return _IAdres.sms_adr_hpl(nerden);
	}
}

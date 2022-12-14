package OBS_C_2025;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("static-access")
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
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI,String port) throws ClassNotFoundException, SQLException
	{
		_IAdres.aDR_SIF_L( kod,dizin_yeri,  dizin,  fir_adi,  ins,  kull, sifre, port);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla("Firma Adi:" + fir_adi,evrak, dBILGI);
	}
	public void aDR_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IAdres.aDR_SIFIR_S(server, ins, kull, sifre, kod, fir_adi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla("Firma Adi:" + fir_adi,evrak, dBILGI);
	}
	public void adr_firma_adi_kayit(String fadi	, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException 
	{
		_IAdres.adr_firma_adi_kayit(fadi);
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
	public void adres_kayit(ADRESS_DEGISKENLER aDEGIS
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException, IOException
	{
		_IAdres.adres_kayit(aDEGIS);
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
	public ResultSet gdy_oku(String kod) throws ClassNotFoundException, SQLException
	{
		return _IAdres.gdy_oku(kod);
	}
	public String adr_firma_adi() throws ClassNotFoundException, SQLException
	{
		return _IAdres.adr_firma_adi();
	}
}

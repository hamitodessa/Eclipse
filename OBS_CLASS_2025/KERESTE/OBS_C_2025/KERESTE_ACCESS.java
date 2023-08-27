package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;
@SuppressWarnings("static-access")
public class KERESTE_ACCESS {
	private static IKERESTE _IKereste;
	private static ILOGGER[] _Logger;
	
	public KERESTE_ACCESS(IKERESTE _IKereste, ILOGGER[] _Logger)
	{
		this._IKereste = _IKereste;
		this._Logger = _Logger;;
	}
	public static void baglan() throws SQLException, ClassNotFoundException
	{
		
		_IKereste.baglan();
	}
	public void kER_SIFIR_L (Server_Bilgi sbilgi, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException 
	{
		_IKereste.kER_SIFIR_L( sbilgi);
		for ( ILOGGER  _Logger : _Logger )
			_Logger.Logla(lBILGI, dBILGI);
		for ( ILOGGER  _Logger : _Logger )
		{
			lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
			_Logger.Logla(lBILGI, dBILGI);
		}
	}
	public void kER_SIFIR_S(Server_Bilgi sbilgi	, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKereste.kER_SIFIR_S(sbilgi);
		for ( ILOGGER  _Logger : _Logger )
			_Logger.Logla(lBILGI, dBILGI);
		for ( ILOGGER  _Logger : _Logger )
		{
			lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
			_Logger.Logla(lBILGI, dBILGI);
		}
	}
	public void ker_firma_adi_kayit(String fadi, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKereste.kER_firma_adi_kayit(fadi);
		for ( ILOGGER  _Logger : _Logger )
			_Logger.Logla(lBILGI, dBILGI);
	}
	public String ker_firma_adi() throws ClassNotFoundException, SQLException
	{
		return _IKereste.ker_firma_adi();
	}
	public ResultSet kod_pln () throws ClassNotFoundException, SQLException
	{
		return _IKereste.kod_pln();
	}
	public void kod_kayit(String kodu,String aciklama, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKereste.kod_kayit(kodu,  aciklama);
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(lBILGI, dBILGI);
	}
	public void kod_sil(String kod, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKereste.kod_sil(kod);
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(lBILGI, dBILGI);
	}
	public ResultSet kons_pln () throws ClassNotFoundException, SQLException
	{
		return _IKereste.kons_pln();
	}
	public void kons_kayit(String kons,String aciklama, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKereste.kons_kayit(kons,  aciklama);
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(lBILGI, dBILGI);
	}
	public void kons_sil(String kons, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKereste.kons_sil(kons);
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(lBILGI, dBILGI);
	}
}

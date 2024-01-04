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
	public void aDR_SIF_L(Server_Bilgi sbilgi, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IAdres.aDR_SIF_L(sbilgi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(lBILGI, dBILGI);
		 for ( ILOGGER  _Logger : _Logger )
		 {
			 lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
			  	_Logger.Logla(lBILGI, dBILGI);
		 }
	}
	public void aDR_SIFIR_S(Server_Bilgi sbilgi	,  lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IAdres.aDR_SIFIR_S(sbilgi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(lBILGI, dBILGI);
		 for ( ILOGGER  _Logger : _Logger )
		 {
			 lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
			  	_Logger.Logla(lBILGI, dBILGI);
		 }
	}
	public void adr_firma_adi_kayit(String fadi	, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException 
	{
		_IAdres.adr_firma_adi_kayit(fadi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(lBILGI, dBILGI);
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
	public void sil(String id, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IAdres.sil(id);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(lBILGI, dBILGI);
	}
	public void adres_kayit(ADRESS_DEGISKENLER aDEGIS
			, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException, IOException
	{
		_IAdres.adres_kayit(aDEGIS);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(lBILGI, dBILGI);
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
	public ResultSet adr_etiket(String siralama) throws ClassNotFoundException, SQLException
	{
		return _IAdres.adr_etiket(siralama);
	}
	public ResultSet adr_etiket_arama(String arama) throws ClassNotFoundException, SQLException
	{
		return _IAdres.adr_etiket_arama( arama);
	}
	public ResultSet kod_kontrol(String arama)throws ClassNotFoundException, SQLException
	{
		return _IAdres.kod_kontrol(arama);
	}
}

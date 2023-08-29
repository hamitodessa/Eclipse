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
	public String kod_adi(String kod) throws ClassNotFoundException, SQLException
	{
		return _IKereste.kod_adi(kod);
	}
	public ResultSet ker_kod_degisken_oku(String fieldd,String sno,String nerden) throws ClassNotFoundException, SQLException
	{
		return _IKereste.ker_kod_degisken_oku(fieldd, sno, nerden);
	}
	public ResultSet ker_kod_degisken_ara(String fieldd,String sno,String nerden,String arama) throws ClassNotFoundException, SQLException 
	{
		return _IKereste.ker_kod_degisken_ara(fieldd, sno, nerden, arama);
	}
	public ResultSet ker_kod_alt_grup_degisken_oku (int sno) throws ClassNotFoundException, SQLException 
	{
		return _IKereste.ker_kod_alt_grup_degisken_oku(sno);
	}
	public  boolean alt_grup_kontrol(int anagrp,int altgrp) throws ClassNotFoundException, SQLException
	{
		return _IKereste.alt_grup_kontrol(anagrp, altgrp);
	}
	public void ker_degisken_alt_grup_sil(int  ID , lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKereste.ker_degisken_alt_grup_sil(ID);
		for ( ILOGGER  _Logger : _Logger )
			_Logger.Logla(lBILGI, dBILGI);
	}
	public void ker_kod_degisken_sil(   String hangi_Y,String nerden  ,   int sira, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKereste.ker_kod_degisken_sil(nerden, hangi_Y, sira);
		for ( ILOGGER  _Logger : _Logger )
			_Logger.Logla(lBILGI, dBILGI);
	}
	public void ker_degisken_eski(String fieldd ,String degisken_adi ,String nerden ,String sno ,int ID ) throws ClassNotFoundException, SQLException 
	{
		_IKereste.ker_degisken_eski(fieldd, degisken_adi, nerden, sno, ID);
	}
	public void ker_degisken_kayit(String fieldd  ,String nerden,String degisken_adi,String sira) throws ClassNotFoundException, SQLException 
	{
		_IKereste.ker_degisken_kayit(fieldd, nerden, degisken_adi, sira);
	}
	public void  ker_degisken_alt_grup_kayit (String alt_grup , int ana_grup ) throws ClassNotFoundException, SQLException
	{
		_IKereste.ker_degisken_alt_grup_kayit(alt_grup, ana_grup);
	}
	public void ker_degisken_alt_grup_eski(String alt_grup ,int ana_grup ,int  ID ) throws ClassNotFoundException, SQLException
	{
		_IKereste.ker_degisken_alt_grup_eski(alt_grup, ana_grup, ID);
	}
	public ResultSet ker_oz_kod (String cins) throws ClassNotFoundException, SQLException
	{
		return _IKereste.ker_oz_kod(cins);
	}
	public String son_no_al(String cins) throws ClassNotFoundException, SQLException
	{
		return _IKereste.son_no_al(cins);
	}
	public void ker_giris_sil(String eno,lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException 
	{
		_IKereste.ker_giris_sil(eno);
		for ( ILOGGER  _Logger : _Logger )
			_Logger.Logla(lBILGI, dBILGI);
	}
	public void ker_kaydet(KER_BILGI kBILGI ,String usr
			, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKereste.ker_kaydet(kBILGI, usr);
		for ( ILOGGER  _Logger : _Logger )
			_Logger.Logla(lBILGI, dBILGI);
	}
}

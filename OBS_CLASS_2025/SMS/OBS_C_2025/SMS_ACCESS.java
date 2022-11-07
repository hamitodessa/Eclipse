package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SMS_ACCESS {
	private static ISMS  _ISms;
	private static ILOGGER[] _Logger;

	public SMS_ACCESS(ISMS _ISms, ILOGGER[] _Logger) {
		this._ISms = _ISms;
		this._Logger = _Logger;
	}

	public static void baglan() throws SQLException
	{
	_ISms.baglan();
	}
	public void sMS_SIFIR_L(String kod, String dizin_yeri, String dizin, String ins, String kull, String sifre
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException 
	{
		_ISms.sMS_SIFIR_L(kod, dizin_yeri, dizin, ins, kull, sifre);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void sMS_SIFIR_S(String server, String ins, String kull, String sifre, String kod
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_ISms.sMS_SIFIR_S(server, ins, kull, sifre, kod);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public boolean kod_ismi(String kodu) throws ClassNotFoundException, SQLException
	{
		return _ISms.kod_ismi(kodu);
	}
	public ResultSet mail_giris_bak() throws ClassNotFoundException, SQLException
	{
		return _ISms.mail_giris_bak();
	}
	public ResultSet mail_grup_bak() throws ClassNotFoundException, SQLException
	{
		return _ISms.mail_grup_bak();
	}
	public void mail_giris_sil(String mail) throws ClassNotFoundException, SQLException
	{
		_ISms.mail_giris_sil(mail);
	}
	public void mail_giris_yaz(String mail , String unv ,String grpkod ,String kod,boolean drm , String unm ) throws ClassNotFoundException, SQLException
	{
		_ISms.mail_giris_yaz(mail, unv, grpkod, kod, drm, unm);
	}
	public ResultSet mail_alici_doldur() throws ClassNotFoundException, SQLException
	{
		return _ISms.mail_alici_doldur();
	}
	public void giden_rapor_yaz(String uname,String tar ,String msj,String mail,String hsp, String unv,String gond,String konu) throws ClassNotFoundException, SQLException
	{
		_ISms.giden_rapor_yaz(uname, tar, msj, mail, hsp, unv, gond, konu);
	}
	public ResultSet mail_giden_bak(String user) throws ClassNotFoundException, SQLException
	{
		return _ISms.mail_giden_bak(user);
	}
	public ResultSet sms_giris_bak() throws ClassNotFoundException, SQLException
	{
		return _ISms.sms_giris_bak();
	}
	public ResultSet sms_grup_bak() throws ClassNotFoundException, SQLException
	{
		return _ISms.sms_grup_bak();
	}
	public void sms_giris_sil(String tel) throws ClassNotFoundException, SQLException
	{
		_ISms.sms_giris_sil(tel);
	}
	public void sms_giris_yaz(String tel , String unv ,String grpkod ,String kod,boolean drm , String unm ) throws ClassNotFoundException, SQLException
	{
		_ISms.sms_giris_yaz(tel, unv, grpkod, kod, drm, unm);
	}
	public ResultSet sms_alici_doldur() throws ClassNotFoundException, SQLException
	{
		return _ISms.sms_alici_doldur();
	}
	public void sms_yaz(String uname,String tar ,String msj,String mobile,String hsp, String unv) throws ClassNotFoundException, SQLException
	{
		_ISms.sms_yaz(uname, tar, msj, mobile, hsp, unv);
	}
	public ResultSet sms_giden_bak(String user) throws ClassNotFoundException, SQLException
	{
		return _ISms.sms_giden_bak(user);
	}
}

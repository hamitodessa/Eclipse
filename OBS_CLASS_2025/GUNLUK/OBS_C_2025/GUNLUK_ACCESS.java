package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
@SuppressWarnings("static-access")
public class GUNLUK_ACCESS {

	private static IGUNLUK  _IGunluk;
	private static ILOGGER[] _Logger;
	
	public GUNLUK_ACCESS(IGUNLUK _IGunluk, ILOGGER[] _Logger) {
		this._IGunluk = _IGunluk;
		this._Logger = _Logger;
		
	}
	
	public static void baglan() throws SQLException, ClassNotFoundException
	{
	_IGunluk.baglan();
	}
	public void gUN_SIFIR_L(Server_Bilgi sbilgi
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IGunluk.gUN_SIFIR_L(sbilgi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void gUN_SIFIR_S(Server_Bilgi sbilgi, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IGunluk.gUN_SIFIR_S(sbilgi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public String gun_firma_adi() throws ClassNotFoundException, SQLException
	{
		return _IGunluk.gun_firma_adi();
	}
	public void gorev_kayit(Gunluk_Bilgi gbilgi, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IGunluk.gorev_kayit(gbilgi);
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
		public void gorev_sil(int id, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IGunluk.gorev_sil(id);
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public int gid_ogren(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException
	{
		return _IGunluk.gid_ogren(gbilgi);
	}
	public ResultSet gorev_oku(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException
	{
		return _IGunluk.gorev_oku(gbilgi);
	}
	public ResultSet isim_oku() throws ClassNotFoundException, SQLException
	{
		return _IGunluk.isim_oku();
	}
	public ResultSet gorev_oku_tarih(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException
	{
		return _IGunluk.gorev_oku_tarih(gbilgi);
	}
	public int gorev_bul(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException
	{
		return _IGunluk.gorev_bul(gbilgi);
	}
	public ResultSet gID_OKU(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException
	{
		return _IGunluk.gID_oku(gbilgi);
	}
	public ResultSet hazir_gorevler(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException
	{
		return _IGunluk.hazir_gorevler(gbilgi);
	}
	public void gorev_tek_sil(int id) throws ClassNotFoundException, SQLException
	{
		_IGunluk.gorev_tek_sil(id);
	}
	public void gunluk_farkli_kayit(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException, ParseException
	{
		_IGunluk.gunluk_farkli_kayit(gbilgi);
	}
	public ResultSet gorev_oku_aylik_grup(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException
	{
		return _IGunluk.gorev_oku_aylik_grup(gbilgi);
	}
	public ResultSet gorev_oku_sonraki(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException
	{
		return _IGunluk.gorev_oku_sonraki(gbilgi);
	}
	public void gun_firma_adi_kayit(String fadi,String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IGunluk.gun_firma_adi_kayit(fadi);
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public ResultSet gorev_oku_yillik_pivot(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException
	{
		return _IGunluk.gorev_oku_yillik_pivot(gbilgi);
	}
}

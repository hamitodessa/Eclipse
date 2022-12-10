package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;

import LOGER_KAYIT.ILOGER_KAYIT;
import LOGER_KAYIT.SQLITE_LOG;

public class KUR_ACCESS {
	
	private static IKUR  _IKur;
	private static ILOGGER[] _Logger;
	public KUR_ACCESS(IKUR _IKur, ILOGGER[] _Logger)
	{
		this._IKur = _IKur;
		this._Logger = _Logger;
	}
	public static void baglan() throws SQLException, ClassNotFoundException
	{
	_IKur.baglan();
	}
	public void kUR_SIFIR_L(String kod, String dizin_yeri, String dizin, String ins, String kull, String sifre
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI,String port) throws ClassNotFoundException, SQLException
	{
		_IKur.kUR_SIFIR_L(kod, dizin_yeri, dizin, ins, kull, sifre, port);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
		 ILOGER_KAYIT asd = new SQLITE_LOG();
		 asd.Logla(mesaj, evrak, dBILGI);
		}
	public void kUR_SIFIR_S(String server, String ins, String kull, String sifre, String kod
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKur.kUR_SIFIR_S(server, ins, kull, sifre, kod);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
		 ILOGER_KAYIT asd = new SQLITE_LOG();
		 asd.Logla(mesaj, evrak, dBILGI);
		}
	public ResultSet kur_oku (String tar , String kur) throws ClassNotFoundException, SQLException 
	{
		return _IKur.kur_oku(tar, kur);
	}
	public void kur_sil (String tar,String kur) throws ClassNotFoundException, SQLException 
	{
		_IKur.kur_sil(tar, kur);
	}
	public void kur_kayit(String tar,String kur ,double  ma ,double ms ,double sa ,double ss ,double ba,double bs ) throws ClassNotFoundException, SQLException
	{
		_IKur.kur_kayit(tar, kur, ma, ms, sa, ss, ba, bs);
	}
	public ResultSet  kur_liste(String tar) throws ClassNotFoundException, SQLException
	{
		return _IKur.kur_liste(tar);
	}
	public ResultSet kur_rapor(String c1 ,String c2 ,String t1 ,String t2 ) throws ClassNotFoundException, SQLException
	{
		return _IKur.kur_rapor(c1, c2, t1, t2);
	}
	public ResultSet kur_graf_rapor(String c1  ,String t1 ,String t2,String cins,String siralama ) throws ClassNotFoundException, SQLException
	{
		return _IKur.kur_graf_rapor(c1, t1, t2, cins, siralama);
	}
	public ResultSet kur_yil_graf_rapor(String c1  ,String t1 ,String t2 ) throws ClassNotFoundException, SQLException
	{
		return _IKur.kur_yil_graf_rapor(c1, t1, t2);
	}
	public ResultSet kur_ay_graf_rapor(String c1  ,String t1 ,String t2 ) throws ClassNotFoundException, SQLException
	{
		return _IKur.kur_ay_graf_rapor(c1, t1, t2);
	}

}

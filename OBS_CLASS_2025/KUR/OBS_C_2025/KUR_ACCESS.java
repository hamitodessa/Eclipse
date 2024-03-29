package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;
@SuppressWarnings("static-access")
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
	public void kUR_SIFIR_L(Server_Bilgi sbilgi	, lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKur.kUR_SIFIR_L(sbilgi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(lBILGI, dBILGI);
		}
	public void kUR_SIFIR_S(Server_Bilgi sbilgi
			,lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKur.kUR_SIFIR_S(sbilgi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(lBILGI, dBILGI);
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

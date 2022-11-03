package OBS_C_2025;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CARI_ACCESS {
	private static ICARI_HESAP _ICari;
	private static ILOGGER[] _Logger;
	static Connection SQLitecon = null;
	private GLOBAL gLB = new GLOBAL();
	
	public CARI_ACCESS(ICARI_HESAP _ICari, ILOGGER[] _Logger)
	{
		this._ICari = _ICari;
		this._Logger = _Logger;
	}
	public static void baglan() throws SQLException
	
	{
	 _ICari.baglan();
	}
	public String cari_firma_adi() throws ClassNotFoundException, SQLException {
		return  _ICari.cari_firma_adi();
	}
	public void cari_sifirdan_L (String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,
			String sifre , String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException 
	{
		 _ICari.cari_sifirdan_L( kod,dizin_yeri, dizin,  fir_adi, ins, kull, sifre);
		 
		 for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void cARI_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		 _ICari.cARI_SIFIR_S(server, kull, sifre, kod, fir_adi, ins);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public ResultSet  cari_sonfisno( ) throws ClassNotFoundException, SQLException
	{
		return _ICari.cari_sonfisno();
	}
	public ResultSet dek_mizan(String kod) throws ClassNotFoundException, SQLException
	{
		return _ICari.dek_mizan(kod);
	}
	public int cari_fisno_al() throws ClassNotFoundException, SQLException
	{
		return _ICari.cari_fisno_al();
	}
	public boolean cari_fino_bak(int fisno) throws ClassNotFoundException, SQLException
	{
		return _ICari.cari_fino_bak(fisno);
	}
	public void evrak_yoket(int num, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_ICari.evrak_yoket(num);
		if (BAGLAN.cariDizin.lOG == true)
		{
		
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(mesaj,evrak, dBILGI);
		}
		
	}
	public void cari_dekont_kaydet(String bhes,String tar,int evrak,String bcins,Double bkur,Double borc ,
			String alhes,String acins,Double alkur,Double alacak,String izahat,String kod,String user, String mesaj,String evrakl, DIZIN_BILGILERI dBILGI) throws SQLException, ClassNotFoundException
	{
		_ICari.cari_dekont_kaydet(bhes, tar, evrak, bcins, bkur, borc, alhes, acins, alkur, alacak, izahat, kod, user);
		if (BAGLAN.cariDizin.lOG == true)
		{
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(mesaj,evrakl, dBILGI);
		}
	}
	public ResultSet fiskon(int evrakno) throws ClassNotFoundException, SQLException
	{
		return _ICari.fiskon(evrakno);
	}
	public ResultSet hp_pln () throws ClassNotFoundException, SQLException
	{
		return _ICari.hp_pln();
	}
	public ResultSet hesap_adi_oku(String hesap) throws ClassNotFoundException, SQLException 
	{
		return _ICari.hesap_adi_oku(hesap);
	}
	public ResultSet kasa_mizan(String kod,String ilktarih,String sontarih) throws ClassNotFoundException, SQLException
	{
		return _ICari.kasa_mizan(kod, ilktarih, sontarih);
	}
	public ResultSet mizan (String kod,String ilktarih,String sontarih,String ilkhcins,String sonhcins,String ilkkar,String sonkar) throws ClassNotFoundException, SQLException
	{
		return _ICari.mizan(kod, ilktarih, sontarih, ilkhcins, sonhcins, ilkkar, sonkar);
	}
	public ResultSet ekstre(String hesap , String t1 ,String t2) throws ClassNotFoundException, SQLException
	{
		return _ICari.ekstre(hesap, t1, t2);
	}
	public void sqlite_yaz(String tar, int evr, String iza, String kodu,double kur, double borc, double alac ,double bak) throws ClassNotFoundException, SQLException
	{
	Class.forName("org.sqlite.JDBC");
	SQLitecon = null;
	SQLitecon = gLB.myConnection();
	PreparedStatement stmt = null;
	String sqll = "INSERT INTO EKSTRE (TARIH,EVRAK,IZAHAT,KOD,KUR,BORC,ALACAK,BAKIYE) ";
	sqll += "VALUES (?,?,?,?,?,?,?,?)";
	{
		stmt = SQLitecon.prepareStatement(sqll);
		stmt.setString(1, tar);
		stmt.setInt(2, evr);
		stmt.setString(3, iza);
		stmt.setString(4, kodu);
		stmt.setDouble(5, kur);
		stmt.setDouble(6, borc);
		stmt.setDouble(7, alac);
		stmt.setDouble(8, bak);
	}
	stmt.executeUpdate();

	}
	public void sqlite_sil() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		SQLitecon = null;
		PreparedStatement stmt = null;
		SQLitecon = gLB.myConnection();
		String sql = "DELETE FROM EKSTRE ";
		stmt = SQLitecon.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		SQLitecon.close();
		}
	public String kod_ismi(String kodu) throws ClassNotFoundException, SQLException
	{
		return _ICari.kod_ismi(kodu);
	}
	public ResultSet hsp_pln(String arama) throws ClassNotFoundException, SQLException
	{
		return _ICari.hsp_pln(arama);
	}
	public void hsp_sil(String hesap, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_ICari.hsp_sil(hesap);
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(mesaj,evrak, dBILGI);
		
	}
	public void hpln_kayit(String kodu,String adi,String karton,String hcins,String usr, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_ICari.hpln_kayit(kodu, adi, karton, hcins, usr);

		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void hpln_detay_kayit(String kodu ,String yet ,String ad1 ,String ad2 ,String semt,String seh  , String vd , String vn 
			, String t1 ,String t2 ,String t3 ,String fx ,String o1 ,String o2 ,String o3 , String web 
			,String mai ,String kim  ,String acik ,boolean sms , InputStream  resim ) throws ClassNotFoundException, SQLException, IOException
	{
		_ICari.hpln_detay_kayit(kodu, yet, ad1, ad2, semt, seh, vd, vn, t1, t2, t3, fx, o1, o2, o3, web, mai, kim, acik, sms, resim);
	}
	public ResultSet ekstre_arama(String hes , String acik , String gun ,String ay,String yil ,String kod,String kullanici 
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		for ( ILOGGER  _Logger : _Logger )
		  	_Logger.Logla(mesaj,evrak, dBILGI);
		return _ICari.ekstre_arama(hes, acik, gun, ay, yil, kod, kullanici);
	}
}

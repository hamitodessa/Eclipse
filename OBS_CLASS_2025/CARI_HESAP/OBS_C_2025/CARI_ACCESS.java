package OBS_C_2025;


import java.sql.ResultSet;
import java.sql.SQLException;

public class CARI_ACCESS {
	private static ICARI_HESAP _ICari;
	private static ILOGGER[] _Logger;
	
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
	
}

package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KAMBIYO_ACCESS {
	
	private static IKAMBIYO  _IKambiyo;
	private static ILOGGER[] _Logger;

	
	public KAMBIYO_ACCESS(IKAMBIYO _IKambiyo, ILOGGER[] _Logger) {
		this._IKambiyo = _IKambiyo;
		this._Logger = _Logger;
	}

	public static void baglan() throws SQLException
	{
	_IKambiyo.baglan();
	}
	public void kAM_SIFIR_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,
			String sifre, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKambiyo.kAM_SIFIR_L(kod, dizin_yeri, dizin, fir_adi, ins, kull, sifre);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void kAM_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKambiyo.kAM_SIFIR_S(server, ins, kull, sifre, kod, fir_adi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public ResultSet kam_bordno(String cins,String bno,String gircik) throws ClassNotFoundException, SQLException
	{
		return _IKambiyo.kam_bordno(cins, bno, gircik);
	}
	public int kam_bordro_no_al(String cins) throws ClassNotFoundException, SQLException
	{
		_IKambiyo.kam_son_bordro_no_al(cins, cins);
		return 0;
	}
	public String kam_son_bordro_no_al(String cins ,String tur) throws ClassNotFoundException, SQLException
	{
		return _IKambiyo.kam_son_bordro_no_al(cins, tur);
	}
	public String kam_aciklama_oku(String evrcins,String satir,String evrno,String gircik) throws ClassNotFoundException, SQLException
	{
		return _IKambiyo.kam_aciklama_oku(evrcins, satir, evrno, gircik);
	}
	public void bordro_cikis_sil(String bno,String ceksen) throws ClassNotFoundException, SQLException
	{
		_IKambiyo.bordro_cikis_sil(bno, ceksen);
	}
	public void bordro_cikis_yaz(String ceksenfrom,String ceksencins_where,String cekno,String cmus ,
			String cbor,String ctar,String ozkod) throws ClassNotFoundException, SQLException
	{
		_IKambiyo.bordro_cikis_yaz(ceksenfrom, ceksencins_where, cekno, cmus, cbor, ctar, ozkod);
	}
	public void kam_aciklama_sil(String evrcins,String evrno,String cins) throws ClassNotFoundException, SQLException
	{
		_IKambiyo.kam_aciklama_sil(evrcins, evrno, cins);
	}
	public void kam_aciklama_yaz(String evrcins,int satir,String evrno,String aciklama,String gircik) throws ClassNotFoundException, SQLException
	{
		_IKambiyo.kam_aciklama_yaz(evrcins, satir, evrno, aciklama, gircik);
	}
	public ResultSet cek_kontrol(String cek) throws ClassNotFoundException, SQLException
	{
		return _IKambiyo.cek_kontrol(cek);
	}
	public void bordro_sil(String cins ,String bno,String tur) throws ClassNotFoundException, SQLException
	{
		_IKambiyo.bordro_sil(cins, bno, tur);
	}
	public void cek_kayit(String cno ,String vade,String gbo, String gmus ,String gtar,String gozk  
            , String ctar , String cbno ,String cmus ,String cozk ,String bank ,String sube 
            , double tut ,String cins ,String serno ,String ilkb ,String chesn 
            , String drm ,String ttarih ,String usr 
            , String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKambiyo.cek_kayit(cno, vade, gbo, gmus, gtar, gozk, ctar, cbno, cmus, cozk, bank, sube, tut, cins, serno, ilkb, chesn, drm, ttarih, usr);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public ResultSet cek_rapor( String cekno1,  String cekno2 ,  String tar1 , String tar2 , String gbord1 , String gbord2 
            ,  String gtar1 ,  String gtar2 , String cbord1 , String cbord2 , String ctar1, String ctar2 
            ,  String gmus1 , String gmus2 , String cmus1 , String cmus2 , String cin1 , String cin2 
            ,  String dur1 ,  String dur2 ,  String ttar1 ,  String ttar2 , String gozel , String cozel ) throws ClassNotFoundException, SQLException
	{
		return _IKambiyo.cek_rapor(cekno1, cekno2, tar1, tar2, gbord1, gbord2, gtar1, gtar2, cbord1, cbord2, ctar1, ctar2, gmus1, gmus2, cmus1, cmus2, cin1, cin2, dur1, dur2, ttar1, ttar2, gozel, cozel);
	}
	public void kam_durum_yaz(String numara,String ceksen_from,String ceksen_where,String durum,String ttarih) throws ClassNotFoundException, SQLException
	{
		_IKambiyo.kam_durum_yaz(numara, ceksen_from, ceksen_where, durum, ttarih);
	}
	public String kam_firma_adi() throws ClassNotFoundException, SQLException
	{
		return _IKambiyo.kam_firma_adi();
	}
}

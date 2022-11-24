package OBS_C_2025;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IKAMBIYO {

	public void baglan() throws SQLException;
	public void kAM_SIFIR_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins,String kull,String sifre,String port) throws ClassNotFoundException, SQLException;
	public void kAM_SIFIR_S(String server, String ins, String kull, String sifre, String kod,  String fir_adi) throws ClassNotFoundException, SQLException;
	public void create_table(String fir_adi) throws SQLException;
	public String kam_firma_adi() throws ClassNotFoundException, SQLException;
	public String kam_son_bordro_no_al(String cins ,String tur) throws ClassNotFoundException, SQLException;
	public int kam_bordro_no_al(String cins) throws ClassNotFoundException, SQLException;
	public void bordro_sil(String cins ,String bno,String tur) throws ClassNotFoundException, SQLException;
	public void cek_kayit(String cno ,String vade,String gbo, String gmus ,String gtar,String gozk  
            , String ctar , String cbno ,String cmus ,String cozk ,String bank ,String sube 
            , double tut ,String cins ,String serno ,String ilkb ,String chesn 
            , String drm ,String ttarih ,String usr ) throws ClassNotFoundException, SQLException ;
	public void kam_aciklama_sil(String evrcins,String evrno,String cins) throws ClassNotFoundException, SQLException;
	public void kam_aciklama_yaz(String evrcins,int satir,String evrno,String aciklama,String gircik) throws ClassNotFoundException, SQLException;
	public ResultSet kam_bordno(String cins,String bno,String gircik) throws ClassNotFoundException, SQLException;
	public String kam_aciklama_oku(String evrcins,String satir,String evrno,String gircik) throws ClassNotFoundException, SQLException;
	public ResultSet cek_kontrol(String cek) throws ClassNotFoundException, SQLException;
	public void bordro_cikis_sil(String bno,String ceksen) throws ClassNotFoundException, SQLException;
	public void bordro_cikis_yaz(String ceksenfrom,String ceksencins_where,String cekno,String cmus ,
			String cbor,String ctar,String ozkod) throws ClassNotFoundException, SQLException;
	public void kam_durum_yaz(String numara,String ceksen_from,String ceksen_where,String durum,String ttarih) throws ClassNotFoundException, SQLException;
	public void kam_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException;
	public ResultSet cek_rapor( String cekno1,  String cekno2 ,  String tar1 , String tar2 , String gbord1 , String gbord2 
            ,  String gtar1 ,  String gtar2 , String cbord1 , String cbord2 , String ctar1, String ctar2 
            ,  String gmus1 , String gmus2 , String cmus1 , String cmus2 , String cin1 , String cin2 
            ,  String dur1 ,  String dur2 ,  String ttar1 ,  String ttar2 , String gozel , String cozel ) throws ClassNotFoundException, SQLException;
	public void create_table_log() throws SQLException;
}

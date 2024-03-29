package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;



public interface IKERESTE {

	public void baglan() throws SQLException, ClassNotFoundException;
	public void kER_SIFIR_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void kER_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void create_table(String fir_adi) throws SQLException;
	public void create_table_log() throws SQLException ;
	public void kER_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException;
	public String ker_firma_adi() throws ClassNotFoundException, SQLException;
	public ResultSet kod_pln () throws ClassNotFoundException, SQLException;
	public void kod_kayit(String kodu,String aciklama) throws ClassNotFoundException, SQLException;
	public void kod_sil(String kod) throws ClassNotFoundException, SQLException;
	public ResultSet kons_pln () throws ClassNotFoundException, SQLException;
	public void kons_kayit(String kons,String aciklama,int paket_no) throws ClassNotFoundException, SQLException;
	public int kons_sil(String kons) throws ClassNotFoundException, SQLException;
	public String kod_adi(String kod) throws ClassNotFoundException, SQLException;
	public ResultSet ker_kod_degisken_oku(String fieldd,String sno,String nerden) throws ClassNotFoundException, SQLException;
	public ResultSet ker_kod_degisken_ara(String fieldd,String sno,String nerden,String arama) throws ClassNotFoundException, SQLException;
	public ResultSet ker_kod_alt_grup_degisken_oku (int sno) throws ClassNotFoundException, SQLException;
	public  boolean alt_grup_kontrol(int anagrp,int altgrp) throws ClassNotFoundException, SQLException;
	public void ker_degisken_alt_grup_sil(int  ID ) throws ClassNotFoundException, SQLException;
	public void ker_kod_degisken_sil(  String nerden  ,   String hangi_Y, int sira) throws ClassNotFoundException, SQLException;
	public void ker_degisken_eski(String fieldd ,String degisken_adi ,String nerden ,String sno ,int ID ) throws ClassNotFoundException, SQLException;
	public void ker_degisken_kayit(String fieldd  ,String nerden,String degisken_adi,String sira) throws ClassNotFoundException, SQLException;
	public void ker_degisken_alt_grup_kayit (String alt_grup , int ana_grup ) throws ClassNotFoundException, SQLException;
	public void ker_degisken_alt_grup_eski(String alt_grup ,int ana_grup ,int  ID ) throws ClassNotFoundException, SQLException;
	public String son_no_al(String cins) throws ClassNotFoundException, SQLException;
	public void ker_giris_sil(String eno) throws ClassNotFoundException, SQLException;
	public void ker_kaydet(KER_BILGI kBILGI,String user) throws ClassNotFoundException, SQLException;
	public ResultSet ker_oku(String eno,String cins) throws ClassNotFoundException, SQLException;
	public void dipnot_sil(String ino,String cins,String gircik) throws ClassNotFoundException, SQLException;
	public void dipnot_yaz(String eno,String bir,String iki,String uc,String tip,String gircik,String usr) throws ClassNotFoundException, SQLException;
	public ResultSet dipnot_oku(String ino,String cins ,String gircik) throws ClassNotFoundException, SQLException;
	public void aciklama_sil(String evrcins,String evrno,String cins) throws ClassNotFoundException, SQLException;
	public void aciklama_yaz(String evrcins,int satir,String evrno,String aciklama,String gircik) throws ClassNotFoundException, SQLException;
	public String aciklama_oku(String evrcins,int satir,String evrno,String gircik) throws ClassNotFoundException, SQLException;
	public ResultSet paket_oku(String pno,String nerden) throws ClassNotFoundException, SQLException;
	public void ker_cikis_sil(String eno) throws ClassNotFoundException, SQLException;
	public void ker_cikis_kaydet(KER_BILGI kBILGI) throws ClassNotFoundException, SQLException;
	public int evrak_no_al(String cins) throws ClassNotFoundException, SQLException;
	public ResultSet baslik_bak(String baslik,String ordr,String jkj,String k1,String k2,String f1,String f2,String t1,String t2,String dURUM,String e1,String e2) throws ClassNotFoundException, SQLException;
	public ResultSet grp_rapor(String gruplama,String sstr_2,String sstr_4,String kur_dos,String qwq6,
			String qwq7,String qwq8,String k1 ,String k2,String s1,String s2,String jkj,String t1,String t2,
			String sstr_5,String sstr_1,String orderBY ,String dURUM,String ko1, String ko2,String dpo, String grup,String e1,String e2) throws ClassNotFoundException, SQLException;
	public ResultSet stok_rapor(KER_RAPOR_BILGI ker_rap_BILGI) throws ClassNotFoundException, SQLException;
	public ResultSet ker_barkod_kod_oku(String sira) throws ClassNotFoundException, SQLException;
	public String kons_adi(String kons) throws ClassNotFoundException, SQLException;
	public ResultSet fat_rapor(KER_RAPOR_BILGI ker_rap_BILGI) throws ClassNotFoundException, SQLException;
	public ResultSet fat_detay_rapor(String fno , String turu) throws ClassNotFoundException, SQLException;
	public ResultSet fat_rapor_fat_tar(KER_RAPOR_BILGI ker_rap_BILGI,String qw1,String qw2,String qw3) throws ClassNotFoundException, SQLException;
	public ResultSet fat_rapor_cari_kod(KER_RAPOR_BILGI ker_rap_BILGI,String qw1,String qw2,String qw3) throws ClassNotFoundException, SQLException;
	public ResultSet urun_detay(KER_RAPOR_BILGI ker_rap_BILGI) throws ClassNotFoundException, SQLException;
	public void ker_kod_degis(String paket_No , String kon , String yenikod,int satir) throws ClassNotFoundException, SQLException;
	public void degisken_degistir(int anagrp,int altgrp, int anaygrp,int altygrp,String durum) throws ClassNotFoundException, SQLException;
	public String[] kod_aciklama_bul(String paket, String kons) throws ClassNotFoundException, SQLException;
	public ResultSet ort_diger_kodu(String yu,String qwq6 , String qwq7, String m1 ,String m2,String s1,String s2,
			String k1 ,String k2 ,String t1,String t2,String kurkod,String kurc,String iu,String hANGI,boolean kurlu) throws ClassNotFoundException, SQLException;
	public boolean kons_kontrol(String kons)throws ClassNotFoundException, SQLException;
	public void ker_toplu_kaydet(JTable table,int degisken[] , KER_RAPOR_BILGI keBilgi,String user) throws ClassNotFoundException, SQLException;
	public ResultSet paket_ara(KER_RAPOR_BILGI ker_rap_BILGI) throws ClassNotFoundException, SQLException;
	public int paket_no_al(String kons) throws ClassNotFoundException, SQLException;
	public ResultSet ortalama_genislik(String gruplama,String sstr_2,String sstr_4,String kur_dos,String qwq6,
			String qwq7,String qwq8,String k1 ,String k2,String s1,String s2,String jkj,String t1,String t2,
			String sstr_5,String sstr_1,String orderBY ,String dURUM,String ko1, String ko2,String dpo, String grup,String e1,String e2) throws ClassNotFoundException, SQLException;
	public ResultSet bos_kur(String kur , String cins,String dURUM) throws ClassNotFoundException, SQLException;
	public ResultSet sql_sorgu(String sql)  throws ClassNotFoundException, SQLException;
	public void ker_kons_degis(String kons , String yenikons,int satir) throws ClassNotFoundException, SQLException;
	public ResultSet envanter(KER_RAPOR_BILGI ker_rap_BILGI,String Gruplama[]) throws ClassNotFoundException, SQLException;
	}

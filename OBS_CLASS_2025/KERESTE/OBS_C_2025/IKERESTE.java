package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;



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
	public void kons_kayit(String kons,String aciklama) throws ClassNotFoundException, SQLException;
	public void kons_sil(String kons) throws ClassNotFoundException, SQLException;
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
	public ResultSet ker_oz_kod (String cins) throws ClassNotFoundException, SQLException;
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
	public ResultSet paket_oku(String pno) throws ClassNotFoundException, SQLException;
	public void ker_cikis_sil(String eno) throws ClassNotFoundException, SQLException;
	public void ker_cikis_kaydet(KER_BILGI kBILGI) throws ClassNotFoundException, SQLException;
	public int evrak_no_al(String cins) throws ClassNotFoundException, SQLException;
	public ResultSet baslik_bak(String baslik,String ordr,String jkj,String k1,String k2,String f1,String f2,String t1,String t2) throws ClassNotFoundException, SQLException;
	public ResultSet grp_urn_kodlu(String sstr_2,String sstr_4,String kur_dos,String qwq6,
			String qwq7,String qwq8,String s1 ,String s2,String k1,String k2,String jkj1,String deg1,String deg2,String t1,String t2,
			String sstr_5,String sstr_1) throws ClassNotFoundException, SQLException;
}

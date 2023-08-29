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
}

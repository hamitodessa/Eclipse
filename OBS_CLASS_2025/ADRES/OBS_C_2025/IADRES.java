package OBS_C_2025;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IADRES {

	public void baglan() throws SQLException;
	public void aDR_SIF_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins,String kull,String sifre) throws ClassNotFoundException, SQLException;
	public void aDR_SIFIR_S(String server, String ins, String kull, String sifre, String kod,  String fir_adi) throws ClassNotFoundException, SQLException;
	public void create_table(String fir_adi) throws SQLException;
	public String adr_firma_adi() throws ClassNotFoundException, SQLException;
	public void adr_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException;
	public ResultSet adres(String sira,String arama) throws ClassNotFoundException, SQLException;
	public String kod_ismi(String kodu) throws ClassNotFoundException, SQLException;
	public void adres_kayit(String kodu ,String adi ,String adr1 ,String adr2 ,String semt,String sehir  , String vd , String vn ,
			String fax, String tel1 ,String tel2 ,String ozel ,String yet ,String e_ma,String n1 ,String n2 ,String n3 ,  InputStream  resim   
			,String tel3 ,String acik   ,boolean sms  ,boolean mailg,String ok1 ,String ok2,String web ,String pkodu ,String usr) throws ClassNotFoundException, SQLException, IOException;
	public void sil(String kod ,String adi) throws ClassNotFoundException, SQLException;
	public ResultSet adr_hpl() throws ClassNotFoundException, SQLException;
	public ResultSet sms_adr_hpl(String nerden) throws ClassNotFoundException, SQLException;
	public ResultSet gdy_oku(String kod) throws ClassNotFoundException, SQLException;
	public String[] adres_oku (String kodu) throws ClassNotFoundException, SQLException;
	public void create_table_log() throws SQLException;
}

package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IKUR {
	
	public void baglan() throws SQLException;
	public void kUR_SIFIR_L(String kod, String dizin_yeri, String dizin, String ins,String kull,String sifre) throws ClassNotFoundException, SQLException;
	public void kUR_SIFIR_S(String server,  String ins, String kull, String sifre, String kod) throws ClassNotFoundException, SQLException;
	public void create_table() throws SQLException;
	public ResultSet  kur_liste(String tar) throws ClassNotFoundException, SQLException;
	public ResultSet kur_oku (String tar , String kur) throws ClassNotFoundException, SQLException;
	public void kur_sil (String tar,String kur) throws ClassNotFoundException, SQLException;
	public void kur_kayit(String tar,String kur ,double  ma ,double ms ,double sa ,double ss ,double ba,double bs ) throws ClassNotFoundException, SQLException;
	public ResultSet kur_rapor(String c1 ,String c2 ,String t1 ,String t2 ) throws ClassNotFoundException, SQLException;
	public ResultSet kur_yil_graf_rapor(String c1  ,String t1 ,String t2 ) throws ClassNotFoundException, SQLException;
	public ResultSet kur_graf_rapor(String c1  ,String t1 ,String t2,String cins,String siralama ) throws ClassNotFoundException, SQLException;
	public void create_table_log() throws SQLException;
	public ResultSet kur_ay_graf_rapor(String c1  ,String t1 ,String t2 ) throws ClassNotFoundException, SQLException;
}

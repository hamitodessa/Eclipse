package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ISMS {

	public void baglan() throws SQLException;
	public void sMS_SIFIR_L(String kod, String dizin_yeri, String dizin, String ins,String kull,String sifre) throws ClassNotFoundException, SQLException;
	public void sMS_SIFIR_S(String server,  String ins, String kull, String sifre, String kod) throws ClassNotFoundException, SQLException;
	public void create_table() throws SQLException ;
	public ResultSet mail_giris_bak() throws ClassNotFoundException, SQLException;
	public boolean kod_ismi(String kodu) throws ClassNotFoundException, SQLException;
	public void mail_giris_sil(String mail) throws ClassNotFoundException, SQLException;
	public void mail_giris_yaz(String mail , String unv ,String grpkod ,String kod,boolean drm , String unm ) throws ClassNotFoundException, SQLException;
	public ResultSet mail_grup_bak() throws ClassNotFoundException, SQLException;
	public ResultSet mail_alici_doldur() throws ClassNotFoundException, SQLException;
	public ResultSet mail_giden_bak(String user) throws ClassNotFoundException, SQLException;
	public void giden_rapor_yaz(String uname,String tar ,String msj,String mail,String hsp, String unv,String gond,String konu) throws ClassNotFoundException, SQLException;
	public ResultSet sms_giris_bak() throws ClassNotFoundException, SQLException;
	public ResultSet sms_grup_bak() throws ClassNotFoundException, SQLException;
	public ResultSet sms_giden_bak(String user) throws ClassNotFoundException, SQLException;
	public void sms_giris_sil(String tel) throws ClassNotFoundException, SQLException;
	public void sms_giris_yaz(String tel , String unv ,String grpkod ,String kod,boolean drm , String unm ) throws ClassNotFoundException, SQLException;
	public ResultSet sms_alici_doldur() throws ClassNotFoundException, SQLException;
	public void sms_yaz(String uname,String tar ,String msj,String mobile,String hsp, String unv) throws ClassNotFoundException, SQLException;
}

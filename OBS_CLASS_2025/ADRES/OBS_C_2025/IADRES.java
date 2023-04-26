package OBS_C_2025;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IADRES {

	public void baglan() throws SQLException;
	public void aDR_SIF_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins,String kull,String sifre,String port) throws ClassNotFoundException, SQLException;
	public void aDR_SIFIR_S(String server, String ins, String kull, String sifre, String kod,  String fir_adi) throws ClassNotFoundException, SQLException;
	public void create_table(String fir_adi) throws SQLException;
	public String adr_firma_adi() throws ClassNotFoundException, SQLException;
	public void adr_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException;
	public ResultSet adres(String sira,String arama) throws ClassNotFoundException, SQLException;
	public String kod_ismi(String kodu) throws ClassNotFoundException, SQLException;
	public void adres_kayit(ADRESS_DEGISKENLER aDEGIS) throws ClassNotFoundException, SQLException, IOException;
	public void sil(String kod ,String adi) throws ClassNotFoundException, SQLException;
	public ResultSet adr_hpl() throws ClassNotFoundException, SQLException;
	public ResultSet sms_adr_hpl(String nerden) throws ClassNotFoundException, SQLException;
	public ResultSet gdy_oku(String kod) throws ClassNotFoundException, SQLException;
	public String[] adres_oku (String kodu) throws ClassNotFoundException, SQLException;
	public void create_table_log() throws SQLException, ClassNotFoundException;
	public ResultSet adr_etiket() throws ClassNotFoundException, SQLException;
}

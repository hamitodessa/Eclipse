package OBS_C_2025;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IADRES {

	public void baglan() throws SQLException;
	public void aDR_SIF_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void aDR_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void create_table(String fir_adi) throws SQLException;
	public String adr_firma_adi() throws ClassNotFoundException, SQLException;
	public void adr_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException;
	public ResultSet adres(String sira,String arama) throws ClassNotFoundException, SQLException;
	public String kod_ismi(String kodu) throws ClassNotFoundException, SQLException;
	public void adres_kayit(ADRESS_DEGISKENLER aDEGIS) throws ClassNotFoundException, SQLException, IOException;
	public void sil(String id) throws ClassNotFoundException, SQLException;
	public ResultSet adr_hpl() throws ClassNotFoundException, SQLException;
	public ResultSet sms_adr_hpl(String nerden) throws ClassNotFoundException, SQLException;
	public ResultSet gdy_oku(String kod) throws ClassNotFoundException, SQLException;
	public String[] adres_oku (String kodu) throws ClassNotFoundException, SQLException;
	public void create_table_log() throws SQLException, ClassNotFoundException;
	public ResultSet adr_etiket(String siralama) throws ClassNotFoundException, SQLException;
	public ResultSet adr_etiket_arama(String arama) throws ClassNotFoundException, SQLException;
	public ResultSet kod_kontrol(String arama)throws ClassNotFoundException, SQLException;
}

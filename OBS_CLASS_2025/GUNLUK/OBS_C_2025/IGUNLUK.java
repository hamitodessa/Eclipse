package OBS_C_2025;

import java.sql.SQLException;

public interface IGUNLUK {
	public void baglan() throws SQLException;
	public void gUN_SIFIR_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins,String kull,String sifre,String port) throws ClassNotFoundException, SQLException;
	public void gUN_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String dizin_yeri, String dizin,
            String fir_adi) throws ClassNotFoundException, SQLException;
	public void create_table(  String fir_adi) throws SQLException;
	public String gun_firma_adi() throws ClassNotFoundException, SQLException;
	public void create_table_log() throws SQLException;
}

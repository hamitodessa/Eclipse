package OBS_PACKAGE;

import java.sql.SQLException;

public interface IGUNLUK {
	public void gun_sifirdan_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins,String kull,String sifre) throws ClassNotFoundException, SQLException;
	public void gun_sifirdan_S(String server, String ins, String kull, String sifre, String kod, String dizin_yeri, String dizin,
            String fir_adi) throws ClassNotFoundException, SQLException;
	public void create_table(  String fir_adi) throws SQLException;
	public String gun_firma_adi() throws ClassNotFoundException, SQLException;
}

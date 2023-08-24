package OBS_C_2025;

import java.sql.SQLException;



public interface IKERESTE {

	public void baglan() throws SQLException, ClassNotFoundException;
	public void kER_SIFIR_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void kER_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void create_table(String fir_adi) throws SQLException;
	public void create_table_log() throws SQLException ;
	public void kER_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException;
	public String ker_firma_adi() throws ClassNotFoundException, SQLException;

}

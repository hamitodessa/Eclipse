package fih;

import java.sql.SQLException;

import OBS_C_2025.Server_Bilgi;

public interface I_Fihrist {
	public void baglan() throws SQLException, ClassNotFoundException;
	public void reh_sifirdan_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void reh_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void create_table(String fir_adi) throws SQLException;
}

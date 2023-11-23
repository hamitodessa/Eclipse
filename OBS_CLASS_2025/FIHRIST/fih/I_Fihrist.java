package fih;

import java.sql.ResultSet;
import java.sql.SQLException;

import OBS_C_2025.Server_Bilgi;

public interface I_Fihrist {
	public void baglan() throws SQLException, ClassNotFoundException;
	public void reh_sifirdan_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void reh_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void create_table(String fir_adi) throws SQLException;
	public ResultSet reh_doldur() throws ClassNotFoundException, SQLException;
	public void reh_kayit(String adi,String t1,String t2,String t3,String t4 , String fax, String note,String note2,String mail) throws ClassNotFoundException, SQLException;
	public void reh_sil(int cdi) throws SQLException, ClassNotFoundException;
}

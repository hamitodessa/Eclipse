package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;



public interface IKERESTE {

	public void baglan() throws SQLException, ClassNotFoundException;
	public void kER_SIFIR_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void kER_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void create_table(String fir_adi) throws SQLException;
	public void create_table_log() throws SQLException ;
	public void kER_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException;
	public String ker_firma_adi() throws ClassNotFoundException, SQLException;
	public ResultSet kod_pln () throws ClassNotFoundException, SQLException;
	public void kod_kayit(String kodu,String aciklama) throws ClassNotFoundException, SQLException;
	public void kod_sil(String kod) throws ClassNotFoundException, SQLException;
	public ResultSet kons_pln () throws ClassNotFoundException, SQLException;
	public void kons_kayit(String kons,String aciklama) throws ClassNotFoundException, SQLException;
	public void kons_sil(String kons) throws ClassNotFoundException, SQLException;
}

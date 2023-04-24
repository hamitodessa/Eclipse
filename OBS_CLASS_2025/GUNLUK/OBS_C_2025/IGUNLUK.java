package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public interface IGUNLUK {
	public void baglan() throws SQLException;
	public void gUN_SIFIR_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void gUN_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void create_table(  String fir_adi) throws SQLException;
	public String gun_firma_adi() throws ClassNotFoundException, SQLException;
	public void create_table_log() throws SQLException;
	public void gorev_kayit(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException;
	public void gorev_sil(int id) throws ClassNotFoundException, SQLException;
	public int gid_ogren(Gunluk_Bilgi gbilgi)throws ClassNotFoundException, SQLException;
	public ResultSet gorev_oku(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException;
	public ResultSet isim_oku() throws ClassNotFoundException, SQLException;
	public ResultSet gorev_oku_tarih(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException;
	public int gorev_bul(Gunluk_Bilgi gbilgi)throws ClassNotFoundException, SQLException;
	public ResultSet gID_oku(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException;
	public ResultSet hazir_gorevler(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException;
	public void gorev_tek_sil(int id) throws ClassNotFoundException, SQLException;
	public void gunluk_farkli_kayit(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException, ParseException;
	public ResultSet gorev_oku_aylik_grup(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException;
	public ResultSet gorev_oku_sonraki(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException;
	public void gun_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException;
}


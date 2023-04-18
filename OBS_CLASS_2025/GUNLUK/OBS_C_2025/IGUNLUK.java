package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public interface IGUNLUK {
	public void baglan() throws SQLException;
	public void gUN_SIFIR_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins,String kull,String sifre,String port) throws ClassNotFoundException, SQLException;
	public void gUN_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String dizin_yeri, String dizin,
            String fir_adi) throws ClassNotFoundException, SQLException;
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
}


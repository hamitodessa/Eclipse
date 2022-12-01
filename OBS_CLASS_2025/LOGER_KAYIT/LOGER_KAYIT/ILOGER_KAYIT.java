package LOGER_KAYIT;

import java.sql.ResultSet;
import java.sql.SQLException;

import OBS_C_2025.DIZIN_BILGILERI;



public interface ILOGER_KAYIT {
	
	public void Logla(String mesaj, String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException ;
	
}

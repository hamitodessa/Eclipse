package OBS_C_2025;

import java.sql.SQLException;

import LOGER_KAYIT.ILOGER_KAYIT;

public interface ILOGGER {
	
	
	
	
	
	public void Logla(String mesaj,String evrak) throws ClassNotFoundException, SQLException;

}

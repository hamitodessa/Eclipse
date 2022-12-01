package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ILOGGER {
		
	public void Logla(String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException;
	

}

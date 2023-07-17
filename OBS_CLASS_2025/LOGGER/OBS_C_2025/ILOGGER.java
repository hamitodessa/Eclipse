package OBS_C_2025;

import java.sql.SQLException;

public interface ILOGGER {
		
	public void Logla(lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException;
	}

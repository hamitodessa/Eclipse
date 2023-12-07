package OBS_C_2025;

import java.sql.SQLException;

public class OBS_ORTAK_MSACCESS implements IConnection {
	
	@Override
	public boolean Server_kontrol_L(Server_Bilgi sbilgi) throws ClassNotFoundException {
		boolean result = false ;
		result = GLOBAL.dos_kontrol(BAGLAN.fihDizin.cONN_STR );
		return result;  
	}

	@Override
	public boolean Server_kontrol_S(Server_Bilgi sbilgi) throws ClassNotFoundException {
		
		return false;
	}

	@Override
	public boolean Dosyakontrol_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		boolean result = false ;
		result = GLOBAL.dos_kontrol(BAGLAN.fihDizin.cONN_STR );
		return result;  
		
	}

	@Override
	public boolean Dosyakontrol_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		
		return false;
	}

}

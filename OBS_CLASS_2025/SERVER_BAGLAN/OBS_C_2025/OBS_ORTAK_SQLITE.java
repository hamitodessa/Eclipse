package OBS_C_2025;

import java.sql.SQLException;

public class OBS_ORTAK_SQLITE implements IConnection {
	
	@Override
	public boolean Server_kontrol_L(Server_Bilgi sbilgi) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		boolean result = false ;
		result = GLOBAL.dos_kontrol(BAGLAN.fihDizin.cONN_STR );
		return result;  
	}

	@Override
	public boolean Server_kontrol_S(Server_Bilgi sbilgi) throws ClassNotFoundException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

}

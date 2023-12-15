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

	@Override
	public void job_sil_L(String jobName,Server_Bilgi sbilgi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void job_sil_S(String jobName, Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void job_baslat_L(String jobName, Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void job_baslat_S(String jobName, Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void job_olustur_L(String jobName, String dosya,String indexISIM, Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void job_olustur_S(String jobName, String dosya,String indexISIM, Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

}

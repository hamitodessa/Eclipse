package OBS_C_2025;

import java.sql.SQLException;

import LOGER_KAYIT.ILOGER_KAYIT;
@SuppressWarnings("static-access")
public class LOG_YAZ {

	private static  ILOGER_KAYIT _ILoger_Kayit;
	
	public LOG_YAZ( ILOGER_KAYIT _ILoger_Kayit)
	{
		this._ILoger_Kayit = _ILoger_Kayit;
	}
	public void Logla(String mesaj, String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException {
		_ILoger_Kayit.Logla(mesaj, evrak,dBILGI);
	}

}

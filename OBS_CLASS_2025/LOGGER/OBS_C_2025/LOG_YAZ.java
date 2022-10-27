package OBS_C_2025;

import LOGER_KAYIT.ILOGER_KAYIT;

public class LOG_YAZ {
	
	private static  ILOGER_KAYIT _ILoger_Kayit;
	
	
	public LOG_YAZ( ILOGER_KAYIT _ILoger_Kayit)
	{
		
		this._ILoger_Kayit = _ILoger_Kayit;
	}
	
	public void Logla(String mesaj) {
		// TODO Auto-generated method stub
		
		
		_ILoger_Kayit.Logla(mesaj);
			
		
	}

}

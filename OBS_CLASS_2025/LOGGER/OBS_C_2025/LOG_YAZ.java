package OBS_C_2025;

import LOGER_KAYIT.ILOGER_KAYIT;

public class LOG_YAZ {
	
	private static  ILOGER_KAYIT _ILoger;
	private static ILOGGER _Logger;
	
	public CARI_ACCESS(ICARI_HESAP _ICari, ILOGGER _Logger)
	{
		this._ICari = _ICari;
		this._Logger = _Logger;
	}

}

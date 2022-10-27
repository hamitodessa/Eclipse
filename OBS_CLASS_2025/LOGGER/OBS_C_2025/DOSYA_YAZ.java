package OBS_C_2025;

import LOGER_KAYIT.ILOGER_KAYIT;

public class DOSYA_YAZ implements ILOGGER{

	


	ILOGER_KAYIT _ILoger_Kayit ;
	

	public DOSYA_YAZ(ILOGER_KAYIT _ILoger_Kayit) {
	
		this._ILoger_Kayit = _ILoger_Kayit;
	}


	@Override
	public void Logla(String mesaj) {
		// TODO Auto-generated method stub
		
		
				System.out.println(  "Dosyaya Loglama Yapildi");
		
	}

}

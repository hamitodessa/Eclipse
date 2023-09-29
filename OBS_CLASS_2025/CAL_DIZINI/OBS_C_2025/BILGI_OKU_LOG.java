package OBS_C_2025;

public class BILGI_OKU_LOG {

	public  void bILGI_OKU(DIZIN_BILGILERI dIZIN, String  dOSYA_BASLANGIC, DIZIN_BILGILERI oDIZIN) 
	{
		if (new String(oDIZIN.yER.toString()).equals("L") == true) 
		{ 
			if (oDIZIN.hAN_SQL.toString().equals("MS SQL") == true)
			{
				dIZIN.kULLANICI = oDIZIN.kULLANICI;
				dIZIN.sIFRESI = oDIZIN.sIFRESI;
				String port = "" ;
				if ( ! oDIZIN.sERVER.toString().equals("") )
				{
				port =  ":" + oDIZIN.sERVER ;
				}
				dIZIN.cONN_STR =  "localhost" + port + ";instanceName=" + oDIZIN.iNSTANCE + " ; database=" + dOSYA_BASLANGIC  + oDIZIN.kOD + "_LOG"; //VERITABANI
				dIZIN.mODUL =    dOSYA_BASLANGIC  + oDIZIN.kOD + "_mSSQL" + ".DB" ;   //SQLITE
				dIZIN.mODULADI =    dOSYA_BASLANGIC  + oDIZIN.kOD  + "_mSSQL" ;//TEXT DOSYA
			}
			else  if (oDIZIN.hAN_SQL.toString().equals("MY SQL") == true)
			{
				dIZIN.kULLANICI = oDIZIN.kULLANICI;
				dIZIN.sIFRESI = oDIZIN.sIFRESI;
				dIZIN.cONN_STR =  "localhost/" + dOSYA_BASLANGIC  + oDIZIN.kOD + "_log";//VERITABANI
				dIZIN.mODUL =     (dOSYA_BASLANGIC  + oDIZIN.kOD).toLowerCase() + "_mYSQL" + ".DB" ;//SQLITE
				dIZIN.mODULADI =  (dOSYA_BASLANGIC  + oDIZIN.kOD).toLowerCase() + "_mYSQL"  ;//TEXT DOSYA
			}
		}
		else
		{ 
			if (oDIZIN.hAN_SQL.toString().equals("MS SQL") == true)
			{
				dIZIN.kULLANICI = oDIZIN.kULLANICI;
				dIZIN.sIFRESI = oDIZIN.sIFRESI;
				dIZIN.cONN_STR = oDIZIN.sERVER + ";instanceName=" + oDIZIN.iNSTANCE + " ; database=" +  dOSYA_BASLANGIC + oDIZIN.kOD + "_LOG";//VERITABANI
				dIZIN.mODUL =   GLOBAL.char_degis( oDIZIN.sERVER) +  dOSYA_BASLANGIC  + oDIZIN.kOD + "_mSSQL" + ".DB" ;//SQLITE
				dIZIN.mODULADI =  GLOBAL.char_degis( oDIZIN.sERVER) +  dOSYA_BASLANGIC  + oDIZIN.kOD  + "_mSSQL" ;//TEXT DOSYA
			}
			else  if (oDIZIN.hAN_SQL.toString().equals("MY SQL") == true)
			{
				dIZIN.kULLANICI = oDIZIN.kULLANICI;
				dIZIN.sIFRESI = oDIZIN.sIFRESI;
				dIZIN.cONN_STR =  oDIZIN.sERVER + "/" +  dOSYA_BASLANGIC + oDIZIN.kOD + "_log";//VERITABANI
				dIZIN.mODUL =      GLOBAL.char_degis( oDIZIN.sERVER) + (dOSYA_BASLANGIC  + oDIZIN.kOD).toLowerCase() + "_mYSQL" + ".DB" ;//SQLITE
				dIZIN.mODULADI =   GLOBAL.char_degis( oDIZIN.sERVER) + (dOSYA_BASLANGIC  + oDIZIN.kOD).toLowerCase() + "_mYSQL"  ;//TEXT DOSYA
			}
		}
	}
}




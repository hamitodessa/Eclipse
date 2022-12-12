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
				dIZIN.cONN_STR =  "localhost;instanceName=" + oDIZIN.iNSTANCE + " ; database=" + dOSYA_BASLANGIC  + oDIZIN.kOD + "_LOG";
				dIZIN.mODULADI =    dOSYA_BASLANGIC  + oDIZIN.kOD  ;
				dIZIN.mODUL =    dOSYA_BASLANGIC  + oDIZIN.kOD + "_mSSQL" + ".DB" ;
			}
			else  if (oDIZIN.hAN_SQL.toString().equals("MY SQL") == true)
			{
				dIZIN.kULLANICI = oDIZIN.kULLANICI;
				dIZIN.sIFRESI = oDIZIN.sIFRESI;
				dIZIN.cONN_STR =  "localhost/" + dOSYA_BASLANGIC  + oDIZIN.kOD + "_log";
				dIZIN.mODULADI =  dOSYA_BASLANGIC  + oDIZIN.kOD  ;
				dIZIN.mODUL =    dOSYA_BASLANGIC  + oDIZIN.kOD + "_mYSQL" + ".DB" ;
			}
		}
		else
		{ 
			if (oDIZIN.hAN_SQL.toString().equals("MS SQL") == true)
			{
				dIZIN.kULLANICI = oDIZIN.kULLANICI;
				dIZIN.sIFRESI = oDIZIN.sIFRESI;
				dIZIN.cONN_STR = oDIZIN.sERVER + ";instanceName=" + oDIZIN.iNSTANCE + " ; database=" +  dOSYA_BASLANGIC + oDIZIN.kOD + "_LOG";
				dIZIN.mODUL =    dOSYA_BASLANGIC  + oDIZIN.kOD + "_mSSQL" + ".DB" ;
			}
			else  if (oDIZIN.hAN_SQL.toString().equals("MY SQL") == true)
			{
				dIZIN.kULLANICI = oDIZIN.kULLANICI;
				dIZIN.sIFRESI = oDIZIN.sIFRESI;
				dIZIN.cONN_STR =  oDIZIN.sERVER + "/" +  dOSYA_BASLANGIC + oDIZIN.kOD + "_log";
				dIZIN.mODUL =    dOSYA_BASLANGIC  + oDIZIN.kOD + "_mYSQL" + ".DB" ;
			}
		}
	}
}




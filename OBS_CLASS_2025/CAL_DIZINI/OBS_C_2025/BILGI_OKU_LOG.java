package OBS_C_2025;


public class BILGI_OKU_LOG {
	
	public  void bILGI_OKU(DIZIN_BILGILERI dIZIN, String  dOSYA_BASLANGIC) 
	{
								if (new String(dIZIN.yER.toString()).equals("L") == true) 
					            { 
					            	dIZIN.cONN_STR =  "localhost;instanceName=" + dIZIN.iNSTANCE + " ; database=" + dOSYA_BASLANGIC  + dIZIN.kOD + "_LOG";
					            }
					            else
					            { 
					            	dIZIN.cONN_STR = dIZIN.sERVER + ";instanceName=" +dIZIN.iNSTANCE + " ; database=" +  dOSYA_BASLANGIC +dIZIN.kOD + "_LOG";
					            }
							}
	}




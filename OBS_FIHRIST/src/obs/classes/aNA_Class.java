package obs.classes;

import OBS_C_2025.IConnection;
import OBS_C_2025.SIFRE_DONDUR;
import OBS_C_2025.USER_ISLEMLERI;
import fih.I_Fihrist;

public class aNA_Class {
	public static boolean FIHRIST_CONN;
	public static I_Fihrist  _IFihrist ;

	public IConnection _IFihristCon ;
	public USER_ISLEMLERI uSER_ISL = new USER_ISLEMLERI();
	public SIFRE_DONDUR sDONDUR = new     SIFRE_DONDUR();
	public static boolean FIH_DOS_VAR;
	public String[] options = {"Tamam......       		!	", "Vazgec......       		!	"}; 
}

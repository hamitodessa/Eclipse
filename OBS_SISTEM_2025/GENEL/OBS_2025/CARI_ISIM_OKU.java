package OBS_2025;

import java.sql.ResultSet;

import OBS_C_2025.CARI_ACCESS;
import raven.toast.Notifications;

public class CARI_ISIM_OKU {
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	public static String[] isim(String kod)  {
		String [] sonuc = {"","","F"}  ;
		if(kod.equals("")) return sonuc;
		try
		{
			ResultSet	rs = null;
			rs = c_Access.hesap_adi_oku(kod);
			if (!rs.isBeforeFirst() ) {  
			} 
			else
			{
				rs.next();
				sonuc [0] = rs.getString("UNVAN");
				sonuc [1]= rs.getString("HESAP_CINSI");
				sonuc [2]= "T" ;
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage() );
		}
		return sonuc;
	}
}

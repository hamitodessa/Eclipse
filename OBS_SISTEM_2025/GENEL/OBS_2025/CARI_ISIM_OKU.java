package OBS_2025;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import OBS_C_2025.CARI_ACCESS;

@SuppressWarnings("static-access")
public class CARI_ISIM_OKU {
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(oac._ICar , oac._ICari_Loger);
	public static String[] isim(String kod)  {
		String [] sonuc = {"","",""}  ;
		try
		{
			ResultSet	rs = null;
			rs = c_Access.hesap_adi_oku(kod);
			if (!rs.isBeforeFirst() ) {  
				sonuc [0]= "" ;
				sonuc [1]= "" ;
				sonuc [2]= "F" ;
			} 
			else
			{
				rs.next();
				sonuc [0] = rs.getString("UNVAN");
				sonuc [1]=rs.getString("HESAP_CINSI");
				sonuc [2]= "T" ;
			}

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage() + " clss",  "Hesap Ismi Okuma", JOptionPane.ERROR_MESSAGE);     
		}
		return sonuc;
	}
}

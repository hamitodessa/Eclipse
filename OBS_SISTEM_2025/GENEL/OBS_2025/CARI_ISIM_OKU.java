package OBS_2025;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class CARI_ISIM_OKU {
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	public static String[] isim(String kod)  {
		String [] sonuc = {"","",""}  ;
		try
	  {
		ResultSet	rs = null;
	    if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
	    {
	    	rs = oac.cARI_HESAP_MSSQL.hesap_adi_oku(kod);
	    }
	    else
	    {
	    	rs = oac.cARI_HESAP_MYSQL.hesap_adi_oku(kod);
	    }
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

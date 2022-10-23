package OBS_PACKAGE;

import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class AUTO_HESAP_KODU {

	 public static CARI_HESAP_MSSQL cARI_HESAP_MSSQL = new CARI_HESAP_MSSQL();
	    public static CARI_HESAP_MYSQL cARI_HESAP_MYSQL = new CARI_HESAP_MYSQL();
public static void auto_doldur(JComboBox<String> cmbox)
{
	try {
		ResultSet rs = null;
		
		if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
		{
			rs =  cARI_HESAP_MSSQL.hp_pln();
		}
		else
		{
			rs =  cARI_HESAP_MYSQL.hp_pln();
		}
		cmbox .removeAllItems();
		cmbox.addItem("");
		if (!rs.isBeforeFirst() ) {  
		}
		else
		{
			while (rs.next())
			{
				cmbox.addItem(rs.getString("HESAP"));
			}
		}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Hesap Kodu", JOptionPane.PLAIN_MESSAGE);
		}
}
}

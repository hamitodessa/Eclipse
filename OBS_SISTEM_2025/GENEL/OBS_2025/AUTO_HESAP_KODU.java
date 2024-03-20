package OBS_2025;

import java.sql.ResultSet;


import javax.swing.JComboBox;
import OBS_C_2025.CARI_ACCESS;
import raven.toast.Notifications;


public class AUTO_HESAP_KODU {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	public static void auto_doldur(JComboBox<String> cmbox)
	{
		try {
			ResultSet rs = null;
			rs = c_Access.hp_pln();
			cmbox.removeAllItems();
			cmbox.addItem("");
			if (!rs.isBeforeFirst() ) {  
			}
			else
			{
				while (rs.next())
					cmbox.addItem(rs.getString("HESAP"));
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
		}
	}
}

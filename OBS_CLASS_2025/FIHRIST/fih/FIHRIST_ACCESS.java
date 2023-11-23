package fih;

import java.sql.ResultSet;
import java.sql.SQLException;

import OBS_C_2025.Server_Bilgi;

@SuppressWarnings("static-access")
public class FIHRIST_ACCESS {
	private static I_Fihrist _IFihrist;
	
	public FIHRIST_ACCESS(I_Fihrist _IFihrist)
	{
		this._IFihrist = _IFihrist ;
	}
	public static void baglan() throws SQLException, ClassNotFoundException
	{
		_IFihrist.baglan();
	}
	public void fihrist_sifirdan_L (Server_Bilgi sbilgi ) throws ClassNotFoundException, SQLException 
	{
		 _IFihrist.reh_sifirdan_L( sbilgi);
	}
	public void fihrist_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException
	{
		 _IFihrist.reh_SIFIR_S(sbilgi);
	}
	public ResultSet reh_doldur() throws ClassNotFoundException, SQLException
	{
		return _IFihrist.reh_doldur();
	}
}

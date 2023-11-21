package fih;

import java.sql.SQLException;

public class FIHRIST_ACCESS {
	private static I_Fihrist _IFihrist;
	
	public FIHRIST_ACCESS(I_Fihrist _IFihrist)
	{
		//this._IFihrist = _IFihrist;
		this._IFihrist = _IFihrist ;
	}
	public static void baglan() throws SQLException, ClassNotFoundException
	{
		_IFihrist.baglan();
	}
}

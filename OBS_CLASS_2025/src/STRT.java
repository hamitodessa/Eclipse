import java.sql.SQLException;

import OBS_C_2025.BAGLAN;


public class STRT {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		BAGLAN bG = new BAGLAN();
		bG.Connect("Admin");
		
		
		System.out.println("=" +BAGLAN.cariDizin.sERVER.toString());
		
	}

}

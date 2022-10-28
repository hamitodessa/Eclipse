package LOGER_KAYIT;

import java.sql.SQLException;

public interface ILOGER_KAYIT {
	
	public void Logla(String mesaj, String evrak) throws ClassNotFoundException, SQLException ;

}

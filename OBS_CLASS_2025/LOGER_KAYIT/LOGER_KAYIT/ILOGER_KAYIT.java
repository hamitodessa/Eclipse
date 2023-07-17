package LOGER_KAYIT;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;
import OBS_C_2025.DIZIN_BILGILERI;
import OBS_C_2025.lOG_BILGI;

public interface ILOGER_KAYIT 
{
	public void Logla(lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException ;
	public ResultSet log_rapor(String t1, String t2, String aciklama, String evrak , String user, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException;
	public DefaultTableModel  log_txt_rapor(String t1, String t2, String aciklama, String evrak , String user, DIZIN_BILGILERI dBILGI);
}

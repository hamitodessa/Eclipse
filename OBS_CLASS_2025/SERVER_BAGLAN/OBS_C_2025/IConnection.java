package OBS_C_2025;

import java.sql.SQLException;

public interface IConnection {

	public boolean Server_kontrol_L(Server_Bilgi sbilgi) throws ClassNotFoundException;
	public boolean Server_kontrol_S(Server_Bilgi sbilgi)throws ClassNotFoundException;
	public boolean Dosyakontrol_L(Server_Bilgi sbilgi)throws ClassNotFoundException, SQLException;
	public boolean Dosyakontrol_S(Server_Bilgi sbilgi)throws ClassNotFoundException, SQLException;
	public void job_sil_L(String jobName, String dosya,Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void job_sil_S(String jobName, String dosya,Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void job_baslat_L(String jobName,Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void job_baslat_S(String jobName,Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void job_olustur_L(String jobName, String dosya,String indexISIM ,Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
	public void job_olustur_S(String jobName, String dosya,String indexISIM ,Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException;
}

//SELECT job_id, [name] FROM msdb.dbo.sysjobs;
//EXEC msdb.dbo.sp_delete_job '160D98C1-3CFC-4F63-A6D8-FD7A8AD143FB'

//USE msdb;  
//GO 

//EXEC sp_start_job N'Cari index';

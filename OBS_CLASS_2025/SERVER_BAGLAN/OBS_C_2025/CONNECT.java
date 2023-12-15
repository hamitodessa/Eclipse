package OBS_C_2025;

import java.sql.SQLException;

public class CONNECT {

	private IConnection _IConnect;

	public CONNECT(IConnection _IConnect) 
	{
		this._IConnect = _IConnect;
	}
	public boolean Server_kontrol_L(Server_Bilgi sbilgi) throws ClassNotFoundException 
	{
		return _IConnect.Server_kontrol_L(sbilgi);
	}
	public boolean Dosya_kontrol_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException 
	{
		return _IConnect.Dosyakontrol_L(sbilgi);
	}
	public boolean Server_kontrol_S(Server_Bilgi sbilgi) throws ClassNotFoundException
	{
		return _IConnect.Server_kontrol_S(sbilgi);
	}
	public boolean Dosya_kontrol_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException 
	{
		return _IConnect.Dosyakontrol_S(sbilgi);
	}
	public void job_sil_L(String jobName, String dosya,Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException
	{
		_IConnect.job_sil_L(jobName, dosya,sbilgi);
	}
	public void job_sil_S(String jobName, String dosya,Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException
	{
		_IConnect.job_sil_S(jobName,dosya,sbilgi);
	}
	public void job_baslat_L(String jobName,Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException
	{
		_IConnect.job_baslat_L(jobName, sbilgi);
	}
	public void job_baslat_S(String jobName,Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException
	{
		_IConnect.job_baslat_S(jobName, sbilgi);
	}
	public void job_olustur_L(String jobName, String dosya,String indexISIM ,Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException
	{
		_IConnect.job_olustur_L(jobName,dosya,indexISIM , sbilgi);
	}
	public void job_olustur_S(String jobName, String dosya,String indexISIM ,Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException
	{
		_IConnect.job_olustur_S(jobName,dosya,indexISIM, sbilgi);
	}
}

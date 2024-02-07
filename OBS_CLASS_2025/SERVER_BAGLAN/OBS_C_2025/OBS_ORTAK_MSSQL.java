package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;



public class OBS_ORTAK_MSSQL implements IConnection {

	private  boolean result;
	public boolean Server_kontrol_L(Server_Bilgi sbilgi) throws ClassNotFoundException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null; 
		if ( ! sbilgi.getPort().equals("") )
		{
			sbilgi.setPort(":" + sbilgi.getPort());
		}
		try
		{
			String cumle = "";
			cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort()  +";instanceName=" + sbilgi.getIns() + ";";
			conn = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
			conn.close();
			result = true;
		} 
		catch (SQLException e)
		{  
			JOptionPane.showMessageDialog(null, e.getMessage(),  "Server Baglanti", JOptionPane.ERROR_MESSAGE);      
			result = false;  
		}  
		return result;
	}
	public boolean Server_kontrol_S(Server_Bilgi sbilgi) throws ClassNotFoundException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		try
		{
			String cumle = "";
			cumle = "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";";
			conn = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
			conn.close();
			result =  true;
		} 
		catch (SQLException e)
		{  
			JOptionPane.showMessageDialog(null, e.getMessage(),  "Server Baglanti_S", JOptionPane.ERROR_MESSAGE);     
			result =  false;  
		}  
		return result;
	}
	public boolean Dosyakontrol_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		String cumle = "";
		if ( ! sbilgi.getPort().toString().equals(""))
			sbilgi.setPort( ":" + sbilgi.getPort());
		cumle ="jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";";
		conn = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sys.databases where name = '" + sbilgi.getDb() + "'");
		ResultSet rs = stmt.executeQuery();
		result = !rs.isBeforeFirst() ? false :true ;
		stmt.close();
		conn.close();
		return result;
	}
	public boolean Dosyakontrol_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		String cumle = "";
		cumle =  "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";";
		conn = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sys.databases where name = '" + sbilgi.getDb() + "'");
		ResultSet rs = stmt.executeQuery();
		result = !rs.isBeforeFirst() ? false :true ;
		stmt.close();
		conn.close();
		return result;
	}
	@Override
	public void job_sil_L(String jobName, String dosya,Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		String cumle = "";
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort()  +";instanceName=" + sbilgi.getIns() + ";";
		conn = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		PreparedStatement stmt = conn.prepareStatement("SELECT job_id, [name] FROM msdb.dbo.sysjobs where name= N'"+ jobName + "'");
		ResultSet rs = stmt.executeQuery();
		if (!rs.isBeforeFirst() ) {  
				
			} 
		else {
			rs.next();
			PreparedStatement stmtt = conn.prepareStatement(" EXEC msdb.dbo.sp_delete_job '" + rs.getString("job_id") + "'");
			stmtt.execute();
			stmtt.close();
		}
		stmt.close();
		conn.close();
	}
	@Override
	public void job_sil_S(String jobName, String dosya, Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		String cumle = "";
		cumle = "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";";
		conn = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		PreparedStatement stmt = conn.prepareStatement("SELECT job_id, [name] FROM msdb.dbo.sysjobs where name= N'"+ jobName + "'");
		ResultSet rs = stmt.executeQuery();
		if (!rs.isBeforeFirst() ) {  
				//System.out.println("Kaayit yok");
			} 
		else {
			System.out.println("Kaayit var");
			rs.next();
			PreparedStatement stmtt = conn.prepareStatement(" EXEC msdb.dbo.sp_delete_job '" + rs.getString("job_id") + "'");
			stmtt.execute();
			stmtt.close();
		}
		stmt.close();
		conn.close();
	}
	@Override
	public void job_baslat_L(String jobName, Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		String cumle = "";
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort()  +";instanceName=" + sbilgi.getIns() + ";";
		conn = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		PreparedStatement stmt = conn.prepareStatement("USE msdb  EXEC sp_start_job  N'"+ jobName + "'");
		stmt.execute();
		stmt.close();
		conn.close();
		
	}
	@Override
	public void job_baslat_S(String jobName, Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		String cumle = "";
		cumle = "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";";
		conn = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		PreparedStatement stmt = conn.prepareStatement("USE msdb  EXEC sp_start_job  N'"+ jobName + "'");
		stmt.execute();
		stmt.close();
		conn.close();
	}
	@Override
	public void job_olustur_L(String jobName, String dosya,String indexISIM , Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		String cumle = "";
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort()  +";instanceName=" + sbilgi.getIns() + ";";
		conn = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
	
		String sql = " USE [msdb]   "
							+ " BEGIN TRANSACTION "
							+ " DECLARE @ReturnCode INT "
							+ " SELECT @ReturnCode = 0 "
							+ " IF NOT EXISTS (SELECT name FROM msdb.dbo.syscategories WHERE name=N'Database Maintenance' AND category_class=1) "
							+ " BEGIN "
							+ " EXEC @ReturnCode = msdb.dbo.sp_add_category @class=N'JOB', @type=N'LOCAL', @name=N'Database Maintenance' "
							+ " IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback "
							+ " END" 
							+ " DECLARE @jobId BINARY(16) "
							+ " EXEC @ReturnCode =  msdb.dbo.sp_add_job @job_name=N'"+ jobName + "', "
							+ " @enabled=1, "
							+ " @notify_level_eventlog=0, "
							+ " @notify_level_email=0, "
							+ " @notify_level_netsend=0, "
							+ " @notify_level_page=0, "
							+ " @delete_level=0, "
							+ " @description=N'No description available.', "
							+ " @category_name=N'Database Maintenance', "
							+ " @job_id = @jobId OUTPUT"	
							+ " IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback"
							+ " EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'"+ dosya +"_Indexle', "
							+ " @step_id=1, "
							+ " @cmdexec_success_code=0, "
							+ " @on_success_action=1, "
							+ " @on_success_step_id=0, "
							+ " @on_fail_action=2, "
							+ " @on_fail_step_id=0, "
							+ " @retry_attempts=0, "
							+ " @retry_interval=0, "
							+ " @os_run_priority=0, @subsystem=N'TSQL', "
							+ " @command=N'USE ["+ dosya +"] "
							+ indexISIM 
							//+ " ALTER INDEX [IX_SATIRLAR] ON [dbo].[SATIRLAR] REBUILD PARTITION = ALL WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)"
							//+ " ALTER INDEX [IX_EVRAK] ON [dbo].[IZAHAT]      REBUILD PARTITION = ALL WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)"
							+ " ', "
							+ " @database_name=N'"+ dosya +"', "
							+ " @flags=0 "
							+ " IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback"
							+ " EXEC @ReturnCode = msdb.dbo.sp_update_job @job_id = @jobId, @start_step_id = 1"
							+ " IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback"
							+ " EXEC @ReturnCode = msdb.dbo.sp_add_jobschedule @job_id=@jobId, @name=N'"+ dosya +"_index', "
							+ " @enabled=1, "
							+ " @freq_type=4, "
							+ " @freq_interval=1, "
							+ " @freq_subday_type=1, "
							+ " @freq_subday_interval=0, "
							+ " @freq_relative_interval=0, "
							+ " @freq_recurrence_factor=0, "
							+ " @active_start_date=20231214, "
							+ " @active_end_date=99991231, "
							+ " @active_start_time=0, "
							+ " @active_end_time=235959 "
							+ " IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback"
							+ " EXEC @ReturnCode = msdb.dbo.sp_add_jobserver @job_id = @jobId"
							+ " IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback"
							+ " COMMIT TRANSACTION"
							+ " GOTO EndSave"
							+ " QuitWithRollback:"
							+ " IF (@@TRANCOUNT > 0) ROLLBACK TRANSACTION"
							+ " EndSave:" ;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.execute();
		stmt.close();
		conn.close();
		
	}
	@Override
	public void job_olustur_S(String jobName, String dosya,String indexISIM , Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		String cumle = "";
		cumle = "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";";
		conn = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
	
		String sql = " USE [msdb]   "
							+ " BEGIN TRANSACTION "
							+ " DECLARE @ReturnCode INT "
							+ " SELECT @ReturnCode = 0 "
							+ " IF NOT EXISTS (SELECT name FROM msdb.dbo.syscategories WHERE name=N'Database Maintenance' AND category_class=1) "
							+ " BEGIN "
							+ " EXEC @ReturnCode = msdb.dbo.sp_add_category @class=N'JOB', @type=N'LOCAL', @name=N'Database Maintenance' "
							+ " IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback "
							+ " END" 
							+ " DECLARE @jobId BINARY(16) "
							+ " EXEC @ReturnCode =  msdb.dbo.sp_add_job @job_name=N'"+ jobName + "', "
							+ " @enabled=1, "
							+ " @notify_level_eventlog=0, "
							+ " @notify_level_email=0, "
							+ " @notify_level_netsend=0, "
							+ " @notify_level_page=0, "
							+ " @delete_level=0, "
							+ " @description=N'No description available.', "
							+ " @category_name=N'Database Maintenance', "
							+ " @job_id = @jobId OUTPUT"	
							+ " IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback"
							+ " EXEC @ReturnCode = msdb.dbo.sp_add_jobstep @job_id=@jobId, @step_name=N'"+ dosya +"_Indexle', "
							+ " @step_id=1, "
							+ " @cmdexec_success_code=0, "
							+ " @on_success_action=1, "
							+ " @on_success_step_id=0, "
							+ " @on_fail_action=2, "
							+ " @on_fail_step_id=0, "
							+ " @retry_attempts=0, "
							+ " @retry_interval=0, "
							+ " @os_run_priority=0, @subsystem=N'TSQL', "
							+ " @command=N'USE ["+ dosya +"] "
							+ indexISIM 
							+ " ', "
							+ " @database_name=N'"+ dosya +"', "
							+ " @flags=0 "
							+ " IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback"
							+ " EXEC @ReturnCode = msdb.dbo.sp_update_job @job_id = @jobId, @start_step_id = 1"
							+ " IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback"
							+ " EXEC @ReturnCode = msdb.dbo.sp_add_jobschedule @job_id=@jobId, @name=N'"+ dosya +"_index', "
							+ " @enabled=1, "
							+ " @freq_type=4, "
							+ " @freq_interval=1, "
							+ " @freq_subday_type=1, "
							+ " @freq_subday_interval=0, "
							+ " @freq_relative_interval=0, "
							+ " @freq_recurrence_factor=0, "
							+ " @active_start_date=20231214, "
							+ " @active_end_date=99991231, "
							+ " @active_start_time=0, "
							+ " @active_end_time=235959 "
							+ " IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback"
							+ " EXEC @ReturnCode = msdb.dbo.sp_add_jobserver @job_id = @jobId"
							+ " IF (@@ERROR <> 0 OR @ReturnCode <> 0) GOTO QuitWithRollback"
							+ " COMMIT TRANSACTION"
							+ " GOTO EndSave"
							+ " QuitWithRollback:"
							+ " IF (@@TRANCOUNT > 0) ROLLBACK TRANSACTION"
							+ " EndSave:" ;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.execute();
		stmt.close();
		conn.close();
	}
	
}

//Agent Control
//DECLARE @agent NVARCHAR(512);
//SELECT @agent = COALESCE(N'SQLAgent$' + CONVERT(SYSNAME, SERVERPROPERTY('InstanceName')),  N'SQLServerAgent');
//EXEC master.dbo.xp_servicecontrol 'QueryState', @agent;

package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import LOGER_KAYIT.DOSYA_MSSQL;
import LOGER_KAYIT.ILOGER_KAYIT;
import LOGER_KAYIT.TXT_LOG;

public class KERESTE_MSSQL implements IKERESTE {
	static Connection con = null;
	static Statement stmt = null;

	@Override
	public void baglan() throws SQLException, ClassNotFoundException {
		
		String cumle = "jdbc:sqlserver://" + BAGLAN.kerDizin.cONN_STR + ";";
		con = DriverManager.getConnection(cumle,BAGLAN.kerDizin.kULLANICI,BAGLAN.kerDizin.sIFRESI);
		
	}

	@Override
	public void kER_SIFIR_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String cumle = "";
		if ( ! sbilgi.getPort().toString().equals("") )
		{
			sbilgi.setPort(  ":" + sbilgi.getPort() );
		}
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		String VERITABANI = "OK_Ker" + sbilgi.getKod();
		stmt = null;
		String sql =null;
		if (sbilgi.getDizin_yeri() == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "', FILENAME = N'" +  sbilgi.getDizin()	+ "\\" + VERITABANI + ".mdf ' ) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table(sbilgi.getFir_adi());
		//
		if (sbilgi.getDizin_yeri() == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG"+ "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "_LOG" + "', FILENAME = N'" +  sbilgi.getDizin()	+ "\\" + VERITABANI + "_LOG" + ".mdf' ) ";
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost " + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + "_LOG" + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MSSQL();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);

		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+ ".DB") == false)
		{
			String dsy = GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+ ".DB" ;
			GLOBAL.create_table_log(dsy,sbilgi.getFir_adi(),BAGLAN_LOG.kerLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi() );
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.kerLogDizin);
		//
		stmt.close();
		con.close();
		
	}

	@Override
	public void kER_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void create_table(String fir_adi) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void create_table_log() throws SQLException {
		String sql = "" ;
		sql = "CREATE TABLE [dbo].[LOGLAMA]("
				+ "	[TARIH] [datetime] NOT NULL,"
				+ "	[MESAJ] [nchar](100) NOT NULL,"
				+ "	[EVRAK] [nchar](15) NOT NULL,"
				+ "	[USER_NAME] [nchar](15) NULL"
				+ ") ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE NONCLUSTERED INDEX [IX_LOGLAMA] ON [dbo].[LOGLAMA](	[TARIH] ASC,	[EVRAK] ASC , [USER_NAME] ASC "
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		
	}

	

	@Override
	public void kER_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String ker_firma_adi() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
}

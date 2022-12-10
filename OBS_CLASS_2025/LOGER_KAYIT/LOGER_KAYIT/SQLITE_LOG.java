package LOGER_KAYIT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

import OBS_C_2025.DIZIN_BILGILERI;
import OBS_C_2025.GLOBAL;

public class SQLITE_LOG implements ILOGER_KAYIT{

	@Override
	public void Logla(String mesaj, String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException {
		try
		{
	   	 Class.forName("org.sqlite.JDBC");
	   	 Connection sQLITEconn = DriverManager.getConnection("jdbc:sqlite:" +GLOBAL.SURUCU +  dBILGI.mODUL   ) ;
  		 PreparedStatement stmt = null;
  		 String sql =  "INSERT INTO LOGLAMA (TARIH,MESAJ,EVRAK,USER_NAME) " +
   						"VALUES (?,?,?,?)";
    		 stmt = sQLITEconn.prepareStatement(sql);
    		 stmt.setDate(1, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
   			 stmt.setString(2, mesaj);
   			 stmt.setString(3, evrak);
   			 stmt.setString(4, GLOBAL.KULL_ADI);
    		 stmt.executeUpdate();
   			 stmt.close();
   			 sQLITEconn.close();
		}
		catch (Exception e){ 
			JOptionPane.showMessageDialog(null, e.getMessage(), "OBS SISTEM", JOptionPane.PLAIN_MESSAGE);
		}
}

	@Override
	public ResultSet log_rapor(String t1, String t2, String aciklama, String evrak, String user, DIZIN_BILGILERI dBILGI)
			throws ClassNotFoundException, SQLException {
		ResultSet	rss = null;
		try
		{
		   	 Connection sQLITEconn = DriverManager.getConnection("jdbc:sqlite:" +GLOBAL.SURUCU +  dBILGI.mODUL   ) ;
		   	 
		   	 SimpleDateFormat f = new SimpleDateFormat ("yyyy.MM.dd");
		   	 Date d = f.parse(t1);
		   	 long  tt1 = d.getTime();
			StringBuilder stb = new StringBuilder();
			f =  new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss.sss");
			 d = f.parse(t2 + " 23:59:59.998");
			 long  tt2 = d.getTime();
			stb.append(" SELECT strftime('%d.%m.%Y %H:%M:%S',datetime(TARIH/1000,'unixepoch')) as TARIH ,MESAJ,EVRAK,[USER_NAME] " ); 
			stb.append(" FROM   loglama  ") ; 

			stb.append(" WHERE  loglama.mesaj  LIKE '" + aciklama + "'") ;
			if ( ! t1.equals(""))
			{
				stb.append(" AND TARIH BETWEEN  '" + tt1 + "' AND '" + tt2 + "' ") ;
			}
			if ( ! evrak.equals("%") &&  ! evrak.equals("%%")) 
			{
				stb.append(" AND EVRAK  LIKE '" + evrak + "'");
			}
			if ( ! user.equals("%") &&  ! user.equals("%%")) 
			{
				stb.append(" AND USER_NAME  LIKE '" + user + "'");
			}
			stb.append(" ORDER BY TARIH ") ;
			String sql = stb.toString() ;
				PreparedStatement stmt =  sQLITEconn.prepareStatement(sql);
			rss = stmt.executeQuery();
				
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "OBS SISTEM", JOptionPane.PLAIN_MESSAGE);
		}
		//
		return rss;
		
	}

}

//File file = new File("C:\\OBS_SISTEM\\" + dBILGI.mODUL + ".txt");
//if (!file.exists()) {
//	file.createNewFile();
//}
//FileWriter fileWriter = new FileWriter(file, true);
//BufferedWriter bWriter = new BufferedWriter(fileWriter);
//String msj=  new java.sql.Timestamp(new java.util.Date().getTime()) +"\t"+mesaj  +"\t" + evrak +"\t"+ GLOBAL.KULL_ADI + "\n";
//bWriter.write(msj);
//bWriter.close();

// okuma

//File file = new File("C:\\OBS_SISTEM\\" + dBILGI.mODUL + ".txt");
//if (!file.exists()) {
//	file.createNewFile();
//}
//FileReader fileReader = new FileReader(file);
//BufferedReader br = new BufferedReader( fileReader );
//try (Scanner sc = new Scanner(br)) {
//	while (sc.hasNextLine()) 
//	{
//		String l =  sc.nextLine();  
//		String[] token = l.split("\t");
//		//System.out.println( token[0] + "=" +token[1] +  "=" + token[2] +  "=" +token[3] );
//	}
//} 
//br.close();

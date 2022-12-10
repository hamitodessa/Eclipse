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
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

import OBS_C_2025.DIZIN_BILGILERI;
import OBS_C_2025.GLOBAL;

public class TXT_LOG implements ILOGER_KAYIT{

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
		try
		{
		}
		catch (Exception e){    }
		//
		return null;
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


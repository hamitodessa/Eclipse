package LOGER_KAYIT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import OBS_C_2025.DIZIN_BILGILERI;
import OBS_C_2025.GLOBAL;

public class TXT_LOG implements ILOGER_KAYIT{

	@Override
	public void Logla(String mesaj, String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException {
		try
		{
			File file = new File("C:\\OBS_SISTEM\\" + dBILGI.mODUL + ".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter bWriter = new BufferedWriter(fileWriter);
			String msj=  new java.sql.Timestamp(new java.util.Date().getTime()) +"\t"+mesaj  +"\t" + evrak +"\t"+ GLOBAL.KULL_ADI + "\n";
			bWriter.write(msj);
			bWriter.close();
		}
		catch (IOException e){    }
	}

	@Override
	public ResultSet log_rapor(String t1, String t2, String aciklama, String evrak, String user, DIZIN_BILGILERI dBILGI)
			throws ClassNotFoundException, SQLException {
		try
		{
			File file = new File("C:\\OBS_SISTEM\\" + dBILGI.mODUL + ".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader( fileReader );
			try (Scanner sc = new Scanner(br)) {
				while (sc.hasNextLine()) 
				{
					String l =  sc.nextLine();  
					String[] token = l.split("\t");
					//System.out.println( token[0] + "=" +token[1] +  "=" + token[2] +  "=" +token[3] );
				}
			} 
			br.close();
		}
		catch (IOException e){    }
		//
		return null;
	}

}

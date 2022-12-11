package LOGER_KAYIT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import OBS_C_2025.DIZIN_BILGILERI;
import OBS_C_2025.GLOBAL;

public class TXT_LOG  implements ILOGER_KAYIT {

	@Override
	public void Logla(String mesaj, String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException {

			try
		{
			File file = new File("C:\\OBS_SISTEM\\" + dBILGI.mODULADI + "_log" + ".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter bWriter = new BufferedWriter(fileWriter);
			String msj=  new java.sql.Timestamp(new java.util.Date().getTime()) +"\t"+mesaj  +"\t" + evrak +"\t"+ GLOBAL.KULL_ADI + "\n";
			bWriter.write(msj);
			bWriter.close();
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "OBS SISTEM", JOptionPane.PLAIN_MESSAGE);
		}
	}

	@Override
	public ResultSet log_rapor(String t1, String t2, String aciklama, String evrak, String user, DIZIN_BILGILERI dBILGI)
			throws ClassNotFoundException, SQLException {
				return null;
	
	}

	@Override
	public DefaultTableModel log_txt_rapor(String t1, String t2, String aciklama, String evrak, String user,
			DIZIN_BILGILERI dBILGI) {
		DefaultTableModel model =new DefaultTableModel(new String[] {"TARIH", "MESAJ", "EVRAK", "USER_NAME"}, 0);
	
		try
		{
			//File file = new File("C:\\OBS_SISTEM\\" + dBILGI.mODULADI + ".txt");  //OK_Car019_log.txt
			File file = new File("C:\\OBS_SISTEM\\"+ dBILGI.mODULADI + "_log" + ".txt");  //OK_Car019_log.txt
			if (!file.exists())
			{
	
			}
			else
			{
				FileReader fileReader = new FileReader(file);
				BufferedReader br = new BufferedReader( fileReader );
				try (Scanner sc = new Scanner(br)) {
					while (sc.hasNextLine()) 
					{
						String l =  sc.nextLine();  
						String[] token = l.split("\t");
						Vector<String> data = new Vector<String>();
						data.add( token[0]);
						data.add( token[1]);
						data.add( token[2]);
						data.add( token[3]);
						model.addRow(data);
					}
				} 
	
				br.close();
			}

		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "OBS SISTEM", JOptionPane.PLAIN_MESSAGE);
		}
		return model ;
	}


}


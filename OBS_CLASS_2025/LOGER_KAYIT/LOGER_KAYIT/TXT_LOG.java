package LOGER_KAYIT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import OBS_C_2025.DIZIN_BILGILERI;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.lOG_BILGI;

@SuppressWarnings("unused")
public class TXT_LOG  implements ILOGER_KAYIT {

	@Override
	public void Logla(lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException {
		try
		{
			File file = new File(GLOBAL.LOG_SURUCU  + dBILGI.mODULADI  + ".txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			Writer bWriter = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(file, true), StandardCharsets.UTF_8));
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss sss");
			String formatli = formatter.format(new Date());
			String msj=  formatli +"\t" + lBILGI.getmESAJ().replace("\t", "").toString().trim()  + "\t" + lBILGI.geteVRAK() +"\t"+ GLOBAL.KULL_ADI + "\r\n";
			bWriter.write(msj);
			bWriter.close();
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Text Dosya Yazma...," +e.getMessage(), "OBS SISTEM", JOptionPane.PLAIN_MESSAGE);
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
		String nerde = "" ;
		try
		{
			File file = new File(GLOBAL.LOG_SURUCU + dBILGI.mODULADI  + ".txt");  //OK_Car019_log.txt
			if (!file.exists())
			{
			}
			else
			{
				FileReader fileReader = new FileReader(file, StandardCharsets.UTF_8);
				BufferedReader br = new BufferedReader( fileReader );
				try (Scanner sc = new Scanner(br)) {
					while (sc.hasNextLine()) 
					{
						String l =  sc.nextLine();  
						if (l.trim().isEmpty())
						{					}
						else
						{
							String[] token = l.split("\t");
							nerde =  token[0] ;
							Vector<String> data = new Vector<String>();
							Date aiLKT =  new SimpleDateFormat("dd.MM.yyyy").parse(t1);  
							Date asLKT =  new SimpleDateFormat("dd.MM.yyyy").parse(t2);  
							Date iLKT =  new SimpleDateFormat("yyyy.MM.dd").parse(token[0].substring(0,10));  
							Date sLKT =  new SimpleDateFormat("yyyy.MM.dd").parse(token[0].substring(0,10));  
							if (  iLKT.after(aiLKT) && sLKT.before(asLKT))  // TARIH
							{
								if(evrak.equals("%%"))  // EVRAK
								{
									if(user.equals("%%"))  // USER
									{
										if(aciklama.equals("%%"))// MESAJ KONTROL 
										{
											data.add( token[0]);
											data.add( token[1]);
											data.add( token[2]);
											data.add( token[3]);
											model.addRow(data);
										}
										else 
										{
											String aranan =aciklama.substring(1, aciklama.length()) ;
											aranan = aranan.substring(0,aranan.length() -1);
											if ( token[1].toUpperCase().contains(aranan.toUpperCase() ) )
											{
												data.add( token[0]);
												data.add( token[1]);
												data.add( token[2]);
												data.add( token[3]);
												model.addRow(data);
											}
										} // MESAJ KONTROL
									}
									else // USER ARANAN VARSA
									{
										String aranan =user.substring(1, user.length()) ;
										aranan = aranan.substring(0,aranan.length() -1);
										if ( token[3].toUpperCase().contains(aranan.toUpperCase() ) )
										{
											if(aciklama.equals("%%"))// MESAJ KONTROL 
											{
												data.add( token[0]);
												data.add( token[1]);
												data.add( token[2]);
												data.add( token[3]);
												model.addRow(data);
											}
											else 
											{
												String arana =aciklama.substring(1, aciklama.length()) ;
												arana = arana.substring(0,arana.length() -1);
												if ( token[1].toUpperCase().contains(aranan.toUpperCase() ) )
												{
													data.add( token[0]);
													data.add( token[1]);
													data.add( token[2]);
													data.add( token[3]);
													model.addRow(data);
												}
											} // MESAJ KONTROL
										}
									} // USER
								}
								else  // EVRAK
								{
									String aran =evrak.substring(1, evrak.length()) ;
									aran = aran.substring(0,aran.length() -1);
									if ( token[2].toUpperCase().contains(aran.toUpperCase() ) )  // EVRAK VAR
									{
										if(user.equals("%%"))  // USER
										{
											if(aciklama.equals("%%"))// MESAJ KONTROL 
											{
												data.add( token[0]);
												data.add( token[1]);
												data.add( token[2]);
												data.add( token[3]);
												model.addRow(data);
											}
											else 
											{
												String aranan =aciklama.substring(1, aciklama.length()) ;
												aranan = aranan.substring(0,aranan.length() -1);
												if ( token[1].toUpperCase().contains(aranan.toUpperCase() ) )
												{
													data.add( token[0]);
													data.add( token[1]);
													data.add( token[2]);
													data.add( token[3]);
													model.addRow(data);
												}
											} // MESAJ KONTROL
										}
										else // USER ARANAN VARSA
										{
											String aranan =user.substring(1, user.length()) ;
											aranan = aranan.substring(0,aranan.length() -1);
											if ( token[3].toUpperCase().contains(aranan.toUpperCase() ) )
											{
												if(aciklama.equals("%%"))// MESAJ KONTROL 
												{
													data.add( token[0]);
													data.add( token[1]);
													data.add( token[2]);
													data.add( token[3]);
													model.addRow(data);
												}
												else 
												{
													String arana =aciklama.substring(1, aciklama.length()) ;
													arana = arana.substring(0,arana.length() -1);
													if ( token[1].toUpperCase().contains(aranan.toUpperCase() ) )
													{
														data.add( token[0]);
														data.add( token[1]);
														data.add( token[2]);
														data.add( token[3]);
														model.addRow(data);
													}
												} // MESAJ KONTROL
											}
										} // USER
									}
								}
							} // TARIH
						} // SATIR BOSSA
					}
				} 
				br.close();
			}
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null,  e.getMessage() , "Text Dosyasi Log Okuma", JOptionPane.PLAIN_MESSAGE);
		}
		return model ;
	}
}


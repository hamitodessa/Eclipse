package VT_YEDEK;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import OBS_C_2025.BACKUP_RESTORE;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.SQL_BACKUP;

import javax.swing.JSplitPane;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class BASLA extends JFrame {
	static SQL_BACKUP sqll = new SQL_BACKUP();
	private static JPanel contentPane;
	GLOBAL glb = new GLOBAL();
	public static   JPanel pPanel;
	public  static List<String> gorevLER  = new ArrayList<>();
	VT_ANA_CLASS oac = new VT_ANA_CLASS();
	static Timer timerr ;
	static Timer timerEMIR;
	static JProgressBar progressBar ;
	static JProgressBar progressBar1 ;
	public String geleCEKTAR ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BASLA frame = new BASLA();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	@SuppressWarnings("static-access")
	public BASLA() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(150,0));
		panel.setLayout(null);
		splitPane.setLeftComponent(panel);
		 pPanel = new JPanel(new GridLayout(0, 1, 3, 3));
	     pPanel.setBorder(new TitledBorder("Emirler"));
        
	     JSplitPane sagPane = new JSplitPane();
	     sagPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	     sagPane.setDividerSize(0);
	     sagPane.setResizeWeight(1.0);
	 
	     
	        JScrollPane scrollPane = new JScrollPane(pPanel,	        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,	                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        
	        splitPane.setRightComponent(sagPane);
	    	sagPane.setLeftComponent(scrollPane);
	      
	    	
	    	progressBar = new JProgressBar();
	    	progressBar.setBounds(0, 24, 714, 20);
	    	progressBar1 = new JProgressBar();
	    	progressBar1.setBounds(0, 2, 714, 20);
	    	JPanel sagaltPane = new JPanel();
	        sagaltPane .setMinimumSize(new Dimension(0,50));
	        sagaltPane .setLayout(null);
	        
	        
	    	sagPane.setRightComponent(sagaltPane);
	    	
	    	////
	    	//sagaltPane.setLayout(new BoxLayout(sagaltPane, BoxLayout.Y_AXIS));
	    	
	    	///
	    	
	    	//progressBar.setBounds(0, 20, 714, 18);
	    	sagaltPane.add(progressBar);
	    	sagaltPane.add(progressBar1);
	    	
	    	
	    
			
			JButton btnNewButton = new JButton("Yeni Emir");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					oac.yENI_EMIR = true ;
					EMIR emr = new EMIR();
					durdur();
					emr.setVisible(true);
				}
			});
			btnNewButton.setBounds(10, 26, 130, 23);
			panel.add(btnNewButton);
			
			JButton btnNewButton_2 = new JButton("olan emir");
			btnNewButton_2.addActionListener(new ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(ActionEvent e) {
					
					oac.yENI_EMIR = false ;
					VT_ANA_CLASS.EMIR_ADI="hamit" ;
					EMIR emr = new EMIR();
				
					emr.setVisible(true);
					dispose();
				}
			});
			btnNewButton_2.setBounds(10, 60, 130, 23);
			panel.add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("deger degis");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
						Component[] components = pPanel.getComponents();
					/////////////////////
					String compoName = "";
					int i = 0;
					 for (Component compoo : components) 
					 {
						 
						// for(int i = 0 ; i < components.length;i++)
						// {
							   System.out.println(  i + "== "+  ((TitledBorder) ((JComponent) components[i]).getBorder()).getTitle()  ) ;
						// }
				            compoName = compoo.getClass().getName();
				     //       System.out.println(   compoName ) ;
						                if (compoo  instanceof JPanel)
					                {
					                   JPanel dp = (JPanel) compoo;
					                   String emirAdi =  ((TitledBorder) ((JComponent) components[i]).getBorder()).getTitle()  ;
//					               if( ((TitledBorder) dp.getBorder()).getTitle().equals(emirAdi))
//					                {
//					            	   System.out.println( "198=" +  ((TitledBorder) dp.getBorder()).getTitle());
				            		Component[] emirPanel = ((Container) components[i]).getComponents();
										 for (Component compo : emirPanel) 
										 {
											 System.out.println(compo.getName());
//										         
											 ///
//										                if (compo  instanceof JButton)
//										                {
//										                   JButton dpp = (JButton) compo;
//										                   System.out.println("208========" +dpp.getText());
//										                   String qwe = dpp.getText().trim();
//										                   if (qwe.equals( ((TitledBorder) ((JComponent) components[i]).getBorder()).getTitle()))
//										                   {
//										                	   GOREV.denemADI=   ((TitledBorder) ((JComponent) components[i]).getBorder()).getTitle()  ;
//										                  System.out.println("+++++++++" +dpp.getText());
//										                   dpp.doClick();
//										                   }
//										           //    dpp.setText("Deneme");
//										                }
//									            ///
										 }
//					           //     Font font2 = new Font("Verdana", Font.ITALIC, 12);
//					          //         TitledBorder border = new TitledBorder("hamit");
//					          //         border.setTitleFont(font2);
//					            //       ((TitledBorder) dp.getBorder()).setTitle("Hamit Emir");
//					               }
					           //     dp.repaint();
					                }
				            ///
						                i = i +1 ;
					 }
					
					/////////////////////
					
//					Component[] emirPanel = ((Container) components[0]).getComponents();
//					
//					 String componentName;
//					 for (Component compo : emirPanel) 
//					 {
//				            componentName = compo.getClass().getName();
//					         //   System.out.println(compo.getClass().getName().substring(componentName.indexOf("swing.") + "swing.".length(), componentName.length()));
//					           ///
//					                if (compo  instanceof JButton)
//					                {
//					                   JButton dp = (JButton) compo;
//					                   System.out.println(dp.getText());
//					               dp.setText("Deneme");
//					                }
//				            ///
//					 }
					       
				}
			});
			btnNewButton_3.setBounds(10, 275, 130, 23);
			panel.add(btnNewButton_3);
			
			
			
			JButton btnNewButton_1 = new JButton("ftp deneme");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					 try {
						sqll.genel_kayit_durum( "Suadiye Yedekleme", true, new Date());
					} catch (InvalidKeyException | ClassNotFoundException | NoSuchAlgorithmException
							| NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException
							| BadPaddingException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//	gorevLER.add("hamit");
				}
			});
			btnNewButton_1.setBounds(10, 455, 89, 23);
			panel.add(btnNewButton_1);
			
		
			
		//
			File tmpDir = new File(glb.SURUCU + glb.SQL_BACKUP);
			boolean exists = tmpDir.exists();
			if (exists == false )
			{   
				glb.backup_dosya_olustur();
			}
			emirDOLDUR();
			baslat();
			emirKONTROL();
	}
	@SuppressWarnings({ "static-access", "unused" })
	public static void emirDOLDUR() throws ClassNotFoundException, SQLException, InterruptedException, NumberFormatException, ParseException
	{
		ResultSet rss ;
		rss = sqll.emirLER();
		pPanel.removeAll();
		pPanel.revalidate();
		pPanel.repaint();
		if (!rss.isBeforeFirst() ) {  
			return;
		} 

		List<Emir_Bilgiler> emirlerr = new ArrayList<Emir_Bilgiler>();
		
		int i = 0;
		while (rss.next())
		{
			String  tarih ="" ;
			if(rss.getDate("SON_YUKLEME") != null)
			{
				SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				tarih = f.format(	rss.getDate("SON_YUKLEME"));
			}
			//System.out.println(rss.getString("EMIR_ISMI"));
			String drm = rss.getBoolean("DURUM") == true ? "Aktiv" : "Pasiv" ;
			emirlerr.add( new Emir_Bilgiler(rss.getString("EMIR_ISMI"), rss.getBoolean("SON_DURUM") == true ? "Yedeklendi" : "Yedeklenmedi",
					2,tarih,"",rss.getString("EMIR_ACIKLAMA"), drm,sqll.ftp_NERESI(rss.getString("EMIR_ISMI"))));
					
		}
	sqll.ccon.close();
		try {
			 for(int y = 0; y < emirlerr.size(); y++) 
			{
				
				GOREV  tt = new GOREV();
				pPanel.add(tt.getShowRoomPanel(emirlerr.get(y).getEmirAdi(),emirlerr.get(y).getSondurUM()  , 
						emirlerr.get(y).getDbSAYI()  ,
						emirlerr.get(y).getTarIH(),""   ,emirlerr.get(y).getAcikLAMA() ,emirlerr.get(y).getDurUM(),
						emirlerr.get(y).getFtpNERESI()));
			

			}
		
		
		} catch (NumberFormatException | ClassNotFoundException | SQLException | InterruptedException
				| ParseException e) {
			// TODO Auto-generated catch block
		System.out.println("basla hata   302" + e.getMessage());
		}
		
	}
	private static void yedekLE() throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		durdur() ;
		if (gorevLER.size() !=0)
		{
			System.out.println(gorevLER.size() +" == gorev adeti ");
		}

		for (int i = 0; i < gorevLER.size(); i++) 
		{
			durdur2();
			System.out.println(gorevLER.get(i) +" =BACKUP ALINIYOR= "+ i);
			backUP(gorevLER.get(i));
			
			///
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String simDI = df.format(new Date());
			DateFormat dff = new SimpleDateFormat("dd.MM.yyyy HH:mm");				
			simDI = dff.format(new Date());
			
			gelecekDEGIS(gorevLER.get(i),""  ,simDI);
			emirKONTROL();
			try {
				sqll.genel_kayit_durum(gorevLER.get(i), true, new Date());
				
			} catch (ClassNotFoundException | SQLException | NumberFormatException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
			}
			
			
			///
			gorevLER.remove(gorevLER.get(i));
			//

			//
		}
		baslat();
	}
	@SuppressWarnings("static-access")
	private static void backUP(String emirADI) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		ResultSet rs = sqll.emirBILGI(emirADI);
		boolean  SQL_YEDEK_MI = true ;
		SQL_YEDEK_MI = rs.getBoolean("SQL_YEDEK");
		        if (SQL_YEDEK_MI == false )
		        {
		     sqll.ccon.close();
		            return ;
		        }
		 
	if (rs.getString("INSTANCE").equals("MS SQL"))
	{
		   sqll.ccon.close();
		msSQL(emirADI);
	}
	else   if (rs.getString("INSTANCE").equals("MY SQL"))
	{
		   sqll.ccon.close();
		mySQL(emirADI);
	}

	}
	@SuppressWarnings("static-access")
	private static void msSQL(String emirADI) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		ResultSet rss = sqll.dbLISTE(emirADI);
		if (!rss.isBeforeFirst() ) {  
			sqll.ccon.close();
			sqll.Logla(emirADI,  "Yuklenecek Dosya Secilmemis....");
			return;
		} 
		sqll.ccon.close();
		GLOBAL glb = new GLOBAL();
		if (glb.internet_kontrol() == false)
		{
			sqll.Logla(emirADI,  "Internet Baglantisi Yok");
			return ;
		}
		
		ResultSet rs = sqll.surBILGI(emirADI);
		if (!rs.isBeforeFirst() ) {  
			sqll.Logla(emirADI,  "FTP Bos");
			sqll.ccon.close();
				return;
			} 
		String neresi = rs.getString("NERESI");
		sqll.ccon.close();
		if (neresi.equals("FTP"))
		{
			msSQL_FTP(emirADI);
		}
		else // yerel surucu
		{
			
		}
	}
	@SuppressWarnings("static-access")
	private static void msSQL_FTP(String emirADI) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		Runnable runner = new Runnable()
		{ 
			public void run() {

				try
				{
					Login_Progres_Bar_Temizle1();
					contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					ResultSet rs =  sqll.serBILGI(emirADI);
					String port =  rs.getString("PORT");
					String  insT = rs.getString("INSTANCE");
					String kulL = rs.getString("KULLANICI");
					String pwD = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(rs.getBytes("SIFRE"));
					sqll.ccon.close();
					
					ResultSet rss = sqll.dbLISTE(emirADI);
					List<String> dbList  = new ArrayList<>();
					while(rss.next())
					{
						dbList.add(rss.getString("DB_ADI"));
					}
					sqll.ccon.close();
					
					ResultSet rrs =  sqll.surBILGI(emirADI);
					Date tar = new Date();
					DateFormat  df = new SimpleDateFormat("ddMMyyyyHHmm");
					String tarih = df.format(tar);
					sqll.Logla(emirADI,  "Yedeklenmeye Baslandi........");
					//
					progressBar1.setMaximum((int) dbList.size());
					progressBar1.setStringPainted(true);
						//
					for(int i= 0  ; i < dbList.size() ; i++)
					{
						Lgn_Progres_Bar1((int) dbList.size(),(int) i+1);
						sqll.msBackup(port,insT  ,kulL,pwD,dbList.get(i)  ,	tarih + "_" + dbList.get(i));
						sqll.Logla(emirADI, dbList.get(i) +  "    Backup Alindi........");
						String  dosya,dzip ;
						dosya =  "C:\\OBS_SISTEM\\BACKUP\\" + tarih +  "_"  +   dbList.get(i) + ".bak" ;
						dzip =  tarih + "_" + dbList.get(i) + ".zip" ;
						BACKUP_RESTORE. BackupdbtoZIP(dosya , dzip);
						sqll.Logla(emirADI,  tarih + "_" + dbList.get(i) + ".zip" + "   Zip Haline Getirildi.....");
						upLOADtoFTP(rrs.getString("HOST") , Integer.parseInt(rrs.getString("PORT")),rrs.getString("KULLANICI"), ENCRYPT_DECRYPT_STRING.dCRYPT_manual(rrs.getBytes("SIFRE"))
								, dzip ,rrs.getString("SURUCU"),emirADI ) ;
						sqll.Logla(emirADI,  tarih + "_" + dbList.get(i) + ".zip" + "   FTP Yuklemesi Yapildi.....");
						File myObj = new File(dosya); 
						myObj.delete();
						sqll.Logla(emirADI, dbList.get(i) + ".bak"  +"     Backup Dosyasi silindi.......");
						myObj = new File(  "C:\\OBS_SISTEM\\BACKUP\\" + dzip); 
						myObj.delete();
						sqll.Logla(emirADI,  tarih + "_" + dbList.get(i) + ".zip" + "   Zip  Dosyasi Silindii.....");
					}
					sqll.ccon.close();
					Login_Progres_Bar_Temizle1();
					Login_Progres_Bar_Temizle();
					contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
				catch (SQLException | InvalidKeyException  | NoSuchAlgorithmException | NoSuchPaddingException | IOException | IllegalBlockSizeException | BadPaddingException | ClassNotFoundException | NumberFormatException | InterruptedException e)
				{
					contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						try {
							sqll.Logla(emirADI, e.getMessage());
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}    // 
				} 
			}
		};
		//// Progress Bar
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
	private static void mySQL(String emirADI)
	{

	}
	static void durdur()
	{
		if (timerr != null)
		{	
			timerr.cancel();
			timerr.purge();
		}
	}
	static void durdur2()
	{
		if (timerEMIR != null)
		{	
			timerEMIR.cancel();
			timerEMIR.purge();
		}
	}
	static void Lgn_Progres_Bar(int max, int deger) throws InterruptedException
	{
		progressBar.setValue(deger);
	}
	static void Login_Progres_Bar_Temizle()
	{
		progressBar.setMaximum(0);
		progressBar.setValue(0);
		progressBar.setStringPainted(false);
	}
	static void Lgn_Progres_Bar1(int max, int deger) throws InterruptedException
	{
		progressBar1.setValue(deger);
	}
	static void Login_Progres_Bar_Temizle1()
	{
		progressBar1.setMaximum(0);
		progressBar1.setValue(0);
		progressBar1.setStringPainted(false);
	}
	static  void baslat()
	{
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run()
			{
				try {
					yedekLE();
				} catch (ClassNotFoundException | SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IOException | IllegalBlockSizeException | BadPaddingException e) {
						e.printStackTrace();
				}
			}
		};
		timerr = new Timer();
		timerr.schedule(timerTask, 0, 5000);
	}
	private static void emirKONTROL()
	{
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run()
			{
				Component[] components = pPanel.getComponents();
				int i = 0;
				for (Component compoo : components) 
				{
					if (compoo  instanceof JPanel)
					{
						JPanel dp = (JPanel) compoo;
						String emirAdi =  ((TitledBorder) ((JComponent) components[i]).getBorder()).getTitle()  ;
						if( ((TitledBorder) dp.getBorder()).getTitle().equals(emirAdi))
						{
							Component[] emirPanel = ((Container) components[i]).getComponents();
							for (Component compo : emirPanel) 
							{
								///
								if (compo  instanceof JButton)
								{
									JButton dpp = (JButton) compo;
									String qwe = dpp.getText().trim();
									if (qwe.equals( ((TitledBorder) ((JComponent) components[i]).getBorder()).getTitle()))
									{
										GOREV.denemADI=   ((TitledBorder) ((JComponent) components[i]).getBorder()).getTitle()  ;
										
										try {
											Thread.sleep(500);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										System.out.println("++++599+++++" +dpp.getText());
										dpp.doClick();
									}
								}
							}
						}
					}
					i = i +1 ;
				}
			}
		};
		timerEMIR = new Timer();
		timerEMIR.schedule(timerTask, 0, 800);
	}
	public static void upLOADtoFTP(String server , int port , String user , String pass , String okunacakDB , String ftpSURUCU,String emirADI ) throws InterruptedException, ClassNotFoundException, SQLException
	{
				FTPClient ftpClient = new FTPClient();
				try {
					ftpClient.connect(server, port);
					ftpClient.login(user, pass);
					ftpClient.enterLocalPassiveMode();
					ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

					File secondLocalFile = new File(okunacakDB);
					String secondRemoteFile =  ftpSURUCU +  "/" + okunacakDB ;
					InputStream inputStream = new FileInputStream(secondLocalFile);

					OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
					byte[] bytesIn = new byte[4096];
					int read = 0;

					//////
					Login_Progres_Bar_Temizle();
					Path path = Paths.get(okunacakDB);
					long bytes = Files.size(path);
					progressBar.setMaximum((int) bytes);
					progressBar.setStringPainted(true);
					int inen= 0 ;
					while ((read = inputStream.read(bytesIn)) != -1) 
					{
						inen += read ;
						Lgn_Progres_Bar((int) bytes,(int) inen);
						outputStream.write(bytesIn, 0, read);
					}
					inputStream.close();
					outputStream.close();

					boolean completed = ftpClient.completePendingCommand();
					if (completed) {
						sqll.Logla(emirADI,  "The file is uploaded successfully.");
					}

				} catch (IOException | InterruptedException | ClassNotFoundException | SQLException ex) {
					try {
						sqll.Logla(emirADI,   ex.getMessage());
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ex.printStackTrace();
				} finally {
					try {
						if (ftpClient.isConnected()) {
							ftpClient.logout();
							ftpClient.disconnect();
						}
					} catch (IOException ex) {
						try {
							sqll.Logla(emirADI,  ex.getMessage());
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
						ex.printStackTrace();
					}
				}
		}
private static void gelecekDEGIS(String emirADI , String eskiTAR, String geleCEK)
{
	Component[] components = pPanel.getComponents();
	int i = 0;
	for (Component compoo : components) 
	{
		if (compoo  instanceof JPanel)
		{
			JPanel dp = (JPanel) compoo;
			if( ((TitledBorder) dp.getBorder()).getTitle().equals(emirADI))
			{
				Component[] emirPanel = ((Container) components[i]).getComponents();
				for (Component compo : emirPanel) 
				{
					 System.out.println(compo.getName());
						if (compo  instanceof JLabel)
					{
						JLabel dpp = (JLabel) compo;
							if (compo.getName() == ("lblgel"))
						{
							dpp.setText(geleCEK);
							break;
						}
					}
				}
			}
		}
		i = i +1 ;
	}
}
}
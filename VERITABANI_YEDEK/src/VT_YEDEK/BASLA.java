package VT_YEDEK;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import OBS_C_2025.BACKUP_RESTORE;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.SQL_BACKUP;

import javax.swing.JSplitPane;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class BASLA extends JFrame {
	static SQL_BACKUP sqll = new SQL_BACKUP();
	private JPanel contentPane;
	GLOBAL glb = new GLOBAL();
	public static   JPanel pPanel;
	public  static List<String> gorevLER  = new ArrayList<>();
	VT_ANA_CLASS oac = new VT_ANA_CLASS();
	static Timer timerr ;
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
	     sagPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	     
	        JScrollPane scrollPane = new JScrollPane(pPanel,	        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,	                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        
	        splitPane.setRightComponent(sagPane);
	    	sagPane.setLeftComponent(scrollPane);
	      
	    	
	    	JProgressBar progressBar = new JProgressBar();
	    	progressBar.setBounds(0, 24, 714, 20);
	    	JProgressBar progressBar1 = new JProgressBar();
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
				@SuppressWarnings("static-access")
				public void actionPerformed(ActionEvent e) {
					
					oac.yENI_EMIR = true ;
					EMIR emr = new EMIR();
				
					emr.setVisible(true);
					dispose();
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
				public void actionPerformed(ActionEvent e) {
					Component[] components = pPanel.getComponents();
					/////////////////////
					String compoName = "";
					 for (Component compoo : components) {
				            compoName = compoo.getClass().getName();
						                if (compoo  instanceof JPanel)
					                {
					                   JPanel dp = (JPanel) compoo;
					                   String emirAdi = "hamit";
					                System.out.println(   ((TitledBorder) dp.getBorder()).getTitle());
					                if( ((TitledBorder) dp.getBorder()).getTitle().equals(emirAdi))
					                {
					           //     Font font2 = new Font("Verdana", Font.ITALIC, 12);
					          //         TitledBorder border = new TitledBorder("hamit");
					          //         border.setTitleFont(font2);
					                   ((TitledBorder) dp.getBorder()).setTitle("Hamit Emir");
					                }
					                dp.repaint();
					                }
				            ///
					 }
					
					/////////////////////
					
					Component[] emirPanel = ((Container) components[0]).getComponents();
					
					 String componentName;
					 for (Component compo : emirPanel) {
				            componentName = compo.getClass().getName();
					         //   System.out.println(compo.getClass().getName().substring(componentName.indexOf("swing.") + "swing.".length(), componentName.length()));
					           ///
					                if (compo  instanceof JButton)
					                {
					                   JButton dp = (JButton) compo;
					                   System.out.println(dp.getText());
					               dp.setText("Deneme");
					                }
				            ///
					 }
					       
				}
			});
			btnNewButton_3.setBounds(10, 275, 89, 23);
			panel.add(btnNewButton_3);
			
			JButton btnNewButton_4 = new JButton("emiradi donus");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			
					for (int i = 0; i < gorevLER.size(); i++) {
					    String stringss = gorevLER.get(i);
					   
					    System.out.println(stringss);
					}
				
				}
			});
			btnNewButton_4.setBounds(10, 330, 130, 23);
			panel.add(btnNewButton_4);
			
			JButton btnNewButton_1 = new JButton("ftp deneme");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gorevLER.add("hamit");
				}
			});
			btnNewButton_1.setBounds(10, 455, 89, 23);
			panel.add(btnNewButton_1);
			
		//
			@SuppressWarnings("static-access")
			File tmpDir = new File(glb.SURUCU + glb.SQL_BACKUP);
			boolean exists = tmpDir.exists();
			if (exists == false )
			{   
				glb.backup_dosya_olustur();
			}
			
			oac.EMIR_ADI = "hamit";
			
			emirDOLDUR();
			
			baslat();
	}
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

		while(rss.next())
		{
			String  tarih ="" ;
			if(rss.getDate("SON_YUKLEME") != null)
			{
				SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				tarih = f.format(	rss.getDate("SON_YUKLEME"));
			}
			pPanel.add(GOREV.getShowRoomPanel(rss.getString("EMIR_ISMI"),rss.getBoolean("SON_DURUM") == true ? "Yedeklendi" : "Yedeklenmedi"  , 
					sqll.dbSAYISI(rss.getString("EMIR_ISMI"))  ,
					tarih,""   ,rss.getString("EMIR_ACIKLAMA") ,rss.getBoolean("DURUM") == true ? "Aktiv" : "Pasiv",
							sqll.ftp_NERESI(rss.getString("EMIR_ISMI"))));
		}
	}
	private void yedekLE() throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		durdur() ;
		for (int i = 0; i < gorevLER.size(); i++) 
		{
		    backUP(gorevLER.get(i));
		    
		    gorevLER.remove(gorevLER.get(i));
		}
		baslat();
	}
	private void backUP(String emirADI) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
	
		ResultSet rs = sqll.emirBILGI(emirADI);
		boolean  SQL_YEDEK_MI = true ;
		SQL_YEDEK_MI = rs.getBoolean("SQL_YEDEK");
		        if (SQL_YEDEK_MI == false )
		        {
		       //     diger_dosya()   ;
		            return ;
		        }
		 
	if (rs.getString("INSTANCE").equals("MS SQL"))
	{
		msSQL(emirADI);
	}
	else   if (rs.getString("INSTANCE").equals("MY SQL"))
	{
		mySQL(emirADI);
	}

	}
	private void msSQL(String emirADI) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		ResultSet rss = sqll.dbLISTE(emirADI);
		if (!rss.isBeforeFirst() ) {  
			sqll.Logla(emirADI,  "Yuklenecek Dosya Secilmemis....");
			return;
		} 
		GLOBAL glb = new GLOBAL();
		if (glb.internet_kontrol() == false)
		{
			sqll.Logla(emirADI,  "Internet Baglantisi Yok");
			return ;
		}
		
		ResultSet rs = sqll.surBILGI(emirADI);
		if (!rs.isBeforeFirst() ) {  
			sqll.Logla(emirADI,  "FTP Bos");
				return;
			} 
		String neresi = rs.getString("NERESI");
		
		if (neresi.equals("FTP"))
		{
			msSQL_FTP(emirADI);
		}
		else // yerel surucu
		{
			
		}
	}
	@SuppressWarnings("static-access")
	private void msSQL_FTP(String emirADI) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		ResultSet rs =  sqll.serBILGI(emirADI);
		String port =  rs.getString("PORT");
		String  insT = rs.getString("INSTANCE");
		String kulL = rs.getString("KULLANICI");
		String pwD = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(rs.getBytes("SIFRE"));
		sqll.ccon.close();
		ResultSet rrs =  sqll.surBILGI(emirADI);
		ResultSet rss = sqll.dbLISTE(emirADI);

		List<String> dbList  = new ArrayList<>();
		while(rss.next())
		{
			dbList.add(rss.getString("DB_ADI"));
		}
		sqll.ccon.close();
		Date tar = new Date();
		DateFormat  df = new SimpleDateFormat("ddMMyyyyHHmm");
		String tarih = df.format(tar);
		for(int i= 0  ; i < dbList.size() ; i++){
			sqll.msBackup(port,insT  ,kulL,pwD,dbList.get(i)  ,	tarih + "_" + dbList.get(i));
			String  dosya,dzip ;
			dosya =  "C:\\OBS_SISTEM\\BACKUP\\" + tarih +  "_"  +   dbList.get(i) + ".bak" ;
			dzip =  tarih + "_" + dbList.get(i) + ".zip" ;
			BACKUP_RESTORE. BackupdbtoZIP(dosya , dzip);
			BACKUP_RESTORE.upLOADtoFTP(rrs.getString("HOST") , Integer.parseInt(rrs.getString("PORT")),rrs.getString("KULLANICI"), ENCRYPT_DECRYPT_STRING.dCRYPT_manual(rrs.getBytes("SIFRE"))
					, dzip ,rrs.getString("SURUCU") ) ;
			
			File myObj = new File(dosya); 
		    myObj.delete();
		    myObj = new File(  "C:\\OBS_SISTEM\\BACKUP\\" + dzip); 
		    myObj.delete();
		    
		}
	}
	private void mySQL(String emirADI)
	{

	}
	private static void durdur()
	{
		if (timerr != null)
		{	
			timerr.cancel();
			timerr.purge();
		}
	}

	private void baslat()
	{
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				try {
					yedekLE();
					  
				} catch (ClassNotFoundException | SQLException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		timerr = new Timer();
		timerr.schedule(timerTask, 0, 1000);
	}
}
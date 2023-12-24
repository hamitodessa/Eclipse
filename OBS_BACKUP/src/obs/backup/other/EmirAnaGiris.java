package obs.backup.other;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.CheckListItem;
import OBS_C_2025.CheckListRenderer;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.SIFRE_DONDUR;
import OBS_C_2025.bilgilendirme_bilgiler;
import OBS_C_2025.db_List;
import OBS_C_2025.emir_bilgiler;
import OBS_C_2025.ftp_bilgiler;
import OBS_C_2025.server_bilgiler;
import OBS_C_2025.yedekleme_bilgiler;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

@SuppressWarnings({"rawtypes","unchecked","serial","deprecation"})
public class EmirAnaGiris extends JPanel {
	static BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private static final long serialVersionUID = 1L;
	public static JPanel container ;
	
	public JTextField txtEmir;
	public JTextArea textAciklama;
	private JButton btnServer ;
	private JButton btnSurucuSec ;
	private JButton btnDosyaSec ;
	
	public static JCheckBox chckbxDurum;
	public JCheckBox chckbxServerDosya ;
	
	public static JLabel lblNewLabel_6 ;
	public static JLabel lblNewLabel_5;



	public static JList list;
	public static DefaultListModel<CheckListItem> model ;
	/**
	 * Create the panel.
	 */
	
	
	public EmirAnaGiris() {
		setName("emirAnaGirisPanel");
		setLayout(new BorderLayout(0, 0));
		JPanel panel_4 = new JPanel();
		add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_2 = new JSplitPane();
		panel_4.add(splitPane_2, BorderLayout.CENTER);
		
		
		JPanel panel_10 = new JPanel();
		splitPane_2.setRightComponent(panel_10);
		panel_10.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Durum");
		lblNewLabel.setBounds(26, 68, 86, 14);
		panel_10.add(lblNewLabel);
		
		chckbxDurum = new JCheckBox("");
		chckbxDurum.setBounds(150, 68, 99, 23);
		panel_10.add(chckbxDurum);
		
		JLabel lblNewLabel_1 = new JLabel("Emir Ismi");
		lblNewLabel_1.setBounds(26, 100, 99, 14);
		panel_10.add(lblNewLabel_1);
		
		txtEmir = new JTextField();
		txtEmir.setBounds(150, 100, 219, 20);
		panel_10.add(txtEmir);
		txtEmir.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Aciklama");
		lblNewLabel_2.setBounds(26, 150, 99, 14);
		panel_10.add(lblNewLabel_2);
		
		textAciklama = new JTextArea();
		textAciklama.setBounds(150, 150, 219, 77);
		textAciklama.setDocument(new JTextFieldLimit(50));
		//textAciklama.setBorder(txtEmir.getBorder());
		//Border borderr = BorderFactory.createLineBorder(new Color(235,235,235));
		//textAciklama.setBorder(BorderFactory.createCompoundBorder(borderr, null));
		panel_10.add(textAciklama);
		
		
		chckbxServerDosya = new JCheckBox("Sql Server / Diger Dosya Yedekleme");
		chckbxServerDosya.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxServerDosya.isSelected())
				{
					btnServer.setVisible(true);
					btnDosyaSec.setVisible(false);
					btnSurucuSec.setVisible(false);
				}
				else {
					btnServer.setVisible(false);
					btnDosyaSec.setVisible(true);
					btnSurucuSec.setVisible(true);
				}
			}
		});
		chckbxServerDosya.setToolTipText("");
		chckbxServerDosya.setBounds(150, 302, 250, 23);
		panel_10.add(chckbxServerDosya);
		
		JLabel lblNewLabel_4 = new JLabel("Dosya Sayisi");
		lblNewLabel_4.setBounds(26, 373, 100, 14);
		panel_10.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("0");
		lblNewLabel_5.setBounds(150, 373, 48, 14);
		panel_10.add(lblNewLabel_5);
		
		JButton btnNewButton_4 = new JButton("Kayit");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					OBS_BACKUP.genelKayit();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(317, 579, 89, 23);
		panel_10.add(btnNewButton_4);
		
		JPanel panel_9 = new JPanel();
		panel_9.setPreferredSize(new Dimension(300,0));
		splitPane_2.setLeftComponent(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_9.add(splitPane_3, BorderLayout.CENTER);
		
		JPanel panel_11 = new JPanel();
		panel_11.setPreferredSize(new Dimension(0,75));
		splitPane_3.setLeftComponent(panel_11);
		panel_11.setLayout(null);
		
		lblNewLabel_6 = new JLabel(".......");
		lblNewLabel_6.setBounds(10, 11, 121, 14);
		panel_11.add(lblNewLabel_6);
		
		btnDosyaSec = new JButton("Dosya Sec");
		btnDosyaSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Dosya Seciniz");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Dosya Sec");
				chooser.setApproveButtonToolTipText("Dosya Sec");
				//FileNameExtensionFilter docFilter = new FileNameExtensionFilter(".docx", "Microsoft Word Documents");
				//chooser.addChoosableFileFilter(docFilter);
				
				//chooser.addChoosableFileFilter(new FileNameExtensionFilter("Dosyalar", "jpg", "png", "gif", "bmp","pdf","txt","xls","xlsx","doc","docx","db","xml","mdb","accdb"));
				chooser.setApproveButtonMnemonic('s');
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					model.addElement( new CheckListItem(chooser.getSelectedFile().getName(),chooser.getSelectedFile().getParent()));
					list.repaint();
				}
				else {
				}
			}
		});
	
		btnDosyaSec.setBounds(2, 41, 146, 30);
		panel_11.add(btnDosyaSec);
		
		btnSurucuSec = new JButton("Surucu Sec");
		btnSurucuSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Surucu Seciniz");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Surucu Sec");
				chooser.setApproveButtonToolTipText("Surucu Sec");
				chooser.setApproveButtonMnemonic('s');
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					model.addElement( new CheckListItem(chooser.getSelectedFile().toString(),"Surucu"));
					list.repaint();
				}
				else {
					// System.out.println("No Selection ");
				}
			}
		});
		btnSurucuSec.setBounds(147, 41, 148, 30);
		panel_11.add(btnSurucuSec);
		
		btnServer = new JButton("Server Baglanti");
		btnServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			OBS_BACKUP.tabbedPane_1.setSelectedIndex(4);
			}
		});
		btnServer.setVisible(false);
		btnServer.setBounds(2, 41, 290, 30);
		panel_11.add(btnServer);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_3.setRightComponent(scrollPane_1);

		model = new DefaultListModel<>();
		
		//model.addElement( new CheckListItem("apple","C:\\"));
		list = new JList(model);
				
				
		list.setCellRenderer(new CheckListRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				JList list = (JList) event.getSource();
				int index = list.locationToIndex(event.getPoint());// Get index of item
				CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);
				item.setSelected(!item.isSelected()); // Toggle selected state
				list.repaint(list.getCellBounds(index, index));// Repaint cell
				dosyaSAYI();
			}
		});
		list.setDropTarget(new DropTarget() {
			
			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					List<File> droppedFiles = (List<File>) evt .getTransferable().getTransferData(  DataFlavor.javaFileListFlavor);
					if(droppedFiles.size() > 1){
					OBS_BACKUP.mesaj_goster(5000,Notifications.Type.WARNING,  "Tek Seferde 1 Dosya Ekleyebilirsiniz.....!!");
					}
					else{
						File droppedFile = (File) droppedFiles.get(0);
						model.addElement( new CheckListItem(droppedFile.getName(),droppedFile.getParent() ));
						list.repaint();
					}
				} catch (Exception ex) {
				OBS_BACKUP.	mesaj_goster(15000,Notifications.Type.ERROR, ex.getMessage() );
				}
			}
		});	
		scrollPane_1.setViewportView(list);
		
	}
	private void dosyaSAYI()
	{
		if (list.getModel().getSize() != 0) 
		{
			int dosyaSayi = 0 ;
			for (int i =0; i< list.getModel().getSize(); i++)
			{
				CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
				item = (CheckListItem) list.getModel().getElementAt(i);
				if(item.isSelected)
				{
					dosyaSayi +=1 ;
				}
			} 
			lblNewLabel_5.setText(Integer.toString(dosyaSayi));
		}
	}
	public  void emirDOLDUR()
	{
		temizle();
		if(! OBS_BACKUP.gelenISIM.equals(""))
		{
			try {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				emirBilgiDoldur();
				OBS_BACKUP.tabbedPane.setSelectedIndex(1);
				OBS_BACKUP.tabbedPane_1.setSelectedIndex(0);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			} catch (Exception e1) {
		
				e1.printStackTrace();
			}
		}
	}

	private void emirBilgiDoldur() throws ClassNotFoundException, SQLException
	{
        try
        {
        	setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            List<emir_bilgiler> emirBilgiler =  bckp.emir_tek(OBS_BACKUP.gelenISIM);
           
            if (emirBilgiler.size()  == 0) return;
            txtEmir.setText(OBS_BACKUP.gelenISIM);
            textAciklama.setText(emirBilgiler.get(0).getEMIR_ACIKLAMA());
            lblNewLabel_6.setText(emirBilgiler.get(0).getINSTANCE());
            chckbxServerDosya.setSelected( emirBilgiler.get(0).isSQL_YEDEK());
            if(chckbxServerDosya.isSelected())
			{
				btnServer.setVisible(true);
				btnDosyaSec.setVisible(false);
				btnSurucuSec.setVisible(false);
			}
			else {
				btnServer.setVisible(false);
				btnDosyaSec.setVisible(true);
				btnSurucuSec.setVisible(true);
			}
            
            chckbxDurum.setSelected( emirBilgiler.get(0).isDURUM());
           //'**********FTP DOLDUR
            List<ftp_bilgiler> ftpBilgiler =   bckp.ftp_bilgi(OBS_BACKUP.gelenISIM);
            if (ftpBilgiler.size() > 0)
            {
                if (ftpBilgiler.get(0).getNERESI().equals("FTP"))
                {
                   OBS_BACKUP.sunucuayarPanel.chckbxFtp.setSelected(true);
                   OBS_BACKUP.sunucuayarPanel.chckbxYerel.setSelected(false);
                }
                else
                {
                	OBS_BACKUP.sunucuayarPanel.chckbxFtp.setSelected(false);
                    OBS_BACKUP.sunucuayarPanel.chckbxYerel.setSelected(true);
                }
                OBS_BACKUP.sunucuayarPanel.textHost.setText(ftpBilgiler.get(0).getHOST());
                OBS_BACKUP.sunucuayarPanel.textKull.setText(ftpBilgiler.get(0).getKULLANICI());
               
                String decodedString = ftpBilgiler.get(0).getSIFRE();
				String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
				byte[] bytes = new byte[byteValues.length];
				for (int i=0, len=bytes.length; i<len; i++) {
					bytes[i] = Byte.parseByte(byteValues[i].trim());     
				}
				OBS_BACKUP.sunucuayarPanel.textSifre.setText(ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes));
				OBS_BACKUP.sunucuayarPanel.textFtpSurucu.setText(ftpBilgiler.get(0).getSURUCU());
				OBS_BACKUP.sunucuayarPanel.textPort.setText(ftpBilgiler.get(0).getPORT());
				OBS_BACKUP.sunucuayarPanel.textZmnasm.setText(ftpBilgiler.get(0).getZMN_ASIMI());
				OBS_BACKUP.sunucuayarPanel.textEskisilme.setText(ftpBilgiler.get(0).getESKI_YEDEK());
				OBS_BACKUP.sunucuayarPanel.textSurucu.setText(ftpBilgiler.get(0).getSURUCU_YER());
            }
         // '**********BILGILENDIRME
            List<bilgilendirme_bilgiler> bilgiBilgiler =  bckp.bilgilendirme_bilgi(OBS_BACKUP.gelenISIM);
             if (bilgiBilgiler.size() > 0)
            {
            	OBS_BACKUP.bilgilendirmePanel.chckbxAktifPasif.setSelected(bilgiBilgiler.get(0).isDURUM());
            	OBS_BACKUP.bilgilendirmePanel.chckbxIslem.setSelected(bilgiBilgiler.get(0).isGONDERILDIGINDE());
            	OBS_BACKUP.bilgilendirmePanel.chckbxHata.setSelected(bilgiBilgiler.get(0).isHATA_DURUMUNDA());
            	OBS_BACKUP.bilgilendirmePanel.textGonIsim.setText(bilgiBilgiler.get(0).getGON_ISIM());
            	OBS_BACKUP.bilgilendirmePanel.textGonHesap.setText(bilgiBilgiler.get(0).getGON_HESAP());
            	OBS_BACKUP.bilgilendirmePanel.textAlici.setText(bilgiBilgiler.get(0).getALICI());
            	OBS_BACKUP.bilgilendirmePanel.textKonu.setText(bilgiBilgiler.get(0).getKONU());
            	OBS_BACKUP.bilgilendirmePanel.textSmtp.setText(bilgiBilgiler.get(0).getSMTP());
            	OBS_BACKUP.bilgilendirmePanel.textPort.setText(bilgiBilgiler.get(0).getSMTP_PORT());
            	OBS_BACKUP.bilgilendirmePanel.textKull.setText(bilgiBilgiler.get(0).getKULLANICI());
            	String decodedString = bilgiBilgiler.get(0).getSIFRE();
				String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
				byte[] bytes = new byte[byteValues.length];
				for (int i=0, len=bytes.length; i<len; i++) {
					bytes[i] = Byte.parseByte(byteValues[i].trim());     
				}
				OBS_BACKUP.bilgilendirmePanel.textSifre.setText(ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes));
				OBS_BACKUP.bilgilendirmePanel.chckbxSSL.setSelected(bilgiBilgiler.get(0).isSSL());
				OBS_BACKUP.bilgilendirmePanel.chckbxTSL.setSelected(bilgiBilgiler.get(0).isTSL());
            }
            //'**********YEDEKLEME
            List<yedekleme_bilgiler> yedekBilgiler =  bckp.yedekleme_bilgi(OBS_BACKUP.gelenISIM);
            if (yedekBilgiler.size() > 0)
            {
            	OBS_BACKUP.yedekaraligiPanel.textHerDakka.setText(yedekBilgiler.get(0).getSAAT());
            	OBS_BACKUP.yedekaraligiPanel.chckbxPtesi.setSelected(yedekBilgiler.get(0).isP_TESI());
            	OBS_BACKUP.yedekaraligiPanel.chckbxSali.setSelected(yedekBilgiler.get(0).isSALI());
            	OBS_BACKUP.yedekaraligiPanel.chckbxCarsamba.setSelected(yedekBilgiler.get(0).isCARS());
            	OBS_BACKUP.yedekaraligiPanel.chckbxPersembe.setSelected(yedekBilgiler.get(0).isPERS());
            	OBS_BACKUP.yedekaraligiPanel.chckbxCuma.setSelected(yedekBilgiler.get(0).isCUMA());
            	OBS_BACKUP.yedekaraligiPanel.chckbxCumartesi.setSelected(yedekBilgiler.get(0).isC_TESI());
            	OBS_BACKUP.yedekaraligiPanel.chckbxPazar.setSelected(yedekBilgiler.get(0).isPAZAR());
            	SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
    			Date snyk =  formatter.parse(yedekBilgiler.get(0).getBASLAMA().toString());
        		Date qweDate = new Date();
        		qweDate.setHours(snyk.getHours());
        		qweDate.setMinutes(snyk.getMinutes());
        		OBS_BACKUP.yedekaraligiPanel.timeBaslangic.setValue(qweDate);
        		
        		Date bts =  formatter.parse(yedekBilgiler.get(0).getBITIS().toString());
        		Date bts1  = new Date();
        		bts1.setHours(bts.getHours());
        		bts1.setMinutes(bts.getMinutes());
        		OBS_BACKUP.yedekaraligiPanel.timeBaslangic.setValue(qweDate);
        		OBS_BACKUP.yedekaraligiPanel.timeBitis.setValue(bts1);
            }
            //'**********SERVER BILGILERI
             List<server_bilgiler> serverBilgiler = bckp.server_bilgi(OBS_BACKUP.gelenISIM);
             if (serverBilgiler.size() > 0)
            {
            	if (serverBilgiler.get(0).getHANGI_SQL().equals("Ms Sql"))
            	{
             		OBS_BACKUP.serverBilgileriPanel.textMSServer.setText(serverBilgiler.get(0).getINSTANCE());
            		OBS_BACKUP.serverBilgileriPanel.textMSPort.setText(serverBilgiler.get(0).getPORT());
            		OBS_BACKUP.serverBilgileriPanel.textMSkull.setText(serverBilgiler.get(0).getKULLANICI());
            		String decodedString = serverBilgiler.get(0).getSIFRE();
            		String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
            		byte[] bytes = new byte[byteValues.length];
            		for (int i=0, len=bytes.length; i<len; i++) {
            			bytes[i] = Byte.parseByte(byteValues[i].trim());     
            		}
            		OBS_BACKUP.serverBilgileriPanel.textMSsifre.setText(ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes));
            	}
            	else if (serverBilgiler.get(0).getHANGI_SQL().equals("My Sql"))
            	{
            		OBS_BACKUP.serverBilgileriPanel.textMYPort.setText(serverBilgiler.get(0).getINSTANCE());
            		OBS_BACKUP.serverBilgileriPanel.textMykull.setText(serverBilgiler.get(0).getKULLANICI());
            		String decodedString = serverBilgiler.get(0).getSIFRE();
            		String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
            		byte[] bytes = new byte[byteValues.length];
            		for (int i=0, len=bytes.length; i<len; i++) {
            			bytes[i] = Byte.parseByte(byteValues[i].trim());     
            		}
            		OBS_BACKUP.serverBilgileriPanel.textMySifre.setText(ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes));
            		OBS_BACKUP.serverBilgileriPanel.textMyDump.setText(serverBilgiler.get(0).getMY_DUMP());
            	}

            }
            ///
            if (chckbxServerDosya.isSelected())
            {
            	if (!lblNewLabel_6.getText().equals(""))
            	{
            		if (lblNewLabel_6.getText().equals("Ms Sql"))
            		{
            			//ResultSet msss = bckp.server_bilgi(OBS_BACKUP.gelenISIM);
            			SIFRE_DONDUR sdon = new SIFRE_DONDUR();
            			String response =sdon.sDONDUR(OBS_BACKUP.serverBilgileriPanel.textMSsifre);
            			bckp.MsSql_baglan(OBS_BACKUP. serverBilgileriPanel.textMSServer.getText() ,OBS_BACKUP.serverBilgileriPanel.textMSkull.getText(),response,OBS_BACKUP.serverBilgileriPanel.textMSPort.getText());
            			List<String> dbList = bckp.db_liste(OBS_BACKUP.gelenISIM);
            			//
            			ResultSet dsss = bckp.db_ismi();
            			list.removeAll();
            			while (dsss.next())
            			{
            				int index = dbList.indexOf(dsss.getString("name"));
            				CheckListItem item = (CheckListItem) new CheckListItem(dsss.getString("name"),"");
            				if (index != -1)
            				{
            					item.setSelected(true);
            				}
            				model.addElement(item);
            				list.repaint();
            			}
            		}
            		else if (lblNewLabel_6.getText().equals("My Sql"))
            		{
            			SIFRE_DONDUR sdon = new SIFRE_DONDUR();
            			String response = sdon.sDONDUR(OBS_BACKUP.serverBilgileriPanel.textMySifre);
            			BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();

            			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            			Boolean result =	bckp.MySql_server_test( OBS_BACKUP.serverBilgileriPanel.textMykull.getText(),response,OBS_BACKUP.serverBilgileriPanel.textMYPort.getText());

            			if(result)
            			{
            				bckp.MySql_baglan( OBS_BACKUP.serverBilgileriPanel.textMykull.getText(),response,OBS_BACKUP.serverBilgileriPanel.textMYPort.getText());   
            				ResultSet dsss = bckp.db_ismiMySql();
            				List<String> dbList = bckp.db_liste(OBS_BACKUP.gelenISIM);
            				list.removeAll();
            				while (dsss.next())
            				{
            					int index = dbList.indexOf(dsss.getString("Database"));
            					CheckListItem item = (CheckListItem) new CheckListItem(dsss.getString("Database"),"");
            					if (index != -1)
            					{
            						item.setSelected(true);
            					}
            					model.addElement(item);
            					list.repaint();
            				}
            			}
            		}
            		//RadLabel32.Text = "0";
            		int dosyaSAYI = 0;
            		for (int i = 0; i <= list.getModel().getSize() - 1; i++)
            		{
            			CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
            			if (item.isSelected)
            			{
            				CheckListItem item2 = item;
            				item2.setSelected(true);
            				model.remove(i);
            				model.insertElementAt(item2, dosyaSAYI);

            				dosyaSAYI += 1;
            			}
            		}

            		// RadLabel32.Text = dosyaSAYI.ToString();
            		if (list.getModel().getSize() > 0)
            		{
            			list.setSelectedIndex(0);;
            		}
            	}
            }
            else // 'DIGER DOSYA
            {
            	List<db_List> dosliste = bckp.diger_dosya_liste(OBS_BACKUP.gelenISIM);
            	list.removeAll();
            	for (int i = 0; i <= dosliste.size() -1  ; i ++)
            	{
            		CheckListItem item = (CheckListItem) new CheckListItem(dosliste.get(i).getAdi(),dosliste.get(i).getPath());
            		item.setSelected(true);
            		model.addElement(item);
            		//    RadLabel32.Text = (r + 1).ToString();
            	}
            }
            //'************
            dosyaSAYI();
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        catch (Exception ex)
        {
        	setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        	bckp.log_kayit(OBS_BACKUP.gelenISIM, new Date(), ex.getMessage());
        	OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());        }
	}
	private void temizle()
	{
		model.clear();
		txtEmir.setText("");
        textAciklama.setText("");
        lblNewLabel_6.setText("");
        chckbxServerDosya.setSelected(false);
		btnServer.setVisible(false);
		btnDosyaSec.setVisible(true);
		btnSurucuSec.setVisible(true);
        chckbxDurum.setSelected( false);
        
        OBS_BACKUP.sunucuayarPanel.chckbxFtp.setSelected(true);
        OBS_BACKUP.sunucuayarPanel.textHost.setText("");
        OBS_BACKUP.sunucuayarPanel.textKull.setText("");
		OBS_BACKUP.sunucuayarPanel.textSifre.setText("");
		OBS_BACKUP.sunucuayarPanel.textFtpSurucu.setText("");
		OBS_BACKUP.sunucuayarPanel.textPort.setText("");
		OBS_BACKUP.sunucuayarPanel.textZmnasm.setText("");
		OBS_BACKUP.sunucuayarPanel.textEskisilme.setText("");
		OBS_BACKUP.sunucuayarPanel.textSurucu.setText("");
		
       	OBS_BACKUP.bilgilendirmePanel.chckbxAktifPasif.setSelected(false);
    	OBS_BACKUP.bilgilendirmePanel.chckbxIslem.setSelected(true);
    	OBS_BACKUP.bilgilendirmePanel.textGonIsim.setText("");
    	OBS_BACKUP.bilgilendirmePanel.textGonHesap.setText("");
    	OBS_BACKUP.bilgilendirmePanel.textAlici.setText("");
    	OBS_BACKUP.bilgilendirmePanel.textKonu.setText("");
    	OBS_BACKUP.bilgilendirmePanel.textSmtp.setText("");
    	OBS_BACKUP.bilgilendirmePanel.textPort.setText("");
    	OBS_BACKUP.bilgilendirmePanel.textKull.setText("");
		OBS_BACKUP.bilgilendirmePanel.textSifre.setText("");
		OBS_BACKUP.bilgilendirmePanel.chckbxSSL.setSelected(false);
		OBS_BACKUP.bilgilendirmePanel.chckbxTSL.setSelected(false);

       	OBS_BACKUP.yedekaraligiPanel.textHerDakka.setText("");
    	OBS_BACKUP.yedekaraligiPanel.chckbxPtesi.setSelected(false);
    	OBS_BACKUP.yedekaraligiPanel.chckbxSali.setSelected(false);
    	OBS_BACKUP.yedekaraligiPanel.chckbxCarsamba.setSelected(false);
    	OBS_BACKUP.yedekaraligiPanel.chckbxPersembe.setSelected(false);
    	OBS_BACKUP.yedekaraligiPanel.chckbxCuma.setSelected(false);
    	OBS_BACKUP.yedekaraligiPanel.chckbxCumartesi.setSelected(false);
    	OBS_BACKUP.yedekaraligiPanel.chckbxPazar.setSelected(false);
		Date qweDate = new Date();
		qweDate.setHours(00);
		qweDate.setMinutes(00);
		OBS_BACKUP.yedekaraligiPanel.timeBaslangic.setValue(qweDate);
		Date bts1  = new Date();
		bts1.setHours(00);
		bts1.setMinutes(00);
		OBS_BACKUP.yedekaraligiPanel.timeBaslangic.setValue(qweDate);
		OBS_BACKUP.yedekaraligiPanel.timeBitis.setValue(bts1);

		OBS_BACKUP.serverBilgileriPanel.textMSServer.setText("");
		OBS_BACKUP.serverBilgileriPanel.textMSPort.setText("");
		OBS_BACKUP.serverBilgileriPanel.textMSkull.setText("");
		OBS_BACKUP.serverBilgileriPanel.textMSsifre.setText("");
		OBS_BACKUP.serverBilgileriPanel.textMYPort.setText("");
		OBS_BACKUP.serverBilgileriPanel.textMykull.setText("");
		OBS_BACKUP.serverBilgileriPanel.textMySifre.setText("");
		OBS_BACKUP.serverBilgileriPanel.textMyDump.setText("");
		OBS_BACKUP.serverBilgileriPanel.tabbedPane.setSelectedIndex(0);

	}
}

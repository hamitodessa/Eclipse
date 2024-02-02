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
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.CheckListItem;
import OBS_C_2025.CheckListRenderer;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.JTextFieldRegularPopupMenu;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SIFRE_DONDUR;
import OBS_C_2025.bilgilendirme_bilgiler;
import OBS_C_2025.db_List;
import OBS_C_2025.emir_bilgiler;
import OBS_C_2025.ftp_bilgiler;
import OBS_C_2025.server_bilgiler;
import OBS_C_2025.yedekleme_bilgiler;
import obs.backup.ayarlar.dilAciklamalar;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;
import java.awt.Font;


@SuppressWarnings({"rawtypes","unchecked","serial","deprecation","static-access"})
public class EmirAnaGiris extends JPanel {
	static BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private static final long serialVersionUID = 1L;
	public static JPanel container ;
	
	public Obs_TextFIeld txtEmir;
	public Obs_TextFIeld textAciklama;
	public JButton btnServer ;
	public JButton btnSurucuSec ;
	public JButton btnDosyaSec ;
	
	public static JCheckBox chckbxDurum;
	public JCheckBox chckbxServerDosya ;
	
	public static JLabel lblNewLabel_6 ;
	public static JLabel lblNewLabel_5;
	public static JLabel lblNewLabel;
	public static JLabel lblNewLabel_1;
	public static JLabel lblNewLabel_4;
	public static JLabel lblNewLabel_2;
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
		splitPane_2.setDividerSize(0);
		panel_4.add(splitPane_2, BorderLayout.CENTER);
		
		
		JPanel panel_10 = new JPanel();
		splitPane_2.setRightComponent(panel_10);
		panel_10.setLayout(null);
		
		lblNewLabel = new JLabel("Durum");
		lblNewLabel.setBounds(25, 72, 86, 14);
		panel_10.add(lblNewLabel);
		
		chckbxDurum = new JCheckBox("");
		chckbxDurum.setBounds(150, 68, 99, 23);
		panel_10.add(chckbxDurum);
		
		lblNewLabel_1 = new JLabel("Emir Ismi");
		lblNewLabel_1.setBounds(25, 104, 99, 14);
		panel_10.add(lblNewLabel_1);
		
		txtEmir = new Obs_TextFIeld(30,"");
		JTextFieldRegularPopupMenu.addTo(txtEmir);
		txtEmir.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtEmir.setBounds(150, 100, 250, 23);
		panel_10.add(txtEmir);
		txtEmir.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Aciklama");
		lblNewLabel_2.setBounds(25, 150, 99, 14);
		panel_10.add(lblNewLabel_2);
		
		textAciklama = new Obs_TextFIeld(50);
		textAciklama.setBounds(150, 150, 376, 23);
		
		
		panel_10.add(textAciklama);
		
		
		chckbxServerDosya = new JCheckBox("Sql / Dosya-Surucu Yedekleme");
		chckbxServerDosya.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.clear();
				list.revalidate();
				list.repaint();
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
					lblNewLabel_6.setText(".......");
				}
			}
		});
		chckbxServerDosya.setToolTipText("");
		chckbxServerDosya.setBounds(150, 302, 250, 23);
		panel_10.add(chckbxServerDosya);
		
		lblNewLabel_4 = new JLabel("Dosya Sayisi");
		lblNewLabel_4.setBounds(25, 450, 115, 14);
		panel_10.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("0");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(150, 450, 48, 14);
		panel_10.add(lblNewLabel_5);
		
		JButton btnNewButton_4 = new JButton("Kayit");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtEmir.getText().equals(""))
					{
						OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING, dilAciklamalar.dilAciklama(OBS_BACKUP.dILS,"Emir Adi Bos Olamaz"));  
						txtEmir.requestFocus();
						return;
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					OBS_BACKUP.genelKayit();
					///
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
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception ex) {
					try {
						bckp.log_kayit("System", new Date(), ex.getMessage());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_4.setBounds(324, 550, 100, 23);
		panel_10.add(btnNewButton_4);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox(dilSecenek.dil(OBS_BACKUP.dILS,"Hepsi"));
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected())
				{
					for (int i = 0; i<= model.size() -1;i++) {
						CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
						item.setSelected(true);
					}
				}
				else {
					for (int i = 0; i<= model.size() -1;i++) {
						CheckListItem item = (CheckListItem) list.getModel().getElementAt(i);
						item.setSelected(false);
					}
				}
				list.repaint();
				dosyaSAYI();
			}
		});
		chckbxNewCheckBox.setBounds(25, 550, 97, 23);
		panel_10.add(chckbxNewCheckBox);

		JPanel panel_9 = new JPanel();
		panel_9.setPreferredSize(new Dimension(300,0));
		splitPane_2.setLeftComponent(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setDividerSize(0);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_9.add(splitPane_3, BorderLayout.CENTER);
		
		JPanel panel_11 = new JPanel();
		panel_11.setPreferredSize(new Dimension(0,75));
		splitPane_3.setLeftComponent(panel_11);
		panel_11.setLayout(null);
		
		lblNewLabel_6 = new JLabel(".......");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(10, 13, 121, 14);
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
				chooser.setAcceptAllFileFilterUsed(true);
				chooser.setApproveButtonText("Dosya Sec");
				chooser.setApproveButtonToolTipText("Dosya Sec");
				chooser.setMultiSelectionEnabled(true);
				//chooser.addChoosableFileFilter(new FileNameExtensionFilter("Dosyalar", "jpg", "png", "gif", "bmp","pdf","txt","xls","xlsx","doc","docx","db","xml","mdb","accdb","jar","exe","txt","dat","zip","rar","sql","mdf"));
				chooser.setApproveButtonMnemonic('s');
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					File selected[] = chooser.getSelectedFiles();
					boolean kontROL = false;
					for(int i=0;i<= selected.length-1;i++)
					{
						for (int ii = 0; ii<= model.size() -1;ii++) {
							if(model.getElementAt(ii).toString().equals(selected[i].getName().toString()))
							{
								kontROL = true ;
								break;
							}
						}
						if(kontROL == false)
							model.addElement( new CheckListItem(selected[i].getName(),selected[i].getParent()));
						kontROL = false;
					}
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
				chooser.setMultiSelectionEnabled(true);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					File selected[] = chooser.getSelectedFiles();
					boolean kontROL =false;
					for(int i=0;i<= selected.length-1;i++)
					{
						for (int ii = 0; ii<= model.size() -1;ii++) {
							if(model.getElementAt(ii).toString().equals(selected[i].getName().toString()))
							{
								kontROL = true ;
								break;
							}
						}
						if(kontROL == false)
							model.addElement( new CheckListItem(selected[i].getName(),selected[i].getParent()));
						kontROL = false;
					}
				}
				else {
				}
			}
		});
		btnSurucuSec.setBounds(147, 41, 148, 30);
		panel_11.add(btnSurucuSec);
		
		btnServer = new JButton("Server Baglanti");
		btnServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OBS_BACKUP.tabbedPane_1.setSelectedIndex(4);
				if(lblNewLabel_6.getText().equals("My Sql"))
				{
					OBS_BACKUP.serverBilgileriPanel.tabbedPane.setSelectedIndex(1);
				}
				else if(lblNewLabel_6.getText().equals("Ms Sql"))
				{
					OBS_BACKUP.serverBilgileriPanel.tabbedPane.setSelectedIndex(0);
				}
			}
		});
		btnServer.setVisible(false);
		btnServer.setBounds(2, 41, 290, 30);
		panel_11.add(btnServer);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_3.setRightComponent(scrollPane_1);

		model = new DefaultListModel<>();
		
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
					boolean kontROL = false;
					for(int i = 0;i<= droppedFiles.size() -1;i++)
					{
						for (int ii = 0; ii<= model.size() -1;ii++) {
							if(model.getElementAt(ii).toString().equals(droppedFiles.get(i).getName().toString()))
							{
								kontROL = true ;
								break;
							}
						}
						if(kontROL == false)
							model.addElement( new CheckListItem(droppedFiles.get(i).getName(),droppedFiles.get(i).getParent()));
						kontROL = false;
					}
				} catch (Exception ex) {
					OBS_BACKUP.	mesajGoster(15000,Notifications.Type.ERROR, ex.getMessage() );
				}
			}
		});	
		scrollPane_1.setViewportView(list);

	}
	public void dosyaSAYI()
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
		else {
			  txtEmir .setEnabled(true);
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
            txtEmir .setEnabled(false);
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
                   OBS_BACKUP.sunucuayarPanel.btnNewButton_6.setEnabled(true);
                   OBS_BACKUP.sunucuayarPanel.btnNewButton_7.setEnabled(false);
                   OBS_BACKUP.sunucuayarPanel.textHost.setEnabled(true);
                   OBS_BACKUP.sunucuayarPanel.textKull.setEnabled(true);
                   OBS_BACKUP.sunucuayarPanel.textSifre.setEnabled(true);
                   OBS_BACKUP.sunucuayarPanel.textSurucu.setEnabled(false);
                   OBS_BACKUP.sunucuayarPanel.textFtpSurucu.setEnabled(true);
                   OBS_BACKUP.sunucuayarPanel.textZmnasm.setEnabled(true);
                   OBS_BACKUP.sunucuayarPanel.textPort.setEnabled(true);
                   
                   
                }
                else
                {
                	OBS_BACKUP.sunucuayarPanel.chckbxFtp.setSelected(false);
                    OBS_BACKUP.sunucuayarPanel.chckbxYerel.setSelected(true);
                    OBS_BACKUP.sunucuayarPanel.btnNewButton_6.setEnabled(false);
                    OBS_BACKUP.sunucuayarPanel.btnNewButton_7.setEnabled(true);
                    OBS_BACKUP.sunucuayarPanel.textHost.setEnabled(false);
                    OBS_BACKUP.sunucuayarPanel.textKull.setEnabled(false);
                    OBS_BACKUP.sunucuayarPanel.textSifre.setEnabled(false);
                    OBS_BACKUP.sunucuayarPanel.textSurucu.setEnabled(true);
                    OBS_BACKUP.sunucuayarPanel.textFtpSurucu.setEnabled(false);
                    OBS_BACKUP.sunucuayarPanel.textZmnasm.setEnabled(false);
                    OBS_BACKUP.sunucuayarPanel.textPort.setEnabled(false);

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
        		qweDate.setSeconds(0);
        		OBS_BACKUP.yedekaraligiPanel.timeBaslangic.setValue(qweDate);
        		
        		Date bts =  formatter.parse(yedekBilgiler.get(0).getBITIS().toString());
        		Date bts1  = new Date();
        		bts1.setYear(qweDate.getYear());
        		bts1.setMonth(qweDate.getMonth());
        		bts1.setDate(qweDate.getDate());
        		bts1.setHours(bts.getHours());
        		bts1.setMinutes(bts.getMinutes());
        		bts1.setSeconds(0);
        	
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
            		OBS_BACKUP.serverBilgileriPanel.textMYPort.setText(serverBilgiler.get(0).getPORT());
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
            if (chckbxServerDosya.isSelected())
            {
            	if (!lblNewLabel_6.getText().equals(""))
            	{
            		if (lblNewLabel_6.getText().equals("Ms Sql"))
            		{
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
        	OBS_BACKUP.mesajGoster(5000,Notifications.Type.ERROR, ex.getMessage());        
        	}
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
		OBS_BACKUP.sunucuayarPanel.textPort.setText("21");
		OBS_BACKUP.sunucuayarPanel.textZmnasm.setText("120");
		OBS_BACKUP.sunucuayarPanel.textEskisilme.setText("0");
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
		OBS_BACKUP.emirKopyalaPanel.textField.setText("");
	}
}

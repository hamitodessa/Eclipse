package obs.backup.other;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.StringConcatFactory;
import java.net.SocketException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.CheckBoxHeader;
import OBS_C_2025.CheckBoxRenderer;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA;
import OBS_C_2025.SAGA_DUZ_RENK;
import OBS_C_2025.SAGA_YANAS;
import OBS_C_2025.SOLA_DUZ_RENK;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.emir_bilgiler;
import OBS_C_2025.ftp_bilgiler;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

import javax.swing.JComboBox;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;


@SuppressWarnings({"serial","unused","static-access"})
public class DownloadFile extends JPanel {

	private static final long serialVersionUID = 1L;
	public static JTable tblFile;
	private boolean hEPSI = false;
	public JComboBox<String> comboBox;
	GLOBAL glb = new GLOBAL();
	BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private boolean ilkBASLA= true;
	DownloadPanel panelalt ;
	int satir = 0 ;
	JPanel panel;
	ScrollPaneWin11 scrollPane;
	public JButton btnNewButton;
	/**
	 * Create the panel.
	 */

	public DownloadFile() {
		setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 50));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(dilSecenek.dil(OBS_BACKUP.dILS,"Emir Ismi"));
		lblNewLabel.setBounds(10, 15, 64, 14);
		panel.add(lblNewLabel);

		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!ilkBASLA)
						lisTELE();
				} catch (Exception e1) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					e1.printStackTrace();
				}
			}
		});
		comboBox.setBounds(84, 11, 300, 25);
		panel.add(comboBox);


		btnNewButton = new JButton("Indir");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (OBS_BACKUP.backupTime ) return;
					if(comboBox.getItemCount() == 0 ) return ;
					if(tblFile.getRowCount() ==0) return;
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					inDIR();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) 
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(400, 11, 100, 23);
		panel.add(btnNewButton);
		
		scrollPane = new ScrollPaneWin11();
		add(scrollPane, BorderLayout.CENTER);

		tblFile = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {  
				switch (column) {
				case 0:
					return true;
				default:
					return false;
				}
			}
		};
		tblFile.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblFile.setSurrendersFocusOnKeystroke(true);
		tblFile.getTableHeader().setReorderingAllowed(false);
		tblFile.setShowHorizontalLines(true);
		tblFile.setShowVerticalLines(true);
		scrollPane.setViewportView(tblFile);

		panelalt = new DownloadPanel();
		panelalt.setPreferredSize(new Dimension(0, 0));
		add(panelalt, BorderLayout.SOUTH);
	
	}

	private void inDIR() throws InterruptedException, ClassNotFoundException, SQLException
	{
		Runnable runner = new Runnable()
		{public void run() {
			try {
				GuiUtil.setWaitCursor(tblFile,true);
				GuiUtil.setWaitCursor(panel,true);
				GuiUtil.setWaitCursor(scrollPane,true);
				GuiUtil.setWaitCursor(OBS_BACKUP.toolBar,true);
				GuiUtil.setWaitCursor(panelalt,true);
				panelalt.setPreferredSize(new Dimension(0, 100));
				panelalt.revalidate();
				DefaultTableModel modell = (DefaultTableModel)tblFile.getModel();
				panelalt.Progres_Bar_Temizle_1();
				panelalt.RPB1.setMaximum(satir_kontrol());
				panelalt.RPB1.setStringPainted(true);
				List<ftp_bilgiler> ftpBilgi = new ArrayList<ftp_bilgiler>();
				ftpBilgi = bckp.ftp_bilgi(comboBox.getSelectedItem().toString());
				for ( int ii = 0; ii <=  modell.getRowCount() -1;ii++)
				{
					if ( modell.getValueAt(ii,0) != null) 
					{
						if (  modell.getValueAt(ii,0).toString().equals("true")   )
						{
							satir +=1;
							panelalt.Progres_Bar_1(satir);
							String ftp, kull, sifre, surucu,  neresi, surucu_yer;
							ftp = ftpBilgi.get(0).getHOST();
							kull = ftpBilgi.get(0).getKULLANICI();

							String decodedString = ftpBilgi.get(0).getSIFRE();
							String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
							byte[] bytes = new byte[byteValues.length];
							for (int i=0, len=bytes.length; i<len; i++) {
								bytes[i] = Byte.parseByte(byteValues[i].trim());     
							}
							sifre = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
							surucu = ftpBilgi.get(0).getSURUCU();
							int port = Integer.valueOf( ftpBilgi.get(0).getPORT());
							surucu_yer =ftpBilgi.get(0).getSURUCU_YER();
							FTPClient ftpc = new FTPClient();
							ftpc.setConnectTimeout(Integer.valueOf(ftpBilgi.get(0).getZMN_ASIMI()) * 1000);
							ftpc.connect(ftp, port);
							ftpc.login(kull, sifre);
							ftpc.enterLocalPassiveMode();
							ftpc.changeWorkingDirectory(surucu);
							ftpc.setFileType(FTP.BINARY_FILE_TYPE);
							boolean success ;
							//******************************
							double toplam = 0 ;
							FTPFile[] files = ftpc.listFiles();
							panelalt.lblSurucu.setText(glb.BACKUP_YERI);
							for (int i=0;i<= files.length-1;i++) 
							{
								if (files[i].getName().equals(modell.getValueAt(ii,1).toString()))  
								{
									panelalt.lblEmirAdi.setText(modell.getValueAt(ii,1).toString());
									toplam =  files[i].getSize();
									panelalt.lblDosBoyut.setText( FORMATLAMA.doub_0(toplam)+ " Bytes");
									double topl =  toplam ;
									String remoteFile2 =   files[i].getName();
									File downloadFile2 = new File(glb.BACKUP_YERI +  files[i].getName());
									OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile2));
									InputStream inputStream = ftpc.retrieveFileStream(remoteFile2);
									double inen= 0;
									byte[] bytesArray = new byte[2048];
									int bytesRead = -1;
									//panelalt.Progres_Bar_Temizle_2();
									panelalt.RPB2.setStringPainted(true);
									panelalt.RPB2.setMaximum((int)toplam);
									Long start = System.currentTimeMillis();
									long timeInSecs = 0;
									while ((bytesRead = inputStream.read(bytesArray)) != -1)
									{
										outputStream2.write(bytesArray, 0, bytesRead);
										inen += bytesRead ;
										panelalt.lblInen.setText(FORMATLAMA.doub_0(inen )+ " Bytes");
										panelalt.lblKalan.setText(FORMATLAMA.doub_0((toplam  - inen)  ) + " Bytes");
										panelalt.Progres_Bar_2((int) inen);
										double speedInKBps = 0.00;
										timeInSecs = (System.currentTimeMillis() - start) ; 
										speedInKBps = ( (inen * 1000) / (timeInSecs + 1))  ;
										panelalt.lblHiz.setText(FORMATLAMA.doub_0( speedInKBps) + " Bytes");
									}
									success = ftpc.completePendingCommand();
									outputStream2.close();
									inputStream.close();
									bckp.log_kayit("System", new Date(), files[i].getName() + dilSecenek.dil(OBS_BACKUP.dILS,  " Dosyasi Indirildi"));
								}
								panelalt.Progres_Bar_Temizle_2();
							}	
							ftpc.logout();
							ftpc.disconnect();
							OBS_BACKUP.mesajGoster(7500,Notifications.Type.INFO,  modell.getValueAt(ii,1).toString() 
									+ System.lineSeparator()
									+ glb.BACKUP_YERI  
									+ System.lineSeparator()  
									+ dilSecenek.dil(OBS_BACKUP.dILS, "Surucusune Indirilmistir")  );  
						}
					}
				}
				panelalt.Progres_Bar_Temizle_1();
				panelalt.setPreferredSize(new Dimension(0, 0));
				panelalt.revalidate();
				GuiUtil.setWaitCursor(tblFile,false);
				GuiUtil.setWaitCursor(panel,false);
				GuiUtil.setWaitCursor(scrollPane,false);
				GuiUtil.setWaitCursor(OBS_BACKUP.toolBar,false);
				GuiUtil.setWaitCursor(panelalt,false);
			} catch (Exception ex) {
				GuiUtil.setWaitCursor(tblFile,false);
				GuiUtil.setWaitCursor(panel,false);
				GuiUtil.setWaitCursor(scrollPane,false);
				GuiUtil.setWaitCursor(OBS_BACKUP.toolBar,false);
				GuiUtil.setWaitCursor(panelalt,false);
				try {
					bckp.log_kayit("System", new Date(), ex.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				OBS_BACKUP.mesajGoster(5000,Notifications.Type.ERROR, ex.getMessage());        
			}
		}
		};
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
	private void lisTELE() 
	{
		if(comboBox.getSelectedItem() == null) return;
		GRID_TEMIZLE.grid_temizle(tblFile);
		if(comboBox.getSelectedItem().toString().equals("")) {
			return;
		}
		Runnable runner = new Runnable()
		{ 
		public void run() {
		try 
		{
			GuiUtil.setWaitCursor(tblFile,true);
			GuiUtil.setWaitCursor(panel,true);
			GuiUtil.setWaitCursor(scrollPane,true);
			GuiUtil.setWaitCursor(OBS_BACKUP.toolBar,true);
			List<ftp_bilgiler> ftpBilgi = new ArrayList<ftp_bilgiler>();
			ftpBilgi = bckp.ftp_bilgi(comboBox.getSelectedItem().toString());
			String ftp, kull, sifre, surucu,  neresi, surucu_yer;
			ftp = ftpBilgi.get(0).getHOST();
			kull = ftpBilgi.get(0).getKULLANICI();
			String decodedString = ftpBilgi.get(0).getSIFRE();
			String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];
			for (int i=0, len=bytes.length; i<len; i++) {
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			}
			sifre = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
			surucu = ftpBilgi.get(0).getSURUCU();
			int port = Integer.valueOf( ftpBilgi.get(0).getPORT());
			surucu_yer =ftpBilgi.get(0).getSURUCU_YER();

			GRID_TEMIZLE.grid_temizle(tblFile);
			tblFile.setModel(bckp.file_liste( ftp , surucu, kull, sifre,port));
			JTableHeader th = tblFile.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			tc = tcm.getColumn(0);
			JCheckBox checkBox = new JCheckBox();
			checkBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JTableHeader th = tblFile.getTableHeader();
					TableColumnModel tcm = th.getColumnModel();
					TableColumn tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
					th.repaint();
					tblFile.repaint();
				}
			});
			checkBox.setHorizontalAlignment(JCheckBox.CENTER);
			DefaultCellEditor dce = new DefaultCellEditor( checkBox );
			tc.setCellEditor(dce);
			tc.setCellRenderer(new CheckBoxRenderer());
			tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));

			tc.setMinWidth(50);
			tc.setMaxWidth(50);

			tc = tcm.getColumn(1);
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("FILE NAME");
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());

			tc = tcm.getColumn(2);
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("SIZE Bytes");
			tc.setHeaderRenderer(new SAGA_DUZ_RENK());
			tc.setCellRenderer(new TABLO_RENDERER(0,false));
			tc.setMinWidth(100);
			tc.setMaxWidth(100);

			tc = tcm.getColumn(3);
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("DATE");
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			tc.setMinWidth(115);
			tc.setMaxWidth(115);

			Dimension dd = tblFile.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 

			tblFile.setRowSelectionInterval(0, 0);
			tblFile.setRowHeight(21);
			tblFile.getModel().addTableModelListener(	(TableModelListener) new TableModelListener() 
			{
				public void tableChanged(TableModelEvent e) 
				{
					TableModel model = (TableModel)e.getSource();
					if (model.getRowCount() > 0) {
						if(!hEPSI)
							secilen_satir();
					}
				}
			});
			GuiUtil.setWaitCursor(tblFile,false);
			GuiUtil.setWaitCursor(panel,false);
			GuiUtil.setWaitCursor(scrollPane,false);
			GuiUtil.setWaitCursor(OBS_BACKUP.toolBar,false);
		} catch (Exception e1) {
			GuiUtil.setWaitCursor(tblFile,false);
			GuiUtil.setWaitCursor(panel,false);
			GuiUtil.setWaitCursor(scrollPane,false);
			GuiUtil.setWaitCursor(OBS_BACKUP.toolBar,false);
		}
			}
		};
		Thread t = new Thread(runner, "Code Executer");
		t.start();

	}

	public void eismiDOLDUR() throws ClassNotFoundException, SQLException
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		List<emir_bilgiler> emirliste = bckp.emir_liste_download();
		if (emirliste.size() == 0 ) {  
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		else {
			comboBox.addItem("");
			for (int i =0; i<= emirliste.size()-1; i++) {
				comboBox.addItem(emirliste.get(i).getEMIR_ISMI());
			}
		}
		ilkBASLA = false;
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	private int satir_kontrol()
	{
		int satir = 0 ;
		DefaultTableModel modell = (DefaultTableModel)tblFile.getModel();
		for ( int i = 0; i <=  modell.getRowCount() - 1;i++)
		{
			if ( modell.getValueAt(i,0) != null) 
			{
				if (modell.getValueAt(i,0).toString().equals("true"))
					satir += 1 ;
			};
		}
		return satir ;
	}
	private void secilen_satir()
	{
		OBS_BACKUP.lblEmir.setText("Secilen Satir"); 
		OBS_BACKUP.lblemirSAYI.setText(FORMATLAMA.doub_0(satir_kontrol()));
	}
	class MyItemListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			Runnable runner = new Runnable()
			{ 
				@SuppressWarnings("removal")
				public void run() {
					Object source = e.getSource();
					if (source instanceof AbstractButton == false) return;
					boolean checked = e.getStateChange() == ItemEvent.SELECTED;
					hEPSI  = true ;
					for(int x = 0, y = tblFile.getRowCount(); x < y; x++)
					{
						tblFile.setValueAt(new Boolean(checked),x,0);
					}
					hEPSI = false;
					secilen_satir();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
				}
			};
			Thread t = new Thread(runner, "Code Executer");
			t.start();
		}
	}
}

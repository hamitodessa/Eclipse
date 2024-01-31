package obs.backup.other;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.FILE_UZANTI;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SOLA_DUZ_RENK;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.bilgilendirme_bilgiler;
import obs.backup.ayarlar.dilAciklamalar;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({"serial","unused","resource"})
public class LoglamaRapor extends JPanel {

	BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private static final long serialVersionUID = 1L;
	private JTable tblLog;
	private Obs_TextFIeld txtArama;
	private JComboBox<String> comboBox ;
	private boolean ilkBASLA= true;
	public JLabel lblNewLabel;
	public JButton btnExcell ;
	JPanel panel;
	ScrollPaneWin11 scrollPane ;
	
	public JButton btnNewButton;
	/**
	 * Create the panel.
	 */
	
	public LoglamaRapor() {
		setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 50));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Arama");
		lblNewLabel.setBounds(10, 15, 48, 14);
		panel.add(lblNewLabel);
		
		txtArama = new Obs_TextFIeld(50,"");
		txtArama.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtArama.setBounds(79, 10, 250, 25);
		panel.add(txtArama);
		txtArama.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(358, 11, 275, 22);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					if(!ilkBASLA)
					{
						if(comboBox.getSelectedItem() != null)
							doldur_log(comboBox.getSelectedItem().toString());
					}
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					e1.printStackTrace();
				}
			}
		});

		panel.add(comboBox);
		
		btnNewButton = new JButton("Sil");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getItemCount() == 0 ) return ;
				int g = JOptionPane.showOptionDialog(null,comboBox.getSelectedItem().toString() + dilAciklamalar.dilAciklama(OBS_BACKUP.dILS," -  Log  Silinecek ?") ,
						"OBS BACKUP ", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, 	new String[] {"Yes", "No"}, "No");
				if(g ==  1) {
					return;
				}
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					if(comboBox.getSelectedItem().toString().equals(dilSecenek.dil(OBS_BACKUP.dILS, "Hepsi")))
					{
						bckp.log_kayit_komple_sil();
					}
					else {
						bckp.log_kayit_sil(comboBox.getSelectedItem().toString());
					}
					doldur();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) 
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(650, 11, 65, 23);
		panel.add(btnNewButton);
		
		btnExcell = new JButton("");
		btnExcell.setToolTipText("Excell Aktarma");
		btnExcell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excel_aktar();
			}
		});
		btnExcell.setIcon(new ImageIcon(LoglamaRapor.class.getResource("/ICONLAR/excel-icon_16.png")));
		btnExcell.setBounds(725, 11, 23, 23);
		panel.add(btnExcell);
		
		JButton btnMail = new JButton("");
		btnMail.setVisible(false);
		btnMail.setIcon(new ImageIcon(LoglamaRapor.class.getResource("/ICONLAR/mail-16.png")));
		btnMail.setBounds(750, 11, 23, 23);
		panel.add(btnMail);
		txtArama.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				arama();
			}
			public void removeUpdate(DocumentEvent e) {
				arama();
			}
			public void insertUpdate(DocumentEvent e) {
				arama();
			}
		});
		txtArama.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Down" )
				{	
					tblLog.requestFocus();
					tblLog.setRowSelectionInterval(0, 0);
				}
			}
		});

		txtArama.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorRemoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorMoved(AncestorEvent pEvent) {
			}
			@Override
			public void ancestorAdded(AncestorEvent pEvent) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						txtArama.requestFocusInWindow();
					}
				});
			}
		});
		scrollPane = new ScrollPaneWin11();
		add(scrollPane, BorderLayout.CENTER);

		tblLog = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		tblLog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isRightMouseButton(e) == true)
				{
				String mesaj = 	tblLog.getValueAt(tblLog.getSelectedRow(),1).toString();
				
		        Clipboard clip = Toolkit.getDefaultToolkit()
		                             .getSystemClipboard();
		        StringSelection strse1 = new StringSelection(mesaj);
		        clip.setContents(strse1, strse1);
				}
			}
		});
		tblLog.setShowHorizontalLines(true);
		tblLog.setShowVerticalLines(true);
		tblLog.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblLog);
	}
	public void doldur() throws ClassNotFoundException, SQLException
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		ArrayList<String> emirliste = bckp.log_isim();
		comboBox.removeAllItems();
		if (emirliste.size() == 0 ) {  
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		else {
			comboBox.addItem(dilSecenek.dil(OBS_BACKUP.dILS, "Hepsi"));
			for (int i =0; i<= emirliste.size()-1; i++) {
				comboBox.addItem(emirliste.get(i));
			}
		}
		doldur_log("Hepsi");
		ilkBASLA = false;
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	public void doldur_log(String eismi) throws ClassNotFoundException, SQLException
	{
		try {
			GRID_TEMIZLE.grid_temizle(tblLog);
			DefaultTableModel tbm   = bckp.log_liste(eismi);
			tblLog.setModel(tbm);

			JTableHeader th = tblLog.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("DATE");
			tc.setMinWidth(115);
			tc.setMaxWidth(115);

			tc = tcm.getColumn(1);
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("DESCRIPTION");
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());

			tc = tcm.getColumn(2);
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("JOB NAME");
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			tc.setMinWidth(200);
			tc.setMaxWidth(200);

			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			tblLog.setRowHeight(21);
			if(tblLog.getRowCount() > 0)
			{
				tblLog.setRowSelectionInterval(0, 0);
				int lastRow = tblLog.convertRowIndexToView(tblLog.getRowCount() - 1);
				tblLog.scrollRectToVisible(tblLog.getCellRect(tblLog.getRowCount()-1, 0, true));
				tblLog.setRowSelectionInterval(lastRow, lastRow);
			}
			OBS_BACKUP.lblemirSAYI.setText(FORMATLAMA.doub_0(tblLog.getRowCount()));
			OBS_BACKUP.lblEmir.setText("Log Sayisi"); 
		} catch (Exception ex) 
		{
			bckp.log_kayit("Loglama", new Date(), ex.getMessage());
			OBS_BACKUP.mesajGoster(5000,Notifications.Type.ERROR, ex.getMessage());	
		}
	}
	public void arama()  
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if (txtArama.getText().equals(""))
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			tblLog.setRowSorter(null);
		}
		else
		{
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tblLog.getModel())); 
			sorter.setStringConverter(new TableStringConverter() {
				@Override
				public String toString(TableModel model, int row, int column) {
					return model.getValueAt(row, column).toString().toLowerCase();
				}
			});
			sorter.setRowFilter(RowFilter.regexFilter("(?iu)" + txtArama.getText().toLowerCase()));
			tblLog.setRowSorter(sorter);
			tblLog.revalidate();
			tblLog.repaint();
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	private void excel_aktar()
	{
		DefaultTableModel mdl = (DefaultTableModel) tblLog.getModel();

		if (mdl.getRowCount() == 0 )
		{
			OBS_BACKUP.mesajGoster(5000,Notifications.Type.WARNING, dilAciklamalar.dilAciklama(OBS_BACKUP.dILS,"Aktarilacak Bilgi Yok") );
		}
		else
		{
			write(false) ;	
		}
	}

	private void write(boolean mail)
	{
		try 
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			UIManager.put("FileChooser.cancelButtonText", dilSecenek.dil(OBS_BACKUP.dILS,"Vazgec"));
			UIManager.put("FileChooser.saveButtonText", dilSecenek.dil(OBS_BACKUP.dILS,"Kaydet"));
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.resetChoosableFileFilters();
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileFilter xlxs = new FileNameExtensionFilter("Microsoft Excel Worksheet (.xlsx) ", "xlsx");
			fileChooser.addChoosableFileFilter(xlxs);
			fileChooser.setCurrentDirectory(new java.io.File("."));
			fileChooser.setApproveButtonText(dilSecenek.dil(OBS_BACKUP.dILS,"Kaydet"));
			fileChooser.setDialogTitle("Excell");   

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");  
			LocalDateTime now = LocalDateTime.now();  
			String zaman = dtf.format(now)  ;

			File outputfile = new File("Log_");
			fileChooser.setSelectedFile(outputfile);
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			int returnVal = fileChooser.showSaveDialog(null);
			if ( returnVal != JFileChooser.APPROVE_OPTION )
			{
				return;
			}
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			//
			String uzanti ="";
			File excelFile =  FILE_UZANTI. getSelectedFileWithExtension(fileChooser);
			uzanti  = excelFile.getName().substring(excelFile.getName().lastIndexOf("."));
			//
			//************************************** XLXS *****************************************************
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("Log_");
				XSSFFont headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setColor(IndexedColors.BLUE.getIndex()); 
				XSSFCellStyle headerStyle = workbook.createCellStyle();
				XSSFCellStyle headerSolaStyle = workbook.createCellStyle();
				headerStyle.setFont(headerFont);
				headerStyle.setAlignment(HorizontalAlignment.RIGHT);

				XSSFFont solaFont = workbook.createFont();
				solaFont.setFontName("Arial Narrow");
				solaFont. setFontHeight((short)(10*20));
				XSSFCellStyle solaStyle = workbook.createCellStyle();
				solaStyle.setFont(solaFont);
				solaStyle.setAlignment(HorizontalAlignment.LEFT);

				XSSFFont headerSolaFont = workbook.createFont();
				headerSolaFont.setBold(true);
				headerSolaFont.setColor(IndexedColors.BLUE.getIndex()); 
				headerSolaStyle.setFont(headerSolaFont);
				headerSolaStyle.setAlignment(HorizontalAlignment.LEFT);

				XSSFCellStyle satirStyle = workbook.createCellStyle();
				XSSFFont satirFont = workbook.createFont();
				satirFont.setFontName("Arial Narrow");
				satirFont. setFontHeight((short)(10*20));
				satirStyle.setFont(satirFont);
				satirStyle.setAlignment(HorizontalAlignment.RIGHT);

				DefaultTableModel mdl = (DefaultTableModel) tblLog.getModel();
				XSSFCellStyle acikStyle = workbook.createCellStyle();
				XSSFFont acikFont = workbook.createFont();
				acikFont.setColor(IndexedColors.RED.getIndex()); 
				acikFont.setBold(true);
				acikFont.setFontName("Arial");
				acikFont. setFontHeight((short)(18*16));
				acikStyle.setFont(acikFont);
				acikStyle.setAlignment(HorizontalAlignment.CENTER);

				Row baslikRow = sheet.createRow(0);
				sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));
				Cell baslikname = baslikRow.createCell(0);

				baslikname.setCellValue("Log");
				baslikname.setCellStyle(acikStyle);
				
				Row headerRow = sheet.createRow(1);
				for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
				{
					Cell bname = headerRow.createCell(q);
					bname.setCellValue(mdl.getColumnName(q));
					bname.setCellStyle(headerSolaStyle);
				}
				for (int i =0;i< mdl.getRowCount() ;i++)
				{
					Row satirRow = sheet.createRow(i+2);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						if ( mdl.getValueAt(i, s) != null)
						{
							hname.setCellValue( mdl.getValueAt(i,s).toString());
							hname.setCellStyle(solaStyle); 
						}
						else
						{
							hname.setCellValue("");
							hname.setCellStyle(satirStyle);
						}
					}
				}
				for (int i=0; i<= mdl.getColumnCount()-1; i++){
					sheet.autoSizeColumn(i);
				}
				if (mail)
				{
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					workbook.write(bos);
					byte[] byteArray= bos.toByteArray();
					InputStream in = new ByteArrayInputStream(byteArray);
				//	oac.ds = new ByteArrayDataSource(in, "application/x-any");
					bos.close();
				}
				else {
				
					FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile()  + "_" + zaman + uzanti));
					workbook.write(out);
					out.close();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					OBS_BACKUP.mesajGoster(5000,Notifications.Type.INFO, dilAciklamalar.dilAciklama(OBS_BACKUP.dILS,"Aktarma Islemi Tamamlandi") );
				}
		}
		catch (Exception ex)
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			OBS_BACKUP.mesajGoster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private static void mail_at(List<bilgilendirme_bilgiler> bilgiBilgi,String mesaj ,String eADI) throws ClassNotFoundException, SQLException
	{
		try {
			
			String gonisim, gonhesap, alici, konu, smtp, port, kull, sifre;
			boolean ssl, tsl;
			gonisim = bilgiBilgi.get(0).getGON_ISIM();
			gonhesap = bilgiBilgi.get(0).getGON_HESAP();
			alici = bilgiBilgi.get(0).getALICI();
			konu = bilgiBilgi.get(0).getKONU();
			smtp = bilgiBilgi.get(0).getSMTP();
			port = bilgiBilgi.get(0).getSMTP_PORT();
			kull = bilgiBilgi.get(0).getKULLANICI();
			String decodedString = bilgiBilgi.get(0).getSIFRE();
			String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];
			for (int i=0, len=bytes.length; i<len; i++) {
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			}
			sifre = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
			if (bilgiBilgi.get(0).isSSL())
				ssl = true;
			else
				ssl = false;
			if (bilgiBilgi.get(0).isTSL())
				tsl = true;
			else
				tsl = false;
			String[] to = { alici };
			MimeBodyPart messagePart = null ;
			Properties props = System.getProperties();
			props.put("mail.smtp.host", smtp);
			props.put("mail.smtp.user", kull);
			props.put("mail.smtp.password",sifre);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			props.put("mail.smtp.starttls.enable", tsl);
			if (ssl)
			{
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
				//props.put("mail.smtp.startsls.enable", SSL);
			}
			Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(kull, sifre);
				}
			});
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(gonhesap ,gonisim ));
			InternetAddress[] toAddress = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}
			for (int i = 0; i < toAddress.length; i++) {
				message.setRecipient(RecipientType.TO,  toAddress[i]);
			}
			messagePart = new MimeBodyPart();
			DatagramSocket socket = new DatagramSocket();
			socket.connect(new InetSocketAddress("google.com", 80));
			messagePart.setText(mesaj ,"UTF-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messagePart);
			message.setSentDate(new Date());
			message.setSubject("OBS BACKUP " , "UTF-8");
			message.setContent(multipart);
			Transport.send(message);
			message= null;
			session = null;
		}
		catch (Exception ex)
		{
			
		}
	}
}


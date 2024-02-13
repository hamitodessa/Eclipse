package obs.backup.other;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.CheckBoxHeader;
import OBS_C_2025.CheckBoxRenderer;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA_DUZ_RENK;
import OBS_C_2025.SOLA_DUZ_RENK;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.server_bilgiler;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;

@SuppressWarnings({"static-access","serial","unchecked", "rawtypes"})
public class RestoreDatabases  extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public static JTable tblFile;
	private boolean hEPSI = false;
	GLOBAL glb = new GLOBAL();
	BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	DownloadPanel panelalt ;
	int satir = 0 ;
	JPanel panel;
	ScrollPaneWin11 scrollPane;
	private JButton btnRestore ;
	List<server_bilgiler> serverBilgi;
	String[] options = {dilSecenek.dil(OBS_BACKUP.dILS,"Tamam"), dilSecenek.dil(OBS_BACKUP.dILS,"Vazgec")}; 

	public RestoreDatabases()
	{
		setLayout(new BorderLayout(0, 0));
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 50));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		btnRestore = new JButton("Restore");
		btnRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modell = (DefaultTableModel)tblFile.getModel();
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				for ( int i = 0; i <=  modell.getRowCount() - 1;i++)
				{
					if ( modell.getValueAt(i,0) != null) 
					{
						if (modell.getValueAt(i,0).toString().equals("true"))
							resTORE(modell.getValueAt(i,1).toString(),modell.getValueAt(i,2).toString());
					};
				}
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnRestore.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRestore.setBounds(20, 11, 167, 23);
		panel.add(btnRestore);

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
	public void doldur()
	{
		GRID_TEMIZLE.grid_temizle(tblFile);
		try 
		{
			GRID_TEMIZLE.grid_temizle(tblFile);
			tblFile.setModel(bckp.restoreFileList(glb.BACKUP_YERI));
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

			tc.setMinWidth(35);
			tc.setMaxWidth(35);

			tc = tcm.getColumn(1);
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("FILE NAME");
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());

			tc = tcm.getColumn(2);
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("SQL");
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			tc.setMinWidth(100);
			tc.setMaxWidth(100);

			tc = tcm.getColumn(3);
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("DATE");
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			tc.setMinWidth(100);
			tc.setMaxWidth(100);

			tc = tcm.getColumn(4);
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("ZIP SIZE Bytes");
			tc.setHeaderRenderer(new SAGA_DUZ_RENK());
			tc.setCellRenderer(new TABLO_RENDERER(0,false));
			tc.setMinWidth(100);
			tc.setMaxWidth(100);

			tc = tcm.getColumn(5);
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("SIZE Bytes");
			tc.setHeaderRenderer(new SAGA_DUZ_RENK());
			tc.setCellRenderer(new TABLO_RENDERER(0,false));
			tc.setMinWidth(100);
			tc.setMaxWidth(100);

			Dimension dd = tblFile.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 

			if(tblFile.getRowCount()>0)
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
		} catch (Exception ex) {
			try {
				bckp.log_kayit("System", new Date(), ex.getMessage());
				OBS_BACKUP.mesajGoster(10000,Notifications.Type.WARNING, ex.getMessage()); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void resTORE(String dosADI,String sqlCins)
	{
		boolean result = bckp.unZip(glb.BACKUP_YERI,dosADI , OBS_BACKUP.zipSIFRE );
		if(result == false) return;
		try 
		{
			String eADIString = bckp.emirAdiFromDbismi(dosADI.substring(13, dosADI.lastIndexOf(".")));
			if(eADIString.equals("")) return;
			sqlBAGLAN(eADIString);
			String input = dosADI;
			int index = dosADI.lastIndexOf(".");
			if (index >= 0)
			{
				if(sqlCins.equals("Ms Sql"))
				{
					if(bckp.S_CONN == null) return;
					if( bckp.dosyaKontrolMS(input.substring(13, index)))
					{
						JOptionPane optionPane = new JOptionPane(input.substring(13, index) + " - " + "Veritabani Server de Mevcut ,silinip bu dosya ile degistirilecek ?", JOptionPane.QUESTION_MESSAGE,
								JOptionPane.YES_NO_OPTION, null,options,  options[1]);
						JDialog	dialog = optionPane.createDialog("Restore");
						Set focusTraversalKeys = new HashSet(dialog.getFocusTraversalKeys(0));
						focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.VK_UNDEFINED));
						focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_LEFT, KeyEvent.VK_UNDEFINED));
						dialog.setFocusTraversalKeys(0, focusTraversalKeys);
						dialog.setVisible(true);
						dialog.dispose();
						if(optionPane.getValue() == null)
							return;
						else
							if(optionPane.getValue().toString().equals(options[1])) return;
						bckp.dosyaSilMS(input.substring(13, index));
					}
					bckp.restoreMSSql(input.substring(13, index), glb.BACKUP_YERI + "\\" + input.substring(0, index) + ".bak" );
					glb.dos_sil(glb.BACKUP_YERI + "\\" + input.substring(0, index) + ".bak");
				}
				else if(sqlCins.equals("My Sql")) 
				{
					if(bckp.MY_CONN == null) return;
					if(bckp.dosyaKontrolMY(input.substring(13, index)))
					{
						JOptionPane optionPane = new JOptionPane(input.substring(13, index) + " - " + "Veritabani Server de Mevcut ,silinip bu dosya ile degistirilecek ?", JOptionPane.QUESTION_MESSAGE,
								JOptionPane.YES_NO_OPTION, null,options,  options[1]);
						JDialog	dialog = optionPane.createDialog("Restore");
						Set focusTraversalKeys = new HashSet(dialog.getFocusTraversalKeys(0));
						focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.VK_UNDEFINED));
						focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_LEFT, KeyEvent.VK_UNDEFINED));
						dialog.setFocusTraversalKeys(0, focusTraversalKeys);
						dialog.setVisible(true);
						dialog.dispose();
						if(optionPane.getValue() == null)
							return;
						else
							if(optionPane.getValue().toString().equals(options[1])) return;
						bckp.dosyaSilMY(input.substring(13, index));
					}
					String  decodedString = serverBilgi.get(0).getSIFRE();
					String[]  byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
					byte[] bytes = new byte[byteValues.length];
					for (int i=0, len=bytes.length; i<len; i++)
						bytes[i] = Byte.parseByte(byteValues[i].trim());     
					String sqlsifre = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
					bckp.mySqlRestore(serverBilgi.get(0).getMY_DUMP(),serverBilgi.get(0).getKULLANICI(),sqlsifre,glb.BACKUP_YERI + "\\" + input.substring(0, index) + ".sql" );
					glb.dos_sil(glb.BACKUP_YERI + "\\" + input.substring(0, index) + ".sql");
				}
				glb.dos_sil(glb.BACKUP_YERI + "\\" + dosADI);
				bckp.log_kayit("System", new Date(), input.substring(0, index) + " " + dilSecenek.dil(OBS_BACKUP.dILS, "Veritabani Restore Edildi"));
				OBS_BACKUP.mesajGoster(10000,Notifications.Type.INFO, input.substring(0, index) + " " + dilSecenek.dil(OBS_BACKUP.dILS, "Veritabani Restore Edildi")); 
			}
		} catch (Exception ex) 
		{
			try 
			{
				bckp.log_kayit("System", new Date(), ex.getMessage());
				OBS_BACKUP.mesajGoster(10000,Notifications.Type.WARNING, ex.getMessage()); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void sqlBAGLAN(String emirADI) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		serverBilgi = new ArrayList<server_bilgiler>();
		serverBilgi = bckp.server_bilgi(emirADI);
		String  decodedString = serverBilgi.get(0).getSIFRE();
		String[]  byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
		byte[] bytes = new byte[byteValues.length];
		for (int i=0, len=bytes.length; i<len; i++) {
			bytes[i] = Byte.parseByte(byteValues[i].trim());     
		}
		String sqlsifre = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
		if (serverBilgi.get(0).getHANGI_SQL().equals("Ms Sql"))
			bckp.MsSql_baglan(serverBilgi.get(0).getINSTANCE() ,serverBilgi.get(0).getKULLANICI(),sqlsifre,serverBilgi.get(0).getPORT());
		else //My sql
			bckp.MySql_baglan( serverBilgi.get(0).getKULLANICI(),sqlsifre,serverBilgi.get(0).getPORT());   
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
		if(satir==0)
			btnRestore.setEnabled(false);
		else
			btnRestore.setEnabled(true);
		return satir ;
	}
	private void secilen_satir()
	{
		OBS_BACKUP.lblEmir.setText("Secilen Satir"); 
		OBS_BACKUP.lblemirSAYI.setText(FORMATLAMA.doub_0(satir_kontrol()));
	}
	@SuppressWarnings("removal")
	class MyItemListener implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			Runnable runner = new Runnable()
			{ 
				public void run() {
					Object source = e.getSource();
					if (source instanceof AbstractButton == false) return;
					boolean checked = e.getStateChange() == ItemEvent.SELECTED;
					hEPSI  = true ;
					for(int x = 0, y = tblFile.getRowCount(); x < y; x++)
						tblFile.setValueAt(new Boolean(checked),x,0);
					hEPSI = false;
					secilen_satir();
				}
			};
			Thread t = new Thread(runner, "Code Executer");
			t.start();
		}
	}
}

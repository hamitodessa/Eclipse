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
import java.io.IOException;
import java.net.SocketException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA;
import OBS_C_2025.SAGA_DUZ_RENK;
import OBS_C_2025.SAGA_YANAS;
import OBS_C_2025.SOLA_DUZ_RENK;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.emir_bilgiler;
import OBS_C_2025.ftp_bilgiler;
import obs.backup.main.OBS_BACKUP;

import javax.swing.JComboBox;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;


@SuppressWarnings({"serial","unused"})
public class DownloadFile extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tblFile;
	private boolean hEPSI = false;
	private JComboBox<String> comboBox;
	BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private boolean ilkBASLA= true;
	/**
	 * Create the panel.
	 */
	
	public DownloadFile() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 50));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Emir Ismi");
		lblNewLabel.setBounds(10, 15, 64, 14);
		panel.add(lblNewLabel);
		
		comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent e) {
				try {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					if(!ilkBASLA)
						inDIR();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception e1) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					e1.printStackTrace();
				}
			}
		});
		
		comboBox.setBounds(84, 11, 233, 25);
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("Indir");
		btnNewButton.setBounds(327, 11, 89, 23);
		panel.add(btnNewButton);
		

		JScrollPane scrollPane = new JScrollPane();
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
		try {
			eismiDOLDUR();
			ilkBASLA = false;
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
	}

	private void inDIR() throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, SocketException, ParseException, IOException
	{
		
		List<ftp_bilgiler> ftpBilgi = new ArrayList<ftp_bilgiler>();
		ftpBilgi = bckp.ftp_bilgi(comboBox.getSelectedItem().toString());
		
		String ftp, kull, sifre, surucu, port, neresi, surucu_yer;
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
		port = ftpBilgi.get(0).getPORT();
		surucu_yer =ftpBilgi.get(0).getSURUCU_YER();
		
		
		
		GRID_TEMIZLE.grid_temizle(tblFile);
		
		tblFile.setModel(bckp.file_liste( ftp , surucu, kull, sifre));
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
		tc.setHeaderRenderer(new SOLA_DUZ_RENK());
		//tc.setMinWidth(200);

		tc = tcm.getColumn(2);
		tc.setHeaderRenderer(new SAGA_DUZ_RENK());
		tc.setCellRenderer(new TABLO_RENDERER(0,false));
		tc.setMinWidth(100);
		tc.setMaxWidth(100);
		
		tc = tcm.getColumn(3);
		tc.setHeaderRenderer(new SOLA_DUZ_RENK());
		tc.setMinWidth(150);
		tc.setMaxWidth(150);
		
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
	
	}
	private void eismiDOLDUR() throws ClassNotFoundException, SQLException
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		List<emir_bilgiler> emirliste = bckp.emir_liste("EMIR_ISMI");
		if (emirliste.size() == 0 ) {  
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		else {
			comboBox.addItem("");
			for (int i =0; i<= emirliste.size()-1; i++) {
				comboBox.addItem(emirliste.get(i).getEMIR_ISMI());
			}
		}
		
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
				if (  modell.getValueAt(i,0).toString().equals("true")   )
				{
					satir += 1 ;
				}	
			};
		}
		return satir ;
	}
	private void secilen_satir()
	{
		OBS_BACKUP.lblemirSAYI.setText(FORMATLAMA.doub_0(tblFile.getRowCount()));
		OBS_BACKUP.lblEmir.setText("Secilen Satir"); 
		//lbladet.setText(FORMATLAMA.doub_0(satir_kontrol()));
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

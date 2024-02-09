package obs.backup.other;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA_DUZ_RENK;
import OBS_C_2025.SOLA_DUZ_RENK;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import obs.backup.main.OBS_BACKUP;

@SuppressWarnings({"static-access","serial"})
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
				for ( int i = 0; i <=  modell.getRowCount() - 1;i++)
				{
					if ( modell.getValueAt(i,0) != null) 
					{
						if (modell.getValueAt(i,0).toString().equals("true"))
							resTORE(modell.getValueAt(i,1).toString());
					};
				}
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
		doldur();
	}
	
	private void doldur()
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

				tc.setMinWidth(50);
				tc.setMaxWidth(50);

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
				System.out.println(ex.getMessage());
			}
	}
	private void resTORE(String dosADI)
	{
		System.out.println(dosADI);
		bckp.getfilesINZIP(dosADI);
		
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

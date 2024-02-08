package obs.backup.other;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

import OBS_C_2025.BACKUP_GLOBAL;
import OBS_C_2025.CheckBoxRenderer;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SOLA_DUZ_RENK;
import OBS_C_2025.ScrollPaneWin11;
import obs.backup.ayarlar.dilSecenek;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;
import java.awt.Font;

public class KayitliEmirler extends JPanel {

	private static final long serialVersionUID = 1L;
	BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private JTable tblEmir;
	private Obs_TextFIeld txtArama;
	public JLabel lblNewLabel;
	/**
	 * Create the panel.
	 */
	
	@SuppressWarnings({"serial"})
	public KayitliEmirler() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 50));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Arama");
		lblNewLabel.setBounds(10, 15, 48, 14);
		panel.add(lblNewLabel);
		
		txtArama = new Obs_TextFIeld(50,"");
		txtArama.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtArama.setBounds(79, 10, 298, 25);
		panel.add(txtArama);
		txtArama.setColumns(10);
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
					tblEmir.requestFocus();
					tblEmir.setRowSelectionInterval(0, 0);
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
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		add(scrollPane, BorderLayout.CENTER);

		tblEmir = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		tblEmir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (OBS_BACKUP.backupTime ) return;
					OBS_BACKUP.emirTekStop(tblEmir.getValueAt(tblEmir.getSelectedRow(), 0).toString());
					OBS_BACKUP.gelenISIM = tblEmir.getValueAt(tblEmir.getSelectedRow(), 0).toString() ;
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					OBS_BACKUP.emirAnaGirisPanel.emirDOLDUR();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					
				}
			}
		});
		tblEmir.setToolTipText(dilSecenek.dil(OBS_BACKUP.dILS, "Cift Tiklamada Emir Duzeltme"));
		tblEmir.setShowHorizontalLines(true);
		tblEmir.setShowVerticalLines(true);
		tblEmir.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblEmir);

	}
	public void doldur() throws ClassNotFoundException, SQLException
	{
		try {
			DefaultTableModel tbm   = 	bckp.emir_liste();
			tblEmir.setModel(tbm);
			JTableHeader th = tblEmir.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			tc = tcm.getColumn(0);
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("JOB NAME");
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			tc.setMinWidth(200);
			tc.setMaxWidth(200);

			tc = tcm.getColumn(1);
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("STATUS");
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			tc.setCellRenderer(new CheckBoxRenderer());
			tc.setMinWidth(50);
			tc.setMaxWidth(50);

			tc = tcm.getColumn(2);
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("MESSAGE");
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			
			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			tc.setMinWidth(100);
			tc.setMaxWidth(100);
			
			tc = tcm.getColumn(4);
			if(! OBS_BACKUP.dILS.equals("Turkce"))
				tc.setHeaderValue("LAST BACKUP");
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			tc.setMinWidth(115);
			tc.setMaxWidth(115);
			
			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			tblEmir.setRowHeight(21);
			if(tblEmir.getRowCount() > 0)
			{
				tblEmir.setRowSelectionInterval(0, 0);
				int lastRow = tblEmir.convertRowIndexToView(tblEmir.getRowCount() - 1);
				tblEmir.scrollRectToVisible(tblEmir.getCellRect(tblEmir.getRowCount()-1, 0, true));
				tblEmir.setRowSelectionInterval(lastRow, lastRow);
			}
			OBS_BACKUP.lblemirSAYI.setText(Integer.toString(tblEmir.getRowCount()));
			OBS_BACKUP.lblEmir.setText( dilSecenek.dil(OBS_BACKUP.dILS,"Emir Sayisi")); 
		} catch (Exception ex) {
			bckp.log_kayit("System", new Date(), ex.getMessage());
			OBS_BACKUP.mesajGoster(5000,Notifications.Type.ERROR, ex.getMessage());	
		}
	}
	public void arama()  
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if (txtArama.getText().equals(""))
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			tblEmir.setRowSorter(null);
		}
		else
		{
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tblEmir.getModel())); 
			sorter.setStringConverter(new TableStringConverter() {
				@Override
				public String toString(TableModel model, int row, int column) {
					return model.getValueAt(row, column).toString().toLowerCase();
				}
			});
			sorter.setRowFilter(RowFilter.regexFilter("(?iu)" + txtArama.getText().toLowerCase()));
			tblEmir.setRowSorter(sorter);
			tblEmir.revalidate();
			tblEmir.repaint();
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
}


package obs.backup.other;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
import OBS_C_2025.SOLA_DUZ_RENK;
import obs.backup.main.OBS_BACKUP;
import raven.toast.Notifications;
import java.awt.Font;

public class KayitliEmirler extends JPanel {

	private static final long serialVersionUID = 1L;
	BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private JTable tblEmir;
	private JTextField txtArama;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public KayitliEmirler() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 50));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Arama");
		lblNewLabel.setBounds(10, 15, 48, 14);
		panel.add(lblNewLabel);
		
		txtArama = new JTextField();
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


		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		tblEmir = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
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
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			tc.setMinWidth(140);
			tc.setMaxWidth(140);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			tc.setCellRenderer(new CheckBoxRenderer());
			tc.setMinWidth(50);
			tc.setMaxWidth(50);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			
			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			tc.setMinWidth(100);
			tc.setMaxWidth(100);
			
			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			tc.setMinWidth(140);
			tc.setMaxWidth(140);
			

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
			OBS_BACKUP.lblEmir.setText("Emir Sayisi"); 
		} catch (Exception ex) {
			bckp.log_kayit("Kayitli Emir", new Date(), ex.getMessage());
			OBS_BACKUP.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());	
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


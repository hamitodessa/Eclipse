package obs.backup.other;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JScrollPane;
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
import OBS_C_2025.SOLA_DUZ_RENK;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoglamaRapor extends JPanel {

	BACKUP_GLOBAL bckp = new BACKUP_GLOBAL();
	private static final long serialVersionUID = 1L;
	private JTable tblLog;
	private JTextField txtArama;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public LoglamaRapor() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0,30));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Arama");
		lblNewLabel.setBounds(10, 7, 48, 14);
		panel.add(lblNewLabel);
		
		txtArama = new JTextField();
		txtArama.setBounds(79, 4, 298, 20);
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


		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		tblLog = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		tblLog.setShowHorizontalLines(true);
		tblLog.setShowVerticalLines(true);
		tblLog.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblLog);

	}
	public void doldur() throws ClassNotFoundException, SQLException
	{
		try {


			DefaultTableModel tbm   = 	bckp.log_liste();
			tblLog.setModel(tbm);


			JTableHeader th = tblLog.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			tc.setMinWidth(140);
			tc.setMaxWidth(140);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA_DUZ_RENK());
			tc.setMinWidth(100);
			tc.setMaxWidth(100);

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
		} catch (Exception e) {

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
}


package GUNLUK;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_2025.OBS_MAIN;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;

@SuppressWarnings({ "serial","static-access", "unused" })
public class GOREV_MESAJ extends JInternalFrame {
	private JTable table;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();

	
	public GOREV_MESAJ(java.util.List<GOREV_BILGI> gBILGI) {
		setResizable(true);
		setClosable(true);
		setTitle("ANLIK MESAJLAR");
		setBounds(100, 100, 689, 305);
		setPreferredSize(new Dimension(600,300));

		Dimension desktopSize = OBS_MAIN.desktopPane.getSize();
		Dimension jInternalFrameSize = this.getSize();
		setLocation((desktopSize.width - jInternalFrameSize.width)/2,(desktopSize.height- jInternalFrameSize.height)/2);

		ScrollPaneWin11 jScrollPane1 = new ScrollPaneWin11();
		jScrollPane1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		getContentPane().add(jScrollPane1, BorderLayout.CENTER);

		DefaultTableModel model = new DefaultTableModel() ; 
		table = new JTable(new DefaultTableModel(
			new Object[][] {},
			new String[] {"Tarih", "Saat", "Isim", "Gorev", "Mesaj"}))
		{
			public boolean isCellEditable(int row, int column) {     return false;          }
			
		};
		JTableHeader th = table.getTableHeader();
		Dimension dd = table.getPreferredSize();
		dd.height = 30;
		th.setPreferredSize(dd); 
	
		TableColumnModel tcm = th.getColumnModel();
		TableColumn col;
		col = tcm.getColumn(0);
		col.setHeaderRenderer(new SOLA());
		col.setMinWidth(80);
		
		col = tcm.getColumn(1);
		col.setHeaderRenderer(new SOLA());
		col.setMinWidth(50);
		
		col = tcm.getColumn(2);
		col.setHeaderRenderer(new SOLA());
		col.setMinWidth(100);
		
		col = tcm.getColumn(3);
		col.setHeaderRenderer(new SOLA());
		col.setMinWidth(100);
		
		col = tcm.getColumn(4);
		col.setHeaderRenderer(new SOLA());
		col.setMinWidth(300);
		
		table.setRowHeight(22);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			table.setGridColor(oac.gridcolor);
		
		jScrollPane1.setViewportView(table);
		table.addAncestorListener(new AncestorListener() {
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
						table.requestFocusInWindow();
					}
				});
			}
		});
		doldur(gBILGI);
	}
	private void doldur(java.util.List<GOREV_BILGI> gBILGI)
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		for (int i = 0; i < gBILGI.size(); i++) 
		{
			mdl.addRow(new Object[]{
					gBILGI.get(i).gettARIH(),
					gBILGI.get(i).getsAAT(),
					gBILGI.get(i).getiSIM(),
					gBILGI.get(i).getgOREV(),gBILGI.get(i).getmESAS()});

		}
		if(table.getRowCount() >0)
			table.setRowSelectionInterval(0, 0);
	}
}

package Pop_Up;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import GUNLUK.GOREV_BILGI;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import net.miginfocom.swing.MigLayout;
import raven.glasspanepopup.GlassPanePopup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.*;

@SuppressWarnings({"serial","unused","static-access"})
public class Notificate_Mesaj extends JPanel {

	private JButton cmdOK;
	private JTable table;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();

	
	public Notificate_Mesaj(java.util.List<GOREV_BILGI> gBILGI) {
	
		setOpaque(false);
		setMinimumSize(new Dimension(750, 300));
		setMaximumSize(new Dimension(750, 300));
		setLayout(new MigLayout("inset 0,fillx,wrap","[fill]"));
		 setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));
		setBackground(new Color(235,235,235));
		JSplitPane pane= new JSplitPane() ;
		
		pane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pane.setDividerSize(0);
		pane.setResizeWeight(1.0);
		add(pane, BorderLayout.CENTER);

		
		JPanel panelu = new JPanel();
	
		panelu.setLayout(new BorderLayout());
		pane.setLeftComponent(panelu);
		
		ScrollPaneWin11 jScrollPane1 = new ScrollPaneWin11();
	
		jScrollPane1.setBorder( BorderFactory.createEmptyBorder() );
		panelu.add(jScrollPane1,BorderLayout.CENTER);
		
		DefaultTableModel model = new DefaultTableModel() ; 
		table = new JTable(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Tarih", "Saat", "Isim", "Gorev", "Mesaj"
				}
				)){
			public boolean isCellEditable(int row, int column) {     return false;          }

		};
		table.setBorder(BorderFactory.createEmptyBorder());
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
		col.setMinWidth(330);

		table.setRowHeight(20);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}


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

		

		JPanel panela = new JPanel();
		panela.setLayout(new BorderLayout());
		panela.setMinimumSize(new Dimension(0, 25));
		panela.setMaximumSize(new Dimension(0, 25));
		pane.setRightComponent(panela);
		
		cmdOK = new JButton();
	    cmdOK.setPreferredSize(new Dimension(100, 24));
	    cmdOK.setFont(new Font("Tahoma", Font.BOLD, 11));
	    cmdOK.setText("OK");
	    

	    panela.add(cmdOK,BorderLayout.EAST);
		doldur( gBILGI);
		cmdOK.addAncestorListener(new AncestorListener() {
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
						cmdOK.requestFocusInWindow();
					}
				});
			}
		});
	}
	 @Override
	    protected void paintComponent(Graphics grphcs) {
	        Graphics2D g2 = (Graphics2D) grphcs.create();
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2.setColor(getBackground());
	        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
	        g2.dispose();
	        super.paintComponent(grphcs);
	    }
	private void doldur(java.util.List<GOREV_BILGI> gBILGI)
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		for (int i = 0; i < gBILGI.size(); i++) {

			mdl.addRow(new Object[]{
					gBILGI.get(i).gettARIH(),
					gBILGI.get(i).getsAAT(),
					gBILGI.get(i).getiSIM(),
					gBILGI.get(i).getgOREV(),gBILGI.get(i).getmESAS()});

		}
		if(table.getRowCount() >0) 		table.setRowSelectionInterval(0, 0);

	}
	private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
		GlassPanePopup.closePopupLast();
	}

	public void eventOK(ActionListener event) {
		cmdOK.addActionListener(event);
	}

}

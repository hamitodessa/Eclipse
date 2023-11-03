package Pop_Up;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import raven.glasspanepopup.GlassPanePopup;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import OBS_C_2025.SOLA;
import OBS_C_2025.U_KODU_RENDERER;

/**
 *
 * @author RAVEN
 */
@SuppressWarnings({"serial","unused"})
public class Gunluk_Mesaj extends javax.swing.JPanel {

	  private Pop_Up.Button cmdOK;
		private javax.swing.JLabel jLabel1;
	    private JTable table;
	    
    public Gunluk_Mesaj() {
 
        setOpaque(false);
        setLayout(new BorderLayout(0, 0));
        setPreferredSize( new Dimension(600, 400) );
        

        
        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerSize(0);
        splitPane.setResizeWeight(1.0);
        add(splitPane, BorderLayout.CENTER);
        
        ////
 
        JPanel leftPanel = new JPanel(new BorderLayout());
		splitPane.setLeftComponent(leftPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		leftPanel.add(scrollPane, BorderLayout.CENTER);
		
		DefaultTableModel model = new DefaultTableModel() ; 
		table = new JTable(model){
			public boolean isCellEditable(int row, int column) {     return false;          }
			
		};
		JTableHeader th = table.getTableHeader();
		Dimension dd = table.getPreferredSize();
		dd.height = 30;
		th.setPreferredSize(dd); 
		
		model.addColumn("Tarih", new String []{""});
		model.addColumn("Saat", new String []{""});
		model.addColumn("Isim", new String []{""});
		model.addColumn("Gorev", new String [] {""});
		model.addColumn("Mesaj", new String [] {""});
		TableColumn col ;
		
		col = table.getColumnModel().getColumn(0);
		col.setMinWidth(80);
		
		col = table.getColumnModel().getColumn(1);
		col.setMinWidth(50);
		
		col = table.getColumnModel().getColumn(2);
		col.setMinWidth(100);
		
		col = table.getColumnModel().getColumn(3);
		col.setMinWidth(100);
		
		col = table.getColumnModel().getColumn(4);
		col.setMinWidth(300);
		
		table.setRowHeight(20);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		
		
		table.setRowSelectionAllowed(false);
		
		
		model.addRow(new Object[]{"","","","",""});
		model.addRow(new Object[]{"","","","",""});
		model.addRow(new Object[]{"","","","",""});
		model.addRow(new Object[]{"","","","",""});
		table.repaint();
		scrollPane.setViewportView(table);
		
		

		///
        
        JPanel rightPanel = new JPanel();
		rightPanel.setMinimumSize(new Dimension(0, 20));
		rightPanel.setMaximumSize(new Dimension(0, 20));
		rightPanel.setLayout(new BorderLayout());
		splitPane.setRightComponent(rightPanel);
		
		cmdOK = new Pop_Up.Button();
        cmdOK.setBackground(new java.awt.Color(48, 170, 63));
        cmdOK.setForeground(new java.awt.Color(255, 255, 255));
        cmdOK.setText("OK");
		cmdOK.setPreferredSize(new Dimension(100, 20));
		rightPanel.add(cmdOK,BorderLayout.EAST);
        
         initComponents();
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

    private void initComponents() {

     
 
 
    }

	private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {
        GlassPanePopup.closePopupLast();
    }

    public void eventOK(ActionListener event) {
        cmdOK.addActionListener(event);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
  
  
}

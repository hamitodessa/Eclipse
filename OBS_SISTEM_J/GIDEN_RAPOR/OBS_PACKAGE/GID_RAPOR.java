package OBS_PACKAGE;

import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Font;

public class GID_RAPOR extends JInternalFrame {
	private static JTable table;
	public static JSplitPane splitPane ;
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GID_RAPOR frame = new GID_RAPOR();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GID_RAPOR() {
		setTitle("GIDEN RAPORLAR");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 1000, 400);
		
		splitPane = new JSplitPane();
		splitPane.setDividerSize(1);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 30));
		panel.setMaximumSize(new Dimension(0, 30));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Arama");
		lblNewLabel.setBounds(10, 5, 46, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(69, 5, 312, 20);
		textField.getDocument().addDocumentListener(new DocumentListener() {
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
		panel.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		table = new JTable();
		table.setGridColor(oac.gridcolor);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		scrollPane.setViewportView(table);
	
		hisset ();

		
	}
	public static void hisset()
	{
		try
		{
	    long startTime = System.currentTimeMillis(); 
		ResultSet	rs = null;
		GRID_TEMIZLE.grid_temizle(table);
			rs = oac.glb.giden_rapor(GLOBAL.KULL_ADI);				
		if (!rs.isBeforeFirst() ) {  
		    return;
		} 
		GRID_TEMIZLE.grid_temizle(table);
		table.setModel(DbUtils.resultSetToTableModel(rs));
		table.removeColumn(table.getColumnModel().getColumn(0));
		
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc;
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new TARIH_SAATLI());
			tc.setMinWidth(130);
			
			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(200);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(200);
			
			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(200);
			
			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(200);
			
			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(400);
			
			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);
		
			Dimension dd = th.getPreferredSize();
		    dd.height = 30;
		    th.setPreferredSize(dd); 
			th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(20);
	   		 long endTime = System.currentTimeMillis();
	   		 long estimatedTime = endTime - startTime; 
	   		 double seconds = (double)estimatedTime/1000; 
	   		 OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
	   		 
	   		String deger;
			String[] parts;
			Font bigFont;
				deger = GLOBAL.setting_oku("STK_RAPORLAMA").toString();
				deger = deger.substring(1, deger.length()-1);
				parts = deger.split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				table.setFont(bigFont);
				
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Rapor Doldurma", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void arama()  
	{
		if (textField.getText().equals(""))
		{
			table.setRowSorter(null);
		}
		else
		{
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
	    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText()));
	    table.setRowSorter(sorter);
		}
	}
	public static  void sil()
	{
		 int g =  JOptionPane.showOptionDialog( null,  "Secilen Rapor  Silinecek ..?"  ,
	        		"Gonderilen Raporlardan Evrak Silme",   JOptionPane.YES_NO_OPTION,
		   			 	JOptionPane.QUESTION_MESSAGE, 	null,     //no custom icon
		   			 	oac.options,  //button titles
		   			 	oac.options[1]); //default button
		 	 if(g != 0 ) { return;	}
		 	if (table.getSelectedRow() < 0 ) return ;
		 	try
		 	{
		 		GuiUtil.setWaitCursor(splitPane,true);
		 		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		 		oac.glb.giden_rapor_sil(Integer.parseInt(mdl.getValueAt(table.getSelectedRow(),0).toString()));
		 		hisset();
		 	GuiUtil.setWaitCursor(splitPane,false);
		 	}
		 	catch (Exception ex)
		 	{
				JOptionPane.showMessageDialog(null, ex.getMessage(),"Rapor Silme", JOptionPane.ERROR_MESSAGE);
		 	}
	}
}

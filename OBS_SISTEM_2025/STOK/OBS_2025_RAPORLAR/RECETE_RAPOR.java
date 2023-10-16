package OBS_2025_RAPORLAR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.STOK_ACCESS;
import OBS_2025.FILTRE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_2025.OBS_MAIN;

import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import net.proteanit.sql.DbUtils;

@SuppressWarnings({"serial" , "static-access"})
public class RECETE_RAPOR extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);

	private static JTable table;
	private static String qwq1 ="" ;
	private static String qwq2  = "";
	private static String qwq6  = "";
	private static String qwq7  = "";
	private static String kol  = "";
	public static JSplitPane splitPane ;
	private static JTable table_1;
	private static JLabel lbladet ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RECETE_RAPOR frame = new RECETE_RAPOR();
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
	public RECETE_RAPOR() {
		setTitle("RECETE RAPORLAMA");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,1150, 600);
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	    public void valueChanged(ListSelectionEvent lse) {
		        if (!lse.getValueIsAdjusting()) {
		        	 DefaultTableModel model = (DefaultTableModel)table.getModel();
		        	 if (model.getRowCount() == 0) return ;
		        	 if (table.getSelectedRow()  < 0) return;
		        	 table.setCursor(oac.WAIT_CURSOR);
		        	 detay_doldur(model.getValueAt(table.getSelectedRow() , 0).toString());
		        	 table.setCursor(oac.DEFAULT_CURSOR);
		        }
		    }
		});
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 200));
		panel.setMaximumSize(new Dimension(0, 200));
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setDividerSize(0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_1.setLeftComponent(scrollPane_1);
		
		table_1 = new JTable();
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

		table_1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		
		
		
		scrollPane_1.setViewportView(table_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_1.setMinimumSize(new Dimension(0, 25));
		panel_1.setMaximumSize(new Dimension(0, 25));
		splitPane_1.setRightComponent(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Kayit Sayisi :");
		label.setBounds(10, 5, 85, 14);
		panel_1.add(label);
		
		lbladet = new JLabel("0");
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setBounds(100, 5, 71, 14);
		panel_1.add(lbladet);

	}
	public static void hisset()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			grup_cevir() ;
			
				rs = f_Access.rec_rapor(FILTRE.textField_45.getText(),FILTRE.textField_58.getText() ,
						FILTRE.textField_62.getText(),FILTRE.textField_63.getText() ,
						 qwq1, qwq2, kol, qwq6, qwq7);
			
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				tc.setMaxWidth(90);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(200);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(70);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(70);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(120);
				
				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(120);
				
				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(250);
				
				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(70);
				
				tc = tcm.getColumn(9);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
		
			lbladet.setText(FORMATLAMA.doub_0(mdl.getRowCount()));
			//**
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				
				int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				table.setRowSelectionInterval(lastRow, lastRow);
	
				
				//table.setSelectionBackground(Color.PINK);
				//table.setSelectionForeground(Color.BLUE);
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
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Recete  Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void grup_cevir()
	{
		try {
		ResultSet	rs = null;
		//***********************ANA GRUP
		if ( FILTRE.comboBox_44.getItemAt(FILTRE.comboBox_44.getSelectedIndex()).equals(""))
		{
            qwq1 = " Like  '%' " ;
		}
        else if  ( FILTRE.comboBox_44.getItemAt(FILTRE.comboBox_44.getSelectedIndex()).equals("Bos Olanlar"))
        {
            qwq1 = " = '' " ;
        }
        else
        {
        	
    			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_44.getItemAt(FILTRE.comboBox_44.getSelectedIndex()));
    			if (!rs.isBeforeFirst() ) {
    			}
    			else
    			{
    				rs.next();
    				qwq1 =  "= " + Integer.toString( rs.getInt("AGID_Y") );
    			}
    		
        }
		//***********************ALT GRUP
				if ( FILTRE.comboBox_46.getItemAt(FILTRE.comboBox_46.getSelectedIndex()).equals(""))
				{
		            qwq2 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_46.getItemAt(FILTRE.comboBox_46.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq2 = " = '' " ;
		        }		        else		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_46.getItemAt(FILTRE.comboBox_46.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq2 ="=" + Integer.toString( rs.getInt("ALID_Y"));
		    			}
		    		
		        }
				
				//** Urun Ana grup
				if ( FILTRE.comboBox_48.getItemAt(FILTRE.comboBox_48.getSelectedIndex()).equals(""))
				{
		            qwq6 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_48.getItemAt(FILTRE.comboBox_48.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq6 = " = '' " ;
		        }
		        else
		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_48.getItemAt(FILTRE.comboBox_48.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq6 = "=" + Integer.toString( rs.getInt("AGID_Y"));
		    			}
		    		
		    		
		        }
				//** Urun Alt Grup
				if ( FILTRE.comboBox_49.getItemAt(FILTRE.comboBox_49.getSelectedIndex()).equals(""))
				{
		            qwq7 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_49.getItemAt(FILTRE.comboBox_49.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq7 = " = '' " ;
		        }	
		        else		      
		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_49.getItemAt(FILTRE.comboBox_49.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq7 ="=" + Integer.toString( rs.getInt("ALID_Y"));
		    			}
		    		
		        }
				//***
				if ( FILTRE.comboBox_47.getItemAt(FILTRE.comboBox_47.getSelectedIndex()).equals(""))
				{
					kol = "" ;
				}
				else if ( FILTRE.comboBox_47.getItemAt(FILTRE.comboBox_47.getSelectedIndex()).equals("AKTIV"))
				{
					kol = "1" ;
				}
				else if ( FILTRE.comboBox_47.getItemAt(FILTRE.comboBox_47.getSelectedIndex()).equals("PASIV"))
				{
					kol = "2" ;
				}
	                
				
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Recete Raporlama",JOptionPane.ERROR_MESSAGE);
		} 
	}
	public static void detay_doldur(String recno)
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rss = null;
			
				rss = f_Access.rec_detay_rapor(recno,recno );
			
			GRID_TEMIZLE.grid_temizle(table_1);
			if (!rss.isBeforeFirst() ) 
			{  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
			table_1.setModel(DbUtils.resultSetToTableModel(rss));
			JTableHeader th = table_1.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			
			DefaultTableModel mdl = (DefaultTableModel) table_1.getModel();
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				tc.setMaxWidth(90);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(70);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(200);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(60);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(120);
				
				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(120);
				
				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(60);
				
				tc = tcm.getColumn(9);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				
				tc = tcm.getColumn(10);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
		
			lbladet.setText(FORMATLAMA.doub_0(mdl.getRowCount()));
			//**
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table_1.setRowSelectionInterval(0, 0);
				table_1.setRowHeight(21);
				table_1.setRowSelectionInterval(0,0);
				table_1.setSelectionBackground(Color.PINK);
				table_1.setSelectionForeground(Color.BLUE);
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
					table_1.setFont(bigFont);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Recete Raporlama",JOptionPane.ERROR_MESSAGE);
		}
	}
}

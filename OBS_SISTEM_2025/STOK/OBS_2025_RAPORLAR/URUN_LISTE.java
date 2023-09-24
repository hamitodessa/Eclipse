package OBS_2025_RAPORLAR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
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

import OBS_2025.FILTRE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.ImagePanel;
import OBS_2025.OBS_MAIN;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.TABLO_RENDERER;
import net.proteanit.sql.DbUtils;

@SuppressWarnings({"serial" , "static-access"})
public class URUN_LISTE extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	
	private static JTable table;
	private static String qwq1 ="" ;
	private static String qwq2  = "";
	private static String qwq3  = "";
	private static String qwq4  = "";
	private static String qwq5  = "";
	private static String qwq6  = "";
	private static JLabel lbladet;
	public static JSplitPane splitPane ;
	private static 	ImagePanel imagePanel ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					URUN_LISTE frame = new URUN_LISTE();
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
	public URUN_LISTE() {
		setTitle("URUN LISTE");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,1000, 600);
		
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
		table.setGridColor(oac.gridcolor);
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
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 215));
		panel.setMaximumSize(new Dimension(0, 215));
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setDividerSize(0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_1);
		
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
		
		JPanel panel_2 = new JPanel();
		splitPane_1.setLeftComponent(panel_2);
		panel_2.setLayout(null);
	
		 imagePanel = new ImagePanel();
		 imagePanel.setBounds(5, 5, 220, 175);
		 imagePanel.setBorder(new LineBorder(new Color(95, 158, 160), 2,true));
		 panel_2.add( imagePanel);

	}
	public static void filtrele()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			grup_cevir() ;
			
				rs =f_Access.urun_liste(FILTRE.textField_64.getText(),FILTRE.textField_65.getText() ,
						FILTRE.textField_61.getText(),FILTRE.textField_66.getText() ,
						FILTRE.textField_68.getText(),FILTRE.textField_69.getText() ,
						FILTRE.textField_67.getText(),FILTRE.textField_70.getText() ,
						qwq1, qwq2, qwq4, qwq5, qwq6, qwq3);
			
			
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
			
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);
			
			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);
			
			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(200);
			
			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(60);
			
			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(0,false));
			tc.setMinWidth(50);
			
			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);
			
			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);
			
			tc = tcm.getColumn(7);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);
			
			tc = tcm.getColumn(8);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);
			
			tc = tcm.getColumn(9);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);
			
			tc = tcm.getColumn(10);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);
			
			tc = tcm.getColumn(11);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);
			
			tc = tcm.getColumn(12);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(60);
			
			tc = tcm.getColumn(13);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);
			
			tc = tcm.getColumn(14);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(3,false));
			tc.setMinWidth(60);
			
			tc = tcm.getColumn(15);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);
			
			tc = tcm.getColumn(16);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(70);
			
			tc = tcm.getColumn(17);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(70);
			
			tc = tcm.getColumn(18);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);
			
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				
				int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				table.setRowSelectionInterval(lastRow, lastRow);
				
				table.setSelectionBackground(Color.PINK);
				table.setSelectionForeground(Color.BLUE);
				
								
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
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Irsaliye Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void grup_cevir()
	{
		try {
		ResultSet	rs = null;
		//***********************ANA GRUP
		if ( FILTRE.comboBox_55.getItemAt(FILTRE.comboBox_55.getSelectedIndex()).equals(""))
		{
            qwq1 = " Like  '%' " ;
		}
        else if  ( FILTRE.comboBox_55.getItemAt(FILTRE.comboBox_55.getSelectedIndex()).equals("Bos Olanlar"))
        {
            qwq1 = " = '' " ;
        }
        else
        {
       	
        	
    			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_55.getItemAt(FILTRE.comboBox_55.getSelectedIndex()));
    			if (!rs.isBeforeFirst() ) {
    			}
    			else
    			{
    				rs.next();
    				qwq1 = "=" + Integer.toString( rs.getInt("AGID_Y"));
    			}
    		
        }
		//***********************ALT GRUP
				if ( FILTRE.comboBox_58.getItemAt(FILTRE.comboBox_58.getSelectedIndex()).equals(""))
				{
		            qwq2 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_58.getItemAt(FILTRE.comboBox_58.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq2 = " = '' " ;
		        }		        else		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_58.getItemAt(FILTRE.comboBox_58.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq2 ="=" + Integer.toString( rs.getInt("ALID_Y"));
		    			}
		    		
		        }
				//***********************DEPO
				if ( FILTRE.comboBox_54.getItemAt(FILTRE.comboBox_54.getSelectedIndex()).equals(""))
				{
		            qwq3 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_54.getItemAt(FILTRE.comboBox_54.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq3 = " = '' " ;
		        }		      
		        else		      
		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN", FILTRE.comboBox_54.getItemAt(FILTRE.comboBox_54.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq3 ="=" + Integer.toString( rs.getInt("DPID_Y"));
		    			}
		    		
		        }
				//***********************MENSEI
				if ( FILTRE.comboBox_52.getItemAt(FILTRE.comboBox_52.getSelectedIndex()).equals(""))
				{
		            qwq4 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_52.getItemAt(FILTRE.comboBox_52.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq4 = " = '' " ;
		        }		      
		        else		      
		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara( "MEID_Y", "MENSEI", "MENSEI_DEGISKEN", FILTRE.comboBox_52.getItemAt(FILTRE.comboBox_52.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq4 ="=" + Integer.toString( rs.getInt("MEID_Y"));
		    			}
		    		
		        }
				//*********************** OZEL KOD 1
				if ( FILTRE.comboBox_59.getItemAt(FILTRE.comboBox_59.getSelectedIndex()).equals(""))
				{
		            qwq5 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_59.getItemAt(FILTRE.comboBox_59.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		        	qwq5 = " = '' " ;
		        }		      
		        else		      
		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara(  "OZ1ID", "OZEL_KOD_1", "OZ_KOD_1_DEGISKEN", FILTRE.comboBox_59.getItemAt(FILTRE.comboBox_59.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq5 ="=" + Integer.toString( rs.getInt("OZ1ID_Y"));
		    			}
		    	
		        }
				//*********************** OZEL KOD 2
				if ( FILTRE.comboBox_53.getItemAt(FILTRE.comboBox_53.getSelectedIndex()).equals(""))
				{
		            qwq6 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_53.getItemAt(FILTRE.comboBox_53.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		        	qwq6  = " = '' " ;
		        }		      
		        else		      
		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara(  "OZ2ID_Y", "OZEL_KOD_2", "OZ_KOD_2_DEGISKEN", FILTRE.comboBox_53.getItemAt(FILTRE.comboBox_53.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq6  ="=" + Integer.toString( rs.getInt("OZ2ID_Y"));
		    			}
		    		
		        }
				
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Urun  Raporlama grpcvr",JOptionPane.ERROR_MESSAGE);
		} 
	}
	public static void detay_doldur(String kod)
	{
		try {
			ResultSet	rss = null;
			
				rss =f_Access.resim_oku(kod);
			
			
			if (! rss.isBeforeFirst() ) {  
				imagePanel.setImage(null);
			} 
			else
			{
				rss.next();
				if (  rss.getBytes("Resim") != null)
				{
					 byte[] img = rss.getBytes("Resim");
					 ImageIcon image = new ImageIcon(img);
			         Image im = image.getImage();
			         Image myImg = im.getScaledInstance(220, 175,Image.SCALE_DEFAULT);
			         ImageIcon newImage = new ImageIcon(myImg);
			         BufferedImage bi = new BufferedImage(newImage .getIconWidth(), newImage .getIconHeight(), BufferedImage.TYPE_INT_RGB);
			    	 Graphics2D g = bi.createGraphics();
			    	 newImage.paintIcon(null, g, 0, 0);
					  g.setColor(Color.WHITE);
					  g.dispose();
					  imagePanel.setImage(bi);
				}
				else
				{
					imagePanel.setImage(null);
				}
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Resim Okuma",JOptionPane.ERROR_MESSAGE);
		}
	}
}

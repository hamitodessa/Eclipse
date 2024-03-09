package OBS_2025_RAPORLAR;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.ScrollPaneWin11;
import OBS_2025.FILTRE;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_2025.OBS_MAIN;

import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

@SuppressWarnings({"serial" , "static-access"})
public class IRSALIYE_RAPOR extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	
	private static JTable table;
	private static JTable table_1;
	private static String qwq1 ="" ;
	private static String qwq2  = "";

	private static String ask = "";
	private static JLabel lbladet;
	public static JSplitPane splitPane ;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public IRSALIYE_RAPOR() {
		setClosable(true);
		setTitle("IRSALIYE RAPOR");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(0, 0,1000, 600);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(IRSALIYE_RAPOR.class.getResource("/ICONLAR/icons8-invoice-30.png")), 16, 16));//
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
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
			        	 detay_doldur(model.getValueAt(table.getSelectedRow() , 0).toString(),model.getValueAt(table.getSelectedRow() , 1).toString());
			        	 table.setCursor(oac.DEFAULT_CURSOR);
			        }
			    }
			});
		table.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						table.requestFocusInWindow();
					}
				});
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					String[] parts;
					String deger ;
					deger = GLOBAL.setting_oku("PRG_FILTRE").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_DOWN_MASK : KeyEvent.ALT_DOWN_MASK) ) != 0))
						{
							getContentPane().setCursor(oac.WAIT_CURSOR);
							OBS_MAIN.btnFiltre.doClick();
							getContentPane().setCursor(oac.DEFAULT_CURSOR);
						}
					}
				}
				catch (Exception ex)
				{

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
		
		ScrollPaneWin11 scrollPane_1 = new ScrollPaneWin11();
		splitPane_1.setLeftComponent(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(table_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(null));
		panel_1.setMinimumSize(new Dimension(0, 25));
		panel_1.setMaximumSize(new Dimension(0, 25));
		splitPane_1.setRightComponent(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Kayit Sayisi :");
		label.setBounds(10, 5, 85, 14);
		panel_1.add(label);
		
		lbladet = new JLabel("0");
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		//lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setBounds(100, 5, 71, 14);
		panel_1.add(lbladet);

	}
	public static void filtrele()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			 GRID_TEMIZLE.grid_temizle(table);
			 GRID_TEMIZLE.grid_temizle(table_1);
			grup_cevir() ;
			
				rs =f_Access.irs_rapor(FILTRE.textField_38.getText(),FILTRE.textField_48.getText() ,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_24),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_25),
						FILTRE.textField_41.getText(),FILTRE.textField_49.getText() ,
						FILTRE.textField_54.getText(),FILTRE.textField_55.getText() ,
						ask,qwq1, qwq2, FILTRE.textField_46.getText() ,
						 FILTRE.textField_47.getText(), FILTRE.textField_53.getText(),
						 FILTRE.textField_44.getText(), FILTRE.textField_50.getText() );
			
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
			GRID_TEMIZLE.grid_temizle(table);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
		
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setCellRenderer(new TARIH());
				tc.setMinWidth(80);

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(120);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(120);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);

				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(130);
				
			
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
				lbladet.setText(FORMATLAMA.doub_0(table.getRowCount() ));
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
					table_1.setFont(bigFont);
				 
			}
		} catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
	private static void grup_cevir()
	{
		try {
		ResultSet	rs = null;
		//***********************ANA GRUP
		if ( FILTRE.comboBox_42.getItemAt(FILTRE.comboBox_42.getSelectedIndex()).equals(""))
		{
            qwq1 = " Like  '%' " ;
		}
        else if  ( FILTRE.comboBox_42.getItemAt(FILTRE.comboBox_42.getSelectedIndex()).equals("Bos Olanlar"))
        {
            qwq1 = " = '' " ;
        }
        else
        {
       	
        	
    			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_42.getItemAt(FILTRE.comboBox_42.getSelectedIndex()));
    			if (!rs.isBeforeFirst() ) {
    			}
    			else
    			{
    				rs.next();
    				qwq1 = "=" + Integer.toString( rs.getInt("AGID_Y"));
    			}
    		
        }
		//***********************ALT GRUP
				if ( FILTRE.comboBox_43.getItemAt(FILTRE.comboBox_43.getSelectedIndex()).equals(""))
				{
		            qwq2 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_43.getItemAt(FILTRE.comboBox_43.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq2 = " = '' " ;
		        }		        else		        {
		        	
		    			rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_43.getItemAt(FILTRE.comboBox_43.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq2 ="=" + Integer.toString( rs.getInt("ALID_Y"));
		    			}
		    		
		        }
				
				//***********************TUR
					if ( FILTRE.comboBox_45.getItemAt(FILTRE.comboBox_45.getSelectedIndex()).equals("GIREN"))
				{
					ask = "G" ;
				}
				else 	if ( FILTRE.comboBox_45.getItemAt(FILTRE.comboBox_45.getSelectedIndex()).equals("CIKAN"))
				{
					ask = "C" ;
				}
				else
				{
					ask = "" ;
				}
				
		} catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		} 
	}
	public static void detay_doldur(String irsno,String har)
	{
		long startTime = System.currentTimeMillis(); 
		
		try {
			ResultSet	rs = null;
		if (har.equals("Satis"))
		{
			ask = "C";
		}
		else
		{
			ask = "G";
		}
			
				rs = f_Access.irs_detay_rapor(irsno,ask);
		
			GRID_TEMIZLE.grid_temizle(table_1);
			if (! rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table_1.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			
			DefaultTableModel mdl = (DefaultTableModel) table_1.getModel();
				
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				tc.setMaxWidth(90);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(200);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(80);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(80);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(80);
				
				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(80);
				
				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				
				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(80);
				
				tc = tcm.getColumn(9);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(10);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(11);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(60);
				
				tc = tcm.getColumn(12);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(70);
				
			
		
			lbladet.setText(FORMATLAMA.doub_0(mdl.getRowCount()));
			//**
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table_1.setRowSelectionInterval(0, 0);
				table_1.setRowHeight(21);
				table_1.setRowSelectionInterval(0,0);
				
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			}
		} catch (Exception ex) {
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
}

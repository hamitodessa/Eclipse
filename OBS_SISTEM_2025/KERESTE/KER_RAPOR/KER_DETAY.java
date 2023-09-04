package KER_RAPOR;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_2025.FILTRE;
import OBS_2025.OBS_MAIN;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.KER_BILGI;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;

public class KER_DETAY extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KERESTE_ACCESS ker_Access = new KERESTE_ACCESS(oac._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);

	private static JTable table;
	private static String gana ="" ;
	private static String galt  = "";
	private static String cana  = "";
	private static String calt  = "";
	private static String goz  = "";
	private static String coz  = "";
	private static String gdpo  = "";
	private static String cdpo  = "";
	private static String kjk = "" ; 
	
	private static JLabel lbladet;
	public static JSplitPane splitPane ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KER_DETAY frame = new KER_DETAY();
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
	public KER_DETAY() {
		setTitle("KERESTE DETAY RAPOR");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,1100,600);

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
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kayit Sayisi :");
		lblNewLabel.setBounds(10, 5, 85, 14);
		panel.add(lblNewLabel);
		
		lbladet = new JLabel("0");
		lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setBounds(100, 5, 40, 14);
		panel.add(lbladet);
	}
	public static void hisset()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			grup_cevir() ;
			KER_BILGI ker_BILGI = new KER_BILGI();
			ker_BILGI.setEvrak_No(FILTRE.);
	//			rs = ker_Access.stok_rapor(FILTRE.textField_25.getText(),FILTRE.textField_30.getText() ,
	//					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_18),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_19),
	//					FILTRE.textField_26.getText(),FILTRE.textField_29.getText() ,
	//					 qwq1, qwq2, qwq3, kjk, qwq4, qwq5, qwq6, qwq7,FILTRE.textField_80.getText(),FILTRE.textField_81.getText() );
			
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
				tc.setMinWidth(170);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(200);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(60);
				
				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SOLA());
				tc.setCellRenderer(new TARIH());
				tc.setMinWidth(80);
				
				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(9);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(60);
				
				tc = tcm.getColumn(10);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(11);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(60);
				
				tc = tcm.getColumn(12);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(13);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(14);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(15);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(16);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(17);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
				
				tc = tcm.getColumn(18);
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
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Imalat Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void grup_cevir()
	{
		try {
		ResultSet	rs = null;
		//***********************GIRIS ANA GRUP
		if ( FILTRE.comboBox_78_1.getItemAt(FILTRE.comboBox_78_1.getSelectedIndex()).equals(""))
		{
			gana = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_78_1.getItemAt(FILTRE.comboBox_78_1.getSelectedIndex()).equals("Bos Olanlar"))
		{
			gana = " = '' " ;
		}
		else
		{
			rs = ker_Access.ker_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_78_1.getItemAt(FILTRE.comboBox_78_1.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				gana =  "= " + Integer.toString( rs.getInt("AGID_Y") );
			}
		}
		//***********************GIRIS ALT GRUP
		if ( FILTRE.comboBox_79_1.getItemAt(FILTRE.comboBox_79_1.getSelectedIndex()).equals(""))
		{
			galt = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_79_1.getItemAt(FILTRE.comboBox_79_1.getSelectedIndex()).equals("Bos Olanlar"))
		{
			galt = " = '' " ;
		}
		else
		{
			rs = ker_Access.ker_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_79_1.getItemAt(FILTRE.comboBox_79_1.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				galt ="=" + Integer.toString( rs.getInt("ALID_Y"));
			}

		}
		//***********************CIKIS ANA GRUP
		if ( FILTRE.comboBox_78_2.getItemAt(FILTRE.comboBox_78_2.getSelectedIndex()).equals(""))
		{
			cana = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_78_2.getItemAt(FILTRE.comboBox_78_2.getSelectedIndex()).equals("Bos Olanlar"))
		{
			cana = " = '' " ;
		}
		else
		{
			rs = ker_Access.ker_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_78_2.getItemAt(FILTRE.comboBox_78_2.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				cana =  "= " + Integer.toString( rs.getInt("AGID_Y") );
			}
		}
		//***********************CIKIS ALT GRUP
		if ( FILTRE.comboBox_79_2.getItemAt(FILTRE.comboBox_79_2.getSelectedIndex()).equals(""))
		{
			calt = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_79_2.getItemAt(FILTRE.comboBox_79_2.getSelectedIndex()).equals("Bos Olanlar"))
		{
			calt = " = '' " ;
		}
		else
		{
			rs = ker_Access.ker_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_79_2.getItemAt(FILTRE.comboBox_79_2.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				calt ="=" + Integer.toString( rs.getInt("ALID_Y"));
			}

		}
		//***********************GIRIS OZEL KOD
		if ( FILTRE.comboBox_80_1.getItemAt(FILTRE.comboBox_80_1.getSelectedIndex()).equals(""))
		{
			goz = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_80_1.getItemAt(FILTRE.comboBox_80_1.getSelectedIndex()).equals("Bos Olanlar"))
		{
			goz = " = '' " ;
		}		      
		else		      
		{
			rs = ker_Access.ker_kod_degisken_ara("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN", FILTRE.comboBox_80_1.getItemAt(FILTRE.comboBox_80_1.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				goz ="=" + Integer.toString( rs.getInt("OZ1ID_Y"));
			}
		}
		//***********************CIKIS OZEL KOD
		if ( FILTRE.comboBox_80_2.getItemAt(FILTRE.comboBox_80_2.getSelectedIndex()).equals(""))
		{
			goz = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_80_2.getItemAt(FILTRE.comboBox_80_2.getSelectedIndex()).equals("Bos Olanlar"))
		{
			goz = " = '' " ;
		}		      
		else		      
		{
			rs = ker_Access.ker_kod_degisken_ara("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN", FILTRE.comboBox_80_2.getItemAt(FILTRE.comboBox_80_2.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				goz ="=" + Integer.toString( rs.getInt("OZ1ID_Y"));
			}
		}
		//***********************GIRIS DEPO
		if ( FILTRE.comboBox_80_3.getItemAt(FILTRE.comboBox_80_3.getSelectedIndex()).equals(""))
		{
			gdpo = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_80_3.getItemAt(FILTRE.comboBox_80_3.getSelectedIndex()).equals("Bos Olanlar"))
		{
			gdpo = " = '' " ;
		}		      
		else		      
		{
			rs = ker_Access.ker_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN", FILTRE.comboBox_80_3.getItemAt(FILTRE.comboBox_80_3.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				gdpo ="=" + Integer.toString( rs.getInt("DPID_Y"));
			}
		}
		//***********************CIKIS DEPO
		if ( FILTRE.comboBox_80_4.getItemAt(FILTRE.comboBox_80_4.getSelectedIndex()).equals(""))
		{
			cdpo = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_80_4.getItemAt(FILTRE.comboBox_80_4.getSelectedIndex()).equals("Bos Olanlar"))
		{
			cdpo = " = '' " ;
		}		      
		else		      
		{
			rs = ker_Access.ker_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN", FILTRE.comboBox_80_4.getItemAt(FILTRE.comboBox_80_4.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				cdpo ="=" + Integer.toString( rs.getInt("DPID_Y"));
			}
		}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kereste Detay", JOptionPane.ERROR_MESSAGE);
		} 
	}
}

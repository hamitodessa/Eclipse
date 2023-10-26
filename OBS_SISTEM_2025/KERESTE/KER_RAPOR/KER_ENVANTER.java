package KER_RAPOR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.SOLA_ORTA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import OBS_C_2025.KER_RAPOR_BILGI;
@SuppressWarnings({"serial","deprecation","static-access"})  
public class KER_ENVANTER extends JInternalFrame {
	
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KERESTE_ACCESS ker_Access = new KERESTE_ACCESS(OBS_SIS_2025_ANA_CLASS._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
	private static JTable table;
	private static String gana ="" ;
	private static String galt  = "";
	private static String cana  = "";
	private static String calt  = "";
	private static String goz  = "";
	private static String coz  = "";
	private static String gdpo  = "";
	private static String cdpo  = "";
	
	private static JLabel lbladet;
	private static JLabel lblm3;
	private static JLabel lblmiktar;
	public static JSplitPane splitPane ;

	public KER_ENVANTER() {
		setTitle("KERESTE ENVANTER");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,1055,600);

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

		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
							OBS_MAIN.btnFiltre.doClick();
						}
					}
				}
				catch (Exception ex)
				{

				}
			}
		});
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Satir Sayisi :");
		lblNewLabel.setBounds(10, 5, 85, 14);
		panel.add(lblNewLabel);
		
		lbladet = new JLabel("0");
		lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setBounds(100, 5, 40, 14);
		panel.add(lbladet);
		
		lblm3 = new JLabel("0.000");
		lblm3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblm3.setForeground(new Color(0, 0, 128));
		lblm3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblm3.setBounds(730, 5, 91, 14);
		panel.add(lblm3);
		
		lblmiktar = new JLabel("0.00");
		lblmiktar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblmiktar.setForeground(new Color(0, 0, 128));
		lblmiktar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblmiktar.setBounds(940, 5, 85, 14);
		panel.add(lblmiktar);

	}
	public static void hisset()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			lblmiktar.setText(FORMATLAMA.doub_0( 0));
			lblm3.setText(FORMATLAMA.doub_3( 0));
			grup_cevir() ;
			KER_RAPOR_BILGI ker_BILGI = new KER_RAPOR_BILGI();
			ker_BILGI.setGTarih1(TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1_1));
			ker_BILGI.setGTarih2(TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1_1));
			ker_BILGI.setGKodu1(FILTRE.formattedTextField_2.getText());
			ker_BILGI.setGKodu2(FILTRE.formattedTextField_1_1.getText());
			ker_BILGI.setPaket_No1(FILTRE.textField_88.getText());
			ker_BILGI.setPaket_No2(FILTRE.textField_88_1.getText());
			ker_BILGI.setGCari_Firma1(FILTRE.textField_84.getText());
			ker_BILGI.setGCari_Firma2(FILTRE.textField_85.getText());
			ker_BILGI.setEvrak_No1(FILTRE.textField_90.getText());
			ker_BILGI.setEvrak_No2(FILTRE.textField_91.getText());
			ker_BILGI.setGAna_Grup(gana);
			ker_BILGI.setGAlt_Grup(galt);
			ker_BILGI.setDepo(gdpo);
			ker_BILGI.setOzel_Kod(goz);
			ker_BILGI.setCTarih1(TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1_1_1));
			ker_BILGI.setCTarih2(TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1_1_1));
			ker_BILGI.setCCari_Firma1(FILTRE.textField_86.getText());
			ker_BILGI.setCCari_Firma2(FILTRE.textField_87.getText());
			ker_BILGI.setCEvrak_No1(FILTRE.textField_92.getText());
			ker_BILGI.setCEvrak_No2(FILTRE.textField_93.getText());
			ker_BILGI.setCAna_Grup(cana);
			ker_BILGI.setCAlt_Grup(calt);
			ker_BILGI.setCDepo(cdpo);
			ker_BILGI.setCOzel_Kod(coz);
			String grupString = "" ;
			if ( FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Urun Kodu"))
			{
				grupString = " Kodu " ;
			}
			else if ( FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Konsimento"))
			{
				grupString = " Konsimento " ;
			}
			
			
			
			//

			rs = ker_Access.envanter(ker_BILGI,grupString);
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
				tc.setCellRenderer(new SOLA_ORTA());
				tc.setMinWidth(120);

				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(0,false));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(0,false));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,true));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				
				tc = tcm.getColumn(9);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);

				lbladet.setText(FORMATLAMA.doub_0(mdl.getRowCount()));
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
				topla();
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

				String deger;
				String[] parts;
				Font bigFont;
				deger = GLOBAL.setting_oku("KER_RAPORLAMA").toString();
				deger = deger.substring(1, deger.length()-1);
				parts = deger.split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				table.setFont(bigFont);

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kereste Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void topla()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		double  m3 = 0.00  ;
		double urunmiktar = 0  ;
		for (int  i = 0 ; i <= table.getRowCount() -1 ; i ++)
		{
			urunmiktar += Double.parseDouble(mdl.getValueAt(i, 9).toString());
			m3 += Double.parseDouble( mdl.getValueAt(i,7).toString());
		}
		lblmiktar.setText(FORMATLAMA.doub_2( urunmiktar));
		lblm3.setText(FORMATLAMA.doub_3( m3));
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
			coz = " Like  '%' " ;
		}
		else if  ( FILTRE.comboBox_80_2.getItemAt(FILTRE.comboBox_80_2.getSelectedIndex()).equals("Bos Olanlar"))
		{
			coz = " = '' " ;
		}		      
		else		      
		{
			rs = ker_Access.ker_kod_degisken_ara("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN", FILTRE.comboBox_80_2.getItemAt(FILTRE.comboBox_80_2.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				coz ="=" + Integer.toString( rs.getInt("OZ1ID_Y"));
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

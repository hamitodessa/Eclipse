package KER_RAPOR;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.mail.util.ByteArrayDataSource;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import OBS_2025.FILTRE;
import OBS_2025.GuiUtil;
import OBS_2025.OBS_MAIN;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.FILE_UZANTI;
import OBS_C_2025.FIT_IMAGE;
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
import raven.toast.Notifications;
import OBS_C_2025.KER_RAPOR_BILGI;
@SuppressWarnings({"serial","static-access"})  
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
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(KER_ENVANTER.class.getResource("/ICONLAR/icons8-hashtag-activity-feed-30.png")), 16, 16));//
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		splitPane.setLeftComponent(scrollPane);
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			table.setGridColor(oac.gridcolor);
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
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Satir Sayisi :");
		lblNewLabel.setBounds(10, 5, 85, 14);
		panel.add(lblNewLabel);
		
		lbladet = new JLabel("0");
		//lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setBounds(100, 5, 40, 14);
		panel.add(lbladet);
		
		lblm3 = new JLabel("0.000");
		lblm3.setHorizontalAlignment(SwingConstants.RIGHT);
		//lblm3.setForeground(new Color(0, 0, 128));
		lblm3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblm3.setBounds(730, 5, 91, 14);
		panel.add(lblm3);
		
		lblmiktar = new JLabel("0.00");
		lblmiktar.setHorizontalAlignment(SwingConstants.RIGHT);
		//lblmiktar.setForeground(new Color(0, 0, 128));
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
			String grupString[] = {"",""} ;
			int sutun = 0 ;
			if ( FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Urun Kodu"))
			{
				grupString[0] = " Kodu " ;
				grupString[1] = " Kodu " ;
			}
			else if ( FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Konsimento"))
			{
				grupString[0] = " Konsimento , (SELECT ACIKLAMA FROM KONS_ACIKLAMA  WHERE KONS = KERESTE.Konsimento ) as Aciklama " ;
				grupString[1] = " Konsimento " ;
				sutun =1 ;
			}
			else if ( FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Hesap-Kodu"))
			{
				if( BAGLAN.kerDizin.hAN_SQL.equals("MS SQL") )
				{
				grupString[0] = " Cari_Firma , (SELECT TOP 1  UNVAN FROM OK_Car" + BAGLAN.cariDizin.kOD+ ".dbo.HESAP WHERE HESAP.HESAP = KERESTE.Cari_Firma  ) as Unvan  " ;
				grupString[1] = " Cari_Firma " ;
				sutun =1 ;
				}
				else if( BAGLAN.kerDizin.hAN_SQL.equals("MY SQL") )
				{
				grupString[0] = " Cari_Firma , (SELECT  UNVAN FROM OK_Car" + BAGLAN.cariDizin.kOD + ".HESAP WHERE HESAP.HESAP = KERESTE.Cari_Firma Limit 1 ) as Unvan  " ;
				grupString[1] = " Cari_Firma " ;
				sutun =1 ;
					
				}
			}
			else if ( FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Ana_Grup-Alt_Grup"))
			{
				grupString[0] = "  (SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = KERESTE.Ana_Grup ) as Ana_Grup , "
								+ " (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = KERESTE.Alt_Grup ) as Alt_Grup " ;
				grupString[1] = " Ana_Grup ,Alt_Grup  " ;
				sutun =1 ;
				
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
				
				if ( FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Ana_Grup-Alt_Grup"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(200);
				}
				else {
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(120);
				}

				if(sutun ==1)
				{
					if ( FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Hesap-Kodu"))
					{
						tc = tcm.getColumn( 1 );
						tc.setHeaderRenderer(new SOLA());
						tc.setCellRenderer(new SOLA_ORTA());
						tc.setMinWidth(200);
						tc.setMaxWidth(200);	
					}
					else if ( FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Ana_Grup-Alt_Grup"))
					{
						tc = tcm.getColumn( 1 );
						tc.setHeaderRenderer(new SOLA());
						tc.setCellRenderer(new SOLA_ORTA());
						tc.setMinWidth(200);
						tc.setMaxWidth(200);	
					}//
					else if ( FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Konsimento"))
					{
					tc = tcm.getColumn( 1 );
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new SOLA_ORTA());
					tc.setMinWidth(100);
					tc.setMaxWidth(100);	
					}
					
				}
				
				tc = tcm.getColumn(sutun + 1 );
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(0,false));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				tc = tcm.getColumn(sutun +2);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				tc = tcm.getColumn(sutun +3);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				tc = tcm.getColumn(sutun +4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(0,false));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				tc = tcm.getColumn(sutun +5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				tc = tcm.getColumn(sutun +6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				tc = tcm.getColumn(sutun +7);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,true));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				tc = tcm.getColumn(sutun +8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(100);
				tc.setMaxWidth(100);
				
				
				tc = tcm.getColumn(sutun +9);
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

				topla(sutun);
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
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private static void topla(int sutun )
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		double  m3 = 0.00  ;
		double urunmiktar = 0  ;
		for (int  i = 0 ; i <= table.getRowCount() -1 ; i ++)
		{
			urunmiktar += Double.parseDouble(mdl.getValueAt(i,sutun + 9).toString());
			m3 += Double.parseDouble( mdl.getValueAt(i,sutun + 7).toString());
		}
		lblmiktar.setText(FORMATLAMA.doub_2( urunmiktar));
		lblm3.setText(FORMATLAMA.doub_3( m3));
			if ( FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Hesap-Kodu"))
			{
				lblm3.setLocation(930, 5);
				lblmiktar.setLocation(1140, 5);
			}
			else if ( FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Ana_Grup-Alt_Grup"))
			{
				lblm3.setLocation(1010, 5);
				lblmiktar.setLocation(1220, 5);
			}
			else if ( FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Konsimento"))
			{
				lblm3.setLocation(830, 5);
				lblmiktar.setLocation(1040, 5);
			}
			else if ( FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Urun Kodu"))
			{
				lblm3.setLocation(730, 5);
				lblmiktar.setLocation(940, 5);
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
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		} 
	}
	public static void excell_aktar()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();

		if (mdl.getRowCount() == 0 )
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Aktarilacak Bilgi Yok....." );
		}
		else
		{
			write() ;	
		}
	}
	@SuppressWarnings("resource")
	public static void write()
	{
		try 
		{
			UIManager.put("FileChooser.cancelButtonText", "Vazgec");
			UIManager.put("FileChooser.saveButtonText", "Kaydet");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.resetChoosableFileFilters();
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileFilter xls = new FileNameExtensionFilter("Microsoft Excel 97-2003 Worksheet (.xls)", "xls");
			FileFilter xlxs = new FileNameExtensionFilter("Microsoft Excel Worksheet (.xlsx) ", "xlsx");
			fileChooser.addChoosableFileFilter(xls);
			fileChooser.addChoosableFileFilter(xlxs);
			fileChooser.setCurrentDirectory(new java.io.File("C:\\OBS_SISTEM\\"));
			fileChooser.setApproveButtonText("Kaydet");
			fileChooser.setDialogTitle("Excell Kayit");   

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");  
			LocalDateTime now = LocalDateTime.now();  
			String zaman = dtf.format(now)  ;

			File outputfile = new File("Envanter_Rapor");
			fileChooser.setSelectedFile(outputfile);
			int returnVal = fileChooser.showSaveDialog(null);
			if ( returnVal != JFileChooser.APPROVE_OPTION )
			{
				return;
			}
			GuiUtil.setWaitCursor(splitPane,true);
			//
			String uzanti ="";
			File excelFile =  FILE_UZANTI. getSelectedFileWithExtension(fileChooser);
			uzanti  = excelFile.getName().substring(excelFile.getName().lastIndexOf("."));
			//
			if  (uzanti.equals(".xls") )
			{
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Envanter_Rapor");
				HSSFFont headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setColor(IndexedColors.BLUE.getIndex()); 
				HSSFCellStyle headerStyle = workbook.createCellStyle();
				HSSFCellStyle headerSolaStyle = workbook.createCellStyle();
				headerStyle.setFont(headerFont);
				headerStyle.setAlignment(HorizontalAlignment.RIGHT);

				HSSFFont solaFont = workbook.createFont();
				solaFont.setFontName("Arial Narrow");
				solaFont. setFontHeight((short)(10*20));
				HSSFCellStyle solaStyle = workbook.createCellStyle();
				solaStyle.setFont(solaFont);
				solaStyle.setAlignment(HorizontalAlignment.LEFT);

				HSSFFont headerSolaFont = workbook.createFont();
				headerSolaFont.setBold(true);
				headerSolaFont.setColor(IndexedColors.BLUE.getIndex()); 
				headerSolaStyle.setFont(headerSolaFont);
				headerSolaStyle.setAlignment(HorizontalAlignment.LEFT);

				HSSFCellStyle satirStyle = workbook.createCellStyle();
				HSSFCellStyle satirStylemik = workbook.createCellStyle();
				HSSFCellStyle satirStyle3 = workbook.createCellStyle();
				HSSFCellStyle satirStyle2 = workbook.createCellStyle();
				HSSFFont satirFont = workbook.createFont();
				satirFont.setFontName("Arial Narrow");
				satirFont. setFontHeight((short)(10*20));
				satirStyle.setFont(satirFont);
				satirStyle.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle3.setFont(satirFont);
				satirStyle2.setFont(satirFont);
				satirStylemik.setFont(satirFont);
				satirStyle3.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
				satirStyle2.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
				satirStylemik.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0"));
				satirStyle3.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle2.setAlignment(HorizontalAlignment.RIGHT);
				satirStylemik.setAlignment(HorizontalAlignment.RIGHT);

				HSSFCellStyle satirStyle3_ARA = workbook.createCellStyle();
				satirStyle3_ARA.setFont(satirFont);
				satirStyle3_ARA.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
				satirStyle3_ARA.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle3_ARA.setBorderTop(BorderStyle.MEDIUM);
				satirStyle3_ARA.setBorderBottom(BorderStyle.MEDIUM);
				
				HSSFCellStyle satirStyle2_ARA = workbook.createCellStyle();
				satirStyle2_ARA.setFont(satirFont);
				satirStyle2_ARA.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
				satirStyle2_ARA.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle2_ARA.setBorderTop(BorderStyle.MEDIUM);
				satirStyle2_ARA.setBorderBottom(BorderStyle.MEDIUM);
				
				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				HSSFCellStyle acikStyle = workbook.createCellStyle();
				HSSFFont acikFont = workbook.createFont();
				acikFont.setColor(IndexedColors.RED.getIndex()); 
				acikFont.setBold(true);
				acikFont.setFontName("Arial");
				acikFont. setFontHeight((short)(22*20));
				acikStyle.setFont(acikFont);
				acikStyle.setAlignment(HorizontalAlignment.CENTER);

				Row baslikRow = sheet.createRow(0);
				sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -1));
				Cell baslikname = baslikRow.createCell(0);

				baslikname.setCellValue( BAGLAN.kerDizin.fIRMA_ADI );
				baslikname.setCellStyle(acikStyle);
				int sutun = 0 ;
				if (FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Urun Kodu"))
				{
					sutun = 0 ;
				}
				else if (FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Konsimento"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Hesap-Kodu"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Ana_Grup-Alt_Grup"))
				{
					sutun = 1 ;
				}//
				Row headerRow = sheet.createRow(1);
				for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
				{
					Cell bname = headerRow.createCell(q);
					if (q > sutun)
					{
						bname.setCellValue(mdl.getColumnName(q));
						bname.setCellStyle(headerStyle);
					}
					else
					{
						bname.setCellValue(mdl.getColumnName(q));
						bname.setCellStyle(headerSolaStyle);
					}
				}
				int satir = 0 ;
				for (int i =0;i< mdl.getRowCount() ;i++)
				{
					Row satirRow = sheet.createRow(i+2);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						if ( mdl.getValueAt(i, s) != null)
						{
							if (s == sutun +3 || s == sutun +6 ||s == sutun +8 || s == sutun +9)
							{
								hname.setCellStyle(satirStyle2);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
							}
							else if (s == sutun +1 || s == sutun +4)
							{
								hname.setCellStyle(satirStylemik);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));	
							}
							else if (s == sutun +2 || s == sutun +5 || s == sutun +7)
							{
								hname.setCellStyle(satirStyle3);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
							}
							else
							{
								hname.setCellValue( mdl.getValueAt(i,s).toString());
								hname.setCellStyle(solaStyle); 
							}
						}
						else
						{
							hname.setCellValue("");
							hname.setCellStyle(satirStyle);
						}
						
					}
					satir += 1 ;
				}
				Row toplam1  = sheet.createRow(satir + 2);
				Cell cell = toplam1.createCell(sutun + 7);
				cell.setCellValue(lblm3.getText());
				cell.setCellStyle(satirStyle3_ARA); 
				
				cell = toplam1.createCell(sutun + 9);
				cell.setCellValue(lblmiktar.getText());
				cell.setCellStyle(satirStyle2_ARA); 
				
				
				for (int i=0; i<= mdl.getColumnCount()-1; i++){
					sheet.autoSizeColumn(i);
				}
				FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile() + "_" + zaman + uzanti));
				workbook.write(out);
				out.close();
			}
			else
			{
				//************************************** XLXS *****************************************************
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("Envanter_Raporlama");
				XSSFFont headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setColor(IndexedColors.BLUE.getIndex()); 
				XSSFCellStyle headerStyle = workbook.createCellStyle();
				XSSFCellStyle headerSolaStyle = workbook.createCellStyle();
				headerStyle.setFont(headerFont);
				headerStyle.setAlignment(HorizontalAlignment.RIGHT);

				XSSFFont solaFont = workbook.createFont();
				solaFont.setFontName("Arial Narrow");
				solaFont. setFontHeight((short)(10*20));
				XSSFCellStyle solaStyle = workbook.createCellStyle();
				solaStyle.setFont(solaFont);
				solaStyle.setAlignment(HorizontalAlignment.LEFT);

				XSSFFont headerSolaFont = workbook.createFont();
				headerSolaFont.setBold(true);
				headerSolaFont.setColor(IndexedColors.BLUE.getIndex()); 
				headerSolaStyle.setFont(headerSolaFont);
				headerSolaStyle.setAlignment(HorizontalAlignment.LEFT);

				XSSFCellStyle satirStyle = workbook.createCellStyle();
				XSSFCellStyle satirStylemik = workbook.createCellStyle();
				XSSFCellStyle satirStyle3 = workbook.createCellStyle();
				XSSFCellStyle satirStyle2 = workbook.createCellStyle();
				XSSFFont satirFont = workbook.createFont();
				satirFont.setFontName("Arial Narrow");
				satirFont. setFontHeight((short)(10*20));
				satirStyle.setFont(satirFont);
				satirStyle.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle3.setFont(satirFont);
				satirStyle2.setFont(satirFont);
				satirStylemik.setFont(satirFont);
				satirStyle3.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
				satirStyle2.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
				satirStylemik.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0"));
				satirStyle3.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle2.setAlignment(HorizontalAlignment.RIGHT);
				satirStylemik.setAlignment(HorizontalAlignment.RIGHT);
				
				XSSFCellStyle satirStyle3_ARA = workbook.createCellStyle();
				satirStyle3_ARA.setFont(satirFont);
				satirStyle3_ARA.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
				satirStyle3_ARA.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle3_ARA.setBorderTop(BorderStyle.MEDIUM);
				satirStyle3_ARA.setBorderBottom(BorderStyle.MEDIUM);
				
				XSSFCellStyle satirStyle2_ARA = workbook.createCellStyle();
				satirStyle2_ARA.setFont(satirFont);
				satirStyle2_ARA.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
				satirStyle2_ARA.setAlignment(HorizontalAlignment.RIGHT);
				satirStyle2_ARA.setBorderTop(BorderStyle.MEDIUM);
				satirStyle2_ARA.setBorderBottom(BorderStyle.MEDIUM);

				DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				XSSFCellStyle acikStyle = workbook.createCellStyle();
				XSSFFont acikFont = workbook.createFont();
				acikFont.setColor(IndexedColors.RED.getIndex()); 
				acikFont.setBold(true);
				acikFont.setFontName("Arial");
				acikFont. setFontHeight((short)(22*20));
				acikStyle.setFont(acikFont);
				acikStyle.setAlignment(HorizontalAlignment.CENTER);

				Row baslikRow = sheet.createRow(0);
				sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -1));
				Cell baslikname = baslikRow.createCell(0);

				baslikname.setCellValue( BAGLAN.kerDizin.fIRMA_ADI );
				baslikname.setCellStyle(acikStyle);
				int sutun = 0 ;
				if (FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Urun Kodu"))
				{
					sutun = 0 ;
				}
				else if (FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Konsimento"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Hesap-Kodu"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Ana_Grup-Alt_Grup"))
				{
					sutun = 1 ;
				}
				Row headerRow = sheet.createRow(1);
				for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
				{
					Cell bname = headerRow.createCell(q);
					if (q > sutun)
					{
						bname.setCellValue(mdl.getColumnName(q));
						bname.setCellStyle(headerStyle);
					}
					else
					{
						bname.setCellValue(mdl.getColumnName(q));
						bname.setCellStyle(headerSolaStyle);
					}
				}
				int satir = 0 ;
				for (int i =0;i< mdl.getRowCount() ;i++)
				{
					Row satirRow = sheet.createRow(i+2);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						if ( mdl.getValueAt(i, s) != null)
						{
							if (s == sutun +3 || s == sutun +6 ||s == sutun +8 || s == sutun +9)
							{
								hname.setCellStyle(satirStyle2);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
							}
							else if (s == sutun +1 || s == sutun +4)
							{
								hname.setCellStyle(satirStylemik);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));	
							}
							else if (s == sutun +2 || s == sutun +5 || s == sutun +7)
							{
								hname.setCellStyle(satirStyle3);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
							}
							else
							{
								hname.setCellValue( mdl.getValueAt(i,s).toString());
								hname.setCellStyle(solaStyle); 
							}
						}
						else
						{
							hname.setCellValue("");
							hname.setCellStyle(satirStyle);
						}
						
					}
					satir += 1 ;
				}
				Row toplam1  = sheet.createRow(satir + 2);
				Cell cell = toplam1.createCell(sutun + 7);
				cell.setCellValue(lblm3.getText());
				cell.setCellStyle(satirStyle3_ARA); 
				
				cell = toplam1.createCell(sutun + 9);
				cell.setCellValue(lblmiktar.getText());
				cell.setCellStyle(satirStyle2_ARA); 
				for (int i=0; i<= mdl.getColumnCount()-1; i++){
					sheet.autoSizeColumn(i);
				}
				FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile()  + "_" + zaman + uzanti));
				workbook.write(out);
				out.close();
				//**************************************
			}
			GuiUtil.setWaitCursor(splitPane,false);
			
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO,"Aktarma Islemi Tamamlandi....." );
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}

	@SuppressWarnings("resource")
	public static void  mail_at()
	{
		try {
			//************************************** XLXS *****************************************************
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Envanter_Raporlama");
			XSSFFont headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex()); 
			XSSFCellStyle headerStyle = workbook.createCellStyle();
			XSSFCellStyle headerSolaStyle = workbook.createCellStyle();
			headerStyle.setFont(headerFont);
			headerStyle.setAlignment(HorizontalAlignment.RIGHT);

			XSSFFont solaFont = workbook.createFont();
			solaFont.setFontName("Arial Narrow");
			solaFont. setFontHeight((short)(10*20));
			XSSFCellStyle solaStyle = workbook.createCellStyle();
			solaStyle.setFont(solaFont);
			solaStyle.setAlignment(HorizontalAlignment.LEFT);

			XSSFFont headerSolaFont = workbook.createFont();
			headerSolaFont.setBold(true);
			headerSolaFont.setColor(IndexedColors.BLUE.getIndex()); 
			headerSolaStyle.setFont(headerSolaFont);
			headerSolaStyle.setAlignment(HorizontalAlignment.LEFT);

			XSSFCellStyle satirStyle = workbook.createCellStyle();
			XSSFCellStyle satirStylemik = workbook.createCellStyle();
			XSSFCellStyle satirStyle3 = workbook.createCellStyle();
			XSSFCellStyle satirStyle2 = workbook.createCellStyle();
			XSSFFont satirFont = workbook.createFont();
			satirFont.setFontName("Arial Narrow");
			satirFont. setFontHeight((short)(10*20));
			satirStyle.setFont(satirFont);
			satirStyle.setAlignment(HorizontalAlignment.RIGHT);
			satirStyle3.setFont(satirFont);
			satirStyle2.setFont(satirFont);
			satirStylemik.setFont(satirFont);
			satirStyle3.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
			satirStyle2.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
			satirStylemik.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0"));
			satirStyle3.setAlignment(HorizontalAlignment.RIGHT);
			satirStyle2.setAlignment(HorizontalAlignment.RIGHT);
			satirStylemik.setAlignment(HorizontalAlignment.RIGHT);
			
			XSSFCellStyle satirStyle3_ARA = workbook.createCellStyle();
			satirStyle3_ARA.setFont(satirFont);
			satirStyle3_ARA.setDataFormat( workbook.createDataFormat().getFormat("###,##0.000"));
			satirStyle3_ARA.setAlignment(HorizontalAlignment.RIGHT);
			satirStyle3_ARA.setBorderTop(BorderStyle.MEDIUM);
			satirStyle3_ARA.setBorderBottom(BorderStyle.MEDIUM);
			
			XSSFCellStyle satirStyle2_ARA = workbook.createCellStyle();
			satirStyle2_ARA.setFont(satirFont);
			satirStyle2_ARA.setDataFormat( workbook.createDataFormat().getFormat("##,###,##0.00"));
			satirStyle2_ARA.setAlignment(HorizontalAlignment.RIGHT);
			satirStyle2_ARA.setBorderTop(BorderStyle.MEDIUM);
			satirStyle2_ARA.setBorderBottom(BorderStyle.MEDIUM);

			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			XSSFCellStyle acikStyle = workbook.createCellStyle();
			XSSFFont acikFont = workbook.createFont();
			acikFont.setColor(IndexedColors.RED.getIndex()); 
			acikFont.setBold(true);
			acikFont.setFontName("Arial");
			acikFont. setFontHeight((short)(22*20));
			acikStyle.setFont(acikFont);
			acikStyle.setAlignment(HorizontalAlignment.CENTER);

				Row baslikRow = sheet.createRow(0);
				sheet.addMergedRegion(new CellRangeAddress(0,0,0,mdl.getColumnCount() -1));
				Cell baslikname = baslikRow.createCell(0);

				baslikname.setCellValue( BAGLAN.kerDizin.fIRMA_ADI );
				baslikname.setCellStyle(acikStyle);
				int sutun = 0 ;
				if (FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Urun Kodu"))
				{
					sutun = 0 ;
				}
				else if (FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Konsimento"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Hesap-Kodu"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_84.getItemAt(FILTRE.comboBox_84.getSelectedIndex()).equals("Ana_Grup-Alt_Grup"))
				{
					sutun = 1 ;
				}
				Row headerRow = sheet.createRow(1);
				for (int q =0;q<= mdl.getColumnCount()-1 ;q++)
				{
					Cell bname = headerRow.createCell(q);
					if (q > sutun)
					{
						bname.setCellValue(mdl.getColumnName(q));
						bname.setCellStyle(headerStyle);
					}
					else
					{
						bname.setCellValue(mdl.getColumnName(q));
						bname.setCellStyle(headerSolaStyle);
					}
				}
				int satir = 0 ;
				for (int i =0;i< mdl.getRowCount() ;i++)
				{
					Row satirRow = sheet.createRow(i+2);
					for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
					{
						Cell hname = satirRow.createCell(s);
						if ( mdl.getValueAt(i, s) != null)
						{
							if (s == sutun +3 || s == sutun +6 ||s == sutun +8 || s == sutun +9)
							{
								hname.setCellStyle(satirStyle2);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
							}
							else if (s == sutun +1 || s == sutun +4)
							{
								hname.setCellStyle(satirStylemik);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));	
							}
							else if (s == sutun +2 || s == sutun +5 || s == sutun +7)
							{
								hname.setCellStyle(satirStyle3);
								hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
							}
							else
							{
								hname.setCellValue( mdl.getValueAt(i,s).toString());
								hname.setCellStyle(solaStyle); 
							}
						}
						else
						{
							hname.setCellValue("");
							hname.setCellStyle(satirStyle);
						}
						
					}
					satir += 1 ;
				}
				Row toplam1  = sheet.createRow(satir + 2);
				Cell cell = toplam1.createCell(sutun + 7);
				cell.setCellValue(lblm3.getText());
				cell.setCellStyle(satirStyle3_ARA); 
				
				cell = toplam1.createCell(sutun + 9);
				cell.setCellValue(lblmiktar.getText());
				cell.setCellStyle(satirStyle2_ARA); 
			for (int i=0; i<= mdl.getColumnCount()-1; i++){
				sheet.autoSizeColumn(i);
			}
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(bos);
			byte[] byteArray= bos.toByteArray();
			InputStream in = new ByteArrayInputStream(byteArray);
			oac.ds = new ByteArrayDataSource(in, "application/x-any");
			bos.close();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
}

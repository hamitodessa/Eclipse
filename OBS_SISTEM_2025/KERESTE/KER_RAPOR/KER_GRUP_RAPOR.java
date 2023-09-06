package KER_RAPOR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import javax.mail.util.ByteArrayDataSource;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.data.category.DefaultCategoryDataset;

import OBS_2025.FILTRE;
import OBS_2025.GuiUtil;
import OBS_2025.OBS_MAIN;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FILE_UZANTI;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;

@SuppressWarnings( {"static-access", "serial","resource"})
public class KER_GRUP_RAPOR extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KERESTE_ACCESS ker_Access = new KERESTE_ACCESS(oac._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
	static CARI_ACCESS c_Access = new CARI_ACCESS(oac._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	private static JTable table;
	private static String qwq6  = "";
	private static String qwq7  = "";
	private static String qwq8  = "";
	private static JLabel lbladet;
	public static JSplitPane splitPane ;
	static String sstr_1 = "" ;
	static String sstr_2 = "" ;
	static String sstr_4 = "" ;
	static String sstr_5 = "" ;
	private static String jkj  = "" ;
	private static String kur_dos = "";
	private static 	long startTime;
	private static int kusur = 0 ;
    private static String hANGI ="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KER_GRUP_RAPOR frame = new KER_GRUP_RAPOR();
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

	public KER_GRUP_RAPOR() {
		setTitle("KERESTE GRUP RAPOR");
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
		
		JLabel lblNewLabel = new JLabel("Satir Sayisi :");
		lblNewLabel.setBounds(10, 5, 85, 14);
		panel.add(lblNewLabel);
		
		lbladet = new JLabel("0");
		lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setBounds(100, 5, 40, 14);
		panel.add(lbladet);
	}
	public static void yenile ()
	{
		try
		{
			GRID_TEMIZLE.grid_temizle(table);
			if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Urun Kodu"))
			{
				baslik_bak();
				if (! sstr_1.equals(""))
				{
					urun_kodlu();
				}
			}
			else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif"))
			{
				baslik_bak();
				if (! sstr_1.equals(""))
					sinif_kodlu();
			}
			else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif-Kal"))
			{
				baslik_bak();
				if (! sstr_1.equals(""))
					sinif_kal_kodlu();
			}
			else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif-Kal-Boy"))
			{
				baslik_bak();
				if (! sstr_1.equals(""))
					sinif_kal_boy_kodlu();
			}
			else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif-Kal-Gen"))
			{
				baslik_bak();
				if (! sstr_1.equals(""))
					sinif_kal_gen_kodlu();
			}
			else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Yil"))
			{
				baslik_bak();
				if (! sstr_1.equals(""))
					yil_kodlu();
			}
			else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Yil-Ay"))
			{
				baslik_bak();
				if (! sstr_1.equals(""))
					yil_ay_kodlu();
			}
			else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Urun Kodu-Yil"))
			{
				baslik_bak();
				if (! sstr_1.equals(""))
					kodu_yil_kodlu();
			}
			else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Paket-Sinif-Kal_Boy"))
			{
				baslik_bak();
				if (! sstr_1.equals(""))
					paket_sinif_kal_boy_kodlu();
			}
			else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Paket-Sinif-Kal_Gen"))
			{
				baslik_bak();
				if (! sstr_1.equals(""))
					paket_sinif_kal_gen_kodlu();
			}
		//
		}
		catch (Exception ex)
		{
			GRID_TEMIZLE.grid_temizle(table);
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void baslik_bak()
	{
		try {
			jkj  = "" ;
			hANGI = "" ;
			if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("GIREN"))
			{
				jkj = " Cikis_Evrak like '%' AND " ;
				hANGI = "" ;
			}
			else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("CIKAN"))
			{
				jkj = " Cikis_Evrak <> '' AND  " ;
				hANGI = "C" ;
			}
			else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("STOK"))
			{
				jkj = " Cikis_Evrak = '' AND " ;
				hANGI = "" ;
				
			}
			ResultSet rs = null ;
			if (FILTRE.comboBox_28_1.getItemAt(FILTRE.comboBox_28_1.getSelectedIndex()).equals("Yil"))
			{
				if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
				{
					rs = ker_Access.baslik_bak("DISTINCT datepart(yyyy,KERESTE."+ hANGI+"Tarih)","order by datepart(yyyy,KERESTE."+ hANGI+"Tarih)",jkj,
							FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
							FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),hANGI);
					sstr_2 = " datepart(yyyy,KERESTE."+ hANGI +"Tarih)" ;
				}
				if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
				{
					rs = ker_Access.baslik_bak("DISTINCT YEAR(STOK."+ hANGI+"Tarih)","order by YEAR(STOK."+ hANGI+"Tarih)",jkj,
							FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
							FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),hANGI);
					sstr_2 = " YEAR(KERESTE."+ hANGI+"Tarih)" ;

				}
			}
			if (FILTRE.comboBox_28_1.getItemAt(FILTRE.comboBox_28_1.getSelectedIndex()).equals("Ay"))
			{
				if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
				{
					rs = ker_Access.baslik_bak("DISTINCT datepart(mm,KERESTE."+ hANGI+"Tarih)", "order by datepart(mm,KERESTE."+ hANGI+"Tarih)",jkj,
							FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
							FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),hANGI);

					sstr_2 = "datepart(mm,KERESTE."+ hANGI +"Tarih)" ;
				}
				if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
				{
					rs = ker_Access.baslik_bak(" DISTINCT MONTH(STOK."+ hANGI +"Tarih)", "order by MONTH(STOK."+ hANGI+"Tarih) ",jkj,
							FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
							FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),hANGI);

					sstr_2 = "MONTH(KERESTE."+ hANGI+"Tarih)" ;

				}
			}
			if (FILTRE.comboBox_28_1.getItemAt(FILTRE.comboBox_28_1.getSelectedIndex()).equals("Gun"))
			{
				if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
				{
					rs = ker_Access.baslik_bak("DISTINCT datepart(dd,KERESTE."+ hANGI+"Tarih)", "order by datepart(dd,KERESTE."+ hANGI+"Tarih)",jkj,
							FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
							FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),hANGI);

					sstr_2 = "datepart(dd,KERESTE."+ hANGI+"Tarih)" ;
				}
				if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
				{
					rs = ker_Access.baslik_bak(" DISTINCT DAY(STOK."+ hANGI+"Tarih)", "order by DAY(STOK."+ hANGI +"Tarih) ",jkj,
							FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
							FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),hANGI);

					sstr_2 = "DAY(KERESTE."+ hANGI+"Tarih)" ;

				}
			}
			if (FILTRE.comboBox_28_1.getItemAt(FILTRE.comboBox_28_1.getSelectedIndex()).equals("Kalinlik"))
			{
				if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
				{
					rs = ker_Access.baslik_bak("DISTINCT CONVERT(INT, SUBSTRING(KERESTE.Kodu, 4, 3) ) ", "order by CONVERT(INT, SUBSTRING(KERESTE.Kodu, 4, 3) ) ",jkj,
							FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
							FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),hANGI);

					sstr_2 = " CONVERT(INT, SUBSTRING(KERESTE.Kodu, 4, 3) ) " ;
				}
				if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
				{

					rs = ker_Access.baslik_bak("DISTINCT   CAST(SUBSTRING(KERESTE.Kodu, 4, 3) AS INTEGER)  ", "order by CAST(SUBSTRING(KERESTE.Kodu, 4, 3) AS INTEGER) ",jkj,
							FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
							FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),hANGI);

					sstr_2 = " CAST(SUBSTRING(KERESTE.Kodu, 4, 3) AS INTEGER) " ;
				}
			}
			if (FILTRE.comboBox_28_1.getItemAt(FILTRE.comboBox_28_1.getSelectedIndex()).equals("Boy"))
			{
				if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
				{
					rs = ker_Access.baslik_bak("DISTINCT CONVERT(INT, SUBSTRING(KERESTE.Kodu, 8, 4) ) ", "order by CONVERT(INT, SUBSTRING(KERESTE.Kodu, 8, 4) ) ",jkj,
							FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
							FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),hANGI);

					sstr_2 = " CONVERT(INT, SUBSTRING(KERESTE.Kodu, 8, 4) ) " ;
				}
				if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
				{
					rs = ker_Access.baslik_bak("DISTINCT   CAST(SUBSTRING(KERESTE.Kodu, 8, 4) AS INTEGER)  ", "order by CAST(SUBSTRING(KERESTE.Kodu, 8, 4) AS INTEGER) ",jkj,
							FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
							FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),hANGI);

					sstr_2 = " CAST(SUBSTRING(KERESTE.Kodu, 8, 4) AS INTEGER) " ;

				}
			} 
			if (FILTRE.comboBox_28_1.getItemAt(FILTRE.comboBox_28_1.getSelectedIndex()).equals("Genislik"))
			{
				if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
				{
					rs = ker_Access.baslik_bak("DISTINCT CONVERT(INT, SUBSTRING(KERESTE.Kodu, 13, 4) ) ", "order by CONVERT(INT, SUBSTRING(KERESTE.Kodu, 13, 4) ) ",jkj,
							FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
							FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),hANGI);

					sstr_2 = " CONVERT(INT, SUBSTRING(KERESTE.Kodu, 13, 4) ) " ;
				}
				if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
				{
					rs = ker_Access.baslik_bak("DISTINCT   CAST(SUBSTRING(KERESTE.Kodu, 13, 4) AS INTEGER)  ", "order by CAST(SUBSTRING(KERESTE.Kodu, 13, 4) AS INTEGER) ",jkj,
							FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
							FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),hANGI);

					sstr_2 = " CAST(SUBSTRING(KERESTE.Kodu, 13, 4) AS INTEGER) " ;

				}
			}
			if (FILTRE.comboBox_28_1.getItemAt(FILTRE.comboBox_28_1.getSelectedIndex()).equals("Sinif"))
			{
				if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
				{
					rs = ker_Access.baslik_bak("DISTINCT  SUBSTRING(KERESTE.Kodu, 1, 2)  ", "order by SUBSTRING(KERESTE.Kodu, 1, 2)  ",jkj,
							FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
							FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),hANGI);

					sstr_2 = "  SUBSTRING(KERESTE.Kodu, 1, 2)  " ;
				}
				else 	if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
				{


				}
			}
			sstr_1 = "";
			String text = "" ;
			while (rs.next())
			{
				text = text +  "[" + rs.getString(1).toString() + "]" + " , " ;
			}
			sstr_1 =  text.equals("") ?   "":    text.substring(0, text.length() - 2);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void urun_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			deg_cevir();
 			grup_cevir() ;
 			if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("GIREN"))
 		     {
 				hANGI = "" ;
 		     }
 			 else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("CIKAN"))
 			 {
 				hANGI = "C" ;
 			}
 			else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("STOK"))
			 {
				hANGI = "" ;
			}
 				rs = ker_Access.grp_rapor("Kodu",sstr_2,sstr_4, kur_dos,   qwq6,  qwq7,  qwq8,
						FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
    					FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
						jkj,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),
						sstr_5, sstr_1," Kodu",hANGI);
			

			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
			
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(120);
				tc.setMaxWidth(120);
				
				
				kusurr();
				for (int i = 1;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				//**
				topla(1);
				//**
				alt_bolum();
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Urun Kodlu Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void sinif_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			deg_cevir();
 			grup_cevir() ;
 			if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("GIREN"))
		     {
				hANGI = "" ;
		     }
			 else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("CIKAN"))
			 {
				hANGI = "C" ;
			}
				rs = ker_Access.grp_rapor(" SUBSTRING(KERESTE.Kodu,1, 2) AS Sinif ",sstr_2,sstr_4, kur_dos,   qwq6,  qwq7,  qwq8,
						FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
    					FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
						jkj,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),
						sstr_5, sstr_1, "Sinif",hANGI);
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(30);
				tc.setMaxWidth(30);
				kusurr();
				for (int i = 1;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				//**
				topla(1);
				//**
				alt_bolum();
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Sinif Kodlu Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void sinif_kal_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			deg_cevir();
 			grup_cevir() ;
 			if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("GIREN"))
		     {
				hANGI = "" ;
		     }
			 else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("CIKAN"))
			 {
				hANGI = "C" ;
			}
				rs = ker_Access.grp_rapor(" SUBSTRING(KERESTE.Kodu,1, 2) AS Sinif ,SUBSTRING(KERESTE.Kodu, 4, 3) as Kal ",sstr_2,sstr_4, kur_dos,   qwq6,  qwq7,  qwq8,
						FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
    					FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
						jkj,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),
						sstr_5, sstr_1, "Sinif , Kal" , hANGI);
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				kusurr();
				for (int i = 2;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				//**
				topla(2);
				//**
				alt_bolum();
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Sinif Kal Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void sinif_kal_boy_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			deg_cevir();
 			grup_cevir() ;
 			if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("GIREN"))
		     {
				hANGI = "" ;
		     }
			 else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("CIKAN"))
			 {
				hANGI = "C" ;
			}
 			
				rs = ker_Access.grp_rapor(" SUBSTRING(KERESTE.Kodu,1, 2) AS Sinif ,SUBSTRING(KERESTE.Kodu, 4, 3) as Kal ,SUBSTRING(KERESTE.Kodu, 8, 4) as Boy ",sstr_2,sstr_4, kur_dos,   qwq6,  qwq7,  qwq8,
						FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
    					FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
						jkj,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),
						sstr_5, sstr_1, "Sinif , Kal,Boy",hANGI);
			GRID_TEMIZLE.grid_temizle(table);
			
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				kusurr();
				for (int i = 3;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				//**
				topla(3);
				//**
				alt_bolum();
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Sinif Kal Boy Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void paket_sinif_kal_boy_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			deg_cevir();
 			grup_cevir() ;
 			if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("GIREN"))
		     {
				hANGI = "" ;
		     }
			 else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("CIKAN"))
			 {
				hANGI = "C" ;
			}
 			
				rs = ker_Access.grp_rapor(" Paket_No,SUBSTRING(KERESTE.Kodu,1, 2) AS Sinif ,SUBSTRING(KERESTE.Kodu, 4, 3) as Kal ,SUBSTRING(KERESTE.Kodu, 8, 4) as Boy ",sstr_2,sstr_4, kur_dos,   qwq6,  qwq7,  qwq8,
						FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
    					FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
						jkj,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),
						sstr_5, sstr_1, "Paket_No,Sinif , Kal,Boy",hANGI);
			GRID_TEMIZLE.grid_temizle(table);
			
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(75);
				tc.setMaxWidth(75);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				kusurr();
				for (int i = 4;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				//**
				topla(4);
				//**
				alt_bolum();
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Sinif Kal Boy Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void paket_sinif_kal_gen_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			deg_cevir();
 			grup_cevir() ;
 			if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("GIREN"))
		     {
				hANGI = "" ;
		     }
			 else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("CIKAN"))
			 {
				hANGI = "C" ;
			}
 			
				rs = ker_Access.grp_rapor(" Paket_No,SUBSTRING(KERESTE.Kodu,1, 2) AS Sinif ,SUBSTRING(KERESTE.Kodu, 4, 3) as Kal ,SUBSTRING(KERESTE.Kodu, 13, 4) as Gen ",sstr_2,sstr_4, kur_dos,   qwq6,  qwq7,  qwq8,
						FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
    					FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
						jkj,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),
						sstr_5, sstr_1, "Paket_No,Sinif , Kal,Gen",hANGI);
			GRID_TEMIZLE.grid_temizle(table);
			
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(75);
				tc.setMaxWidth(75);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				kusurr();
				for (int i = 4;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				//**
				topla(4);
				//**
				alt_bolum();
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Sinif Kal Gen Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void sinif_kal_gen_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			deg_cevir();
 			grup_cevir() ;
 			if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("GIREN"))
		     {
				hANGI = "" ;
		     }
			 else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("CIKAN"))
			 {
				hANGI = "C" ;
			}
				rs = ker_Access.grp_rapor(" SUBSTRING(KERESTE.Kodu,1, 2) AS Sinif ,SUBSTRING(KERESTE.Kodu, 4, 3) as Kal ,SUBSTRING(KERESTE.Kodu, 13, 4) as Gen ",sstr_2,sstr_4, kur_dos,   qwq6,  qwq7,  qwq8,
						FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
    					FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
						jkj,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),
						sstr_5, sstr_1, "Sinif , Kal,Gen",hANGI);
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				kusurr();
				for (int i = 3;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				//**
				topla(3);
				//**
				alt_bolum();
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Sinif Kal Gen Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void yil_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			deg_cevir();
 			grup_cevir() ;
 			if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("GIREN"))
		     {
				hANGI = "" ;
		     }
			 else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("CIKAN"))
			 {
				hANGI = "C" ;
			}
				rs = ker_Access.grp_rapor(" datepart(yyyy,KERESTE.Tarih) AS Yil ",sstr_2,sstr_4, kur_dos,   qwq6,  qwq7,  qwq8,
						FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
    					FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
						jkj,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),
						sstr_5, sstr_1," Yil",hANGI);
			

			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
			
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(120);
				
				
				
				kusurr();
				for (int i = 1;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				//**
				topla(1);
				//**
				alt_bolum();
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Yil Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void yil_ay_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			deg_cevir();
 			grup_cevir() ;
 			if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("GIREN"))
		     {
				hANGI = "" ;
		     }
			 else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("CIKAN"))
			 {
				hANGI = "C" ;
			}
				rs = ker_Access.grp_rapor(" datepart(yyyy,KERESTE.Tarih) AS Yil ,datepart(mm,KERESTE.Tarih) as Ay ",sstr_2,sstr_4, kur_dos,   qwq6,  qwq7,  qwq8,
						FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
    					FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
						jkj,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),
						sstr_5, sstr_1, "Yil , Ay",hANGI);
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				kusurr();
				for (int i = 2;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				//**
				topla(2);
				//**
				alt_bolum();
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Yil Ay Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void kodu_yil_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			deg_cevir();
 			grup_cevir() ;
 			if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("GIREN"))
		     {
				hANGI = "" ;
		     }
			 else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("CIKAN"))
			 {
				hANGI = "C" ;
			}
				rs = ker_Access.grp_rapor(" Kodu , datepart(yyyy,KERESTE.Tarih) AS Yil ",sstr_2,sstr_4, kur_dos,   qwq6,  qwq7,  qwq8,
						FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
    					FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
						jkj,
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1),
						sstr_5, sstr_1, " Kodu ,Yil ",hANGI);
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(110);
				tc.setMaxWidth(110);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);
				tc.setMaxWidth(40);
				
				kusurr();
				for (int i = 2;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				//**
				topla(2);
				//**
				alt_bolum();
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Kodu Yil Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void grup_cevir()
	{
		try {
			ResultSet	rs = null;
			//** Urun Ana grup
			if ( FILTRE.comboBox_78.getItemAt(FILTRE.comboBox_78.getSelectedIndex()).equals(""))
			{
				qwq6 = " Like  '%' " ;
			}
			else if  ( FILTRE.comboBox_78.getItemAt(FILTRE.comboBox_78.getSelectedIndex()).equals("Bos Olanlar"))
			{
				qwq6 = " = '' " ;
			}
			else
			{
				rs = ker_Access.ker_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_78.getItemAt(FILTRE.comboBox_78.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					qwq6 = "=" + Integer.toString( rs.getInt("AGID_Y"));
				}

			}
			//** Urun Alt Grup
			if ( FILTRE.comboBox_79.getItemAt(FILTRE.comboBox_79.getSelectedIndex()).equals(""))
			{
				qwq7 = " Like  '%' " ;
			}
			else if  ( FILTRE.comboBox_79.getItemAt(FILTRE.comboBox_79.getSelectedIndex()).equals("Bos Olanlar"))
			{
				qwq7 = " = '' " ;
			}		        else		      
			{

				rs = ker_Access.ker_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_79.getItemAt(FILTRE.comboBox_79.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					qwq7 ="=" + Integer.toString( rs.getInt("ALID_Y"));
				}

			}
			//** OZ1 OKU
			if ( FILTRE.comboBox_80.getItemAt(FILTRE.comboBox_80.getSelectedIndex()).equals(""))
			{
				qwq8 = " Like  '%' " ;
			}
			else if  ( FILTRE.comboBox_80.getItemAt(FILTRE.comboBox_80.getSelectedIndex()).equals("Bos Olanlar"))
			{
				qwq8 = " = '' " ;
			}		        
			else
			{
				rs =ker_Access.ker_kod_degisken_ara("OZ1ID_Y", "OZEL_KOD_1", "OZ_KOD_1_DEGISKEN", FILTRE.comboBox_80.getItemAt(FILTRE.comboBox_80.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					qwq8 ="=" + Integer.toString( rs.getInt("OZ1ID_Y"));
				}
			}
		} 
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kereste Raporlama", JOptionPane.ERROR_MESSAGE);
		} 
	}
	private static void kusurr()
	{
		 if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("Tutar"))
		{
			kusur = 2 ;
		} 
		if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("Miktar"))
		{
			kusur = 0 ;
		}
		if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("m3"))
		{
			kusur = 3 ;
		}
	}
	private static void deg_cevir()
	{
		String hangiFiatString = "" ;
		 if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("GIREN"))
	     {
			 hangiFiatString = "Fiat " ;
	     }
		 else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("CIKAN"))
		 {
			 hangiFiatString = "CFiat " ;
		}
		 else if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("STOK"))
		 {
			 hangiFiatString = "Fiat " ;
		}
		if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("Tutar"))
		{
			if (FILTRE.chckbxDovizeCevirme_1.isSelected())
			{
				if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
				{
					sstr_4 = " ((((CONVERT(INT, SUBSTRING(KERESTE.Kodu, 4, 3) )  *  CONVERT(INT, SUBSTRING(KERESTE.Kodu, 8, 4)) * CONVERT(INT, SUBSTRING(KERESTE.Kodu, 13, 4) )  ) * Miktar)/1000000000) * " + hangiFiatString + ") / iif(k." + FILTRE.comboBox_77_2.getItemAt(FILTRE.comboBox_77_2.getSelectedIndex())+ " = 0 ,1, k." + FILTRE.comboBox_77_2.getItemAt(FILTRE.comboBox_77_2.getSelectedIndex()) + ") as Tutar ";
				}
				else if (BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
				{
					sstr_4 = " ((((CONVERT(INT, SUBSTRING(KERESTE.Kodu, 4, 3) )  *  CONVERT(INT, SUBSTRING(KERESTE.Kodu, 8, 4)) * CONVERT(INT, SUBSTRING(KERESTE.Kodu, 13, 4) )  ) * Miktar)/1000000000) * " + hangiFiatString + ") / IF(k." + FILTRE.comboBox_77_2.getItemAt(FILTRE.comboBox_77_2.getSelectedIndex())+ " = 0 ,1, k." + FILTRE.comboBox_77_2.getItemAt(FILTRE.comboBox_77_2.getSelectedIndex()) + ") as Tutar ";
				}
			}
			else
			{
				sstr_4 = " ((((CONVERT(INT, SUBSTRING(KERESTE.Kodu, 4, 3) )  *  CONVERT(INT, SUBSTRING(KERESTE.Kodu, 8, 4)) * CONVERT(INT, SUBSTRING(KERESTE.Kodu, 13, 4) )  ) * Miktar)/1000000000) * " + hangiFiatString + ") as Tutar";
			}
			sstr_5 = "Tutar";
		}
		else  if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("Miktar"))
		{
			sstr_4 = " Miktar";
			sstr_5 = "Miktar";
		}
		else  if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("m3"))
		{
			sstr_4 = " (((CONVERT(INT, SUBSTRING(KERESTE.Kodu, 4, 3) )  *  CONVERT(INT, SUBSTRING(KERESTE.Kodu, 8, 4)) * CONVERT(INT, SUBSTRING(KERESTE.Kodu, 13, 4) )  ) * Miktar)/1000000000)  as m3";
			sstr_5 = "m3";
		}
		if (FILTRE.chckbxDovizeCevirme_1.isSelected())
		{
			if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("Tutar"))
			{
				if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
				{
					kur_dos = "  left outer join OK_Kur" + BAGLAN .kurDizin.kOD + ".dbo.kurlar k on k.Tarih = convert(varchar(10), KERESTE.Tarih, 120) and (k.kur IS NULL OR k.KUR ='" + FILTRE.comboBox_77_1.getItemAt(FILTRE.comboBox_77_1.getSelectedIndex())+ "') ";
				}
				else if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
				{
					kur_dos = "  left outer join ok_kur" + BAGLAN .kurDizin.kOD + ".kurlar k on k.Tarih = DATE( KERESTE.Tarih) and  k.kur ='" + FILTRE.comboBox_77_1.getItemAt(FILTRE.comboBox_77_1.getSelectedIndex())+ "' ";
				}
			}
			else
			{
				kur_dos = "";
			}
		}
		else
		{
			kur_dos = "" ;
		}
	}
	private static void topla(int aa)
	{
		double top = 0;
		 DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		 for (int w = 0; w <= mdl.getRowCount()-1;w++)	
		 {
			 top=0 ;
			 for (int a = aa;a<=table.getColumnCount() -2;a++)
			 {
				 if( mdl.getValueAt(w,a) != null)
					{
					top += Double.parseDouble( mdl.getValueAt(w,a).toString());
					}
			 }
			 mdl.setValueAt(top,w, mdl.getColumnCount()-1) ;
		 }
		 //**************************************************************
		 mdl.addRow(new Object[]{});
		 top =0;
		 for (int i = aa;i<=table.getColumnCount() -1;i++)
			{
			for (int q = 0 ;q <= mdl.getRowCount()-2;q++)	
			{
			if( mdl.getValueAt(q,i) != null)
				{
				top += Double.parseDouble( mdl.getValueAt(q,i).toString()); 

				}
			}
			mdl.setValueAt(top,mdl.getRowCount()-1, i) ;
		
			top = 0 ;
			}
	}
	private static void  alt_bolum()
	{
		 int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
		lbladet.setText(FORMATLAMA.doub_0(lastRow));
		table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
		table.setRowSelectionInterval(lastRow, lastRow);
		table.setSelectionBackground(Color.PINK);
		table.setSelectionForeground(Color.BLUE);
		long endTime = System.currentTimeMillis();
		long estimatedTime = endTime - startTime;
		double seconds = (double)estimatedTime/1000; 
		OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
	}
	private static void fontt()
	{
		String deger;
		String[] parts;
		Font bigFont;
			try {
				deger = GLOBAL.setting_oku("KER_RAPORLAMA").toString();
				deger = deger.substring(1, deger.length()-1);
				parts = deger.split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				table.setFont(bigFont);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	public static void grafik()
	{
	DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		
		if (mdl.getRowCount() == 0 )
		{
		JOptionPane.showMessageDialog(null, "Aktarilacak Bilgi Yok.....","Grup Raporlama", JOptionPane.PLAIN_MESSAGE);
		GLOBAL.g_baslik = "";
		return;
		}
		
		if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Yil") || FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif")
				|| FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Urun Kodu"))
		{
		GLOBAL.g_baslik = "KERESTE GRUP RAPORLAMA " + FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex());
		DefaultTableModel mdll = (DefaultTableModel) table.getModel();
		GLOBAL.g_legends = "AYLAR";
		GLOBAL.g_setNumbersAxisTitleText = "Tutar" ;
		ArrayList<Double> tutar = new ArrayList<Double>();
		 for (int i = 0;i<=mdll.getRowCount() - 2 ;i++)
		 {
			 for(int y = 1;y<=mdll.getColumnCount() -2 ;y++)
			 {
			tutar.add(  mdll.getValueAt(i,y) == null ? 0: Double.parseDouble( mdll.getValueAt(i,y).toString()));
			 }
		 }
		GLOBAL.max_value =  Collections.max(tutar) + (Collections.max(tutar) * .05) ;
		GLOBAL.min_value = Collections.min(tutar) - (Collections.min(tutar) * .05) ;
		Double asd = 0.00 ;
		GLOBAL.gkusurat = 0;
		GLOBAL.dataset = new DefaultCategoryDataset();  
		String series1 = "";  
		 for (int i= 0 ;i<=mdll.getRowCount() -2 ;i++)
		 {
			 series1 =  mdll.getValueAt(i,0).toString();
			 for (int y = 1;y<=mdll.getColumnCount() -2;y++)
			 {
					 asd =  mdll.getValueAt(i,y) == null ? 0: Double.parseDouble( mdll.getValueAt(i,y).toString());
					 GLOBAL.dataset.addValue(asd, series1,mdll.getColumnName(y));  
			 }
		 }
		}
	}
	public static void excell_aktar()
	{
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		
		if (mdl.getRowCount() == 0 )
		{
		JOptionPane.showMessageDialog(null, "Aktarilacak Bilgi Yok.....","Grup Raporlama", JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			write() ;	
		}
	}
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
		   
		  File outputfile = new File("Kereste_Grup_Rapor");
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
					   HSSFSheet sheet = workbook.createSheet("Kereste_Grup_Raporlama");
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
						   //
//"Urun Kodu","Sinif" , "Sinif-Kal" ,"Sinif-Kal-Boy","Sinif-Kal-Gen", "Urun Kodu-Yil", "Yil",  "Yil-Ay","Paket-Sinif-Kal_Boy"}));						   
							int sutun = 0 ;
							if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Urun Kodu"))
							{
								sutun = 0 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif"))
							{
								sutun = 0 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif-Kal"))
							{
								sutun = 1 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif-Kal-Boy"))
							{
								sutun = 2 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif-Kal-Gen"))
							{
								sutun = 2 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Urun Kodu-Yil"))
							{
								sutun = 1 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Yil"))
							{
								sutun = 0 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Yil-Ay"))
							{
								sutun = 1 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Paket-Sinif-Kal_Boy"))
							{
								sutun = 3 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Paket-Sinif-Kal_Gen"))
							{
								sutun = 3 ;
							}
							//"
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
						for (int i =0;i< mdl.getRowCount() ;i++)
						{
							 Row satirRow = sheet.createRow(i+2);
							for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
							{
								   Cell hname = satirRow.createCell(s);
								   if ( mdl.getValueAt(i, s) != null)
								   {
									   ////////////
									   if (s > sutun)
									   {
										   if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("Tutar"))
									  		 {
											   hname.setCellStyle(satirStyle2);
									  		 }
										   else  if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("Miktar"))
										  	 {
											   hname.setCellStyle(satirStylemik);
										  	 }
											else  if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("m3"))
											{
												   hname.setCellStyle(satirStyle3);
											 }
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
								
									   }
									   else
									   {
										
										   hname.setCellValue( mdl.getValueAt(i,s).toString());
										   hname.setCellStyle(solaStyle); 
									   }
									   ///////////
								   }
								   else
								   {
									   hname.setCellValue("");
									   hname.setCellStyle(satirStyle);
								   }
							}
						}
						for (int i=0; i<= mdl.getColumnCount()-1; i++){
							sheet.autoSizeColumn(i);
							}
					   //
					   FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile() + "_" + zaman + uzanti));
					   workbook.write(out);
					   out.close();
		    	  }
		    	  else
		    	  {
		    		  //************************************** XLXS *****************************************************
		    		  XSSFWorkbook workbook = new XSSFWorkbook();
					   XSSFSheet sheet = workbook.createSheet("Kereste_Grup_Raporlama");
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
						   
						   baslikname.setCellValue(BAGLAN.kerDizin.fIRMA_ADI );
						   baslikname.setCellStyle(acikStyle);
						   //
							int sutun = 0 ;
							if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Urun Kodu"))
							{
								sutun = 0 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif"))
							{
								sutun = 0 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif-Kal"))
							{
								sutun = 1 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif-Kal-Boy"))
							{
								sutun = 2 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif-Kal-Gen"))
							{
								sutun = 2 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Urun Kodu-Yil"))
							{
								sutun = 1 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Yil"))
							{
								sutun = 0 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Yil-Ay"))
							{
								sutun = 1 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Paket-Sinif-Kal_Boy"))
							{
								sutun = 3 ;
							}
							else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Paket-Sinif-Kal_Gen"))
							{
								sutun = 3 ;
							}
							//
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
						for (int i =0;i< mdl.getRowCount() ;i++)
						{
							 Row satirRow = sheet.createRow(i+2);
							for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
							{
								   Cell hname = satirRow.createCell(s);
								   if ( mdl.getValueAt(i, s) != null)
								   {
									   ////////////
									   if (s > sutun)
									   {
										   if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("Tutar"))
									  		 {
											   hname.setCellStyle(satirStyle2);
									  		 }
										   else  if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("Miktar"))
										  	 {
											   hname.setCellStyle(satirStylemik);
										  	 }
											else  if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("m3"))

											{
												   hname.setCellStyle(satirStyle3);
											 }
										   hname.setCellValue(Double.parseDouble( mdl.getValueAt(i,s).toString()));
									   }
									   else
									   {
										   hname.setCellValue( mdl.getValueAt(i,s).toString());
										   hname.setCellStyle(solaStyle); 
									   }
									   ///////////
								   }
								   else
								   {
									   hname.setCellValue("");
									   hname.setCellStyle(satirStyle);
								   }
							}
						}
						for (int i=0; i<= mdl.getColumnCount()-1; i++){
							sheet.autoSizeColumn(i);
							}
					   //
					   FileOutputStream out = new FileOutputStream(new File(fileChooser.getSelectedFile()  + "_" + zaman + uzanti));
					    workbook.write(out);
					   out.close();
		    		  //**************************************
		    	  }
		    		GuiUtil.setWaitCursor(splitPane,false);
			JOptionPane.showMessageDialog(null, "Aktarma Islemi Tamamlandi.....","Grup Raporlama", JOptionPane.PLAIN_MESSAGE);
	  }
	  catch (Exception ex)
	  {
			JOptionPane.showMessageDialog(null, "Excell Aktarma.....","Grup Raporlama", JOptionPane.ERROR_MESSAGE);
	  }
	  }

	public static void  mail_at()
	{
		try {
		  //************************************** XLXS *****************************************************
		  XSSFWorkbook workbook = new XSSFWorkbook();
		   XSSFSheet sheet = workbook.createSheet("Kereste_Grup_Raporlama");
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
			   
			   baslikname.setCellValue(BAGLAN.kerDizin.fIRMA_ADI );
			   baslikname.setCellStyle(acikStyle);
			   //
				int sutun = 0 ;
				if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Urun Kodu"))
				{
					sutun = 0 ;
				}
				else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif"))
				{
					sutun = 0 ;
				}
				else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif-Kal"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif-Kal-Boy"))
				{
					sutun = 2 ;
				}
				else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Sinif-Kal-Gen"))
				{
					sutun = 2 ;
				}
				else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Urun Kodu-Yil"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Yil"))
				{
					sutun = 0 ;
				}
				else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Yil-Ay"))
				{
					sutun = 1 ;
				}
				else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Paket-Sinif-Kal_Boy"))
				{
					sutun = 3 ;
				}
				else if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Paket-Sinif-Kal_Gen"))
				{
					sutun = 3 ;
				}
				//
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
			for (int i =0;i< mdl.getRowCount() ;i++)
			{
				Row satirRow = sheet.createRow(i+2);
				for (int s =0;s<= mdl.getColumnCount()-1 ;s++)
				{
					Cell hname = satirRow.createCell(s);
					if ( mdl.getValueAt(i, s) != null)
					{
						if (s > sutun)
						{
							if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("Tutar"))
							{
								hname.setCellStyle(satirStyle2);
							}
							else  if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("Miktar"))
							{
								hname.setCellStyle(satirStylemik);
							}
							else  if (FILTRE.comboBox_26_1.getItemAt(FILTRE.comboBox_26_1.getSelectedIndex()).equals("m3"))

							{
								hname.setCellStyle(satirStyle3);
							}
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
			}
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
			JOptionPane.showMessageDialog(null, "Excell Aktarma.....","Grup Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
}

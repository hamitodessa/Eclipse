package KER_RAPOR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_2025.FILTRE;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;

@SuppressWarnings({"serial","static-access","unused"})
public class KER_ORT_SATIS extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KERESTE_ACCESS ker_Access = new KERESTE_ACCESS(oac._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
	
	private static JTable table;
	private static String qwq6  = "";
	private static String qwq7  = "";
	private static JLabel lbladet;
	private static String ozkod ="";
	private static String hANGI = "" ;
	public static JSplitPane splitPane ;
	static String sstr_1 = "" ;
	static String sstr_2 = "" ;
	static String sstr_4 = "" ;
	static String sstr_5 = "" ;
	static String yu = "" ;
	static String iu = "" ;
	private static 	long startTime ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KER_ORT_SATIS frame = new KER_ORT_SATIS();
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

	public KER_ORT_SATIS() {
		setTitle("KERESTE ORTALAMA SATIS");
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
		lblNewLabel.setBounds(10, 7, 85, 14);
		panel.add(lblNewLabel);
		
		lbladet = new JLabel("0");
		lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setBounds(100, 7, 71, 14);
		panel.add(lbladet);

	}
	public static void yenile ()
	{
		 try
		 {
		 	//GRID_TEMIZLE.grid_temizle(table);
			diger_kodlu();
			
		 }
     catch (Exception ex)
		 {
    	 GRID_TEMIZLE.grid_temizle(table);
    		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Ortalama Fiat", JOptionPane.ERROR_MESSAGE);	
    	}
	}
	private static void diger_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			grup_cevir() ;
			rs = ker_Access.ort_diger_kodu(yu,qwq6,qwq7,
					FILTRE.textField_96.getText(),FILTRE.textField_97.getText() ,
					FILTRE.textField_95.getText(),FILTRE.textField_98.getText() ,
					FILTRE.formattedTextField_3.getText(),FILTRE.formattedTextField_1_2.getText() ,
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1_2),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1_2), 
					BAGLAN.kurDizin.kOD ,
					FILTRE.comboBox_82.getItemAt(FILTRE.comboBox_82.getSelectedIndex()),
					iu ,hANGI);

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

				int sut = 0 ;
				if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Sinif"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(90);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(250);
					tc.setMaxWidth(250);
					sut = 2 ;

				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Kodu"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(100);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(250);
					tc.setMaxWidth(250);
					sut = 2 ;

				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Yil"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(60);
					sut = 1 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Yil_Ay"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(60);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(50);

					sut = 2 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Urun Ana Grup"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(175);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(175);

					sut = 2 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Hesap Kodu"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(100);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(200);

					sut = 2 ;
				}
				else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Hesap Kodu-Ana_Alt_Grup"))
				{
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(100);

					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(175);

					tc = tcm.getColumn(2);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(175);

					sut = 3 ;
				}
				tc = tcm.getColumn(sut +0 );
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);

				tc = tcm.getColumn(sut +1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);

				tc = tcm.getColumn(sut + 2);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(90);

				tc = tcm.getColumn(sut + 3);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(90);

				tc = tcm.getColumn(sut + 4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(110);

				tc = tcm.getColumn(sut + 5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(110);

				Dimension dd = th.getPreferredSize();
				dd.height = 30;
				th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				table.setRowSelectionInterval(lastRow, lastRow);
				lbladet.setText(FORMATLAMA.doub_0(table.getRowCount()));
				table.setSelectionBackground(Color.PINK);
				table.setSelectionForeground(Color.BLUE);
				fontt();
			}
		} 
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Ortalama Fiat Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void grup_cevir()
	{
		try 
		{
			ResultSet	rs = null;

			if ( FILTRE.comboBox_83.getItemAt(FILTRE.comboBox_83.getSelectedIndex()).equals("GIREN"))
			{
				hANGI= "" ;
			}
			else if ( FILTRE.comboBox_83.getItemAt(FILTRE.comboBox_83.getSelectedIndex()).equals("CIKAN"))
			{
				hANGI= "C" ;
			}
			else 
			{
				hANGI= "" ;
			}
			//** Urun Ana grup
			if ( FILTRE.comboBox_78_3.getItemAt(FILTRE.comboBox_78_3.getSelectedIndex()).equals(""))
			{
				qwq6 = " Like  '%' " ;
			}
			else if  ( FILTRE.comboBox_78_3.getItemAt(FILTRE.comboBox_78_3.getSelectedIndex()).equals("Bos Olanlar"))
			{
				qwq6 = " = '' " ;
			}
			else
			{
				rs = ker_Access.ker_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_78_3.getItemAt(FILTRE.comboBox_78_3.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					qwq6 = "=" + Integer.toString( rs.getInt("AGID_Y"));
				}

			}
			//** Urun Alt Grup

			if ( FILTRE.comboBox_79_3.getItemAt(FILTRE.comboBox_79_3.getSelectedIndex()).equals(""))
			{
				qwq7 = " Like  '%' " ;
			}
			else if  ( FILTRE.comboBox_79_3.getItemAt(FILTRE.comboBox_79_3.getSelectedIndex()).equals("Bos Olanlar"))
			{
				qwq7 = " = '' " ;
			}		        else		      
			{

				rs = ker_Access.ker_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_79_3.getItemAt(FILTRE.comboBox_79_3.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					qwq7 ="=" + Integer.toString( rs.getInt("ALID_Y"));
				}

			}
			//**
			//** OZ1 OKU
			if ( FILTRE.comboBox_80_5.getItemAt(FILTRE.comboBox_80_5.getSelectedIndex()).equals(""))
			{
				ozkod = " Like  '%' " ;
			}
			else if  ( FILTRE.comboBox_80_5.getItemAt(FILTRE.comboBox_80_5.getSelectedIndex()).equals("Bos Olanlar"))
			{
				ozkod = " = '' " ;
			}		        
			else
			{
				rs =ker_Access.ker_kod_degisken_ara("OZ1ID_Y", "OZEL_KOD_1", "OZ_KOD_1_DEGISKEN", FILTRE.comboBox_80_5.getItemAt(FILTRE.comboBox_80_5.getSelectedIndex()));
				if (!rs.isBeforeFirst() ) {
				}
				else
				{
					rs.next();
					ozkod="=" + Integer.toString( rs.getInt("OZ1ID_Y"));
				}
			}

			/////
			//'**************************************************
			if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Ana Grup"))
			{ 
				yu = " (SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y =  KERESTE."+hANGI+"Ana_Grup ) as Ana_Grup " +
						" , (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y =  KERESTE."+hANGI+"Alt_Grup ) as Alt_Grup ";
				iu = " KERESTE."+hANGI+"Ana_Grup ,  KERESTE."+hANGI+"Alt_Grup order by KERESTE."+hANGI+"Ana_Grup ";
			}
			else  if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Sinif"))
			{
				yu = " SUBSTRING(KERESTE.Kodu,1, 2) as Sinif, (SELECT ACIKLAMA FROM KOD_ACIKLAMA  WHERE KOD = SUBSTRING(KERESTE.Kodu,1, 2) ) as Adi ";
				iu = " SUBSTRING(KERESTE.Kodu,1, 2)  order by SUBSTRING(KERESTE.Kodu,1, 2)  ";
			}
			else  if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Kodu"))
			{
				yu = " KERESTE.Kodu, (SELECT ACIKLAMA FROM KOD_ACIKLAMA  WHERE KOD = SUBSTRING(KERESTE.Kodu,1, 2) ) as Adi ";
				iu = " KERESTE.Kodu  order by KERESTE.Kodu  ";
			}
			else  if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Yil"))
			{
				yu = " datepart(yyyy,KERESTE."+hANGI+"Tarih) as Yil ";
				iu = "  datepart(yyyy,KERESTE."+hANGI+"Tarih) order by datepart(yyyy,KERESTE."+hANGI+"Tarih)  ";
			}
			else  if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Yil_Ay"))
			{
				yu = " datepart(yyyy,KERESTE."+hANGI+"Tarih) as Yil, datepart(mm,KERESTE."+hANGI+"Tarih) as Ay ";
				iu = "  datepart(yyyy,KERESTE."+hANGI+"Tarih) , datepart(mm,KERESTE."+hANGI+"Tarih) order by datepart(yyyy,KERESTE."+hANGI+"Tarih),datepart(mm,KERESTE."+hANGI+"Tarih)  ";
			}
			else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Hesap Kodu-Ana_Alt_Grup"))
			{ 
				yu = " KERESTE."+ hANGI+"Cari_Firma,(SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y =  KERESTE."+hANGI+"Ana_Grup ) as Ana_Grup " +
						" , (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y =  KERESTE."+hANGI+"Alt_Grup ) as Alt_Grup ";
				iu = " KERESTE."+ hANGI+"Cari_Firma,KERESTE."+hANGI+"Ana_Grup ,  KERESTE."+hANGI+"Alt_Grup order by KERESTE."+hANGI+"Ana_Grup ";
			}
			else if (FILTRE.comboBox_27_1_1.getItemAt(FILTRE.comboBox_27_1_1.getSelectedIndex()).toString().equals("Hesap Kodu"))
			{ 
				yu = "   KERESTE."+ hANGI+"Cari_Firma,(SELECT DISTINCT  UNVAN FROM [OK_Car" + BAGLAN.cariDizin.kOD + "].[dbo].[HESAP] WHERE hesap.hesap = KERESTE."+hANGI+"Cari_Firma   ) as Cari_Adi  ";
				iu = " KERESTE."+ hANGI+"Cari_Firma ORDER BY  KERESTE."+ hANGI+"Cari_Firma";
			}
			//'************************************
		} 
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Ort Raporlama", JOptionPane.ERROR_MESSAGE);
		} 
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
}

//dateChooser_20_1_2   - dateChooser_21_1_2
//formattedTextField_3 - formattedTextField_1_2
//h kodu   textField_96  - textField_97
// kons textField_95 - textField_98

// gruplama comboBox_27_1_1
// anagrp comboBox_78_3
// altgrp comboBox_79_3
// ozkod comboBox_80_5

// turu comboBox_83

// dvz comboBox_82

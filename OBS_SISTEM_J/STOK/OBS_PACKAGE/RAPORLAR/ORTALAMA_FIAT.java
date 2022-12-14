package OBS_PACKAGE.RAPORLAR;

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

import OBS_PACKAGE.CONNECTION;
import OBS_PACKAGE.FILTRE;
import OBS_PACKAGE.FORMATLAMA;
import OBS_PACKAGE.GLOBAL;
import OBS_PACKAGE.GRID_TEMIZLE;
import OBS_PACKAGE.OBS_SIS_ANA_CLAS;
import OBS_PACKAGE.SAGA;
import OBS_PACKAGE.SOLA;
import OBS_PACKAGE.TABLO_RENDERER;
import OBS_PACKAGE.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;

public class ORTALAMA_FIAT extends JInternalFrame {
	
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	private static JTable table;
	private static String qwq6  = "";
	private static String qwq7  = "";
	private static JLabel lbladet;
	public static JSplitPane splitPane ;
	static String sstr_1 = "" ;
	static String sstr_2 = "" ;
	static String sstr_4 = "" ;
	static String sstr_5 = "" ;
	static String yu = "" ;
	static String iu = "" ;
	private static  String  fdf  = "" ;
	@SuppressWarnings("unused")
	private static 	long startTime ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ORTALAMA_FIAT frame = new ORTALAMA_FIAT();
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
	public ORTALAMA_FIAT() {
		setTitle("ORTALAMA SATIS");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 900, 600);

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
		lblNewLabel.setBounds(10, 7, 71, 14);
		panel.add(lblNewLabel);
		
		lbladet = new JLabel("0");
		lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setBounds(91, 7, 71, 14);
		panel.add(lbladet);
	}
	public static void yenile ()
	{
		 try
		 {
		 	GRID_TEMIZLE.grid_temizle(table);
				 if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Hesap Kodu-Ana_Alt_Grup"))
					{
					  mus_ana_kodlu();
					}
				 else  if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Hesap Kodu"))
					{
					 mus_kodlu();
					}
				 else 
					{
					 diger_kodlu();
					}
		 }
     catch (Exception ex)
		 {
    	 GRID_TEMIZLE.grid_temizle(table);
    		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Ortalama Fiat", JOptionPane.ERROR_MESSAGE);	
    	}
	}
	private static void mus_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
 			grup_cevir() ;
			if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
			{
					 rs = oac.sTOK_MSSQL.ort_hes_kodu(fdf,qwq6,qwq7,
							 FILTRE.textField_59.getText(),FILTRE.textField_60.getText() ,
							 FILTRE.textField_56.getText(),FILTRE.textField_57.getText() ,
							 FILTRE.textField_51.getText(),FILTRE.textField_52.getText() ,
							 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_26),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_27),  CONNECTION.kurdizinbilgi.kod ,
							 FILTRE.comboBox_50.getItemAt(FILTRE.comboBox_50.getSelectedIndex()) );
					}
				 else
				 {
					 rs = oac.sTOK_MYSQL.ort_hes_kodu(fdf,qwq6,qwq7,
							 FILTRE.textField_59.getText(),FILTRE.textField_60.getText() ,
							 FILTRE.textField_56.getText(),FILTRE.textField_57.getText() ,
							 FILTRE.textField_51.getText(),FILTRE.textField_52.getText() ,
							 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_26),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_27),  CONNECTION.kurdizinbilgi.kod ,
							 FILTRE.comboBox_50.getItemAt(FILTRE.comboBox_50.getSelectedIndex()) );
				 }
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
				tc.setMinWidth(250);
				tc.setMaxWidth(250);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
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
				lbladet.setText(FORMATLAMA.doub_0(table.getRowCount()));
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Hesap Kodu Raporlama",JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void mus_ana_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
 			grup_cevir() ;
			if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
			{
					 rs = oac.sTOK_MSSQL.ort_hes_ana_kodu(fdf,qwq6,qwq7,
							 FILTRE.textField_59.getText(),FILTRE.textField_60.getText() ,
							 FILTRE.textField_56.getText(),FILTRE.textField_57.getText() ,
							 FILTRE.textField_51.getText(),FILTRE.textField_52.getText() ,
							 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_26),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_27),  CONNECTION.kurdizinbilgi.kod ,
							 FILTRE.comboBox_50.getItemAt(FILTRE.comboBox_50.getSelectedIndex()) );
					}
				 else
				 {
					 rs = oac.sTOK_MYSQL.ort_hes_ana_kodu(fdf,qwq6,qwq7,
							 FILTRE.textField_59.getText(),FILTRE.textField_60.getText() ,
							 FILTRE.textField_56.getText(),FILTRE.textField_57.getText() ,
							 FILTRE.textField_51.getText(),FILTRE.textField_52.getText() ,
							 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_26),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_27),  CONNECTION.kurdizinbilgi.kod ,
							 FILTRE.comboBox_50.getItemAt(FILTRE.comboBox_50.getSelectedIndex()) );
				 }
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
				tc.setMinWidth(250);
				tc.setMaxWidth(250);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,false));
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
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
				lbladet.setText(FORMATLAMA.doub_0(table.getRowCount()));
				table.setSelectionBackground(Color.PINK);
				table.setSelectionForeground(Color.BLUE);
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Hesap Kodu Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void diger_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			grup_cevir() ;
			if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
			{
					 rs = oac.sTOK_MSSQL.ort_diger_kodu(yu,qwq6,qwq7,
							 FILTRE.textField_59.getText(),FILTRE.textField_60.getText() ,
							 FILTRE.textField_56.getText(),FILTRE.textField_57.getText() ,
							 FILTRE.textField_51.getText(),FILTRE.textField_52.getText() ,
							 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_26),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_27),  CONNECTION.kurdizinbilgi.kod ,
							 FILTRE.comboBox_50.getItemAt(FILTRE.comboBox_50.getSelectedIndex()),iu );
					}
				 else
				 {
					 rs = oac.sTOK_MYSQL.ort_diger_kodu(yu,qwq6,qwq7,
							 FILTRE.textField_59.getText(),FILTRE.textField_60.getText() ,
							 FILTRE.textField_56.getText(),FILTRE.textField_57.getText() ,
							 FILTRE.textField_51.getText(),FILTRE.textField_52.getText() ,
							 TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_26),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_27),  CONNECTION.kurdizinbilgi.kod ,
							 FILTRE.comboBox_50.getItemAt(FILTRE.comboBox_50.getSelectedIndex()),iu );
				 }
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
				if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Urun Kodu"))
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
				else if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Yil"))
	            {
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(60);
					sut = 1 ;
	            }
				else if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Yil_Ay"))
	            {
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(60);
					
					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(50);
					
					sut = 2 ;
	            }
				else if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Urun Ana Grup"))
	            {
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(150);
					
					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(150);
					
					sut = 2 ;
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
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(sut + 5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
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
				lbladet.setText(FORMATLAMA.doub_0(table.getRowCount()));
				table.setSelectionBackground(Color.PINK);
				table.setSelectionForeground(Color.BLUE);
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Diger Kodlu Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void grup_cevir()
	{
		try {
		ResultSet	rs = null;
				//** Urun Ana grup
				if ( FILTRE.comboBox_56.getItemAt(FILTRE.comboBox_56.getSelectedIndex()).equals(""))
				{
		            qwq6 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_56.getItemAt(FILTRE.comboBox_56.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq6 = " = '' " ;
		        }
		        else
		        {
		        	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		    		{
		    			rs = oac.sTOK_MSSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_56.getItemAt(FILTRE.comboBox_56.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq6 = "=" + Integer.toString( rs.getInt("AGID_Y"));
		    			}
		    		}
		    		else
		    		{
		    			rs = oac.sTOK_MYSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_56.getItemAt(FILTRE.comboBox_56.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    			rs.next();
		    			qwq6 = "=" + Integer.toString(rs.getInt("AGID_Y"));
		    			
		    			}
		    		}
		        }
				//** Urun Alt Grup
				if ( FILTRE.comboBox_57.getItemAt(FILTRE.comboBox_57.getSelectedIndex()).equals(""))
				{
		            qwq7 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_57.getItemAt(FILTRE.comboBox_57.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq7 = " = '' " ;
		        }		        else		      
		        {
		        	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		    		{
		    			rs = oac.sTOK_MSSQL.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_57.getItemAt(FILTRE.comboBox_57.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq7 ="=" + Integer.toString( rs.getInt("ALID_Y"));
		    			}
		    		}
		    		else
		    		{
		    			rs = oac.sTOK_MYSQL.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_57.getItemAt(FILTRE.comboBox_57.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    			rs.next();
		    			qwq7 ="=" + Integer.toString(rs.getInt("ALID_Y"));
		    			}
		    		}
		        }
			//**
			 fdf = " (SELECT DISTINCT  UNVAN FROM [OK_Car" + CONNECTION.caridizinbilgi.kod + "].[dbo].[HESAP] WHERE hesap.hesap = FATURA.Cari_Firma   ) as Cari_Adi " ;
			 //'**************************************************
			 if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Urun Ana Grup"))
				{ 
	                yu = " (SELECT DISTINCT  ANA_GRUP FROM ANA_GRUP_DEGISKEN WHERE ANA_GRUP_DEGISKEN.AGID_Y = MAL.Ana_Grup ) as Ana_Grup " +
	                     " , (SELECT DISTINCT  ALT_GRUP FROM ALT_GRUP_DEGISKEN WHERE ALT_GRUP_DEGISKEN.ALID_Y = MAL.Alt_Grup ) as Alt_Grup ";
	                iu = " mal.Ana_Grup ,  mal.Alt_Grup order by mal.Ana_Grup ";
				}
	            else  if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Urun Kodu"))
	            {
	                yu = " Stok.Urun_Kodu , Mal.Adi ";
	                iu = " Stok.Urun_Kodu, Mal.Adi order by Stok.Urun_Kodu  ";
	            }
	            else  if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Yil"))
	            {
	                yu = " datepart(yyyy,STOK.Tarih) as Yil ";
	                iu = "  datepart(yyyy,STOK.Tarih) order by datepart(yyyy,STOK.Tarih)  ";
	            }
	            else  if (FILTRE.comboBox_51.getItemAt(FILTRE.comboBox_51.getSelectedIndex()).toString().equals("Yil_Ay"))
	            {
	                yu = " datepart(yyyy,STOK.Tarih) as Yil, datepart(mm,STOK.Tarih) as Ay ";
	                iu = "  datepart(yyyy,STOK.Tarih) , datepart(mm,STOK.Tarih) order by datepart(yyyy,STOK.Tarih),datepart(mm,STOK.Tarih)  ";
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
				deger = GLOBAL.setting_oku("STK_RAPORLAMA").toString();
				deger = deger.substring(1, deger.length()-1);
				parts = deger.split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				table.setFont(bigFont);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}

package OBS_2025_RAPORLAR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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

import OBS_PACKAGE.CONNECTION;
import OBS_PACKAGE.FILTRE;
import OBS_PACKAGE.FORMATLAMA;
import OBS_PACKAGE.GLOBAL;
import OBS_PACKAGE.GRID_TEMIZLE;
import OBS_PACKAGE.OBS_MAIN;
import OBS_PACKAGE.OBS_SIS_ANA_CLAS;
import OBS_PACKAGE.SAGA;
import OBS_PACKAGE.SOLA;
import OBS_PACKAGE.TABLO_RENDERER;
import OBS_PACKAGE.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;

public class STOK_RAPOR extends JInternalFrame {

	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	public static JTable table;
	private static String qwq1 ="" ;
	private static String qwq2  = "";
	private static String qwq3  = "";
	private static String qwq4  = "";
	private static String qwq5  = "";
	private static String qwq6  = "";
	private static String qwq7  = "";
	private static JLabel lbl1;
	private static JLabel lbl2 ;
	private static JLabel lbl3 ;
	private static JLabel lbl4 ;
	private static JLabel lbl5 ;
	private static JLabel lbl6 ;
	
	private static JLabel lbladet;
	public static JSplitPane splitPane ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					STOK_RAPOR frame = new STOK_RAPOR();
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
	public STOK_RAPOR() {
		setTitle("STOK_RAPOR");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 1100, 600);

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
		
		lbl3 = new JLabel("0.000");
		lbl3.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl3.setForeground(new Color(0, 0, 128));
		lbl3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl3.setBounds(619, 5, 107, 14);
		panel.add(lbl3);
		
		lbl2 = new JLabel("0.000");
		lbl2.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl2.setForeground(new Color(0, 0, 128));
		lbl2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl2.setBounds(509, 5, 107, 14);
		panel.add(lbl2);
		
		lbl1 = new JLabel("0.000");
		lbl1.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl1.setForeground(new Color(0, 0, 128));
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl1.setBounds(390, 5, 117, 14);
		panel.add(lbl1);
		
		JLabel lblNewLabel = new JLabel("Kayit Sayisi :");
		lblNewLabel.setBounds(10, 5, 71, 14);
		panel.add(lblNewLabel);
		
		lbladet = new JLabel("0");
		lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setBounds(91, 5, 71, 14);
		panel.add(lbladet);
		
		lbl4 = new JLabel("0.000");
		lbl4.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl4.setForeground(new Color(0, 0, 128));
		lbl4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl4.setBounds(730, 5, 107, 14);
		panel.add(lbl4);
		
		lbl5 = new JLabel("0.000");
		lbl5.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl5.setForeground(new Color(0, 0, 128));
		lbl5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl5.setBounds(840, 5, 107, 14);
		panel.add(lbl5);
		
		lbl6 = new JLabel("0.000");
		lbl6.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl6.setForeground(new Color(0, 0, 128));
		lbl6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl6.setBounds(949, 5, 107, 14);
		panel.add(lbl6);
	}
	public static void hisset()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			grup_cevir() ;
			GLOBAL.yazici  = new String[7];
			GLOBAL.yazici[0] = qwq1;
			GLOBAL.yazici[1] = qwq2;
			GLOBAL.yazici[2] = qwq3;
			GLOBAL.yazici[3] = qwq4;
			GLOBAL.yazici[4] = qwq5;
			GLOBAL.yazici[5] = qwq6;
			GLOBAL.yazici[6] = qwq7;
			if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
			{
				if (FILTRE.comboBox_19.getItemAt(FILTRE.comboBox_19.getSelectedIndex()).equals("Urun Kodu"))
				{
				rs = oac.sTOK_MSSQL.envanter_rapor_u_kodu(
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_16),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_17),
						FILTRE.textField_19.getText(),FILTRE.textField_20.getText() ,
						FILTRE.textField_18.getText(),FILTRE.textField_24.getText() ,
						 "","",qwq1, qwq2, qwq3,qwq4,qwq5,qwq6,qwq7 );
				}
				else  // Gruplama
				{
					rs = oac.sTOK_MSSQL.envanter_rapor_ana_grup_alt_grup(
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_16),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_17),
							FILTRE.textField_19.getText(),FILTRE.textField_20.getText() ,
							FILTRE.textField_18.getText(),FILTRE.textField_24.getText() ,
							 "","",qwq1, qwq2, qwq3,qwq4,qwq5,qwq6,qwq7 );
				}
			}
			else
			{
				if (FILTRE.comboBox_19.getItemAt(FILTRE.comboBox_19.getSelectedIndex()).equals("Urun Kodu"))
				{
				rs = oac.sTOK_MYSQL.envanter_rapor_u_kodu(
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_16),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_17),
						FILTRE.textField_19.getText(),FILTRE.textField_20.getText() ,
						FILTRE.textField_18.getText(),FILTRE.textField_24.getText() ,
						 "","",qwq1, qwq2, qwq3,qwq4,qwq5,qwq6,qwq7 );
				}
				else  // Gruplama
				{
					rs = oac.sTOK_MYSQL.envanter_rapor_ana_grup_alt_grup(
							TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_16),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_17),
							FILTRE.textField_19.getText(),FILTRE.textField_20.getText() ,
							FILTRE.textField_18.getText(),FILTRE.textField_24.getText() ,
							 "","",qwq1, qwq2, qwq3,qwq4,qwq5,qwq6,qwq7 );
				}
				}
			
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lbl1.setText(FORMATLAMA.doub_3(0));
				lbl2.setText(FORMATLAMA.doub_3(0));
				lbl3.setText(FORMATLAMA.doub_3(0));
				lbl4.setText(FORMATLAMA.doub_3(0));
				lbl5.setText(FORMATLAMA.doub_3(0));
				lbl6.setText(FORMATLAMA.doub_3(0));
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			
			//**
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			double d1 = 0,d2 = 0,d3 = 0,d4 = 0,d5 = 0,d6 = 0 ;
			if (FILTRE.comboBox_19.getItemAt(FILTRE.comboBox_19.getSelectedIndex()).equals("Urun Kodu"))
			{
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(231);
				tc.setMaxWidth(231);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				
				for (int i = 3;i<=8;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(3,false));
					tc.setMinWidth(110);
				}
				//**
			
				for (int i = 0 ; i <= mdl.getRowCount()-1;i++)
				{
					d1 += (double) mdl.getValueAt(i , 3);
					d2 += (double) mdl.getValueAt(i , 4);
					d3 += (double) mdl.getValueAt(i , 5);
					d4 += (double) mdl.getValueAt(i , 6);
					d5 += (double) mdl.getValueAt(i , 7);
					d6 += (double) mdl.getValueAt(i , 8);
				}
			
			}
			else
			{
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(200);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(198);
				tc.setMaxWidth(198);
				
				for (int i = 2;i<=7;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(3,false));
					tc.setMinWidth(110);
				}
				for (int i = 0 ; i <= mdl.getRowCount()-1;i++)
				{
					d1 += (double) mdl.getValueAt(i , 2);
					d2 += (double) mdl.getValueAt(i , 3);
					d3 += (double) mdl.getValueAt(i , 4);
					d4 += (double) mdl.getValueAt(i , 5);
					d5 += (double) mdl.getValueAt(i , 6);
					d6 += (double) mdl.getValueAt(i , 7);
				}
			}
			//***
			lbl1.setText(FORMATLAMA.doub_3(d1));
			lbl2.setText(FORMATLAMA.doub_3(d2));
			lbl3.setText(FORMATLAMA.doub_3(d3));
			lbl4.setText(FORMATLAMA.doub_3(d4));
			lbl5.setText(FORMATLAMA.doub_3(d5));
			lbl6.setText(FORMATLAMA.doub_3(d6));
			//***
			
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
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Stok  Raporlama",JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void grup_cevir()
	{
		try {
		ResultSet	rs = null;
		//***********************ANA GRUP
		if ( FILTRE.comboBox_14.getItemAt(FILTRE.comboBox_14.getSelectedIndex()).equals(""))
		{
            qwq1 = " Like  '%' " ;
		}
        else if  ( FILTRE.comboBox_14.getItemAt(FILTRE.comboBox_14.getSelectedIndex()).equals("Bos Olanlar"))
        {
            qwq1 = " = '' " ;
        }
        else
        {
        	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
    		{
    			rs = oac.sTOK_MSSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_14.getItemAt(FILTRE.comboBox_14.getSelectedIndex()));
    			if (!rs.isBeforeFirst() ) {
    			}
    			else
    			{
    				rs.next();
    				qwq1 =  "= " + Integer.toString( rs.getInt("AGID_Y") );
    			}
    		}
    		else
    		{
    			rs = oac.sTOK_MYSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_14.getItemAt(FILTRE.comboBox_14.getSelectedIndex()));
    			if (!rs.isBeforeFirst() ) {
    			}
    			else
    			{
    			rs.next();
    			qwq1 = "=" + Integer.toString(rs.getInt("AGID_Y"));
      			}
    		}
        }
		//***********************ALT GRUP
				if ( FILTRE.comboBox_15.getItemAt(FILTRE.comboBox_15.getSelectedIndex()).equals(""))
				{
		            qwq2 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_15.getItemAt(FILTRE.comboBox_15.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq2 = " = '' " ;
		        }		        else		        {
		        	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		    		{
		    			rs = oac.sTOK_MSSQL.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_15.getItemAt(FILTRE.comboBox_15.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq2 ="=" + Integer.toString( rs.getInt("ALID_Y"));
		    			}
		    		}
		    		else
		    		{
		    			rs = oac.sTOK_MYSQL.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_15.getItemAt(FILTRE.comboBox_15.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    			rs.next();
		    			qwq2 ="=" +  Integer.toString(rs.getInt("ALID_Y"));
		    			}
		    		}
		        }
				//***********************DEPO
				if ( FILTRE.comboBox_16.getItemAt(FILTRE.comboBox_16.getSelectedIndex()).equals(""))
				{
		            qwq3 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_16.getItemAt(FILTRE.comboBox_16.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq3 = " = '' " ;
		        }		      
		        else		      
		        {
		        	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		    		{
		    			rs = oac.sTOK_MSSQL.urun_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN", FILTRE.comboBox_16.getItemAt(FILTRE.comboBox_16.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    				rs.next();
		    				qwq3 ="=" + Integer.toString( rs.getInt("DPID_Y"));
		    			}
		    		}
		    		else
		    		{
		    			rs = oac.sTOK_MYSQL.urun_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN", FILTRE.comboBox_16.getItemAt(FILTRE.comboBox_16.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    			rs.next();
		    			qwq3 = "=" + Integer.toString(rs.getInt("DPID_Y"));
		    			}
		    		}
		        }
				//** Urun Ana grup
				if ( FILTRE.comboBox_17.getItemAt(FILTRE.comboBox_17.getSelectedIndex()).equals(""))
				{
		            qwq6 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_17.getItemAt(FILTRE.comboBox_17.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq6 = " = '' " ;
		        }
		        else
		        {
		        	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		    		{
		    			rs = oac.sTOK_MSSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_17.getItemAt(FILTRE.comboBox_17.getSelectedIndex()));
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
		    			rs = oac.sTOK_MYSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", FILTRE.comboBox_17.getItemAt(FILTRE.comboBox_17.getSelectedIndex()));
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
				if ( FILTRE.comboBox_18.getItemAt(FILTRE.comboBox_18.getSelectedIndex()).equals(""))
				{
		            qwq7 = " Like  '%' " ;
				}
		        else if  ( FILTRE.comboBox_18.getItemAt(FILTRE.comboBox_18.getSelectedIndex()).equals("Bos Olanlar"))
		        {
		            qwq7 = " = '' " ;
		        }		        else		      
		        {
		        	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		    		{
		    			rs = oac.sTOK_MSSQL.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_18.getItemAt(FILTRE.comboBox_18.getSelectedIndex()));
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
		    			rs = oac.sTOK_MYSQL.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", FILTRE.comboBox_18.getItemAt(FILTRE.comboBox_18.getSelectedIndex()));
		    			if (!rs.isBeforeFirst() ) {
		    			}
		    			else
		    			{
		    			rs.next();
		    			qwq7 ="=" + Integer.toString(rs.getInt("ALID_Y"));
		    			}
		    		}
		        }
				//***
				if (FILTRE.chckbxNewCheckBox.isSelected())
	                    qwq4 = "E" ;
	                else
	                    qwq4 = "H";
				if (FILTRE.chckbxNewCheckBox_1.isSelected())
	                    qwq5 = "E" ;
	                else
	                    qwq5 = "H";
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Fatura Raporlama",JOptionPane.ERROR_MESSAGE);
		} 
	}
}

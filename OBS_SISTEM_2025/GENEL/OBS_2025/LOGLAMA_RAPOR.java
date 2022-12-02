package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.DOSYA_YAZ;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.ILOGGER;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.TARIH_SAATLI;
import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import LOGER_KAYIT.DOSYA_MSSQL;
import LOGER_KAYIT.DOSYA_MYSQL;
import LOGER_KAYIT.ILOGER_KAYIT;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class LOGLAMA_RAPOR extends JInternalFrame {
	 static JSplitPane splitPane ;
	 static JTable table;
	 static JComboBox<String> comboBox = new JComboBox<String>();
	 private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
		
	 static DOSYA_MYSQL mYSQL = new DOSYA_MYSQL ();
	 static DOSYA_MSSQL mSSQL = new DOSYA_MSSQL ();
	 private static JTextField textField;
	 private static JTextField textField_1;
	 private static JTextField textField_2;
	private static 	JDateChooser dateChooser ;
		private static JDateChooser dateChooser_1 ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGLAMA_RAPOR frame = new LOGLAMA_RAPOR();
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
	public LOGLAMA_RAPOR() {
		setTitle("LOG RAPORLAMA");
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(0, 0, 1250, 600);
		splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 50));
		panel.setMaximumSize(new Dimension(0, 50));
		panel.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Cari Hesap", "Fatura", "Kambiyo"}));
		comboBox.setBounds(10, 11, 142, 22);
		panel.add(comboBox);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(178, 11, 109, 20);dateChooser.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dateChooser.setDate(new Date());
				}
			}
		});
		dateChooser.setDateFormatString("dd.MM.yyyy");
        dateChooser.setFont(new Font("Tahoma", Font.BOLD, 14));
        dateChooser.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel.add(dateChooser);
		
		 dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(297, 11, 109, 20);dateChooser.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dateChooser_1.setDate(new Date());
				}
			}
		});
		dateChooser_1.setDateFormatString("dd.MM.yyyy");
        dateChooser_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        dateChooser_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		panel.add(dateChooser_1);
		
		textField = new JTextField();
		textField.setBounds(424, 12, 349, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(874, 12, 134, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Evrak");
		lblNewLabel.setBounds(816, 15, 48, 14);
		panel.add(lblNewLabel);
		
		textField_2 = new JTextField();
		textField_2.setText("");
		textField_2.setBounds(1108, 12, 96, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblKullanici = new JLabel("Kullanici");
		lblKullanici.setBounds(1050, 15, 48, 14);
		panel.add(lblKullanici);
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.getTableHeader().setReorderingAllowed(false);
		table.setGridColor(oac.gridcolor);
		
		
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		scrollPane.setViewportView(table);
	}
	public static void hisset()
	{
		   try 
		    {
		    	long startTime = System.currentTimeMillis(); 
		    	ResultSet	rs = null;
		    	
		    	 if (  comboBox.getSelectedItem().toString().equals("Cari Hesap"))
		            {
		    		 if(BAGLAN.cariDizin.hAN_SQL.equals("MS SQL"))
		    		 {
								rs  = 	mSSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								  "%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								  BAGLAN_LOG.cariLogDizin);
		    		 }
		    		 else 
		    			 if(BAGLAN.cariDizin.hAN_SQL.equals("MY SQL"))
			    		 {
		    				 rs  = 	mYSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
									  "%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
									  BAGLAN_LOG.cariLogDizin);
			    		 }
		            }
		            else if (  comboBox.getSelectedItem().toString().equals("Fatura"))
		            {
		            	 if(BAGLAN.cariDizin.hAN_SQL.equals("MS SQL"))
			    		 {
		            	rs  = 	mSSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								  "%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								  BAGLAN_LOG.fatLogDizin);
			    		 }
		            	 else if(BAGLAN.cariDizin.hAN_SQL.equals("MY SQL"))
			    		 {
		            		 rs  = 	mYSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
									  "%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
									  BAGLAN_LOG.fatLogDizin);
			    		 }
		            }
		            else if (  comboBox.getSelectedItem().toString().equals("Kambiyo"))
		            {
		            	 if(BAGLAN.kamDizin.hAN_SQL.equals("MS SQL"))
			    		 {
		            	rs  = 	mSSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
								  "%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
								  BAGLAN_LOG.kamLogDizin);
			    		 }
		            	 else if(BAGLAN.kamDizin.hAN_SQL.equals("MY SQL"))
			    		 {
		            		 rs  = 	mYSQL.log_rapor( TARIH_CEVIR.tarih_geri(dateChooser), TARIH_CEVIR.tarih_geri(dateChooser_1),
									  "%" + textField.getText()   + "%",   "%" + textField_1.getText()  + "%" ,"%" + textField_2.getText()  + "%", 
									  BAGLAN_LOG.kamLogDizin);
			    		 }
		            }
	   			if (!rs.isBeforeFirst() ) {  
	   				GRID_TEMIZLE.grid_temizle(table);
	   		//		lblNewLabel_1.setText("0");
	                OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + 0 + " saniye");
	   			    return;
	   			} 
	   			
	   			GRID_TEMIZLE.grid_temizle(table);
				table.setModel(DbUtils.resultSetToTableModel(rs));
					JTableHeader th = table.getTableHeader();
					TableColumnModel tcm = th.getColumnModel();
					TableColumn tc;
						
	
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					//tc.setCellRenderer(new TARIH_SAATLI());
					tc.setMinWidth(110);
					
					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(600);

					tc = tcm.getColumn(2);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(100);

					tc = tcm.getColumn(3);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(100);
					
						
						
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
			//			lblNewLabel_1.setText(FORMATLAMA.doub_0(table.getRowCount()));
						 long endTime = System.currentTimeMillis();
						 long estimatedTime = endTime - startTime; 
						 double seconds = (double)estimatedTime/1000; 
						 OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
						
						
							 String deger;
							 String[] parts;
							Font bigFont;
							try {
								deger = GLOBAL.setting_oku("CARI_ARAMA").toString();
								deger = deger.substring(1, deger.length()-1);
								parts = deger.split(",");
								bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
								table.setFont(bigFont);
							} catch (IOException e) {
								
								e.printStackTrace();
							} 
		            
		    }
		    catch (Exception ex)
		    {
		    	JOptionPane.showMessageDialog(null, ex.getMessage(),"Cari Arama", JOptionPane.ERROR_MESSAGE);
		    	}

	}
}

package OBS_2025;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;

import net.proteanit.sql.DbUtils;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EKSTRE extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(oac._ICar , oac._ICari_Loger);

	
	public static JTable table;
	private static JLabel lblNewLabel_3  ;
	
	public static JLabel lblNewLabel ; 
	public static JLabel lblNewLabel_1 ;
	public static JLabel lblNewLabel_1_1 ;
	public static JLabel lblNewLabel_4_1 ;
	public static JLabel lblNewLabel_5_1 ;
	private static JLabel lblNewLabel_4 ;
	private static JLabel lblNewLabel_5 ;
	private static JLabel lblNewLabel_5_2 ;
	private static JLabel lblNewLabel_4_2 ;
	private static JLabel lblNewLabel_5_2_1 ;
    static double double_1 = 0 ;
    static double double_2 = 0 ;
    static double double_3 = 0 ;
    static double double_4 = 0 ;
    
    public static JSplitPane pane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EKSTRE frame = new EKSTRE();
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
	public EKSTRE() {
		setTitle("EKSTRE");
		setResizable(true);
		setClosable(true);
		setIconifiable(true);
		setMaximizable(true);
		setBounds(0, 0, 1150, 700);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(EKSTRE.DISPOSE_ON_CLOSE);
		pane= new JSplitPane() ;
		pane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pane.setDividerSize(0);
        pane.setResizeWeight(1.0);
      
      
		getContentPane().add(pane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		pane.setRightComponent(panel);
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 85));
		panel.setMaximumSize(new Dimension(0, 85));
		
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("...");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(139, 0, 0));
		lblNewLabel.setBounds(10, 11, 128, 14);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("...");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(139, 0, 0));
		lblNewLabel_1.setBounds(148, 11, 291, 14);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_3 = new JLabel(".....");
		lblNewLabel_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_3.setBounds(10, 65, 187, 14);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("0.00");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setForeground(new Color(0, 0, 128));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(850, 11, 111, 14);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("0.00");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setForeground(new Color(0, 0, 128));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(720, 11, 131, 14);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_4_1 = new JLabel("0.00");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1.setBounds(850, 31, 111, 14);
		panel.add(lblNewLabel_4_1);
		
		lblNewLabel_4_2 = new JLabel("0.00");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2.setBounds(850, 56, 111, 14);
		panel.add(lblNewLabel_4_2);
		
		lblNewLabel_5_1 = new JLabel("0.00");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5_1.setBounds(720, 31, 131, 14);
		panel.add(lblNewLabel_5_1);
		
		lblNewLabel_5_2 = new JLabel("0.00");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_5_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5_2.setBounds(720, 56, 131, 14);
		panel.add(lblNewLabel_5_2);
		
		lblNewLabel_5_2_1 = new JLabel("0.00");
		lblNewLabel_5_2_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_5_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5_2_1.setBounds(970, 56, 131, 14);
		panel.add(lblNewLabel_5_2_1);
		
		lblNewLabel_1_1 = new JLabel("...");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setForeground(new Color(139, 0, 0));
		lblNewLabel_1_1.setBounds(10, 36, 111, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_5_3 = new JLabel("Eski Donem");
		lblNewLabel_5_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5_3.setBounds(588, 11, 100, 14);
		panel.add(lblNewLabel_5_3);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Yeni Donem");
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5_1_1.setBounds(588, 31, 100, 14);
		panel.add(lblNewLabel_5_1_1);
		
		JLabel lblNewLabel_5_2_2 = new JLabel("Toplam");
		lblNewLabel_5_2_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5_2_2.setBounds(588, 56, 100, 14);
		panel.add(lblNewLabel_5_2_2);
		
		JScrollPane scrollPane = new JScrollPane();
		pane.setLeftComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                if (col == 7)
                {
                	if (getValueAt(row,7) != null)
                	{
               
                	double tut = Double.parseDouble(getValueAt(row,7).toString()) ;
    
                		if (tut < 0)
                		{
                		c.setForeground(new Color(128,0,0));
                		 Font fnt = new Font(table.getFont().getFontName(),1 ,table.getFont().getSize());
      		            c.setFont(fnt);
                		}
                		else
                		{
                          c.setForeground(super.getForeground());
                		}
                	}
                }
                return c;
            }
		};
		table.setGridColor(oac.gridcolor);
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
	                OBS_MAIN.btnNewButton_3.doClick();
	                }
				}
				}
				catch (Exception ex)
				{
					
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					 boolean varmi = OBS_MAIN.pencere_bak("DEKONT");
					 if (varmi  ) 
	             		{
	             	try {
	             		OBS_MAIN.pencere_aktiv_yap("DEKONT");
						} catch (PropertyVetoException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	              	}
					 else
	                {
						 JInternalFrame internalFrame;
						 internalFrame  = new DEKONT();
						 OBS_MAIN.desktopPane.add(internalFrame);
						 internalFrame.setVisible(true);
		            }
					try 
					{
						DEKONT.txtevrak.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
						DEKONT.fiskont();
					} 
					catch (NumberFormatException e1) 
					{
						e1.printStackTrace();
					}
					
				}
			}
		});
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(table);
	}
	public static void hisset() 
	{
		long startTime = System.currentTimeMillis();
		double_1 =0 ;
		double_2 =0 ;
		double_3 =0 ;
		double_4 =0 ;
		try {
			ResultSet	rs = null;
			rs = c_Access.ekstre(FILTRE.txtkodu.getText(), TARIH_CEVIR.tarih_geri(FILTRE.dateChooser),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_1));
			//rs = oac.cARI_HESAP_MSSQL. ekstre_proc(FILTRE.txtkodu.getText(),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_1));
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
			} 
			else
			{
			table.setModel(DbUtils.resultSetToTableModel(rs));
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setCellRenderer(new TARIH());
					tc.setMinWidth(80);
					
					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(60);

					tc = tcm.getColumn(2);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(450);
					
					tc = tcm.getColumn(3);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(50);

					tc = tcm.getColumn(4);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(4,false));
					tc.setMinWidth(80);

					tc = tcm.getColumn(5);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(2,false));
					tc.setMinWidth(100);
					
					tc = tcm.getColumn(6);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(2,false));
					tc.setMinWidth(100);
					
					tc = tcm.getColumn(7);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(2,false));
					tc.setMinWidth(120);
					
					tc = tcm.getColumn(8);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(30);
					
					Dimension dd = th.getPreferredSize();
				    dd.height = 30;
				    th.setPreferredSize(dd); 
					th.repaint();
					table.setRowSelectionInterval(0, 0);
					table.setRowHeight(21);
					
			}
					//*************************************************************************
			lblNewLabel_5.setText(FORMATLAMA.doub_2(0.0)); 
			lblNewLabel_4.setText(FORMATLAMA.doub_2(0.0));  
				if (! TARIH_CEVIR.tarih_geri(FILTRE.dateChooser).equals("1900.01.01"))
				{
					 onceki_bakiye ();
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					Vector<Object> data = new Vector<Object>();
					data.add(TARIH_CEVIR.tarih_ekstre(TARIH_CEVIR.tarih_geri(FILTRE.dateChooser)));
					data.add(0);
					data.add("Onceki Bakiye");
					data.add("");
					data.add(1);
					data.add(double_1);
					data.add(double_2);
					data.add(double_2 - double_1);
					data.add("Admin");
  				    model.insertRow(0,data);
  	 				    ///*************************************************************************
  				 double tutar = 0 ;
  				 double borc = 0 ;
  				 double alacak =0 ;
  				 for (int i = 1; i < table.getRowCount()  ; i ++)
  				 {
  					// if(BAGLAN.cariDizin.hAN_SQL.equals("MS SQL"))
  					// {
  				borc =  (double) model.getValueAt(i , 5) ;
   				alacak = (double) model.getValueAt(i , 6);
  				tutar = (double) model.getValueAt(i - 1 , 7) + (  alacak - borc ); 
  					// }
  					// else if (BAGLAN.cariDizin.hAN_SQL.equals("MY SQL")) 
  					// {
//  		 					borc = Double.parseDouble(Float.toString((float) model.getValueAt(i , 5)));
//  		  				    alacak = Double.parseDouble(Float.toString((float) model.getValueAt(i , 6)));
//  		  				    double d =  (double) model.getValueAt(i - 1 , 7) ;
//  		  				    float f = (float)d;
//  		  				    tutar = f +  (  alacak - borc ) ;
  					//	borc =  (double) model.getValueAt(i , 5) ;
  		   			//	alacak = (double) model.getValueAt(i , 6);
  		  			//	tutar = (double) model.getValueAt(i - 1 , 7) + (  alacak - borc ); 
  					// }
 					
  				table.setValueAt((double) Math.round(tutar * 100.0) / 100.0, i, 7);
  					borc = 0;
  					alacak=0; 
  				 }
  				 table.repaint();
  			}
				 //**********  Guncel Bakiye
				 String myDate = TARIH_CEVIR.tarih_geri_SQL(FILTRE.dateChooser);
				 String myDate1 = TARIH_CEVIR.tarih_geri_SQL(FILTRE.dateChooser_1); 
				rs = null;
				rs = c_Access.kasa_mizan(FILTRE.txtkodu.getText(), myDate, myDate1);
				if (!rs.isBeforeFirst() ) {   
					double_3 = 0;
					double_4 = 0;
					lblNewLabel_5_1.setText(FORMATLAMA.doub_2(double_3)); 
					lblNewLabel_4_1.setText(FORMATLAMA.doub_2(double_4));  
				    return;
				} 
				else
				{
					rs.next();
					double_3 = rs.getDouble("islem");
					double_4 = rs.getDouble("islem2");
					lblNewLabel_5_1.setText(FORMATLAMA.doub_2(double_3)); 
					lblNewLabel_4_1.setText(FORMATLAMA.doub_2(double_4));  
				}
				double top1 = 0 ;
				double top2 = 0 ;
				top1=double_1 + double_3;
				lblNewLabel_5_2.setText(FORMATLAMA.doub_2(top1));
				top2=double_2 + double_4 ;
				lblNewLabel_4_2.setText(FORMATLAMA.doub_2(top2));
				lblNewLabel_5_2_1.setText(FORMATLAMA.doub_2((Math.round(top2 * 100.0) / 100.0)- (Math.round(top1 * 100.0) / 100.0)));
				
			String satir ;
		    satir = String.format("Satir Sayisi : %,d %n" ,  table.getRowCount());
			lblNewLabel_3.setText(satir);  
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
			
				deger = GLOBAL.setting_oku("CARI_EKSTRE").toString();
				deger = deger.substring(1, deger.length()-1);
				parts = deger.split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				table.setFont(bigFont);
	
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Cari Ekstre", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private static void onceki_bakiye () throws ClassNotFoundException, SQLException
	{
		ResultSet	rs = null;
		rs = c_Access.mizan(FILTRE.txtkodu.getText(), "1900/01/01", TARIH_CEVIR.chooser_string_eksi1(FILTRE.dateChooser)  + " 23:59:59.000", "   ", "ZZZ", "     ", "ZZZZZ");
		if (!rs.isBeforeFirst() ) {  
			double_1 = 0;
			double_2 = 0;
			lblNewLabel_5.setText(FORMATLAMA.doub_2(double_1)); 
			lblNewLabel_4.setText(FORMATLAMA.doub_2(double_2));  
		} 
		else
		{
			rs.next();
			double_1 = rs.getDouble("ISLEM");
			double_2 = rs.getDouble("ISLEM2");
			lblNewLabel_5.setText(FORMATLAMA.doub_2(double_1)); 
			lblNewLabel_4.setText(FORMATLAMA.doub_2(double_2));  
		}
	}
	public static void sqlite_yaz() 
	{
	try
	{
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		String str= "";
		Date date = null;
		SimpleDateFormat sdf;
	
		c_Access.sqlite_sil();
		 Progres_Bar_Temizle();  
		 OBS_MAIN.progressBar.setStringPainted(true);
	     OBS_MAIN.progressBar.setMaximum(table.getRowCount()-1); 
		for (int i = 0; i < table.getRowCount()  ; i ++)
		 {
			 Progres_Bar(table.getRowCount()-1, i);
			if (i == 0)
			{
				Long trh = (Long) model.getValueAt(i , 0);
				str = TARIH_CEVIR.milis_ddMMyyyy(trh);
			}
			else
			{
			    str =  model.getValueAt(i , 0).toString();
			    sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = (Date) sdf.parse(str);
				str =TARIH_CEVIR.milis_ddMMyyyy(date.getTime());
			}
			//double baki = (double) Math.round(Double.parseDouble(model.getValueAt(i , 7).toString())) ;
			double baki = Math.round(Double.parseDouble(model.getValueAt(i , 7).toString()) * 100.0) / 100.0;
			c_Access.sqlite_yaz(str,Integer.parseInt(model.getValueAt(i , 1).toString()),model.getValueAt(i , 2).toString(),model.getValueAt(i , 3).toString()
					, Double.parseDouble(model.getValueAt(i , 4).toString())  ,  Double.parseDouble(model.getValueAt(i , 5).toString()), Double.parseDouble(model.getValueAt(i , 6).toString()), baki );
			
		}
         Progres_Bar_Temizle();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Ekstre sqllt", JOptionPane.ERROR_MESSAGE);   		
			}
		}
	static void Progres_Bar(int max, int deger) throws InterruptedException
    {
 	    OBS_MAIN.progressBar.setValue(deger);
     }
    static void Progres_Bar_Temizle()
    {
    	OBS_MAIN.progressBar.setMaximum(0);
    	OBS_MAIN.progressBar.setValue(0);
    	OBS_MAIN.progressBar.setStringPainted(false);
    }
}


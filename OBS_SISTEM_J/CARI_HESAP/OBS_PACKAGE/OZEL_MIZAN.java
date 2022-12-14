package OBS_PACKAGE;

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
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultRowSorter;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.RowSorter.SortKey;
import javax.swing.border.LineBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.commons.lang.StringUtils;

import net.proteanit.sql.DbUtils;
import javax.swing.JSplitPane;
import javax.swing.JPanel;

public class OZEL_MIZAN extends JInternalFrame {
	public static JTable table;
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	
	private static JLabel lblonceki ;
	private static JLabel lblborc ;
	private static JLabel lblalacak ;
	private static JLabel lblbakkvartal ;
	private static JLabel lblbakiye ;
	public static JSplitPane splitPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OZEL_MIZAN frame = new OZEL_MIZAN();
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
public OZEL_MIZAN() 
{
	
	setResizable(true);
	setTitle("CARI OZEL MIZAN");
	setMaximizable(true);
	setIconifiable(true);
	setClosable(true);
	setBounds(0,0, 1150, 600);
	
	splitPane = new JSplitPane();
	splitPane.setResizeWeight(1.0);
	splitPane.setDividerSize(0);
	splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	getContentPane().add(splitPane, BorderLayout.CENTER);
	
	JScrollPane scrollPane = new JScrollPane();
	splitPane.setLeftComponent(scrollPane);
	
	table = new JTable(){
		public boolean isCellEditable(int row, int column) {     return false;          }
		public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
            Component c = super.prepareRenderer(renderer, row, col);
            String status = (String)getValueAt(row,0);
            if (status.length() == 3) {
            	c.setBackground(Color.PINK);
		            c.setForeground(Color.BLUE);
		            Font fnt = new Font(table.getFont().getFontName(),1 ,12);
		            c.setFont(fnt);
             } else 
             {
                c.setBackground(super.getBackground());
                c.setForeground(super.getForeground());
            }
            if (col == 7)
            {
            	if (getValueAt(row,7) != null)
            	{
            	double tut = (double)getValueAt(row,7);
            	if (tut < 0)
            	{
            		c.setForeground(new Color(128,0,0));
            		Font fnt = new Font(table.getFont().getFontName(),1 ,table.getFont().getSize());
  		            c.setFont(fnt);
            	}
            	else if (tut >0)
            	{
            		Font fnt = new Font(table.getFont().getFontName(),1 ,table.getFont().getSize());
        			c.setFont(fnt);
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
				boolean varmi = OBS_MAIN.pencere_bak("EKSTRE");
				if (varmi  ) 
             		{
             	try {
             		OBS_MAIN.pencere_aktiv_yap("EKSTRE");
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
              	}
				 else
                {
					 JInternalFrame internalFrame;
					 internalFrame  = new EKSTRE();
					 OBS_MAIN.desktopPane.add(internalFrame);
					 internalFrame.setVisible(true);
	            }
				try 
				{
					//FILTRE intFrame = new FILTRE();
					FILTRE.txtkodu.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					EKSTRE.hisset();
				} 
				catch (NumberFormatException e1) 
				{
					e1.printStackTrace();
				}
				
			}
		}
	});
	
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	table.setRowSelectionAllowed(true);
	table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
	table.setBorder(null);
	
	scrollPane.setViewportView(table);
	
	JPanel panel = new JPanel();
	panel.setBorder(new LineBorder(new Color(0, 191, 255)));
	panel.setMinimumSize(new Dimension(0, 25));
	panel.setMaximumSize(new Dimension(0, 25));
	panel.setLayout(null);
	
	lblbakiye = new JLabel("0.00");
	lblbakiye.setForeground(new Color(0, 0, 128));
	lblbakiye.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblbakiye.setHorizontalAlignment(SwingConstants.RIGHT);
	lblbakiye.setBounds(1003, 5, 117, 14);
	panel.add(lblbakiye);
	
	lblbakkvartal = new JLabel("0.00");
	lblbakkvartal.setForeground(new Color(0, 0, 128));
	lblbakkvartal.setHorizontalAlignment(SwingConstants.RIGHT);
	lblbakkvartal.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblbakkvartal.setBounds(883, 6, 117, 14);
	panel.add(lblbakkvartal);
	
	lblalacak = new JLabel("0.00");
	lblalacak.setForeground(new Color(0, 0, 128));
	lblalacak.setHorizontalAlignment(SwingConstants.RIGHT);
	lblalacak.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblalacak.setBounds(763, 6, 117, 14);
	panel.add(lblalacak);
	splitPane.setRightComponent(panel);
	
	lblborc = new JLabel("0.00");
	lblborc.setHorizontalAlignment(SwingConstants.RIGHT);
	lblborc.setForeground(new Color(0, 0, 128));
	lblborc.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblborc.setBounds(643, 6, 117, 14);
	panel.add(lblborc);
	
	lblonceki = new JLabel("0.00");
	lblonceki.setHorizontalAlignment(SwingConstants.RIGHT);
	lblonceki.setForeground(new Color(0, 0, 128));
	lblonceki.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblonceki.setBounds(523, 6, 117, 14);
	panel.add(lblonceki);

}
public static void hisset ()  
{
	long startTime = System.currentTimeMillis(); 
	try {
		ResultSet	rs = null;
		//**************
		String o1 = "" ;
		String o2 = "" ;
		String hangi_tur = FILTRE.comboBox.getItemAt(FILTRE.comboBox.getSelectedIndex());
		if (hangi_tur.equals("Borclu Hesaplar") )
		{ o1 = " HAVING ( ROUND((ISNULL( (SELECT SUM(SATIRLAR.ALACAK) - SUM(SATIRLAR.BORC)  FROM HESAP WITH (INDEX (IX_HESAP)) , " + 
				"SATIRLAR WITH (INDEX (IX_SATIRLAR)) WHERE   HESAP.HESAP = SATIRLAR.HESAP   AND HESAP.HESAP = h.HESAP 	AND " + 
				"TARIH <  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2) + "'  ) ,0)) + " + 
				"(ISNULL( (SELECT SUM(SATIRLAR.ALACAK)   FROM HESAP WITH (INDEX (IX_HESAP)) , SATIRLAR WITH (INDEX (IX_SATIRLAR)) " + 
				"WHERE   HESAP.HESAP = SATIRLAR.HESAP   AND HESAP.HESAP = h.HESAP AND TARIH " + 
				"BETWEEN  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2) + "' AND  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2_1) + " 23:59:59.998'  ) ,0)  -  " + 
				"ISNULL( (SELECT SUM(SATIRLAR.BORC)  FROM HESAP WITH (INDEX (IX_HESAP)) , SATIRLAR WITH (INDEX (IX_SATIRLAR)) " + 
				"WHERE   HESAP.HESAP = SATIRLAR.HESAP   AND HESAP.HESAP = h.HESAP  AND TARIH " + 
				"BETWEEN  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2) + "' AND  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2_1) + " 23:59:59.998'  ) ,0)),2))  < 0 " ; }
		 else if (hangi_tur.equals("Alacakli Hesaplar"))  
		{ o1 = " HAVING ( ROUND((ISNULL( (SELECT SUM(SATIRLAR.ALACAK) - SUM(SATIRLAR.BORC)  FROM HESAP WITH (INDEX (IX_HESAP)) , " + 
				"SATIRLAR WITH (INDEX (IX_SATIRLAR)) WHERE   HESAP.HESAP = SATIRLAR.HESAP   AND HESAP.HESAP = h.HESAP 	AND " + 
				"TARIH <  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2) + "'  ) ,0)) + " + 
				"(ISNULL( (SELECT SUM(SATIRLAR.ALACAK)   FROM HESAP WITH (INDEX (IX_HESAP)) , SATIRLAR WITH (INDEX (IX_SATIRLAR)) " + 
				"WHERE   HESAP.HESAP = SATIRLAR.HESAP   AND HESAP.HESAP = h.HESAP AND TARIH " + 
				"BETWEEN  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2) + "' AND  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2_1) + " 23:59:59.998'  ) ,0)  -  " + 
				"ISNULL( (SELECT SUM(SATIRLAR.BORC)  FROM HESAP WITH (INDEX (IX_HESAP)) , SATIRLAR WITH (INDEX (IX_SATIRLAR)) " + 
				"WHERE   HESAP.HESAP = SATIRLAR.HESAP   AND HESAP.HESAP = h.HESAP  AND TARIH " + 
				"BETWEEN  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2) + "' AND  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2_1) + " 23:59:59.998'  ) ,0)),2))  > 0 " ; }
		else if (hangi_tur.equals( "Bakiyesi 0 Olanlar" ))     
		{ o1 = " HAVING ( ROUND((ISNULL( (SELECT SUM(SATIRLAR.ALACAK) - SUM(SATIRLAR.BORC)  FROM HESAP WITH (INDEX (IX_HESAP)) , " + 
				"SATIRLAR WITH (INDEX (IX_SATIRLAR)) WHERE   HESAP.HESAP = SATIRLAR.HESAP   AND HESAP.HESAP = h.HESAP 	AND " + 
				"TARIH <  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2) + "'  ) ,0)) + " + 
				"(ISNULL( (SELECT SUM(SATIRLAR.ALACAK)   FROM HESAP WITH (INDEX (IX_HESAP)) , SATIRLAR WITH (INDEX (IX_SATIRLAR)) " + 
				"WHERE   HESAP.HESAP = SATIRLAR.HESAP   AND HESAP.HESAP = h.HESAP AND TARIH " + 
				"BETWEEN  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2) + "' AND  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2_1) + " 23:59:59.998'  ) ,0)  -  " + 
				"ISNULL( (SELECT SUM(SATIRLAR.BORC)  FROM HESAP WITH (INDEX (IX_HESAP)) , SATIRLAR WITH (INDEX (IX_SATIRLAR)) " + 
				"WHERE   HESAP.HESAP = SATIRLAR.HESAP   AND HESAP.HESAP = h.HESAP  AND TARIH " + 
				"BETWEEN  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2) + "' AND  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2_1) + " 23:59:59.998'  ) ,0)),2))  = 0 " ; }
		else if (hangi_tur.equals( "Bakiyesi 0 Olmayanlar" ))
		{ o1 = " HAVING ( ROUND((ISNULL( (SELECT SUM(SATIRLAR.ALACAK) - SUM(SATIRLAR.BORC)  FROM HESAP WITH (INDEX (IX_HESAP)) , " + 
				"SATIRLAR WITH (INDEX (IX_SATIRLAR)) WHERE   HESAP.HESAP = SATIRLAR.HESAP   AND HESAP.HESAP = h.HESAP 	AND " + 
				"TARIH <  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2) + "'  ) ,0)) + " + 
				"(ISNULL( (SELECT SUM(SATIRLAR.ALACAK)   FROM HESAP WITH (INDEX (IX_HESAP)) , SATIRLAR WITH (INDEX (IX_SATIRLAR)) " + 
				"WHERE   HESAP.HESAP = SATIRLAR.HESAP   AND HESAP.HESAP = h.HESAP AND TARIH " + 
				"BETWEEN  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2) + "' AND  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2_1) + " 23:59:59.998'  ) ,0)  -  " + 
				"ISNULL( (SELECT SUM(SATIRLAR.BORC)  FROM HESAP WITH (INDEX (IX_HESAP)) , SATIRLAR WITH (INDEX (IX_SATIRLAR)) " + 
				"WHERE   HESAP.HESAP = SATIRLAR.HESAP   AND HESAP.HESAP = h.HESAP  AND TARIH " + 
				"BETWEEN  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2) + "' AND  '"+ TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2_1) + " 23:59:59.998'  ) ,0)),2))  <> 0 " ; }
		
		o2 = " ORDER BY h.HESAP ASC " ;  
		//**************
		if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
		{
			rs = oac.cARI_HESAP_MSSQL.ozel_mizan(FILTRE.txtilk.getText(),FILTRE.txtson.getText() ,
										TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2_1) ,
										FILTRE.txticins.getText(),FILTRE.txtscins.getText() ,
										FILTRE.txtikarton.getText(),FILTRE.txtskarton.getText() ,
										o1 , o2);
		}
		else
		{
			rs = oac.cARI_HESAP_MYSQL.ozel_mizan(FILTRE.txtilk.getText(),FILTRE.txtson.getText() ,
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2_1) ,
					FILTRE.txticins.getText(),FILTRE.txtscins.getText() ,
					FILTRE.txtikarton.getText(),FILTRE.txtskarton.getText() ,
					o1 , o2);
		}
		GRID_TEMIZLE.grid_temizle(table);
		if (!rs.isBeforeFirst() ) {  
		    return;
		} 
		table.setModel(DbUtils.resultSetToTableModel(rs));
		ara_ayir();
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(95);
			
			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(350);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);
			
			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);
			
			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);
			
			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);
			
			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);
			
			tc = tcm.getColumn(7);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);
			
			Dimension dd = th.getPreferredSize();
		    dd.height = 30;
		    th.setPreferredSize(dd); 
			th.repaint();
			
			//***
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			double borc = 0,alacak = 0,bakiye = 0,bakkvartal =0,onceki = 0 ;
			for (int i = 0 ; i <= mdl.getRowCount()-1;i++)
			{
				onceki  += (double) mdl.getValueAt(i , 3);
				borc  += (double) mdl.getValueAt(i , 4);
				alacak  += (double) mdl.getValueAt(i , 5);
				bakkvartal  += (double)   (mdl.getValueAt(i , 6) == null ? 0 :(double)mdl.getValueAt(i , 6) );
				bakiye  +=  (double)   (mdl.getValueAt(i , 7) == null ? 0 :(double)mdl.getValueAt(i , 7) );
			}
			lblonceki.setText(FORMATLAMA.doub_2(onceki));
			lblborc.setText(FORMATLAMA.doub_2(borc));
			lblalacak.setText(FORMATLAMA.doub_2(alacak));
			lblbakkvartal.setText(FORMATLAMA.doub_2(bakkvartal));
			lblbakiye.setText(FORMATLAMA.doub_2(bakiye));
			//***
			
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);
			
			int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
			table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
			table.setRowSelectionInterval(lastRow, lastRow);
			
			table.setSelectionBackground(Color.GREEN);
			table.setSelectionForeground(Color.BLUE);
			table.repaint();
			 long endTime = System.currentTimeMillis();
			 long estimatedTime = endTime - startTime; 
			 double seconds = (double)estimatedTime/1000; 
			 OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
				try {
					String deger;
					String[] parts;
					Font bigFont;
					deger = GLOBAL.setting_oku("CARI_MIZAN").toString();
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
		JOptionPane.showMessageDialog(null, ex.getMessage(),"Ozel Mizan Raporlama", JOptionPane.ERROR_MESSAGE);
	}
}
private static void ara_ayir()
{
	try
	{
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		Vector<Object> data = new Vector<Object>();
		int satir = table.getRowCount() - 1 ;
		int deger = Integer.parseInt( GLOBAL.setting_oku("CARI_MIZ_GRUP").toString());
		String onceki = "" ;
		for (int i = 1; i <= satir   ; i ++)
		{
			if (  model.getValueAt((i) - 1 , 0).toString().length() < deger  )
			{
				int kj = 0 ;
				kj = deger - model.getValueAt((i) - 1 , 0).toString().length() ;
				onceki =  model.getValueAt((i) - 1 , 0).toString() + StringUtils.repeat(" ", kj);
			}
			else
			{
				onceki = model.getValueAt((i) - 1 , 0).toString().substring(0, deger   );
			}
			if (! model.getValueAt(i , 0).toString().substring(0, model.getValueAt(i  , 0).toString().length() < deger  ? model.getValueAt(i  , 0).toString().length()  : deger ).equals(onceki)) 
			{
				data = new Vector<Object>();
				data.add(model.getValueAt(i , 0).toString().substring(0, model.getValueAt(i  , 0).toString().length() < deger  ? model.getValueAt(i  , 0).toString().length()  : deger ));
				data.add(isimoku(model.getValueAt(i , 0).toString().substring(0, model.getValueAt(i  , 0).toString().length() < deger  ? model.getValueAt(i  , 0).toString().length()  : deger )));

				data.add("---");
				double doub = 0 ;
				data.add(doub);
				data.add(doub);
				data.add(doub);
			    model.addRow(data);
			}
		}
		//*****
		data = new Vector<Object>();
		data.add(model.getValueAt(0 , 0).toString().substring(0,deger));
		data.add(isimoku(model.getValueAt(0 , 0).toString().substring(0,deger)));
		data.add("---");
		double doub = 0 ;
		data.add(doub);
		data.add(doub);
		data.add(doub);
		data.add(doub);
		data.add(doub);
	    model.addRow(data);
		//*****
		   table.setAutoCreateRowSorter(true);
	       DefaultRowSorter<?, ?> sorter = ((DefaultRowSorter<?, ?>)table.getRowSorter()); 
	       ArrayList<SortKey> list = new ArrayList<SortKey>();
	       list.add( new RowSorter.SortKey(0, SortOrder.ASCENDING) );
	       list.add( new RowSorter.SortKey(2,SortOrder.ASCENDING) );
	       sorter.setSortKeys(list);
	       sorter.sort();
	}
	catch (Exception ex)
	{
		JOptionPane.showMessageDialog(null, ex.getMessage(),"Ozel Mizan Raporlama", JOptionPane.ERROR_MESSAGE);
	}
}
private static String isimoku(String kod) {
	String sonuc = "" ;
	try
	{
    ResultSet	rs = null;
    if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
    {
    	rs = oac.cARI_HESAP_MSSQL.hesap_adi_oku(kod);
    }
    else
    {
    	rs = oac.cARI_HESAP_MYSQL.hesap_adi_oku(kod);
    }
	if (!rs.isBeforeFirst() ) {  
		sonuc = "" ;
	} 
	else
	{
	rs.next();
	sonuc=rs.getString("UNVAN");
	}
	
	}
	catch (Exception ex)
	{
		JOptionPane.showMessageDialog(null, ex.getMessage(),"Ozel Mizan Raporlama", JOptionPane.ERROR_MESSAGE);
	}
	return sonuc ;
}
}

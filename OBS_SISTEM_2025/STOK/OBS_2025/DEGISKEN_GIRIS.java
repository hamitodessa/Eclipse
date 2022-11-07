package OBS_2025;

import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

public class DEGISKEN_GIRIS extends JInternalFrame {
	
	static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	
	private static JTable table;
	private static JTextField textField;
	private static JTextField textField_1;
	private static String hangi = "" ;
	private static JComboBox<String> cmbanagrup  ;
	
	private static ResultSet rs=null;
	private static JTextField textField_2;
	private static boolean ilkmi = true;
	
	private static JPanel panel ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DEGISKEN_GIRIS frame = new DEGISKEN_GIRIS("");
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
	public DEGISKEN_GIRIS(String nerden) {
		hangi = nerden ;
		setTitle("DEGISKENLER");
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(500, 100, 358, 563);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 200));
		panel.setMaximumSize(new Dimension(0, 200));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Arama", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1.setBounds(10, 11, 320, 50);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    arama();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    arama();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    arama();
			  }
			});
		textField.setBounds(43, 19, 248, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Kullanici", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_2.setBounds(10, 72, 320, 50);
		panel.add(panel_2);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(43, 19, 248, 20);
		panel_2.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 19, 24, 20);
		textField_2.setVisible(false);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ana Grup", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_3.setBounds(10, 133, 320, 50);
		panel.add(panel_3);
		
		cmbanagrup = new JComboBox<String>();
		cmbanagrup.setForeground(new Color(0, 0, 128));
		cmbanagrup.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbanagrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (! ilkmi)
				{
			    	if (cmbanagrup.getItemCount() == 0 ) return ;
					if (cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals("") ) return ;
					alt_grup_degis();
				}			
			}
		});
		cmbanagrup.setBounds(43, 19, 248, 22);
		panel_3.add(cmbanagrup);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.setGridColor(oac.gridcolor);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				if (table.getRowSorter()!=null) {
				    row = table.getRowSorter().convertRowIndexToModel(row);
				    textField_1.setText(table.getModel().getValueAt(row, 1).toString());
					textField_2.setText(table.getModel().getValueAt(row, 0).toString());
				}
				else
				{
					textField_1.setText(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
					textField_2.setText(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
				}
			}
		});
		scrollPane.setViewportView(table);
		if (nerden.equals("altgrup"))
		{
			panel.setMinimumSize(new Dimension(0, 200));
			panel.setMaximumSize(new Dimension(0, 200));
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Alt Grup", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		}
		else if (nerden.equals("anagrup"))
		{
			panel.setMinimumSize(new Dimension(0,135));
			panel.setMaximumSize(new Dimension(0, 135));
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ana Grup", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		}
		else if (nerden.equals("mensei"))
		{
			panel.setMinimumSize(new Dimension(0,135));
			panel.setMaximumSize(new Dimension(0, 135));
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Mensei", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		}
		else if (nerden.equals("depo"))
		{
			panel.setMinimumSize(new Dimension(0,135));
			panel.setMaximumSize(new Dimension(0, 135));
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Depo", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		}
		else if (nerden.equals("oz1"))
		{
			panel.setMinimumSize(new Dimension(0,135));
			panel.setMaximumSize(new Dimension(0, 135));
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ozel Kod 1", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		}
		else if (nerden.equals("oz2"))
		{
			panel.setMinimumSize(new Dimension(0,135));
			panel.setMaximumSize(new Dimension(0, 135));
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ozel Kod 2", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		}
		yenile();
	}
	private static void yenile()
	{
		try {
		
	        if (hangi.equals("mensei"))
	        {
	        	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
				{
	        	rs =	oac.sTOK_MSSQL.stk_kod_degisken_oku("MENSEI", "MEID_Y", "MENSEI_DEGISKEN");
				}
	        	else
	        	{
	        	rs =	oac.sTOK_MYSQL.stk_kod_degisken_oku("MENSEI", "MEID_Y", "MENSEI_DEGISKEN");
	        	}
	            
	        }
	        else if (hangi.equals("anagrup"))
	        {
	        	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
				{
	        	rs =	oac.sTOK_MSSQL.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
				}
	        	else
	        	{
	        	rs =	oac.sTOK_MYSQL.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
	        	}
	        }
	        else if (hangi.equals("altgrup"))
	        {
	          ana_grup_doldur();
	        }
	        else if (hangi.equals("depo"))
	        {
	            	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
					{
		        	rs =	oac.sTOK_MSSQL.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
					}
		        	else
		        	{
		        	rs =	oac.sTOK_MYSQL.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
		        	}
	        }
	         else if (hangi.equals("oz1"))
	        {
	        	 if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
					{
		        	rs =	oac.sTOK_MSSQL.stk_kod_degisken_oku("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN");
					}
		        	else
		        	{
		        	rs =	oac.sTOK_MYSQL.stk_kod_degisken_oku("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN");
		        	}
	        }
	        else if (hangi.equals("oz2"))
	        {
	        	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
				{
	        	rs =	oac.sTOK_MSSQL.stk_kod_degisken_oku("OZEL_KOD_2", "OZ2ID_Y", "OZ_KOD_2_DEGISKEN");
				}
	        	else
	        	{
	        	rs =	oac.sTOK_MYSQL.stk_kod_degisken_oku("OZEL_KOD_2", "OZ2ID_Y", "OZ_KOD_2_DEGISKEN");
	        	}
	        }
	        //***********TABLO DOLDUR
	        if (!rs.isBeforeFirst() ) {  
			} 
	        else
	        {
	       grid_doldur();
	        }
	     
	        //*************************
		}
		catch (Exception ex)
		{
		
			JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Degiskenler", JOptionPane.ERROR_MESSAGE);        
		}
		}
	private static void grid_doldur()
	{
		try {
		GRID_TEMIZLE.grid_temizle(table);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			table.removeColumn(table.getColumnModel().getColumn(0));
			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			if (table.getRowCount() > 0)
			{
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);
			textField_1.setText(table.getModel().getValueAt(0, 1).toString());
			textField_2.setText(table.getModel().getValueAt(0, 0).toString());
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Tablo Doldur", JOptionPane.ERROR_MESSAGE);    
		}
	}
	public static  void sil()
	{
		try {
        if (hangi.equals("altgrup"))
        {
        	
        	int g =  JOptionPane.showOptionDialog( null,  "Alt Grup Degisken Silinecek ..?" + System.lineSeparator() +
        			"Silme operasyonu butun dosyayi etkileyecek..." + System.lineSeparator() + 
        			"Ilk once Degisken Yenileme Bolumunden degistirip sonra siliniz...."  ,
	        		"Degisken  Silme",   JOptionPane.YES_NO_OPTION,
		   			 	JOptionPane.QUESTION_MESSAGE,
		   			 	null,     //no custom icon
		   			 	oac.options,  //button titles
		   			 	oac.options[1]); //default button
		 	 if(g != 0 ) { return;	}
		 	 
        	///////////************Dosya Kontrol
		 	 int anaG = 0 , altG = 0 ;
		 	 ResultSet rss = null;
		 	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
    		{
    			rss = oac.sTOK_MSSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString());
    		}
    		else
    		{
    			rss = oac.sTOK_MYSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString());
    		}
    		if (!rss.isBeforeFirst() ) {      		
	    	}
	    	else
	    	{
	    		rss.next();
        		anaG  = rss.getInt("AGID_Y");
	    	}
    		
 	      	if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
 			{
 	    		rss = oac.sTOK_MSSQL.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN",  textField_1.getText());
 			}
 			else
 			{
 	    		rss = oac.sTOK_MYSQL.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN", textField_1.getText());
 			}
 	      	if (!rss.isBeforeFirst() ) {      		
	    	}
	    	else
	    	{
	    		rss.next();
     	      	altG  = rss.getInt("ALID_Y");
	    	}
 		 	 if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
				{
			   if (oac.sTOK_MSSQL.alt_grup_kontrol(anaG,altG) )
				   {
					JOptionPane.showMessageDialog(null, "Ilk once Degisken Yenileme Bolumunden degistirip sonra siliniz....",  "Degiskenler", JOptionPane.ERROR_MESSAGE);        
				   return;
				   }
				}
     	  else
     	  	{
     		 if (oac.sTOK_MYSQL.alt_grup_kontrol(anaG,altG) )
			   {
				JOptionPane.showMessageDialog(null, "Ilk once Degisken Yenileme Bolumunden degistirip sonra siliniz....",  "Degiskenler", JOptionPane.ERROR_MESSAGE);        
			   return;
			   }
     	  	}
		 	 //*********************
        	  if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
				{
  			   oac.sTOK_MSSQL.urun_degisken_alt_grup_sil(Integer.parseInt(textField_2.getText()));
				}
        	  else
        	  	{
			   oac.sTOK_MYSQL.urun_degisken_alt_grup_sil(Integer.parseInt(textField_2.getText()));
        	  	}
        	
        	  sifirla();
              textField.setText("");
              ana_grup_doldur();
              grid_doldur();

              textField.requestFocus();
        } else if (hangi.equals("mensei"))
        {
        	int g =  JOptionPane.showOptionDialog( null,  "Mensei  Degisken Silinecek ..?"   ,
	        		"Degisken  Silme",   JOptionPane.YES_NO_OPTION,
		   			 	JOptionPane.QUESTION_MESSAGE,
		   			 	null,     //no custom icon
		   			 	oac.options,  //button titles
		   			 	oac.options[1]); //default button
		 	 if(g != 0 ) { return;	}
		 	 
        	 if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
				{
			   oac.sTOK_MSSQL.urun_kod_degisken_sil( "MEID_Y", "MENSEI_DEGISKEN", Integer.parseInt(textField_2.getText()));
				}
     	  else
     	  	{
			   oac.sTOK_MYSQL.urun_kod_degisken_sil( "MEID_Y", "MENSEI_DEGISKEN", Integer.parseInt(textField_2.getText()));
     	  	}
        	
        	 sifirla();
             textField.setText("");
             yenile();
             grid_doldur();

             textField.requestFocus();
        	 
       
		} else if (hangi.equals("depo"))
        {
        	int g =  JOptionPane.showOptionDialog( null,  "Depo  Degisken Silinecek ..?"   ,
	        		"Degisken  Silme",   JOptionPane.YES_NO_OPTION,
		   			 	JOptionPane.QUESTION_MESSAGE,
		   			 	null,     //no custom icon
		   			 	oac.options,  //button titles
		   			 	oac.options[1]); //default button
		 	 if(g != 0 ) { return;	}
		 	 
        	 if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
				{
			   oac.sTOK_MSSQL.urun_kod_degisken_sil( "DPID_Y", "DEPO_DEGISKEN", Integer.parseInt(textField_2.getText()));
				}
     	  else
     	  	{
			   oac.sTOK_MYSQL.urun_kod_degisken_sil( "DPID_Y", "DEPO_DEGISKEN", Integer.parseInt(textField_2.getText()));
     	  	}
        	
        	 sifirla();
             textField.setText("");
             yenile();
             grid_doldur();

             textField.requestFocus();
        	 
        }
		 else if (hangi.equals("oz1"))
	        {
	        	int g =  JOptionPane.showOptionDialog( null,  "Ozel Kod 1  Degisken Silinecek ..?"   ,
		        		"Degisken  Silme",   JOptionPane.YES_NO_OPTION,
			   			 	JOptionPane.QUESTION_MESSAGE,
			   			 	null,     //no custom icon
			   			 	oac.options,  //button titles
			   			 	oac.options[1]); //default button
			 	 if(g != 0 ) { return;	}
			 	 
	        	 if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
					{
				   oac.sTOK_MSSQL.urun_kod_degisken_sil( "OZ1ID_Y", "OZ_KOD_1_DEGISKEN", Integer.parseInt(textField_2.getText()));
					}
	     	  else
	     	  	{
				   oac.sTOK_MYSQL.urun_kod_degisken_sil( "OZ1ID_Y", "OZ_KOD_1_DEGISKEN", Integer.parseInt(textField_2.getText()));
	     	  	}
	        	
	        	 sifirla();
	             textField.setText("");
	             yenile();
	             grid_doldur();

	             textField.requestFocus();
	        	 
	        }
		 else if (hangi.equals("oz2"))
	        {
	        	int g =  JOptionPane.showOptionDialog( null,  "Ozel Kod 2  Degisken Silinecek ..?"   ,
		        		"Degisken  Silme",   JOptionPane.YES_NO_OPTION,
			   			 	JOptionPane.QUESTION_MESSAGE,
			   			 	null,     //no custom icon
			   			 	oac.options,  //button titles
			   			 	oac.options[1]); //default button
			 	 if(g != 0 ) { return;	}
			 	 
	        	 if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
					{
				   oac.sTOK_MSSQL.urun_kod_degisken_sil( "OZ2ID_Y", "OZ_KOD_2_DEGISKEN", Integer.parseInt(textField_2.getText()));
					}
	     	  else
	     	  	{
				   oac.sTOK_MYSQL.urun_kod_degisken_sil( "OZ2ID_Y", "OZ_KOD_2_DEGISKEN", Integer.parseInt(textField_2.getText()));
	     	  	}
	        	
	        	 sifirla();
	             textField.setText("");
	             yenile();
	             grid_doldur();

	             textField.requestFocus();
	        	 
	        }
		 else if (hangi.equals("anagrup"))
	        {
	        	int g =  JOptionPane.showOptionDialog( null,  "Ana Grup  Degisken Silinecek ..?"   ,
		        		"Degisken  Silme",   JOptionPane.YES_NO_OPTION,
			   			 	JOptionPane.QUESTION_MESSAGE,
			   			 	null,     //no custom icon
			   			 	oac.options,  //button titles
			   			 	oac.options[1]); //default button
			 	 if(g != 0 ) { return;	}
			 	 
	        	 if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
					{
				   oac.sTOK_MSSQL.urun_kod_degisken_sil( "AGID_Y", "ANA_GRUP_DEGISKEN", Integer.parseInt(textField_2.getText()));
					}
	     	  else
	     	  	{
				   oac.sTOK_MYSQL.urun_kod_degisken_sil( "AGID_Y", "ANA_GRUP_DEGISKEN", Integer.parseInt(textField_2.getText()));
	     	  	}
	        	
	        	 sifirla();
	             textField.setText("");
	             yenile();
	             grid_doldur();

	             textField.requestFocus();
	        	 
	        }
       
        
		  }
        catch (Exception ex)
        {
    		JOptionPane.showMessageDialog(null, ex.getMessage(),  "Degiskenler", JOptionPane.ERROR_MESSAGE);        
        }
	}
	private static void ana_grup_doldur()
	{
		try {

		cmbanagrup .removeAllItems();
		if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		{
			rs=null;
			rs = oac.sTOK_MSSQL.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
			//
			if (!rs.isBeforeFirst() ) {      		
	    	}
	    	else
	    	{
	    		while (rs.next())
			    {
			    	cmbanagrup .addItem(rs.getString("ANA_GRUP"));
			    }
	    	}
		    
		}
		else
		{
			rs = oac.sTOK_MYSQL.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
			if (!rs.isBeforeFirst() ) {      		
	    	}
	    	else
	    	{
	    		while (rs.next())
			    {
			    	cmbanagrup .addItem(rs.getString("ANA_GRUP"));
			    }
	    	}
		}
		int in1= 0 ;
		if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		{
			rs = null ;
			rs = oac.sTOK_MSSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
			
			//
			if (!rs.isBeforeFirst() ) {      		
	    	}
	    	else
	    	{
	    		rs.next();
				in1 = rs.getInt("AGID_Y");
				rs = null ;
				rs  = oac.sTOK_MSSQL.stk_kod_alt_grup_degisken_oku(in1);
	    	}
			//
			
		
		}
		else
		{
			rs = null ;
			rs = oac.sTOK_MYSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {      		
	    	}
	    	else
	    	{
			rs.next();
			in1 = rs.getInt("AGID_Y");
			rs = null ;
			rs = oac.sTOK_MYSQL.stk_kod_alt_grup_degisken_oku(in1);
	    	}
		}
		ilkmi=false;

		}
		catch (Exception ex)
		{
	
			JOptionPane.showMessageDialog(null, "DDDDDD" + ex.getMessage(),  "Degiskenler", JOptionPane.ERROR_MESSAGE);    
		}
	}
	private void alt_grup_degis()
	{
		try {
			getContentPane().setCursor(WAIT_CURSOR);
		int in1= 0 ;
		if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		{
			rs = null ;
			rs = oac.sTOK_MSSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
			rs.next();
			in1 = rs.getInt("AGID_Y");
			rs = null ;
			 rs  = oac.sTOK_MSSQL.stk_kod_alt_grup_degisken_oku(in1);
		}
		else
		{
			rs = null ;
			rs = oac.sTOK_MYSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
			rs.next();
			in1 = rs.getInt("AGID_Y");
			rs = null ;
			rs = oac.sTOK_MYSQL.stk_kod_alt_grup_degisken_oku(in1);
		}
		ilkmi= false ;
			if (!rs.isBeforeFirst() ) {  
				textField_1.setText("");
				textField_2.setText("");
				GRID_TEMIZLE.grid_temizle(table);
				getContentPane().setCursor(DEFAULT_CURSOR);
			 }
			else
			{
			 grid_doldur(); 
			 getContentPane().setCursor(DEFAULT_CURSOR);
			}
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Degiskenler", JOptionPane.ERROR_MESSAGE);    
		}		
	}
	public static void yeni ()
	{
        sifirla();
        textField_1.requestFocus();
	}
	private static void sifirla()
	{
		textField_1.setText("");
		textField_2.setText("");
	}
	public void arama()  
	{
		if (textField.getText().equals(""))
		{
			table.setRowSorter(null);
		}
		else
		{
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
		sorter.setStringConverter(new TableStringConverter() {
	        @Override
	        public String toString(TableModel model, int row, int column) {
	            return model.getValueAt(row, column).toString().toLowerCase();
	        }
	    });
	    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText().toLowerCase()));
	    table.setRowSorter(sorter);
		}
	}
	public static void kayit()
	{
		
		 if(textField_1.getText().toString().equals("")) return ;
		   try 
		   {
			           if( ! textField_2.getText().toString().equals("")  )//  ' ESKI KAYIT
			           { 
			        	   if (hangi.equals("mensei"))
			        	   {
			        		   if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
								{
			        			   oac.sTOK_MSSQL.urun_degisken_eski("MENSEI", textField_1.getText(), "MENSEI_DEGISKEN", "MEID_Y",  Integer.parseInt(textField_2.getText()));
			        			   rs =  oac.sTOK_MSSQL.stk_kod_degisken_oku("MENSEI", "MEID_Y", "MENSEI_DEGISKEN");
								}
			        		   else
			        		   {
			        			   oac.sTOK_MYSQL.urun_degisken_eski("MENSEI", textField_1.getText(), "MENSEI_DEGISKEN", "MEID_Y",  Integer.parseInt(textField_2.getText()));
			        			   rs = oac.sTOK_MYSQL.stk_kod_degisken_oku("MENSEI", "MEID_Y", "MENSEI_DEGISKEN");
			        		   }
			        	   }
			        	   else  if (hangi.equals("anagrup"))
			        	   {
			        		   if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
								{
			        			   oac.sTOK_MSSQL.urun_degisken_eski("ANA_GRUP",textField_1.getText(), "ANA_GRUP_DEGISKEN", "AGID_Y", Integer.parseInt(textField_2.getText()));
			        			   rs = oac.sTOK_MSSQL.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
								}
			        		   else
			        		   {
			        			   oac.sTOK_MYSQL.urun_degisken_eski("ANA_GRUP",textField_1.getText(), "ANA_GRUP_DEGISKEN", "AGID_Y",  Integer.parseInt(textField_2.getText()));
			        			   rs = oac.sTOK_MYSQL.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
			        		   }
			        	   }
			        	   else  if (hangi.equals("altgrup"))
			        	   {
			        		   if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
								{
			        			  int in1  = 0;
			        	           ResultSet rss = oac.sTOK_MSSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
			        	           rss.next();
			        	           in1 = rss.getInt("AGID_Y");
			        			   oac.sTOK_MSSQL.urun_degisken_alt_grup_eski(textField_1.getText(), in1, Integer.parseInt(textField_2.getText()));
			        			   rs = oac.sTOK_MSSQL.stk_kod_alt_grup_degisken_oku(in1);
								}
			        		   else
			        		   {
			        			   int in1  = 0;
			        	           ResultSet rss = oac.sTOK_MYSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
			        	           rss.next();
			        	           in1 = rss.getInt("AGID_Y");
			        			   oac.sTOK_MYSQL.urun_degisken_alt_grup_eski(textField_1.getText(), in1, Integer.parseInt(textField_2.getText()));
			        			   rs = oac.sTOK_MYSQL.stk_kod_alt_grup_degisken_oku(in1);
			        		   }
			        	   }
			        	   else  if (hangi.equals("depo"))
			        	   {
			        		   if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
								{
			        			   oac.sTOK_MSSQL.urun_degisken_eski("DEPO", textField_1.getText(), "DEPO_DEGISKEN", "DPID_Y",  Integer.parseInt(textField_2.getText()));
			        			   rs =oac.sTOK_MSSQL.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
								}
			        		   else
			        		   {
			        			   oac.sTOK_MYSQL.urun_degisken_eski("DEPO", textField_1.getText(), "DEPO_DEGISKEN", "DPID_Y",  Integer.parseInt(textField_2.getText()));
			        			   rs = oac.sTOK_MYSQL.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
			        		   }
			        	   }
			        	   else  if (hangi.equals("oz1"))
			        	   {
			        		   if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
								{
			        			   oac.sTOK_MSSQL.urun_degisken_eski("OZEL_KOD_1", textField_1.getText(), "OZ_KOD_1_DEGISKEN", "OZ1ID_Y",  Integer.parseInt(textField_2.getText()));
			        			   rs = oac.sTOK_MSSQL.stk_kod_degisken_oku("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN");
								}
			        		   else
			        		   {
			        			   oac.sTOK_MYSQL.urun_degisken_eski("OZEL_KOD_1", textField_1.getText(), "OZ_KOD_1_DEGISKEN", "OZ1ID_Y",  Integer.parseInt(textField_2.getText()));
			        			   rs =oac.sTOK_MYSQL.stk_kod_degisken_oku("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN");
			        		   }
			        	   }
			        	   else  if (hangi.equals("oz2"))
			        	   {
			        		   if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
								{
			        			   oac.sTOK_MSSQL.urun_degisken_eski("OZEL_KOD_2", textField_1.getText(), "OZ_KOD_2_DEGISKEN", "OZ2ID_Y", Integer.parseInt(textField_2.getText()));
			        			   rs = oac.sTOK_MSSQL.stk_kod_degisken_oku("OZEL_KOD_2", "OZ2ID_Y", "OZ_KOD_2_DEGISKEN");
								}
			        		   else
			        		   {
			        			   oac.sTOK_MYSQL.urun_degisken_eski("OZEL_KOD_2", textField_1.getText(), "OZ_KOD_2_DEGISKEN", "OZ2ID_Y",  Integer.parseInt(textField_2.getText()));
			        			   rs =oac.sTOK_MYSQL.stk_kod_degisken_oku("OZEL_KOD_2", "OZ2ID_Y", "OZ_KOD_2_DEGISKEN");
			        		   }
			        	   }
			           }
			           else  // YENI KAYIT
			           {
			        	  
			        	   if (hangi.equals("mensei"))
			        	   {
			        		   if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
								{
			        			   oac.sTOK_MSSQL.urun_degisken_kayit("MEID_Y","MENSEI_DEGISKEN", "MENSEI", textField_1.getText());
			        			   rs =   oac.sTOK_MSSQL.stk_kod_degisken_oku("MENSEI", "MEID_Y", "MENSEI_DEGISKEN");
								}
			        		   else
			        		   {
			        			   oac.sTOK_MYSQL.urun_degisken_kayit("MEID_Y","MENSEI_DEGISKEN", "MENSEI", textField_1.getText());
			        			   rs = oac.sTOK_MYSQL.stk_kod_degisken_oku("MENSEI", "MEID_Y", "MENSEI_DEGISKEN");
			        		   }
			        	   }
			        	   else  if (hangi.equals("anagrup"))
			        	   {
			        		   if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
								{
			        			  oac.sTOK_MSSQL.urun_degisken_kayit("AGID_Y","ANA_GRUP_DEGISKEN", "ANA_GRUP", textField_1.getText());
				        		  rs = oac.sTOK_MSSQL.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
								}
			        		   else
			        		   {
			        			   oac.sTOK_MYSQL.urun_degisken_kayit("AGID_Y","ANA_GRUP_DEGISKEN", "ANA_GRUP", textField_1.getText());
			        			   rs = oac.sTOK_MYSQL.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
			        		   }
			        	   }
			        	   else  if (hangi.equals("altgrup"))
			        	   {
			        		   if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
								{
			        			  int in1  = 0;  
			        	           ResultSet rss = oac.sTOK_MSSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
			        	           while (rss.next())
			        	   	    {
			        	           in1 = rss.getInt("AGID_Y");
			        			   oac.sTOK_MSSQL.urun_degisken_alt_grup_kayit(textField_1.getText(), in1);
			        			   rs = oac.sTOK_MSSQL.stk_kod_alt_grup_degisken_oku(in1);
			        	   	    }
								}
			        		   else 
			        		   {
			        			   int in1  = 0;
			        	           ResultSet rss = oac.sTOK_MYSQL.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
			        	           while (rss.next())
			        	   	    {
			        	           in1 = rss.getInt("AGID_Y");
			        			   oac.sTOK_MYSQL.urun_degisken_alt_grup_eski(textField_1.getText(), in1, Integer.parseInt(textField_2.getText()));
			        			   rs = oac.sTOK_MYSQL.stk_kod_alt_grup_degisken_oku(in1);
			        	   	    }
			        		   }
			        	   }
			        	   else  if (hangi.equals("depo"))
			        	   {
			        		   if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
								{
			        			   oac.sTOK_MSSQL.urun_degisken_kayit("DPID_Y","DEPO_DEGISKEN", "DEPO", textField_1.getText());
			        			   rs = oac.sTOK_MSSQL.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
								}
			        		   else
			        		   {
			        			   oac.sTOK_MYSQL.urun_degisken_kayit("DPID_Y","DEPO_DEGISKEN", "DEPO", textField_1.getText());
			        			   rs = oac.sTOK_MYSQL.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
			        		   }
			        	   }
			        	   else  if (hangi.equals("oz1"))
			        	   {
			        		   if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
								{
			        			   oac.sTOK_MSSQL.urun_degisken_kayit("OZ1ID_Y","OZ_KOD_1_DEGISKEN", "OZEL_KOD_1", textField_1.getText());
			        			   rs = oac.sTOK_MSSQL.stk_kod_degisken_oku("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN");
								}
			        		   else
			        		   {
			        			   oac.sTOK_MYSQL.urun_degisken_kayit("OZ1ID_Y","OZ_KOD_1_DEGISKEN", "OZEL_KOD_1", textField_1.getText());
			        			   rs = oac.sTOK_MYSQL.stk_kod_degisken_oku("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN");
			        		   }
			        	   }
			        	   else  if (hangi.equals("oz2"))
			        	   {
			        		   if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
								{
			        			   oac.sTOK_MSSQL.urun_degisken_kayit("OZ2ID_Y","OZ_KOD_2_DEGISKEN", "OZEL_KOD_2", textField_1.getText());
			        			   rs =  oac.sTOK_MSSQL.stk_kod_degisken_oku("OZEL_KOD_2", "OZ2ID_Y", "OZ_KOD_2_DEGISKEN");
								}
			        		   else
			        		   {
			        			   oac.sTOK_MYSQL.urun_degisken_kayit("OZ2ID_Y","OZ_KOD_2_DEGISKEN", "OZEL_KOD_2", textField_1.getText());
			        			   rs = oac.sTOK_MYSQL.stk_kod_degisken_oku("OZEL_KOD_2", "OZ2ID_Y", "OZ_KOD_2_DEGISKEN");
			        		   }
			        	   }
			           }
			       	
			           sifirla();
			           textField.setText("");
			           grid_doldur();
			           textField.requestFocus();
		   }
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage(),  "Degiskenler", JOptionPane.ERROR_MESSAGE);        
			}
	}
}

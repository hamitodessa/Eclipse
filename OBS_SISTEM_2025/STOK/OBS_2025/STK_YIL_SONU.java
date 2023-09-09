package OBS_2025;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import OBS_C_2025.CheckBoxRenderer;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import OBS_2025_RAPORLAR.ENVANTER;

import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;

@SuppressWarnings({"serial","static-access","unused"})
public class STK_YIL_SONU extends JInternalFrame {
	
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(oac._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	
	private static final Vector<?> Boolean = null;
	public static JTable table;
	private static JTextField textField;
	private static JDateChooser dtc ;
	private static  String tar = "" ;
	private static JTextField textField_1;
	private static JLabel lblNewLabel_1 ;
	private JLabel lblNewLabel_3 ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					STK_YIL_SONU frame = new STK_YIL_SONU();
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
	@SuppressWarnings("removal")
	public STK_YIL_SONU() {
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,1100, 600);
		setTitle("STOK YIL SONU");
		
		JSplitPane splitPaneana = new JSplitPane();
		splitPaneana.setResizeWeight(1.0);
		splitPaneana.setDividerSize(0);
		splitPaneana.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPaneana, BorderLayout.CENTER);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.0);
		splitPane.setDividerSize(1);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPaneana.setLeftComponent(splitPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 70));
		panel.setMaximumSize(new Dimension(0, 70));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Ana Grup - Alt Grup");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ana_alt();
			}
		});
		btnNewButton.setBounds(10, 8, 142, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Gidecegi Yer");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gidecegi_yer();
			}
		});
		btnNewButton_1.setBounds(10, 36, 142, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Evrak Formatlama");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evr_format();
							}
		});
		btnNewButton_2.setBounds(160, 8, 142, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Envanter");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				envr_aktar();
			}
		});
		btnNewButton_3.setBounds(160, 36, 142, 23);
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Tarih");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(315, 12, 39, 14);
		panel.add(lblNewLabel);
		
		dtc = new JDateChooser();
		dtc.setBounds(355, 8, 121, 20);
		dtc.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dtc.setDate(new Date());
				}
			}
		});
		dtc.getComponent(1).addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            	if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                	SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); 
            		Date date;
    				try {
    					date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc));
    					Calendar cal = Calendar.getInstance();
    	        		cal.setTime(date);
    	        		cal.add(Calendar.DAY_OF_MONTH, -1); 
    	        		dtc.setDate(new Date(cal.getTimeInMillis()));
    				} catch (ParseException e1) {
    					e1.printStackTrace();
    				}
                }
            	else if(e.getKeyCode()==KeyEvent.VK_UP) {
                	SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); 
            		Date date;
    				try {
    					date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc));
    					Calendar cal = Calendar.getInstance();
    	        		cal.setTime(date);
    	        		cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
    	        		dtc.setDate(new Date(cal.getTimeInMillis()));
    				} catch (ParseException e1) {
    					e1.printStackTrace();
    				}
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
	
		dtc.setDateFormatString("dd.MM.yyyy");
		dtc.setFont(new Font("Tahoma", Font.BOLD, 12));
		dtc.setDate(new Date());
		panel.add(dtc);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Aktarilacak  Dosya Kodu", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1.setBounds(486, 8, 161, 51);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(26, 20, 85, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Karsi Hesap Kodu", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_2.setBounds(655, 8, 416, 51);
		panel.add(panel_2);
		
		textField_1 = new JTextField();
		textField_1.setDocument(new JTextFieldLimit(12));
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
					lblNewLabel_1.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
			  }
			  public void removeUpdate(DocumentEvent e) {
					lblNewLabel_1.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
			  }
			  public void insertUpdate(DocumentEvent e) {
					lblNewLabel_1.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
			  }
			});
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						textField_1.setText(oac.hsp_hsp_kodu);
						lblNewLabel_1.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_1.setBounds(10, 20, 110, 20);
		panel_2.add(textField_1);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_1.setColumns(10);
		
		lblNewLabel_1 = new JLabel("...");
		lblNewLabel_1.setForeground(new Color(0, 0, 139));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(130, 23, 276, 14);
		panel_2.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		DefaultTableModel mode = new DefaultTableModel() ;
		
		table = new JTable(mode) {
			@Override
			public boolean isCellEditable(int row, int column) {  
				 switch (column) {
		         case 0:
		             return true;
		         case 2:
		             return true;
		         default:
		             return false;
		      }
				}
		};
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    mode.addColumn("", Boolean);
		    mode.addColumn("Kodu", new String []{""});
		    mode.addColumn("Yeni Kod", new String []{""});
		    mode.addColumn("Adi", new String []{""});
		    mode.addColumn("Simge", new String []{""});
		    mode.addColumn("Stok Miktari",new Double [] {new Double(0.00 )});
		    mode.addColumn("Fiat",new Double [] {new Double( 0.00 )});
		    mode.addColumn("Tutar",new Double [] {new Double( 0.00 )});
		  
		    table.setGridColor(oac.gridcolor);
		    JTableHeader th = table.getTableHeader();
		    TableColumnModel tcm = th.getColumnModel();
	        TableColumn tc;
		
	        tc = tcm.getColumn(0);
			//tc.setHeaderRenderer(new SOLA());
			JCheckBox checkBox = new JCheckBox();
			checkBox.setHorizontalAlignment(JCheckBox.CENTER);
			DefaultCellEditor dce = new DefaultCellEditor( checkBox );
			table.getColumnModel().getColumn(0).setCellEditor(dce);
		    tc.setCellRenderer(new CheckBoxRenderer());
			tc.setMinWidth(50);
			tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
			
		    tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(100);
			
			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(100);
			
			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(350);
			
			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);
			
			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(3,false));
			tc.setMinWidth(120);
			
			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);
			
			tc = tcm.getColumn(7);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);
		   
	
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(false);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		
		 Dimension dd = table.getPreferredSize();
		 dd.height = 30;
		 th.setPreferredSize(dd); 
		 
		 th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(22);
			
		    tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
		    th.repaint();
		    
			table.setSelectionBackground(Color.PINK);
			table.setSelectionForeground(Color.BLUE);
		    scrollPane.setViewportView(table);
		
		GRID_TEMIZLE.grid_temizle(table);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_3.setMinimumSize(new Dimension(0, 25));
		panel_3.setMaximumSize(new Dimension(0, 25));
		
		
		
		splitPaneana.setRightComponent(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Secilen Kayit Sayisi:");
		lblNewLabel_2.setBounds(10, 5, 128, 14);
		panel_3.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("0");
		lblNewLabel_3.setBounds(148, 5, 46, 14);
		panel_3.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
	//***********
				table.getModel().addTableModelListener(	(TableModelListener) new TableModelListener() 
		       			{
						@Override
						public void tableChanged(TableModelEvent e) 
		       			{
		 	   				  TableModel model = (TableModel)e.getSource();
		  			   			if (model.getRowCount() > 0) {
		   			   			int row;
		 		   				row = table.getSelectedRow();     //e.getFirstRow();
		   			   		    int column = e.getColumn();
		   			   		  //      Object data = model.getValueAt(row, column);
		   			   		 secilen_satir();
		  						}
		       						}
		  		   			});
			//****
		doldur();
	}
	public void doldur()
	{
		try
		{
		 GRID_TEMIZLE.grid_temizle(table);
		 DefaultTableModel modell = (DefaultTableModel)table.getModel();
		 DefaultTableModel model1 = (DefaultTableModel)ENVANTER.table.getModel();
		for (int i =0 ;i <= model1.getRowCount() - 1;i++)
		{
			modell.addRow(new Object[]{false, model1.getValueAt(i,0).toString(),model1.getValueAt(i,0).toString(),
					model1.getValueAt(i,1).toString(),model1.getValueAt(i,2).toString(),
					DecimalFormat.getNumberInstance().parse(model1.getValueAt(i,8).toString()).doubleValue(),
					DecimalFormat.getNumberInstance().parse(model1.getValueAt(i,9).toString()).doubleValue(),
					DecimalFormat.getNumberInstance().parse(model1.getValueAt(i,10).toString()).doubleValue()});
		}
		String deger;
		String[] parts;
		Font bigFont;
			deger = GLOBAL.setting_oku("STK_RAPORLAMA").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			table.setFont(bigFont);
		 }
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Yil Sonu Aktarma",JOptionPane.ERROR_MESSAGE);
		}
}
	private void ana_alt()
	{
		try
		{
			getContentPane().setCursor(oac.WAIT_CURSOR);
			if (textField.getText().equals(""))
			{
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				textField.requestFocus();
				return;
			}
			
			f_Access.ana_yaz(textField.getText().toString(), GLOBAL.KULL_ADI);
			f_Access.alt_yaz(textField.getText().toString(), GLOBAL.KULL_ADI);
			
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null,  "Ana Grup Alt Grup Aktarildi.....", "Yil Sonu Aktarma",JOptionPane.PLAIN_MESSAGE);
		 }
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Yil Sonu Aktarma",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void gidecegi_yer()
	{
		try
		{
			getContentPane().setCursor(oac.WAIT_CURSOR);
			if (textField.getText().equals(""))
			{
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				textField.requestFocus();
				return;
			}
			
			f_Access.gidecegi_yer(textField.getText().toString(), GLOBAL.KULL_ADI);
		
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null,  "Gidecegi Yer Aktarildi.....", "Yil Sonu Aktarma",JOptionPane.PLAIN_MESSAGE);
		 }
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Yil Sonu Aktarma",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void evr_format()
	{
		try
		{
			getContentPane().setCursor(oac.WAIT_CURSOR);
			if (textField.getText().equals(""))
			{
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				textField.requestFocus();
				return;
			}
			
			f_Access.evr_for(textField.getText().toString(), GLOBAL.KULL_ADI);
			
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null,  "Gidecegi Yer Aktarildi.....", "Yil Sonu Aktarma",JOptionPane.PLAIN_MESSAGE);
		 }
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Yil Sonu Aktarma",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void envr_aktar()
	{
		try
		{
			getContentPane().setCursor(oac.WAIT_CURSOR);
			if (textField.getText().equals(""))
			{
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				textField.requestFocus();
				return;
			}
			if (satir_kontrol() == 0)
			{
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				JOptionPane.showMessageDialog(null,  "Secili Satir Bulunamadi....", "Yil Sonu Aktarma",JOptionPane.ERROR_MESSAGE);
				return;
			}
			int g =  JOptionPane.showOptionDialog( null,  "Aktarma Islemine Baslanacak .....Satir Sayisi:" + satir_kontrol()  ,
					"Yil Sonu Aktarma",   JOptionPane.YES_NO_OPTION, 	JOptionPane.QUESTION_MESSAGE, 	null,     //no custom icon
		   			 	oac.options,  //button titles
		   			 	oac.options[1]); //default button
		 	 if(g != 0 ) {getContentPane().setCursor(oac.DEFAULT_CURSOR);  return;	}
		 	 
		 	long startTime = System.currentTimeMillis(); 
		 	DefaultTableModel modell = (DefaultTableModel)table.getModel();
			
			     for ( int i = 0; i <=  modell.getRowCount() - 1;i++)
			     {
			            if ( (boolean) modell.getValueAt(i,0) )
			            {
			            	f_Access.mal_yaz(textField.getText().toString(), GLOBAL.KULL_ADI, modell.getValueAt(i,1).toString(),modell.getValueAt(i,2).toString());
			            }
			     }
			
			//**************SATIRLAI KAYIT YAP************
			tar = TARIH_CEVIR.tarih_geri_saatli(dtc) ;
	        satir_yaz_1();
            stok_isle();
           //********************************************
			//
            long endTime = System.currentTimeMillis();
    		long estimatedTime = endTime - startTime;
    		double seconds = (double)estimatedTime/1000; 
    		OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null,  "Envanter  Aktarildi.....", "Yil Sonu Aktarma",JOptionPane.PLAIN_MESSAGE);
		 }
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Yil Sonu Aktarma",JOptionPane.ERROR_MESSAGE);
		}
	}
	private void satir_yaz_1()
	{
		 DefaultTableModel mdl = (DefaultTableModel) table.getModel();
	       for (int  i = 0 ; i <=  mdl.getRowCount() - 1 ; i++)
	       {
	          //  Progres_Bar(RG1.Rows.Count - 1, i)
	    	   if ( (boolean) mdl.getValueAt(i,0) )
	            {
	                sat_yaz_2(i);
	    	   }
	       }
	}
	private static void sat_yaz_2(int i)
	{
		try {
		String gircik, izahat ;
		double  miktar, kur ;
	    int angrp, altgrp, depo;
	    depo = 0 ;
   		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
	    depo = 0 ;
	    gircik = "";
	    miktar = Double.parseDouble( mdl.getValueAt(i,5).toString());
	    gircik = "G";
        double tutar ;
        tutar =Double.parseDouble(mdl.getValueAt(i,7).toString());
        izahat = "Devir......" ;
        kur = 0;
        angrp = 0 ;
        altgrp = 0;
        double tevk = 0  ;
        double fiat =0 ;
         fiat = Double.parseDouble( mdl.getValueAt(i,6).toString());
        double isk = 0 ;
        double kdv = 0 ; 
	      
	      f_Access.ysonu_fat_kaydet("0000000001",  mdl.getValueAt(i,2).toString(), depo,fiat , tevk,
	    	            miktar, gircik, tutar ,isk,kdv,
	    	            tar, izahat, GLOBAL.setting_oku("PRG_PARA").toString(), "",textField_1.getText(), 
	    	            "", kur, "", angrp, altgrp, GLOBAL.KULL_ADI,textField.getText());
 			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Satyz2", JOptionPane.ERROR_MESSAGE);     
		}
	}
	private static void stok_isle() 
	{
		 DefaultTableModel mdl = (DefaultTableModel) table.getModel();
	       for (int  i = 0 ; i <=  mdl.getRowCount() - 1 ; i++)
	       {
	    	   if ( (boolean) mdl.getValueAt(i,0) )
	            {
	           stk_yaz_2(i);
	    	   }
	       }
	}
	private static void stk_yaz_2(int sat)
	{
		try {
	        double miktar ,tutar,isk,kdvlitut,kur ;
	        String  har, izah ;
	        int anagrp, altgrp, depo ;
	        depo = 0 ;
	   		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
	   	    depo = 0 ;
	   	    har = "G" ;
	        izah = "0000000001 Nolu Giris (DEVIR)...";
            miktar =  Double.parseDouble( mdl.getValueAt(sat,5).toString());
            tutar =  Double.parseDouble( mdl.getValueAt(sat,7).toString());
            isk =  0;
            kdvlitut = Double.parseDouble( mdl.getValueAt(sat,7).toString());          
	        anagrp = 0 ;
	        altgrp = 0;
	        kur = 0;
	        double fiat =0 ;
	         fiat = Double.parseDouble( mdl.getValueAt(sat,5).toString());
	        
	        f_Access.ysonu_stk_kaydet("0000000001", "FAT", tar, depo,  mdl.getValueAt(sat,1).toString(), miktar, fiat
                        ,(double) Math.round(tutar), kdvlitut, har, izah, anagrp, altgrp, kur, ""
                        , GLOBAL.setting_oku("PRG_PARA").toString(), textField_1.getText(),GLOBAL.KULL_ADI,textField.getText());
 			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Devir Stkyz2", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private int satir_kontrol()
	{
		 int satir = 0 ;
		 DefaultTableModel modell = (DefaultTableModel)table.getModel();
	     for ( int i = 0; i <=  modell.getRowCount() - 1;i++)
	     {
	            if ( (boolean) modell.getValueAt(i,0) )
	            {
	                satir += 1 ;
	            }
	     }
	        return satir ;
	}
	private void secilen_satir()
	{
		lblNewLabel_3.setText(FORMATLAMA.doub_0(satir_kontrol()));
	}
	
	class MyItemListener implements ItemListener
	{
		@SuppressWarnings("removal")
		@Override
		public void itemStateChanged(ItemEvent e) {
			Object source = e.getSource();
			if (source instanceof AbstractButton == false) return;
			boolean checked = e.getStateChange() == ItemEvent.SELECTED;
			for(int x = 0, y =  table.getRowCount(); x < y; x++)
			{
				table.setValueAt(new Boolean(checked),x,0);
			}
		}
	}
	class CheckBoxHeader extends JCheckBox   implements TableCellRenderer, MouseListener {
		protected CheckBoxHeader rendererComponent;
		protected int column;
		protected boolean mousePressed = false;
		public CheckBoxHeader(ItemListener itemListener) {
			rendererComponent = this;
			rendererComponent.addItemListener(itemListener);
		}
		public Component getTableCellRendererComponent(
				JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			if (table != null) {
				JTableHeader header = table.getTableHeader();
				if (header != null) {
					rendererComponent.setForeground(header.getForeground());
					rendererComponent.setBackground(header.getBackground());
					rendererComponent.setFont(header.getFont());
					header.addMouseListener(rendererComponent);
				}
			}
			setColumn(column);

			setHorizontalAlignment(JLabel.CENTER);

			setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			//setSelected(true);
			return rendererComponent;
		}
		protected void setColumn(int column) {
			this.column = column;
		}
		public int getColumn() {
			return column;
		}
		protected void handleClickEvent(MouseEvent e) {
			if (mousePressed) {
				mousePressed=false;
				JTableHeader header = (JTableHeader)(e.getSource());
				JTable tableView = header.getTable();
				TableColumnModel columnModel = tableView.getColumnModel();
				int viewColumn = columnModel.getColumnIndexAtX(e.getX());
				int column = tableView.convertColumnIndexToModel(viewColumn);

				if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {
					doClick();
				}
			}
		}
		public void mouseClicked(MouseEvent e) {
			handleClickEvent(e);
			((JTableHeader)e.getSource()).repaint();
		}
		public void mousePressed(MouseEvent e) {
			mousePressed = true;
		}
		public void mouseReleased(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
	}
}

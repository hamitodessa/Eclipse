package OBS_PACKAGE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.commons.lang.StringUtils;

import com.toedter.calendar.JDateChooser;

public class CEK_CIKIS extends JInternalFrame {

	private static JTable table;
	public static JTextField textField;
	public static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	public static JTextField textField_4;
	private static JTextField textField_5;
	private static JTextField textField_6;
	public static JLabel lblNewLabel_2 ;
	public static JDateChooser dateChooser ;
	private static JComboBox<String> comboBox  ;
	public static JLabel label;
	public static JLabel lblNewLabel_12;
	private JLabel lblNewLabel_7;
	
	Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CEK_CIKIS frame = new CEK_CIKIS();
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
	public CEK_CIKIS() {
		
		setTitle("CEK CIKIS");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 1250, 780);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		//splitPane.setDividerLocation(70);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setOneTouchExpandable(true);
		splitPane_1.setResizeWeight(1.0);
		
		splitPane_1.setDividerSize(1);
		//splitPane_1.setDividerLocation(30);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane_1.setLeftComponent(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel() ; 
		
		table = new JTable(model){public boolean isCellEditable(int row,int column)
        {switch(column){
        case 0:
        return true;
        default: return false;}
        }};

		
    	table.setGridColor(oac.gridcolor);
		model.addColumn("Cek No", new String []{""});
		model.addColumn("Vade", new Date []{ new Date() });
	    model.addColumn("Banka", new String []{""});
	    model.addColumn("Sube", new String []{""});
	    model.addColumn("Seri No", new String []{""});
	    model.addColumn("Ilk Borclu", new String []{""});
	    model.addColumn("Hesap", new String []{"" });
	    model.addColumn("Cins", new String []{"" });
	    model.addColumn("Tutar",new Double [] {new Double( 0 )});
	    model.addColumn("Cikis_Bordro", new String []{"" });
	    model.addColumn("Cikis_Tarihi", new Date []{ new Date() });
	    model.addColumn("Cikis_Musteri", new String []{"" });
	    model.addColumn("Durum", new String []{"" });
	    model.addColumn("T_Tarih", new Date []{ new Date() });
	    model.addColumn("Cikis_Ozel_Kod", new String []{"" });
	   
	   
	    TableColumn col ;
	  
	    col = table.getColumnModel().getColumn(0);
	    col.setCellEditor(new KAM_CEKNO(new JTextField(),"CC"));
	    col.setCellRenderer(new KAM_CEKNO_REN());
		col.setMinWidth(100);
	    col.setHeaderRenderer(new SOLA());
	      
	    col = table.getColumnModel().getColumn(1);
	   
		col.setHeaderRenderer(new SOLA());
		col.setCellRenderer(new COKLU_GIRIS_TARIH());
		col.setMinWidth(100);
	      
	    col = table.getColumnModel().getColumn(2);
	    col.setMinWidth(250);
	    col.setHeaderRenderer(new SOLA());
	      
	    col = table.getColumnModel().getColumn(3);
	    col.setMinWidth(150);
	    col.setHeaderRenderer(new SOLA());
	      
	    col = table.getColumnModel().getColumn(4);
	    col.setMinWidth(150);
	    col.setHeaderRenderer(new SOLA());
	      
	    col = table.getColumnModel().getColumn(5);
	    col.setMinWidth(150);
	    col.setHeaderRenderer(new SOLA());
	      
	    col = table.getColumnModel().getColumn(6);
	    col.setMinWidth(150);
	    col.setHeaderRenderer(new SOLA());
	      
	    col = table.getColumnModel().getColumn(7);
	    col.setMinWidth(50);
	    col.setMaxWidth(50);
	    col.setHeaderRenderer(new SOLA());
	      
	    col = table.getColumnModel().getColumn(8);
	    col.setHeaderRenderer(new SAGA());
	    col.setCellRenderer(new TABLO_RENDERER(2,true));
		col.setMinWidth(115);
		
		for (int i=0 ; i< 6;i++)
		{
			table.removeColumn(table.getColumnModel().getColumn(9));
		}
		 JTableHeader th = table.getTableHeader();
		    Dimension dd = table.getPreferredSize();
		    dd.height = 30;
		    th.setPreferredSize(dd); 
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);
			table.getModel().addTableModelListener(new TableModelListener() {
	            public void tableChanged(TableModelEvent e) {
	               // System.out.println("Column: " + e.getColumn() + " Row: " + e.getFirstRow());
	            	topla();
	            }
	        });
			table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			th.repaint();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		splitPane_1.setRightComponent(panel);
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 30));
		panel.setMaximumSize(new Dimension(0, 30));
		panel.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Evrak Sayisi :");
		lblNewLabel_6.setBounds(10, 11, 83, 14);
		panel.add(lblNewLabel_6);
		
		lblNewLabel_12 = new JLabel("0");
		lblNewLabel_12.setForeground(new Color(0, 0, 139));
		lblNewLabel_12.setBounds(103, 11, 58, 14);
		panel.add(lblNewLabel_12);
		
		label = new JLabel("0.00");
		label.setForeground(new Color(139, 0, 0));
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(1073, 11, 136, 14);
		panel.add(label);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.setMinimumSize(new Dimension(0, 112));
		tabbedPane.setMaximumSize(new Dimension(0, 112));
		splitPane.setLeftComponent(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane.addTab("Cek Giris", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bordro No");
		lblNewLabel.setBounds(10, 11, 67, 14);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hesap");
		lblNewLabel_1.setBounds(10, 38, 46, 14);
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					try {
					int sno = 0 ;
					if (CONNECTION.kamdizinbilgi.han_sql.equals("MS SQL"))
		    		{
		             sno  = oac.kAMBIYO_MSSQL.kam_bordro_no_al("CEK_C") ;
		    		}
		            else
		            {
		             sno  = oac.kAMBIYO_MYSQL.kam_bordro_no_al("CEK_C") ;
		            }
					int kj = 0 ;
					kj = 10 - Integer.toString(sno).length() ;
			        String str_ = StringUtils.repeat("0", kj)   + Integer.toString(sno);
					//String str_ = ("0".repeat(kj)) + Integer.toString(sno);
					textField.setText(str_);
					}
					catch (Exception ex)
					{
						 JOptionPane.showMessageDialog(null,  ex.getMessage()); 	
					}
				}
			}
		});
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.setBounds(87, 8, 108, 20);
		textField.setDocument(new JTextFieldLimit(10));
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    	getContentPane().setCursor(WAIT_CURSOR);
					kontrol();
					getContentPane().setCursor(DEFAULT_CURSOR);
			  }
			  public void removeUpdate(DocumentEvent e) {
			    	getContentPane().setCursor(WAIT_CURSOR);
					kontrol();
					getContentPane().setCursor(DEFAULT_CURSOR);
			  }
			  public void insertUpdate(DocumentEvent e) {
			    	getContentPane().setCursor(WAIT_CURSOR);
					kontrol();
					getContentPane().setCursor(DEFAULT_CURSOR);
		  }
			});
		panel_2.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		
		textField_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.show();
						textField_1.setText( GLOBAL.hsp_hsp_kodu);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
        	}
        });
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_1.setBounds(87, 35, 108, 20);
		textField_1.setDocument(new JTextFieldLimit(12));
        textField_1.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    	getContentPane().setCursor(WAIT_CURSOR);
			    	lblNewLabel_2.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
					getContentPane().setCursor(DEFAULT_CURSOR);
			  }
			  public void removeUpdate(DocumentEvent e) {
			    	getContentPane().setCursor(WAIT_CURSOR);
			    	lblNewLabel_2.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
					getContentPane().setCursor(DEFAULT_CURSOR);
			  }
			  public void insertUpdate(DocumentEvent e) {
			    	getContentPane().setCursor(WAIT_CURSOR);
			    	lblNewLabel_2.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
					getContentPane().setCursor(DEFAULT_CURSOR);
		  }
			});
        
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel_2 = new JLabel(".....");
		lblNewLabel_2.setForeground(new Color(139, 0, 0));
		lblNewLabel_2.setBounds(87, 60, 273, 14);
		panel_2.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
	            int int_1  = 0;
	            String str_2 ="";
	            String sts ="" ;
	            //**************** EVRAK NO OKU ************
	            getContentPane().setCursor(WAIT_CURSOR);
	            if (CONNECTION.kamdizinbilgi.han_sql.equals("MS SQL"))
	    		{
	            
	             sts  = oac.kAMBIYO_MSSQL.kam_son_bordro_no_al("CEK","Cikis_Bordro") ;
	    		}
	            else
	            {
	             sts  = oac.kAMBIYO_MYSQL.kam_son_bordro_no_al("CEK","Cikis_Bordro") ;
	            }
	           
	            if ( sts.equals(""))
	            	{
	            	getContentPane().setCursor(DEFAULT_CURSOR);
	    			JOptionPane.showMessageDialog(null,   "Dosyada Hic Kayit Yok....."); 	
	            	return ;
	            	}
	            //**********SIFIRLAR*************************
	            int_1 = 10 - sts.toString().length() ;
	            String str_ = StringUtils.repeat("0", int_1)   + sts.toString();
	            //str_2= ("0".repeat(int_1)) + sts.toString() ;
	            textField .setText(str_) ;
	            textField .requestFocus();
	            int_1 = 0;
	            //********************************************
	           
	            getContentPane().setCursor(DEFAULT_CURSOR);
			}
			catch (Exception ex)
			{
				 getContentPane().setCursor(DEFAULT_CURSOR);
				 JOptionPane.showMessageDialog(null,  ex.getMessage()); 	
			 }
			}
			
		});
		btnNewButton.setIcon(new ImageIcon(CEK_GIRIS.class.getResource("/ICONLAR/icons8-view-16.png")));
		btnNewButton.setBounds(204, 7, 25, 22);
		panel_2.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Tarih");
		lblNewLabel_3.setBounds(261, 11, 46, 14);
		panel_2.add(lblNewLabel_3);
		
		dateChooser = new JDateChooser();
		dateChooser.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dateChooser.setDate(new Date());
				}
			}
		});
		dateChooser.setBounds(312, 8, 129, 22);
		dateChooser.setDateFormatString("dd.MM.yyyy");
        dateChooser.setFont(new Font("Tahoma", Font.BOLD, 14));
        dateChooser.setDate(new Date());
        
        dateChooser.getComponent(1).addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            	if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                	SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
            		Date date;
    				try {
    					date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser));
    					Calendar cal = Calendar.getInstance();
    	        		cal.setTime(date);
    	        		cal.add(Calendar.DAY_OF_MONTH, -1); 
    	        		dateChooser.setDate(new Date(cal.getTimeInMillis()));
    				} catch (ParseException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
                }
            	else if(e.getKeyCode()==KeyEvent.VK_UP) {
                	SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
            		Date date;
    				try {
    					date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser));
    					Calendar cal = Calendar.getInstance();
    	        		cal.setTime(date);
    	        		cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
    	        			        		
    	        		dateChooser.setDate(new Date(cal.getTimeInMillis()));
    				} catch (ParseException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
		panel_2.add(dateChooser);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Vade Farki", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_3.setBounds(451, 5, 352, 53);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("%");
		lblNewLabel_4.setBounds(10, 27, 25, 14);
		panel_3.add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_2.setBounds(39, 24, 44, 20);
		textField_2.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    	getContentPane().setCursor(WAIT_CURSOR);
					ort();
					getContentPane().setCursor(DEFAULT_CURSOR);
			  }
			  public void removeUpdate(DocumentEvent e) {
			    	getContentPane().setCursor(WAIT_CURSOR);
					ort();
					getContentPane().setCursor(DEFAULT_CURSOR);
			  }
			  public void insertUpdate(DocumentEvent e) {
			    	getContentPane().setCursor(WAIT_CURSOR);
					ort();
					getContentPane().setCursor(DEFAULT_CURSOR);
		  }
			});
		
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Ort.Gun");
		lblNewLabel_5.setBounds(91, 27, 54, 14);
		panel_3.add(lblNewLabel_5);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_3.setBounds(141, 24, 66, 20);
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		lblNewLabel_7 = new JLabel("0.00");
		lblNewLabel_7.setForeground(Color.RED);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_7.setBounds(228, 26, 101, 14);
		panel_3.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Ozel Kod");
		lblNewLabel_8.setBounds(826, 12, 67, 14);
		panel_2.add(lblNewLabel_8);
		
		comboBox = new JComboBox<String>();
		comboBox.setForeground(new Color(0, 0, 128));
		comboBox.setEditable(true);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox.setBounds(918, 8, 129, 22);
		panel_2.add(comboBox);
		
		JLabel lblNewLabel_9 = new JLabel("Doviz Turu");
		lblNewLabel_9.setBounds(826, 39, 82, 14);
		panel_2.add(lblNewLabel_9);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_4.setBounds(918, 35, 62, 20);
		textField_4.setDocument(new JTextFieldLimit(3));
		panel_2.add(textField_4);
		textField_4.setColumns(10);
		
				
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane.addTab("Aciklama", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("Aciklama 1");
		lblNewLabel_10.setBounds(10, 11, 72, 14);
		panel_1.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Aciklama 2");
		lblNewLabel_11.setBounds(10, 36, 72, 14);
		panel_1.add(lblNewLabel_11);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_5.setBounds(92, 8, 563, 20);
		textField_5.setDocument(new JTextFieldLimit(50));
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_6.setBounds(92, 33, 563, 20);
		textField_6.setDocument(new JTextFieldLimit(50));
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		GRID_TEMIZLE.grid_temizle(table);
		
		//***********
		String deger;
		Integer sat_sayi;
		
			try {
				textField_4.setText(GLOBAL.setting_oku("PRG_PARA").toString());
				deger = GLOBAL.setting_oku("KAM_CEK_CIK").toString();
				sat_sayi =Integer.parseInt(deger);
				for (int i = 0; i <= sat_sayi; i ++)
				{
					satir_ilave();
				}
				textField.requestFocus();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//***********
	}
	
	private void kontrol()
	{
		  GRID_TEMIZLE.grid_temizle(table);
		 if (textField.getText().toString().length() == 0)
		 {
			textField_1.setText("");
			dateChooser.setDate(new Date());
			textField_2.setText("");
        	textField_3.setText("");
        	comboBox.removeAllItems();
        	comboBox.addItem("");
        	 topla();
      		String deger;
      		Integer sat_sayi;
      			try {
      				textField_4.setText(GLOBAL.setting_oku("PRG_PARA").toString());
      				deger = GLOBAL.setting_oku("KAM_CEK_CIK").toString();
      				sat_sayi =Integer.parseInt(deger);
      				
      				for (int i = 0; i <= sat_sayi; i ++)
      				{
      					satir_ilave();
      				}
      				textField.requestFocus();
      			} catch (IOException e1) {
      				e1.printStackTrace();
      			}
		    return;
		 }
		try 
		{
			getContentPane().setCursor(WAIT_CURSOR);
			ResultSet rs = null ;
			if (CONNECTION.kamdizinbilgi.han_sql.equals("MS SQL"))
    	    {
    	    	rs= oac.kAMBIYO_MSSQL.kam_bordno("CEK",textField.getText(),"Cikis_Bordro");
    	    }
    	    else
    	    {
    	    	rs= oac.kAMBIYO_MYSQL.kam_bordno("CEK",textField.getText(),"Cikis_Bordro");
    	    }
			int kayit_sayi = 0 ;
       		 if (!rs.isBeforeFirst() ) {  
       			 textField_1.setText("");
                 dateChooser.setDate(new Date());
                 textField_2.setText("");
                 textField_3.setText("");
                 comboBox.removeAllItems();
                 comboBox.addItem("");
                 topla();
   			} 
       		 else
      		{
             DefaultTableModel mdll = (DefaultTableModel) table.getModel();
             int ozk = 0 ;
   			 while (rs.next()) 
   			 {
   				textField_1.setText(rs.getString("Cikis_Musteri"));
   				dateChooser.setDate(rs.getDate("Cikis_Tarihi"));
   				if (ozk == 0) comboBox.addItem(rs.getString("Cikis_Ozel_Kod"));
   				ozk=1 ;
   				String sDate1= rs.getDate("Vade").toString(); 
   				Date vade= new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);  
   				sDate1= rs.getDate("Cikis_Tarihi").toString(); 
   				Date ct= new SimpleDateFormat("yyyy-MM-dd").parse(sDate1); 
   				sDate1= rs.getDate("T_Tarih").toString(); 
   				Date tt= new SimpleDateFormat("yyyy-MM-dd").parse(sDate1); 
              
                mdll.addRow(new Object[]{rs.getString("Cek_No"), vade,
                        		 rs.getString("Banka"),rs.getString("Sube"),rs.getString("Seri_No"),
                        		 rs.getString("Ilk_Borclu"),rs.getString("Cek_Hesap_No"),rs.getString("Cins"),
                        		 rs.getDouble("Tutar"),
                        		 rs.getString("Cikis_Bordro"),
                        		 ct,
                        		 rs.getString("Cikis_Musteri"),rs.getString("Durum"),
                        		 tt,rs.getString("Cikis_Ozel_Kod")});
                kayit_sayi += 1 ;
    		 }
      			
           // RG1.CurrentRow = RG1.Rows(ELIF.KA_DTS.Tables(0).Rows.Count)
           //'************ACIKLAMA OKU ***********************************************************
   			 if (CONNECTION.kamdizinbilgi.han_sql.equals("MS SQL"))
   	    	    {
   				 textField_5.setText(oac.kAMBIYO_MSSQL.kam_aciklama_oku("CEK","1",textField.getText(),"C"));
   				 textField_6.setText(oac.kAMBIYO_MSSQL.kam_aciklama_oku("CEK","2",textField.getText(),"C"));
   	    	    }
   	    	    else
   	    	    {
   	    	    textField_5.setText(oac.kAMBIYO_MYSQL.kam_aciklama_oku("CEK","1",textField.getText(),"C"));
   				 textField_6.setText(oac.kAMBIYO_MYSQL.kam_aciklama_oku("CEK","2",textField.getText(),"C"));
   	    	    }
      		 }
            topla();
       		getContentPane().setCursor(WAIT_CURSOR);
       		satir_tamamla(Integer.parseInt(GLOBAL.setting_oku("KAM_CEK_CIK").toString()) - kayit_sayi );
		}
     catch (Exception ex)
		{
    	 JOptionPane.showMessageDialog(null,  ex.getMessage()); 
    	 }
	}
	public static void kaydet() 
	{
		if (textField.getText().equals("")) return ;
        long startTime = System.currentTimeMillis(); 
		DefaultTableModel mdll = (DefaultTableModel) table.getModel();
		if (mdll.getRowCount() == 0 ) return ;	
	        try
	        {
	        	if (CONNECTION.kamdizinbilgi.han_sql.equals("MS SQL"))
	    	    {
    	    	 oac.kAMBIYO_MSSQL.bordro_cikis_sil(textField.getText(),"CEK");
	    	    }
	    	    else
	    	    {
    	    	 oac.kAMBIYO_MYSQL.bordro_cikis_sil(textField.getText(),"CEK");
	    	    }
	            for (int i = 0 ; i <= mdll.getRowCount() - 1 ; i ++)
	            {
	            	if (! mdll.getValueAt(i,0).toString().equals(""))
	            	{
	            		if (CONNECTION.kamdizinbilgi.han_sql.equals("MS SQL"))
			    	    {
			    	   	 oac.kAMBIYO_MSSQL.bordro_cikis_yaz("CEK","Cek_No",mdll.getValueAt(i,0).toString(),
			    	     textField_1.getText(),textField.getText(),TARIH_CEVIR.tarih_geri(dateChooser),
			    	     comboBox.getSelectedItem().toString()	 );
			    	    }
			    	    else
			    	    {
		    	    	 oac.kAMBIYO_MYSQL.bordro_cikis_yaz("CEK","Cek_No",mdll.getValueAt(i,0).toString(),
    	    			 textField_1.getText(),textField.getText(),TARIH_CEVIR.tarih_geri(dateChooser),
    	    			 comboBox.getSelectedItem().toString()	 );
			    	    }
	            	}
	            }
	            if (CONNECTION.kamdizinbilgi.han_sql.equals("MS SQL"))
	    	    {
	    			oac.kAMBIYO_MSSQL.kam_aciklama_sil("CEK", textField.getText(), "C");
	    			oac.kAMBIYO_MSSQL.kam_aciklama_yaz("CEK", 1, textField.getText(), textField_5.getText(), "C");
	    			oac.kAMBIYO_MSSQL.kam_aciklama_yaz("CEK", 2, textField.getText(), textField_6.getText(), "C");
	    	    }
	    		else
	    		{
	    			oac.kAMBIYO_MYSQL.kam_aciklama_sil("CEK", textField.getText(), "C");
	    			oac.kAMBIYO_MYSQL.kam_aciklama_yaz("CEK", 1, textField.getText(), textField_5.getText(), "C");
	    			oac.kAMBIYO_MYSQL.kam_aciklama_yaz("CEK", 2, textField.getText(), textField_6.getText(), "C");	
	    		}
	            textField.setText("");
	    		 long endTime = System.currentTimeMillis();
	    		 long estimatedTime = endTime - startTime; 
	    		 double seconds = (double)estimatedTime/1000; 
	    		 OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
	        }
	        catch (Exception ex)
	        {
	       	 JOptionPane.showMessageDialog(null,  ex.getMessage()); 	
	        }
	}
	public static void cari_kaydet()
	{
		if (textField_1.getText().toString().equals("")) return;
		if (textField.getText().toString().equals("")) return;
       
		Runnable runner = new Runnable()
	    { public void run() {
		 try 
		 {
			 long startTime = System.currentTimeMillis(); 
		 Progres_Bar_Temizle();
        //'***************** hsp cinsleri ogren***********************BORCLU HESAP
        String alh = "" ;
        alh = JOptionPane.showInputDialog(null,"Alacakli Hesabi Giriniz....", "Alacakli Hesap",JOptionPane.QUESTION_MESSAGE);
        if(alh == null || (alh != null && ("".equals(alh))))   
        {
            return;
        }
        ResultSet rs ;
        //*******************************************************************************
        if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
	    {
	    	rs = oac.cARI_HESAP_MSSQL.hesap_adi_oku(alh);
	    }
	    else
	    {
	    	rs = oac.cARI_HESAP_MYSQL.hesap_adi_oku(alh);
	    }
		if (!rs.isBeforeFirst() ) {  
			 JOptionPane.showMessageDialog(null,  "Bu numarada hesaba rastlanmadi!!!!"); 
			 return ;
		} 
       //********************************************************************************
		rs= null;
		if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
	    {
	    	rs = oac.cARI_HESAP_MSSQL.hesap_adi_oku(textField_1.getText());
	    }
	    else
	    {
	    	rs = oac.cARI_HESAP_MYSQL.hesap_adi_oku(textField_1.getText());
	    }
		
		if (!rs.isBeforeFirst() ) {  
			 JOptionPane.showMessageDialog(null,  "Bu numarada hesaba rastlanmadi!!!!"); 
			 return;
		} 
		
		if (alh.equals(textField_1.getText().toString()))
        {
            JOptionPane.showMessageDialog(null,  "Borclu ve Alacakli Hesap Ayni..."); 
			 return;
        }
       //********************************************************************************
        String  str_4  = "" ;
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        OBS_MAIN.progressBar.setMaximum(model.getRowCount() - 1);
            for (int i = 0; i <= model.getRowCount() - 1 ; i ++)
            {
            	Progres_Bar(model.getRowCount() - 1, i);
            	if (!  model.getValueAt(i , 0).toString().equals("")) 
                {
            		int eno =0;
            		if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
            		{
            			eno = oac.cARI_HESAP_MSSQL.cari_fisno_al();
            		}
            		else
            		{
            			eno = oac.cARI_HESAP_MYSQL.cari_fisno_al();
            		}
            		 String vade = "";
          	        if (model.getValueAt(i , 1).toString().length() >  10)
          	      	{
          	        	 vade = dateFormater(model.getValueAt(i , 1).toString() , "dd.MM.yyyy HH:mm:ss.sss", "EEE MMM dd kk:mm:ss zzzz yyyy" ) ;
          	      	}
          	      	else
          	      	{
          	      	  String qwe =model.getValueAt(i , 1).toString()  ;
          	      	 vade  = qwe;
          	      	 }
            		
                    str_4 = textField.getText()+  "'Bordro ile " + model.getValueAt(i , 0).toString() + " Nolu " + vade + " Vadeli ???ek" ;
                    if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
        	        {
        	        	oac.cARI_HESAP_MSSQL.cari_dekont_kaydet(textField_1.getText(),
        	        			TARIH_CEVIR.tarih_geri(dateChooser),
        	        		eno,
        	        		"",1.00,
        	        		 Double.parseDouble( model.getValueAt(i, 8).toString()),
        	        		 alh,
        	        		"",1.00,
        	        		Double.parseDouble( model.getValueAt(i, 8).toString()),
        	        		str_4,"" , GLOBAL.KULL_ADI);
        	        }
          	        else
        	        {
        	        	oac.cARI_HESAP_MYSQL.cari_dekont_kaydet(textField_1.getText(),
        	        			TARIH_CEVIR.tarih_geri(dateChooser),
        	        		eno,
        	        		"",1.00,
        	        		 Double.parseDouble( model.getValueAt(i, 8).toString()),
        	        		 alh,
        	        		"",1.00,
        	        		Double.parseDouble( model.getValueAt(i, 8).toString()),
        	        		str_4,"" , GLOBAL.KULL_ADI);
        	        }
                }
            }
         Thread.currentThread().isInterrupted();
         Progres_Bar_Temizle();
   		 long endTime = System.currentTimeMillis();
   		 long estimatedTime = endTime - startTime; 
   		 double seconds = (double)estimatedTime/1000; 
   		 OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
          JOptionPane.showMessageDialog(null, "Cikislar Cari Hesaba Basari ile Kaydedilmistir....");   
        }
        catch (Exception ex)
        {
       	 JOptionPane.showMessageDialog(null, ex.getMessage());       
        }
    }
    };
	    //// Progress Bar
	    Thread t = new Thread(runner, "Code Executer");
	    t.start();
	}
	public static void sil()
	{
	 if ( textField.getText().equals("")) return ;
	 if (table.getRowCount() == 0) return ;
	 int g = JOptionPane.showOptionDialog( null,  "Islem Dosyadan Silinecek ..?", "Cek Dosyasindan Evrak Silme",   JOptionPane.YES_NO_OPTION,
			    	   		JOptionPane.QUESTION_MESSAGE,null, oac.options, oac.options[1]); 
			    	        if(g != 0 ) { return;	}	
     try 
     {
         long startTime = System.currentTimeMillis(); 
       	if (CONNECTION.kamdizinbilgi.han_sql.equals("MS SQL"))
   	    {
  	    	 oac.kAMBIYO_MSSQL.bordro_cikis_sil(textField.getText(),"CEK");
   	    	 oac.kAMBIYO_MSSQL.kam_aciklama_sil("CEK", textField.getText(), "C");
   	    }
   	    else
   	    {
  	    	 oac.kAMBIYO_MYSQL.bordro_cikis_sil(textField.getText(),"CEK");
   	    	 oac.kAMBIYO_MYSQL.kam_aciklama_sil("CEK", textField.getText(), "C");
   	    }
      	textField.setText("");
  		 long endTime = System.currentTimeMillis();
  		 long estimatedTime = endTime - startTime; 
  		 double seconds = (double)estimatedTime/1000; 
  		 OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

	  }
      catch (Exception ex)
      {
       	 JOptionPane.showMessageDialog(null,  ex.getMessage()); 
      }
	}
	public static boolean cek_kontrol (String cekno,String tur, int satir)
	{
		boolean result = false ;
		try
		{
		ResultSet rs = null ;
		if (CONNECTION.kamdizinbilgi.han_sql.equals("MS SQL"))
	    {
	    	rs= oac.kAMBIYO_MSSQL.cek_kontrol(cekno);
	    }
	    else
	    {
	    	rs= oac.kAMBIYO_MYSQL.cek_kontrol(cekno);
	    }
		 if (!rs.isBeforeFirst() ) {  
			 result = false;
			} 
   		 else
   		 {
   			rs.next();
   			String bno = rs.getString("Cikis_Bordro").toString() ;
   			if ( ! bno.equals("")   && textField.getText().toString().equals(bno))
   			{
   				JOptionPane.showMessageDialog(null, "Bu Cek bu Bordroda Cikis Yapilmis.."); 
   				result = true;
   			}
   			else if ( ! bno.equals("")   &&  ! textField.getText().toString().equals(bno))
   			{
   			JOptionPane.showMessageDialog(null, "Bu Cek " + bno + " 'nolu Bordroda Cikis Yapilmis.."); 
   			 result = true ;
   			}
   			else
   			{  // DOLDUR
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			String sDate1 = rs.getDate("Vade").toString(); 
			Date vade= new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);  
   				
   			 model.setValueAt(vade,satir, 1);
   			 model.setValueAt(rs.getString("Banka"),satir, 2);
   			 model.setValueAt(rs.getString("Sube"),satir, 3);
   			 model.setValueAt(rs.getString("Seri_No"),satir, 4);
   			 model.setValueAt(rs.getString("Ilk_Borclu"),satir, 5);
   			 model.setValueAt(rs.getString("Cek_Hesap_No"),satir, 6);
   			 model.setValueAt(rs.getString("Cins"),satir, 7);
   			 model.setValueAt(rs.getDouble("Tutar"),satir, 8);
   			 model.setValueAt(rs.getString("Cikis_Bordro"),satir, 9);
   				sDate1= rs.getDate("Cikis_Tarihi").toString(); 
   				Date ct= new SimpleDateFormat("yyyy-MM-dd").parse(sDate1); 
   			 model.setValueAt(ct,satir, 10);
   			 model.setValueAt(rs.getString("Cikis_Musteri"),satir, 11);
   			 model.setValueAt(rs.getString("Durum"),satir, 12);
   			 	sDate1= rs.getDate("T_Tarih").toString(); 
   				Date tt= new SimpleDateFormat("yyyy-MM-dd").parse(sDate1); 
   			 model.setValueAt(tt,satir, 13);
   			 model.setValueAt(rs.getString("Cikis_Ozel_Kod"),satir, 14);
   			result = false ;
   			}
   		 }
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage()); 	
		}
		return result ;
	}
	
	private void satir_tamamla(int satir)
	{
		 DefaultTableModel mdll = (DefaultTableModel) table.getModel();
		for (int i = 0; i <= satir; i ++)
		{
			 mdll.addRow(new Object[]{"", new Date(),"","","","","","",0.00,"",TARIH_CEVIR.tarih("01.01.1900"),"","",TARIH_CEVIR.tarih("01.01.1900"),""});
		}
	}
	public static void satir_ilave()
	{
		 DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		 int satir = table.getSelectedRow();
		 if ( satir  < 0 ) 
		 {
		 mdl.addRow(new Object[]{"", new Date(),"","","","","","",0.00,"",TARIH_CEVIR.tarih("01.01.1900"),"","",TARIH_CEVIR.tarih("01.01.1900"),""});
		 satir = 0 ;
		 }
		 else
		 {
	      mdl.insertRow(satir, new Object[]{"",  new Date(),"","","","","","",0.00,"",TARIH_CEVIR.tarih("01.01.1900"),"","",TARIH_CEVIR.tarih("01.01.1900"),""});
		 }
		 table.isRowSelected(satir);
		 table.repaint();
	}
	public static void satir_sil()
	{
		if (table.getSelectedRow() < 0 ) return ;
		 DefaultTableModel mdll = (DefaultTableModel) table.getModel();
		 mdll.removeRow(table.getSelectedRow());
		 table.repaint();
		 topla();
	}
	private static void topla()
	{
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		double tutar = 0 ;
		int evr_sayi = 0 ;
		 for (int i = 0; i <= table.getRowCount() - 1 ; i ++)
		{
			 if (! model.getValueAt(i,0).toString().equals(""))
			 {
			 tutar  += (double) model.getValueAt(i , 8);
	 		 evr_sayi += 1 ;
			 }
		}
		 label.setText(FORMATLAMA.doub_2(tutar));
		 lblNewLabel_12.setText(FORMATLAMA.doub_0(evr_sayi));
	}
	private void ort()
	{
		try
		{
		DefaultTableModel model = (DefaultTableModel)table.getModel();
         if (model.getRowCount()  == 0 ) return ;
         if (textField_2.getText().equals("")) {
        	 lblNewLabel_7.setText(FORMATLAMA.doub_2(0.00));
        	 textField_3.setText("");
       	 return;}
         getContentPane().setCursor(WAIT_CURSOR);
         double tfaiz ,gun,faiz,orgun,toppara,double_0 ;
         tfaiz = 0;
         gun = 0;
         faiz = 0;
         orgun = 0;
         toppara = 0;
         for (int i =0 ; i<= model.getRowCount() -1 ; i ++) {
             gun = 0;
             faiz = 0;
             orgun = 0;
             if (!  model.getValueAt(i , 0).toString().equals("")) 
             {
               	 String vade = "";
     	        if (model.getValueAt(i , 1).toString().length() >  10)
     	      	{
     	        	 vade = dateFormater(model.getValueAt(i , 1).toString() , "yyyy.MM.dd", "EEE MMM dd kk:mm:ss zzzz yyyy" ) ;
     	      	}
     	      	else
     	      	{
     	      	  String qwe =dateFormater(model.getValueAt(i , 1).toString() , "yyyy.MM.dd", "dd.MM.yyyy" ) ;
     	      	 vade  = qwe;
     	      	 }
     	        String gtt = TARIH_CEVIR.tarih_geri(dateChooser);
     	        	
     	        Date vadee =new SimpleDateFormat("yyyy.MM.dd").parse(vade); 
     	        Date bugun =new SimpleDateFormat("yyyy.MM.dd").parse(gtt);  
     	         gun  = daysBetween(bugun,vadee);
                 double_0 = Double.parseDouble(textField_2.getText());
                 faiz = ((((double) model.getValueAt(i , 8) * double_0) / 365) * gun) / 100;
                 toppara = toppara + (double) model.getValueAt(i , 8);
                 tfaiz = tfaiz + faiz ;
                 orgun = ((toppara * double_0) / 365) / 100 ;
                 textField_3.setText(FORMATLAMA.doub_2(tfaiz / orgun));
                 lblNewLabel_7.setText(FORMATLAMA.doub_2(tfaiz));
             }
         }
         getContentPane().setCursor(DEFAULT_CURSOR);
	}
    catch (Exception ex)
	{
   	 getContentPane().setCursor(DEFAULT_CURSOR);
   	 JOptionPane.showMessageDialog(null,  ex.getMessage()); 
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
	public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
}
	 private static String dateFormater(String dateFromJSON, String expectedFormat, String oldFormat) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
	        Date date = null;
	        String convertedDate = null;
	        try {
	            date = dateFormat.parse(dateFromJSON);
	            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(expectedFormat);
	            convertedDate = simpleDateFormat.format(date);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return convertedDate;
	    }
}

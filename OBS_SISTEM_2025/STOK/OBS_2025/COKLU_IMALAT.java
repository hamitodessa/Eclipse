package OBS_2025;

import java.awt.EventQueue;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import OBS_C_2025.DoubleEditor;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.NextCellActioin;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;

import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class COKLU_IMALAT extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(oac._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	
	private static JTable table;
	private static JDateChooser dtc ;
	private static JComboBox<String> cmbaltgrup;
	private static JComboBox<String> cmbanagrup;
	private static JComboBox<String> cmbdepo;
	private static JTextArea textArea;
	private ArrayList<String> listSomeString = null ;
	private ArrayList<String> listBarkod = null ;
	private JLabel label_3;
	private static JSplitPane splitPane ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					COKLU_IMALAT frame = new COKLU_IMALAT();
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
	public COKLU_IMALAT() {
		setResizable(true);
		setTitle("COKLU IMALAT");
	
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 779, 600);
		
		JSplitPane anaPane = new JSplitPane();
		anaPane.setDividerSize(0);
		anaPane.setResizeWeight(1.0);
		anaPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(anaPane, BorderLayout.CENTER);
		
		
		splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		anaPane.setLeftComponent(splitPane);
		
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 100));
		panel.setMaximumSize(new Dimension(0, 100));
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setForeground(new Color(0, 0, 128));
		tabbedPane_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(tabbedPane_1, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_1.addTab("Imalat Bilgileri", null, panel_2, null);
		tabbedPane_1.setEnabledAt(0, true);
		panel_2.setLayout(null);
	
		dtc = new JDateChooser();
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
                	SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
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
                	SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
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
		dtc.setBounds(201, 11, 153, 20);
		dtc.setDateFormatString("dd.MM.yyyy");
		dtc.setFont(new Font("Tahoma", Font.BOLD, 12));
		dtc.setDate(new Date());
		panel_2.add(dtc);
		
		cmbdepo = new JComboBox<String>();
		cmbdepo.setForeground(new Color(0, 0, 128));
		cmbdepo.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbdepo.setBounds(201, 40, 153, 22);
		panel_2.add(cmbdepo);
		
		JLabel label = new JLabel("Ana Grup");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(380, 15, 59, 14);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("Alt Grup");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(380, 44, 61, 14);
		panel_2.add(label_1);
		
		cmbaltgrup = new JComboBox<String>();
		cmbaltgrup.setForeground(new Color(0, 0, 128));
		cmbaltgrup.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbaltgrup.setEnabled(false);
		cmbaltgrup.setBounds(451, 40, 162, 22);
		panel_2.add(cmbaltgrup);
		
		cmbanagrup = new JComboBox<String>();
		cmbanagrup.setForeground(new Color(0, 0, 128));
		cmbanagrup.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbanagrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			alt_grup_doldur();
			}
		});
		cmbanagrup.setBounds(451, 11, 162, 22);
		panel_2.add(cmbanagrup);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ana_grup_doldur();
			}
		});
		button_1.setBounds(623, 11, 28, 23);
		button_1.setIcon(new ImageIcon(IMALAT.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		panel_2.add(button_1);
		
		JLabel lblNewLabel = new JLabel("Depo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(134, 44, 67, 14);
		panel_2.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Temizle");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setIcon(new ImageIcon(COKLU_IMALAT.class.getResource("/ICONLAR/icons8-housekeeper-16.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sifirla();
			}
		});
		btnNewButton.setBounds(10, 11, 103, 23);
		panel_2.add(btnNewButton);
		
				
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_1.addTab("Aciklama", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_14 = new JLabel("Aciklama");
		lblNewLabel_14.setBounds(10, 26, 66, 14);
		panel_4.add(lblNewLabel_14);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(86, 11, 445, 47);
		textArea.setDocument(new JTextFieldLimit(40));
		textArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		Border border1 = BorderFactory.createLineBorder(Color.GRAY);
		textArea.setBorder(BorderFactory.createCompoundBorder(border1,
	    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		panel_4.add(textArea);
			
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel() ; 
		table = new JTable(model){
			@Override
			public boolean isCellEditable(int row, int column) {  
				 switch (column) {
		         case 2:
		             return false;
		         case 4:
		             return false;
		         default:
		             return true;
		      }
				}
			public void changeSelection(final int row, final int column, boolean toggle, boolean extend)
            {
                super.changeSelection(row, column, toggle, extend);
                if (column <2 )
                {
               table.editCellAt(row, column);
               table.transferFocus();
                }
            }	
		};
		table.setGridColor(oac.gridcolor);
		table.setCellSelectionEnabled(true);
		model.addColumn("Barkod", new String []{""});
	    model.addColumn("Urun Kodu", new String []{""});
	    model.addColumn("Adi", new String []{""});
	    model.addColumn("Miktar", new Double [] {( 0.000 )});
	    model.addColumn("Evrak No", new String []{""});
	    TableColumn col ;
	    listBarkod = new ArrayList<String> () ;
	    stk_kodu_auto("Barkod");
	    ComboBoxTableCellEditor editor = new ComboBoxTableCellEditor( listBarkod ,table,"toplu");
	    col = table.getColumnModel().getColumn(0);
	    col.setCellEditor(editor);
		col.setMinWidth(100);
	    col.setHeaderRenderer(new SOLA());
  
	    listSomeString = new ArrayList<String> () ;
	    stk_kodu_auto("Kodu");
	    col = table.getColumnModel().getColumn(1);
	    ComboBoxTableCellEditor editorkodu = new ComboBoxTableCellEditor(  listSomeString ,table,"toplu");
	    col.setCellEditor(editorkodu);
		col.setMinWidth(100);
	    col.setHeaderRenderer(new SOLA());
	    
	    /*
        Java2sAutoComboBox comboBox1 = new Java2sAutoComboBox( listSomeString,"toplu");
        comboBox1.setDataList(listSomeString);
        comboBox1.setMaximumRowCount(10);
        comboBox1.setFont(new Font("Tahoma", Font.BOLD, 12));
        col.setCellEditor(new DefaultCellEditor(comboBox1));
		col.setMinWidth(130);
	    col.setHeaderRenderer(new SOLA());
	    */
	   
	    col = table.getColumnModel().getColumn(2);
		col.setMinWidth(300);
		col.setHeaderRenderer(new SOLA());
		
		col = table.getColumnModel().getColumn(3);
	    col.setHeaderRenderer(new SAGA());
	    col.setCellEditor( new DoubleEditor(3) );
		col.setCellRenderer(new TABLO_RENDERER(3,true));
		col.setMinWidth(100);
		
		col = table.getColumnModel().getColumn(4);
		col.setMinWidth(100);
		col.setHeaderRenderer(new SOLA());
		//***************
		DefaultTableCellRenderer r6 = new DefaultTableCellRenderer() {
	    @Override
	    public Component getTableCellRendererComponent(JTable table,
	            Object value, boolean isSelected, boolean hasFocus,
	            int row, int column) {
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);
	        Font font =new Font(table.getFont().getFontName(),Font.BOLD ,table.getFont().getSize());
		        setForeground(new Color(0, 0, 139));
		        setFont(font);
		        return this;
		    }
		};
		col.setCellRenderer(r6);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10)
				{
					if (table.isEditing())
					table.getCellEditor().stopCellEditing();
				}
			}
		});
		
		 JTableHeader th = table.getTableHeader();
		    Dimension dd = table.getPreferredSize();
		    dd.height = 30;
		    th.setPreferredSize(dd); 
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(22);
			table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			InputMap im = table.getInputMap(JTable.WHEN_FOCUSED);
	        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Action.NextCell");
	        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Action.NextCell");
	        ActionMap am = table.getActionMap();
	        am.put("Action.NextCell", new NextCellActioin(table,"toplu"));
	        table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
	        
	        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
        	Font font = new Font("Arial", 1, 12);
        	@Override
        	public Component getTableCellRendererComponent(JTable table,
        	            Object value, boolean isSelected, boolean hasFocus,
        	            int row, int column) 
        	{
        	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,  row, column);
        	        setFont(font);
        	        setForeground(Color.BLUE);
        	        return this;
        	}
        	};
       	table.getColumnModel().getColumn(1).setCellRenderer(r);
		table.repaint();
		table.getModel().addTableModelListener(	(TableModelListener) new TableModelListener() 
			{		@Override
		public void tableChanged(TableModelEvent e) {
				  TableModel model = (TableModel)e.getSource();
		   			if (model.getRowCount() > 0) {
		   			int row;
	   				row = table.getSelectedRow();     //e.getFirstRow();
		   		    int column = e.getColumn();
		   //**********
		   int urunsayi = 0 ;
	        for (int  i = 0 ; i <= table.getRowCount() -1 ; i ++)
	        {
	        	 if (! model.getValueAt(i,1).toString().equals(""))
		            {
		                urunsayi += 1;
		            }
	        }
	        label_3.setText( FORMATLAMA.doub_0(urunsayi));
		   //**********
		   			}
				}
  			});
	    scrollPane.setViewportView(table);
	    
	    JPanel panel_1 = new JPanel();
	    anaPane.setRightComponent(panel_1);
	    panel_1.setMinimumSize(new Dimension(0, 25));
		panel_1.setMaximumSize(new Dimension(0, 25));
		panel_1.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_1.setLayout(null);
		
		JLabel label_2 = new JLabel("Satir Sayisi :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(10, 3, 73, 14);
		panel_1.add(label_2);
		
		 label_3 = new JLabel("0");
		label_3.setForeground(new Color(139, 0, 0));
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(82, 3, 46, 14);
		panel_1.add(label_3);
		
	    for (int i = 0; i <= 15 ; i ++)
		{
			satir_ilave();
		}
	   // stk_kodu_auto();
		ana_grup_doldur();
		depo_doldur();
	}
	public static void satir_ilave()
	{
		 DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		 int satir = table.getSelectedRow();
		 if ( satir  < 0 ) 
		 {
		 mdl.addRow(new Object[]{"","","",0.000,""});
		 satir = 0 ;
		 }
		 else
		 {
	      mdl.insertRow(satir, new Object[]{"","","",0.000,""});
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
	}
	private static String urun_bilgi_doldur(String cins,String field) 
	{
		String isim = "" ;
		try {
			ResultSet rs = null;
				rs = f_Access.urun_adi_oku(cins,field);
		if (!rs.isBeforeFirst() ) {  
			 isim = "" ;
		}
		else
		{
			rs.next();
			isim = rs.getString("Adi");
		}
	}
	catch (Exception ex)
	{
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Bilgi Doldur", JOptionPane.ERROR_MESSAGE);
	}
		return isim ;
	}
	private  void stk_kodu_auto(String field)
	{
		try {
			ResultSet rs = null;
		 		rs = f_Access.stk_barkod_kod_oku(field);
			if (!rs.isBeforeFirst() ) {  
				if (field.equals("Kodu"))
				{
				listSomeString.add("");
				}
				else
				{
				listBarkod.add("");
				}
			}
			else
			{
				if (field.equals("Kodu"))
				{
					listSomeString.clear();
					listSomeString.add("");
					while (rs.next())
					{
						listSomeString.add(rs.getString("Kodu").toString());
					}
				}
				else
				{
					listBarkod.clear();
					listBarkod.add("");
					while (rs.next())
					{
						listBarkod.add(rs.getString("Barkod").toString());
					}
				}
			}
			}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Stok Kodu", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static  void kaydet()
	{
		if (table.getRowCount()  == 0 ) return ;
		  int urunsayi = 0 ;
	        for (int  i = 0 ; i <= table.getRowCount() -1 ; i ++)
	        {
	        	 if (! table.getValueAt(i,1).toString().equals(""))
		            {
		                urunsayi += 1;
		            }
	        }
		 int g = JOptionPane.showOptionDialog( null,  "Kayit Baslamasi...Kayit Sayisi:" + urunsayi, "Coklu Imalat",   JOptionPane.YES_NO_OPTION,
	    	   		JOptionPane.QUESTION_MESSAGE,null, oac.options, oac.options[1]); 
	    	        if(g != 0 ) { return;	}	
		 ///// Progres Bsr olayi
		Runnable runner = new Runnable()
	    { public void run() {
	        /////  
	    	try {
	   GuiUtil.setWaitCursor(splitPane,true);
		JInternalFrame internalFrame ;
		internalFrame  = new IMALAT();
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		 
		 int urunsayi = 0 ;
	        for (int  i = 0 ; i <= table.getRowCount() -1 ; i ++)
	        {
	        	 if (! table.getValueAt(i,1).toString().equals(""))
		            {
		                urunsayi += 1;
		            }
	        }
	    Progres_Bar_Temizle();
		OBS_MAIN.progressBar.setStringPainted(true);
		OBS_MAIN.progressBar.setMaximum(urunsayi);
		int satir = 0;
		for (int i =0;i<= table.getRowCount() -1;i++)
		{
			  if (! mdl.getValueAt(i,1).toString().equals(""))
			{
			 satir ++ ;
			IMALAT.btnNewButton_1.doClick();     // Yeni Fisno Al
			 Progres_Bar(urunsayi, satir);
			mdl.setValueAt(IMALAT.textField.getText(),i, 4)  ;
			IMALAT.dtc.setDate(dtc.getDate()) ;   // Tarih Esitle
			IMALAT.cmbdepo.setSelectedItem(cmbdepo.getSelectedItem().toString());
			IMALAT.cmbanagrup.setSelectedItem(cmbanagrup.getSelectedItem());
			IMALAT.cmbaltgrup.setSelectedItem(cmbaltgrup.getSelectedItem());
			IMALAT.textArea.setText(textArea.getText());  // Aciklama
			IMALAT.textField_1.setText(mdl.getValueAt(i,1).toString()); // Urun Kodu
			IMALAT.txtmiktar.setText(mdl.getValueAt(i,3).toString()); // Miktar
			IMALAT.btnNewButton.doClick();  // Cikan Hesaplat
			IMALAT. kaydet();
			
		
			}
		}
		 Progres_Bar_Temizle();
			GuiUtil.setWaitCursor(splitPane,false);
			JOptionPane.showMessageDialog(null,  "Imalat Islemi Tamamlandi .....", "Coklu Imalat", JOptionPane.PLAIN_MESSAGE);
	    }
		catch (Exception ex)
		{
			 Progres_Bar_Temizle();
			GuiUtil.setWaitCursor(splitPane,false);
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Coklu Imalat", JOptionPane.ERROR_MESSAGE);
		}
	    }
	    };
	    //// Progress Bar
	    Thread t = new Thread(runner, "Code Executer");
	    t.start();
	    //
	}
	private static void sifirla()
	{
		GRID_TEMIZLE.grid_temizle(table);
		for (int i = 0; i <= 16; i ++)
		{
			satir_ilave();
		}
		dtc.setDate(new Date());
		cmbanagrup.setSelectedItem("");
        cmbaltgrup.setSelectedItem("");
        cmbdepo.setSelectedItem("");
        textArea.setText("");
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
	private void ana_grup_doldur()
	{
		try {
		getContentPane().setCursor(oac.WAIT_CURSOR);
		cmbanagrup .removeAllItems();
		ResultSet rs=null;
			rs = f_Access.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
		if (!rs.isBeforeFirst() ) {  
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			cmbaltgrup.setEnabled(false);
			cmbanagrup .addItem("");
			cmbanagrup.setSelectedItem("");
			cmbaltgrup.setSelectedItem("");
		    return;
		} 
		cmbanagrup .addItem("");
	    while (rs.next())
	    {
	    	cmbanagrup .addItem(rs.getString("ANA_GRUP"));
	    }
	    cmbanagrup.setSelectedItem("");
	    getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Ana Grup", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void alt_grup_doldur()
	{
		try {
		getContentPane().setCursor(oac.WAIT_CURSOR);
		cmbaltgrup.removeAllItems();
		cmbaltgrup .addItem("");
		ResultSet rs=null;
			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				int in1 = rs.getInt("AGID_Y");
				rs =null;
				rs = f_Access.stk_kod_alt_grup_degisken_oku(in1);
			}
		if (!rs.isBeforeFirst() ) {  
			cmbaltgrup.setSelectedItem("");
			cmbaltgrup.setEnabled(false);
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		} 
		else
		{
	    while (rs.next())
	    {
	    	cmbaltgrup .addItem(rs.getString("ALT_GRUP"));
	    }
	    cmbaltgrup.setSelectedItem(0);
	    cmbaltgrup.setEnabled(true);
		}
		getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Alt Grup", JOptionPane.ERROR_MESSAGE);    	
		}
	}
	private void depo_doldur()
	{
			try {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				cmbdepo .removeAllItems();
			ResultSet rs = null;
				   rs = f_Access.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
			if (!rs.isBeforeFirst() ) {  
				cmbdepo.addItem("");
				cmbdepo.setSelectedItem("");
			}
			else
			{
				cmbdepo.addItem("");
				while (rs.next())
				{
					cmbdepo.addItem(rs.getString("DEPO"));
				}
			}
			cmbdepo.setSelectedItem("");
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null,  ex.getMessage(), "Depo Doldur", JOptionPane.ERROR_MESSAGE);
			}
		}
	public static void bilgi_doldur(String cins) 
	{
		try {
			 DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			if (table.getSelectedColumn() == 0)  // Barkod
			{
				if (cins.equals(""))
				{
					mdl.setValueAt("",table.getSelectedRow() ==  -1 ? 0  : table.getSelectedRow(), 1)  ;
					mdl.setValueAt("",table.getSelectedRow() ==  -1 ? 0  : table.getSelectedRow(), 2)  ;
					return;
				}
				mdl.setValueAt(urun_bilgi_doldur(cins,"Barkod"),table.getSelectedRow() ==  -1 ? 0  : table.getSelectedRow(), 2)  ;
			}
			/////////////////////////////////////////////////////////
		else if (table.getSelectedColumn() == 1)  // URUN KODU
		{
			if (cins.equals(""))
			{
				mdl.setValueAt("",table.getSelectedRow() ==  -1 ? 0  : table.getSelectedRow(), 0)  ;
				mdl.setValueAt("",table.getSelectedRow() ==  -1 ? 0  : table.getSelectedRow(), 2)  ;
				return;
			}
			mdl.setValueAt(urun_bilgi_doldur(cins,"Kodu"),table.getSelectedRow() ==  -1 ? 0  : table.getSelectedRow() , 2)  ;
		}
	
	}
	catch (Exception ex)
	{
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Bilgi Doldur", JOptionPane.ERROR_MESSAGE);
	}
	}
}

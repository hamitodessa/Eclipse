package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
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
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.apache.commons.lang.StringUtils;

import com.toedter.calendar.JDateChooser;

import OBS_C_2025.DoubleEditor;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.NextCellActioin;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;

public class ZAYI extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(oac._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);

	private ArrayList<String> listSomeString = null ;
	private ArrayList<String> listBarkod = null ;
	private ArrayList<String> listdepo = null ;
	private static JTextField textField;
	private JTextField textField_4;
	private static JTextField textField_9;
	private static JTextField textField_10;
	private static JTable table;
	private static JDateChooser dtc ;
	private static JLabel lblNewLabel_12 ;
	private static JLabel lblNewLabel_13 ;
	private static JLabel label_5 ;
	private static JLabel label_8 ;
	private static JLabel label_9 ;
	private static JComboBox<String> cmbanagrup ;
	private static JComboBox<String> cmbaltgrup ;
	private static JComboBox<String> cmbdepo;
	public static JSplitPane splitPane ;
	private static  String tar = "" ;
	private static JTabbedPane tabbedPane ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZAYI frame = new ZAYI();
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
	public ZAYI() {
		setTitle("ZAYI");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,1015,675);
		
		splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 100));
		panel.setMaximumSize(new Dimension(0, 100));
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(tabbedPane_1, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_1.addTab("Evrak Bilgileri", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Evrak No");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 11, 69, 14);
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setDocument(new JTextFieldLimit(10));
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					try {
						getContentPane().setCursor(oac.WAIT_CURSOR);
					int sno = 0 ;
							 sno  =f_Access.zayi_fisno_al() ;
					int kj = 0 ;
					kj = 10 - Integer.toString(sno).length() ;
					String str_ = StringUtils.repeat("0", kj)   + Integer.toString(sno);
					textField.setText(str_.equals("0000000000") ? "0000000001":str_);
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
					}
					catch (Exception ex)
					{
						getContentPane().setCursor(oac.DEFAULT_CURSOR);
						 JOptionPane.showMessageDialog(null,  "Zayii Numaralarinda onceden harf ve rakkam kullanildigindan otomatik numara verilemez...."); 	
					}
				}
			}
		});
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) 
			  {
				  getContentPane().setCursor(oac.WAIT_CURSOR);
				  fiskont();
				  getContentPane().setCursor(oac.DEFAULT_CURSOR);
			  }
			  public void removeUpdate(DocumentEvent e) {
				  	getContentPane().setCursor(oac.WAIT_CURSOR);
				  	fiskont();
				  	getContentPane().setCursor(oac.DEFAULT_CURSOR);
			  }
			 public void insertUpdate(DocumentEvent e) {
			  		getContentPane().setCursor(oac.WAIT_CURSOR);
			  		fiskont();
			  		getContentPane().setCursor(oac.DEFAULT_CURSOR);
			  }
			});
		textField.addAncestorListener(new AncestorListener() {
		    @Override
		    public void ancestorRemoved(AncestorEvent pEvent) {
		    }
		    @Override
		    public void ancestorMoved(AncestorEvent pEvent) {
		    }
		    @Override
		    public void ancestorAdded(AncestorEvent pEvent) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	textField.requestFocusInWindow();
		            }
		        });
		    }
		});
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.setBounds(77, 8, 110, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Tarih");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(233, 11, 46, 14);
		panel_2.add(lblNewLabel_4);
		
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
		dtc.setBounds(278, 8, 128, 20);
		dtc.setDateFormatString("dd.MM.yyyy");
		dtc.setFont(new Font("Tahoma", Font.BOLD, 12));
		dtc.setDate(new Date());
		panel_2.add(dtc);
		
		JLabel lblAnaGrup = new JLabel("Ana Grup");
		lblAnaGrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAnaGrup.setBounds(472, 11, 61, 14);
		panel_2.add(lblAnaGrup);
		
		cmbanagrup = new JComboBox<String>();
		cmbanagrup.setForeground(new Color(0, 0, 128));
		cmbanagrup.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbanagrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur();
			}
		});
		cmbanagrup.setBounds(543, 8, 148, 22);
		panel_2.add(cmbanagrup);
		
		JLabel lblAltGrup = new JLabel("Alt Grup");
		lblAltGrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAltGrup.setBounds(472, 40, 69, 14);
		panel_2.add(lblAltGrup);
		
		cmbaltgrup = new JComboBox<String>();
		cmbaltgrup.setForeground(new Color(0, 0, 128));
		cmbaltgrup.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbaltgrup.setBounds(543, 37, 148, 22);
		panel_2.add(cmbaltgrup);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
						getContentPane().setCursor(oac.WAIT_CURSOR);
			            textField.setText("");
			        		  textField.setText( f_Access.zayi_son_bordro_no_al());
			            if ( textField.getText().equals("0") )
			            		{
			            	 textField.setText("");
			            	JOptionPane.showMessageDialog(null,  "Hic Kayit Yok...", "Zayi Fisi Okuma", JOptionPane.ERROR_MESSAGE);
			            	textField.requestFocus();
			            		}
			        	getContentPane().setCursor(oac.DEFAULT_CURSOR);
					 }
			        catch (Exception ex)
					 {
			        	getContentPane().setCursor(oac.DEFAULT_CURSOR);
			        	JOptionPane.showMessageDialog(null,  ex.getMessage(), "Zayi Fisi Okuma", JOptionPane.ERROR_MESSAGE);
					 }
			}
		});
		button.setIcon(new ImageIcon(ZAYI.class.getResource("/ICONLAR/icons8-view-16.png")));
		button.setBounds(195, 7, 28, 23);
		panel_2.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ana_grup_doldur();
			}
		});
		button_1.setIcon(new ImageIcon(ZAYI.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		button_1.setToolTipText("Yenile");
		button_1.setBounds(701, 7, 26, 23);
		panel_2.add(button_1);
		
		JLabel lblDepo = new JLabel("Depo");
		lblDepo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDepo.setBounds(233, 40, 46, 14);
		panel_2.add(lblDepo);
		
		cmbdepo = new JComboBox<String>();
		cmbdepo.setForeground(new Color(0, 0, 128));
		cmbdepo.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbdepo.setBounds(278, 37, 148, 22);
		panel_2.add(cmbdepo);
		
	
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_1.addTab("Ek Bilgi", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_18 = new JLabel("1 -");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_18.setBounds(10, 11, 34, 14);
		panel_5.add(lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel("2 -");
		lblNewLabel_19.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_19.setBounds(10, 38, 34, 14);
		panel_5.add(lblNewLabel_19);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_9.setBounds(54, 8, 447, 20);
		textField_9.setDocument(new JTextFieldLimit(50));
		panel_5.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_10.setBounds(54, 33, 447, 20);
		textField_10.setDocument(new JTextFieldLimit(50));
		panel_5.add(textField_10);
		textField_10.setColumns(10);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(0);
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		splitPane.setRightComponent(splitPane_1);
		
		JPanel panel_3 = new JPanel();
		splitPane_1.setRightComponent(panel_3);
		panel_3.setMinimumSize(new Dimension(0,90));
		panel_3.setMaximumSize(new Dimension(0, 90));
		panel_3.setLayout(null);
		
		//***
		JPanel panel_71 = new JPanel();
		panel_71.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_71.setBounds(2, 1, 990, 21);
		panel_71.setMinimumSize(new Dimension(0, 25));
		panel_71.setMaximumSize(new Dimension(0,25));
		panel_3.add(panel_71);
		panel_71.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Satir Sayisi :");
		lblNewLabel.setBounds(32, 3, 73, 14);
		panel_71.add(lblNewLabel);
		
		lblNewLabel_13 = new JLabel("0");
		lblNewLabel_13.setForeground(new Color(139, 0, 0));
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_13.setBounds(104, 3, 46, 14);
		panel_71.add(lblNewLabel_13);
		
		label_8 = new JLabel("0.000");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		label_8.setForeground(new Color(139, 0, 0));
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(471, 3, 102, 14);
		panel_71.add(label_8);
		
		label_9 = new JLabel("0.00");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setForeground(new Color(139, 0, 0));
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(659, 3, 109, 14);
		panel_71.add(label_9);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(2, 22, 990, 68);
		panel_3.add(tabbedPane_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane_2.addTab("Toplamlar", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_11 = new JLabel("Barkod");
		lblNewLabel_11.setBounds(10, 11, 46, 14);
		panel_1.add(lblNewLabel_11);
		
		textField_4 = new JTextField();
		textField_4.setBounds(66, 8, 156, 20);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		lblNewLabel_12 = new JLabel(".....");
		lblNewLabel_12.setForeground(new Color(139, 0, 0));
		lblNewLabel_12.setBounds(232, 11, 270, 14);
		panel_1.add(lblNewLabel_12);
		
		label_5 = new JLabel(".....");
		label_5.setForeground(new Color(0, 0, 128));
		label_5.setBounds(557, 11, 270, 14);
		panel_1.add(label_5);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setDividerSize(0);
		splitPane_1.setLeftComponent(splitPane_2);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setMinimumSize(new Dimension(30, 0));
		toolBar_1.setMaximumSize(new Dimension(30, 0));
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		splitPane_2.setLeftComponent(toolBar_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() < 0 ) return ;
				URUN_ARAMA arm ;
				getContentPane().setCursor(oac.WAIT_CURSOR);
					arm = new URUN_ARAMA();
					arm.setVisible(true);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				table.getModel().setValueAt( oac.stk_kodu,table.getSelectedRow(), 1) ;
				bilgi_doldur(oac.stk_kodu) ;
						}
		});
		btnNewButton.setIcon(new ImageIcon(ZAYI.class.getResource("/ICONLAR/icons8-view-16.png")));
		toolBar_1.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					satir_ilave();
					DefaultTableModel mdll = (DefaultTableModel) table.getModel();
					 mdll.removeRow(mdll.getRowCount() -1);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(ZAYI.class.getResource("/ICONLAR/yeni.png")));
		toolBar_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() < 0 ) return ;
				satir_sil();
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addRow(new Object[]{"","","",0.00,0.000,"",0.00,""});
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(ZAYI.class.getResource("/ICONLAR/icons8-reduce-16.png")));
		toolBar_1.add(btnNewButton_3);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		splitPane_2.setRightComponent(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Zayi", null, scrollPane, null);
		
		DefaultTableModel model = new DefaultTableModel() ; 
		table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {  
				 switch (column) {
		         case 5:
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				   urun_bilgi_doldur(table.getValueAt(table.getSelectedRow(),1).toString()); 
			}
		});
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
		table.setCellSelectionEnabled(true);
		model.addColumn("Barkod", new String []{""});
	    model.addColumn("Urun Kodu", new String []{""});
	    model.addColumn("Depo", new String []{""});
	    model.addColumn("Fiat", new Double [] {( 0.00 )});
	    model.addColumn("Miktar", new Double [] {( 0.000 )});
	    model.addColumn("Birim", new String []{"" });
	    model.addColumn("Tutar",new Double [] {( 0.00 )});
	    model.addColumn("Izahat", new String []{"" });
	    
	    TableColumn col ;
	    listBarkod = new ArrayList<String> () ;
	    stk_kodu_auto("Barkod");
	    ComboBoxTableCellEditor editor = new ComboBoxTableCellEditor( listBarkod ,table,"zayi");
	    col = table.getColumnModel().getColumn(0);
	    col.setCellEditor(editor);
		col.setMinWidth(100);
	    col.setHeaderRenderer(new SOLA());
  
	    listSomeString = new ArrayList<String> () ;
	    stk_kodu_auto("Kodu");
	    col = table.getColumnModel().getColumn(1);
	    ComboBoxTableCellEditor editorkodu = new ComboBoxTableCellEditor(  listSomeString ,table,"zayi");
	    col.setCellEditor(editorkodu);
		col.setMinWidth(100);
	    col.setHeaderRenderer(new SOLA());
	   /**
	    col = table.getColumnModel().getColumn(0);
		col.setMinWidth(100);
	    col.setHeaderRenderer(new SOLA());
	    //**
	    col = table.getColumnModel().getColumn(1);
	    listSomeString = new ArrayList<String> () ;
	    stk_kodu_auto();
        Java2sAutoComboBox comboBox1 = new Java2sAutoComboBox( listSomeString,"zayi");
        comboBox1.setDataList(listSomeString);
        comboBox1.setMaximumRowCount(10);
        comboBox1.setFont(new Font("Tahoma", Font.BOLD, 12));
        col.setCellEditor(new DefaultCellEditor(comboBox1));
		col.setMinWidth(110);
	    col.setHeaderRenderer(new SOLA());
	    */
	    
	    col = table.getColumnModel().getColumn(2);
	    listdepo = new ArrayList<String> () ;
	    depo_auto();
        Java2sAutoComboBox combodp = new Java2sAutoComboBox( listdepo,"zayi");
        combodp.setDataList(listdepo);
	    col.setCellEditor(new DefaultCellEditor(combodp));
		col.setMinWidth(130);
	    col.setHeaderRenderer(new SOLA());

	    col = table.getColumnModel().getColumn(3);
	    col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setMinWidth(100);
		
		col = table.getColumnModel().getColumn(4);
	    col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(3) );
		col.setCellRenderer(new TABLO_RENDERER(3,false));
		col.setMinWidth(100);
		
		col = table.getColumnModel().getColumn(5);
		 col.setHeaderRenderer(new SOLA());
		col.setMinWidth(100);
		
		col = table.getColumnModel().getColumn(6);
	    col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setMinWidth(100);
		
		col = table.getColumnModel().getColumn(7);
		col.setMinWidth(200);
		JTextField atf = new JTextField();
	    atf.setDocument(new JTextFieldLimit(40));
	    col.setCellEditor(new DefaultCellEditor(atf));
		col.setHeaderRenderer(new SOLA());
		
	   
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
        am.put("Action.NextCell", new NextCellActioin(table,"fatura"));
        table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
  		
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
        	    Font font = new Font("Arial", 1, 12);
        	    @Override
        	    public Component getTableCellRendererComponent(JTable table,
        	            Object value, boolean isSelected, boolean hasFocus,
        	            int row, int column) {
        	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
        	                row, column);
        	        setFont(font);
        	        setForeground(Color.BLUE);
        	        return this;
        	    }
        	};
       	table.getColumnModel().getColumn(1).setCellRenderer(r);
		table.repaint();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent lse) {
			        if (!lse.getValueIsAdjusting()) {
			        	 DefaultTableModel model = (DefaultTableModel)table.getModel();
			        	 if (model.getRowCount() == 0) return ;
			        	 if (table.getSelectedRow()  < 0) return;
			        	 urun_bilgi_doldur(model.getValueAt(table.getSelectedRow() , 1).toString());
			        }
			    }
			});
		scrollPane.setViewportView(table);
	
	//	stk_kodu_auto();
		ana_grup_doldur();
		depo_doldur();
		//***********
		String deger;
		Integer sat_sayi;
		try {
				deger = GLOBAL.setting_oku("STK_FAT_SATIR").toString();
				sat_sayi =Integer.parseInt(deger);
				for (int i = 0; i <= sat_sayi -1 ; i ++)
				{
					satir_ilave();
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,  ex.getMessage(), "Zayi Fis", JOptionPane.ERROR_MESSAGE);					
			}
		//***********
		table.getModel().addTableModelListener(	(TableModelListener) new TableModelListener() 
       			{		@Override
				public void tableChanged(TableModelEvent e) {
 	   				  TableModel model = (TableModel)e.getSource();
  			   			if (model.getRowCount() > 0) {
   			   			int row;
 		   				row = table.getSelectedRow();     //e.getFirstRow();
   			   		    int column = e.getColumn();
   			   if (column == 3)  //FIAT
		       {
   			   	    	double fiat ,miktar = 0 ;
   			   	    	fiat =  Double.parseDouble(model.getValueAt(row, 3).toString());
   			   	    	miktar = Double.parseDouble(model.getValueAt(row, 4).toString());
   			   	    	model.setValueAt( fiat * miktar,row, 6)  ;
		   	   }
  			   	 if (column == 4)  //MIKTAR
		   	     {
   			   		 	double fiat ,miktar = 0 ;
   			   		 	fiat =  Double.parseDouble(model.getValueAt(row, 3).toString());
   			   		 	miktar = Double.parseDouble(model.getValueAt(row, 4).toString());
   			   		 	model.setValueAt( fiat * miktar,row, 6)  ;
		   	     }
		   		}
  			   		 toplam();
				}
   			});
		textField.requestFocus();
	}
	//******************************************************************************************************
	public static void kaydet()
	{
        if (textField.getText().equals("")) return ;
        DefaultTableModel mdl = (DefaultTableModel) table.getModel();
        if (mdl.getRowCount() == 0)  return;
       try
       {
        long startTime = System.currentTimeMillis(); 
         tar = TARIH_CEVIR.tarih_geri_saatli(dtc) ;
         GuiUtil.setWaitCursor(ZAYI.splitPane,true);
		stok_isle();
         acik_yaz();
         GuiUtil.setWaitCursor(ZAYI.splitPane,false);
        long endTime = System.currentTimeMillis();
		long estimatedTime = endTime - startTime;
		double seconds = (double)estimatedTime/1000; 
		OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
     
       textField.setText("");
       textField.requestFocus();
       }
        catch (Exception ex)
        {
        	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Zayi Kayit", JOptionPane.ERROR_MESSAGE);             
         }
	}
	private void fiskont()
	{
		try {
			// GuiUtil.setWaitCursor(ZAYI.splitPane,true);
			 long startTime = System.currentTimeMillis();
			ResultSet rss = null;
				 	rss = f_Access.zayi_oku(textField.getText(), "ZAI");
			  if (!rss.isBeforeFirst() ) {  
	              GRID_TEMIZLE.grid_temizle(table);
	              sifirla();
	 			}
				else
				{
				rss.next();
				  GRID_TEMIZLE.grid_temizle(table);
				  sifirla();
				  dtc.setDate(rss.getDate("Tarih"));
			  //  '***********GRUP DOLDUR
			  cmbanagrup.setSelectedItem(rss.getString("Ana_Grup") == null ? "" :rss.getString("Ana_Grup"));
	         cmbaltgrup.setSelectedItem(rss.getString("Alt_Grup") == null ? "" :rss.getString("Alt_Grup"));
					//  '************ACIKLAMA OKU ***********************************************************
						
							textField_9.setText( f_Access.aciklama_oku("ZAI", 1, textField.getText(), "C"));
							textField_10.setText( f_Access.aciklama_oku("ZAI", 2, textField.getText(), "C"));
						
					 // '*************************************************************************************
			        rss.first();   
			    	DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			    	int satir =0 ;
			    	do
			        {
			   		 mdl.insertRow(satir,new Object[]{rss.getString("Barkod"),rss.getString("Urun_Kodu"),rss.getString("Depo"),
			   				 			rss.getDouble("Fiat"), Math.abs(rss.getDouble("Miktar")),
			   				 			rss.getString("Birim"),  Math.abs(rss.getDouble("Tutar")),rss.getString("Izahat")});
			   		satir +=1 ;
			   		mdl.removeRow(mdl.getRowCount() -1);
			        }  while (rss.next()) ;
		                urun_bilgi_doldur(mdl.getValueAt(0,1).toString()); 
		                toplam();
		                long endTime = System.currentTimeMillis();
		        		long estimatedTime = endTime - startTime;
		        		double seconds = (double)estimatedTime/1000; 
		        		OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
				}
		}
		catch (Exception ex)
		{
			// GuiUtil.setWaitCursor(ZAYI.splitPane,false);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Zayii Fis Kontrol", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void sifirla()
	{
		String deger;
		Integer sat_sayi;
				try {
					deger = GLOBAL.setting_oku("STK_FAT_SATIR").toString();
					sat_sayi =Integer.parseInt(deger);
					for (int i = 0; i <= sat_sayi; i ++)
					{
						satir_ilave();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	        table.isRowSelected(0);
	        label_8.setText("0.000");
	        label_9.setText("0.00");
	        cmbanagrup.setSelectedItem("");
	        cmbaltgrup.setSelectedItem("");
	        textField_9.setText("");
	        textField_10.setText("");
	        lblNewLabel_12.setText("....");
	        label_5.setText("");
	       dtc.setDate(new Date());
	}
	private static void stok_isle() throws ClassNotFoundException, SQLException 
	{
		try {
			
			f_Access.stok_sil(textField.getText(), "ZAI", "C");
 			
		 DefaultTableModel mdl = (DefaultTableModel) table.getModel();
	       for (int  i = 0 ; i <=  mdl.getRowCount() - 1 ; i++)
	    	   if (! mdl.getValueAt(i,1).toString().equals(""))
	    	   {
	           stk_yaz_2(i);
	    	   }
	}
	catch (Exception ex)
	{
		JOptionPane.showMessageDialog(null, ex.getMessage(),  "Zayi  Stokisl", JOptionPane.ERROR_MESSAGE);   
	}
	}
	private static void stk_yaz_2(int sat) throws ClassNotFoundException, SQLException
	{
		try {
	        double miktar = 0 ,tutar = 0 ;
	        String  izah = "" ;
	        int anagrp, altgrp, depo ;
	        depo = 0 ;
	   		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
	   		ResultSet rs =null ;
	   	 if (mdl.getValueAt(sat,2) == null)
	   		{
		            depo = 0 ;
	   		}
		    else
		    {
		    	
		    		rs = f_Access.urun_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN",  mdl.getValueAt(sat,2).toString());
				
		    	if (!rs.isBeforeFirst() ) {      		
    	    	}
    	    	else
    	    	{
    	    		rs.next();
    				depo = rs.getInt("DPID_Y");
    	    	}
		   }
	        	miktar = Double.parseDouble( mdl.getValueAt(sat,4).toString());
	            miktar = miktar * -1;
	            tutar =  Double.parseDouble( mdl.getValueAt(sat,6).toString());
	            tutar =  tutar * -1;
	         
	            if ( mdl.getValueAt(sat,7).equals(""))
	       		{
		            izah = textField.getText() + " Nolu Zayiat Fisi...";
	       		}
		        else
		        {
		            izah =  mdl.getValueAt(sat,7) .toString();
		        }
	            
	        anagrp = 0 ;
	        if ( ! cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals("") ) {
	        		
	        			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",  cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString());
	        		
	        		if (!rs.isBeforeFirst() ) {      		
	    	    	}
	        		else
	        		{
	        			rs.next();
		        		anagrp  = rs.getInt("AGID_Y");	
	        		}
	        }
	        altgrp = 0;
	        if ( ! cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString().equals("") ) {
	     	      
	     	    		rs =f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN",  cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString());
	     			
	     	      	if (!rs.isBeforeFirst() ) {      		
	    	    	}
	        		else
	        		{
	        			rs.next();
		     	      	altgrp  = rs.getInt("ALID_Y");
	        		}
	     	}
	        double fiat =0 ;
	         fiat = Double.parseDouble( mdl.getValueAt(sat,3).toString());
	       
	        f_Access.stk_kaydet(textField.getText(), "ZAI", tar, depo,  mdl.getValueAt(sat,1).toString(), miktar, fiat
                        ,KUSUR_YUVARLA. round(tutar,2),KUSUR_YUVARLA. round(tutar,2), "C", izah, anagrp, altgrp,0, "","","",GLOBAL.KULL_ADI);
 			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Zayiat Stkyz2", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private static void acik_yaz()
	{
		try {
	        acik_sil();
	        	
	        		f_Access.aciklama_yaz("ZAI", 1, textField.getText(),  textField_9.getText(), "C");
	        		f_Access.aciklama_yaz("ZAI", 2, textField.getText(), textField_10.getText(), "C");
		 		
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Zayiat  Acikyz", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private static void acik_sil()
	{
		try {
			
				f_Access.aciklama_sil("ZAI", textField.getText(), "C");
 			
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.getMessage(),  "Zayiat  Aciksil", JOptionPane.ERROR_MESSAGE);   
			}
	}
	public static void satir_ilave()
	{
		 DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		 int satir = table.getSelectedRow();
		 if ( satir  < 0 ) 
		 {
		 mdl.addRow(new Object[]{"","","",0.00,0.000,"",0.00,""});
		 satir = 0 ;
		 }
		 else
		 {
	      mdl.insertRow(satir, new Object[]{"","","",0.00,0.000,"",0.00,""});
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
		 toplam();
	}
	public static void bilgi_doldur(String cins) 
	{
		try {
			ResultSet rs = null;
			
			if (table.getSelectedColumn() == 0)  //BARKOD
			{
				if (cins.equals(""))
				{
					lblNewLabel_12.setText("");
					label_5.setText("");
					table.getModel().setValueAt("",table.getSelectedRow(), 1) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 3) ;
					table.getModel().setValueAt(0.000,table.getSelectedRow(), 4) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 5) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 6) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 7) ;
					toplam();
					return;
				}
				
					rs = f_Access.urun_adi_oku(cins,"Barkod");
				
		
			if (!rs.isBeforeFirst() ) {  
				lblNewLabel_12.setText("");
				label_5.setText("");
				table.getModel().setValueAt("",table.getSelectedRow(), 1) ;
				table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
				table.getModel().setValueAt(0.00,table.getSelectedRow(), 3) ;
				table.getModel().setValueAt(0.000,table.getSelectedRow(), 4) ;
				table.getModel().setValueAt("",table.getSelectedRow(), 5) ;
				table.getModel().setValueAt(0.00,table.getSelectedRow(), 6) ;
				table.getModel().setValueAt("",table.getSelectedRow(), 7) ;
			}
			else
			{
				rs.next();
				lblNewLabel_12.setText(rs.getString("Adi"));
				label_5.setText(rs.getString("Ana_Grup"));
				table.getModel().setValueAt(rs.getString("Kodu"),table.getSelectedRow(), 1) ;
				table.getModel().setValueAt(rs.getString("Birim"),table.getSelectedRow(), 5) ;
			}
				toplam();
			}
			else if (table.getSelectedColumn() == 1)  // URUN KODU
		{
				if (cins.equals(""))
				{
					lblNewLabel_12.setText("");
					label_5.setText("");
					table.getModel().setValueAt("",table.getSelectedRow(), 0) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 3) ;
					table.getModel().setValueAt(0.000,table.getSelectedRow(), 4) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 5) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 6) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 7) ;
					toplam();
					return;
				}
			
				rs = f_Access.urun_adi_oku(cins,"Kodu");
			
	
		if (!rs.isBeforeFirst() ) {  
			lblNewLabel_12.setText("");
			label_5.setText("");
			table.getModel().setValueAt("",table.getSelectedRow(), 0) ;
			table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
			//table.getModel().setValueAt("0.00",table.getSelectedRow(), 3) ;
			//table.getModel().setValueAt("0.000",table.getSelectedRow(), 4) ;
			//table.getModel().setValueAt("",table.getSelectedRow(), 5) ;
			//table.getModel().setValueAt("0.00",table.getSelectedRow(), 6) ;
			//table.getModel().setValueAt("",table.getSelectedRow(), 7) ;
		}
		else
		{
			rs.next();
			lblNewLabel_12.setText(rs.getString("Adi"));
			label_5.setText(rs.getString("Ana_Grup"));
			table.getModel().setValueAt(rs.getString("Barkod"),table.getSelectedRow(), 0) ;
			table.getModel().setValueAt(rs.getString("Birim"),table.getSelectedRow(), 5) ;
		}
			toplam();
		}
	}
	catch (Exception ex)
	{
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Bilgi Doldur...", JOptionPane.ERROR_MESSAGE);
	}
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
	private  void depo_auto()
	{
		try {
		ResultSet rs = null;
		
			   rs =f_Access.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
			
		if (!rs.isBeforeFirst() ) {  
			listdepo.add("");
		}
		else
		{
			listdepo.add("");
			while (rs.next())
			{
				listdepo.add(rs.getString("DEPO"));
			}
		}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Depo Doldur", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void ana_grup_doldur()
	{
		try {
		getContentPane().setCursor(oac.WAIT_CURSOR);
		cmbanagrup .removeAllItems();
		ResultSet rs=null;
		
			rs =f_Access.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
		
		if (!rs.isBeforeFirst() ) {  
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			cmbaltgrup.setEnabled(false);
			cmbanagrup .addItem("");
			cmbanagrup.setSelectedItem("");
		    return;
		} 
		cmbanagrup .addItem("");
	    while (rs.next())
	    {
	    	cmbanagrup .addItem(rs.getString("ANA_GRUP"));
	    }
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
	private static void toplam()
	{
		try {
			double  dbl=0, dbl1=0;
		      if (   table == null) return ;
		      DefaultTableModel mdll = (DefaultTableModel) table.getModel();
		        for (int  i = 0 ; i < mdll.getRowCount()- 1;i++)
		        {
		        	dbl1 += Double.parseDouble(table.getValueAt(i,4).toString()) ;
		            dbl += Double.parseDouble(table.getValueAt(i,6).toString()) ;
		        }
		        label_8.setText(FORMATLAMA.doub_3(dbl1));
		        label_9.setText(FORMATLAMA.doub_2(dbl));
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Toplam", JOptionPane.ERROR_MESSAGE);   
		}
	}
	public static void zai_sil()
	{
		try {
        if (textField.getText().equals("")) return ;
        if (table.getRowCount() == 0) return;
        int g =  JOptionPane.showOptionDialog( null,  "Kayit Dosyadan Silinecek ..?"  ,
        		"Zai Dosyasindan Evrak Silme",   JOptionPane.YES_NO_OPTION,
	   			 	JOptionPane.QUESTION_MESSAGE,   			 	null,     //no custom icon
	   			 	oac.options,  //button titles
	   			 	oac.options[1]); //default button
	 	 if(g != 0 ) { return;	}
	 	  long startTime = System.currentTimeMillis();
	 	
	 	
	 		f_Access.stok_sil(textField.getText(), "ZAI", "C");
			
        acik_sil();
        long endTime = System.currentTimeMillis();
		long estimatedTime = endTime - startTime;
		double seconds = (double)estimatedTime/1000; 
		OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
      
        textField.setText("");
        textField.requestFocus();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Zayi Silme", JOptionPane.ERROR_MESSAGE);   
		}
	}
	public static void urun_bilgi_doldur(String cins) 
	{
		try {
			ResultSet rs = null;
			
				rs = f_Access.urun_adi_oku(cins,"Kodu");
			
		if (!rs.isBeforeFirst() ) {  
			lblNewLabel_12.setText("");
			label_5.setText("");
		}
		else
		{
			rs.next();
			lblNewLabel_12.setText(rs.getString("Adi"));
			label_5.setText(rs.getString("Ana_Grup"));
		}
	}
	catch (Exception ex)
	{
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Bilgi Doldur.", JOptionPane.ERROR_MESSAGE);
	}
	}
	 private void depo_doldur()
		{
				try {
					getContentPane().setCursor(oac.WAIT_CURSOR);
					cmbdepo.removeAllItems();
				ResultSet rs = null;
				
					   rs = f_Access.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
				
				if (!rs.isBeforeFirst() ) {  
					cmbdepo.addItem("");
					cmbdepo.addItem("Bos Olanlar");
					cmbdepo.setSelectedItem("");
				}
				else
				{
					cmbdepo.addItem("");
					cmbdepo.addItem("Bos Olanlar");
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
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
					JOptionPane.showMessageDialog(null,  ex.getMessage(), "Depo Doldur", JOptionPane.ERROR_MESSAGE);
				}
			}
}

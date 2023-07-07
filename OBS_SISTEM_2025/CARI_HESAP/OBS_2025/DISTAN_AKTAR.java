package OBS_2025;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.COKLU_GIRIS_TARIH;
import OBS_C_2025.DoubleEditor;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JDateChooserEditor;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.dEKONT_BILGI;

import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings({"serial" , "static-access","deprecation"})
public class DISTAN_AKTAR extends JInternalFrame {
	
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(oac._ICar , oac._ICari_Loger);
	private JTabbedPane tabbedPane ;
	
	private static JTable tblexcell;
	private static JLabel lblborc ;
	private static JLabel lblalacak ;
	private static JLabel lblsatir ;
	private static JLabel lblNewLabel  ;
	private static JComboBox<String> comboBox  ;
	private JFileChooser chooser;
	private static JTable table;
	private static JTable table_1;
	private JTextField textField;
	private static boolean ilk = true;
	public static JSplitPane splitPane ;
	private JTextField txtBORCLU;
	private JTextField txtARAMA;
	private JTextField txtALACAK;
	private JLabel lblunvan_1 ;
	private JLabel lblunvan_2 ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DISTAN_AKTAR frame = new DISTAN_AKTAR();
					
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
	public DISTAN_AKTAR() {
		
		setTitle("TOPLU GIRIS");
		setToolTipText("");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0,0, 1190, 600);
		
				
		splitPane = new JSplitPane();
		splitPane.setDividerSize(1);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 75));
		panel.setMaximumSize(new Dimension(0, 75));
		panel.setLayout(null);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		toolBar_1.setBounds(10, 11, 91, 30);
		panel.add(toolBar_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 tabbedPane.setSelectedIndex(0);
					dosya_oku();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(DISTAN_AKTAR.class.getResource("/ICONLAR/icons8-add-folder-16.png")));
		toolBar_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//**********************
			aciklama_duzelt();
			//**********************
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(DISTAN_AKTAR.class.getResource("/ICONLAR/Windows-Table-icon.png")));
		toolBar_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			hesap_uygula();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(DISTAN_AKTAR.class.getResource("/ICONLAR/icons8-compose-16.png")));
		toolBar_1.add(btnNewButton_2);
		
		txtBORCLU = new JTextField();
		txtBORCLU.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtBORCLU.setBounds(603, 18, 100, 20);
		txtBORCLU.setColumns(12);
		InputMap txtkoduMap = txtBORCLU.getInputMap();
		txtkoduMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_H,KeyEvent.CTRL_MASK ), "foo");
		txtBORCLU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					String[] parts;
					String deger ;
					deger = GLOBAL.setting_oku("CARI_HSPPLN_CAG").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
							HESAP_PLN hsp ;
							getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
							hsp = new HESAP_PLN();
							hsp.show();
							txtBORCLU.setText(oac.hsp_hsp_kodu);
							lblunvan_1.setText(CARI_ISIM_OKU.isim(txtBORCLU.getText())[0]);
							getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
						}
					}
				}
				catch (Exception ex)
				{

				}
			}
		});
		txtBORCLU.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.show(); 
						txtBORCLU.setText( OBS_SIS_2025_ANA_CLASS.hsp_hsp_kodu);
						getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
						lblunvan_1.setText(CARI_ISIM_OKU.isim(txtBORCLU.getText())[0]);
						getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		txtBORCLU.getDocument().addDocumentListener(new DocumentListener() {
		 public void changedUpdate(DocumentEvent e) {
		  
			    	getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
			    	lblunvan_1.setText(CARI_ISIM_OKU.isim(txtBORCLU.getText())[0]);
					getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
				
		  }
		  public void removeUpdate(DocumentEvent e) {
		    
			    	getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
			    	lblunvan_1.setText(CARI_ISIM_OKU.isim(txtBORCLU.getText())[0]);
					getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
				
			  }
		  public void insertUpdate(DocumentEvent e) {
		    
			    	getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
			    	lblunvan_1.setText(CARI_ISIM_OKU.isim(txtBORCLU.getText())[0]);
					getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
				
			  }
			});
		panel.add(txtBORCLU);
		
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setToolTipText("Uygula");
		btnNewButton_3.setIcon(new ImageIcon(DISTAN_AKTAR.class.getResource("/ICONLAR/icons8-approved-16.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borcluUYGULA();
			}
		});
		btnNewButton_3.setBounds(708, 17, 24, 24);
		panel.add(btnNewButton_3);
		
		txtARAMA = new JTextField();
		txtARAMA.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtARAMA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				aciklamaARAMA();
			}
		});
		txtARAMA.getDocument().addDocumentListener(new DocumentListener() {
			 public void changedUpdate(DocumentEvent e) {
				    	getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
				    	aciklamaARAMA();
						getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
			  }
			  public void removeUpdate(DocumentEvent e) {
				    	getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
				    	aciklamaARAMA();
						getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
				  }
			  public void insertUpdate(DocumentEvent e) {
				    	getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
				    	aciklamaARAMA();
						getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
				  }
				});

		txtARAMA.setBounds(160, 46, 287, 20);
		panel.add(txtARAMA);
		txtARAMA.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Arama");
		lblNewLabel_4.setBounds(100, 49, 46, 14);
		panel.add(lblNewLabel_4);
		
		txtALACAK = new JTextField();
		txtALACAK.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtALACAK.setColumns(12);
		txtALACAK.setBounds(945, 18, 100, 20);
		InputMap txtkoduMap1 = txtALACAK.getInputMap();
		txtkoduMap1.put(KeyStroke.getKeyStroke(KeyEvent.VK_H,KeyEvent.CTRL_MASK ), "foo");
		txtALACAK.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.show(); 
						txtALACAK.setText( OBS_SIS_2025_ANA_CLASS.hsp_hsp_kodu);
						getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
						lblunvan_2.setText(CARI_ISIM_OKU.isim(txtALACAK.getText())[0]);
						getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		txtALACAK.getDocument().addDocumentListener(new DocumentListener() {
		 public void changedUpdate(DocumentEvent e) {
			    	getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
			    	lblunvan_2.setText(CARI_ISIM_OKU.isim(txtALACAK.getText())[0]);
					getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
		  }
		  public void removeUpdate(DocumentEvent e) {
			    	getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
			    	lblunvan_2.setText(CARI_ISIM_OKU.isim(txtALACAK.getText())[0]);
					getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
			  }
		  public void insertUpdate(DocumentEvent e) {
			    	getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
			    	lblunvan_2.setText(CARI_ISIM_OKU.isim(txtALACAK.getText())[0]);
					getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
			  }
			});
		txtALACAK.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					String[] parts;
					String deger ;
					deger = GLOBAL.setting_oku("CARI_HSPPLN_CAG").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
							HESAP_PLN hsp ;
							getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
							hsp = new HESAP_PLN();
							hsp.show();
							txtALACAK.setText(oac.hsp_hsp_kodu);
							lblunvan_2.setText(CARI_ISIM_OKU.isim(txtALACAK.getText())[0]);
							getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
						}
					}
				}
				catch (Exception ex)
				{

				}
			}
		});
		panel.add(txtALACAK);
		
		lblunvan_1 = new JLabel("...");
		lblunvan_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblunvan_1.setBounds(603, 49, 267, 14);
		panel.add(lblunvan_1);
		
		lblunvan_2 = new JLabel("...");
		lblunvan_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblunvan_2.setBounds(945, 49, 212, 14);
		panel.add(lblunvan_2);
		
		JButton btnNewButton_3_1 = new JButton("");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alacakliUYGULA();
			}
		});
		btnNewButton_3_1.setToolTipText("Uygula");
		btnNewButton_3_1.setIcon(new ImageIcon(DISTAN_AKTAR.class.getResource("/ICONLAR/icons8-approved-16.png")));
		btnNewButton_3_1.setBounds(1050, 17, 24, 24);
		panel.add(btnNewButton_3_1);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(1);
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		splitPane.setRightComponent(splitPane_1);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(0, 0, 128));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		splitPane_1.setLeftComponent(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Excell", null, scrollPane, null);
		
		
		DefaultTableModel model = new DefaultTableModel() ;
		tblexcell = new JTable(model) ;
		tblexcell.getTableHeader().setReorderingAllowed(false);
		tblexcell.setGridColor(OBS_SIS_2025_ANA_CLASS.gridcolor);
		tblexcell.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
			doldur(tblexcell.getModel().getValueAt(tblexcell.getSelectedRow(), 2).toString(),"B");
			doldur(tblexcell.getModel().getValueAt(tblexcell.getSelectedRow(), 5).toString(),"A");
			getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
		    	}
		    });
	    tblexcell.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    model.addColumn("Tarih", new Date []{ new Date() });
		    model.addColumn("Aciklama", new String []{"deneme"});
		    model.addColumn("Borclu_Hesap", new String []{""});
		    model.addColumn("Borc",new Double [] {new Double( 0 )});
		    model.addColumn("Alacak",new Double [] {new Double( 0 )});
		    model.addColumn("Alacakli_Hesap", new String []{"" });
		    model.addColumn("Evrak_No", new String []{"" });
		   
		    TableColumn col ;
		    int sut ;
		    sut= 0;
		   
		    col = tblexcell.getColumnModel().getColumn(sut);
		    //col.setCellEditor(new COKLU_GIR_TAR_EDITOR(new JTextField()));
		    col.setCellEditor(new JDateChooserEditor(new JCheckBox()));
			col.setHeaderRenderer(new SOLA());
			col.setCellRenderer(new COKLU_GIRIS_TARIH());
			col.setMinWidth(100);
		    
		    sut= 1;
		    col = tblexcell.getColumnModel().getColumn(sut);
			col.setHeaderRenderer(new SOLA());
			col.setMinWidth(500);

			sut= 2;
		    col = tblexcell.getColumnModel().getColumn(sut);
			col.setHeaderRenderer(new SOLA());
			col.setCellEditor(new COKLU_GIRIS_HSP(new JTextField(),"B"));
			col.setMinWidth(100);
			//***************
			DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
		    Font font =new Font("Tahoma", Font.BOLD, 11);
		    @Override
		    public Component getTableCellRendererComponent(JTable table,
		            Object value, boolean isSelected, boolean hasFocus,
		            int row, int column) {
		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
	                row, column);
			        setFont(font);
			        setForeground(new Color(178, 34, 34));
			        return this;
			    }
			};
			col.setCellRenderer(r);
			 //***
		    sut= 3;
		    col = tblexcell.getColumnModel().getColumn(sut);
		    col.setMinWidth(120);
		    col.setHeaderRenderer(new SAGA());
		    col.setCellEditor( new DoubleEditor(2) );
		    col.setCellRenderer(new TABLO_RENDERER(2,false));
		      
		    sut= 4;
			col = tblexcell.getColumnModel().getColumn(sut);
			col.setMinWidth(120);
			col.setHeaderRenderer(new SAGA());
			col.setCellEditor( new DoubleEditor(2) );
			col.setCellRenderer(new TABLO_RENDERER(2,false));
			
			sut= 5;
		    col = tblexcell.getColumnModel().getColumn(sut);
			col.setHeaderRenderer(new SOLA());
			col.setCellEditor(new COKLU_GIRIS_HSP(new JTextField(),"A"));
			//***************
			DefaultTableCellRenderer rr = new DefaultTableCellRenderer() {
		    Font font =new Font("Tahoma", Font.BOLD, 11);
			    @Override
			    public Component getTableCellRendererComponent(JTable table,
			            Object value, boolean isSelected, boolean hasFocus,
			            int row, int column) {
			        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
			                row, column);
			        setFont(font);
			        setForeground(new Color(0, 128, 128));
			        return this;
			    }

			};
			col.setCellRenderer(rr);
			col.setMinWidth(100);
			
			sut= 6;
		    col = tblexcell.getColumnModel().getColumn(sut);
			col.setHeaderRenderer(new SOLA());
			col.setMinWidth(100);
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
			 //***
		    tblexcell.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		    tblexcell.setRowHeight(22);
		    //**
		    
		    JTableHeader th = tblexcell.getTableHeader();
		    Dimension dd = tblexcell.getPreferredSize();
		    dd.height = 30;
		    th.setPreferredSize(dd); 
		    th.repaint();
		scrollPane.setViewportView(tblexcell);
		//**********
		JSplitPane splitPane_2 = new JSplitPane();
		tabbedPane.addTab("Temalar", null, splitPane_2, null);
		
		splitPane_2.setDividerSize(1);
		splitPane_2.setResizeWeight(0);
		splitPane_2.setDividerLocation(350);
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_2.setRightComponent(splitPane_3);
		
		splitPane_3.setDividerSize(1);
		splitPane_3.setResizeWeight(1.0);
		splitPane_3.setDividerLocation(450);
		
		JPanel panel_2 = new JPanel();
		splitPane_3.setLeftComponent(panel_2);
		panel_2.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setEditable(true);
		comboBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (comboBox.getItemCount()<1) return;
			if (comboBox.getSelectedItem() == null ) return;
				if (ilk)
				{
					ilk = false ;
				}
				else
				{
					try {
				hesap_doldur();
				temadoldur();
				textField.setText(oac.tCR.tema_anahesap(comboBox.getSelectedItem().toString()));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox.setBounds(76, 73, 156, 22);
		panel_2.add(comboBox);
	    
		lblNewLabel = new JLabel("...");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(76, 147, 345, 14);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tema");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 77, 46, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ana Hesap");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 119, 61, 14);
		panel_2.add(lblNewLabel_2);
		
		JToolBar toolBar_2 = new JToolBar();
		toolBar_2.setFloatable(false);
		toolBar_2.setBounds(10, 2, 61, 25);
		panel_2.add(toolBar_2);
		
		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		 if (comboBox.getSelectedItem().toString().equals("")) return ;
	     try
		    {
	  
	    	 GuiUtil.setWaitCursor(splitPane,true);
	       //**************tema sil
	    	oac.tCR.tema_sil(comboBox.getSelectedItem().toString());
	       //********** KAYIT ********
	    	 oac.tCR.tema_ana_hes_kayit(comboBox.getSelectedItem().toString(), textField.getText());
		       for(int i = 0 ; i  <= table.getRowCount() -1  ; i ++) 
		       {
		    	   oac.tCR.tema_tem_hes_kayit(comboBox.getSelectedItem().toString(),
		    		   table.getModel().getValueAt(i, 0).toString(),
		    		   table.getModel().getValueAt(i, 1).toString());
		       }
           //*******HESAPLAR
		       for( int i = 0 ; i  <= table_1.getRowCount() -1  ; i ++) 
		       {
		    	   oac.tCR.tema_hes_kayit(comboBox.getSelectedItem().toString(),
	        		   table_1.getModel().getValueAt(i, 0).toString(),
	        		   table_1.getModel().getValueAt(i, 1).toString());
				}
	        //***************
	       ilk = true ;
	       te_sifirla();
	       tema_doldur();
	       hesap_doldur();
		   temadoldur();
	     
	  	 GuiUtil.setWaitCursor(splitPane,false);
		    }
			catch (Exception ex)
			{
				 GuiUtil.setWaitCursor(splitPane,false);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Distan Aktar", JOptionPane.ERROR_MESSAGE);   
			}
		}
		});
		btnNewButton_6.setIcon(new ImageIcon(DISTAN_AKTAR.class.getResource("/ICONLAR/save.png")));
		toolBar_2.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			 if (comboBox.getSelectedItem().toString().equals("")) return ;
	    try 
	    {
			int g =  JOptionPane.showOptionDialog( null,  "Tema Silme Islemi......Silme ? ", "Cari Coklu Kayit",   JOptionPane.YES_NO_OPTION,
	   		JOptionPane.QUESTION_MESSAGE,null, oac.options, oac.options[1]); 
	        if(g != 0 ) { return;	}	        
	        getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
	       oac.tCR.tema_sil(comboBox.getSelectedItem().toString());
	        ilk = true ;
	        tema_doldur();
	        getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
		}
	    catch (Exception ex)
	    {
	    	 getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
	 		JOptionPane.showMessageDialog(null, ex.getMessage(),  "Distan Aktar", JOptionPane.ERROR_MESSAGE);   
	    }
			}
		});
		btnNewButton_7.setIcon(new ImageIcon(DISTAN_AKTAR.class.getResource("/ICONLAR/sil.png")));
		toolBar_2.add(btnNewButton_7);
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) 
			{
				HESAP_PLN hsp ;
				try {
					hsp = new HESAP_PLN();
					hsp.show();
					textField.setText( OBS_SIS_2025_ANA_CLASS.hsp_hsp_kodu);
					getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
					//lblNewLabel.setText(isimoku(textField.getText()));
					getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			}
		});
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setColumns(10);
		textField.setBounds(76, 116, 156, 20);
		textField.setDocument(new JTextFieldLimit(12));
		textField.getDocument().addDocumentListener(new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent e) {
				getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
					lblNewLabel.setText(CARI_ISIM_OKU.isim(textField.getText())[0]);
				getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
			}
		@Override
		public void removeUpdate(DocumentEvent e) {
				getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
					lblNewLabel.setText(CARI_ISIM_OKU.isim(textField.getText())[0]);
				getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
			}
		@Override
		public void changedUpdate(DocumentEvent e) {
				getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
					lblNewLabel.setText(CARI_ISIM_OKU.isim(textField.getText())[0]);
				getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
			}
		});
		panel_2.add(textField);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 30, 439, 2);
		panel_2.add(separator);
		
		JSplitPane splitPane_5 = new JSplitPane();
		splitPane_5.setDividerSize(1);
		splitPane_5.setResizeWeight(0.0);
		splitPane_5.setDividerLocation(30);
		
		splitPane_5.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setRightComponent(splitPane_5);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Karsi Hesap Kodlari", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		splitPane_5.setRightComponent(scrollPane_2);
		
		DefaultTableModel mdl = new DefaultTableModel() ;
		table_1 = new JTable(mdl);
		table_1.setGridColor(OBS_SIS_2025_ANA_CLASS.gridcolor);
		table_1.setCellSelectionEnabled(true);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		 mdl.addColumn("Aranacak", new String []{null});
		 mdl.addColumn("Hesap Kodu", new String []{null});
		 TableColumn colon ;
		    int sutun =0;
		    sutun= 0;
		    colon = table_1.getColumnModel().getColumn(sutun);
			colon.setHeaderRenderer(new SOLA());
			colon.setMinWidth(240);
			sutun= 1;
		    colon = table_1.getColumnModel().getColumn(sutun);
			colon.setHeaderRenderer(new SOLA());
			colon.setCellEditor(new COKLU_GIRIS_HSP(new JTextField(),"C"));
			colon.setMinWidth(95);
			table_1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			table_1.setRowHeight(22);
		    //**
		    th = table_1.getTableHeader();
		    dd = table_1.getPreferredSize();
		    dd.height = 30;
		    th.setPreferredSize(dd); 
		    th.repaint();		
		scrollPane_2.setViewportView(table_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_5.setMinimumSize(new Dimension(0, 30));
		panel_5.setMaximumSize(new Dimension(0, 30));
		splitPane_5.setLeftComponent(panel_5);
		panel_5.setLayout(null);
		
		JToolBar toolBar_3 = new JToolBar();
		toolBar_3.setFloatable(false);
		toolBar_3.setBounds(10, 2, 64, 25);
		panel_5.add(toolBar_3);
		
		JButton btnNewButton_8 = new JButton("");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sol_tablo_ilave(table_1);
			}
		});
		btnNewButton_8.setIcon(new ImageIcon(DISTAN_AKTAR.class.getResource("/ICONLAR/yeni.png")));
		toolBar_3.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sol_tablo_sil(table_1,table_1.getSelectedRow());
			}
		});
		btnNewButton_9.setIcon(new ImageIcon(DISTAN_AKTAR.class.getResource("/ICONLAR/icons8-reduce-16.png")));
		toolBar_3.add(btnNewButton_9);
		
		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setDividerSize(1);
		splitPane_4.setResizeWeight(0);
		splitPane_4.setDividerLocation(30);
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_2.setLeftComponent(splitPane_4);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new TitledBorder(null, "Izahat Kismi Degisim Parametreleri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane_4.setRightComponent(scrollPane_1);
		
		DefaultTableModel mdlt = new DefaultTableModel() ;
		table = new JTable(mdlt);
		table.setGridColor(OBS_SIS_2025_ANA_CLASS.gridcolor);
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			 mdlt.addColumn("Aranacak", new String []{null});
			 mdlt.addColumn("Yazilacak", new String []{null});
			 
			    sutun= 0;
			    colon = table.getColumnModel().getColumn(sutun);
				colon.setHeaderRenderer(new SOLA());
				colon.setMinWidth(170);
				sutun= 1;
			    colon = table.getColumnModel().getColumn(sutun);
				colon.setHeaderRenderer(new SOLA());
				colon.setMinWidth(150);
				table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
				table.setRowHeight(22);
			    th = table.getTableHeader();
			    dd = table.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
			    th.repaint();		
				
		scrollPane_1.setViewportView(table);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_4.setMinimumSize(new Dimension(0, 30));
		panel_4.setMaximumSize(new Dimension(0, 30));
		panel_4.setLayout(null);
		splitPane_4.setLeftComponent(panel_4);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(10, 2, 64, 25);
		panel_4.add(toolBar);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sol_tablo_ilave(table);
			}
		});
		btnNewButton_4.setIcon(new ImageIcon(DISTAN_AKTAR.class.getResource("/ICONLAR/yeni.png")));
		toolBar.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sol_tablo_sil(table,table.getSelectedRow());
			}
		});
		btnNewButton_5.setIcon(new ImageIcon(DISTAN_AKTAR.class.getResource("/ICONLAR/icons8-reduce-16.png")));
		toolBar.add(btnNewButton_5);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane_1.setRightComponent(panel_3);
		panel_3.setMinimumSize(new Dimension(0, 30));
		panel_3.setMaximumSize(new Dimension(0, 30));
		panel_3.setLayout(null);
		
		lblborc = new JLabel("...");
		lblborc.setForeground(new Color(178, 34, 34));
		lblborc.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblborc.setBounds(606, 11, 284, 14);
		panel_3.add(lblborc);
		
		lblalacak = new JLabel("...");
		lblalacak.setForeground(new Color(0, 128, 128));
		lblalacak.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblalacak.setBounds(943, 11, 262, 14);
		panel_3.add(lblalacak);
		
		JLabel lblNewLabel_3 = new JLabel("Satir Sayisi:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(10, 11, 85, 14);
		panel_3.add(lblNewLabel_3);
		
		lblsatir = new JLabel("0");
		lblsatir.setForeground(new Color(0, 0, 128));
		lblsatir.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblsatir.setBounds(100, 11, 56, 14);
		panel_3.add(lblsatir);
		GRID_TEMIZLE.grid_temizle(tblexcell);
		
			 getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
			try {
				te_sifirla();
				ilk = true;
				tema_doldur();
				hesap_doldur();
				temadoldur();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
	}
	public static void doldur(String kod,String tur)
	{
		try
		{
			if (tur.equals("B"))
			{
				lblborc.setText(CARI_ISIM_OKU.isim(kod)[0]);
			}
			else
			{
				lblalacak.setText(CARI_ISIM_OKU.isim(kod)[0]);
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Distan Aktar", JOptionPane.ERROR_MESSAGE);   
		}
	}
	
	private void dosya_oku() throws IOException
	{
		try
		{
			GuiUtil.setWaitCursor(splitPane,true);
		UIManager.put("FileChooser.cancelButtonText", "Vazgec");
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("Dosya Seciniz");
	    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    chooser.setApproveButtonText("Dosya Sec");
	    chooser.setApproveButtonToolTipText("Dosya Sec");
	    chooser.addChoosableFileFilter(new FileNameExtensionFilter("Excell Dosyalari", "xls", "xlsx"));
	    chooser.setApproveButtonMnemonic('s');
		GuiUtil.setWaitCursor(splitPane,false);
	    Workbook workbook = null ;
	    FileInputStream fis = null ;
	    Sheet sheet = null;
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
	    	File excelFile = chooser.getSelectedFile();
	    	String path = excelFile.getAbsolutePath();
	    	  if(path.endsWith("xls"))
	    	  {
	    		  fis = new FileInputStream(excelFile); 
	    		  workbook = new HSSFWorkbook(fis );
	    	
	    		  HSSFFont wbFont ;
	    		  wbFont=  (HSSFFont) workbook.createFont();
	    		  wbFont.setCharSet(HSSFFont.ANSI_CHARSET); //Your Character encoding goes in the parameter
	    		  sheet = (HSSFSheet) workbook.getSheetAt(0);
	    	  }
	    	  else if(path.endsWith("xlsx"))
	    	  {
	    		  fis = new FileInputStream(excelFile); 
	    		  workbook = new XSSFWorkbook(fis);
		    		  XSSFFont wbFont ;
	    		  wbFont=  (XSSFFont) workbook.createFont();
	    		  wbFont.setCharSet(XSSFFont.ANSI_CHARSET); //Your Character encoding goes in the parameter
	    		  sheet = (XSSFSheet) workbook.getSheetAt(0);
	    	  }
		}
	    else
	    {
	    	return ;
	    }
		GuiUtil.setWaitCursor(splitPane,true);
		 Iterator<Row> rowIt = sheet.iterator();
		 String izahat = "" ;
		 Double borc = (double) 0;
		 Double alacak = (double) 0 ;
		 GRID_TEMIZLE.grid_temizle(tblexcell);
		 DefaultTableModel defaultModel = (DefaultTableModel) tblexcell.getModel();
			Row row = rowIt.next();
		    while(rowIt.hasNext()) 
		    {
		      row = rowIt.next();
		      izahat =  row.getCell(1).getStringCellValue();
		      if (row.getCell(2) == null)
		      {
		    	borc = (double) 0 ;  
		      } 
		      else
		      {
		    	  if (row.getCell(2).getCellType().toString().equals("STRING"))
		    	  {
		    		  borc = (double) 0 ;
		    	  }
		    	  else
		    	  {
		    		  borc = row.getCell(2).getNumericCellValue(); 
		    	  }
		      }
		      if (row.getCell(3) == null)
		      {
		    	alacak = (double) 0 ;  
		      } 
		      else
		      {
		    	  if (row.getCell(3).getCellType().toString().equals("STRING"))
		    	  {
		    		  alacak = (double) 0 ;
		    	  }
		    	  else
		    	  {
		    		  alacak = row.getCell(3).getNumericCellValue();  
		    	  }
		      }
		      Date tar =new Date();
		      if (row.getCell(0).getCellType().toString().equals("NUMERIC"))
		       {
		    	 tar = row.getCell(0).getDateCellValue() ;
	    	     }
		      else
		      {
		    	    	String sDate1= row.getCell(0).toString();
						tar = new SimpleDateFormat("dd.MM.yy").parse(sDate1);
		      }
		      defaultModel.addRow(new Object[]{tar, izahat  ,"", borc ,alacak,"",""});
		    }
	    workbook.close();
	    fis.close();
	  GuiUtil.setWaitCursor(splitPane,false);
	    lblsatir.setText(Integer.toString(tblexcell.getRowCount()));
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Distan Aktar", JOptionPane.ERROR_MESSAGE);   
		}
	}
	public static void satir_sil()
	{
		DefaultTableModel model = (DefaultTableModel) tblexcell.getModel();
		model.removeRow(tblexcell.getSelectedRow());
		tblexcell.repaint();
		lblsatir.setText(Integer.toString(tblexcell.getRowCount()));
	}
	private void tema_doldur() throws ClassNotFoundException, SQLException
	{
		try {
			te_sifirla();
		ResultSet rs = null ;
	
		rs = oac.tCR.tema_oku();
		
		if (!rs.isBeforeFirst() ) { 
			comboBox.addItem("");
			textField.setText("");
			return ;
		} 
		while(rs.next())
		{
			comboBox.addItem(rs.getString("TEMA").toString());
		}
		oac.tCR.conn.close();
		comboBox.addItem("");
		textField.setText(Tema_Cari.tema_anahesap(comboBox.getSelectedItem().toString()));
		table.enable(true);
		table_1.enable(true);
	} catch (Exception ex) {
		JOptionPane.showMessageDialog(null, ex.getMessage(),  "OBS SISTEM", JOptionPane.ERROR_MESSAGE);   
	}
	}
	private void sol_tablo_ilave(JTable tbl)
	{
		 DefaultTableModel mdl = (DefaultTableModel) tbl.getModel();
		 mdl.addRow(new Object[]{"", ""});
		 tbl.repaint();
	}
	private void sol_tablo_sil(JTable tbl,int sat)
	{
		 DefaultTableModel mdll = (DefaultTableModel) tbl.getModel();
		 mdll.removeRow(sat);
		 tbl.repaint();
	}
	private void hesap_doldur() throws ClassNotFoundException, SQLException 
	{
		try
		{
		if (comboBox.getItemCount() == 0) return ;
		ResultSet rs = null ;
		GRID_TEMIZLE.grid_temizle(table_1);
		rs =  oac.tCR.ttema_oku(comboBox.getSelectedItem().toString());
		if (!rs.isBeforeFirst() ) {  
			return;
		} 
		DefaultTableModel mdl = (DefaultTableModel) table_1.getModel();
		 while(rs.next()) 
		 {
			 mdl.addRow(new Object[]{rs.getString("ARANACAK"),rs.getString("HESAP_KODU")}); 
		 }
		 oac.tCR.conn.close();
		}
		catch (Exception ex)
		{
		 getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Distan Aktar", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void temadoldur() 
	{
		try
		{
		if (comboBox.getItemCount() == 0) return ;
		ResultSet rs = null ;
		GRID_TEMIZLE.grid_temizle(table);
		rs =  oac.tCR.temalar_oku(comboBox.getSelectedItem().toString());
		if (!rs.isBeforeFirst() ) {  
			return;
		} 
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		 while(rs.next()) 
		 {
			 mdl.addRow(new Object[]{rs.getString("ARANACAK"),rs.getString("YAZILACAK")}); 
		 }
		 oac.tCR.conn.close();
		}
		catch (Exception ex)
		{
			 getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);
				JOptionPane.showMessageDialog(null, ex.getMessage(),  "Distan Aktarma", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void te_sifirla()
		{
		comboBox.removeAllItems();
		GRID_TEMIZLE.grid_temizle(table);
		GRID_TEMIZLE.grid_temizle(table_1);
		table.enable(false);
		table_1.enable(false);
		}

	
	private void aciklama_duzelt()
	{
		if ( tblexcell.getRowCount() == 0 ) return ;
		Runnable runner = new Runnable()
	    { public void run() {
	    	 try {
	    	String iki,ass ,degisen= "" ;
	    	DefaultTableModel model = (DefaultTableModel) tblexcell.getModel();
	    	getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);
	    	Progres_Bar_Temizle();
	    	OBS_MAIN.progressBar.setMaximum(tblexcell.getRowCount() - 1);
	      for( int i = 0 ; i <= tblexcell.getRowCount() - 1;i ++)
	   		{
				Progres_Bar(tblexcell.getRowCount() - 1, i);
               iki =  model.getValueAt(i , 3).toString();
               if (! iki.equals("") &&  Double.parseDouble(iki) != 0 ) 
               {
                   model.setValueAt(textField.getText(), i, 2);
	           }
	           else
	           {
	                model.setValueAt(textField.getText(), i, 5);
	                iki = "" ;
	           }
	   		}
	      Progres_Bar_Temizle();
	      OBS_MAIN.progressBar.setStringPainted(true);
	      OBS_MAIN.progressBar.setMaximum(table.getRowCount() - 1);
	      DefaultTableModel mdl = (DefaultTableModel) table.getModel();
	       for(int  t = 0 ; t <= table.getRowCount() - 1;t ++) 
	       {   
				Progres_Bar(table.getRowCount() - 1, t);
	    	   for( int  i = 0 ; i <= tblexcell.getRowCount() - 1;i ++)
	    	   {
    		   ass =  model.getValueAt(i , 1).toString() ;
	 		   degisen= ass.replace( mdl.getValueAt(t , 0).toString().equals("vbLf") ? "\n" :mdl.getValueAt(t , 0).toString(),  mdl.getValueAt(t ,1).toString());
  			   model.setValueAt(degisen, i, 1);
	    	   ass = "";
	           }
	      }
	     Thread.currentThread().isInterrupted();
	     getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);   
	    	 }  catch (Exception ex) {
		    		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Cari Distan Aktarma", JOptionPane.ERROR_MESSAGE);
				}
	    	  Progres_Bar_Temizle();
	    }
	    };
	    //// Progress Bar
	    Thread t = new Thread(runner, "Code Executer");
	    t.start();
	}
	private void hesap_uygula()
	{
		if ( tblexcell.getRowCount() == 0 ) return ;
		Runnable runner = new Runnable()
		{ public void run() {
			//************
			try {
				String iki = "",bir,uc = "" ;
				int sat = 0 ;
				DefaultTableModel mdl = (DefaultTableModel) table_1.getModel();
				//DefaultTableModel model = (DefaultTableModel) tblexcell.getModel();
				getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.WAIT_CURSOR);  
				Progres_Bar_Temizle();
				OBS_MAIN.progressBar.setStringPainted(true);
				OBS_MAIN.progressBar.setMaximum(table_1.getRowCount() - 1);
				for(int  t = 0 ; t <= table_1.getRowCount() - 1;t ++) 
				{
					Progres_Bar(table_1.getRowCount() - 1, t);
					bir =  mdl.getValueAt(t , 0).toString() ;
					TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tblexcell.getModel())); 
					//sorter = new TableRowSorter<TableModel>(model);
					RowFilter<TableModel, Object> rf = null;
					rf = RowFilter.regexFilter("(?i)" + Pattern.quote(bir) , 1);
					sorter.setRowFilter(rf);
					tblexcell.setRowSorter(sorter);
					if ( tblexcell.getRowCount() > 0 )
					{
						iki =  mdl.getValueAt(t , 1).toString() ;
						for(int  r = 0 ; r <= tblexcell.getRowCount() - 1;r ++) 
						{
							sat = tblexcell.getRowSorter().convertRowIndexToModel(r);
							uc = tblexcell.getModel().getValueAt(sat, 3).toString();
							if (! iki.equals("") &&   Double.parseDouble(uc) != 0 ) 
							{
								if( tblexcell.getModel().getValueAt(sat, 2).toString().equals("") ) 
								{
									tblexcell.getModel().setValueAt(iki,sat, 2) ;
								}
								else
								{
									tblexcell.getModel().setValueAt(iki,sat, 5) ;
								}
							}
							else
							{
								if( tblexcell.getModel().getValueAt(sat, 5).toString().equals("")) 
								{
									tblexcell.getModel().setValueAt(iki,sat, 5) ;
								}
								else
								{
									tblexcell.getModel().setValueAt(iki,sat, 2) ;
								}
							}
						}
					}
					Thread.sleep(1);
					tblexcell.setRowSorter(null);
				}
				getContentPane().setCursor(OBS_SIS_2025_ANA_CLASS.DEFAULT_CURSOR);  
				Thread.currentThread().isInterrupted();
				Progres_Bar_Temizle();
			} 
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null,  ex.getMessage(), "Cari Distan Aktarma", JOptionPane.ERROR_MESSAGE);
			}
//***********			
		}
		};
		//// Progress Bar
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
	public static void kaydet_carii()
	{
		try
		{
		DefaultTableModel model = (DefaultTableModel) tblexcell.getModel();
        if (model.getRowCount() == 0) return ;
        int  kon = 0 ;
        for(int  i = 0 ;i <= model.getRowCount() - 1; i ++)
        {
            if( model.getValueAt(i, 2).toString().equals("") || model.getValueAt(i, 5).toString().equals("") )
            {
                kon += 1 ;
            }
        }
        if ( kon > 0 )
        {
            JOptionPane.showMessageDialog(null, "Bos Hesap Kodlari mevcut .... Satir Sayisi:" + kon); 
            return;
        }
        int g = JOptionPane.showOptionDialog( null,  "Kayit Baslamasi...Kayit Sayisi:" + model.getRowCount(), "Cari Coklu Kayit",   JOptionPane.YES_NO_OPTION,
    	   		JOptionPane.QUESTION_MESSAGE,null, oac.options, oac.options[1]); 
    	        if(g != 0 ) { return;	}	
        cari_kaydet();
		}
    catch (Exception ex)
		{
		JOptionPane.showMessageDialog(null, ex.getMessage(),  "Distan aktar", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void aciklamaARAMA()
	{
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tblexcell.getModel())); 
		RowFilter<TableModel, Object> rf = null;
		if(txtARAMA.getText().toString().equals(""))
		{
			tblexcell.setRowSorter(null);
		}
		else
		{
			rf = RowFilter.regexFilter("(?iu)" +txtARAMA.getText().toString() , 1);
		}
		sorter.setRowFilter(rf);
		tblexcell.setRowSorter(sorter);
	}
	private void borcluUYGULA()
	{
		if ( tblexcell.getRowCount() > 0 )
		{
			int sat = 0 ;
			for(int  r = 0 ; r <= tblexcell.getRowCount() - 1;r ++) 
			{
				if (tblexcell.getRowSorter() !=  null)
				{
					sat = tblexcell.getRowSorter().convertRowIndexToModel(r);
				}
				else
				{
					sat = r ;
				}
				
				tblexcell.getModel().setValueAt(txtBORCLU.getText(),sat, 2) ;
			} 
		}
		txtARAMA.setText("");
	}
	private void alacakliUYGULA()
	{
		if ( tblexcell.getRowCount() > 0 )
		{
			int sat = 0 ;
			for(int  r = 0 ; r <= tblexcell.getRowCount() - 1;r ++) 
			{
				if (tblexcell.getRowSorter() !=  null)
				{
					sat = tblexcell.getRowSorter().convertRowIndexToModel(r);
				}
				else
				{
					sat = r ;
				}
				tblexcell.getModel().setValueAt(txtALACAK.getText(),sat, 5) ;
			} 
		}
		txtARAMA.setText("");
	}

	public static void cari_kaydet()
	{
		//
		Runnable runner = new Runnable()
	    { public void run() {
	    //
		try
		{
		GuiUtil.setWaitCursor(splitPane,true);
		 Progres_Bar_Temizle();
		 OBS_MAIN.progressBar.setStringPainted(true);
		 DefaultTableModel model = (DefaultTableModel) tblexcell.getModel();
         int  evr_no  = 0;
         String strg  = "";
         evr_no = c_Access.coklu_cari_fisno_al(tblexcell.getRowCount());
         double asdd = 0.00 ;
         double dsa = 0.00 ;
         double dds = 0.00 ;
          OBS_MAIN.progressBar.setMaximum(tblexcell.getRowCount() - 1);
		  for(int  i = 0 ;i <= tblexcell.getRowCount() - 1; i ++)
         {
        	Progres_Bar(tblexcell.getRowCount() - 1, i);
        	String iki = tblexcell.getModel().getValueAt(i, 3).toString();
            if (! iki.equals("") &&   Double.parseDouble(iki) != 0 ) 
              {
                 dds = Double.parseDouble( model.getValueAt(i, 3).toString());
                 dsa = 0;
             }
             else
             {
                 dsa = Double.parseDouble( model.getValueAt(i, 4).toString());
                 dds = 0;
             }
             asdd = dds + dsa;
             if (model.getValueAt(i, 1).toString().length() > 100)
             {
             strg = model.getValueAt(i, 1).toString().substring(0, 100);
             }
             else
             {
            	 strg = model.getValueAt(i, 1).toString();
             }
         	String strDate = TARIH_CEVIR.dateFormater(model.getValueAt(i , 0).toString() , "yyyy.MM.dd HH:mm:ss.sss", "EEE MMM dd kk:mm:ss zzzz yyyy" ) ;
			String mesaj = "A. Hes:" +  model.getValueAt(i, 2).toString() + " Tut:" + asdd  +
					" B. Hes:"+  model.getValueAt(i, 5).toString() + " Tut:" +asdd;
			if( mesaj.length() + strg.length() <= 95)
			{
				mesaj = mesaj + " Msj:" + strg ;
			}
			else
			{
				mesaj = mesaj + " Msj:" + strg.substring(0, 95  -(mesaj.length())) ;
			}
		
		dEKONT_BILGI dBilgi = new dEKONT_BILGI();
		dBilgi.setbHES(model.getValueAt(i, 2).toString());
		dBilgi.settAR(strDate);
		dBilgi.seteVRAK(evr_no);
		dBilgi.setbCINS("");
		dBilgi.setbKUR(1);
		dBilgi.setbORC(asdd);
		dBilgi.setaHES(model.getValueAt(i, 5).toString());
		dBilgi.setaCINS("");
		dBilgi.setaKUR(1);
		dBilgi.setaLACAK(asdd);
		dBilgi.setiZAHAT(strg);
		dBilgi.setkOD("");
		dBilgi.setuSER( GLOBAL.KULL_ADI);
       	c_Access.cari_dekont_kaydet(dBilgi,	mesaj,String.valueOf(evr_no) ,	BAGLAN_LOG.cariLogDizin);
        model.setValueAt(evr_no,i, 6);
        evr_no += 1;
        asdd = 0;
        dds = 0;
        dsa = 0;
         }
		  GuiUtil.setWaitCursor(splitPane,false);
         Thread.currentThread().isInterrupted();
         Progres_Bar_Temizle();
         JOptionPane.showMessageDialog(null, "Disardan Aktarma Cari Hesaba Basari ile Kaydedilmistir...." + System.lineSeparator()  +  System.lineSeparator() +  "Evrak No:" +  model.getValueAt(0, 6 ).toString() + " / " + model.getValueAt(model.getRowCount() -1, 6 ).toString() ); 
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Coklu Giris", JOptionPane.ERROR_MESSAGE);
		}
		//// Progress Bar
		    }
	    };
	    Thread t = new Thread(runner, "Code Executer");
	    t.start();
	    //
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
//IIf(RG2.Rows(t).Cells(0).Value.ToString = "vbLf", vbLf, RG2.Rows(t).Cells(0).Value.ToString), RG2.Rows(t).Cells(1).Value.ToString)
//System.lineSeparator()
//if (HSSFDateUtil.isCellDateFormatted(row.getCell(0))) {
//  System.out.println ("Row No.: " + row.getRowNum ()+ " " + 
//             row.getCell(0).getDateCellValue());
//     }

//new Object[]{row.getCell(0).getDateCellValue()

//if(comboBox_1.getSelectedItem().toString().equals("Bos Olanlar"))
//{
//	rf = RowFilter.regexFilter("^\\s*$"  , 2);
//}
//else if(comboBox_1.getSelectedItem().toString().equals("Bos Olmayanlar"))
//{
//	rf = RowFilter.regexFilter("(?!$|\\s+).*"  , 2);
//}
//else if(comboBox_1.getSelectedItem().toString().equals("Filtre Yok"))
//{
//	tblexcell.setRowSorter(null);
//}



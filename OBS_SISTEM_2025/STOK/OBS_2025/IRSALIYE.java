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
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JSplitPane;
 
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
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

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.DoubleEditor;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.MaterialTabbed;
import OBS_C_2025.NextCellActioin;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.dEKONT_BILGI;
import OBS_C_2025.lOG_BILGI;
import raven.toast.Notifications;

@SuppressWarnings({"serial","static-access","unused"})
public class IRSALIYE extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(OBS_SIS_2025_ANA_CLASS._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	static CARI_ACCESS c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	
	private ArrayList<String> listSomeString = new ArrayList<String>();
	private ArrayList<String> listBarkod =  new ArrayList<String>();
	private ArrayList<String> listdepo = null ;
	private static Obs_TextFIeld textField;
	private static Obs_TextFIeld txtcari;
	private static Obs_TextFIeld txtadres;
	private static Obs_TextFIeld txtdoviz;
	private Obs_TextFIeld textField_4;
	private static Obs_TextFIeld textField_5;
	private static Obs_TextFIeld textField_6;
	private Obs_TextFIeld textField_7;
	public static Obs_TextFIeld textField_8;
	private static Obs_TextFIeld textField_9;
	private static Obs_TextFIeld textField_10;
	private static JTable table;
	private static JDateChooser dtc ;
	private static JDateChooser dateChooser ;
	private static JDateChooser dateChooser_1;
	private static JFormattedTextField txtkur ;
	private static JLabel lblNewLabel_12 ;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_6 ;
	private static JLabel lblNewLabel_13 ;
	private static JLabel label ;
	private static JLabel label_1 ;
	private static JLabel label_2 ;
	private static JLabel label_3 ;
	private static JLabel label_4 ;
	private static JLabel label_6 ;
	private static JLabel label_7 ;
	private static JLabel label_8 ;
	
	private static JLabel lblmiktar;
	private static JLabel lbltutar;
	private static JLabel lblNewLabel_20;
	private static JLabel lblNewLabel_17;
	private static JComboBox<String> cmbanagrup ;
	private static JComboBox<String> cmbaltgrup ;
	private static JComboBox<String> cmbfiat ;
	private static JComboBox<String> cmbozkod ;
	private static JComboBox<String> cmbcins;
	private static  String tar = "" ;

	private static  String fattar = "" ;
	private static  String sevktar = "" ;
	private static boolean yeni_fat = false;
	private static Obs_TextFIeld textField_1;
	private JSplitPane splitPane;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("removal")
	public IRSALIYE() {
		setTitle("IRSALIYE		- SATIS");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);

		setBounds(0, 0,1175,800);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(IRSALIYE.class.getResource("/ICONLAR/icons8-questionnaire-30.png")), 16, 16));//
		splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 135));
		panel.setMaximumSize(new Dimension(0, 135));
		panel.setLayout(new BorderLayout(0, 0));
		splitPane.setLeftComponent(panel);
		
		MaterialTabbed tabbedPane_1 = new MaterialTabbed();
		//tabbedPane_1.setForeground(new Color(0, 0, 128));
		tabbedPane_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(tabbedPane_1, BorderLayout.CENTER);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(null));
		tabbedPane_1.addTab("Irsaliye Bilgileri", null, panel_2, null);
		panel_2.setLayout(null);

		
		JLabel lblNewLabel_1 = new JLabel("Irsaliye No");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 11, 69, 14);
		panel_2.add(lblNewLabel_1);
		
		textField = new Obs_TextFIeld(10);

		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					try {
						getContentPane().setCursor(oac.WAIT_CURSOR);
					int sno = 0 ;
					 if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
				        {
						 
				             sno  = f_Access.irsaliye_no_al("C") ;
				    		
				        }
					 else
					 {
						 
				             sno  = f_Access.irsaliye_no_al("G") ;
				    		
					 }
					
					int kj = 0 ;
					kj = 10 - Integer.toString(sno).length() ;
					String str_ = StringUtils.repeat("0", kj)   + Integer.toString(sno);
					
					textField.setText(str_.equals("0000000000") ? "0000000001":str_);
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
					}
					catch (Exception ex)
					{
						getContentPane().setCursor(oac.DEFAULT_CURSOR);
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,  "Irsaliye Numaralarinda onceden harf ve rakkam kullanildigindan otomatik numara verilemez....");
						// JOptionPane.showMessageDialog(null,  "Irsaliye Numaralarinda onceden harf ve rakkam kullanildigindan otomatik numara verilemez...."); 	
					}
				}
			}
		});
		textField.getDocument().addDocumentListener(new DocumentListener() {
			
			  public void changedUpdate(DocumentEvent e) {
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
		        // TextField is added to its parent => request focus in Event Dispatch Thread
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	textField.requestFocusInWindow();
		            }
		        });
		    }
		});
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.setBounds(89, 8, 110, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Cari Hesap");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 36, 69, 14);
		panel_2.add(lblNewLabel_2);
		
		txtcari = new Obs_TextFIeld(12);
	
		txtcari.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  lblNewLabel_3.setText(CARI_ISIM_OKU.isim(txtcari.getText())[0]);
			  }
			  public void removeUpdate(DocumentEvent e) {
				  lblNewLabel_3.setText(CARI_ISIM_OKU.isim(txtcari.getText())[0]);
			  }
			  public void insertUpdate(DocumentEvent e) {
				  lblNewLabel_3.setText(CARI_ISIM_OKU.isim(txtcari.getText())[0]);
			  }
			});
		txtcari.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.setVisible(true);
						if (! oac.hsp_hsp_kodu.equals(""))
						{
							txtcari.setText(oac.hsp_hsp_kodu);
						}
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
				}
			}
		});
		
		txtcari.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtcari.setBounds(89, 33, 110, 20);
		panel_2.add(txtcari);
		txtcari.setColumns(10);
		
		lblNewLabel_3 = new JLabel(".....");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		//lblNewLabel_3.setForeground(new Color(25, 25, 112));
		lblNewLabel_3.setBounds(89, 62, 284, 14);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tarih");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(330, 11, 46, 14);
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
                	SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
            		Date date;
    				try {
    					date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc));
    					Calendar cal = Calendar.getInstance();
    	        		cal.setTime(date);
    	        		cal.add(Calendar.DAY_OF_MONTH, -1); 
    	        		dtc.setDate(new Date(cal.getTimeInMillis()));
    				} catch (ParseException e1) {
    					// TODO Auto-generated catch block
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
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
		dtc.setBounds(383, 8, 125, 20);
		dtc.setDateFormatString("dd.MM.yyyy");
		dtc.setFont(new Font("Tahoma", Font.BOLD, 12));
		dtc.setDate(new Date());
		panel_2.add(dtc);
		
		JLabel lblNewLabel_5 = new JLabel("Adres");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(330, 36, 46, 14);
		panel_2.add(lblNewLabel_5);
		
		txtadres = new Obs_TextFIeld(12);
		txtadres.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  String sonuc = "";
				  try 
				  {
					
							sonuc = a_Access.kod_ismi(txtadres.getText());
					
					lblNewLabel_6.setText(sonuc);
				  }
					catch (Exception ex) 
				{		
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
					}
			  }
			  public void removeUpdate(DocumentEvent e) {
				  String sonuc = "";
				  try 
				  {
					
							sonuc = a_Access.kod_ismi(txtadres.getText());
					
					lblNewLabel_6.setText(sonuc);
				  }
					catch (Exception ex) 
				{		
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
					}
			  }
			  public void insertUpdate(DocumentEvent e) {
				  String sonuc = "";
				  try 
				  {
					
							sonuc = a_Access.kod_ismi(txtadres.getText());
					
					lblNewLabel_6.setText(sonuc);
				  }
					catch (Exception ex) 
				{		
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
					}
			  }
			});
		
		txtadres.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					ADRES_LISTE asp ;
					asp = new ADRES_LISTE();
					asp.setVisible(true);
					if(!  oac.hsp_hsp_kodu.equals(""))
					{
					txtadres.setText(oac.hsp_hsp_kodu);
					}
				}
			}
		});
		txtadres.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtadres.setBounds(383, 33, 125, 20);
		panel_2.add(txtadres);
		txtadres.setColumns(10);
		
		lblNewLabel_6 = new JLabel(".....");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		//lblNewLabel_6.setForeground(new Color(139, 0, 0));
		lblNewLabel_6.setBounds(383, 62, 314, 14);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Ozel Kod");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(510, 36, 61, 14);
		panel_2.add(lblNewLabel_7);
		
		cmbozkod = new JComboBox<String>();
		cmbozkod.setEditable(true);
		
		
		cmbozkod.setBounds(587, 33, 110, 22);
		panel_2.add(cmbozkod);
		
		JLabel lblNewLabel_8 = new JLabel("Varsayilan");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_8.setBounds(726, 62, 69, 14);
		panel_2.add(lblNewLabel_8);
		
		cmbfiat = new JComboBox<String>();
		//cmbfiat.setForeground(new Color(0, 0, 128));
		cmbfiat.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbfiat.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Fiat_1", "Fiat_2", "Fiat_3", "Son Alis/Satis Fiati"}));
		cmbfiat.setBounds(797, 58, 148, 22);
		panel_2.add(cmbfiat);
		
		JLabel lblAnaGrup = new JLabel("Ana Grup");
		lblAnaGrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAnaGrup.setBounds(726, 10, 61, 14);
		panel_2.add(lblAnaGrup);
		
		cmbanagrup = new JComboBox<String>();
		//cmbanagrup.setForeground(new Color(0, 0, 128));
		cmbanagrup.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbanagrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur();
			}
		});
		cmbanagrup.setBounds(797, 7, 148, 22);
		panel_2.add(cmbanagrup);
		
		JLabel lblAltGrup = new JLabel("Alt Grup");
		lblAltGrup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAltGrup.setBounds(726, 35, 69, 14);
		panel_2.add(lblAltGrup);
		
		cmbaltgrup = new JComboBox<String>();
		//cmbaltgrup.setForeground(new Color(0, 0, 128));
		cmbaltgrup.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbaltgrup.setBounds(797, 33, 148, 22);
		panel_2.add(cmbaltgrup);
		
		JLabel lblNewLabel_9 = new JLabel("Doviz");
		lblNewLabel_9.setBounds(998, 6, 52, 14);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Kur");
		lblNewLabel_10.setBounds(998, 31, 46, 14);
		panel_2.add(lblNewLabel_10);
		
		txtdoviz = new Obs_TextFIeld();
		txtdoviz.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtdoviz.setBounds(1056, 4, 86, 20);
		panel_2.add(txtdoviz);
		txtdoviz.setColumns(10);
		
		txtkur = new JFormattedTextField();
		txtkur.setHorizontalAlignment(SwingConstants.RIGHT);
		txtkur.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtkur.setText("0.0000");
		txtkur.setBounds(1056, 29, 86, 20);
		panel_2.add(txtkur);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(null));
		tabbedPane_1.addTab("Fatura Bilgileri", null, panel_9, null);
		panel_9.setLayout(null);

		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Kesilmis Fatura Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(10, 11, 510, 70);
		panel_9.add(panel_6);
		panel_6.setLayout(null);
		
		dateChooser_1  = new JDateChooser();
		dateChooser_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		dateChooser_1.setDate(new Date());
		dateChooser_1.setBounds(21, 26, 110, 20);
		panel_6.add(dateChooser_1);
		
		JLabel lblNewLabel_23 = new JLabel("Irsaliye No");
		lblNewLabel_23.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_23.setBounds(141, 30, 83, 14);
		panel_6.add(lblNewLabel_23);
		
		textField_1 = new Obs_TextFIeld();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_1.setBounds(234, 26, 216, 20);
		panel_6.add(textField_1);
		textField_1.setColumns(10);
		
	
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(null));
		tabbedPane_1.addTab("Yazici Bilgileri", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_14 = new JLabel("Not 1");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_14.setBounds(10, 11, 46, 14);
		panel_4.add(lblNewLabel_14);
		
		textField_5 = new Obs_TextFIeld(40);
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_5.setBounds(81, 8, 312, 20);
	
		panel_4.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new Obs_TextFIeld(40);
		textField_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_6.setBounds(81, 35, 312, 20);
	
		panel_4.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new Obs_TextFIeld();
		textField_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_7.setBounds(81, 62, 312, 20);
		panel_4.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Not 2");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_15.setBounds(10, 38, 46, 14);
		panel_4.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("Not 3");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_16.setBounds(10, 65, 46, 14);
		panel_4.add(lblNewLabel_16);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Irsaliye'de Toplam Miktarin Yazilmasi");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxNewCheckBox.setBounds(425, 7, 263, 23);
		panel_4.add(chckbxNewCheckBox);


		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Gidecegi Yer Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(710, 5, 257, 70);
		panel_4.add(panel_7);
		panel_7.setLayout(null);

		
		textField_8 = new Obs_TextFIeld();
		textField_8.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  String sonuc = "";
					try {
					
							sonuc = a_Access.kod_ismi(textField_8.getText());
					
					lblNewLabel_17.setText(sonuc);
					}
					catch (Exception ex)
					{	
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
					//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Adres Hesap Okuma", JOptionPane.ERROR_MESSAGE);   
					}
			  }
			  public void removeUpdate(DocumentEvent e) {
				  String sonuc = "";
					try {
					
							sonuc = a_Access.kod_ismi(textField_8.getText());
					 
					lblNewLabel_17.setText(sonuc);
					}
					catch (Exception ex)
					{	
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
					//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Adres Hesap Okuma", JOptionPane.ERROR_MESSAGE);   
					}
			  }
			  public void insertUpdate(DocumentEvent e) {
				  String sonuc = "";
					try {
					
							sonuc = a_Access.kod_ismi(textField_8.getText());
					
					lblNewLabel_17.setText(sonuc);
					}
					catch (Exception ex)
					{	
						OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
					//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Adres Hesap Okuma", JOptionPane.ERROR_MESSAGE);   
					}
			  }
			});
		
		textField_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					ADRES_LISTE asp ;
					asp = new ADRES_LISTE();
					asp.setVisible(true);
					if(! oac.hsp_hsp_kodu.equals(""))
					{
						textField_8.setText( oac.hsp_hsp_kodu);
					}
				}
			}
		});
		textField_8.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_8.setBounds(10, 20, 149, 20);
		panel_7.add(textField_8);
		textField_8.setColumns(10);

		
		lblNewLabel_17 = new JLabel(".....");
		//lblNewLabel_17.setForeground(new Color(0, 0, 139));
		lblNewLabel_17.setBounds(10, 45, 237, 14);
		panel_7.add(lblNewLabel_17);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setIcon(new ImageIcon(IRSALIYE.class.getResource("/ICONLAR/icons8-truck-16.png")));
		btnNewButton_4.setToolTipText("Gidecegi Yer Bilgileri");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean varmi = OBS_MAIN.pencere_bak("GIDECEGI YER");
				 if (varmi  ) 
            		{
            	try {
            		OBS_MAIN.pencere_aktiv_yap("GIDECEGI YER");
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
            	return;
             	}
				 else
	                {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				JInternalFrame internalFrame;
				GLOBAL.nerden = "irs" ;
				internalFrame  = new GIDECEGI_YER();
				OBS_MAIN.desktopPane.add(internalFrame);
				internalFrame.setVisible(true);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				
	                }
			}
		});
		btnNewButton_4.setBounds(169, 18, 25, 23);
		panel_7.add(btnNewButton_4);

		//*********************************************************NORMAL *****************************************************
		  JPanel panel_5 = new JPanel(); panel_5.setBorder(new LineBorder(null)); 
		  tabbedPane_1.addTab("Ek Bilgi", null, panel_5, null);
		  panel_5.setLayout(null);
		  
		  JLabel lblNewLabel_18 = new JLabel("1 -"); lblNewLabel_18.setFont(new
		  Font("Tahoma", Font.PLAIN, 11)); lblNewLabel_18.setBounds(10, 11, 34, 14);
		  panel_5.add(lblNewLabel_18);
		  
		  JLabel lblNewLabel_19 = new JLabel("2 -"); lblNewLabel_19.setFont(new
		  Font("Tahoma", Font.PLAIN, 11)); lblNewLabel_19.setBounds(10, 38, 34, 14);
		  panel_5.add(lblNewLabel_19);
		  
		  textField_9 = new Obs_TextFIeld(50); 
		  textField_9.setFont(new Font("Tahoma",Font.BOLD, 12)); 
		  textField_9.setBounds(54, 8, 447, 20);
		  panel_5.add(textField_9);
		  textField_9.setColumns(10);
		  
		  textField_10 = new Obs_TextFIeld(50); 
		  textField_10.setFont(new Font("Tahoma",  Font.BOLD, 12)); 
		  textField_10.setBounds(54, 33, 447, 20);
		  panel_5.add(textField_10);
		  textField_10.setColumns(10);
		 		
		
		//*******************************************************************************
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(0);
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		splitPane.setRightComponent(splitPane_1);
		
		JPanel panel_3 = new JPanel();
		splitPane_1.setRightComponent(panel_3);
		panel_3.setMinimumSize(new Dimension(0, 135));
		panel_3.setMaximumSize(new Dimension(0, 135));
		panel_3.setLayout(null);
		
		JPanel panel_71 = new JPanel();
		panel_71.setBorder(new LineBorder(null));
		panel_71.setBounds(2, 1, 1158, 21);
		panel_71.setMinimumSize(new Dimension(0, 25));
		panel_71.setMaximumSize(new Dimension(0,25));
		panel_3.add(panel_71);
		panel_71.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Satir Sayisi :");
		lblNewLabel.setBounds(32, 3, 73, 14);
		panel_71.add(lblNewLabel);
		
		lblNewLabel_13 = new JLabel("0");
		//lblNewLabel_13.setForeground(new Color(139, 0, 0));
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_13.setBounds(104, 3, 46, 14);
		panel_71.add(lblNewLabel_13);
		
		lblmiktar = new JLabel("0.000");
		lblmiktar.setHorizontalAlignment(SwingConstants.RIGHT);
		//lblmiktar.setForeground(new Color(139, 0, 0));
		lblmiktar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblmiktar.setBounds(571, 3, 102, 14);
		panel_71.add(lblmiktar);
		
		lbltutar = new JLabel("0.00");
		lbltutar.setHorizontalAlignment(SwingConstants.RIGHT);
		//lbltutar.setForeground(new Color(139, 0, 0));
		lbltutar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbltutar.setBounds(809, 3, 125, 14);
		panel_71.add(lbltutar);
		
		MaterialTabbed tabbedPane_2 = new MaterialTabbed();
		tabbedPane_2.setBounds(2, 22, 1158, 110);
		panel_3.add(tabbedPane_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(null));
		tabbedPane_2.addTab("Toplamlar", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_11 = new JLabel("Barkod");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_11.setBounds(10, 11, 46, 14);
		panel_1.add(lblNewLabel_11);
		
		textField_4 = new Obs_TextFIeld();
		textField_4.setBounds(66, 8, 156, 20);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		lblNewLabel_12 = new JLabel(".....");
		lblNewLabel_12.setForeground(new Color(139, 0, 0));
		lblNewLabel_12.setBounds(66, 33, 270, 14);
		panel_1.add(lblNewLabel_12);
		
		lblNewLabel_20 = new JLabel("0.00");
		//lblNewLabel_20.setForeground(new Color(0, 0, 255));
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_20.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_20.setBounds(1019, 11, 109, 14);
		panel_1.add(lblNewLabel_20);
		
		label = new JLabel("0.00");
		//label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(1019, 39, 109, 14);
		panel_1.add(label);
		
		JLabel lblNewLabel_21 = new JLabel("Beyan Edilen KDV");
		lblNewLabel_21.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_21.setBounds(920, 11, 109, 14);
		panel_1.add(lblNewLabel_21);
		
		JLabel lblNewLabel_22 = new JLabel("Tev.Har.Top.Tutar");
		lblNewLabel_22.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_22.setBounds(920, 39, 109, 14);
		panel_1.add(lblNewLabel_22);
		
		label_1 = new JLabel("0.00");
		//label_1.setForeground(new Color(0, 0, 255));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(810, 11, 99, 14);
		panel_1.add(label_1);
		
		label_2 = new JLabel("0.00");
		//label_2.setForeground(new Color(0, 0, 255));
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(810, 39, 99, 14);
		panel_1.add(label_2);
		
		JLabel lblTevedilenKdv = new JLabel("Tev.Edilen K.D.V.");
		lblTevedilenKdv.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTevedilenKdv.setBounds(700, 11, 109, 14);
		panel_1.add(lblTevedilenKdv);
		
		JLabel lblTevdahtoptutar = new JLabel("Tev.Dah.Top.Tutar");
		lblTevdahtoptutar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTevdahtoptutar.setBounds(700, 39, 109, 14);
		panel_1.add(lblTevdahtoptutar);
		
		label_3 = new JLabel("0.00");
		//label_3.setForeground(new Color(0, 0, 255));
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(585, 11, 99, 14);
		panel_1.add(label_3);
		
		label_4 = new JLabel("0.00");
		//label_4.setForeground(new Color(0, 0, 255));
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(585, 39, 99, 14);
		panel_1.add(label_4);
		
		JLabel lblKdv = new JLabel("K.D.V.");
		lblKdv.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblKdv.setBounds(520, 11, 52, 14);
		panel_1.add(lblKdv);
		
		
		
		JLabel lblIskonto = new JLabel("Iskonto");
		lblIskonto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblIskonto.setBounds(346, 11, 52, 14);
		panel_1.add(lblIskonto);
		
		label_6 = new JLabel("0.00");
		//label_6.setForeground(new Color(0, 0, 255));
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(411, 11, 99, 14);
		panel_1.add(label_6);
		
		label_7 = new JLabel("0.00");
		//label_7.setForeground(new Color(0, 0, 255));
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_7.setBounds(411, 39, 99, 14);
		panel_1.add(label_7);
		
		JLabel lblBakiye = new JLabel("Bakiye");
		lblBakiye.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBakiye.setBounds(346, 39, 82, 14);
		panel_1.add(lblBakiye);
		
		label_8 = new JLabel(".....");
		//label_8.setForeground(new Color(0, 0, 128));
		label_8.setBounds(66, 50, 254, 14);
		panel_1.add(label_8);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setDividerSize(0);
		splitPane_2.setResizeWeight(0.0);
		splitPane_1.setLeftComponent(splitPane_2);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setMinimumSize(new Dimension(30, 0));
		toolBar_1.setMaximumSize(new Dimension(30, 0));
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		splitPane_2.setLeftComponent(toolBar_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (table.getSelectedRow() < 0 ) return ;
				URUN_ARAMA arm ;
				getContentPane().setCursor(oac.WAIT_CURSOR);
					arm = new URUN_ARAMA();
					arm.setVisible(true);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				if ( ! oac.stk_kodu.equals(""))
				{
				table.getModel().setValueAt( oac.stk_kodu,table.getSelectedRow(), 1) ;
				bilgi_doldur(oac.stk_kodu) ;
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-view-16.png")));
		toolBar_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 boolean varmi = OBS_MAIN.pencere_bak("URUN KARTI");
				 	try {
				 if (varmi  ) 
          		{
          		OBS_MAIN.pencere_aktiv_yap("URUN KARTI");
          		}
				 else
             {
					 JInternalFrame internalFrame;
					 internalFrame  = new URUN_KART();
					 OBS_MAIN.desktopPane.add(internalFrame);
					 internalFrame.setVisible(true);
	            }
					URUN_KART.textField_1.setText(table.getValueAt(table.getSelectedRow() == -1 ? 0:table.getSelectedRow(), 1).toString());
          		}
				catch (Exception ex) 
				{
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-product-16.png")));
		toolBar_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					satir_ilave();
					DefaultTableModel mdll = (DefaultTableModel) table.getModel();
					 mdll.removeRow(mdll.getRowCount() -1);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/yeni.png")));
		toolBar_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() < 0 ) return ;
				satir_sil();
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addRow(new Object[]{"","","",0.00,0,0,"",0,0.00,""});
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(FATURA.class.getResource("/ICONLAR/icons8-reduce-16.png")));
		toolBar_1.add(btnNewButton_3);
		
		MaterialTabbed tabbedPane = new MaterialTabbed();
		//tabbedPane.setForeground(new Color(0, 0, 128));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		splitPane_2.setRightComponent(tabbedPane);
		
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		tabbedPane.addTab("Irsaliye", null, scrollPane, null);
		
		DefaultTableModel model = new DefaultTableModel() ; 
		table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {  
				 switch (column) {
		         case 6:
		             return false;
		         default:
		             return true;
		      }
				}
			public void changeSelection(final int row, final int column, boolean toggle, boolean extend)
            {
                super.changeSelection(row, column, toggle, extend);
                if (column < 2)
                {
               table.editCellAt(row, column);
               table.transferFocus();
                }
            }	
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

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
		table.setCellSelectionEnabled(true);
		model.addColumn("Barkod", new String []{""});
	    model.addColumn("Urun Kodu", new String []{""});
	    model.addColumn("Depo", new String []{""});
	    model.addColumn("Fiat", new Double [] {new Double( 0.00 )});
	    model.addColumn("Iskonto", new Double [] {new Double( 0.00 )});
	    model.addColumn("Miktar", new Double [] {new Double( 0.000 )});
	    model.addColumn("Birim", new String []{"" });
	    model.addColumn("KDV",new Double [] {new Double( 0.00 )});
	    model.addColumn("Tutar",new Double [] {new Double( 0.00 )});
	    model.addColumn("Izahat", new String []{"" });
	    
	    TableColumn col ;
	   
	    listBarkod = new ArrayList<String> () ;
	    stk_kodu_auto("Barkod");
	    ComboBoxTableCellEditor editor = new ComboBoxTableCellEditor( listBarkod ,table,"irsaliye");
	    col = table.getColumnModel().getColumn(0);
	    col.setCellEditor(editor);
		col.setMinWidth(100);
	    col.setHeaderRenderer(new SOLA());
  
	    listSomeString = new ArrayList<String> () ;
	    stk_kodu_auto("Kodu");
	    col = table.getColumnModel().getColumn(1);
	    ComboBoxTableCellEditor editorkodu = new ComboBoxTableCellEditor(  listSomeString ,table,"irsaliye");
	    col.setCellEditor(editorkodu);
		col.setMinWidth(110);
	    col.setHeaderRenderer(new SOLA());
	    /*
	    col = table.getColumnModel().getColumn(0);
		col.setMinWidth(100);
	    col.setHeaderRenderer(new SOLA());
	    //**
	    col = table.getColumnModel().getColumn(1);
	    listSomeString = new ArrayList<String> () ;
	    stk_kodu_auto();
        Java2sAutoComboBox comboBox1 = new Java2sAutoComboBox( listSomeString,"irsaliye");
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
        Java2sAutoComboBox combodp = new Java2sAutoComboBox( listdepo,"irsaliye");
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
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setMinWidth(100);
		
		col = table.getColumnModel().getColumn(5);
	    col.setHeaderRenderer(new SAGA());
	    col.setCellEditor( new DoubleEditor(3) );
		col.setCellRenderer(new TABLO_RENDERER(3,true));
		col.setMinWidth(100);
		
		col = table.getColumnModel().getColumn(6);
		col.setMinWidth(75);
		col.setHeaderRenderer(new SOLA());
		 
		col = table.getColumnModel().getColumn(7);
		col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,false));
		col.setMinWidth(70);
		
		col = table.getColumnModel().getColumn(8);
		col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(2) );
		col.setCellRenderer(new TABLO_RENDERER(2,true));
		col.setMinWidth(110);
		
		col = table.getColumnModel().getColumn(9);
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
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
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
		scrollPane.setViewportView(table);
		

		cmbcins = new JComboBox<String>();
		cmbcins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS"))
				{
					setTitle("IRSALIYE		- SATIS");
					textField.setText(".");
					textField.setText("");
					textField.requestFocus();
				}
				else
				{
					setTitle("IRSALIYE		- ALIS");
					textField.setText(".");
					textField.setText("");
					textField.requestFocus();
				}
			}
		});
		cmbcins.setBackground(new Color(255, 255, 255));
		cmbcins.setForeground(new Color(0, 0, 139));
		cmbcins.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbcins.setModel(new DefaultComboBoxModel<String>(new String[] {"SATIS", "ALIS"}));
		cmbcins.setBounds(998, 54, 144, 22);
		panel_2.add(cmbcins);
		//stk_kodu_auto();
		ana_grup_doldur();
		irs_oz_kod();
		//***********
				String deger;
				Integer sat_sayi;
					try {
						deger = GLOBAL.setting_oku("STK_IRS_SATIR").toString();
						sat_sayi =Integer.parseInt(deger);
						for (int i = 0; i <= sat_sayi -1 ; i ++)
						{
							satir_ilave();
						}
						txtdoviz.setText(GLOBAL.setting_oku("PRG_PARA").toString());
						
						JLabel lblSevkTarih = new JLabel("Sevk Tarih");
						lblSevkTarih.setFont(new Font("Tahoma", Font.PLAIN, 11));
						lblSevkTarih.setBounds(510, 11, 61, 14);
						panel_2.add(lblSevkTarih);
						
						dateChooser = new JDateChooser();
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
						dateChooser.setBounds(587, 8, 110, 20);
						dateChooser.setDateFormatString("dd.MM.yyyy");
						dateChooser.setFont(new Font("Tahoma", Font.BOLD, 12));
						dateChooser.setDate(new Date());
						panel_2.add(dateChooser);
						
						JButton button = new JButton("");
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								ana_grup_doldur();
							}
						});
						button.setIcon(new ImageIcon(IRSALIYE.class.getResource("/ICONLAR/icons8-repeat-16.png")));
						button.setToolTipText("Yenile");
						button.setBounds(955, 7, 26, 23);
						panel_2.add(button);
						
						JButton button_1 = new JButton("");
						button_1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								son_fisoku();
							}
						});
						button_1.setIcon(new ImageIcon(IRSALIYE.class.getResource("/ICONLAR/icons8-view-16.png")));
						button_1.setToolTipText("Son Fis");
						button_1.setBounds(209, 7, 26, 23);
						panel_2.add(button_1);
						
					} catch (Exception ex) {
					
					}
		//***********
		table.getModel().addTableModelListener(	(TableModelListener) new TableModelListener() 
       			{	
				@Override
				public void tableChanged(TableModelEvent e) {
 	   				  TableModel model = (TableModel)e.getSource();
  			   			if (model.getRowCount() > 0) {
   			   			int row;
 		   				row = table.getSelectedRow();     //e.getFirstRow();
   			   		    int column = e.getColumn();
   			   		  //      Object data = model.getValueAt(row, column);
   			   if (column == 3)  //FIAT
   			       {
   			   	    	double fiat ,miktar = 0 ;
   			   	    	fiat =  Double.parseDouble(model.getValueAt(row, 3).toString());
   			   	    	miktar = Double.parseDouble(model.getValueAt(row, 5).toString());
   			   	    	model.setValueAt( fiat * miktar,row, 8)  ;
   			   	   }
   			   	 if (column == 5)  //MIKTAR
			   	     {
   			   		 	double fiat ,miktar = 0 ;
   			   		 	fiat =  Double.parseDouble(model.getValueAt(row, 3).toString());
   			   		 	miktar = Double.parseDouble(model.getValueAt(row, 5).toString());
   			   		 	model.setValueAt( fiat * miktar,row, 8)  ;
			   	     }
   			   	 	//** Bakiye Kontrol
   			   	 		String deger ="";
   			   	 		Integer sat_sayi;
				if (column == 1)
					{
					try {
						deger = GLOBAL.setting_oku("STK_STOK_KONTROL").toString();
						sat_sayi = Integer.parseInt(deger);
					} catch (Exception ex) {		} 
					if (! deger.equals("-1"))
						{
						if ( table.getValueAt(row,1).toString().equals(""))
						 {	 return ;		 }
						 boolean durum = false ;
						
								try {
								durum = f_Access.stok_bak_kontrol(table.getValueAt(row,1).toString());
								} catch (Exception ex) {		} 
						
		                  if (durum)
		                  {
		                	
		                	  OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Bu Urun Bakiyesi Eksi Konumdadir....");
		                	//	JOptionPane.showMessageDialog(null, "Bu Urun Bakiyesi Eksi Konumdadir....",   "Stok Bakiye Kontrol", JOptionPane.ERROR_MESSAGE);              
		                  }
		                    else
	                    {
	                      //  ' Stokta - Bakiye Degil
	                        //Cursor = System.Windows.Forms.Cursors.Default
	                    }
					}
						}
			//*** barkod
			if (column == 0)
				{
					try {
								deger = GLOBAL.setting_oku("STK_STOK_KONTROL").toString();
								sat_sayi = Integer.parseInt(deger);
					} catch (Exception ex) {		} 
							if (! deger.equals("-1"))
							{
							if ( table.getValueAt(row,1).toString().equals(""))
							 {
							 return ;
							 }
							 boolean durum = false ;
								
									try {
										durum = f_Access.stok_bak_kontrol_barcode(table.getValueAt(row,0).toString());
									} catch (Exception ex) {		} 
								
			                  if (durum)
			                  {
			                	
			                	  OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Bu Urun Bakiyesi Eksi Konumdadir....");
			                //		JOptionPane.showMessageDialog(null, "Bu Urun Bakiyesi Eksi Konumdadir....",   "Stok Bakiye Kontrol", JOptionPane.ERROR_MESSAGE);              
			                  }
			                    else
			                    {
			                      //  ' Stokta - Bakiye Degil
			                        //Cursor = System.Windows.Forms.Cursors.Default
			                    }
							}
						}			
   			   	 //**
   			   		}
  			   		 toplam();
				}
   			});
		textField.requestFocus();
	}
	//******************************************************************************************************
	public static void kaydet()
	{
		if(dtc.getDate() == null) return;
        if (textField.getText().equals("")) return ;
        DefaultTableModel mdl = (DefaultTableModel) table.getModel();
        if (mdl.getRowCount() == 0)  return;
        try
        {
			long startTime = System.currentTimeMillis();
         tar = TARIH_CEVIR.tarih_geri_saatli(dtc) ;
         sevktar = TARIH_CEVIR.tarih_geri_saatli(dateChooser) ;
         fattar =  TARIH_CEVIR.tarih_geri_saatli(dateChooser_1) ;
          satir_yaz_1();
          dipnot_yaz();
          acik_yaz();

        long endTime = System.currentTimeMillis();
	 	long estimatedTime = endTime - startTime;
	 	double seconds = (double)estimatedTime/1000; 
	 	OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
         //**** Cari Dekont Kontrol ***********
        if ( yeni_fat == false ) // ' eski kayit
            {
        	ResultSet rss = null;
        	
        		 rss = c_Access.evrak_ogren(textField.getText());
			
        	boolean result ;
        	if (!rss.isBeforeFirst() ) {
        		result = false;
			}
        	else
        	{
        		rss.next();
        		String sonuc = rss.getString("EVRAK");
        		if ( sonuc.equals(""))
        		{
        			result = false;
        		}
        		else
        		{
        			result = true;
        		}
        	}
                if  (result)
				{
                	int g = JOptionPane.showOptionDialog( null, "Cariye onceden Kayit Yapilmis.. Dekont Acilsin mi ? ", "Cari Irsaliye Kayit",   JOptionPane.YES_NO_OPTION,
			    	   		JOptionPane.QUESTION_MESSAGE,null, oac.options, oac.options[1]); 
			  	        if(g != 0 ) { 
		    	        	textField.setText("");
			   	        	textField.requestFocus();
			    	        	return;
			    }	
			     else
			    {
			      cari_kontrol() ;
			      }
              	}
             }
            //'************************************
       textField.setText("");
       textField.requestFocus();
       }
        catch (Exception ex)
        {
        	  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
        //	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Kayit", JOptionPane.ERROR_MESSAGE);             
         }
	}
	private void fiskont()
	{
		try {
			long startTime = System.currentTimeMillis();
		ResultSet rss = null;
		  if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
	        {
      	  
      		    rss = f_Access.irsaliye_oku(textField.getText(), "C");
	 			
	        }
      	  else
      	  {
      		
      			 rss = f_Access.irsaliye_oku(textField.getText(), "G");
 			
      	  }
		  if (!rss.isBeforeFirst() ) {  
			  txtadres.setText("");
              yeni_fat = true;
              GRID_TEMIZLE.grid_temizle(table);
              sifirla();
 			}
			else
			{
			rss.next();
			  yeni_fat = false;
			  txtadres.setText("");
			  GRID_TEMIZLE.grid_temizle(table);
			  sifirla();
			  dtc.setDate(rss.getDate("Tarih"));
			  dateChooser.setDate(rss.getDate("Sevk_Tarihi"));
			 
			  txtadres.setText(rss.getString("Firma"));
			  txtcari.setText(rss.getString("Cari_Hesap_Kodu"));
			  txtdoviz.setText(rss.getString("Doviz"));
			  txtkur.setText(FORMATLAMA.doub_4(rss.getDouble("Kur")));
			  cmbozkod.setSelectedItem(rss.getString("Ozel_Kod"));
			  textField_1.setText(rss.getString("Fatura_No"));
			 // dateChooser_1.setDate(rss.getDate("Fatura_Tarihi"));
			  
			  //  '***********GRUP DOLDUR
				ResultSet rsa=null;
				
					rsa = f_Access.urun_kod_degisken_ara("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN",String.valueOf(rss.getInt("Ana_Grup")));
				
				if (!rsa.isBeforeFirst() ) {  
					cmbaltgrup.setEnabled(false);
					cmbanagrup.setSelectedItem("");
				} 
				else
				{
					rsa.next();
					cmbanagrup.setSelectedItem(rsa.getString("ANA_GRUP"));
				   	cmbaltgrup.setEnabled(true);
				}
				//**Alt Grup
				 rsa = null;
				
					rsa = f_Access.urun_kod_degisken_ara("ALT_GRUP", "ALID_Y", "ALT_GRUP_DEGISKEN",String.valueOf(rss.getInt("Alt_Grup")));
				
				if (!rsa.isBeforeFirst() ) {  
					cmbaltgrup.setSelectedItem("");
				} 
				else
				{
					 rsa.next();
					 cmbaltgrup.setSelectedItem(rsa.getString("ALT_GRUP"));
				}

				//  '************ACIKLAMA OKU ***********************************************************
				if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
				{
						textField_9.setText( f_Access.aciklama_oku("IRS", 1, textField.getText(), "C"));
						textField_10.setText(f_Access.aciklama_oku("IRS", 2, textField.getText(), "C"));
				}
				else
				{
						textField_9.setText(f_Access.aciklama_oku("IRS", 1, textField.getText(), "G"));
						textField_10.setText(f_Access.aciklama_oku("IRS", 2, textField.getText(), "G"));
				}
			        rss.first();   
			    	DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			    	int satir =0 ;
			    	do
			        {
			   		 mdl.insertRow(satir,new Object[]{rss.getString("Barkod"),rss.getString("Kodu"),rss.getString("Depo"),
			   				 			rss.getDouble("Fiat"),rss.getDouble("Iskonto"),rss.getDouble("Miktar"),
			   				 			rss.getString("Birim"),rss.getDouble("Kdv"),Math.abs(rss.getDouble("Tutar")) ,rss.getString("Izahat")});
			   		satir +=1 ;
			   		mdl.removeRow(mdl.getRowCount() -1);
			        }  while (rss.next()) ;
	                dipnot_oku();
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
			  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Fis Kontrol", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void sifirla()
	{
		String deger;
		Integer sat_sayi;
				try {
					deger = GLOBAL.setting_oku("STK_IRS_SATIR").toString();
					sat_sayi =Integer.parseInt(deger);
					for (int i = 0; i <= sat_sayi; i ++)
					{
						satir_ilave();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	        table.isRowSelected(0);
	        textField_5.setText("");
	        textField_6.setText("");
	        textField_7.setText("");
	        textField_8.setText("");
	        textField_1.setText("");
	        label.setText("0.00");
	        lblNewLabel_20.setText("0.00");
	        label_2.setText("0.00");
	        label_1.setText("0.00");
	        label_3.setText("0.00");
	        label_6.setText("0.00");
	        label_7.setText("0.00");
	        lblmiktar.setText("0.000");
	        lbltutar.setText("0.00");
	        try {
				txtdoviz.setText(GLOBAL.setting_oku("PRG_PARA").toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	        txtcari.setText("");
	        cmbanagrup.setSelectedItem("");
	        cmbaltgrup.setSelectedItem("");
	        cmbozkod.setSelectedItem("");
	        textField_9.setText("");
	        textField_10.setText("");
	        txtkur.setText("0.0000");
	        lblNewLabel_12.setText("....");
	        label_8.setText("....");
	       dtc.setDate(new Date());
	       dateChooser.setDate(new Date());
	       dateChooser_1.setDate(new Date());
	 
	}
	private static void cari_kontrol() 
	{
		try {
		 boolean varmi = OBS_MAIN.pencere_bak("DEKONT");
		 if (varmi  ) 
     	{
      		OBS_MAIN.pencere_aktiv_yap("DEKONT");
      	}
		 else
        {
			 JInternalFrame internalFrame;
			 internalFrame  = new DEKONT();
			 OBS_MAIN.desktopPane.add(internalFrame);
			 internalFrame.setVisible(true);
        }
		 ResultSet rss = null;
     	
     		 rss = c_Access.evrak_ogren(textField.getText());
			
      	String sonuc = null ;
     	if (!rss.isBeforeFirst() ) {
 			}
     	else
     	{
     		rss.next();
     		sonuc = rss.getString("EVRAK");
     	}
 			DEKONT.txtevrak.setText(sonuc);
			DEKONT.fiskont();
		} 
		catch (Exception ex)
		{
			  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
	private static void satir_yaz_1 ()
	{
		try {
		if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
		{
			
				f_Access.irs_giris_sil(textField.getText(), "C");
			
		}
		{
			
				f_Access.irs_giris_sil(textField.getText(), "G");
			
		}
		 DefaultTableModel mdl = (DefaultTableModel) table.getModel();
	       for (int  i = 0 ; i <=  mdl.getRowCount() - 1 ; i++)
	       {
	          //  Progres_Bar(RG1.Rows.Count - 1, i)
	    	   if (! mdl.getValueAt(i,1).toString().equals(""))
	    	   {
	                sat_yaz_2(i);
	    	   }
	       }
	       // Progres_Bar_Temizle()
		}
		catch (Exception ex)
		{
			  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Satyz1", JOptionPane.ERROR_MESSAGE);             
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
   		ResultSet rs =null ;
   	 if (mdl.getValueAt(i,2) == null)
   		{
	       depo = 0 ;
   		}
	    else
	    {
	    	
	    		rs = f_Access.urun_kod_degisken_ara("DPID_Y", "DEPO", "DEPO_DEGISKEN",  mdl.getValueAt(i,2).toString());
			
	    	if (!rs.isBeforeFirst() ) {      		
	    	}
	    	else
	    	{
	    		rs.next();
				depo = rs.getInt("DPID_Y");
	    	}
	   }
	        gircik = "";
	        if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
	        {
	            miktar = Double.parseDouble( mdl.getValueAt(i,5).toString());
	            miktar =miktar * -1;
	            gircik = "C" ;
	        }
	        else
	        {
	            miktar = Double.parseDouble( mdl.getValueAt(i,5).toString());
	            gircik = "G";
	        }
	        double tutar ;
	        tutar =Double.parseDouble( mdl.getValueAt(i,8).toString());
       		if ( mdl.getValueAt(i,9) != null)
       		{
	            izahat = "" ;
       		}
	        else
	        {
	            izahat =  mdl.getValueAt(i,9) .toString();
	        }
       	   
	        kur = DecimalFormat.getNumberInstance().parse(txtkur.getText()).doubleValue();
	        angrp = 0 ;
	        if ( ! cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals("") ) {
	        		
	        			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",  cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString());
	        		
	        		if (!rs.isBeforeFirst() ) {      		
	    	    	}
	    	    	else
	    	    	{
	    	    		rs.next();
		        		angrp  = rs.getInt("AGID_Y");
	    	    	}
	        }
	        altgrp = 0;
	        if ( ! cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString().equals("") ) {
	     	      	
	     	    		rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN",  cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString());
	     			
	     	      	if (!rs.isBeforeFirst() ) {      		
	    	    	}
	    	    	else
	    	    	{
	    	    		rs.next();
		     	      	altgrp  = rs.getInt("ALID_Y");
	    	    	}
	     	}
	        double tevk = DecimalFormat.getNumberInstance().parse(label_4.getText()).doubleValue()  ;
	        double fiat =0 ;
	         fiat = Double.parseDouble( mdl.getValueAt(i,3).toString());
	        double isk = 0 ;
	        isk = Double.parseDouble( mdl.getValueAt(i,4).toString());
	        double kdv = 0 ; 
	        kdv =Double.parseDouble( mdl.getValueAt(i,7).toString());
	       
	        
	        	f_Access.irs_kayit(textField.getText(),  mdl.getValueAt(i,1).toString(), depo,fiat , isk,
	    	            miktar, tutar,kdv,tar,txtdoviz.getText(),kur,txtadres.getText(),txtcari.getText(),sevktar,cmbozkod.getItemAt(cmbozkod.getSelectedIndex()).toString()
	    	           , angrp, altgrp,textField_1.getText(),gircik,""  , GLOBAL.KULL_ADI,izahat);
			
		}
		catch (Exception ex)
		{
			  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Satyz2", JOptionPane.ERROR_MESSAGE);     
		}
	}

	private static double sat_toplam(double tutar,double isk ,double kdv ,double tev )
	{
	       double double_0, double_1, double_2;
	        double_1 = (tutar * isk) / 100  ; //' iskonto
	        double_2 = ((tutar - (tutar * isk) / 100) * kdv) / 100 ; //' kdv
	        //'**********Tevkif Islemi **********************************************************
	        double_0 = ((double) Math.round(tutar) - (double) Math.round(double_1)) + ((double) Math.round(double_2) - ((double) Math.round(double_2) / 10) * (double) Math.round(tev));
	        return double_0;
	}
	private static void dipnot_yaz()
	{
		try {
	          dipnot_sil();
	          lOG_BILGI lBILGI = new lOG_BILGI();
				
				
	          if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
		        {
	        	 
	        	  lBILGI.setmESAJ( "Irsaliye Dipnot Yaz C "  );
					lBILGI.seteVRAK(textField.getText());
					
				f_Access.dipnot_yaz(textField.getText(), textField_5.getText(),textField_6.getText(),textField_8.getText(), "I", "C",GLOBAL.KULL_ADI,
						lBILGI  ,BAGLAN_LOG.fatLogDizin);
		 			
		        }
	          else
	          {
	        	  lBILGI.setmESAJ( "Irsaliye Dipnot Yaz G " );
					lBILGI.seteVRAK(textField.getText()); 
	        		f_Access.dipnot_yaz(textField.getText(), textField_5.getText(),textField_6.getText(),textField_8.getText(), "I", "G",GLOBAL.KULL_ADI,
	        				lBILGI  ,BAGLAN_LOG.fatLogDizin);
		 			
	          }
		}
		catch (Exception ex)
		{
			  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Dipnotyz", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private static void dipnot_sil()
	{
		try {
			 lOG_BILGI lBILGI = new lOG_BILGI();
			
		 if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
	        {
			 
				lBILGI.setmESAJ("Irsaliye Dipnot Sil  C " );
				lBILGI.seteVRAK(textField.getText());
			f_Access.dipnot_sil(textField.getText(), "I", "C",
					lBILGI  ,BAGLAN_LOG.fatLogDizin);
	 			
	        }
		 else
		 {
			 lBILGI.setmESAJ( "Irsaliye Dipnot Sil G " );
				lBILGI.seteVRAK(textField.getText()); 
			f_Access.dipnot_sil(textField.getText(), "I", "G",
					lBILGI  ,BAGLAN_LOG.fatLogDizin);
	 			
		 }
		}
		catch (Exception ex)
		{
			
		}
	}
	private static void acik_yaz()
	{
		try {
	        acik_sil();
	        lOG_BILGI lBILGI = new lOG_BILGI();
	        if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
	        {
	        	lBILGI.setmESAJ("Irsaliye Aciklama Yaz C : " + textField_9.getText()   );
				lBILGI.seteVRAK(textField.getText()); 
				
	        		f_Access.aciklama_yaz("IRS", 1, textField.getText(),  textField_9.getText(), "C",
	        				lBILGI  ,BAGLAN_LOG.fatLogDizin);
	        		
	        		lBILGI.setmESAJ( "Irsaliye Aciklama Yaz C : " + textField_10.getText()   );
	        		f_Access.aciklama_yaz("IRS", 2, textField.getText(), textField_10.getText(), "C",
	        				lBILGI  ,BAGLAN_LOG.fatLogDizin);
		 			
	        }
	        else
	        {
	        	lBILGI.setmESAJ( "Irsaliye Aciklama Yaz G : " + textField_9.getText()    );
				lBILGI.seteVRAK(textField.getText());  
	        	f_Access.aciklama_yaz("IRS", 1, textField.getText(),  textField_9.getText(), "G",
	        			lBILGI  ,BAGLAN_LOG.fatLogDizin);
	        	
	        	lBILGI.setmESAJ( "Irsaliye Aciklama Yaz G : " + textField_10.getText()   );
	        	f_Access.aciklama_yaz("IRS", 2, textField.getText(), textField_10.getText(), "G",
	        			lBILGI ,BAGLAN_LOG.fatLogDizin);
		 			
	        }
		}
		catch (Exception ex)
		{
			  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Fatura Acikyz", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private static void acik_sil()
	{
		try {
			 lOG_BILGI lBILGI = new lOG_BILGI();
			 if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
		        {
				 
				 lBILGI.setmESAJ( "Irsaliye Aciklama Sil  C"     );
					lBILGI.seteVRAK(textField.getText());  
					
				 f_Access.aciklama_sil("IRS", textField.getText(), "C",
						lBILGI  ,BAGLAN_LOG.fatLogDizin);
		 			
		        }
			 else
			 {
				 lBILGI.setmESAJ(	 "Irsaliye Aciklama Sil  G"   );
					lBILGI.seteVRAK(textField.getText());  
				 f_Access.aciklama_sil("IRS", textField.getText(), "G",
						lBILGI  ,BAGLAN_LOG.fatLogDizin);
		 			
			 }
			}
			catch (Exception ex)
			{
				
			}
	}
	public static void satir_ilave()
	{
		 DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		 int satir = table.getSelectedRow();
		 if ( satir  < 0 ) 
		 {
		 mdl.addRow(new Object[]{"","","",0.00,0,0.000,"",0,0.00,""});
		 satir = 0 ;
		 }
		 else
		 {
	      mdl.insertRow(satir, new Object[]{"","","",0.00,0,0.000,"",0,0.00,""});
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
			if (table.getSelectedColumn() == 0)  // BARKOD
			{
				if (cins.equals(""))
				{
					lblNewLabel_12.setText("");
					label_8.setText("");
					table.getModel().setValueAt("",table.getSelectedRow(), 1) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 3) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 4) ;
					table.getModel().setValueAt(0.000,table.getSelectedRow(), 5) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 6) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 7) ;
					table.getModel().setValueAt(0.00,table.getSelectedRow(), 8) ;
					table.getModel().setValueAt("",table.getSelectedRow(), 9) ;
					toplam();
					return;
				}
				
					rs = f_Access.urun_adi_oku(cins,"Barkod");
				
		
			if (!rs.isBeforeFirst() ) {  
				lblNewLabel_12.setText("");
				label_8.setText("");
				table.getModel().setValueAt("",table.getSelectedRow(), 1) ;
				table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
				table.getModel().setValueAt(0.00,table.getSelectedRow(), 3) ;
				table.getModel().setValueAt(0.00,table.getSelectedRow(), 4) ;
				table.getModel().setValueAt(0.000,table.getSelectedRow(), 5) ;
				table.getModel().setValueAt("",table.getSelectedRow(), 6) ;
				table.getModel().setValueAt(0.00,table.getSelectedRow(), 7) ;
				table.getModel().setValueAt(0.00,table.getSelectedRow(), 8) ;
				table.getModel().setValueAt("",table.getSelectedRow(), 9) ;
			}
			else
			{
				rs.next();
				lblNewLabel_12.setText(rs.getString("Adi"));
				label_8.setText(rs.getString("Ana_Grup"));
				table.getModel().setValueAt(rs.getString("Kodu"),table.getSelectedRow(), 1) ;
				table.getModel().setValueAt(rs.getString("Birim"),table.getSelectedRow(), 6) ;
				if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_1"))
				{
					table.getModel().setValueAt(rs.getDouble("Fiat"),table.getSelectedRow(), 3) ;
				}
				else if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_2"))
				{
					table.getModel().setValueAt(rs.getDouble("Fiat_2"),table.getSelectedRow(),3) ;
				}
				if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_3"))
				{
					table.getModel().setValueAt(rs.getDouble("Fiat_3"),table.getSelectedRow(),3) ;
				}
				if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Son Alis/Satis Fiati"))
				{
					ResultSet rss = null;
					
						rss = f_Access.irs_son_satis_fiati_oku("Kodu",txtcari.getText(), cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") ? "C" : "G");
					
					if (!rss.isBeforeFirst() ) {  
						table.getModel().setValueAt(0.00,table.getSelectedRow(),3) ;
					}
					else
					{
						rss.next();
						table.getModel().setValueAt(rss.getDouble("Fiat"),table.getSelectedRow(),3) ;
					}
				}
			}
				toplam();
			}
			else if (table.getSelectedColumn() == 1)  // URUN KODU
		{
			if (cins.equals(""))
			{
				lblNewLabel_12.setText("");
				label_8.setText("");
				table.getModel().setValueAt("",table.getSelectedRow(), 0) ;
				table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
				table.getModel().setValueAt(0.00,table.getSelectedRow(), 3) ;
				table.getModel().setValueAt(0.00,table.getSelectedRow(), 4) ;
				table.getModel().setValueAt(0.000,table.getSelectedRow(), 5) ;
				table.getModel().setValueAt("",table.getSelectedRow(), 6) ;
				table.getModel().setValueAt(0.00,table.getSelectedRow(), 7) ;
				table.getModel().setValueAt(0.00,table.getSelectedRow(), 8) ;
				table.getModel().setValueAt("",table.getSelectedRow(), 9) ;
				toplam();
				return;
			}
			
				rs = f_Access.urun_adi_oku(cins,"Kodu");
			
	
		if (!rs.isBeforeFirst() ) {  
			lblNewLabel_12.setText("");
			label_8.setText("");
			table.getModel().setValueAt("",table.getSelectedRow(), 0) ;
			table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
			table.getModel().setValueAt(0.00,table.getSelectedRow(), 3) ;
			table.getModel().setValueAt(0.00,table.getSelectedRow(), 4) ;
			table.getModel().setValueAt(0.000,table.getSelectedRow(), 5) ;
			table.getModel().setValueAt("",table.getSelectedRow(), 6) ;
			table.getModel().setValueAt(0.00,table.getSelectedRow(), 7) ;
			table.getModel().setValueAt(0.00,table.getSelectedRow(), 8) ;
			table.getModel().setValueAt("",table.getSelectedRow(), 9) ;
		}
		else
		{
			rs.next();
			lblNewLabel_12.setText(rs.getString("Adi"));
			label_8.setText(rs.getString("Ana_Grup"));
			table.getModel().setValueAt(rs.getString("Barkod"),table.getSelectedRow(), 0) ;
			table.getModel().setValueAt(rs.getString("Birim"),table.getSelectedRow(), 6) ;
			if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_1"))
			{
				table.getModel().setValueAt(rs.getDouble("Fiat"),table.getSelectedRow(), 3) ;
			}
			else if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_2"))
			{
				table.getModel().setValueAt(rs.getDouble("Fiat_2"),table.getSelectedRow(),3) ;
			}
			if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Fiat_3"))
			{
				table.getModel().setValueAt(rs.getDouble("Fiat_3"),table.getSelectedRow(),3) ;
			}
			if (cmbfiat.getItemAt(cmbfiat.getSelectedIndex()).toString().equals("Son Alis/Satis Fiati"))
			{
				ResultSet rss = null;
				
					rss = f_Access.irs_son_satis_fiati_oku("Kodu",txtcari.getText(), cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") ? "C" : "G");
				
				if (!rss.isBeforeFirst() ) {  
					table.getModel().setValueAt(0.00,table.getSelectedRow(),3) ;
				}
				else
				{
					rss.next();
					table.getModel().setValueAt(rss.getDouble("Fiat"),table.getSelectedRow(),3) ;
				}
			}
		}
			toplam();
		}
	}
	catch (Exception ex)
	{
		  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
	//	JOptionPane.showMessageDialog(null,  ex.getMessage(), "Bilgi Doldur", JOptionPane.ERROR_MESSAGE);
	}
	}
	private  void stk_kodu_auto(String field)
	{
		try {
			ResultSet rs = null;
			
		 		rs =f_Access.stk_barkod_kod_oku(field);
			
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
			  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null,  ex.getMessage(), "Stok Kodu", JOptionPane.ERROR_MESSAGE);
		}
	}
	private  void depo_auto()
	{
		try {
		ResultSet rs = null;
		
			   rs = f_Access.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
			
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
			  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null,  ex.getMessage(), "Depo Doldur", JOptionPane.ERROR_MESSAGE);
		}
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
			  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Ana Grup", JOptionPane.ERROR_MESSAGE);   
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
			  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
	private void irs_oz_kod()
	{
		try {
		getContentPane().setCursor(oac.WAIT_CURSOR);
		cmbozkod .removeAllItems();
		ResultSet rs=null;
		
			rs = f_Access.irs_oz_kod(cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") ? "C" : "G");
		
		if (!rs.isBeforeFirst() ) {  
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			cmbozkod .addItem("");
		    return;
		} 
		cmbozkod .addItem("");
	    while (rs.next())
	    {
	    	cmbozkod .addItem(rs.getString("Ozel_Kod"));
	    }
	    getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Ozel Kod", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private static void toplam()
	{
		try {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	        double  double_0, double_1 = 0, double_2 = 0, double_3 = 0, double_4, double_5 = 0  ;
	        int urunsayi = 0 ;
	        for (int  i = 0 ; i <= table.getRowCount() -1 ; i ++)
	        {
	            double_5 += Double.parseDouble(model.getValueAt(i, 8).toString());
	            double_1 += (Double.parseDouble(model.getValueAt(i, 8).toString()) * (Double.parseDouble(model.getValueAt(i, 4).toString()))) / 100 ; // iskonto
	            double_2 += (( Double.parseDouble(model.getValueAt(i, 8).toString()) - ( Double.parseDouble(model.getValueAt(i, 8).toString()) *  Double.parseDouble(model.getValueAt(i, 4).toString())) / 100) *  Double.parseDouble(model.getValueAt(i, 7).toString())) / 100 ; // kdv
	            double_3 +=  Double.parseDouble(model.getValueAt(i, 5).toString());
	            if (! model.getValueAt(i,1).toString().equals(""))
	            {
	                urunsayi += 1;
	            }
	        }
	        lblmiktar.setText(FORMATLAMA.doub_2(double_3));
	        lbltutar.setText(FORMATLAMA.doub_2(double_5));
	        lblNewLabel_13.setText( FORMATLAMA.doub_0(urunsayi));
	        label_6.setText(FORMATLAMA.doub_2(double_1));
	        double_0 =double_5 - double_1 ;
	        label_7.setText(FORMATLAMA.doub_2(double_0));
	        label_3.setText(FORMATLAMA.doub_2(double_2));
	       // '**********Tevkif Islemi **********************************************************
	        double_4 =DecimalFormat.getNumberInstance().parse( label_4.getText()).doubleValue();
	        double_0 = (double_2 / 10) * double_4 ;
	        label_1.setText(FORMATLAMA.doub_2(double_0));
	        double_0 = (double_5 - double_1) + double_2;
	        label_2.setText(FORMATLAMA.doub_2(double_0));
	        double_0 = (double_2 - (double_2 / 10) * double_4);
	        lblNewLabel_20.setText(FORMATLAMA.doub_2(double_0));
	        double_0 = (double_5 - double_1) + (double_2 - (double_2 / 10) * double_4);
	        label.setText(FORMATLAMA.doub_2(double_0));
		}
		catch (Exception ex)
		{
			  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Toplam", JOptionPane.ERROR_MESSAGE);   
		}
	
	}
	private void dipnot_oku()
	{
		try {
		ResultSet rs =null ;
		if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
		{
			
				rs =   f_Access.dipnot_oku(textField.getText(), "I", "C");
			
		}
		else
		{
			
				rs =   f_Access.dipnot_oku(textField.getText(), "I", "G");
			
		}
		if (!rs.isBeforeFirst() ) {  
		} 
		else
		{
			rs.next();
			textField_5.setText(rs.getString("Bir"));
			textField_6.setText(rs.getString("Iki"));
			textField_8.setText(rs.getString("Uc"));
		}
		}
		catch (Exception ex)
		{
			  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Dipnot", JOptionPane.ERROR_MESSAGE);   
		}
	}
	public static void irs_sil()
	{
		try {
        if (textField.getText().equals("")) return ;
        if (table.getRowCount() == 0) return;
        int g =  JOptionPane.showOptionDialog( null,  "Kayit Dosyadan Silinecek ..?"  ,
        		"Irsaliye Dosyasindan Evrak Silme",   JOptionPane.YES_NO_OPTION,
	   			 	JOptionPane.QUESTION_MESSAGE,
	   			 	null,     //no custom icon
	   			 	oac.options,  //button titles
	   			 	oac.options[1]); //default button
	 	 if(g != 0 ) { return;	}
			long startTime = System.currentTimeMillis();
	 	if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
		{
	 		
			f_Access.irs_giris_sil(textField.getText(), "C");
			
		}
	 	else
	 	{
	 		
				f_Access.irs_giris_sil(textField.getText(), "G");
			
	 	}
      
        dipnot_sil();
        acik_sil();
        long endTime = System.currentTimeMillis();
 		long estimatedTime = endTime - startTime;
 		double seconds = (double)estimatedTime/1000; 
 		OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
       // '**** Cari Dekont Kontrol ***********
        if ( yeni_fat = false ) // ' eski kayit
        {
    	ResultSet rss = null;
    	
    		 rss = c_Access.evrak_ogren(textField.getText());
		
    	boolean result ;
    	if (!rss.isBeforeFirst() ) {
    		result = false;
		}
    	else
    	{
    		rss.next();
    		String sonuc = rss.getString("EVRAK");
    		if ( sonuc.equals(""))
    		{
    			result = false;
    		}
    		else
    		{
    			result = true;
    		}
    	}
            if  (result)
			{
            	int gg = JOptionPane.showOptionDialog( null, "Cariye onceden Kayit Yapilmis.. Dekont Acilsin mi ? ", "Cari Fatura Kayit",   JOptionPane.YES_NO_OPTION,
		    	   		JOptionPane.QUESTION_MESSAGE,null, oac.options, oac.options[1]); 
		  	        if(gg != 0 ) { 
	    	        	textField.setText("");
		   	        	textField.requestFocus();
		    	        	return;
		    }	
		     else
		    {
		      cari_kontrol() ;
		      }
          	}
         }
      //  '************************************
        textField.setText("");
        textField.requestFocus();
		}
		catch (Exception ex)
		{
			  OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Irsaliye Silme", JOptionPane.ERROR_MESSAGE);   
		}
	}
	public static void cari_kaydet()
	{
		try {
		BORC_ALACAK hsp ;
			hsp = new BORC_ALACAK();
			hsp.setLocationRelativeTo(OBS_MAIN.desktopPane);
			String bh = "",alh="";
			oac.hsp_hsp_kodu = "" ;
		       // '***************** hsp cinsleri ogren***********************BORCLU HESAP
			 if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			 {
				hsp.lblNewLabel.setText("Alacakli Hesap");
				hsp.setVisible(true);
				alh =  oac.hsp_hsp_kodu;
				bh = txtcari.getText();
				if (alh.equals("")) return ;
			 }
			 else if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("ALIS") )
			 {
				hsp.lblNewLabel.setText("Borclu Hesap");
				hsp.setVisible(true);
				bh =  oac.hsp_hsp_kodu;
				alh = txtcari.getText();
				if (bh.equals("")) return ;
			 }
			 ResultSet rs ;
			 if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
			 {
				
			        //*******************************************************************************
			        
				    	rs = c_Access.hesap_adi_oku(alh);
				  
					if (!rs.isBeforeFirst() ) {  
						 
						  OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Girilen Alacakli Hesap Kodunda  bir  hesaba rastlanmadi!!!!");
					//	 JOptionPane.showMessageDialog(null,  "Girilen Alacakli Hesap Kodunda  bir  hesaba rastlanmadi!!!!",  "Fatura Cari Kaydetme", JOptionPane.ERROR_MESSAGE); 
						 return ;
					} 
			       //********************************************************************************
			 }
			 else
			 {
				//*******************************************************************************
			      
				    	rs = c_Access.hesap_adi_oku(bh);
				   
					if (!rs.isBeforeFirst() ) {  
						  OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Girilen Borclu Hesap Kodunda  bir  hesaba rastlanmadi!!!!");
						// JOptionPane.showMessageDialog(null,  "Girilen Borclu Hesap Kodunda  bir  hesaba rastlanmadi!!!!",  "Fatura Cari Kaydetme", JOptionPane.ERROR_MESSAGE); 
						 return ;
					} 
			       //********************************************************************************
			 }
			//********************************************************************************
				rs= null;
				
			    	rs = c_Access.hesap_adi_oku(txtcari.getText());
			    
				
				if (!rs.isBeforeFirst() ) { 
					
					 OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,textField.getText() +  " Bu numarada hesaba rastlanmadi!!!!");
					 return;
				} 
				 lOG_BILGI lBILGI = new lOG_BILGI();
				//*************************************
		            double sdf =  DecimalFormat.getNumberInstance().parse(lblNewLabel_13.getText()).doubleValue()  ;
		            String str_4  ="";
		            int e_number =0;
            		
            			e_number =c_Access.cari_fisno_al();
            		
            		 DefaultTableModel model = (DefaultTableModel)table.getModel();
		            double tutar  = DecimalFormat.getNumberInstance().parse(label.getText()).doubleValue()  ;
		            		 if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") )
		            		 {
		                str_4 = textField.getText() + "'Irsaliye ile " + sdf + " " + model.getValueAt(0 , 6).toString() + " Urun Satisi" ;
		                dEKONT_BILGI dBilgi = new dEKONT_BILGI();
						dBilgi.setbHES(bh);
						dBilgi.settAR(TARIH_CEVIR.tarih_geri_saatli(dtc));
						dBilgi.seteVRAK(e_number);
						dBilgi.setbCINS("");
						dBilgi.setbKUR(1);
						dBilgi.setbORC(tutar);
						dBilgi.setaHES(alh);
						dBilgi.setaCINS("");
						dBilgi.setaKUR(1);
						dBilgi.setaLACAK(tutar);
						dBilgi.setiZAHAT(str_4);
						dBilgi.setkOD("Satış");
						dBilgi.setuSER( GLOBAL.KULL_ADI);
						
						lBILGI.setmESAJ("Alacakli Hes:" +alh + " Tut:" +tutar+
	        	        		" Borclu Hes:"+ bh  );
						lBILGI.seteVRAK(textField.getText());
						
	        	        	c_Access.cari_dekont_kaydet(dBilgi,
	        	        		lBILGI ,	BAGLAN_LOG.cariLogDizin);
	        	       
		            		 }
		                else if (cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("ALIS") )
		                {
			                str_4 = textField.getText() + "'Irsaliye ile " + sdf + " " +  model.getValueAt(0 , 6).toString() + " Urun Girisi" ;
			                dEKONT_BILGI dBilgi = new dEKONT_BILGI();
							dBilgi.setbHES(bh);
							dBilgi.settAR(TARIH_CEVIR.tarih_geri_saatli(dtc));
							dBilgi.seteVRAK(e_number);
							dBilgi.setbCINS("");
							dBilgi.setbKUR(1);
							dBilgi.setbORC(tutar);
							dBilgi.setaHES(alh);
							dBilgi.setaCINS("");
							dBilgi.setaKUR(1);
							dBilgi.setaLACAK(tutar);
							dBilgi.setiZAHAT(str_4);
							dBilgi.setkOD("Alış");
							dBilgi.setuSER( GLOBAL.KULL_ADI); 	
							lBILGI.setmESAJ("Alacakli Hes:" +alh + " Tut:" +tutar+
		        	        		" Borclu Hes:"+ bh  );
							lBILGI.seteVRAK(textField.getText());
							
		        	        c_Access.cari_dekont_kaydet(dBilgi,
		        	        		lBILGI ,
		        	        		BAGLAN_LOG.cariLogDizin);
		        	        

		                }
		            		 OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO, "Irsaliye Cari Hesaba Basari ile Kaydedilmistir....");
		      //      JOptionPane.showMessageDialog(null,  "Irsaliye Cari Hesaba Basari ile Kaydedilmistir....",  "Fatura Cari Kaydetme", JOptionPane.ERROR_MESSAGE);
		}
		catch (Exception ex)
		{
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		 //   JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Fatura Cari Kaydetme", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void urun_bilgi_doldur(String cins) 
	{
		try {
			ResultSet rs = null;
			
				rs = f_Access.urun_adi_oku(cins,"Kodu");
			
		if (!rs.isBeforeFirst() ) {  
			lblNewLabel_12.setText("");
			label_8.setText("");
		}
		else
		{
			rs.next();
			lblNewLabel_12.setText(rs.getString("Adi"));
			label_8.setText(rs.getString("Ana_Grup"));
		}
	}
	catch (Exception ex)
	{
		 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
	}
	}
	private void son_fisoku()
	{
		try
		{
		
			textField.setText( f_Access.son_irsno_al(cmbcins.getItemAt(cmbcins.getSelectedIndex()).toString().equals("SATIS") ? "C":"G"));
		
		}
		catch (Exception ex)
		{
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		//	JOptionPane.showMessageDialog(null, ex.getMessage(),  "Irsaliye Okuma", JOptionPane.ERROR_MESSAGE);   
		}
	}
}


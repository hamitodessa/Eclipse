package obs.reh;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CONNECT;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.OBS_ORTAK_MSSQL;
import OBS_C_2025.OBS_ORTAK_MYSQL;
import OBS_C_2025.OBS_ORTAK_SQLITE;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.Server_Bilgi;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.USER_ISLEMLERI;
import fih.FIHRIST_MSSQL;
import fih.FIHRIST_MYSQL;
import fih.FIHRIST_SQLITE;
import javazoom.jl.player.Player;
import net.proteanit.sql.DbUtils;
import obs.ayarlar.aNA_Class;
import raven.toast.Notifications;

import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import fih.FIHRIST_ACCESS;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

@SuppressWarnings({"static-access","unused","serial"})
public class FIHRIST extends JFrame {

	private static final long serialVersionUID = 1L;
	boolean FIH_DOS_VAR;
	BAGLAN bAGLAN = new BAGLAN();
	aNA_Class oac = new aNA_Class();
	FIHRIST_ACCESS  fih_Access ;
	boolean surucubilgi = false;
	obs.ayarlar.MaterialTabbed tabbedPane;
	
	private JTable table_1;
	private JTable table;
	private JTextField textField;
	private static JTextField txtcd;
	private static JTextField txtAdi;
	private static JTextField txtT1;
	private static JTextField txtT2;
	private static JTextField txtT3;
	private static JTextField txtT4;
	private static JTextField txtFax;
	private static JTextField txtMail;
	private static JTextField txtNot;
	private static JTextField txtNot2;
	private static JTextField txtKodu;
	private static JTextField txtIp;
	private static JTextField txtUser;
	private static JTextField txtcdid;
	private static JPasswordField txtPwd;

	private static JCheckBox chckbxS ;
	private static JCheckBox chckbxL ;
	private static JComboBox<String> cmbInstance ;
	private static JComboBox<String> cmbip ;
	private static JComboBox<String> cmbhangisql ;

	private JLabel lblSatir ;
	private JLabel lblbilgi;
	private JButton btnServer ;
	private JButton btnVtKontrol ;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FIHRIST frame = new FIHRIST();
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
	public FIHRIST() {

		//		 try {
		//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
		//		} catch (Exception e) {
		//		
		//			e.printStackTrace();
		//		}
		FlatLaf.registerCustomDefaultsSource("obs.ayarlar");
		//FlatArcOrangeIJTheme.setup();
		FlatMacDarkLaf.setup();
		//FlatLightFlatIJTheme.setup();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setTitle("FIHRIST");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FIHRIST.class.getResource("/obs/ayarlar/iconlar/fihrist.png")));
		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		tabbedPane = new obs.ayarlar.MaterialTabbed();
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tabbedPane.getSelectedIndex() ==1)
				{
					ayar_doldur();
				}
			}
		});
		tabbedPane.setPreferredSize(new Dimension(970,500));

		scrollPane.setViewportView(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("FIHRIST", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane, BorderLayout.CENTER);

		ScrollPaneWin11 scrollPane_1 = new ScrollPaneWin11();
		scrollPane_1.setMinimumSize(new Dimension(0, 170));
		scrollPane_1.setMaximumSize(new Dimension(0, 170));
		splitPane.setLeftComponent(scrollPane_1);

		JPanel panel_2 = new JPanel();
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 33, 855, 5);
		panel_2.add(separator);

		JLabel lblNewLabel = new JLabel("Adi");
		lblNewLabel.setBounds(10, 11, 48, 14);
		panel_2.add(lblNewLabel);

		textField = new JTextField();
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

		textField.setBounds(68, 7, 372, 20);
		panel_2.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Adi");
		lblNewLabel_1.setBounds(10, 46, 48, 14);
		panel_2.add(lblNewLabel_1);

		txtAdi = new JTextField();
		txtAdi.setDocument(new JTextFieldLimit(50));
		txtAdi.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAdi.setBounds(68, 43, 372, 20);
		panel_2.add(txtAdi);
		txtAdi.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tel_1");
		lblNewLabel_2.setBounds(10, 75, 48, 14);
		panel_2.add(lblNewLabel_2);

		txtT1 = new JTextField();
		txtT1.setDocument(new JTextFieldLimit(25));
		txtT1.setBounds(68, 72, 148, 20);
		panel_2.add(txtT1);
		txtT1.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Tel_2");
		lblNewLabel_2_1.setBounds(234, 72, 48, 14);
		panel_2.add(lblNewLabel_2_1);

		txtT2 = new JTextField();
		txtT2.setDocument(new JTextFieldLimit(25));
		txtT2.setColumns(10);
		txtT2.setBounds(292, 69, 148, 20);
		panel_2.add(txtT2);

		JLabel lblNewLabel_2_2 = new JLabel("Tel_3");
		lblNewLabel_2_2.setBounds(450, 72, 48, 14);
		panel_2.add(lblNewLabel_2_2);

		txtT3 = new JTextField();
		txtT3.setDocument(new JTextFieldLimit(25));
		txtT3.setColumns(10);
		txtT3.setBounds(502, 69, 148, 20);
		panel_2.add(txtT3);

		JLabel lblNewLabel_2_3 = new JLabel("Tel_4");
		lblNewLabel_2_3.setBounds(660, 72, 48, 14);
		panel_2.add(lblNewLabel_2_3);

		txtT4 = new JTextField();
		txtT4.setDocument(new JTextFieldLimit(25));
		txtT4.setColumns(10);
		txtT4.setBounds(718, 69, 148, 20);
		panel_2.add(txtT4);

		JLabel lblNewLabel_2_4 = new JLabel("Fax");
		lblNewLabel_2_4.setBounds(10, 101, 48, 14);
		panel_2.add(lblNewLabel_2_4);

		txtFax = new JTextField();
		txtFax.setDocument(new JTextFieldLimit(25));
		txtFax.setColumns(10);
		txtFax.setBounds(68, 98, 148, 20);
		panel_2.add(txtFax);

		JLabel lblNewLabel_2_5 = new JLabel("Mail");
		lblNewLabel_2_5.setBounds(234, 100, 48, 14);
		panel_2.add(lblNewLabel_2_5);

		txtMail = new JTextField();
		txtMail.setDocument(new JTextFieldLimit(50));
		txtMail.setColumns(10);
		txtMail.setBounds(292, 97, 360, 20);
		panel_2.add(txtMail);

		JLabel lblNewLabel_2_6 = new JLabel("Not");
		lblNewLabel_2_6.setBounds(10, 129, 48, 14);
		panel_2.add(lblNewLabel_2_6);

		txtNot = new JTextField();
		txtNot.setDocument(new JTextFieldLimit(50));
		txtNot.setColumns(10);
		txtNot.setBounds(68, 126, 360, 20);
		panel_2.add(txtNot);
		
		JLabel lblNewLabel_2_6_1 = new JLabel("Not_2");
		lblNewLabel_2_6_1.setBounds(450, 129, 48, 14);
		panel_2.add(lblNewLabel_2_6_1);
		
		txtNot2 = new JTextField();
		txtNot2.setDocument(new JTextFieldLimit(50));
		txtNot2.setColumns(10);
		txtNot2.setBounds(508, 126, 360, 20);
		panel_2.add(txtNot2);
		
		txtcd = new JTextField();
		txtcd.setVisible(false);
		txtcd.setBounds(45, 148, 15, 15);
		panel_2.add(txtcd);
		txtcd.setColumns(10);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		toolBar_1.setBounds(772, 5, 138, 27);
		panel_2.add(toolBar_1);

		JButton btnKayitf = new JButton("");
		btnKayitf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtAdi.getText().toString().equals(""))
						return;
					tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					fih_kaydet();
					doldur();
					tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception ex) {
					mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage().toString() );
				}
			}
		});
		btnKayitf.setToolTipText("Kaydet");
		btnKayitf.setIcon(new ImageIcon(FIHRIST.class.getResource("/obs/ayarlar/iconlar/save.png")));
		toolBar_1.add(btnKayitf);
		
		JButton btnSilf = new JButton("");
		btnSilf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtcd.getText().toString().equals("")) return ;
				int g = JOptionPane.showOptionDialog(null,txtAdi.getText() + System.lineSeparator()  + System.lineSeparator() + "Kayit Silinecek..........?" ,
						"Fihrist ", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, 	new String[] {"Yes", "No"}, "No");
				if(g ==  1) {
					return;
				}
				tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					if(! txtcd.getText().toString().equals(""))
					fih_Access.reh_sil(Integer.parseInt(txtcd.getText().toString()));
					doldur();
					tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception ex)
				{
					tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage().toString() );
				}
			}
		});
		btnSilf.setToolTipText("Sil");
		btnSilf.setIcon(new ImageIcon(FIHRIST.class.getResource("/obs/ayarlar/iconlar/sil.png")));
		toolBar_1.add(btnSilf);

		JButton btnYenif = new JButton("");
		btnYenif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fih_kutu_temizle();
					txtAdi.requestFocus();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnYenif.setToolTipText("Yeni");
		btnYenif.setIcon(new ImageIcon(FIHRIST.class.getResource("/obs/ayarlar/iconlar/yeni.png")));
		toolBar_1.add(btnYenif);
		
		JButton btnNewButton = new JButton("Beyaz");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 boolean isDark = FlatLaf.isLafDark();
				 if(!isDark) return;
				 FlatAnimatedLafChange.showSnapshot();
				 FlatArcOrangeIJTheme.setup();
                 FlatLaf.updateUI();
            
                 FlatAnimatedLafChange.hideSnapshotWithAnimation();
			}
		});
		btnNewButton.setBounds(577, 7, 89, 23);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Siyah");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 boolean isDark = FlatLaf.isLafDark();
				 if(isDark) return;
				 FlatAnimatedLafChange.showSnapshot();
				 
				 FlatMacDarkLaf.setup();
                 FlatLaf.updateUI();
            
                 FlatAnimatedLafChange.hideSnapshotWithAnimation();
			}
		});
		btnNewButton_1.setBounds(450, 7, 89, 23);
		panel_2.add(btnNewButton_1);
		///
		JSplitPane splitPanealt = new JSplitPane();
		splitPanealt.setDividerSize(0);
		splitPanealt.setResizeWeight(1.0);
		splitPanealt.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPanealt);
		////
		ScrollPaneWin11 scrollPane_2 = new ScrollPaneWin11();
		scrollPane_2.setBorder(BorderFactory.createEmptyBorder(5, 7, 5, 7));
		splitPanealt.setLeftComponent(scrollPane_2);

		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.setFont(new Font("Calibri", Font.PLAIN, 13));
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					if (table.getRowCount() == 0) return ;
					if (table.getSelectedRow()  < 0) return;
					tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					try {
						fih_kutu_temizle();
						fih_doldur_kutu(table,table.getSelectedRow());
						tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					} catch (Exception e1) {
						tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						mesaj_goster(5000,Notifications.Type.ERROR, e1.getMessage());
					}
					tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});		
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		scrollPane_2.setViewportView(table);

		JPanel panel_4 = new JPanel();
		panel_4.setMinimumSize(new Dimension(0, 30));
		panel_4.setMaximumSize(new Dimension(0, 30));

		splitPanealt.setRightComponent(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_2_7 = new JLabel("Satir Sayisi :");
		lblNewLabel_2_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_7.setBounds(10, 7, 85, 14);
		panel_4.add(lblNewLabel_2_7);
		
		lblSatir = new JLabel("0");
		lblSatir.setForeground(new Color(0, 0, 128));
		lblSatir.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSatir.setBounds(100, 7, 51, 14);
		panel_4.add(lblSatir);
		
		lblbilgi = new JLabel("...");
		lblbilgi.setBounds(174, 7, 300, 14);
		lblbilgi.setForeground(new Color(0, 0, 128));
		lblbilgi.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_4.add(lblbilgi);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Ayarlar", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_1 = new JSplitPane();
		panel_1.add(splitPane_1, BorderLayout.CENTER);

		ScrollPaneWin11 scrollPane_3 = new ScrollPaneWin11();
		scrollPane_3.setMinimumSize(new Dimension(300, 0));
		scrollPane_3.setMaximumSize(new Dimension(300, 0));


		splitPane_1.setLeftComponent(scrollPane_3);

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(290,580));
		scrollPane_3.setViewportView(panel_3);
		panel_3.setLayout(null);

		cmbhangisql = new JComboBox<String>();
		cmbhangisql.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String hangi = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex())  ;
				if (hangi == "MS SQL")
				{
					chckbxS.setEnabled(true);
					chckbxL.setEnabled(true);
					cmbInstance.setEnabled(true);
					cmbip.setEnabled(true);
					txtIp.setEnabled(true);
					txtUser.setEnabled(true);
					txtPwd.setEnabled(true);
					btnServer.setEnabled(true);
				}
				else if (hangi == "MY SQL")
				{
					chckbxS.setEnabled(true);
					chckbxL.setEnabled(true);
					cmbInstance.setEnabled(false);
					cmbip.setEnabled(true);
					txtIp.setEnabled(true);
					txtUser.setEnabled(true);
					txtPwd.setEnabled(true);
					btnServer.setEnabled(true);
				}
				else if (hangi == "SQ LITE")
				{
					chckbxS.setEnabled(false);
					chckbxL.setEnabled(true);
					cmbInstance.setEnabled(false);
					cmbip.setEnabled(false);
					txtIp.setEnabled(false);
					txtUser.setEnabled(false);
					txtPwd.setEnabled(false);
					btnServer.setEnabled(false);
					btnVtKontrol.setEnabled(true);
				}
			}
		});
		cmbhangisql.setModel(new DefaultComboBoxModel<String>(new String[] {"MS SQL", "MY SQL", "SQ LITE"}));
		cmbhangisql.setForeground(new Color(0, 0, 139));
		cmbhangisql.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbhangisql.setBounds(88, 55, 157, 22);
		panel_3.add(cmbhangisql);

		JLabel lblNewLabel_1_1 = new JLabel("Kodu");
		lblNewLabel_1_1.setBounds(10, 88, 68, 14);
		panel_3.add(lblNewLabel_1_1);

		txtKodu = new JTextField();
		txtKodu.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtKodu.setColumns(10);
		txtKodu.setBounds(88, 83, 78, 20);
		panel_3.add(txtKodu);

		chckbxL = new JCheckBox("Lokal");
		chckbxL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxL.isSelected())
				{
					chckbxS.setSelected(false);
				}
				else
				{
					chckbxS.setSelected(true);
				}
			}
		});
		chckbxL.setSelected(true);
		chckbxL.setBounds(88, 107, 65, 23);
		panel_3.add(chckbxL);

		chckbxS = new JCheckBox("Server");
		chckbxS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxS.isSelected())
				{
					chckbxL.setSelected(false);
				}
				else
				{
					chckbxL.setSelected(true);
				}
			}
		});
		chckbxS.setSelected(false);
		chckbxS.setBounds(167, 107, 78, 23);
		panel_3.add(chckbxS);

		JLabel lblInstance = new JLabel("Instance");
		lblInstance.setBounds(10, 160, 68, 14);
		panel_3.add(lblInstance);

		cmbInstance = new JComboBox<String>();
		cmbInstance.setEditable(true);
		cmbInstance.setBounds(88, 155, 157, 22);
		panel_3.add(cmbInstance);

		JLabel lblServer = new JLabel("Server / Port");
		lblServer.setBounds(10, 185, 78, 14);
		panel_3.add(lblServer);

		txtIp = new JTextField();
		txtIp.setText("");
		txtIp.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtIp.setColumns(10);
		txtIp.setBounds(88, 180, 157, 20);
		panel_3.add(txtIp);

		JLabel lblKayitserver = new JLabel("Serverler");
		lblKayitserver.setBounds(10, 210, 78, 14);
		panel_3.add(lblKayitserver);

		cmbip = new JComboBox<String>();
		cmbip.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbip.setEditable(true);
		cmbip.setBounds(88, 205, 157, 22);
		panel_3.add(cmbip);

		JLabel lblSifre = new JLabel("Kullanici");
		lblSifre.setBounds(10, 240, 68, 14);
		panel_3.add(lblSifre);

		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtUser.setColumns(10);
		txtUser.setBounds(88, 235, 157, 20);
		panel_3.add(txtUser);

		JLabel lblKullanici = new JLabel("Sifre");
		lblKullanici.setBounds(10, 263, 68, 14);
		panel_3.add(lblKullanici);

		txtPwd = new JPasswordField();
		txtPwd.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPwd.setBounds(88, 258, 157, 20);
		panel_3.add(txtPwd);

		txtcdid = new JTextField();
		txtcdid.setBounds(10, 108, 32, 20);
		txtcdid.setText("");
		panel_3.add(txtcdid);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBounds(88, 17, 138, 27);

		//////////////////////
		btnServer = new JButton("");
		btnServer.setToolTipText("Server Kontrol");
		btnServer.setIcon(new ImageIcon(FIHRIST.class.getResource("/obs/ayarlar/iconlar/server.png")));
		btnServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					if(txtKodu.getText().toString().equals(""))
						return;
					if (cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()).toString().equals("MS SQL"))
					{
						if (cmbInstance.getSelectedItem().toString() == null)
							return;
					}
					try {
						tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						server_control();
						tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					} catch  (Exception ex)
					{
						tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage().toString() );
					}
				}
			}
		});

		toolBar.add(btnServer);

		btnVtKontrol = new JButton("");
		btnVtKontrol.setToolTipText("Veritabani Kontrol");
		btnVtKontrol.setEnabled(false);
		btnVtKontrol.setIcon(new ImageIcon(FIHRIST.class.getResource("/obs/ayarlar/iconlar/db.png")));
		btnVtKontrol.addActionListener(new ActionListener() {  // VERITABANI KONTROL
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(txtKodu.getText().toString().equals(""))
						return;
					tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					database_kontrol();
					tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					String	qwe = BAGLAN.fihDizin.yER.equals("S") ?  BAGLAN.fihDizin.sERVER : "Lokal" ;
					lblbilgi.setText (BAGLAN.fihDizin.kOD + "  /  " + qwe  + " / "+ BAGLAN.fihDizin.hAN_SQL );
				}
				catch  (Exception ex)
				{
					tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage().toString() );
				}
			}
		});

		toolBar.add(btnVtKontrol);
		JButton btnSil = new JButton("");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtcdid.getText().toString().equals(""))
					return ;
				int g = JOptionPane.showOptionDialog(null, "Kayit Silinecek..........?" ,
						"Calisma Dizini ", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, 	new String[] {"Yes", "No"}, "No");
				if(g ==  1) {
					return;
				}
				tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					oac.uSER_ISL.cd_sil(Integer.parseInt(txtcdid.getText()) );
					ayar_doldur();
					lblbilgi.setText ("");
					tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception ex)
				{
					tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					mesaj_goster(1000,Notifications.Type.ERROR,  ex.getMessage().toString() );
				}
			}
		});
		btnSil.setToolTipText("Sil");
		btnSil.setIcon(new ImageIcon(FIHRIST.class.getResource("/obs/ayarlar/iconlar/sil.png")));
		toolBar.add(btnSil);

		JButton btnYeni = new JButton("");
		btnYeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kutu_temizle();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnYeni.setToolTipText("Yeni");
		btnYeni.setIcon(new ImageIcon(FIHRIST.class.getResource("/obs/ayarlar/iconlar/yeni.png")));
		toolBar.add(btnYeni);

		///////////////////////
		panel_3.add(toolBar);
		txtcdid.setVisible(false);

		ScrollPaneWin11 scrollPane_4 = new ScrollPaneWin11();
		scrollPane_4.setBorder(BorderFactory.createEmptyBorder(5, 7, 5, 7));
		splitPane_1.setRightComponent(scrollPane_4);

		table_1 = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					if (table_1.getRowCount() == 0) return ;
					if (table_1.getSelectedRow()  < 0) return;
					tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					try {
						kutu_temizle();
						doldur_kutu(table_1,table_1.getSelectedRow());
						tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					} catch (Exception e1) {
						tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						mesaj_goster(5000,Notifications.Type.ERROR, e1.getMessage());
					}
					tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		table_1.setShowHorizontalLines(true);
		table_1.setShowVerticalLines(true);
		table_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		table_1.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		Notifications.getInstance().setJFrame(this);
		//************SURUCU KONTROL**************************
		GLOBAL.surucu_kontrol();
		calisma_dizini_oku() ;
		if(! surucubilgi) // Bilgi Yok
		{
			tabbedPane.setSelectedIndex(1);
			ayar_doldur();
		}
		else 
		{
			try {
				fih_Access = new FIHRIST_ACCESS(oac._IFihrist );
				fih_Access.baglan();
				fihrist_kont();
			} catch (Exception e) {
				mesaj_goster(5000,Notifications.Type.WARNING, e.getMessage());
			}
		}
	}
	void calisma_dizini_oku() 
	{
		try 
		{
			bAGLAN.cONNECT("Admin");
			cONN_AKTAR(BAGLAN.fihDizin.hAN_SQL);
			mODUL_AKTAR( BAGLAN.fihDizin.hAN_SQL);
			String hangi_sql =  BAGLAN.fihDizin.hAN_SQL;
			if(hangi_sql.equals("") )
			{
				surucubilgi = false ;
				return ;
			}
			surucubilgi = true ;
			fihrist_calisma_dizini_oku();
		} catch (Exception e) {
			mesaj_goster(5000,Notifications.Type.ERROR, e.getMessage());
		}
	}
	void fihrist_calisma_dizini_oku() throws ClassNotFoundException, SQLException
	{
		CONNECT s_CONN = new CONNECT( oac._IFihristCon);
		if (BAGLAN.fihDizin.yER.equals(""))
		{
			oac.FIHRIST_CONN  = false;
			FIH_DOS_VAR = false;
			return;
		}
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setIns(BAGLAN.fihDizin.iNSTANCE); 
		sBilgi.setKull(BAGLAN.fihDizin.kULLANICI) ;
		sBilgi.setSifre(BAGLAN.fihDizin.sIFRESI);
		sBilgi.setPort(BAGLAN.fihDizin.sERVER);
		sBilgi.setServer( BAGLAN.fihDizin.sERVER);
		sBilgi.setDb("OK_Fih" + BAGLAN.fihDizin.kOD);
		if (BAGLAN.fihDizin.yER.equals("L"))
		{
			if (s_CONN.Server_kontrol_L(sBilgi) == true)   
			{
				if (s_CONN.Dosya_kontrol_L( sBilgi) == false)
				{
					FIH_DOS_VAR = false;
				}
				else
				{
					FIH_DOS_VAR = true;
					oac.FIHRIST_CONN = true ;
				}
			}
			else
			{
				oac.FIHRIST_CONN = false;
			}
		}
		else
		{
			if (s_CONN.Server_kontrol_S(sBilgi) == true)   
			{
				if (s_CONN.Dosya_kontrol_S( sBilgi) == false)
				{
					FIH_DOS_VAR = false;
				}
				else
				{
					FIH_DOS_VAR = true;
					oac.FIHRIST_CONN = true ;
				}
			}
			else
			{
				oac.FIHRIST_CONN = false;
			}
		}
	}
	void fihrist_kont() throws ClassNotFoundException, SQLException
	{
		String qwe = "" ;
		if (oac.FIHRIST_CONN == true)
		{
			if (FIH_DOS_VAR == false)
			{
				tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				mesaj_goster(5000,Notifications.Type.WARNING,  "Calisilan Fihrist -" + BAGLAN.cariDizin.kOD + "- Nolu Dosya Bulunamadi.....");
				lblbilgi.setText ("" );
			}
			else 
			{ 
				qwe = BAGLAN.fihDizin.yER.equals("S") ?  BAGLAN.fihDizin.sERVER : "Lokal" ;
				lblbilgi.setText (BAGLAN.fihDizin.kOD + "  /  " + qwe.toString().trim()  + " / "+ BAGLAN.fihDizin.hAN_SQL );
				doldur();
			}
		}
		else
		{
			lblbilgi.setText ("" );
			tabbedPane.setSelectedIndex(1);
			ayar_doldur();
			mesaj_goster(7500,Notifications.Type.WARNING, "Dosya Baglanti Kurulamadi.....");
		}
	}
	void ayar_doldur() {
		try {
			GRID_TEMIZLE.grid_temizle(table_1);
			ResultSet	rs = null;
			rs = oac.uSER_ISL.user_db_izinleri("Admin", "Fihrist");
			if (!rs.isBeforeFirst() ) {  
				return;
			} 
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			table_1.removeColumn(table_1.getColumnModel().getColumn(0));
			table_1.removeColumn(table_1.getColumnModel().getColumn(1));
			table_1.removeColumn(table_1.getColumnModel().getColumn(1));
			table_1.removeColumn(table_1.getColumnModel().getColumn(1));
			table_1.removeColumn(table_1.getColumnModel().getColumn(1));
			table_1.removeColumn(table_1.getColumnModel().getColumn(3));
			table_1.removeColumn(table_1.getColumnModel().getColumn(4));
			table_1.removeColumn(table_1.getColumnModel().getColumn(4));
			table_1.removeColumn(table_1.getColumnModel().getColumn(4));
			table_1.removeColumn(table_1.getColumnModel().getColumn(5));
			table_1.removeColumn(table_1.getColumnModel().getColumn(5));
			JTableHeader th = table_1.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc = tcm.getColumn(0);
			tc.setHeaderValue( "Kodu" );
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(70);
			tc.setMaxWidth(70);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setHeaderValue( "Ip" );
			tc.setMinWidth(200);
			//tc.setMaxWidth(200);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setHeaderValue( "Modul" );

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setHeaderValue( "Yer" );
			tc.setMinWidth(50);
			tc.setMaxWidth(50);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA());
			tc.setHeaderValue( "SQL" );
			tc.setMinWidth(80);
			tc.setMaxWidth(80);

			th.repaint();
			table_1.setRowHeight(22);
			table_1.setRowSelectionInterval(0, 0);
			doldur_kutu(table_1,0);
		} catch (Exception e) {
			mesaj_goster(5000,Notifications.Type.WARNING, e.getMessage());
		}
	}
	private void server_control() throws HeadlessException, ClassNotFoundException
	{
		tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		cONN_AKTAR(cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()));
		CONNECT s_CONN = new CONNECT(oac._IFihristCon);
		if (chckbxL.isSelected() )
		{
			Server_Bilgi sBilgi = new Server_Bilgi() ;
			sBilgi.setIns(cmbInstance.getSelectedItem() == null ? "" :cmbInstance.getSelectedItem().toString());
			sBilgi.setKull(txtUser.getText()); 
			sBilgi.setSifre( oac.sDONDUR.sDONDUR(txtPwd)); 
			sBilgi.setPort(txtIp.getText()); 
			tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			if ( s_CONN.Server_kontrol_L( sBilgi) == true  )
			{
				tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				btnVtKontrol.setEnabled(true);
			}
			else
			{
				tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				mesaj_goster(5000,Notifications.Type.WARNING,  "Baglanti Saglanamadi........" );
				btnVtKontrol.setEnabled(false);
			}
		}
		else
		{
			tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Server_Bilgi sBilgi = new Server_Bilgi() ;
			sBilgi.setServer(txtIp.getText());
			sBilgi.setIns(cmbInstance.getSelectedItem() == null ? "" :cmbInstance.getSelectedItem().toString() );
			sBilgi.setKull(txtUser.getText()); 
			sBilgi.setSifre( oac.sDONDUR.sDONDUR(txtPwd));
			if (s_CONN.Server_kontrol_S(sBilgi ) == true)
			{
				tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				btnVtKontrol.setEnabled(true);
			}
			else
			{
				tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				mesaj_goster(5000,Notifications.Type.WARNING,  "Baglanti Saglanamadi........" );
				btnVtKontrol.setEnabled(false);
			}
		}
	}
	private void cONN_AKTAR(String hangi)
	{
		if(hangi.equals("MS SQL"))
		{
			oac._IFihristCon = new OBS_ORTAK_MSSQL() ;
		}
		else if(hangi.equals("MY SQL"))
		{
			oac._IFihristCon = new OBS_ORTAK_MYSQL() ;
		}
		else if(hangi.equals("SQ LITE"))
		{
			oac._IFihristCon = new OBS_ORTAK_SQLITE() ;
		}
	}
	private void mODUL_AKTAR(String hangi)
	{
		if(hangi.equals("MS SQL"))
		{
			oac._IFihrist =  new FIHRIST_MSSQL();
		}
		else if(hangi.equals("MY SQL"))
		{
			oac._IFihrist =  new FIHRIST_MYSQL();
		}
		else if(hangi.equals("SQ LITE"))
		{
			oac._IFihrist =  new FIHRIST_SQLITE();
		}
	}
	public static void mesaj_goster(int zaman, Notifications.Type tipType , String mesaj)
	{
		InputStream stream = null ;
		try {
			Notifications.getInstance().show(tipType,Notifications.Location.BOTTOM_RIGHT ,zaman ,mesaj);
			stream = FIHRIST.class.getClassLoader().getResourceAsStream("DOSYA/hata.mp3"); //whts
			Player player = new Player(stream);
			player.play();
			if(stream != null)
				stream.close();
		} catch (Exception ex) {
		}
	}
	private  void database_kontrol() throws ClassNotFoundException, HeadlessException, SQLException, IOException
	{
		tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		cONN_AKTAR(cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()));	 
		mODUL_AKTAR(cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()));
		CONNECT s_CONN = new CONNECT(oac._IFihristCon);
		String program = "";
		String modul = "";
		modul = "Fihrist";
		program = "OK_Fih" + txtKodu.getText();
		if(cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()).equals("SQ LITE"))
		{
			BAGLAN.fihDizin.cONN_STR = GLOBAL.DBYERI +program  + ".DB" ;   //SQLITE
		}
		if (chckbxL.isSelected())
		{
			lokal_dosya(s_CONN,program,modul);//LOCAL DOSYA KONTROL \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		}
		else  // Server
		{
			server_dosya(s_CONN,program,modul);//SERVER DOSYA KONTROL \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		}
	}
	private void lokal_dosya(CONNECT s_CONN,String program,String modul) throws HeadlessException, ClassNotFoundException, SQLException, IOException
	{
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setDb(program);
		sBilgi.setKull(txtUser.getText());
		sBilgi.setSifre(oac.sDONDUR.sDONDUR(txtPwd));
		sBilgi.setIns(cmbInstance.getSelectedItem() == null ? "" :cmbInstance.getSelectedItem().toString() ); 
		sBilgi.setPort(txtIp.getText());
		if ( s_CONN.Dosya_kontrol_L(sBilgi) == true)
		{
			mdb_yaz();
			ayar_doldur();
			BAGLAN bAGLAN = new BAGLAN();
			bAGLAN.cONNECT("Admin");
			fih_Access = new FIHRIST_ACCESS(oac._IFihrist );
			fih_Access.baglan();
			doldur();
			tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			mesaj_goster(5000,Notifications.Type.INFO,   "Veritabani Baglantisi gerceklestirildi" );
		}
		else
		{
			tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			int g =  JOptionPane.showOptionDialog( null,  "Yeni Dosya Olusturulsunmu............?", "Dosya Olusturma",   JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,	 	null,    	oac.options,  	 	oac.options[1]); 
			if(g != 0 ) { return;	}
			dosya_olustur_L();
			mdb_yaz();
			ayar_doldur();
			BAGLAN bAGLAN = new BAGLAN();
			bAGLAN.cONNECT("Admin");
			fih_Access = new FIHRIST_ACCESS(oac._IFihrist );
			fih_Access.baglan();
			doldur();
			tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			mesaj_goster(5000,Notifications.Type.INFO,    "Dosya Olusturuldu ..." );
		}
	}
	private  void dosya_olustur_L() throws IOException, ClassNotFoundException, SQLException
	{
		tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		cONN_AKTAR(cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()));
		mODUL_AKTAR(cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()));
		fih_Access = new FIHRIST_ACCESS(oac._IFihrist);
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());
		sbilgi.setIns(cmbInstance.getSelectedItem() == null ? "" :cmbInstance.getSelectedItem().toString());
		sbilgi.setKull(txtUser.getText());
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtPwd));
		sbilgi.setPort(txtIp.getText()); 
		sbilgi.setDizin_yeri("default");
		sbilgi.setDizin("");
		fih_Access.fihrist_sifirdan_L(sbilgi);
	}
	private void server_dosya(CONNECT s_CONN,String program,String modul) throws HeadlessException, ClassNotFoundException, SQLException, IOException
	{
		Server_Bilgi sBilgi = new Server_Bilgi() ;
		sBilgi.setServer(txtIp.getText());;
		sBilgi.setIns(cmbInstance.getSelectedItem() == null ? "" :cmbInstance.getSelectedItem().toString()); ;
		sBilgi.setKull(txtUser.getText()); ;
		sBilgi.setSifre( oac.sDONDUR.sDONDUR(txtPwd));;
		sBilgi.setDb( program); ;
		sBilgi.setPort(txtIp.getText());
		if ( s_CONN.Dosya_kontrol_S(sBilgi) ==true)
		{
			mdb_yaz();
			ayar_doldur();
			BAGLAN bAGLAN = new BAGLAN();
			bAGLAN.cONNECT("Admin");
			fih_Access = new FIHRIST_ACCESS(oac._IFihrist );
			fih_Access.baglan();
			doldur();
			tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			mesaj_goster(5000,Notifications.Type.INFO,    "Dosya Baglanti Kuruldu ..." );
		}
		else
		{
			tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			int g =  JOptionPane.showOptionDialog( null,  "Yeni Dosya Olusturulsunmu............?", "Dosya Olusturma",   JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,null, oac.options, 	oac.options[1]); 
			if(g != 0 ) { return;	}
			{
				tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				dosya_olustur_S();
				mdb_yaz();
				ayar_doldur();
				BAGLAN bAGLAN = new BAGLAN();
				bAGLAN.cONNECT("Admin");
				fih_Access = new FIHRIST_ACCESS(oac._IFihrist );
				fih_Access.baglan();
				doldur();
				tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				mesaj_goster(5000,Notifications.Type.INFO,    "Dosya Olusturuldu ..." );
			}
		}
	}
	private void mdb_yaz() throws ClassNotFoundException, SQLException
	{
		if(! txtIp.getText().toString().equals(""))
		{
			oac.uSER_ISL.ip_dos_kont(txtIp.getText());
		}
		oac.uSER_ISL.calisanmi_degis("Admin","Fihrist",chckbxL.isSelected() ? "L" : "S"); // CaLISANMI DOSYA KONTROLU
		oac.uSER_ISL.details_yaz(txtKodu.getText(),"Admin",txtUser.getText(), oac.sDONDUR.sDONDUR(txtPwd),
				cmbInstance.getSelectedItem() == null ? "" :cmbInstance.getSelectedItem().toString() , 
				txtIp.getText(), "Fihrist","", chckbxL.isSelected() ? "L" : "S",  "D" , "E", "E",
				cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()), 
				txtcdid.getText(), 0, "false,false,false,false");
	}
	private void dosya_olustur_S()throws ClassNotFoundException, SQLException
	{
		tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		cONN_AKTAR(cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()));
		mODUL_AKTAR( cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex()));
		fih_Access = new FIHRIST_ACCESS(oac._IFihrist);
		Server_Bilgi sbilgi = new Server_Bilgi();
		sbilgi.setKod(txtKodu.getText());;
		sbilgi.setIns(cmbInstance.getSelectedItem() == null ? "" :cmbInstance.getSelectedItem().toString());;
		sbilgi.setKull(txtUser.getText()); ;
		sbilgi.setSifre(oac.sDONDUR.sDONDUR(txtPwd)); ;
		sbilgi.setServer(txtIp.getText()); ;
		sbilgi.setDizin_yeri("default");;
		sbilgi.setDizin("");;
		fih_Access.fihrist_SIFIR_S(sbilgi);
	}
	private static  void doldur_kutu( JTable grd,int satir) throws ClassNotFoundException, SQLException 
	{
		if (grd.getRowCount()== 0 ) {  
			kutu_temizle();
			return;
		} 
		ip_doldur();
		txtKodu.setText(grd.getModel().getValueAt(satir, 1).toString());
		txtIp.setText(grd.getModel().getValueAt(satir, 6).toString());
		txtUser.setText(grd.getModel().getValueAt(satir, 3).toString());
		String decodedString = grd.getModel().getValueAt(satir, 4).toString();
		String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
		byte[] bytes = new byte[byteValues.length];
		for (int i=0, len=bytes.length; i<len; i++) {
			bytes[i] = Byte.parseByte(byteValues[i].trim());     
		}
		try {
			txtPwd.setText( ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		txtcdid.setText(grd.getModel().getValueAt(satir, 0).toString());
		cmbhangisql.setSelectedItem(grd.getModel().getValueAt(satir, 13).toString());
		cmbInstance.removeAllItems();
		cmbInstance.addItem(grd.getModel().getValueAt(satir, 5).toString());
		if (grd.getModel().getValueAt(satir, 9).equals("L"))
		{
			chckbxL.setSelected(true);
			chckbxS.setSelected(false);
		}
		else
		{
			chckbxS.setSelected(true);
			chckbxL.setSelected(false);
		}
	}
	private static void kutu_temizle() throws ClassNotFoundException, SQLException
	{
		txtKodu.setText("");
		txtIp.setText("");
		txtUser.setText("");
		txtPwd.setText("");
		txtcdid.setText("");
		cmbInstance.removeAllItems();
		chckbxL.setSelected(true);
		chckbxS.setSelected(false);
		cmbhangisql.setSelectedItem("MS SQL");
		ip_doldur();
	}
	private static  void ip_doldur() throws ClassNotFoundException, SQLException
	{
		cmbip.removeAllItems();
		ResultSet	rs = null;
		USER_ISLEMLERI usr = new USER_ISLEMLERI();
		rs = usr.ipp("Admin");
		if (!rs.isBeforeFirst() ) {  
			return;
		} 
		else
		{
			cmbip.addItem("");
			while (rs.next())
			{
				cmbip.addItem(rs.getString("IP"));
			}
		}
	}
	private void doldur()
	{
		try {
			ResultSet	rs = null;
			fih_Access = new FIHRIST_ACCESS(oac._IFihrist );
			rs = fih_Access.reh_doldur();
			GRID_TEMIZLE.grid_temizle(table);
			fih_kutu_temizle();
			if (!rs.isBeforeFirst() ) {  
				
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				table.removeColumn(table.getColumnModel().getColumn(9));
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(250);
				tc.setMaxWidth(250);

				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(125);
				tc.setMaxWidth(125);

				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(125);
				tc.setMaxWidth(125);

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(125);
				tc.setMaxWidth(125);

				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(125);
				tc.setMaxWidth(125);

				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(150);
				tc.setMaxWidth(150);

				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(200);
				tc.setMaxWidth(200);

				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(200);
				tc.setMaxWidth(200);
				
				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(200);
				tc.setMaxWidth(200);
				
				Dimension dd = th.getPreferredSize();
				dd.height = 30;
				th.setPreferredSize(dd); 
				th.repaint();
				table.setRowHeight(21);
				lblSatir.setText( String.format("%,d %n" ,  table.getRowCount()));
				fih_doldur_kutu(table,0);
			}
		} catch (Exception ex) 
		{
			mesaj_goster(7000,Notifications.Type.ERROR,ex.getMessage() );
		}
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
	    sorter.setRowFilter(RowFilter.regexFilter("(?iu)" + textField.getText().toLowerCase(),0));
	    table.setRowSorter(sorter);
	    table.revalidate();
	    table.repaint();
		}
	}
	private void fih_kaydet() throws NumberFormatException, ClassNotFoundException, SQLException
	{
		if(! txtcd.getText().toString().equals(""))
			fih_Access.reh_sil(Integer.parseInt(txtcd.getText().toString()));
		fih_Access.reh_kayit(txtAdi.getText(), txtT1.getText(), txtT2.getText(),txtT3.getText(),txtT4.getText(), txtFax.getText(),  txtNot.getText(),  txtNot2.getText(),txtMail.getText());
	}
	private static void fih_kutu_temizle() 
	{
		txtAdi.setText("");
		txtT1.setText("");
		txtT2.setText("");
		txtT3.setText("");
		txtT4.setText("");
		txtFax.setText("");
		txtMail.setText("");
		txtNot.setText("");
		txtNot2.setText("");
		txtcd.setText("");
	}
	private static  void fih_doldur_kutu( JTable grd,int satir) throws ClassNotFoundException, SQLException 
	{
		if (grd.getRowCount()== 0 ) {  
			fih_kutu_temizle();
			return;
		} 
		txtAdi.setText(grd.getModel().getValueAt(satir, 0).toString());
		txtT1.setText(grd.getModel().getValueAt(satir, 1).toString());
		txtT2.setText(grd.getModel().getValueAt(satir, 2).toString());
		txtT3.setText(grd.getModel().getValueAt(satir, 3).toString());
		txtT4.setText(grd.getModel().getValueAt(satir, 4).toString());
		txtFax.setText(grd.getModel().getValueAt(satir, 5).toString());
		txtNot.setText(grd.getModel().getValueAt(satir, 6).toString());
		txtNot2.setText(grd.getModel().getValueAt(satir, 7).toString());
		txtMail.setText(grd.getModel().getValueAt(satir, 8).toString());
		txtcd.setText(grd.getModel().getValueAt(satir, 9).toString());
	}
}

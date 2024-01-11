package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.CheckBoxRenderer;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.MaterialTabbed;
import OBS_C_2025.ORTA;
import OBS_C_2025.SMS_ACCESS;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;


@SuppressWarnings({ "static-access", "serial" })
public class SMS extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static SMS_ACCESS sms_Access = new SMS_ACCESS(OBS_SIS_2025_ANA_CLASS._ISms , OBS_SIS_2025_ANA_CLASS._ISms_Loger);
	static CARI_ACCESS c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(OBS_SIS_2025_ANA_CLASS._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	private static JTable table;
	private JTextField txtgonderen;
	private JTable table_1;
	private static JTextField txttel;
	private static JTextField txtunvan;
	private static JTextField txtkodu;
	private static JTable table_2;
	private static JCheckBox chcdurum ;
	private static JComboBox<String> comboBox_2  ;
	private static JLabel lblNewLabel_12 ;
	private JComboBox<String> comboBox ;
	private JTextArea txtaciklama  ;
	private JComboBox<String> comboBox_1 ;
	private JComboBox<String> comboBox_3 ;
	private static MaterialTabbed tabbedPane ;
	private static JCheckBox chckbxNewCheckBox ;
	private JTextField textField;
	private JTextField textField_1;
	private CommPortIdentifier com ;
	private JLabel lblNewLabel_3 ;
	private CommPort commPort ;
	private  SerialPort serialPort ;
	private JComboBox<String> comboBox_4 ;
	private boolean bagli =false;

	private int m_Databits;
	private int m_Stopbits;
	private int intParity = SerialPort.PARITY_NONE;
	public SMS() {
		setTitle("SMS");
		setBounds(0, 0,900, 600);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);


		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(1);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

		getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 30));
		panel.setMaximumSize(new Dimension(0, 30));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_11 = new JLabel("Kayit Sayisi :");
		lblNewLabel_11.setBounds(10, 8, 72, 14);
		panel.add(lblNewLabel_11);

		lblNewLabel_12 = new JLabel("0");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_12.setBounds(85, 8, 46, 14);
		panel.add(lblNewLabel_12);

		tabbedPane = new MaterialTabbed();
		tabbedPane.setForeground(new Color(0, 0, 128));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblNewLabel_12.setText("0");
				if (tabbedPane.getSelectedIndex()== 1)
				{
					giden_doldur();
				}
				else if (tabbedPane.getSelectedIndex()== 3)
				{
					giris_doldur();
				}
			}
		});
		splitPane.setLeftComponent(tabbedPane);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.0);
		splitPane_1.setDividerSize(0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		tabbedPane.addTab("Sms Gonder", null, splitPane_1, null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_1.setMinimumSize(new Dimension(0, 130));
		panel_1.setMaximumSize(new Dimension(0, 130));
		splitPane_1.setLeftComponent(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Telefon No");
		lblNewLabel.setBounds(10, 11, 100, 14);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_4 = new JLabel("Aciklama");
		lblNewLabel_4.setBounds(10, 40, 86, 14);
		panel_1.add(lblNewLabel_4);

		txtgonderen = new JTextField();
		txtgonderen.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtgonderen.setDocument(new JTextFieldLimit(50));
		txtgonderen.setBounds(120, 8, 150, 20);
		panel_1.add(txtgonderen);
		txtgonderen.setColumns(10);
		txtgonderen.setDocument(new JTextFieldLimit(15));

		txtaciklama = new JTextArea();
		txtaciklama.setFont(new Font("Monospaced", Font.BOLD, 13));
		txtaciklama.setBounds(120, 39, 365, 71);
		txtaciklama.setLineWrap(true);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		txtaciklama.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		txtaciklama.setDocument(new JTextFieldLimit(140));
		panel_1.add(txtaciklama);

		JLabel lblNewLabel_5 = new JLabel("Alinacak Bilgiler");
		lblNewLabel_5.setBounds(565, 4, 100, 14);
		panel_1.add(lblNewLabel_5);

		comboBox = new JComboBox<String>();
		comboBox.setForeground(new Color(0, 0, 128));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Cari Hesap") || comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Adres")  )
				{
					comboBox_1.setVisible(true);
				}
				else
				{
					comboBox_1.setVisible(false);
				}
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Dosyadan", "Cari Hesap", "Adres"}));
		comboBox.setBounds(565, 25, 121, 22);
		panel_1.add(comboBox);

		chckbxNewCheckBox = new JCheckBox("Coklu Gonderim");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( chckbxNewCheckBox.isSelected())
				{
					txtgonderen.setEnabled(false);
					lblNewLabel_12.setText("0");
					isim_doldur();
				}
				else
				{
					GRID_TEMIZLE.grid_temizle(table);
					txtgonderen.setEnabled(true);
					lblNewLabel_12.setText("0");
				}
			}
		});
		chckbxNewCheckBox.setBounds(565, 58, 129, 23);
		panel_1.add(chckbxNewCheckBox);

		JButton btnNewButton_2 = new JButton("Gonder");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (! bagli)
				{
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Modem Bagli Degil......   ");
					//JOptionPane.showMessageDialog(null, "Modem Bagli Degil......   ",  "Sms Gonderme", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				if ( chckbxNewCheckBox.isSelected()) //' Coklu gonderim
				{
					getContentPane().setCursor(oac.WAIT_CURSOR);
					coklu_gonder();
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
				}
				else  //' Tek Gonderim
				{
					getContentPane().setCursor(oac.WAIT_CURSOR);    
					tek_gonder();
					getContentPane().setCursor(oac.DEFAULT_CURSOR);  
				}
				txtgonderen.setText("");
				txtaciklama.setText("");
				txtgonderen.requestFocus();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(MAIL.class.getResource("/ICONLAR/mail-16.png")));
		btnNewButton_2.setBounds(568, 84, 112, 23);
		panel_1.add(btnNewButton_2);

		comboBox_1 = new JComboBox<String>();
		comboBox_1.setForeground(new Color(0, 0, 128));
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"TEL_1", "TEL_2", "TEL_3", "FAX"}));
		comboBox_1.setBounds(719, 25, 112, 22);
		comboBox_1.setVisible(false);
		panel_1.add(comboBox_1);


		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		splitPane_1.setRightComponent(scrollPane);

		table = new JTable();
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);

		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setResizeWeight(0.0);
		splitPane_2.setDividerSize(0);
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		tabbedPane.addTab("Gonderilmis  Sms", null, splitPane_2, null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_2.setMinimumSize(new Dimension(0, 30));
		panel_2.setMaximumSize(new Dimension(0, 30));
		splitPane_2.setLeftComponent(panel_2);

		ScrollPaneWin11 scrollPane_1 = new ScrollPaneWin11();
		splitPane_2.setRightComponent(scrollPane_1);

		table_1 = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table_1.setGridColor(oac.gridcolor);
		}
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table_1.setShowHorizontalLines(true);
		table_1.setShowVerticalLines(true);
		scrollPane_1.setViewportView(table_1);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 191, 255)));
		tabbedPane.addTab("Modem Baglanti", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Baglanti Noktasi");
		lblNewLabel_1.setBounds(97, 67, 113, 14);
		panel_4.add(lblNewLabel_1);

		comboBox_3 = new JComboBox<String>();
		comboBox_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox_3.setForeground(new Color(0, 0, 128));
		comboBox_3.setBounds(220, 63, 90, 22);
		panel_4.add(comboBox_3);

		comboBox_4 = new JComboBox<String>();
		comboBox_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox_4.setForeground(new Color(0, 0, 128));
		comboBox_4.setModel(new DefaultComboBoxModel<String>(new String[] {"4800", "9600", "19200", "38400", "57600", "115200", "230400", "460800", "4921600"}));
		comboBox_4.setSelectedIndex(1);
		comboBox_4.setBounds(220, 96, 90, 22);
		panel_4.add(comboBox_4);

		JLabel lblNewLabel_2 = new JLabel("Baud Rate");
		lblNewLabel_2.setBounds(97, 100, 90, 14);
		panel_4.add(lblNewLabel_2);

		JComboBox<String> comboBox_5 = new JComboBox<String>();
		comboBox_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox_5.setForeground(new Color(0, 0, 128));
		comboBox_5.setModel(new DefaultComboBoxModel<String>(new String[] {"5", "6", "7", "8"}));
		comboBox_5.setSelectedIndex(3);
		comboBox_5.setBounds(220, 129, 90, 22);
		panel_4.add(comboBox_5);

		JLabel lblDataBits = new JLabel("Data Bits");
		lblDataBits.setBounds(97, 133, 90, 14);
		panel_4.add(lblDataBits);

		JComboBox<String> comboBox_6 = new JComboBox<String>();
		comboBox_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox_6.setForeground(new Color(0, 0, 128));
		comboBox_6.setModel(new DefaultComboBoxModel<String>(new String[] {"0", "1", "1.5", "2"}));
		comboBox_6.setSelectedIndex(1);
		comboBox_6.setBounds(220, 162, 90, 22);
		panel_4.add(comboBox_6);

		JLabel lblStopBits = new JLabel("Stop Bits");
		lblStopBits.setBounds(97, 166, 90, 14);
		panel_4.add(lblStopBits);

		JComboBox<String> comboBox_7 = new JComboBox<String>();
		comboBox_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox_7.setForeground(new Color(0, 0, 128));
		comboBox_7.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Even", "Mark", "Odd", "Space"}));
		comboBox_7.setSelectedIndex(0);
		comboBox_7.setBounds(220, 195, 90, 22);
		panel_4.add(comboBox_7);

		JLabel lblParityBits = new JLabel("Parity Bits");
		lblParityBits.setBounds(97, 199, 90, 14);
		panel_4.add(lblParityBits);

		JLabel lblReadTimeout = new JLabel("Read Timeout");
		lblReadTimeout.setBounds(97, 233, 90, 14);
		panel_4.add(lblReadTimeout);

		JLabel lblWriteTimeout = new JLabel("Write Timeout");
		lblWriteTimeout.setBounds(97, 258, 90, 14);
		panel_4.add(lblWriteTimeout);

		textField = new JTextField();
		textField.setText("300");
		textField.setBounds(220, 228, 86, 20);
		panel_4.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setText("300");
		textField_1.setBounds(220, 255, 86, 20);
		panel_4.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Baglan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(comboBox_3.getItemAt(comboBox_3.getSelectedIndex()));
					commPort = portIdentifier.open(this.getClass().getName(),Integer.parseInt(comboBox_4.getItemAt(comboBox_4.getSelectedIndex())));
					serialPort = (SerialPort) commPort;


					setDatabits(Integer.parseInt(comboBox_5.getItemAt(comboBox_5.getSelectedIndex()))) ;
					setStopbits(Integer.parseInt(comboBox_6.getItemAt(comboBox_6.getSelectedIndex()))) ;
					setParity(comboBox_7.getItemAt(comboBox_7.getSelectedIndex()));

					serialPort.setSerialPortParams(Integer.parseInt(comboBox_4.getItemAt(comboBox_4.getSelectedIndex())), 
							m_Databits	 , m_Stopbits, intParity);



					lblNewLabel_3.setText("Baglandi");
					lblNewLabel_3.setForeground(Color.GREEN);
					//  System.out.println("BaudRate: " + serialPort.getBaudRate());
					//  System.out.println("DataBIts: " + serialPort.getDataBits());
					//  System.out.println("StopBits: " + serialPort.getStopBits());
					//  System.out.println("Parity: " + serialPort.getParity());
					bagli=true;
				} catch (Exception ex) {
					lblNewLabel_3.setText("Bagli Degil");
					lblNewLabel_3.setForeground(Color.RED);
					bagli=false;
				}
			}
		});
		btnNewButton.setBounds(220, 298, 89, 23);
		panel_4.add(btnNewButton);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Baglanti durumu", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_5.setBounds(97, 350, 251, 66);
		panel_4.add(panel_5);
		panel_5.setLayout(null);

		lblNewLabel_3 = new JLabel("Bagli Degil");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(27, 28, 85, 14);
		panel_5.add(lblNewLabel_3);

		JButton btnBaglantiyiKes = new JButton("Baglantiyi Kes");
		btnBaglantiyiKes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				try {

					commPort .close();

					lblNewLabel_3.setText("Bagli Degil");
					lblNewLabel_3.setForeground(Color.RED);
					bagli = false;
				} catch (Exception ex) {
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
					//JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Sms Gonderme", JOptionPane.PLAIN_MESSAGE);	
				}

			}
		});
		btnBaglantiyiKes.setBounds(122, 24, 119, 23);
		panel_5.add(btnBaglantiyiKes);

		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setResizeWeight(0.0);
		splitPane_3.setDividerSize(0);
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		tabbedPane.addTab("Bilgi Girisi", null, splitPane_3, null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_3.setMinimumSize(new Dimension(0, 100));
		panel_3.setMaximumSize(new Dimension(0, 100));
		splitPane_3.setLeftComponent(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("Telefon");
		lblNewLabel_6.setBounds(10, 11, 46, 14);
		panel_3.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Durum");
		lblNewLabel_7.setBounds(10, 36, 46, 14);
		panel_3.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Unvan");
		lblNewLabel_8.setBounds(10, 61, 46, 14);
		panel_3.add(lblNewLabel_8);

		txttel = new JTextField();
		txttel.setBounds(81, 8, 150, 20);
		txttel.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				boolean sonuc = false;
				try {
					sonuc = sms_Access.kod_ismi(txttel.getText());
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if ( sonuc )
				{

					txttel.setForeground(Color.red);
				}
				else
				{
					txttel.setForeground(Color.black);

				}
			}
		});
		txttel.setDocument(new JTextFieldLimit(15));
		panel_3.add(txttel);
		txttel.setColumns(10);

		chcdurum = new JCheckBox("Aktif / Pasif");
		chcdurum.setBounds(78, 32, 97, 23);
		panel_3.add(chcdurum);

		txtunvan = new JTextField();
		txtunvan.setBounds(81, 58, 374, 20);
		txtunvan.setDocument(new JTextFieldLimit(50));
		panel_3.add(txtunvan);
		txtunvan.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Kodu");
		lblNewLabel_9.setBounds(491, 8, 46, 14);
		panel_3.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Grup");
		lblNewLabel_10.setBounds(491, 36, 46, 14);
		panel_3.add(lblNewLabel_10);

		txtkodu = new JTextField();
		txtkodu.setBounds(572, 8, 122, 20);
		txtkodu.setDocument(new JTextFieldLimit(12));
		panel_3.add(txtkodu);
		txtkodu.setColumns(10);

		comboBox_2 = new JComboBox<String>();
		comboBox_2.setForeground(new Color(0, 0, 128));
		comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox_2.setEditable(true);
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {""}));
		comboBox_2.setBounds(572, 33, 122, 22);

		panel_3.add(comboBox_2);

		ScrollPaneWin11 scrollPane_2 = new ScrollPaneWin11();
		splitPane_3.setRightComponent(scrollPane_2);

		table_2 = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table_2.setGridColor(oac.gridcolor);
		}

		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				kutu_temizle();
				doldur_kutu(table_2.getSelectedRow());
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_2.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table_2.setShowHorizontalLines(true);
		table_2.setShowVerticalLines(true);
		scrollPane_2.setViewportView(table_2);


		port_doldur();

	}
	private static void giris_doldur()
	{
		try {
			ResultSet rs = null ;
			GRID_TEMIZLE.grid_temizle(table);
			long startTime = System.currentTimeMillis(); 
			rs = sms_Access.sms_giris_bak();
			if (!rs.isBeforeFirst() ) {  
				kutu_temizle();
				lblNewLabel_12.setText("0");
				return;
			} 

			GRID_TEMIZLE.grid_temizle(table_2);
			table_2.setModel(DbUtils.resultSetToTableModel(rs));

			JTableHeader th = table_2.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(200);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());

			tc.setMinWidth(300);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());

			tc.setMinWidth(80);
			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new ORTA());
			CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
			table_2.getColumnModel().getColumn(4).setCellRenderer(checkBoxRenderer);
			tc.setMinWidth(50);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);
			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			table_2.setRowSelectionInterval(0, 0);
			table_2.setRowHeight(21);
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

			lblNewLabel_12.setText(Integer.toString(table_2.getRowCount()));
			//******* GRUP DOLDUR
			comboBox_2.removeAllItems();
			comboBox_2.addItem("");
			rs = sms_Access.sms_grup_bak();
			if (!rs.isBeforeFirst() ) {  
			} 
			else
			{
				while(rs.next()) 
				{
					comboBox_2.addItem(rs.getString("GRUP"));
				}
			}
			//*******
			kutu_temizle();

			doldur_kutu(0);

		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
			//JOptionPane.showMessageDialog(null, ex.getMessage());   
		}
	}
	private static void kutu_temizle() 
	{
		txttel.setText("");
		txtunvan.setText("");
		txtkodu.setText("");
		chcdurum.setSelected(false);
		comboBox_2.setSelectedItem("");

	}
	private static void doldur_kutu(int satir)
	{
		if (table_2.getRowCount() == 0 ) {  
			kutu_temizle();
			return;
		} 
		txttel.setText(table_2.getModel().getValueAt(satir, 0).toString());
		txtunvan.setText(table_2.getModel().getValueAt(satir, 1).toString());
		comboBox_2.setSelectedItem(table_2.getModel().getValueAt(satir, 2).toString());
		txtkodu.setText(table_2.getModel().getValueAt(satir, 3).toString());
		if (table_2.getModel().getValueAt(satir, 4).toString().equals("0")) chcdurum.setSelected(false);
		else if (table_2.getModel().getValueAt(satir, 4).toString().equals("1")) chcdurum.setSelected(true);
		else if (table_2.getModel().getValueAt(satir, 4).toString().equals("false")) chcdurum.setSelected(false);
		else if (table_2.getModel().getValueAt(satir, 4).toString().equals("true")) chcdurum.setSelected(true);


	}
	public static void yeni ()
	{
		if (tabbedPane.getSelectedIndex() != 3) return ;
		kutu_temizle();
		txttel.requestFocus();
	}
	public static void giris_kayit()
	{
		if (tabbedPane.getSelectedIndex() != 3) return ;
		if (txttel.getText().equals("")) {
			txttel.requestFocus();
			return ;
		}
		try {

			sms_Access.sms_giris_sil(txttel.getText());
			sms_Access.sms_giris_yaz(txttel.getText(), 
					txtunvan.getText(), comboBox_2.getSelectedItem().toString(),
					txtkodu.getText(), chcdurum.isSelected(), GLOBAL.KULL_ADI);
			kutu_temizle();
			giris_doldur();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
			//JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kayit Giris", JOptionPane.PLAIN_MESSAGE);		
		}
	}
	public static void sil()
	{
		if (tabbedPane.getSelectedIndex() != 3) return ;
		if (txttel.getText().equals("")) {
			txttel.requestFocus();
			return ;
		}
		int g =  JOptionPane.showOptionDialog( null,  "Kisi Silinecek ..?"  ,	 "SMS Silme",   JOptionPane.YES_NO_OPTION, 	JOptionPane.QUESTION_MESSAGE,	null,     //no custom icon
				oac.options,  //button titles
				oac.options[1]); //default button
		if(g != 0 ) { return;	}
		try {
			sms_Access.sms_giris_sil(txttel.getText());
			kutu_temizle();
			giris_doldur();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
		}
	}
	private void isim_doldur() 
	{
		try {
			ResultSet rs = null ;
			if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Cari Hesap"))
			{
				rs = c_Access.sms_cari_pln(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()));
			}
			else if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Adres"))
			{
				rs = a_Access.sms_adr_hpl(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()));
			}
			else if(comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Dosyadan"))
			{
				rs = sms_Access.sms_alici_doldur();
			}
			if (!rs.isBeforeFirst() ) {  
				lblNewLabel_12.setText("0");
				return;
			} 
			GRID_TEMIZLE.grid_temizle(table);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(200);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(300);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(100);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(40);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(130);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);

			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);

			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);

			lblNewLabel_12.setText(Integer.toString(table.getRowCount()));
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
		}
	}
	private void coklu_gonder()
	{
		if (txtaciklama.getText().equals(""))
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Aciklama Bos .....");
			txtaciklama.requestFocus();
			return ;
		}
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int i = 0 ; i <= model.getRowCount()  - 1 ; i ++) {
			if (model.getValueAt(i, 0).toString().equals( "") )
			{
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, i + 1 + " Nolu Satirda Telefon Numarasi Bos ...");
				return;
			}
		}


		Runnable runner = new Runnable()
		{ public void run() { 
			Progres_Bar_Temizle();
			OBS_MAIN.progressBar.setStringPainted(true);

			int say = 0 ;
			OBS_MAIN.progressBar.setMaximum(model.getRowCount() - 1);
			for (int i = 0 ; i <= model.getRowCount()  - 1 ; i ++)
			{

				say += 1;
				send_sms(model.getValueAt(i, 0).toString(),model.getValueAt(i,4).toString(),model.getValueAt(i, 1).toString());
				Date zaman = new Date();
				DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");  
				model.setValueAt( dateFormat.format(zaman),i, 5) ;

			}
			Thread.currentThread().isInterrupted();
			Progres_Bar_Temizle();
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO, say + " Adet SMS Gonderildi   ");

		}
		};
		//// Progress Bar
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
	private void tek_gonder()
	{
		if (txtgonderen.getText().equals(""))
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Alici Numara Bos .....");
			txtgonderen.requestFocus();
			return ;
		}
		if (txtaciklama.getText().equals(""))
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,"Aciklama Bos .....");
			txtaciklama.requestFocus();
			return ;
		}
		try
		{
			send_sms(txtgonderen.getText(),"","") ;
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.INFO, "SMS 'iniz Gonderildi   ");
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	private void send_sms(String numara,String dort,String bir)
	{
		try
		{
			OutputStream outputStream;
			outputStream = commPort.getOutputStream();
			char enter = 13;
			char CTRLZ = 26;
			String messageString3 = "AT+CMGF=1"; 
			String messageString4 = "AT+CMGS=\""+ numara + "\"";
			String messageString5 = txtaciklama.getText();
			outputStream.write((messageString3 + enter).getBytes());
			Thread.sleep(100); 
			outputStream.write((messageString4 + enter).getBytes()); 
			Thread.sleep(100); 
			outputStream.write((messageString5 + CTRLZ).getBytes());  
			Thread.sleep(500); 
			outputStream.close();
			//**********************Raporlama Dosyasina Yaz ***************************
			Date zaman = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");  
			sms_Access.sms_yaz(GLOBAL.KULL_ADI,dateFormat.format(zaman)  ,txtaciklama.getText(), numara,dort ,bir ) ;
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	public static void satir_sil()
	{
		if (table.getRowCount() == 0 ) return ;
		if (table.getSelectedRow() < 0 ) return ;
		DefaultTableModel mdll = (DefaultTableModel) table.getModel();
		mdll.removeRow(table.getSelectedRow());
		table.repaint();
		lblNewLabel_12.setText(Integer.toString(table.getRowCount()));
		if (table.getRowCount() == 0 ) 
		{
			chckbxNewCheckBox.setSelected(false);

		}
	}
	private void giden_doldur()
	{
		try {
			ResultSet rs = null;
			long startTime = System.currentTimeMillis(); 
			getContentPane().setCursor(oac.WAIT_CURSOR);
			if (GLOBAL.KULL_ADI.equals("Admin"))
			{
				rs = sms_Access.sms_giden_bak("");
			}
			else
			{
				rs = sms_Access.sms_giden_bak("  WHERE USER_NAME = '" + GLOBAL.KULL_ADI.toString()  +  "' ");
			}
			if (!rs.isBeforeFirst() ) {  
				GRID_TEMIZLE.grid_temizle(table_1);
				lblNewLabel_12.setText("0");
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				return;
			} 
			GRID_TEMIZLE.grid_temizle(table_1);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table_1.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			//tc.setCellRenderer(new TARIH_SAATLI());
			tc.setMinWidth(150);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(170);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(300);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(80);

			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			table_1.setRowSelectionInterval(0, 0);
			table_1.setRowHeight(21);
			getContentPane().setCursor(oac.DEFAULT_CURSOR);

			lblNewLabel_12.setText(Integer.toString(table_1.getRowCount()));

			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	@SuppressWarnings("rawtypes")
	private void port_doldur()
	{
		try 
		{
			Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
			while (thePorts.hasMoreElements())
			{
				com = (CommPortIdentifier) thePorts.nextElement();
				if (com.getPortType() == CommPortIdentifier.PORT_SERIAL)
				{
					comboBox_3.addItem(com.getName());	
				}
				//  switch (com.getPortType())
				//  {
				//     case CommPortIdentifier.PORT_SERIAL:
				
				//  }
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
			//JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Com  Port Baglanti Okuma", JOptionPane.PLAIN_MESSAGE);	
		}
	}
	public void setDatabits(int databits) throws IllegalArgumentException 
	{
		switch (databits) 
		{
		case 5:
			m_Databits = SerialPort.DATABITS_5;
			break;
		case 6:
			m_Databits = SerialPort.DATABITS_6;
			break;
		case 7:
			m_Databits = SerialPort.DATABITS_7;
			break;
		case 8:
			m_Databits = SerialPort.DATABITS_8;
			break;
		default:
			m_Databits = SerialPort.DATABITS_8;
			break;
		}
	}
	public void setStopbits(double stopbits) throws IllegalArgumentException 
	{
		if (stopbits == 1) 
		{
			m_Stopbits = SerialPort.STOPBITS_1;
		} 
		else if (stopbits == 1.5) 
		{
			m_Stopbits = SerialPort.STOPBITS_1_5;
		} 
		else if (stopbits == 2) 
		{
			m_Stopbits = SerialPort.STOPBITS_2;
		} 
		else 
		{
			m_Stopbits = SerialPort.STOPBITS_1;
		}
	}// setStopbits
	public void setParity(String parity) throws IllegalArgumentException 
	{
		if (parity.equals("None") ) 
		{
			intParity = SerialPort.PARITY_NONE;
		} 
		else if (parity.equals("Even") ) 
		{
			intParity = SerialPort.PARITY_EVEN;
		} 
		else if (parity.equals("Odd") ) 
		{
			intParity = SerialPort.PARITY_ODD;
		} 
		else if (parity.equals("Space") ) 
		{
			intParity = SerialPort.PARITY_SPACE;
		} 
		else if (parity.equals("Mark") ) 
		{
			intParity = SerialPort.PARITY_MARK; 
		} 
		else 
		{
			throw new IllegalArgumentException("unknown parity string '" + parity + "'");
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

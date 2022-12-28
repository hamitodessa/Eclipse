package VT_YEDEK;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.OBS_ORTAK_MSSQL;
import OBS_C_2025.OBS_ORTAK_MYSQL;
import OBS_C_2025.SQL_BACKUP;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class EMIR extends JFrame {
	VT_ANA_CLASS oac = new VT_ANA_CLASS();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private static JList<CheckListItem> list ;
	public static JComboBox<String> cmbSQL;
	static SQL_BACKUP sqll = new SQL_BACKUP();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BASLA bsl = new BASLA();
					bsl.setVisible(false);
					bsl.dispose();
					EMIR frame = new EMIR();
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
	@SuppressWarnings("unchecked")
	public EMIR() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				try {
					BASLA frame = new BASLA();
					frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void windowOpened(WindowEvent e) {
				
					contentPane.setCursor(oac.WAIT_CURSOR);
					try {
						if (! oac.EMIR_ADI.equals(""))
						{
							serBILGILERI();
						}
					
					} catch (InvalidKeyException | ClassNotFoundException | NoSuchAlgorithmException
							| NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException
							| BadPaddingException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//activ_sayfa =0;
					//grid_doldur();
					//doldur_kutu(tblCari,0);
					contentPane.setCursor(oac.DEFAULT_CURSOR);
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JSplitPane splitPanesol = new JSplitPane();
		splitPanesol.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setLeftComponent(splitPanesol);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(175,40));
		panel.setLayout(null);
		splitPanesol.setLeftComponent(panel);
		
		cmbSQL = new JComboBox<String>();
		cmbSQL.setEnabled(false);
		cmbSQL.setForeground(new Color(0, 0, 139));
		cmbSQL.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbSQL.setModel(new DefaultComboBoxModel(new String[] {"MS SQL", "MY SQL"}));
		cmbSQL.setBounds(10, 11, 106, 22);
		panel.add(cmbSQL);
		
		JButton btnNewButton = new JButton(".....");
		btnNewButton.setToolTipText("Server Baglanti");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		SQL_BILGI hsp = new SQL_BILGI();
		hsp.show();
				}
		});
		btnNewButton.setBounds(126, 11, 39, 23);
		panel.add(btnNewButton);
		
		list = new JList<CheckListItem>();
		
	    list.setCellRenderer(new CheckListRenderer());
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.addListSelectionListener(new ListSelectionListener() {
	    	 @SuppressWarnings("deprecation")
			public void valueChanged(ListSelectionEvent e) {
			      if (!e.getValueIsAdjusting()) {
			        System.out.println(Arrays.toString(list.getSelectedValues()));
			      }
			    }
	    	
	    });
	    list.addMouseListener(new MouseAdapter() {
	      @SuppressWarnings("rawtypes")
		@Override
	      public void mouseClicked(MouseEvent event) {
	        JList list = (JList) event.getSource();
	        int index = list.locationToIndex(event.getPoint());// Get index of item
	                                                           // clicked
	        CheckListItem item = (CheckListItem) list.getModel()
	            .getElementAt(index);
	        item.setSelected(!item.isSelected()); // Toggle selected state
	        list.repaint(list.getCellBounds(index, index));// Repaint cell
	        System.out.println(list.getSelectedValue() + " = " + item.isSelected());
	      }
	    });

		splitPanesol.setRightComponent(list);
			
		///
		
		DefaultListModel<CheckListItem> demoList = new DefaultListModel<CheckListItem>();
		 demoList.addElement( new CheckListItem("mango"));
		 demoList.addElement( new CheckListItem("elma"));
		 list.setModel(demoList);
		//
	
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Genel", null, panel_1, null);
		
		JLabel lblNewLabel = new JLabel("Durum");
		lblNewLabel.setBounds(29, 52, 46, 14);
		panel_1.add(lblNewLabel);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Aktif / Pasif");
		chckbxNewCheckBox.setBounds(100, 48, 156, 23);
		panel_1.add(chckbxNewCheckBox);
		
		JLabel lblNewLabel_1 = new JLabel("Emir Ismi");
		lblNewLabel_1.setBounds(29, 100, 64, 14);
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(103, 97, 322, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Aciklama");
		lblNewLabel_2.setBounds(29, 149, 64, 14);
		panel_1.add(lblNewLabel_2);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(103, 143, 322, 70);
		panel_1.add(textPane);
		
		JLabel lblNewLabel_3 = new JLabel("Dosya Sayisi");
		lblNewLabel_3.setBounds(22, 456, 73, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("0");
		lblNewLabel_4.setBounds(105, 456, 46, 14);
		panel_1.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("Kaydet");
		btnNewButton_1.setBounds(381, 456, 89, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Vazgec");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					BASLA frame = new BASLA();
					frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(483, 456, 89, 23);
		panel_1.add(btnNewButton_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		tabbedPane.addTab("Surucu Ayarlari", null, panel_2, null);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("FTP");
		chckbxNewCheckBox_1.setBounds(40, 18, 97, 23);
		panel_2.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Yerel");
		chckbxNewCheckBox_2.setBounds(235, 18, 97, 23);
		panel_2.add(chckbxNewCheckBox_2);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "FTP Ayarlari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setLayout(null);
		panel_6.setBounds(10, 62, 537, 239);
		panel_2.add(panel_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "FTP Ayar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setLayout(null);
		panel_7.setBounds(10, 24, 517, 107);
		panel_6.add(panel_7);
		
		JLabel lblNewLabel_5 = new JLabel("Host");
		lblNewLabel_5.setBounds(10, 25, 46, 14);
		panel_7.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Kullanici");
		lblNewLabel_6.setBounds(10, 52, 46, 14);
		panel_7.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Sifre");
		lblNewLabel_7.setBounds(10, 78, 46, 14);
		panel_7.add(lblNewLabel_7);
		
		textField_1 = new JTextField();
		textField_1.setBounds(88, 22, 158, 20);
		panel_7.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(88, 50, 158, 20);
		panel_7.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setText("");
		textField_3.setBounds(88, 76, 158, 20);
		panel_7.add(textField_3);
		textField_3.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Diger Ayarlar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(10, 142, 517, 90);
		panel_8.setLayout(null);
		panel_6.add(panel_8);
		
		JLabel lblNewLabel_8 = new JLabel("Sunucu");
		lblNewLabel_8.setBounds(10, 24, 65, 14);
		panel_8.add(lblNewLabel_8);
		
		textField_4 = new JTextField();
		textField_4.setBounds(88, 21, 222, 20);
		panel_8.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Zaman Asimi");
		lblNewLabel_9.setBounds(10, 56, 75, 14);
		panel_8.add(lblNewLabel_9);
		
		textField_5 = new JTextField();
		textField_5.setBounds(88, 53, 55, 20);
		panel_8.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Port");
		lblNewLabel_10.setBounds(166, 56, 46, 14);
		panel_8.add(lblNewLabel_10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(222, 52, 86, 20);
		panel_8.add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("Surucu Kontrol");
		btnNewButton_5.setBounds(398, 52, 109, 23);
		panel_8.add(btnNewButton_5);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Yerel Surucu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(10, 305, 537, 75);
		panel_9.setLayout(null);
		panel_2.add(panel_9);
		
		JLabel lblNewLabel_11 = new JLabel("Surucu");
		lblNewLabel_11.setBounds(10, 21, 46, 14);
		panel_9.add(lblNewLabel_11);
		
		textField_7 = new JTextField();
		textField_7.setBounds(100, 18, 345, 20);
		panel_9.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.setBounds(481, 17, 46, 23);
		panel_9.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Surucu Sec");
		btnNewButton_7.setBounds(100, 44, 89, 23);
		panel_9.add(btnNewButton_7);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(null, "Eski Yedek", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBounds(10, 391, 537, 49);
		panel_10.setLayout(null);
		panel_2.add(panel_10);
		
		JLabel lblNewLabel_12 = new JLabel("Eski Yedekleri Silme");
		lblNewLabel_12.setBounds(10, 24, 108, 14);
		panel_10.add(lblNewLabel_12);
		
		textField_8 = new JTextField();
		textField_8.setBounds(128, 21, 86, 20);
		panel_10.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("gunden eski olanlar(0 Silinmez)");
		lblNewLabel_13.setBounds(224, 24, 209, 14);
		panel_10.add(lblNewLabel_13);
		
		JButton btnNewButton_3 = new JButton("Baglanti Test");
		btnNewButton_3.setBounds(20, 452, 117, 23);
		panel_2.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Kaydet");
		btnNewButton_4.setBounds(458, 451, 89, 23);
		panel_2.add(btnNewButton_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		tabbedPane.addTab("Bilgilendirme", null, panel_3, null);
		
		JLabel lblNewLabel_14 = new JLabel("Durum");
		lblNewLabel_14.setBounds(30, 22, 46, 14);
		panel_3.add(lblNewLabel_14);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Aktif / Pasif");
		chckbxNewCheckBox_3.setBounds(100, 18, 153, 23);
		panel_3.add(chckbxNewCheckBox_3);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(null, "Gonderme Durumu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_11.setBounds(30, 60, 530, 61);
		panel_11.setLayout(null);
		panel_3.add(panel_11);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Gonderildiginde");
		chckbxNewCheckBox_4.setBounds(46, 26, 152, 23);
		panel_11.add(chckbxNewCheckBox_4);
		
		JCheckBox chckbxNewCheckBox_5 = new JCheckBox("Hata Durumunda");
		chckbxNewCheckBox_5.setBounds(286, 26, 152, 23);
		panel_11.add(chckbxNewCheckBox_5);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(null, "Mail Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_12.setBounds(30, 132, 530, 144);
		panel_12.setLayout(null);
		panel_3.add(panel_12);
		
		JLabel lblNewLabel_15 = new JLabel("Gonderen Isim");
		lblNewLabel_15.setBounds(10, 23, 76, 14);
		panel_12.add(lblNewLabel_15);
		
		textField_9 = new JTextField();
		textField_9.setBounds(106, 20, 325, 20);
		panel_12.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(106, 51, 325, 20);
		panel_12.add(textField_10);
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setBounds(106, 82, 325, 20);
		panel_12.add(textField_11);
		textField_11.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setBounds(106, 113, 325, 20);
		panel_12.add(textField_12);
		textField_12.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Gonderen Adres");
		lblNewLabel_16.setBounds(10, 54, 92, 14);
		panel_12.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("Alici");
		lblNewLabel_17.setBounds(10, 85, 46, 14);
		panel_12.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("Konu");
		lblNewLabel_18.setBounds(10, 116, 46, 14);
		panel_12.add(lblNewLabel_18);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(null, "Server Ayarlari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_13.setBounds(30, 287, 530, 144);
		panel_13.setLayout(null);
		panel_3.add(panel_13);
		
		textField_13 = new JTextField();
		textField_13.setBounds(109, 22, 103, 20);
		panel_13.add(textField_13);
		textField_13.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setBounds(346, 22, 86, 20);
		panel_13.add(textField_14);
		textField_14.setColumns(10);
		
		textField_15 = new JTextField();
		textField_15.setBounds(109, 53, 323, 20);
		panel_13.add(textField_15);
		textField_15.setColumns(10);
		
		textField_16 = new JTextField();
		textField_16.setBounds(109, 84, 323, 20);
		panel_13.add(textField_16);
		textField_16.setColumns(10);
		
		JCheckBox chckbxNewCheckBox_6 = new JCheckBox("SSL");
		chckbxNewCheckBox_6.setBounds(109, 111, 97, 23);
		panel_13.add(chckbxNewCheckBox_6);
		
		JCheckBox chckbxNewCheckBox_7 = new JCheckBox("TSL");
		chckbxNewCheckBox_7.setBounds(248, 111, 63, 23);
		panel_13.add(chckbxNewCheckBox_7);
		
		JLabel lblNewLabel_19 = new JLabel("SMTP Server");
		lblNewLabel_19.setBounds(10, 25, 89, 14);
		panel_13.add(lblNewLabel_19);
		
		JLabel lblNewLabel_20 = new JLabel("Kullanici");
		lblNewLabel_20.setBounds(10, 56, 89, 14);
		panel_13.add(lblNewLabel_20);
		
		JLabel lblNewLabel_21 = new JLabel("Sifre");
		lblNewLabel_21.setBounds(10, 87, 89, 14);
		panel_13.add(lblNewLabel_21);
		
		JLabel lblNewLabel_22 = new JLabel("SMTP Port");
		lblNewLabel_22.setBounds(273, 25, 63, 14);
		panel_13.add(lblNewLabel_22);
		
		JButton btnNewButton_8 = new JButton("Deneme");
		btnNewButton_8.setBounds(346, 111, 86, 23);
		panel_13.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Kayit");
		btnNewButton_9.setBounds(471, 452, 89, 23);
		panel_3.add(btnNewButton_9);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		tabbedPane.addTab("Yedekleme Araligi", null, panel_4, null);
		
		JLabel lblNewLabel_23 = new JLabel("Her");
		lblNewLabel_23.setBounds(36, 32, 46, 14);
		panel_4.add(lblNewLabel_23);
		
		textField_17 = new JTextField();
		textField_17.setBounds(118, 29, 46, 20);
		panel_4.add(textField_17);
		textField_17.setColumns(10);
		
		JLabel lblNewLabel_24 = new JLabel("Dakkada bir");
		lblNewLabel_24.setBounds(189, 32, 105, 14);
		panel_4.add(lblNewLabel_24);
		
		JLabel lblNewLabel_25 = new JLabel("Gunler");
		lblNewLabel_25.setBounds(36, 79, 46, 14);
		panel_4.add(lblNewLabel_25);
		
		JCheckBox chckbxNewCheckBox_8 = new JCheckBox("Pazartesi");
		chckbxNewCheckBox_8.setBounds(118, 75, 97, 23);
		panel_4.add(chckbxNewCheckBox_8);
		
		JCheckBox chckbxNewCheckBox_9 = new JCheckBox("Sali");
		chckbxNewCheckBox_9.setBounds(118, 101, 97, 23);
		panel_4.add(chckbxNewCheckBox_9);
		
		JCheckBox chckbxNewCheckBox_10 = new JCheckBox("Carsamba");
		chckbxNewCheckBox_10.setBounds(118, 127, 97, 23);
		panel_4.add(chckbxNewCheckBox_10);
		
		JCheckBox chckbxNewCheckBox_11 = new JCheckBox("Persembe");
		chckbxNewCheckBox_11.setBounds(118, 153, 97, 23);
		panel_4.add(chckbxNewCheckBox_11);
		
		JCheckBox chckbxNewCheckBox_12 = new JCheckBox("Cuma");
		chckbxNewCheckBox_12.setBounds(118, 179, 97, 23);
		panel_4.add(chckbxNewCheckBox_12);
		
		JCheckBox chckbxNewCheckBox_13 = new JCheckBox("Cumartesi");
		chckbxNewCheckBox_13.setBounds(118, 205, 97, 23);
		panel_4.add(chckbxNewCheckBox_13);
		
		JCheckBox chckbxNewCheckBox_14 = new JCheckBox("Pazar");
		chckbxNewCheckBox_14.setBounds(118, 231, 97, 23);
		panel_4.add(chckbxNewCheckBox_14);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new TitledBorder(null, "Yedekleme Araligi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_14.setBounds(36, 299, 383, 122);
		panel_14.setLayout(null);
		panel_4.add(panel_14);
		
		JLabel lblNewLabel_26 = new JLabel("Baslangic");
		lblNewLabel_26.setBounds(10, 31, 76, 14);
		panel_14.add(lblNewLabel_26);
		
		JLabel lblNewLabel_27 = new JLabel("Bitis");
		lblNewLabel_27.setBounds(10, 56, 46, 14);
		panel_14.add(lblNewLabel_27);
		
		JSpinner spinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinner, "mm");
		spinner.setEditor(timeEditor);
		Date d = new Date();
		  d.setHours(0);
		  d.setMinutes(0);
		spinner.setValue(d); 
		spinner.setBounds(119, 28, 52, 20);
		panel_14.add(spinner);
		
		JSpinner spinner_1 = new JSpinner( new SpinnerDateModel() );
		 timeEditor = new JSpinner.DateEditor(spinner_1, "mm");
		spinner_1.setEditor(timeEditor);
		  d.setHours(0);
		  d.setMinutes(0);
		spinner_1.setValue(d); 
		spinner_1.setBounds(119, 53, 52, 20);
		panel_14.add(spinner_1);
		
		JButton btnNewButton_10 = new JButton("Kayit");
		btnNewButton_10.setBounds(471, 452, 89, 23);
		panel_4.add(btnNewButton_10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		tabbedPane.addTab("Emir Kopyala", null, panel_5, null);

	}
	@SuppressWarnings("rawtypes")
	public static void dbDOLDUR (String ipp , String user , String pwd) throws ClassNotFoundException, SQLException
	{
		try
		{
			ResultSet rss = null;
			DefaultListModel<CheckListItem> demoList = new DefaultListModel<CheckListItem>();
			demoList.removeAllElements();
			String hangisql = cmbSQL.getSelectedItem().toString();
			if(hangisql.toString().equals("MS SQL"))
			{
				VT_ANA_CLASS._IConn = new OBS_ORTAK_MSSQL();
				rss = sqll.msSQLDB(ipp,user,pwd);
				while (rss.next()) 
				{
					demoList.addElement( new CheckListItem(rss.getString("name")));
				}
			}
			else
			{
				VT_ANA_CLASS._IConn = new OBS_ORTAK_MYSQL();
				rss = sqll.mySQLDB(ipp,user,pwd);
				while (rss.next()) 
				{
					demoList.addElement( new CheckListItem(rss.getString("Database")));
				}
			}
			list.setModel(demoList);
		} 
		catch (Exception e1)
		{
			DefaultListModel listModel = (DefaultListModel) list.getModel();
	        listModel.removeAllElements();
			e1.printStackTrace();
		}	
	}
	@SuppressWarnings("rawtypes")
	public static void serBILGILERI() throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, SQLException
	{
		try
		{
			ResultSet rss = sqll.serBILGI(VT_ANA_CLASS.EMIR_ADI);
			if (!rss.isBeforeFirst() ) {  
				DefaultListModel listModel = (DefaultListModel) list.getModel();
				listModel.removeAllElements();
			}
			else
			{
				cmbSQL.setSelectedItem(rss.getString("HANGI_SQL").toString());
				dbDOLDUR(rss.getString("INSTANCE") , rss.getString("KULLANICI"), ENCRYPT_DECRYPT_STRING.dCRYPT_manual(rss.getBytes("SIFRE")));
			}
		} 
	catch (Exception e1)
	{
		DefaultListModel listModel = (DefaultListModel) list.getModel();
        listModel.removeAllElements();
		e1.printStackTrace();
	}	
	}
}
@SuppressWarnings({ "serial", "rawtypes" })
class CheckListRenderer extends JCheckBox implements ListCellRenderer {
	  public Component getListCellRendererComponent(JList list, Object value,
	      int index, boolean isSelected, boolean hasFocus) {
	    setEnabled(list.isEnabled());
	    setSelected(((CheckListItem) value).isSelected());
	    setFont(list.getFont());
	    setBackground(list.getBackground());
	    setForeground(list.getForeground());
	    setText(value.toString());
	    return this;
	  }
	}
class CheckListItem {

	  private String label;
	  private boolean isSelected = false;

	  public CheckListItem(String label) {
	    this.label = label;
	  }

	  public boolean isSelected() {
	    return isSelected;
	  }

	  public void setSelected(boolean isSelected) {
	    this.isSelected = isSelected;
	  }

	  @Override
	  public String toString() {
	    return label;
	  }
	}

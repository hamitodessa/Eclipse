package OBS_PACKAGE;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.proteanit.sql.DbUtils;

import javax.swing.event.ChangeEvent;
import javax.swing.ListSelectionModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MAIL extends JInternalFrame {
	
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	private static JTable table;
	private JTextField txtgonderen;
	private JTextField txtgonadi;
	private static JTextField txtalici;
	private JTextField txtkonu;
	private JTable table_1;
	private static JTextField txtmail;
	private static JTextField txtunvan;
	private static JTextField txtkodu;
	private static JTable table_2;
	private static JCheckBox chcdurum ;
	private static JComboBox<String> comboBox_2  ;
	private static JLabel lblNewLabel_12 ;
	private JComboBox<String> comboBox_1 ;
	private JComboBox<String> comboBox ;
	private JTextArea txtaciklama  ;
	
	private String HESAP;
	private String HOST;
	private String  PORT;
	private String  SIFR;
	private boolean  SSL;
	private boolean  TSL;
	private static JTabbedPane tabbedPane ;
	private static JCheckBox chckbxNewCheckBox ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MAIL frame = new MAIL();
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
	public MAIL() {
		setTitle("MAIL");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 1000, 600);
		
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
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(0, 0, 128));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblNewLabel_12.setText("0");
				if (tabbedPane.getSelectedIndex()== 1)
					{
						giden_doldur();
					}
				else if (tabbedPane.getSelectedIndex()== 2)
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
		tabbedPane.addTab("Mail Gonder", null, splitPane_1, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_1.setMinimumSize(new Dimension(0, 160));
		panel_1.setMaximumSize(new Dimension(0, 160));
		splitPane_1.setLeftComponent(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gonderen Mail");
		lblNewLabel.setBounds(10, 11, 100, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Gonderen Isim");
		lblNewLabel_1.setBounds(10, 35, 86, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Alici");
		lblNewLabel_2.setBounds(10, 60, 46, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Konu");
		lblNewLabel_3.setBounds(10, 85, 46, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Aciklama");
		lblNewLabel_4.setBounds(10, 110, 86, 14);
		panel_1.add(lblNewLabel_4);
		
		txtgonderen = new JTextField();
		txtgonderen.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtgonderen.setDocument(new JTextFieldLimit(50));
		txtgonderen.setBounds(120, 8, 258, 20);
		panel_1.add(txtgonderen);
		txtgonderen.setColumns(10);
		
		txtgonadi = new JTextField();
		txtgonadi.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtgonadi.setBounds(120, 33, 258, 20);
		txtgonadi.setDocument(new JTextFieldLimit(50));
		panel_1.add(txtgonadi);
		txtgonadi.setColumns(10);
		
		txtalici = new JTextField();
		txtalici.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtgonderen.setDocument(new JTextFieldLimit(50));
		txtalici.setBounds(120, 58, 258, 20);
		panel_1.add(txtalici);
		txtalici.setColumns(10);
		
		txtkonu = new JTextField();
		txtkonu.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtkonu.setBounds(120, 83, 365, 20);
		txtkonu.setDocument(new JTextFieldLimit(50));
		panel_1.add(txtkonu);
		txtkonu.setColumns(10);
		
		txtaciklama = new JTextArea();
		txtaciklama.setFont(new Font("Monospaced", Font.BOLD, 13));
		txtaciklama.setBounds(120, 108, 365, 41);
		txtaciklama.setLineWrap(true);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		txtaciklama.setBorder(BorderFactory.createCompoundBorder(border,
	    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		txtaciklama.setDocument(new JTextFieldLimit(100));
		panel_1.add(txtaciklama);
		
		JLabel lblNewLabel_5 = new JLabel("Alinacak Bilgiler");
		lblNewLabel_5.setBounds(565, 4, 100, 14);
		panel_1.add(lblNewLabel_5);
		
		comboBox = new JComboBox<String>();
		comboBox.setForeground(new Color(0, 0, 128));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Dosyadan", "Cari Hesap", "Adres"}));
		comboBox.setBounds(565, 25, 121, 22);
		panel_1.add(comboBox);
		
		JButton btnNewButton = new JButton("Dosya Ekle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle("Surucu Seciniz");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);
			    chooser.setApproveButtonText("Dosya Sec");
			    chooser.setApproveButtonToolTipText("Dosya Sec");
			    chooser.setApproveButtonMnemonic('s');
			    getContentPane().setCursor(DEFAULT_CURSOR);
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			    		 comboBox_1.addItem(chooser.getSelectedFile().toString());
  			       }
			      else {
			        }
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(MAIL.class.getResource("/ICONLAR/icons8-add-folder-16.png")));
		btnNewButton.setBounds(704, 25, 129, 23);
		panel_1.add(btnNewButton);
		
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setForeground(new Color(0, 0, 128));
		comboBox_1.setBounds(565, 56, 278, 22);
		comboBox_1.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List droppedFiles = (List) evt .getTransferable().getTransferData(  DataFlavor.javaFileListFlavor);
                    if(droppedFiles.size() > 1){
                        JOptionPane.showMessageDialog(null,  "Tek Seferde 1 Dosya Ekleyebilirsiniz.....!!", "Dosya Ekleme", JOptionPane.PLAIN_MESSAGE);
                    }
                    else{
                        File droppedFile = (File) droppedFiles.get(0);
                        comboBox_1.addItem(droppedFile.getName());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,  ex.getMessage(), "Dosya Ekleme", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });	
		panel_1.add(comboBox_1);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_1.getModel().getSize() != 0) {
                comboBox_1.removeItemAt(comboBox_1.getSelectedIndex());
			}
		}
		});
		btnNewButton_1.setIcon(new ImageIcon(MAIL.class.getResource("/ICONLAR/sil.png")));
		btnNewButton_1.setBounds(843, 55, 30, 24);
		panel_1.add(btnNewButton_1);
		
		chckbxNewCheckBox = new JCheckBox("Coklu Gonderim");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if ( chckbxNewCheckBox.isSelected())
				 {
					lblNewLabel_12.setText("0");
					txtalici.setEnabled(false);
		            isim_doldur();
				 }
				 else
				 {
					 txtalici.setEnabled(true);
					 GRID_TEMIZLE.grid_temizle(table);
					 lblNewLabel_12.setText("0");
				 }
			}
		});
		chckbxNewCheckBox.setBounds(758, 100, 129, 23);
		panel_1.add(chckbxNewCheckBox);
		
		JButton btnNewButton_2 = new JButton("Gonder");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( chckbxNewCheckBox.isSelected()) //' Coklu gonderim
				{
					getContentPane().setCursor(WAIT_CURSOR);
				        coklu_gonder();
				    getContentPane().setCursor(DEFAULT_CURSOR);
				}
				else  //' Tek Gonderim
				{
				   getContentPane().setCursor(WAIT_CURSOR);    
				     tek_gonder();
				   getContentPane().setCursor(DEFAULT_CURSOR);  
				}
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(MAIL.class.getResource("/ICONLAR/mail-16.png")));
		btnNewButton_2.setBounds(761, 126, 112, 23);
		panel_1.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane_1.setRightComponent(scrollPane);
		
		table = new JTable();
		table.setGridColor(oac.gridcolor);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		scrollPane.setViewportView(table);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setResizeWeight(0.0);
		splitPane_2.setDividerSize(0);
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		tabbedPane.addTab("Gonderilmis Mail", null, splitPane_2, null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_2.setMinimumSize(new Dimension(0, 30));
		panel_2.setMaximumSize(new Dimension(0, 30));
		splitPane_2.setLeftComponent(panel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_2.setRightComponent(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setGridColor(oac.gridcolor);
		table_1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		scrollPane_1.setViewportView(table_1);
		
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
		
		JLabel lblNewLabel_6 = new JLabel("Mail");
		lblNewLabel_6.setBounds(10, 11, 46, 14);
		panel_3.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Durum");
		lblNewLabel_7.setBounds(10, 36, 46, 14);
		panel_3.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Unvan");
		lblNewLabel_8.setBounds(10, 61, 46, 14);
		panel_3.add(lblNewLabel_8);
		
		txtmail = new JTextField();
		txtmail.setBounds(81, 8, 283, 20);
		txtmail.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				boolean sonuc = false;
				if (CONNECTION.smsdizinbilgi.han_sql.equals("MS SQL"))
				{
					try {
						sonuc = oac.sMS_MSSQL.kod_ismi(txtmail.getText());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					try {
						sonuc = oac.sMS_MYSQL.kod_ismi(txtmail.getText());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if ( sonuc )
						{
		            
		            txtmail.setForeground(Color.red);
						}
				else
				{
					txtmail.setForeground(Color.black);
					
				}
			}
		});
		txtmail.setDocument(new JTextFieldLimit(50));
		panel_3.add(txtmail);
		txtmail.setColumns(10);
		
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
		
		JScrollPane scrollPane_2 = new JScrollPane();
		splitPane_3.setRightComponent(scrollPane_2);
		
		table_2 = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table_2.setGridColor(oac.gridcolor);
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getContentPane().setCursor(WAIT_CURSOR);
				kutu_temizle();
				doldur_kutu(table_2.getSelectedRow());
				getContentPane().setCursor(DEFAULT_CURSOR);
			}
		});
		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_2.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		scrollPane_2.setViewportView(table_2);
		
		smtp_bak ();

	}
	private static void giris_doldur()
	{
		try {
			ResultSet rs = null ;
			 GRID_TEMIZLE.grid_temizle(table);
			long startTime = System.currentTimeMillis(); 
			if (CONNECTION.smsdizinbilgi.han_sql.equals("MS SQL"))
		    {
				rs = oac.sMS_MSSQL.mail_giris_bak();
		    }
			else
			{
				rs = oac.sMS_MYSQL.mail_giris_bak();
			}

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
			table_2.setSelectionBackground(Color.PINK);
			table_2.setSelectionForeground(Color.BLUE);
			 long endTime = System.currentTimeMillis();
			 long estimatedTime = endTime - startTime; 
			 double seconds = (double)estimatedTime/1000; 
			 OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			 
			lblNewLabel_12.setText(Integer.toString(table_2.getRowCount()));
			//******* GRUP DOLDUR
			comboBox_2.removeAllItems();
			comboBox_2.addItem("");
			if (CONNECTION.smsdizinbilgi.han_sql.equals("MS SQL"))
		    {
				rs = oac.sMS_MSSQL.mail_grup_bak();
		    }
			else
			{
				rs = oac.sMS_MYSQL.mail_grup_bak();
			}
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
			 JOptionPane.showMessageDialog(null, ex.getMessage());   
		}
	}
	private static void kutu_temizle() 
	{
		txtmail.setText("");
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
			txtmail.setText(table_2.getModel().getValueAt(satir, 0).toString());
			txtunvan.setText(table_2.getModel().getValueAt(satir, 1).toString());
			
			comboBox_2.setSelectedItem(table_2.getModel().getValueAt(satir, 2).toString());
			txtkodu.setText(table_2.getModel().getValueAt(satir, 3).toString());
			chcdurum.setSelected((boolean) table_2.getModel().getValueAt(satir, 4));
	}
	public static void yeni ()
	{
		if (tabbedPane.getSelectedIndex() != 2) return ;
		kutu_temizle();
		txtmail.requestFocus();
	}
	public static void giris_kayit()
	{
		if (tabbedPane.getSelectedIndex() != 2) return ;
	        if (txtmail.getText().equals("")) {
	            txtmail.requestFocus();
	           return ;
	        }
	        try {
	        	if (CONNECTION.smsdizinbilgi.han_sql.equals("MS SQL"))
			    {
					 oac.sMS_MSSQL.mail_giris_sil(txtmail.getText());
					 oac.sMS_MSSQL.mail_giris_yaz(txtmail.getText(), txtunvan.getText(),
							 comboBox_2.getSelectedItem().toString(),
							 txtkodu.getText(), chcdurum.isSelected(), GLOBAL.KULL_ADI);
			    }
				else
				{
					 oac.sMS_MYSQL.mail_giris_sil(txtmail.getText());
					 oac.sMS_MYSQL.mail_giris_yaz(txtmail.getText(), txtunvan.getText(),
							 comboBox_2.getSelectedItem().toString(),
							 txtkodu.getText(), chcdurum.isSelected(), GLOBAL.KULL_ADI);
				}
	            kutu_temizle();
	            giris_doldur();
	        }
	        catch (Exception ex)
	        {
        	JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kayit Giris", JOptionPane.PLAIN_MESSAGE);			        }
			}
	public static void sil()
	{
		if (tabbedPane.getSelectedIndex() != 2) return ;
		if (txtmail.getText().equals("")) {
            txtmail.requestFocus();
           return ;
        }
		int g =  JOptionPane.showOptionDialog( null,  "Kisi Silinecek ..?"  ,	 "Mail Silme",   JOptionPane.YES_NO_OPTION, 	JOptionPane.QUESTION_MESSAGE,	null,     //no custom icon
   			 	oac.options,  //button titles
   			 	oac.options[1]); //default button
	 	 if(g != 0 ) { return;	}
	        try {
        	if (CONNECTION.smsdizinbilgi.han_sql.equals("MS SQL"))
		    {
				 oac.sMS_MSSQL.mail_giris_sil(txtmail.getText());
		    }
			else
			{
				 oac.sMS_MYSQL.mail_giris_sil(txtmail.getText());
			}
        	kutu_temizle();
            giris_doldur();
	        }
	        catch (Exception ex)
	        {
        	 JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kayit Silme", JOptionPane.PLAIN_MESSAGE);		        
        	 }
		}
	private void isim_doldur() 
	{
		try {
		ResultSet rs = null ;
	        if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Cari Hesap"))
	        {
	        	if (CONNECTION.caridizinbilgi.han_sql.equals("MS SQL"))
	    		{
	    		rs = oac.cARI_HESAP_MSSQL.sms_cari_pln("E_MAIL");
	    		}
	    		else
	    		{
	    		rs = oac.cARI_HESAP_MYSQL.sms_cari_pln("E_MAIL");
	    		}
	        }
	        else if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Adres"))
       		{
	        	if (CONNECTION.adrdizinbilgi.han_sql.equals("MS SQL"))
    		    {
    				rs = oac.aDRES_MSSQL.sms_adr_hpl("E_Mail");
    		    }
    			else
    			{
    				rs = oac.aDRES_MYSQL.sms_adr_hpl("E_Mail");
    			}
	        }
	        else if(comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Dosyadan"))
	        {
	        	if (CONNECTION.adrdizinbilgi.han_sql.equals("MS SQL"))
    		    {
    				rs = oac.sMS_MSSQL.mail_alici_doldur();
    		    }
    			else
    			{
    				rs = oac.sMS_MYSQL.mail_alici_doldur();
    			}
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
			table.setSelectionBackground(Color.PINK);
			table.setSelectionForeground(Color.BLUE);
			
			lblNewLabel_12.setText(Integer.toString(table.getRowCount()));
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(DEFAULT_CURSOR);
			 JOptionPane.showMessageDialog(null,  ex.getMessage(), "Alici Doldurma", JOptionPane.PLAIN_MESSAGE);	
		}
	}
	private void coklu_gonder()
	{
		 if ( ! ValidEmailAddress.isValid(txtgonderen.getText()))
		 {
		 txtgonderen.requestFocus();
		 return ;
		 }
		 if (txtkonu.getText().equals(""))
		 {
    	 JOptionPane.showMessageDialog(null,  "Konu Bos...",  "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);	
          txtkonu.requestFocus();
         return ;
		 }
		 if (txtaciklama.getText().equals(""))
		 {
    	 JOptionPane.showMessageDialog(null,  "Aciklama Bos...",  "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);	
          txtaciklama.requestFocus();
        return ;
		 }
		 if ( !smtp_bak()  ) // 'SMTP bilgileri yok
  		{
  		getContentPane().setCursor(DEFAULT_CURSOR);
     	JOptionPane.showMessageDialog(null,  "SMTP Bilgilerine Ulasilamadi ...",  "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);	
         return ;
  		}
		 Runnable runner = new Runnable()
		 { public void run() { 
    	Progres_Bar_Temizle();
		 OBS_MAIN.progressBar.setStringPainted(true);
		 DefaultTableModel model = (DefaultTableModel) table.getModel();
		 int say = 0 ;
		 OBS_MAIN.progressBar.setMaximum(model.getRowCount() - 1);
		 for (int i = 0 ; i <= model.getRowCount()  - 1 ; i ++)
     	{
			 	if ( ! ValidEmailAddress.isValid(model.getValueAt(i, 0).toString()))
			 	{  
			 		model.setValueAt("Gecersiz....",i, 5) ;  // ' Gecersiz Email
			 	}
			 	else
			 	{
			 		say += 1;
			 		send_mail(model.getValueAt(i, 0).toString());
			 		Date zaman = new Date();
			 		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");  
			 		model.setValueAt( dateFormat.format(zaman),i, 5) ;
			 	}
    	}
		 Thread.currentThread().isInterrupted();
         Progres_Bar_Temizle();
		 getContentPane().setCursor(DEFAULT_CURSOR);
     	JOptionPane.showMessageDialog(null,  say + " Adet Mailiniz Gonderildi   ",  "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);	
     	
	 }
		    };
		    //// Progress Bar
		    Thread t = new Thread(runner, "Code Executer");
		    t.start();
	}
	private void tek_gonder()
	{
		 if ( ! ValidEmailAddress.isValid(txtgonderen.getText()))
		 {
			 txtgonderen.requestFocus();
		 return ;
		 }
		 if ( ! ValidEmailAddress.isValid(txtalici.getText()))
		 {
			 txtalici.requestFocus();
		 return ;
		 }
		 if (txtkonu.getText().equals(""))
		 {	
			 getContentPane().setCursor(DEFAULT_CURSOR);
    	 JOptionPane.showMessageDialog(null,  "Konu Bos...",  "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);	
          txtkonu.requestFocus();
        return ;
     	}
		 if (txtaciklama.getText().equals(""))
		 {
		 getContentPane().setCursor(DEFAULT_CURSOR);
    	 JOptionPane.showMessageDialog(null,  "Aciklama Bos...",  "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);	
         txtaciklama.requestFocus();
        return ;
		 }
     	try
     	{
     		if ( !smtp_bak()  ) // 'SMTP bilgileri yok
     		{
     			getContentPane().setCursor(DEFAULT_CURSOR);
        	JOptionPane.showMessageDialog(null,  "SMTP Bilgilerine Ulasilamadi ...",  "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);	
            return ;
     		}
     		send_mail(txtalici.getText()) ;
     		getContentPane().setCursor(DEFAULT_CURSOR);
     		JOptionPane.showMessageDialog(null,   "Mailiniz Gonderildi   ",  "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);	
     	}
     	catch (Exception ex)
     	{
    	 getContentPane().setCursor(DEFAULT_CURSOR);
         JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);	
     	}
	}
	private boolean smtp_bak ()
	{
		boolean result = false ;
		try {
		 ResultSet rs = null;
		 rs= oac.glb.mail_bak(GLOBAL.KULL_ADI);
		 if (!rs.isBeforeFirst() ) {  result = false;} 
	        else {
	        HESAP = rs.getString("HESAP");
	        HOST = rs.getString("HOST");
	        PORT = rs.getString("PORT");
	        SIFR = rs.getString("SIFR");
	        SSL = rs.getBoolean("SSL");
	        TSL = rs.getBoolean("TSL");
	        txtgonderen.setText(rs.getString("GON_MAIL"));
	        txtgonadi.setText(rs.getString("GON_ISIM"));
            result = true ;
	        }
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(DEFAULT_CURSOR);
		    JOptionPane.showMessageDialog(null,  ex.getMessage(),  "SMTP Bilgisi Okuma", JOptionPane.PLAIN_MESSAGE);		
		}
		return result;
	}
	private void send_mail(String alici)
	{
		try
		{
			   MimeBodyPart messagePart = null ;
		       String[] to = { alici };
			   Properties props = System.getProperties();
			   props.put("mail.smtp.starttls.enable", TSL);
			   if (SSL)
			   {
				   props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
				   //props.put("mail.smtp.startsls.enable", SSL);
			   }
			   props.put("mail.smtp.host", HOST);
			   props.put("mail.smtp.user", HESAP);
			   props.put("mail.smtp.password", SIFR);
			   props.put("mail.smtp.port", PORT);
			   props.put("mail.smtp.auth", "true");
			   Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(HESAP, SIFR);
                   }
               });
			   MimeMessage message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(txtgonderen.getText(),txtgonadi.getText()));
			   InternetAddress[] toAddress = new InternetAddress[to.length];
			   for (int i = 0; i < to.length; i++) {
			    toAddress[i] = new InternetAddress(to[i]);
			   }
			   for (int i = 0; i < toAddress.length; i++) {
			    message.setRecipient(RecipientType.TO,  toAddress[i]);
			   }
			   messagePart = new MimeBodyPart();
	           messagePart.setText(txtaciklama.getText(),"UTF-8");
		       Multipart multipart = new MimeMultipart();
			   //*****
		       if (comboBox_1.getModel().getSize() != 0) 
		       {
			   for (int i =0; i< comboBox_1.getModel().getSize(); i++)
	            {
	                try {
	                	 String dosya =  comboBox_1.getItemAt(i);
	                	 MimeBodyPart att = new MimeBodyPart();
	                	 att.attachFile(dosya.toString());
	                	 multipart.addBodyPart(att);
	                } catch (IOException ex) {
	                    throw ex;
	                }
	            } 
		       }
			   //*****
	           multipart.addBodyPart(messagePart);
	           message.setSubject(txtkonu.getText(), "UTF-8");
			   message.setContent(multipart);
			   Transport.send(message);
			   //**********************Raporlama Dosyasina Yaz ***************************
			   Date zaman = new Date();
			   DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");  
			   if (CONNECTION.smsdizinbilgi.han_sql.equals("MS SQL"))
			    {
				   oac.sMS_MSSQL.giden_rapor_yaz(GLOBAL.KULL_ADI,dateFormat.format(zaman)  ,txtaciklama.getText(),alici,"", 	"",	txtgonderen.getText(),txtkonu.getText()) ;
			    }
			   else
			   {
				   oac.sMS_MYSQL.giden_rapor_yaz(GLOBAL.KULL_ADI,dateFormat.format(zaman)  ,txtaciklama.getText(),alici,"", 	"",	txtgonderen.getText(),txtkonu.getText()) ;
			   }
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(DEFAULT_CURSOR);
		    JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);	
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
			 txtalici.setEnabled(true);
		 }
	}
	private void giden_doldur()
	{
		 try {
		 ResultSet rs = null;
		 long startTime = System.currentTimeMillis(); 
		 getContentPane().setCursor(WAIT_CURSOR);
         if (GLOBAL.KULL_ADI.equals("Admin"))
         {
        	 if (CONNECTION.smsdizinbilgi.han_sql.equals("MS SQL"))
			    {
     		 rs = oac.sMS_MSSQL.mail_giden_bak("");
			    }
        	 else
        	 {
     		 rs = oac.sMS_MYSQL.mail_giden_bak("");
        	 }
         }
         else
         {
        	 if (CONNECTION.smsdizinbilgi.han_sql.equals("MS SQL"))
			    {
        		 rs = oac.sMS_MSSQL.mail_giden_bak("  WHERE USER_NAME = '" + GLOBAL.KULL_ADI.toString()  +  "' ");
			    }
        	 else
        	 {
        		 rs = oac.sMS_MYSQL.mail_giden_bak("  WHERE USER_NAME = '" + GLOBAL.KULL_ADI.toString()  +  "' ");
        	 }
         }
         if (!rs.isBeforeFirst() ) {  
        	 getContentPane().setCursor(DEFAULT_CURSOR);
        	     GRID_TEMIZLE.grid_temizle(table_1);
	        	lblNewLabel_12.setText("0");
			    return;
			} 
	        GRID_TEMIZLE.grid_temizle(table_1);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table_1.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new TARIH_SAATLI());
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
			tc.setMinWidth(150);
			
			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(80);
			
			Dimension dd = th.getPreferredSize();
		    dd.height = 30;
		    th.setPreferredSize(dd); 
			th.repaint();
			table_1.setRowSelectionInterval(0, 0);
			table_1.setRowHeight(21);
			table_1.setSelectionBackground(Color.PINK);
			table_1.setSelectionForeground(Color.BLUE);
			getContentPane().setCursor(DEFAULT_CURSOR);
			
			lblNewLabel_12.setText(Integer.toString(table_1.getRowCount()));
			
			 long endTime = System.currentTimeMillis();
			 long estimatedTime = endTime - startTime; 
			 double seconds = (double)estimatedTime/1000; 
			 OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
		 }
     catch (Exception ex)
		 {
    	 getContentPane().setCursor(DEFAULT_CURSOR);
		    JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Mail Gonderme", JOptionPane.PLAIN_MESSAGE);	
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

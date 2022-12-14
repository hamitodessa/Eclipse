package OBS_PACKAGE;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


@SuppressWarnings("serial")
public class USER_DETAY_EKLEME extends JInternalFrame {
	
	private static JSplitPane splitPane;
	private static JScrollPane scrollPane ;
	private static JComboBox<String> comboBox_1;
	private static JComboBox<String> comboBox;
	private static JTextField txtkodu;
	private static JTextField txtskull;
	private static JTextField txtsifre;
	private static JTextField txtins;
	private static JTextField txtip;
	private static JTextField txtdiz;
	private static JTextField txtyer;
	private static JTextField txtdcins;
	private static JLabel lblcdid ;
	private static JTable table_1;
	private static JCheckBox chckbxNewCheckBox;
	private static JCheckBox chckbxNewCheckBox_1;
	
	static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	private static JComboBox<String> comboBox_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					USER_DETAY_EKLEME frame = new USER_DETAY_EKLEME();
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
	public USER_DETAY_EKLEME() {
		
		setResizable(true);
		setTitle("KULLANICI DETAY EKLEME");
		setClosable(true);
		setBounds(0, 0, 1217, 600);
		
		splitPane = new JSplitPane(){
		    private final int location = 100;
		    {
		        setDividerLocation( location );
		    }
		    @Override
		    public int getDividerLocation() {
		        return location ;
		    }
		    @Override
		    public int getLastDividerLocation() {
		        return location ;
		    }
		};
		splitPane.setResizeWeight(0.8);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kullanici");
		lblNewLabel.setBounds(23, 11, 74, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Modul");
		lblNewLabel_1.setBounds(23, 36, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Kodu");
		lblNewLabel_2.setBounds(23, 61, 46, 14);
		panel.add(lblNewLabel_2);
		
		comboBox = new JComboBox<String>();
		comboBox.setForeground(new Color(0, 0, 128));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setBounds(107, 7, 125, 22);
		panel.add(comboBox);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setForeground(new Color(0, 0, 128));
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Cari Hesap", "Fatura", "Kambiyo", "Adres", "Kur", "Sms", "Gunluk"}));
		comboBox_1.setBounds(107, 32, 125, 22);
		panel.add(comboBox_1);
		
		txtkodu = new JTextField();
		txtkodu.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtkodu.setBounds(107, 58, 74, 20);
		panel.add(txtkodu);
		txtkodu.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Server Kullanici");
		lblNewLabel_3.setBounds(250, 11, 97, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Server Sifre");
		lblNewLabel_4.setBounds(250, 36, 74, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Calisma Izni");
		lblNewLabel_5.setBounds(250, 61, 74, 14);
		panel.add(lblNewLabel_5);
		
		txtskull = new JTextField();
		txtskull.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtskull.setBounds(357, 8, 125, 20);
		panel.add(txtskull);
		txtskull.setColumns(10);
		
		txtsifre = new JTextField();
		txtsifre.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtsifre.setBounds(357, 33, 125, 20);
		panel.add(txtsifre);
		txtsifre.setColumns(10);
		
		txtins = new JTextField();
		txtins.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtins.setBounds(581, 8, 125, 20);
		panel.add(txtins);
		txtins.setColumns(10);
		
		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(357, 57, 125, 23);
		panel.add(chckbxNewCheckBox);
		
		JLabel lblNewLabel_6 = new JLabel("Instance");
		lblNewLabel_6.setBounds(505, 11, 66, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Ip");
		lblNewLabel_7.setBounds(505, 36, 46, 14);
		panel.add(lblNewLabel_7);
		
		txtip = new JTextField();
		txtip.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtip.setBounds(581, 33, 125, 20);
		panel.add(txtip);
		txtip.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Calisanmi");
		lblNewLabel_8.setBounds(505, 61, 66, 14);
		panel.add(lblNewLabel_8);
		
		chckbxNewCheckBox_1 = new JCheckBox("");
		chckbxNewCheckBox_1.setBounds(581, 57, 58, 23);
		panel.add(chckbxNewCheckBox_1);
		
		JLabel lblNewLabel_9 = new JLabel("Dizin");
		lblNewLabel_9.setBounds(718, 11, 80, 14);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Yer");
		lblNewLabel_10.setBounds(718, 36, 80, 14);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Dizin Cins");
		lblNewLabel_11.setBounds(718, 61, 80, 14);
		panel.add(lblNewLabel_11);
		
		txtdiz = new JTextField();
		txtdiz.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtdiz.setBounds(800, 8, 314, 20);
		panel.add(txtdiz);
		txtdiz.setColumns(10);
		
		txtyer = new JTextField();
		txtyer.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtyer.setBounds(800, 33, 46, 20);
		panel.add(txtyer);
		txtyer.setColumns(10);
		
		txtdcins = new JTextField();
		txtdcins.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtdcins.setBounds(800, 58, 46, 20);
		panel.add(txtdcins);
		txtdcins.setColumns(10);
		
		lblcdid = new JLabel("");
		lblcdid.setBounds(0, 0, 46, 14);
		lblcdid.setVisible(false);
		
		panel.add(lblcdid);
		
		JLabel lblNewLabel_12 = new JLabel("SQL Cinsi");
		lblNewLabel_12.setBounds(919, 61, 66, 14);
		panel.add(lblNewLabel_12);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setForeground(new Color(0, 0, 128));
		comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"MS SQL", "MY SQL"}));
		comboBox_2.setBounds(995, 57, 119, 22);
		panel.add(comboBox_2);
			String columnheaders[] = { "Kodu", "Kullanici", "Ser.Kullanici" ,"Sifre","Instance", "IP", "Modul" ,
					"Dizin","Yer","Dizin Cins","Izinli Mi" ,"Calisan Mi","SQL Cinsi" ,"ID"};
			DefaultTableModel model = new DefaultTableModel(null,columnheaders);
			table_1 = new JTable(model){
				private static final long serialVersionUID = 1L;
				@Override
				public Class getColumnClass(int column) {
					return String.class;
				}
				public boolean isCellEditable(int row, int column) {     return false;          }
				};
				
				table_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						getContentPane().setCursor(WAIT_CURSOR);
						try {
							kutu_temizle();
							doldur_kutu(table_1.getSelectedRow());
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						getContentPane().setCursor(DEFAULT_CURSOR);
					}
				});
				table_1.setGridColor(oac.gridcolor);
				table_1.setSurrendersFocusOnKeystroke(true);
				table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			  int vColIndex = 0;
			  TableColumn col = table_1.getColumnModel().getColumn(vColIndex);

			  //table.getColumnModel().getColumn(0).setPreferredWidth(195);
			  //col.setPreferredWidth(60);
		      col.setMaxWidth(60);
		      col.setHeaderRenderer(new SOLA());
		      vColIndex = 1;
		      col = table_1.getColumnModel().getColumn(vColIndex);
		      col.setMinWidth(100);
		      col.setHeaderRenderer(new SOLA());
		      vColIndex = 2;
		      col = table_1.getColumnModel().getColumn(vColIndex);
		      col.setMinWidth(130);
		      col.setHeaderRenderer(new SOLA());
		      
		      vColIndex = 3;
		      col = table_1.getColumnModel().getColumn(vColIndex);
		      col.setMinWidth(130);
		      col.setHeaderRenderer(new SOLA());
		      vColIndex = 4;
		      col = table_1.getColumnModel().getColumn(vColIndex);
		      col.setMinWidth(130);
		      col.setHeaderRenderer(new SOLA());
		      vColIndex = 5;
		      col = table_1.getColumnModel().getColumn(vColIndex);
		      col.setMinWidth(110);
		      col.setHeaderRenderer(new SOLA());
		      vColIndex = 6;
		      col = table_1.getColumnModel().getColumn(vColIndex);
		      col.setMinWidth(150);
		      col.setHeaderRenderer(new SOLA());
		      vColIndex = 7;
		      col = table_1.getColumnModel().getColumn(vColIndex);
		      col.setMinWidth(100);
		      col.setHeaderRenderer(new SOLA());
		      vColIndex = 8;
		      col = table_1.getColumnModel().getColumn(vColIndex);
		      col.setMinWidth(40);
		      col.setMaxWidth(40);
		      col.setHeaderRenderer(new SOLA());
		      vColIndex = 9;
		      col = table_1.getColumnModel().getColumn(vColIndex);
		      col.setMaxWidth(90);
		      col.setHeaderRenderer(new SOLA());
		      vColIndex = 10;
		      col = table_1.getColumnModel().getColumn(vColIndex);
		      col.setMinWidth(80);
		      col.setHeaderRenderer(new SOLA());
		      vColIndex = 11;
		      col = table_1.getColumnModel().getColumn(vColIndex);
		      col.setMinWidth(80);
		      col.setHeaderRenderer(new SOLA());
		      vColIndex = 12;
		      col = table_1.getColumnModel().getColumn(vColIndex);
		      col.setMinWidth(1);
		      col.setHeaderRenderer(new SOLA());
		      vColIndex = 13;
		      col = table_1.getColumnModel().getColumn(vColIndex);
		      col.setMinWidth(1);
		      col.setHeaderRenderer(new SOLA());
		      
		      JTableHeader header = table_1.getTableHeader();
		      Dimension d = header.getPreferredSize();
		      d.height = 30;
		      header.setFont(new Font("Tahoma",Font.BOLD, 14));
		      header.setPreferredSize(d);
		      
		      table_1.setRowHeight(22);
		      table_1.setSelectionBackground(Color.PINK);
			  table_1.setSelectionForeground(Color.BLUE);
			  
		      scrollPane = new JScrollPane(table_1);
			  splitPane.setRightComponent(scrollPane);
			  getContentPane().setCursor(WAIT_CURSOR);
		        try {
					grid_doldur();
					table_1.removeColumn(table_1.getColumnModel().getColumn(12));
					table_1.removeColumn(table_1.getColumnModel().getColumn(12));
					table_1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
					kutu_temizle();
					doldur();
					doldur_kutu(0);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        getContentPane().setCursor(DEFAULT_CURSOR);
	}
	private static void grid_doldur() throws ClassNotFoundException, SQLException
	{
		GRID_TEMIZLE.grid_temizle(table_1);
		ResultSet	rs = null;
		rs =  oac.glb.user_details_bak();
		if (!rs.isBeforeFirst() ) {  
		    return;
		}
		DefaultTableModel defaultModel =  (DefaultTableModel) table_1.getModel();
			while(rs.next())
			{
				defaultModel.addRow(new Object[]{rs.getString(2), rs.getString(3)  ,rs.getString(4), rs.getString(5) ,rs.getString(6) ,
			    		  rs.getString(7), rs.getString(8)  ,rs.getString(9), rs.getString(10) ,rs.getString(11) ,rs.getString(12),rs.getString(13),
			    		  rs.getString(14),rs.getInt(1)});
			}
			
			table_1.requestFocus();
			table_1.changeSelection(0,0,false,false); 
			String deger;
			String[] parts;
			Font bigFont;
				try {
					deger = GLOBAL.setting_oku("STK_RAPORLAMA").toString();
					deger = deger.substring(1, deger.length()-1);
					parts = deger.split(",");
					bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
					table_1.setFont(bigFont);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}
	private static void doldur_kutu( int satir) throws ClassNotFoundException, SQLException 
	{
		if (table_1.getRowCount() == 0 ) {  
			kutu_temizle();
		    return;
		} 
		if (table_1.getModel().getValueAt(satir, 0) == null)
		{
			txtkodu.setText("");
		}
		else
		{
			txtkodu.setText(table_1.getModel().getValueAt(satir, 0).toString());
		}
		
		if (table_1.getModel().getValueAt(satir, 1) == null)
		{
			
		}
		else
		{
		comboBox.setSelectedItem(table_1.getModel().getValueAt(satir, 1).toString());
		}
		if (table_1.getModel().getValueAt(satir, 2) == null)
		{
			txtskull.setText("");
		}
		else
		{
			txtskull.setText(table_1.getModel().getValueAt(satir, 2).toString());
		}
		if (table_1.getModel().getValueAt(satir, 3) == null)
		{
			txtsifre.setText("");
		}
		else
		{
			txtsifre.setText(table_1.getModel().getValueAt(satir, 3).toString());
		}
		if (table_1.getModel().getValueAt(satir, 4) == null)
		{
			txtins.setText("");
		}
		else
		{
			txtins.setText(table_1.getModel().getValueAt(satir, 4).toString());
		}
		if (table_1.getModel().getValueAt(satir, 5) == null)
		{
			txtip.setText("");
		}
		else
		{
			txtip.setText(table_1.getModel().getValueAt(satir, 5).toString());
		}
		if (table_1.getModel().getValueAt(satir, 6) == null)
		{
			//comboBox_1.setSelectedItem(0);
		}
		else
		{
			comboBox_1.setSelectedItem(table_1.getModel().getValueAt(satir, 6).toString());
		}
		if (table_1.getModel().getValueAt(satir, 7) == null)
		{
			txtdiz.setText("");
		}
		else
		{
			txtdiz.setText(table_1.getModel().getValueAt(satir, 7).toString());
		}
		if (table_1.getModel().getValueAt(satir, 8) == null)
		{
			txtyer.setText("");
		}
		else
		{
			txtyer.setText(table_1.getModel().getValueAt(satir, 8).toString());
		}
		if (table_1.getModel().getValueAt(satir, 9) == null)
		{
			txtdcins.setText("");
		}
		else
		{
			txtdcins.setText(table_1.getModel().getValueAt(satir, 9).toString());
		}
		if ( table_1.getModel().getValueAt(satir, 10).toString().equals("E"))
		{
			chckbxNewCheckBox_1.setSelected((boolean) true);
		}
		else
		{
			chckbxNewCheckBox_1.setSelected((boolean) false);
		}
		if (  table_1.getModel().getValueAt(satir, 11).toString().equals("E"))
		{
			chckbxNewCheckBox.setSelected((boolean) true);
		}
		else
		{
			chckbxNewCheckBox.setSelected((boolean) false);
		}
		if (table_1.getModel().getValueAt(satir, 12) == null)
		{
			
		}
		else
		{
		comboBox_2.setSelectedItem(table_1.getModel().getValueAt(satir, 12).toString());
		
		}
		if (  table_1.getModel().getValueAt(satir, 13)== null)
		{
			lblcdid.setText("");
		}
		else
		{
			lblcdid.setText(table_1.getModel().getValueAt(satir, 13).toString());
		}
	}
	private static void doldur() throws ClassNotFoundException, SQLException
	{
		ResultSet	rs = null;
		rs = oac.glb.user_isim_doldur();
		if (!rs.isBeforeFirst() )  return;
		comboBox.removeAllItems();
		while(rs.next())
		{
			comboBox.addItem(rs.getString("USER_NAME"));
		}  
	}
	private static void kutu_temizle() 
	{
		txtkodu.setText("");
		txtskull.setText("");
		txtsifre.setText("");
		txtins.setText("");
		txtip.setText("");
		txtdiz.setText("");
		txtyer.setText("");
		txtdcins.setText("");
		lblcdid.setText("");
		chckbxNewCheckBox.setSelected(false);
		chckbxNewCheckBox_1.setSelected(false);
		comboBox.setSelectedItem("Admin");
		comboBox_1.setSelectedItem("Cari Hesap");
		comboBox_2.setSelectedItem("MS SQL");
	}
	public static void kayit() 
	{
	       if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Admin")  &&  ! GLOBAL.KULL_ADI.equals("Admin") ) 
	       {
	    	   JOptionPane.showMessageDialog(null, "Admin - Harici 'Admin' Bilgisini degisemez... ", "Kullanici", JOptionPane.WARNING_MESSAGE);
	    	   return;
	       }
	      try
	      {
	        	splitPane.setCursor(WAIT_CURSOR);
	        	oac.glb.details_yaz(txtkodu.getText(), comboBox.getItemAt(comboBox.getSelectedIndex()).toString(), txtskull.getText(), txtsifre.getText(),txtins.getText(),
	        			txtip.getText(),comboBox_1.getItemAt(comboBox_1.getSelectedIndex()), txtdiz.getText(), 
	        			txtyer.getText(), txtdcins.getText(), chckbxNewCheckBox.isSelected() ?   "E" : "",chckbxNewCheckBox_1.isSelected() ?   "E" : "",comboBox_2.getItemAt(comboBox_2.getSelectedIndex()).toString() ,lblcdid.getText());
	        	grid_doldur();
				kutu_temizle();
				doldur();
				doldur_kutu(0); 
	           splitPane.setCursor(DEFAULT_CURSOR);
	       }
	       catch (Exception ex )
	      {
	         	splitPane.setCursor(DEFAULT_CURSOR);
	       	    JOptionPane.showMessageDialog(null, ex.getMessage(), "Kullanici Ekleme", JOptionPane.WARNING_MESSAGE);
	        }
	}
	public static void sil() 
	{
		 			if (lblcdid.getText().toString().equals("")) return ;
			        if (comboBox.getItemAt(comboBox.getSelectedIndex()).equals("Admin"))
			        {   
			        JOptionPane.showMessageDialog(null, "Admin - Kullanici Silinemez... ", "Kullanici Silme", JOptionPane.WARNING_MESSAGE);
			        return;
			        }
			        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(LOGIN.class.getResource("/ICONLAR/sil.png")));
		   		   	 int g =  JOptionPane.showOptionDialog( null,  "Kayit Silinecek ?", "Kullanici Silme",   JOptionPane.YES_NO_OPTION,
		   							JOptionPane.QUESTION_MESSAGE,
		   			   				icon,     //no custom icon
		   			   				oac.options,  //button titles
		   			   				oac.options[1]); //default button
		   		   if(g != 0 ) { return;	}
		   		    splitPane.setCursor(WAIT_CURSOR);
		   		    try {
		   		    	oac.glb.cd_sil(Integer.parseInt(lblcdid.getText().toString()));
						grid_doldur();
						kutu_temizle();
						doldur();
						doldur_kutu(0); 
						splitPane.setCursor(DEFAULT_CURSOR);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			        splitPane.setCursor(DEFAULT_CURSOR);
	}
	public static void yeni()
	{
		kutu_temizle();
		txtkodu.requestFocus();
		
	}
	
}

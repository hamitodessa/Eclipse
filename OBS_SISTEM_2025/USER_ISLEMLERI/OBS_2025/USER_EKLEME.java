package OBS_2025;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.ORTA;
import OBS_C_2025.SOLA;

import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.IOException;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class USER_EKLEME extends JInternalFrame {

	private static JTable table_1;
	private static JTextField txtkull;
	private static JTextField txtsev;
	private static JTextField txtmail;
	private static JTextField txtdb;
	private static JCheckBox chckbxl;
	private static JCheckBox chckbxs;
	private static JScrollPane pane;
	private static JSplitPane splitPane ;
	static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					USER_EKLEME frame = new USER_EKLEME();
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
	@SuppressWarnings({ "unchecked", "rawtypes" ,"static-access"})
	public USER_EKLEME() {

		setTitle("KULLANICI EKLEME");
		setResizable(true);
		setClosable(true);
		setBounds(0, 0, 921, 450);

		splitPane = new JSplitPane();
		splitPane.setDividerSize(1);

		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		Object[][] tabledata = {
				{ "", ""  , "" ,"" ,"",false,false},    };
		String columnheaders[] = { "KULLANICI", "SIFRE", "SEVIYE" ,"DB IZIN","MAIL", "LOKAL IZIN", "SERVER IZIN"};
		DefaultTableModel model = new DefaultTableModel(tabledata,columnheaders);
		table_1 = new JTable(model){
			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;

				default:
					return Boolean.class;
				}
			}
			public boolean isCellEditable(int row, int column) {     return false;          }

		};
		table_1.setGridColor(oac.gridcolor);
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent lse) {
			        if (!lse.getValueIsAdjusting()) {
			        	getContentPane().setCursor(WAIT_CURSOR);
						try {
							kutu_temizle();
							doldur_kutu(table_1,table_1.getSelectedRow());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						getContentPane().setCursor(DEFAULT_CURSOR);
			        }
			    }
			});
		table_1.setSurrendersFocusOnKeystroke(true);
		table_1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		int vColIndex = 0;
		TableColumn col = table_1.getColumnModel().getColumn(vColIndex);
		col.setHeaderRenderer(new SOLA());
		col.setMaxWidth(130);
		col.setMinWidth(130);
		vColIndex = 1;
		col = table_1.getColumnModel().getColumn(vColIndex);
		col.setMaxWidth(130);
		col.setMinWidth(130);
		col.setHeaderRenderer(new SOLA());
		vColIndex = 2;
		col = table_1.getColumnModel().getColumn(vColIndex);
		col.setMaxWidth(60);
		col.setMinWidth(60);
		col.setHeaderRenderer(new SOLA());
		vColIndex = 3;
		col = table_1.getColumnModel().getColumn(vColIndex);
		col.setMaxWidth(80);
		col.setMinWidth(80);
		col.setHeaderRenderer(new SOLA());
		vColIndex = 4;
		col = table_1.getColumnModel().getColumn(vColIndex);
		col.setMaxWidth(300);
		col.setMinWidth(300);
		col.setHeaderRenderer(new SOLA());
		vColIndex = 5;
		col = table_1.getColumnModel().getColumn(vColIndex);
		col.setMaxWidth(70);
		col.setMinWidth(70);
		col.setHeaderRenderer(new ORTA());
		vColIndex = 6;
		col = table_1.getColumnModel().getColumn(vColIndex);
		col.setMaxWidth(80);
		col.setMinWidth(80);
		col.setHeaderRenderer(new ORTA());

		JTableHeader header = table_1.getTableHeader();
		Dimension d = header.getPreferredSize();
		d.height = 30;
		header.setPreferredSize(d); //addColumn case test
		table_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		table_1.setRowHeight(22);
		table_1.setSelectionBackground(Color.PINK);
		table_1.setSelectionForeground(Color.BLUE);
		table_1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		// 
		pane = new JScrollPane(table_1);
		pane.setViewportView(table_1);
		splitPane.setRightComponent(pane);

		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 100));
		panel.setMaximumSize(new Dimension(0, 100));
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Kullanici");
		lblNewLabel.setBounds(21, 11, 74, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sifre");
		lblNewLabel_1.setBounds(21, 39, 62, 14);
		panel.add(lblNewLabel_1);

		txtkull = new JTextField();
		txtkull.setEnabled(false);
		txtkull.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtkull.setBounds(105, 10, 113, 20);
		panel.add(txtkull);
		txtkull.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Seviye");
		lblNewLabel_2.setBounds(432, 14, 58, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Mail");
		lblNewLabel_3.setBounds(432, 39, 58, 14);
		panel.add(lblNewLabel_3);

		txtsev = new JTextField();
		txtsev.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtsev.setBounds(500, 10, 41, 20);
		panel.add(txtsev);
		txtsev.setColumns(10);

		txtmail = new JTextField();
		txtmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtmail.setBounds(500, 36, 284, 20);
		panel.add(txtmail);
		txtmail.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("DB Izni");
		lblNewLabel_4.setBounds(432, 64, 58, 14);
		panel.add(lblNewLabel_4);

		txtdb = new JTextField();
		txtdb.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtdb.setBounds(500, 61, 284, 20);
		panel.add(txtdb);
		txtdb.setColumns(10);

		chckbxl = new JCheckBox("Lokal Izni");
		chckbxl.setSelected(true);
		chckbxl.setBounds(273, 11, 97, 23);
		panel.add(chckbxl);

		chckbxs = new JCheckBox("Server Izni");
		chckbxs.setSelected(true);
		chckbxs.setBounds(273, 36, 97, 23);
		panel.add(chckbxs);

		passwordField = new JPasswordField();
		passwordField.setBounds(105, 36, 113, 20);
		panel.add(passwordField);

		getContentPane().setCursor(WAIT_CURSOR);
		try {
			grid_doldur();
			kutu_temizle();
			doldur_kutu(table_1,0);
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		getContentPane().setCursor(DEFAULT_CURSOR);
	}
	private static  void grid_doldur() throws ClassNotFoundException, SQLException, IOException
	{
		GRID_TEMIZLE.grid_temizle(table_1);
		ResultSet	rs = null;
		rs =  oac.uSER_ISL.user_ekleme_bak();

		if (!rs.isBeforeFirst() ) {  
			return;
		} 
		DefaultTableModel defaultModel = (DefaultTableModel) table_1.getModel();
		while(rs.next())
		{
			boolean liz;
			boolean siz;
			String uname = rs.getString(1);
			String sif = rs.getString(2) ; //decodedString ;
			String sev = rs.getString(3);
			String dbiz = rs.getString(4);
			String mail = rs.getString(5);
			if (Integer.parseInt(rs.getString(6)) >=  1) { 
				liz = true ;
			} 
			else { 
				liz = false ;
			} 
			if (Integer.parseInt(rs.getString(7)) >=  1) { 
				siz = true ;
			} 
			else { 
				siz = false ;
			} 
			defaultModel.addRow(new Object[]{uname, sif  ,sev, dbiz ,mail,liz,siz});
		}
		table_1.setModel(defaultModel); 
		table_1.requestFocus();
		table_1.changeSelection(0,0,false,false); 

		String deger;
		String[] parts;
		Font bigFont;
		deger = GLOBAL.setting_oku("STK_RAPORLAMA").toString();
		deger = deger.substring(1, deger.length()-1);
		parts = deger.split(",");
		bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
		table_1.setFont(bigFont);
	}
	private static  void doldur_kutu( JTable grd,int satir) throws ClassNotFoundException, SQLException 
	{
		if (grd.getRowCount() == 0 ) {  
			kutu_temizle();
			return;
		} 
		if (grd.getModel().getValueAt(satir, 0) == null)
		{
			txtkull.setText("");
		}
		else
		{
			txtkull.setText(grd.getModel().getValueAt(satir, 0).toString());
		}
		if (grd.getModel().getValueAt(satir, 1) == null)
		{
			passwordField.setText("");
		}
		else
		{
			String decodedString = grd.getModel().getValueAt(satir, 1).toString();
			String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
			byte[] bytes = new byte[byteValues.length];
			for (int i=0, len=bytes.length; i<len; i++) {
				bytes[i] = Byte.parseByte(byteValues[i].trim());     
			}
			try {
				decodedString = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes);
			} catch (Exception e) {
				e.printStackTrace();
			}
			passwordField.setText(decodedString);
		}
		if (grd.getModel().getValueAt(satir, 2) == null)
		{
			txtsev.setText("");
		}
		else
		{
			txtsev.setText(grd.getModel().getValueAt(satir, 2).toString());
		}

		if (grd.getModel().getValueAt(satir, 3) == null)
		{
			txtdb.setText("");
		}
		else
		{
			txtdb.setText(grd.getModel().getValueAt(satir, 3).toString());
		}
		if (grd.getModel().getValueAt(satir, 4) == null)
		{
			txtmail.setText("");
		}
		else
		{
			txtmail.setText(grd.getModel().getValueAt(satir, 4).toString());
		}
		chckbxl.setSelected((boolean) grd.getModel().getValueAt(satir, 5));
		chckbxs.setSelected((boolean) grd.getModel().getValueAt(satir, 6));
	}
	private static void kutu_temizle() 
	{
		txtkull.setText("");
		txtsev.setText("");
		txtmail.setText("");
		txtdb.setText("");
		chckbxl.setSelected(true);
		chckbxs.setSelected(true);
		passwordField.setText("");
	}
	public static void kayit() 
	{
		if (txtkull.getText().equals("") ) 
		{	return ;		}
		if (txtkull.getText().equals("Admin")  &&  ! GLOBAL.KULL_ADI.equals("Admin") ) 
		{
			JOptionPane.showMessageDialog(null, "Admin - Harici 'Admin' Bilgisini degisemez... ", "Kullanici", JOptionPane.WARNING_MESSAGE);
			return;
		}
		try
		{
			splitPane.setCursor(WAIT_CURSOR);
			boolean varmi =  oac.uSER_ISL.user_bak(txtkull.getText());
			if ( varmi == true ) // ' USER VAR  
			{
				oac.uSER_ISL.user_sil(txtkull.getText());
			}
			oac.uSER_ISL.user_ekle_degis(txtkull.getText(),oac.sDONDUR.sDONDUR(passwordField), txtsev.getText(), txtdb.getText(), txtmail.getText(),  chckbxl.isSelected()  ? true : false,  chckbxs.isSelected()  ? true :false);
			//
			if (txtkull.getText().equals(GLOBAL.KULL_ADI)) {
				
				//String decodedString = GLOBAL.setting_oku("SIFRE").toString();
				//String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
				//byte[] bytes = new byte[byteValues.length];
				//for (int i=0, len=bytes.length; i<len; i++) {
				//	bytes[i] = Byte.parseByte(byteValues[i].trim());     
				//}
				//String kSIFREString =  ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes);
				//System.out.println("Global kull="+ GLOBAL.KULL_ADI + "  Kayitli Sifresi =" + kSIFREString);
				
				byte[]  qaz =	ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(oac.sDONDUR.sDONDUR(passwordField)) ;
				String response = Arrays.toString(qaz);
				
				//System.out.println(oac.sDONDUR.sDONDUR(passwordField) + "   " + response);
				GLOBAL.setting_yaz("SIFRE", response);
			}
			
			//
			grid_doldur();
			kutu_temizle();
			doldur_kutu(table_1,0);
			txtkull.setEnabled(false);
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
		if ( txtkull.getText().equals("")) return;
		if ( txtkull.getText().equals("Admin") )
		{
			JOptionPane.showMessageDialog(null, "Admin - Kullanici Silinemez... ", "Kullanici Silme", JOptionPane.WARNING_MESSAGE);
			return;
		}
		ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(LOGIN.class.getResource("/ICONLAR/sil.png")));
		int g =  JOptionPane.showOptionDialog( null,  "Kullaniciya Ait butun bilgiler silinecek ?", "Kullanici Silme",   JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,	icon, 	oac.options, oac.options[1]); //default button
		if(g != 0 ) { return;	}
		try
		{
			if (oac.uSER_ISL.user_bak(txtkull.getText())) // USER VAR 
			{
				splitPane.setCursor(WAIT_CURSOR);
				oac.uSER_ISL.user_sil(txtkull.getText());
				oac.uSER_ISL.user_details_sil(txtkull.getText());
				//
				if (txtkull.getText().equals(GLOBAL.KULL_ADI)) 
				{
					GLOBAL.setting_yaz("SIFRE", "");
					GLOBAL.setting_yaz("BENI_HATIRLA", "H");	
					GLOBAL.setting_yaz("ISIM", "");
					LOGIN frame = new LOGIN();
					frame.setVisible(true);
					OBS_MAIN.button_1.doClick();
				}
				else 
				{
					grid_doldur();
					kutu_temizle();
					doldur_kutu(table_1,0);
					txtkull.setEnabled(false);
					splitPane.setCursor(DEFAULT_CURSOR);
				}
				
			}
		}
		catch (Exception ex)
		{
			splitPane.setCursor(DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Kullanici Silme", JOptionPane.WARNING_MESSAGE);
		}  
	}
	public static void yeni() 
	{
		kutu_temizle();
		txtkull.setEnabled(true); 
		txtkull.requestFocus();	 
	}
}

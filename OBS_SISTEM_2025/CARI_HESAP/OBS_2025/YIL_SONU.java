package OBS_2025;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.toedter.calendar.JDateChooser;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.CheckBoxRenderer;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.SOLA;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.lOG_BILGI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({"serial" , "static-access" ,"deprecation" ,"unused"})
public class YIL_SONU extends JInternalFrame {
	private static final Vector<?> Boolean = null;
	private static JTable table;
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(oac._ICar , oac._ICari_Loger);
	private static JTextField textField;
	private static JTextField textField_1;
	private JPanel panel_1 ;
	private JLabel lblNewLabel_1;
	private static JCheckBox chckbxNewCheckBox ;
	private static JDateChooser dateChooser ;
	private  JLabel lblNewLabel_3 ;
	private static JSplitPane splitPaneana ;
	private static JPanel panel ;
	private JScrollPane scrollPane ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YIL_SONU frame = new YIL_SONU();
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
	public YIL_SONU() {
		setClosable(true);
		setResizable(true);
		setIconifiable(true);
		setTitle("YIL SONU AKTARMA");
		setBounds(0, 0, 700, 600);

		splitPaneana = new JSplitPane();
		splitPaneana.setDividerSize(0);
		splitPaneana.setResizeWeight(1.0);
		splitPaneana.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPaneana, BorderLayout.CENTER);

		JSplitPane splitPane1 = new JSplitPane();
		splitPane1.setDividerSize(1);
		splitPane1.setResizeWeight(0.0);
		splitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPaneana.setLeftComponent(splitPane1);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel_3.setMinimumSize(new Dimension(0, 25));
		panel_3.setMaximumSize(new Dimension(0, 25));

		splitPaneana.setRightComponent(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Secilen Kayit Sayisi:");
		lblNewLabel_2.setBounds(10, 5, 120, 14);
		panel_3.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("0");
		lblNewLabel_3.setBounds(135, 5, 56, 14);
		lblNewLabel_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		panel_3.add(lblNewLabel_3);
		scrollPane = new JScrollPane();
		splitPane1.setRightComponent(scrollPane);

		table = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {  
				switch (column) {
				case 0:
					return true;
				default:
					return false;
				}
			}
		};
		table.getTableHeader().setReorderingAllowed(false);
		table.setGridColor(oac.gridcolor);
		scrollPane.setViewportView(table);

		panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 80));
		panel.setMaximumSize(new Dimension(0, 80));
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		splitPane1.setLeftComponent(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Yazilacak Firma Kodu");
		lblNewLabel.setBounds(10, 11, 136, 14);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(10, 30, 77, 20);
		panel.add(textField);
		textField.setColumns(10);

		chckbxNewCheckBox = new JCheckBox("Mizan Aktar");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected())
				{
					panel_1.setVisible(true);
				}
				else
				{
					panel_1.setVisible(false);
				}
			}
		});
		chckbxNewCheckBox.setBounds(160, 7, 115, 23);
		panel.add(chckbxNewCheckBox);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Mizan Devir Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1.setBounds(281, 5, 391, 63);
		panel_1.setVisible(false);
		panel.add(panel_1);
		panel_1.setLayout(null);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(10, 20, 114, 22);
		dateChooser.setDateFormatString("dd.MM.yyyy");
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 14));
		dateChooser.setDate(new Date());

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
		panel_1.add(dateChooser);

		textField_1 = new JTextField();
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					HESAP_PLN hsp ;
					try {
						hsp = new HESAP_PLN();
						hsp.show();
						textField_1.setText( oac.hsp_hsp_kodu);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(148, 20, 138, 20);
		textField_1.setToolTipText("Devir Hesabi");
		textField_1.setDocument(new JTextFieldLimit(12));
		textField_1.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				lblNewLabel_1.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				lblNewLabel_1.setText(CARI_ISIM_OKU.isim(textField_1.getText())[0]);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}

		});
		panel_1.add(textField_1);

		lblNewLabel_1 = new JLabel("....");
		lblNewLabel_1.setBounds(148, 42, 233, 14);
		panel_1.add(lblNewLabel_1);

		doldur();
		//***********
		table.getModel().addTableModelListener(	(TableModelListener) new TableModelListener() 
		{
			public void tableChanged(TableModelEvent e) 
			{
				TableModel model = (TableModel)e.getSource();
				if (model.getRowCount() > 0) {
					int row;
					row = table.getSelectedRow();     //e.getFirstRow();
					int column = e.getColumn();
					secilen_satir();
				}
			}
		});
		//****
	}
	private void doldur()
	{
		try
		{
			getContentPane().setCursor(oac.WAIT_CURSOR);
			ResultSet	rs = null;
			rs = c_Access.hp_pln();
			if (!rs.isBeforeFirst() ) {  
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				return;
			}
			table.setModel(DbUtils.resultSetToTableModel(rs));
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addColumn("Sec",Boolean);
			table.moveColumn(table.getColumnCount()-1, 0);

			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			JCheckBox checkBox = new JCheckBox();
			checkBox.setHorizontalAlignment(JCheckBox.CENTER);
			DefaultCellEditor dce = new DefaultCellEditor( checkBox );
			tc.setCellEditor(dce);
			tc.setCellRenderer(new CheckBoxRenderer());
			tc.setMinWidth(50);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(100);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(275);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(60);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);

			th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(22);

			Dimension dd = table.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			//
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
			//
			th.repaint();

			table.setSelectionBackground(Color.PINK);
			table.setSelectionForeground(Color.BLUE);

			String deger;
			String[] parts;
			Font bigFont;
			deger = GLOBAL.setting_oku("CARI_HSPPLN").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			table.setFont(bigFont);
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		} 
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null,  ex.getMessage()); 	
		}
	}
	private int satir_kontrol()
	{
		int satir = 0 ;
		DefaultTableModel modell = (DefaultTableModel)table.getModel();
		for ( int i = 0; i <=  modell.getRowCount() - 1;i++)
		{
			if ( modell.getValueAt(i,5) != null) 
			{
				if (  (boolean) modell.getValueAt(i,5) )
				{
					satir += 1 ;
				}
			};

		}
		return satir ;
	}
	private void secilen_satir()
	{

		lblNewLabel_3.setText(FORMATLAMA.doub_0(satir_kontrol()));
	}
	public static  void kaydet()
	{
		try 
		{
			if (textField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,  "Aktarma Yapilacak Veritabani Kodu Bos..."); 	
				return ;
			}     
			if (chckbxNewCheckBox.isSelected() )
			{
				if (textField_1.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Devir Karsi Hesap Kodu Bos..."); 	
					return ;
				}
			}
			c_Access.akt_baglan(textField.getText() ,BAGLAN.cariDizin.sERVER);
			int kaysay = 0 ;
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for(int  i = 0 ;i <= model.getRowCount() - 1; i ++)
			{
				boolean durum =  model.getValueAt(i, 5) != null ?    (boolean) model.getValueAt(i, 5) : false;
				if (durum) 
				{
					kaysay += 1 ;
				}
			}
			int g = JOptionPane.showOptionDialog( null,  "Aktarilacak Kayit Sayisi.. =" + kaysay, "Aktarma",   JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,null, oac.options, oac.options[1]); 
			if(g != 0 ) { return;	}	
			int sayi = 0 ;
			sayi =c_Access.yilsonu_hesap_plani_kayit_adedi() ;
			JOptionPane.showMessageDialog(null, "Aktarilacak Dosyadaki Kayit Sayisi.....:" + sayi); 
			int say   = 0 ;
			for(int  i = 0 ;i <= model.getRowCount() - 1; i ++)
			{
				boolean durum =  model.getValueAt(i, 5) != null ?    (boolean) model.getValueAt(i, 5) : false;
				if (durum) 
				{
					c_Access.yilsonu_hpln_kayit(model.getValueAt(i, 0).toString(),
							model.getValueAt(i, 1).toString(), model.getValueAt(i, 2).toString(), 
							model.getValueAt(i, 3).toString(), GLOBAL.KULL_ADI);
					c_Access.yilsonu_hpln_detay_kayit(model.getValueAt(i, 0).toString(), "", "", "", "", "", "", "" 
							, "", "", "", "", "", "", "", "", "" 
							, "", "");
					say += 1;
				}
			}
			JOptionPane.showMessageDialog(null,  "Hesap Plani Aktarma Islemi Basari ile gerceklestirildi...." 
					+ System.lineSeparator() + System.lineSeparator()  + "Aktarilan Hesap Sayisi...: " + say); 
			//'*****MIZAN AKTARMA YAP '******
			if (chckbxNewCheckBox.isSelected() )
			{
				mizan_aktar() ;
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage()); 
		}
	}
	private static void mizan_aktar()
	{
		try
		{
			double  bir = 0 ;
			double  iki = 0 ;
			double  uc = 0 ;
			int  say = 0 ;
			int enumara = 0 ;
			ResultSet rs = null ;
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for(int  i = 0 ;i <= model.getRowCount() - 1; i ++)
			{
				boolean durum =  model.getValueAt(i, 5) != null ?    (boolean) model.getValueAt(i, 5) : false;
				if (durum) 
				{
					rs =  c_Access.mizan_aktar(model.getValueAt(i, 0).toString());
					bir = 0;
					iki = 0;
					uc = 0;
					if (!rs.isBeforeFirst() ) {  
					}  
					else
					{
						say += 1;
						enumara=0;
						enumara = c_Access.yilsonu_cari_fisno_al();
						rs.next();
						bir = rs.getDouble("BORC");
						iki = rs.getDouble("ALACAK");
						uc = iki - bir ;
						
						lOG_BILGI lBILGI = new lOG_BILGI();
						
						
						
						if ( bir == iki)   // ' Bakiye Sifir  bir = iki
						{
							String str =TARIH_CEVIR.tarih_geri(dateChooser);
							double sifir = 0 ;
							lBILGI.setmESAJ("A.Hes:" + textField_1.getText() + " B.Hes:" + rs.getString("HESAP")  + " Tut:" +sifir + " Msj:" +"Devir Islemi...");
							lBILGI.seteVRAK(String.valueOf(enumara));
							
							c_Access.yilsonu_cari_dekont_kaydet(rs.getString("HESAP"), str, 
									enumara, "", 1.0, sifir,textField_1.getText(),"", 1.0,sifir,"Devir Islemi...", "D", GLOBAL.KULL_ADI,
									lBILGI ,BAGLAN_LOG.cariLogDizin);
						}
						else if (uc < 0)  // 'Borclu hesaplar -0.001
						{
							String str =TARIH_CEVIR.tarih_geri(dateChooser);
							lBILGI.setmESAJ("A.Hes:" + textField_1.getText() + " B.Hes:" + rs.getString("HESAP")  + " Tut:" +uc + " Msj:" +"Devir Islemi...");
							lBILGI.seteVRAK(String.valueOf(enumara));
							c_Access.yilsonu_cari_dekont_kaydet(rs.getString("HESAP"), str,enumara, "",
									1.0, uc, textField_1.getText(),"",1.0,uc,	"Devir Islemi...", "D", GLOBAL.KULL_ADI,
									lBILGI ,BAGLAN_LOG.cariLogDizin);
						}
						else if (uc > 0 )  //   'Alacakli hesaplar  0.001
						{
							String str =TARIH_CEVIR.tarih_geri(dateChooser);
							lBILGI.setmESAJ("A.Hes:" + textField_1.getText() + " B.Hes:" + rs.getString("HESAP")  + " Tut:" +uc + " Msj:" +"Devir Islemi...");
							lBILGI.seteVRAK(String.valueOf(enumara));

							
							c_Access.yilsonu_cari_dekont_kaydet(textField_1.getText(), str, 
									enumara, "",1.0, uc,rs.getString("HESAP"),"",1.0,uc,	"Devir Islemi...", "D", GLOBAL.KULL_ADI,
									lBILGI,
									BAGLAN_LOG.cariLogDizin);
						}
					}

				}
				else
				{
					// ' Hesaba iliskin kayit Yok 0 olarak islenecek
				}

			}  // ilk For INT
			JOptionPane.showMessageDialog(null,  "Mizan Aktarma Islemi Basari ile Tamamlandi...."  + System.lineSeparator() + System.lineSeparator() + "Aktarilan Hesap Sayisi...: " + say); 
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage()); 
		}
	}
	///********
	class MyItemListener implements ItemListener
	{
		@SuppressWarnings("removal")
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			Object source = e.getSource();
			if (source instanceof AbstractButton == false) return;
			boolean checked = e.getStateChange() == ItemEvent.SELECTED;
			for(int x = 0, y = table.getRowCount(); x < y; x++)
			{
				table.setValueAt(new Boolean(checked),x,0);
			}
		}
	}
	class CheckBoxHeader extends JCheckBox   implements TableCellRenderer, MouseListener 
	{
		protected CheckBoxHeader rendererComponent;
		protected int column;
		protected boolean mousePressed = false;
		public CheckBoxHeader(ItemListener itemListener) {
			rendererComponent = this;
			rendererComponent.addItemListener(itemListener);
		}
		public Component getTableCellRendererComponent(
				JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			if (table != null) {
				JTableHeader header = table.getTableHeader();
				if (header != null) {
					rendererComponent.setForeground(header.getForeground());
					rendererComponent.setBackground(header.getBackground());
					rendererComponent.setFont(header.getFont());
					header.addMouseListener(rendererComponent);
				}
			}
			setColumn(column);

			setHorizontalAlignment(JLabel.CENTER);

			setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			//setSelected(true);
			return rendererComponent;
		}
		protected void setColumn(int column) {
			this.column = column;
		}
		public int getColumn() {
			return column;
		}
		protected void handleClickEvent(MouseEvent e) {
			if (mousePressed) {
				mousePressed=false;
				JTableHeader header = (JTableHeader)(e.getSource());
				JTable tableView = header.getTable();
				TableColumnModel columnModel = tableView.getColumnModel();
				int viewColumn = columnModel.getColumnIndexAtX(e.getX());
				int column = tableView.convertColumnIndexToModel(viewColumn);

				if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {
					doClick();
				}
			}
		}
		public void mouseClicked(MouseEvent e) {
			handleClickEvent(e);
			((JTableHeader)e.getSource()).repaint();
		}
		public void mousePressed(MouseEvent e) {
			mousePressed = true;
		}
		public void mouseReleased(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) 
		{
		}
	}
}

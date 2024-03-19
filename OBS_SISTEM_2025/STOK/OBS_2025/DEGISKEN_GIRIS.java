package OBS_2025;

import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.lOG_BILGI;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings({"serial","static-access"})
public class DEGISKEN_GIRIS extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);


	private static JTable table;
	private static Obs_TextFIeld textField;
	private static Obs_TextFIeld textField_1;
	private static String hangi = "" ;
	private static JComboBox<String> cmbanagrup  ;

	private static ResultSet rs=null;
	private static Obs_TextFIeld textField_2;
	private static boolean ilkmi = true;

	private static JPanel panel ;

	/**
	 * Create the frame.
	 */
	public DEGISKEN_GIRIS(String nerden) {
		hangi = nerden ;
		setTitle("DEGISKENLER");
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(500, 100, 358, 563);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(DEGISKEN_GIRIS.class.getResource("/ICONLAR/degisken-16.png")), 16, 16));//

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel.setMinimumSize(new Dimension(0, 200));
		panel.setMaximumSize(new Dimension(0, 200));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Arama", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panel_1.setBounds(10, 11, 320, 50);
		panel.add(panel_1);
		panel_1.setLayout(null);

		textField = new Obs_TextFIeld();
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
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
		textField.setBounds(43, 19, 248, 20);
		panel_1.add(textField);
		textField.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,null,null), "Kullanici", TitledBorder.LEADING, TitledBorder.TOP, null,null));
		panel_2.setBounds(10, 72, 320, 50);
		panel.add(panel_2);

		textField_1 = new Obs_TextFIeld();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(43, 19, 248, 20);
		panel_2.add(textField_1);

		textField_2 = new Obs_TextFIeld();
		textField_2.setBounds(10, 19, 24, 20);
		textField_2.setVisible(false);
		panel_2.add(textField_2);
		textField_2.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ana Grup", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 133, 320, 50);
		panel.add(panel_3);

		cmbanagrup = new JComboBox<String>();
		cmbanagrup.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbanagrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (! ilkmi)
				{
					if (cmbanagrup.getItemCount() == 0 ) return ;
					if (cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals("") ) return ;
					alt_grup_degis();
				}			
			}
		});
		cmbanagrup.setBounds(43, 19, 248, 22);
		panel_3.add(cmbanagrup);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		splitPane.setRightComponent(scrollPane);

		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			table.setGridColor(oac.gridcolor);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					if (table.getRowCount() == 0) return ;
					if (table.getSelectedRow()  < 0) return;
					int row = table.getSelectedRow();
					if (table.getRowSorter()!=null) {
						row = table.getRowSorter().convertRowIndexToModel(row);
						textField_1.setText(table.getModel().getValueAt(row, 1).toString());
						textField_2.setText(table.getModel().getValueAt(row, 0).toString());
					}
					else
					{
						textField_1.setText(table.getModel().getValueAt(table.getSelectedRow(), 1).toString());
						textField_2.setText(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
					}
				}
			}
		});
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
		if (nerden.equals("altgrup"))
		{
			panel.setMinimumSize(new Dimension(0, 200));
			panel.setMaximumSize(new Dimension(0, 200));
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Alt Grup", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		else if (nerden.equals("anagrup"))
		{
			panel.setMinimumSize(new Dimension(0,135));
			panel.setMaximumSize(new Dimension(0, 135));
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ana Grup", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		else if (nerden.equals("mensei"))
		{
			panel.setMinimumSize(new Dimension(0,135));
			panel.setMaximumSize(new Dimension(0, 135));
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Mensei", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		else if (nerden.equals("depo"))
		{
			panel.setMinimumSize(new Dimension(0,135));
			panel.setMaximumSize(new Dimension(0, 135));
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Depo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		else if (nerden.equals("oz1"))
		{
			panel.setMinimumSize(new Dimension(0,135));
			panel.setMaximumSize(new Dimension(0, 135));
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ozel Kod 1", TitledBorder.LEADING, TitledBorder.TOP, null,null));
		}
		else if (nerden.equals("oz2"))
		{
			panel.setMinimumSize(new Dimension(0,135));
			panel.setMaximumSize(new Dimension(0, 135));
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ozel Kod 2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		yenile();
	}
	private static void yenile()
	{
		try {
			if (hangi.equals("mensei"))
				rs = f_Access.stk_kod_degisken_oku("MENSEI", "MEID_Y", "MENSEI_DEGISKEN");
			else if (hangi.equals("anagrup"))
				rs = f_Access.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
			else if (hangi.equals("altgrup"))
				ana_grup_doldur();
			else if (hangi.equals("depo"))
				rs = f_Access.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
			else if (hangi.equals("oz1"))
				rs = f_Access.stk_kod_degisken_oku("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN");
			else if (hangi.equals("oz2"))
				rs = f_Access.stk_kod_degisken_oku("OZEL_KOD_2", "OZ2ID_Y", "OZ_KOD_2_DEGISKEN");
			//***********TABLO DOLDUR
			if (!rs.isBeforeFirst() ) {  
			} 
			else
				grid_doldur();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
	private static void grid_doldur()
	{
		try {
			GRID_TEMIZLE.grid_temizle(table);
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			table.removeColumn(table.getColumnModel().getColumn(0));
			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			if (table.getRowCount() > 0)
			{
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				textField_1.setText(table.getModel().getValueAt(0, 1).toString());
				textField_2.setText(table.getModel().getValueAt(0, 0).toString());
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
	public static  void sil()
	{
		try {
			lOG_BILGI lBILGI = new lOG_BILGI();
			if (hangi.equals("altgrup"))
			{
				int g =  JOptionPane.showOptionDialog( null,  "Alt Grup Degisken Silinecek ..?" + System.lineSeparator() +
						"Silme operasyonu butun dosyayi etkileyecek..." + System.lineSeparator() + 
						"Ilk once Degisken Yenileme Bolumunden degistirip sonra siliniz...."  ,
						"Degisken  Silme",   JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,     //no custom icon
						oac.options,  //button titles
						oac.options[1]); //default button
				if(g != 0 ) { return;	}
				int anaG = 0 , altG = 0 ;
				ResultSet rss = null;
				rss = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString());
				if (!rss.isBeforeFirst() ) {      		
				}
				else
				{
					rss.next();
					anaG  = rss.getInt("AGID_Y");
				}
				rss = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN",  textField_1.getText());
				if (!rss.isBeforeFirst() ) {      		
				}
				else
				{
					rss.next();
					altG  = rss.getInt("ALID_Y");
				}
				if (f_Access.alt_grup_kontrol(anaG,altG) )
				{
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Ilk once Degisken Yenileme Bolumunden degistirip sonra siliniz....");
					return;
				}
				lBILGI.setmESAJ("Alt Grup Silme:" + Integer.parseInt(textField_2.getText()));
				lBILGI.seteVRAK("");
				f_Access.urun_degisken_alt_grup_sil(Integer.parseInt(textField_2.getText()),
						lBILGI,BAGLAN_LOG.fatLogDizin);
				sifirla();
				textField.setText("");
				ana_grup_doldur();
				grid_doldur();
				textField.requestFocus();
			} else if (hangi.equals("mensei"))
			{
				int g =  JOptionPane.showOptionDialog( null,  "Mensei  Degisken Silinecek ..?"   ,
						"Degisken  Silme",   JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,     //no custom icon
						oac.options,  //button titles
						oac.options[1]); //default button
				if(g != 0 ) { return;	}

				lBILGI.setmESAJ(" Mensei Sil:" + Integer.parseInt(textField_2.getText()));
				lBILGI.seteVRAK("");
				f_Access.urun_kod_degisken_sil( "MEID_Y", "MENSEI_DEGISKEN", Integer.parseInt(textField_2.getText()),
						lBILGI,BAGLAN_LOG.fatLogDizin);
				sifirla();
				textField.setText("");
				yenile();
				textField.requestFocus();
			} else if (hangi.equals("depo"))
			{
				int g =  JOptionPane.showOptionDialog( null,  "Depo  Degisken Silinecek ..?"   ,
						"Degisken  Silme",   JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,     //no custom icon
						oac.options,  //button titles
						oac.options[1]); //default button
				if(g != 0 ) { return;	}

				lBILGI.setmESAJ( "Depo Sil:" + Integer.parseInt(textField_2.getText()) );
				lBILGI.seteVRAK("");
				f_Access.urun_kod_degisken_sil( "DPID_Y", "DEPO_DEGISKEN", Integer.parseInt(textField_2.getText()),
						lBILGI,BAGLAN_LOG.fatLogDizin);
				sifirla();
				textField.setText("");
				yenile();
				textField.requestFocus();
			}
			else if (hangi.equals("oz1"))
			{
				int g =  JOptionPane.showOptionDialog( null,  "Ozel Kod 1  Degisken Silinecek ..?"   ,
						"Degisken  Silme",   JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,     //no custom icon
						oac.options,  //button titles
						oac.options[1]); //default button
				if(g != 0 ) { return;	}
				lBILGI.setmESAJ("Ozel Kod1 :" + Integer.parseInt(textField_2.getText()) );
				lBILGI.seteVRAK("");
				f_Access.urun_kod_degisken_sil( "OZ1ID_Y", "OZ_KOD_1_DEGISKEN", Integer.parseInt(textField_2.getText()),
						lBILGI,BAGLAN_LOG.fatLogDizin);
				sifirla();
				textField.setText("");
				yenile();
				textField.requestFocus();
			}
			else if (hangi.equals("oz2"))
			{
				int g =  JOptionPane.showOptionDialog( null,  "Ozel Kod 2  Degisken Silinecek ..?"   ,
						"Degisken  Silme",   JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,     //no custom icon
						oac.options,  //button titles
						oac.options[1]); //default button
				if(g != 0 ) { return;	}
				lBILGI.setmESAJ("Ozel Kod2 :" + Integer.parseInt(textField_2.getText()) );
				lBILGI.seteVRAK("");
				f_Access.urun_kod_degisken_sil( "OZ2ID_Y", "OZ_KOD_2_DEGISKEN", Integer.parseInt(textField_2.getText()),
						lBILGI,BAGLAN_LOG.fatLogDizin);
				sifirla();
				textField.setText("");
				yenile();
				textField.requestFocus();
			}
			else if (hangi.equals("anagrup"))
			{
				int g =  JOptionPane.showOptionDialog( null,  "Ana Grup  Degisken Silinecek ..?"   ,
						"Degisken  Silme",   JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,     //no custom icon
						oac.options,  //button titles
						oac.options[1]); //default button
				if(g != 0 )
					return;
				lBILGI.setmESAJ("Ana Grup :" + Integer.parseInt(textField_2.getText()) );
				lBILGI.seteVRAK("");
				f_Access.urun_kod_degisken_sil( "AGID_Y", "ANA_GRUP_DEGISKEN", Integer.parseInt(textField_2.getText()),
						lBILGI,BAGLAN_LOG.fatLogDizin);
				sifirla();
				textField.setText("");
				yenile();
				textField.requestFocus();
			}
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
	private static void ana_grup_doldur()
	{
		try {
			cmbanagrup .removeAllItems();
			rs=null;
			rs = f_Access.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
			if (!rs.isBeforeFirst() ) {      		
			}
			else
			{
				while (rs.next())
					cmbanagrup .addItem(rs.getString("ANA_GRUP"));
			}
			int in1= 0 ;
			rs = null ;
			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) 
			{      		
			}
			else
			{
				rs.next();
				in1 = rs.getInt("AGID_Y");
				rs = null ;
				rs  = f_Access.stk_kod_alt_grup_degisken_oku(in1);
			}
			ilkmi=false;
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
	private void alt_grup_degis()
	{
		try {
			getContentPane().setCursor(oac.WAIT_CURSOR);
			int in1= 0 ;
			rs = null ;
			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
			rs.next();
			in1 = rs.getInt("AGID_Y");
			rs = null ;
			rs  = f_Access.stk_kod_alt_grup_degisken_oku(in1);
			ilkmi= false ;
			if (!rs.isBeforeFirst() ) {  
				textField_1.setText("");
				textField_2.setText("");
				GRID_TEMIZLE.grid_temizle(table);
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			else
			{
				grid_doldur(); 
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}		
	}
	public static void yeni ()
	{
		sifirla();
		textField_1.requestFocus();
	}
	private static void sifirla()
	{
		textField_1.setText("");
		textField_2.setText("");
	}
	public void arama()  
	{
		if (textField.getText().equals(""))
			table.setRowSorter(null);
		else
		{
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
			sorter.setStringConverter(new TableStringConverter() {
				@Override
				public String toString(TableModel model, int row, int column) {
					return model.getValueAt(row, column).toString().toLowerCase();
				}
			});
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText().toLowerCase()));
			table.setRowSorter(sorter);
		}
	}
	public static void kayit()
	{
		if(textField_1.getText().toString().equals("")) return ;
		try 
		{
			if( ! textField_2.getText().toString().equals("")  )//  ' ESKI KAYIT
			{ 
				if (hangi.equals("mensei"))
				{
					f_Access.urun_degisken_eski("MENSEI", textField_1.getText(), "MENSEI_DEGISKEN", "MEID_Y",  Integer.parseInt(textField_2.getText()));
					rs =  f_Access.stk_kod_degisken_oku("MENSEI", "MEID_Y", "MENSEI_DEGISKEN");
				}
				else  if (hangi.equals("anagrup"))
				{
					f_Access.urun_degisken_eski("ANA_GRUP",textField_1.getText(), "ANA_GRUP_DEGISKEN", "AGID_Y", Integer.parseInt(textField_2.getText()));
					rs =  f_Access.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
				}
				else  if (hangi.equals("altgrup"))
				{
					int in1  = 0;
					ResultSet rss =  f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
					rss.next();
					in1 = rss.getInt("AGID_Y");
					f_Access.urun_degisken_alt_grup_eski(textField_1.getText(), in1, Integer.parseInt(textField_2.getText()));
					rs =  f_Access.stk_kod_alt_grup_degisken_oku(in1);
				}
				else  if (hangi.equals("depo"))
				{
					f_Access.urun_degisken_eski("DEPO", textField_1.getText(), "DEPO_DEGISKEN", "DPID_Y",  Integer.parseInt(textField_2.getText()));
					rs = f_Access.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
				}
				else  if (hangi.equals("oz1"))
				{
					f_Access.urun_degisken_eski("OZEL_KOD_1", textField_1.getText(), "OZ_KOD_1_DEGISKEN", "OZ1ID_Y",  Integer.parseInt(textField_2.getText()));
					rs = f_Access.stk_kod_degisken_oku("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN");
				}
				else  if (hangi.equals("oz2"))
				{
					f_Access.urun_degisken_eski("OZEL_KOD_2", textField_1.getText(), "OZ_KOD_2_DEGISKEN", "OZ2ID_Y", Integer.parseInt(textField_2.getText()));
					rs = f_Access.stk_kod_degisken_oku("OZEL_KOD_2", "OZ2ID_Y", "OZ_KOD_2_DEGISKEN");
				}
			}
			else  // YENI KAYIT
			{
				if (hangi.equals("mensei"))
				{
					f_Access.urun_degisken_kayit("MEID_Y","MENSEI_DEGISKEN", "MENSEI", textField_1.getText());
					rs =   f_Access.stk_kod_degisken_oku("MENSEI", "MEID_Y", "MENSEI_DEGISKEN");
				}
				else  if (hangi.equals("anagrup"))
				{
					f_Access.urun_degisken_kayit("AGID_Y","ANA_GRUP_DEGISKEN", "ANA_GRUP", textField_1.getText());
					rs = f_Access.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
				}
				else  if (hangi.equals("altgrup"))
				{
					int in1  = 0;  
					ResultSet rss = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
					while (rss.next())
					{
						in1 = rss.getInt("AGID_Y");
						f_Access.urun_degisken_alt_grup_kayit(textField_1.getText(), in1);
						rs = f_Access.stk_kod_alt_grup_degisken_oku(in1);
					}
				}
				else  if (hangi.equals("depo"))
				{
					f_Access.urun_degisken_kayit("DPID_Y","DEPO_DEGISKEN", "DEPO", textField_1.getText());
					rs = f_Access.stk_kod_degisken_oku("DEPO", "DPID_Y", "DEPO_DEGISKEN");
				}
				else  if (hangi.equals("oz1"))
				{
					f_Access.urun_degisken_kayit("OZ1ID_Y","OZ_KOD_1_DEGISKEN", "OZEL_KOD_1", textField_1.getText());
					rs = f_Access.stk_kod_degisken_oku("OZEL_KOD_1", "OZ1ID_Y", "OZ_KOD_1_DEGISKEN");
				}
				else  if (hangi.equals("oz2"))
				{
					f_Access.urun_degisken_kayit("OZ2ID_Y","OZ_KOD_2_DEGISKEN", "OZEL_KOD_2", textField_1.getText());
					rs =  f_Access.stk_kod_degisken_oku("OZEL_KOD_2", "OZ2ID_Y", "OZ_KOD_2_DEGISKEN");
				}
			}
			sifirla();
			textField.setText("");
			grid_doldur();
			textField.requestFocus();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage());
		}
	}
}

package OBS_2025;

import java.awt.Font;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.RowSorter.SortKey;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.commons.lang.StringUtils;

import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

@SuppressWarnings({"serial" , "static-access" })
public class MIZAN extends JInternalFrame {
	public static JTable table;
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	private static JLabel lblbakiye ;
	private static JLabel lblalacak ;
	private static JLabel lblborc ;
	public static JSplitPane splitPane;
	private static JLabel lblNewLabel_3 ;


	public MIZAN() {

		setResizable(true);
		setTitle("CARI MIZAN");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0,0, 910, 600);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(MIZAN.class.getResource("/ICONLAR/icons8-check-30.png")), 16, 16));//
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		ScrollPaneWin11 jScrollPane1 = new ScrollPaneWin11();
		jScrollPane1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		splitPane.setLeftComponent(jScrollPane1);

		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				String status = (String)getValueAt(row,0);
				int deger = 0 ;
				try {
					deger = Integer.parseInt( GLOBAL.setting_oku("CARI_MIZ_GRUP").toString());
				} catch (Exception e) {
					e.printStackTrace();}
				if (status.length() == deger) 
				{
					c.setBackground(oac.satBackColor);
					c.setFont(new Font(table.getFont().getFontName(),1 ,12));
				} else 
				{
					c.setBackground(super.getBackground());
					c.setForeground(super.getForeground());
				}
				if (col == 5)
				{
					if (getValueAt(row,5) != null)
					{
						c.setFont(new Font(table.getFont().getFontName(),1 ,table.getFont().getSize()));
						if (status.length() == deger)
							c.setForeground(oac.satForeColor);
						else {
							if ((double)getValueAt(row,5) < 0)
								c.setForeground(new Color(128,0,0));
						}
					}
				}
				else 
				{
					if (status.length() == deger)
						c.setForeground(oac.satForeColor);
					else
						c.setForeground(super.getForeground());
				}
				if (isRowSelected(row)) {
					c.setBackground(table.getSelectionBackground());
					c.setForeground(table.getSelectionForeground());
                } 
				return c;
			}
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}
		table.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						table.requestFocusInWindow();
					}
				});
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				try {
					String[] parts;
					String deger ;
					deger = GLOBAL.setting_oku("PRG_FILTRE").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_DOWN_MASK : KeyEvent.ALT_DOWN_MASK) ) != 0))
						{
							getContentPane().setCursor(oac.WAIT_CURSOR);
							OBS_MAIN.btnFiltre.doClick();
							getContentPane().setCursor(oac.DEFAULT_CURSOR);
						}
					}
				}
				catch (Exception ex)
				{

				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
				{
					boolean varmi = OBS_MAIN.pencere_bak("EKSTRE");
					if (varmi  ) 
					{
						try {
							OBS_MAIN.pencere_aktiv_yap("EKSTRE");
						} catch (PropertyVetoException e1) {
							e1.printStackTrace();
						}
					}
					else
					{
						JInternalFrame internalFrame;
						internalFrame  = new EKSTRE();
						OBS_MAIN.desktopPane.add(internalFrame);
						internalFrame.setVisible(true);
					}
					try 
					{
						//FILTRE intFrame = new FILTRE();
						FILTRE.txtkodu.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
						EKSTRE.hisset();
					} 
					catch (NumberFormatException e1) 
					{
						e1.printStackTrace();
					}

				}
			}
		});

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		//table.setBorder(null);
		jScrollPane1.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);

		lblbakiye = new JLabel("0.00");
		//lblbakiye.setForeground(new Color(0, 0, 128));
		lblbakiye.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblbakiye.setHorizontalAlignment(SwingConstants.RIGHT);
		lblbakiye.setBounds(762, 5, 117, 14);
		panel.add(lblbakiye);

		lblalacak = new JLabel("0.00");
		//lblalacak.setForeground(new Color(0, 0, 128));
		lblalacak.setHorizontalAlignment(SwingConstants.RIGHT);
		lblalacak.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblalacak.setBounds(643, 6, 117, 14);
		panel.add(lblalacak);

		lblborc = new JLabel("0.00");
		//lblborc.setForeground(new Color(0, 0, 128));
		lblborc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblborc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblborc.setBounds(524, 6, 117, 14);
		panel.add(lblborc);
		
		JLabel lblNewLabel_2 = new JLabel("Satir Sayisi :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 5, 85, 14);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("0");
		//lblNewLabel_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(100, 5, 51, 14);
		panel.add(lblNewLabel_3);

	}
	public static void hisset () 
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			//**************
			lblbakiye.setText("0.00");
			lblalacak.setText("0.00");
			lblborc.setText("0.00");
			lblNewLabel_3.setText("0");
			String o1 = "" ;
			String o2 = "" ;
			String hangi_tur = "" ;
			if(FILTRE.comboBox != null)
				hangi_tur = FILTRE.comboBox.getItemAt(FILTRE.comboBox.getSelectedIndex());
			else
				return;
			if (hangi_tur.equals("Borclu Hesaplar"))
				o1 = " HAVING ROUND(SUM(SATIRLAR.ALACAK - SATIRLAR.BORC),2) < 0 " ;
			else if (hangi_tur.equals("Alacakli Hesaplar"))
				o1 = " HAVING ROUND(SUM(SATIRLAR.ALACAK - SATIRLAR.BORC),2) > 0 " ;
			else if (hangi_tur.equals( "Bakiyesi 0 Olanlar" ))
				o1 = " HAVING ROUND(SUM(SATIRLAR.ALACAK - SATIRLAR.BORC),2) = 0" ;
			else if (hangi_tur.equals( "Bakiyesi 0 Olmayanlar" ))
				o1 = " HAVING ROUND(SUM(SATIRLAR.ALACAK - SATIRLAR.BORC),2) <> 0" ;
			o2 = " ORDER BY SATIRLAR.HESAP ASC " ;
			rs = c_Access.mizan(FILTRE.txtilk.getText(),FILTRE.txtson.getText() ,
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_2_1) ,
					FILTRE.txticins.getText(),FILTRE.txtscins.getText() ,
					FILTRE.txtikarton.getText(),FILTRE.txtskarton.getText() ,
					o1 , o2);

			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() )
			{
				
				return;
			}
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			lblNewLabel_3.setText(FORMATLAMA.doub_0(table.getRowCount()));
			ara_ayir();

			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(95);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(350);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);

			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();

			
			table.setRowHeight(21);
			int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
			table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
			table.setRowSelectionInterval(lastRow, lastRow);
			
			//***
			DefaultTableModel mdl = (DefaultTableModel) table.getModel();
			double borc = 0,alacak = 0 ;
			for (int i = 0 ; i <= mdl.getRowCount()-1;i++)
			{
				borc  +=  (double)   (mdl.getValueAt(i , 3) == null ? 0 :(double)mdl.getValueAt(i , 3) );
				alacak  +=  (double)   (mdl.getValueAt(i , 4) == null ? 0 :(double)mdl.getValueAt(i , 4) );
			}
			lblalacak.setText(FORMATLAMA.doub_2(alacak));
			lblborc.setText(FORMATLAMA.doub_2(borc));
			lblbakiye.setText(FORMATLAMA.doub_2(alacak - borc));
			//***
			
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
			String deger;
			String[] parts;
			Font bigFont;
			deger = GLOBAL.setting_oku("CARI_MIZAN").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			table.setFont(bigFont);
			table.repaint();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}

	private static void ara_ayir()
	{
		try
		{
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			Vector<Object> data = new Vector<Object>();
			int satir = table.getRowCount()  ;
			int deger = Integer.parseInt( GLOBAL.setting_oku("CARI_MIZ_GRUP").toString());
			String onceki = "" ;
			for (int i = 1; i <= satir  -1 ; i ++)
			{
				if (  model.getValueAt((i) - 1 , 0).toString().length() < deger  )
				{
					int kj = 0 ;
					kj = deger - model.getValueAt((i) - 1 , 0).toString().length() ;
					onceki =  model.getValueAt((i) - 1 , 0).toString() + StringUtils.repeat(" ", kj);
				}
				else
					onceki = model.getValueAt((i) - 1 , 0).toString().substring(0, deger   );
				if (! model.getValueAt(i , 0).toString().substring(0, model.getValueAt(i  , 0).toString().length() < deger  ? model.getValueAt(i  , 0).toString().length()  : deger ).equals(onceki)) 
				{
					data = new Vector<Object>();
					data.add(model.getValueAt(i , 0).toString().substring(0, model.getValueAt(i  , 0).toString().length() < deger  ? model.getValueAt(i  , 0).toString().length()  : deger ));
					data.add(isimoku(model.getValueAt(i , 0).toString().substring(0, model.getValueAt(i  , 0).toString().length() < deger  ? model.getValueAt(i  , 0).toString().length()  : deger )));
					data.add("---");
					double doub = 0 ;
					data.add(doub);
					data.add(doub);
					data.add(doub);
					model.addRow(data);
				}
				onceki = "";
			}
			data = new Vector<Object>();
			if(model.getValueAt(0 , 0).toString().length() < deger)
			{
				//data.add(model.getValueAt(0 , 0).toString());
				//data.add(isimoku(model.getValueAt(0 , 0).toString()));
				//data.add("---");
				//double doub = 0 ;
				//data.add(doub);
				//data.add(doub);
				//data.add(doub);
				//model.addRow(data);
			}
			else {
				data.add(model.getValueAt(0 , 0).toString().substring(0,deger));
				data.add(isimoku(model.getValueAt(0 , 0).toString().substring(0,deger)));
				data.add("---");
				double doub = 0 ;
				data.add(doub);
				data.add(doub);
				data.add(doub);
				model.addRow(data);
			}
			//*****
			table.setAutoCreateRowSorter(true);
			DefaultRowSorter<?, ?> sorter = ((DefaultRowSorter<?, ?>)table.getRowSorter()); 
			ArrayList<SortKey> list = new ArrayList<SortKey>();
			list.add( new RowSorter.SortKey(0, SortOrder.ASCENDING) );
			list.add( new RowSorter.SortKey(2,SortOrder.ASCENDING) );
			sorter.setSortKeys(list);
			sorter.sort();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private static String isimoku(String kod) {
		String sonuc = "" ;
		try
		{
			ResultSet	rs = null;
			rs = c_Access.hesap_adi_oku(kod);
			if (!rs.isBeforeFirst() )
				sonuc = "" ;
			else
			{
				rs.next();
				sonuc=rs.getString("UNVAN");
			}
			
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
		return sonuc ;
	}
}

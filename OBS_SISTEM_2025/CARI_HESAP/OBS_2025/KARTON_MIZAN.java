package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;

@SuppressWarnings({"serial","static-access","deprecation"})
public class KARTON_MIZAN extends JInternalFrame {
	public static JTable table;
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	private static JLabel lblbakiye ;
	private static JLabel lblalacak ;
	private static JLabel lblborc ;
	public static JSplitPane splitPane;
	private static JLabel lblNewLabel_3 ;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KARTON_MIZAN frame = new KARTON_MIZAN();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public KARTON_MIZAN() {
		setResizable(true);
		setTitle("CARI KARTON MIZAN");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0,0, 1000, 600);

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		ScrollPaneWin11 jScrollPane1 = new ScrollPaneWin11();
		splitPane.setLeftComponent(jScrollPane1);

		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				String status = (String)getValueAt(row,0);
				if (status == null) 
				{
					c.setBackground(Color.PINK);
					c.setForeground(Color.BLUE);
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
						if ((double)getValueAt(row,5) < 0)
						{
							c.setForeground(new Color(128,0,0));
						}
					}
				}
				else 
				{
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
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
							OBS_MAIN.btnFiltre.doClick();
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
						FILTRE.txtkodu.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
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
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);

		lblbakiye = new JLabel("0.00");
		lblbakiye.setForeground(new Color(0, 0, 128));
		lblbakiye.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblbakiye.setHorizontalAlignment(SwingConstants.RIGHT);
		lblbakiye.setBounds(840, 5, 117, 14);
		panel.add(lblbakiye);

		lblalacak = new JLabel("0.00");
		lblalacak.setForeground(new Color(0, 0, 128));
		lblalacak.setHorizontalAlignment(SwingConstants.RIGHT);
		lblalacak.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblalacak.setBounds(721, 6, 117, 14);
		panel.add(lblalacak);

		lblborc = new JLabel("0.00");
		lblborc.setForeground(new Color(0, 0, 128));
		lblborc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblborc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblborc.setBounds(602, 6, 117, 14);
		panel.add(lblborc);
		
		JLabel lblNewLabel_2 = new JLabel("Satir Sayisi :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 5, 85, 14);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("0");
		lblNewLabel_3.setForeground(new Color(0, 0, 128));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(100, 5, 51, 14);
		panel.add(lblNewLabel_3);

	}
	public static void hisset () 
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			lblbakiye.setText("0.00");
			lblalacak.setText("0.00");
			lblborc.setText("0.00");
			lblNewLabel_3.setText("0");
			String o1 = "" ;
			String o2 = "" ;
			String hangi_tur = "" ;
			if(FILTRE.comboBox != null)
			{
				hangi_tur = FILTRE.comboBox.getItemAt(FILTRE.comboBox.getSelectedIndex());
			}
			else {
				return;
			}
			if (hangi_tur.equals("Borclu Hesaplar") )
			{ o1 = " HAVING ROUND(SUM(SATIRLAR.ALACAK - SATIRLAR.BORC),2) < 0 " ; }
			else if (hangi_tur.equals("Alacakli Hesaplar")) 
			{o1 = " HAVING ROUND(SUM(SATIRLAR.ALACAK - SATIRLAR.BORC),2) > 0 " ;}
			else if (hangi_tur.equals( "Bakiyesi 0 Olanlar" )) 
			{ o1 = " HAVING ROUND(SUM(SATIRLAR.ALACAK - SATIRLAR.BORC),2) = 0" ;}
			else if (hangi_tur.equals( "Bakiyesi 0 Olmayanlar" ))
			{ o1 = " HAVING ROUND(SUM(SATIRLAR.ALACAK - SATIRLAR.BORC),2) <> 0" ;}
			o2 = " ORDER BY HESAP.KARTON,SATIRLAR.HESAP ASC " ;
			rs = c_Access.karton_mizan(FILTRE.txtilk.getText(),FILTRE.txtson.getText() ,
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
			tc.setMinWidth(50);

			
			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(95);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(350);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);

			tc = tcm.getColumn(6);
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
				borc  +=  (double)   (mdl.getValueAt(i , 4) == null ? 0 :(double)mdl.getValueAt(i , 4) );
				alacak  +=  (double)   (mdl.getValueAt(i , 5) == null ? 0 :(double)mdl.getValueAt(i , 5) );
			}
			lblalacak.setText(FORMATLAMA.doub_2(alacak));
			lblborc.setText(FORMATLAMA.doub_2(borc));
			lblbakiye.setText(FORMATLAMA.doub_2(alacak - borc));
			//***
			table.repaint();
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
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Karton Mizan Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void ara_ayir()
	{
		try
		{
			if(table.getRowCount()==2)
			{
				return;
			}
			DefaultTableModel mdll = (DefaultTableModel) table.getModel();
			int satir = 0;
			String ustsatString = "";
			String altsatString ="" ;
			do
			{
				if(mdll.getValueAt(satir + 1, 0) == null)
				{
					mdll.insertRow(satir + 1, new Object[]{});
					table.repaint();
					break;
				}
				ustsatString = mdll.getValueAt(satir, 0).toString() ;
				altsatString = mdll.getValueAt(satir + 1, 0).toString() ;
				if (! ustsatString.equals(altsatString))
				{
					mdll.insertRow(satir +1, new Object[]{});
					table.repaint();
					satir += 1;
				}
				satir += 1;
			}
			while (satir < table.getRowCount()-1);
			table.repaint();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),"Karton Mizan Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
}
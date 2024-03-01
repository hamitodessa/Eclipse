package OBS_2025;

import java.awt.Font;
import java.awt.Point;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.KAMBIYO_ACCESS;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

@SuppressWarnings({"serial","static-access","deprecation"})
public class CEK_RAPOR extends JInternalFrame {
	private static JTable table;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();

	static KAMBIYO_ACCESS ka_Access = new KAMBIYO_ACCESS(OBS_SIS_2025_ANA_CLASS._IKambiyo , OBS_SIS_2025_ANA_CLASS._IKambiyo_Loger);
	public static ScrollPaneWin11 scrollPane;
	private static JLabel lbladet;
	private static JLabel lblToplam;

	public CEK_RAPOR() {
		setTitle("CEK RAPOR");
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(0, 0, 1000, 600);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		
		scrollPane = new ScrollPaneWin11();
		splitPane.setLeftComponent(scrollPane);

		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		JTableHeader header = table.getTableHeader();
		header.addMouseListener(new TableHeaderMouseListener(table));
		
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			table.setGridColor(oac.gridcolor);
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
						if ((e.getKeyCode() == c) && ((e.getModifiersEx() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
							OBS_MAIN.btnFiltre.doClick();
						}
					}
				}
				catch (Exception ex)
				{
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					boolean varmi = OBS_MAIN.pencere_bak("CEK TAKIP");
					if (varmi  ) 
					{
						try {
							OBS_MAIN.pencere_aktiv_yap("CEK TAKIP");
						} catch (PropertyVetoException e1) {
							e1.printStackTrace();
						}
					}
					else
					{
						JInternalFrame internalFrame;
						internalFrame  = new CEK_TAKIP();
						OBS_MAIN.desktopPane.add(internalFrame);
						internalFrame.setVisible(true);
					}
					try 
					{
						CEK_TAKIP.textField.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
						CEK_TAKIP.kontrol();
					} 
					catch (NumberFormatException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		});

		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Kayit Sayisi :");
		label.setBounds(5, 5, 85, 14);
		panel.add(label);
		
		lbladet = new JLabel("0");
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setBounds(100, 5, 47, 14);
		panel.add(lbladet);
		
		lblToplam = new JLabel("");
		lblToplam.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblToplam.setHorizontalAlignment(SwingConstants.RIGHT);
		lblToplam.setBounds(687, 5, 150, 14);
		panel.add(lblToplam);

	}
	public static void hisset()
	{
		try {
			ResultSet rs = null ;
			long startTime = System.currentTimeMillis(); 
			rs = ka_Access.cek_rapor(FILTRE.txtcn1.getText(), FILTRE.txtcn2.getText(),
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_6), TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_6_1),
					FILTRE.txtgb1.getText(), FILTRE.txtgb2.getText(),
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_7), TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_8), 
					FILTRE.txtcb1.getText(), FILTRE.txtcb2.getText(),
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_9), TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_10),
					FILTRE.txtgh1.getText(), FILTRE.txtgh2.getText(),
					FILTRE.txtch1.getText(), FILTRE.txtch2.getText(),
					FILTRE.txtc1.getText(), FILTRE.txtc2.getText(),
					FILTRE.txtd1.getText(), FILTRE.txtd2.getText(),
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_6_2), TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_6_2_1),
					FILTRE.cmbg.getItemAt(FILTRE.cmbg.getSelectedIndex()).toString().equals("Bos") ? "" :FILTRE.cmbg.getItemAt(FILTRE.cmbg.getSelectedIndex()).toString() + "%",
					FILTRE.cmbc.getItemAt(FILTRE.cmbc.getSelectedIndex()).toString().equals("Bos") ? "" :FILTRE.cmbc.getItemAt(FILTRE.cmbc.getSelectedIndex()).toString() + "%");
			if (!rs.isBeforeFirst() ) {  
				lbladet.setText(FORMATLAMA.doub_0(0));
				return;
			} 
			GRID_TEMIZLE.grid_temizle(table);
			table.setModel(DbUtils.resultSetToTableModel(rs));

			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(60);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new TARIH());
			tc.setMinWidth(70);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new TARIH());
			tc.setMinWidth(80);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(150);

			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(100);

			tc = tcm.getColumn(7);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(60);

			tc = tcm.getColumn(8);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(100);

			tc = tcm.getColumn(9);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(60);

			tc = tcm.getColumn(10);
			tc.setHeaderRenderer(new SOLA());
			//tc.setCellRenderer(new TARIH());
			tc.setMinWidth(80);

			tc = tcm.getColumn(11);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(110);

			tc = tcm.getColumn(12);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(13);
			tc.setHeaderRenderer(new SOLA());
			//tc.setCellRenderer(new TARIH());
			tc.setMinWidth(80);

			tc = tcm.getColumn(14);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(15);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(110);

			tc = tcm.getColumn(16);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(60);

			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			//table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);
			int toplam=0;
			for (int i = 0 ; i <= table.getRowCount() -1 ; i ++)
			{
				if (table.getValueAt(i , 10).toString().equals("01.01.1900"))
					table.setValueAt("",i , 10);
				if (table.getValueAt(i , 13).toString().equals("01.01.1900"))
					table.setValueAt("",i , 13);
				toplam += (double) table.getValueAt(i , 8);
			}
			lblToplam.setText(FORMATLAMA.doub_2(toplam));
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			lbladet.setText(FORMATLAMA.doub_0(table.getRowCount()));
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
		}
	}
	public class TableHeaderMouseListener extends MouseAdapter {
	    private JTable table;
	    public TableHeaderMouseListener(JTable table) {
	        this.table = table;
	    }
	    public void mouseClicked(MouseEvent event) {
	        Point point = event.getPoint();
	        int column = table.columnAtPoint(point);
	        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
	        table.setRowSorter(sorter);
	        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	        int columnIndexToSort = column;
	        sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
	        sorter.setSortKeys(sortKeys);
	        sorter.sort();
	    }
	}
}

//table.getRowSorter().addRowSorterListener(new RowSorterListener() {
//    @Override
//    public void sorterChanged(RowSorterEvent e) {
//        // Sorting changed
//    }
//});

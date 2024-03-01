package OBS_2025;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;

@SuppressWarnings({"serial", "static-access","unused" })
public class TAH_DOKUM extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();

	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);
	private static JTable table;
	public static JSplitPane splitPane;
	private static JTable table_1;

	
	public TAH_DOKUM() {
		setTitle("TAHSILAT DOKUM");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0,0, 900, 600);

		splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(null));
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 30));
		panel.setMaximumSize(new Dimension(0, 30));
		panel.setLayout(null);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);

		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		splitPane_1.setLeftComponent(scrollPane);

		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
			
		};
		scrollPane.setViewportView(table);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent lse) {
			        if (!lse.getValueIsAdjusting()) {
			        	 DefaultTableModel model = (DefaultTableModel)table.getModel();
			        	 if (model.getRowCount() == 0) return ;
			        	 if (table.getSelectedRow()  < 0) return;
			        	 table.setCursor(oac.WAIT_CURSOR);
			        	 detay_doldur(model.getValueAt(table.getSelectedRow() , 0).toString(),model.getValueAt(table.getSelectedRow() , 4).toString());
			        	 table.setCursor(oac.DEFAULT_CURSOR);
			        }
			    }
			});

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
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		
		ScrollPaneWin11 scrollPane_1 = new ScrollPaneWin11();
		scrollPane_1.setMinimumSize(new Dimension(0, 100));
		scrollPane_1.setMaximumSize(new Dimension(0, 100));
		
		splitPane_1.setRightComponent(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setSurrendersFocusOnKeystroke(true);
		table_1.setShowHorizontalLines(true);
		table_1.setShowVerticalLines(true);
		scrollPane_1.setViewportView(table_1);
	}
	public static void yenile()
	{
		try {
			
			//int cins, int tur, String ilktarih, String sontarih, String ilkevr, String sonevr
			
		GRID_TEMIZLE.grid_temizle(table);
		long startTime = System.currentTimeMillis();
		ResultSet	rs = null;
		rs = c_Access.tah_listele(FILTRE.comboBox_85.getSelectedIndex(), 
				FILTRE.comboBox_86.getSelectedIndex(),
				TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_35),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_36),
				FILTRE.textTaheno1.getText(),FILTRE.textTaheno2.getText(),
				FILTRE.textTahck1.getText(),FILTRE.textTahck2.getText()	);
		
		if (!rs.isBeforeFirst() ) {  
			return;
		} 
		
		table.setModel(DbUtils.resultSetToTableModel(rs));
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc;
		
		tc = tcm.getColumn(0);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(70);
		tc.setMaxWidth(70);
		

		tc = tcm.getColumn(1);
		tc.setHeaderRenderer(new SOLA());
		tc.setCellRenderer(new TARIH());
		tc.setMinWidth(70);
		tc.setMaxWidth(70);
		
		tc = tcm.getColumn(2);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(100);
		tc.setMaxWidth(100);
		
		tc = tcm.getColumn(3);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(100);
		tc.setMaxWidth(100);
		
		tc = tcm.getColumn(4);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(70);
		tc.setMaxWidth(70);
		
		tc = tcm.getColumn(5);
		tc.setHeaderRenderer(new SAGA());
		tc.setCellRenderer(new TABLO_RENDERER(2,true));
		tc.setMinWidth(120);
		tc.setMaxWidth(120);
		
		tc = tcm.getColumn(6);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(70);
		tc.setMaxWidth(70);
		
		tc = tcm.getColumn(7);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(70);
		tc.setMaxWidth(70);
		
		
		Dimension dd = th.getPreferredSize();
		dd.height = 30;
		th.setPreferredSize(dd); 
		th.repaint();
		table.setRowHeight(21);
		
		long endTime = System.currentTimeMillis();
		long estimatedTime = endTime - startTime; 
		double seconds = (double)estimatedTime/1000; 
		OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

		String deger;
		String[] parts;
		Font bigFont;
		deger = GLOBAL.setting_oku("CARI_EKSTRE").toString();
		deger = deger.substring(1, deger.length()-1);
		parts = deger.split(",");
		bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
		table.setFont(bigFont);
		table.repaint();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void detay_doldur(String evrno,String cins)
	{
		try {
			
		
		int cin = 0 ;
		if(cins.equals("Tadilat"))
			cin = 1 ;
		
		long startTime = System.currentTimeMillis(); 
		
		GRID_TEMIZLE.grid_temizle(table_1);
		
		ResultSet	rs = null;
		rs = c_Access.tah_cek_doldur(evrno,cin	);
		
		if (!rs.isBeforeFirst() ) {  
			return;
		} 
		table_1.setModel(DbUtils.resultSetToTableModel(rs));
		JTableHeader th = table_1.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc;
		
		table_1.removeColumn(table_1.getColumnModel().getColumn(0));
		table_1.removeColumn(table_1.getColumnModel().getColumn(0));
		
		tc = tcm.getColumn(0);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(200);
	
		
		tc = tcm.getColumn(1);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(150);
		
		
		tc = tcm.getColumn(2);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(100);
		tc.setMaxWidth(100);
		
		tc = tcm.getColumn(3);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(100);
		tc.setMaxWidth(100);
		
		tc = tcm.getColumn(4);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(150);
		tc.setMaxWidth(150);
		
		tc = tcm.getColumn(5);
		tc.setHeaderRenderer(new SOLA());
		tc.setCellRenderer(new TARIH());
		tc.setMinWidth(70);
		tc.setMaxWidth(70);
		
		tc = tcm.getColumn(6);
		tc.setHeaderRenderer(new SAGA());
		tc.setCellRenderer(new TABLO_RENDERER(2,true));
		tc.setMinWidth(100);
		tc.setMaxWidth(100);
		
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
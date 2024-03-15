package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.CheckBoxRenderer;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.IMAGE_RENDERER;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

@SuppressWarnings({"serial","static-access"})
public class ADRESLER extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(OBS_SIS_2025_ANA_CLASS._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);
	private JTable table;
	private Obs_TextFIeld textField;
	private JLabel lbladet ;
	
	public ADRESLER() { 
	setTitle("ADRES DETAY");
	setResizable(true);
	setMaximizable(true);
	setIconifiable(true);
	setClosable(true);
	setBounds(0, 0, 1000, 600);
	setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(ADRESLER.class.getResource("/ICONLAR/icons8-contact-us-30.png")), 16, 16));//
	JSplitPane splitPane = new JSplitPane();
	splitPane.setDividerSize(0);
	splitPane.setResizeWeight(0.0);
	splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
	getContentPane().add(splitPane, BorderLayout.CENTER);
	
	JSplitPane splitPane_1 = new JSplitPane();
	splitPane_1.setDividerSize(0);
	splitPane_1.setResizeWeight(1.0);
	splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
	splitPane.setRightComponent(splitPane_1);
	

	ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
	scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

	splitPane_1.setLeftComponent(scrollPane);

	table = new JTable(){
		public boolean isCellEditable(int row, int column) {     return false;

		}
		public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
			Component c = super.prepareRenderer(renderer, row, col);
			if( table.getRowSorter() != null)
			{
				if(table.getModel().getValueAt(table.getRowSorter().convertRowIndexToModel(row),25)== null)
					table.setRowHeight(row, 21);
				else
					table.setRowHeight(row, 100);
			}
			else 
			{
				if(table.getModel().getValueAt(row,25)== null)
					table.setRowHeight(row, 21);
				else
					table.setRowHeight(row, 100);
			}
			return c;
		}
	};
	if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
		table.setGridColor(oac.gridcolor);

	table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
	table.setShowHorizontalLines(true);
	table.setShowVerticalLines(true);
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	table.setSurrendersFocusOnKeystroke(true);
	scrollPane.setViewportView(table);

	JPanel panel = new JPanel();
	splitPane.setLeftComponent(panel);
	panel.setMinimumSize(new Dimension(0, 30));
	panel.setMaximumSize(new Dimension(0, 30));
	panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

	panel.setLayout(null);

	JLabel lblNewLabel = new JLabel("Arama");
	lblNewLabel.setBounds(21, 7, 46, 14);
	panel.add(lblNewLabel);

	textField = new Obs_TextFIeld(30,"");
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
	textField.addAncestorListener(new AncestorListener() {
		@Override
		public void ancestorRemoved(AncestorEvent pEvent) {
		}
		@Override
		public void ancestorMoved(AncestorEvent pEvent) {
		}
		@Override
		public void ancestorAdded(AncestorEvent pEvent) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					textField.requestFocusInWindow();
				}
			});
		}
	});

	textField.setFont(new Font("Tahoma", Font.BOLD, 11));
	textField.setBounds(83, 4, 321, 20);
	panel.add(textField);
	textField.setColumns(10);
	
	JPanel panelalt = new JPanel();
	panelalt.setMinimumSize(new Dimension(0, 25));
	panelalt.setMaximumSize(new Dimension(0, 25));
	panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

	splitPane_1.setRightComponent(panelalt);
	panelalt.setLayout(null);
	
	JLabel lblNewLabel_1 = new JLabel("Satir Sayisi:");
	lblNewLabel_1.setBounds(10, 5, 85, 14);
	panelalt.add(lblNewLabel_1);
	
	lbladet = new JLabel("0");
	//lbladet.setForeground(new Color(0, 0, 128));
	lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
	lbladet.setBounds(100, 5, 75, 14);
	panelalt.add(lbladet);
	
	hisset();

	}
	private void hisset() 
	{
		long startTime = System.currentTimeMillis(); 
		ResultSet rs = null ;
		try
		{
			rs = a_Access.adres("M_Kodu","") ;
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
			tc.setMinWidth(100);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(275);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(200);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(200);

			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);
			
			tc = tcm.getColumn(25);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new IMAGE_RENDERER(100,100));

			for (int i = 4; i<= 25; i++)
			{
				tc = tcm.getColumn(i);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(100);
			}
			tc = tcm.getColumn(19);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new CheckBoxRenderer());
			tc.setMinWidth(50);
			
			tc = tcm.getColumn(20);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new CheckBoxRenderer());
			tc.setMinWidth(50);
			
			table.removeColumn(table.getColumnModel().getColumn(26));
			
			lbladet.setText( String.format("%,d %n" ,  table.getRowCount()));
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime;
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

			String deger;
			String[] parts;
			Font bigFont;
			deger = GLOBAL.setting_oku("CARI_HSPPLN").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			table.setFont(bigFont);
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage());
		}
	}
	public void arama()  
	{
		try {
			if (textField.getText().equals(""))
				table.setRowSorter(null);
			else
			{
				TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
				RowFilter<TableModel, Object> rf = null;
				rf = RowFilter.regexFilter("(?iu)" +  textField.getText().toLowerCase() , 1);
				sorter.setRowFilter(rf);
				table.setRowSorter(sorter);
				table.revalidate();
				table.repaint();		
			}
		} catch (Exception e) {
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, e.getMessage());
		}
	}
}

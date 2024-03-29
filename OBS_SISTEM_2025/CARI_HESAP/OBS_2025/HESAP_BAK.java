package OBS_2025;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.Font;
import java.sql.ResultSet;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

@SuppressWarnings({"serial" , "static-access"})
public class HESAP_BAK extends JInternalFrame {
	
	private JTable tblhesap;
	private JTable tblkarton;
	private JTable tbldetay;
	private Obs_TextFIeld textField;
	private JLabel lblNewLabel_1 ;
	private JLabel lblNewLabel_2_2 ;
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	public HESAP_BAK() {
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("HESAP BAK");
		setBounds(0, 0, 1091, 600);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(HESAP_BAK.class.getResource("/ICONLAR/icons8-order-history-30.png")), 16, 16));//
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(10);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(10);
		splitPane_1.setResizeWeight(1.0);
		splitPane.setLeftComponent(splitPane_1);
		splitPane_1.setMinimumSize(new Dimension(0, 200));
		splitPane_1.setMaximumSize(new Dimension(0, 200));

		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		splitPane_1.setLeftComponent(scrollPane);

		tblhesap = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false; } 
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				String status = (String)tblhesap.getModel().getValueAt(row,0);
				 if( tblhesap.getRowSorter() == null)
				 {
					if (status.length() == 3)
					{
						c.setBackground(oac.satBackColor);
						c.setForeground(oac.satForeColor);
						Font fnt = new Font(tblhesap.getFont().getFontName(),1 ,12);
						c.setFont(fnt);
					} else 
					{
						c.setBackground(tblhesap.getBackground());
						c.setForeground(tblhesap.getForeground());
					}   
					if (isRowSelected(row)) {
						c.setBackground(tblhesap.getSelectionBackground());
						c.setForeground(tblhesap.getSelectionForeground());
	                } 
				}
				 else 
				 {
					 int satt = tblhesap.getRowSorter().convertRowIndexToModel(row);
					 String statuss = (String)tblhesap.getModel().getValueAt(satt,0);
					 if (statuss.length() == 3)
						{
							c.setBackground(oac.satBackColor);
							c.setForeground(oac.satForeColor);
							Font fnt = new Font(tblhesap.getFont().getFontName(),1 ,12);
							c.setFont(fnt);
						} 
					 else
						 {
						 	c.setBackground(tblhesap.getBackground());
						 	c.setForeground(tblhesap.getForeground());
						 }
					 if (isRowSelected(row)) 
					 {
						 c.setBackground(tblhesap.getSelectionBackground());
						 c.setForeground(tblhesap.getSelectionForeground());
					 } 
				}
				return c;
			}
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			tblhesap.setGridColor(oac.gridcolor);
		tblhesap.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) 
				{
					int row = tblhesap.getSelectedRow() == -1 ? 0: tblhesap.getSelectedRow();
					if (tblhesap.getRowSorter()!=null) 
					{
						row = tblhesap.getRowSorter().convertRowIndexToModel(row);
						tblhesap.setCursor(oac.WAIT_CURSOR);
						karton_doldur(row);
						detay_doldur(tblhesap.getModel().getValueAt(row, 0).toString());
						tblhesap.setCursor(oac.DEFAULT_CURSOR);
					}
					else
					{
						tblhesap.setCursor(oac.WAIT_CURSOR);
						karton_doldur(row);
						detay_doldur(tblhesap.getModel().getValueAt(row, 0).toString());
						tblhesap.setCursor(oac.DEFAULT_CURSOR);
					}
				}
			}
		});
		tblhesap.setShowHorizontalLines(true);
		tblhesap.setShowVerticalLines(true);
		tblhesap.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblhesap);

		ScrollPaneWin11 scrollPane_1 = new ScrollPaneWin11();
		scrollPane_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		splitPane_1.setRightComponent(scrollPane_1);
		scrollPane_1.setMinimumSize(new Dimension(0, 410));
		scrollPane_1.setMaximumSize(new Dimension(0, 410));

		tblkarton = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		tblkarton.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					if (tblkarton.getSelectedRow() < 0) return ;
					tblkarton.setCursor(oac.WAIT_CURSOR);
					detay_doldur(tblkarton.getModel().getValueAt(tblkarton.getSelectedRow(), 0).toString());
					tblkarton.setCursor(oac.DEFAULT_CURSOR);
				}
			}
		});
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			tblkarton.setGridColor(oac.gridcolor);

		tblkarton.setShowHorizontalLines(true);
		tblkarton.setShowVerticalLines(true);
		tblkarton.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(tblkarton);

		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setDividerSize(0);
		splitPane_2.setResizeWeight(0.0);
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_2);

		ScrollPaneWin11 scrollPane_2 = new ScrollPaneWin11();
		scrollPane_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		splitPane_2.setRightComponent(scrollPane_2);

		tbldetay = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				if (col == 7)
				{
					if (getValueAt(row,7) != null)
					{
						double tut = Double.parseDouble(getValueAt(row,7).toString()) ;
						if (tut < 0)
						{
							c.setForeground(new Color(128,0,0));
							Font fnt = new Font(tbldetay.getFont().getFontName(),1 ,tbldetay.getFont().getSize());
							c.setFont(fnt);
						}
						else
							c.setForeground(super.getForeground());
					}
				}
				return c;
			}
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			tbldetay.setGridColor(oac.gridcolor);

		tbldetay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					boolean varmi = OBS_MAIN.pencere_bak("DEKONT");
					if (varmi  ) 
					{
						try {
							OBS_MAIN.pencere_aktiv_yap("DEKONT");
						} catch (PropertyVetoException e1) {
							e1.printStackTrace();
						}
					}
					else
					{
						JInternalFrame internalFrame;
						internalFrame  = new DEKONT();
						OBS_MAIN.desktopPane.add(internalFrame);
						internalFrame.setVisible(true);
					}
					try 
					{
						DEKONT.txtevrak.setText(tbldetay.getValueAt(tbldetay.getSelectedRow(), 1).toString());
						DEKONT.fiskont();
					} 
					catch (NumberFormatException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		});
		tbldetay.setShowHorizontalLines(true);
		tbldetay.setShowVerticalLines(true);
		tbldetay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(tbldetay);

		ScrollPaneWin11 jScrollPane1alt = new ScrollPaneWin11(); //1091-30
		jScrollPane1alt.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		jScrollPane1alt.setMinimumSize(new Dimension(0,40));
		splitPane_2.setLeftComponent(jScrollPane1alt);
		
		JPanel panel = new JPanel();
		jScrollPane1alt.setViewportView(panel);

		panel.setPreferredSize(new Dimension(1060,35));
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Arama");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 7, 61, 14);
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
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(81, 5, 364, 20);
		panel.add(textField);
		textField.setColumns(10);

		lblNewLabel_1 = new JLabel("0.00");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(914, 7, 134, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("+ / - Bakiye");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(797, 7, 91, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Satir Sayisi :");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setBounds(480, 7, 85, 14);
		panel.add(lblNewLabel_2_1);

		lblNewLabel_2_2 = new JLabel("0");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_2.setBounds(570, 7, 50, 14);
		panel.add(lblNewLabel_2_2);

		hesap_doldur();
		if (tblhesap.getRowCount() > 0)
		{
			karton_doldur(0);
			GRID_TEMIZLE.grid_temizle(tbldetay);
			detay_doldur(tblhesap.getModel().getValueAt(0, 0).toString());
		}
		else
		{
			//	GRID_TEMIZLE.grid_temizle(tblkarton);
			//	lblNewLabel_1.setText("0.00");
			//	GRID_TEMIZLE.grid_temizle(tbldetay);
		}

	}
	private void hesap_doldur() 
	{
		try
		{
			// Cursor = System.Windows.Forms.Cursors.WaitCursor
			// RG1.Visible = False
			ResultSet	rs = null;
			rs = c_Access.hp_pln();
			if (!rs.isBeforeFirst() ) {  
				GRID_TEMIZLE.grid_temizle(tbldetay);
				GRID_TEMIZLE.grid_temizle(tblkarton);
				lblNewLabel_1.setText("0.00");
				return;
			} 

			GRID_TEMIZLE.grid_temizle(tblhesap);
			tblhesap.setModel(DbUtils.resultSetToTableModel(rs));
			
			JTableHeader th = tblhesap.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(80);
			tc.setMaxWidth(80);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			//tc.setMinWidth(295);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(70);
			tc.setMaxWidth(70);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(65);
			tc.setMaxWidth(65);

			tblhesap.removeColumn(tcm.getColumn(4));

			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			tblhesap.setRowSelectionInterval(0, 0);
			tblhesap.setRowHeight(21);


			String deger;
			String[] parts;
			Font bigFont;
			deger = GLOBAL.setting_oku("CARI_HES_BAK").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			tblhesap.setFont(bigFont);
		}
		catch (Exception ex)
		{
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}

	}
	private void karton_doldur(int satir)
	{
		try
		{
			ResultSet	rs = null;
			rs = c_Access.karton_dold(tblhesap.getModel().getValueAt(satir, 2).toString());
			GRID_TEMIZLE.grid_temizle(tblkarton);
			if (!rs.isBeforeFirst() ) {  
				GRID_TEMIZLE.grid_temizle(tbldetay);
				lblNewLabel_1.setText("0.00");
				return;
			} 
			tblkarton.setModel(DbUtils.resultSetToTableModel(rs));
			
			JTableHeader th = tblkarton.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(80);
			tc.setMaxWidth(80);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			//tc.setMinWidth(250);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(60);
			tc.setMaxWidth(60);

			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			tblkarton.setRowSelectionInterval(0, 0);
			tblkarton.setRowHeight(21);

			String deger;
			String[] parts;
			Font bigFont;
			deger = GLOBAL.setting_oku("CARI_HES_BAK").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			tblkarton.setFont(bigFont);
		}
		catch (Exception ex)
		{
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void  detay_doldur(String hesap)
	{
		try
		{
			long startTime = System.currentTimeMillis(); 
			ResultSet	rs = null;
			getContentPane().setCursor(oac.WAIT_CURSOR);
			rs = c_Access.ekstre(hesap,"1900.01.01", "2100.12.31");
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			GRID_TEMIZLE.grid_temizle(tbldetay);
			if (!rs.isBeforeFirst() ) {  
				lblNewLabel_1.setText("0.00");
				lblNewLabel_2_2.setText("0");
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + 0 + " saniye");
				return;
			} 
			tbldetay.setModel(DbUtils.resultSetToTableModel(rs));
			
			JTableHeader th = tbldetay.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new TARIH());
			tc.setMinWidth(80);
			tc.setMaxWidth(80);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(60);
			tc.setMaxWidth(60);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			//tc.setMinWidth(420);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);
			tc.setMaxWidth(50);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(4,false));
			tc.setMinWidth(80);
			tc.setMaxWidth(80);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(100);
			tc.setMaxWidth(100);

			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(100);
			tc.setMaxWidth(100);

			tc = tcm.getColumn(7);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(120);
			tc.setMaxWidth(120);

			tc = tcm.getColumn(8);
			tc.setHeaderRenderer(new SOLA());
			//tc.setCellRenderer(new SAGA());
			tc.setMinWidth(50);
			tc.setMaxWidth(50);

			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			tbldetay.setRowSelectionInterval(0, 0);
			tbldetay.setRowHeight(21);
			if (tbldetay.getRowCount() == 0) 
			{
				lblNewLabel_1.setText("0.00");
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + 0 + " saniye");
				return ;
			}
			//************************ BAKIYE ************
			double qwe =Double.parseDouble( tbldetay.getModel().getValueAt(tbldetay.getRowCount()-1, 7).toString());
			lblNewLabel_1.setText(FORMATLAMA.doub_2(qwe)); 
			//********************************************
			if (tbldetay.getRowCount() != 0) 
			{
				int lastRow = tbldetay.convertRowIndexToView(tbldetay.getRowCount() - 1);
				tbldetay.scrollRectToVisible(tbldetay.getCellRect(tbldetay.getRowCount()-1, 0, true));
				tbldetay.setRowSelectionInterval(lastRow, lastRow);

				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime; 
				double seconds = (double)estimatedTime/1000;
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
				lblNewLabel_2_2.setText(FORMATLAMA.doub_0(tbldetay.getRowCount()));
			}

			String deger;
			String[] parts;
			Font bigFont;
			deger = GLOBAL.setting_oku("CARI_HES_BAK").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			tbldetay.setFont(bigFont);
		}
		catch (Exception ex)
		{
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	public void arama()  
	{
		if (textField.getText().equals(""))
			tblhesap.setRowSorter(null);
		else
		{
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tblhesap.getModel())); 
			sorter.setStringConverter(new TableStringConverter() {
				@Override
				public String toString(TableModel model, int row, int column) {
					return model.getValueAt(row, column).toString().toLowerCase();
				}
			});
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText().toLowerCase(),0,1));
			tblhesap.setRowSorter(sorter);
			if (tblhesap.getRowCount() > 0)
			{
				karton_doldur(tblhesap.getRowSorter().convertRowIndexToModel(0));
				GRID_TEMIZLE.grid_temizle(tbldetay);
				int sat = tblhesap.getRowSorter().convertRowIndexToModel(0);
				detay_doldur(tblhesap.getModel().getValueAt(sat, 0).toString());
			}
		}
	}
}

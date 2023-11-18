package OBS_2025;

import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.JTableHeader;
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
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings({"serial" , "static-access" , "deprecation"})
public class GUNLUK_ISLEM extends JInternalFrame {
	private static JTable table;
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static CARI_ACCESS  c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	public static JScrollPane scrollPane;
	public static JSplitPane splitPane;
	private static JLabel lblNewLabel_1;
	public GUNLUK_ISLEM() {
		setTitle("GUNLUK ISLEM");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0,0, 1025, 600);

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		splitPane.setLeftComponent(scrollPane);

		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
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

		table.addMouseListener(new MouseAdapter() {
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
						DEKONT.txtevrak.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
						DEKONT.fiskont();
					} 
					catch (NumberFormatException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		});
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.setSurrendersFocusOnKeystroke(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Satir Sayisi :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 5, 85, 14);
		panel.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("0");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(100, 5, 58, 14);
		panel.add(lblNewLabel_1);

	}
	public static void hisset()
	{
		try
		{
			long startTime = System.currentTimeMillis(); 
			ResultSet	rs = null;
			rs = c_Access.gunisl(TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_5),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_5_1));
			//rs = oac.cARI_HESAP_MSSQL.gunisl_proc( TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_5),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_5_1));
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lblNewLabel_1.setText(FORMATLAMA.doub_0(0));
				return;
			} 
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();

			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new TARIH());
			tc.setMinWidth(80);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(50);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(400);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(60);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(100);

			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,false));
			tc.setMinWidth(100);

			tc = tcm.getColumn(7);
			tc.setHeaderRenderer(new SOLA());
			//tc.setCellRenderer(new SAGA());
			tc.setMinWidth(30);

			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);

			int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
			table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
			table.setRowSelectionInterval(lastRow, lastRow);

			lblNewLabel_1.setText(FORMATLAMA.doub_0(table.getRowCount()));
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

			String deger;
			String[] parts;
			Font bigFont;
			deger = GLOBAL.setting_oku("CARI_GUN_ISL").toString();
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			table.setFont(bigFont);
		}
		catch (Exception ex)
		{
			 OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
}

package OBS_2025;

import java.awt.EventQueue;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.KUR_ACCESS;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KUR_RAPORLAMA extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KUR_ACCESS k_Access = new KUR_ACCESS(oac._IKur , OBS_SIS_2025_ANA_CLASS._IKur_Loger);

	private static JTable table;
	public static JScrollPane scrollPane ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KUR_RAPORLAMA frame = new KUR_RAPORLAMA();
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
	public KUR_RAPORLAMA() {
		setTitle("KUR RAPORLAMA");
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(0, 0, 700, 600);

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
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
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
							OBS_MAIN.btnNewButton_3.doClick();
						}
					}
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage());   
				}
			}
		});
		//table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		scrollPane.setViewportView(table);

	}
	public static void hisset()
	{
		try 
		{
			ResultSet rs ;
			long startTime = System.currentTimeMillis(); 
			rs = k_Access.kur_rapor(FILTRE.txtkc1.getText(), FILTRE.txtkc2.getText(),
					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_11), TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_11_1));
			if (!rs.isBeforeFirst() ) {  
				GRID_TEMIZLE.grid_temizle(table);
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + 0 + " saniye");
				return ;
			} 

			GRID_TEMIZLE.grid_temizle(table);
			table.setModel(DbUtils.resultSetToTableModel(rs));

			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new TARIH());
			//tc.setMinWidth(80);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			//tc.setMinWidth(30);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(4,false));
			//tc.setMinWidth(60);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(4,false));
			//tc.setMinWidth(60);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(4,false));
			//tc.setMinWidth(60);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(4,false));
			//tc.setMinWidth(60);

			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(4,false));
			//tc.setMinWidth(60);

			tc = tcm.getColumn(7);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(4,false));
			//tc.setMinWidth(60);

			Dimension dd = th.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);
			table.setSelectionBackground(Color.PINK);
			table.setSelectionForeground(Color.BLUE);
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage(), "Kur Raporlama", JOptionPane.PLAIN_MESSAGE);

		}
	}
}

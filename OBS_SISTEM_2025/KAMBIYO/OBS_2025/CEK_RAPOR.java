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
import OBS_C_2025.KAMBIYO_ACCESS;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings({"serial","static-access"})
public class CEK_RAPOR extends JInternalFrame {
	private static JTable table;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();

	static KAMBIYO_ACCESS ka_Access = new KAMBIYO_ACCESS(oac._IKambiyo , OBS_SIS_2025_ANA_CLASS._IKambiyo_Loger);
	public static JScrollPane scrollPane ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CEK_RAPOR frame = new CEK_RAPOR();
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
	public CEK_RAPOR() {
		setTitle("CEK RAPOR");
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(0, 0, 1000, 600);

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.setGridColor(oac.gridcolor);
		table.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
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
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

		scrollPane.setViewportView(table);

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
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(21);
			for (int i = 0 ; i <= table.getRowCount() -1 ; i ++)
			{
				if (table.getValueAt(i , 10).toString().equals("01.01.1900"))
				{
					table.setValueAt("",i , 10);
				}
				if (table.getValueAt(i , 13).toString().equals("01.01.1900"))
				{
					table.setValueAt("",i , 13);
				}
			}
			table.setSelectionBackground(Color.PINK);
			table.setSelectionForeground(Color.BLUE);
			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());   
		}
	}
}

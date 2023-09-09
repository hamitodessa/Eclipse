package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.KER_RAPOR_BILGI;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import net.proteanit.sql.DbUtils;

@SuppressWarnings({"serial","static-access"})
public class KER_KOD_DEGISTIRME extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static KERESTE_ACCESS  ker_Access = new KERESTE_ACCESS(oac._IKereste , oac._IKereste_Loger);
	private static JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KER_KOD_DEGISTIRME frame = new KER_KOD_DEGISTIRME();
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
	public KER_KOD_DEGISTIRME() {
		setIconifiable(true);
		setTitle("KERESTE KOD DEGISTIRME");
		setResizable(true);
		setClosable(true);
		setBounds(0, 0,1000,400);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerSize(0);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 75));
		panel.setMaximumSize(new Dimension(0, 75));
		
		panel.setLayout(null);
		splitPane.setLeftComponent(panel);
		
		JLabel lblNewLabel = new JLabel("Paket No");
		lblNewLabel.setBounds(29, 39, 48, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Konsimento");
		lblNewLabel_1.setBounds(29, 14, 70, 14);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.setBounds(117, 11, 96, 20);
		panel.add(textField);
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				hisset();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void removeUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				hisset();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void insertUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				hisset();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_1.setBounds(117, 36, 130, 20);
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				hisset();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void removeUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				hisset();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void insertUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				hisset();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
		});
		panel.add(textField_1);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.setGridColor(oac.gridcolor);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

		table.setGridColor(oac.gridcolor);
	
		scrollPane.setViewportView(table);

	}
	private void hisset()
	{
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			System.out.println(textField_1.getText() + "-" + textField.getText());
			
			KER_RAPOR_BILGI ker_BILGI = new KER_RAPOR_BILGI();
			ker_BILGI.setPaket_No1(textField_1.getText());
			ker_BILGI.setKonsimento1(textField.getText());
			rs = ker_Access.urun_detay(ker_BILGI );
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				//lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;

				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(120);

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(0,true));
				tc.setMinWidth(60);

				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(3,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SOLA());
				tc.setCellRenderer(new TARIH());
				tc.setMinWidth(80);

				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(9);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(10);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(11);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(12);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(13);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(14);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(15);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(16);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(17);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(18);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(19);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(20);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(21);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(22);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(23);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(24);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(25);
				tc.setHeaderRenderer(new SOLA());
				tc.setCellRenderer(new TARIH());
				tc.setMinWidth(80);

				tc = tcm.getColumn(26);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(27);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(28);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(29);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(30);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(31);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(32);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(33);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(34);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(2,true));
				tc.setMinWidth(70);

				tc = tcm.getColumn(35);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(36);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(37);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(38);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(39);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(40);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);

				tc = tcm.getColumn(41);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);


				
				Dimension dd = th.getPreferredSize();
				dd.height = 30;
				th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);

				int lastRow = table.convertRowIndexToView(table.getRowCount() - 1);
				table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
				table.setRowSelectionInterval(lastRow, lastRow);

				table.setSelectionBackground(Color.PINK);
				table.setSelectionForeground(Color.BLUE);
				//topla();
				long endTime = System.currentTimeMillis();
				long estimatedTime = endTime - startTime;
				double seconds = (double)estimatedTime/1000; 
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

				String deger;
				String[] parts;
				Font bigFont;
				deger = GLOBAL.setting_oku("KER_RAPORLAMA").toString();
				deger = deger.substring(1, deger.length()-1);
				parts = deger.split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				table.setFont(bigFont);

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kereste Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
}

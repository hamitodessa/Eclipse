package OBS_2025;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.SOLA;
import OBS_C_2025.lOG_BILGI;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class KOD_ACIKLAMA extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static KERESTE_ACCESS  ker_Access = new KERESTE_ACCESS(oac._IKereste , oac._IKereste_Loger);
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KOD_ACIKLAMA frame = new KOD_ACIKLAMA();
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
	public KOD_ACIKLAMA() {
		setIconifiable(true);
		setTitle("KOD ACIKLAMA GIRIS");
		setResizable(true);
		setClosable(true);
		setBounds(0, 0,600,400);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 75));
		panel.setMaximumSize(new Dimension(0, 75));
		panel.setLayout(null);
		splitPane.setLeftComponent(panel);
		
		JLabel lblNewLabel = new JLabel("Kod");
		lblNewLabel.setBounds(10, 28, 46, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField(2);
		textField.setBounds(66, 25, 46, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Aciklama");
		lblNewLabel_1.setBounds(134, 28, 63, 14);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(188, 25, 303, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		table = new JTable();
		table.setGridColor(oac.gridcolor);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		scrollPane.setViewportView(table);
		kaydet();
		hisset();

	}
	public void hisset() 
	{
		try {
		ResultSet	rs = null;
		rs = ker_Access.kod_pln();
		if (!rs.isBeforeFirst() ) {  
		    return;
		}
		table.setModel(DbUtils.resultSetToTableModel(rs));
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc;
		tc = tcm.getColumn(0);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(40);
		
		tc = tcm.getColumn(1);
		tc.setHeaderRenderer(new SOLA());
		tc.setMinWidth(275);


		th.repaint();
		
		
		th.repaint();
		table.setRowSelectionInterval(0, 0);
		table.setRowHeight(22);
		
	    Dimension dd = table.getPreferredSize();
	    dd.height = 30;
	    th.setPreferredSize(dd); 
	    th.repaint();
	    
		table.setSelectionBackground(Color.PINK);
		table.setSelectionForeground(Color.BLUE);
		
		
		//String deger;
		//String[] parts;
		//Font bigFont;
		//deger = GLOBAL.setting_oku("CARI_HSPPLN").toString();
		//deger = deger.substring(1, deger.length()-1);
		//parts = deger.split(",");
		//bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
		//table.setFont(bigFont);
		} catch (Exception ex) {
			 JOptionPane.showMessageDialog(null, ex.getMessage()); 
		}
	}
	public void kaydet() 
	{
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ(textField.getText() + " Acik=" + textField_1.getText());
		lBILGI.seteVRAK("");
		
		
		 try {
			ker_Access.kod_kayit("11", "Cam cift yildiz", lBILGI ,BAGLAN_LOG.kerLogDizin);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.KER_RAPOR_BILGI;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.SOLA_ORTA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

@SuppressWarnings({ "static-access", "serial" })
public class PAKET_ARA extends JDialog {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static KERESTE_ACCESS  ker_Access = new KERESTE_ACCESS(OBS_SIS_2025_ANA_CLASS._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
	private static JTable table;
	private Obs_TextFIeld textField;
	private Obs_TextFIeld textField_1;
	private JFormattedTextField formattedTextField ;
	private JLabel lblNewLabel_2;
	private JSplitPane splitPane ;

	public PAKET_ARA() {
		setModal(true);
		setTitle("KERESTE PAKET ARAMA");
		setResizable(true);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerSize(0);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 75));
		panel.setMaximumSize(new Dimension(0, 75));
		
		panel.setLayout(null);
		splitPane.setLeftComponent(panel);
		
		JLabel lblNewLabel = new JLabel("Paket No");
		lblNewLabel.setBounds(29, 39, 70, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Konsimento");
		lblNewLabel_1.setBounds(29, 14, 70, 14);
		panel.add(lblNewLabel_1);
		
		textField = new Obs_TextFIeld(10);
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.setBounds(117, 11, 96, 20);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
				{	
					hisset();
				}
			}
		});
		
		panel.add(textField);
		
		textField_1 = new Obs_TextFIeld(10);
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_1.setBounds(117, 36, 130, 20);
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
				{	
					hisset();
				}
			}
		});
		panel.add(textField_1);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(287, 36, 150, 20);
		MaskFormatter mask;
		try {
		    mask = new MaskFormatter("AA-###-####-####");
		    mask.install(formattedTextField);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		formattedTextField.setText("00-000-0000-0000");
		formattedTextField.setFont(new Font("Tahoma", Font.BOLD, 12));
		formattedTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
				{	
					hisset();
				}
			}
		});

		panel.add(formattedTextField);
		
		JLabel lblKodu = new JLabel("Kodu");
		lblKodu.setBounds(285, 14, 70, 14);
		panel.add(lblKodu);
		
		JSplitPane splitPane1 = new JSplitPane();
		splitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane1.setDividerSize(0);
		splitPane1.setResizeWeight(1.0);
		splitPane.setRightComponent(splitPane1);
		
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane1.setLeftComponent(scrollPane);
		
		table = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {  
				switch (column) {
				default:
					return false;
				}
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					int row = table.getSelectedRow();
						   oac.stk_kodu = 	table.getModel().getValueAt(row, 3).toString() +"-"+table.getModel().getValueAt(row, 4).toString() ;
						dispose();
				}
			}
		});

		table.getTableHeader().setReorderingAllowed(false);
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]")) 
		{
			table.setGridColor(oac.gridcolor);
		}

		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel1.setMinimumSize(new Dimension(0, 25));
		panel1.setMaximumSize(new Dimension(0, 25));
		splitPane1.setRightComponent(panel1);
		panel1.setLayout(null);
		
		lblNewLabel_2 = new JLabel("0");
		lblNewLabel_2.setForeground(new Color(0, 0, 139));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(100, 5, 118, 14);
		panel1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Secilen Satir :");
		lblNewLabel_3.setBounds(10, 5, 80, 14);
		panel1.add(lblNewLabel_3);

	}
	private void hisset()
	{
		//Runnable runner1 = new Runnable()
		//{ public void run() {
		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			mWAIT();
			KER_RAPOR_BILGI ker_BILGI = new KER_RAPOR_BILGI();
			ker_BILGI.setPaket_No1(textField_1.getText());
			ker_BILGI.setKonsimento1(textField.getText());
			ker_BILGI.setGKodu1(formattedTextField.getText());
			rs = ker_Access.paket_ara(ker_BILGI );
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				//Thread.currentThread().isInterrupted();
				lblNewLabel_2.setText(FORMATLAMA.doub_0(0));
				mDEFAULT();
			} 
			else
			{
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;


			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(120);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
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
			tc.setCellRenderer(new SOLA_ORTA());
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
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(14);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
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
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(18);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);
			
			tc = tcm.getColumn(19);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(20);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(21);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(22);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(23);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);
			th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(22);

			Dimension dd = table.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			th.repaint();
			Thread.currentThread().isInterrupted();
			//table.setSelectionBackground(Color.PINK);
			//table.setSelectionForeground(Color.BLUE);
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
			lblNewLabel_2.setText(FORMATLAMA.doub_0(table.getRowCount()));
			mDEFAULT();
			}
		} catch (Exception ex) {
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,ex.getMessage() );
		}
		//}
		//};
		//Thread q = new Thread(runner1, "Code Executer1");
		//q.start();

	}
	private void mWAIT()
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
		GuiUtil.setWaitCursor(splitPane,true);
		GuiUtil.setWaitCursor(textField,true);
		GuiUtil.setWaitCursor(textField_1,true);
		GuiUtil.setWaitCursor(formattedTextField,true);

	}
	private void mDEFAULT()
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		GuiUtil.setWaitCursor(splitPane,false);
		GuiUtil.setWaitCursor(textField,false);
		GuiUtil.setWaitCursor(textField_1,false);
		GuiUtil.setWaitCursor(formattedTextField,false);

	}
	///********
}

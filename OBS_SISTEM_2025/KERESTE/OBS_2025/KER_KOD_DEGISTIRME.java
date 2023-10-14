package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CheckBoxRenderer;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.KER_RAPOR_BILGI;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.SOLA_ORTA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH;
import OBS_C_2025.lOG_BILGI;
import net.proteanit.sql.DbUtils;
import javax.swing.border.TitledBorder;
import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;

@SuppressWarnings({"serial","static-access"})
public class KER_KOD_DEGISTIRME extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static KERESTE_ACCESS  ker_Access = new KERESTE_ACCESS(OBS_SIS_2025_ANA_CLASS._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
	private static JTable table;
	private JTextField txtKons;
	private JTextField txtPaketNo;
	private JTextField txtYKod;
	private JFormattedTextField txtKodu ;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_4 ;
	private JLabel lblNewLabel_4_1 ;
	private JSplitPane splitPane ;
	private boolean ilk = true ;
	CheckBoxHeader asdBoxHeader = new CheckBoxHeader(new MyItemListener());
	private JTextField txtYKons;
	private JTextField txtEvrak;

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
		setMaximizable(true);
		setIconifiable(true);
		setTitle("KERESTE KOD-KONSIMENTO DEGISTIRME");
		setResizable(true);
		setClosable(true);
		setBounds(0, 0,1000,400);
		
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerSize(0);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 83));
		panel.setMaximumSize(new Dimension(0, 83));
		
		panel.setLayout(null);
		splitPane.setLeftComponent(panel);
		
		JLabel lblNewLabel = new JLabel("Paket No");
		lblNewLabel.setBounds(29, 39, 70, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Konsimento");
		lblNewLabel_1.setBounds(29, 14, 70, 14);
		panel.add(lblNewLabel_1);
		
		txtKons = new JTextField();
		txtKons.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
				{	
					hisset();
				}
			}
		});
		txtKons.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtKons.setDocument(new JTextFieldLimit(15));
		txtKons.setBounds(117, 11, 96, 20);
	
		panel.add(txtKons);
		
		txtPaketNo = new JTextField();
		txtPaketNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
				{	
					hisset();
				}
			}
		});
		txtPaketNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtPaketNo.setDocument(new JTextFieldLimit(10));
		txtPaketNo.setBounds(117, 36, 96, 20);
		panel.add(txtPaketNo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Yeni Kod", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(444, 14, 224, 65);
		panel_1.setLayout(null);
		panel.add(panel_1);
		
		txtYKod = new JTextField();
		txtYKod.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtYKod.setBounds(10, 18, 30, 20);
		txtYKod.setDocument(new JTextFieldLimit(2));
		txtYKod.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
			}
			public void removeUpdate(DocumentEvent e) {
				try {
					mWAIT();
					kod_ADI( txtYKod.getText());
					mDEFAULT();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			public void insertUpdate(DocumentEvent e) {
				try {
					mWAIT();
					kod_ADI( txtYKod.getText());
					mDEFAULT();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		panel_1.add(txtYKod);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Kod Degistirme");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				if (txtYKod.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Kod Bos ...", "Kod Degistirmea", JOptionPane.INFORMATION_MESSAGE);
				}
				else if (satir_kontrol() == 0 ) {
					JOptionPane.showMessageDialog(null, "Secili Satir Bulunmamaktadir...", "Kod Degistirmea", JOptionPane.INFORMATION_MESSAGE);
				}
				else if (lblNewLabel_4.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Kayitli Kod Bulunmamaktadir.....", "Kod Degistirmea", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					mWAIT();	
					kaydet();
					mDEFAULT();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/save.png")));
		btnNewButton.setBounds(193, 18, 23, 23);
		panel_1.add(btnNewButton);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(10, 45, 203, 14);
		panel_1.add(lblNewLabel_4);
		
		txtKodu = new JFormattedTextField();
		txtKodu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
				{	
					hisset();
				}
			}
		});
		txtKodu.setBounds(287, 36, 135, 20);
		MaskFormatter mask;
		try {
		    mask = new MaskFormatter("AA-###-####-####");
		    mask.install(txtKodu);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		txtKodu.setText("00-000-0000-0000");
		txtKodu.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(txtKodu);
		
		JLabel lblKodu = new JLabel("Kodu");
		lblKodu.setBounds(285, 14, 70, 14);
		panel.add(lblKodu);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Yeni Konsimento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1.setBounds(678, 14, 224, 65);
		panel.add(panel_1_1);
		
		txtYKons = new JTextField();
		txtYKons.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtYKons.setBounds(10, 18, 88, 20);
		txtYKons.setDocument(new JTextFieldLimit(15));
		txtYKons.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
			}
			public void removeUpdate(DocumentEvent e) {
				try {
					mWAIT();
					kons_ADI(txtYKons.getText());
					mDEFAULT();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			public void insertUpdate(DocumentEvent e) {
				try {
					mWAIT();
					kons_ADI(txtYKons.getText());
					mDEFAULT();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		panel_1_1.add(txtYKons);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setToolTipText("Konsimento Degistirme");
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				if (txtYKons.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Konsimento Bos ...", "Konsimento Degistirme", JOptionPane.INFORMATION_MESSAGE);
				}
				else if (satir_kontrol() == 0 ) {
					JOptionPane.showMessageDialog(null, "Secili Satir Bulunmamaktadir...", "Konsimento Degistirmea", JOptionPane.INFORMATION_MESSAGE);
				}
				else if (lblNewLabel_4_1.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Kayitli Konsimento Bulunmamaktadir.....", "Konsimento Degistirmea", JOptionPane.INFORMATION_MESSAGE);
				}
				else 
				{
					mWAIT();	
					konskaydet();
					mDEFAULT();
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/save.png")));
		btnNewButton_1.setBounds(193, 18, 23, 23);
		panel_1_1.add(btnNewButton_1);
		
		lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_1.setBounds(10, 45, 203, 14);
		panel_1_1.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Giris Evrak");
		lblNewLabel_1_1.setBounds(29, 62, 78, 14);
		panel.add(lblNewLabel_1_1);
		
		txtEvrak = new JTextField();
		txtEvrak.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtEvrak.setBounds(117, 59, 96, 20);
		txtEvrak.setDocument(new JTextFieldLimit(10));
		txtEvrak.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Enter" )
				{	
					hisset();
				}
			}
		});
		panel.add(txtEvrak);
		
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
				case 0:
					return true;
				default:
					return false;
				}
			}
		};
		table.getTableHeader().setReorderingAllowed(false);
		table.setGridColor(oac.gridcolor);
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
		lblNewLabel_2.setBounds(155, 5, 63, 14);
		panel1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Secilen Satir :");
		lblNewLabel_3.setBounds(10, 5, 140, 14);
		panel1.add(lblNewLabel_3);
		
		KER_RAPOR_BILGI ker_BILGI = new KER_RAPOR_BILGI();
		ker_BILGI.setPaket_No1(txtPaketNo.getText());
		ker_BILGI.setKonsimento1(txtKons.getText());
		ker_BILGI.setGKodu1(txtKodu.getText());
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
			ker_BILGI.setPaket_No1(txtPaketNo.getText());
			ker_BILGI.setKonsimento1(txtKons.getText());
			ker_BILGI.setGKodu1(txtKodu.getText());
			ker_BILGI.setEvrak_No1(txtEvrak.getText());
			rs = ker_Access.urun_detay(ker_BILGI );
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				lblNewLabel_2.setText(FORMATLAMA.doub_0(0));
				if(! ilk)
				{
					JTableHeader th = table.getTableHeader();
					TableColumnModel tcm = th.getColumnModel();
					TableColumn tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
					th.repaint();
					table.repaint();
				}
				ilk= true ;
				mDEFAULT();
			} 
			else
			{
			ilk = false ;
			table.setModel(DbUtils.resultSetToTableModel(rs));
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc;

			tc = tcm.getColumn(0);
			JCheckBox checkBox = new JCheckBox();
			checkBox.setHorizontalAlignment(JCheckBox.CENTER);
			DefaultCellEditor dce = new DefaultCellEditor( checkBox );
			tc.setCellEditor(dce);
			tc.setCellRenderer(new CheckBoxRenderer());
			tc.setMinWidth(50);

			tc = tcm.getColumn(1);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(120);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(6);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(0,true));
			tc.setMinWidth(60);

			tc = tcm.getColumn(7);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(3,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(8);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new TARIH());
			tc.setMinWidth(80);

			tc = tcm.getColumn(9);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(10);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(11);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(12);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(13);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(14);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(15);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(16);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(17);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

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

			tc = tcm.getColumn(24);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(25);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(26);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(27);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(80);

			tc = tcm.getColumn(28);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(29);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(30);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(31);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(32);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(33);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(34);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(35);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(36);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(37);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(38);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(39);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(40);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(41);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(42);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(43);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new SOLA_ORTA());
			tc.setMinWidth(90);

			table.removeColumn(table.getColumnModel().getColumn(44));
			th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(22);

			Dimension dd = table.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			//
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(asdBoxHeader);
			
			th.repaint();
			table.getModel().addTableModelListener(	(TableModelListener) new TableModelListener() 
			{
				public void tableChanged(TableModelEvent e) 
				{
					TableModel model = (TableModel)e.getSource();
					if (model.getRowCount() > 0) {
						secilen_satir();
					}
				}
			});
			Thread.currentThread().isInterrupted();
			table.setSelectionBackground(Color.PINK);
			table.setSelectionForeground(Color.BLUE);
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
			mDEFAULT();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kereste Raporlama", JOptionPane.ERROR_MESSAGE);
		}
		//}
		//};
		//Thread q = new Thread(runner1, "Code Executer1");
		//q.start();

	}
	private void kaydet()
	{
		try {
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ("Kod Degisim ,  Paket_No :"  );
			lBILGI.seteVRAK("");
			DefaultTableModel modell = (DefaultTableModel)table.getModel();
			for ( int i = 0; i <=  modell.getRowCount() - 1;i++)
			{
				if (  (boolean) modell.getValueAt(i,43) )
				{
					ker_Access.ker_kod_degis(modell.getValueAt(i, 3).toString(),
							modell.getValueAt(i, 4).toString(),
							txtYKod.getText(),
							(int) modell.getValueAt(i, 0) ,
							lBILGI,BAGLAN_LOG.kerLogDizin);
				}
			}
			GRID_TEMIZLE.grid_temizle(table);
			
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
			th.repaint();
			table.repaint();
			txtKons.setText("");
			txtPaketNo.setText("");
			txtYKod.setText("");
			txtKodu.setText("00-000-0000-0000");

		} catch (Exception ex) {
			mDEFAULT();
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kereste Kod Degisimi", JOptionPane.ERROR_MESSAGE);
		}

	}
	private void konskaydet()
	{
		try {
			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ("Konsimento Degisim ,  Konsimento_No :"  );
			lBILGI.seteVRAK("");
			DefaultTableModel modell = (DefaultTableModel)table.getModel();
			for ( int i = 0; i <=  modell.getRowCount() - 1;i++)
			{
				if (  (boolean) modell.getValueAt(i,43) )
				{
					ker_Access.ker_kons_degis(modell.getValueAt(i, 5).toString(),txtYKons.getText() ,lBILGI,BAGLAN_LOG.kerLogDizin);
				}
			}
			GRID_TEMIZLE.grid_temizle(table);
			
			JTableHeader th = table.getTableHeader();
			TableColumnModel tcm = th.getColumnModel();
			TableColumn tc = tcm.getColumn(0);
			tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
			th.repaint();
			table.repaint();
			txtKons.setText("");
			txtPaketNo.setText("");
			txtYKod.setText("");
			txtYKons.setText("");
			txtKodu.setText("00-000-0000-0000");

		} catch (Exception ex) {
			mDEFAULT();
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kereste Kod Degisimi", JOptionPane.ERROR_MESSAGE);
		}

	}
	private void secilen_satir()
	{
		lblNewLabel_2.setText(FORMATLAMA.doub_0(satir_kontrol()));
	}

	private int satir_kontrol()
	{
		int satir = 0 ;
		DefaultTableModel modell = (DefaultTableModel)table.getModel();
		for ( int i = 0; i <=  modell.getRowCount() - 1;i++)
		{
			if ( modell.getValueAt(i,0) != null) 
			{
				if( BAGLAN.kerDizin.hAN_SQL.equals("MS SQL") )
				{
					if (  (boolean) modell.getValueAt(i,0) )
						{
							satir += 1 ;
						}
				}
				else {
					if ( ! modell.getValueAt(i,0).toString().equals("0") )
					{
						satir += 1 ;
					}
				}
			
			};
		}
		return satir ;
	}
	private void kod_ADI(String kod) throws ClassNotFoundException, SQLException 
	{
		lblNewLabel_4.setText(ker_Access.kod_adi(kod));
	}
	private void kons_ADI(String kons) throws ClassNotFoundException, SQLException 
	{
		lblNewLabel_4_1.setText(ker_Access.kons_adi(kons));
	}
	private void mWAIT()
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
		GuiUtil.setWaitCursor(splitPane,true);
		GuiUtil.setWaitCursor(txtKons,true);
		GuiUtil.setWaitCursor(txtPaketNo,true);
		GuiUtil.setWaitCursor(txtEvrak,true);
		GuiUtil.setWaitCursor(txtKodu,true);

	}
	private void mDEFAULT()
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		GuiUtil.setWaitCursor(splitPane,false);
		GuiUtil.setWaitCursor(txtKons,false);
		GuiUtil.setWaitCursor(txtPaketNo,false);
		GuiUtil.setWaitCursor(txtEvrak,false);
		GuiUtil.setWaitCursor(txtKodu,false);

	}
	///********
	class MyItemListener implements ItemListener
	{
		@SuppressWarnings("removal")
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			Object source = e.getSource();
			if (source instanceof AbstractButton == false) return;
			boolean checked = e.getStateChange() == ItemEvent.SELECTED;
			for(int x = 0, y = table.getRowCount(); x < y; x++)
			{
				table.setValueAt(new Boolean(checked),x,0);
			}
		}
	}
	class CheckBoxHeader extends JCheckBox   implements TableCellRenderer, MouseListener 
	{
		protected CheckBoxHeader rendererComponent;
		protected int column;
		protected boolean mousePressed = false;
		public CheckBoxHeader(ItemListener itemListener) {
			rendererComponent = this;
			rendererComponent.addItemListener(itemListener);
		}
		public Component getTableCellRendererComponent(
				JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			if (table != null) {
				JTableHeader header = table.getTableHeader();
				if (header != null) {
					rendererComponent.setForeground(header.getForeground());
					rendererComponent.setBackground(header.getBackground());
					rendererComponent.setFont(header.getFont());
					header.addMouseListener(rendererComponent);
				}
			}
			setColumn(column);
			setHorizontalAlignment(JLabel.CENTER);
			setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			return rendererComponent;
		}
		protected void setColumn(int column) {
			this.column = column;
		}
		public int getColumn() {
			return column;
		}
		protected void handleClickEvent(MouseEvent e) {
			if (mousePressed) {
				mousePressed=false;
				JTableHeader header = (JTableHeader)(e.getSource());
				JTable tableView = header.getTable();
				TableColumnModel columnModel = tableView.getColumnModel();
				int viewColumn = columnModel.getColumnIndexAtX(e.getX());
				int column = tableView.convertColumnIndexToModel(viewColumn);
				if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {
					doClick();
				}
			}
		}
		public void mouseClicked(MouseEvent e) {
			handleClickEvent(e);
			((JTableHeader)e.getSource()).repaint();
		}
		public void mousePressed(MouseEvent e) {
			mousePressed = true;
		}
		public void mouseReleased(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) 
		{
		}
		
	}
}

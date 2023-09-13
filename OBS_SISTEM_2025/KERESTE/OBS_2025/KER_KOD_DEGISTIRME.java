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

@SuppressWarnings({"serial","static-access"})
public class KER_KOD_DEGISTIRME extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static KERESTE_ACCESS  ker_Access = new KERESTE_ACCESS(oac._IKereste , oac._IKereste_Loger);
	private static JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JFormattedTextField formattedTextField ;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_4 ;
	private JSplitPane splitPane ;
	CheckBoxHeader asdBoxHeader = new CheckBoxHeader(new MyItemListener());

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
		setTitle("KERESTE KOD DEGISTIRME");
		setResizable(true);
		setClosable(true);
		setBounds(0, 0,1000,400);
		
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
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.setDocument(new JTextFieldLimit(10));
		textField.setBounds(117, 11, 96, 20);
	
		panel.add(textField);
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				//hisset();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void removeUpdate(DocumentEvent e) {
				hisset();
			}
			public void insertUpdate(DocumentEvent e) {
				hisset();
			}
		});
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_1.setDocument(new JTextFieldLimit(10));
		textField_1.setBounds(117, 36, 130, 20);
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				//hisset();
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void removeUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				GuiUtil.setWaitCursor(textField_1,true);
				hisset();
				GuiUtil.setWaitCursor(textField_1,false);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void insertUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				GuiUtil.setWaitCursor(textField_1,true);
				hisset();
				GuiUtil.setWaitCursor(textField_1,false);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
		});
		panel.add(textField_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Yeni Kod", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(550, 11, 393, 53);
		panel_1.setLayout(null);
		panel.add(panel_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_2.setBounds(59, 15, 44, 20);
		textField_2.setDocument(new JTextFieldLimit(2));
		textField_2.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void removeUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				try {
					kod_ADI( textField_2.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void insertUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				try {
					kod_ADI( textField_2.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
		});

		panel_1.add(textField_2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				if (textField_2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Kod Bos ...", "Kod Degistirmea", JOptionPane.INFORMATION_MESSAGE);
				}
				else if (satir_kontrol() == 0 ) {
					JOptionPane.showMessageDialog(null, "Secili Satir Bulunmamaktadir...", "Kod Degistirmea", JOptionPane.INFORMATION_MESSAGE);
				}
				else if (lblNewLabel_4.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Kayitli Kod Bulunmamaktadir.....", "Kod Degistirmea", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
					kaydet();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(OBS_MAIN.class.getResource("/ICONLAR/save.png")));
		btnNewButton.setBounds(363, 15, 20, 23);
		panel_1.add(btnNewButton);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(113, 19, 240, 14);
		panel_1.add(lblNewLabel_4);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(287, 36, 135, 20);
		MaskFormatter mask;
		try {
		    mask = new MaskFormatter("AA-###-####-####");
		    mask.install(formattedTextField);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		formattedTextField.setText("00-000-0000-0000");
		formattedTextField.setFont(new Font("Tahoma", Font.BOLD, 12));
		formattedTextField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
			}
			public void removeUpdate(DocumentEvent e) {
			}
			public void insertUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				GuiUtil.setWaitCursor(formattedTextField,true);
				hisset();
				GuiUtil.setWaitCursor(formattedTextField,false);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
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
		Runnable runner1 = new Runnable()
		{ public void run() {

		long startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			GuiUtil.setWaitCursor(splitPane,true);
			GuiUtil.setWaitCursor(textField,true);
			GuiUtil.setWaitCursor(textField_1,true);
			GuiUtil.setWaitCursor(formattedTextField,true);
			KER_RAPOR_BILGI ker_BILGI = new KER_RAPOR_BILGI();
			ker_BILGI.setPaket_No1(textField_1.getText());
			ker_BILGI.setKonsimento1(textField.getText());
			ker_BILGI.setGKodu1(formattedTextField.getText());
			
			rs = ker_Access.urun_detay(ker_BILGI );
			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
				Thread.currentThread().isInterrupted();
				lblNewLabel_2.setText(FORMATLAMA.doub_0(0));
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
				th.repaint();
				table.repaint();
				GuiUtil.setWaitCursor(splitPane,false);
				GuiUtil.setWaitCursor(textField,false);
				GuiUtil.setWaitCursor(textField_1,false);
				GuiUtil.setWaitCursor(formattedTextField,false);
			} 
			else
			{
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
			tc.setMinWidth(90);

			tc = tcm.getColumn(2);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(3);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(120);

			tc = tcm.getColumn(4);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(5);
			tc.setHeaderRenderer(new SOLA());
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
			tc.setMinWidth(90);

			tc = tcm.getColumn(15);
			tc.setHeaderRenderer(new SOLA());
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
			tc.setMinWidth(90);

			tc = tcm.getColumn(26);
			tc.setHeaderRenderer(new SOLA());
			tc.setCellRenderer(new TARIH());
			tc.setMinWidth(80);

			tc = tcm.getColumn(27);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(28);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(29);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(30);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(31);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(32);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(33);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);

			tc = tcm.getColumn(34);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

			tc = tcm.getColumn(35);
			tc.setHeaderRenderer(new SAGA());
			tc.setCellRenderer(new TABLO_RENDERER(2,true));
			tc.setMinWidth(70);

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

			tc = tcm.getColumn(42);
			tc.setHeaderRenderer(new SOLA());
			tc.setMinWidth(90);

			table.removeColumn(table.getColumnModel().getColumn(43));
			th.repaint();
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(22);

			Dimension dd = table.getPreferredSize();
			dd.height = 30;
			th.setPreferredSize(dd); 
			//
			tc = tcm.getColumn(0);
			tc.setHeaderRenderer(asdBoxHeader);
			
			//
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
			GuiUtil.setWaitCursor(splitPane,false);
			GuiUtil.setWaitCursor(textField,false);
			GuiUtil.setWaitCursor(textField_1,false);
			GuiUtil.setWaitCursor(formattedTextField,false);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kereste Raporlama", JOptionPane.ERROR_MESSAGE);
		}
		}
		};
		Thread q = new Thread(runner1, "Code Executer1");
		q.start();

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
							textField_2.getText(),
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
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			formattedTextField.setText("00-000-0000-0000");

		} catch (Exception ex) {
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
				if (  (boolean) modell.getValueAt(i,0) )
				{
					satir += 1 ;
				}
			};
		}
		return satir ;
	}
	private void kod_ADI(String kod) throws ClassNotFoundException, SQLException 
	{
		lblNewLabel_4.setText(ker_Access.kod_adi(kod));
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
			//setSelected(true);
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

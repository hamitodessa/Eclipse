package obs.obs_fihrist.other;

import net.proteanit.sql.DbUtils;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;


import OBS_C_2025.BAGLAN;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.SOLA_DUZ_RENK;
import OBS_C_2025.ScrollPaneWin11;
import fih.FIHRIST_ACCESS;
import obs.classes.aNA_Class;
import obs.obs_fihrist.OBS_FIHRIST;
import raven.toast.Notifications;
@SuppressWarnings({"static-access","serial"})
public class FormFihrist  extends javax.swing.JPanel {

	
	BAGLAN bAGLAN = new BAGLAN();
	aNA_Class oac = new aNA_Class();
	private static FIHRIST_ACCESS  fih_Access = new FIHRIST_ACCESS(obs.classes.aNA_Class._IFihrist);

	
	private JLabel lblSatir ;
	private JLabel lblbilgi;
	private JTable table;
	private JTextField textField;
	private static JTextField txtcd;
	private static JTextField txtAdi;
	private static JTextField txtT1;
	private static JTextField txtT2;
	private static JTextField txtT3;
	private static JTextField txtT4;
	private static JTextField txtFax;
	private static JTextField txtMail;
	private static JTextField txtNot;
	private static JTextField txtNot2;
	private JCheckBox chckbxKriter;
	public FormFihrist() {
		
		setLayout(new BorderLayout(0, 0));
		add(new Title_Bar(), BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);

		ScrollPaneWin11 scrollPane_1 = new ScrollPaneWin11();
		scrollPane_1.setMinimumSize(new Dimension(0, 170));
		scrollPane_1.setMaximumSize(new Dimension(0, 170));
		splitPane.setLeftComponent(scrollPane_1);

		JPanel panel_2 = new JPanel();
		scrollPane_1.setViewportView(panel_2);
		panel_2.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 33, 855, 5);
		panel_2.add(separator);

		JLabel lblNewLabel = new JLabel("Adi");
		lblNewLabel.setBounds(10, 11, 48, 14);
		panel_2.add(lblNewLabel);

		textField = new JTextField();
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
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.getKeyText(e.getKeyCode()) == "Down" )
				{	
					table.requestFocus();
					table.setRowSelectionInterval(0, 0);
				}
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

		textField.setBounds(68, 7, 372, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		chckbxKriter = new JCheckBox("(Secili) Isim Sutununda / Butun Tabloda");
		chckbxKriter.setSelected(true);
		chckbxKriter.setBounds(461, 5, 273, 23);
		panel_2.add(chckbxKriter);

		JLabel lblNewLabel_1 = new JLabel("Adi");
		lblNewLabel_1.setBounds(10, 46, 48, 14);
		panel_2.add(lblNewLabel_1);

		txtAdi = new JTextField();
		txtAdi.setDocument(new JTextFieldLimit(50));
		txtAdi.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtAdi.setBounds(68, 43, 372, 20);
		panel_2.add(txtAdi);
		txtAdi.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tel_1");
		lblNewLabel_2.setBounds(10, 75, 48, 14);
		panel_2.add(lblNewLabel_2);

		txtT1 = new JTextField();
		txtT1.setDocument(new JTextFieldLimit(25));
		txtT1.setBounds(68, 72, 148, 20);
		panel_2.add(txtT1);
		txtT1.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Tel_2");
		lblNewLabel_2_1.setBounds(234, 72, 48, 14);
		panel_2.add(lblNewLabel_2_1);

		txtT2 = new JTextField();
		txtT2.setDocument(new JTextFieldLimit(25));
		txtT2.setColumns(10);
		txtT2.setBounds(292, 69, 148, 20);
		panel_2.add(txtT2);

		JLabel lblNewLabel_2_2 = new JLabel("Tel_3");
		lblNewLabel_2_2.setBounds(450, 72, 48, 14);
		panel_2.add(lblNewLabel_2_2);

		txtT3 = new JTextField();
		txtT3.setDocument(new JTextFieldLimit(25));
		txtT3.setColumns(10);
		txtT3.setBounds(502, 69, 148, 20);
		panel_2.add(txtT3);

		JLabel lblNewLabel_2_3 = new JLabel("Tel_4");
		lblNewLabel_2_3.setBounds(660, 72, 48, 14);
		panel_2.add(lblNewLabel_2_3);

		txtT4 = new JTextField();
		txtT4.setDocument(new JTextFieldLimit(25));
		txtT4.setColumns(10);
		txtT4.setBounds(718, 69, 148, 20);
		panel_2.add(txtT4);

		JLabel lblNewLabel_2_4 = new JLabel("Fax");
		lblNewLabel_2_4.setBounds(10, 101, 48, 14);
		panel_2.add(lblNewLabel_2_4);

		txtFax = new JTextField();
		txtFax.setDocument(new JTextFieldLimit(25));
		txtFax.setColumns(10);
		txtFax.setBounds(68, 98, 148, 20);
		panel_2.add(txtFax);

		JLabel lblNewLabel_2_5 = new JLabel("Mail");
		lblNewLabel_2_5.setBounds(234, 100, 48, 14);
		panel_2.add(lblNewLabel_2_5);

		txtMail = new JTextField();
		txtMail.setDocument(new JTextFieldLimit(50));
		txtMail.setColumns(10);
		txtMail.setBounds(292, 97, 360, 20);
		panel_2.add(txtMail);

		JLabel lblNewLabel_2_6 = new JLabel("Not");
		lblNewLabel_2_6.setBounds(10, 129, 48, 14);
		panel_2.add(lblNewLabel_2_6);

		txtNot = new JTextField();
		txtNot.setDocument(new JTextFieldLimit(50));
		txtNot.setColumns(10);
		txtNot.setBounds(68, 126, 360, 20);
		panel_2.add(txtNot);

		JLabel lblNewLabel_2_6_1 = new JLabel("Not_2");
		lblNewLabel_2_6_1.setBounds(450, 129, 48, 14);
		panel_2.add(lblNewLabel_2_6_1);

		txtNot2 = new JTextField();
		txtNot2.setDocument(new JTextFieldLimit(50));
		txtNot2.setColumns(10);
		txtNot2.setBounds(508, 126, 360, 20);
		panel_2.add(txtNot2);

		txtcd = new JTextField();
		txtcd.setVisible(false);
		txtcd.setBounds(45, 148, 15, 15);
		panel_2.add(txtcd);
		txtcd.setColumns(10);
		///
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		toolBar_1.setBounds(772, 5, 138, 27);
		panel_2.add(toolBar_1);

		JButton btnKayitf = new JButton("");
		btnKayitf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtAdi.getText().toString().equals(""))
						return;
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					fih_kaydet();
					doldur();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception ex) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage().toString() );
				}
			}
		});
		btnKayitf.setToolTipText("Kaydet");
		btnKayitf.setIcon(new ImageIcon(OBS_FIHRIST.class.getResource("/obs/icon/png/save.png")));
		toolBar_1.add(btnKayitf);

		JButton btnSilf = new JButton("");
		btnSilf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtcd.getText().toString().equals("")) return ;
				int g = JOptionPane.showOptionDialog(null,txtAdi.getText() + System.lineSeparator()  + System.lineSeparator() + "Kayit Silinecek..........?" ,
						"Fihrist ", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, 	new String[] {"Yes", "No"}, "No");
				if(g ==  1) {
					return;
				}
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					if(! txtcd.getText().toString().equals(""))
						OBS_FIHRIST.fih_Access .reh_sil(Integer.parseInt(txtcd.getText().toString()));
					doldur();
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (Exception ex)
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage().toString() );
				}
			}
		});
		btnSilf.setToolTipText("Sil");
		btnSilf.setIcon(new ImageIcon(OBS_FIHRIST.class.getResource("/obs/icon/png/sil.png")));
		toolBar_1.add(btnSilf);

		JButton btnYenif = new JButton("");
		btnYenif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fih_kutu_temizle();
					txtAdi.requestFocus();
				} catch (Exception e1) {
					OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.ERROR,  e1.getMessage().toString() );
				}
			}
		});
		btnYenif.setToolTipText("Yeni");
		btnYenif.setIcon(new ImageIcon(OBS_FIHRIST.class.getResource("/obs/icon/png/yeni.png")));
		toolBar_1.add(btnYenif);
		///////////////////////////////////////////////////////////////////////////////////////////////
		JSplitPane splitPanealt = new JSplitPane();
		splitPanealt.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPanealt.setDividerSize(0);
		splitPanealt.setResizeWeight(1.0);
		splitPane.setRightComponent(splitPanealt);

		JPanel panel_4 = new JPanel();
		panel_4.setMinimumSize(new Dimension(0, 30));
		panel_4.setMaximumSize(new Dimension(0, 30));

		splitPanealt.setRightComponent(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel_2_7 = new JLabel("Satir Sayisi :");
		lblNewLabel_2_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_7.setBounds(10, 7, 85, 14);
		panel_4.add(lblNewLabel_2_7);

		lblSatir = new JLabel("0");

		lblSatir.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSatir.setBounds(100, 7, 51, 14);
		panel_4.add(lblSatir);

		lblbilgi = new JLabel("...");
		lblbilgi.setBounds(174, 7, 300, 14);

		lblbilgi.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_4.add(lblbilgi);

		/////////// fihrist tablo
		ScrollPaneWin11 scrollPane_2 = new ScrollPaneWin11();
		splitPanealt.setLeftComponent(scrollPane_2);

		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.setFont(new Font("Calibri", Font.PLAIN, 13));
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					if (table.getRowCount() == 0) return ;
					if (table.getSelectedRow()  < 0) return;
					try {
						fih_kutu_temizle();
						 if( table.getRowSorter() == null)
						 {
							 fih_doldur_kutu(table,table.getSelectedRow());
						 }
						 else {
							 fih_doldur_kutu(table,table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()));
						}
					} catch (Exception e1) {
						OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.ERROR, e1.getMessage());
					}
				}
			}
		});		
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(table);
		//************SURUCU KONTROL**************************
		//System.out.println("+*+*+"+oac._IFihrist + "==="+ oac.FIHRIST_CONN + "===" + oac.FIH_DOS_VAR);
		if(oac._IFihrist != null && oac.FIHRIST_CONN != false && oac.FIH_DOS_VAR != false)
		{
			basla();
		}
		else {
			//OBS_FIHRIST.kontrol();
		}
	}
	public  void basla()
	{
			try {
				fih_Access = new FIHRIST_ACCESS(oac._IFihrist );
				fih_Access.baglan();
				String qwe = "" ;
				qwe = BAGLAN.fihDizin.yER.equals("S") ?  BAGLAN.fihDizin.sERVER : "Lokal" ;
				lblbilgi.setText (BAGLAN.fihDizin.kOD + "  /  " + qwe.toString().trim()  + " / "+ BAGLAN.fihDizin.hAN_SQL );
				doldur();
			} catch (Exception e) {
				OBS_FIHRIST.mesaj_goster(5000,Notifications.Type.WARNING, e.getMessage());
			}
	}

	public void doldur()
	{
		try {
			ResultSet	rs = null;
			rs = fih_Access.reh_doldur();
			GRID_TEMIZLE.grid_temizle(table);
			fih_kutu_temizle();
			if (!rs.isBeforeFirst() ) {  

			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				table.removeColumn(table.getColumnModel().getColumn(9));
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA_DUZ_RENK());
				tc.setMinWidth(250);
				tc.setMaxWidth(250);

				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA_DUZ_RENK());
				tc.setMinWidth(125);
				tc.setMaxWidth(125);

				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA_DUZ_RENK());
				tc.setMinWidth(125);
				tc.setMaxWidth(125);

				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SOLA_DUZ_RENK());
				tc.setMinWidth(125);
				tc.setMaxWidth(125);

				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SOLA_DUZ_RENK());
				tc.setMinWidth(125);
				tc.setMaxWidth(125);

				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SOLA_DUZ_RENK());
				tc.setMinWidth(150);
				tc.setMaxWidth(150);

				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SOLA_DUZ_RENK());
				tc.setMinWidth(200);
				tc.setMaxWidth(200);

				tc = tcm.getColumn(7);
				tc.setHeaderRenderer(new SOLA_DUZ_RENK());
				tc.setMinWidth(200);
				tc.setMaxWidth(200);

				tc = tcm.getColumn(8);
				tc.setHeaderRenderer(new SOLA_DUZ_RENK());
				tc.setMinWidth(200);
				tc.setMaxWidth(200);

				Dimension dd = th.getPreferredSize();
				dd.height = 30;
				th.setPreferredSize(dd); 
				th.repaint();
				table.setRowHeight(21);
				lblSatir.setText( String.format("%,d %n" ,  table.getRowCount()));
				fih_doldur_kutu(table,0);
			}
		} catch (Exception ex) 
		{
			OBS_FIHRIST.mesaj_goster(7000,Notifications.Type.ERROR,ex.getMessage() );
		}
	}
	private void fih_kaydet() throws NumberFormatException, ClassNotFoundException, SQLException
	{
		if(! txtcd.getText().toString().equals(""))
			 fih_Access.reh_sil(Integer.parseInt(txtcd.getText().toString()));
		 fih_Access.reh_kayit(txtAdi.getText(), txtT1.getText(), txtT2.getText(),txtT3.getText(),txtT4.getText(), txtFax.getText(),  txtNot.getText(),  txtNot2.getText(),txtMail.getText());
	}
	private static void fih_kutu_temizle() 
	{
		txtAdi.setText("");
		txtT1.setText("");
		txtT2.setText("");
		txtT3.setText("");
		txtT4.setText("");
		txtFax.setText("");
		txtMail.setText("");
		txtNot.setText("");
		txtNot2.setText("");
		txtcd.setText("");
	}
	private static  void fih_doldur_kutu( JTable grd,int satir) throws ClassNotFoundException, SQLException 
	{
		if (grd.getRowCount()== 0 ) {  
			fih_kutu_temizle();
			return;
		} 
		txtAdi.setText(grd.getModel().getValueAt(satir, 0).toString());
		txtT1.setText(grd.getModel().getValueAt(satir, 1).toString());
		txtT2.setText(grd.getModel().getValueAt(satir, 2).toString());
		txtT3.setText(grd.getModel().getValueAt(satir, 3).toString());
		txtT4.setText(grd.getModel().getValueAt(satir, 4).toString());
		txtFax.setText(grd.getModel().getValueAt(satir, 5).toString());
		txtNot.setText(grd.getModel().getValueAt(satir, 6).toString());
		txtNot2.setText(grd.getModel().getValueAt(satir, 7).toString());
		txtMail.setText(grd.getModel().getValueAt(satir, 8).toString());
		txtcd.setText(grd.getModel().getValueAt(satir, 9).toString());
	}
	public void arama()  
	{
		if (textField.getText().equals(""))
		{
			table.setRowSorter(null);
		}
		else
		{
			TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel())); 
			sorter.setStringConverter(new TableStringConverter() {
				@Override
				public String toString(TableModel model, int row, int column) {
					return model.getValueAt(row, column).toString().toLowerCase();
				}
			});
			if(chckbxKriter.isSelected())
			{
				sorter.setRowFilter(RowFilter.regexFilter("(?iu)" + textField.getText().toLowerCase(),0));
			}
			else {
				sorter.setRowFilter(RowFilter.regexFilter("(?iu)" + textField.getText().toLowerCase()));
			}
			table.setRowSorter(sorter);
			table.revalidate();
			table.repaint();
			if (table.getRowCount()== 0 ) {  
				fih_kutu_temizle();
				return;
			} 
			else {
				try {
					fih_doldur_kutu(table,table.getRowSorter().convertRowIndexToModel(0));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

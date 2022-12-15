package OBS_2025;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.commons.lang.StringUtils;

import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.DoubleEditor;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.NextCellActioin;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.TABLO_RENDERER;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;

import java.awt.Font;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RECETE extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(oac._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	
	
	private ArrayList<String> listkodlar = null ;
	private static JComboBox<String> cmbanagrup ;
	private static JComboBox<String> cmbaltgrup ;
	private static JComboBox<String> comboBox_2 ;

	public static JTextField textField;
	private static JTextField textField_1;
	private static JTable table;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel label_5 ;
	private JLabel label_6 ;
	private JLabel lblNewLabel_6 ;
	private JLabel lblNewLabel_7 ;
	private static JTextArea textArea ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RECETE frame = new RECETE();
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
	public RECETE() {
		setTitle("RECETE");
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,625, 548);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "Uretim Recetesi", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));

		panel_1.setBounds(5, 11, 604, 117);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kodu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 28, 46, 14);
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setDocument(new JTextFieldLimit(10));
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					try {
						getContentPane().setCursor(oac.WAIT_CURSOR);
					int sno = 0 ;
					
		             sno  = f_Access.recete_no_al() ;
		    		
					int kj = 0 ;
					kj = 10 - Integer.toString(sno).length() ;
					String str_ = StringUtils.repeat("0", kj)   + Integer.toString(sno);
					textField.setText(str_.equals("0000000000") ? "0000000001":str_);
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
					}
					catch (Exception ex)
					{
						getContentPane().setCursor(oac.DEFAULT_CURSOR);
						 JOptionPane.showMessageDialog(null,  "Recete Numaralarinda onceden harf ve rakkam kullanildigindan otomatik numara verilemez...."); 	
					}
				}
			}
		});
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				kontrol();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				kontrol();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				kontrol();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
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
		        // TextField is added to its parent => request focus in Event Dispatch Thread
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	textField.requestFocusInWindow();
		            }
		        });
		    }
		});
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(66, 25, 115, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					getContentPane().setCursor(oac.WAIT_CURSOR);
		            textField.setText("");
		            temizle();
		        	
		        		  textField.setText(f_Access.recete_son_bordro_no_al());
	        		
		          
		            if ( textField.getText().equals("0") )
		            		{
		            	 textField.setText("");
		            	JOptionPane.showMessageDialog(null,  "Hic Kayit Yok...", "Recete Okuma", JOptionPane.PLAIN_MESSAGE);
		            	textField.requestFocus();
		            		}
		        	getContentPane().setCursor(oac.DEFAULT_CURSOR);
				 }
		        catch (Exception ex)
				 {
		        	getContentPane().setCursor(oac.DEFAULT_CURSOR);
		        	JOptionPane.showMessageDialog(null,  ex.getMessage(), "Recete Okuma", JOptionPane.ERROR_MESSAGE);
				 }
			}
		});
		btnNewButton.setIcon(new ImageIcon(RECETE.class.getResource("/ICONLAR/icons8-view-16.png")));
		btnNewButton.setBounds(185, 24, 25, 23);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Aciklama");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 57, 57, 14);
		panel_1.add(lblNewLabel_1);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(66, 56, 265, 45);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		textArea.setBorder(BorderFactory.createCompoundBorder(border,
	    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		textArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		textArea.setDocument(new JTextFieldLimit(50));
		panel_1.add(textArea);
		
		JLabel lblNewLabel_2 = new JLabel("Ana Grup");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(341, 21, 59, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Alt Grup");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(341, 50, 61, 14);
		panel_1.add(lblNewLabel_3);
		
		cmbanagrup = new JComboBox<String>();
		cmbanagrup.setForeground(new Color(0, 0, 128));
		cmbanagrup.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbanagrup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alt_grup_doldur();
			}
		});
		cmbanagrup.setBounds(412, 17, 142, 22);
		panel_1.add(cmbanagrup);
		
		cmbaltgrup = new JComboBox<String>();
		cmbaltgrup.setForeground(new Color(0, 0, 128));
		cmbaltgrup.setFont(new Font("Tahoma", Font.BOLD, 12));
		cmbaltgrup.setEnabled(false);
		cmbaltgrup.setBounds(412, 46, 142, 22);
		panel_1.add(cmbaltgrup);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setForeground(new Color(0, 0, 128));
		comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"Aktiv", "Pasiv"}));
		comboBox_2.setBounds(412, 79, 142, 22);
		panel_1.add(comboBox_2);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ana_grup_doldur();
			}
		});
		button.setBounds(564, 17, 25, 23);
		button.setIcon(new ImageIcon(RECETE.class.getResource("/ICONLAR/icons8-repeat-16.png")));
		panel_1.add(button);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 191, 255), new Color(160, 160, 160)), "Uretilecek (Yeni) Urun ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel_2.setBounds(5, 401, 604, 110);
		panel.add(panel_2);
		
		JLabel label = new JLabel("Kodu");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(10, 28, 46, 14);
		panel_2.add(label);
		
		textField_1 =  new JTextField();
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					URUN_ARAMA arm ;
					getContentPane().setCursor(oac.WAIT_CURSOR);
						arm = new URUN_ARAMA();
						arm.show();
					getContentPane().setCursor(oac.DEFAULT_CURSOR);
					textField_1.setText( oac.stk_kodu);
				}
			}
		});
		textField_1.setDocument(new JTextFieldLimit(12));
		textField_1.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				isimoku();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				isimoku();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				isimoku();
			}
			});
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_1.setColumns(10);
		textField_1.setBounds(66, 25, 115, 20);
		panel_2.add(textField_1);
		
		JButton button_2 = new JButton("");
		button_2.setToolTipText("Recete Bul");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = null;
				try {
					getContentPane().setCursor(oac.WAIT_CURSOR);
				
		        rs =f_Access.imalat_urun_ara(textField_1.getText());
				
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			 if (! rs.isBeforeFirst() )
			 	{  }
			 else
			 {
				 rs.next();
				 textField.setText(rs.getString("Recete"));
			 }
				}
				catch (Exception ex)
				{
					
				}
			}
		});
		button_2.setBounds(185, 24, 25, 23);
		button_2.setIcon(new ImageIcon(RECETE.class.getResource("/ICONLAR/icons8-view-16.png")));
		panel_2.add(button_2);
		
		JLabel lblBarkod = new JLabel("Barkod");
		lblBarkod.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBarkod.setBounds(10, 57, 46, 14);
		panel_2.add(lblBarkod);
		
		JLabel label_2 = new JLabel("Ana Grup");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(385, 28, 59, 14);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("Alt Grup");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(385, 56, 61, 14);
		panel_2.add(label_3);
		
		lblNewLabel_4 = new JLabel("...");
		lblNewLabel_4.setForeground(Color.BLUE);
		lblNewLabel_4.setBounds(66, 56, 155, 14);
		panel_2.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("...");
		lblNewLabel_5.setForeground(Color.BLUE);
		lblNewLabel_5.setBounds(66, 78, 155, 14);
		panel_2.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("...");
		lblNewLabel_6.setForeground(new Color(128, 0, 0));
		lblNewLabel_6.setBounds(453, 28, 122, 14);
		panel_2.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("...");
		lblNewLabel_7.setForeground(new Color(128, 0, 0));
		lblNewLabel_7.setBounds(453, 56, 141, 14);
		panel_2.add(lblNewLabel_7);
		
		JLabel lblAgirlik = new JLabel("Agirlik");
		lblAgirlik.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAgirlik.setBounds(228, 29, 59, 14);
		panel_2.add(lblAgirlik);
		
		JLabel lblBirim = new JLabel("Birim");
		lblBirim.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBirim.setBounds(228, 57, 61, 14);
		panel_2.add(lblBirim);
		
		label_5 = new JLabel("...");
		label_5.setForeground(new Color(128, 0, 0));
		label_5.setBounds(296, 57, 81, 14);
		panel_2.add(label_5);
		
		label_6 = new JLabel("0.000");
		label_6.setForeground(new Color(128, 0, 0));
		label_6.setBounds(296, 29, 81, 14);
		panel_2.add(label_6);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setBounds(5, 128, 25, 222);
		panel.add(toolBar);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() < 0 ) return ;
				URUN_ARAMA arm ;
				getContentPane().setCursor(oac.WAIT_CURSOR);
					arm = new URUN_ARAMA();
					arm.show();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				table.getModel().setValueAt( oac.stk_kodu,table.getSelectedRow(), 1) ;
				bilgi_doldur(oac.stk_kodu);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(RECETE.class.getResource("/ICONLAR/icons8-view-16.png")));
		toolBar.add(btnNewButton_1);
		
		JButton button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				satir_ilave();
			}
		});
		button_3.setIcon(new ImageIcon(RECETE.class.getResource("/ICONLAR/yeni.png")));
		toolBar.add(button_3);
		
		JButton button_4 = new JButton("");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				satir_sil();
			}
		});
		button_4.setIcon(new ImageIcon(RECETE.class.getResource("/ICONLAR/icons8-reduce-16.png")));
		toolBar.add(button_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 128, 574, 272);
		panel.add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel() ; 
		table = new JTable(model){
			@Override
			public boolean isCellEditable(int row, int column) {  
				 switch (column) {
		         case 0:
		             return false;
		         case  2:
		             return false;
				 case 3:
		             return false;
		         default:
		             return true;
		      }
				}
			public void changeSelection(final int row, final int column, boolean toggle, boolean extend)
            {
                super.changeSelection(row, column, toggle, extend);
                if (column == 1)
                {
               table.editCellAt(row, column);
               table.transferFocus();
                }
            }	
		};
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10)
				{
					if (table.isEditing())
					table.getCellEditor().stopCellEditing();
				}
			}
		});
		table.setGridColor(oac.gridcolor);
		table.setCellSelectionEnabled(true);
		model.addColumn("Tur", new String []{""});
	    model.addColumn("Urun Kodu", new String []{""});
	    model.addColumn("Adi", new String []{""});
	    model.addColumn("Birim", new String []{"" });
	    model.addColumn("Miktar", new Double [] {new Double( 0.000 )});
	    TableColumn col ;
	    
	    col = table.getColumnModel().getColumn(0);
	   	col.setMinWidth(60);
	    col.setHeaderRenderer(new SOLA());
	    
	    col = table.getColumnModel().getColumn(1);
	    listkodlar = new ArrayList<String> () ;
	    stk_kodu_auto("Kodu");
	    ComboBoxTableCellEditor editorkodu = new ComboBoxTableCellEditor(listkodlar ,table,"recete");
	    col.setCellEditor(editorkodu);
		col.setMinWidth(100);
	    col.setHeaderRenderer(new SOLA());
	    /**
        Java2sAutoComboBox comboBox1 = new Java2sAutoComboBox( listkodlar,"recete");
        comboBox1.setDataList(listkodlar);
        comboBox1.setMaximumRowCount(10);
        comboBox1.setFont(new Font("Tahoma", Font.BOLD, 12));
        col.setCellEditor(new DefaultCellEditor(comboBox1));
		col.setMinWidth(100);
	    col.setHeaderRenderer(new SOLA());
	    */
	    col = table.getColumnModel().getColumn(2);
	   	col.setMinWidth(200);
	    col.setHeaderRenderer(new SOLA());
	    
	    col = table.getColumnModel().getColumn(3);
	   	col.setMinWidth(50);
	    col.setHeaderRenderer(new SOLA());
	    
	    col = table.getColumnModel().getColumn(4);
	    col.setHeaderRenderer(new SAGA());
	    col.setCellEditor( new DoubleEditor(3) );
		col.setCellRenderer(new TABLO_RENDERER(3,true));
		col.setMinWidth(100);
		
		 JTableHeader th = table.getTableHeader();
		    Dimension dd = table.getPreferredSize();
		    dd.height = 30;
		    th.setPreferredSize(dd); 
			table.setRowSelectionInterval(0, 0);
			table.setRowHeight(22);
			table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			InputMap im = table.getInputMap(JTable.WHEN_FOCUSED);
	        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Action.NextCell");
	        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Action.NextCell");
	        ActionMap am = table.getActionMap();
	        am.put("Action.NextCell", new NextCellActioin(table,"recete"));
	        table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
	    
		scrollPane.setViewportView(table);

		 ana_grup_doldur();
		//***********
			String deger;
			Integer sat_sayi;
				try {
					
					for (int i = 0; i <= 8 ; i ++)
					{
						satir_ilave();
					}
				} catch (Exception ex) {
				
				}
	//***********
				textField.requestFocus();
	}
	//**************************************************************************************************************************************
	private void ana_grup_doldur()
	{
		try {
		getContentPane().setCursor(oac.WAIT_CURSOR);
		cmbanagrup .removeAllItems();
		ResultSet rs=null;
		
			rs = f_Access.stk_kod_degisken_oku("ANA_GRUP", "AGID_Y", "ANA_GRUP_DEGISKEN");
		
		if (!rs.isBeforeFirst() ) {  
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			cmbaltgrup.setEnabled(false);
			cmbanagrup .addItem("");
			cmbanagrup.setSelectedItem("");
		    return;
		} 
		cmbanagrup .addItem("");
	    while (rs.next())
	    {
	    	cmbanagrup .addItem(rs.getString("ANA_GRUP"));
	    }
	    cmbanagrup.setSelectedItem("");
	    getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Ana Grup", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private void alt_grup_doldur()
	{
		try {
		getContentPane().setCursor(oac.WAIT_CURSOR);
		cmbaltgrup.removeAllItems();
		cmbaltgrup .addItem("");
		ResultSet rs=null;
		
			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN", cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()));
			if (!rs.isBeforeFirst() ) {
			}
			else
			{
				rs.next();
				int in1 = rs.getInt("AGID_Y");
				rs =null;
				rs = f_Access.stk_kod_alt_grup_degisken_oku(in1);
			}
		
		
		if (!rs.isBeforeFirst() ) {  
			cmbaltgrup.setSelectedItem("");
			cmbaltgrup.setEnabled(false);
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		} 
		else
		{
	    while (rs.next())
	    {
	    	cmbaltgrup .addItem(rs.getString("ALT_GRUP"));
	    }
	    cmbaltgrup.setSelectedItem(0);
	    cmbaltgrup.setEnabled(true);
		}
		getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Alt Grup", JOptionPane.ERROR_MESSAGE);    	
		}
	}
	private  void stk_kodu_auto(String field)
	{
		try {
		ResultSet rs = null;
		
	 		rs = f_Access.stk_barkod_kod_oku(field);
		
		if (!rs.isBeforeFirst() ) {  
			if (field.equals("Kodu"))
			{
				listkodlar.add("");
			}
			else
			{
				listkodlar.add("");
			}
		}
		else
		{
			if (field.equals("Kodu"))
			{
				listkodlar.clear();
				listkodlar.add("");
				while (rs.next())
				{
					listkodlar.add(rs.getString("Kodu").toString());
				}
			}
			else
			{
				//listBarkod.clear();
				//listBarkod.add("");
				while (rs.next())
				{
					//listBarkod.add(rs.getString("Barkod").toString());
				}
			}
		}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Otomatik Doldurma", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void satir_ilave()
	{
		 DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		 int satir = table.getSelectedRow();
		 if ( satir  < 0 ) 
		 {
		 mdl.addRow(new Object[]{"","","","",0.000});
		 satir = 0 ;
		 }
		 else
		 {
	      mdl.insertRow(satir, new Object[]{"","","","",0.000});
		 }
		 
		table.isRowSelected(satir);
		table.repaint();
	}
	public static void satir_sil()
	{
		if (table.getSelectedRow() < 0 ) return ;
		 DefaultTableModel mdll = (DefaultTableModel) table.getModel();
		 mdll.removeRow(table.getSelectedRow());
		 table.repaint();
	}
	public static void bilgi_doldur(String cins)
	{
		try {
			ResultSet rs = null;
		if (table.getSelectedColumn() == 1)  // URUN KODU
		{
			if (cins.equals(""))
			{
				table.getModel().setValueAt("Cikan",table.getSelectedRow(), 0) ;
				table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
				table.getModel().setValueAt("",table.getSelectedRow(), 3) ;
				table.getModel().setValueAt(0.000,table.getSelectedRow(), 4) ;
				return;
			}
			
				rs =f_Access.urun_adi_oku(cins,"Kodu");
			
		if (!rs.isBeforeFirst() ) 
		{  
			table.getModel().setValueAt("Cikan",table.getSelectedRow(), 0) ;
			table.getModel().setValueAt("",table.getSelectedRow(), 2) ;
			table.getModel().setValueAt("",table.getSelectedRow(), 3) ;
			table.getModel().setValueAt(0.000,table.getSelectedRow(), 4) ;	
		}
		else
		{
			rs.next();
			table.getModel().setValueAt("Cikan",table.getSelectedRow(), 0) ;
			table.getModel().setValueAt(rs.getString("Adi"),table.getSelectedRow(), 2) ;
			table.getModel().setValueAt(rs.getString("Birim"),table.getSelectedRow(), 3) ;
			table.getModel().setValueAt(0.000,table.getSelectedRow(), 4) ;
		    table.setCellSelectionEnabled(true);
		   // int col = 4;
		   // table.changeSelection(table.getSelectedRow(),col,false,false);
		    //table.getEditorComponent().requestFocus();
		}
		}
	}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Bilgi Doldur", JOptionPane.ERROR_MESSAGE);
		}
}		
	private void isimoku ()
	{
		try {
			ResultSet rs = null;

		
			
				rs = f_Access.urun_adi_oku(textField_1.getText(),"Kodu");
			
		if (!rs.isBeforeFirst() ) {  
			lblNewLabel_4.setText("") ;
			lblNewLabel_5.setText("") ;
			lblNewLabel_6.setText("") ;
			lblNewLabel_7.setText("") ;
			label_6.setText("0.000");
			label_5.setText("");
		}
		else
		{
			rs.next();
			lblNewLabel_5.setText(rs.getString("Adi")) ;
			lblNewLabel_4.setText(rs.getString("Barkod")) ;
			label_5.setText(rs.getString("Birim")) ;
			label_6.setText(FORMATLAMA.doub_3(rs.getDouble("Agirlik"))) ;
			lblNewLabel_6.setText(rs.getString("Ana_Grup")) ;
			lblNewLabel_7.setText(rs.getString("Alt_Grup"));
		}
	}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Urun Kodu", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void kaydet()
	{
		if (textField.getText().equals("") ) return ;
		if (textField_1.getText().equals("") ) return ;
		if (table.getRowCount()  == 0 ) return ;
		      try {
			            satir_yaz_1();
			            acik_yaz();
			           // '*************** URUN KODUNA RECETE YAZ *****************
			            koda_recete_yaz();
			            //'********************************************************
			            textField_1.setText("");
			            textField.setText("");
			            textArea.setText("");
			            textField.requestFocus();
			 }
			 catch (Exception ex)
			 {
			    	 JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Recete Kaydetme", JOptionPane.ERROR_MESSAGE);
			}
	}
	private static void koda_recete_yaz()
	{
		try {
		
			f_Access.kod_recete_yaz(textField_1.getText(), textField.getText());
		
	}
	   catch (Exception ex)
	   {
	       	 JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Recete Kaydetme", JOptionPane.ERROR_MESSAGE);
	   }
	}
	private static void acik_yaz()
	{
		try {
		 aciklama_sil();
		
			f_Access.aciklama_yaz("REC", 1,textField.getText(), textArea.getText(), "G",
					 "Recete Aciklama Yaz  G :" +  textArea.getText()   , textField.getText()  ,BAGLAN_LOG.fatLogDizin);
		
	}
	   catch (Exception ex)
	   {
	       	 JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Recete Aciksl", JOptionPane.ERROR_MESSAGE);
	   }
	}
	private static void aciklama_sil()
	{
		try {
		
		f_Access.aciklama_sil("REC", textField.getText(), "G",
				 "Recete Aciklama Sil G"    , textField.getText()  ,BAGLAN_LOG.fatLogDizin);
		
	}
	   catch (Exception ex)
	   {
	       	 JOptionPane.showMessageDialog(null,  ex.getMessage(),  "Recete Aciksl", JOptionPane.ERROR_MESSAGE);
	   }
	}
	private static void satir_yaz_1 () 
	{
		try {
		
		f_Access.rec_sil(textField.getText());
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		  for (int  i = 0 ; i <=  model.getRowCount() - 1 ; i++)
	       {
			   if (! model.getValueAt(i,0).toString().equals(""))
	    	   {
	                sat_yaz_2(i);
	    	   }
	       }
	        gir_yaz();
	}
	catch (Exception ex)
	{
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Recete Stayz1", JOptionPane.ERROR_MESSAGE);
	}
	}
	private static void gir_yaz ()
	{
	try {
		 boolean drm ;
	        int  anagrp, altgrp ;
	        if (comboBox_2.getItemAt(comboBox_2.getSelectedIndex()).toString().equals("Aktiv"))
	        {
	            drm = true;
	        }
	        else
	        {
	            drm = false;
	        }
	        anagrp = 0;
	        altgrp = 0;
	        ResultSet rs =null ;
	        if (! cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals(""))
	        {
	        	 if ( ! cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals("") ) {
		        		
		        			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",  cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString());
		        		
		        		if (!rs.isBeforeFirst() ) {      		
		    	    	}
		    	    	else
		    	    	{
		    	    		rs.next();
		    	    		 anagrp  = rs.getInt("AGID_Y");
		    	    	}
		        }
	        }
	        if (! cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString().equals(""))
	        {
	        	  if ( ! cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString().equals("") ) {
		     	      
		     	    		rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN",  cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString());
		     			
		     	      	if (!rs.isBeforeFirst() ) {      		
		    	    	}
		    	    	else
		    	    	{
		    	    		rs.next();
			     	      	altgrp  = rs.getInt("ALID_Y");
		    	    	}
		     	}
	        }
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	       
	        f_Access.recete_kayit(textField.getText(), drm, "Giren",textField_1.getText(),1.00
                         , anagrp, altgrp, GLOBAL.KULL_ADI);
 			
	}
	catch (Exception ex)
	{
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Recete Giryz", JOptionPane.ERROR_MESSAGE);
	}
	}
	private static void sat_yaz_2 (int sat) 
	{
		try {
	        boolean drm ;
	        int  anagrp, altgrp ;
	        if (comboBox_2.getItemAt(comboBox_2.getSelectedIndex()).toString().equals("Aktiv"))
	        {
	            drm = true;
	        }
	        else
	        {
	            drm = false;
	        }
	        double miktar = 0 ;
	    	DefaultTableModel model = (DefaultTableModel) table.getModel();
	        miktar = Double.parseDouble( model.getValueAt(sat,4).toString());
	        anagrp = 0 ;
	        altgrp = 0 ;
	        ResultSet rs =null ;
	        if (! cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString().equals(""))
	        {
	        		
		        			rs = f_Access.urun_kod_degisken_ara("AGID_Y", "ANA_GRUP", "ANA_GRUP_DEGISKEN",  cmbanagrup.getItemAt(cmbanagrup.getSelectedIndex()).toString());
		        		
		        		if (!rs.isBeforeFirst() ) {      		
		    	    	}
		    	    	else
		    	    	{
		    	    		rs.next();
		    	    		 anagrp  = rs.getInt("AGID_Y");
		    	    	}
	        }
	        if (! cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString().equals(""))
	        {
		     	      
		     	    		rs = f_Access.urun_kod_degisken_ara("ALID_Y", "ALT_GRUP", "ALT_GRUP_DEGISKEN",  cmbaltgrup.getItemAt(cmbaltgrup.getSelectedIndex()).toString());
		     			
		     	      	if (!rs.isBeforeFirst() ) {      		
		    	    	}
		    	    	else
		    	    	{
		    	    		rs.next();
			     	      	altgrp  = rs.getInt("ALID_Y");
		    	    	}
	        }
	        
	        f_Access.recete_kayit(textField.getText(), drm,model.getValueAt(sat,0).toString(),model.getValueAt(sat,1).toString()
                         , miktar, anagrp, altgrp, GLOBAL.KULL_ADI);
 			
	}
	catch (Exception ex)
	{
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Recete Stayz2", JOptionPane.ERROR_MESSAGE);
	}
	}
	public static void recete_sil()
	{
		try {
		if (textField.getText().equals("") ) return ;
		if (table.getRowCount()  == 0 ) return ;
		 int g =  JOptionPane.showOptionDialog( null,  "Kayit Dosyadan Silinecek ..?"  ,
	        		"Recete Dosyasindan Evrak Silme",   JOptionPane.YES_NO_OPTION,
		   			 	JOptionPane.QUESTION_MESSAGE,
		   			 	null,     //no custom icon
		   			 	oac.options,  //button titles
		   			 	oac.options[1]); //default button
		 	 if(g != 0 ) { return;	}
		 	
		      f_Access.rec_sil(textField.getText());
	 		
	        aciklama_sil();
	        //'***************MAL DOSYASI RECETE SIFIRLA ***************************
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
			  for (int  i = 0 ; i <=  model.getRowCount() - 1 ; i++)
		       {
				   if (! model.getValueAt(i,0).toString().equals("Giren"))
				   {
					  
				  f_Access.kod_recete_yaz(model.getValueAt(i,1).toString(), "");
			 			
				   }
		       }	   
	        //'*********************************************************************
	        textField.setText("");
	        textField.requestFocus();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Recete Sil", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void kontrol()
	{
	 try {
	   
		 ResultSet rs = null ;
		
	        rs =	f_Access.recete_oku(textField.getText());
			
		 temizle();
		 if (! rs.isBeforeFirst() )
		 	{  
		//	temizle();
			}
			else
			{
            // '************ACIKLAMA OKU ***********************************************************
				
					 textArea.setText(f_Access.aciklama_oku("REC", 1,textField.getText(), "G").toString());
					
				 DefaultTableModel mdl = (DefaultTableModel) table.getModel();
				 int satir =0 ;
				 while (rs.next())
             {
                 if(rs.getString("Tur").toString().equals("Cikan"))
               		 {
                	 mdl.insertRow(satir,new Object [] {rs.getString("Tur"), rs.getString("Kodu"), rs.getString("Adi") , rs.getString("Birim"),rs.getDouble("Miktar")});
                	 satir +=1 ;
     			   	mdl.removeRow(mdl.getRowCount() -1);
               		 }
                     else
                     {
                    	 textField_1.setText(rs.getString("Kodu"));
                    	 cmbanagrup.setSelectedItem(rs.getString("Ana_Grup"));
                    	 cmbaltgrup.setSelectedItem(rs.getString("Alt_Grup"));
                     }
             }
			}
	
		 }
     catch (Exception ex)
		 {
 
    	 JOptionPane.showMessageDialog(null,  ex.getMessage(), "Recete Okuma", JOptionPane.ERROR_MESSAGE);
		 }
	}
	private static void temizle()
	{
		 GRID_TEMIZLE.grid_temizle(table);
		 for (int i = 0; i <= 8 ; i ++)
			{
				satir_ilave();
			}
		 textField_1.setText("");
		 textArea.setText("");
    	 cmbanagrup.setSelectedItem("");
	}
}

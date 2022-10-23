package OBS_PACKAGE;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.text.JTextComponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Deneme extends JInternalFrame {
	static OBS_SIS_ANA_CLAS oac = new OBS_SIS_ANA_CLAS();
	Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private static String [] hpl = {"","",""};
	private 	JComboBox<String> comboBox ;
	private ArrayList<String> listSomeString = null ;
	private JPanel panel;
	private JComboBox comboBox_1;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Deneme frame = new Deneme();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Deneme()  {
		setResizable(true);
		setClosable(true);
		
		
		setBounds(100, 100, 812, 309);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		lblNewLabel_1 = new JLabel("...");
		lblNewLabel_1.setForeground(new Color(0, 0, 139));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(237, 15, 219, 14);
		contentPane.add(lblNewLabel_1);
			
			comboBox = new JComboBox<String>();
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
				}
			});
			comboBox.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
				}
				@Override
				public void keyReleased(KeyEvent e) {
				}
			});
			
			
		
			comboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					System.out.println("gggg" );
				}
			});
			
			comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
			comboBox.setForeground(new Color(0, 0, 0));
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.setCursor(WAIT_CURSOR);
					kontrol();
					contentPane.setCursor(DEFAULT_CURSOR);
				}
				
			});
			comboBox.setEditable(true);
			comboBox.setBounds(10, 11, 199, 22);
			comboBox.getEditor().getEditorComponent().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) 
					{
			
						JOptionPane.showMessageDialog(null,"Double Click.......",  "OBS SISTEM", JOptionPane.ERROR_MESSAGE);   
					}
				}
			});
			contentPane.add(comboBox);
			AutoCompleteDecorator.decorate(comboBox);
			//doldur();
			AUTO_HESAP_KODU.auto_doldur(comboBox);
			
			panel = new JPanel();
			panel.setBackground(new Color(245, 255, 250));
			panel.setBorder(new LineBorder(new Color(95, 158, 160), 1));
			panel.setBounds(570, 15, 201, 36);
			contentPane.add(panel);
			
			
		    listSomeString = new ArrayList<String> () ;
			stk_kodu_auto();
	
			Java2sAutoComboBox comboBox1 = new Java2sAutoComboBox( listSomeString,"toplu");
		    comboBox1.getEditor().selectAll();
	        comboBox1.setDataList(listSomeString);
		    comboBox1.setMaximumRowCount(10);
		    comboBox1.setFont(new Font("Tahoma", Font.BOLD, 12));
		    comboBox1.getEditor().selectAll();
		    comboBox1.setBounds(10, 50, 201, 25);
		    contentPane.add(  comboBox1);
		    
		    comboBox_1 = new JComboBox();
		    comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		    comboBox_1.setEditable(true);
		    comboBox_1.setBounds(10, 148, 199, 22);
		    contentPane.add(comboBox_1);
		    
		 
		
		
		  
		    
	}
	
	private void kontrol()
	{
		try 
		{
		if (comboBox.getItemAt(comboBox.getSelectedIndex() )== null) return ;
		    hpl = new String[3];
			hpl = CARI_ISIM_OKU.isim(comboBox.getItemAt(comboBox.getSelectedIndex()).toString());
			lblNewLabel_1.setText(hpl[0]);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,"Isim Okuma.......",  "OBS SISTEM", JOptionPane.ERROR_MESSAGE);   
		}
	}
	private  void stk_kodu_auto()
	{
		try {
	
		ResultSet rs = null;
		if (CONNECTION.fatdizinbilgi.han_sql.equals("MS SQL"))
		{
	 		rs = oac.sTOK_MSSQL.stk_barkod_kod_oku("Kodu");
		}
	 	else
	 	{
	 		rs = oac.sTOK_MYSQL.stk_barkod_kod_oku("Kodu");
	 	}
		if (!rs.isBeforeFirst() ) {  
			listSomeString.add("");
		}
		else
		{
			listSomeString.add("");
			while (rs.next())
			{
				listSomeString.add(rs.getString("Kodu"));
			}
		}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Stok Kodu", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public class PlaceholderRenderer extends DefaultTableCellRenderer {

	    final private String placeholder;

	    public PlaceholderRenderer(String placeholder) {
	        super();
	        this.placeholder = placeholder;
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table,
	            Object value,
	            boolean isSelected,
	            boolean hasFocus,
	            int row,
	            int column) {
	        if ((value == null) || (value.equals(""))) { 
	            return super.getTableCellRendererComponent(table, this.placeholder, isSelected, hasFocus, row, column);  
	        } else { 
	            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
	        }
	    }

	}
}


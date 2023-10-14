package OBS_2025;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_C_2025.ADRES_ACCESS;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.SOLA;
import OBS_C_2025.STOK_ACCESS;
import OBS_C_2025.lOG_BILGI;
import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.sql.ResultSet;

@SuppressWarnings({"serial","static-access","unused"})
public class GIDECEGI_YER extends JInternalFrame {
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static STOK_ACCESS f_Access = new STOK_ACCESS(OBS_SIS_2025_ANA_CLASS._IStok , OBS_SIS_2025_ANA_CLASS._IFatura_Loger);
	static ADRES_ACCESS a_Access = new ADRES_ACCESS(OBS_SIS_2025_ANA_CLASS._IAdres , OBS_SIS_2025_ANA_CLASS._IAdres_Loger);

	
	private static JTable table;
	private static JTextField textField;
	public static JTextField textField_1;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_4;
	private static JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GIDECEGI_YER frame = new GIDECEGI_YER();
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
	public GIDECEGI_YER() {
		setTitle("GIDECEGI YER");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 664, 452);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(1.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(0, 190));
		panel.setMaximumSize(new Dimension(0, 190));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 626, 34);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Arama");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(77, 8, 340, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setVisible(false);
		textField_5.setBounds(495, 8, 86, 20);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Gidecegi Yer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 55, 626, 127);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Isim");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 23, 46, 14);
		panel_2.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_1.setBounds(77, 20, 524, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Adres");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 50, 46, 14);
		panel_2.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_2.setBounds(77, 47, 524, 20);
		panel_2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Semt");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(10, 75, 46, 14);
		panel_2.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_3.setBounds(77, 72, 524, 20);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_4.setBounds(77, 97, 524, 20);
		panel_2.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Sehir");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(10, 100, 46, 14);
		panel_2.add(lblNewLabel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);

		table = new JTable(){
			@Override
			public boolean isCellEditable(int row, int column) {  
				return false;	}	};
				table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent lse) {
						if (!lse.getValueIsAdjusting()) {
							kutu_doldur();
						}
					}
				});
		table.setGridColor(oac.gridcolor);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
		doldur();
		//**
		adres_bak();
		 //**
	}
	private static void doldur()
	{
		try {
		ResultSet	rs = null;
		
			 rs = f_Access.ggdy_oku();
		
			GRID_TEMIZLE.grid_temizle(table);
		 if (!rs.isBeforeFirst() ) {  
			} 
		 else
		 {
		
			table.setModel(DbUtils.resultSetToTableModel(rs));
				JTableHeader th = table.getTableHeader();
				
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
					tc = tcm.getColumn(0);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(250);
					
					tc = tcm.getColumn(1);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(200);
					
					tc = tcm.getColumn(2);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(150);
					
					tc = tcm.getColumn(3);
					tc.setHeaderRenderer(new SOLA());
					tc.setMinWidth(150);
					table.removeColumn(table.getColumnModel().getColumn(4));
	
					Dimension dd = th.getPreferredSize();
				    dd.height = 30;
				    th.setPreferredSize(dd); 
					th.repaint();
		
					table.setRowSelectionInterval(0, 0);
					table.setRowHeight(21);
					table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
					table.setRowSelectionInterval(0,0);
					table.changeSelection(0,0,false,false);
					table.setSelectionBackground(Color.PINK);
					table.setSelectionForeground(Color.BLUE);
		 }
		
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Gidecegi Yer Okuma", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void kutu_doldur()
	{
		if (table.getSelectedRow() < 0) return ;
		DefaultTableModel mdl = (DefaultTableModel) table.getModel();
		 if (mdl.getRowCount() == 0 ) return ;
		 textField_1.setText( mdl.getValueAt(table.getSelectedRow(),0).toString());
		 textField_2.setText( mdl.getValueAt(table.getSelectedRow(),1).toString());
		 textField_3.setText( mdl.getValueAt(table.getSelectedRow(),2).toString());
		 textField_4.setText( mdl.getValueAt(table.getSelectedRow(),3).toString());
		 textField_5.setText( mdl.getValueAt(table.getSelectedRow(),4).toString());
	}
	private static void temizle()
	{
		textField.setText("");
		 textField_1.setText("");
		 textField_2.setText("");
		 textField_3.setText("");
		 textField_4.setText("");
		 textField_5.setText("");
	}
	public static void yeni()
	{
		temizle();
	}
	public static void sil()
	{
		 if ( textField_5.getText().equals("")) return ;
	        int g =  JOptionPane.showOptionDialog( null,  "Kayit Dosyadan Silinecek ..?"  ,
	        		"Gidecegi Yer Dosyasindan Silme",   JOptionPane.YES_NO_OPTION,
		   			 	JOptionPane.QUESTION_MESSAGE,
		   			 	null,     //no custom icon
		   			 	oac.options,  //button titles
		   			 	oac.options[1]); //default button
		 	 if(g != 0 ) { return;	}
		
			try
			{
				lOG_BILGI lBILGI = new lOG_BILGI();
				lBILGI.setmESAJ("Gid Yer  Sil" );
				lBILGI.seteVRAK(textField_5.getText());
					f_Access.gdy_sil(Integer.parseInt(textField_5.getText()),
							lBILGI ,BAGLAN_LOG.fatLogDizin);
				
				 temizle();
				doldur();
					textField.requestFocus();
			}
			  catch (Exception ex)
			{
					JOptionPane.showMessageDialog(null,  ex.getMessage(),"Gidecegi Yer Silme", JOptionPane.ERROR_MESSAGE);
			}
	}
	public static void kaydet()
	{
		 if (textField_1.getText().equals("") && textField_2.getText().equals("")) return ;
		try
		{
			lOG_BILGI lBILGI = new lOG_BILGI();
			
			
			if ( ! textField_5.getText().equals(""))
			{
				lBILGI.setmESAJ("Gid Yer  Sil" );
				lBILGI.seteVRAK(textField_5.getText());
					f_Access.gdy_sil(Integer.parseInt(textField_5.getText()),
							lBILGI ,BAGLAN_LOG.fatLogDizin);
					
			}
			
			lBILGI.setmESAJ( "Gid Yer  Kayit :"  + textField_1.getText());
			lBILGI.seteVRAK("");
			f_Access.gdy_kayit(textField_1.getText(),textField_2.getText(), textField_3.getText(), textField_4.getText(),GLOBAL.KULL_ADI,
					lBILGI ,BAGLAN_LOG.fatLogDizin);
			
			temizle();
			doldur();
			textField.requestFocus();
		}
		  catch (Exception ex)
		{
				JOptionPane.showMessageDialog(null,  ex.getMessage(),"Gidecegi Yer Kaydetme", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void adres_bak()
	{
		 ResultSet rs =null;
		 try
		 {
		if (oac.nerden.equals("irs"))
				{
			 if (!  IRSALIYE.textField_8.getText().equals(""))
			 {
				
				 
						rs =  a_Access.gdy_oku(IRSALIYE.textField_8.getText());
				
				 if (!rs.isBeforeFirst() ) {  
						JOptionPane.showMessageDialog(null,  "Adres Dosyasinda Bu Kodda Kayitli Bilgi Yok....","Irsaliye Yazdirma", JOptionPane.PLAIN_MESSAGE);   
					}
				 else
				 {
                  rs.next();
                  textField_1.setText(rs.getString("Adi"));
                  textField_2.setText(rs.getString("Adres_1"));
                  textField_3.setText(rs.getString("Adres_2"));
                  textField_4.setText(rs.getString("Semt") + "/" +rs.getString("Sehir") );
 				 }
				}
				}
            else if  (oac.nerden.equals("fat"))
            		{
                if (!  FATURA.textField_8.getText().equals(""))
                {
                	
 						rs = a_Access.gdy_oku(FATURA.textField_8.getText());
 					
 				 if (!rs.isBeforeFirst() ) {  
 						JOptionPane.showMessageDialog(null,  "Adres Dosyasinda Bu Kodda Kayitli Bilgi Yok....","Fatura Yazdirma", JOptionPane.PLAIN_MESSAGE);   
 					}
 				 else
 				 {
                   rs.next();
                   textField_1.setText(rs.getString("Adi"));
                   textField_2.setText(rs.getString("Adres_1"));
                   textField_3.setText(rs.getString("Adres_2"));
                   textField_4.setText(rs.getString("Semt") + "/" +rs.getString("Sehir") );
  				 }
                }
            		}
		 }
		  catch (Exception ex)
		{
				JOptionPane.showMessageDialog(null,  ex.getMessage(),"Gidecegi Adres Okuma", JOptionPane.ERROR_MESSAGE);
		}
	}
}

package KER_RAPOR;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import OBS_2025.FILTRE;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.KERESTE_ACCESS;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;

public class KER_GRUP_RAPOR extends JInternalFrame {

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KERESTE_ACCESS ker_Access = new KERESTE_ACCESS(oac._IKereste , OBS_SIS_2025_ANA_CLASS._IKereste_Loger);
	static CARI_ACCESS c_Access = new CARI_ACCESS(oac._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	private static JTable table;
	private static String qwq6  = "";
	private static String qwq7  = "";
	private static String qwq8  = "";
	private static JLabel lbladet;
	public static JSplitPane splitPane ;
	static String sstr_1 = "" ;
	static String sstr_2 = "" ;
	static String sstr_4 = "" ;
	static String sstr_5 = "" ;
	private static  String  jkj  = "" ;
	private static String jkj1 = "";
	private static String ch1 ="";
	private static String kur_dos = "";
	private static 	long startTime;
	private static int kusur = 0 ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KER_GRUP_RAPOR frame = new KER_GRUP_RAPOR();
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
	public KER_GRUP_RAPOR() {
		setTitle("KERESTE GRUP RAPOR");
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0,1100,800);

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.setGridColor(oac.gridcolor);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		panel.setMinimumSize(new Dimension(0, 25));
		panel.setMaximumSize(new Dimension(0, 25));
		splitPane.setRightComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Satir Sayisi :");
		lblNewLabel.setBounds(10, 5, 85, 14);
		panel.add(lblNewLabel);
		
		lbladet = new JLabel("0");
		lbladet.setForeground(new Color(0, 0, 128));
		lbladet.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbladet.setHorizontalAlignment(SwingConstants.LEFT);
		lbladet.setBounds(100, 5, 40, 14);
		panel.add(lbladet);
	}
	public static void yenile ()
	{
		 try
		 {
		 		GRID_TEMIZLE.grid_temizle(table);
				 if (FILTRE.comboBox_27_1.getItemAt(FILTRE.comboBox_27_1.getSelectedIndex()).equals("Urun Kodu"))
					{
					 	baslik_bak();
					 	if (! sstr_1.equals(""))
						urun_kodlu();
					}
				
		 }
     catch (Exception ex)
		 {
    	 GRID_TEMIZLE.grid_temizle(table);
    		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Raporlama", JOptionPane.ERROR_MESSAGE);
    	}
	}
	private static void baslik_bak()
	{
	 try {
	     String  jkj  = "" ;
	     if (FILTRE.comboBox_77.getItemAt(FILTRE.comboBox_77.getSelectedIndex()).equals("GIREN"))
	     {
	    	 jkj = " Cikis_Evrak = '' " ;
	     }
	     else {
	    	 jkj = " Cikis_Evrak <> '' " ;
		}
	     
	    
	    
	     ResultSet rs= null ;
	     if (FILTRE.comboBox_28_1.getItemAt(FILTRE.comboBox_28_1.getSelectedIndex()).equals("Yil"))
	     {
	    		if(BAGLAN.kerDizin.hAN_SQL.equals("MS SQL"))
	       		{
	    			rs = ker_Access.baslik_bak("DISTINCT datepart(yyyy,Tarih)","order by datepart(yyyy,Tarih)",jkj,
	    					FILTRE.formattedTextField.getText(),FILTRE.formattedTextField_1.getText() ,
	    					FILTRE.textField_82.getText(),FILTRE.textField_83.getText() ,
	    					TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20_1),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21_1));
		           sstr_2 = " datepart(yyyy,Tarih)" ;
	       		}
	    		else 	if(BAGLAN.kerDizin.hAN_SQL.equals("MY SQL"))
	       		{
	    			

	       		}
	     
	    
	        sstr_1 = "";
	        String text = "" ;
	          while (rs.next())
	        	{
	                	 text = text +  "[" + rs.getString(1).toString() + "]" + " , " ;
	        	 }
	          sstr_1 =  text.equals("") ?   "":    text.substring(0, text.length() - 2);
	          System.out.println( sstr_1);
	     }
	 }
     catch (Exception ex)
		 {
    		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Raporlama", JOptionPane.ERROR_MESSAGE);
    	}
	}
	private static void urun_kodlu() 
	{
		startTime = System.currentTimeMillis(); 
		try {
			ResultSet	rs = null;
			deg_cevir();
 			grup_cevir() ;
			
				rs = f_Access.grp_urn_kodlu(sstr_2,sstr_4, kur_dos, jkj, ch1,  qwq6,  qwq7,  qwq8,
						FILTRE.textField_35.getText(), FILTRE.textField_36.getText(),
						FILTRE.textField_32.getText(),FILTRE.textField_33.getText(),
						jkj1,
						FILTRE.textField_31.getText(), FILTRE.textField_34.getText(),
						TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_20),TARIH_CEVIR.tarih_geri(FILTRE.dateChooser_21),
						sstr_5, sstr_1);
			

			GRID_TEMIZLE.grid_temizle(table);
			if (!rs.isBeforeFirst() ) {  
			
				lbladet.setText(FORMATLAMA.doub_0(0));
			} 
			else
			{
				table.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel mdll = (DefaultTableModel) table.getModel();
				 mdll.addColumn("Toplam");
				JTableHeader th = table.getTableHeader();
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(90);
				
				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(231);
				tc.setMaxWidth(231);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(50);
				
				kusurr();
				for (int i = 3;i<=table.getColumnCount() -2;i++)
				{
					tc = tcm.getColumn(i);
					tc.setHeaderRenderer(new SAGA());
					tc.setCellRenderer(new TABLO_RENDERER(kusur,false));
					tc.setMinWidth(110);
				}
				tc = tcm.getColumn(table.getColumnCount() -1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(kusur,true));
				tc.setMinWidth(110);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(21);
				//**
				topla(3);
				//**
				alt_bolum();
				fontt();
			}
		} 
		catch (Exception ex) {
		JOptionPane.showMessageDialog(null,  ex.getMessage(), "Grup Urun Kodlu Raporlama", JOptionPane.ERROR_MESSAGE);
		}
	}
}

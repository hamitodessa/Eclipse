package OBS_2025;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.KUR_ACCESS;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DefaultFormatterFactory;

import javax.swing.text.NumberFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.swing.JSeparator;


public class KUR_GIRIS extends JInternalFrame {
	private static JTable table;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KUR_ACCESS k_Access = new KUR_ACCESS(oac._IKur , OBS_SIS_2025_ANA_CLASS._IKur_Loger);

	private static  JDateChooser dateChooser ;
	private static  JComboBox<String> comboBox ;
	
	private static  JFormattedTextField formattedTextField ;
	private static  JFormattedTextField formattedTextField_1 ;
	private static  JFormattedTextField formattedTextField_2 ;
	private static  JFormattedTextField formattedTextField_3 ;
	
	private static  JFormattedTextField formattedTextField_1_1 ;
	private static  JFormattedTextField formattedTextField_1_2 ;
	
	DecimalFormat df = new DecimalFormat(); // And here..
	NumberFormatter dnff = new NumberFormatter(df);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KUR_GIRIS frame = new KUR_GIRIS();
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
	public KUR_GIRIS() {
		setIconifiable(true);
		setResizable(true);
		setTitle("KUR GIRIS");
		setClosable(true);
		setBounds(0, 0, 500, 438);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(1);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table.setGridColor(oac.gridcolor);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 140));
		panel.setMaximumSize(new Dimension(0, 140));
		panel.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setForeground(new Color(0, 0, 128));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
 	        	kur_liste();
 	        	kur_oku();
 	        	getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"USD", "EUR", "GBR", "CHF", "SEK", "NOK", "SAR", "RUB"}));
		comboBox.setEditable(true);
		comboBox.setBounds(75, 9, 93, 30);
		panel.add(comboBox);
		
		dateChooser = new JDateChooser();
		dateChooser.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dateChooser.setDate(new Date());
				}
			}
		});
		dateChooser.setDateFormatString("dd.MM.yyyy");
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 16));
		dateChooser.setDate(new Date());
		dateChooser.setBounds(190, 9, 135, 30);
		dateChooser.getDateEditor().addPropertyChangeListener(
        	    new PropertyChangeListener() {
        	        @Override
        	        public void propertyChange(PropertyChangeEvent e) {
        	        	if ("date".equals(e.getPropertyName())) {
        	        	getContentPane().setCursor(oac.WAIT_CURSOR);
         	        	kur_liste();
         	        	kur_oku();
         	        	getContentPane().setCursor(oac.DEFAULT_CURSOR);
        	        	}
     	        }
        	    });
		panel.add(dateChooser);
		
		JButton btnNewButton = new JButton("Merkez");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (oac.glb.internet_kontrol() == false)
				{
					 JOptionPane.showMessageDialog(null,  "Internet Baglantisi Yok ",  "Merkez Bankasi Kur Okuma", JOptionPane.ERROR_MESSAGE);	
					return ;
				}
				getContentPane().setCursor(oac.WAIT_CURSOR);
				merkez();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);			
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(364, 11, 91, 23);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Merkez", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1.setBounds(75, 51, 120, 79);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		formattedTextField = new JFormattedTextField();
		formattedTextField.setFont(new Font("Tahoma", Font.BOLD, 12));
		formattedTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField.setText("0.0000");
		
		DefaultFormatterFactory f_dob = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(4);
        df.setMaximumFractionDigits(4);
        formattedTextField.setFormatterFactory(f_dob);
		formattedTextField.setBounds(26, 21, 75, 20);
		panel_1.add(formattedTextField);
		
		formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		formattedTextField_1.setText("0.0000");
		DefaultFormatterFactory f_dob1 = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(4);
        df.setMaximumFractionDigits(4);
        formattedTextField_1.setFormatterFactory(f_dob1);
		formattedTextField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField_1.setBounds(26, 45, 75, 20);
		panel_1.add(formattedTextField_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Serbest", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1_1.setBounds(200, 51, 120, 79);
		panel.add(panel_1_1);
		
		formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		formattedTextField_2.setText("0.0000");
		DefaultFormatterFactory f_dob2 = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(4);
        df.setMaximumFractionDigits(4);
        formattedTextField_2.setFormatterFactory(f_dob2);
		formattedTextField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField_2.setBounds(26, 21, 75, 20);
		panel_1_1.add(formattedTextField_2);
		
		formattedTextField_1_1 = new JFormattedTextField();
		formattedTextField_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		formattedTextField_1_1.setText("0.0000");
		DefaultFormatterFactory f_dob11 = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(4);
        df.setMaximumFractionDigits(4);
        formattedTextField_1_1.setFormatterFactory(f_dob11);
		formattedTextField_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField_1_1.setBounds(26, 45, 75, 20);
		panel_1_1.add(formattedTextField_1_1);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Bankalar Arasi", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1_2.setBounds(335, 51, 120, 79);
		panel.add(panel_1_2);
		
		formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		formattedTextField_3.setText("0.0000");
		DefaultFormatterFactory f_dob3 = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(4);
        df.setMaximumFractionDigits(4);
        formattedTextField_3.setFormatterFactory(f_dob3);
		formattedTextField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField_3.setBounds(26, 21, 75, 20);
		panel_1_2.add(formattedTextField_3);
		
		formattedTextField_1_2 = new JFormattedTextField();
		formattedTextField_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		formattedTextField_1_2.setText("0.0000");
		DefaultFormatterFactory f_dob12 = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(4);
        df.setMaximumFractionDigits(4);
        formattedTextField_1_2.setFormatterFactory(f_dob12);
		formattedTextField_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField_1_2.setBounds(26, 45, 75, 20);
		panel_1_2.add(formattedTextField_1_2);
		
		JLabel lblNewLabel = new JLabel("Alis");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 75, 55, 14);
		panel.add(lblNewLabel);
		
		JLabel lblSatis = new JLabel("Satis");
		lblSatis.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSatis.setBounds(10, 98, 55, 14);
		panel.add(lblSatis);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 445, 2);
		panel.add(separator);
		kur_liste();
		kur_oku();
	}
	private static void kur_liste()
	{
		try
		{
			 ResultSet rs ;
						 rs = k_Access.kur_liste(TARIH_CEVIR.tarih_geri_SQL(dateChooser));
			if (!rs.isBeforeFirst() ) {  
				GRID_TEMIZLE.grid_temizle(table);
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + 0 + " saniye");
				return ;
			} 
			GRID_TEMIZLE.grid_temizle(table);
			table.setModel(DbUtils.resultSetToTableModel(rs));
				JTableHeader th = table.getTableHeader();
				
				TableColumnModel tcm = th.getColumnModel();
				TableColumn tc;
				
				tc = tcm.getColumn(0);
				tc.setHeaderRenderer(new SOLA());
				tc.setMinWidth(40);

				tc = tcm.getColumn(1);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(4,false));
				tc.setMinWidth(55);
				
				tc = tcm.getColumn(2);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(4,false));
				tc.setMinWidth(55);
				
				tc = tcm.getColumn(3);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(4,false));
				tc.setMinWidth(55);
				
				tc = tcm.getColumn(4);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(4,false));
				tc.setMinWidth(55);
				
				tc = tcm.getColumn(5);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(4,false));
				tc.setMinWidth(55);
				
				tc = tcm.getColumn(6);
				tc.setHeaderRenderer(new SAGA());
				tc.setCellRenderer(new TABLO_RENDERER(4,false));
				tc.setMinWidth(55);
				
				Dimension dd = th.getPreferredSize();
			    dd.height = 30;
			    th.setPreferredSize(dd); 
				th.repaint();
				table.setRowSelectionInterval(0, 0);
				table.setRowHeight(20);
		}
		catch (Exception ex)
		{
			 JOptionPane.showMessageDialog(null,  ex.getMessage()); 
		}
	}
	private void kur_oku()
	{
		ResultSet rs ;
		try
		{
		 
			rs = k_Access.kur_oku(TARIH_CEVIR.tarih_geri_SQL(dateChooser),comboBox.getItemAt(comboBox.getSelectedIndex()));
	
		if (!rs.isBeforeFirst() ) {  
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + 0 + " saniye");
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			sifirla ();
			return ;
		} 
		 rs.next ();
		DecimalFormat decimal4 = new DecimalFormat("#,##0.0000");
		
		String tut = decimal4.format(rs.getDouble("MA"));
		formattedTextField.setText((String.valueOf(tut)));
		
		tut = decimal4.format(rs.getDouble("MS"));
		formattedTextField_1.setText((String.valueOf(tut)));
		
		tut = decimal4.format(rs.getDouble("SA"));
		formattedTextField_2.setText((String.valueOf(tut)));
		
		tut = decimal4.format(rs.getDouble("SS"));
		formattedTextField_1_1.setText((String.valueOf(tut)));
		
		tut = decimal4.format(rs.getDouble("BA"));
		formattedTextField_3.setText((String.valueOf(tut)));
		
		tut = decimal4.format(rs.getDouble("BS"));
		formattedTextField_1_2.setText((String.valueOf(tut)));
		}
        catch (Exception ex)
        {
        	 JOptionPane.showMessageDialog(null,  ex.getMessage()); 
        }
	}
	private static void sifirla()
	{
		formattedTextField.setText("0.0000");
		formattedTextField_1.setText("0.0000");
		formattedTextField_2.setText("0.0000");
		formattedTextField_1_1.setText("0.0000");
		formattedTextField_3.setText("0.0000");
		formattedTextField_1_2.setText("0.0000");
	}
	public static void kayit()
	{
        try
        {
        	long startTime = System.currentTimeMillis(); 
        
			k_Access.kur_sil(TARIH_CEVIR.tarih_geri_SQL(dateChooser),comboBox.getItemAt(comboBox.getSelectedIndex()));
			k_Access.kur_kayit(TARIH_CEVIR.tarih_geri_SQL(dateChooser),comboBox.getItemAt(comboBox.getSelectedIndex()) ,
					Double.parseDouble(formattedTextField.getText ()),Double.parseDouble(formattedTextField_1.getText ()),
					Double.parseDouble(formattedTextField_2.getText ()),Double.parseDouble(formattedTextField_1_1.getText ()),
					Double.parseDouble(formattedTextField_3.getText ()),Double.parseDouble(formattedTextField_1_2.getText ()));
             kur_liste();
    		 long endTime = System.currentTimeMillis();
    		 long estimatedTime = endTime - startTime; 
    		 double seconds = (double)estimatedTime/1000; 
    		 OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");
        }
        catch (Exception ex)
        {
        	 JOptionPane.showMessageDialog(null,  ex.getMessage());  
        }
	}
	public static void sil()
	{
        try
        {
  			k_Access.kur_sil(TARIH_CEVIR.tarih_geri_SQL(dateChooser),comboBox.getItemAt(comboBox.getSelectedIndex()));
            kur_liste();
            sifirla();
        }
        catch (Exception ex )
        {
        	 JOptionPane.showMessageDialog(null,  ex.getMessage());  
        }
	}
	private void merkez ()
	{
		try
		{
		String bugun = LocalDate.now().toString() ;
		if (TARIH_CEVIR.tarih_geri_kasa(dateChooser).equals(bugun) )
		{
			Document document = Jsoup.connect("https://www.tcmb.gov.tr/kurlar/today.xml").get();
	        Elements elements = document.select("Currency"); 
	            for( Element element : elements ) 
	            {
	            	String KUR = element.attr("Kod");
	            	if (KUR.equals(comboBox.getItemAt(comboBox.getSelectedIndex())))
	            	{
	            	String ForexBuying = element.select("ForexBuying").first().text();
	            	String ForexSelling = element.select("ForexSelling").first().text();
	            	formattedTextField.setText(ForexBuying);
	            	formattedTextField_1.setText(ForexSelling);
	            	}
	            }
		}
		else
			{
			 String tarih = TARIH_CEVIR.tarih_geri_kasa(dateChooser) ;
			 tarih  = tarih.substring(0,4) + tarih.substring(5,7) +  "/" + tarih.substring(8,10) + tarih.substring(5,7) + tarih.substring(0,4) + ".xml" ;
		
			 Document document = Jsoup.connect("https://www.tcmb.gov.tr/kurlar/" + tarih).get();

			 Elements elements = document.select("Currency");
	            for( Element element : elements ) 
	            {
	            	String KUR = element.attr("Kod");
	            	if (KUR.equals(comboBox.getItemAt(comboBox.getSelectedIndex())))
	            	{
	            	String ForexBuying = element.select("ForexBuying").first().text();
	            	String ForexSelling = element.select("ForexSelling").first().text();
	            	formattedTextField.setText(ForexBuying);
	            	formattedTextField_1.setText(ForexSelling);
	            	}
	            }
			}
			
		}
    catch (Exception ex )
		{
    	 sifirla();
    	}
	 }
}

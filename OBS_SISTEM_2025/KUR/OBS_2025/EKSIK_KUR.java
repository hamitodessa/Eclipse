package OBS_2025;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.toedter.calendar.JDateChooser;
import OBS_C_2025.DoubleEditor;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.KUR_ACCESS;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings({"serial","static-access", "removal"})
public class EKSIK_KUR extends JInternalFrame 
{

	private static JTable table;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KUR_ACCESS k_Access = new KUR_ACCESS(OBS_SIS_2025_ANA_CLASS._IKur ,OBS_SIS_2025_ANA_CLASS._IKur_Loger);

	static  JDateChooser dateChooser ;
	private  JDateChooser dateChooser_1 ;
	static JComboBox<String> cmbCins;
	static JLabel lblkayit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					EKSIK_KUR frame = new EKSIK_KUR();
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
	public EKSIK_KUR() 
	{
		setIconifiable(true);
		setResizable(true);
		setTitle("EKSIK KUR");
		setClosable(true);
		setBounds(0, 0, 379, 538);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(1);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 120));
		panel.setMaximumSize(new Dimension(0, 120));
		panel.setLayout(null);

		dateChooser = new JDateChooser();
		dateChooser.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		dateChooser.setDateFormatString("dd.MM.yyyy");
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 14));

		dateChooser.setBounds(21, 11, 150, 20);
		panel.add(dateChooser);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		dateChooser_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		dateChooser_1.setBounds(180, 11, 150, 20);
		panel.add(dateChooser_1);

		cmbCins = new JComboBox<String>();
		cmbCins.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbCins.setModel(new DefaultComboBoxModel<String>(new String[] {"USD", "EUR", "GBR", "CHF", "SEK", "NOK", "SAR", "RUB"}));
		cmbCins.setEditable(true);
		cmbCins.setBounds(21, 42, 60, 22);
		panel.add(cmbCins);

		JButton btnNewButton = new JButton("Bos Tarihleri Bul");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.setForeground(new Color(0, 0, 128));
		btnNewButton.setMargin(new Insets(2, 1, 2, 1));
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().setCursor(oac.WAIT_CURSOR);
				te_sifirla();
				oku();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);			
			}
		});
		btnNewButton.setBounds(21, 75, 120, 23);
		panel.add(btnNewButton);

		lblkayit = new JLabel("0");
		lblkayit.setForeground(new Color(0, 0, 205));
		lblkayit.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblkayit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblkayit.setBounds(300, 79, 40, 14);
		panel.add(lblkayit);

		JButton btnMerkezOku = new JButton("Merkez Oku");
		btnMerkezOku.setForeground(new Color(0, 0, 128));
		btnMerkezOku.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnMerkezOku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				merkez_oku();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);			
			}
		});
		btnMerkezOku.setBounds(147, 75, 105, 23);
		panel.add(btnMerkezOku);

		JLabel lblNewLabel = new JLabel("Satir :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(250, 79, 42, 14);
		panel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

		DefaultTableModel model = new DefaultTableModel() ;
		table = new JTable(model) ;
		table.setGridColor(oac.gridcolor);
		table.getTableHeader().setReorderingAllowed(false);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model.addColumn("Tarih",   new String []{""});
		model.addColumn("MA",new Double [] {new Double( 0 )});
		model.addColumn("MS",new Double [] {new Double( 0 )});

		TableColumn col ;
		int sut ;
		sut= 0;

		col = table.getColumnModel().getColumn(sut);
		col.setHeaderRenderer(new SOLA());
		col.setMinWidth(100);

		sut= 1;
		col = table.getColumnModel().getColumn(sut);

		col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(4) );
		col.setCellRenderer(new TABLO_RENDERER(4,false));

		sut= 2;
		col = table.getColumnModel().getColumn(sut);

		col.setHeaderRenderer(new SAGA());
		col.setCellEditor( new DoubleEditor(4) );
		col.setCellRenderer(new TABLO_RENDERER(4,false));

		JTableHeader th = table.getTableHeader();
		Dimension dd = table.getPreferredSize();
		dd.height = 30;
		th.setPreferredSize(dd); 
		th.repaint();
		table.setRowHeight(21);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);
		te_sifirla();
	}
	private void oku() 
	{
		String tar = "" ;
		String son_tar = "" ;
		tar = TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser);
		son_tar = TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_1);
		JDateChooser artan_tarih ;
		artan_tarih = new JDateChooser();
		artan_tarih.setDate(TARIH_CEVIR.tarih(tar));
		DefaultTableModel defaultModel = (DefaultTableModel) table.getModel();
		defaultModel.addRow(new Object[]{tar, 0,0});
		Date date = null;
		do
		{
			SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); 
			try {
				date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy( artan_tarih));
			} catch (ParseException e) {					}
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
			artan_tarih.setDate(new Date(cal.getTimeInMillis()));
			tar = TARIH_CEVIR.tarih_dt_ddMMyyyy(artan_tarih);
			defaultModel.addRow(new Object[]{tar, 0,0});  
		}
		while (! tar.equals( son_tar) );
		kur_oku();
	}
	private void kur_oku()
	{
		try {
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			String str= "";
			double ma ,ms ;

			for (int i = 0; i < table.getRowCount()  ; i ++)
			{
				str =  model.getValueAt(i , 0).toString();
				str = TARIH_CEVIR.tarih_sql(str) ;
				ma =kur_oku(str,cmbCins.getSelectedItem().toString(),"MA");
				ms =kur_oku(str,cmbCins.getSelectedItem().toString(),"MS");
				table.setValueAt(ma, i, 1);
				table.setValueAt(ms, i, 2);
			}
			int i = -1 ;
			DefaultTableModel modell = (DefaultTableModel)table.getModel();
			do {
				i++;
				if(i==modell.getRowCount())
				{
					break;
				}
				ma =  (double) modell.getValueAt(i , 1);
				if (  ma!=0)
				{
					modell.removeRow(i);
					i = i-1;
				}
			}
			while (i < modell.getRowCount());

			lblkayit.setText(String.valueOf(modell.getRowCount()));
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);			
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kur Okuma", JOptionPane.ERROR_MESSAGE);   		
		}
	}
	@SuppressWarnings("deprecation")
	private void te_sifirla()
	{
		GRID_TEMIZLE.grid_temizle(table);
		table.enable(false);
	}
	private void merkez_oku()
	{
		DefaultTableModel modell = (DefaultTableModel)table.getModel();
		if(modell.getRowCount() == 0)
		{
			return;
		}
		Runnable runner = new Runnable()
		{ public void run() {
			String tar = "" ;
			for (int i = 0; i < modell.getRowCount()  ; i ++)
			{
				tar =  (String) modell.getValueAt(i , 0);
				merkez(tar,i);
			}
			double kur = 0 ;
			int i = -1 ;
			do {
				i++;
				if(i==modell.getRowCount())
				{
					break;
				}
				kur =  (double) modell.getValueAt(i , 1);
				if (  kur ==0)
				{
					modell.removeRow(i);
					i = i-1;
				}
			}
			while (i < modell.getRowCount());
			lblkayit.setText(String.valueOf(modell.getRowCount()));
			Thread.currentThread().isInterrupted();
		}
		};
		Thread t = new Thread(runner, "Code Executer");
		t.start();

	}
	private void merkez (String tar,int satir)
	{
		try
		{
			String tarih = TARIH_CEVIR.tarih_sql(tar) ;
			getContentPane().setCursor(oac.WAIT_CURSOR);
			tarih  = tarih.substring(0,4) + tarih.substring(5,7) +  "/" + tarih.substring(8,10) + tarih.substring(5,7) + tarih.substring(0,4) + ".xml" ;
			Document document = Jsoup.connect("https://www.tcmb.gov.tr/kurlar/" + tarih).get();
			Elements elements = document.select("Currency");
			for( Element element : elements ) 
			{
				String KUR = element.attr("Kod");
				if (KUR.equals(cmbCins.getSelectedItem().toString()))
				{
					String ForexBuying = element.select("ForexBuying").first().text();
					String ForexSelling = element.select("ForexSelling").first().text();
					table.setValueAt( Double.parseDouble(ForexBuying), satir, 1);	
					table.setValueAt( Double.parseDouble(ForexSelling), satir, 2);	
				}
			}
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex )
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);			
		}
	}
	private double kur_oku(String tarih,String cins,String tur)
	{
		double kur =0.00 ;
		try
		{
			ResultSet rs ;
			getContentPane().setCursor(oac.WAIT_CURSOR);
			rs = k_Access.kur_oku(tarih,cins);
			if (!rs.isBeforeFirst() ) {  
				kur =0.00 ;
			} 
			else
			{
				rs.next();
				kur  = rs.getDouble(tur);
			}
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Kur Okuma", JOptionPane.ERROR_MESSAGE);
		}
		return kur;
	}
	public static void kaydet()
	{
		try
		{
			int g =  JOptionPane.showOptionDialog( null,  "Kayit Islemi Baslayacak ..?", "Kur Dosyasina Kayit",   JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,	   			 	null,   	oac.options,   	oac.options[1]); 
			if(g != 0 ) { return;	}

			String tarih = "";
			double ma,ms =0 ;
			long startTime = System.currentTimeMillis(); 
			DefaultTableModel modell = (DefaultTableModel)table.getModel();
			for (int i = 0; i < modell.getRowCount()  ; i ++)
			{
				tarih =  (String) modell.getValueAt(i , 0);
				ma =  (double) modell.getValueAt(i , 1);
				ms =  (double) modell.getValueAt(i , 2);
				kayit(tarih = TARIH_CEVIR.tarih_sql(tarih),(String) cmbCins.getSelectedItem().toString(),ma,ms);
			}

			long endTime = System.currentTimeMillis();
			long estimatedTime = endTime - startTime; 
			double seconds = (double)estimatedTime/1000; 
			OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + FORMATLAMA.doub_4(seconds) +  " saniye");

		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kur Kayit", JOptionPane.ERROR_MESSAGE);   		
		}
	}
	static void kayit(String tarih,String cins,double ma,double ms)
	{
		try
		{
			k_Access.kur_sil(tarih,cins);
			k_Access.kur_kayit(tarih,cins ,	ma,ms,0,0,0,0);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage(),  "Kur Kayit", JOptionPane.ERROR_MESSAGE);  
		}
	}
}

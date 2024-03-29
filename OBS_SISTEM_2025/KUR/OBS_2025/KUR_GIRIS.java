package OBS_2025;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import com.formdev.flatlaf.FlatClientProperties;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.GRID_TEMIZLE;
import OBS_C_2025.JTextFieldRegularPopupMenu;
import OBS_C_2025.KUR_ACCESS;
import OBS_C_2025.SAGA;
import OBS_C_2025.SOLA;
import OBS_C_2025.ScrollPaneWin11;
import OBS_C_2025.TABLO_RENDERER;
import OBS_C_2025.TARIH_CEVIR;
import net.proteanit.sql.DbUtils;
import raven.toast.Notifications;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DefaultFormatterFactory;

import javax.swing.text.NumberFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.swing.JSeparator;


@SuppressWarnings({"serial","static-access"})
public class KUR_GIRIS extends JInternalFrame {
	private static JTable table;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KUR_ACCESS k_Access = new KUR_ACCESS(OBS_SIS_2025_ANA_CLASS._IKur , OBS_SIS_2025_ANA_CLASS._IKur_Loger);

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

	public KUR_GIRIS() {
		setIconifiable(true);
		setResizable(true);
		setTitle("KUR GIRIS");
		setClosable(true);
		setBounds(0, 0, 487, 438);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(KUR_GIRIS.class.getResource("/ICONLAR/icons8-currency-exchange-30.png")), 16, 16));//
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		ScrollPaneWin11 scrollPane = new ScrollPaneWin11();
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));
		splitPane.setRightComponent(scrollPane);

		table = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		if(! oac.gridcolor.toString().equals("java.awt.Color[r=255,g=255,b=255]"))
			table.setGridColor(oac.gridcolor);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if (model.getRowCount() > 0) 
				{
					if(table.getSelectedRow() < 0) return;
					kutu_doldur(table.getSelectedRow());
				}
			}
		});
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if (model.getRowCount() > 0) 
				{
					if(table.getSelectedRow() < 0) return;
					kutu_doldur(table.getSelectedRow());
				}
			}
		});

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		splitPane.setLeftComponent(panel);
		panel.setMinimumSize(new Dimension(0, 140));
		panel.setMaximumSize(new Dimension(0, 140));
		panel.setLayout(null);

		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"USD", "EUR", "RUB", "GBR", "CHF", "SEK", "NOK", "SAR"}));
		comboBox.setEditable(true);
		comboBox.setBounds(75, 9, 67, 30);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				for (int i = 0; i <= table.getRowCount() -1; i++) {
					if(model.getValueAt(i, 0).equals(comboBox.getSelectedItem()))
					{
						sifirla();
						kutu_doldur(i);
						break;
					}
					else
						sifirla();
				}
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
			}
		});
		panel.add(comboBox);
		dateChooser = new JDateChooser();
		((JTextField)dateChooser.getDateEditor()).setBackground(oac.dtcColor);
		JCalendar qweCalendar =  dateChooser.getJCalendar();
		qweCalendar.getYearChooser().getSpinner().setBackground(oac.dtcColor);
		dateChooser.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser.setDate(new Date());
			}
		});
		dateChooser.setDateFormatString("dd.MM.yyyy");
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 16));
		dateChooser.setDate(new Date());
		dateChooser.setBounds(185, 9, 140, 30);
		dateChooser.getDateEditor().addPropertyChangeListener(
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						if ("date".equals(e.getPropertyName())) {
							getContentPane().setCursor(oac.WAIT_CURSOR);
							kur_liste();
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
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING, "Internet Baglantisi Yok ");
					return ;
				}
				getContentPane().setCursor(oac.WAIT_CURSOR);
				merkez();
				getContentPane().setCursor(oac.DEFAULT_CURSOR);			
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(369, 9, 86, 30);
		panel.add(btnNewButton);

		DefaultFormatterFactory f_dob = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(4);
		df.setMaximumFractionDigits(4);
		DefaultFormatterFactory f_dob1 = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(4);
		df.setMaximumFractionDigits(4);
		DefaultFormatterFactory f_dob2 = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(4);
		df.setMaximumFractionDigits(4);
		DefaultFormatterFactory f_dob11 = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(4);
		df.setMaximumFractionDigits(4);
		DefaultFormatterFactory f_dob3 = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(4);
		df.setMaximumFractionDigits(4);
		DefaultFormatterFactory f_dob12 = new DefaultFormatterFactory(dnff); 
		df.setMinimumFractionDigits(4);
		df.setMaximumFractionDigits(4);

		JLabel lblNewLabel = new JLabel("Alis");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 74, 55, 14);
		panel.add(lblNewLabel);

		JLabel lblSatis = new JLabel("Satis");
		lblSatis.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSatis.setBounds(10, 104, 55, 14);
		panel.add(lblSatis);

		JSeparator separator = new JSeparator();
        separator.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		separator.setBounds(10, 48, 442, 2);
		panel.add(separator);

		JButton btnNewButton_1_2 = new JButton("");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
				Date date;
				try {
					date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser));
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					cal.add(Calendar.DAY_OF_MONTH, -1);
					dateChooser.setDate(new Date(cal.getTimeInMillis()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_2.setToolTipText("Geri");
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1_2.setIcon(new ImageIcon(KUR_GIRIS.class.getResource("/ICONLAR/icons_geri-24.png")));
		btnNewButton_1_2.setBounds(152, 9, 30, 30);
		panel.add(btnNewButton_1_2);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
				Date date;
				try {
					date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser));
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
					dateChooser.setDate(new Date(cal.getTimeInMillis()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setToolTipText("Ileri");
		btnNewButton_1.setIcon(new ImageIcon(KUR_GIRIS.class.getResource("/ICONLAR/icons_ileri-24.png")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBounds(329, 9, 30, 30);
		panel.add(btnNewButton_1);

		formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(75, 75, 100, 20);
		panel.add(formattedTextField);
		formattedTextField.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		JTextFieldRegularPopupMenu.addTo(formattedTextField);
		formattedTextField.setFont(new Font("Tahoma", Font.BOLD, 12));
		formattedTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		formattedTextField.setText("0.0000");
		formattedTextField.setFormatterFactory(f_dob);

		formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(75, 100, 100, 20);
		panel.add(formattedTextField_1);
		JTextFieldRegularPopupMenu.addTo(formattedTextField_1);
		formattedTextField_1.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		formattedTextField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					formattedTextField_1.setText(formattedTextField.getText());		
			}
		});
		formattedTextField_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		formattedTextField_1.setText("0.0000");
		formattedTextField_1.setFormatterFactory(f_dob1);
		formattedTextField_1.setHorizontalAlignment(SwingConstants.RIGHT);

		formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setBounds(213, 75, 100, 20);
		panel.add(formattedTextField_2);
		JTextFieldRegularPopupMenu.addTo(formattedTextField_2);
		formattedTextField_2.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		formattedTextField_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		formattedTextField_2.setText("0.0000");
		formattedTextField_2.setFormatterFactory(f_dob2);
		formattedTextField_2.setHorizontalAlignment(SwingConstants.RIGHT);

		formattedTextField_1_1 = new JFormattedTextField();
		formattedTextField_1_1.setBounds(213, 100, 100, 20);
		panel.add(formattedTextField_1_1);
		JTextFieldRegularPopupMenu.addTo(formattedTextField_1_1);
		formattedTextField_1_1.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		formattedTextField_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					formattedTextField_1_1.setText(formattedTextField_2.getText());		
			}
		});
		formattedTextField_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		formattedTextField_1_1.setText("0.0000");
		formattedTextField_1_1.setFormatterFactory(f_dob11);
		formattedTextField_1_1.setHorizontalAlignment(SwingConstants.RIGHT);

		formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setBounds(355, 75, 100, 20);
		panel.add(formattedTextField_3);
		JTextFieldRegularPopupMenu.addTo(formattedTextField_3);
		formattedTextField_3.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		formattedTextField_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		formattedTextField_3.setText("0.0000");
		formattedTextField_3.setFormatterFactory(f_dob3);
		formattedTextField_3.setHorizontalAlignment(SwingConstants.RIGHT);

		formattedTextField_1_2 = new JFormattedTextField();
		formattedTextField_1_2.setBounds(355, 100, 100, 20);
		panel.add(formattedTextField_1_2);
		JTextFieldRegularPopupMenu.addTo(formattedTextField_1_2);
		formattedTextField_1_2.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		formattedTextField_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					formattedTextField_1_2.setText(formattedTextField_3.getText());		
			}
		});
		formattedTextField_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		formattedTextField_1_2.setText("0.0000");
		formattedTextField_1_2.setFormatterFactory(f_dob12);
		formattedTextField_1_2.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblNewLabel_1 = new JLabel("Merkez");
		lblNewLabel_1.setBounds(75, 58, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Bankalar Arası");
		lblNewLabel_2.setBounds(213, 58, 100, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Serbest");
		lblNewLabel_3.setBounds(355, 58, 46, 14);
		panel.add(lblNewLabel_3);
		kur_liste();
		kutu_doldur(0);
	}
	private static void kur_liste()
	{
		try
		{
			ResultSet rs ;
			sifirla();
			GRID_TEMIZLE.grid_temizle(table);
			rs = k_Access.kur_liste(TARIH_CEVIR.tarih_geri_SQL(dateChooser));
			if (!rs.isBeforeFirst() ) {  
				comboBox.setSelectedIndex(0);
				OBS_MAIN.lblNewLabel_9.setText("Son Raporlama Suresi : " + 0 + " saniye");
				return ;
			} 
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
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}

	private void kutu_doldur(int satir)
	{
		if( table.getRowCount()  > 0)  
		{
			comboBox.setSelectedItem(table.getValueAt(satir, 0));
			DecimalFormat decimal4 = new DecimalFormat("#,##0.0000");
			formattedTextField.setText((String.valueOf(decimal4.format(table.getValueAt(satir, 1)))));
			formattedTextField_1.setText((String.valueOf(decimal4.format(table.getValueAt(satir, 2)))));
			formattedTextField_2.setText((String.valueOf( decimal4.format(table.getValueAt(satir, 3)))));
			formattedTextField_1_1.setText((String.valueOf(decimal4.format(table.getValueAt(satir, 4)))));
			formattedTextField_3.setText((String.valueOf( decimal4.format(table.getValueAt(satir, 5)))));
			formattedTextField_1_2.setText((String.valueOf(decimal4.format(table.getValueAt(satir, 6)))));
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
			if(dateChooser.getDate() == null) return;
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
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
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
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
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

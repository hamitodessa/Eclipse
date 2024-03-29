package GUNLUK;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.text.JTextComponent;

import com.formdev.flatlaf.FlatClientProperties;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import OBS_2025.GuiUtil;
import OBS_2025.OBS_MAIN;
import OBS_2025.OBS_SIS_2025_ANA_CLASS;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.GUNLUK_ACCESS;
import OBS_C_2025.Gunluk_Bilgi;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.JTextFieldRegularPopupMenu;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.TARIH_CEVIR;
import OBS_C_2025.lOG_BILGI;
import raven.toast.Notifications;

import javax.swing.SwingConstants;

@SuppressWarnings({ "static-access","serial" })
public class GOREV_GIRIS extends JInternalFrame {
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(OBS_SIS_2025_ANA_CLASS._IGunluk , oac._IGunluk_Loger);
	private static Obs_TextFIeld txtIsim;
	private static Obs_TextFIeld txtGorev;
	private static JTextArea txtMesaj;
	public static Obs_TextFIeld txtGID;
	public static JDateChooser dtcBaslama ;
	public static JDateChooser dtcBitis;
	public static JComboBox<String> cmbBaslamaSaat ;
	public static JComboBox<String> cmbBitisSaat;
	private static JComboBox<String> cmbSecenek;
	private JLabel lblBitis;
	private static Obs_TextFIeld txtYer;
	private static Obs_TextFIeld txtDeger;
	static JPanel panel;

	public GOREV_GIRIS() {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 483, 271);
		setTitle("GOREV GIRIS");
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(GOREV_GIRIS.class.getResource("/ICONLAR/icons8-active-directory-30.png")), 16, 16));//
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));

		panel.setLayout(null);
		getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Isim");
		lblNewLabel.setBounds(10, 14, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Gorev");
		lblNewLabel_1.setBounds(10, 64, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Mesaj");
		lblNewLabel_2.setBounds(10, 85, 46, 14);
		panel.add(lblNewLabel_2);

		txtIsim = new Obs_TextFIeld(30);
		txtIsim.setBounds(100, 10, 354, 20);
		panel.add(txtIsim);
		txtIsim.setColumns(10);
		txtIsim.addAncestorListener(new AncestorListener() {
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
						txtIsim.requestFocusInWindow();
					}
				});
			}
		});


		txtGorev = new Obs_TextFIeld(30);
		txtGorev.setBounds(100, 60, 354, 20);
		panel.add(txtGorev);
		txtGorev.setColumns(10);

		txtMesaj = new JTextArea();
		txtMesaj.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		txtMesaj.setBounds(100, 85, 354, 75);
		txtMesaj.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMesaj.selectAll();
			}
		});
		txtMesaj.setFont(new Font("Monospaced", Font.BOLD, 13));
		txtMesaj.setLineWrap(true);
		txtMesaj.setDocument(new JTextFieldLimit(100));
		JTextFieldRegularPopupMenu.addTo(txtMesaj,100);
		txtMesaj.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 12), null));


		panel.add(txtMesaj);

		dtcBaslama = new JDateChooser();
		((JTextField)dtcBaslama.getDateEditor()).setBackground(oac.dtcColor);
		JCalendar qweCalendar =  dtcBaslama.getJCalendar();
		qweCalendar.getYearChooser().getSpinner().setBackground(oac.dtcColor);
		
		dtcBaslama.setBounds(100, 173, 135, 25);
		dtcBaslama.getDateEditor().getUiComponent().addFocusListener(new FocusAdapter()    {
			@Override
			public void focusGained(FocusEvent evt) {
				final JTextComponent textComponent=((JTextComponent)evt.getSource());
				SwingUtilities.invokeLater(new Runnable(){
					public void run() {
						textComponent.selectAll();
					}});
			}
		});
		dtcBaslama.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dtcBaslama.setDate(new Date());
				}
			}
		});
		dtcBaslama.getComponent(1).addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) 
			{
			}
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_DOWN) 
				{
					if (TARIH_CEVIR.tarih_dt_ddMMyyyy(dtcBaslama) == null)
					{
						return;
					}
					final JTextComponent textComponent=((JTextComponent)e.getSource());
					int currentCaretPosition = textComponent.getCaretPosition();
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtcBaslama));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						if (currentCaretPosition >=0 && currentCaretPosition <3)
							cal.add(Calendar.DAY_OF_MONTH, -1); 
						else if (currentCaretPosition >=3 && currentCaretPosition <=5)
							cal.add(Calendar.MONTH,-1);
						else if (currentCaretPosition >=6 )
							cal.add(Calendar.YEAR, -1); 
						dtcBaslama.setDate(new Date(cal.getTimeInMillis()));
						textComponent.setCaretPosition(currentCaretPosition);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					if (TARIH_CEVIR.tarih_dt_ddMMyyyy(dtcBaslama) == null)
						return;
					final JTextComponent textComponent1=((JTextComponent)e.getSource());
					int currentCaretPosition = textComponent1.getCaretPosition();

					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtcBaslama));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						if (currentCaretPosition >=0 && currentCaretPosition <3)
							cal.add(Calendar.DAY_OF_MONTH, 1); 
						else if (currentCaretPosition >=3 && currentCaretPosition <=5)
							cal.add(Calendar.MONTH,1);
						else if (currentCaretPosition >=6 )
							cal.add(Calendar.YEAR, 1); 
						dtcBaslama.setDate(new Date(cal.getTimeInMillis()));
						textComponent1.setCaretPosition(currentCaretPosition);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		dtcBaslama.setDateFormatString("dd.MM.yyyy");
		dtcBaslama.setFont(new Font("Tahoma", Font.BOLD, 14));
		dtcBaslama.setDate(new Date());

		panel.add(dtcBaslama);

		cmbBaslamaSaat = new JComboBox<String>();
		cmbBaslamaSaat.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmbBaslamaSaat.setModel(new DefaultComboBoxModel<String>(new String[] {"06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"}));
		cmbBaslamaSaat.setBounds(245, 173, 80, 25);
		panel.add(cmbBaslamaSaat);


		cmbBitisSaat = new JComboBox<String>();
		cmbBitisSaat.setModel(new DefaultComboBoxModel<String>(new String[] {"06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"}));
		cmbBitisSaat.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmbBitisSaat.setBounds(245, 200, 80, 25);
		panel.add(cmbBitisSaat);


		txtGID = new Obs_TextFIeld();
		txtGID.setBounds(10, 135, 39, 20);

		txtGID.setVisible(false);
		panel.add(txtGID);

		dtcBitis = new JDateChooser();
		((JTextField)dtcBitis.getDateEditor()).setBackground(oac.dtcColor);
		qweCalendar =  dtcBitis.getJCalendar();
		qweCalendar.getYearChooser().getSpinner().setBackground(oac.dtcColor);
		dtcBitis.setFont(new Font("Tahoma", Font.BOLD, 14));
		dtcBitis.setDateFormatString("dd.MM.yyyy");
		dtcBitis.setDate(new Date());
		dtcBitis.setBounds(100, 200, 135, 25);
		dtcBitis.getDateEditor().getUiComponent().addFocusListener(new FocusAdapter()    {
			@Override
			public void focusGained(FocusEvent evt) {
				final JTextComponent textComponent=((JTextComponent)evt.getSource());
				SwingUtilities.invokeLater(new Runnable(){
					public void run() {
						textComponent.selectAll();
					}});
			}
		});
		dtcBitis.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dtcBitis.setDate(new Date());
				}
			}
		});
		dtcBitis.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					if (TARIH_CEVIR.tarih_dt_ddMMyyyy(dtcBitis) == null)
						return;
					final JTextComponent textComponent=((JTextComponent)e.getSource());
					int currentCaretPosition = textComponent.getCaretPosition();
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtcBitis));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						if (currentCaretPosition >=0 && currentCaretPosition <3)
							cal.add(Calendar.DAY_OF_MONTH, -1); 
						else if (currentCaretPosition >=3 && currentCaretPosition <=5)
							cal.add(Calendar.MONTH,-1);
						else if (currentCaretPosition >=6 )
							cal.add(Calendar.YEAR, -1); 
						dtcBitis.setDate(new Date(cal.getTimeInMillis()));
						textComponent.setCaretPosition(currentCaretPosition);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					if (TARIH_CEVIR.tarih_dt_ddMMyyyy(dtcBitis) == null)
						return;
					final JTextComponent textComponent1=((JTextComponent)e.getSource());
					int currentCaretPosition = textComponent1.getCaretPosition();

					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtcBitis));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						if (currentCaretPosition >=0 && currentCaretPosition <3)
							cal.add(Calendar.DAY_OF_MONTH, 1); 
						else if (currentCaretPosition >=3 && currentCaretPosition <=5)
							cal.add(Calendar.MONTH,1);
						else if (currentCaretPosition >=6 )
							cal.add(Calendar.YEAR, 1); 
						dtcBitis.setDate(new Date(cal.getTimeInMillis()));
						textComponent1.setCaretPosition(currentCaretPosition);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel.add(dtcBitis);

		JLabel lblNewLabel_3 = new JLabel("Yer");
		lblNewLabel_3.setBounds(10, 39, 80, 14);
		panel.add(lblNewLabel_3);

		txtYer = new Obs_TextFIeld(30);
		
		txtYer.setBounds(100, 35, 354, 20);
		panel.add(txtYer);
		txtYer.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Baslama");
		lblNewLabel_4.setBounds(10, 177, 69, 14);
		panel.add(lblNewLabel_4);

		lblBitis = new JLabel("Bitis");
		lblBitis.setBounds(10, 205, 48, 14);
		panel.add(lblBitis);

		cmbSecenek = new JComboBox<String>();
		cmbSecenek.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmbSecenek.setModel(new DefaultComboBoxModel<String>(new String[] {"Ayda", "Haftada", "Gunde", "Saatte"}));
		cmbSecenek.setBounds(355, 173, 100, 25);
		panel.add(cmbSecenek);

		txtDeger = new Obs_TextFIeld();
		txtDeger.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDeger.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtDeger.setBounds(408, 202, 46, 20);
		panel.add(txtDeger);
		txtDeger.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Deger");
		lblNewLabel_5.setBounds(355, 205, 50, 14);
		panel.add(lblNewLabel_5);
		sifirla();
	}
	public static  void kaydet()
	{
		//
		Runnable runner = new Runnable()
		{ public void run() {
			//
			try
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ENGLISH);
				String qweString = TARIH_CEVIR.tarih_geri(dtcBaslama);
				qweString = qweString + " " + cmbBaslamaSaat.getSelectedItem().toString() ;
				Date basDate = df.parse(qweString);
				qweString = TARIH_CEVIR.tarih_geri(dtcBitis);
				qweString = qweString + " " + cmbBitisSaat.getSelectedItem().toString() ;
				Date bitDate = df.parse(qweString);
				if(bitDate.before(basDate))
				{
					OBS_MAIN.mesaj_goster(5000,Notifications.Type.WARNING,  "Bitis Tarihi Baslangic Tarihinden Kucuk Olamaz....");		
					return;
				}
				if (txtIsim.getText().equals(""))return;
				if (txtGorev.getText().equals(""))return;
				if (txtDeger.getText().equals(""))return;
				if (dtcBaslama.getDate() == null) return;
				if (dtcBitis.getDate() == null) return;
				boolean isInteger = Pattern.matches("^\\d*$", txtDeger.getText());
				if(! isInteger) return;
				String mesaj = "" ;
				mesaj = "Isim="+ txtIsim.getText() + " Gorev="+ txtGorev.getText() + 
						" Mesaj="  ;
				if( mesaj.length() +  txtMesaj.getText().length() <= 95)
					mesaj = mesaj + " Msj:" + txtMesaj.getText().toString() + " Silme " ;
				else
					mesaj = mesaj + " Msj:" + txtMesaj.getText().toString().substring(0, 89  -(mesaj.length()) ) + "Silme" ;
				if (! txtGID.getText().toString().equals(""))
				{
					int g =  JOptionPane.showOptionDialog( null,  "Gorev Onceden Kayitli Guncellenecek  ..?", "Bilgi Guncelleme",   JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,	   			 	null,   	oac.options,   	oac.options[1]); 
					if(g != 0 )
					{ 
						GuiUtil.setWaitCursor(GOREV_GIRIS.panel,false);	
						return;	
					}

					lOG_BILGI lBILGI = new lOG_BILGI();
					lBILGI.setmESAJ(mesaj);
					lBILGI.seteVRAK(txtGID.getText());
					g_Access.gorev_sil(Integer.parseInt(txtGID.getText()),lBILGI, BAGLAN_LOG.gunLogDizin  );
				}
				GuiUtil.setWaitCursor(GOREV_GIRIS.panel,true);	
				String str1 = TARIH_CEVIR.gunluk_t_ffmmyyyy(dtcBaslama) ;
				String str2 = TARIH_CEVIR.gunluk_t_ffmmyyyy(dtcBitis) ;
				Gunluk_Bilgi gbilgi = new Gunluk_Bilgi() ;
				gbilgi.tarih1 = str1;
				gbilgi.tarih2 = str2;
				gbilgi.saat1 =cmbBaslamaSaat.getSelectedItem().toString() ;
				gbilgi.saat2 =cmbBitisSaat.getSelectedItem().toString() ;
				gbilgi.isim = txtIsim.getText();
				gbilgi.gorev = txtGorev.getText();
				gbilgi.tekrarla = true;
				gbilgi.yer = txtYer.getText();
				gbilgi.mesaj = txtMesaj.getText() ;
				gbilgi.user =  GLOBAL.KULL_ADI ;
				gbilgi.secenek = cmbSecenek.getSelectedItem().toString();
				gbilgi.deger = Integer.parseInt(txtDeger.getText());
				mesaj = "" ;
				mesaj = "Isim="+ txtIsim.getText() + " Gorev="+ txtGorev.getText() + 
						" Mesaj="  ;
				if( mesaj.length() +  txtMesaj.getText().length() <= 95)
					mesaj = mesaj + " Msj:" + txtMesaj.getText().toString()  ;
				else
					mesaj = mesaj + " Msj:" + txtMesaj.getText().toString().substring(0, 89  -(mesaj.length()) ) + " Kayit" ;

				lOG_BILGI lBILGI = new lOG_BILGI();
				lBILGI.setmESAJ(mesaj);
				lBILGI.seteVRAK("");
				g_Access.gorev_kayit(gbilgi,lBILGI, BAGLAN_LOG.gunLogDizin );
				gbilgi.gid = g_Access.gid_ogren(gbilgi);
				g_Access.gunluk_farkli_kayit(gbilgi);
				sifirla();
				Thread.currentThread().isInterrupted();
				GuiUtil.setWaitCursor(GOREV_GIRIS.panel,false);	
			}
			catch (Exception ex)
			{
				GuiUtil.setWaitCursor(GOREV_GIRIS.panel,false);	
				OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
			}
			//
		}
		};
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
	public static void sil() 
	{
		if ( txtGID.getText().toString().equals(""))
			return ;
		try
		{
			int g =  JOptionPane.showOptionDialog( null,  "Islem Dosyadan Silinecek ..?", "Gunluk Dosyasindan Gorev Silme",   JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,	   			 	null,   	oac.options,   	oac.options[1]); 
			if(g != 0 ) { return;	}

			lOG_BILGI lBILGI = new lOG_BILGI();
			lBILGI.setmESAJ("Isim="+ txtIsim.getText() + " Gorev="+ txtGorev.getText() + " Mesaj="+ txtMesaj.getText() + " Silme ");
			lBILGI.seteVRAK(txtGID.getText());
			g_Access.gorev_sil(Integer.parseInt(txtGID.getText())  ,lBILGI, BAGLAN_LOG.gunLogDizin  );
			sifirla();
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR,  ex.getMessage());
		}
	}
	public static void gOKU() throws ClassNotFoundException, SQLException
	{
		Gunluk_Bilgi gbilgi = new Gunluk_Bilgi();
		gbilgi.gid = Integer.parseInt(txtGID.getText());
		ResultSet rSet = g_Access.gID_OKU(gbilgi);
		if (!rSet.isBeforeFirst() )
			sifirla();
		else
		{
			rSet.next();
			txtIsim.setText(rSet.getString("ISIM"));
			txtGorev.setText(rSet.getString("GOREV"));
			txtYer.setText(rSet.getString("YER"));
			txtMesaj.setText(rSet.getString("MESAJ"));
			dtcBaslama.setDate(rSet.getDate("BASL_TARIH"));
			dtcBitis.setDate(rSet.getDate("BIT_TARIH"));
			cmbBitisSaat.setSelectedItem(rSet.getString("BIT_SAAT"));
			cmbBaslamaSaat.setSelectedItem(rSet.getString("BASL_SAAT"));
			cmbSecenek.setSelectedItem(rSet.getString("SECENEK"));
			txtDeger.setText(Integer.toString(rSet.getInt("DEGER")));
		}
	}
	private static void sifirla()
	{
		txtGID.setText("");
		txtIsim.setText("");
		txtGorev.setText("");
		txtMesaj.setText("");
		txtYer.setText("");
		cmbBaslamaSaat.setSelectedItem("06:00");
		cmbBitisSaat.setSelectedItem("23:00");
		dtcBaslama.setDate(new Date());
		dtcBitis.setDate(new Date());
		cmbSecenek.setSelectedItem("Ayda");
		txtDeger.setText("");
	}
}

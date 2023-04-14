package GUNLUK;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import OBS_C_2025.GLOBAL;
import OBS_C_2025.GUNLUK_ACCESS;
import OBS_C_2025.Gunluk_Bilgi;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.JTextFieldRegularPopupMenu;
import OBS_C_2025.TARIH_CEVIR;

import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;

import OBS_2025.OBS_SIS_2025_ANA_CLASS;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GOREV_GIRIS extends JInternalFrame {
	private static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	@SuppressWarnings({ "unused", "static-access" })
	private static GUNLUK_ACCESS  g_Access = new GUNLUK_ACCESS(oac._IGunluk , oac._IGunluk_Loger);
	private static JTextField txtIsim;
	private static JTextField txtGorev;
	private static JTextArea txtMesaj;
	private static JTextField txtGID;
	private static JDateChooser dtc ;
	private static JDateChooser dtcBitis;
	private static JComboBox<String> cmbSaat ;
	private JTextField txtKacgun;
	private JLabel lblkacgun;
	private static JCheckBox chckbxTekrarla ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GOREV_GIRIS frame = new GOREV_GIRIS();
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
	public GOREV_GIRIS() {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 299);
		setTitle("GOREV GIRIS");

		JPanel panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Isim");
		lblNewLabel.setBounds(10, 29, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Gorev");
		lblNewLabel_1.setBounds(10, 54, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Mesaj");
		lblNewLabel_2.setBounds(10, 95, 46, 14);
		panel.add(lblNewLabel_2);

		txtIsim = new JTextField(20);
		txtIsim.setBounds(66, 26, 134, 20);
		panel.add(txtIsim);
		txtIsim.setColumns(10);

		txtGorev = new JTextField(20);
		txtGorev.setBounds(66, 51, 134, 20);
		panel.add(txtGorev);
		txtGorev.setColumns(10);

		txtMesaj = new JTextArea();
		txtMesaj.setBounds(66, 95, 319, 75);
		txtMesaj.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMesaj.selectAll();
			}
		});
		txtMesaj.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtMesaj.setLineWrap(true);
		txtMesaj.setDocument(new JTextFieldLimit(100));
		JTextFieldRegularPopupMenu.addTo(txtMesaj);
		Border borderr = BorderFactory.createLineBorder(Color.GRAY);
		txtMesaj.setBorder(BorderFactory.createCompoundBorder(borderr, BorderFactory.createEmptyBorder(2, 2, 2, 2)));

		panel.add(txtMesaj);

		dtc = new JDateChooser();
		dtc.setBounds(66, 181, 105, 20);
		dtc.getDateEditor().getUiComponent().addFocusListener(new FocusAdapter()    {
			@Override
			public void focusGained(FocusEvent evt) {
				final JTextComponent textComponent=((JTextComponent)evt.getSource());
				SwingUtilities.invokeLater(new Runnable(){
					public void run() {
						textComponent.selectAll();
					}});
			}
		});
		dtc.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dtc.setDate(new Date());
				}
			}
		});
		dtc.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					if (TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc) == null)
					{
						return;
					}
					final JTextComponent textComponent=((JTextComponent)e.getSource());
					int currentCaretPosition = textComponent.getCaretPosition();
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						if (currentCaretPosition >=0 && currentCaretPosition <3)
						{
							cal.add(Calendar.DAY_OF_MONTH, -1); 
						}
						else if (currentCaretPosition >=3 && currentCaretPosition <=5)
						{
							cal.add(Calendar.MONTH,-1);
						}
						else if (currentCaretPosition >=6 )
						{
							cal.add(Calendar.YEAR, -1); 
						}
						dtc.setDate(new Date(cal.getTimeInMillis()));
						textComponent.setCaretPosition(currentCaretPosition);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					if (TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc) == null)
					{
						return;
					}
					final JTextComponent textComponent1=((JTextComponent)e.getSource());
					int currentCaretPosition = textComponent1.getCaretPosition();

					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dtc));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						if (currentCaretPosition >=0 && currentCaretPosition <3)
						{
							cal.add(Calendar.DAY_OF_MONTH, 1); 
						}
						else if (currentCaretPosition >=3 && currentCaretPosition <=5)
						{
							cal.add(Calendar.MONTH,1);
						}
						else if (currentCaretPosition >=6 )
						{
							cal.add(Calendar.YEAR, 1); 
						}
						dtc.setDate(new Date(cal.getTimeInMillis()));
						textComponent1.setCaretPosition(currentCaretPosition);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		dtc.setDateFormatString("dd.MM.yyyy");
		dtc.setFont(new Font("Tahoma", Font.BOLD, 11));
		dtc.setDate(new Date());

		panel.add(dtc);

		cmbSaat = new JComboBox<String>();
		cmbSaat.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbSaat.setModel(new DefaultComboBoxModel<String>(new String[] {"06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"}));
		cmbSaat.setBounds(66, 211, 80, 22);
		panel.add(cmbSaat);

		txtGID = new JTextField();
		txtGID.setBounds(210, 26, 39, 20);

		txtGID.setVisible(false);
		panel.add(txtGID);
		
		chckbxTekrarla = new JCheckBox("Tekrarla");
		chckbxTekrarla.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxTekrarla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxTekrarla.isSelected())
				{
					dtcBitis.setVisible(true);
					txtKacgun.setVisible(true);
					lblkacgun.setVisible(true);
				}
				else
				{
					dtcBitis.setVisible(false);
					txtKacgun.setVisible(false);
					lblkacgun.setVisible(false);
				}
				
			}
		});
		chckbxTekrarla.setBounds(289, 178, 99, 23);
		panel.add(chckbxTekrarla);
		
		dtcBitis = new JDateChooser();
		dtcBitis.setFont(new Font("Tahoma", Font.BOLD, 11));
		dtcBitis.setDateFormatString("dd.MM.yyyy");
		dtcBitis.setDate(new Date());
		dtcBitis.setBounds(280, 239, 105, 20);
		dtcBitis.setVisible(false);
		panel.add(dtcBitis);
		
		txtKacgun = new JTextField();
		txtKacgun.setBounds(330, 212, 55, 20);
		txtKacgun.setVisible(false);
		panel.add(txtKacgun);
		txtKacgun.setColumns(3);
		
		lblkacgun = new JLabel("Kac Gunde ");
		lblkacgun.setBounds(250, 215, 70, 14);
		lblkacgun.setVisible(false);
		panel.add(lblkacgun);
		
		
		
		sifirla();

	}
	public static  void kaydet()
	{
		try
		{
			if (! txtGID.getText().toString().equals(""))
			{
				int g =  JOptionPane.showOptionDialog( null,  "Gorev Onceden Kayitli Guncellenecek  ..?", "Bilgi Guncelleme",   JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,	   			 	null,   	oac.options,   	oac.options[1]); 
				if(g != 0 ) { return;	}
				g_Access.gorev_sil(Integer.parseInt(txtGID.getText())  );
			}
			String str = TARIH_CEVIR.tarih_geri_saatli(dtc) ;
			Gunluk_Bilgi gbilgi = new Gunluk_Bilgi() ;
			
			gbilgi.tarih = str;
			gbilgi.saat =cmbSaat.getSelectedItem().toString() ;
			gbilgi.isim = txtIsim.getText();
			gbilgi.gorev = txtGorev.getText();
			gbilgi.mesaj = txtMesaj.getText() ;
			gbilgi.user =  GLOBAL.KULL_ADI ;
			
			if(chckbxTekrarla.isSelected())
			{
				
			}
			g_Access.gorev_kayit(gbilgi);
			sifirla();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Gorev  Kaydetme", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void sil() 
	{
		if ( txtGID.getText().toString().equals(""))
		{
			return ;
		}
		try
		{
			int g =  JOptionPane.showOptionDialog( null,  "Islem Dosyadan Silinecek ..?", "Gunluk Dosyasindan Gorev Silme",   JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,	   			 	null,   	oac.options,   	oac.options[1]); 
			if(g != 0 ) { return;	}
			g_Access.gorev_sil(Integer.parseInt(txtGID.getText())  );
			sifirla();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,  ex.getMessage(), "Gorev  Kaydetme", JOptionPane.ERROR_MESSAGE);
		}
	}
	private static void sifirla()
	{
		txtGID.setText("");
		txtIsim.setText("");
		txtGorev.setText("");
		txtMesaj.setText("");
		cmbSaat.setSelectedItem("06:00");
		dtc.setDate(new Date());
	}
}

package OBS_2025;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.Obs_TextFIeld;
import OBS_C_2025.TARIH_CEVIR;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Cursor;

import javax.swing.JButton;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class DOKUM_AKTAR extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private Obs_TextFIeld textDosya;
	private Obs_TextFIeld textPort;
	private Obs_TextFIeld textInstance;
	private Obs_TextFIeld textIp;
	private Obs_TextFIeld textPassword;
	private Obs_TextFIeld textUser;
	private Obs_TextFIeld textCKodu;

	private JCheckBox msLokal;
	private JButton btnAktar;
	
	Connection MS_conn = null;  
	
	public DOKUM_AKTAR() {
		setBounds(100, 100, 510, 262);
		setTitle("DOKUM AKTAR");
		setClosable(true);
		setFrameIcon(FIT_IMAGE.formIcon(new ImageIcon(MSSQL_TO_MYSQL.class.getResource("/ICONLAR/transfer-16.png")), 16, 16));//

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),  "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblAlinacakDosya = new JLabel("Alinacak Dosya");
		lblAlinacakDosya.setBounds(10, 25, 97, 14);
		panel.add(lblAlinacakDosya);
		
		textDosya = new Obs_TextFIeld();
		textDosya.setText("019");
		textDosya.setColumns(10);
		textDosya.setBounds(107, 22, 64, 20);
		panel.add(textDosya);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Baglanti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(215, 11, 269, 204);
		panel.add(panel_3);
		
		msLokal = new JCheckBox("Lokal");
		msLokal.setSelected(true);
		msLokal.setBounds(10, 15, 97, 23);
		panel_3.add(msLokal);
		
		JLabel lblNewLabel_2 = new JLabel("Kullanici");
		lblNewLabel_2.setBounds(10, 44, 80, 14);
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sifre");
		lblNewLabel_3.setBounds(10, 69, 80, 14);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Server");
		lblNewLabel_4.setBounds(10, 94, 80, 14);
		panel_3.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Instance");
		lblNewLabel_5.setBounds(10, 119, 80, 14);
		panel_3.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Port");
		lblNewLabel_6.setBounds(10, 144, 80, 14);
		panel_3.add(lblNewLabel_6);
		
		textPort = new Obs_TextFIeld();
		textPort.setColumns(10);
		textPort.setBounds(100, 140, 159, 20);
		panel_3.add(textPort);
		
		textInstance = new Obs_TextFIeld();
		textInstance.setText("SQLEXPRESS");
		textInstance.setColumns(10);
		textInstance.setBounds(100, 115, 159, 20);
		panel_3.add(textInstance);
		
		textIp = new Obs_TextFIeld();
		textIp.setText("78.189.76.247:1433");
		textIp.setColumns(10);
		textIp.setBounds(100, 90, 159, 20);
		panel_3.add(textIp);
		
		textPassword = new Obs_TextFIeld();
		textPassword.setText("197227oOk");
		textPassword.setColumns(10);
		textPassword.setBounds(100, 65, 159, 20);
		panel_3.add(textPassword);
		
		textUser = new Obs_TextFIeld();
		textUser.setText("sa");
		textUser.setColumns(10);
		textUser.setBounds(100, 40, 159, 20);
		panel_3.add(textUser);
		
		JButton btnNewButton = new JButton("Baglan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dosBAGLAN();
			}
		});
		btnNewButton.setBounds(100, 170, 159, 23);
		panel_3.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Hesap");
		lblNewLabel.setBounds(10, 64, 55, 14);
		panel.add(lblNewLabel);
		
		textCKodu = new Obs_TextFIeld();
		textCKodu.setBounds(75, 61, 118, 20);
		panel.add(textCKodu);
		textCKodu.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Ilk Tarih");
		lblNewLabel_1.setBounds(10, 101, 64, 14);
		panel.add(lblNewLabel_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(75, 95, 118, 20);
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
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser.setDateFormatString("dd.MM.yyyy");
		dateChooser.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		dateChooser.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
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
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
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
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel.add(dateChooser);
		
		JLabel lblNewLabel_7 = new JLabel("Son Tarih");
		lblNewLabel_7.setBounds(10, 132, 64, 14);
		panel.add(lblNewLabel_7);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(75, 126, 118, 20);
		((JTextField)dateChooser_1.getDateEditor()).setBackground(oac.dtcColor);
		qweCalendar =  dateChooser_1.getJCalendar();
		qweCalendar.getYearChooser().getSpinner().setBackground(oac.dtcColor);
		dateChooser_1.setDate(TARIH_CEVIR.tarih("31.12.2100"));
		dateChooser_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser_1.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					dateChooser_1.setDate(new Date());
			}
		});
		dateChooser_1.getComponent(1).addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_1));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, -1); 
						dateChooser_1.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else if(e.getKeyCode()==KeyEvent.VK_UP) {
					SimpleDateFormat datefmt = new SimpleDateFormat("dd.MM.yyyy"); // Or format you're using
					Date date;
					try {
						date = datefmt.parse(TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser_1));
						Calendar cal = Calendar.getInstance();
						cal.setTime(date);
						cal.add(Calendar.DAY_OF_MONTH, 1); // Add 30 days
						dateChooser_1.setDate(new Date(cal.getTimeInMillis()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel.add(dateChooser_1);
		
		btnAktar = new JButton("Aktar");
		btnAktar.setEnabled(false);
		btnAktar.setBounds(75, 188, 111, 23);
		panel.add(btnAktar);

	}
	private void dosBAGLAN()
	{
		if (textDosya.getText().equals("")) return;
		if (textUser.getText().equals("")) return;
		if (textPassword.getText().equals("")) return;
		//if (textIp.getText().equals("")) return;
		//if (textInstance.getText().equals("")) return;
		//if (textPort.getText().equals("")) return;
		try {
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			if( BAGLAN.cariDizin.hAN_SQL.equals("MS SQL"))
			{
				String cumle = "";
				String serverString = "" ;
				String port = "" ;
				if (msLokal.isSelected())
				{
					if ( ! textPort.getText().toString().equals(""))
						port =  ":" + textPort.getText() ;
					serverString = "localhost" + port ;
				}
				else
					serverString = textIp.getText()  ;	
				cumle = "jdbc:sqlserver://" + serverString + ";instanceName=" + textInstance.getText() + ";database=OK_Car" + textDosya.getText() ;
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				MS_conn = DriverManager.getConnection(cumle,textUser.getText(),textPassword.getText()); 
				System.out.println("308");
				btnAktar.setEnabled(true);
			}
			else 
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				String serverString = "" ;
				if (msLokal.isSelected())
					serverString = "localhost:" + textPort.getText() ;
				else
					serverString = textIp.getText() +":" + textPort.getText() ;	
				String url = "jdbc:mysql://"+ serverString +"/ok_car" +  textDosya.getText()  ; 
				MS_conn = DriverManager.getConnection(url, textUser.getText(),textPassword.getText());
				btnAktar.setEnabled(true);
			}
			JOptionPane.showMessageDialog(null,"Baglanti Saglandi", "Veritabani Baglanma", JOptionPane.INFORMATION_MESSAGE);
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		} catch (Exception e) {
			getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			JOptionPane.showMessageDialog(null,"Baglanti Hata", "Veritabani Baglanma", JOptionPane.ERROR_MESSAGE);
			btnAktar.setEnabled(false);
		}
	}
}

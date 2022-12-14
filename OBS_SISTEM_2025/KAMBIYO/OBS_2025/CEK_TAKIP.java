package OBS_2025;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import OBS_C_2025.FORMATLAMA;
import OBS_C_2025.JTextFieldLimit;
import OBS_C_2025.KAMBIYO_ACCESS;
import OBS_C_2025.TARIH_CEVIR;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CEK_TAKIP extends JInternalFrame {
	private static JTextField textField;
	private static JLabel lblNewLabel_10 ;
	private static JLabel lblNewLabel_11 ;
	private static JLabel lblNewLabel_12 ;
	private static JLabel lblNewLabel_13 ;
	private static JLabel lblNewLabel_14 ;
	private static JLabel lblNewLabel_15 ;
	private static JLabel lblNewLabel_16 ;
	private static JLabel lblNewLabel_17 ;
	private static JLabel lblNewLabel_18 ;
	private static JLabel lblNewLabel_20 ;
	private static JLabel lblNewLabel_22 ;
	private static JLabel lblNewLabel_25 ;
	private static JLabel label ;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;
	private JDateChooser dateChooser_2;
	private static JDateChooser dateChooser_3;
	private static JComboBox<String> comboBox ;


	Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static KAMBIYO_ACCESS ka_Access = new KAMBIYO_ACCESS(oac._IKambiyo , OBS_SIS_2025_ANA_CLASS._IKambiyo_Loger);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CEK_TAKIP frame = new CEK_TAKIP();
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
	public CEK_TAKIP() {
		setTitle("CEK TAKIP");
		setClosable(true);
		setBounds(100, 100, 614, 395);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cek No", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1.setBounds(10, 11, 587, 59);
		panel.add(panel_1);
		panel_1.setLayout(null);

		textField = new JTextField();
		textField.setBounds(99, 20, 137, 20);
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));

		textField.setDocument(new JTextFieldLimit(10));
		textField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				getContentPane().setCursor(WAIT_CURSOR);
				kontrol();
				getContentPane().setCursor(DEFAULT_CURSOR);
			}
			public void removeUpdate(DocumentEvent e) {
				getContentPane().setCursor(WAIT_CURSOR);
				kontrol();
				getContentPane().setCursor(DEFAULT_CURSOR);
			}
			public void insertUpdate(DocumentEvent e) {
				getContentPane().setCursor(WAIT_CURSOR);
				kontrol();
				getContentPane().setCursor(DEFAULT_CURSOR);
			}
		});
		panel_1.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Cek No");
		lblNewLabel.setBounds(10, 91, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Cikis Bordro");
		lblNewLabel_1.setBounds(10, 116, 89, 14);
		panel.add(lblNewLabel_1);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(131, 138, 130, 22);
		dateChooser.setDateFormatString("dd.MM.yyyy");
		dateChooser.setEnabled(false);
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(dateChooser);

		JLabel lblNewLabel_2 = new JLabel("Cikis Tarihi");
		lblNewLabel_2.setBounds(10, 146, 89, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Giris Bordro");
		lblNewLabel_2_1.setBounds(10, 174, 89, 14);
		panel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("Giris Tarihi");
		lblNewLabel_2_2.setBounds(10, 204, 89, 14);
		panel.add(lblNewLabel_2_2);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(131, 196, 130, 22);
		dateChooser_1.setDateFormatString("dd.MM.yyyy");
		dateChooser_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		dateChooser_1.setEnabled(false);
		panel.add(dateChooser_1);

		JLabel lblNewLabel_3 = new JLabel("Seri no");
		lblNewLabel_3.setBounds(10, 232, 89, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Cek Hesap No");
		lblNewLabel_4.setBounds(10, 257, 111, 14);
		panel.add(lblNewLabel_4);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Islem", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.textHighlight));
		panel_1_1.setBounds(10, 301, 587, 59);
		panel.add(panel_1_1);

		dateChooser_3 = new JDateChooser();
		dateChooser_3.getComponent(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) 
				{
					dateChooser_3.setDate(new Date());
				}
			}
		});
		dateChooser_3.setBounds(88, 20, 127, 20);
		dateChooser_3.setDateFormatString("dd.MM.yyyy");
		dateChooser_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1_1.add(dateChooser_3);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"", "1-Iade", "2-Protesto", "3-Tahsil"}));
		comboBox.setBounds(320, 18, 106, 22);
		panel_1_1.add(comboBox);

		JLabel lblNewLabel_5 = new JLabel("Banka");
		lblNewLabel_5.setBounds(282, 91, 79, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Cikis Hes Kod");
		lblNewLabel_6.setBounds(282, 116, 89, 14);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Vade");
		lblNewLabel_7.setBounds(282, 146, 79, 14);
		panel.add(lblNewLabel_7);

		dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(371, 138, 130, 22);
		dateChooser_2.setDateFormatString("dd.MM.yyyy");
		dateChooser_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		dateChooser_2.setEnabled(false);
		panel.add(dateChooser_2);

		JLabel lblNewLabel_8 = new JLabel("Giris Hes Kod");
		lblNewLabel_8.setBounds(282, 174, 79, 14);
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Sube");
		lblNewLabel_9.setBounds(282, 204, 79, 14);
		panel.add(lblNewLabel_9);

		lblNewLabel_10 = new JLabel("...");
		lblNewLabel_10.setBounds(131, 91, 111, 14);
		panel.add(lblNewLabel_10);

		lblNewLabel_11 = new JLabel("...");
		lblNewLabel_11.setBounds(131, 116, 130, 14);
		panel.add(lblNewLabel_11);

		lblNewLabel_12 = new JLabel("...");
		lblNewLabel_12.setBounds(131, 174, 130, 14);
		panel.add(lblNewLabel_12);

		lblNewLabel_13 = new JLabel("...");
		lblNewLabel_13.setBounds(371, 91, 202, 14);
		panel.add(lblNewLabel_13);

		lblNewLabel_14 = new JLabel("...");
		lblNewLabel_14.setBounds(131, 232, 130, 14);
		panel.add(lblNewLabel_14);

		lblNewLabel_15 = new JLabel("...");
		lblNewLabel_15.setBounds(131, 257, 130, 14);
		panel.add(lblNewLabel_15);

		lblNewLabel_16 = new JLabel("...");
		lblNewLabel_16.setBounds(371, 116, 152, 14);
		panel.add(lblNewLabel_16);

		lblNewLabel_17 = new JLabel("...");
		lblNewLabel_17.setBounds(371, 174, 130, 14);
		panel.add(lblNewLabel_17);

		lblNewLabel_18 = new JLabel("...");
		lblNewLabel_18.setBounds(371, 204, 152, 14);
		panel.add(lblNewLabel_18);

		JLabel lblNewLabel_19 = new JLabel("Ilk Borclu");
		lblNewLabel_19.setBounds(282, 232, 79, 14);
		panel.add(lblNewLabel_19);

		lblNewLabel_20 = new JLabel("...");
		lblNewLabel_20.setBounds(371, 232, 152, 14);
		panel.add(lblNewLabel_20);

		JLabel lblNewLabel_21 = new JLabel("Tutar");
		lblNewLabel_21.setBounds(282, 257, 79, 14);
		panel.add(lblNewLabel_21);

		lblNewLabel_22 = new JLabel("0.00");
		lblNewLabel_22.setForeground(Color.BLUE);
		lblNewLabel_22.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_22.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_22.setBounds(371, 257, 130, 14);
		panel.add(lblNewLabel_22);

		JLabel lblNewLabel_23 = new JLabel("Giris Oz Kod");
		lblNewLabel_23.setBounds(10, 282, 89, 14);
		panel.add(lblNewLabel_23);

		label = new JLabel("...");
		label.setBounds(131, 282, 130, 14);
		panel.add(label);

		JLabel lblNewLabel_24 = new JLabel("Cikis Oz Kod");
		lblNewLabel_24.setBounds(282, 282, 79, 14);
		panel.add(lblNewLabel_24);

		lblNewLabel_25 = new JLabel("...");
		lblNewLabel_25.setBounds(371, 282, 152, 14);
		panel.add(lblNewLabel_25);

	}
	private void kontrol()
	{
		try
		{
			ResultSet rs = null ;
			getContentPane().setCursor(WAIT_CURSOR);
			rs= ka_Access.cek_kontrol(textField.getText());
			if (!rs.isBeforeFirst() ) {  
				getContentPane().setCursor(DEFAULT_CURSOR);
				temizle();
			} 
			else
			{
				rs.next();
				lblNewLabel_10.setText(rs.getString("Cek_No").toString() );
				lblNewLabel_11.setText(rs.getString("Cikis_Bordro").toString() );
				lblNewLabel_12.setText(rs.getString("Giris_Bordro").toString() );
				lblNewLabel_13.setText(rs.getString("Banka").toString() );
				lblNewLabel_14.setText(rs.getString("Seri_No").toString() );
				lblNewLabel_15.setText(rs.getString("Cek_Hesap_No").toString() );
				lblNewLabel_16.setText(rs.getString("Cikis_Musteri").toString() );
				lblNewLabel_17.setText(rs.getString("Giris_Musteri").toString() );
				lblNewLabel_18.setText(rs.getString("Sube").toString() );
				lblNewLabel_20.setText(rs.getString("Ilk_Borclu").toString() );
				lblNewLabel_22.setText(FORMATLAMA.doub_2(rs.getDouble("Tutar")) );

				dateChooser.setDate(rs.getDate("Cikis_Tarihi"));   // Cikis Tarihi
				dateChooser_2.setDate(rs.getDate("Vade")); // Vade
				dateChooser_1.setDate(rs.getDate("Giris_Tarihi")); // Giris tarihi

				if (! rs.getDate("T_Tarih").toString().equals("1900-01-01"))
				{
					dateChooser_3.setDate(rs.getDate("T_Tarih")); // T tarihi
				}
				else
				{
					dateChooser_3.setDate(null); // T tarihi
				}

				label.setText(rs.getString("Giris_Ozel_Kod").toString() );
				lblNewLabel_25.setText(rs.getString("Cikis_Ozel_Kod").toString() );
				if (rs.getString("Durum").equals("1"))
				{
					comboBox.setSelectedItem("1-Iade");
				}
				else if (rs.getString("Durum").equals("2"))
				{
					comboBox.setSelectedItem("2-Protesto");
				}
				else if (rs.getString("Durum").equals("3"))
				{
					comboBox.setSelectedItem("3-Tahsil");
				}
				else  
				{
					comboBox.setSelectedItem("");
				}


			}
			getContentPane().setCursor(DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			getContentPane().setCursor(DEFAULT_CURSOR);
			JOptionPane.showMessageDialog(null,ex.getMessage(), "Cek Takip", JOptionPane.PLAIN_MESSAGE);
		}

	}
	public static void kaydet()
	{
		try
		{
			String durum = "" ;

			if (comboBox.getSelectedIndex() == 1)
			{
				durum= "1";
			}
			else if (comboBox.getSelectedIndex() == 2)
			{
				durum= "2";
			}
			else if (comboBox.getSelectedIndex() == 3)
			{
				durum= "3";
			}
			else  
			{
				durum= "";
			}

			ka_Access.kam_durum_yaz(textField.getText(), "cek", "Cek_No", durum,  TARIH_CEVIR.tarih_geri(dateChooser_3));
			textField.setText("");
			textField.requestFocus();
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage(), "Cek Takip", JOptionPane.PLAIN_MESSAGE);
		}
	}
	private void temizle()
	{
		lblNewLabel_10.setText("" );
		lblNewLabel_11.setText("" );
		lblNewLabel_12.setText("" );
		lblNewLabel_13.setText("" );
		lblNewLabel_14.setText("" );
		lblNewLabel_15.setText("" );
		lblNewLabel_16.setText("" );
		lblNewLabel_17.setText("" );
		lblNewLabel_18.setText("" );
		lblNewLabel_20.setText("" );
		lblNewLabel_22.setText("0.00" );
		dateChooser.setDate(null);   // Cikis Tarihi
		dateChooser_2.setDate(null); // Vade
		dateChooser_1.setDate(null); // Giris tarihi
		dateChooser_3.setDate(null); // Giris tarihi
		label.setText("" );
		lblNewLabel_25.setText("" );
		comboBox.setSelectedItem("");
	}
}

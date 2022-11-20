package OBS_2025;

import java.awt.EventQueue;
import java.sql.ResultSet;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import OBS_C_2025.GLOBAL;
import OBS_C_2025.MAIL_SETTINGS;

import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Color;

public class E_MAIL_BILGILERI extends JInternalFrame {
	private static JTextField txtmail;
	private static JTextField txthost;
	private static JTextField txtport;
	private static JTextField txtsifre;
	private static JTextField txtgonhesap;
	private static JTextField txtgonisim;
	private static JCheckBox chckbxNewCheckBox_1;
	private static JCheckBox chckbxNewCheckBox;
	private static JPanel panel ;
	
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					E_MAIL_BILGILERI frame = new E_MAIL_BILGILERI();
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
	public E_MAIL_BILGILERI() {
		setTitle("E MAIL BILGILERI");
		setClosable(true);
		setBounds(100, 100, 492, 289);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mail");
		lblNewLabel.setBounds(45, 31, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Host");
		lblNewLabel_1.setBounds(45, 56, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Port");
		lblNewLabel_2.setBounds(45, 81, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sifre");
		lblNewLabel_3.setBounds(45, 106, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("SSL");
		lblNewLabel_4.setBounds(45, 131, 46, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("TSL");
		lblNewLabel_5.setBounds(45, 156, 46, 14);
		panel.add(lblNewLabel_5);
		
		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(133, 127, 97, 23);
		panel.add(chckbxNewCheckBox);
		
		chckbxNewCheckBox_1 = new JCheckBox("");
		chckbxNewCheckBox_1.setBounds(133, 152, 97, 23);
		panel.add(chckbxNewCheckBox_1);
		
		txtmail = new JTextField();
		txtmail.setForeground(new Color(0, 0, 128));
		txtmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtmail.setBounds(133, 28, 315, 20);
		panel.add(txtmail);
		txtmail.setColumns(10);
		
		txthost = new JTextField();
		txthost.setForeground(new Color(0, 0, 128));
		txthost.setFont(new Font("Tahoma", Font.BOLD, 12));
		txthost.setBounds(133, 53, 315, 20);
		panel.add(txthost);
		txthost.setColumns(10);
		
		txtport = new JTextField();
		txtport.setForeground(new Color(0, 0, 128));
		txtport.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtport.setBounds(133, 78, 61, 20);
		panel.add(txtport);
		txtport.setColumns(10);
		
		txtsifre = new JTextField();
		txtsifre.setForeground(new Color(0, 0, 128));
		txtsifre.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtsifre.setBounds(133, 103, 197, 20);
		panel.add(txtsifre);
		txtsifre.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Hesap");
		lblNewLabel_6.setBounds(45, 197, 46, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Isim");
		lblNewLabel_7.setBounds(45, 224, 46, 14);
		panel.add(lblNewLabel_7);
		
		txtgonhesap = new JTextField();
		txtgonhesap.setForeground(new Color(0, 0, 128));
		txtgonhesap.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtgonhesap.setBounds(133, 194, 315, 20);
		panel.add(txtgonhesap);
		txtgonhesap.setColumns(10);
		
		txtgonisim = new JTextField();
		txtgonisim.setForeground(new Color(0, 0, 128));
		txtgonisim.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtgonisim.setBounds(133, 221, 315, 20);
		panel.add(txtgonisim);
		txtgonisim.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(135, 206, 235));
		separator.setBounds(45, 181, 403, 2);
		panel.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(465, 176, -17, -163);
		panel.add(separator_2);
		
		
			getContentPane().setCursor(oac.WAIT_CURSOR);
			doldur();
			getContentPane().setCursor(oac.DEFAULT_CURSOR);
	}
	private void doldur() 
	{
		try {
		oac.uSER_ISL.mail_bak();
		  txtmail.setText(MAIL_SETTINGS.HESAP);
          txthost.setText(MAIL_SETTINGS.HOST);
          txtport.setText(MAIL_SETTINGS.PORT);
          txtsifre.setText(MAIL_SETTINGS.PWD);
          txtgonhesap.setText(MAIL_SETTINGS.GHESAP);
          txtgonisim.setText(MAIL_SETTINGS.GADI);
         // 
      chckbxNewCheckBox.setSelected( ((boolean) MAIL_SETTINGS.SSL  ? true : false ));
       chckbxNewCheckBox_1.setSelected((boolean) (MAIL_SETTINGS.TSL ? true : false ));
		}
		catch (Exception ex)
		{
			  JOptionPane.showMessageDialog(null, ex.getMessage(), "Mail Bilgi Kayit", JOptionPane.WARNING_MESSAGE);
		}
	}
	public static void kayit () 
	{
		try
		{
		panel.setCursor(oac.WAIT_CURSOR);
		oac.uSER_ISL.mail_yaz(txtmail.getText(), txthost.getText(), txtport.getText(),txtsifre.getText(), 
		txtgonhesap.getText(),txtgonisim.getText(), chckbxNewCheckBox.isSelected() ? 1 : -1 , chckbxNewCheckBox_1.isSelected() ? 1 : -1);
		panel.setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			panel.setCursor(oac.DEFAULT_CURSOR);
       	    JOptionPane.showMessageDialog(null, ex.getMessage(), "Mail Bilgi Kayit", JOptionPane.WARNING_MESSAGE);
		}
	}
}

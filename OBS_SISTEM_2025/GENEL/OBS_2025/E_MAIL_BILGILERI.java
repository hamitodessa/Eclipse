package OBS_2025;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import OBS_C_2025.MAIL_SETTINGS;
import raven.toast.Notifications;

import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;

@SuppressWarnings({"serial","static-access"})
public class E_MAIL_BILGILERI extends JInternalFrame {
	private static JTextField txtmail;
	private static JTextField txthost;
	private static JTextField txtport;
	private static JTextField txtgonhesap;
	private static JTextField txtgonisim;
	private static JCheckBox chckbxNewCheckBox_1;
	private static JCheckBox chckbxNewCheckBox;
	private static JPanel panel ;

	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	private static JPasswordField passwordField;
	public E_MAIL_BILGILERI() {
		setTitle("E MAIL BILGILERI");
		setClosable(true);
		setBounds(100, 100, 492, 249);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mail");
		lblNewLabel.setBounds(39, 14, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Host");
		lblNewLabel_1.setBounds(39, 39, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Port");
		lblNewLabel_2.setBounds(39, 64, 46, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Sifre");
		lblNewLabel_3.setBounds(39, 89, 46, 14);
		panel.add(lblNewLabel_3);

		chckbxNewCheckBox = new JCheckBox("SSL");
		chckbxNewCheckBox.setBounds(127, 110, 97, 23);
		panel.add(chckbxNewCheckBox);

		chckbxNewCheckBox_1 = new JCheckBox("TSL");
		chckbxNewCheckBox_1.setBounds(309, 110, 97, 23);
		panel.add(chckbxNewCheckBox_1);

		txtmail = new JTextField();
		txtmail.setForeground(new Color(0, 0, 128));
		txtmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtmail.setBounds(127, 11, 315, 20);
		panel.add(txtmail);
		txtmail.setColumns(10);

		txthost = new JTextField();
		txthost.setForeground(new Color(0, 0, 128));
		txthost.setFont(new Font("Tahoma", Font.BOLD, 12));
		txthost.setBounds(127, 36, 315, 20);
		panel.add(txthost);
		txthost.setColumns(10);

		txtport = new JTextField();
		txtport.setForeground(new Color(0, 0, 128));
		txtport.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtport.setBounds(127, 61, 61, 20);
		panel.add(txtport);
		txtport.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Hesap");
		lblNewLabel_6.setBounds(39, 156, 46, 14);
		panel.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Isim");
		lblNewLabel_7.setBounds(39, 183, 46, 14);
		panel.add(lblNewLabel_7);

		txtgonhesap = new JTextField();
		txtgonhesap.setForeground(new Color(0, 0, 128));
		txtgonhesap.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtgonhesap.setBounds(127, 153, 315, 20);
		panel.add(txtgonhesap);
		txtgonhesap.setColumns(10);

		txtgonisim = new JTextField();
		txtgonisim.setForeground(new Color(0, 0, 128));
		txtgonisim.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtgonisim.setBounds(127, 180, 315, 20);
		panel.add(txtgonisim);
		txtgonisim.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(135, 206, 235));
		separator.setBounds(39, 140, 403, 2);
		panel.add(separator);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(465, 176, -17, -163);
		panel.add(separator_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(127, 86, 279, 20);
		panel.add(passwordField);


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
			txtgonhesap.setText(MAIL_SETTINGS.GHESAP);
			txtgonisim.setText(MAIL_SETTINGS.GADI);
			passwordField.setText(MAIL_SETTINGS.PWD);
			chckbxNewCheckBox.setSelected( ((boolean) MAIL_SETTINGS.SSL  ? true : false ));
			chckbxNewCheckBox_1.setSelected((boolean) (MAIL_SETTINGS.TSL ? true : false ));
		}
		catch (Exception ex)
		{
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
			//JOptionPane.showMessageDialog(null, ex.getMessage(), "Mail Bilgi Kayit", JOptionPane.WARNING_MESSAGE);
		}
	}
	public static void kayit () 
	{
		try
		{
			panel.setCursor(oac.WAIT_CURSOR);
			oac.uSER_ISL.mail_yaz(txtmail.getText(), txthost.getText(), txtport.getText(),oac.sDONDUR.sDONDUR(passwordField), 
					txtgonhesap.getText(),txtgonisim.getText(), chckbxNewCheckBox.isSelected() ? 1 : -1 , chckbxNewCheckBox_1.isSelected() ? 1 : -1);
			panel.setCursor(oac.DEFAULT_CURSOR);
		}
		catch (Exception ex)
		{
			panel.setCursor(oac.DEFAULT_CURSOR);
			OBS_MAIN.mesaj_goster(5000,Notifications.Type.ERROR, ex.getMessage() );
			//JOptionPane.showMessageDialog(null, ex.getMessage(), "Mail Bilgi Kayit", JOptionPane.WARNING_MESSAGE);
		}
	}
}

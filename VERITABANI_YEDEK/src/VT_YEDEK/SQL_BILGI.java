package VT_YEDEK;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class SQL_BILGI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JPasswordField passwordField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SQL_BILGI frame = new SQL_BILGI();
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
	public SQL_BILGI() {
		setTitle("SQL SERVER BAGLANTI");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		tabbedPane.addTab("MS SQL", null, panel, null);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setEditable(true);
		comboBox.setBounds(72, 25, 260, 22);
		panel.add(comboBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Baglanti Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(72, 58, 372, 171);
		panel_2.setLayout(null);
		panel.add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Kullanici");
		lblNewLabel.setBounds(10, 50, 74, 14);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sifre");
		lblNewLabel_1.setBounds(10, 82, 46, 14);
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(98, 47, 130, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(98, 79, 130, 20);
		panel_2.add(passwordField);
		
		JButton btnNewButton = new JButton("Baglanti Test");
		btnNewButton.setBounds(98, 117, 130, 23);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Kaydet");
		btnNewButton_1.setBounds(228, 240, 89, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Vazgec");
		btnNewButton_2.setBounds(355, 240, 89, 23);
		panel.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("MY SQL", null, panel_1, null);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(null, "Baglanti Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2_1.setBounds(72, 58, 372, 171);
		panel_1.add(panel_2_1);
		
		JLabel lblNewLabel_2 = new JLabel("Kullanici");
		lblNewLabel_2.setBounds(10, 50, 74, 14);
		panel_2_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sifre");
		lblNewLabel_1_1.setBounds(10, 82, 46, 14);
		panel_2_1.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(98, 47, 130, 20);
		panel_2_1.add(textField_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setEchoChar('*');
		passwordField_1.setBounds(98, 79, 130, 20);
		panel_2_1.add(passwordField_1);
		
		JButton btnNewButton_3 = new JButton("Baglanti Test");
		btnNewButton_3.setBounds(98, 117, 130, 23);
		panel_2_1.add(btnNewButton_3);
		
		JButton btnNewButton_1_1 = new JButton("Kaydet");
		btnNewButton_1_1.setBounds(228, 240, 89, 23);
		panel_1.add(btnNewButton_1_1);
		
		JButton btnNewButton_2_1 = new JButton("Vazgec");
		btnNewButton_2_1.setBounds(355, 240, 89, 23);
		panel_1.add(btnNewButton_2_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(170, 27, 130, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblServer = new JLabel("Server / Port");
		lblServer.setBounds(72, 30, 78, 14);
		panel_1.add(lblServer);
	}
}

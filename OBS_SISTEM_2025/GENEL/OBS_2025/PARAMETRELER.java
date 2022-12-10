package OBS_2025;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import OBS_C_2025.ColorChooserButton;
import OBS_C_2025.ColorChooserButton.ColorChangedListener;
import OBS_C_2025.Font_Sec;
import OBS_C_2025.TARIH_CEVIR;

import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

public class PARAMETRELER   extends JInternalFrame   {
	
	static Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	static Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	Font_Sec fc = new Font_Sec();
	Font bigFont;

	private static String [] Harf = { " ","A", "B", "C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","Y","Z" };
	private static DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(Harf);
	private static DefaultComboBoxModel<String> comboModel1 = new DefaultComboBoxModel<String>(Harf);
	private static DefaultComboBoxModel<String> comboModel2 = new DefaultComboBoxModel<String>(Harf);
	private static DefaultComboBoxModel<String> comboModel3 = new DefaultComboBoxModel<String>(Harf);
	
	private static final long serialVersionUID = 1L;
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;
	private static JTextField textField_4;
	private static JTextField textField_5;
	private static JTextField textField_6;
	private static JTextField textField_7;
	private static JTextField textField_8;
	private static JTextField textField_9;
	private static JCheckBox chckbxNewCheckBox;
	private static JLabel lblNewLabel_3;
	private static JCheckBox chckbxNewCheckBox_2_2;
	private static JCheckBox chckbxNewCheckBox_2_1_1;
	private static JComboBox<String> comboBox_2_1;
	private static JLabel lblNewLabel_3_3;
	private static JLabel lblNewLabel_3_4;
	private static JLabel lblNewLabel_3_5;
	private static JLabel lblNewLabel_3_6;
	private static JLabel lblNewLabel_3_7;
	private static JComboBox<String> comboBox_2_1_1 ;
	private static JCheckBox chckbxNewCheckBox_2_2_1;
	private static JCheckBox chckbxNewCheckBox_2_1_1_1;
	private static JLabel lblNewLabel_3_8 ;
	private static JLabel lblNewLabel_3_9 ;
	//**********FAT********************
	private static JLabel lblNewLabel_3_10 ;
	private static JLabel lblNewLabel_3_1_1 ;
	private static JLabel lblNewLabel_3_2_1 ;
	private static JComboBox<String> comboBox ;
	private static JLabel lblNewLabel_3_2_1_1 ;
	private static JCheckBox chckbxNewCheckBox_1 ;
	private static JLabel lblNewLabel_3_2_1_1_1 ;
	private static JLabel lblNewLabel_3_10_1 ;
	private static JLabel lblNewLabel_3_1_1_1 ;
	private static JLabel lblNewLabel_3_2_1_2 ;
	private static JComboBox<String> comboBox_1 ;
	private static JLabel lblNewLabel_3_2_1_1_2 ;
	private static JLabel lblNewLabel_3_10_1_1 ;
	private static JCheckBox chckbxNewCheckBox_1_1 ;
	private static JCheckBox chckbxNewCheckBox_1_2 ; 
	private static JDateChooser dateChooser ;
	//*********************** GUNLUK **************
	private static JCheckBox chckbxNewCheckBox_1_3  ;
	private static JCheckBox chckbxNewCheckBox_1_3_1 ;
	private static JCheckBox chckbxNewCheckBox_2_2_1_1_1 ;
	private static JCheckBox chckbxNewCheckBox_2_1_1_1_1_1 ;
	private static JComboBox<String> comboBox_2_1_1_1_1  ;
	private static JComboBox<String> comboBox_2_1_1_2_1 ;
	private static JCheckBox chckbxNewCheckBox_2_2_1_2_1  ;
	private static JCheckBox chckbxNewCheckBox_2_1_1_1_2_1 ;
	//***********************PROGRAM ***************
	private static JCheckBox chckbxNewCheckBox_2_2_1_1 ;
	private static JCheckBox chckbxNewCheckBox_2_1_1_1_1 ;
	private static JComboBox<String> comboBox_2_1_1_1 ;
	private static JCheckBox chckbxNewCheckBox_2_2_1_2 ;
	private static JCheckBox chckbxNewCheckBox_2_1_1_1_2 ;
	private static JComboBox<String> comboBox_2_1_1_2  ;
	private static JCheckBox chckbxNewCheckBox_2_2_1_2_2 ;
	private static JCheckBox chckbxNewCheckBox_2_1_1_1_2_2 ;
	private static JComboBox<String> comboBox_2_1_1_2_2 ;
	private static JCheckBox chckbxNewCheckBox_2_2_1_2_3 ;
	private static JCheckBox chckbxNewCheckBox_2_1_1_1_2_3 ;
	private static JComboBox<String> comboBox_2_1_1_2_3 ;
	private static JComboBox<String> comboBox_2 ;
	private static ColorChooserButton colorChooser ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PARAMETRELER frame = new PARAMETRELER();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public PARAMETRELER()  {
		setIconifiable(true);
		setTitle("PARAMETRELER");
		setClosable(true);
		setBounds(0, 0, 515, 574);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(25, 25, 112));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String[] parts;
				String deger ;
				try {
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
		                OBS_MAIN.btnNewButton.doClick();
		                }
					}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		tabbedPane.addTab("Program", null, scrollPane_5, null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 191, 255)));
		scrollPane_5.setViewportView(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_4_1_2_1_5_1 = new JLabel("Para Birimi");
		lblNewLabel_4_1_2_1_5_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_1_5_1.setBounds(10, 14, 126, 14);
		panel_4.add(lblNewLabel_4_1_2_1_5_1);
		
		textField_9 = new JTextField();
		textField_9.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				String[] parts;
				String deger ;
				try {
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0)) {
		                OBS_MAIN.btnNewButton.doClick();
		           //     System.out.println("woot!");
		                	}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//if ((e.getKeyCode() == KeyEvent.VK_C) && ((e.getModifiers() & KeyEvent.ALT_MASK) != 0)) {
                //    System.out.println("woot!");
                //}
			}
		});
		textField_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_9.setColumns(10);
		textField_9.setBounds(151, 11, 53, 20);
		panel_4.add(textField_9);
		
		JLabel lblNewLabel_4_2_1 = new JLabel("Filtre");
		lblNewLabel_4_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_2_1.setBounds(10, 39, 126, 14);
		panel_4.add(lblNewLabel_4_2_1);
		
		chckbxNewCheckBox_2_2_1_1 = new JCheckBox("Ctrl");
		chckbxNewCheckBox_2_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_2_1_1.isSelected()) chckbxNewCheckBox_2_1_1_1_1.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_2_1_1.setBounds(148, 35, 55, 23);
		panel_4.add(chckbxNewCheckBox_2_2_1_1);
		
		chckbxNewCheckBox_2_1_1_1_1 = new JCheckBox("Alt");
		chckbxNewCheckBox_2_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_1_1_1_1.isSelected()) chckbxNewCheckBox_2_2_1_1.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_1_1_1_1.setBounds(220, 35, 55, 23);
		panel_4.add(chckbxNewCheckBox_2_1_1_1_1);
		
		comboBox_2_1_1_1 = new JComboBox<String>(comboModel);
		comboBox_2_1_1_1.setForeground(new Color(25, 25, 112));
		comboBox_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_2_1_1_1.setBounds(295, 35, 55, 22);
		panel_4.add(comboBox_2_1_1_1);
		
		JLabel lblNewLabel_4_2_2 = new JLabel("Yenile");
		lblNewLabel_4_2_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_2_2.setBounds(10, 64, 126, 14);
		panel_4.add(lblNewLabel_4_2_2);
		
		chckbxNewCheckBox_2_2_1_2 = new JCheckBox("Ctrl");
		chckbxNewCheckBox_2_2_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_2_1_2.isSelected()) chckbxNewCheckBox_2_1_1_1_2.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_2_1_2.setBounds(148, 60, 55, 23);
		panel_4.add(chckbxNewCheckBox_2_2_1_2);
		
		chckbxNewCheckBox_2_1_1_1_2 = new JCheckBox("Alt");
		chckbxNewCheckBox_2_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_1_1_1_2.isSelected()) chckbxNewCheckBox_2_2_1_2.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_1_1_1_2.setBounds(220, 60, 55, 23);
		panel_4.add(chckbxNewCheckBox_2_1_1_1_2);
		
		comboBox_2_1_1_2 = new JComboBox<String>(comboModel1);
		comboBox_2_1_1_2.setForeground(new Color(25, 25, 112));
		comboBox_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_2_1_1_2.setBounds(295, 60, 55, 22);
		panel_4.add(comboBox_2_1_1_2);
		
		JLabel lblNewLabel_4_2_2_2 = new JLabel("Kaydet");
		lblNewLabel_4_2_2_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_2_2_2.setBounds(10, 102, 126, 14);
		panel_4.add(lblNewLabel_4_2_2_2);
		
		chckbxNewCheckBox_2_2_1_2_2 = new JCheckBox("Ctrl");
		chckbxNewCheckBox_2_2_1_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_2_1_2_2.isSelected()) chckbxNewCheckBox_2_1_1_1_2_2.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_2_1_2_2.setBounds(148, 98, 55, 23);
		panel_4.add(chckbxNewCheckBox_2_2_1_2_2);
		
		chckbxNewCheckBox_2_1_1_1_2_2 = new JCheckBox("Alt");
		chckbxNewCheckBox_2_1_1_1_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_1_1_1_2_2.isSelected()) chckbxNewCheckBox_2_2_1_2_2.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_1_1_1_2_2.setBounds(220, 98, 55, 23);
		panel_4.add(chckbxNewCheckBox_2_1_1_1_2_2);
		
		comboBox_2_1_1_2_2 = new JComboBox<String>(comboModel2);
		comboBox_2_1_1_2_2.setForeground(new Color(25, 25, 112));
		comboBox_2_1_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_2_1_1_2_2.setBounds(295, 98, 55, 22);
		panel_4.add(comboBox_2_1_1_2_2);
		
		JLabel lblNewLabel_4_2_2_3 = new JLabel("Sil");
		lblNewLabel_4_2_2_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_2_2_3.setBounds(10, 127, 126, 14);
		panel_4.add(lblNewLabel_4_2_2_3);
		
		chckbxNewCheckBox_2_2_1_2_3 = new JCheckBox("Ctrl");
		chckbxNewCheckBox_2_2_1_2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_2_1_2_3.isSelected()) chckbxNewCheckBox_2_1_1_1_2_3.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_2_1_2_3.setBounds(148, 123, 55, 23);
		panel_4.add(chckbxNewCheckBox_2_2_1_2_3);
		
		chckbxNewCheckBox_2_1_1_1_2_3 = new JCheckBox("Alt");
		chckbxNewCheckBox_2_1_1_1_2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_1_1_1_2_3.isSelected()) chckbxNewCheckBox_2_2_1_2_3.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_1_1_1_2_3.setBounds(220, 123, 55, 23);
		panel_4.add(chckbxNewCheckBox_2_1_1_1_2_3);
		
		comboBox_2_1_1_2_3 = new JComboBox<String>(comboModel3);
		comboBox_2_1_1_2_3.setForeground(new Color(25, 25, 112));
		comboBox_2_1_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_2_1_1_2_3.setBounds(295, 123, 55, 22);
		panel_4.add(comboBox_2_1_1_2_3);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 89, 340, 2);
		separator_2.setForeground(new Color(0, 191, 255));
		panel_4.add(separator_2);
		
		JLabel lblNewLabel_2 = new JLabel("Gorunum");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 167, 68, 14);
		panel_4.add(lblNewLabel_2);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setForeground(new Color(25, 25, 112));
		comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"Metal Klasik", "Metal","Windows","Nimbus"}));
		comboBox_2.setBounds(151, 163, 199, 22);
		panel_4.add(comboBox_2);
		
		colorChooser = new ColorChooserButton(new Color(0, 191, 255));
		colorChooser.setBounds(151, 237, 25, 25);
		colorChooser.addColorChangedListener(new ColorChangedListener() {
		    @Override
		    public void colorChanged(Color newColor) {
		 //  System.out.println("Renk=" + newColor.getRed() + "," +  newColor.getGreen()+ "," +  newColor.getBlue()  );
		  //	 JOptionPane.showMessageDialog(null, newColor.toString(), "Parametre Kayit", JOptionPane.ERROR_MESSAGE);
		    }
		});

		panel_4.add(colorChooser);
		
		JLabel lblNewLabel_6 = new JLabel("Grid Izgara Rengi");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_6.setBounds(10, 243, 101, 14);
		panel_4.add(lblNewLabel_6);
		
		JScrollPane scrollPane = new JScrollPane();
		
		tabbedPane.addTab("Cari Hesap", null, scrollPane, null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bakiye Goster");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 21, 97, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cari Arama");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(10, 46, 71, 14);
		panel.add(lblNewLabel_1);
		
		chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(146, 17, 46, 23);
		panel.add(chckbxNewCheckBox);
		
		lblNewLabel_3 = new JLabel("...");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(150, 46, 253, 14);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton(".....");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] parts;
				parts = lblNewLabel_3.getText() .substring(1, lblNewLabel_3.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"Font Secimi");
				
				lblNewLabel_3.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3.setFont(bigFont);
				
				//JColorChooser asd = new JColorChooser();
				//JOptionPane.showMessageDialog(null, asd,  "Renk Secimi", JOptionPane.PLAIN_MESSAGE);        
				//System.out.println(asd.getColor().toString());
				
			}
		});
	    btnNewButton.setBounds(417, 42, 46, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("Cari Dekont Yeni Fis");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(10, 75, 126, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Cari Dovize Cevirme");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(10, 100, 126, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Cari Ekstre");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_1.setBounds(10, 125, 110, 14);
		panel.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("Cari Gunluk Islem");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_2.setBounds(10, 150, 110, 14);
		panel.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_3 = new JLabel("Cari Hesap Bak");
		lblNewLabel_5_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_3.setBounds(10, 175, 110, 14);
		panel.add(lblNewLabel_5_3);
		
		JLabel lblNewLabel_5_4 = new JLabel("Cari Hesap Plani");
		lblNewLabel_5_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_4.setBounds(10, 200, 110, 14);
		panel.add(lblNewLabel_5_4);
		
		JLabel lblNewLabel_5_5 = new JLabel("Cari Karton");
		lblNewLabel_5_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_5.setBounds(10, 250, 110, 14);
		panel.add(lblNewLabel_5_5);
		
		JLabel lblNewLabel_5_6 = new JLabel("Cari Mizan");
		lblNewLabel_5_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_6.setBounds(10, 275, 110, 14);
		panel.add(lblNewLabel_5_6);
		
		JLabel lblNewLabel_5_7 = new JLabel("Cari Mizan Grup");
		lblNewLabel_5_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5_7.setBounds(10, 300, 110, 14);
		panel.add(lblNewLabel_5_7);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String[] parts;
				String deger ;
				try {
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
		                OBS_MAIN.btnNewButton.doClick();
		                }
					}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(150, 297, 46, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_3_3 = new JLabel("...");
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_3.setBounds(150, 100, 253, 14);
		panel.add(lblNewLabel_3_3);
		
		lblNewLabel_3_4 = new JLabel("...");
		lblNewLabel_3_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_4.setBounds(150, 125, 253, 14);
		panel.add(lblNewLabel_3_4);
		
		lblNewLabel_3_5 = new JLabel("...");
		lblNewLabel_3_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_5.setBounds(150, 150, 253, 14);
		panel.add(lblNewLabel_3_5);
		
		lblNewLabel_3_6 = new JLabel("...");
		lblNewLabel_3_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_6.setBounds(150, 175, 253, 14);
		panel.add(lblNewLabel_3_6);
		
		lblNewLabel_3_7 = new JLabel("...");
		lblNewLabel_3_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_7.setBounds(150, 200, 253, 14);
		panel.add(lblNewLabel_3_7);
		
		lblNewLabel_3_8 = new JLabel("...");
		lblNewLabel_3_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_8.setBounds(150, 250, 253, 14);
		panel.add(lblNewLabel_3_8);
		
		lblNewLabel_3_9 = new JLabel("...");
		lblNewLabel_3_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_9.setBounds(150, 275, 253, 14);
		panel.add(lblNewLabel_3_9);
		
		
		
		JButton btnNewButton_3 = new JButton(".....");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_3.getText() .substring(1, lblNewLabel_3_3.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				
				fc.showDialog(null,"");
				lblNewLabel_3_3.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_3.setFont(bigFont);

			}
		});
		btnNewButton_3.setBounds(417, 96, 46, 23);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton(".....");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_4.getText() .substring(1, lblNewLabel_3_4.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				
				fc.showDialog(null,"");
				lblNewLabel_3_4.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_4.setFont(bigFont);

			}
		});
		btnNewButton_4.setBounds(417, 121, 46, 23);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton(".....");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_5.getText() .substring(1, lblNewLabel_3_5.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				
				fc.showDialog(null,"");
				lblNewLabel_3_5.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_5.setFont(bigFont);

			}
		});
		btnNewButton_5.setBounds(417, 146, 46, 23);
		panel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton(".....");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_6.getText() .substring(1, lblNewLabel_3_6.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"");
				lblNewLabel_3_6.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_6.setFont(bigFont);

			}
		});
		btnNewButton_6.setBounds(417, 171, 46, 23);
		panel.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton(".....");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_7.getText() .substring(1, lblNewLabel_3_7.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"");
				lblNewLabel_3_7.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_7.setFont(bigFont);

			}
		});
		btnNewButton_7.setBounds(417, 196, 46, 23);
		panel.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton(".....");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_8.getText() .substring(1, lblNewLabel_3_8.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"");
				lblNewLabel_3_8.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_8.setFont(bigFont);

			}
		});
		btnNewButton_8.setBounds(417, 246, 46, 23);
		panel.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton(".....");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_9.getText() .substring(1, lblNewLabel_3_9.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"");
				lblNewLabel_3_9.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_9.setFont(bigFont);

			}
		});
		btnNewButton_9.setBounds(417, 271, 46, 23);
		panel.add(btnNewButton_9);
		
		chckbxNewCheckBox_2_2 = new JCheckBox("Ctrl");
		chckbxNewCheckBox_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_2.isSelected()) chckbxNewCheckBox_2_1_1.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_2.setBounds(146, 71, 71, 23);
		panel.add(chckbxNewCheckBox_2_2);
		
		chckbxNewCheckBox_2_1_1 = new JCheckBox("Alt");
		chckbxNewCheckBox_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_1_1.isSelected()) chckbxNewCheckBox_2_2.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_1_1.setBounds(220, 71, 55, 23);
		panel.add(chckbxNewCheckBox_2_1_1);
		
		comboBox_2_1 = new JComboBox<String>(comboModel);
		comboBox_2_1.setForeground(new Color(25, 25, 112));
		comboBox_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_2_1.setBounds(295, 71, 55, 22);
		panel.add(comboBox_2_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Cari Hesap Plani");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_2.setBounds(10, 225, 126, 14);
		panel.add(lblNewLabel_4_2);
		
		chckbxNewCheckBox_2_2_1 = new JCheckBox("Ctrl");
		chckbxNewCheckBox_2_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_2_1.isSelected()) chckbxNewCheckBox_2_1_1_1.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_2_1.setBounds(146, 221, 55, 23);
		panel.add(chckbxNewCheckBox_2_2_1);
		
		chckbxNewCheckBox_2_1_1_1 = new JCheckBox("Alt");
		chckbxNewCheckBox_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_1_1_1.isSelected()) chckbxNewCheckBox_2_2_1.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_1_1_1.setBounds(220, 221, 55, 23);
		panel.add(chckbxNewCheckBox_2_1_1_1);
		
		comboBox_2_1_1 =  new JComboBox<String>(comboModel1);
		comboBox_2_1_1.setForeground(new Color(25, 25, 112));
		comboBox_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_2_1_1.setBounds(295, 221, 55, 22);
		panel.add(comboBox_2_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Fatura", null, scrollPane_1, null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 191, 255)));
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fatura Alt Bilgi");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(10, 15, 112, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Fatura Baslik");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1.setBounds(10, 40, 126, 14);
		panel_1.add(lblNewLabel_2_1);
		
		lblNewLabel_3_10 = new JLabel("...");
		lblNewLabel_3_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_10.setBounds(150, 15, 253, 14);
		panel_1.add(lblNewLabel_3_10);
		
		JButton btnNewButton_10 = new JButton(".....");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_10.getText() .substring(1, lblNewLabel_3_10.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"Font Secimi");
				lblNewLabel_3_10.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_10.setFont(bigFont);
			}
		});
		btnNewButton_10.setBounds(417, 11, 46, 23);
		panel_1.add(btnNewButton_10);
		
		JLabel lblNewLabel_4_1 = new JLabel("Fatura Detay");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1.setBounds(10, 65, 126, 14);
		panel_1.add(lblNewLabel_4_1);
		
		lblNewLabel_3_1_1 = new JLabel("...");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_1_1.setBounds(150, 40, 253, 14);
		panel_1.add(lblNewLabel_3_1_1);
		
		lblNewLabel_3_2_1 = new JLabel("...");
		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_2_1.setBounds(150, 65, 253, 14);
		panel_1.add(lblNewLabel_3_2_1);
		
		JButton btnNewButton_1_1 = new JButton(".....");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_1_1.getText() .substring(1, lblNewLabel_3_1_1.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"Font Secimi");
				lblNewLabel_3_1_1.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_1_1.setFont(bigFont);
			}
		});
		btnNewButton_1_1.setBounds(417, 36, 46, 23);
		panel_1.add(btnNewButton_1_1);
		
		JButton btnNewButton_2_1 = new JButton(".....");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_2_1.getText() .substring(1, lblNewLabel_3_2_1.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"Font Secimi");
				lblNewLabel_3_2_1.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_2_1.setFont(bigFont);
			}
		});
		btnNewButton_2_1.setBounds(417, 61, 46, 23);
		panel_1.add(btnNewButton_2_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Fatura Firma Bilgi");
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_1.setBounds(10, 90, 126, 14);
		panel_1.add(lblNewLabel_4_1_1);
		
		comboBox = new JComboBox<String>();
		comboBox.setForeground(new Color(25, 25, 112));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Cari_Dosya", "Adres_Dosya"}));
		comboBox.setBounds(150, 86, 139, 22);
		panel_1.add(comboBox);
		
		JLabel lblNewLabel_4_1_2 = new JLabel("Fatura Giris");
		lblNewLabel_4_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2.setBounds(10, 119, 126, 14);
		panel_1.add(lblNewLabel_4_1_2);
		
		lblNewLabel_3_2_1_1 = new JLabel("...");
		lblNewLabel_3_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_2_1_1.setBounds(150, 119, 253, 14);
		panel_1.add(lblNewLabel_3_2_1_1);
		
		JButton btnNewButton_2_1_1 = new JButton(".....");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_2_1_1.getText() .substring(1, lblNewLabel_3_2_1_1.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"Font Secimi");
				lblNewLabel_3_2_1_1.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_2_1_1.setFont(bigFont);
			}
		});
		btnNewButton_2_1_1.setBounds(417, 115, 46, 23);
		panel_1.add(btnNewButton_2_1_1);
		
		JLabel lblNewLabel_4_1_2_1 = new JLabel("Fatura Satir Sayi");
		lblNewLabel_4_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_1.setBounds(10, 147, 126, 14);
		panel_1.add(lblNewLabel_4_1_2_1);
		
		JLabel lblNewLabel_4_1_2_2 = new JLabel("Fatura Stok Kontrol");
		lblNewLabel_4_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_2.setBounds(10, 171, 126, 14);
		panel_1.add(lblNewLabel_4_1_2_2);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String[] parts;
				String deger ;
				try {
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
		                OBS_MAIN.btnNewButton.doClick();
		                }
					}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_1.setBounds(150, 144, 40, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		chckbxNewCheckBox_1 = new JCheckBox("");
		chckbxNewCheckBox_1.setBounds(147, 165, 97, 23);
		panel_1.add(chckbxNewCheckBox_1);
		
		JLabel lblNewLabel_4_1_2_3 = new JLabel("Fatura Yazi Ile");
		lblNewLabel_4_1_2_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_3.setBounds(10, 194, 126, 14);
		panel_1.add(lblNewLabel_4_1_2_3);
		
		lblNewLabel_3_2_1_1_1 = new JLabel("...");
		lblNewLabel_3_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_2_1_1_1.setBounds(150, 194, 253, 14);
		panel_1.add(lblNewLabel_3_2_1_1_1);
		
		JButton btnNewButton_2_1_1_1 = new JButton(".....");
		btnNewButton_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_2_1_1_1.getText() .substring(1, lblNewLabel_3_2_1_1_1.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"Font Secimi");
				lblNewLabel_3_2_1_1_1.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_2_1_1_1.setFont(bigFont);
			}
		});
		btnNewButton_2_1_1_1.setBounds(417, 190, 46, 23);
		panel_1.add(btnNewButton_2_1_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Irsaliye Alt Bilgi");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_1.setBounds(10, 238, 112, 14);
		panel_1.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Irsaliye Baslik");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1_1.setBounds(10, 263, 126, 14);
		panel_1.add(lblNewLabel_2_1_1);
		
		lblNewLabel_3_10_1 = new JLabel("...");
		lblNewLabel_3_10_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_10_1.setBounds(150, 238, 253, 14);
		panel_1.add(lblNewLabel_3_10_1);
		
		JButton btnNewButton_10_1 = new JButton(".....");
		btnNewButton_10_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_10_1.getText() .substring(1, lblNewLabel_3_10_1.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"Font Secimi");
				lblNewLabel_3_10_1.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_10_1.setFont(bigFont);
			}
		});
		btnNewButton_10_1.setBounds(417, 234, 46, 23);
		panel_1.add(btnNewButton_10_1);
		
		JLabel lblNewLabel_4_1_3 = new JLabel("Irsaliye Detay");
		lblNewLabel_4_1_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_3.setBounds(10, 288, 126, 14);
		panel_1.add(lblNewLabel_4_1_3);
		
		lblNewLabel_3_1_1_1 = new JLabel("...");
		lblNewLabel_3_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_1_1_1.setBounds(150, 263, 253, 14);
		panel_1.add(lblNewLabel_3_1_1_1);
		
		lblNewLabel_3_2_1_2 = new JLabel("...");
		lblNewLabel_3_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_2_1_2.setBounds(150, 288, 253, 14);
		panel_1.add(lblNewLabel_3_2_1_2);
		
		JButton btnNewButton_1_1_1 = new JButton(".....");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_1_1_1.getText() .substring(1, lblNewLabel_3_1_1_1.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"Font Secimi");
				lblNewLabel_3_1_1_1.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_1_1_1.setFont(bigFont);
			}
		});
		btnNewButton_1_1_1.setBounds(417, 259, 46, 23);
		panel_1.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_2_1_2 = new JButton(".....");
		btnNewButton_2_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_2_1_2.getText() .substring(1, lblNewLabel_3_2_1_2.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"Font Secimi");
				lblNewLabel_3_2_1_2.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_2_1_2.setFont(bigFont);
			}
		});
		btnNewButton_2_1_2.setBounds(417, 284, 46, 23);
		panel_1.add(btnNewButton_2_1_2);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Irsaliye Firma Bilgi");
		lblNewLabel_4_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_1_1.setBounds(10, 313, 126, 14);
		panel_1.add(lblNewLabel_4_1_1_1);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setForeground(new Color(25, 25, 112));
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Cari_Dosya", "Adres_Dosya"}));
		comboBox_1.setBounds(150, 309, 139, 22);
		panel_1.add(comboBox_1);
		
		JLabel lblNewLabel_4_1_2_4 = new JLabel("Irsaliye Giris");
		lblNewLabel_4_1_2_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_4.setBounds(10, 342, 126, 14);
		panel_1.add(lblNewLabel_4_1_2_4);
		
		lblNewLabel_3_2_1_1_2 = new JLabel("...");
		lblNewLabel_3_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_2_1_1_2.setBounds(150, 342, 253, 14);
		panel_1.add(lblNewLabel_3_2_1_1_2);
		
		JButton btnNewButton_2_1_1_2 = new JButton(".....");
		btnNewButton_2_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_2_1_1_2.getText() .substring(1, lblNewLabel_3_2_1_1_2.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"Font Secimi");
				lblNewLabel_3_2_1_1_2.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_2_1_1_2.setFont(bigFont);
			}
		});
		btnNewButton_2_1_1_2.setBounds(417, 338, 46, 23);
		panel_1.add(btnNewButton_2_1_1_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 225, 453, 2);
		separator.setForeground(new Color(0, 191, 255));
		panel_1.add(separator);
		
		JLabel lblNewLabel_4_1_2_1_1 = new JLabel("Irsaliye Satir Sayi");
		lblNewLabel_4_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_1_1.setBounds(10, 370, 126, 14);
		panel_1.add(lblNewLabel_4_1_2_1_1);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String[] parts;
				String deger ;
				try {
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
		                OBS_MAIN.btnNewButton.doClick();
		                }
					}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_2.setColumns(10);
		textField_2.setBounds(150, 367, 40, 20);
		panel_1.add(textField_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 395, 453, 2);
		separator_1.setForeground(new Color(0, 191, 255));
		panel_1.add(separator_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Raporlama Font");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_1_1.setBounds(10, 404, 112, 14);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		lblNewLabel_3_10_1_1 = new JLabel("...");
		lblNewLabel_3_10_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3_10_1_1.setBounds(150, 404, 253, 14);
		panel_1.add(lblNewLabel_3_10_1_1);
		
		JButton btnNewButton_10_1_1 = new JButton(".....");
		btnNewButton_10_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] parts;
				parts = lblNewLabel_3_10_1_1.getText() .substring(1,lblNewLabel_3_10_1_1.getText().length()-1).split(",");
				bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
				fc.setFont(bigFont);
				fc.showDialog(null,"Font Secimi");
				lblNewLabel_3_10_1_1.setText("[" + fc.getFont().getName() + "," + fc.getFont().getStyle() + "," + fc.getFont().getSize()+ "]" );
				bigFont = new Font(fc.getFont().getName(), fc.getFont().getStyle(), fc.getFont().getSize());
				lblNewLabel_3_10_1_1.setFont(bigFont);
			}
		});
		btnNewButton_10_1_1.setBounds(417, 400, 46, 23);
		panel_1.add(btnNewButton_10_1_1);
		
		JLabel lblNewLabel_4_1_2_2_1 = new JLabel("Envanter Kusurat");
		lblNewLabel_4_1_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_2_1.setBounds(10, 431, 126, 14);
		panel_1.add(lblNewLabel_4_1_2_2_1);
		
		chckbxNewCheckBox_1_1 = new JCheckBox("");
		chckbxNewCheckBox_1_1.setBounds(147, 425, 31, 23);
		panel_1.add(chckbxNewCheckBox_1_1);
		
		JLabel lblNewLabel_4_1_2_2_2 = new JLabel("Envanter Yazdirma");
		lblNewLabel_4_1_2_2_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_2_2.setBounds(10, 458, 126, 14);
		panel_1.add(lblNewLabel_4_1_2_2_2);
		
		chckbxNewCheckBox_1_2 = new JCheckBox("");
		chckbxNewCheckBox_1_2.setBounds(147, 452, 97, 23);
		panel_1.add(chckbxNewCheckBox_1_2);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(150, 478, 139, 20);
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 11));
		dateChooser.setDateFormatString("dd.MM.yyyy");
		dateChooser.setDate(TARIH_CEVIR.tarih("01.01.1900"));
		panel_1.add(dateChooser);
		
		JLabel lblImalatOrtfiatBaslangic = new JLabel("Imalat Ort.Fiat Baslangic");
		lblImalatOrtfiatBaslangic.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblImalatOrtfiatBaslangic.setBounds(10, 483, 139, 14);
		panel_1.add(lblImalatOrtfiatBaslangic);
		
							
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("Gunluk", null, scrollPane_2, null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 191, 255)));
		scrollPane_2.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_4_1_2_2_3 = new JLabel("Gunluk Kontrol");
		lblNewLabel_4_1_2_2_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_2_3.setBounds(10, 13, 126, 14);
		panel_2.add(lblNewLabel_4_1_2_2_3);
		
		chckbxNewCheckBox_1_3 = new JCheckBox("");
		chckbxNewCheckBox_1_3.setBounds(189, 7, 97, 23);
		panel_2.add(chckbxNewCheckBox_1_3);
		
		JLabel lblNewLabel_4_1_2_2_3_1 = new JLabel("Gunluk Kontrol Zamani");
		lblNewLabel_4_1_2_2_3_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_2_3_1.setBounds(10, 38, 126, 14);
		panel_2.add(lblNewLabel_4_1_2_2_3_1);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String[] parts;
				String deger ;
				try {
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
		                OBS_MAIN.btnNewButton.doClick();
		                }
					}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_3.setBounds(193, 37, 86, 20);
		panel_2.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4_1_2_2_3_2 = new JLabel("Gunluk Server Kontrol");
		lblNewLabel_4_1_2_2_3_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_2_3_2.setBounds(10, 69, 126, 14);
		panel_2.add(lblNewLabel_4_1_2_2_3_2);
		
		chckbxNewCheckBox_1_3_1 = new JCheckBox("");
		chckbxNewCheckBox_1_3_1.setBounds(189, 63, 97, 23);
		panel_2.add(chckbxNewCheckBox_1_3_1);
		
		JLabel lblNewLabel_4_1_2_2_3_1_1 = new JLabel("Gunluk Server Kontrol Zamani");
		lblNewLabel_4_1_2_2_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_2_3_1_1.setBounds(10, 94, 173, 14);
		panel_2.add(lblNewLabel_4_1_2_2_3_1_1);
		
		textField_4 = new JTextField();
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String[] parts;
				String deger ;
				try {
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
		                OBS_MAIN.btnNewButton.doClick();
		                }
					}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_4.setColumns(10);
		textField_4.setBounds(193, 93, 86, 20);
		panel_2.add(textField_4);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane.addTab("Kambiyo", null, scrollPane_3, null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 191, 255)));
		scrollPane_3.setViewportView(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_4_1_2_1_2 = new JLabel("Cek Giris");
		lblNewLabel_4_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_1_2.setBounds(10, 14, 126, 14);
		panel_3.add(lblNewLabel_4_1_2_1_2);
		
		textField_5 = new JTextField();
		textField_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String[] parts;
				String deger ;
				try {
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
		                OBS_MAIN.btnNewButton.doClick();
		                }
					}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_5.setColumns(10);
		textField_5.setBounds(150, 11, 40, 20);
		panel_3.add(textField_5);
		
		JLabel lblNewLabel_4_1_2_1_3 = new JLabel("Cek Cikis");
		lblNewLabel_4_1_2_1_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_1_3.setBounds(10, 42, 126, 14);
		panel_3.add(lblNewLabel_4_1_2_1_3);
		
		textField_6 = new JTextField();
		textField_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String[] parts;
				String deger ;
				try {
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
		                OBS_MAIN.btnNewButton.doClick();
		                }
					}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		textField_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_6.setColumns(10);
		textField_6.setBounds(150, 39, 40, 20);
		panel_3.add(textField_6);
		
		JLabel lblNewLabel_4_1_2_1_4 = new JLabel("Senet Giris");
		lblNewLabel_4_1_2_1_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_1_4.setBounds(10, 70, 126, 14);
		panel_3.add(lblNewLabel_4_1_2_1_4);
		
		textField_7 = new JTextField();
		textField_7.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String[] parts;
				String deger ;
				try {
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
		                OBS_MAIN.btnNewButton.doClick();
		                }
					}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		textField_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_7.setColumns(10);
		textField_7.setBounds(150, 67, 40, 20);
		panel_3.add(textField_7);
		
		JLabel lblNewLabel_4_1_2_1_5 = new JLabel("Senet Cikis");
		lblNewLabel_4_1_2_1_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_1_2_1_5.setBounds(10, 98, 126, 14);
		panel_3.add(lblNewLabel_4_1_2_1_5);
		
		textField_8 = new JTextField();
		textField_8.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String[] parts;
				String deger ;
				try {
					deger = oac.glb.setting_oku("PRG_KAYDET").toString();
					parts = deger.split(",");
					if ( ! parts[2].equals(" ")) 
					{
						char c=parts[2].charAt(0);
						if ((e.getKeyCode() == c) && ((e.getModifiers() & (parts[0].equals("E") ?  KeyEvent.CTRL_MASK : KeyEvent.ALT_MASK) ) != 0))
						{
		                OBS_MAIN.btnNewButton.doClick();
		                }
					}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		textField_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField_8.setColumns(10);
		textField_8.setBounds(150, 95, 40, 20);
		panel_3.add(textField_8);
		
		JLabel lblNewLabel_4_2_1_1 = new JLabel("Satir Kopyala");
		lblNewLabel_4_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_2_1_1.setBounds(10, 131, 126, 14);
		panel_3.add(lblNewLabel_4_2_1_1);
		
		chckbxNewCheckBox_2_2_1_1_1 = new JCheckBox("Ctrl");
		chckbxNewCheckBox_2_2_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_2_1_1_1.isSelected()) chckbxNewCheckBox_2_1_1_1_1_1.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_2_1_1_1.setBounds(148, 127, 55, 23);
		panel_3.add(chckbxNewCheckBox_2_2_1_1_1);
		
		chckbxNewCheckBox_2_1_1_1_1_1 = new JCheckBox("Alt");
		chckbxNewCheckBox_2_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_1_1_1_1_1.isSelected()) chckbxNewCheckBox_2_2_1_1_1.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_1_1_1_1_1.setBounds(220, 127, 55, 23);
		panel_3.add(chckbxNewCheckBox_2_1_1_1_1_1);
		
		comboBox_2_1_1_1_1 = new JComboBox<String>(comboModel);
		comboBox_2_1_1_1_1.setForeground(new Color(25, 25, 112));
		comboBox_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_2_1_1_1_1.setBounds(295, 127, 55, 22);
		panel_3.add(comboBox_2_1_1_1_1);
		
		JLabel lblNewLabel_4_2_2_1 = new JLabel("Satir Yapistir");
		lblNewLabel_4_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4_2_2_1.setBounds(10, 156, 126, 14);
		panel_3.add(lblNewLabel_4_2_2_1);
		
		chckbxNewCheckBox_2_2_1_2_1 = new JCheckBox("Ctrl");
		chckbxNewCheckBox_2_2_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_2_1_2_1.isSelected()) chckbxNewCheckBox_2_1_1_1_2_1.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_2_1_2_1.setBounds(148, 152, 55, 23);
		panel_3.add(chckbxNewCheckBox_2_2_1_2_1);
		
		chckbxNewCheckBox_2_1_1_1_2_1 = new JCheckBox("Alt");
		chckbxNewCheckBox_2_1_1_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox_2_1_1_1_2_1.isSelected()) chckbxNewCheckBox_2_2_1_2_1.setSelected(false);
			}
		});
		chckbxNewCheckBox_2_1_1_1_2_1.setBounds(220, 152, 55, 23);
		panel_3.add(chckbxNewCheckBox_2_1_1_1_2_1);
		
		comboBox_2_1_1_2_1 = new JComboBox<String>(comboModel1);
		comboBox_2_1_1_2_1.setForeground(new Color(25, 25, 112));
		comboBox_2_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox_2_1_1_2_1.setBounds(295, 152, 55, 22);
		panel_3.add(comboBox_2_1_1_2_1);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		
		tabbedPane.addTab("Kur", null, scrollPane_4, null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 191, 255)));
		scrollPane_4.setViewportView(panel_5);
		panel_5.setLayout(null);
			doldur();
	
		
	}
	private void doldur()  {
		try {
			getContentPane().setCursor(WAIT_CURSOR);
		String deger;
		String[] parts;
		deger = oac.glb.setting_oku("CARI_DEKONT_BAKIYE_GOSTER").toString();
		
		if (deger.equals("-1"))
		{
			chckbxNewCheckBox.setSelected(false);
		}
		else
		{
			chckbxNewCheckBox.setSelected(true);
		}
		deger = oac.glb.setting_oku("CARI_ARAMA").toString();
			lblNewLabel_3.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3.setFont(bigFont);
		
		deger = oac.glb.setting_oku("CARI_DEK_Y_FIS").toString();
			parts = deger.split(",");
			if (parts[0].equals("E")) {chckbxNewCheckBox_2_2.setSelected(true); } else {chckbxNewCheckBox_2_2.setSelected(false);} 
			if (parts[1].equals("E")) {chckbxNewCheckBox_2_1_1.setSelected(true); } else {chckbxNewCheckBox_2_1_1.setSelected(false);}
			comboBox_2_1.setSelectedItem(parts[2]);
			
		deger = oac.glb.setting_oku("CARI_DVZ_CEV").toString();
			lblNewLabel_3_3.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_3.setFont(bigFont);
		
		deger = oac.glb.setting_oku("CARI_EKSTRE").toString();
			lblNewLabel_3_4.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_4.setFont(bigFont);
		
		deger = oac.glb.setting_oku("CARI_GUN_ISL").toString();
			lblNewLabel_3_5.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_5.setFont(bigFont);
			
		deger = oac.glb.setting_oku("CARI_HES_BAK").toString();
			lblNewLabel_3_6.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_6.setFont(bigFont);
		
		deger = oac.glb.setting_oku("CARI_HSPPLN").toString();
			lblNewLabel_3_7.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_7.setFont(bigFont);
			
		deger = oac.glb.setting_oku("CARI_HSPPLN_CAG").toString();
			parts = deger.split(",");
			if (parts[0].equals("E")) {chckbxNewCheckBox_2_2_1.setSelected(true); } else {chckbxNewCheckBox_2_2_1.setSelected(false);} 
			if (parts[1].equals("E")) {chckbxNewCheckBox_2_1_1_1.setSelected(true);} else {chckbxNewCheckBox_2_1_1_1.setSelected(false);} 
			comboBox_2_1_1.setSelectedItem(parts[2]);
		
		deger = oac.glb.setting_oku("CARI_KARTON").toString();
			lblNewLabel_3_8.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_8.setFont(bigFont);
		
		deger = oac.glb.setting_oku("CARI_MIZAN").toString();
			lblNewLabel_3_9.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_9.setFont(bigFont);
			
		deger = oac.glb.setting_oku("CARI_MIZ_GRUP").toString();
		textField.setText(deger);
		// ********************************FATURA**********************************************************************
		deger = oac.glb.setting_oku("STK_FAT_ABILGI").toString();
			lblNewLabel_3_10.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_10.setFont(bigFont);
			
		deger = oac.glb.setting_oku("STK_FAT_BASLIK").toString();
			lblNewLabel_3_1_1.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_1_1.setFont(bigFont);
			
		deger = oac.glb.setting_oku("STK_FAT_DETAY").toString();
			lblNewLabel_3_2_1.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_2_1.setFont(bigFont);

		deger = oac.glb.setting_oku("STK_FAT_FIR_BILGI").toString();
			comboBox.setSelectedItem(deger);
		deger = oac.glb.setting_oku("STK_FAT_GIRIS").toString();
			lblNewLabel_3_2_1_1.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_2_1_1.setFont(bigFont);
		
		deger = oac.glb.setting_oku("STK_FAT_SATIR").toString();
		textField_1.setText(deger);
		deger = oac.glb.setting_oku("STK_STOK_KONTROL").toString();
		if (deger.equals("-1"))
		{
			chckbxNewCheckBox_1.setSelected(false);
		}
		else
		{
			chckbxNewCheckBox_1.setSelected(true);
		}
		deger = oac.glb.setting_oku("STK_YAZI_ILE").toString();
			lblNewLabel_3_2_1_1_1.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_2_1_1_1.setFont(bigFont);
		
		/// ***********************************************IRS
		deger = oac.glb.setting_oku("STK_ISR_ABILGI").toString();
			lblNewLabel_3_10_1.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_10_1.setFont(bigFont);
			
		deger = oac.glb.setting_oku("STK_IRS_BASLIK").toString();
			lblNewLabel_3_1_1_1.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_1_1_1.setFont(bigFont);
			
		deger = oac.glb.setting_oku("STK_IRS_DETAY").toString();
			lblNewLabel_3_2_1_2.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_2_1_2.setFont(bigFont);
			
		deger = oac.glb.setting_oku("STK_IRS_FIR_BILGI").toString();
		comboBox_1.setSelectedItem(deger);
		
		deger = oac.glb.setting_oku("STK_IRS_GIRIS").toString();
			lblNewLabel_3_2_1_1_2.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_2_1_1_2.setFont(bigFont);
		
		deger = oac.glb.setting_oku("STK_IRS_SATIR").toString();
		textField_2.setText(deger);
		deger = oac.glb.setting_oku("STK_RAPORLAMA").toString();
			lblNewLabel_3_10_1_1.setText(deger);
			deger = deger.substring(1, deger.length()-1);
			parts = deger.split(",");
			bigFont = new Font(parts[0], Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim()));
			lblNewLabel_3_10_1_1.setFont(bigFont);
		
		deger = oac.glb.setting_oku("STK_ENV_KUS").toString();
		if (deger.equals("-1"))
		{
			chckbxNewCheckBox_1_1.setSelected(false);
		}
		else
		{
			chckbxNewCheckBox_1_1.setSelected(true);
		}
		deger = oac.glb.setting_oku("STK_ENV_YAZ").toString();
		if (deger.equals("-1"))
		{
			chckbxNewCheckBox_1_2.setSelected(false);
		}
		else
		{
			chckbxNewCheckBox_1_2.setSelected(true);
		}
		deger = oac.glb.setting_oku("STK_IMA_BAS_TAR").toString();
		dateChooser.setDate(TARIH_CEVIR.tarih(deger));
		//************************* GUNLUK ***********************************
		deger = oac.glb.setting_oku("GUN_KON").toString();
		if (deger.equals("-1"))
		{
			chckbxNewCheckBox_1_3.setSelected(false);
		}
		else
		{
			chckbxNewCheckBox_1_3.setSelected(true);
		}
		deger = oac.glb.setting_oku("GUN_KON_ZAM").toString();
		textField_3.setText(deger);
		deger = oac.glb.setting_oku("GUN_SER_KON").toString();
		if (deger.equals("-1"))
		{
			chckbxNewCheckBox_1_3_1.setSelected(false);
		}
		else
		{
			chckbxNewCheckBox_1_3_1.setSelected(true);
		}
		deger = oac.glb.setting_oku("GUN_SER_KON_ZAM").toString();
		textField_4.setText(deger);
		//****************************KAMBIYO *******************************
		deger = oac.glb.setting_oku("KAM_CEK_GIR").toString();
		textField_5.setText(deger);
		deger = oac.glb.setting_oku("KAM_CEK_CIK").toString();
		textField_6.setText(deger);
		deger = oac.glb.setting_oku("KAM_SEN_GIR").toString();
		textField_7.setText(deger);
		deger = oac.glb.setting_oku("KAM_SEN_CIK").toString();
		textField_8.setText(deger);
		
		deger = oac.glb.setting_oku("KAM_SAT_KOP").toString();
			parts = deger.split(",");
			if (parts[0].equals("E")) {chckbxNewCheckBox_2_2_1_1_1.setSelected(true);} else {chckbxNewCheckBox_2_2_1_1_1.setSelected(false);}
			if (parts[1].equals("E")) {chckbxNewCheckBox_2_1_1_1_1_1.setSelected(true);} else {chckbxNewCheckBox_2_1_1_1_1_1.setSelected(false);}
			comboBox_2_1_1_1_1.setSelectedItem(parts[2]);
		deger = oac.glb.setting_oku("KAM_SAT_YAP").toString();
			parts = deger.split(",");
			if (parts[0].equals("E")) {chckbxNewCheckBox_2_2_1_2_1.setSelected(true);} else {chckbxNewCheckBox_2_2_1_2_1.setSelected(false);}
			if (parts[1].equals("E")) {chckbxNewCheckBox_2_1_1_1_2_1.setSelected(true);} else {chckbxNewCheckBox_2_1_1_1_2_1.setSelected(false);}
			comboBox_2_1_1_2_1.setSelectedItem(parts[2]);
		//*********************PROGRAM *************************************
		deger = oac.glb.setting_oku("PRG_PARA").toString();
		textField_9.setText(deger);
		deger = oac.glb.setting_oku("PRG_FILTRE").toString();
			parts = deger.split(",");
			if (parts[0].equals("E")) {chckbxNewCheckBox_2_2_1_1.setSelected(true);} else {chckbxNewCheckBox_2_2_1_1.setSelected(false);}
			if (parts[1].equals("E")) {chckbxNewCheckBox_2_1_1_1_1.setSelected(true);} else {chckbxNewCheckBox_2_1_1_1_1.setSelected(false);}
			comboBox_2_1_1_1.setSelectedItem(parts[2]);
		deger = oac.glb.setting_oku("PRG_YENILE").toString();
			parts = deger.split(",");
			if (parts[0].equals("E")) {chckbxNewCheckBox_2_2_1_2.setSelected(true);} else {chckbxNewCheckBox_2_2_1_2.setSelected(false);} 
			if (parts[1].equals("E")) {chckbxNewCheckBox_2_1_1_1_2.setSelected(true);} else {chckbxNewCheckBox_2_1_1_1_2.setSelected(false);} 
			comboBox_2_1_1_2.setSelectedItem(parts[2]);
			
		deger = oac.glb.setting_oku("PRG_KAYDET").toString();
			parts = deger.split(",");
			if (parts[0].equals("E")) {chckbxNewCheckBox_2_2_1_2_2.setSelected(true);} else {chckbxNewCheckBox_2_2_1_2_2.setSelected(false);} 
			if (parts[1].equals("E")) {chckbxNewCheckBox_2_1_1_1_2_2.setSelected(true);} else {chckbxNewCheckBox_2_1_1_1_2_2.setSelected(false);} 
			comboBox_2_1_1_2_2.setSelectedItem(parts[2]);
		deger = oac.glb.setting_oku("PRG_SIL").toString();
			parts = deger.split(",");
			if (parts[0].equals("E")) {chckbxNewCheckBox_2_2_1_2_3.setSelected(true);} else {chckbxNewCheckBox_2_2_1_2_3.setSelected(false);} 
			if (parts[1].equals("E")) {chckbxNewCheckBox_2_1_1_1_2_3.setSelected(true);} else {chckbxNewCheckBox_2_1_1_1_2_3.setSelected(false);} 
			comboBox_2_1_1_2_3.setSelectedItem(parts[2]);
		deger = oac.glb.setting_oku("PRG_GORUNUM").toString();
		comboBox_2.setSelectedItem(deger);
			
			deger = oac.glb.setting_oku("PRG_GRID_RENK").toString();
			parts = deger.split(",");
			colorChooser.setSelectedColor( new Color( Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()), Integer.parseInt(parts[2].trim())));

			getContentPane().setCursor(DEFAULT_CURSOR);
		} catch (Exception ex) 
		{
			getContentPane().setCursor(DEFAULT_CURSOR);
          	 JOptionPane.showMessageDialog(null, ex.getMessage(), "Parametre Okuma", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	public static  void kayit()
	{
		try {
		String deger = "" ;
		oac.glb.setting_yaz("CARI_DEKONT_BAKIYE_GOSTER", chckbxNewCheckBox.isSelected() ?  "0" : "-1");
		oac.glb.setting_yaz("CARI_ARAMA", lblNewLabel_3.getText());
		
		deger = (chckbxNewCheckBox_2_2.isSelected() ?  "E" : "" ) + "," + (chckbxNewCheckBox_2_1_1.isSelected() ?  "E" : "" ) + "," + (comboBox_2_1.getSelectedItem());
		oac.glb.setting_yaz("CARI_DEK_Y_FIS", deger);
		
		oac.glb.setting_yaz("CARI_DVZ_CEV", lblNewLabel_3_3.getText());
		
		oac.glb.setting_yaz("CARI_EKSTRE", lblNewLabel_3_4.getText());
		
		
		oac.glb.setting_yaz("CARI_GUN_IS", lblNewLabel_3_5.getText());
		
	
		oac.glb.setting_yaz("CARI_HES_BAK", lblNewLabel_3_6.getText());
		
		
		oac.glb.setting_yaz("CARI_HSPPLN", lblNewLabel_3_7.getText());
		
		deger = (chckbxNewCheckBox_2_2_1.isSelected() ?  "E" : "" ) + "," + (chckbxNewCheckBox_2_1_1_1.isSelected() ?  "E" : "" ) + "," + (comboBox_2_1_1.getSelectedItem());
		oac.glb.setting_yaz("CARI_HSPPLN_CAG", deger);
		
		
		oac.glb.setting_yaz("CARI_KARTON", lblNewLabel_3_8.getText());
		
		
		oac.glb.setting_yaz("CARI_MIZAN", lblNewLabel_3_9.getText());
		
		deger = textField.getText();
		oac.glb.setting_yaz("CARI_MIZ_GRUP", deger);
		//************************************************ STOK FAT*******************
		
		oac.glb.setting_yaz("STK_FAT_ABILGI", lblNewLabel_3_10.getText());
		oac.glb.setting_yaz("STK_FAT_BASLIK", lblNewLabel_3_1_1.getText());
		oac.glb.setting_yaz("STK_FAT_DETAY", lblNewLabel_3_2_1.getText());
		deger =  comboBox.getItemAt(comboBox.getSelectedIndex());
		oac.glb.setting_yaz("STK_FAT_FIR_BILGI", deger);
		oac.glb.setting_yaz("STK_FAT_GIRIS",lblNewLabel_3_2_1_1.getText());
		deger = textField_1.getText();
		oac.glb.setting_yaz("STK_FAT_SATIR", deger);
		oac.glb.setting_yaz("STK_STOK_KONTROL", chckbxNewCheckBox_1.isSelected() ?  "0" : "-1");
		oac.glb.setting_yaz("STK_YAZI_ILE",lblNewLabel_3_2_1_1_1.getText());
		//************************************************ STOK IRS*******************
		
		oac.glb.setting_yaz("STK_IRS_ABILGI", lblNewLabel_3_10_1.getText());
		
		
		oac.glb.setting_yaz("STK_IRS_BASLIK", lblNewLabel_3_1_1_1.getText());
		
	
		oac.glb.setting_yaz("STK_IRS_DETAY", lblNewLabel_3_2_1_2.getText());
		
		deger =  comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
		oac.glb.setting_yaz("STK_IRS_FIR_BILGI", deger);
		
		
		oac.glb.setting_yaz("STK_IRS_GIRIS",  lblNewLabel_3_2_1_1_2.getText());
		
		deger = textField_2.getText();
		oac.glb.setting_yaz("STK_IRS_SATIR", deger);
		//************************************************ STOK STK
		oac.glb.setting_yaz("STK_RAPORLAMA", lblNewLabel_3_10_1_1.getText());
		
		oac.glb.setting_yaz("STK_ENV_KUS", chckbxNewCheckBox_1_1.isSelected() ?  "0" : "-1");
		
		oac.glb.setting_yaz("STK_ENV_YAZ", chckbxNewCheckBox_1_2.isSelected() ?  "0" : "-1");
		
		oac.glb.setting_yaz("STK_IMA_BAS_TAR", TARIH_CEVIR.tarih_dt_ddMMyyyy(dateChooser));
		
		//***********************GUNLUK ******************************
		
		oac.glb.setting_yaz("GUN_KON", chckbxNewCheckBox_1_3.isSelected() ?  "0" : "-1");
		
		deger = textField_3.getText();
		oac.glb.setting_yaz("GUN_KON_ZAM", deger);
		
		oac.glb.setting_yaz("GUN_SER_KON", chckbxNewCheckBox_1_3_1.isSelected() ?  "0" : "-1");
		
		deger = textField_4.getText();
		oac.glb.setting_yaz("GUN_SER_KON_ZAM", deger);
		
		//*****************KAMBIYO***************************
		deger = textField_5.getText();
		oac.glb.setting_yaz("KAM_CEK_GIR", deger);
		
		deger = textField_6.getText();
		oac.glb.setting_yaz("KAM_CEK_CIK", deger);
		
		deger = textField_7.getText();
		oac.glb.setting_yaz("KAM_SEN_GIR", deger);
		
		deger = textField_8.getText();
		oac.glb.setting_yaz("KAM_SEN_CIK", deger);
		
		deger = (chckbxNewCheckBox_2_2_1_1_1.isSelected() ?  "E" : "" ) + "," + (chckbxNewCheckBox_2_1_1_1_1_1.isSelected() ?  "E" : "" ) + "," + (comboBox_2_1_1_1_1.getSelectedItem());
		oac.glb.setting_yaz("KAM_SAT_KOP", deger);
		
		deger = (chckbxNewCheckBox_2_2_1_2_1.isSelected() ?  "E" : "" ) + "," + (chckbxNewCheckBox_2_1_1_1_2_1.isSelected() ?  "E" : "" ) + "," + (comboBox_2_1_1_2_1.getSelectedItem());
		oac.glb.setting_yaz("KAM_SAT_YAP", deger);
		
		//****************PROGRAM ****************************
		deger = textField_9.getText();
		oac.glb.setting_yaz("PRG_PARA", deger);
		
		deger = (chckbxNewCheckBox_2_2_1_1.isSelected() ?  "E" : "" ) + "," + (chckbxNewCheckBox_2_1_1_1_1.isSelected() ?  "E" : "" ) + "," + (comboBox_2_1_1_1.getSelectedItem());
		oac.glb.setting_yaz("PRG_FILTRE", deger);
		
		deger = (chckbxNewCheckBox_2_2_1_2.isSelected() ?  "E" : "" ) + "," + (chckbxNewCheckBox_2_1_1_1_2.isSelected() ?  "E" : "" ) + "," + (comboBox_2_1_1_2.getSelectedItem());
		oac.glb.setting_yaz("PRG_YENILE", deger);
		
		deger = (chckbxNewCheckBox_2_2_1_2_2.isSelected() ?  "E" : "" ) + "," + (chckbxNewCheckBox_2_1_1_1_2_2.isSelected() ?  "E" : "" ) + "," + (comboBox_2_1_1_2_2.getSelectedItem());
		oac.glb.setting_yaz("PRG_KAYDET", deger);
		
		deger = (chckbxNewCheckBox_2_2_1_2_3.isSelected() ?  "E" : "" ) + "," + (chckbxNewCheckBox_2_1_1_1_2_3.isSelected() ?  "E" : "" ) + "," + (comboBox_2_1_1_2_3.getSelectedItem());
		oac.glb.setting_yaz("PRG_SIL", deger);
		
		deger = comboBox_2.getItemAt(comboBox_2.getSelectedIndex());
		oac.glb.setting_yaz("PRG_GORUNUM", deger);
	
		
		deger = Integer.toString(colorChooser.getSelectedColor().getRed() ) + "," + Integer.toString(colorChooser.getSelectedColor().getGreen()) + "," + Integer.toString(colorChooser.getSelectedColor().getBlue());
		oac.glb.setting_yaz("PRG_GRID_RENK", deger);
		
				OBS_SIS_2025_ANA_CLASS. gridcolor =  colorChooser.getSelectedColor();
		
		} 
		catch (Exception ex) 
		{
          	 JOptionPane.showMessageDialog(null, ex.getMessage(), "Parametre Kayit", JOptionPane.ERROR_MESSAGE);
		}
	}
	public static String dateFormater(String dateFromJSON, String expectedFormat, String oldFormat) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
		Date date = null;
		String convertedDate = null;
		try {
			date = dateFormat.parse(dateFromJSON);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(expectedFormat);
			convertedDate = simpleDateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertedDate;
	}
	 
	 
}



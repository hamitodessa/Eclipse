package obs.backup.main;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import OBS_C_2025.GLOBAL;
import obs.backup.gorev.gOREV_TAKIP;
import obs.backup.other.Title_Bar;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class OBS_BACKUP extends JFrame {

	GLOBAL glb = new GLOBAL();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel container ;
	private JPanel panel_2 ;
	private JTabbedPane tabbedPane ;
	int x ,y ;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	/**
	 * Hamit.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OBS_BACKUP frame = new OBS_BACKUP();
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
	public OBS_BACKUP() {
		setUndecorated(true);
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("obs.backup.theme");
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
		FlatMacDarkLaf.setup();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX(); 
				y = e.getY(); 
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xx = e.getXOnScreen();
				int yy = e.getYOnScreen(); 
				setLocation(xx-x,yy-y);
			}
		});
		
		setBounds(100, 100, 900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		contentPane.add(new Title_Bar(this), BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(150,0));
		splitPane.setLeftComponent(panel);
		
		JButton btnGorevler = new JButton("Gorevler");
		btnGorevler.setPreferredSize(new Dimension(0,25));
		btnGorevler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				container.removeAll();
					gOREV_TAKIP x = new gOREV_TAKIP("hamit");
					//x.setBounds(0, 0, 400, 100);
					
					container.add(x);
					gOREV_TAKIP x2 = new gOREV_TAKIP("aden");
					//x2.setBounds(0, 110, 400, 100);
					container.add(x2);
//				for (int i = 0; i < 15; i++) {
//					gOREV_TAKIP x = new gOREV_TAKIP(String.valueOf(i + 1));
//			          x.setLocation(0, 45 *i);
//			         container.add(x);
//			      }
					pack();
				}
			});
		panel.setLayout(new GridLayout(20, 1, 0, 0));
		panel.add(btnGorevler);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Component[] components = container.getComponents();

				for (Component component : components) {
					System.out.println(component.getName());
					if (component.getName().toString().equals("hamit")) {


						JPanel qweJPanel = (JPanel) component ; 

						Component[] componentt = qweJPanel.getComponents();
						for (Component compo : componentt) {

							System.out.println(compo.getName());
						}

					}


					// }
				}
			}
		});
		
		JButton btnYeni_Gorev = new JButton("Yeni Gorev");
		btnYeni_Gorev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		panel.add(btnYeni_Gorev);
		btnNewButton_1.setPreferredSize(new Dimension(0,25));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("alt pan");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(false);
			}
		});
		panel.add(btnNewButton);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Gorevler", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(1.0);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane_1, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 128, 128));
		panel_2.setPreferredSize(new Dimension(0,75));
		splitPane_1.setRightComponent(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		
		splitPane_1.setLeftComponent(scrollPane);
		
		container = new JPanel(); 
		scrollPane.setViewportView(container);
	
		container.setLayout(new GridLayout(10, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Yeni Gorev", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel_3.add(tabbedPane_1, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		tabbedPane_1.addTab("Genel", null, panel_4, null);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_2 = new JSplitPane();
		panel_4.add(splitPane_2, BorderLayout.CENTER);
		
		
		JPanel panel_10 = new JPanel();
		splitPane_2.setRightComponent(panel_10);
		panel_10.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Durum");
		lblNewLabel.setBounds(26, 68, 86, 14);
		panel_10.add(lblNewLabel);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(150, 68, 99, 23);
		panel_10.add(chckbxNewCheckBox);
		
		JLabel lblNewLabel_1 = new JLabel("Emir Ismi");
		lblNewLabel_1.setBounds(26, 100, 99, 14);
		panel_10.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(150, 100, 219, 20);
		panel_10.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Aciklama");
		lblNewLabel_2.setBounds(26, 150, 99, 14);
		panel_10.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(150, 150, 219, 77);
		panel_10.add(textField_1);
		textField_1.setColumns(10);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Sql Server / Diger Dosya Yedekleme");
		chckbxNewCheckBox_1.setToolTipText("");
		chckbxNewCheckBox_1.setBounds(150, 302, 250, 23);
		panel_10.add(chckbxNewCheckBox_1);
		
		JLabel lblNewLabel_4 = new JLabel("Dosya Sayisi");
		lblNewLabel_4.setBounds(26, 373, 100, 14);
		panel_10.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("0");
		lblNewLabel_5.setBounds(150, 373, 48, 14);
		panel_10.add(lblNewLabel_5);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(317, 579, 89, 23);
		panel_10.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBounds(218, 579, 89, 23);
		panel_10.add(btnNewButton_5);
		
		JPanel panel_9 = new JPanel();
		panel_9.setPreferredSize(new Dimension(300,0));
		splitPane_2.setLeftComponent(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_9.add(splitPane_3, BorderLayout.CENTER);
		
		JPanel panel_11 = new JPanel();
		panel_11.setPreferredSize(new Dimension(0,75));
		splitPane_3.setLeftComponent(panel_11);
		panel_11.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel(".....");
		lblNewLabel_6.setBounds(10, 11, 121, 14);
		panel_11.add(lblNewLabel_6);
		
		JButton btnNewButton_2 = new JButton("Dosya Sec");
		btnNewButton_2.setBounds(2, 41, 145, 30);
		panel_11.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Surucu Sec");
		btnNewButton_3.setBounds(148, 41, 145, 30);
		panel_11.add(btnNewButton_3);
		
		JPanel panel_5 = new JPanel();
		tabbedPane_1.addTab("Surucu Ayarlari", null, panel_5, null);
		panel_5.setLayout(null);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("FTP");
		chckbxNewCheckBox_2.setBounds(67, 22, 99, 23);
		panel_5.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Yerel Surucu");
		chckbxNewCheckBox_3.setBounds(215, 22, 150, 23);
		panel_5.add(chckbxNewCheckBox_3);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(null, "FTP Ayarlari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_12.setBounds(10, 73, 500, 100);
		panel_5.add(panel_12);
		panel_12.setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setBounds(124, 22, 300, 20);
		panel_12.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(124, 46, 96, 20);
		panel_12.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(124, 69, 96, 20);
		panel_12.add(textField_4);
		textField_4.setColumns(10);
		
		JPanel panel_12_1 = new JPanel();
		panel_12_1.setLayout(null);
		panel_12_1.setBorder(new TitledBorder(null, "FTP Ayarlari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_12_1.setBounds(10, 174, 500, 100);
		panel_5.add(panel_12_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(124, 22, 300, 20);
		panel_12_1.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(124, 46, 96, 20);
		panel_12_1.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(124, 69, 96, 20);
		panel_12_1.add(textField_7);
		
		JButton btnNewButton_6 = new JButton("New button");
		btnNewButton_6.setBounds(401, 68, 89, 23);
		panel_12_1.add(btnNewButton_6);
		
		JPanel panel_12_1_1 = new JPanel();
		panel_12_1_1.setLayout(null);
		panel_12_1_1.setBorder(new TitledBorder(null, "FTP Ayarlari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_12_1_1.setBounds(10, 276, 500, 75);
		panel_5.add(panel_12_1_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(124, 22, 300, 20);
		panel_12_1_1.add(textField_8);
		
		JButton btnNewButton_7 = new JButton("New button");
		btnNewButton_7.setBounds(401, 47, 89, 23);
		panel_12_1_1.add(btnNewButton_7);
		
		JPanel panel_12_1_1_1 = new JPanel();
		panel_12_1_1_1.setLayout(null);
		panel_12_1_1_1.setBorder(new TitledBorder(null, "FTP Ayarlari", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_12_1_1_1.setBounds(10, 367, 500, 75);
		panel_5.add(panel_12_1_1_1);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(124, 22, 300, 20);
		panel_12_1_1_1.add(textField_9);
		
		JButton btnNewButton_7_1 = new JButton("New button");
		btnNewButton_7_1.setBounds(401, 47, 89, 23);
		panel_12_1_1_1.add(btnNewButton_7_1);
		
		JButton btnNewButton_8 = new JButton("New button");
		btnNewButton_8.setBounds(10, 581, 89, 23);
		panel_5.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("New button");
		btnNewButton_9.setBounds(624, 581, 89, 23);
		panel_5.add(btnNewButton_9);
		
		JPanel panel_6 = new JPanel();
		tabbedPane_1.addTab("Bilgilendirme", null, panel_6, null);
		
		JPanel panel_7 = new JPanel();
		tabbedPane_1.addTab("Yedekleme Araligi", null, panel_7, null);
		
		JPanel panel_8 = new JPanel();
		tabbedPane_1.addTab("Emir Kopyala", null, panel_8, null);
		
	 	try {
			glb.backup_surucu_kontrol();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	public static void okuma(String isim)
	{
		System.out.println(isim);
	}
	@Override
	   public Dimension getPreferredSize() {
	      Dimension superSz = super.getPreferredSize();
	      if (isPreferredSizeSet()) {
	         return superSz;
	      }
	      return new Dimension(900, 700);
	   }
}

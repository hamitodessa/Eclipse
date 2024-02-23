package OBS_2025;

import javax.swing.JInternalFrame;

import com.formdev.flatlaf.FlatLaf;

import OBS_C_2025.MaterialTabbed;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import OBS_C_2025.CARI_ACCESS;
import OBS_C_2025.FIT_IMAGE;
import OBS_C_2025.ImagePanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

@SuppressWarnings("static-access")
public class TAH_FISI extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	static OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	static CARI_ACCESS c_Access = new CARI_ACCESS(OBS_SIS_2025_ANA_CLASS._ICar , OBS_SIS_2025_ANA_CLASS._ICari_Loger);

	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TAH_FISI() {
		FlatLaf.registerCustomDefaultsSource("OBS_2025");
		setResizable(true);

		setTitle("TAHSILAT");
		setClosable(true);
		setBounds(100, 100, 800, 400);
		
		MaterialTabbed tabbedPane = new MaterialTabbed();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Tahsilat Fisi", null, panel, null);
		
		JPanel panel_Ayarlar = new JPanel();
		tabbedPane.addTab("Ayarlar", null, panel_Ayarlar, null);
		panel_Ayarlar.setLayout(null);
		
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setBorder(new LineBorder(new Color(95, 158, 160), 2,true));
		imagePanel.setBounds(500, 25, 250, 150);
		panel_Ayarlar.add(imagePanel);
		
		textField = new JTextField();
		textField.setBounds(101, 25, 350, 20);
		panel_Ayarlar.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(101, 50, 350, 20);
		panel_Ayarlar.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(101, 75, 350, 20);
		panel_Ayarlar.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(101, 100, 350, 20);
		panel_Ayarlar.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(101, 125, 350, 20);
		panel_Ayarlar.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(101, 150, 350, 20);
		panel_Ayarlar.add(textField_5);
		
		JLabel lblNewLabel = new JLabel("FIRMA ADI");
		lblNewLabel.setBounds(10, 28, 81, 14);
		panel_Ayarlar.add(lblNewLabel);
		
		JLabel lblAdres = new JLabel("ADRES 1");
		lblAdres.setBounds(10, 53, 81, 14);
		panel_Ayarlar.add(lblAdres);
		
		JLabel lblAdres_1 = new JLabel("ADRES 2");
		lblAdres_1.setBounds(10, 78, 81, 14);
		panel_Ayarlar.add(lblAdres_1);
		
		JLabel lblVdVn = new JLabel("VD - VN");
		lblVdVn.setBounds(10, 103, 81, 14);
		panel_Ayarlar.add(lblVdVn);
		
		JLabel lblEMail = new JLabel("E MAIL");
		lblEMail.setBounds(10, 128, 81, 14);
		panel_Ayarlar.add(lblEMail);
		
		JLabel lblDiger = new JLabel("DIGER");
		lblDiger.setBounds(10, 153, 81, 14);
		panel_Ayarlar.add(lblDiger);
		
		JButton btnNewButton_4 = new JButton("Sec");
		btnNewButton_4.setBounds(500, 186, 73, 23);
		btnNewButton_4.setMargin(new Insets(2, 1, 2, 14));
		btnNewButton_4.setIcon(new ImageIcon(H_PLANI.class.getResource("/ICONLAR/icons8-camera-16.png")));
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);

				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Resim Seciniz");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Resim Sec");
				chooser.setApproveButtonToolTipText("Resim Sec");
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("Resim Dosyalari", "jpg", "png", "gif", "bmp"));
				chooser.setApproveButtonMnemonic('s');
				getContentPane().setCursor(oac.DEFAULT_CURSOR);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					File file = chooser.getSelectedFile();
					ImageIcon icon = new ImageIcon(file.getPath());
					ImageIcon imageIcon = new ImageIcon(FIT_IMAGE.image(icon.getImage(), 250, 150));
					BufferedImage bi = new BufferedImage(imageIcon .getIconWidth(), imageIcon .getIconHeight(), BufferedImage.TYPE_INT_RGB);
					Graphics2D g = bi.createGraphics();
					imageIcon.paintIcon(null, g, 0, 0);
					g.setColor(Color.WHITE);
					g.dispose();
					imagePanel.setImage(bi);
				}
				else {
				}
			}
		});
		panel_Ayarlar.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Temizle");
		btnNewButton_5.setMargin(new Insets(2, 5, 2, 14));
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_5.setBounds(677, 186, 73, 23);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imagePanel.setImage(null);
			}
		});
		panel_Ayarlar.add(btnNewButton_5);

	}
}

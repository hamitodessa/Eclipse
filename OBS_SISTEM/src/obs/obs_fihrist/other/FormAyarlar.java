package obs.obs_fihrist.other;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import OBS_C_2025.ScrollPaneWin11;
import java.awt.Color;

@SuppressWarnings("serial")
public class FormAyarlar extends javax.swing.JPanel{

	private static JCheckBox chckbxS ;
	private static JCheckBox chckbxL ;
	private static JComboBox<String> cmbInstance ;
	private static JComboBox<String> cmbip ;
	private static JComboBox<String> cmbhangisql ;

	private JButton btnServer ;
	private JButton btnVtKontrol ;
	
	private static JTextField txtIp;
	private static JTextField txtUser;
	private static JTextField txtcdid;
	private static JPasswordField txtPwd;
	private static JTextField txtKodu;
	private JTable table_1;
	 
	 public FormAyarlar() {
	 	setLayout(new BorderLayout(0, 0));
	 	
	 	add(new Title_Bar(), BorderLayout.NORTH);

	 	
	 	JSplitPane splitPane = new JSplitPane();
	 	add(splitPane, BorderLayout.CENTER);
	   
	 	
	 	ScrollPaneWin11 scrollPane_3 = new ScrollPaneWin11();
		scrollPane_3.setMinimumSize(new Dimension(300, 0));
		scrollPane_3.setMaximumSize(new Dimension(300, 0));


		splitPane.setLeftComponent(scrollPane_3);

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(290,580));
		scrollPane_3.setViewportView(panel_3);
		panel_3.setLayout(null);

		cmbhangisql = new JComboBox<String>();
		cmbhangisql.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String hangi = cmbhangisql.getItemAt(cmbhangisql.getSelectedIndex())  ;
				if (hangi == "MS SQL")
				{
					chckbxS.setEnabled(true);
					chckbxL.setEnabled(true);
					cmbInstance.setEnabled(true);
					cmbip.setEnabled(true);
					txtIp.setEnabled(true);
					txtUser.setEnabled(true);
					txtPwd.setEnabled(true);
					btnServer.setEnabled(true);
				}
				else if (hangi == "MY SQL")
				{
					chckbxS.setEnabled(true);
					chckbxL.setEnabled(true);
					cmbInstance.setEnabled(false);
					cmbip.setEnabled(true);
					txtIp.setEnabled(true);
					txtUser.setEnabled(true);
					txtPwd.setEnabled(true);
					btnServer.setEnabled(true);
				}
				else if (hangi == "SQ LITE")
				{
					chckbxS.setEnabled(false);
					chckbxL.setEnabled(true);
					cmbInstance.setEnabled(false);
					cmbip.setEnabled(false);
					txtIp.setEnabled(false);
					txtUser.setEnabled(false);
					txtPwd.setEnabled(false);
					btnServer.setEnabled(false);
					btnVtKontrol.setEnabled(true);
				}
			}
		});
		cmbhangisql.setModel(new DefaultComboBoxModel<String>(new String[] {"MS SQL", "MY SQL", "SQ LITE"}));
		cmbhangisql.setForeground(new Color(0, 0, 139));
		cmbhangisql.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbhangisql.setBounds(88, 55, 157, 22);
		panel_3.add(cmbhangisql);

		JLabel lblNewLabel_1_1 = new JLabel("Kodu");
		lblNewLabel_1_1.setBounds(10, 88, 68, 14);
		panel_3.add(lblNewLabel_1_1);

		txtKodu = new JTextField();
		txtKodu.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtKodu.setColumns(10);
		txtKodu.setBounds(88, 83, 78, 20);
		panel_3.add(txtKodu);

		chckbxL = new JCheckBox("Lokal");
		chckbxL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxL.isSelected())
				{
					chckbxS.setSelected(false);
				}
				else
				{
					chckbxS.setSelected(true);
				}
			}
		});
		chckbxL.setSelected(true);
		chckbxL.setBounds(88, 107, 65, 23);
		panel_3.add(chckbxL);

		chckbxS = new JCheckBox("Server");
		chckbxS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxS.isSelected())
				{
					chckbxL.setSelected(false);
				}
				else
				{
					chckbxL.setSelected(true);
				}
			}
		});
		chckbxS.setSelected(false);
		chckbxS.setBounds(167, 107, 78, 23);
		panel_3.add(chckbxS);

		JLabel lblInstance = new JLabel("Instance");
		lblInstance.setBounds(10, 160, 68, 14);
		panel_3.add(lblInstance);

		cmbInstance = new JComboBox<String>();
		cmbInstance.setEditable(true);
		cmbInstance.setBounds(88, 155, 157, 22);
		panel_3.add(cmbInstance);

		JLabel lblServer = new JLabel("Server / Port");
		lblServer.setBounds(10, 185, 78, 14);
		panel_3.add(lblServer);

		txtIp = new JTextField();
		txtIp.setText("");
		txtIp.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtIp.setColumns(10);
		txtIp.setBounds(88, 180, 157, 20);
		panel_3.add(txtIp);

		JLabel lblKayitserver = new JLabel("Serverler");
		lblKayitserver.setBounds(10, 210, 78, 14);
		panel_3.add(lblKayitserver);

		cmbip = new JComboBox<String>();
		cmbip.setFont(new Font("Tahoma", Font.BOLD, 11));
		cmbip.setEditable(true);
		cmbip.setBounds(88, 205, 157, 22);
		panel_3.add(cmbip);

		JLabel lblSifre = new JLabel("Kullanici");
		lblSifre.setBounds(10, 240, 68, 14);
		panel_3.add(lblSifre);

		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtUser.setColumns(10);
		txtUser.setBounds(88, 235, 157, 20);
		panel_3.add(txtUser);

		JLabel lblKullanici = new JLabel("Sifre");
		lblKullanici.setBounds(10, 263, 68, 14);
		panel_3.add(lblKullanici);

		txtPwd = new JPasswordField();
		txtPwd.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPwd.setBounds(88, 258, 157, 20);
		panel_3.add(txtPwd);

		txtcdid = new JTextField();
		txtcdid.setBounds(10, 108, 32, 20);
		txtcdid.setText("");
		panel_3.add(txtcdid);
	       
		
		///////AYAR TABLO***********************
		ScrollPaneWin11 scrollPane_4 = new ScrollPaneWin11();
		splitPane.setRightComponent(scrollPane_4);

		table_1 = new JTable(){
			public boolean isCellEditable(int row, int column) {     return false;          }
		};
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (!lse.getValueIsAdjusting()) {
					if (table_1.getRowCount() == 0) return ;
					if (table_1.getSelectedRow()  < 0) return;
					try {
						//kutu_temizle();
						//doldur_kutu(table_1,table_1.getSelectedRow());
					} catch (Exception e1) {
						//mesaj_goster(5000,Notifications.Type.ERROR, e1.getMessage());
					}
				}
			}
		});
		table_1.setShowHorizontalLines(true);
		table_1.setShowVerticalLines(true);
		table_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrollPane_4.setViewportView(table_1);

	    }
}

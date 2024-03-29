package OBS_2025;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.MaskFormatter;

import com.crystaldecisions.reports.exporters.format.page.pdf.pdflib.PdfEncryptionSession.Version;
import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;
import com.healthmarketscience.jackcess.crypt.CryptCodecProvider;

import LOGER_KAYIT.TXT_LOG;
import OBS_C_2025.BACKUP_RESTORE;
import OBS_C_2025.BadgeButton;


import OBS_C_2025.ENCRYPT_DECRYPT_STRING;

import OBS_C_2025.SearchOption;
import OBS_C_2025.TextFieldSearchOption;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bridj.Pointer;

import OBS_C_2025.TextFieldSearchOption.*;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;


@SuppressWarnings({"serial","static-access","unused","unchecked"})
public class DENEMELER extends JInternalFrame {
	OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	BadgeButton badgeButton1 ;
	private JTextField textField;
	
	
	@SuppressWarnings("rawtypes")
	public DENEMELER() {
		setClosable(true);
		setBounds(100, 100, 800, 600);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		
		
		JButton btnNewButton_5 = new JButton("Access");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				//InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/HSP_PLN.rpt");
				//Files.copy(is, Paths.get("C:\\OBS_SISTEM\\HSP_PLN.rpt"),StandardCopyOption.REPLACE_EXISTING);
				
				
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				} catch (ClassNotFoundException e2) {
				System.out.println("149");
					e2.printStackTrace();
				}
				Connection conn;
				try {
					
					conn = DriverManager.getConnection(
					        "jdbc:ucanaccess://C:/OBS_SISTEM/OBS_SISTEM_2025.MDB","","oOk271972");
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM [USERS]");
				  System.out.println("------------------------------------");
				while (rs.next()) {
				    System.out.println(rs.getString(1) + "-" + rs.getString(2));
				}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_5.setBounds(503, 107, 89, 23);
		panel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("accdb");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("C:/OBS_SISTEM/test.accdb");
				try {
					String password = "obs";
				

					Database db = new DatabaseBuilder(file)
							.setCodecProvider(new CryptCodecProvider(password))
							.setFileFormat(Database.FileFormat.V2010)
							.create();
						Table table = new TableBuilder("Test")
							.addColumn(new ColumnBuilder("ID", DataType.LONG)
									.setAutoNumber(true))
							.addColumn(new ColumnBuilder("Name", DataType.TEXT))
							.addColumn(new ColumnBuilder("Salary", DataType.MONEY))
							.addColumn(new ColumnBuilder("StartDate", DataType.SHORT_DATE_TIME))
							.toTable(db);

					db.close();
					
					////
					Connection conn = null;
			        Statement stmt = null;
					   // JDBC URL for UCanAccess with user and password
		            String url = "jdbc:ucanaccess://C:/OBS_SISTEM/test.accdb;singleConnection=true;user=admin;password=obs";

		            // Establish connection
		            conn = DriverManager.getConnection(url);

		            // Create a Statement
		            stmt = conn.createStatement();

		            // Set a password for the database
		            stmt.execute("ALTER DATABASE PASSWORD 'obs'");
					///
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton_6.setBounds(503, 141, 89, 23);
		panel.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("oku");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  
					
					
					  try {
							Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
						Connection conn;
						
							conn = DriverManager.getConnection(
								        "jdbc:ucanaccess://C:/OBS_SISTEM/test.accdb","","pop");
							Statement s = conn.createStatement();
							ResultSet rs = s.executeQuery("SELECT * FROM test");
							  System.out.println("------------------------------------");
								while (rs.next()) {
								    System.out.println(rs.getString(1) + "-" + rs.getString(2));
								}
						} catch (Exception e1) {
							System.out.println(e1);
						}
			}
		});
		btnNewButton_7.setBounds(503, 175, 89, 23);
		panel.add(btnNewButton_7);
		
		JButton btnNewButton = new JButton("Sifre");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qwe;
				 try {
//[-128, 112, -83, -117, 30, 48, -23, -38, 87, 45, -91, 122, 47, -32, -1, 81]    197227oOk
//[12, -101, 24, -121, -123, -79, -97, 31, -27, 81, -82, 32, 3, -67, -68, -100]    obs 
//[-56, 120, -17, -74, -27, 90, 50, -61, -18, -100, -19, 81, -2, 106, -88, -120]  CRy7lGgj
					 byte[]  qaz =	ENCRYPT_DECRYPT_STRING.eNCRYPT_manual("CRy7lGgj") ;
					String response = Arrays.toString(qaz);
					System.out.println(response);
					String[] byteValues = response.substring(1, response.length() - 1).split(",");
					byte[] bytes = new byte[byteValues.length];
					for (int i=0, len=bytes.length; i<len; i++) {
					   bytes[i] = Byte.parseByte(byteValues[i].trim());     
					}
					qwe = 	 ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
					System.out.println(qwe);

					
		      
				} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
						| IOException | IllegalBlockSizeException | BadPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			}
		});
		btnNewButton.setBounds(503, 222, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Backup");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().setCursor(oac.WAIT_CURSOR);
				BACKUP_RESTORE.BackupdbtoMYsql("ok_car019","hamit","197227oOk", "C:/Program Files/MySQL/MySQL Workbench 8.0");
				getContentPane().setCursor(oac.DEFAULT_CURSOR);

			}
		});
		btnNewButton_1.setBounds(503, 260, 89, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("GPT CHAT");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cHATGPT();
			}
		});
		btnNewButton_2.setBounds(503, 294, 89, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("zip file sifre");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			ENCRYPT_DECRYPT_STRING.zip_file_sifrele("C:\\OBS_SISTEM\\BACKUP\\SQL_LOG.MDB", "C:\\OBS_SISTEM\\BACKUP\\SQL_LOG.ZIP","oOk271972");
				

			}
		});
		btnNewButton_3.setBounds(503, 346, 89, 23);
		panel.add(btnNewButton_3);
		
		TextFieldSearchOption txt = new TextFieldSearchOption();
		txt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if (txt.isSelected()) {
					int option = txt.getSelectedIndex();
					if (option==0) {
						System.out.println("0 =" + txt.getText().trim());
					}
					else if (option==1) {
						System.out.println("1 =" + txt.getText().trim());
					}
				}
			}
		});
		txt.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txt.setBounds(503, 409, 204, 38);
		txt.addOption(new SearchOption("Name",new ImageIcon(DENEMELER.class.getResource("/ICONLAR/exit.png"))));
		txt.addOption(new SearchOption("Tel",new ImageIcon(DENEMELER.class.getResource("/ICONLAR/db.png"))));
		txt.setSelectedIndex(0);
		
		txt.addEventOptionSelected(new SearchOptinEvent()
				{
			@Override
			public void optionSelected(SearchOption option , int index) {
				txt.setHint("Search by " + option.getName());
			}
				});
		panel.add(txt);
		
		ComboBoxMultiSelection comboBox = new ComboBoxMultiSelection();
		comboBox.setBounds(54, 47, 211, 35);
		comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
				"Veritabani Kayit",
				"Dosya",
				"Text Dosya",
				"Email Atma"
		        }));
		panel.add(comboBox);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				comboBox.clearSelectedItems();
		        List<Object> list = new ArrayList<>();
		        list.add("Dosya");
		        list.add("Email Atma");
		        comboBox.setSelectedItems(list);
			}
		});
		btnNewButton_4.setBounds(281, 46, 89, 23);
		panel.add(btnNewButton_4);
		
		badgeButton1 = new BadgeButton();
		badgeButton1.setHorizontalAlignment(SwingConstants.LEFT);
		badgeButton1.setBounds(678, 25, 60, 55);
		badgeButton1.setIcon(new ImageIcon(DENEMELER.class.getResource("/ICONLAR/bellD-16.png"))); //icon
        badgeButton1.setText("80");
        badgeButton1.setBadgeColor(Color.RED);
        badgeButton1.setMargin(new Insets(0, 0, 2, 14));
        badgeButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                badgeButton1ActionPerformed(evt);
            }
        });
        
		panel.add(badgeButton1);
		
		JButton btnNewButton_9 = new JButton("New button");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 comboBox.removeAll();
				 comboBox.clearSelectedItems();
		
				
				}
		});
		btnNewButton_9.setBounds(275, 71, 89, 23);
		panel.add(btnNewButton_9);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(620, 91, 115, 169);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		

		
		JButton btnNewButton_8 = new JButton("File Sifrele");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ENCRYPT_DECRYPT_STRING.encryptedFile( "C:\\OBS_SISTEM\\BACKUP\\SQL_LOG.MDB", "C:\\OBS_SISTEM\\BACKUP\\DENEME.OBS");
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}			}
		});
		btnNewButton_8.setBounds(54, 222, 89, 23);
		panel.add(btnNewButton_8);
		
		JButton btnNewButton_11 = new JButton("File Coz");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ENCRYPT_DECRYPT_STRING.decryptedFile("C:\\OBS_SISTEM\\BACKUP\\DENEME.OBS", "C:\\OBS_SISTEM\\BACKUP\\COZULMUS.MDB");
				} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
						| IllegalBlockSizeException | BadPaddingException | IOException e1) {
					
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_11.setBounds(176, 222, 89, 23);
		panel.add(btnNewButton_11);
		
		JButton btnNewButton_12 = new JButton("zip folder sifre");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ENCRYPT_DECRYPT_STRING.zip_folder_sifrele("C:\\ydk\\", "C:\\OBS_SISTEM\\BACKUP\\ydk.ZIP","oOk271972");
	
			}
		});
		btnNewButton_12.setBounds(620, 346, 103, 23);
		panel.add(btnNewButton_12);
		

		
		
		

	}
    private void badgeButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_badgeButton1ActionPerformed
	        badgeButton1.setText(Integer.parseInt(badgeButton1.getText()) + 1 + "");
	    }//GEN-LAST:event_badgeButton1ActionPerformed

	private void cHATGPT()
	{
		try 
		{
        String apiKey = "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";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String apiUrl = "https://api.openai.com/v1/engines/davinci/completions";

        HttpPost httpPost = new HttpPost(apiUrl);

        httpPost.setHeader("Authorization", "Bearer " + apiKey);

        String prompt = "Translate the following English text to French: 'Hello, how are you?'";
        StringEntity entity = new StringEntity("{\"prompt\":\"" + prompt + "\"}");
        httpPost.setEntity(entity);

        HttpResponse response = httpClient.execute(httpPost);

        HttpEntity responseEntity = response.getEntity();
        String responseBody = EntityUtils.toString(responseEntity);

        System.out.println("Generated Text: " + responseBody);

        
			httpClient.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
   

		
	}

}

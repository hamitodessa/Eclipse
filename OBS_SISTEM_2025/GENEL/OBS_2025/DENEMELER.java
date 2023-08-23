package OBS_2025;

import java.awt.EventQueue;
import java.text.ParseException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.MaskFormatter;

import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;
import com.healthmarketscience.jackcess.crypt.CryptCodecProvider;

import OBS_C_2025.BACKUP_RESTORE;
import OBS_C_2025.CLONE_RESULTSET;
import OBS_C_2025.CustomResultSetMetaData;
import OBS_C_2025.ENCRYPT_DECRYPT_STRING;
import OBS_C_2025.ManualResultSet;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

@SuppressWarnings({"serial","static-access","unused"})
public class DENEMELER extends JInternalFrame {
	private JTable table;
	OBS_SIS_2025_ANA_CLASS oac = new OBS_SIS_2025_ANA_CLASS();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DENEMELER frame = new DENEMELER();
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
	public DENEMELER() {
		setClosable(true);
		setBounds(100, 100, 800, 600);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		DefaultTableModel model = new DefaultTableModel() ; 
		table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {  
				
					return true;
				
			}
			
		};
		table.setSize(400, 300);
		model.addColumn("Barkod", new String []{""});
		model.addColumn("Urun Kodu", new String []{""});
		
	
		JFormattedTextField ftext = new JFormattedTextField();
		
		ftext.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				//System.out.println(ftext.getText());
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void removeUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				//System.out.println(ftext.getText());
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
			public void insertUpdate(DocumentEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));	
				
				String[] token = ftext.getText().split("-");
				System.out.println(token[0] + "=" +token[1]+ "=" + token[2] + "=" +token[3]);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
			}
		});
		
		MaskFormatter mask;
		try {
		    mask = new MaskFormatter("##-###-####-####");
		    mask.install(ftext);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(ftext));
		
		JTableHeader th = table.getTableHeader();
		Dimension dd = table.getPreferredSize();
		dd.height = 30;
		th.setPreferredSize(dd); 
		table.setRowSelectionInterval(0, 0);
		table.setRowHeight(22);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		panel.add(table);
		
		JButton btnNewButton_8 = new JButton("Clone");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try  {
					CLONE_RESULTSET clrsClone_RESULTSET = new CLONE_RESULTSET();
		            
					ResultSet clonedResultSet = clrsClone_RESULTSET.cloneResultSet( oac.uSER_ISL.user_details_bak());
		           System.out.println(clrsClone_RESULTSET.cnames[1].toString()); 
			            //oac.uSER_ISL.con.close();
			            // Process the cloned result set
		            while (clonedResultSet.next()) {
		                String data = clonedResultSet.getString(1) + " - " +clonedResultSet.getString(2) + " - " + clonedResultSet.getString(3) + " - " +clonedResultSet.getString(4);
		                System.out.println(data);
			            }
			        } catch (SQLException | ClassNotFoundException ex) {
			            ex.printStackTrace();
			        }
			}
		});
		btnNewButton_8.setBounds(503, 25, 89, 23);
		panel.add(btnNewButton_8);
		
		JButton btnNewButton_8_1 = new JButton("yeni Clone");
		btnNewButton_8_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clone_yap();
			}
		});
		btnNewButton_8_1.setBounds(503, 59, 89, 23);
		panel.add(btnNewButton_8_1);
		
		JButton btnNewButton_5 = new JButton("Access");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				//InputStream is = this.getClass().getClassLoader().getResourceAsStream("RPT/HSP_PLN.rpt");
				//Files.copy(is, Paths.get("C:\\OBS_SISTEM\\HSP_PLN.rpt"),StandardCopyOption.REPLACE_EXISTING);
				
				
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
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

				
					
		            
				} catch (IOException e1) {
					e1.printStackTrace();
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
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					  try {
				
						Connection conn;
						
							conn = DriverManager.getConnection(
								        "jdbc:ucanaccess://C:/OBS_SISTEM/test.accdb","","obs");
							Statement s = conn.createStatement();
							ResultSet rs = s.executeQuery("SELECT * FROM test");
							  System.out.println("------------------------------------");
								while (rs.next()) {
								    System.out.println(rs.getString(1) + "-" + rs.getString(2));
								}
						} catch (SQLException e1) {
							e1.printStackTrace();
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
		
		

	}
	private void clone_yap()
	{
		 String[] columnNames = {"TARIH", "EVRAK", "IZAHAT","KOD","KUR","BORC","ALACAK","BAKIYE"};
	        String[] columnTypes = {"VARCHAR","INTEGER" ,"VARCHAR", "VARCHAR","DOUBLE","DOUBLE","DOUBLE","DOUBLE"};

	        ManualResultSet customResultSet;
			try {
				customResultSet = createCustomResultSet(columnNames, columnTypes);
			
	        
	        // Add rows to the custom result set
	        customResultSet.addRow("01.01.2023", 1,"DENEME","",0,0,0,0);

	        // Iterate through the custom result set
	        while (customResultSet.next()) {
	            String tarih = (String) customResultSet.getObject(1);
	            int evrak = (int) customResultSet.getObject(2);
	            String izahat = (String) customResultSet.getObject(3);
	           

	            System.out.println("TARIH: " + tarih + ", Evrak: " + evrak + ", Izahat: " + izahat);
	        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	private static ManualResultSet createCustomResultSet(String[] columnNames, String[] columnTypes) throws SQLException {
        ResultSetMetaData metaData = new CustomResultSetMetaData(columnNames, columnTypes);
        return new ManualResultSet(metaData);
    }
}

package OBS_INDIR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class ANA_MENU extends JDialog {
	Cursor WAIT_CURSOR =  Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
	Cursor DEFAULT_CURSOR =  Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
	private JPanel contentPane;
	private JTextField txtdiz;
	private JLabel lblboyut;
	private JLabel lblinen;
	private JLabel lblkalan;
	private JProgressBar progressBar ;
	private JLabel label;
	private JLabel lblHiz;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ANA_MENU frame = new ANA_MENU();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public ANA_MENU() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setTitle("OBS INDIRME");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 239);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		MetalLookAndFeel.setCurrentTheme(new  DefaultMetalTheme());
		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Indir 2");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setForeground(new Color(0, 0, 205));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indir();

			}
		});
		btnNewButton.setBounds(314, 119, 80, 23);
		panel.add(btnNewButton);

		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(39, 45, 61));
		progressBar.setBorder(new LineBorder(new Color(0, 191, 255)));
		progressBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 11));
		progressBar.setBounds(52, 153, 342, 25);
		panel.add(progressBar);

		txtdiz = new JTextField();
		txtdiz.setForeground(new Color(0, 0, 128));
		txtdiz.setText("C:\\OBS_SISTEM");
		txtdiz.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtdiz.setBounds(52, 24, 281, 20);
		panel.add(txtdiz);
		txtdiz.setColumns(10);

		JButton btnNewButton_1 = new JButton(".....");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setCursor(WAIT_CURSOR);

				UIManager.put("FileChooser.cancelButtonText", "Vazgec");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Surucu Seciniz");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setApproveButtonText("Surucu Sec");
				chooser.setApproveButtonToolTipText("Surucu Sec");
				chooser.setApproveButtonMnemonic('s');
				contentPane.setCursor(DEFAULT_CURSOR);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					txtdiz.setText(chooser.getSelectedFile().toString());
				}
				else {
					// System.out.println("No Selection ");
				}
			}

		});
		btnNewButton_1.setBounds(343, 23, 51, 23);
		panel.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Kalan");
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(52, 103, 85, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Dosya Boyutu");
		lblNewLabel_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(52, 55, 85, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Indirilen");
		lblNewLabel_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(52, 80, 85, 14);
		panel.add(lblNewLabel_2);

		lblboyut = new JLabel("");
		lblboyut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblboyut.setHorizontalAlignment(SwingConstants.RIGHT);
		lblboyut.setBounds(146, 55, 115, 14);
		panel.add(lblboyut);

		lblinen = new JLabel("");
		lblinen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblinen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblinen.setBounds(146, 80, 115, 14);
		panel.add(lblinen);

		lblkalan = new JLabel("");
		lblkalan.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblkalan.setHorizontalAlignment(SwingConstants.RIGHT);
		lblkalan.setBounds(146, 103, 115, 14);
		panel.add(lblkalan);

		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(146, 128, 115, 14);
		panel.add(label);

		lblHiz = new JLabel("Hiz   Saniye");
		lblHiz.setForeground(new Color(128, 0, 0));
		lblHiz.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHiz.setBounds(52, 128, 85, 14);
		panel.add(lblHiz);

		JButton btnIndir = new JButton("Indir 1");
		btnIndir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indir_natro();
			}
		});
		btnIndir.setForeground(new Color(0, 0, 205));
		btnIndir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIndir.setBounds(314, 95, 80, 23);
		panel.add(btnIndir);

	}
	private void indir()
	{

		Runnable runner = new Runnable()
		{ 
			public void run() {
				/////  
				FTPClient ftp = new FTPClient();
				try {
					lblboyut.setText(FORMATLAMA.doub_0(0) + " bytes");
					lblinen.setText(FORMATLAMA.doub_0(0)+ " bytes");
					lblkalan.setText(FORMATLAMA.doub_0(0)+ " bytes");
					Login_Progres_Bar_Temizle();
					if (txtdiz.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Dizin Secilmemis....",  "OBS Indirme", JOptionPane.ERROR_MESSAGE);   
						txtdiz.requestFocus();
						return;
					}
					String serverAddress = "78.26.149.175";
					String userId ="hamitadmin";
					String password ="SDFks9hfji3#DEd";
					ftp.connect(serverAddress);
					if(!ftp.login(userId, password))
					{
						ftp.logout();
						JOptionPane.showMessageDialog(null, "Baglanti Hatasi.......",  "OBS Indirme", JOptionPane.ERROR_MESSAGE);   
					}
					int reply = ftp.getReplyCode();
					if (!FTPReply.isPositiveCompletion(reply))
					{
						ftp.disconnect();
						JOptionPane.showMessageDialog(null, "Baglanti Hatasi.......",  "OBS Indirme", JOptionPane.ERROR_MESSAGE);   
					}
					ftp.setFileType(FTP.BINARY_FILE_TYPE);
					ftp.enterLocalPassiveMode();
					boolean success ;
					//******************************
					double toplam = 0 ;
					FTPFile[] files = ftp.listFiles();
					for (FTPFile file : files) {
						if (file.getName().equals("OBS_SISTEM_2025.jar"))  
							toplam = file.getSize();
						double topl =  toplam ;
						lblboyut.setText(FORMATLAMA.doub_0(topl /1024)+ " KBytes");
					}
					contentPane.setCursor(WAIT_CURSOR);
					String remoteFile2 =  ftp.printWorkingDirectory() + "/OBS_SISTEM_2025.jar";
					File downloadFile2 = new File(txtdiz.getText() + "/OBS_SISTEM_2025.jar");
					OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile2));
					InputStream inputStream = ftp.retrieveFileStream(remoteFile2);
					double inen= 0;
					byte[] bytesArray = new byte[4096];
					int bytesRead = -1;
					progressBar.setMaximum((int) toplam);
					progressBar.setStringPainted(true);
					Long start = System.currentTimeMillis();
					long timeInSecs = 0;
					while ((bytesRead = inputStream.read(bytesArray)) != -1)
					{
						outputStream2.write(bytesArray, 0, bytesRead);
						inen += bytesRead ;
						lblinen.setText(FORMATLAMA.doub_0(inen /1024 )+ " KBytes");
						lblkalan.setText(FORMATLAMA.doub_0((toplam  - inen) /1024 )+ " KBytes");
						Lgn_Progres_Bar((int) toplam,(int) inen);
						double speedInKBps = 0.00;
						timeInSecs = (System.currentTimeMillis() - start) ; //converting millis to seconds as 1000m in 1 second
						speedInKBps = ( (inen * 1000) / (timeInSecs + 1))  ;
						label.setText(FORMATLAMA.doub_0( speedInKBps /1024) + " KBytes");
					}
					success = ftp.completePendingCommand();
					outputStream2.close();
					inputStream.close();
					//Version dosyasi Indir
					String remoteFile1 = ftp.printWorkingDirectory() + "/OBS_VERSION.txt";
					File downloadFile1 = new File( "C:/OBS_SISTEM" + "/OBS_VERSION.txt");
					OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
					ftp.retrieveFile(remoteFile1, outputStream1);
					outputStream1.close();
					//*******************************
					if (success) {
						JOptionPane.showMessageDialog(null, "Indirme Islemi Basari ile tamamlandi....",  "OBS Indirme", JOptionPane.PLAIN_MESSAGE);   
					}
					contentPane.setCursor(DEFAULT_CURSOR);
					Thread.currentThread().isInterrupted();
					System.exit(1);
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage(),  "OBS Indirme", JOptionPane.ERROR_MESSAGE);   
				} 
			}
		};
		//// Progress Bar
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
	private void indir_natro()
	{
		///// Progres Bsr olayi
		Runnable runner = new Runnable()
		{ 
			public void run() {
				/////  
				FTPClient ftp = new FTPClient();
				try {
					lblboyut.setText(FORMATLAMA.doub_0(0) + " bytes");
					lblinen.setText(FORMATLAMA.doub_0(0)+ " bytes");
					lblkalan.setText(FORMATLAMA.doub_0(0)+ " bytes");
					Login_Progres_Bar_Temizle();
					if (txtdiz.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Dizin Secilmemis....",  "OBS Indirme", JOptionPane.ERROR_MESSAGE);   
						txtdiz.requestFocus();
						return;
					}
					String serverAddress = "ftp.okumus.gen.tr";
					String userId ="u5789784";
					String password ="4wX.5Wx53-Y..nlG";

					ftp.connect(serverAddress);
					if(!ftp.login(userId, password))
					{
						ftp.logout();
						JOptionPane.showMessageDialog(null, "Baglanti Hatasi.......",  "OBS Indirme", JOptionPane.ERROR_MESSAGE);   
					}
					int reply = ftp.getReplyCode();
					if (!FTPReply.isPositiveCompletion(reply))
					{
						ftp.disconnect();
						JOptionPane.showMessageDialog(null, "Baglanti Hatasi.......",  "OBS Indirme", JOptionPane.ERROR_MESSAGE);   
					}
					ftp.setFileType(FTP.BINARY_FILE_TYPE);
					ftp.enterLocalPassiveMode();
					boolean success ;
					double toplam = 0 ;
					String filePath = "/OBS_SISTEM_2025/OBS_SISTEM_2025.jar";
					FTPFile file = ftp.mlistFile(filePath);
					toplam = file.getSize();
					ftp.setFileType(FTP.BINARY_FILE_TYPE);
					lblboyut.setText(FORMATLAMA.doub_0(toplam /1024)+ " KBytes");

					contentPane.setCursor(WAIT_CURSOR);
					File downloadFile2 = new File(txtdiz.getText() + "/OBS_SISTEM_2025.jar");
					OutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile2));
					InputStream inputStream = ftp.retrieveFileStream(filePath );
					double inen= 0;
					byte[] bytesArray = new byte[4096];
					int bytesRead = -1;
					progressBar.setMaximum((int) toplam);
					progressBar.setStringPainted(true);
					Long start = System.currentTimeMillis();

					long timeInSecs = 0;
					while ((bytesRead = inputStream.read(bytesArray)) != -1)
					{
						outputStream2.write(bytesArray, 0, bytesRead);
						inen += bytesRead ;
						lblinen.setText(FORMATLAMA.doub_0(inen /1024 )+ " KBytes");
						lblkalan.setText(FORMATLAMA.doub_0((toplam  - inen) /1024 )+ " KBytes");
						Lgn_Progres_Bar((int) toplam,(int) inen);
						double speedInKBps = 0.00;
						timeInSecs = (System.currentTimeMillis() - start) ; //converting millis to seconds as 1000m in 1 second
						speedInKBps = ( (inen * 1000) / (timeInSecs + 1))  ;
						label.setText(FORMATLAMA.doub_0( speedInKBps /1024) + " KBytes");
					}
					success = ftp.completePendingCommand();
					outputStream2.close();
					inputStream.close();
					//Version dosyasi Indir
					String remoteFile1 = "/OBS_SISTEM_2025/OBS_VERSION.txt";
					File downloadFile1 = new File( "C:/OBS_SISTEM" + "/OBS_VERSION.txt");
					OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
					ftp.retrieveFile(remoteFile1, outputStream1);
					outputStream1.close();
					//*******************************
					if (success) {
						JOptionPane.showMessageDialog(null, "Indirme Islemi Basari ile tamamlandi....",  "OBS Indirme", JOptionPane.PLAIN_MESSAGE);   
					}
					contentPane.setCursor(DEFAULT_CURSOR);
					Thread.currentThread().isInterrupted();
					System.exit(1);
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage(),  "OBS Indirme", JOptionPane.ERROR_MESSAGE);   
				} 
			}
		};
		//// Progress Bar
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}

	void Lgn_Progres_Bar(int max, int deger) throws InterruptedException
	{
		progressBar.setValue(deger);
	}
	void Login_Progres_Bar_Temizle()
	{
		progressBar.setMaximum(0);
		progressBar.setValue(0);
		progressBar.setStringPainted(false);
	}
}

//*****************************

// APPROACH #1: using retrieveFile(String, OutputStream)
// String remoteFile1 = ftp.printWorkingDirectory() + "/OBS_SISTEM.jar";
//File downloadFile1 = new File("C:/OBS_SISTEM.jar");
// OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
//boolean success = ftp.retrieveFile(remoteFile1, outputStream1);
//outputStream1.close();

//if (success) {
//    System.out.println("File #1 has been downloaded successfully.");
//}

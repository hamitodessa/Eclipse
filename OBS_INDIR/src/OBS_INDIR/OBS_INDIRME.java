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

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import OBS_C_2025.GLOBAL;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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

@SuppressWarnings("serial")
public class OBS_INDIRME extends JFrame {
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
	int x ,y ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OBS_INDIRME frame = new OBS_INDIRME();
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
	public OBS_INDIRME() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
	
		setUndecorated(true);
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("OBS_INDIR_THEME");
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

		
		setTitle("OBS INDIRME");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 561, 255);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		setIconImage(Toolkit.getDefaultToolkit().getImage(OBS_INDIRME.class.getResource("/OBS_INDIR/download-48.png")));

		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(new Title_Bar(this), BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 191, 255)));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("OBS_2025 -EXE");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indir("OBS_2025.exe");

			}
		});
		btnNewButton.setBounds(318, 75, 133, 23);
		panel.add(btnNewButton);

		progressBar = new JProgressBar();
	
		progressBar.setBorder(new LineBorder(new Color(0, 191, 255)));
		progressBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		progressBar.setFont(new Font("Tahoma", Font.BOLD, 11));
		progressBar.setBounds(52, 183, 484, 25);
		panel.add(progressBar);

		txtdiz = new JTextField();
		//txtdiz.setForeground(new Color(0, 0, 128));
		txtdiz.setText(GLOBAL.SURUCU);
		txtdiz.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtdiz.setBounds(52, 24, 399, 23);
		panel.add(txtdiz);
		txtdiz.setColumns(10);

		JButton btnNewButton_1 = new JButton("SURUCU");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
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
		btnNewButton_1.setBounds(456, 23, 80, 25);
		panel.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Kalan");
		//lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(52, 103, 85, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Dosya Boyutu");
		//lblNewLabel_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(52, 55, 85, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Indirilen");
		//lblNewLabel_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(52, 80, 85, 14);
		panel.add(lblNewLabel_2);

		lblboyut = new JLabel("");
		lblboyut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblboyut.setHorizontalAlignment(SwingConstants.RIGHT);
		lblboyut.setBounds(146, 55, 143, 14);
		panel.add(lblboyut);

		lblinen = new JLabel("");
		lblinen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblinen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblinen.setBounds(146, 80, 143, 14);
		panel.add(lblinen);

		lblkalan = new JLabel("");
		lblkalan.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblkalan.setHorizontalAlignment(SwingConstants.RIGHT);
		lblkalan.setBounds(146, 103, 143, 14);
		panel.add(lblkalan);

		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(146, 128, 143, 14);
		panel.add(label);

		lblHiz = new JLabel("Hiz   Saniye");
		//lblHiz.setForeground(new Color(128, 0, 0));
		lblHiz.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHiz.setBounds(52, 128, 85, 14);
		panel.add(lblHiz);

		JButton btnIndir = new JButton("Indir 1");
		btnIndir.setVisible(false);
		btnIndir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indir_natro();
			}
		});
		
		btnIndir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIndir.setBounds(10, 135, 28, 23);
		panel.add(btnIndir);
		
		JButton btnBackupIndir = new JButton("BACKUP -EXE");
		btnBackupIndir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backup_indir("OBS_BACKUP.exe");
			}
		});
	
		btnBackupIndir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBackupIndir.setBounds(318, 100, 133, 23);
		panel.add(btnBackupIndir);
		
		JButton btnNewButton_2 = new JButton("OBS GOREV -EXE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gorev_indir("OBS_GOREV.exe");
			}
		});
		
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(318, 125, 133, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("JAR");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				indir("OBS_2025.jar");
			}
		});
		btnNewButton_3.setBounds(456, 75, 80, 23);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("JAR");
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backup_indir("OBS_BACKUP.jar");
			}
		});
		btnNewButton_3_1.setBounds(456, 99, 80, 23);
		panel.add(btnNewButton_3_1);
		
		JButton btnNewButton_3_2 = new JButton("JAR");
		btnNewButton_3_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gorev_indir("OBS_GOREV.jar");
			}
		});
		btnNewButton_3_2.setBounds(456, 124, 80, 23);
		panel.add(btnNewButton_3_2);
		
		JButton btnNewButton_2_1 = new JButton("FIHRIST -EXE");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fihrist_indir("FIHRIST.exe");
			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2_1.setBounds(318, 150, 133, 23);
		panel.add(btnNewButton_2_1);
		
		JButton btnNewButton_3_2_1 = new JButton("JAR");
		btnNewButton_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fihrist_indir("FIHRIST.jar");
			}
		});
		btnNewButton_3_2_1.setBounds(456, 150, 80, 23);
		panel.add(btnNewButton_3_2_1);
		basla();
	}
	private void basla()
	{
		File tmpDir = new File(GLOBAL.SURUCU);
		if (! tmpDir.exists())
			tmpDir.mkdirs();
	}
	private void indir(String hangi)
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
					String serverAddress = "78.189.76.247";
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
						if (file.getName().equals(hangi))  
							toplam = file.getSize();
						double topl =  toplam ;
						lblboyut.setText(FORMATLAMA.doub_0(topl /1024)+ " KBytes");
					}
					contentPane.setCursor(WAIT_CURSOR);
					String remoteFile2 =  ftp.printWorkingDirectory() + "/" + hangi;
					File downloadFile2 = new File(txtdiz.getText() + "/" + hangi);
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
						timeInSecs = (System.currentTimeMillis() - start) ; 
						speedInKBps = ( (inen * 1000) / (timeInSecs + 1))  ;
						label.setText(FORMATLAMA.doub_0( speedInKBps /1024) + " KBytes");
					}
					success = ftp.completePendingCommand();
					outputStream2.close();
					inputStream.close();
					//Version dosyasi Indir
					String remoteFile1 = ftp.printWorkingDirectory() + "/OBS_VERSION.txt";
					File downloadFile1 = new File(GLOBAL.SURUCU + File.separator + "OBS_VERSION.txt");
					OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
					ftp.retrieveFile(remoteFile1, outputStream1);
					outputStream1.close();
					//*******************************
					if (success) {
						JOptionPane.showMessageDialog(null, "Indirme Islemi Basari ile tamamlandi....",  "OBS Indirme", JOptionPane.PLAIN_MESSAGE);   
					}
					contentPane.setCursor(DEFAULT_CURSOR);
					Thread.currentThread().isInterrupted();
				}
				catch (Exception ex)
				{
					contentPane.setCursor(DEFAULT_CURSOR);
					JOptionPane.showMessageDialog(null, ex.getMessage(),  "OBS Indirme", JOptionPane.ERROR_MESSAGE);   
				} 
			}
		};
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
	private void backup_indir(String hangi)
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
						JOptionPane.showMessageDialog(null, "Dizin Secilmemis....",  "OBS Backup Indirme", JOptionPane.ERROR_MESSAGE);   
						txtdiz.requestFocus();
						return;
					}
					String serverAddress = "78.189.76.247";
					String userId ="hamitadmin";
					String password ="SDFks9hfji3#DEd";
					ftp.connect(serverAddress);
					if(!ftp.login(userId, password))
					{
						ftp.logout();
						JOptionPane.showMessageDialog(null, "Baglanti Hatasi.......",   "OBS Backup Indirme", JOptionPane.ERROR_MESSAGE);   
					}
					int reply = ftp.getReplyCode();
					if (!FTPReply.isPositiveCompletion(reply))
					{
						ftp.disconnect();
						JOptionPane.showMessageDialog(null, "Baglanti Hatasi.......",   "OBS Backup Indirme", JOptionPane.ERROR_MESSAGE);   
					}
					ftp.setFileType(FTP.BINARY_FILE_TYPE);
					ftp.enterLocalPassiveMode();
					boolean success ;
					//******************************
					double toplam = 0 ;
					FTPFile[] files = ftp.listFiles();
					for (FTPFile file : files) {
						if (file.getName().equals(hangi))  
							toplam = file.getSize();
						double topl =  toplam ;
						lblboyut.setText(FORMATLAMA.doub_0(topl /1024)+ " KBytes");
					}
					contentPane.setCursor(WAIT_CURSOR);
					String remoteFile2 =  ftp.printWorkingDirectory() + "/"+ hangi;
					File downloadFile2 = new File(txtdiz.getText() + "/" + hangi);
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
						timeInSecs = (System.currentTimeMillis() - start) ; 
						speedInKBps = ( (inen * 1000) / (timeInSecs + 1))  ;
						label.setText(FORMATLAMA.doub_0( speedInKBps /1024) + " KBytes");
					}
					success = ftp.completePendingCommand();
					outputStream2.close();
					inputStream.close();
					
					//Version dosyasi Indir
					String remoteFile1 = ftp.printWorkingDirectory() + "/OBS_BACKUP_VERSION.txt";
					File downloadFile1 = new File(GLOBAL.SURUCU + File.separator + "OBS_BACKUP_VERSION.txt");
					OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
					ftp.retrieveFile(remoteFile1, outputStream1);
					outputStream1.close();
					//*******************************
					if (success) {
						JOptionPane.showMessageDialog(null, "Indirme Islemi Basari ile tamamlandi....",   "OBS Backup Indirme", JOptionPane.PLAIN_MESSAGE);   
					}
					contentPane.setCursor(DEFAULT_CURSOR);
					Thread.currentThread().isInterrupted();
				}
				catch (Exception ex)
				{
					contentPane.setCursor(DEFAULT_CURSOR);
					JOptionPane.showMessageDialog(null, ex.getMessage(),   "OBS Backup Indirme", JOptionPane.ERROR_MESSAGE);   
				} 
			}
		};
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
	private void gorev_indir(String hangi)
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
						JOptionPane.showMessageDialog(null, "Dizin Secilmemis....",  "OBS GOREV Indirme", JOptionPane.ERROR_MESSAGE);   
						txtdiz.requestFocus();
						return;
					}
					String serverAddress = "78.189.76.247";
					String userId ="hamitadmin";
					String password ="SDFks9hfji3#DEd";
					ftp.connect(serverAddress);
					if(!ftp.login(userId, password))
					{
						ftp.logout();
						JOptionPane.showMessageDialog(null, "Baglanti Hatasi.......",   "OBS GOREV Indirme", JOptionPane.ERROR_MESSAGE);   
					}
					int reply = ftp.getReplyCode();
					if (!FTPReply.isPositiveCompletion(reply))
					{
						ftp.disconnect();
						JOptionPane.showMessageDialog(null, "Baglanti Hatasi.......",   "OBS GOREV Indirme", JOptionPane.ERROR_MESSAGE);   
					}
					ftp.setFileType(FTP.BINARY_FILE_TYPE);
					ftp.enterLocalPassiveMode();
					boolean success ;
					//******************************
					double toplam = 0 ;
					FTPFile[] files = ftp.listFiles();
					for (FTPFile file : files) {
						if (file.getName().equals(hangi))  
							toplam = file.getSize();
						double topl =  toplam ;
						lblboyut.setText(FORMATLAMA.doub_0(topl /1024)+ " KBytes");
					}
					contentPane.setCursor(WAIT_CURSOR);
					String remoteFile2 =  ftp.printWorkingDirectory() + "/" + hangi;
					File downloadFile2 = new File(txtdiz.getText() + "/" + hangi);
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
						timeInSecs = (System.currentTimeMillis() - start) ; 
						speedInKBps = ( (inen * 1000) / (timeInSecs + 1))  ;
						label.setText(FORMATLAMA.doub_0( speedInKBps /1024) + " KBytes");
					}
					success = ftp.completePendingCommand();
					outputStream2.close();
					inputStream.close();
					//*******************************
					if (success) {
						JOptionPane.showMessageDialog(null, "Indirme Islemi Basari ile tamamlandi....",  "OBS GOREV Indirme", JOptionPane.PLAIN_MESSAGE);   
					}
					contentPane.setCursor(DEFAULT_CURSOR);
					Thread.currentThread().isInterrupted();
				}
				catch (Exception ex)
				{
					contentPane.setCursor(DEFAULT_CURSOR);
					JOptionPane.showMessageDialog(null, ex.getMessage(),   "OBS Backup Indirme", JOptionPane.ERROR_MESSAGE);   
				} 
			}
		};
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
	private void fihrist_indir(String hangi)
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
						JOptionPane.showMessageDialog(null, "Dizin Secilmemis....",  "OBS FIHRIST Indirme", JOptionPane.ERROR_MESSAGE);   
						txtdiz.requestFocus();
						return;
					}
					String serverAddress = "78.189.76.247";
					String userId ="hamitadmin";
					String password ="SDFks9hfji3#DEd";
					ftp.connect(serverAddress);
					if(!ftp.login(userId, password))
					{
						ftp.logout();
						JOptionPane.showMessageDialog(null, "Baglanti Hatasi.......",   "OBS FIHRIST  Indirme", JOptionPane.ERROR_MESSAGE);   
					}
					int reply = ftp.getReplyCode();
					if (!FTPReply.isPositiveCompletion(reply))
					{
						ftp.disconnect();
						JOptionPane.showMessageDialog(null, "Baglanti Hatasi.......",   "OBS FIHRIST  Indirme", JOptionPane.ERROR_MESSAGE);   
					}
					ftp.setFileType(FTP.BINARY_FILE_TYPE);
					ftp.enterLocalPassiveMode();
					boolean success ;
					//******************************
					double toplam = 0 ;
					FTPFile[] files = ftp.listFiles();
					for (FTPFile file : files) {
						if (file.getName().equals(hangi))  
							toplam = file.getSize();
						double topl =  toplam ;
						lblboyut.setText(FORMATLAMA.doub_0(topl /1024)+ " KBytes");
					}
					contentPane.setCursor(WAIT_CURSOR);
					String remoteFile2 =  ftp.printWorkingDirectory() + "/" + hangi;
					File downloadFile2 = new File(txtdiz.getText() + "/" + hangi);
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
						timeInSecs = (System.currentTimeMillis() - start) ; 
						speedInKBps = ( (inen * 1000) / (timeInSecs + 1))  ;
						label.setText(FORMATLAMA.doub_0( speedInKBps /1024) + " KBytes");
					}
					success = ftp.completePendingCommand();
					outputStream2.close();
					inputStream.close();
					//*******************************
					if (success) {
						JOptionPane.showMessageDialog(null, "Indirme Islemi Basari ile tamamlandi....",  "OBS FIHRIST  Indirme", JOptionPane.PLAIN_MESSAGE);   
					}
					contentPane.setCursor(DEFAULT_CURSOR);
					Thread.currentThread().isInterrupted();
				}
				catch (Exception ex)
				{
					contentPane.setCursor(DEFAULT_CURSOR);
					JOptionPane.showMessageDialog(null, ex.getMessage(),   "OBS FIHRIST Indirme", JOptionPane.ERROR_MESSAGE);   
				} 
			}
		};
		Thread t = new Thread(runner, "Code Executer");
		t.start();
	}
	private void indir_natro()
	{
		Runnable runner = new Runnable()
		{ 
			public void run() {
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
					String filePath = "/OBS_SISTEM_2025/OBS_2025.exe";
					FTPFile file = ftp.mlistFile(filePath);
					toplam = file.getSize();
					ftp.setFileType(FTP.BINARY_FILE_TYPE);
					lblboyut.setText(FORMATLAMA.doub_0(toplam /1024)+ " KBytes");

					contentPane.setCursor(WAIT_CURSOR);
					File downloadFile2 = new File(txtdiz.getText() + "/OBS_2025.exe");
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
						timeInSecs = (System.currentTimeMillis() - start) ; 
						speedInKBps = ( (inen * 1000) / (timeInSecs + 1))  ;
						label.setText(FORMATLAMA.doub_0( speedInKBps /1024) + " KBytes");
					}
					success = ftp.completePendingCommand();
					outputStream2.close();
					inputStream.close();
					//Version dosyasi Indir
					String remoteFile1 = "/OBS_SISTEM_2025/OBS_VERSION.txt";
					File tmpDir = new File( "C:" + File.separator + "OBS_SISTEM" + File.separator );
					boolean exists = tmpDir.exists();
					if (! exists)
					{
						tmpDir.mkdirs();
						File logDir = new File("C:" + File.separator + "OBS_SISTEM" + File.separator);
						logDir.mkdirs();
					}
					File downloadFile1 = new File( "C:" + File.separator + "OBS_SISTEM" + File.separator +"OBS_VERSION.txt");
					//
					if (!downloadFile1.exists()) {
						downloadFile1.createNewFile();
					}
					//
					OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
					ftp.retrieveFile(remoteFile1, outputStream1);
					outputStream1.close();
					//*******************************
					if (success) {
						contentPane.setCursor(DEFAULT_CURSOR);
						JOptionPane.showMessageDialog(null, "Indirme Islemi Basari ile tamamlandi....",  "OBS Indirme", JOptionPane.PLAIN_MESSAGE);   
					}
					contentPane.setCursor(DEFAULT_CURSOR);
					Thread.currentThread().isInterrupted();
				}
				catch (Exception ex)
				{
					contentPane.setCursor(DEFAULT_CURSOR);
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

package OBS_C_2025;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class BACKUP_RESTORE {

	@SuppressWarnings("deprecation")
	public static void BackupdbtoMYsql(String dbName ,String dbUser,String dbPass , String myDUMP) {

		String savePath = "C:/OBS_SISTEM/" + dbName +".sql";
		String executeCmd = myDUMP +"/mysqldump.exe -u" + dbUser + " -p" + dbPass + " -B " + dbName + " -r " + savePath;
		System.out.println(myDUMP +"/mysqldump.exe -u" + dbUser + " -p" + dbPass + " -B " + dbName + " -r " + savePath);
		Process runtimeProcess;
		try {
			Runtime runtime = Runtime.getRuntime();
			runtimeProcess = runtime.exec(executeCmd,null);
			int processComplete = runtimeProcess.waitFor();
			if (processComplete == 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmm");
				String str = sdf.format(new Date());
				FileOutputStream fos = new FileOutputStream("C:\\OBS_SISTEM\\" + str + "_" + dbName + ".zip");
				ZipOutputStream zipOut = new ZipOutputStream(fos);

				File fileToZip = new File(savePath);
				FileInputStream fis = new FileInputStream(fileToZip);
				ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
				zipOut.putNextEntry(zipEntry);

				byte[] bytes = new byte[1024];
				int length;
				while((length = fis.read(bytes)) >= 0) {
					zipOut.write(bytes, 0, length);
				}
				zipOut.close();
				fis.close();
				fos.close();
				//
				File file = new File(savePath);
				Files.deleteIfExists(file.toPath());
				//System.out.println("Backup created successfully");
			} else {
				File file = new File(savePath);
				Files.deleteIfExists(file.toPath());
				//System.out.println("Could not create the backup");
			}
		} catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	public static void BackupdbtoZIP(String dbName , String savePath) throws IOException {

		String sourceFile = dbName;
        FileOutputStream fos = new FileOutputStream(savePath);
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        File fileToZip = new File(sourceFile);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }

        zipOut.close();
        fis.close();
        fos.close();
			
		
	}
	public static void upLOADtoFTP(String server , int port , String user , String pass , String okunacakDB , String ftpSURUCU )
	{
	        FTPClient ftpClient = new FTPClient();
	        try {
	            ftpClient.connect(server, port);
	            ftpClient.login(user, pass);
	            ftpClient.enterLocalPassiveMode();
	 
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	 
	            // APPROACH #2: uploads second file using an OutputStream
	            File secondLocalFile = new File(okunacakDB);
	            String secondRemoteFile =  ftpSURUCU +  "/" + okunacakDB ;
	            InputStream inputStream = new FileInputStream(secondLocalFile);
	 
            OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
	            byte[] bytesIn = new byte[4096];
	            int read = 0;
	 
	            while ((read = inputStream.read(bytesIn)) != -1) {
	                outputStream.write(bytesIn, 0, read);
	            }
	            inputStream.close();
	            outputStream.close();
	 
	            boolean completed = ftpClient.completePendingCommand();
	            if (completed) {
	                System.out.println("The second file is uploaded successfully.");
	            }
	 
	        } catch (IOException ex) {
	            System.out.println("Error: " + ex.getMessage());
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	
}

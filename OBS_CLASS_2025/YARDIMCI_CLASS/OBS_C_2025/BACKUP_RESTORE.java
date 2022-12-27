package OBS_C_2025;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BACKUP_RESTORE {
	
	@SuppressWarnings("deprecation")
	public static void Backupdbtosql(String dbName ,String dbUser,String dbPass , String myDUMP) {
	 
	        String savePath = "C:/OBS_SISTEM/" + dbName +".sql";
	        String executeCmd = myDUMP +"/mysqldump.exe -u" + dbUser + " -p" + dbPass + " -B " + dbName + " -r " + savePath;
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
	            //
	           
	            
	        } catch (Exception ex) {
	        	 ex.printStackTrace();
	        }
	        

	}

}

package OBS_C_2025;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.sql.Connection;

import javax.swing.JOptionPane;

public class BACKUP_RESTORE {
	
	public static void Backupdbtosql() {
	 

	        String dbName = "ok_car019";
	        String dbUser = "root";
	        String dbPass = "197227oOk";


	        /*NOTE: Creating Path Constraints for backup saving*/
	        /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
	         String savePath = "c:\\" + "obsbackup.sql";
	      
	        /*NOTE: Used to create a cmd command*/
	//      mysqldump.exe -uroot -p197227oOk -B ok_car019 > c:\obsbackup.sql
	        String executeCmd = "C:\\Program Files\\MySQL\\MySQL Workbench 8.0\\mysqldump.exe -u" + dbUser + " -p" + dbPass + " -B " + dbName + " > " + savePath;
	        Process runtimeProcess;
	        try {
	        	Runtime runtime = Runtime.getRuntime();
	        	 File dir = new File("c:/");
	        	 
	        	runtimeProcess = runtime.exec(executeCmd);
	            System.out.println(executeCmd);
	            int processComplete = runtimeProcess.waitFor();
	            
	            System.out.println("processComplete" + processComplete);
	            if (processComplete == 0) {
	                System.out.println("Backup created successfully");

	            } else {
	                System.out.println("Could not create the backup");
	            }
	        } catch (Exception ex) {
	        	  System.out.println(ex.getMessage());
	        }
	        

	}

}

package OBS_C_2025;

public class BACKUP_RESTORE {
	
	@SuppressWarnings("deprecation")
	public static void Backupdbtosql() {
	 

	        String dbName = "ok_car019";
	        String dbUser = "hamit";
	        String dbPass = "197227oOk";

         String savePath = "C:/OBS_SISTEM/" + "obsbackup.sql";
	        String executeCmd = "C:/Program Files/MySQL/MySQL Workbench 8.0/mysqldump.exe -u" + dbUser + " -p" + dbPass + " -B " + dbName + " -r " + savePath;
	        Process runtimeProcess;
	        try {
	        	Runtime runtime = Runtime.getRuntime();
	        	runtimeProcess = runtime.exec(executeCmd,null);
	            System.out.println(executeCmd);
	            int processComplete = runtimeProcess.waitFor();
	            
	            System.out.println("processComplete" + processComplete);
	            if (processComplete == 0) {
	                System.out.println("Backup created successfully");

	            } else {
	                System.out.println("Could not create the backup");
	            }
	        } catch (Exception ex) {
	        	 ex.printStackTrace();
	        }
	        

	}

}

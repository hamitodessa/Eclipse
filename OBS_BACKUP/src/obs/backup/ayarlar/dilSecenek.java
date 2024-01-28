package obs.backup.ayarlar;

public class dilSecenek {

	static String tEXT = "" ;
	public static String dil(String dilString , String text) {
		tEXT  = text ;
		if(dilString.equals("English"))
		{
			switch(text) {
			case "Dil":
				tEXT = "Language";
				break;
			case "Tema":
				tEXT = "Themes";
				break;
			case "Kaydet":
				tEXT = "Save";
				break;
			case "Gorevler":
				tEXT = "Jobs";
				break;
			case "Yeni Gorev":
				tEXT = "New Job";
				break;
			case "Loglama":
				tEXT = "Logs";
				break;
			case "Kayitli Emirler":
				tEXT = "Saved Jobs";
				break;
			case "Hepsini Kucult":
				tEXT = "Collapse All";
				break;
			case "Hepsini Buyult":
				tEXT = "Expand All";
				break;
			case "Sifre Yenile":
				tEXT = "Change Password";
				break;
			case "Sifre Yanlis":
				tEXT = "Wrong password";
				break;
			case "Gorevleri Yedekle":
				tEXT = "Upload All Jobs";
				break;
			case "Gorevleri Baslat":
				tEXT = "Start All Jobs";
				break;
			case "Gorevleri Durdur":
				tEXT = "Stop All Jobs";
				break;
			case "Dosya Indir":
				tEXT = "Download File";
				break;
			case "Sifre Ekrani":
				tEXT = "Login Page";
				break;
			case "Gorevleri Aktif Yap":
				tEXT = "Activate All Job";
				break;
			case "Gorevleri Pasiv Yap":
				tEXT = "Passive All Job";
				break;
			case "Hakkinda":
				tEXT = "About";
				break;
			case "Ayarlar":
				tEXT = "Settings";
				break;
			case "Kapat":
				tEXT = "Exit";
				break;
			case "Buyult":
				tEXT = "Open";
				break;	
			case "Sifre":
				tEXT = "Password";
				break;
			case "Varsayilan Sifre :       obs":
				tEXT = "Default Password :       obs";
				break;  
			case "Emir Sayisi":
				tEXT = "Jobs Count";
				break;   
			case "Durum":
				tEXT = "Status";
				break;   
			case "Aktif / Pasif":
				tEXT = "Enable / Disable";
				break;   
			case  "Islem Gerceklestiginde":
				tEXT = "When Job is done"  ;
				break;   	  
			case  "Hata Durumunda":
				tEXT = "When In Case of Error"  ;
				break;   	  
			case "Gonderen Isim":
				tEXT = "Sender Name"  ;
				break;   
			case "Gonderen Hesap":
				tEXT = "Sender Account"  ;
				break;
			case "Gonderme Durumu":
				tEXT = "Send Status"  ;
				break;  
			case  "Alici":
				tEXT = "To"  ;
				break;   
			case  "Konu":
				tEXT = "Subject"  ;
				break;     
			case  "Server Ayarlari":
				tEXT = "Server Settings"  ;
				break;      
			case "Deneme Maili":
				tEXT = "Trial Email"  ;
				break;  	
			case "Kullanici":
				tEXT = "User Name"  ;
				break;
			case "Her":
				tEXT = "Every"  ;
				break;
			case "dakkada bir":
				tEXT = "minute(s)"  ;
				break;
			case "Gunler":
				tEXT = "Days"  ;
				break;
			case "Pazartesi":
				tEXT = "Monday"  ;
				break;
			case "Sali":
				tEXT = "Tuesday"  ;
				break;
			case "Carsamba":
				tEXT = "Wednesday"  ;
				break;
			case "Persembe":
				tEXT = "Thursday"  ;
				break;
			case "Cuma":
				tEXT = "Friday"  ;
				break;
			case "Cumartesi":
				tEXT = "Saturday"  ;
				break;
			case "Pazar":
				tEXT = "Sunday"  ;
				break;
			case "Baslangic":
				tEXT = "Starts"  ;
				break;
			case "Bitis":
				tEXT = "Ends"  ;
				break;
			case "Yedekleme Araligi":
				tEXT = "Backup Interval"  ;
				break;
			case "Aralik":
				tEXT = "Interval"  ;
				break;
			case "Yeni Emir Ismi" :
				tEXT = "New Job Name"  ;
				break;
			case "Gecerli Sifreniz":
				tEXT = "Current Password"  ;
				break;
			case "Yeni Sifreniz":
				tEXT = "New Password"  ;
				break;
			case "Sifre Yenileme":
				tEXT = "Change Password"  ;
				break;
			case "Arama":
				tEXT = "Search"  ;
				break;
			case "Sil":
				tEXT = "Delete"  ;
				break;
			case "Excell Aktarma":
				tEXT = "Export to Excell"  ;
				break;  
			case "Dosya Sec":
				tEXT = "Select File(s)"  ;
				break;  
			case "Surucu Sec":
				tEXT = "Select Folder(s)"  ;
				break;  
			case "Server Baglanti" :
				tEXT = "Server Connect"  ;
				break;  
			case "Emir Ismi" :
				tEXT = "Job Name"  ;
				break;	
			case "Dosya Sayisi" :
				tEXT = "Selected Counts"  ;
				break;	
			case "Dosya Adet:" :
				tEXT = "File Counts"  ;
				break;	
			case "Sql Veritabani / Dosya-Surucu Yedekleme" :
				tEXT = "Sql Databases /  Files & Folders"  ;
				break;
			case "Aciklama" :
				tEXT = "Description"  ;
				break;
			case "Mail Bilgileri" :
				tEXT = "Email Information"  ;
				break;
			case "Baglanti" :
				tEXT = "Connect"  ;
				break;
			case "Baglanti Test" :
				tEXT = "Connection Test"  ;
				break;
			case "Sec" :
				tEXT = "Select Folder"  ;
				break;
			case "Yerel Surucu" :
				tEXT = "Local Folder"  ;
				break;	  
			case "FTP Ayarlari" :
				tEXT = "FTP Settings"  ;
				break;	
			case "FTP Diger Ayarlar" :
				tEXT = "FTP Another Settings"  ;
				break;	
			case "Surucu" :
				tEXT = "Folder"  ;
				break;	
			case "Zaman Asimi" :
				tEXT = "Time out"  ;
				break;	
			case "Surucu Kontrol" :
				tEXT = "Check Folder"  ;
				break;
			case "Eski Yedek" :
				tEXT = "Check Old Backups"  ;
				break;
			case  "Eski Yed.Silme":
				tEXT = "Delete Old Backups"  ;
				break;
			case  "gunden eski olanari silme (0 Silinmez)":
				tEXT = "day(s) Delete backups older than  (0 Not deleted)"  ;
				break;
			case  "Ftp Kontrol":
				tEXT = "Check FTP"  ;
				break;
			case  "sn.":
				tEXT = "seconds"  ;
				break;
			case  "Hepsi":
				tEXT = "All"  ;
				break;
			case  "Vazgec":
				tEXT = "Cancel"  ;
				break;
			case  "Baglanti Saglandi":
				tEXT = "Connection Established"  ;
				break;
			case  "Baglanti Saglanamadi":
				tEXT = "Connection Failed"  ;
				break;
			case  "Backup islemi icin  'mysqldump.exe'  surucusu belirtilmelidir":
				tEXT = "'Mysqldump.exe' fo;der must be specified for the backup process"  ;
				break;
			case  "Surucu Secilmemis":
				tEXT = "Folder Not Selected"  ;
				break;
			case  "FTP Baglanti Bilgileri Eksik":
				tEXT = "FTP Connection Information is Missing"  ;
				break;
			case  "FTP Surucu Secilmemis"	:
				tEXT = "FTP Folder Not Selected"  ;
				break;
			case  "Baglanti Gerceklesti":
				tEXT = "Connection Completed"  ;
				break;
			case  "Baglanti Hata":
				tEXT = "Connection Error"  ;
				break;
			case  "ZIP Sifrele":
				tEXT = "Set Password to ZIP"  ;
				break;
			case  "Acilis Sifre Sor":
				tEXT = "Start Login Page"  ;
				break;

				
			}  
		}
		return tEXT;
	}
}

package obs.backup.ayarlar;

public class dilSecenek {

	static String tEXT = "" ;
	public static String dil(String dilString , String text) {
		tEXT  = text ;
		if(dilString.equals("English"))
		{
			switch(text) {
			case "Dil" -> tEXT = "Language";
			case "Tema" -> tEXT = "Themes";
			case "Kaydet" -> tEXT = "Save";
			case "Gorevler" -> tEXT = "Jobs";
			case "Yeni Gorev" -> tEXT = "New Job";
			case "Loglama" -> tEXT = "Logs";
			case "Kayitli Emirler" -> tEXT = "Saved Jobs";
			case "Hepsini Kucult" -> tEXT = "Collapse All";
			case "Hepsini Buyult" -> tEXT = "Expand All";
			case "Sifre Yenile" -> tEXT = "Change Password";
			case "Sifre Yanlis" -> tEXT = "Wrong password";
			case "Gorevleri Yedekle" -> tEXT = "Upload All Jobs";
			case "Gorevleri Baslat" -> tEXT = "Start All Jobs";
			case "Gorevleri Durdur" -> tEXT = "Stop All Jobs";
			case "Dosya Indir" -> tEXT = "Download File";
			case "Sifre Ekrani" -> tEXT = "Login Page";
			case "Gorevleri Aktif Yap" -> tEXT = "Activate All Job";
			case "Gorevleri Pasiv Yap" -> tEXT = "Passive All Job";
			case "Hakkinda" -> tEXT = "About";
			case "Ayarlar" -> tEXT = "Settings";
			case "Kapat" -> tEXT = "Exit";
			case "Buyult" -> tEXT = "Open";
			case "Sifre" -> tEXT = "Password";
			case "Varsayilan Sifre :       obs" -> tEXT = "Default Password :       obs";
			case "Emir Sayisi" -> tEXT = "Jobs Count";
			case "Durum" -> tEXT = "Status";
			case "Aktif / Pasif" -> tEXT = "Enable / Disable";
			case  "Islem Gerceklestiginde" -> tEXT = "When Job is done"  ;
			case  "Hata Durumunda" -> tEXT = "When In Case of Error"  ;
			case "Gonderen Isim" -> tEXT = "Sender Name"  ;
			case "Gonderen Hesap" -> tEXT = "Sender Account"  ;
			case "Gonderme Durumu" -> tEXT = "Send Status"  ;
			case  "Alici" -> tEXT = "To"  ;
			case  "Konu" -> tEXT = "Subject"  ;
			case  "Server Ayarlari" -> tEXT = "Server Settings"  ;
			case "Deneme Maili" -> tEXT = "Trial Email"  ;
			case "Kullanici" -> tEXT = "User Name"  ;
			case "Her" -> tEXT = "Every"  ;
			case "dakkada bir" -> tEXT = "minute(s)"  ;
			case "Gunler" -> tEXT = "Days"  ;
			case "Pazartesi" -> tEXT = "Monday"  ;
			case "Sali" -> tEXT = "Tuesday"  ;
			case "Carsamba" -> tEXT = "Wednesday"  ;
			case "Persembe" -> tEXT = "Thursday"  ;
			case "Cuma" -> tEXT = "Friday"  ;
			case "Cumartesi" -> tEXT = "Saturday"  ;
			case "Pazar" -> tEXT = "Sunday"  ;
			case "Baslangic" -> tEXT = "Starts"  ;
			case "Bitis" -> tEXT = "Ends"  ;
			case "Yedekleme Araligi" -> tEXT = "Backup Interval"  ;
			case "Aralik" -> tEXT = "Interval"  ;
			case "Yeni Emir Ismi" -> tEXT = "New Job Name"  ;
			case "Gecerli Sifreniz" -> tEXT = "Current Password"  ;
			case "Yeni Sifreniz" -> tEXT = "New Password"  ;
			case "Sifre Yenileme" -> tEXT = "Change Password"  ;
			case "Arama" -> tEXT = "Search"  ;
			case "Sil" -> tEXT = "Delete"  ;
			case "Excell Aktarma" -> tEXT = "Export to Excell"  ;
			case "Dosya Sec" -> tEXT = "Select File(s)"  ;
			case "Surucu Sec" -> tEXT = "Select Folder(s)"  ;
			case "Server Baglanti" -> tEXT = "Server Connect"  ;
			case "Emir Ismi" -> tEXT = "Job Name"  ;
			case "Dosya Sayisi" -> tEXT = "Selected Counts"  ;
			case "Dosya Adet:" -> tEXT = "File Counts"  ;
			case "Sql Veritabani / Dosya-Surucu Yedekleme" -> tEXT = "Sql Databases /  Files & Folders"  ;
			case "Aciklama" -> tEXT = "Description"  ;
			case "Mail Bilgileri" -> tEXT = "Email Information"  ;
			case "Baglanti" -> tEXT = "Connect"  ;
			case "Baglanti Test" -> tEXT = "Connection Test"  ;
			case "Sec" -> tEXT = "Select Folder"  ;
			case "Yerel Surucu" -> tEXT = "Local Folder"  ;
			case "FTP Ayarlari" -> tEXT = "FTP Settings"  ;
			case "FTP Diger Ayarlar" -> tEXT = "FTP Another Settings"  ;
			case "Surucu" -> tEXT = "Folder"  ;
			case "Zaman Asimi" -> tEXT = "Time out"  ;
			case "Surucu Kontrol" -> tEXT = "Check Folder"  ;
			case "Eski Yedek" -> tEXT = "Check Old Backups"  ;
			case  "Eski Yed.Silme" -> tEXT = "Delete Old Backups"  ;
			case  "gunden eski olanari silme (0 Silinmez)" -> tEXT = "day(s) Delete backups older than  (0 Not deleted)"  ;
			case  "Ftp Kontrol" -> tEXT = "Check FTP"  ;
			case  "sn." -> tEXT = "seconds"  ;
			case  "Hepsi" -> tEXT = "All"  ;
			case  "Vazgec" -> tEXT = "Cancel"  ;
			case  "Baglanti Saglandi" -> tEXT = "Connection Established"  ;
			case  "Baglanti Saglanamadi" -> tEXT = "Connection Failed"  ;
			case  "Backup islemi icin  'mysqldump.exe'  surucusu belirtilmelidir" -> tEXT = "'Mysqldump.exe' folder must be specified for the backup process"  ;
			case  "Surucu Secilmemis" -> tEXT = "Folder Not Selected"  ;
			case  "FTP Baglanti Bilgileri Eksik" -> tEXT = "FTP Connection Information is Missing"  ;
			case  "FTP Surucu Secilmemis" -> tEXT = "FTP Folder Not Selected"  ;
			case  "Baglanti Gerceklesti" -> tEXT = "Connection Completed"  ;
			case  "Baglanti Hata" -> tEXT = "Connection Error"  ;
			case  "ZIP Sifrele" -> tEXT = "Set Password to ZIP"  ;
			case  "Acilis Sifre Sor" -> tEXT = "Start Login Page"  ;
			case  "Windows ile Baslat" -> tEXT = "Start with Windows"  ;
			case  "Version Kontrol" -> tEXT = "Check Version"  ;
			}  
		}
		return tEXT;
	}
}

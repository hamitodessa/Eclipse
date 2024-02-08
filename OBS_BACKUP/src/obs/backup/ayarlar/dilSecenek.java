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
			case "Islem Gerceklestiginde" -> tEXT = "When Job is done"  ;
			case "Hata Durumunda" -> tEXT = "When In Case of Error"  ;
			case "Gonderen Isim" -> tEXT = "Sender Name"  ;
			case "Gonderen Hesap" -> tEXT = "Sender Account"  ;
			case "Gonderme Durumu" -> tEXT = "Send Status"  ;
			case "Alici" -> tEXT = "To"  ;
			case "Konu" -> tEXT = "Subject"  ;
			case "Server Ayarlari" -> tEXT = "Server Settings"  ;
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
			case "Eski Yed.Silme" -> tEXT = "Delete Old Backups"  ;
			case "gunden eski olanari silme (0 Silinmez)" -> tEXT = "day(s) Delete backups older than  (0 Not deleted)"  ;
			case "Ftp Kontrol" -> tEXT = "Check FTP"  ;
			case "sn." -> tEXT = "seconds"  ;
			case "Hepsi" -> tEXT = "All"  ;
			case "Vazgec" -> tEXT = "Cancel"  ;
			case "Baglanti Saglandi" -> tEXT = "Connection Established"  ;
			case "Baglanti Saglanamadi" -> tEXT = "Connection Failed"  ;
			case "Backup islemi icin  'mysqldump.exe'  surucusu belirtilmelidir" -> tEXT = "'Mysqldump.exe' folder must be specified for the backup process"  ;
			case "Surucu Secilmemis" -> tEXT = "Folder Not Selected"  ;
			case "FTP Baglanti Bilgileri Eksik" -> tEXT = "FTP Connection Information is Missing"  ;
			case "FTP Surucu Secilmemis" -> tEXT = "FTP Folder Not Selected"  ;
			case "Baglanti Gerceklesti" -> tEXT = "Connection Completed"  ;
			case "Baglanti Hata" -> tEXT = "Connection Error"  ;
			case "ZIP Sifrele" -> tEXT = "Set Password to ZIP"  ;
			case "Acilis Sifre Sor" -> tEXT = "Start Login Page"  ;
			case "Windows ile Baslat" -> tEXT = "Start with Windows"  ;
			case "Version Kontrol" -> tEXT = "Check Version"  ;
			case "Program Baslangici" -> tEXT = "Application Started" ;
			case "Yedekleme Icin Gun secilmediginden " -> tEXT = "No day has been selected for backup" ;
			case "Emir durumu Pasiv olarak Degistirildi" -> tEXT = "the Job status has been changed to Passive" ;
			case "Emir Islemi Kaydedildi" -> tEXT = "Job is Saved" ;
			case "Yedekleme Aralik Zamani Girilmemis" -> tEXT = "Backup Interval Time Not Entered" ;
			case "Bitis Zamani Baslangic Zamanindan Kucuk olamaz" -> tEXT = "End Time Cannot Be Less Than Start Time" ;
			case "Emir Yedekleme Bilgileri  Kaydedildi" -> tEXT = "Job Backup Information Saved" ;
			case "Emir Bilgilendirme Bilgileri Kaydedildi" -> tEXT = "Job Informational Information Saved" ;
			case "Emir FTP Bilgileri  Kaydedildi" -> tEXT = "Job FTP Information Saved" ;
			case "Emir Adi Bos Olamaz" -> tEXT = "Job Name Cannot Be Empty" ;
			case "Veritabani Isimleri yuklendi" -> tEXT = "Database Names loaded" ;
			case "SQL SERVER Instance Bilgileri Kaydedildi" -> tEXT = "MS SQL SERVER Instance Information Saved" ;
			case "MY SQL SERVER Instance Bilgileri Kaydedildi" -> tEXT = "MY SQL SERVER Instance Information Saved" ;
			case "Emir Silme Islemine Baslandi" -> tEXT = "Job Deletion Process Started" ;
			case "Emir Islemi Silindi" -> tEXT = "Job Deleted" ;
			case "Yuklenecek Dosya Secilmemis" -> tEXT = "No File to Upload Has Been Selected" ;
			case "Emir Yuklendi" -> tEXT = "Job Loaded" ;
			case "Yedeklemeye Baslandi" -> tEXT = "Backup Started" ;
			case " Backup Alindi" -> tEXT = " Created Backup" ;
			case " Zip Haline Getirildi" -> tEXT = " Created ZIP" ;
			case "Zip Haline Getiriliyor" -> tEXT = "File is zipping" ;
			case " Surucuye Yuklendi" -> tEXT = " Copied to Folder" ;
			case " FTP Yuklendi" -> tEXT = " Uploaded to FTP" ;
			case " bak Dosyasi Silindi" -> tEXT = " bak File Deleted" ;
			case " sql Dosyasi Silindi" -> tEXT = " sql File Deleted" ;
			case " ZIP Dosyasi Silindi" -> tEXT = " ZIP File Deleted" ;
			case "Yedeklendi" -> tEXT = "Backuped" ;
			case " Surucuye Silmeye Gitti" -> tEXT = " Went to Folder to Delete" ;
			case " FTP Silmeye Gitti" -> tEXT = " Went to FTP to Delete" ;
			case " Dosya Surucuden Eski Tarihli Silindi" -> tEXT = " Older File Deleted from Folder" ;
			case " FTP Eski Tarihli Silindi" -> tEXT = " Older File Deleted from FTP" ;
			case "Yedekleme Islemi Sona Erdi" -> tEXT = "Backup Process Completed" ;
			case "Hata Durumundan Emir Bosaldi" -> tEXT = " Jobr Discharged Due to Error Status" ;
			case "Emir Duzelt" -> tEXT = "Change" ;
			case "Emir Dosyadan Silinecek ..?" -> tEXT = "The Job Will Be Deleted ?" ;
			case "Yedekle" -> tEXT = "Backup" ;
			case "Pasiv Durumda" -> tEXT = "In Passive State" ;
			case "Son Durum" -> tEXT = "Last Situation" ;
			case "Dosya Sayisi" -> tEXT = "File Counts" ;
			case "Son Yedekleme" -> tEXT = "Last Backup" ;
			case "Gel.Yedekleme" -> tEXT = "Next Backup" ;
			case "- Isimli Emir Pasiv Durumda !!! ,  Oncelikle aktivlestirin...." -> tEXT = "- Named Job is in Passive Status!!! , Activate it first...." ;
			case "Yedekleme Sirasina Konuldu" -> tEXT = "Placed in the Backup Queue" ;
			case "0 Adet Dosya" -> tEXT = "0 Files" ;
			case " Adet Dosya - " -> tEXT = " Files - " ;
			case " Adet Dosya" -> tEXT = " Files" ;
			case "Yedeklenmedi Internet Baglantisi Yok" -> tEXT = "Not Backuped No Internet Connection" ;
			case "Yedeklenmedi FTP Surucu Bulunamadi" -> tEXT = "Not Backuped, FTP Folder Not Found" ;
			case "FTP Baglanti Hatasi Login" -> tEXT = "FTP Connection Error Login" ;
			case "FTP Baglanti Hatasi isPositiveCompletion" -> tEXT = "FTP Connection Error isPositiveCompletion" ;
			case "Mail Gonderirken Hata Olustu" -> tEXT = "An Error Occurred While Sending Email" ;
			case "Yedekleme Yapildi Maili gonderildi" -> tEXT = "Backup Done Email was sent" ;
			case "Yedekleme Yapilamadi Maili gonderildi" -> tEXT = "Backup Failed Email was sent" ;
			case "Arka Plan basladi" -> tEXT = "System tray started" ;
			case "Gonderen Bilgileri Eksik" -> tEXT = "Sender Information Missing" ;
			case "Smtp Bilgileri Eksik" -> tEXT = "Smtp Information Missing" ;
			case "Surucusune Indirilmistir" -> tEXT = "Downloaded to its Folder" ;
			case "Dos.Boyut" -> tEXT = "File Size" ;
			case "Dosya Adi" -> tEXT = "File Name" ;
			case "Hiz" -> tEXT = "Speed" ;
			case "Inen" -> tEXT = "Downloaded" ;
			case "Kalan" -> tEXT = "Remainder" ;
			case "Kopyalanacak Emir ismi Bos" -> tEXT = "The Job name to be copied is Empty" ;
			case "Emir Kopyalama Gerceklestirildi" -> tEXT = "Job Copy Completed" ;
			case " -  Log  Silinecek ?" -> tEXT = " - Log will be deleted  ?" ;
			case "Aktarilacak Bilgi Yok" -> tEXT = "No Information to Transfer" ;
			case "Aktarma Islemi Tamamlandi" -> tEXT = "Transfer Completed";
			case "Programi Yonetici olarak calistirip Oyle Kayit Yapabilirsiniz" -> tEXT = "Must run the program as an Administrator and create job";
			case " Dosya Bulunamadi***************" -> tEXT = " File Not Found***************";
			case "Kayitli Emir Yok" -> tEXT = "Job Not Found" ;
			case "Indir" -> tEXT = "Download";
			case " Dosyasi Indirildi" -> tEXT = " File Downloaded";
			
			}  
		}
		return tEXT;
	}
}

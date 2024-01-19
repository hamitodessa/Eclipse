package obs.backup.ayarlar;

public class dilAciklamalar {
	
	static String acikLAMAString = "";
	
	public static String dilAciklama(String dilString , String mesaj)
	{
		acikLAMAString = mesaj ;
		if(dilString.equals("English"))
		{
			switch (mesaj) {
			case "Program Baslangici": 
				acikLAMAString = "Application Started" ;
				break;
			case "Yedekleme Icin Gun secilmediginden ": 
				acikLAMAString = "No day has been selected for backup" ;
				break;
			case "Emir durumu Pasiv olarak Degistirildi": 
				acikLAMAString = "the Job status has been changed to Passive" ;
				break;
			case "Emir Islemi Kaydedildi": 
				acikLAMAString = "Job is Saved" ;
				break;
			case "Yedekleme Aralik Zamani Girilmemis": 
				acikLAMAString = "Backup Interval Time Not Entered" ;
				break;
			case "Bitis Zamani Baslangic Zamanindan Kucuk olamaz": 
				acikLAMAString = "End Time Cannot Be Less Than Start Time" ;
				break;
			case "Emir Yedekleme Bilgileri  Kaydedildi": 
				acikLAMAString = "Job Backup Information Saved" ;
				break;
			case "Emir Bilgilendirme Bilgileri Kaydedildi": 
				acikLAMAString = "Job Informational Information Saved" ;
				break;
			case "Emir FTP Bilgileri  Kaydedildi": 
				acikLAMAString = "Job FTP Information Saved" ;
				break;
			case "Emir Adi Bos Olamaz": 
				acikLAMAString = "Job Name Cannot Be Empty" ;
				break;
			case "Veritabani Isimleri yuklendi": 
				acikLAMAString = "Database Names loaded" ;
				break;
			case "SQL SERVER Instance Bilgileri Kaydedildi": 
				acikLAMAString = "MS SQL SERVER Instance Information Saved" ;
				break;
			case "MY SQL SERVER Instance Bilgileri Kaydedildi": 
				acikLAMAString = "MY SQL SERVER Instance Information Saved" ;
				break;
			case "Emir Silme Islemine Baslandi": 
				acikLAMAString = "Job Deletion Process Started" ;
				break;
			case "Emir Islemi Silindi": 
				acikLAMAString = "Job Deleted" ;
				break;
			case "Yuklenecek Dosya Secilmemis": 
				acikLAMAString = "No File to Upload Has Been Selected" ;
				break;
			case "Emir Yuklendi": 
				acikLAMAString = "Job Loaded" ;
				break;
			case "FTP Surucu Secilmemis": 
				acikLAMAString = "FTP Folder Not Selected" ;
				break;
			case "Yedeklemeye Baslandi": 
				acikLAMAString = "Backup Started" ;
				break;
			case " Backup Alindi": 
				acikLAMAString = " Created Backup" ;
				break;
			case " Zip Haline Getirildi": 
				acikLAMAString = " Created ZIP" ;
				break;
			case " Surucuye Yuklendi": 
				acikLAMAString = " Copied to Folder" ;
				break;
			case " BAK Dosyasi Silindi": 
				acikLAMAString = " Bak File Deleted" ;
				break;
			case "Yedeklendi": 
				acikLAMAString = "Backuped" ;
				break;
			case " Surucuye Silmeye Gitti": 
				acikLAMAString = " Went to Folder to Delete" ;
				break;
			case " Dosya Surucuden Eski Tarihli Silindi": 
				acikLAMAString = " Older File Deleted from Folder" ;
				break;
			case " Yedekleme Islemi Sona Erdi": 
				acikLAMAString = " Backup Process Completed" ;
				break;
			case "Hata Durumundan Emir Bosaldi": 
				acikLAMAString = " Jobr Discharged Due to Error Status" ;
				break;
				
			}
		
		
		}
	return acikLAMAString;

}

//

}

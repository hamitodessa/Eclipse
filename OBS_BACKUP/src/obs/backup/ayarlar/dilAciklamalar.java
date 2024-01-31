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
			case "Surucu Secilmemis": 
				acikLAMAString = "Folder Not Selected" ;
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
			case "Zip Haline Getiriliyor": 
				acikLAMAString = "File is zipping" ;
				break;
			case " Surucuye Yuklendi": 
				acikLAMAString = " Copied to Folder" ;
				break;
			case " FTP Yuklendi": 
				acikLAMAString = " Uploaded to FTP" ;
				break;
			case " BAK Dosyasi Silindi": 
				acikLAMAString = " Bak File Deleted" ;
				break;
			case " sql Dosyasi Silindi": 
				acikLAMAString = " sql File Deleted" ;
				break;
			case " ZIP Dosyasi Silindi": 
				acikLAMAString = " ZIP File Deleted" ;
				break;
			case "Yedeklendi": 
				acikLAMAString = "Backuped" ;
				break;
			case " Surucuye Silmeye Gitti": 
				acikLAMAString = " Went to Folder to Delete" ;
				break;
			case " FTP Silmeye Gitti": 
				acikLAMAString = " Went to FTP to Delete" ;
				break;
			case " Dosya Surucuden Eski Tarihli Silindi": 
				acikLAMAString = " Older File Deleted from Folder" ;
				break;
			case " FTP Eski Tarihli Silindi": 
				acikLAMAString = " Older File Deleted from FTP" ;
				break;
			case "Yedekleme Islemi Sona Erdi": 
				acikLAMAString = "Backup Process Completed" ;
				break;
			case "Hata Durumundan Emir Bosaldi": 
				acikLAMAString = " Jobr Discharged Due to Error Status" ;
				break;
			case "Emir Duzelt": 
				acikLAMAString = "Change" ;
				break;
			case "Sil": 
				acikLAMAString = "Delete" ;
				break;
			case "Emir Dosyadan Silinecek ..?": 
				acikLAMAString = "The Job Will Be Deleted ?" ;
				break;
			case "Yedekle": 
				acikLAMAString = "Backup" ;
				break;
			case "Pasiv Durumda": 
				acikLAMAString = "In Passive State" ;
				break;
			case "Son Durum": 
				acikLAMAString = "Last Situation" ;
				break;
			case "Dosya Sayisi": 
				acikLAMAString = "File Counts" ;
				break;
			case "Surucu": 
				acikLAMAString = "Folder" ;
				break;
			case "Son Yedekleme": 
				acikLAMAString = "Last Backup" ;
				break;
			case "Gel.Yedekleme": 
				acikLAMAString = "Next Backup" ;
				break;
			case "Aciklama": 
				acikLAMAString = "Description" ;
				break;
			case "- Isimli Emir Pasiv Durumda !!! ,  Oncelikle aktivlestirin....": 
				acikLAMAString = "- Named Job is in Passive Status!!! , Activate it first...." ;
				break;
			case "Yedekleme Sirasina Konuldu": 
				acikLAMAString = "Placed in the Backup Queue" ;
				break;
			case "0 Adet Dosya": 
				acikLAMAString = "0 Files" ;
				break;
			case " Adet Dosya - ": 
				acikLAMAString = " Files - " ;
				break;
			case " Adet Dosya": 
				acikLAMAString = " Files" ;
				break;
			case "Yedeklenmedi Internet Baglantisi Yok": 
				acikLAMAString = "Not Backuped No Internet Connection" ;
				break;
			case "Yedeklenmedi FTP Surucu Bulunamadi": 
				acikLAMAString = "Not Backuped, FTP Folder Not Found" ;
				break;
			case "FTP Baglanti Hatasi Login": 
				acikLAMAString = "FTP Connection Error Login" ;
				break;
			case "FTP Baglanti Hatasi isPositiveCompletion": 
				acikLAMAString = "FTP Connection Error isPositiveCompletion" ;
				break;
			case "Mail Gonderirken Hata Olustu": 
				acikLAMAString = "An Error Occurred While Sending Email" ;
				break;
			case "Yedekleme Yapildi Maili gonderildi": 
				acikLAMAString = "Backup Done Email was sent" ;
				break;
			case "Yedekleme Yapilamadi Maili gonderildi": 
				acikLAMAString = "Backup Failed Email was sent" ;
				break;
			case "Arka Plan basladi": 
				acikLAMAString = "System tray started" ;
				break;
			case "Gonderen Bilgileri Eksik": 
				acikLAMAString = "Sender Information Missing" ;
				break;
			case "Smtp Bilgileri Eksik": 
				acikLAMAString = "Smtp Information Missing" ;
				break;
			case "Surucusune Indirilmistir": 
				acikLAMAString = "Downloaded to its Folder" ;
				break;
			case "Dos.Boyut": 
				acikLAMAString = "File Size" ;
				break;
			case "Dosya Adi": 
				acikLAMAString = "File Name" ;
				break;
			case "Hiz": 
				acikLAMAString = "Speed" ;
				break;
			case "Inen": 
				acikLAMAString = "Downloaded" ;
				break;
			case "Kalan": 
				acikLAMAString = "Remainder" ;
				break;
			case "Kopyalanacak Emir ismi Bos": 
				acikLAMAString = "The Job name to be copied is Empty" ;
				break;
			case "Emir Kopyalama Gerceklestirildi": 
				acikLAMAString = "Job Copy Completed" ;
				break;
			case " -  Log  Silinecek ?": 
				acikLAMAString = " - Log will be deleted  ?" ;
				break;
			case "Aktarilacak Bilgi Yok": 
				acikLAMAString = "No Information to Transfer" ;
				break;
			case "Aktarma Islemi Tamamlandi": 
				acikLAMAString = "Transfer Completed";
				break;
			case "Programi Yonetici olarak calistirip Oyle Kayit Yapabilirsiniz": 
				acikLAMAString = "Must run the program as an Administrator and create job";
				break;
//				
			}
		}
		return acikLAMAString;
	}
}

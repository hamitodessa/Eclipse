package obs.backup.ayarlar;

public class dilAciklamalar {

	static String acikLAMAString = "";

	public static String dilAciklama(String dilString , String mesaj)
	{
		acikLAMAString = mesaj ;
		if(dilString.equals("English"))
		{
			switch (mesaj) {
			case "Program Baslangici" -> acikLAMAString = "Application Started" ;
			case "Yedekleme Icin Gun secilmediginden " -> acikLAMAString = "No day has been selected for backup" ;
			case "Emir durumu Pasiv olarak Degistirildi" -> acikLAMAString = "the Job status has been changed to Passive" ;
			case "Emir Islemi Kaydedildi" -> acikLAMAString = "Job is Saved" ;
			case "Yedekleme Aralik Zamani Girilmemis" -> acikLAMAString = "Backup Interval Time Not Entered" ;
			case "Bitis Zamani Baslangic Zamanindan Kucuk olamaz" -> acikLAMAString = "End Time Cannot Be Less Than Start Time" ;
			case "Emir Yedekleme Bilgileri  Kaydedildi" -> acikLAMAString = "Job Backup Information Saved" ;
			case "Emir Bilgilendirme Bilgileri Kaydedildi" -> acikLAMAString = "Job Informational Information Saved" ;
			case "Emir FTP Bilgileri  Kaydedildi" -> acikLAMAString = "Job FTP Information Saved" ;
			case "Emir Adi Bos Olamaz" -> acikLAMAString = "Job Name Cannot Be Empty" ;
			case "Veritabani Isimleri yuklendi" -> acikLAMAString = "Database Names loaded" ;
			case "SQL SERVER Instance Bilgileri Kaydedildi" -> acikLAMAString = "MS SQL SERVER Instance Information Saved" ;
			case "MY SQL SERVER Instance Bilgileri Kaydedildi" -> acikLAMAString = "MY SQL SERVER Instance Information Saved" ;
			case "Emir Silme Islemine Baslandi" -> acikLAMAString = "Job Deletion Process Started" ;
			case "Emir Islemi Silindi" -> acikLAMAString = "Job Deleted" ;
			case "Yuklenecek Dosya Secilmemis" -> acikLAMAString = "No File to Upload Has Been Selected" ;
			case "Emir Yuklendi" -> acikLAMAString = "Job Loaded" ;
			case "FTP Surucu Secilmemis" -> acikLAMAString = "FTP Folder Not Selected" ;
			case "Surucu Secilmemis" -> acikLAMAString = "Folder Not Selected" ;
			case "Yedeklemeye Baslandi" -> acikLAMAString = "Backup Started" ;
			case " Backup Alindi" -> acikLAMAString = " Created Backup" ;
			case " Zip Haline Getirildi" -> acikLAMAString = " Created ZIP" ;
			case "Zip Haline Getiriliyor" -> acikLAMAString = "File is zipping" ;
			case " Surucuye Yuklendi" -> acikLAMAString = " Copied to Folder" ;
			case " FTP Yuklendi" -> acikLAMAString = " Uploaded to FTP" ;
			case " BAK Dosyasi Silindi" -> acikLAMAString = " Bak File Deleted" ;
			case " sql Dosyasi Silindi" -> acikLAMAString = " sql File Deleted" ;
			case " ZIP Dosyasi Silindi" -> acikLAMAString = " ZIP File Deleted" ;
			case "Yedeklendi" -> acikLAMAString = "Backuped" ;
			case " Surucuye Silmeye Gitti" -> acikLAMAString = " Went to Folder to Delete" ;
			case " FTP Silmeye Gitti" -> acikLAMAString = " Went to FTP to Delete" ;
			case " Dosya Surucuden Eski Tarihli Silindi" -> acikLAMAString = " Older File Deleted from Folder" ;
			case " FTP Eski Tarihli Silindi" -> acikLAMAString = " Older File Deleted from FTP" ;
			case "Yedekleme Islemi Sona Erdi" -> acikLAMAString = "Backup Process Completed" ;
			case "Hata Durumundan Emir Bosaldi" -> acikLAMAString = " Jobr Discharged Due to Error Status" ;
			case "Emir Duzelt" -> acikLAMAString = "Change" ;
			case "Sil" -> acikLAMAString = "Delete" ;
			case "Emir Dosyadan Silinecek ..?" -> acikLAMAString = "The Job Will Be Deleted ?" ;
			case "Yedekle" -> acikLAMAString = "Backup" ;
			case "Pasiv Durumda" -> acikLAMAString = "In Passive State" ;
			case "Son Durum" -> acikLAMAString = "Last Situation" ;
			case "Dosya Sayisi" -> acikLAMAString = "File Counts" ;
			case "Surucu" -> acikLAMAString = "Folder" ;
			case "Son Yedekleme" -> acikLAMAString = "Last Backup" ;
			case "Gel.Yedekleme" -> acikLAMAString = "Next Backup" ;
			case "Aciklama" -> acikLAMAString = "Description" ;
			case "- Isimli Emir Pasiv Durumda !!! ,  Oncelikle aktivlestirin...." -> acikLAMAString = "- Named Job is in Passive Status!!! , Activate it first...." ;
			case "Yedekleme Sirasina Konuldu" -> acikLAMAString = "Placed in the Backup Queue" ;
			case "0 Adet Dosya" -> acikLAMAString = "0 Files" ;
			case " Adet Dosya - " -> acikLAMAString = " Files - " ;
			case " Adet Dosya" -> acikLAMAString = " Files" ;
			case "Yedeklenmedi Internet Baglantisi Yok" -> acikLAMAString = "Not Backuped No Internet Connection" ;
			case "Yedeklenmedi FTP Surucu Bulunamadi" -> acikLAMAString = "Not Backuped, FTP Folder Not Found" ;
			case "FTP Baglanti Hatasi Login" -> acikLAMAString = "FTP Connection Error Login" ;
			case "FTP Baglanti Hatasi isPositiveCompletion" -> acikLAMAString = "FTP Connection Error isPositiveCompletion" ;
			case "Mail Gonderirken Hata Olustu" -> acikLAMAString = "An Error Occurred While Sending Email" ;
			case "Yedekleme Yapildi Maili gonderildi" -> acikLAMAString = "Backup Done Email was sent" ;
			case "Yedekleme Yapilamadi Maili gonderildi" -> acikLAMAString = "Backup Failed Email was sent" ;
			case "Arka Plan basladi" -> acikLAMAString = "System tray started" ;
			case "Gonderen Bilgileri Eksik" -> acikLAMAString = "Sender Information Missing" ;
			case "Smtp Bilgileri Eksik" -> acikLAMAString = "Smtp Information Missing" ;
			case "Surucusune Indirilmistir" -> acikLAMAString = "Downloaded to its Folder" ;
			case "Dos.Boyut" -> acikLAMAString = "File Size" ;
			case "Dosya Adi" -> acikLAMAString = "File Name" ;
			case "Hiz" -> acikLAMAString = "Speed" ;
			case "Inen" -> acikLAMAString = "Downloaded" ;
			case "Kalan" -> acikLAMAString = "Remainder" ;
			case "Kopyalanacak Emir ismi Bos" -> acikLAMAString = "The Job name to be copied is Empty" ;
			case "Emir Kopyalama Gerceklestirildi" -> acikLAMAString = "Job Copy Completed" ;
			case " -  Log  Silinecek ?" -> acikLAMAString = " - Log will be deleted  ?" ;
			case "Aktarilacak Bilgi Yok" -> acikLAMAString = "No Information to Transfer" ;
			case "Aktarma Islemi Tamamlandi" -> acikLAMAString = "Transfer Completed";
			case "Programi Yonetici olarak calistirip Oyle Kayit Yapabilirsiniz" -> acikLAMAString = "Must run the program as an Administrator and create job";
			case " Dosya Bulunamadi***************" -> acikLAMAString = " File Not Found***************";
//				
			}
		}
		return acikLAMAString;
	}
}

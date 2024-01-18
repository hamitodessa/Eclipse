package obs.backup.ayarlar;

public class dilSecenek {
	
	static String tEXT = "" ;
	

	public static String dil(String text) {
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
			  
		}  
		return tEXT;
	}
}

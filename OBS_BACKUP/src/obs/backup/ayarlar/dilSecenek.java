package obs.backup.ayarlar;

public class dilSecenek {
	
	static String tEXT = "" ;
	

	public static String dil(String text) {
		switch(text) {
		  case "Hakkinda":
			  tEXT = "About";
			  break;
		  case "Ayarlar":
			  tEXT = "Settings";
			  break;
		  case "Dil":
			  tEXT = "Language";
			  break;
		  case "Tema":
			  tEXT = "Themes";
			  break;
		  case "Gorevler":
			  tEXT = "Jobs";
			  break;
		}  
		return tEXT;
	}



}

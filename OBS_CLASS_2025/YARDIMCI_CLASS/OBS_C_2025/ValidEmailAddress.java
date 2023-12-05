package OBS_C_2025;

import java.util.regex.Pattern; 

public class ValidEmailAddress {

	public static boolean isValid(String email) 
	{ 
		String emailRegex = "^[a-zA-Z0-9ğüşöçıİĞÜŞÖÇ_+&*-]+(?:\\."+ 
				"[a-zA-Z0-9_+&*-]+)*@" + 
				"(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
				"A-Z]{2,7}$"; 

		Pattern pat = Pattern.compile(emailRegex); 
		if (email == null) 
			return false; 
		return pat.matcher(email).matches(); 
	} 
}

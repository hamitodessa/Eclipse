package OBS_C_2025;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class ENCRYPT_DECRYPT_STRING {
	
	static SecretKey myDesKey  ; //= new SecretKey("com.sun.crypto.provider.DESKey@180d9") ;
	
	@SuppressWarnings("unused")
	public static void kEY () throws NoSuchAlgorithmException
	{
		KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
		 myDesKey = keygenerator.generateKey();
		 
	
			System.out.println( myDesKey);

	}
	public static  String eNCRYPT(String kelime) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher desCipher;
		desCipher = Cipher.getInstance("DES");
		byte[] text = kelime.getBytes("UTF8");
		desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
		byte[] textEncrypted = desCipher.doFinal(text);
		return  new String(textEncrypted);
	}
	public static String dCRYPT(byte[] kelime) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher desCipher;
		desCipher = Cipher.getInstance("DES");
		desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
		byte[] textDecrypted = desCipher.doFinal(kelime);
		return  new String(textDecrypted);
		}

}

package OBS_C_2025;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ENCRYPT_DECRYPT_STRING {
	
	static SecretKey myDesKey  ; //= new SecretKey("com.sun.crypto.provider.DESKey@180d9") ;
	
	@SuppressWarnings("unused")
	public static void kEY () throws NoSuchAlgorithmException
	{
	//	KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
	//	 myDesKey =   keygenerator.generateKey();
	//	System.out.println( myDesKey);
		
		//
	//	SecretKey secretKey = KeyGenerator.getInstance("DES").generateKey();
	//	String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
	//	System.out.println( encodedKey);
		byte[] decodedKey = Base64.getDecoder().decode("rsJ6ouC6CCA=");
		myDesKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
		//
	}
	public static  String eNCRYPT(String kelime) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher desCipher;
		desCipher = Cipher.getInstance("DES");
		byte[] text = kelime.getBytes("UTF8");
		desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
		byte[] textEncrypted = desCipher.doFinal(text);
		System.out.println(new String(textEncrypted));
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
	public static  byte[] eNCRYPT_manual(String kelime) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		String key = "Bar54321Bar54321";
		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		byte[] encrypted = cipher.doFinal(kelime.getBytes());
		return  encrypted;
		//return  new String(encrypted);
	}
	public static String dCRYPT_manual(byte[] kelime) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		System.out.println(new String(kelime));
		String key = "Bar54321Bar54321";
		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
		String decrypted = new String(cipher.doFinal(kelime));
		System.out.println(new String( new String(decrypted)));
		return  new String(decrypted);
	}

}

//String qwe = ENCRYPT_DECRYPT_STRING.eNCRYPT("Hamit_Okumus") ;
//System.out.println("DES=" +qwe);
//String asd = ENCRYPT_DECRYPT_STRING.dCRYPT(qwe.getBytes()) ;
//System.out.println("DES=" + asd);
/////
//qwe = ENCRYPT_DECRYPT_STRING.eNCRYPT_manual("Hamit_Okumus") ;
//System.out.println("AES=" +qwe);
//asd = ENCRYPT_DECRYPT_STRING.dCRYPT_manual(qwe.getBytes()) ;
//System.out.println("AES=" +asd);
/////


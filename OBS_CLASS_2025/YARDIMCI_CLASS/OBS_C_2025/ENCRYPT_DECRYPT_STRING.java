package OBS_C_2025;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.EncryptionMethod;

public class ENCRYPT_DECRYPT_STRING {
	
	static SecretKey myDesKey  ; //= new SecretKey("com.sun.crypto.provider.DESKey@180d9") ;
	
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
		//System.out.println(new String(textEncrypted));
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
	}
	public static String dCRYPT_manual(byte[] kelime) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		String key = "Bar54321Bar54321";
		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
		String decrypted = new String(cipher.doFinal(kelime));
		
		return  new String(decrypted);
	}
	public static void encryptedFile( String fileInputPath, String fileOutPath)
			  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			  IllegalBlockSizeException, BadPaddingException {
			String secretKey = "Bar54321Bar54321";
			 var key = new SecretKeySpec(secretKey.getBytes(), "AES");
			 var cipher = Cipher.getInstance("AES");
			 cipher.init(Cipher.ENCRYPT_MODE, key);

			 var fileInput = new File(fileInputPath);
			 var inputStream = new FileInputStream(fileInput);
			 var inputBytes = new byte[(int) fileInput.length()];
			 inputStream.read(inputBytes);

			 var outputBytes = cipher.doFinal(inputBytes);

			 var fileEncryptOut = new File(fileOutPath);
			 var outputStream = new FileOutputStream(fileEncryptOut);
			 outputStream.write(outputBytes);

			 inputStream.close();
			 outputStream.close();
			 
			 System.out.println("File successfully encrypted!");
			 System.out.println("New File: " + fileOutPath);
			}
	public static void decryptedFile(String fileInputPath, String fileOutPath)
			  throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException,
			  IllegalBlockSizeException, BadPaddingException {
		String secretKey = "Bar54321Bar54321";
			 var key = new SecretKeySpec(secretKey.getBytes(), "AES");
			 var cipher = Cipher.getInstance("AES");
			 cipher.init(Cipher.DECRYPT_MODE, key);

			 var fileInput = new File(fileInputPath);
			 var inputStream = new FileInputStream(fileInput);
			 var inputBytes = new byte[(int) fileInput.length()];
			 inputStream.read(inputBytes);

			 byte[] outputBytes = cipher.doFinal(inputBytes);

			 var fileEncryptOut = new File(fileOutPath);
			 var outputStream = new FileOutputStream(fileEncryptOut);
			 outputStream.write(outputBytes);

			 inputStream.close();
			 outputStream.close();
			 
			 System.out.println("File successfully decrypted!");
			 System.out.println("New File: " + fileOutPath);
			}
	@SuppressWarnings("resource")
	public static void zip_file_sifrele(String fileInputPath, String fileOutPath,String sifre) 
	{
		ZipParameters zipParameters = new ZipParameters();
		zipParameters.setEncryptFiles(true);
		zipParameters.setCompressionLevel(CompressionLevel.HIGHER);
		zipParameters.setEncryptionMethod(EncryptionMethod.AES);

		ZipFile zipFile = new ZipFile(fileOutPath, sifre.toCharArray());
		try {
			zipFile.addFile(new File(fileInputPath), zipParameters);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("resource")
	public static void zip_folder_sifrele(String folderInputPath, String folderOutPath,String sifre) 
	{
		ZipParameters zipParameters = new ZipParameters();
		zipParameters.setEncryptFiles(true);
		zipParameters.setCompressionLevel(CompressionLevel.HIGHER);
		zipParameters.setEncryptionMethod(EncryptionMethod.AES);
		ZipFile zipFile = new ZipFile(folderOutPath, sifre.toCharArray());
		try {
			zipFile.addFolder(new File(folderInputPath), zipParameters);
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
//byte[]  qaz =	ENCRYPT_DECRYPT_STRING.eNCRYPT_manual("oOk271972") ;
//System.out.println("Array=" +Arrays.toString(qaz));
//byte[] ewq = {-90, -12, -3, -79, 32, -23, 64, -33, -73, 94, -3, 26, -36, -105, 120, -46};
//qwe = 	 ENCRYPT_DECRYPT_STRING.dCRYPT_manual(ewq) ;
//System.out.println(qwe);


//byte[]  qaz =	ENCRYPT_DECRYPT_STRING.eNCRYPT_manual("197227oOk") ;
//String response = Arrays.toString(qaz);
//System.out.println(response);
//String[] byteValues = response.substring(1, response.length() - 1).split(",");
//byte[] bytes = new byte[byteValues.length];
//for (int i=0, len=bytes.length; i<len; i++) {
//   bytes[i] = Byte.parseByte(byteValues[i].trim());     
//}
//qwe = 	 ENCRYPT_DECRYPT_STRING.dCRYPT_manual(bytes) ;
//System.out.println(qwe);



package Backend;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES
{
	
	private static SecretKeySpec secretKey ;
    private static byte[] key ;
    
	
	public static void setKey(String myKey){
    	MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			//System.out.println(key.length);
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
	    	key = Arrays.copyOf(key, 16); // use only first 128 bit
	    	System.out.println("Key: "+new String(key,"UTF-8"));
	    	System.out.println("Key Length: "+key.length);
	    	secretKey = new SecretKeySpec(key, "AES");
		    
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    }
    
	public static byte[] encrypt(byte[] data, String key)
    {
			AES.setKey(key);
		
		try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
             return cipher.doFinal(data);
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: "+e.toString());
        }
        return null;
    }

    public static byte[] decrypt(byte[] data,String key)
    {
    	AES.setKey(key);
        try
        {
        	
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: "+e.toString());
        }
        return null;
    }
}
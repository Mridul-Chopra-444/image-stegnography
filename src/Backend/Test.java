package Backend;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import Backend.AES;

public class Test {
	public  byte[] originalfile;
	public  byte[] encryptedfile;
	
	
	public  void ReadData(String filename){
		try{
			originalfile = Files.readAllBytes(new File(filename).toPath());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public  void Encrypt(String filename,String outputfilename, String key){
		try{
			ReadData(filename);
			encryptedfile = AES.encrypt(originalfile,key);
			if(encryptedfile!=null){
				WriteData(outputfilename);
				System.out.println("Encryption Done");
			}
		}catch (Exception e)
		{
			System.out.println("Error while encrypting: "+e.toString());
		}
	}
	
	
	public  void WriteData(String filename){
		try{
			FileOutputStream fos = new FileOutputStream(new File(filename));
			fos.write(encryptedfile);
			fos.flush();
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public  void Decrypt(String filename, String outputfilename,String Key){

		ReadData(filename);
		encryptedfile = AES.decrypt(originalfile,Key);
		if(encryptedfile!=null){
			WriteData(outputfilename);
			System.out.println("Decryption Done");
		}
	}
	
	
}

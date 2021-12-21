/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author kunal
 */
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
//import java.util.Arrays;
import java.util.Scanner;
import stagneography.Home2Controller;

import javax.imageio.ImageIO;
public class decode {

	public static String b_msg="";
	public static int len = 0;

	
public static BufferedImage readImageFile(String COVERIMAGEFILE){
BufferedImage theImage = null;
File p = new File (COVERIMAGEFILE);
try{
theImage = ImageIO.read(p);
}catch (IOException e){
e.printStackTrace();
System.exit(1);
}
return theImage;
}


public static void DecodeTheMessage (BufferedImage yImage) throws Exception{
System.out.println("Decode the message");
int j=0;
int currentBitEntry=0;
String bx_msg="";
for (int x = 0; x < yImage.getWidth(); x++){
for ( int y = 0; y < yImage.getHeight(); y++){
if(x==0&&y<8){
	//System.out.println("enc "+yImage.getRGB(x, y)+" dec "+yImage.getRGB(x, y)+" "+b_msg);
	int currentPixel = yImage.getRGB(x, y);	
	int red = currentPixel>>16;
	red = red & 255;
	int green = currentPixel>>8;
	green = green & 255;
	int blue = currentPixel;
	blue = blue & 255;
	String x_s=Integer.toBinaryString(blue);
	bx_msg+=x_s.charAt(x_s.length()-1);
	len=Integer.parseInt(bx_msg,2); 
	
}
else if(currentBitEntry<len*8){
//System.out.println("enc "+yImage.getRGB(x, y)+" dec "+yImage.getRGB(x, y)+" "+b_msg);
	int currentPixel = yImage.getRGB(x, y);	
	int red = currentPixel>>16;
	red = red & 255;
	int green = currentPixel>>8;
	green = green & 255;
	int blue = currentPixel;
	blue = blue & 255;
	String x_s=Integer.toBinaryString(blue);
	b_msg+=x_s.charAt(x_s.length()-1);

	
	currentBitEntry++;	
	//System.out.println("curre "+currentBitEntry);
}
}
}
System.out.println("bin value of msg hided in img is "+b_msg);
}
}

package stagneography;


public class Crypto {
    
    private static String key;
    
    static String getKey()
    {
        ProduceKey();
        return key;
    }
    
    static void ProduceKey()
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        int n =20;
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) 
        { 
             int index= (int)(AlphaNumericString.length() * Math.random()); 
            sb.append(AlphaNumericString.charAt(index)); 
        } 
  
        key = sb.toString();
    }
    
    
}


package stagneography;
import java.io.IOException;
import java.sql.*;
public class sql {
    private static Connection cn;
    private static Statement st;
    private static ResultSet rs;
    private static String sql;
    public static String key;
    public static boolean Connect() throws IOException, ClassNotFoundException, SQLException, InterruptedException 
    {
        if(!InternetConnected())
            return false;
        Class.forName("com.mysql.cj.jdbc.Driver");  
 cn=DriverManager.getConnection(  
"jdbc:mysql://mysql1006.mochahost.com/itronixs_Image_Stegno--Image_Encrypt?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","itronixs_Steg_fx","Steg_fx"); 
    System.out.println("Connection Successful");
    return true;
    }
    
    // Sign Up function 
    public static int SignUp(String user , String pass,String key) throws SQLException, IOException, ClassNotFoundException, InterruptedException
    {
        if(!Connect())
            return -1;
        
        st=cn.createStatement();
        sql="Select * from users where email='"+user+"' ;";
        rs=st.executeQuery(sql);
        if(rs.next())
        {
            rs.close();
            cn.close();
            st.close();
            return 0;
        }
        else{
            sql="Insert into users values ('"+user+"','"+pass+"','"+key+"');";
            st=cn.createStatement();
            st.executeUpdate(sql);
            System.out.println("Successful");
            rs.close();
            cn.close();
            st.close();
            return 1;
        }
        
    }
    
    
    // Login function
    public static int Login(String user , String pass) throws SQLException, IOException, ClassNotFoundException, InterruptedException
    {
        if(!Connect())
            return -1;
        st=cn.createStatement();  
        sql = "Select * from users where email='"+user+"' and password='"+pass+"' ;";
        rs=st.executeQuery(sql);
        if(rs.next())
                    {
                      key=rs.getString("key");
                      return 1;
                       }
        else
           return 0;
    }
    
    
    // Internet Connectivity Test
    private static boolean InternetConnected() throws IOException, InterruptedException
    {
         Process process = java.lang.Runtime.getRuntime().exec("ping www.google.com"); 
        int x = process.waitFor(); 
        if (x == 0) { 
            
            return true;
        } 
        else { 
          
            return false;
        } 
    }
    
    
    
    // Change password
    
    public static int ChangePassword(String user, String pass,String newpass) throws SQLException, IOException, ClassNotFoundException, InterruptedException
    {
        if(!Connect())
        {return -1;}
        else{
                st=cn.createStatement();  
                sql = "Select * from users where email='"+user+"' and password='"+pass+"' ;";
                rs=st.executeQuery(sql);
                if(rs.next())
                {
                    sql="Update users set password='"+newpass+"' where email='"+user+"';";
                    st.executeUpdate(sql);
                    return 1;
                }
                else{
                return 0;
                }
        }
    }
    
    
    public static int ChangeEmail(String user, String pass,String newemail) throws SQLException, IOException, ClassNotFoundException, InterruptedException
    {
        if(!Connect())
        {return -1;}
        else{
                st=cn.createStatement();  
                sql = "Select * from users where email='"+user+"' and password='"+pass+"' ;";
                rs=st.executeQuery(sql);
                if(rs.next())
                {
                    sql = "Select * from users where email='"+user+"' ;";
                    rs=st.executeQuery(sql);
                    if(rs.next())
                        return 2;
                    sql="Update users set email='"+newemail+"' where email='"+user+"';";
                    st.executeUpdate(sql);
                    return 1;
                }
                else{
                return 0;
                }
        }
    }
    
     public static int DeleteAccount(String user, String pass) throws SQLException, IOException, ClassNotFoundException, InterruptedException
    {
        if(!Connect())
        {return -1;}
        else{
                st=cn.createStatement();  
                sql = "Select * from users where email='"+user+"' and password='"+pass+"' ;";
                rs=st.executeQuery(sql);
                if(rs.next())
                {
                    sql="Delete from users where email='"+user+"' and password='"+pass+"';";
                    st.executeUpdate(sql);
                    return 1;
                }
                else{
                return 0;
                }
        }
    }
     public static String RecvEmail(String email) throws SQLException, IOException, ClassNotFoundException, InterruptedException
     {
          
          if(!Connect())
        {return "-1";}
        else{
                st=cn.createStatement();  
                sql = "Select * from users where email='"+email+"';";
                rs=st.executeQuery(sql);
                if(rs.next())
                {
                    sql="Select * from users where email='"+email+"';";
                    rs=st.executeQuery(sql);
                    if(rs.next())
                    {
                       String key=rs.getString("key");
                       return key;
                    }
                }
                else
                {
                    return "2";
                }
                return "0";
     }
     }
}

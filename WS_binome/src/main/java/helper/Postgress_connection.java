package helper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Postgress_connection {
  
	public Connection getSQLServerConnection() throws Exception
  	{
          Connection conn = null;
        try{
           Class.forName("org.postgresql.Driver"); // Facultatif
     
       //     String driver = "postgresql.Driver";
            String url = "jdbc:postgresql://localhost:5432/operateur";
             String username = "postgres";
            String password = "123456";
              
            conn = DriverManager.getConnection(url, username, password); 
            System.out.println("Connecter");
        }catch(Exception ex){
            throw ex;
        }
        return conn;
   
  }
	
	/*public static Connection getSQLServerConnection() throws Exception
  	{
          Connection conn = null;
        try{
           Class.forName("org.postgresql.Driver"); // Facultatif
     
       //     String driver = "postgresql.Driver";
            String url = "jdbc:postgresql://localhost:5432/mobile_money";
             String username = "mobile";
            String password = "123456";
              
            conn = DriverManager.getConnection(url, username, password); 
            System.out.println("Connecter");
        }catch(Exception ex){
            throw ex;
        }
        return conn;
   
  } */
	
	public static void main(String[] args) throws SQLException {
		Connection conn=null;
		try {
			conn=new Postgress_connection().getSQLServerConnection();
		} catch(Exception exp) { System.out.println(exp.getMessage()); }
		finally {
			if(conn!=null)
				conn.close();
		}
	}

}

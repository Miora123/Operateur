package mobile.WS_binome.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDatabaseFactory;

import com.mongodb.internal.connection.Connection;

//import mobile.WS_binome.repository.AchatcreditRepository;

public class DbBean {
	 private final MongoDatabaseFactory mongo;
	    private final Connection postgres;

	    //@Autowired
	  //  private AchatcreditRepository repository;

	    @Autowired
	    public DbBean(MongoDatabaseFactory mongo) throws ClassNotFoundException, SQLException {
	        this.mongo = mongo;
	        this.postgres = getConnection();
	    }
	    public Connection getConnection() throws SQLException, ClassNotFoundException
	    {
	         Class.forName("org.postgresql.Driver");
	        String url="jdbc:postgresql://localhost:5432/systeme";
	          Connection con = (Connection) DriverManager.getConnection(url,"postgres","123456");
	          System.out.println(con+"connection postgres e");
	          return con;
	       
	    }

	    

		public MongoDatabaseFactory getMongo() {
			return mongo;
		}

		public Connection getPostgres() {
			return postgres;
		}
	    
}

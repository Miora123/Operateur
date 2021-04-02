package mobile.WS_binome;

    import java.util.ArrayList;

    import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDatabaseFactory;

import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClient;
    import com.mongodb.client.MongoClients;
    import com.mongodb.client.MongoCollection;
    import com.mongodb.client.MongoCursor;
    import com.mongodb.client.MongoDatabase;

//mport mobile.WS_binome.repository.AchatcreditRepository;

    public class ConnectMongoDB {
    	//private final MongoDatabaseFactory mongo;
    	 //@Autowired
    	 //private AchatcreditRepository repository;

    	   
	    public static MongoClient client = MongoClients.create("mongodb://127.0.0.1:27017");
	    public static String urlMongo="mongodb://127.0.0.1:27017";
    }	       
	       // @Autowired
    	   // public ConnectMongoDB(MongoDatabaseFactory mongo) throws ClassNotFoundException, SQLException {
    	     //   this.mongo = mongo;
    	        
    	/*    //}
	        public static ArrayList<Document> find(String table,String colonne,String valeur){
            MongoCursor cursor=null;
            try{
            		MongoDatabase db = client.getDatabase("telephone");
            		DBObject query =  new BasicDBObject().append(colonne,valeur);
            		DBCollection collection =db.getCollection(table);
                while(cursor.hasNext() ){
                    docs.add((Document) cursor.next());
                    System.out.println(docs.size()+"asasaa");
                }
                return docs;
              }catch(Exception ex){
                  throw ex;
              }finally{
                cursor.close();
            }
        }
        
        public static MongoClient getClient(){
            if(client==null){
                client=MongoClients.create(urlMongo);
            }
            return client;
        }
        public static void main(String[] args){
         
        }
}
*/
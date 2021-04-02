package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import helper.Postgress_connection;

public class Appel {
	int id;
	int temps;
	int idclient1;
	int idclient2;
	Timestamp dateappel;
	public Postgress_connection hlp = new Postgress_connection();
	
	public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
    
    public int getTemps() {
        return temps;
    }
 
    public void setTemps(int temps) {
        this.temps = temps;
    }
    
    public int getIdclient1() {
        return idclient1;
    }
 
    public void setIdclient1(int idclient1) {
        this. idclient1 =  idclient1;
    }
    
    public int getIdclient2() {
        return idclient2;
    }
 
    public void setIdclient2(int idclient2) {
        this.idclient2 = idclient2;
    }
    
    public Timestamp getDateappel() {
        return dateappel;
    }
 
    public void setDateappel(Timestamp dateappel) {
        this.dateappel = dateappel;
    }
    
    
   
    public void insert(int temps,int idclient1,int idclient2, Timestamp date)throws SQLException,Exception{
    	Connection conn=null;
    	PreparedStatement pst=null;
        try{
        	conn = hlp.getSQLServerConnection();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("INSERT INTO appel VALUES (DEFAULT,?,?,?,?)");
            pst.setInt(1,temps);
            pst.setInt(2,idclient1);
            pst.setInt(3,idclient2);
            pst.setTimestamp(4,date);
            System.out.println("suery insert: "+pst.toString()); 
            
            pst.executeUpdate();
            conn.commit();
        }catch(Exception ex){   
            conn.rollback();
            throw ex;
        }finally{
            if(pst!=null)pst.close();      
        }  
    }
    
    public ArrayList<Appel> findAll() throws Exception{
	     Connection conn=null;
	        PreparedStatement pst=null;
	        ResultSet result = null;
	        ArrayList<Appel> liste = new ArrayList<Appel>();
	        try{
	            conn  = hlp.getSQLServerConnection();
	            pst=conn.prepareStatement("SELECT * FROM appel",result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);            
	            result = pst.executeQuery();
	           while(result.next()){ 
	             Appel tmp = new Appel();
	  
	               tmp.setId(result.getInt(1));
	               tmp.setTemps(result.getInt(2));
	               tmp.setIdclient1(result.getInt(3));
	               tmp.setIdclient2(result.getInt(4));
	               tmp.setDateappel(result.getTimestamp(5));
	               liste.add(tmp);
	           }
	        }catch(Exception ex){ 
	            System.out.println(" Appel findAll error: "+ex.getMessage());
	            throw ex;
	        }finally{
	            if(result!=null)result.close();
	            if(pst!=null)pst.close();   
	            if(conn!=null)conn.close(); 
	        }  
	        return liste;
	    }
    
    
	public static void main(String[] argv) throws Exception{
        Connection conn=null;
        try{
      
        	 ArrayList<Appel> tab = new Appel().findAll();
            System.out.println("count: "+tab.size());
            
        } catch(Exception exp){ System.out.println(exp.getMessage()); 
        }
        finally{
            if(conn!=null) conn.close(); 
        }
    }
}

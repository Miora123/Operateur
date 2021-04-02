package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import helper.*;

public class Token {
	public int idClient;
    public String nom;
    public String numero;
    public String token;
    public String expiration;
    
    public String error;
    public String succes;
    
    
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
    
	public Token() {
		super();
	}
	public Token(int idClient,String nom,String numero,String token,String expiration) {
		super();
		this.idClient = idClient;
		this.numero = numero;
		this.token = token;
		this.expiration = expiration;
	}
		public Token findWhere(String token) throws Exception{
	     Connection conn=null;
	        PreparedStatement pst=null;
	        ResultSet result = null;
	        Token liste = new Token();
	        try{
	            conn  = new Postgress_connection().getSQLServerConnection();
	            pst=conn.prepareStatement("SELECT * FROM Token WHERE token=?",result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);            
	            pst.setString(1,token);
	            result = pst.executeQuery();
	           while(result.next()){ 
	        	   	liste = new Token(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5));
	           }
	        }catch(Exception ex){ 
	            throw ex;
	        }finally{
	            if(result!=null)result.close();
	            if(pst!=null)pst.close();   
	            if(conn!=null)conn.close(); 
	        }  
	        return liste;
	    }
	public void insert(Connection conn,String idClient,String nom,String numero,String token,String expiration)throws SQLException,Exception{
	    
    	
		 PreparedStatement pst=null;
 	        try{
 	            conn.setAutoCommit(false);
 	            pst = conn.prepareStatement("INSERT INTO Token(idClient,nom,numero,token,expiration) VALUES (?,?,?,?,CURRENT_TIMESTAMP+'2 hour'::INTERVAL)");
 	            pst.setInt(1,Integer.parseInt(idClient));
 	            pst.setString(2,nom);
 	            pst.setString(3,numero);
 	            pst.setString(4,token);
 	          
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
	public void delete(Connection conn,String token)throws SQLException,Exception{
        PreparedStatement pst=null;
        try{
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("DELETE FROM Token WHERE token=?");
            pst.setString(1,token);
            
            pst.executeUpdate();
            conn.commit();
        }catch(Exception ex){   
            conn.rollback();
            throw ex;
        }finally{
            if(pst!=null)pst.close();   
        }  
    }
    
}

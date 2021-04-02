package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import helper.Postgress_connection;

public class Oper {
	int id;
	String nom;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<Oper> findAll() throws Exception{
	     Connection conn=null;
	        PreparedStatement pst=null;
	        ResultSet result = null;
	        ArrayList<Oper> liste = new ArrayList<Oper>();
	        try{
	            conn  = new Postgress_connection().getSQLServerConnection();
	            pst=conn.prepareStatement("SELECT * FROM operateur",result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);            
	            result = pst.executeQuery();
	           while(result.next()){ 
	        	   Oper tmp = new Oper();
	               
	               tmp.setId(result.getInt(1));
	               tmp.setNom(result.getString(2));
	               liste.add(tmp);
	           }
	        }catch(Exception ex){ 
	            System.out.println(" Devise findAll error: "+ex.getMessage());
	            throw ex;
	        }finally{
	            if(result!=null)result.close();
	            if(pst!=null)pst.close();   
	            if(conn!=null)conn.close(); 
	        }  
	        return liste;
	    }
	public static void main(String[] args) throws Exception{
		 ArrayList<Oper> tab = new Oper().findAll();
         System.out.println("count: "+tab.size());
	}
}

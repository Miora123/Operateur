package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import helper.*;
public class Devise  extends BaseModel{
	
	
	public Devise(int id,String name)
	{
		this.setId(id);
		this.setNom(name);
	}
	public Devise() {}
	
	public ArrayList<Devise> findAllteste(){
		ArrayList<Devise> tab= new ArrayList<Devise>();
		tab.add(new Devise(1,"Noam"));
		tab.add(new Devise(1,"ANTOENJARA"));
		tab.add(new Devise(1,"GB"));
		
		return tab;
	}
	
	public ArrayList<Devise> findAll() throws Exception{
	     Connection conn=null;
	        PreparedStatement pst=null;
	        ResultSet result = null;
	        ArrayList<Devise> liste = new ArrayList<Devise>();
	        try{
	            conn  = new Postgress_connection().getSQLServerConnection();
	            pst=conn.prepareStatement("SELECT * FROM Devise",result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);            
	            result = pst.executeQuery();
	           while(result.next()){ 
	        	   Devise tmp = new Devise();
	               
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
}

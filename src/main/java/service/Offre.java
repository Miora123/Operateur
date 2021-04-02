package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;

import helper.Postgress_connection;

@CrossOrigin(origins="*",allowedHeaders="*")
public class Offre {
	int id;
	String nomOffre;
	double prixOffre;
	String code;
	int idOperateur;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNomOffre() {
		return nomOffre;
	}


	public void setNomOffre(String nomOffre) {
		this.nomOffre = nomOffre;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	public double getPrixOffre() {
		return prixOffre;
	}


	public void setPrixOffre(double prixOffre) {
		this.prixOffre = prixOffre;
	}


	public int getIdOperateur() {
		return idOperateur;
	}


	public void setIdOperateur(int idOperateur) {
		this.idOperateur = idOperateur;
	}
	
	
	
	public ArrayList<Offre> findOffre(String idOperateur) throws Exception
	{
		Connection conn=null;
        PreparedStatement pst=null;
        ResultSet result = null;
        ArrayList<Offre> liste = new ArrayList<Offre>();
        try
        {
        	conn  = new Postgress_connection().getSQLServerConnection();
        	pst=conn.prepareStatement("select*from Offre where idOperateur=?",result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);
        	pst.setInt(1,Integer.parseInt(idOperateur));
        	result = pst.executeQuery();
        	 while(result.next())
        	 {
        		 Offre f=new Offre();
        		 f.setId(result.getInt(1));
        		 f.setNomOffre(result.getString(2));
        		 f.setPrixOffre(result.getDouble(3));
        		 f.setCode(result.getString(4));
        		 f.setIdOperateur(result.getInt(5));
        		 liste.add(f);
        	 }
        }catch(Exception ex){ 
            System.out.println(" Offre findAll error: "+ex.getMessage());
            throw ex;
        }finally{
            if(result!=null)result.close();
            if(pst!=null)pst.close();   
            if(conn!=null)conn.close(); 
        }  
        return liste;
	}

	
	public ArrayList<Offre> findAllOffre() throws Exception
	{
		Connection conn=null;
        PreparedStatement pst=null;
        ResultSet result = null;
        ArrayList<Offre> liste = new ArrayList<Offre>();
        try
        {
        	conn  = new Postgress_connection().getSQLServerConnection();
        	pst=conn.prepareStatement("select*from Offre",result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);
        	result = pst.executeQuery();
        	 while(result.next())
        	 {
        		 Offre f=new Offre();
        		 f.setId(result.getInt(1));
        		 f.setNomOffre(result.getString(2));
        		 f.setPrixOffre(result.getDouble(3));
        		 f.setCode(result.getString(4));
        		 f.setIdOperateur(result.getInt(5));
        		 liste.add(f);
        	 }
        }catch(Exception ex){ 
            System.out.println(" Offre findAll error: "+ex.getMessage());
            throw ex;
        }finally{
            if(result!=null)result.close();
            if(pst!=null)pst.close();   
            if(conn!=null)conn.close(); 
        }  
        return liste;
	}
	
	
	
	public void deleteOffre(int id)throws SQLException,Exception{
    	Connection conn=null;
    	PreparedStatement pst=null;
        try{
        	conn  = new Postgress_connection().getSQLServerConnection();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("DELETE FROM OFFRE WHERE id=?");
            pst.setInt(1,id);
          
            System.out.println("offre deleted: "+pst.toString()); 
            
            pst.executeUpdate();
            conn.commit();
        }catch(Exception ex){   
            conn.rollback();
            throw ex;
        }finally{
            if(pst!=null)pst.close();      
        }  
    }
	public void insertOffre(Connection conn,Offre input)throws Exception{
        PreparedStatement pst=null;
        try{
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("INSERT INTO Offre VALUES (DEFAULT,?,?,?,1);");
            pst.setString(1,input.getNomOffre());
            pst.setDouble(2,input.getPrixOffre());
            pst.setString(3,input.getCode());
            System.out.println("query insert: "+pst.toString());  
            pst.executeUpdate();
            conn.commit();
        }catch(SQLException ex){   
            conn.rollback();
            throw ex;
        }finally{
            if(pst!=null)pst.close();      
        }  
    }
 

	public void updateOffre(Connection conn,Offre input,int indice)throws Exception{
        PreparedStatement pst=null;
        try{
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("UPDATE Devise SET nomOffre=?  ,prixOffre=?, code=?  WHERE id=?");
            pst.setString(1,input.getNomOffre());
            pst.setDouble(2,input.getPrixOffre());
            pst.setString(3,input.getCode());
            pst.setInt(4,indice);
            
            pst.executeUpdate();
            conn.commit();
        }catch(SQLException ex){   
            conn.rollback();
            throw ex;
        }finally{
            if(pst!=null)pst.close();      
        }  
    }
  
}
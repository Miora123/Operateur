package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import helper.Postgress_connection;

public class DetailsOffre {
	int id;
	int idOffre;
	int idType;
	int idAction;
	double valeur;
	String nomOffre;
	double prixOffre;
	String code;
	int idOperateur;
	String nomType;
	int durer ;
	String nomAction;
	String unite;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	//
	
	public int getIdOffre() {
		return idOffre;
	}


	public void setIdOffre(int idOffre) {
		this.idOffre = idOffre;
	}

	
	public int getIdType() {
		return idType;
	}


	public void setIdType(int idType) {
		this.idType = idType;
	}

	//
	public int getIdAction() {
		return idAction;
	}


	public void setIdAction(int idAction) {
		this.idAction= idAction;
	}

	//
	public double getValeur() {
		return valeur;
	}


	public void setValeur(double valeur) {
		this.valeur = valeur;
	}


	//
	public String getNomOffre() {
		return nomOffre;
	}


	public void setNomOffre(String nomOffre) {
		this.nomOffre = nomOffre;
	}


	public String getNomType() {
		
		return nomType;
	}


	public void setNomType(String nomType) {
		this.nomType = nomType;
	}
	
	public int getDurer() {
		return durer;
	}


	public void setDurer(int durer) {
		this.durer = durer;
	}
	
	public String getNomAction() {
		return nomAction;
	}


	public void setNomAction(String nomAction) {
		this.nomAction = nomAction;
	}
	
	public String getUnite() {
		return unite;
	}


	public void setUnite(String unite) {
		this.unite = unite;
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
	
	public ArrayList<DetailsOffre> findDetailsOffre(String idOffre) throws Exception
	{
		Connection conn=null;
        PreparedStatement pst=null;
        ResultSet result = null;
        ArrayList<DetailsOffre> liste = new ArrayList<DetailsOffre>();
        try
        {
        	conn  = new Postgress_connection().getSQLServerConnection();
        	pst=conn.prepareStatement("select*from v_offredetails where idOffre=?",result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);
        	pst.setInt(1,Integer.parseInt(idOffre));
        	result = pst.executeQuery();
        	 while(result.next())
        	 {
        		 DetailsOffre f=new DetailsOffre();
        		 f.setId(result.getInt(1));
        		 f.setIdOffre(result.getInt(2));
        		 f.setIdType(result.getInt(3));
        		 f.setIdAction(result.getInt(4));
        		 f.setValeur(result.getDouble(5));
        		 f.setNomOffre(result.getString(6));
        		 f.setPrixOffre(result.getDouble(7));
        		 f.setCode(result.getString(8));
        		 f.setIdOperateur(result.getInt(9));
        		 f.setNomType(result.getString(10));
        		 f.setDurer(result.getInt(11));
        		 f.setNomAction(result.getString(12));
        		 f.setUnite(result.getString(13));
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
	
	
	
	public void deleteDetailsOffre(int idOffre)throws SQLException,Exception{
    	Connection conn=null;
    	PreparedStatement pst=null;
        try{
        	conn  = new Postgress_connection().getSQLServerConnection();
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("DELETE FROM detailsOffre WHERE idoffre=?");
            pst.setInt(1,idOffre);
          
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
	
	public void insertDetailsOffre(Connection conn,DetailsOffre input)throws Exception{
        PreparedStatement pst=null;
        try{
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("INSERT INTO detailsOffre VALUES (Default,?,?,?,?);");
            pst.setInt(1,input.getIdOffre());
            pst.setInt(2,input.getIdType());
            pst.setInt(3,input.getIdAction());
            pst.setDouble(4,input.getValeur());
            
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
 

	public void updateDetailsOffre(Connection conn,DetailsOffre input,int idOffre, int idAction )throws Exception{
        PreparedStatement pst=null;
        try{
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("UPDATE detailsOffre SET valeur=? WHERE idOffre=? and idAction=?");
            pst.setDouble(1,input.getValeur());
            pst.setInt(2,idOffre);
            pst.setInt(3,idAction);
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

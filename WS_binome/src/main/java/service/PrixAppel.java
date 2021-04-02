package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import helper.Postgress_connection;

public class PrixAppel {
	int id;
	double prix;
	int temps;
	int idOperateur1;
	int idOperateur2;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getTemps() {
		return temps;
	}
	public void setTemps(int temps) {
		this.temps = temps;
	}
	public int getIdOperateur1() {
		return idOperateur1;
	}
	public void setIdOperateur1(int idOperateur1) {
		this.idOperateur1 = idOperateur1;
	}
	public int getIdOperateur2() {
		return idOperateur2;
	}
	public void setIdOperateur2(int idOperateur2) {
		this.idOperateur2 = idOperateur2;
	}
	
	public ArrayList<PrixAppel> findPrix(String idOperateur1,String idOperateur2) throws Exception
	{
		Connection conn=null;
        PreparedStatement pst=null;
        ResultSet result = null;
        ArrayList<PrixAppel> liste = new ArrayList<PrixAppel>();
        try
        {
        	conn  = new Postgress_connection().getSQLServerConnection();
        	pst=conn.prepareStatement("select*from prixAppel where idOperateur1=? and idOperateur2=?",result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);
        	pst.setInt(1,Integer.parseInt(idOperateur1));
        	pst.setInt(2,Integer.parseInt(idOperateur2));
        	result = pst.executeQuery();
        	 while(result.next())
        	 {
        		 PrixAppel f=new PrixAppel();
        		 f.setId(result.getInt(1));
        		 f.setPrix(result.getDouble(2));
        		 f.setTemps(result.getInt(3));
        		 f.setIdOperateur1(result.getInt(4));
        		 f.setIdOperateur2(result.getInt(5));
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
	public static void main(String[] arg) throws Exception{
		 ArrayList<PrixAppel> tab = new PrixAppel().findPrix("1","2");
		 System.out.println("count: "+tab.size());
       
	}
	
}

package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;

import helper.Postgress_connection;

public class Operateur {
	int id;
	String nom_op;
	String ref;
	int valeur;
	int key_num;
	int cout_num;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom_op() {
		return nom_op;
	}
	public void setNom_op(String nom_op) {
		this.nom_op = nom_op;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	public int getKey_num() {
		return key_num;
	}
	public void setKey_num(int key_num) {
		this.key_num = key_num;
	}
	public int getCout_num() {
		return cout_num;
	}
	public void setCout_num(int cout_num) {
		this.cout_num = cout_num;
	}
	
	public ArrayList<Operateur>  findOperateur() throws Exception{
		Connection conn=null;
		PreparedStatement pst=null;
        ResultSet result = null;
        ArrayList<Operateur> liste = new ArrayList<Operateur>();
        try {
        	conn = new Postgress_connection().getSQLServerConnection();
        	pst=conn.prepareStatement("SELECT * FROM Operateur",result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);           
            result = pst.executeQuery();
	            while(result.next()) 
	            {
	            	Operateur op=new Operateur();
	            	op.setId(result.getInt(1));
	            	op.setNom_op(result.getString(2));
	            	op.setRef(result.getString(3));
	            	op.setValeur(result.getInt(4));
	            	op.setKey_num(result.getInt(5));
	            	op.setCout_num(result.getInt(6));
	            	liste.add(op);
	            }
            } catch(Exception ex) {
            	 System.out.println(" Operateur findAll error: "+ex.getMessage());
 	            throw ex;
            }finally{
	            if(result!=null)result.close();
	            if(pst!=null)pst.close();   
	            if(conn!=null)conn.close();
        }
        return liste;
	}
	public static void main(String[] arg) throws Exception{
		 ArrayList<Operateur> tab = new Operateur().findOperateur();
         /*for(int i=0;i<tab.size();i++) {
        	 System.out.println(tab[i].getNom_op());
         }*/
	}
}
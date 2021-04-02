package service;

import java.sql.Date;


import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import helper.*;


public class Client extends BaseModel{
	
	 public Postgress_connection hlp = new Postgress_connection();
	    
	 	public int id;
	    public String nom;
	    public String prenom;
	    public String identite;
	    public String numero;
	    public String motDePasse;
	    public int idOperateur;
	    
	    public String succes="";
	    public String error="";
	    
	    public String token;
	    
	    public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
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
		public String getPrenom() {
			return prenom;
		}
		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}
		public String getIdentite() {
			return identite;
		}
		public void setIdentite(String identite) {
			this.identite = identite;
		}
		public String getNumero() {
			return numero;
		}
		public void setNumero(String numero) {
			this.numero = numero;
		}
		public String getMotDePasse() {
			return motDePasse;
		}
		public void setMotDePasse(String motDePasse) {
			this.motDePasse = motDePasse;
		}
		public int getIdOperateur() {
			return idOperateur;
		}
		public void setIdOperateur(int idOperateur) {
			this.idOperateur = idOperateur;
		}
		public Client(int id,String nom,String prenom,String identite,String numero,String motDePasse,int idOperateur){
	    	try {
	    	this.setId(id);
	    	this.setNom(nom);
	    	this.setPrenom(prenom);
	    	this.setIdentite(identite);
	    	this.setNumero(numero);
	    	this.setMotDePasse(motDePasse);
	    	this.setIdOperateur(idOperateur);
	    	} catch(Exception exp) { System.out.println(exp.getMessage()); }
	    }
	    public Client() {
	    }
	   
	    public String queryVerify(String nomTable,String[] parameter,String[] opt,String[] valiny) throws Exception
	    {
	        String query="";
	        String tmp="SELECT COUNT(*) FROM "+nomTable+" WHERE ";
	        if(parameter.length==opt.length || parameter.length==valiny.length)
	        {
	            for(int i=0;i<parameter.length;i++)
	            {
	             tmp+=" "+parameter[i]+" "+opt[i]+" '"+valiny[i]+"'";         
	             if(i+1<parameter.length)
	             {
	                tmp+=" AND ";
	             }
	            }
	            query=tmp;
	            
	        } else {
	                throw new Exception("length parameter and autre invalid");
	        }
	        return query;
	    }
	    
	    public String queryWhere(String nomTable,String[] parameter,String[] opt,String[] valiny) throws Exception
	    {
	        String query="";
	        String tmp="SELECT * FROM "+nomTable+" WHERE ";
	        if(parameter.length==opt.length || parameter.length==valiny.length)
	        {
	            for(int i=0;i<parameter.length;i++)
	            {
	             tmp+=" "+parameter[i]+" "+opt[i]+" '"+valiny[i]+"'";         
	             if(i+1<parameter.length)
	             {
	                tmp+=" AND ";
	             }
	            }
	            query=tmp;
	            
	        } else {
	                throw new Exception("length parameter and autre invalid");
	        }
	        return query;
	    }
	    
	    public int verify(Connection conn,String nomTable,String[] parameter,String[] opt,String[] valiny) throws Exception{
	        int count=0;
	         PreparedStatement pst=null;
	        ResultSet result = null;
	        String query="";
	        try{
	            query=this.queryVerify(nomTable,parameter,opt,valiny);
	            pst=conn.prepareStatement(query,result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);            
	            result = pst.executeQuery();
	           while(result.next()){ 
	               count=result.getInt(1);
	           }
	        } catch(Exception exp){ System.out.println(exp.getMessage()); }
	        return count;
	    }
	    
	    public ArrayList<Client> findAll() throws Exception{
	     Connection conn=null;
	        PreparedStatement pst=null;
	        ResultSet result = null;
	        ArrayList<Client> liste = new ArrayList<Client>();
	        try{
	            conn  = hlp.getSQLServerConnection();
	            pst=conn.prepareStatement("SELECT * FROM Client",result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);            
	            result = pst.executeQuery();
	           while(result.next()){ 
	               Client tmp = new Client();
	               
	         //      System.out.println("client: "+result.getString(2));
	               tmp.setId(result.getInt(1));
	               tmp.setNom(result.getString(2));
	               tmp.setPrenom(result.getString(3));
	               tmp.setIdentite(result.getString(4));
	               tmp.setNumero(result.getString(5));
	               tmp.setMotDePasse(result.getString(6));
	               tmp.setIdOperateur(result.getInt(7));
	               liste.add(tmp);
	           }
	        }catch(Exception ex){ 
	            System.out.println(" Client findAll error: "+ex.getMessage());
	            throw ex;
	        }finally{
	            if(result!=null)result.close();
	            if(pst!=null)pst.close();   
	            if(conn!=null)conn.close(); 
	        }  
	        return liste;
	    }
	    
	    public Client findByNum(String numero) throws Exception{
	     Connection conn=null;
	        PreparedStatement pst=null;
	        ResultSet result = null;
	        Client liste = new Client();
	        try{
	            conn  = hlp.getSQLServerConnection();
	            pst=conn.prepareStatement("SELECT * FROM Client WHERE numero=?",result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);            
	            pst.setString(1,numero);
	            System.out.println(pst.toString());
	            result = pst.executeQuery();
	           while(result.next()){ 
	               System.out.println("client: "+result.getString(2));
	               
	               liste.setId(result.getInt(1));
	               liste.setNom(result.getString(2));
	               liste.setPrenom(result.getString(3));
	               liste.setIdentite(result.getString(4));
	               liste.setNumero(result.getString(5));
	               liste.setMotDePasse(result.getString(6));
	               liste.setIdOperateur(result.getInt(7));  
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
	      
	    public void insert(String nom,String prenom,String identite,String numero,String motdepasse,int idOperateur)throws SQLException,Exception{
	    	Connection conn=null;
	    	PreparedStatement pst=null;
	        try{
	        	conn = hlp.getSQLServerConnection();
	            conn.setAutoCommit(false);
	            pst = conn.prepareStatement("INSERT INTO Client(nom,prenom,identite,numero,motDePasse,idOperateur) VALUES (?,?,?,?,?,?)");
	            pst.setString(1,nom);
	            pst.setString(2,prenom);
	            pst.setString(3,identite);
	            pst.setString(4,numero);
	            pst.setString(5,motdepasse);
	            pst.setInt(6,idOperateur);
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
	    public Client userConnected(String numero,String motDePasse) throws Exception{
		     Connection conn=null;
		        PreparedStatement pst=null;
		        ResultSet result = null;
		        Client liste = new Client();
		        try{
		            conn  = new Postgress_connection().getSQLServerConnection();
		            pst=conn.prepareStatement("SELECT * FROM Client WHERE numero=? AND motDePasse=?",result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);            
		            pst.setString(1,numero);
		            pst.setString(2,motDePasse);
		            
		            System.out.println(pst.toString());
		            
		            result = pst.executeQuery();
		           while(result.next()){ 
		              
		               liste.setId(result.getInt(1));
		               liste.setNom(result.getString(2));
		               liste.setPrenom(result.getString(3));
		               liste.setIdentite(result.getString(4));
		               liste.setNumero(result.getString(5));
		               liste.setMotDePasse(result.getString(6));
		               liste.setIdOperateur(result.getInt(7));
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
	    public Client sign(String numero,String motDePasse) throws Exception
	    {
	    	Connection conn=null;
	    	Client connected = new Client();
	    	try
	    	{
		    	if(numero==null || motDePasse==null || numero=="" || motDePasse=="")
		    	{
		    		this.error = "Champs invalid";
		    		
		     	} else 
		    	{
		     	//	String pssword="sha256("+mdp+")";
		    		String[] parameter= {"numero","motDePasse"};
		    		String[] opt= {"=","="};
		    		String[] valiny= {numero,motDePasse};
		    		int verify = new Fonction().verify("Client", parameter, opt, valiny);
		    		if(verify==0)
		    		{
		    			this.error = "numero or password incorrect";
		    		
		    		} 
	    			if(verify!=0){
		    			connected = this.userConnected(numero,motDePasse);
		    		}
	        	}
	    	} catch(SQLException exp) { System.out.println(exp.getMessage()); }
	    	System.out.println("error: "+this.error);
	    	return connected;
	    }
	    
	    public double solde(int idclient) throws Exception{
		     Connection conn=null;
		        PreparedStatement pst=null;
		        ResultSet result = null;
		        Client liste = new Client();
		        double solde= 0;
		        try{
		            conn  = new Postgress_connection().getSQLServerConnection();
		            pst=conn.prepareStatement("SELECT * FROM soldeClient WHERE idclient=?",result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);            
		            pst.setInt(1,idclient);
		            System.out.println(pst.toString());
		            result = pst.executeQuery();
		           while(result.next()){ 
		           
		              solde=(result.getDouble(1));		           }
		        }catch(Exception ex){ 
		            throw ex;
		        }finally{
		            if(result!=null)result.close();
		            if(pst!=null)pst.close();   
		            if(conn!=null)conn.close(); 
		        }  
		        return solde;
		    }
	    
	    
	     /*public void update(Connection conn,Client input,String indice)throws SQLException,Exception{
	        PreparedStatement pst=null;
	        try{
	            conn.setAutoCommit(false);
	            pst = conn.prepareStatement("UPDATE Client SET nom=?,prenom=?,email=?,mdp=?,age=?,naissance=? WHERE id=?");
	            pst.setString(1,input.getNom());
	            pst.setString(2,input.getPrenom());
	            pst.setString(3,input.getEmail());
	            pst.setString(4,input.getMdp());
	            pst.setString(5,""+input.getAge());
	            pst.setString(6,input.getNaissance().toString());
	            pst.setInt(7,Integer.parseInt(indice));
	            
	            pst.executeUpdate();
	            conn.commit();
	        }catch(Exception ex){   
	            conn.rollback();
	            throw ex;
	        }finally{
	            if(pst!=null)pst.close();      
	        }  
	    }
	     
	     public void delete(String indice)throws SQLException,Exception{
	        PreparedStatement pst=null;
	        Connection conn=null;
	        try{
	            conn=this.hlp.getSQLServerConnection();
	            conn.setAutoCommit(false);
	            pst = conn.prepareStatement("DELETE FROM Client WHERE id=?");
	            pst.setString(1,indice);
	            
	            pst.executeUpdate();
	            conn.commit();
	        }catch(Exception ex){   
	            conn.rollback();
	            throw ex;
	        }finally{
	            if(pst!=null)pst.close();   
	             if(conn!=null)conn.close(); 
	        }  
	    }*/
	    
	    /*public static void main(String[] argv) throws Exception{
	        Connection conn=null;
	        try{
	      //      Client q=new Client().findWhere("1");
	            ArrayList<Client> tab = new Client().findAll();
	            System.out.println("count: "+tab.size());
	            conn=new Client().hlp.getSQLServerConnection();
	            Client input = new Client();
	            input.nom="KOTO";
	            input.prenom="Kto kely";
	            input.setEmail("koto@gmail.com");
	            input.mdp="123456";
	            input.setAge(23);
	            input.naissance=new Date(1998, 02, 26);

	            new Client().insert(conn, input);
	            System.out.println(new Client().succes.getMessage()); 
	        } catch(Exception exp){ System.out.println(exp.getMessage()); 
	        }
	        finally{
	            if(conn!=null) conn.close(); 
	        }
	    }*/
	    public static void main(String[] args) throws Exception{
			 ArrayList<Client> tab = new Client().findAll();
	         //System.out.println("count: "+tab.size());
	         tab.add( new Client(1,"Andria","Patrick","123783267","0343208193","AndriaPatrick",1));
		}

}

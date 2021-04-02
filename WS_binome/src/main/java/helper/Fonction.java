package helper;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Fonction {
	public static String encryptPassword(String motDePasse) throws NoSuchAlgorithmException, UnsupportedEncodingException {

	    MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	    crypt.reset();
	    crypt.update(motDePasse.getBytes("UTF-8"));
	    return new BigInteger(1, crypt.digest()).toString(60);
	}
	public String queryVerify(String nomTable,String[] parameter,String[] opt,String[] valiny) throws Exception
    {
        String query="";
        String tmp="SELECT (COUNT(*)) obj FROM "+nomTable+" WHERE ";
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
        System.out.println(query);
        return query;
    }

	public int verify(String nomTable,String[] parameter,String[] opt,String[] valiny) throws Exception{
        int count=0;
        Connection conn=null;
        PreparedStatement pst=null;
        ResultSet result = null;
        String query="";
        try{
        	conn =new Postgress_connection().getSQLServerConnection();
            query=this.queryVerify(nomTable,parameter,opt,valiny);
            pst=conn.prepareStatement(query,result.TYPE_SCROLL_INSENSITIVE,result.CONCUR_READ_ONLY);            
            result = pst.executeQuery();
           while(result.next()){ 
               count=result.getInt(1);
               
               System.out.println("verify: "+count);
           }
        } catch(Exception exp){ System.out.println(exp.getMessage()); }
        finally{
 	            if(pst!=null)pst.close();  
 	            if(conn!=null)pst.close(); 
 	        }
        return count;
    }
}

package controller;

import java.sql.Connection;
import java.sql.Date;

import helper.Fonction;
import helper.Postgress_connection;
import service.Client;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import service.Token;

public class loginController {
	public Token controller_login(String numero,String motDePasse) throws Exception{
		
		Connection conn = null;
		Client connected = new Client();
		Token donner = new Token();
		try {
			conn = new Postgress_connection().getSQLServerConnection();
			if(numero!=null || numero!="" || motDePasse!="" || motDePasse!=null)
			{
				
				String[] parameter= {"numero","motDePasse"};
				String[] opt = {"=","="};
				String[] valiny = {numero,motDePasse };
				int verify = new Fonction().verify("Client", parameter, opt, valiny);
				 connected = new Client().sign(numero,motDePasse);
				
				if(verify==0)
				{
					donner.error="numero or motDePasse Incorrecte";
					donner.succes="";
				} else {
					donner.error="";
					donner.succes="Connected";
					DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date(verify);
					String codage = motDePasse+""+format.format(date).toString()+""+numero;
					String token = Fonction.encryptPassword(codage);
				    connected.token=token;
					new Token().insert(conn,""+connected.getId(),connected.getNom(),connected.getNumero(),token,format.format(date).toString());
					donner= new Token().findWhere(token);
				}
			}
		} catch(Exception exp) { throw exp; }
		finally {
			if(conn!=null) conn.close();
		}
		
		return donner;
	}
}

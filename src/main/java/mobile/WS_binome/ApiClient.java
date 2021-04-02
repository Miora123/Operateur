package mobile.WS_binome;

import java.sql.Connection;


import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import controller.loginController;
import helper.JsonRequest;
import helper.Postgress_connection;
import  service.*;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping
public class ApiClient {
	public Postgress_connection hlp = new Postgress_connection();
	
	@GetMapping(path="api/cl")
	public  ArrayList<Client> get_index() throws Exception
	{
		return new Client().findAll();
	}
	

	@GetMapping(path="api/solde/{idclient}")
	public JsonRequest get(@PathVariable int idclient) throws Exception
	{
		double repo=new Client().solde(idclient);
		 JsonRequest rep=new JsonRequest(200,repo,"ok");
		return rep;
	}
	
	/*@GetMapping(path="api/clients")
	public ArrayList<Client> get_index()
	{
		ArrayList<Client> liste = new ArrayList<Client>();
		
		try 
		{
			liste.add( new Client(1,"Andria","Patrick","123783267","0343208193","AndriaPatrick",1));
			//liste.add( new Client(1,"Bogossy","GB","noam@gmail.com","123456",12,new Date(26,2,1998)));
			liste= new Client().findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return liste;
	}*/
	
	
	@PostMapping(path="api/client")
	public void get_post(@RequestBody Client input)  throws SQLException
	{
		//Connection conn=null;
		try 
		{
			//conn = hlp.getSQLServerConnection();
			//conn = new Client().hlp.getSQLServerConnection();
			Client c = new Client();
			c.insert(input.getNom(),input.getPrenom(),input.getIdentite(),input.getNumero(),input.getMotDePasse(),input.getIdOperateur());
		} catch(Exception exp) {
			exp.printStackTrace();
		}
		
		
	}
	@PostMapping(path="api/loginclient")
	@CrossOrigin
	    public Token get_login(@RequestParam("numero") String numero,@RequestParam("motDePasse") String motDePasse) throws Exception
	   {		   
		 	return  new loginController().controller_login(numero, motDePasse);
	   }
}

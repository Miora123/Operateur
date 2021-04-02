package mobile.WS_binome;

import java.sql.Connection;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import helper.JsonRequest;
import helper.Postgress_connection;
import service.Depot;
import service.Offre;



@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping
public class ApiDepot {
	
	
	@PostMapping(value = "api/depot")
	public JsonRequest get_post(@RequestParam("valeur") double valeur,@RequestParam("idclient") int idclient) throws Exception
	{
	           
		String a= "create  depot  Succes";
		Connection conn=null;
		try {			
			conn = new Postgress_connection().getSQLServerConnection();
			Depot of=new Depot();
			of.insertDepot(valeur,idclient)	;	
			return new JsonRequest(200,of,"Depot created");
		}
		catch(Exception exp) { 
					ArrayList axp=new ArrayList();
					axp.add(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
				 return new JsonRequest(21,axp,"error");
			} finally {
				if(conn!=null) conn.close();
			}
		
	    }
	
	
	@PutMapping(value = "api/validation/{id}")
    public JsonRequest get_put(@PathVariable int id,String etat) throws Exception
 	{           
		String[] a= {"put nom: Offre Succes"};
		Connection conn=null;
		try 
		{				
			conn = new Postgress_connection().getSQLServerConnection();
			new Depot().updateDepot(conn,etat,id);
			return new JsonRequest(200,a,"Depot modifile");
		}
		catch(Exception exp) 
		{ 				
			ArrayList axp=new ArrayList();
			  axp.add(new ResponseEntity<>(HttpStatus.NOT_FOUND));
			  return new JsonRequest(21,axp,"error");
		} finally 
		{
			if(conn!=null) conn.close();
		}
		
    }

}

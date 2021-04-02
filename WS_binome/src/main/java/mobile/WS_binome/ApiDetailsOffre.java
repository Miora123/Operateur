package mobile.WS_binome;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import helper.JsonRequest;
import helper.Postgress_connection;
import service.DetailsOffre;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping
public class ApiDetailsOffre {
	@GetMapping(path="api/detailsoffre/{idoffre}")
	public JsonRequest   get_index(@PathVariable String idoffre)throws Exception
	{
		List<DetailsOffre> liste = new DetailsOffre().findDetailsOffre(idoffre);
		JsonRequest rep=new JsonRequest(200,(ArrayList)liste,"ok");
		return rep;
	  }

	
	@DeleteMapping(path="api/deleteDetailsOffre/{idoffre}")
	public JsonRequest deleteoffre(@PathVariable int idoffre) {
		
		try 
		{
			DetailsOffre c = new DetailsOffre();
			c.deleteDetailsOffre(idoffre);
			JsonRequest rep=new JsonRequest(200,c,"ok");
			return rep;
		} catch(Exception exp) {
			 ArrayList ax=new ArrayList();
             ax.add(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
             return new JsonRequest(21,ax,"votre achat n a pas ete effectuer");
		}
		
	}
	
	@PostMapping(value = "api/detailsOffre")
	public JsonRequest get_post(DetailsOffre imput) throws Exception
	{
	           
		String a= "create  DetailsOffre Succes";
		Connection conn=null;
		try {			
			conn = new Postgress_connection().getSQLServerConnection();
			new DetailsOffre().insertDetailsOffre(conn,imput);			
			return new JsonRequest(200,null,"detailsOffre created");
		}
		catch(Exception exp) { 
				ArrayList axp=new ArrayList();
				  axp.add(new ResponseEntity<>(HttpStatus.NOT_FOUND));
				  return new JsonRequest(21,axp,"error");
			} finally {
				if(conn!=null) conn.close();
			}
		
	    }
	 
	  @PutMapping(value = "detailsOffre/{idOffre}/{idAction}")
	    public JsonRequest get_put(@PathVariable int idOffre,DetailsOffre imput,@PathVariable int idAction) throws Exception
	 	{           
			String[] a= {"put nom: DetailsOffre Succes"};
			Connection conn=null;
			try 
			{				
				conn = new Postgress_connection().getSQLServerConnection();
				
				new DetailsOffre().updateDetailsOffre(conn,imput,idOffre,idAction);
				return new JsonRequest(200,a,"detailsOffre modifie");
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

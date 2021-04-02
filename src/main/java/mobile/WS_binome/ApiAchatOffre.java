package mobile.WS_binome;

import java.sql.Connection;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import helper.JsonRequest;
import helper.Postgress_connection;
import service.Achatoffre;



@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping
public class ApiAchatOffre {
	@PostMapping(value = "api/achatOffre")
	public JsonRequest get_post(@RequestParam("idclient") int idclient,@RequestParam("idoffre") int idoffre,@RequestParam("payement") int payement) throws Exception
	{
	    //   return "lol";    
		String a= "achat reussi";
		Connection conn=null;
		try {		
			conn = new Postgress_connection().getSQLServerConnection();
			Achatoffre of=new Achatoffre();
			of.insert( idclient,idoffre,payement);	
			
			return new JsonRequest(200,null,a);
		}
		catch(Exception exp) { 
					ArrayList axp=new ArrayList();
				 axp.add(new ResponseEntity<>(HttpStatus.NOT_FOUND));
				 return new JsonRequest(21,axp,"error");
			} finally {
				if(conn!=null) conn.close();
			}
		
	    }

}

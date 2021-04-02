package mobile.WS_binome;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import controller.loginController;
import helper.JsonRequest;
import helper.Postgress_connection;
import service.Appel;
import service.Client;
import service.Offre;
import service.Token;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping
public class ApiAppel {
	public Postgress_connection hlp = new Postgress_connection();
	
	@PostMapping(value = "api/Appel")
	public JsonRequest get_post(@RequestParam("temps") int temps,@RequestParam("idclient1") int idclient1,@RequestParam("numero") String numero,@RequestParam("dateAppel") Timestamp dateAppel) throws Exception
	{
	           
		String a= "Simulation appel reussi";
		Connection conn=null;
		try {		
			
			Client c= new Client().findByNum(numero);
			conn = new Postgress_connection().getSQLServerConnection();
			String mess="numero invalide";
			Appel of=new Appel();
			if(c.getId()!=idclient1) {
				of.insert(temps,idclient1,c.getId(), dateAppel);	
				 mess="reussi";
			}
			return new JsonRequest(200,mess,a);
		}
		catch(Exception exp) { 
					ArrayList axp=new ArrayList();
					axp.add(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
				 return new JsonRequest(21,axp,"error");
			} finally {
				if(conn!=null) conn.close();
			}
		
	    }
	
	

	@GetMapping(path="api/allapp")
	public  ArrayList<Appel> get_index() throws Exception
	{
		return new Appel().findAll();
	}
	
	
	 
}

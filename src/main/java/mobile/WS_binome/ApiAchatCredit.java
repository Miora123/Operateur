package mobile.WS_binome;

import java.sql.Connection;
import java.sql.Timestamp;
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
import service.Achatcredit;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping
public class ApiAchatCredit {

	@PostMapping(value = "api/Achatcredit")
	public JsonRequest get_post(@RequestParam("idclient") int idclient,@RequestParam("prix") double prix) throws Exception
	{
	    //   return "lol";    
		String a= "achat reussi";
		Connection conn=null;
		try {		
			conn = new Postgress_connection().getSQLServerConnection();
			Achatcredit of=new Achatcredit();
			of.insert( idclient,prix);	
			
			return new JsonRequest(200,null,a);
		}
		catch(Exception exp) { 
					ArrayList axp=new ArrayList();
				 axp.add(new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR));
				 return new JsonRequest(21,axp,"error");
			} finally {
				if(conn!=null) conn.close();
			}
		
	    }
	
	
	 
}

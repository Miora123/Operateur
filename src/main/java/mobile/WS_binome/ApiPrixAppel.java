package mobile.WS_binome;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.*;
@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping("/prixappel")
public class ApiPrixAppel {
	@GetMapping(value="/{id1}/{id2}")
	public ArrayList<PrixAppel> get_index(@PathVariable("id1") String idOperateur1,@PathVariable ("id2") String idOperateur2 ) throws Exception
	{
		return new PrixAppel().findPrix(idOperateur1,idOperateur2);
	}
}

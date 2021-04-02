package mobile.WS_binome;

import java.util.ArrayList;


import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.Oper;
//import service.Devise;
import service.Operateur;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping
public class ApiOperator 
{
	@GetMapping(path="api/operateur")
	public  ArrayList<Oper> get_index() throws Exception
	{
		return new Oper().findAll();
	}
	
	/*@GetMapping(path="api/operateur/  {id}")
	public  ArrayList<Operateur> get_index2(@PathVariable("id") String id) throws Exception
	{
		return new Operateur().findOperateur();
	}*/
}

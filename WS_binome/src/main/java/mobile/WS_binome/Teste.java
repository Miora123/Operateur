package mobile.WS_binome;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import  service.*;

@RestController
@RequestMapping
public class Teste {
	
	@GetMapping(path="api/teste")
	public String get_index()
	{
		return "teste ok";
	}
	
	@GetMapping(path="api/devise")
	public  ArrayList<Devise> get_index1() throws Exception
	{
		return new Devise().findAll();
	}
	
	
}

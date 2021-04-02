package mobile.WS_binome;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.*;


@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping
public class ApiOper {
	@GetMapping(path="api/oper")
	public  ArrayList<Oper> get_index() throws Exception
	{
		return new Oper().findAll();
	}
}

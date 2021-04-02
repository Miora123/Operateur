package mobile.WS_binome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

public class WsBinomeApplication {
	public static void main(String[] args) {
		SpringApplication.run(WsBinomeApplication.class, args);
	}
}

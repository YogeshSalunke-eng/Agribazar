package agribazar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgribazarApplication {

	public static void main(String[] args) {
		System.out.println("application started");
		SpringApplication.run(AgribazarApplication.class, args);
	}

}

package socialmedia.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class ServerApplication {
	@PostConstruct
	public void init() {
		// Set timezone to Europe/Bucharest
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Bucharest"));
		System.out.println("Timezone set to: " + TimeZone.getDefault().getID());
	}
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		System.out.println("----Aplicatia a pornit cu succes!----");
	}

}

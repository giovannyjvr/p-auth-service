package store.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients(basePackages = {
	"store.account"
})
@SpringBootApplication
public class AuthApplication {

	// *** Cria o RestTemplate para uso via @Autowired no AuthService ***
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	  return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}

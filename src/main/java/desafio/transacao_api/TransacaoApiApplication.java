package desafio.transacao_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
    servers = {
        @Server(url = "https://friendly-space-cod-pjrwr664qwqvf6659-3336.app.github.dev")
    }
)
@SpringBootApplication
public class TransacaoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransacaoApiApplication.class, args);
	}

}

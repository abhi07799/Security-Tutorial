package com.securitytutorial;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SecurityTutorialApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SecurityTutorialApplication.class, args);
    }

	@Bean
	public OpenAPI swaggerConfiguration()
	{
		Server devServer = new Server();
		devServer.setUrl("http://localhost:8080");
		devServer.setDescription("Server URL in Development environment");


		Info info = new Info()
				.title("Security Tutorial")
				.version("1.0")
				.description("This API exposes endpoints to manage tutorials.")
				.termsOfService("None as of now");

		return new OpenAPI().info(info).servers(List.of(devServer));
	}

}

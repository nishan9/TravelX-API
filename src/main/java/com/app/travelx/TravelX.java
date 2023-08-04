package com.app.travelx;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title="Testing Management Information Systessm",
				contact=@Contact(name="JAmes Bond",email="anc@Qa.com",url="www.bbc.com"),
				description="This our First Information System",
				version="78.98"
		)
)
public class TravelX {
	public static void main(String[] args) {
		SpringApplication.run(TravelX.class, args);
	}

}

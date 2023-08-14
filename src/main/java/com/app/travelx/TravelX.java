package com.app.travelx;
import com.google.gson.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Type;

@SpringBootApplication
public class TravelX {
	public static void main(String[] args) {
		SpringApplication.run(TravelX.class, args);
	}

	@Configuration
	@EnableSwagger2
	public class SpringFoxConfig {
		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any())
					.build();
		}
	}

	public class SpringfoxJsonToGsonAdapter implements JsonSerializer<Json> {

		@Override
		public JsonElement serialize(Json json, Type type, JsonSerializationContext context) {
			return JsonParser.parseString(json.value());
		}

		@Bean
		public GsonHttpMessageConverter gsonHttpMessageConverter() {
			GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
			converter.setGson(gson());
			return converter;
		}

		private Gson gson() {
			final GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter());
			return builder.create();
		}
	}
}


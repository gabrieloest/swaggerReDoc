package br.com.softplan.example.swaggerReDoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicates;

import br.com.softplan.example.swaggerReDoc.model.Student;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SwaggerReDocApplication {

	public static HashMap<Long, Student> hmStudent;

	@Bean
	public Docket swaggerSpringPlugin() {
		return new Docket(DocumentationType.SWAGGER_2)
			.useDefaultResponseMessages(false)
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("br.com.softplan.example.swaggerReDoc"))
			.paths(Predicates.not(PathSelectors.regex("/error.*")))
			.build()
			.useDefaultResponseMessages(false)
			.globalResponseMessage(RequestMethod.GET, responseConfiguration());
	}

	private List<ResponseMessage> responseConfiguration() {
		final List<ResponseMessage> responseMessageList = new ArrayList<ResponseMessage>();
		responseMessageList.add(new ResponseMessageBuilder().code(500).message("Failure").build());
		responseMessageList.add(new ResponseMessageBuilder().code(403).message("Forbidden").build());
		responseMessageList.add(new ResponseMessageBuilder().code(401).message("Unauthorized").build());
		responseMessageList.add(new ResponseMessageBuilder().code(404).message("Not Found").build());
		return responseMessageList;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Example Sudent CRUD")
			.description("Example application using Spring boot + Rest + Swagger + ReDoc")
			.contact(new Contact("Gabriel Oest", "http://softplan.com.br", "gabriel.oest@softplan.com.br"))
			.license("Apache License Version 2.0")
			.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
			.version("1.0.0-0")
			.build();
	}

	public static void main(String[] args) {
		SwaggerReDocApplication.hmStudent = new HashMap<Long, Student>();

		Student one = new Student("John", "math");
		SwaggerReDocApplication.hmStudent.put(new Long(one.getId()), one);

		SpringApplication.run(SwaggerReDocApplication.class, args);

		Student two = new Student("Jane", "history");
		SwaggerReDocApplication.hmStudent.put(new Long(two.getId()), two);
	}
}

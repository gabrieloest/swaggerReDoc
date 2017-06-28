package br.com.softplan.example.swaggerReDoc;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.softplan.example.swaggerReDoc.model.Student;

@SpringBootApplication
public class SwaggerReDocApplication {

	public static HashMap<Long, Student> hmStudent;

	public static void main(String[] args) {
		SwaggerReDocApplication.hmStudent = new HashMap<Long, Student>();

		Student one = new Student("John", "math");
		SwaggerReDocApplication.hmStudent.put(new Long(one.getId()), one);

		SpringApplication.run(SwaggerReDocApplication.class, args);

		Student two = new Student("Jane", "history");
		SwaggerReDocApplication.hmStudent.put(new Long(two.getId()), two);
	}
}

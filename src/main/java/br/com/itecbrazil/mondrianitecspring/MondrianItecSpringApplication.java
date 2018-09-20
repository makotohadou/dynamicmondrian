package br.com.itecbrazil.mondrianitecspring;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import br.com.itecbrazil.mondrianitecspring.rest.MondrianSOAP;

@SpringBootApplication
public class MondrianItecSpringApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MondrianItecSpringApplication.class, args);
				
	}
	
	@Bean
	public ServletRegistrationBean<MondrianSOAP> servletRegistrationBean(){
		ServletRegistrationBean<MondrianSOAP> servlet = new ServletRegistrationBean<MondrianSOAP>(new MondrianSOAP(),"/xmla");
		try {
			servlet.addInitParameter("DataSourcesConfig", new ClassPathResource("datasources.xml").getURL().toString());
		} catch (IOException e) {
			// TODO nothing
			e.printStackTrace();
		}

		return servlet;
	}
}

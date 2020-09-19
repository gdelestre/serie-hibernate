package fr.springboot.serie_hibernate;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@EnableEncryptableProperties
@SpringBootApplication
public class SerieHibernateApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(new Class[]{SerieHibernateApplication.class});
    }

    public static void main(String[] args) {
        SpringApplication.run(SerieHibernateApplication.class, args);
    }
}

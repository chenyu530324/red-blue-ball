package com.jichen.redblueball.etl;

import com.jichen.redblueball.etl.service.killer.KillerEtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.JobExecutionExitCodeGenerator;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static java.util.Collections.emptyList;

@Configuration
@ComponentScan(basePackages = {"com.jichen.redblueball"})
@SpringBootApplication
public class Application {

    @Autowired
    private KillerEtl killerEtl;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        KillerEtl killerEtl = (KillerEtl) applicationContext.getBean("killerEtl");
        killerEtl.etl(emptyList());
        SpringApplication.exit(applicationContext, new JobExecutionExitCodeGenerator());
    }

}


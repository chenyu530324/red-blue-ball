package com.jichen.redblueball.importer;

import com.jichen.redblueball.importer.service.HistoryDataImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.JobExecutionExitCodeGenerator;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan(basePackages = {"com.jichen.redblueball"})
@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        HistoryDataImporter importer = (HistoryDataImporter) applicationContext.getBean("historyDataImporter");
        LOGGER.info("Input args is " + Arrays.toString(args));
        importer.importHistoryData(args);
        LOGGER.info("Import history data successfully");
        SpringApplication.exit(applicationContext, new JobExecutionExitCodeGenerator());
    }

}


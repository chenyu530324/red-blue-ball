package com.jichen.redblueball.etl.service;


import org.springframework.context.ConfigurableApplicationContext;

public interface EtlService {

    boolean etl(ConfigurableApplicationContext ctx);

}

package br.com.cinq.spring.data.sample;

import br.com.cinq.spring.data.sample.controller.CityController;
import br.com.cinq.spring.data.sample.controller.CountryController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Register Jersey modules
 *
 * @author Adriano Kretschmer
 */
@Component
@ApplicationPath("rest")
public class Config extends ResourceConfig {

    public Config() {
        register(CityController.class);
        register(CountryController.class);
    }


}
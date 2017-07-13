package br.com.cinq.spring.data.sample.controller;

import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("countries")
public class CountryController {

    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryRepository repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Country> findCountries() {
        return repo.findAll();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response saveCities(@RequestBody List<Country> countries) {
        try {
            countries.forEach(country -> repo.save(country));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.error("Error saving countries.", e);
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}

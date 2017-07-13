package br.com.cinq.spring.data.sample.controller;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.QCity;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
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
@Path("cities")
public class CityController {
    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private CityRepository repo;

    @GET
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<City> findCities(@QueryParam("country") String countryName) {
        QCity city = QCity.city;

        BooleanExpression expression = null;
        if (countryName != null) {
            expression = city.country.name.like(String.format("%%%s%%", countryName));
        }

        return (List<City>) repo.findAll(expression);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response saveCities(@RequestBody List<City> cities) {
        try {
            cities.forEach(city -> repo.save(city));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.error("Error saving cities.", e);
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}

package br.com.cinq.spring.data.sample.unit;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
//import br.com.cinq.spring.data.sample.entity.City;
//import br.com.cinq.spring.data.sample.entity.Country;
//import br.com.cinq.spring.data.sample.repository.CityRepository;

/**
 * Eye candy: implements a sample in using JpaRespositories
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("unit")
@DataJpaTest
public class CityRepositoryTest {

    @Autowired
    private CityRepository dao;

    @Test
    public void testQueryPerson() {

        Assert.assertNotNull(dao);

        Assert.assertTrue(dao.count()>0);

        Country country = new Country();
        country.setId(3L); // Should be France

        List<City> list = dao.findByCountry(country);

        Assert.assertEquals(2, list.size());
    }
}

package br.com.cinq.spring.data.repository.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import br.com.cinq.spring.data.sample.entity.Country;
//import br.com.cinq.spring.data.sample.repository.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
@DataJpaTest
public class CountryRepositoryTest {

//    @Autowired
//    private CountryRepository dao;

    @Test
    public void testGelAllCountries() {

//        Assert.assertNotNull(dao);

//        long count = dao.count();

//        Assert.assertTrue(count > 0);

//        List<Country> countries = dao.findAll();

//        Assert.assertEquals((int) count, countries.size());
    }

    @Test
    public void testFindOneCountry() {

//        Assert.assertNotNull(dao);

//        List<Country> countries = dao.findLikeName("Fra");

//        Assert.assertEquals(1, countries.size());

    }

}

package br.com.cinq.spring.data.sample.repository;

import br.com.cinq.spring.data.sample.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long>, QueryDslPredicateExecutor<Country> {
    List<Country> findByNameContaining(String name);
}

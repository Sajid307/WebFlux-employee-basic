package com.learn.webFluxBasic.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.learn.webFluxBasic.entity.Employee;

import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, Integer> {

	  @Query("{ 'name': ?0 }")
	  Flux<Employee> findByName(final String name);

}

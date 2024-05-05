package com.learn.webFluxBasic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.learn.webFluxBasic.entity.Employee;
import com.learn.webFluxBasic.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;
 
  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody Employee e) {
    employeeService.create(e);
  }
 
  @GetMapping("/{id}")
  public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") Integer id) {
    Mono<Employee> e = employeeService.findById(id);
    return new ResponseEntity<Mono<Employee>>(e, HttpStatus.OK);
  }
 
  @GetMapping("/name/{name}")
  public Flux<Employee> findByName(@PathVariable("name") String name) {
    return employeeService.findByName(name);
  }
 
  @GetMapping
  public Flux<Employee> findAll() {
    Flux<Employee> emps = employeeService.findAll();
    return emps;
  }
 
  @PutMapping("/update")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Employee> update(@RequestBody Employee e) {
    return employeeService.update(e);
  }
 
  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable("id") Integer id) {
    employeeService.delete(id).subscribe();
  }
 
}

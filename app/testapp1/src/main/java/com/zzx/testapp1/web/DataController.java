package com.zzx.testapp1.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzx.testapp1.dao.PersonRepository;
import com.zzx.testapp1.domain.Person;

@RestController
public class DataController {

	@Autowired
	PersonRepository personRepository;
	
	@RequestMapping("/save")
	public Person save(Person person){
		Person p = personRepository.save(person);
		return p;
	}
	
	@RequestMapping("/q1")
	public List<Person> q1(String address){
		List<Person> people = personRepository.findByAddress(address);
		return people;
	}
	
	@RequestMapping("/q2")
	public Person q2(String name, String address){
//		Person people = personRepository.findByNameAndAddress(name, address);
//		Person people = personRepository.withNameAndAddressQuery(name, address);
		Person people = personRepository.withNameAndAddressNamedQuery(name, address);
		return people;
	}
	
	@RequestMapping("/sort")
	public List<Person> sort(){
		List<Person> peoples = personRepository.findAll(new Sort(Direction.ASC, "age"));
		return peoples;
	}
	
	@RequestMapping("/page")
	public Page<Person> page(){
		Page<Person> pagePeople = personRepository.findAll(new PageRequest(1, 2));
		return pagePeople;
	}
}

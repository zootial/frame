package com.zzx.testapp1.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import com.zzx.common.dao.domain.PO;

@Entity
@NamedQuery(name = "Person.withNameAndAddressNamedQuery", query = "select p from Person p where p.name=?1 and address=?2")
public class Person extends PO {

	private String name;
	
	private Integer age;
	
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}

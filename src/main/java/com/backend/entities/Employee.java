package com.backend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
	
//	hibernate uses proxies to create proxies to create proxy objects.
//	So, we shd always create default constructor wheneve we create paramaterised constructor
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private  String designation;
	private String email;
	
	public Employee(String name, String designation, String email) {
		this.name=name;
		this.designation=designation;
		this.email=email;
	}

}

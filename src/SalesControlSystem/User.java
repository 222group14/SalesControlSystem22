package SalesControlSystem;

import java.util.ArrayList;
import java.util.List;

import Enums.Gender;

public class User {

	private String name;
	private int age;
	private Gender gender;

	public User(String name, int age, Gender gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;

		// insert: error checkings
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
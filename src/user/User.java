package src.user;

import src.incommon.Gender;

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		// implement
		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (other != null && other instanceof User) {
			User otherUser = (User) other;
			if (name.equals(otherUser.name) && age == otherUser.age && gender.equals(otherUser.gender))
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hCode = name.hashCode() * 3 + 1;
		hCode += 7*age;
		return hCode;
	}	
}
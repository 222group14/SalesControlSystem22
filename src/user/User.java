package src.user;

import src.incommon.Gender;

public class User {

	private String name;
	private int age;
	private Gender gender;
	private String username;
	private String password;

	public User(String name, int age, Gender gender, String username, String password) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.username = username;
		this.password = password;
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

	public String getUserName(){
		return username;
	}

	public String getPassword(){
		return password;
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

	public void setUserName(String username){
		this.username = username;
	}

	public void setPassword(String password){
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Name: " +name + "\n");
		sb.append(" Username: " + username + "\n");
		sb.append(" Age: " + age + "\n");
		sb.append(" Gender: " + gender.name() + "\n");
		return sb.toString();
	}

	@Override
	public boolean equals(Object other) {
		if (other != null && other instanceof User) {
			User otherUser = (User) other;
			if (name.equals(otherUser.name) && age == otherUser.age && gender.equals(otherUser.gender) 
			&& username.equals(otherUser.username) && password.equals(otherUser.password))
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hCode = name.hashCode() * 3 + 1;
		hCode += 7*age;
		hCode += username.hashCode();
		hCode += password.hashCode();
		return hCode;
	}	
}
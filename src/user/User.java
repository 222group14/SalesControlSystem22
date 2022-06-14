package src.user;

import src.product.Gender;

/**
 * 
 * A general concrete User class which can be extended for various kind of users.
 */ 
public class User {

	/** name of the user */
	private String name;
	
	/** age of the user */
	private int age;
	
	/** gender of the user */
	private Gender gender;
	
	/** username of the user */
	private String username;

	/** password of the user */	
	private String password;

	/**
	 * Constructs a user with given properties
	 * @param name Name of the manager
	 * @param age Age of the manager
	 * @param gender Gender of the manager
	 * @param username Username of the manager
	 * @param password Password of the manager
	 */ 
	public User(String name, int age, Gender gender, String username, String password) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.username = username;
		this.password = password;
	}

	/**
	 * Getter for name.
	 * @return String
	 */ 
	public String getName() {
		return name;
	}

	/**
	 * Getter for age.
	 * @return int
	 */ 
	public int getAge() {
		return age;
	}

	/**
	 * Getter for gender.
	 * @return Gender
	 */ 
	public Gender getGender() {
		return gender;
	}

	/**
	 * Getter for username.
	 * @return String
	 */ 
	public String getUserName(){
		return username;
	}

	/**
	 * Getter for password.
	 * @return String
	 */ 
	public String getPassword(){
		return password;
	}

	/**
	 * Setter for name.
	 * @param name name of the user
	 */ 
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter for age.
	 * @param age age of the user
	 */ 
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Setter for gender.
	 * @param gender gender of the user
	 */ 
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Setter for username.
	 * @param username username
	 */ 
	public void setUserName(String username){
		this.username = username;
	}

	/**
	 * Setter for password.
	 * @param password password as String
	 */ 
	public void setPassword(String password){
		this.password = password;
	}

	/**
	 * String representation of User information.
	 * @return String
	 */ 
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Name: " +name + "\n");
		sb.append(" Username: " + username + "\n");
		sb.append(" Age: " + age + "\n");
		sb.append(" Gender: " + gender.name() + "\n");
		return sb.toString();
	}

	/**
	 * Compares both Users.
	 * @param other other User
	 * @return true if both User's are equal.
	 */ 
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

	/**
	 * Returns unique hash code for user.
	 * @return hash code
	 */ 
	@Override
	public int hashCode() {
		int hCode = name.hashCode() * 3 + 1;
		hCode += 7*age;
		hCode += username.hashCode();
		hCode += password.hashCode();
		return hCode;
	}	
}
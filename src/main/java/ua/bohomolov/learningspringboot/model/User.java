package ua.bohomolov.learningspringboot.model;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	private final UUID userUid;

	private final String firstName;

	private final String lastName;

	private final Gender gender;

	private final Integer age;

	private final String email;

	public User(
			@JsonProperty("id") UUID userUid,
			@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName,
			@JsonProperty("gender") Gender gender,
			@JsonProperty("age") Integer age,
			@JsonProperty("email") String email) {
		this.userUid = userUid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.email = email;
	}

	@JsonProperty("id")
	public UUID getUserUid() {
		return userUid;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public Integer getAge() {
		return age;
	}

	public String getEmail() {
		return email;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public int getDateOfBirth() {
		return LocalDate.now().minusYears(age).getYear();
	}

	public enum Gender {
		MALE,
		FEMALE
	}

	public static User newUser(UUID userUid, User user) {
		return new User(userUid, user.firstName, user.lastName, user.gender, user.age, user.email);
	}
}

package com.example.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "userinfo")
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String phno;
	private String role;
	private Integer age;	
	private String gender;
	private boolean loggedIn=true;


	public User(@NotBlank String username, 
            @NotBlank String password) {
    this.username = username;
    this.password = password;
    this.loggedIn = false;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return Objects.equals(email, user.email) && Objects.equals(password, user.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email, password, loggedIn);

	}
	  @Override
	    public String toString() {
	        return "User{" +
	                "id=" + id +
	                ", username='" + email + '\'' +
	                ", password='" + password + '\'' +
	                ", loggedIn=" + loggedIn +
	                '}';
}
}

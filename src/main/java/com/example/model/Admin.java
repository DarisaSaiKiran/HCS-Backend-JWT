package com.example.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
@Table

public class Admin {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	@Email
	private String email;
	private String password;
	private boolean loggedIn;
	
	
	 public Admin(@NotBlank String email, 
             @NotBlank String password) {
     this.email = email;
     this.password = password;
     this.loggedIn = false;
	 }
	 
	 
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Admin)) return false;
	        Admin user = (Admin) o;
	        return Objects.equals(email, user.email) &&
	                Objects.equals(password, user.password);
	    }
	    @Override
	    public int hashCode() {
	        return Objects.hash(id, email, password, 
	                            loggedIn);
	    }
	    @Override
	    public String toString() {
	        return "Admin{" +
	                "id=" + id +
	                ", username='" + email + '\'' +
	                ", password='" + password + '\'' +
	                ", loggedIn=" + loggedIn +
	                '}';
	    }


		public Admin() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
}

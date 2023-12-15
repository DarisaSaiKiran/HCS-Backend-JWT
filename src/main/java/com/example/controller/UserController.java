package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.CenterNotFoundException;
import com.example.exception.ListEmptyException;
import com.example.imp.UserImp;
import com.example.model.Status;
import com.example.model.User;
import com.example.repository.UserRepo;

@RestController
@RequestMapping("/hello")
@CrossOrigin(origins = "http://localhost:3008")
public class UserController {
	@Autowired
	private UserImp userservice;

	@Autowired
	private UserRepo u;



	@GetMapping("/getall")
	public ResponseEntity<List<User>> getAll() throws ListEmptyException {
		List<User> allUsers = userservice.getAll();
		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
	}

	@GetMapping("/get-user-by-username/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userservice.getUserByUsername(username);
	}

	@PostMapping("/register")
	public String addUser(@RequestBody User obj) {
		return userservice.addUser(obj);
	}

	@PutMapping("/updateuser")
	public ResponseEntity<User> updateUser(@RequestBody User student) {
		student = userservice.updateUser(student);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		String response = userservice.deleteUser(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) throws CenterNotFoundException {
		User ruser = userservice.getUserById(id);
		return new ResponseEntity<User>(ruser, HttpStatus.OK);
	}

	@ResponseBody
	@PostMapping("/login")
	public Integer loginUser(@Valid @RequestBody User user) {
		List<User> users = u.findAll();
		for (User other : users) {
			if (other.equals(user)) {
				user.setLoggedIn(true);
//				u.save(user);
				return(other.getId());
			}
		}

		return 0;
	}
	
	
//	 @PostMapping("/login")
//	    public Status loginUser(@Valid @RequestBody User user) {
//	        List<User> users = u.findAll();
//
//	        for (User other : users) {
//	            if (other.equals(user)) {
//	                user.setLoggedIn(true);
////	                u.save(user);
//	                return Status.SUCCESS;
//	            }
//	        }
//
//	        return Status.FAILURE;
//	    }

	
}


package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.imp.AdminService;
import com.example.model.Admin;
import com.example.repository.AdminRepository;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3008")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private AdminRepository u;

	@PostMapping("/add")
	public String addAdmin(@RequestBody Admin admin) {
		return adminService.addAdmin(admin);
	}
//
	@PostMapping("/login")
	public String loginUser(@Valid @RequestBody Admin user) {
		List<Admin> users = u.findAll();
		for (Admin other : users) {
			if (other.equals(user)) {
				user.setLoggedIn(true);
//				u.save(user);
				return "Success";
			}
		}
		return "Fail";
	}
	
	
//	@PostMapping(value = "/login")
//	public ResponseEntity<String> loginAdmin(@RequestParam String email, @RequestParam String password)
//			throws InvalidCredentialsException {
//		return new ResponseEntity<>(adminService.login(email, password), HttpStatus.FOUND);
//	}

}

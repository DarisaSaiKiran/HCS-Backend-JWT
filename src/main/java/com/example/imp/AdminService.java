package com.example.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.InvalidCredentialsException;
import com.example.model.Admin;
import com.example.repository.AdminRepository;

@Service
public class AdminService {
	
	
	@Autowired
	private AdminRepository adminRepo;

	public String addAdmin(Admin admin) {
		
		Admin anotherAdmin=adminRepo.findByEmail(admin.getEmail());
		if (anotherAdmin != null) {
			return "Admin already exists please try again";
		}
		String email = admin.getEmail();
		adminRepo.save(admin);
		return "Admin added succesfully";
	}
	
	
	public String login(String email, String password) throws InvalidCredentialsException {
		if (adminRepo.existsByEmail(email)) {
			Admin user = adminRepo.getReferenceByEmail(email);
			if (user.getPassword().equals(password)) {
				return "Successful login";
			}
		}
		return "Please Check Credentials";
	}
	
	
	

}

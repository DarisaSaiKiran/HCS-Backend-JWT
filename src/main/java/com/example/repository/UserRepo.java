package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Appointment;
import com.example.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	List<User> getUserById(int id);
	User findByUsername(String username);
	boolean existsByEmail(String email);
	User getReferenceByEmail(String email);
	User findByEmail(String email);
	User findByPhno(String phno);
}

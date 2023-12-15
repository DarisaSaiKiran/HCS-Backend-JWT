package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

	Admin findByEmail(String email);

	boolean existsByEmail(String email);

	Admin getReferenceByEmail(String email);
	

}

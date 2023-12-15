package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Center;
import com.example.model.Test;
@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
	Test findByTestName(String testName);
	
	  List<Test> findTestsByCentersId(int centerId);

}

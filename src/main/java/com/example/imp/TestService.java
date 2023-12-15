package com.example.imp;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.CenterNotFoundException;
import com.example.exception.ListEmptyException;
import com.example.model.Center;
import com.example.model.Test;
import com.example.repository.CenterRepository;
import com.example.repository.TestRepository;
@Service
public class TestService {

	@Autowired
	TestRepository testRepo;
	@Autowired
	CenterRepository centerRepo;
	

	public String saveTest(Test test) {
		Test anotherTest =testRepo.findByTestName(test.getTestName());
		if (anotherTest != null) {
			return "Test already exists please try again";
		}
		String testName = test.getTestName();	
		testRepo.save(test);
		return "Test Added Succesfully";
	}

	public List<Test> getAll() {

		return testRepo.findAll();

	}

	public String removeTestId(int testId) {
		if (testRepo.existsById(testId)) {
			Test test = testRepo.getReferenceById(testId);
			testRepo.deleteById(testId);
			return "Test " + testId + " Deleted";

		} else {
			return "Test not Found";

		}
	}
	
	
	public List<Test> gettests() throws ListEmptyException {
		List<Test> allTests =  testRepo.findAll();
		if(ListEmptyException.checkTests(allTests)) {
			throw new ListEmptyException("No tests available.");
		}
		return allTests;
	}
	
//	public Optional<Test> getTestById(int id) {
//		return testRepo.findById(id);
//	}
	
	
	
	public Test getTestById(int id) throws CenterNotFoundException {
		List<Test> allTests = testRepo.findAll();
		if (!CenterNotFoundException.checkIfTestExist(allTests, id)) {
			throw new CenterNotFoundException("No details found for the given ID");
		}
		return testRepo.findById(id).get();
	}

//	  public Test assignTestToCenter(Integer testId, Integer centerId) {
//	        Set<Center> centerSet = null;
//	        Test test = testRepo.findById(testId).get();
//	        Center center = centerRepo.findById(centerId).get();
//	        centerSet =  test.getCenters();
//	        centerSet.add(center);
//	        test.setCenters(centerSet);
//	        return testRepo.save(test);
//	    }

}

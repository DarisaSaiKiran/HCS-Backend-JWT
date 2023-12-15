package com.example.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.CenterNotFoundException;
import com.example.exception.ListEmptyException;
import com.example.exception.ResourceNotFoundException;
import com.example.imp.TestService;
import com.example.model.Center;
import com.example.model.Test;
import com.example.repository.CenterRepository;
import com.example.repository.TestRepository;
@CrossOrigin(origins="http://localhost:3008")

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	TestService testService;
	
	@Autowired
	CenterRepository centerRepo;
	
	@Autowired
	TestRepository testRepo;
	
	
	@PostMapping("/addtest")
	public String addTest(@RequestBody Test test) {
		return testService.saveTest(test);
	}
	
//	@GetMapping("/gettests")
//	public List<Test> getTests() {
//		return testService.getAll();
//	}
	

	@GetMapping("/all")
	public ResponseEntity<List<Test>> gettests() throws ListEmptyException{
		List<Test> allTests = testService.gettests();
		return new ResponseEntity<List<Test>>(allTests,HttpStatus.OK);
	}
	
	
	
	@DeleteMapping(value = "/{testId}")
	public String deleteTest(@PathVariable int testId) {
		return testService.removeTestId(testId);
	}
	
	
//	@GetMapping("/{id}")
//	public Optional<Test> getTestById(@PathVariable("id") int id) {
//		return testService.getTestById(id);
//	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Test> getTestById(@PathVariable("id") int id) throws CenterNotFoundException{
		Test rtest = testService.getTestById(id);
		return new ResponseEntity<Test>(rtest,HttpStatus.OK);
	}
	
//	 @PutMapping("/{testId}/center/{centerId}")
//	    public Test assignTestToCenter(
//	            @PathVariable Integer testId,
//	            @PathVariable Integer centerId
//	    ){
//	        return testService.assignTestToCenter(testId, centerId);
//	    }

	
	 @PostMapping("/centers/tests/{centerId}")
	  public ResponseEntity<Test> addTest(@PathVariable(value = "centerId") Integer centerId, @RequestBody Test testRequest) {
	    Test test = centerRepo.findById(centerId).map(center -> {
	      Integer testId = testRequest.getId();
	      
	      // tag is existed
	      if (testId != 0L) {
	        Test _test = testRepo.findById(testId)
	            .orElseThrow(() -> new ResourceNotFoundException("Not found Test with id = " + testId));
	        center.addTest(_test);
	        centerRepo.save(center);
	        return _test;
	      }
	      
	      // add and create new Tag
	      center.addTest(testRequest);
	      return testRepo.save(testRequest);
	    }).orElseThrow(() -> new ResourceNotFoundException("Not found Center with id = " + centerId));

	    return new ResponseEntity<>(test, HttpStatus.CREATED);
	  }
	 
	 
	 
	 @GetMapping("/centers/{centerId}/test")
	  public ResponseEntity<List<Test>> getAllTestsByCenterId(@PathVariable(value = "centerId") Integer centerId) {
	    if (!centerRepo.existsById(centerId)) {
	      throw new ResourceNotFoundException("Not found Tutorial with id = " + centerId);
	    }

	    List<Test> tests = testRepo.findTestsByCentersId(centerId);
	    return new ResponseEntity<>(tests, HttpStatus.OK);
	  }
	 
	 @DeleteMapping("/centers/{centerId}/tests/{testId}")
	  public ResponseEntity<HttpStatus> deleteTestFromCenter(@PathVariable(value = "centerId") int centerId, @PathVariable(value = "testId") int testId) {
	    Center center = centerRepo.findById(centerId)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Center with id = " + centerId));
	    
	    center.removeTest(testId);
	    centerRepo.save(center);
	    
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	 
	 @GetMapping("/tests/{testId}/center")
	  public ResponseEntity<List<Center>> getAllCentersByTestId(@PathVariable(value = "testId") Integer testId) {
	    if (!testRepo.existsById(testId)) {
	      throw new ResourceNotFoundException("Not found Test  with id = " + testId);
	    }

	    List<Center> centers = centerRepo.findCentersByTestId(testId);
	    return new ResponseEntity<>(centers, HttpStatus.OK);
	  }
	

	
	
}

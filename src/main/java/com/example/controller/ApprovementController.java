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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.CenterNotFoundException;
import com.example.exception.ListEmptyException;
import com.example.imp.ApprovementService;
import com.example.model.Appointment;
import com.example.model.Approvement;
@RequestMapping("/approvement")
@RestController
@CrossOrigin(origins = "http://localhost:3008")
public class ApprovementController {
	
	@Autowired
	 private ApprovementService apprService;
	
	@PostMapping("/add/{appId}")
	public ResponseEntity<Approvement> addApprovement(@PathVariable Integer appId, 
			 @RequestBody Approvement approvement)  {
		Approvement newApprove = apprService.addApprovement(appId,approvement);
		return new ResponseEntity<Approvement>(newApprove, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteApp(@PathVariable("id") int id) {
		String response = apprService.deleteApp(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Approvement>> getall() throws ListEmptyException {
		List<Approvement> allApps = apprService.getapps();
		return new ResponseEntity<List<Approvement>>(allApps, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<Approvement> getAppById(@PathVariable("userId") int userId) throws CenterNotFoundException {
		Approvement app = apprService.getAppById(userId);
		return new ResponseEntity<Approvement>(app, HttpStatus.OK);
	}
}

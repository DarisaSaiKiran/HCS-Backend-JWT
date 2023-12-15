package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
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
import com.example.imp.CenterService;
//import com.example.imp.CustomUserDetailsService;
import com.example.model.Center;
//import com.example.util.JwtUtil;

@RestController
@RequestMapping("/center")
@CrossOrigin(origins = "http://localhost:3008")

public class CenterController {

	@Autowired
	private CenterService centerService;

	@PostMapping("/addcenter")
	public String addCenter(@RequestBody Center center) {
		return centerService.addCenter(center);
	}

	@DeleteMapping("/{centerId}")
	public String deleteCenter(@PathVariable("centerId") Integer centerId) {
		return centerService.removeCenter(centerId);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Center>> getcenters() throws ListEmptyException {
		List<Center> allCenters = centerService.getcenters();
		return new ResponseEntity<List<Center>>(allCenters, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Center> getCenterById(@PathVariable("id") int id) throws CenterNotFoundException {
		Center rcenter = centerService.getCenterById(id);
		return new ResponseEntity<Center>(rcenter, HttpStatus.OK);
	}

	
	@PutMapping("/update/{id}")
	public String updateCenter(@PathVariable(value = "id") int id,  @RequestBody Center center) {
		return centerService.updateCenter(id, center);
	}
}

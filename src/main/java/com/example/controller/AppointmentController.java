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
import com.example.imp.AppointmentService;
import com.example.imp.UserImp;
import com.example.model.Appointment;
import com.example.model.User;
import com.example.repository.AppointmentRepository;
import com.example.repository.UserRepo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@CrossOrigin(origins = "http://localhost:3008")
//@JsonIgnoreProperties
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	AppointmentService appService;

	@Autowired
	UserImp userService;

	@Autowired
	AppointmentRepository appRepo;

	@Autowired
	UserRepo userRepo;

	@GetMapping("/all")
	public ResponseEntity<List<Appointment>> getappointments() throws ListEmptyException {
		List<Appointment> allAppointments = appService.getappointments();
		return new ResponseEntity<List<Appointment>>(allAppointments, HttpStatus.OK);
	}

	@PostMapping("/{userId}/add/{centerId}/{testId}")
	public ResponseEntity<Appointment> addAppointment(@PathVariable Integer userId, @PathVariable Integer centerId,
			@PathVariable Integer testId, @RequestBody Appointment appointment) throws CenterNotFoundException {
		Appointment newAppointment = appService.addAppointment(userId, centerId, testId, appointment);
		return new ResponseEntity<Appointment>(newAppointment, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteApp(@PathVariable("id") int id) {
		String response = appService.deleteApp(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Appointment> getAppById(@PathVariable("id") int id) throws CenterNotFoundException {
		Appointment app = appService.getAppById(id);
		return new ResponseEntity<Appointment>(app, HttpStatus.OK);
	}
}

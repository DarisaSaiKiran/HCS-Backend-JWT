package com.example.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.exception.CenterNotFoundException;
import com.example.exception.ListEmptyException;
import com.example.model.Appointment;
import com.example.model.Center;
import com.example.model.Test;
import com.example.model.User;
import com.example.repository.AppointmentRepository;
import com.example.repository.CenterRepository;
import com.example.repository.TestRepository;
import com.example.repository.UserRepo;

@Service
public class AppointmentService {

	@Autowired
	AppointmentRepository appRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	CenterRepository centerRepo;

	@Autowired
	TestRepository testRepo;

	public Appointment saveAppointment(Appointment app) {
		return appRepo.save(app);
	}

	public List<Appointment> getappointments() throws ListEmptyException {
		List<Appointment> allAppointments = appRepo.findAll();
		if (ListEmptyException.checkAppointments(allAppointments)) {
			throw new ListEmptyException("Oh nooooo ! No Appointments Yet.");
		}
		return allAppointments;
	}

	public Appointment addAppointment(@PathVariable Integer userId, @PathVariable Integer centerId,
			@PathVariable Integer testId, @RequestBody Appointment appointment) throws CenterNotFoundException {

		List<User> allUser = userRepo.findAll();
		User bookedUser = userRepo.findById(userId).get();
		if (!CenterNotFoundException.checkIfUserExist(allUser, userId)) {
			throw new CenterNotFoundException("No user found for this userId.");
		}
		List<Center> allCenter = centerRepo.findAll();
		Center bookedCenter = centerRepo.findById(centerId).get();

		List<Test> allTest = testRepo.findAll();
		Test bookedTest = testRepo.findById(testId).get();

		appointment.setTest(bookedTest);
		appointment.setCenter(bookedCenter);
		appointment.setUser(bookedUser);

		userRepo.save(bookedUser);
		centerRepo.save(bookedCenter);
		testRepo.save(bookedTest);
		appRepo.save(appointment);

		return appointment;
	}

	public String deleteApp(int id) {
		Appointment app = appRepo.getReferenceById(id);
		if (appRepo.existsById(id)) {
			appRepo.deleteById(id);
			return "Appointment " + id + " Deleted";
		} else
			return "Appointment does not exists";
	}
	public Appointment getAppById(int id) throws CenterNotFoundException {
		List<Appointment> allApps = appRepo.findAll();
		if (!CenterNotFoundException.checkIfAppExist(allApps, id)) {
			throw new CenterNotFoundException("No details found for the given ID");
		}
		return appRepo.findById(id).get();
	}
}

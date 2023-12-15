package com.example.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.exception.CenterNotFoundException;
import com.example.exception.ListEmptyException;
import com.example.model.Appointment;
import com.example.model.Approvement;
import com.example.repository.AppointmentRepository;
import com.example.repository.ApprovementRepo;

@Service
public class ApprovementService {
	
	@Autowired
	private AppointmentRepository appRepo;
	
	@Autowired
	private ApprovementRepo apprRepo;
	
	public Approvement addApprovement(
			@PathVariable Integer appId, @RequestBody Approvement approvement)  {

		

		List<Appointment> allApps = appRepo.findAll();
		Appointment bookedAppointment = appRepo.findById(appId).get();

		approvement.setAppointment(bookedAppointment);
		
		apprRepo.save(approvement);
		appRepo.save(bookedAppointment);

		return approvement;
	}
	
	public String deleteApp(int id) {
		Appointment app = appRepo.getReferenceById(id);
		if (appRepo.existsById(id)) {
			appRepo.deleteById(id);
			return "Appointment " + id + " Deleted";
		} else
			return "Appointment does not exists";
	}
	
	public List<Approvement> getapps() throws ListEmptyException {
		List<Approvement> allApps = apprRepo.findAll();
		if (ListEmptyException.checkApps(allApps)) {
			throw new ListEmptyException("Oh nooooo ! No Appointments Yet.");
		}
		return allApps;
	}
	public Approvement getAppById(int userId) throws CenterNotFoundException {
		List<Approvement> allApps = apprRepo.findAll();
		if (!CenterNotFoundException.checkIfApprExist(allApps, userId)) {
			throw new CenterNotFoundException("No details found for the given ID");
		}
		return apprRepo.findById(userId).get();
	}
}

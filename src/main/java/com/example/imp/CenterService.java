package com.example.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.CenterNotFoundException;
import com.example.exception.ListEmptyException;
import com.example.model.Center;
import com.example.repository.CenterRepository;
import com.example.repository.TestRepository;

@Service
public class CenterService {

	@Autowired
	private CenterRepository centerRepo;

	@Autowired
	private TestRepository testRepo;

	public String addCenter(Center center) {

		Center anotherCenter = centerRepo.findByCentername(center.getCentername());
		if (anotherCenter != null) {
			return "Center already exists please try again";
		}
		String centername = center.getCentername();
		centerRepo.save(center);
		return "Center added succesfully";
	}

	public String removeCenter(Integer centerId) {
		Center center = centerRepo.getReferenceById(centerId);
		if (centerRepo.existsById(centerId)) {
			centerRepo.deleteById(centerId);
			return "Center " + centerId + " deleted successfully";
		} else
			return "Center does not exists";

	}

//	public List<Center> getAll() {
//
//		return centerRepo.findAll();
//
//	}

	public List<Center> getcenters() throws ListEmptyException {
		List<Center> allCenters = centerRepo.findAll();
		if (ListEmptyException.checkCenters(allCenters)) {
			throw new ListEmptyException("No centers available.");
		}
		return allCenters;
	}

	public Center getCenterById(int id) throws CenterNotFoundException {
		List<Center> allCenters = centerRepo.findAll();
		if (!CenterNotFoundException.checkIfCenterExist(allCenters, id)) {
			throw new CenterNotFoundException("No details found for the given ID");
		}
		return centerRepo.findById(id).get();
	}

	
	public String updateCenter(Integer id, Center center) {
		Center center1 = centerRepo.getReferenceById(id);
		if (centerRepo.existsById(id)) {
			if (center.getCentername().isEmpty() || center.getCity().isEmpty() || center.getAddress().isEmpty()
					) {
				return "Please fill all fields";
			} else if (center.getContact().length() > 10 || center.getContact().length() < 10) {
				return "Invalid Phone number";
			}else {
			center1.setCentername(center.getCentername());
			center1.setCity(center.getCity());
			center1.setContact(center.getContact());
			centerRepo.save(center1);
			return "Center Updated Succesfully" ;
			}

		} else {
			return "CenterID Not Found";
		}
	}
}

package com.example.exception;

import java.util.List;

import com.example.model.Appointment;
import com.example.model.Approvement;
import com.example.model.Center;
import com.example.model.Test;
import com.example.model.User;

public class CenterNotFoundException extends Exception {

	public CenterNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 8888435367687861212L;

	
	public CenterNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public static boolean checkIfCenterExist(List<Center> allCenters, int id) {
		boolean centerExists = allCenters.stream().filter(s -> s.getId().equals(id)).findFirst().isPresent();
		return centerExists;
	}
	
	public static boolean checkIfTestExist(List<Test> allTests, int id) {
		boolean testExists = allTests.stream().filter(s -> s.getId().equals(id)).findFirst().isPresent();
		return testExists;
	}
	
	
	public static boolean checkIfUserExist(List<User> allUsers, int id) {
		boolean userExists = allUsers.stream().filter(s -> s.getId().equals(id)).findFirst().isPresent();
		return userExists;
	}
	
	public static boolean checkIfAppExist(List<Appointment> allApps, int id) {
		boolean appExists = allApps.stream().filter(s -> s.getId().equals(id)).findFirst().isPresent();
		return appExists;
	}
	public static boolean checkIfApprExist(List<Approvement> allApps, int userId) {
		boolean appExists = allApps.stream().filter(s -> s.getId().equals(userId)).findFirst().isPresent();
		return appExists;
	}
	

}

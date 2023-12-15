package com.example.exception;

import java.util.List;

import com.example.model.Appointment;
import com.example.model.Approvement;
import com.example.model.Center;
import com.example.model.Test;
import com.example.model.User;

public class ListEmptyException extends Exception {
	public ListEmptyException() {
		super();
	}

	private static final long serialVersionUID = 8888435367687861212L;

	public ListEmptyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public static boolean checkCenters(List<Center> allCenters) {

		if (allCenters.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean checkUsers(List<User> allUsers) {

		if (allUsers.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean checkTests(List<Test> allTests) {

		if (allTests.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean checkAppointments(List<Appointment> allAppointments) {

		if (allAppointments.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean checkApps(List<Approvement> allApps) {

		if (allApps.isEmpty()) {
			return true;
		}
		return false;
	}
	
	
	

}

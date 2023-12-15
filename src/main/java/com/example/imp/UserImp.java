package com.example.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.CenterNotFoundException;
import com.example.exception.InvalidCredentialsException;
import com.example.exception.ListEmptyException;
import com.example.model.User;
import com.example.repository.UserRepo;

@Service
public class UserImp {
	@Autowired
	private UserRepo repoObj;


	private static final String EMAIL_FORMAT = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}";
	private static final String PASSWORD_FORMAT = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
	private static final String USERNAME_FORMAT = "^(?=[a-zA-Z0-9._]{3,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";


	public List<User> getAll() throws ListEmptyException {
		List<User> allUsers = repoObj.findAll();
		if (ListEmptyException.checkUsers(allUsers)) {
			throw new ListEmptyException("Oops! No Users ");
		}
		return allUsers;
	}


	public String addUser(User user) {

		if (!user.getEmail().matches(EMAIL_FORMAT)) {
			return "Invalid email format.";
		}

		if (!user.getPassword().matches(PASSWORD_FORMAT)) {
			return "Invalid password format.";
		}


		if (!user.getUsername().matches(USERNAME_FORMAT)) {
			return "Invalid username format.";
		}

		User anotherUser = repoObj.findByUsername(user.getUsername());

		if (anotherUser != null) {
			return "Username already exists please try again";
		}

		String username = user.getUsername();
		
//		User u=repoObj.findByEmail(user.getEmail());
//		if(u!=null) {
//			return "Email already registered";
//		}
//		String email = user.getEmail();
//		
//		User us=repoObj.findByPhno(user.getPhno());
//		if(us!=null) {
//			return "Mobile Number already registered";
//		}
//		String phno = user.getPhno();
		repoObj.save(user);

		return "User Added Successfully!!\nYour Email:\t " + user.getEmail()+"\n Password:\t "+user.getPassword();

	}


	public User getUserByUsername(String username) {
		return repoObj.findByUsername(username);
	}

	public User updateUser(User obj) {
		return repoObj.save(obj);
	}


	public String deleteUser(int userId) {
		User user = repoObj.getReferenceById(userId);
		if (repoObj.existsById(userId)) {
			repoObj.deleteById(userId);
			return "User " + userId + " Deleted";
		} else
			return "UserId does not exists";
	}
	
	
	public User getUserById(int id) throws CenterNotFoundException {
		List<User> allUsers = repoObj.findAll();
		if (!CenterNotFoundException.checkIfUserExist(allUsers, id)) {
			throw new CenterNotFoundException("No details found for the given ID");
		}
		return repoObj.findById(id).get();
	}
	
	public String login(String email, String password) throws InvalidCredentialsException {
		if (repoObj.existsByEmail(email)) {
			User user = repoObj.getReferenceByEmail(email);

			if (user.getPassword().equals(password)) {
				return "Successful login";
			}
		}
		return "Please Check Credentials";
	}

	
	
	
}
package com.ecommerce.main.interceptors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class InMemoryUserService implements UserService {
	private static List<User> users = new ArrayList<>();

	public boolean addUser(User user) {
		if (user != null) {
			users.add(user);
			
			//  System.out.println(users); System.out.println(users.indexOf(user));
			 
			return true;
		}
		return false;

	}

	public InMemoryUserService() {
	}

	@Override
	public User loginUser(User user) {
		Optional<User> loggedInUser = users.stream().filter(u -> validateUser(u, user)).findAny();
		
		//System.out.println("Login User : "+users);
		return loggedInUser.isPresent() ? loggedInUser.get() : null;
	}

	private static boolean validateUser(User existingUser, User user) {
		return existingUser.getEmailAddress().equals(user.getEmailAddress())
				&& existingUser.getId().equals(user.getId());
	}

	@Override
	public boolean removeUser(User user) {
		List<User> userList = new ArrayList<>();
		users.forEach(u -> {
			if (u.getId().equalsIgnoreCase(user.getId())) {

			} else {
				userList.add(u);
			}

		});
		if (userList != null) {
			users = userList;
			// users.size();
			// System.out.println(users);
			return true;
		}
		return false;
	}
}

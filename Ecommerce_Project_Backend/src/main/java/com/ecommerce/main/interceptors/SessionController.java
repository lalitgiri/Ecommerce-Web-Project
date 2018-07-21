package com.ecommerce.main.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.main.dao.EmployeeDetails;
import com.ecommerce.main.dao.UserDetails;
import com.ecommerce.main.service.EmployeeDetailsService;
import com.ecommerce.main.service.UserDetailsService;

@RestController
public class SessionController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private EmployeeDetailsService employeeDetailsService;

	@RequestMapping(method = RequestMethod.POST, value = "/getAuthentication")
	public String handleLoginRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Map<String, String> userRequest) {

		User user = new User();
		String emailId = userRequest.get("Username");
		String password = userRequest.get("lpassword");

		UserDetails userDetails = userDetailsService.userAuthentication(emailId, password);

		if (userDetails != null) {

			user.setEmailAddress(userRequest.get("Username"));
			user.setId(userRequest.get("lpassword"));
			user.setUserName(userDetails.getName());
			user.setUserId((int)userDetails.getCart().getUserId());
			user.setRole("User");
			user.setPhoneNumber(userDetails.getPhoneNumber());

			User loggedUser = userService.loginUser(user);
			
			if (loggedUser == null) {
				userService.addUser(user);
				request.getSession(true).setAttribute("user", user);
				TokenProvider tokenProvider = new TokenProvider();

				String token = tokenProvider.generate(user);

				// response.addHeader("token",token);

				//System.out.println(token);
				return token;
			}
			//this is done to remove the problem which arise when the brwoser closes accidently!!!
			else if(loggedUser!=null) {
				request.getSession(true).setAttribute("user", user);
				TokenProvider tokenProvider = new TokenProvider();
				String token = tokenProvider.generate(user);
				//System.out.println(token);
				return token;
			}
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getAdminAuthentication")
	public String handleAdminLoginRequest(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Map<String, String> userRequest) {

		User user = new User();
		String userName = userRequest.get("Username");
		String password = userRequest.get("lpassword");

		EmployeeDetails employeeDetails = employeeDetailsService.userAuthentication(userName, password);
	/*	{
			System.out.println(
					"Employee Name= " + employeeDetails.getEmployeeName() + "  Id: " + employeeDetails.getPassword());

		}*/

		if (employeeDetails != null) {

			user.setEmailAddress(userRequest.get("Username"));
			user.setId(userRequest.get("lpassword"));
			user.setUserName(employeeDetails.getEmployeeName());
			user.setUserId((int)employeeDetails.getEmployeeId());
			user.setRole(employeeDetails.getEmployeeRole());
			user.setPhoneNumber((int)employeeDetails.getContactNumber());

			User loggedUser = userService.loginUser(user);
			if (loggedUser == null) {
				userService.addUser(user);
				request.getSession(true).setAttribute("user", user);
				TokenProvider tokenProvider = new TokenProvider();

				String token = tokenProvider.generate(user);

				// response.addHeader("token",token);

				//System.out.println(token);
				return token;
			}
			//this is done to remove the problem which arise when the brwoser closes accidently!!!
			else if(loggedUser!=null) {
				request.getSession(true).setAttribute("user", user);
				TokenProvider tokenProvider = new TokenProvider();
				String token = tokenProvider.generate(user);
				//System.out.println(token);
				return token;
			}
		}
		return null;
	}

	@RequestMapping("/logout")
	public boolean removeSession(HttpServletRequest request, HttpServletResponse response, Object handler) {

		JwtValidator validate = new JwtValidator();

		User user = validate.validate(request.getHeader("token"));
		User loggedUser = userService.loginUser(user);
		if (loggedUser != null) {
			if (userService.removeUser(user)) {
				request.getSession(true).removeAttribute("user");
			//	System.out.println("Sucessfully Deleted");
				return true;
			}
		}
		return false;
	}
}

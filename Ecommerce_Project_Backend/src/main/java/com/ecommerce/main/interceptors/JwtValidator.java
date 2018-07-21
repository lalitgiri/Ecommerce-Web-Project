package com.ecommerce.main.interceptors;


import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

private final String secret="lalit";

	public User validate(String token) {
		
		//System.out.println("JWT Validator");
		User user=null;
		try {
		Claims body=Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		user=new User();
		/*user.setEmailAddress(body.getSubject());
		user.setId(((String )body.get("UserId")));
		user.setRole((String)body.get("role")) ;*/
		
		user.setEmailAddress(body.getSubject());
		user.setId(((String )body.get("UserId")));
		user.setRole((String)body.get("role")) ;
		
		
		String phn=  String.valueOf(body.get("phoneNumber"));
		user.setPhoneNumber(Long.parseLong(phn));
		
		String id= String.valueOf(body.get("userId"));
		user.setUserId(Integer.parseInt(id));
		user.setUserName((String)body.get("userName"));
		//System.out.println("User Value Properly Set...");
		}
		catch(Exception e) {System.out.println("JWt Validator Catch block "+e);}
	//	System.out.println("return user");
		return user;
	}

}

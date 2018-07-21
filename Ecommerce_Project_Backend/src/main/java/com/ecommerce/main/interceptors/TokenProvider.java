package com.ecommerce.main.interceptors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenProvider {

	public String generate(User user) {

		Claims claims = Jwts.claims().setSubject(user.getEmailAddress());
		claims.put("UserId", String.valueOf(user.getId()));
		claims.put("role", user.getRole());
		claims.put("userName",user.getUserName());
		claims.put("userId", user.getUserId());
		claims.put("phoneNumber", user.getPhoneNumber());
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "lalit").compact();
	}
}

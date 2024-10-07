package com.artist.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artist.dto.CustomersDTO;
import com.artist.entity.Customers;
import com.artist.repository.CustomersRepository;
import com.artist.service.CustomersService;
import com.artist.utils.IdGenerator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class CustomersServiceImpl implements CustomersService {

	@Autowired
	private CustomersRepository cr;

	@Autowired
	IdGenerator idGenerator; // 注入 IdGenerator
	@Autowired
	private PasswordEncoder passwordEncoder;

	public CustomersServiceImpl(CustomersRepository cr, PasswordEncoder passwordEncoder) {
		this.cr = cr;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Value("${jwt.secret}")
	private String jwtSecret;


	@Override
	public void create(CustomersDTO customersDTO) {
		Customers customer = new Customers();
		customer.setCustomerId(idGenerator.customersId());
		customer.setEmail(customersDTO.getEmail());
		customer.setPassword(passwordEncoder.encode(customersDTO.getPassword()));

		customer.setName(customersDTO.getName());
		customer.setNickName(customersDTO.getNickName());
		customer.setPhone(customersDTO.getPhone());
		customer.setAddress(customersDTO.getAddress());
		customer.setCreditCardNo(customersDTO.getCreditCardNo());
		cr.save(customer);
	}

	@Override
	public void update(CustomersDTO customersDTO) {

	}

	@Override
	public void delete(Customers Customers) {

	}

	@Override
	public void deleteByEmail(String email) {

	}

	public Customers getCustomer(String email) {
		Optional<Customers> optionalCustomers = cr.findByEmail(email);
		if (optionalCustomers.isPresent()) {
			Customers customer = optionalCustomers.get();
			return customer;
		}
		return null;
	}

	@Override
	public String login(String email, String password) {
		// 根據電子郵件查找用戶
		Customers customer = cr.findByEmail(email).orElseThrow(() -> new RuntimeException("Invalid email or password"));
		// 檢查密碼是否匹配
		if (passwordEncoder.matches(password, customer.getPassword())) {
			// 生成 JWT
			return generateToken(customer);
		} else {
			throw new RuntimeException("Invalid email or password");
		}
	}

	// 建立 token
	private String generateToken(Customers customer) {
		Map<String, Object> claims = new HashMap<>();
		//將想放近token的資訊一併寫入
		claims.put("nickname", customer.getNickName());
		claims.put("customerId", customer.getCustomerId());

		// 生成 JWT
		return Jwts.builder().setSubject(customer.getEmail()).addClaims(claims) // 添加其他 claims
				.setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 天
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
	// 檢查 token 過期
	public boolean isTokenExpired(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			return claims.getExpiration().before(new Date());
		} catch (ExpiredJwtException e) {
			return true;
		}
	}

	// 刷新 token
	public String refreshToken(String token) {
		if (isTokenExpired(token)) {
			Customers customer = getByCustomerId(getCustomerIdFromToken(token));
			if (customer != null) {
				return generateToken(customer); // 重新生成 token
			}
		}
		return null;
	}
	
	

	public String getCustomerIdFromToken(String token) {
		if (token.startsWith("Bearer ")) {
			token = token.substring(7);
		}

		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return (String) claims.get("customerId");
	}

	public Customers getByCustomerId(String customerId) {
		Optional<Customers> optionalCustomerId = cr.findByCustomerId(customerId);
		if (optionalCustomerId.isPresent()) {
			Customers customers = optionalCustomerId.get();
			return customers;
		} else {
			return null;
		}
	}
	
}

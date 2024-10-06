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

//			 // 直接比對密碼，不使用 passwordEncoder
//		    if (password.equals(customer.getPassword())) {
//		        return generateToken(customer);
//		    } else {
//		        throw new RuntimeException("Invalid email or password");
//		    }
		} else {
			throw new RuntimeException("Invalid email or password");
		}
	}

	@Value("${jwt.secret}")
	private String jwtSecret;

	private String generateToken(Customers customer) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("nickname", customer.getNickName());
		claims.put("customerId", customer.getCustomerId());

		// 生成 JWT
		return Jwts.builder().setSubject(customer.getEmail()).addClaims(claims) // 添加其他 claims
				.setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 天
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
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

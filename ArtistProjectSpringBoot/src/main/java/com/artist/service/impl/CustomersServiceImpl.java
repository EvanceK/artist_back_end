package com.artist.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artist.dto.request.CustomersDTO;
import com.artist.entity.Customers;
import com.artist.repository.CustomersRepository;
import com.artist.service.CustomersService;
import com.artist.utils.IdGenerator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;

@Service
public class CustomersServiceImpl implements CustomersService {

	
	@Autowired
	private CustomersRepository cr;

	@Autowired
	private IdGenerator idGenerator; // 注入 IdGenerator
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
		Customers customer = cr.findByEmail(email).orElseThrow(() -> new RuntimeException("Invalid email"));
		// 檢查密碼是否匹配
		if (passwordEncoder.matches(password, customer.getPassword())) {
			// 生成 JWT
			return generateToken(customer);
		} else {
			throw new RuntimeException("Invalid password");
		}
	}

	public Customers getByCustomerId(String customerId) {
		Optional<Customers> optionalCustomerId = cr.findById(customerId);
	if (optionalCustomerId.isPresent()) {
			Customers customers = optionalCustomerId.get();

			return customers;
		} else {
			return null;
		}
	}
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
	public String getNicknameFromToken(String token) {
		if (token.startsWith("Bearer ")) {
			token = token.substring(7);
		}

		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return (String) claims.get("nickname");
	}
	
	
	
	public String getEmailFromToken(String token) {
		if (token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		   Claims claims = Jwts.parser()
	                .setSigningKey(jwtSecret)
	                .parseClaimsJws(token)
	                .getBody();
	        return claims.getSubject(); // 獲取 subject，也就是 email
	}
	
    public String refreshToken(String token) {
//    	 // 檢查 token 是否已過期
//        if (!jwtUtil.isTokenExpired(token)) {
//            // 如果 token 未過期，提取 email 並生成新的 token
//            String email = jwtUtil.extractEmail(token);
//            Optional<Customers> byEmail = cr.findByEmail(email);
//            if(byEmail.isPresent()) {
//            	Customers customers = byEmail.get();
//                return jwtUtil.generateToken(customers);
//            }
//        }
//        // 如果 token 已過期，返回 null 或拋出異常
        return null; // 或者拋出自定義異常，例如 throw new RuntimeException("Token has expired");
    }

    public boolean validateToken(String token, String username) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
            // 確認 Token 的過期時間
            Date expiration = claims.getExpiration();
            return (expiration != null && !expiration.before(new Date()) && claims.getSubject().equals(username));
        } catch (SignatureException e) {
            // Token 的簽名不正確
            return false;
        } catch (Exception e) {
            // 其他任何錯誤處理
            return false;
        }
    }

}

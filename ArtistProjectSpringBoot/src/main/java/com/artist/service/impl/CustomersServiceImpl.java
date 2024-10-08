package com.artist.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artist.dto.CustomersDTO;
import com.artist.entity.Customers;
import com.artist.repository.CustomersRepository;
import com.artist.service.CustomersService;
import com.artist.utils.IdGenerator;
import com.artist.utils.JwtUtil;

@Service
public class CustomersServiceImpl implements CustomersService {

	@Autowired
	private CustomersRepository cr;

	@Autowired
	private IdGenerator idGenerator; // 注入 IdGenerator
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil; // JwtUtil 來處理 token相關問題

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
			return jwtUtil.generateToken(customer);
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

    public String refreshToken(String token) {
    	 // 檢查 token 是否已過期
        if (!jwtUtil.isTokenExpired(token)) {
            // 如果 token 未過期，提取 email 並生成新的 token
            String email = jwtUtil.extractEmail(token);
            Optional<Customers> byEmail = cr.findByEmail(email);
            if(byEmail.isPresent()) {
            	Customers customers = byEmail.get();
                return jwtUtil.generateToken(customers);
            }
        }
        // 如果 token 已過期，返回 null 或拋出異常
        return null; // 或者拋出自定義異常，例如 throw new RuntimeException("Token has expired");
    }

    public String getCustomerIdFromToken(String token) {
        return jwtUtil.extractClaim(token, claims -> claims.get("customerId", String.class));

    }

}

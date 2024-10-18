package com.artist.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artist.dto.response.CustomersDTO;
import com.artist.entity.Customers;
import com.artist.repository.CustomersRepository;
import com.artist.service.CustomersService;
import com.artist.utils.IdGenerator;
import com.artist.utils.JwtUtil;

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

    @Autowired
    private JwtUtil jwtUtil;
    
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	@Override
	public void create(CustomersDTO customersDTO) {
		Customers customer = new Customers();
		customer.setCustomerId(idGenerator.customersId());
		  if (cr.existsByEmail(customersDTO.getEmail())) {
		        throw new RuntimeException("email already exists"); 
		    }
		customer.setEmail(customersDTO.getEmail());
		customer.setPassword(passwordEncoder.encode(customersDTO.getPassword()));

		customer.setName(customersDTO.getName());
		customer.setNickName(customersDTO.getNickName());
		customer.setPhone(customersDTO.getPhone());
		customer.setAddress(customersDTO.getAddress());
		customer.setCreditCardNo(customersDTO.getCreditCardNo());
		customer.setBankAccount("823 Nextbank");//暫時先這樣
		customer.setBankBalance(0.0);//預設0.0

		cr.save(customer);
	}

//	@SuppressWarnings("unused")
	@Override
	public void update(Customers customer) {
		cr.save(customer);
	}
	public void deitAccountUpdate(String CustomerId,
//					   String pwd,
					   String name,
					   String nickName,
					   String phone,
					   String address) {
		Optional<Customers> optionalCustomers  = cr.findByCustomerId(CustomerId);
		if (optionalCustomers.isPresent()) {
			Customers customer = optionalCustomers.get();
//			customer.setPassword(passwordEncoder.encode(pwd));
			
			customer.setName(name);
			customer.setNickName(nickName);
			customer.setPhone(phone);
			customer.setAddress(address);
			cr.save(customer);
		}else {
			System.out.println("找不到此 id 的客戶");
		}
	}
	public void editAccountUpdate(CustomersDTO customersDTO) {
		Customers customer = getCustomer(customersDTO.getEmail());
		customer.setName(customersDTO.getName());
		customer.setNickName(customersDTO.getNickName());
		customer.setPhone(customersDTO.getPhone());
		customer.setAddress(customersDTO.getAddress());
		cr.save(customer);
	}
	public void editPassword(CustomersDTO customersDTO) {
		Customers customer = getCustomer(customersDTO.getEmail());
		customer.setPassword(passwordEncoder.encode(customersDTO.getPassword()));
		
		cr.save(customer);
	}
	
	public void editPasswordforemail(Customers customer, String password) {
		customer.setPassword(passwordEncoder.encode(password));
		cr.save(customer);
	}

	@Override
	public void delete(Customers customers) {

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
		Customers customer = cr.findByEmail(email).orElseThrow(() -> new RuntimeException("Email doesn't exist"));		
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
	public CustomersDTO getCustomerDTO(String customerId) {
		Optional<Customers> optionalCustomerId = cr.findById(customerId);
	if (optionalCustomerId.isPresent()) {
			Customers customers = optionalCustomerId.get();
			CustomersDTO customerDTO = new CustomersDTO();
			customerDTO.setName(customers.getName());
			customerDTO.setNickName(customers.getNickName());
			customerDTO.setEmail(customers.getEmail());
			customerDTO.setPhone(customers.getPhone());
			customerDTO.setAddress(customers.getAddress());
			customerDTO.setPassword(customers.getPassword());

			return customerDTO;
		} else {
			return null;
		}
	}
	private String generateToken(Customers customer) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("nickname", customer.getNickName());
		claims.put("customerId", customer.getCustomerId());
		// 添加角色信息
//	    List<String> roles = fetchRolesForCustomer(customer.getEmail()); // 從其他地方得到角色信息
//	    claims.put("roles", roles); // 將角色信息添加到 claims
		// 生成 JWT
		return Jwts.builder().setSubject(customer.getEmail()).addClaims(claims) // 添加其他 claims
				.setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 天
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
//    private List<String> fetchRolesForCustomer(String email) {
//        List<String> roles = new ArrayList<>();
//        // 用email模擬 role
//        if (email.equals("artistjava2024@gmail.com")) {
//            roles.add("ADMIN");
//        } else if (cr.existsByEmail(email)){
//            roles.add("CUSTOMER");
//        }else {
//        	roles.add("GUEST");
//        }
//        return roles;
//    }
    

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
	public List<String> getRolesFromToken(String token) {
		if (token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		System.out.println(token);
	        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	        return claims.get("roles", List.class); // 使用 List.class 
	   
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
    	 // 檢查 token 是否已過期
        if (!jwtUtil.isTokenExpired(token)) {
            // 如果 token 未過期，提取 email 並生成新的 token
            String email = jwtUtil.extractEmail(token);
            Optional<Customers> byEmail = cr.findByEmail(email);
            if(byEmail.isPresent()) {
            	Customers customers = byEmail.get();
                return generateToken(customers);// 生成新 token
            }
        }
        // 如果 token 已過期，拋出自定義異常
        throw new RuntimeException("Token has expired");
    }

    public boolean validateToken(String token, String email) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
            // 確認 Token 的過期時間
            Date expiration = claims.getExpiration();
            return (expiration != null && !expiration.before(new Date()) && claims.getSubject().equals(email));
        } catch (SignatureException e) {
            // Token 的簽名不正確
            return false;
        } catch (Exception e) {
            // 其他任何錯誤處理
            return false;
        }
    }

	@Override
	public void editCreditCard(String customerId,String bankAccount, String creditCardNo) {
		Customers customer = getByCustomerId(customerId);
		customer.setBankAccount(bankAccount);
		customer.setCreditCardNo(creditCardNo);
		cr.save(customer);
	}

   
}

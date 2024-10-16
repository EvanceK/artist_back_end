package com.artist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artist.dto.request.LoginRequest;
import com.artist.dto.response.CustomersDTO;
import com.artist.dto.response.LoginResponse;
import com.artist.dto.response.WalletDTO;
import com.artist.dto.response.WalletResponse;
import com.artist.dto.response.WinningRecordResponse;
import com.artist.dto.response.WinningRecords;
import com.artist.entity.Customers;
import com.artist.service.impl.BidrecordServiceImpl;
import com.artist.service.impl.CustomersServiceImpl;
import com.artist.service.impl.OrdersServiceImpl;

@RestController
@RequestMapping("/customers")
public class CustomersController {
	@Autowired
	private CustomersServiceImpl csi;
	@Autowired
	private BidrecordServiceImpl bsi;
	@Autowired
	private OrdersServiceImpl osi;

	// 註冊
	@PostMapping(value = "/register", consumes = "application/json")
	public ResponseEntity<?> createCustomer(@RequestBody CustomersDTO customersDTO) {
		try {
			csi.create(customersDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("註冊成功");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

	// 登入
	@PostMapping(value = "/login", consumes = "application/json")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		String email = request.getEmail();
		String password = request.getPassword();
		try {
			String token = csi.login(email, password);
			String customerId = csi.getCustomerIdFromToken(token);
			Customers customer = csi.getByCustomerId(customerId);
			String nickName = customer.getNickName();
			LoginResponse response = new LoginResponse(token, nickName);
			return ResponseEntity.ok(response);
		} catch (RuntimeException e) {
			// 捕捉密碼或電子郵件錯誤，返回錯誤信息
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}

	// 刷新 token
	@PostMapping("/token/refresh")
	public ResponseEntity<?> refreshToken(@RequestParam String token) {
		String newToken = csi.refreshToken(token);
		if (newToken != null) {
			return ResponseEntity.ok(newToken);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
		}
	}

	//
	@GetMapping("/mywallet")
	public ResponseEntity<?> wallet(@RequestHeader("Authorization") String token) {
		try {
			String customerId = csi.getCustomerIdFromToken(token);
			Customers customer = csi.getByCustomerId(customerId);

			if (customer == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("客戶不存在");
			}

			String bankAccount = customer.getBankAccount();
			Double bankBalance = customer.getBankBalance();
			String creditCardNo = customer.getCreditCardNo();
			List<WalletDTO> depositRecord = bsi.getDepositRecord(customerId, "refunded");

			WalletResponse walletDTO = new WalletResponse(bankAccount, creditCardNo, bankBalance, depositRecord);
			return ResponseEntity.ok(walletDTO);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("無效的請求：" + e.getMessage());
		}
	}

	// 填充客戶資料
	@GetMapping("/initEditData")
	public ResponseEntity<?> initEdit(@RequestHeader("Authorization") String token) {
		String customerId = csi.getCustomerIdFromToken(token);
		CustomersDTO customerDTO = csi.getCustomerDTO(customerId);
		return ResponseEntity.ok(customerDTO);
	}

//    // 編輯客戶資料
//    @PutMapping(value ="/EditAccount")
//    public ResponseEntity<?> updateCustomer(
//    		@RequestHeader("Authorization") String token,
////    		@RequestParam(value = "customerId") String customerId,
////    		@RequestParam(value = "password") String pwd,
//    		@RequestParam(value = "name") String name,
//    		@RequestParam(value = "nickname") String nickname,
//    		@RequestParam(value = "phone") String phone,
//    		@RequestParam(value = "address") String address) {
//    	csi.deitAccountUpdate(customerId,name,nickname,phone,address);
//        return ResponseEntity.status(HttpStatus.OK).body("修改成功");
//    }

	@GetMapping("/mywinningrecords")
	public ResponseEntity<?> myWinningRecords(@RequestHeader("Authorization") String token) {
		try {
			String customerId = csi.getCustomerIdFromToken(token);
			CustomersDTO customer = csi.getCustomerDTO(customerId);

			if (customer == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("客戶不存在");
			}
			List<WinningRecords> allWinningRecords = osi.getAllWinningRecordsByCustomerId(customerId);
			if (allWinningRecords.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("沒有得標資料");

			} else {
				WinningRecordResponse response = new WinningRecordResponse(customer, allWinningRecords);

				return ResponseEntity.ok(response);
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("無效的請求：" + e.getMessage());
		}
	}
    // 編輯客戶資料
    @PutMapping(value ="/EditAccount", consumes = "application/json")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomersDTO customersDTO){
    		
    	csi.deitAccountUpdate(customersDTO);
        return ResponseEntity.status(HttpStatus.OK).body("修改成功");
    }
    // 編輯客戶密碼
    @PutMapping(value ="/EditPassword", consumes = "application/json")
    public ResponseEntity<?> updatePassword(@RequestBody CustomersDTO customersDTO){
    		
    	csi.editPassword(customersDTO);
        return ResponseEntity.status(HttpStatus.OK).body("修改成功");
    }
    
}

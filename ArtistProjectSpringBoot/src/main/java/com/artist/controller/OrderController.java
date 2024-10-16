package com.artist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.artist.entity.Orders;

import com.artist.service.impl.OrdersServiceImpl;


@RestController
@RequestMapping("/OrderController")
public class OrderController {
	
	@Autowired // 透過 @Autowired 的方式，去加入想要使用的 serviceimpl
	private OrdersServiceImpl osi;
	
	@GetMapping(value = "/selectall")
	public ResponseEntity<?> findall() {
		List<Orders> alllist = osi.getAll();
		return ResponseEntity.ok(alllist); // 自動轉換為 JSON
	}
	
	// 新增
//	@PostMapping(value = "/createOrder", consumes = "application/json")
//	public ResponseEntity<?> createOrder(@RequestBody PaintingDTO paintingDTO) {
//		try {
//			osi.create(paintingDTO);
//			return ResponseEntity.status(HttpStatus.CREATED).body("新增成功");
//		} catch (RuntimeException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//		}
//	}
		
		
	// 修改
	@PutMapping(value ="/editOrder", consumes = "application/json")
    public ResponseEntity<?> updateOrder(@RequestBody Orders orders){
		osi.update(orders);
        return ResponseEntity.status(HttpStatus.OK).body("修改成功");
    }
		
		
	// 刪除
	@DeleteMapping("/{orderId}")
	public ResponseEntity<Void> deleteOrderById(@PathVariable String orderId) {
		osi.delete(orderId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
}

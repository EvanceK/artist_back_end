package com.artist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.artist.dto.request.DeliveryOrderRequestDTO;
import com.artist.dto.response.DeliveryOrderResponseDTO;
import com.artist.dto.response.DeliveryOrdersDTO;
import com.artist.dto.response.PaintingDTO;
import com.artist.entity.DeliveryOrders;
import com.artist.repository.DeliveryOrdersRepository;
import com.artist.service.impl.DeliveryOrdersServiceImpl;


@RestController
@RequestMapping("/DeliveryOrderController")
public class DeliveryOrdersController {
	
	@Autowired
    private DeliveryOrdersServiceImpl dosi;
	
	@Autowired
	private DeliveryOrdersRepository dor;
	

	
	@PostMapping(value = "/createDeliveryOrder", consumes = "application/json")
	public String createDeliveryOrder(@RequestBody DeliveryOrderRequestDTO deliveryOrderRequestDTO) {
        // 呼叫 Service 層的 createDeliveryOrder 方法
        return dosi.createDeliveryOrder(deliveryOrderRequestDTO);
       }
//	
//	@GetMapping("/deliveryOrders/{deliveryId}")
//	public DeliveryOrderResponseDTO getDeliveryOrderDetails(@PathVariable String deliveryId) {
//	    Deliveryorders deliveryOrder = deliveryOrderRepository.findById(deliveryId)
//	        .orElseThrow(() -> new RuntimeException("出貨單號 " + deliveryId + " 找不到"));
//
//	    // 將相關訂單數據傳遞給前端
//	    List<OrdersDTO> ordersDTOList = deliveryOrder.getOrderList().stream()
//	        .map(order -> new OrdersDTO(order.getOrderNumber(), order.getTotalAmount(), ...))
//	        .collect(Collectors.toList());
//
//	    DeliveryOrderResponseDTO responseDTO = new DeliveryOrderResponseDTO();
//	    responseDTO.setDeliveryNumber(deliveryOrder.getDeliveryNumber());
//	    responseDTO.setOrderList(ordersDTOList);
//	    
//	    return responseDTO;
//	}
	// 取得全部
	@RequestMapping(value = "/findall", method=RequestMethod.GET)
	public List<DeliveryOrders> findall(Model model){
		return dor.findAll();
	}
	
	// get one by deliveryNumber
	@RequestMapping(value="/{deliveryNumber}", method = RequestMethod.GET)
    public DeliveryOrders findOneById(@PathVariable("deliveryNumber")String deliveryNumber,  Model model){
        return dor.findById(deliveryNumber).get();
    }
	
	// delete by deliveryNumber
	@DeleteMapping("/{deliveryNumber}")
	public ResponseEntity<Void> deleteDeliveryOrdersByNumber(@PathVariable String deliveryNumber) {
		dor.deleteById(deliveryNumber);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	// 修改
	@PutMapping(value ="/editDeliveryOrders", consumes = "application/json")
    public ResponseEntity<?> updateDeliveryOrders(@RequestBody DeliveryOrdersDTO deliveryOrdersfDTO){
		dosi.update(deliveryOrdersfDTO);
        return ResponseEntity.status(HttpStatus.OK).body("修改成功");
    }
	
	// 新增
	@PostMapping(value = "/createDeliveryOrders", consumes = "application/json")
	public ResponseEntity<?> createDeliveryOrders(@RequestBody DeliveryOrdersDTO deliveryOrdersfDTO) {
		try {
			dosi.create(deliveryOrdersfDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body("新增成功");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	
	@GetMapping(value = "/selectall")
	public ResponseEntity<?> selectall() {
	List<DeliveryOrderResponseDTO> allWithOrders = dosi.findAllWithOrders();
		return ResponseEntity.ok(allWithOrders);
	}
	
	
}

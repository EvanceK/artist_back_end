package com.artist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.artist.dto.CustomersDTO;
//import com.artist.dto.LoginResponse;
import com.artist.dto.PaintingDTO;
//import com.artist.entity.Customers;
import com.artist.service.impl.PaintingsServiceImpl;

//@RestController 是 @Controller 和 @ResponseBody 的結合體
@RestController
@RequestMapping("/PTController")
public class PaintingsController {

	@Autowired // 透過 @Autowired 的方式，去加入想要使用的 serviceimpl
	private PaintingsServiceImpl psi;

	@GetMapping(value = "/findall")
	public ResponseEntity<?> findall() {
		List<PaintingDTO> alllist = psi.getAll();
		return ResponseEntity.ok(alllist); // 自動轉換為 JSON
	}
	
	
	@GetMapping(value = "/findallavailable")
	public ResponseEntity<?> getDelicatedPaintings() {
		List<PaintingDTO> allAvailable = psi.getAllAvailablePainting();
		return ResponseEntity.ok(allAvailable); // 
	}

	@GetMapping(value = "/findpaintingname")
	public ResponseEntity<?> findPaintingsName() {

		List<PaintingDTO> paintingsName = psi.getByPaintingsName("Portrait of a Youth Holding an Arrow");
		return ResponseEntity.ok(paintingsName); // 自動轉換為 JSON

	}
	
	@GetMapping(value = "/findpaintingid")
	public ResponseEntity<?> getpaintingsid() {

		PaintingDTO byPaintingsId = psi.getByPaintingsId("PT0002");
	
		return ResponseEntity.ok(byPaintingsId); // 自動轉換為 JSON

	}

	@GetMapping(value = "/findByPage")
	public ResponseEntity<Map<String, Object>> findByPage(
			@RequestParam(value = "currentPage", defaultValue = "0") int currentPage,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		// 1. 查詢總數
		Long totalCount = psi.findPaintingsTotalCount();
		System.out.println(totalCount);//106

		// 2. 計算總頁數
		int totalPage = (int) Math.ceil((double) totalCount / pageSize);
		System.out.println(totalPage);//11

		int page = currentPage-1;	//JPA分頁機制是從0開始。
		// 3. 根據當前頁和每頁大小查詢分頁數據
		Page<PaintingDTO> paintingsByPage = psi.getPaintingsByPage(pageSize, page);
		
		// 4. 將數據封裝到 Map 中返回
		Map<String, Object> result = new HashMap<>();
		result.put("totalCount", totalCount);
		result.put("totalPage", totalPage);
		result.put("currentPage", currentPage);
		result.put("pageSize", pageSize);
		result.put("paintingsList", paintingsByPage.getContent());
		
		return ResponseEntity.ok(result);
	}
	
    // 首頁search
	@GetMapping(value = "/search")
    public ResponseEntity<?> getPaintingsAndArtistPartOfName(@RequestParam() String keyword) {
    	List<PaintingDTO> paintingAndArtistPartOfName = psi.findPaintingAndArtistPartOfName(keyword);
    	return ResponseEntity.ok(paintingAndArtistPartOfName);
    }
   
}

package com.artist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artist.dto.PaintingDTO;
import com.artist.entity.Paintings;
import com.artist.service.impl.PaintingsServiceImpl;

//@RestController 是 @Controller 和 @ResponseBody 的結合體
@RestController
@RequestMapping("/PTController")
public class PaintingsController {

	@Autowired // 透過 @Autowired 的方式，去加入想要使用的 serviceimpl
	private PaintingsServiceImpl psi;

	@GetMapping(value = "/findall", produces = "application/json")
	public ResponseEntity<?> findall() {
		List<PaintingDTO> alllist = psi.findAllforArtisName();
		return ResponseEntity.ok(alllist); // 自動轉換為 JSON
	}

	@GetMapping(value = "/findptname", produces = "application/json")
	public ResponseEntity<?> findPaintingsName() {

		List<Paintings> paintingsName = psi.findByPaintingsName("Portrait of a Youth Holding an Arrow");
		return ResponseEntity.ok(paintingsName); // 自動轉換為 JSON

	}

	@GetMapping(value = "/findByPage", produces = "application/json")
	public ResponseEntity<Map<String, Object>> findByPage(
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		// 1. 查詢總數
		Long totalCount = psi.findPaintingsTotalCount();
		System.out.println(totalCount);

		// 2. 計算總頁數
		int totalPage = (int) Math.ceil((double) totalCount / pageSize);
		System.out.println(totalPage);

		// 3. 根據當前頁和每頁大小查詢分頁數據
		List<Paintings> paintingsList = psi.findByPage(pageSize, currentPage);

		// 4. 將數據封裝到 Map 中返回
		Map<String, Object> result = new HashMap<>();
		result.put("totalCount", totalCount);
		result.put("totalPage", totalPage);
		result.put("currentPage", currentPage);
		result.put("pageSize", pageSize);
		result.put("paintingsList", paintingsList);

		return ResponseEntity.ok(result);
	}

}

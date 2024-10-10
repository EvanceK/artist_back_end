package com.artist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artist.dto.response.PaintingDTO;
import com.artist.entity.Artist;
import com.artist.service.impl.ArtistServiceImpl;
import com.artist.service.impl.PaintingsServiceImpl;

@RestController
@RequestMapping("/ArtController")
public class ArtistController {
	
	@Autowired
	ArtistServiceImpl asi;
	
	@Autowired
	PaintingsServiceImpl psi;
	
	@GetMapping(value = "/findall")
	public ResponseEntity<?> findall(){
		List<Artist> alllist = asi.getAll();
		System.out.println(alllist);
		return ResponseEntity.ok(alllist);
		
	}
	@GetMapping(value = "/artists")
	public ResponseEntity<Map<String, Object>> findByPage(
			@RequestParam(value = "artistId") String artistId,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		Long totalCount = psi.countByDelicatedAndArtistId(1,artistId);
		System.out.println(totalCount);

		// 2. 計算總頁數
		int totalPage = (int) Math.ceil((double) totalCount / pageSize);
		System.out.println(totalPage);
		
		int page = currentPage-1;	//JPA分頁機制是從0開始。
		// 3. 根據當前頁和每頁大小查詢分頁數據
		Page<PaintingDTO> paintingsforArtistId = psi.getAllforArtistIdByPage(pageSize, page, artistId);
		
		// 4. 將數據封裝到 Map 中返回
		Map<String, Object> result = new HashMap<>();
		result.put("totalCount", totalCount);
		result.put("totalPage", totalPage);
		result.put("currentPage", currentPage);
		result.put("pageSize", pageSize);
		result.put("paintingsList", paintingsforArtistId.getContent());
		
		return ResponseEntity.ok(result);
	}
}

package com.artist.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artist.dto.response.PaintingDTO;
import com.artist.dto.response.TopBiddingsDTO;
import com.artist.dto.response.TopFavoritesDTO;
import com.artist.entity.Paintings;
import com.artist.service.impl.BidrecordServiceImpl;
import com.artist.service.impl.CustomersServiceImpl;
import com.artist.service.impl.PaintingsServiceImpl;
import com.artist.service.impl.WishlistServiceImpl;

//@RestController 是 @Controller 和 @ResponseBody 的結合體
@RestController
@RequestMapping("/PTController")
public class PaintingsController {

	@Autowired // 透過 @Autowired 的方式，去加入想要使用的 serviceimpl
	private PaintingsServiceImpl psi;
	@Autowired
	private WishlistServiceImpl wsi;
	
	@Autowired
	private BidrecordServiceImpl bsi;
	@Autowired
	private CustomersServiceImpl csi;

	// 新增
		@PostMapping(value = "/createPainting", consumes = "application/json")
		public ResponseEntity<?> createPainting(@RequestBody PaintingDTO paintingDTO) {
			try {
				psi.create(paintingDTO);
				return ResponseEntity.status(HttpStatus.CREATED).body("新增成功");
			} catch (RuntimeException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			}
		}
		
		
		// 修改
		@PutMapping(value ="/editPainting", consumes = "application/json")
	    public ResponseEntity<?> updatePaintings(@RequestBody PaintingDTO paintingDTO){
			psi.update(paintingDTO);
	        return ResponseEntity.status(HttpStatus.OK).body("修改成功");
	    }
		
		
		// 刪除
		@DeleteMapping("/{paintingId}")
		public ResponseEntity<Void> deletePaintingById(@PathVariable String paintingId) {
			psi.delete(paintingId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		
	@GetMapping(value = "/selectall")
	public ResponseEntity<?> findall() {
		List<PaintingDTO> alllist = psi.getAll();
		return ResponseEntity.ok(alllist); // 自動轉換為 JSON
	}
	
	
	@GetMapping(value = "/findallavailable")
	public ResponseEntity<?> getDelicatedPaintings() {
		List<PaintingDTO> allAvailable = psi.getAllAvailablePainting();
		return ResponseEntity.ok(allAvailable); // 
	}

	
	//GetMapping 
	//方式一 路徑會長這樣>>>>   /findpaintingname?findpaintingname=xxxxx
	
	@GetMapping(value = "/findpaintingname")
	public ResponseEntity<?> findPaintingsName(@RequestParam("paintingname") String paintingname) {

		List<PaintingDTO> paintingsName = psi.getByPaintingsName(paintingname);
		return ResponseEntity.ok(paintingsName);
	}
	
	//方式二 路徑會長這樣>>>>   /findpaintingid/PT0002
	@GetMapping(value = "/findpaintingid/{paintingId}")
	public ResponseEntity<?> getpaintingId(@PathVariable("paintingId") String paintingId) {

		Paintings byPaintingsId = psi.getOnePaintingsById(paintingId);
		//System.out.println(byPaintingsId.toString());
		return ResponseEntity.ok(byPaintingsId);
	}

	//多組參數
	@GetMapping(value = "/findByPage")
	public ResponseEntity<Map<String, Object>> findByPage(
			@RequestParam(value = "currentPage", defaultValue = "0") int currentPage,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		// 1. 查詢總數
		Long totalCount = psi.findPaintingsTotalCount();
//		System.out.println(totalCount);//106

		// 2. 計算總頁數
		int totalPage = (int) Math.ceil((double) totalCount / pageSize);
//		System.out.println(totalPage);//11

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
	
	@GetMapping(value = "/findAllInBidding")
	public ResponseEntity<Map<String, Object>> findAllInBidding(
			@RequestParam(value = "currentPage", defaultValue = "0") int currentPage,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		// 1. 查詢總數
		Long totalCount = psi.findInBiddingTotalCount();
//		System.out.println(totalCount);//106

		// 2. 計算總頁數
		int totalPage = (int) Math.ceil((double) totalCount / pageSize);
//		System.out.println(totalPage);//11

		int page = currentPage-1;	//JPA分頁機制是從0開始。
		// 3. 根據當前頁和每頁大小查詢分頁數據
		Page<PaintingDTO> paintingsByPage = psi.getAllInBidding(pageSize, page);
		
		// 4. 將數據封裝到 Map 中返回
		Map<String, Object> result = new HashMap<>();
		result.put("totalCount", totalCount);
		result.put("totalPage", totalPage);
		result.put("currentPage", currentPage);
		result.put("pageSize", pageSize);
		result.put("paintingsList", paintingsByPage.getContent());
		
		return ResponseEntity.ok(result);
	}
	
	
	@GetMapping(value = "/findAllPresaleExhibition")
	public ResponseEntity<Map<String, Object>> findAllPresaleExhibition(
			@RequestParam(value = "currentPage", defaultValue = "0") int currentPage,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		// 1. 查詢總數
		Long totalCount = psi.findPresaleExhibitionTotalCount();
//		System.out.println(totalCount);//106

		// 2. 計算總頁數
		int totalPage = (int) Math.ceil((double) totalCount / pageSize);
//		System.out.println(totalPage);//11

		int page = currentPage-1;	//JPA分頁機制是從0開始。
		// 3. 根據當前頁和每頁大小查詢分頁數據
		Page<PaintingDTO> paintingsByPage = psi.getAllInPresaleExhibition(pageSize, page);
		
		// 4. 將數據封裝到 Map 中返回
		Map<String, Object> result = new HashMap<>();
		result.put("totalCount", totalCount);
		result.put("totalPage", totalPage);
		result.put("currentPage", currentPage);
		result.put("pageSize", pageSize);
		result.put("paintingsList", paintingsByPage.getContent());
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/artists")
	public ResponseEntity<Map<String, Object>> findByPage(
			@RequestParam(value = "artistId") String artistId,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		Long totalCount = psi.countByDelicatedAndArtistId(artistId);
//		System.out.println(totalCount);

		// 2. 計算總頁數
		int totalPage = (int) Math.ceil((double) totalCount / pageSize);
//		System.out.println(totalPage);
		
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
	
    // 首頁search
	@GetMapping(value = "/search")
    public ResponseEntity<?> getPaintingsAndArtistPartOfName(@RequestParam() String keyword) {
    	List<PaintingDTO> paintingAndArtistPartOfName = psi.findPaintingAndArtistPartOfName(keyword);
    	return ResponseEntity.ok(paintingAndArtistPartOfName);
    }
	
	@GetMapping(value = "/topfavorites")
    public ResponseEntity<Map<String, Object>>getTopFavorites(
    		@RequestParam(value = "token", defaultValue ="")String token,
    		@RequestParam(value = "pageSize", defaultValue = "3") int pageSize){
		String customerId = null;
		if(token.equals("")){customerId = null;}else { customerId = csi.getCustomerIdFromToken(token);} 
		List<TopFavoritesDTO> topFavorites;
		if(customerId!=null) {
		topFavorites = wsi.getTopFavorites(customerId,pageSize);//排除掉自己的前三
		}else {
		topFavorites = wsi.getTopFavorites(pageSize);//所有人都算的前三
		}
		
        List<PaintingDTO> paintingList = new ArrayList<>();
        for (TopFavoritesDTO p : topFavorites) {
            String pId = p.getPaintingId();
         PaintingDTO paintingsId = psi.getByPaintingsId(pId);   // 單個查詢，因為數量不多，所以計畫分次查。
         paintingList.add(paintingsId);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("paintingsCount", topFavorites);
        response.put("paintingsList", paintingList);
        
    	return ResponseEntity.ok(response);
    }
	
	@GetMapping(value = "/topbiddings")
    public ResponseEntity<Map<String, Object>>getTopbiggings(@RequestParam(value = "pageSize", defaultValue = "3") int pageSize){
		List<TopBiddingsDTO> topBiddings= bsi.getTopBidding(pageSize);//前三
        List<PaintingDTO> paintingList = new ArrayList<>();
        for (TopBiddingsDTO p : topBiddings) {
            String pId = p.getPaintingId();
         PaintingDTO paintingsId = psi.getByPaintingsId(pId);  // 單個查詢，因為數量不多，所以計畫分次查。
         paintingList.add(paintingsId);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("paintingsCount", topBiddings);
        response.put("paintingsList", paintingList);
        
    	return ResponseEntity.ok(response);
    }
	
	@GetMapping("/closing-soon")
    public ResponseEntity<List<PaintingDTO>> getUpcomingAuction() {
        List<PaintingDTO> paintings = psi.getUpcomingAuction();
        return ResponseEntity.ok(paintings);
    }
	@GetMapping("/recentuploads")
    public ResponseEntity<List<PaintingDTO>> getRecentUploads() {
        List<PaintingDTO> paintings = psi.getRecentlyUploaded();
        return ResponseEntity.ok(paintings);
    }
   	
	 @GetMapping(value = "/image/{paintingId}", produces = MediaType.IMAGE_JPEG_VALUE)
	    public ResponseEntity<byte[]> getImage(@PathVariable String paintingId) {
	        byte[] imageData = psi.getPaintingBlob(paintingId);  // 從資料庫獲取圖片的 byte[] 資料
	        if (imageData == null || imageData.length == 0) {
	            return ResponseEntity.notFound().build();  // 如果沒有找到圖片，返回 404
	        }
	        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData); // 返回圖片數據給前端
	    }
   
}

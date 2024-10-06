package com.artist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artist.dto.WishlistDTO;
import com.artist.service.impl.CustomersServiceImpl;
import com.artist.service.impl.WishlistServiceImpl;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistContorller {

	@Autowired
	private WishlistServiceImpl wsi;
	@Autowired
	private CustomersServiceImpl csi;


    @PostMapping
    public ResponseEntity<Void> addToWishlist(@RequestHeader("Authorization") String token, 
                                               @RequestBody String paintingId) {
        wsi.addToWishlist(token, paintingId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

	// 查詢用戶所有願望清單的方法
	@GetMapping
    public ResponseEntity<List<WishlistDTO>> getAllWishlist(@RequestHeader("Authorization") String token) {
        String customerId = csi.getCustomerIdFromToken(token);
		List<WishlistDTO> allWishlistWithPaintings = wsi.findAllWishlistWithPaintings(customerId);
		return ResponseEntity.ok(allWishlistWithPaintings);
	}
 }

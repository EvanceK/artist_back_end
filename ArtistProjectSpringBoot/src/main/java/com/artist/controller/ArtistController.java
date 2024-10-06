package com.artist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artist.entity.Artist;
import com.artist.service.impl.ArtistServiceImpl;

@RestController
@RequestMapping("/ArtController")
public class ArtistController {
	
	@Autowired
	ArtistServiceImpl asi;
	
	@GetMapping(value = "/findall")
	public ResponseEntity<?> findall(){
		List<Artist> alllist = asi.getAll();
		System.out.println(alllist);
		return ResponseEntity.ok(alllist);
		
	}
}

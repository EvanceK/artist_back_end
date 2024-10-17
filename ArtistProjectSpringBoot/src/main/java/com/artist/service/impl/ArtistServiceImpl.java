package com.artist.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artist.entity.Artist;
import com.artist.repository.ArtistRepository;
import com.artist.service.ArtistService;
import com.artist.utils.IdGenerator;

@Service
public class ArtistServiceImpl implements ArtistService {
	@Autowired
	private ArtistRepository ar;

	@Autowired
	private IdGenerator idGenerator;

	@Override
	public void create(Artist artist) {
		Artist art = new Artist();
		art.setArtistId(idGenerator.artistId());
		art.setArtistName(artist.getArtistName());
		art.setDesciption(artist.getDesciption());
		art.setUrl(artist.getUrl());
		art.setPaintings(artist.getPaintings());
		ar.save(art);
	}

	@Override
	public String getArtistInfo() {

		return null;
	}
	@Override
	public List<Artist> getAll() {
		return ar.findAll();
	}

	@Override
	public void update(Artist artist) {
		Optional<Artist> optionalArtistId = ar.findById(artist.getArtistId());
		if (optionalArtistId.isPresent()) {
			Artist art = optionalArtistId.get();
			art.setArtistName(artist.getArtistName());
			art.setDesciption(artist.getDesciption());
			art.setUrl(artist.getUrl());
			art.setPaintings(artist.getPaintings());
			ar.save(art);
		}else {
			System.out.println("Artist is not find");
		}
		
//		Artist artist = new Artist();
//		artist.setArtistName(artistDTO.getArtistName());
//		artist.setDesciption(artistDTO.getDesciption());
//		artist.setUrl(artistDTO.getUrl());
//		if(ar.existsByArtistId(artistDTO.getArtistName())) {	//如果存在的話，修改資訊
//			ar.save(artist);
//		}else {
//			artist.setArtistId(idGenerator.artistId());//如果沒有同artistId的幫它產生artistId並新增
//			ar.save(artist);
//		}
	}

	@Override
	public void deleteArtist(Artist artist) {
		ar.delete(artist);
	}

	@Override
	public void deleteByArtistId(String artistId) {
		ar.deleteById(artistId);
	}

	@Override
	public Artist getOneById(String artistId) {
		Optional<Artist> optionalArtistId = ar.findById(artistId);//.getById(artistId);
		if (optionalArtistId.isPresent()) {
			Artist artist = optionalArtistId.get();
			return artist;
		}else {
			return null;
		}
	}

}

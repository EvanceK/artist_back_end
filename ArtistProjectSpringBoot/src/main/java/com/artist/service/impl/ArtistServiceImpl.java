package com.artist.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artist.dto.response.ArtistDTO;
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
	public void create(ArtistDTO artistDTO) {
		Artist artist = new Artist();
		artist.setArtistId(idGenerator.artistId());
		artist.setArtistName(artistDTO.getArtistName());
		artist.setDesciption(artistDTO.getDesciption());
		artist.setUrl(artistDTO.getUrl());
		ar.save(artist);
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
	public void update(ArtistDTO artistDTO) {
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

}

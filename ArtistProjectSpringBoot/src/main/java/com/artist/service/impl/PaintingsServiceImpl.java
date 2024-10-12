package com.artist.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.artist.dto.response.PaintingDTO;
import com.artist.entity.Paintings;
import com.artist.repository.PaintingsRepository;
import com.artist.service.PaintingsService;

@Service
public class PaintingsServiceImpl implements PaintingsService {

	// 用spring data jpa 實現

	@Autowired // 這裡是用 com.paintingsRepository; //不是自己寫的 PaintingsDao
	private PaintingsRepository ptr;

	// 新增
	@Override
	public void create(PaintingDTO paintingDTO) {
		Paintings painting = new Paintings();
		painting.setPaintingId(paintingDTO.getPaintingId());
		painting.setPaintingName(paintingDTO.getPaintingName());
		painting.setArtistId(paintingDTO.getArtistId());
		painting.setLargUrl(paintingDTO.getLargUrl());
		painting.setSmallUrl(paintingDTO.getSmallUrl());
		painting.setPrice(paintingDTO.getPrice());
		painting.setDate(paintingDTO.getDate());
		painting.setStyle(paintingDTO.getStyle());
		painting.setUploadDate(LocalDateTime.now());
		painting.setGenre(paintingDTO.getGenre());
		painting.setDelicated(paintingDTO.getDelicated());
		painting.setStatus(paintingDTO.getStatus());
		ptr.save(painting);
	}

	// 查詢所有的畫作
	@Override
	public List<PaintingDTO> getAll() {
		List<Paintings> paintings = ptr.findAll();
		return paintings.stream()
				.map(p -> new PaintingDTO(p.getPaintingId(), p.getPaintingName(), p.getArtist().getArtistId(),
						p.getArtist().getArtistName(), p.getLargUrl(), p.getSmallUrl(), p.getPrice(), p.getDate(),
						p.getStyle(), p.getUploadDate(), p.getGenre(), p.getDelicated(), p.getStatus()))
				.collect(Collectors.toList());
	}

	// 查詢所有未下架的畫作
	@Override
	public List<PaintingDTO> getAllAvailablePainting() {
		List<Paintings> paintings = ptr.findAllDelicatedPaintingsWithArtist();
		return paintings.stream()
				.map(p -> new PaintingDTO(p.getPaintingId(), p.getPaintingName(), p.getArtist().getArtistId(),
						p.getArtist().getArtistName(), p.getLargUrl(), p.getSmallUrl(), p.getPrice(), p.getDate(),
						p.getStyle(), p.getUploadDate(), p.getGenre(), p.getDelicated(), p.getStatus()))
				.collect(Collectors.toList());
	}

	public Page<PaintingDTO> getPaintingsByPage(Integer pageSize, Integer currentPage) {
		Pageable pageable = PageRequest.of(currentPage, pageSize); 
		Page<Paintings> paintingsPage = ptr.findAllDelicatedPaintingsWithArtist(pageable);
		// 映射到 Page<PaintingDTO>
		List<PaintingDTO> paintingDTOs = paintingsPage.getContent().stream()
				.map(p -> new PaintingDTO(p.getPaintingId(), p.getPaintingName(), p.getArtist().getArtistId(),
						p.getArtist().getArtistName(), p.getLargUrl(), p.getSmallUrl(), p.getPrice(), p.getDate(),
						p.getStyle(), p.getUploadDate(), p.getGenre(), p.getDelicated(), p.getStatus()))
				.collect(Collectors.toList());
		return new PageImpl<>(paintingDTOs, pageable, paintingsPage.getTotalElements()); 
	}
	
	@Override
	public Page<PaintingDTO> getAllforArtistIdByPage(Integer pageSize, Integer currentPage, String artistId) {
		Pageable pageable = PageRequest.of(currentPage, pageSize); 
		Page<Paintings> pagePaintingsWithArtist = ptr.findAllDelicatedWithArtist(pageable, artistId);
		List<PaintingDTO> paintingDTOs = pagePaintingsWithArtist.getContent().stream()
				.map(p -> new PaintingDTO(p.getPaintingId(), p.getPaintingName(), p.getArtist().getArtistId(),
						p.getArtist().getArtistName(), p.getLargUrl(), p.getSmallUrl(), p.getPrice(), p.getDate(),
						p.getStyle(), p.getUploadDate(), p.getGenre(), p.getDelicated(), p.getStatus()))
				.collect(Collectors.toList());
		return new PageImpl<>(paintingDTOs, pageable, pagePaintingsWithArtist.getTotalElements());
	}

	@Override
	public List<Paintings> getByStlye(String stlye) {
		return null;
	}

	@Override
	public List<Paintings> getByPeriod(String period) {
		return null;
	}

	@Override
	public List<Paintings> getByGenre(String genre) {
		return null;
	}

	@Override
	public List<Paintings> sortByUploadDate(LocalDateTime date) {
		return null;
	}

	@Override
	public List<Paintings> sortByPrice(Double price) {
		return null;
	}

	@Override
	public List<Paintings> sortBypopular(Integer popular) {
		return null;
	}

	// 商品上架的邏輯
	public void uploadItems() {
		// create new paintings

		// re-upload

	}

	@Override
	public Long findPaintingsTotalCount() {
		long countByDelicated = ptr.countByDelicated(1);
		return countByDelicated;
	}
	public long countByDelicatedAndArtistId(Integer delicatedValue, String artistId) {
		long countByDelicatedAndArtistId = ptr.countByDelicatedAndArtistId(delicatedValue, artistId);
		return countByDelicatedAndArtistId;
	}

	@Override
	public void updatePrice(String paintingId, Double price) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUploadDate(String paintingId, LocalDateTime uploadDate) {
		Optional<Paintings> optionalPainting = ptr.findByPaintingId(paintingId);
		if (optionalPainting.isPresent()) {
			Paintings painting = optionalPainting.get();
			painting.setUploadDate(LocalDateTime.now()); // 設定日期
			ptr.save(painting); // 更新畫作
			System.out.println("更新成功");
		} else {
			System.out.println("找不到此 id 的畫");
		}
	}

	@Override
	public void setPaintingAvailable(String paintingId) {
		Optional<Paintings> optionalPainting = ptr.findByPaintingId(paintingId);
		if (optionalPainting.isPresent()) {
			Paintings painting = optionalPainting.get();
			System.out.println(painting.getPaintingId());
			painting.setDelicated(1); // 設定有貨
			ptr.save(painting); // 更新畫作
			System.out.println("更新成功");

		} else {
			System.out.println("找不到此 id 的畫");
		}

	}

	@Override
	public void setPaintingSold(String paintingId) {
		Optional<Paintings> optionalPainting = ptr.findById(paintingId);
		if (optionalPainting.isPresent()) { // 檢查有沒有找到
			Paintings painting = optionalPainting.get(); // 得到 Painting 對象
			painting.setDelicated(0); // 改成賣出或過期 //之後狀態也要一起更新
			ptr.save(painting); // 更新畫作
			System.out.println("更新成功");
		} else {
			System.out.println("找不到此 id 的畫");
		}
	}
//	@Override
//	public void delete(String paintingId) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public List<PaintingDTO> getAllDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaintingDTO> getAllforArtisName() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public PaintingDTO getByPaintingsId(String paintingId) {
		Optional<Paintings> painting = ptr.findById(paintingId);
//		System.out.println("Querying painting with ID: " + paintingId);// 這邊正常
		if (painting.isPresent()) {
			Paintings paintings = painting.get();
//			System.out.println("Found painting: " + paintings);

			return new PaintingDTO(paintings.getPaintingId(), paintings.getPaintingName(),
					paintings.getArtist().getArtistId(), paintings.getArtist().getArtistName(), paintings.getLargUrl(),
					paintings.getSmallUrl(), paintings.getPrice(), paintings.getDate(), paintings.getStyle(),
					paintings.getUploadDate(), paintings.getGenre(), paintings.getDelicated(), paintings.getStatus());
		} else {
			System.out.println("No painting found for ID: " + paintingId);

			return null;
		}
	}

	@Override
	public List<PaintingDTO> getByPaintingsName(String paintingName) {
		List<Paintings> listPaintingId = ptr.findByPaintingName(paintingName);
		if (listPaintingId.isEmpty()) {
			return Collections.emptyList();
		}
		return listPaintingId.stream()
				.map(painting -> new PaintingDTO(painting.getPaintingId(), painting.getPaintingName(),
						painting.getArtist().getArtistId(), painting.getArtist().getArtistName(), painting.getLargUrl(),
						painting.getSmallUrl(), painting.getPrice(), painting.getDate(), painting.getStyle(),
						painting.getUploadDate(), painting.getGenre(), painting.getDelicated(), painting.getStatus()))
				.collect(Collectors.toList());
	}



	@Override
	public List<Paintings> getByArtisId(String artisId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paintings> UploadMedia(String media) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paintings> getByDimensions(String dimensions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paintings> sortByArtisName(String artisName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paintings> sortByPaintingName(String paintingName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String paintingId) {
		ptr.deleteById(paintingId);
	}

	@Override
	public boolean existsBypaintingId(String paintingId) {
		return ptr.existsBypaintingId(paintingId);
	}

	@Override
	public List<PaintingDTO> findPaintingAndArtistPartOfName(String keyword) {
		List<Paintings> paintingAndArtistPartOfName = ptr.findPaintingAndArtistPartOfName(keyword);
		
		if (paintingAndArtistPartOfName.isEmpty()) {
			return Collections.emptyList();
		}
		return paintingAndArtistPartOfName.stream()
				.map(painting -> new PaintingDTO(painting.getPaintingId(), painting.getPaintingName(),
						painting.getArtist().getArtistId(), painting.getArtist().getArtistName(), painting.getLargUrl(),
						painting.getSmallUrl(), painting.getPrice(), painting.getDate(), painting.getStyle(),
						painting.getUploadDate(), painting.getGenre(), painting.getDelicated(), painting.getStatus()))
				.collect(Collectors.toList());
	}
}

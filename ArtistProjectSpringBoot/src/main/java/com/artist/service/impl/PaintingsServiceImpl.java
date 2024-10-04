package com.artist.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artist.dto.PaintingDTO;
import com.artist.entity.Paintings;
import com.artist.repository.PaintingsRepository;
import com.artist.service.PaintingsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Service
public class PaintingsServiceImpl implements PaintingsService {

	// 用spring data jpa 實現
	
	@Autowired // 這裡是用 com.paintingsRepository; //不是自己寫的 PaintingsDao
	private PaintingsRepository ptr;

	
	//新增
	@Override
	public void create(PaintingDTO paintingDTO) {
		Paintings painting= new Paintings();
		painting.setPaintingId(paintingDTO.getPaintingId());
		painting.setPaintingName(paintingDTO.getPaintingName());
		painting.setArtisId(paintingDTO.getArtisId());
		painting.setLargUrl(paintingDTO.getLargUrl());
		painting.setSmallUrl(paintingDTO.getSmallUrl());
		painting.setPrice(paintingDTO.getPrice());
		painting.setDate(paintingDTO.getDate());
		painting.setStyle(paintingDTO.getStyle());
		painting.setUploadDate(LocalDateTime.now());
		painting.setGenre(paintingDTO.getGenre());
		painting.setMedia(paintingDTO.getMedia());
		painting.setDelicated(paintingDTO.getDelicated());
		painting.setStatus(paintingDTO.getStatus());
		ptr.save(painting);
	}
	
	//查詢所有的畫作
	@Override
	public List<PaintingDTO> getAll() {
	    List<Paintings> paintings = ptr.findAll();
	    return paintings.stream().map(p -> new PaintingDTO(
	            p.getPaintingId(),
	            p.getPaintingName(),
	            p.getArtist().getArtistId(),
	            p.getArtist().getArtistName(),
	            p.getLargUrl(),
	            p.getSmallUrl(),
	            p.getPrice(),
	            p.getDate(),
	            p.getStyle(),
	            p.getUploadDate(),
	            p.getGenre(),
	            p.getMedia(),
	            p.getDelicated(),
	            p.getStatus()
	    )).collect(Collectors.toList());
	}
	
	//查詢所有未下架的畫作
	@Override
	public List<Paintings> getAllAvailablePainting() {
	    List<Paintings> paintings = ptr.findAllDelicatedPaintingsWithArtist();
	    return paintings;
	}
	
    public Page<Paintings> getPaintingsByPage(Integer pageSize, Integer currentPage){
    	 Pageable pageable = PageRequest.of(currentPage, pageSize); // 创建分页请求
         return ptr.findAllDelicatedPaintingsWithArtist(pageable); // 获取分页结果    	
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



	@Override
	public void updatePrice(String paintingId, Double price) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUploadDate(String paintingId, LocalDateTime uploadDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPaintingAvailable(String paintingId) {
		Optional<Paintings> optionalPainting  = ptr.findById(paintingId);
	    if (optionalPainting.isPresent()) { 
	        Paintings painting = optionalPainting.get(); 
	        painting.setDelicated(1); //設定有貨
	        ptr.save(painting); // 更新畫作
	    } else {
	        System.out.println("找不到此 id 的畫");
	    }
		
	}

	@Override
	public void setPaintingSold(String paintingId) {
		Optional<Paintings> optionalPainting  = ptr.findById(paintingId);
	    if (optionalPainting.isPresent()) { // 檢查有沒有找到
	        Paintings painting = optionalPainting.get(); // 得到 Painting 對象
	        painting.setDelicated(0); // 改成賣出或過期 //之後狀態也要一起更新
	        ptr.save(painting); // 更新畫作
	    } else {
	        System.out.println("找不到此 id 的畫");
	    }
	}
	@Override
	public void delete(String paintingId) {
		// TODO Auto-generated method stub
		
	}



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
	public List<PaintingDTO> getAllforArtisNameLike() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paintings> getByPaintingsId(String paintingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paintings> getByPaintingsName(String paintingName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paintings> getByPaintingsNameLike(String paintingName) {
		// TODO Auto-generated method stub
		return null;
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


//	//商品下架的邏輯有request調一次 太耗資源
//	@Override
//	public void removeItems() {
//		//loading出目前已上架的商品
//		List<PaintingDTO> allPainting = pdi.selectAllforArtisName();
//		//計算出下架的時間
//		for(PaintingDTO pd:allPainting) {
//			LocalDateTime removeDate = pd.getUploadDate().plusDays(1L); //1天以上for測試
//			pd.setRemoveDate(removeDate);
//		//時間到時自動下架
//		 if (LocalDateTime.now().isAfter(removeDate)) {
//			 	this.setPaintingSold(pd.getPaintingId());
//	        }
//		}
//	}
	




// =========================================================================================================
	
//	@Autowired // 用spring管理
//	PaintingsDaoImpl pdi;
	// 用傳統的寫法
//	@Override
//	public List<Paintings> findAll() {
//		return pdi.selectAll();
//	}
//
//	@Override
//	public List<PaintingDTO> findAllforArtisName() {
//		return pdi.selectAllforArtisName();
//	}
//
//	@Override
//	public List<Paintings> findByPaintingsName(String paintingName) {
//		List<Paintings> paintings = pdi.selectAllOrderByPaintingsName(paintingName);
//		return paintings;
//	}
//
//	@Override
//	public void updatePrice(String paintingId, Double price) {
//		List<Paintings> paintings = pdi.selectPaintingsByPaintingsId(paintingId);
//		Paintings painting = paintings.get(0);
//		if (painting != null) {
//			painting.setPrice(price);
//			pdi.update(painting);
//		} else {
//			System.out.println("找不到此id的畫作");
//		}
//	}
//
//	@Override
//	public void updateUploadDate(String paintingId, LocalDateTime uploadDate) {
//		List<Paintings> paintings = pdi.selectPaintingsByPaintingsId(paintingId);
//		Paintings painting = paintings.get(0);
//
//		if (painting != null) {
//			painting.setUploadDate(uploadDate);
//			pdi.update(painting);
//		} else {
//			System.out.println("找不到此id的畫作");
//		}
//	}
//
//	@Override
//	public void setPaintingAvailable(String paintingId) {
//		List<Paintings> paintings = pdi.selectPaintingsByPaintingsId(paintingId);
//		Paintings painting = paintings.get(0);
//		if (painting != null) {
//			painting.setDelicated(1); // 改成有庫存
//			pdi.update(painting);
//		} else {
//			System.out.println("找不到此id的畫作");
//		}
//	}
//
//	@Override
//	public void setPaintingSold(String paintingId) {
//		List<Paintings> paintings = pdi.selectPaintingsByPaintingsId(paintingId);
//		Paintings painting = paintings.get(0);
//		if (painting != null) {
//			painting.setDelicated(0); // 改成賣出
//			pdi.update(painting);
//		} else {
//			System.out.println("找不到此id的畫作");
//		}
//	}
//
//	@Override
//	public void delete(String paintingId) {
//		if (pdi.selectPaintingsByPaintingsId(paintingId) != null) {
//			pdi.delete(paintingId);
//		} else {
//			System.out.println("找不到此id的畫作");
//		}
//
//	}
//
//	@Override
//	public Long findPaintingsTotalCount() {
//		Long paintingsTotalCount = pdi.getPaintingsTotalCount();
//		return paintingsTotalCount;
//	}
//
//	@Override
//	public List<Paintings> findByPage(Integer pageSize, Integer currentPage) {
//		List<Paintings> selectPaintingsLimit = pdi.selectPaintingsLimit(pageSize, currentPage);
//		return selectPaintingsLimit;
//	}

	// =========================================================================================================

	
	

}

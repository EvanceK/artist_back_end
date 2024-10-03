package com.artist.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artist.dao.impl.PaintingsDaoImpl;
import com.artist.dto.PaintingDTO;
import com.artist.entity.Paintings;
import com.artist.repository.PaintingsRepository;
import com.artist.service.PaintingsService;

@Service
public class PaintingsServiceImpl implements PaintingsService {

	@Autowired // 用spring管理
	PaintingsDaoImpl pdi;

	@Autowired // 這裡是用 com.artist.repository.PaintingsDao; //不是自己寫的 PaintingsDao
	private PaintingsRepository ptr;

	
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	// =========================================================================================================

	// 用傳統的寫法
	@Override
	public List<Paintings> findAll() {
		return pdi.selectAll();
	}

	@Override
	public List<PaintingDTO> findAllforArtisName() {
		return pdi.selectAllforArtisName();
	}

	@Override
	public List<Paintings> findByPaintingsName(String paintingName) {
		List<Paintings> paintings = pdi.selectAllOrderByPaintingsName(paintingName);
		return paintings;
	}

	@Override
	public void updatePrice(String paintingId, Double price) {
		List<Paintings> paintings = pdi.selectPaintingsByPaintingsId(paintingId);
		Paintings painting = paintings.get(0);
		if (painting != null) {
			painting.setPrice(price);
			pdi.update(painting);
		} else {
			System.out.println("找不到此id的畫作");
		}
	}

	@Override
	public void updateUploadDate(String paintingId, LocalDateTime uploadDate) {
		List<Paintings> paintings = pdi.selectPaintingsByPaintingsId(paintingId);
		Paintings painting = paintings.get(0);

		if (painting != null) {
			painting.setUploadDate(uploadDate);
			pdi.update(painting);
		} else {
			System.out.println("找不到此id的畫作");
		}
	}

	@Override
	public void setPaintingAvailable(String paintingId) {
		List<Paintings> paintings = pdi.selectPaintingsByPaintingsId(paintingId);
		Paintings painting = paintings.get(0);
		if (painting != null) {
			painting.setDelicated(1); // 改成有庫存
			pdi.update(painting);
		} else {
			System.out.println("找不到此id的畫作");
		}
	}

	@Override
	public void setPaintingSold(String paintingId) {
		List<Paintings> paintings = pdi.selectPaintingsByPaintingsId(paintingId);
		Paintings painting = paintings.get(0);
		if (painting != null) {
			painting.setDelicated(0); // 改成賣出
			pdi.update(painting);
		} else {
			System.out.println("找不到此id的畫作");
		}
	}

	@Override
	public void delete(String paintingId) {
		if (pdi.selectPaintingsByPaintingsId(paintingId) != null) {
			pdi.delete(paintingId);
		} else {
			System.out.println("找不到此id的畫作");
		}

	}

	@Override
	public Long findPaintingsTotalCount() {
		Long paintingsTotalCount = pdi.getPaintingsTotalCount();
		return paintingsTotalCount;
	}

	@Override
	public List<Paintings> findByPage(Integer pageSize, Integer currentPage) {
		List<Paintings> selectPaintingsLimit = pdi.selectPaintingsLimit(pageSize, currentPage);
		return selectPaintingsLimit;
	}

	// =========================================================================================================

	// 用spring data jpa 實現
	@Override
	public void create(Paintings painting) {
		ptr.save(painting);
	}

	@Override
	public List<Paintings> findByArtisId(String artisId) {
		return null;
	}

	@Override
	public List<Paintings> findByArtisName(String artisName) {
		return null;
	}

	@Override
	public List<Paintings> findByDate(String date) {
		return null;
	}

	@Override
	public List<Paintings> findByStlye(String stlye) {
		return null;
	}

	@Override
	public List<Paintings> findByUploadDate(LocalDateTime date) {
		return null;
	}

	@Override
	public List<Paintings> findByPeriod(String period) {
		return null;
	}

	@Override
	public List<Paintings> findByGenre(String genre) {
		return null;
	}

	@Override
	public List<Paintings> UploadMedia(String media) {
		return null;
	}

	@Override
	public List<Paintings> findByDimensions(String dimensions) {
		return null;
	}

	
	@Override
	public List<Paintings> sortByArtisName(String artisName) {
	
		return null;
	}

	@Override
	public List<Paintings> sortByDate(String paintingName) {
		return null;
	}

	@Override
	public List<Paintings> sortByUploadDate(LocalDateTime date) {
		return null;
	}

	@Override
	public List<Paintings> sortByStlye(String stlye) {
		return null;
	}

	@Override
	public List<Paintings> sortByPaintingName(String paintingName) {
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

	@Override
	public List<Paintings> findAllDesc() {
		return null;
	}

	@Override
	public List<Paintings> findByPaintingsId(String paintingId) {
		return null;
	}

	
	
	//商品上架的邏輯
	public void uploadItems() {
		//create new paintings
		
		// re-upload
	
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
	        
}

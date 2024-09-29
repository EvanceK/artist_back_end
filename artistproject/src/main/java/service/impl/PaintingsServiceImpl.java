package service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import bean.Paintings;
import dao.impl.PaintingsDaoImpl;
import service.PaintingsService;

@Component
public class PaintingsServiceImpl implements PaintingsService {
	PaintingsDaoImpl pdi = new PaintingsDaoImpl();

	@Override
	public void create(Paintings painting) {
		pdi.create(painting);

	}

	@Override
	public List<Paintings> findAll() {
		return pdi.selectAll();
		
	}
	
	public List<Paintings> findAllforArtisName(){
		return pdi.selectAllforArtisName();

    }


	@Override
	public List<Paintings> findAllDesc() {
		return null;
	}

	@Override
	public List<Paintings> findByPaintingsId(String paintingId) {
		return null;
	}

	@Override
	public List<Paintings> findByPaintingsName(String paintingName) {
		List<Paintings> paintings = pdi.selectAllOrderByPaintingsName(paintingName);
		return paintings;
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
	public List<Paintings> findByPage(Integer paintingsPerPage, Integer currentPage) {
		List<Paintings> selectPaintingsLimit = pdi.selectPaintingsLimit(paintingsPerPage, currentPage);
		return selectPaintingsLimit;
	}

	@Override
	public Integer findPaintingsTotalCount() {
		Integer paintingsTotalCount = pdi.getPaintingsTotalCount();
		return paintingsTotalCount;
	}
	



}

package dao;

import bean.Paintings;
import dto.PaintingDTO;

import java.util.List;
import java.util.Map;


public interface PaintingsDao {
    // Create
    void create(Paintings painting);

    // Read
    List<Paintings> selectAll();
    List<PaintingDTO> selectAllforArtisName();
	
    List<Paintings> selectAllOrderByPaintingsIdDESC();
    List<Paintings> selectPaintingsByPaintingsId(String paintingId);
	List<PaintingDTO> selectAllOrderByPaintingsName(String paintingName);

	
	
    //實現分頁
    List<PaintingDTO> selectPaintingsLimit(Integer PaintingsPerPage, Integer pageNumber);
    
    //查詢總數
    Long getPaintingsTotalCount();

    // Update
    void update(Paintings painting);

    // Delete
    void delete(String paintingId);
}

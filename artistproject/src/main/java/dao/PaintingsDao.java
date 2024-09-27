package dao;

import bean.Paintings;
import java.util.List;


public interface PaintingsDao {
    // Create
    void create(Paintings painting);

    // Read
    List<Paintings> selectAll();
    
    List<Paintings> selectAllOrderByPaintingsIdDESC();

    List<Paintings> selectPaintingsByPaintingsId(String paintingId);
    
    //實現分頁
    List<Paintings> selectPaintingsLimit(Integer PaintingsPerPage, Integer pageNumber);
    
    //查詢總數
    Integer getPaintingsTotalCount();

    // Update
    void update(Paintings painting);

    // Delete
    void delete(String paintingId);
}

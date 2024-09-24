package dao;

import bean.Paintings;
import java.util.List;


public interface PaintingsDao {
    // Create
    void create(Paintings painting);

    // Read
    List<Paintings> selectAll();
    
    List<Paintings> selectPaintingsByPaintingsId(String paintingId);

    // Update
    void update(Paintings painting);

    // Delete
    void delete(String paintingId);
}

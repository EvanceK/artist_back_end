package service;

import java.time.LocalDateTime;
import java.util.List;

import bean.Paintings;
import dao.impl.PaintingsDaoImpl;

public interface PaintingsService {

	// Create
    void create(Paintings painting);

    // Read
    List<Paintings> findAll();
    List<Paintings> findAllDesc();
    List<Paintings> findByPaintingsId(String paintingId);
    List<Paintings> findByPaintingsName(String paintingName);
    List<Paintings> findByArtisId(String artisId);
    List<Paintings> findByArtisName(String artisName); 	//inner join or two times sql select
    List<Paintings> findByDate(String date);
    List<Paintings> findByStlye(String stlye);
    List<Paintings> findByUploadDate(LocalDateTime date);
    List<Paintings> findByPeriod(String period);
    List<Paintings> findByGenre(String genre);
    List<Paintings> UploadMedia(String media);
    List<Paintings> findByDimensions(String dimensions);
    

    //sort by 
    List<Paintings> sortByArtisName(String artisName);	//inner join
    List<Paintings> sortByPaintingsName(String paintingName);
    List<Paintings> sortByDate(String paintingName);
    List<Paintings> sortByUploadDate(LocalDateTime date);

    
    // Update
    void updatePrice(String paintingId, Double price);
    void updateUploadDate(String paintingId, LocalDateTime uploadDate);
    void setPaintingAvailable(String paintingId);//need to apply Transaction
    void setPaintingSold(String paintingId);//need to apply Transaction

 
    // Delete
    void delete(String paintingId);
}

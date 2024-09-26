package service;

import java.time.LocalDateTime;
import java.util.List;

import bean.Paintings;

public interface PaintingsService {
	
    // Create
    void create(Paintings painting);

    // Read
    List<Paintings> findAll();
    List<Paintings> findAllDesc();
    List<Paintings> findByPaintingsId(String paintingId);
    List<Paintings> findByPaintingsName(String paintingName);
    List<Paintings> findByArtisId(String artisId);
    List<Paintings> findByArtisName(String artisName); 	//inner join
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
    List<Paintings> sortByDate(LocalDateTime date);

    
    // Update
    void updatePrice(Paintings painting);
    void updateUploadDate(Paintings painting);
    void updateDelicated(Paintings painting);//need to apply Transaction

 
    // Delete
    void delete(String paintingId);
}

package com.artist.service;

import java.time.LocalDateTime;
import java.util.List;

import com.artist.dto.PaintingDTO;
import com.artist.entity.Paintings;

public interface PaintingsService {

	// Create
    void create(Paintings painting);

    // Read
    List<Paintings> findAll();
    List<PaintingDTO> findAllforArtisName();

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

    List<Paintings> findByPage(Integer pageSize, Integer currentPage);
    Long findPaintingsTotalCount();

    //sort by 
    List<Paintings> sortByArtisName(String artisName);	//inner join
    List<Paintings> sortByUploadDate(LocalDateTime date);
    List<Paintings> sortByStlye(String stlye);
    List<Paintings> sortByPaintingName(String paintingName);
    List<Paintings> sortByPrice(Double price);
    List<Paintings> sortByDate(String date);
    List<Paintings> sortBypopular(Integer popular);
    
    // Update
    void updatePrice(String paintingId, Double price);
    void updateUploadDate(String paintingId, LocalDateTime uploadDate);
    void setPaintingAvailable(String paintingId);//need to apply Transaction
    void setPaintingSold(String paintingId);//need to apply Transaction

 
    // Delete
    void delete(String paintingId);
    
    
    //上架下架機制
    void uploadItems();

    void removeItems();
    
}
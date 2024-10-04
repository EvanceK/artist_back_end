package com.artist.service;

import java.time.LocalDateTime;
import java.util.List;

import com.artist.dto.PaintingDTO;
import com.artist.entity.Paintings;

public interface PaintingsService {

	// Create
    void create(Paintings painting);

    // Read
    Long findPaintingsTotalCount();
    List<Paintings> findAll();
    List<Paintings> findAllDesc();
    
    //dto把ArtisName加上
    List<PaintingDTO> findAllforArtisName();
    List<PaintingDTO> findAllforArtisNameLike();
    List<PaintingDTO> getAllAvailablePainting();

    
    //用Paintings查詢
    List<Paintings> findByPaintingsId(String paintingId);
    List<Paintings> findByPaintingsName(String paintingName);
    List<Paintings> findByPaintingsNameLike(String paintingName);

    
    //用ArtisId查詢
    List<Paintings> findByArtisId(String artisId);
    //用Style查詢
    List<Paintings> findByStlye(String stlye);
    
    List<Paintings> findByPeriod(String period);
    List<Paintings> findByGenre(String genre);
    List<Paintings> UploadMedia(String media);
    List<Paintings> findByDimensions(String dimensions);

    List<Paintings> findByPage(Integer pageSize, Integer currentPage);

    //sort by 
    List<Paintings> sortByArtisName(String artisName);
    List<Paintings> sortByPaintingName(String paintingName);
    List<Paintings> sortByUploadDate(LocalDateTime date);
    List<Paintings> sortByPrice(Double price);
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
//    void removeItems();//不用這個
    
}

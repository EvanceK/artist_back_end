package com.artist.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;

import com.artist.dto.response.PaintingDTO;
import com.artist.entity.Paintings;


public interface PaintingsService {

	// Create
    void create(PaintingDTO paintingDTO);

    // Read
    Long findPaintingsTotalCount();
    List<PaintingDTO> getAll();
    List<PaintingDTO> getAllDesc();
    
    //dto把ArtisName加上
    List<PaintingDTO> getAllforArtisName();
    List<PaintingDTO> getAllAvailablePainting();

    
    //用Paintings查詢
    PaintingDTO getByPaintingsId(String paintingId);
    List<PaintingDTO> getByPaintingsName(String paintingName);
    
    
    //Like查詢
	List<PaintingDTO> findPaintingAndArtistPartOfName(String name); 

    
    //用ArtisId查詢
    List<Paintings> getByArtisId(String artisId);
    //用Style查詢
    List<Paintings> getByStlye(String stlye);
    
    List<Paintings> getByPeriod(String period);
    List<Paintings> getByGenre(String genre);
    List<Paintings> UploadMedia(String media);
    List<Paintings> getByDimensions(String dimensions);

    public Page<PaintingDTO> getPaintingsByPage(Integer pageSize, Integer currentPage);
	public Page<PaintingDTO> getAllforArtistIdByPage(Integer pageSize, Integer currentPage, String artistId);

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
    //判斷用
    boolean existsBypaintingId(String paintingId);

}

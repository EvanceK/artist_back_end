package com.artist.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.artist.dto.response.PaintingDTO;
import com.artist.entity.Paintings;

public interface PaintingsService {

	// Create
    void create(PaintingDTO paintingDTO);
	Paintings saveImage(MultipartFile file) throws IOException;

    // update
    void update(PaintingDTO PaintingDTO);
    // Read
    Long findPaintingsTotalCount();
	Long findPresaleExhibitionTotalCount();
	Long findInBiddingTotalCount();

    
    List<PaintingDTO> getAll();
    
    //dto把ArtisName加上
    List<PaintingDTO> getAllAvailablePainting();
    
	Page<PaintingDTO> getAllInBidding(Integer pageSize, Integer currentPage);
	Page<PaintingDTO> getAllInPresaleExhibition(Integer pageSize, Integer currentPage);

	List<PaintingDTO> getPaintingsByBidrecords();


    
    //用Paintings查詢
    PaintingDTO getByPaintingsId(String paintingId);
    List<PaintingDTO> getByPaintingsName(String paintingName);
    Paintings getOnePaintingsById(String paintingId);
    
	byte[] getPaintingBlob(String paintingId);
	
    //Like查詢
	List<PaintingDTO> findPaintingAndArtistPartOfName(String name); 


    public Page<PaintingDTO> getPaintingsByPage(Integer pageSize, Integer currentPage);
	public Page<PaintingDTO> getAllforArtistIdByPage(Integer pageSize, Integer currentPage, String artistId);


    //即將結束<24hr
	List<PaintingDTO> getUpcomingAuction();
    //新上架 <24hr
	List<PaintingDTO> getRecentlyUploaded();

    
    // Update
    void updateUploadDate(String paintingId, LocalDateTime uploadDate);
    void setPaintingAvailable(String paintingId);//need to apply Transaction
    void setPaintingSold(String paintingId);//need to apply Transaction
    void updatePrice(String paintingId, Double price);//need to apply Transaction

    
 
    // Delete
    void delete(String paintingId);
       

    //判斷用
    boolean existsBypaintingId(String paintingId);

    
	void setSatusfinished(String paintingId);

}

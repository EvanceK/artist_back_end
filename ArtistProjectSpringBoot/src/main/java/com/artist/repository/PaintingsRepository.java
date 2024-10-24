package com.artist.repository;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.artist.entity.Paintings;


public interface PaintingsRepository extends JpaRepository<Paintings,String>{
	
//JpaRepository已經有
//  基本 CRUD 操作
//  S save(S entity)：保存一個實體，新增或更新。
//  Iterable<S> saveAll(Iterable<S> entities)：批量保存實體。
//  Optional<T> findById(ID id)：根據 ID 查詢實體，返回一個 Optional。
//  boolean existsById(ID id)：檢查是否存在特定 ID 的實體。
//  Iterable<T> findAll()：查詢所有實體。
//  Iterable<T> findAllById(Iterable<ID> ids)：根據 ID 列表查詢實體。
//  long count()：計算實體的總數。
//  void deleteById(ID id)：根據 ID 刪除實體。
//  void delete(T entity)：刪除指定實體。
//  void deleteAll(Iterable<? extends T> entities)：批量刪除實體。
//  void deleteAll()：刪除所有實體。
//  2. 分頁和排序操作
//  JpaRepository 還提供了用於分頁和排序的功能：
//
//  Page<T> findAll(Pageable pageable)：根據分頁請求查詢實體。
//  List<T> findAll(Sort sort)：根據排序請求查詢所有實體。
//	
//	===================================================================================================
//	以下自己寫的
//	//確定返回只有一個對象會用Optional
//    //關鍵字 Equal/Is/ Like
//	  Optional<Paintings> findByPaintingId(String paintingId); // where paintingId = ?1
//    Optional<Paintings> findByfindByPaintingIdIs(Long id); // where paintingId = ?1
//    Optional<Paintings> findByPaintingIdEqual(String paintingId); // where paintingId = ?1
//    //關鍵字 Like
//	List<Paintings> findByPaintingIdLike(String paintingId); // where paintingId Like ?1
//	List<Paintings> findByPaintingIdNotLike(String paintingId); // where paintingId not Like ?1
//	
//    //確定返回只有一個對象會用Optional
//    Optional<Paintings> findByPaintingName(String paintingName); // where paintingName = ?1
//
//    //關鍵字 And /Or
//    List<Paintings> findByPaintingIdAndPaintingsName(String paintingId, String paintingName); // where paintingId = ?1 and paintingName = ?2
//    List<Paintings> findByPaintingIdOrPaintingsName(String paintingId, String paintingName); // where paintingId = ?1 or paintingName = ?2
//
//    //關鍵字 LessThan /ThanEqual/GreaterThan/GreaterThanEqual
//    List<Paintings> findByPriceThan(Double price); // where price < ?1
//    List<Paintings> findByPriceLessThanEqual(Double price); // where price <= ?1
//    List<Paintings> findByPriceGreaterThan(Double price); // where price > ?1
//    List<Paintings> findByPriceGreaterThanEqual(Double price); // where price >= ?1
//    
//   //關鍵字Order By
//    List<Paintings> findByArtistIdOrderByPaintingIdDesc(String artistId,String paintingId); // where  artistId = ?1 order by paintingId=?2 desc
//
//    List<Paintings> findBypaintingIdNot(String paintingId); // where paintingId <> ?1
//
//    List<Paintings> findBypaintingIdIn(Collection<Paintings> paintingId); // where paintingId in ?1
//    List<Paintings> findBypaintingIdNotIn(Collection<Paintings> Paintings); // where paintingId not in ?1
//
//    //關鍵字 IsNotNull /IsNull
//    List<Paintings> findBypaintingNameIsNotNull(String paintingName); // where paintingName is not null ?1
//    List<Paintings> findBypaintingNameIsNull(String paintingName); // where paintingName is null ?1
//
//    //關鍵字 Top GreaterThan/First GreaterThan
//    Optional<Paintings> findTopByPaintingIdGreaterThan(String paintingId); // 取得第一筆
//    Optional<Paintings> findFirstByPaintingIdGreaterThan(String paintingId); // 取得第一筆
//    
//    List<Paintings> findTop10ByPaintingIdGreaterThan(String paintingId); // 取得前10筆
//    List<Paintings> findFirst10ByPaintingIdGreaterThan(String paintingId); // 取得前10筆
//    //關鍵字count
//    long countByPriceGreaterThan(Double price); // select count(*) from paintings p where p.price > ?1
//    //關鍵字 exists
//    boolean existsBypaintingId(String paintingId);
//    boolean existsBypaintingName(String paintingName);
//    
//    
//	//After / Before / Date：
//	//用於日期範圍查詢。
//    List<Paintings> findByUploadDateAfter(LocalDateTime date);
//    List<Paintings> findByUploadDateBefore(LocalDateTime date);
//    
//    
//    //用於關聯查詢。
//    //With：
//    List<Paintings> findByArtistIdWithPaintings(String artistId);
//    
//    //Join / LeftJoin / RightJoin：
//    @Query("SELECT p FROM Paintings p JOIN p.artist a WHERE a.artisId = ?1")
//    List<Paintings> findByArtistIdUsingJoin(String artistId);
//    //GroupBy：
//    @Query("SELECT p.genre, COUNT(p) FROM Paintings p GROUP BY p.genre")
//    List<Object[]> countPaintingsByGenre();
//    
//    
	  

    
    //以下實作
	Optional <Paintings> findByPaintingId(String paintingId);
	//一直抓不到....手動實現
//	@Query("SELECT p FROM Paintings p WHERE p.paintingId = :paintingId")
//	Optional<Paintings> findByPaintingId(@Param("paintingId") String paintingId);
	
	
	List<Paintings> findByPaintingName(String paintingName);
	
	@Query(value = "SELECT p.*, a.artist_name AS artistName FROM paintings p JOIN artist a ON p.artist_id = a.artist_id WHERE p.upload_date > NOW() - INTERVAL :totalDay DAY ORDER BY p.painting_id", nativeQuery = true)
    List<Paintings> findAllDelicatedPaintingsWithArtist(@Param("totalDay") int totalDay);
    
	@Query(value = "SELECT p.*, a.artist_name FROM paintings p JOIN artist a ON p.artist_id = a.artist_id WHERE p.upload_date > NOW() - INTERVAL :totalDay DAY ORDER BY p.painting_id", nativeQuery = true)
   	Page<Paintings> findAllDelicatedPaintingsWithArtist(Pageable pageable, @Param("totalDay") int totalDay);
    
    @Query(value = "SELECT p.*, a.artist_name FROM Paintings p JOIN artist a ON p.artist_id = a.artist_id WHERE p.upload_date > NOW() - INTERVAL :canBidDay DAY AND p.upload_date <= NOW() ", nativeQuery = true) // 例子：查找 delicated =1 by分頁
   	Page<Paintings> findAllPresaleExhibition(Pageable pageable,@Param("canBidDay") int canBidDay);
    
	@Query(value = "SELECT p.*, a.artist_name FROM paintings p JOIN artist a ON p.artist_id = a.artist_id WHERE p.upload_date > NOW() - INTERVAL 2 DAY ORDER BY p.painting_id", nativeQuery = true)
   	Page<Paintings> findAllInBidding(Pageable pageable);
    
    

//    @Query("SELECT p FROM Paintings p JOIN p.artist a ORDER BY p.paintingId")
//    List<Paintings> findAll();
    
    @Query(value = "SELECT COUNT(*) FROM paintings p WHERE p.upload_date > NOW() - INTERVAL :totalDay DAY", nativeQuery = true)
    long countByDelicated(@Param("totalDay") int totalDay);
    
    @Query(value ="SELECT COUNT(*) FROM Paintings p WHERE p.upload_date > NOW() - INTERVAL :canBidDay DAY AND p.upload_date <= NOW()", nativeQuery = true)
    long countByPresaleExhibition(@Param("canBidDay") int canBidDay);
    
    @Query(value = "SELECT COUNT(*) FROM Paintings p WHERE p.upload_date > NOW() - INTERVAL :canBidDay DAY", nativeQuery = true)
    long countByInBidding(@Param("canBidDay") int canBidDay);
    
    //給畫家頁面用的
    @Query(value = "SELECT p.*, a.artist_name FROM Paintings p JOIN artist a ON p.artist_id = a.artist_id WHERE p.upload_date > NOW() - INTERVAL :totalDay DAY AND p.artist_Id = :artistId", nativeQuery = true)
    Page<Paintings> findAllDelicatedWithArtist(Pageable pageable,@Param("totalDay") int totalDay, @Param("artistId") String artistId);
    
    @Query(value = "SELECT COUNT(*) FROM paintings p WHERE p.upload_date > NOW() - INTERVAL :totalDay DAY AND p.artist_id = :artistId", nativeQuery = true)
    long countByDelicated(@Param("totalDay") int totalDay,String artistId);
    
    boolean existsBypaintingId(String paintingId);
  
  //給首頁search用
  @Query(value = "SELECT p.* FROM paintings p JOIN artist a ON p.artist_id = a.artist_id WHERE p.upload_date > NOW() - INTERVAL :totalDay DAY AND (p.painting_name LIKE CONCAT('%', :keyword, '%') OR a.artist_name LIKE CONCAT('%', :keyword, '%')) ORDER BY p.painting_id", nativeQuery = true)
  List<Paintings> findPaintingAndArtistPartOfName(@Param("totalDay") int totalDay,@Param("keyword") String keyword);
    
  //用於查快結標得商品 < 1天
  @Query(value = "SELECT * FROM paintings p " +
          "WHERE TIMESTAMPDIFF(HOUR, CURRENT_TIMESTAMP, DATE_ADD(p.upload_date, INTERVAL 3 DAY)) <= 24 " +
          "AND TIMESTAMPDIFF(HOUR, CURRENT_TIMESTAMP, DATE_ADD(p.upload_date, INTERVAL 3 DAY)) >= 0 " +
          "ORDER BY TIMESTAMPDIFF(HOUR, CURRENT_TIMESTAMP, DATE_ADD(p.upload_date, INTERVAL 3 DAY)) ASC", 
          nativeQuery = true)
   List<Paintings> findPaintingsClosingSoon();
  
  
  @Query(value = "SELECT * FROM paintings p " +
          "WHERE TIMESTAMPDIFF(HOUR, p.upload_date, CURRENT_TIMESTAMP) <= 24 " +
          "ORDER BY p.upload_date DESC", 
          nativeQuery = true)
   List<Paintings> findRecentlyUploaded();
  
  Optional<Paintings> findByPaintingIdAndDelicated(String paintingId, Integer Delicated); // where paintingId = ?1 and paintingName = ?2

  @Query(value = "SELECT p.* FROM paintings p WHERE p.painting_id IN (SELECT b.painting_id FROM bidrecord b) AND p.painting_id NOT IN (SELECT o.painting_id FROM orderdetails o)", nativeQuery = true)
  List<Paintings> findPaintingsByBidrecords();

}

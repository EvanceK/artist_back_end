package com.artist.config;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.artist.dto.response.PaintingDTO;
import com.artist.repository.BidrecordRepository;
import com.artist.repository.PaintingsRepository;
import com.artist.service.impl.EmailServiceImpl;
import com.artist.service.impl.OrdersServiceImpl;
import com.artist.service.impl.PaintingsServiceImpl;

@Component
public class InitService implements CommandLineRunner {

	@Autowired // 用spring管理
	PaintingsServiceImpl psi;
	@Autowired
	PaintingsRepository ptr;
	@Autowired
	BidrecordRepository brr;
	@Autowired
	OrdersServiceImpl osi;
	@Autowired
	EmailServiceImpl esi;

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	@Override
	public void run(String... args) throws Exception {
		System.out.println("InitService is running...");
		initializeAllPaintings(); // 應用啟動時運行商品下架邏輯
	}

	// 商品下架的邏輯 自動計算下架時間
	public void initializeAllPaintings() {
		// loading出目前已上架的商品
		List<PaintingDTO> allPaintings = psi.getPaintingsByBidrecords();
		System.out.println("Load the number of paintings scheduled for removal :" + allPaintings.size());

		for (PaintingDTO painting : allPaintings) {
			// 計算下架時間
			LocalDateTime uploadDate = painting.getUploadDate();
			LocalDateTime removeDate = uploadDate.plusDays(14); // 這邊修改下架時間 plusDays plusHours plusMinutes
			// 計算現在時間和下架時間的時間差
			long delay = Duration.between(LocalDateTime.now(), removeDate).toMillis();
			// 如果下架時間已過，就立即標記為下架
			if (delay <= 0) {
				psi.setSatusfinished(painting.getPaintingId());
				osi.finalizeHighestBidAsOrder(painting, removeDate);

				esi.sendAuctionWinningEmail(painting.getPaintingId());

				System.out.println("商品已自動下架：" + painting.getPaintingId());
			}

//			long twoDaysInMillis = 48 * 60 * 60 * 1000; // 兩天的毫秒
//			if (delay > twoDaysInMillis) {
//				long statusChangeDelay = delay - twoDaysInMillis;
////                System.out.println("安排更新：" + painting.getPaintingId() + "，延遲：" + statusChangeDelay + " 毫秒");
//
//				scheduler.schedule(() -> {
//					 try {
//		                    setSatusCanBid(painting.getPaintingId()); // 改成可以下單
////		                    System.out.println("可以下單：" + painting.getPaintingId());
//		                } catch (Exception e) {
//		                    System.err.println("下單時發生錯誤：" + e.getMessage());
//		                }
//				}, statusChangeDelay, TimeUnit.MILLISECONDS);
//			}else {
//                setSatusCanBid(painting.getPaintingId()); // 改成可以下單
//			} 
//			
			if (delay > 0) {// 如果還未到下架時間，則設置定時任務
				System.out.println("Scheduling removal task: " + painting.getPaintingId() + "，延遲：" + delay + " 毫秒");

				scheduler.schedule(() -> {
					try {
						osi.finalizeHighestBidAsOrder(painting, removeDate);
						psi.setSatusfinished(painting.getPaintingId());
						System.out.println("商品已自動下架：" + painting.getPaintingId());
					} catch (Exception e) {
						e.printStackTrace();
					}

					esi.sendAuctionWinningEmail(painting.getPaintingId());

				}, delay, TimeUnit.MILLISECONDS);
			}
		}
	}

//
//	public void setSatusCanBid(String paintingId) {
//		Optional<Paintings> optionalPainting = ptr.findById(paintingId);
//		if (optionalPainting.isPresent()) { // 檢查有沒有找到
//			Paintings painting = optionalPainting.get(); // 得到 Painting 對象
//			
//	        if (painting.getDelicated() == 2) {
//	            painting.setDelicated(1); // 改成可以下單
//	            painting.setStatus("In Bidding");
//	            ptr.save(painting); // 更新畫作
//	        } else {
////	            System.out.println("畫作不是Presale狀態：" + painting.getPaintingId());
//	        }
//	    } else {
//	        System.out.println("找不到此 id 的畫");
//		}
//	}

}

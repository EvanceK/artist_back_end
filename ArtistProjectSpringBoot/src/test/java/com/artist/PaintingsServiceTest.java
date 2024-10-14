package com.artist;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test; // 確保使用 JUnit 5 的 @Test
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import com.artist.dto.response.PaintingDTO;
import com.artist.service.impl.PaintingsServiceImpl;

//@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PaintingsServiceTest {

	@Autowired
	private PaintingsServiceImpl psi;

	@Test
	public void setUp() {
		PaintingDTO painting = new PaintingDTO();
		painting.setPaintingId("PT2222");
		painting.setPaintingName("Mona Lisa");
		painting.setArtistId("AR0009");
		painting.setPrice(2222222.0);
		painting.setUploadDate(LocalDateTime.now());  // 設置當前時間
		painting.setDelicated(1);  // 設置當前時間

		psi.create(painting);

	}

	@Test
	void testDeletePaintingById() {
//		psi.delete("PT0104");
//		psi.delete("PT0105");

//		psi.delete("PT0109");
	}
	
	@Test
	void testUpdatePaintingById() {
		
		for(int i=1;i<=10;i++) {
		// 使用 String.format() 補零，"%04d" 代表總長度為4，前面補零
		String x = String.format("PT%04d", i);
		//設定現在時間
//		psi.updateUploadDate(x, LocalDateTime.now());
		
		// 指定時間
		LocalDateTime specificDateTime = LocalDateTime.of(2024, 10, 10, 17, 30, 0);
		psi.updateUploadDate(x, specificDateTime);
		
		
		psi.setPaintingAvailable(x);

		}
	}
	@Test
	void testUpdatePrice() {
		psi.updatePrice("PT0001", 300000.0);	
		psi.updatePrice("PT0002", 200000.0);	
		psi.updatePrice("PT0003", 400000.0);	
		psi.updatePrice("PT0004", 200000.0);	
		psi.updatePrice("PT0005", 500000.0);	
		psi.updatePrice("PT0006", 200000.0);	
		psi.updatePrice("PT0007", 900000.0);	
		psi.updatePrice("PT0008", 300000.0);	
		psi.updatePrice("PT0009", 1100000.0);	
		psi.updatePrice("PT0010", 1200000.0);	
	}
	
	
	@Test
	void testRemoveDate() {
//		psi.removeItems();

	}
	@Test
	void testgetByPaintingsId(String paintingId) {
		paintingId="PT0002";
		PaintingDTO byPaintingsId = psi.getByPaintingsId(paintingId);
		System.out.println(byPaintingsId);
	}
	@Test
	void testgetAll(){
		List<PaintingDTO> all = psi.getAll();
		System.out.println(all);

	}
	
}

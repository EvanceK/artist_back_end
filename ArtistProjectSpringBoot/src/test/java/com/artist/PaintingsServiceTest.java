package com.artist;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test; // 確保使用 JUnit 5 的 @Test
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.artist.entity.Paintings;
import com.artist.repository.PaintingsRepository;
import com.artist.service.impl.PaintingsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaintingsServiceTest {

	@Autowired
	private PaintingsServiceImpl psi;

	@Test
	@Transactional
	public void setUp() {
		Paintings painting = new Paintings();
		painting.setPaintingId("PT2222");
		painting.setPaintingName("Mona Lisa");
		painting.setArtisId("AR0009");
		painting.setPrice(2222222.0);
		painting.setUploadDate(LocalDateTime.now());  // 設置當前時間
		painting.setDelicated(1);  // 設置當前時間

		psi.create(painting);

	}

	@Test
	void testDeletePaintingById() {
		psi.delete("PT0108");
		psi.delete("PT0109");
	}
	
	@Test
	void testUpdatePaintingById() {
		psi.updateUploadDate("PT0071", LocalDateTime.now().minusHours(3L));
		psi.updateUploadDate("PT0072", LocalDateTime.now().minusHours(3L));
		psi.updateUploadDate("PT0073", LocalDateTime.now().minusHours(3L));

	
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
		psi.removeItems();
	}
	
	
}

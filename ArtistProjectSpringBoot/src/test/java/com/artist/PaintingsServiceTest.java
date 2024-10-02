package com.artist;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test; // 確保使用 JUnit 5 的 @Test
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.artist.dao.PaintingsDao;
import com.artist.entity.Paintings;
import com.artist.service.impl.PaintingsServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaintingsServiceTest {

	@Autowired
	private PaintingsServiceImpl psi;

	@Autowired
	private PaintingsDao paintingsDao;

	@Test
	@Transactional
	public void setUp() {
		// 你可以在這裡預設一些測試用的數據
		Paintings painting = new Paintings();
		painting.setPaintingId("PT2222");
		painting.setPaintingName("Mona Lisa");
		painting.setArtisId("AR0009");
		painting.setPrice(2222222.0);
		painting.setUploadDate(LocalDateTime.now());  // 設置當前時間
		painting.setDelicated(1);  // 設置當前時間

		paintingsDao.create(painting);

	}

	@Test
	void testDeletePaintingById() {
		psi.delete("PT0108");
		psi.delete("PT0109");

	}
}

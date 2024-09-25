package artistproject;

import java.time.LocalDateTime;
import java.util.List;

import bean.Paintings;
import dao.impl.PaintingsDaoImpl;

public class PaintingsDaoImplTest {

	public static void main(String[] args) {
        PaintingsDaoImpl paintingsDao = new PaintingsDaoImpl();

////		//測試添加
//	        Paintings paintings = new Paintings();
//	        paintings.setPaintingName("The Persistence of Memory222");
//	        paintings.setArtisId("AR0001");
//	        paintings.setLargUrl("largUrl");
//	        paintings.setSmallUrl("smallUrl");
//	        paintings.setPrice(22.0);
//	        // 在需要顯示年份時，提取年份
//	        //int year = paintings.getDate().getYear(); // 只獲取年份 輸出：1999
//	        paintings.setDate(LocalDateTime.of(1999, 1, 1, 0, 0));
//	        paintings.setStyle("aaa");
//	        paintings.setUploadDate(LocalDateTime.now());
//	        paintings.setPeriod("aaa");
//	        paintings.setGenre("aaa");
//	        paintings.setMedia("aaa");
//	        paintings.setDimensions("aaa");
//	        paintings.setDelicated(1);
//	        paintingsDao.create(paintings);
//	        
//=================================================================
        	//測試刪除
//        paintingsDao.delete("PT0001");
        
//=================================================================

        	//測試select
        List<Paintings> selectAll = paintingsDao.selectAll();
        for(Paintings p:selectAll)
        {
        	System.out.println(p);
        }
        
        System.out.println();
        
        List<Paintings> selectAlldesc = paintingsDao.selectAllOrderByPaintingsIdDESC();
        for(Paintings p:selectAlldesc)
        {
        	System.out.println(p);
        }
        //只有一個 取index(0)
//        Paintings paintings = selectPaintingsByPaintingsId.get(0);
//        System.out.println(paintings);
        
//=================================================================

        //測試update
//        List<Paintings> selectPaintingsByPaintingsId = paintingsDao.selectPaintingsByPaintingsId("PT0002");
//        Paintings paintings = selectPaintingsByPaintingsId.get(0);
//        System.out.println(paintings.getPaintingName());
//        paintings.setMedia("dddddd");
//        paintingsDao.update(paintings);
//        System.out.println(paintings.getPaintingName());

        
	    }

}

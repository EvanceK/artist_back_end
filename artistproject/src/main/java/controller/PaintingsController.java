package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Paintings;
import service.impl.PaintingsServiceImpl;

//@RestController 是 @Controller 和 @ResponseBody 的結合體
@CrossOrigin
@RestController
@RequestMapping("/PTController")
public class PaintingsController {

	@Autowired // 透過 @Autowired 的方式，去加入想要使用的 serviceimpl
	private PaintingsServiceImpl psi;

	@RequestMapping("/findall") // /PTController/findall
	public String findall() throws JsonProcessingException {
		ObjectMapper jsonMapper = new ObjectMapper();
		List<Paintings> alllist = psi.findAllforArtisName();	//inner joing artis_id
        // 轉成json格式
		return jsonMapper.writeValueAsString(alllist); // 直接返回序列化結果
	}
	@RequestMapping("/findptname") // /PTController/findptname
	public String findPaintingsName() throws JsonProcessingException {
		ObjectMapper jsonMapper = new ObjectMapper();
		List<Paintings> paintingsName = psi.findByPaintingsName("Portrait of a Youth Holding an Arrow");
        // 轉成json格式
		return jsonMapper.writeValueAsString(paintingsName); // 直接返回序列化結果
	}
	
	
	
	@RequestMapping(value = "/findByPage", method = RequestMethod.GET)
    public Map<String, Object> findByPage(
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "pageSize", defaultValue = "6") int pageSize) {

        // 1. 查詢總數
		int totalCount = psi.findPaintingsTotalCount();
        System.out.println(totalCount);

        // 2. 計算總頁數
        int totalPage = (int) Math.ceil((double) totalCount / pageSize);
        System.out.println(totalPage);

        // 3. 根據當前頁和每頁大小查詢分頁數據
        List<Paintings> paintingsList = psi.findByPage(currentPage, pageSize);

        // 4. 將數據封裝到 Map 中返回
        Map<String, Object> result = new HashMap<>();
        result.put("totalCount", totalCount);
        result.put("totalPage", totalPage);
        result.put("currentPage", currentPage);
        result.put("pageSize", pageSize);
        result.put("paintingsList", paintingsList);

        return result;
    }
	

	
}

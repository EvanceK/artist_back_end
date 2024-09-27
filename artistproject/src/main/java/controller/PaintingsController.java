package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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
		List<Paintings> alllist = psi.findAll();
        // 轉成json格式
		return jsonMapper.writeValueAsString(alllist); // 直接返回序列化結果
	}
	@RequestMapping("/findptname") // /PTController/findptname
	public String findPaintingsName() throws JsonProcessingException {
		ObjectMapper jsonMapper = new ObjectMapper();
		List<Paintings> paintingsName = psi.findByPaintingsName(null);
		List<Paintings> alllist = psi.findAll();
        // 轉成json格式
		return jsonMapper.writeValueAsString(alllist); // 直接返回序列化結果
	}
}

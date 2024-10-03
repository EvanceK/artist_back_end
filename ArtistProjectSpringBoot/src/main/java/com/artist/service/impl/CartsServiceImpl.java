package com.artist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.artist.entity.Carts;
import com.artist.repository.CartsRepository;
import com.artist.service.CartsService;

public class CartsServiceImpl implements CartsService{

	@Autowired // 這裡是用 com.artist.repository.PaintingsDao; //不是自己寫的 PaintingsDao
	private CartsRepository cr;

	
	@Override
	public void create( String customerId, String paintingId, Double price) {
	 Carts carts = new Carts();
	carts.setCustomerId(customerId);	
	carts.setPaintingId(paintingId);
	carts.setPrice(price);
	carts.setStatus("");//狀態的名詞要設定
		
		cr.save(carts);
	}

	@Override
	public void delete(String paintingId) {
		// TODO Auto-generated method stub
		
	}

}

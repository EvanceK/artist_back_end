package com.artist.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.artist.bean.Carts;
import com.artist.dao.CartsDAO;
import com.artist.utils.DbConnection;
import com.artist.utils.IdGenerator;

public class CartsDaoImpl implements CartsDAO{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	Connection conn = DbConnection.getDb();
	@Override
	public void create(Carts c) {
		String SQL = "INSERT INTO artistproject.carts(carts_id,customer_id,painting_id) VALUES (?,?,?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1,IdGenerator.cartsId());
			ps.setString(1,c.getCustomerId());
			ps.setString(2,c.getPaintingId());
			ps.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	@Override
	public List<Carts> selectAll() {
		String SQL="select * from artistproject.carts";
		List<Carts> data=new ArrayList<Carts>();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Carts c=new Carts();
				c.setCartId(rs.getString("cart_id"));
				c.setCustomerId(rs.getString("customer_id"));
				c.setPaintingId(rs.getString("painting_id"));
				data.add(c);
			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(String cart_id,String painting_id) {
		String SQL = "update artistproject.carts set painting_id=? where cart_id=?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1,cart_id);
			ps.setString(2,painting_id);
			ps.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String id) {
		String SQL="delete from artistproject.carts where cart_id=?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1,id);
			ps.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}

package com.artist.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.artist.dao.CustomersDao;
import com.artist.entity.Customers;
import com.artist.utils.DbConnection;
import com.artist.utils.IdGenerator;


//暫時先註釋
public class CustomersDaoImpl implements CustomersDao{

	@Override
	public void create(Customers c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customers> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String id, String lastname, String firstname, String email, String account, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

//	Connection conn = DbConnection.getDb();
//	@Override
//	public void create(Customers c) {
//		String SQL = "INSERT INTO `artistproject`.`customers` (`customerId`,`lastName`,`firstName`,`email`,`account`,`password`) VALUES (?,?,?,?,?,?)";
//		try {
//			conn.setAutoCommit(false);
//			PreparedStatement ps = conn.prepareStatement(SQL);
//			ps.setString(1,IdGenerator.customersId());
//			ps.setString(2,c.getName());
//			ps.setString(3,c.getNickName());
//			ps.setString(4,c.getEmail());
//			ps.setString(5,c.getAccount());
//			ps.setString(6,c.getLastName());
//			ps.executeUpdate();
//			conn.commit();
//			conn.setAutoCommit(true);
//		} catch (SQLException e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}	
//	}
//	@Override
//			ps.setString(5,password);
//			ps.executeUpdate();
//			conn.commit();
//			conn.setAutoCommit(true);
//		} catch (SQLException e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}	
//	}		
//	@Override
//	public void delete(String id) {
//		String SQL = "delete from`artistproject`.`customers`  where customerId=?";
//		try {
//			conn.setAutoCommit(false);
//			PreparedStatement ps = conn.prepareStatement(SQL);
//			ps.setString(1,id);
//			ps.executeUpdate();
//			conn.commit();
//			conn.setAutoCommit(true);
//		} catch (SQLException e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}	
//	}			
}

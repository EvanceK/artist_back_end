package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Artis;
import bean.Customers;
import dao.CustomersDao;
import utils.DbConnection;
import utils.IdGenerator;

public class CustomersDaoImpl implements CustomersDao{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	Connection conn = DbConnection.getDb();
	@Override
	public void create(Customers c) {
		String SQL = "INSERT INTO `artistproject`.`customers` (`customerId`,`lastName`,`firstName`,`email`,`account`,`password`) VALUES (?,?,?,?,?,?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1,IdGenerator.customersId());
			ps.setString(2,c.getLastName());
			ps.setString(3,c.getFirstName());
			ps.setString(4,c.getEmail());
			ps.setString(5,c.getAccount());
			ps.setString(6,c.getLastName());
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
	public List<Customers> selectAll() {
		List<Customers> data = new ArrayList<>();;
		String SQL ="SELECT * FROM `artistproject`.`customers` ORDER BY `customers`.`customers_id`";
		Statement ps;
		try {
			
			ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(SQL);
			while(rs.next()) {
				Customers c = new Customers();
				c.setCustomerId(rs.getString("customers_id"));
				c.setLastName(rs.getString("lastName"));
				c.setFirstName(rs.getString("firstName"));
				c.setEmail(rs.getString("email"));
				c.setAccount(rs.getString("account"));
				c.setLastName(rs.getString("password"));
				data.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	@Override
	public void update(String id,String lastname,String firstname,String email,String account,String password) {
		String SQL = "update `artistproject`.`customers` set lastName=?,firstName=?,email=?,account=?,password=? where customerId=?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(6,id);
			ps.setString(1,lastname);
			ps.setString(2,firstname);
			ps.setString(3,email);
			ps.setString(4,account);
			ps.setString(5,password);
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
		String SQL = "delete from`artistproject`.`customers`  where customerId=?";
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

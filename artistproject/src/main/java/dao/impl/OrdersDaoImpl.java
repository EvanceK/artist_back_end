package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bean.Orders;
import dao.OrdersDao;
import utils.DbConnection;

public class OrdersDaoImpl implements OrdersDao{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrdersDaoImpl odi = new OrdersDaoImpl();
		List <Orders> allList = odi.selectAll();
		if(allList != null)
		{
		for (Orders o : allList) 
			{
			    // 在這裡處理每一個 Orders 對象
			    System.out.println(o.toString());
			}
		}
		else
			{
				System.out.println("List is null");
			}
		
		odi.delete("O002");
		List<Orders> orderList = odi.selectAll();
		for(Orders o : orderList)
		{
			System.out.println(o.toString());
		}
		LocalDateTime orderDate = LocalDateTime.of(2024, 9, 25, 0, 0);  // 使用 LocalDateTime 構建日期時間
		Orders ord = new Orders("O002", orderDate, "c002");
		odi.create(ord);
		List<Orders> orderList2 = odi.selectAll();
		for(Orders o : orderList2)
		{
			System.out.println(o.toString());
		}
		
		Orders ord2 = new Orders("O002",orderDate, "c001");
		odi.update(ord2);
		List<Orders> orderList3 = odi.selectAll();
		for(Orders o : orderList3)
		{
			System.out.println(o.toString());
		}
		
		}//不可以不見
	

		
		
	Connection conn = DbConnection.getDb();
	@Override
	public void create(Orders ord) {
		// TODO Auto-generated method stub
		String SQL = "insert into orders (order_number, order_date, customer_id) values(?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, ord.getOrderNumber());
			ps.setTimestamp(2, Timestamp.valueOf(ord.getOrderDate()));
			ps.setString(3, ord.getCustomerId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	@Override
	public List<Orders> selectAll() {
		// TODO Auto-generated method stub
		String SQL = "SELECT * FROM orders";
		List<Orders> orderList = new ArrayList();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Orders ord = new Orders();
				ord.setOrderNumber(rs.getString("order_number"));
				Timestamp tt = rs.getTimestamp("order_date");
				LocalDateTime orderDate = tt.toLocalDateTime(); // 將 Timestamp 轉換為 LocalDateTime
			    ord.setOrderDate(orderDate); // 傳入 LocalDateTime
			    ord.setCustomerId(rs.getString("customer_id"));
			    orderList.add(ord);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public void update(Orders ord) {
		// TODO Auto-generated method stub
		String SQL = "UPDATE orders SET order_date = ?, customer_id = ? WHERE order_number = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setTimestamp(1, Timestamp.valueOf(ord.getOrderDate()));
			ps.setString(2, ord.getCustomerId());
			ps.setString(3, ord.getOrderNumber());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(String orderNumber) {
		// TODO Auto-generated method stub
		String SQL = "delete from orders where order_number = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, orderNumber);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	
	@Override
	public List<Orders> selectByOrderNumber(String orderNumber) {
		// TODO Auto-generated method stub
				String SQL = "SELECT * FROM orders where orderNumber = ?";
				List<Orders> orderList = new ArrayList();
				try {
					PreparedStatement ps = conn.prepareStatement(SQL);
					ps.setString(1, orderNumber);
					ResultSet rs = ps.executeQuery();
					while(rs.next())
					{
						Orders ord = new Orders();
						ord.setOrderNumber(rs.getString("order_number"));
						Timestamp tt = rs.getTimestamp("order_date");
						LocalDateTime orderDate = tt.toLocalDateTime(); // 將 Timestamp 轉換為 LocalDateTime
					    ord.setOrderDate(orderDate); // 傳入 LocalDateTime
					    ord.setCustomerId(rs.getString("customer_id"));
					    orderList.add(ord);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return orderList;
			}



	@Override
	public List<Orders> selectByOrderDate(LocalDateTime orderDate) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Orders> selectByCustomerId(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}//不可以不見
package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bean.OrderDetails;
import bean.Orders;
import dao.OrderDetailsDao;
import utils.DbConnection;

public class OrderDetailsDaoImpl implements OrderDetailsDao{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	Connection conn = DbConnection.getDb();
	@Override
	public void create(String orderNumber, String paintingId, Double price) {
		// TODO Auto-generated method stub
		String SQL = "insert into orderdetails (order_number, painting_id, prices) value (?,?,?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, orderNumber);
			ps.setString(2, paintingId);
			ps.setDouble(3, price);
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public List<OrderDetails> selectAll() {
		// TODO Auto-generated method stub
		String SQL = "select * from orders";
		List<OrderDetails> orderDetialsList = null;
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				OrderDetails odel = new OrderDetails();
				odel.setOrderNumber(rs.getString("order_number"));
				odel.setPaintingId(rs.getString("painting_id"));
				odel.setPrice(rs.getDouble("price"));
				orderDetialsList.add(odel);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderDetialsList;
	}

	@Override
	public void update(OrderDetails preodl, OrderDetails newodl) {
		// TODO Auto-generated method stub
		String SQL = "update orderdetails set order_number = ?, painting_id = ?, price = ? where order_number = ? and painting_id = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setString(1, newodl.getOrderNumber());
			ps.setString(2, newodl.getPaintingId());
			ps.setDouble(3,newodl.getPrice() );
			ps.setString(4, preodl.getOrderNumber());
			ps.setString(5, preodl.getPaintingId());
			ps.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void delete(String orderNumber) {
		// TODO Auto-generated method stub
		String SQL = "delete * from orderdetails where order_number = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, orderNumber);
			ps.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}//nono

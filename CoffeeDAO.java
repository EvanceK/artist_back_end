package model;

import java.util.*;
import java.sql.*;

public class CoffeeDAO {
	Connection connect() {
		Connection cn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/classicmodels?useUnicode=true&characterEncoding=utf8", "root", "1234");
		} catch (Exception ex) {
			System.out.println("connect() error:" + ex.getMessage());
		}
		return cn;
	}

	public List<Coffee> getAll() {
		Connection cn = connect();
		List<Coffee> data = new ArrayList<Coffee>();
		if (cn == null)
			return data;
		else {
			Statement stmt = null;
			String query = "select COF_NAME, SUP_ID, Price, Sales, Total from classicmodels.COFFEES";
			try {
				stmt = cn.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					int id = rs.getInt("SUP_ID");
					String cofName = rs.getString("COF_NAME");
					double price = rs.getDouble("Price");
					int sales = rs.getInt("Sales");
					int total = rs.getInt("Total");
					Coffee s1 = new Coffee(cofName, id, sales, total, price);
					data.add(s1);
				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return data;
		}
	}
	public void insert(String cofName,int id,int sale,int total,double price) {
		Connection cn=connect();
		String sql="insert into classicmodels.coffees(COF_NAME,SUP_ID,SALES,TOTAL,PRICE)values(?,?,?,?,?)";
		try {
			cn.setAutoCommit(false);
			PreparedStatement st=cn.prepareStatement(sql);
			st.setString(1, cofName);
			st.setInt(2, id);
			st.setInt(3, sale);
			st.setInt(4, total);
			st.setDouble(5, price);
			st.executeUpdate();
			cn.commit();
			cn.setAutoCommit(true);			
		}catch(Exception ex) {
			try {
				cn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("coffee insert error "+ex.getMessage());
		}
	}
}

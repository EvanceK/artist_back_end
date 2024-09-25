package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bean.Inventories;
import dao.InventoriesDao;
import utils.DbConnection;
import utils.IdGenerator;

public class InventoriesDaoImpl implements InventoriesDao {
	Connection conn = DbConnection.getDb();

	public static void main(String[] args) {
		InventoriesDaoImpl idi = new InventoriesDaoImpl();
		idi.create();

	}

	@Override
	public void create() {
		String SQL = "INSERT INTO `artistproject`.`inventories` (`inventory_number`) VALUES (?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, IdGenerator.inventoriesId());
			ps.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public List<Inventories> selectAll() {
		List<Inventories> invList = new ArrayList<>();
		String SQL = "SELECT * FROM `artistproject`.`inventories` ORDER BY inventory_number";
		Statement ps;
		try {
			ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(SQL);
			while (rs.next()) {
				LocalDateTime dateTime = null;
				Inventories inv = new Inventories();
				inv.setInventoryNumber(rs.getString("inventory_number"));
				Timestamp timestamp = rs.getTimestamp("inventory_date");
				if (timestamp != null) {
					dateTime = timestamp.toLocalDateTime(); // Convert Timestamp to LocalDateTime
				}
				inv.setInventoryDate(dateTime);
				invList.add(inv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return invList;
	}

	@Override
	public void update(Inventories inv) {
		String SQL = "UPDATE `artistproject`.`inventories` SET `inventory_date` = ? WHERE `inventory_number` = ?;";
		// nothing to update
	}

	@Override
	public void delete(String id) {
		String SQL = "DELETE FROM `artistproject`.`inventories` WHERE ? ";
		PreparedStatement ps;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(SQL);
			ps.setString(1, id);
			ps.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}

package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bean.InventoryDetails;
import dao.InventoryDetailsDao;
import utils.DbConnection;

public class InventoryDetailsDaoImpl implements InventoryDetailsDao {
	Connection conn = DbConnection.getDb();

	@Override
	public void create(String inventoryNumber, String paintingId) {
		String SQL = "INSERT INTO `artistproject`.`inventoryDetails` (`inventory_number`, `painting_id`) VALUES (？, ？) ";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, inventoryNumber);
			ps.setString(2, paintingId);
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
	public List<InventoryDetails> selectAll() {
		List<InventoryDetails> invList = null;
		String SQL = "SELECT * FROM `artistproject`.`inventoryDetails`";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				InventoryDetails inv = new InventoryDetails();
				inv.setInventoryNumber(rs.getString("inventory_number"));
				inv.setPaintingId(rs.getString("painting_id"));
				invList.add(inv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invList;
	}

	@Override
	public void update(InventoryDetails preInv, InventoryDetails newInv) {
		String SQL = "UPDATE `artistproject`.`inventoryDetails` SET `inventory_number` = ?, `painting_id` = ? WHERE `inventory_number` = ? AND `painting_id` = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, newInv.getInventoryNumber());
			ps.setString(2, newInv.getPaintingId());
			ps.setString(3, preInv.getInventoryNumber());
			ps.setString(4, preInv.getPaintingId());
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
	public void delete(String inventoryNumber, String paintingId) {
		String SQL = "DELETE FROM `artistproject`.`inventoryDetails` WHERE `inventory_number` = ? AND `painting_id` = ?";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, inventoryNumber);
			ps.setString(2, paintingId);
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

}

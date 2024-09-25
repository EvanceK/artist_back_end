package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import bean.InventoryDetails;
import dao.InventoryDetailsDao;
import utils.DbConnection;

public class InventoryDetailsDaoImpl implements InventoryDetailsDao {
	Connection conn = DbConnection.getDb();
	@Override
	public void create(String inventoryNumber, String paintingId) {
		String SQL ="INSERT INTO `artistproject`.`inventoryDetails` (`inventory_number`, `painting_id`) VALUES (<{inventory_number: }>, <{painting_id: }>) ";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(InventoryDetails inv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String inventoryNumber, String paintingId) {
		// TODO Auto-generated method stub
		
	}
	

}

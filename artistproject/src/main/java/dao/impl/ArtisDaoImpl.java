package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import bean.Artis;
import dao.ArtisDao;
import dao.DbConnection;
import ulitilies.IdGenerator;

public class ArtisDaoImpl  implements ArtisDao{
	Connection conn = DbConnection.getDb();
	@Override
	public void create(Artis art) {
		String SQL = "INSERT INTO `artistproject`.`artis` (`artis_id`,`artis_name`) VALUES ("+ IdGenerator.artisId() +",?)";
		try {
			PreparedStatement ps = conn.prepareStatement(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public List<Artis> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Artis art) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}

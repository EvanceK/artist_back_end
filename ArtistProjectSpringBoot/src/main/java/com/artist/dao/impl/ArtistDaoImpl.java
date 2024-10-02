package com.artist.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.artist.dao.ArtistDao;
import com.artist.entity.Artist;
import com.artist.utils.DbConnection;
import com.artist.utils.IdGenerator;

public class ArtistDaoImpl  implements ArtistDao{
	Connection conn = DbConnection.getDb();
	
	public static void main(String[] args) {
		ArtistDaoImpl adi = new ArtistDaoImpl();
		adi.create("Giovanni Antonio Boltraffio");
		adi.selectAll().forEach((a)->System.out.println(a.getArtisName()));
		
	}
	
	@Override
	public void create(String artName) {
		String SQL = "INSERT INTO `artistproject`.`artis` (`artis_id`,`artis_name`) VALUES (?,?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, IdGenerator.artistId());
			ps.setString(2, artName);
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
	public List<Artist> selectAll() {
		List<Artist> artisList = new ArrayList<>();;
		String SQL ="SELECT `artis`.`artis_id`, `artis`.`artis_name` FROM `artistproject`.`artis` ORDER BY `artis`.`artis_id`";
		Statement ps;
		try {
			
			ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(SQL);
			while(rs.next()) {
				Artist art = new Artist();
				art.setArtisId(rs.getString("artis_id"));
				art.setArtisName(rs.getString("artis_name"));
				artisList.add(art);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return artisList;
	}

	@Override
	public void update(Artist art) {
		String SQL ="UPDATE `artistproject`.`artis` SET `artis_name` = ? WHERE `artis_id` = ? ";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, art.getArtisName());
			ps.setString(2, art.getArtisId());
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
	public void delete(String id) {
		String SQL ="DELETE FROM `artistproject`.`artis` WHERE ? ";
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

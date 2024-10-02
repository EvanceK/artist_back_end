package com.artist.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.artist.dao.ArtisDao;
import com.artist.entity.Artis;
import com.artist.utils.DbConnection;
import com.artist.utils.IdGenerator;

public class ArtisDaoImpl  implements ArtisDao{
	Connection conn = DbConnection.getDb();
	
	public static void main(String[] args) {
		ArtisDaoImpl adi = new ArtisDaoImpl();
		adi.create("Giovanni Antonio Boltraffio");
		adi.selectAll().forEach((a)->System.out.println(a.getArtisName()));
		
	}
	
	@Override
	public void create(String artName) {
		String SQL = "INSERT INTO `artistproject`.`artis` (`artis_id`,`artis_name`) VALUES (?,?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(SQL);
			ps.setString(1, IdGenerator.artisId());
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
	public List<Artis> selectAll() {
		List<Artis> artisList = new ArrayList<>();;
		String SQL ="SELECT `artis`.`artis_id`, `artis`.`artis_name` FROM `artistproject`.`artis` ORDER BY `artis`.`artis_id`";
		Statement ps;
		try {
			
			ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(SQL);
			while(rs.next()) {
				Artis art = new Artis();
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
	public void update(Artis art) {
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

package com.artist.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.artist.dao.PaintingsDao;
import com.artist.dto.PaintingDTO;
import com.artist.entity.Paintings;
import com.artist.utils.DbConnection;
import com.artist.utils.IdGenerator;

@Repository  // 加上這個註解表示這個類是 DAO 層
public class PaintingsDaoImpl implements PaintingsDao {
	Connection conn = DbConnection.getDb();

	@Override
	public void create(Paintings painting) {
		PreparedStatement ps = null;
		String sql = "insert into paintings(painting_id,painting_name, artis_id,larg_url, small_url, price,`date`, style,upload_date,`period`,genre, media,dimensions,delicated)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			// 填充佔位符 ?
			ps.setString(1, IdGenerator.paintingId(conn));
			ps.setString(2, painting.getPaintingName());
			ps.setString(3, painting.getArtisId());
			ps.setString(4, painting.getLargUrl());
			ps.setString(5, painting.getSmallUrl());
			ps.setDouble(6, painting.getPrice());
			ps.setString(7, painting.getDate());
			ps.setString(8, painting.getStyle());
			// 將 LocalDateTime 類型，轉換為 Timestamp
			ps.setTimestamp(9, Timestamp.valueOf(painting.getUploadDate()));
			ps.setString(10, painting.getPeriod());
			ps.setString(11, painting.getGenre());
			ps.setString(12, painting.getMedia());
			ps.setString(13, painting.getDimensions());
			ps.setInt(14, painting.getDelicated());
			// 執行
			ps.executeUpdate();
			System.out.println("新增成功");

		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			// 關閉資源
			DbConnection.closeResource(null, ps);
		}
	}

	@Override
	public List<Paintings> selectAll() {
		List<Paintings> paintingsList = new ArrayList<>();
		PreparedStatement ps = null;
		String sql = "Select * From paintings where delicated = 1";//只給前端 delicated = 1

		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paintings paintings = new Paintings();
				paintings.setPaintingId(rs.getString("painting_id"));
				paintings.setPaintingName(rs.getString("painting_name"));
				paintings.setArtisId(rs.getString("artis_id"));
				paintings.setLargUrl(rs.getString("larg_url"));
				paintings.setSmallUrl(rs.getString("small_url"));
				paintings.setPrice(rs.getDouble("price"));
				paintings.setDate(rs.getString("date"));
				paintings.setStyle(rs.getString("style"));
				if (rs.getTimestamp("upload_date") != null) {
					paintings.setUploadDate(rs.getTimestamp("upload_date").toLocalDateTime());
				}
				paintings.setPeriod(rs.getString("period"));
				paintings.setGenre(rs.getString("genre"));
				paintings.setMedia(rs.getString("media"));
				paintings.setDimensions(rs.getString("dimensions"));
				paintings.setDelicated(rs.getInt("delicated"));

				paintingsList.add(paintings);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return paintingsList;
	}

	public List<PaintingDTO> selectAllforArtisName() {
	    List<PaintingDTO> paintingsList = new ArrayList<>();
	    PreparedStatement ps = null;
		String sql = "Select painting_id,painting_name,artis_id,artis_name,larg_url,small_url,price,`date`,style,upload_date,`period`,genre,media,dimensions,delicated From paintings inner join artis USING (artis_id) Having delicated = 1 order by painting_id";

		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PaintingDTO paintings = new PaintingDTO();
				paintings.setPaintingId(rs.getString("painting_id"));
				paintings.setPaintingName(rs.getString("painting_name"));
				paintings.setArtisName(rs.getString("artis_name"));
				paintings.setLargUrl(rs.getString("larg_url"));
				paintings.setSmallUrl(rs.getString("small_url"));
				paintings.setPrice(rs.getDouble("price"));
				paintings.setDate(rs.getString("date"));
				paintings.setStyle(rs.getString("style"));
				if (rs.getTimestamp("upload_date") != null) {
					paintings.setUploadDate(rs.getTimestamp("upload_date").toLocalDateTime());
				}
				paintings.setPeriod(rs.getString("period"));
				paintings.setGenre(rs.getString("genre"));
				paintings.setMedia(rs.getString("media"));
				paintings.setDimensions(rs.getString("dimensions"));
				paintings.setDelicated(rs.getInt("delicated"));

				paintingsList.add(paintings);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return paintingsList;
	}
	
	@Override
	public List<Paintings> selectPaintingsByPaintingsId(String paintingId) {

		List<Paintings> paintingsList = new ArrayList<>();
		PreparedStatement ps = null;
		String sql = "Select * From paintings where painting_id=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, paintingId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paintings paintings = new Paintings();
				paintings.setPaintingId(rs.getString("painting_id"));
				paintings.setPaintingName(rs.getString("painting_name"));
				paintings.setArtisId(rs.getString("artis_id"));
				paintings.setLargUrl(rs.getString("larg_url"));
				paintings.setSmallUrl(rs.getString("small_url"));
				paintings.setPrice(rs.getDouble("price"));
				paintings.setDate(rs.getString("date"));
				paintings.setStyle(rs.getString("style"));
				if (rs.getTimestamp("upload_date") != null) {
					paintings.setUploadDate(rs.getTimestamp("upload_date").toLocalDateTime());
				}
				paintings.setPeriod(rs.getString("period"));
				paintings.setGenre(rs.getString("genre"));
				paintings.setMedia(rs.getString("media"));
				paintings.setDimensions(rs.getString("dimensions"));
				paintings.setDelicated(rs.getInt("delicated"));

				paintingsList.add(paintings);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return paintingsList;
	}

	@Override
	public void update(Paintings painting) {
		PreparedStatement ps = null;
		String sql = "update paintings set painting_name =?, artis_id =?,larg_url =?, small_url =?, price =?,`date` =?, style =?,upload_date =?,`period` =?,genre =?, media =?,dimensions =?,delicated =? where painting_id =?";
		try {
			ps = conn.prepareStatement(sql);
			// 填充佔位符 ?
			ps.setString(1, painting.getPaintingName());
			ps.setString(2, painting.getArtisId());
			ps.setString(3, painting.getLargUrl());
			ps.setString(4, painting.getSmallUrl());
			ps.setDouble(5, painting.getPrice());
			ps.setString(6, painting.getDate());
			ps.setString(7, painting.getStyle());
			ps.setTimestamp(8, Timestamp.valueOf(painting.getUploadDate()));
			ps.setString(9, painting.getPeriod());
			ps.setString(10, painting.getGenre());
			ps.setString(11, painting.getMedia());
			ps.setString(12, painting.getDimensions());
			ps.setInt(13, painting.getDelicated());
			ps.setString(14, painting.getPaintingId());
			System.out.println("修改成功");

			// 執行
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			// 關閉資源
			DbConnection.closeResource(null, ps);
		}
	}

	@Override
	public void delete(String paintingId) {
		PreparedStatement ps = null;
		String sql = "delete from paintings where painting_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			// 填充佔位符 ?
			ps.setString(1, paintingId);
			// 執行
			ps.executeUpdate();
			System.out.println("刪除成功");

		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			// 關閉資源
			DbConnection.closeResource(null, ps);
		}
	}

	@Override
	public List<Paintings> selectAllOrderByPaintingsIdDESC() {
		List<Paintings> paintingsList = new ArrayList<>();
		PreparedStatement ps = null;
		String sql = "Select * From paintings Order By painting_id desc";

		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paintings paintings = new Paintings();
				paintings.setPaintingId(rs.getString("painting_id"));
				paintings.setPaintingName(rs.getString("painting_name"));
				paintings.setArtisId(rs.getString("artis_id"));
				paintings.setLargUrl(rs.getString("larg_url"));
				paintings.setSmallUrl(rs.getString("small_url"));
				paintings.setPrice(rs.getDouble("price"));
				paintings.setDate(rs.getString("date"));
				paintings.setStyle(rs.getString("style"));
				if (rs.getTimestamp("upload_date") != null) {
					paintings.setUploadDate(rs.getTimestamp("upload_date").toLocalDateTime());
				}
				paintings.setPeriod(rs.getString("period"));
				paintings.setGenre(rs.getString("genre"));
				paintings.setMedia(rs.getString("media"));
				paintings.setDimensions(rs.getString("dimensions"));
				paintings.setDelicated(rs.getInt("delicated"));

				paintingsList.add(paintings);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return paintingsList;
	}
	
	@Override
	public List<Paintings> selectAllOrderByPaintingsName(String paintingName) {

			List<Paintings> paintingsList = new ArrayList<>();
			PreparedStatement ps = null;
			String sql = "Select * From paintings where painting_name=?";

			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, paintingName);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Paintings paintings = new Paintings();
					paintings.setPaintingId(rs.getString("painting_id"));
					paintings.setPaintingName(rs.getString("painting_name"));
					paintings.setArtisId(rs.getString("artis_id"));
					paintings.setLargUrl(rs.getString("larg_url"));
					paintings.setSmallUrl(rs.getString("small_url"));
					paintings.setPrice(rs.getDouble("price"));
					paintings.setDate(rs.getString("date"));
					paintings.setStyle(rs.getString("style"));
					if (rs.getTimestamp("upload_date") != null) {
						paintings.setUploadDate(rs.getTimestamp("upload_date").toLocalDateTime());
					}
					paintings.setPeriod(rs.getString("period"));
					paintings.setGenre(rs.getString("genre"));
					paintings.setMedia(rs.getString("media"));
					paintings.setDimensions(rs.getString("dimensions"));
					paintings.setDelicated(rs.getInt("delicated"));

					paintingsList.add(paintings);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return paintingsList;
	}


	@Override
	public List<Paintings> selectPaintingsLimit(Integer pageSize, Integer currentPage) {
		List<Paintings> paintingsList = new ArrayList<>();

		int offset = (currentPage - 1) * pageSize;
		String sql = "Select * From paintings ORDER by painting_id LIMIT ? OFFSET ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pageSize);
			ps.setInt(2, offset);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Paintings paintings = new Paintings();
				paintings.setPaintingId(rs.getString("painting_id"));
				paintings.setPaintingName(rs.getString("painting_name"));
				paintings.setArtisId(rs.getString("artis_id"));
				paintings.setLargUrl(rs.getString("larg_url"));
				paintings.setSmallUrl(rs.getString("small_url"));
				paintings.setPrice(rs.getDouble("price"));
				paintings.setDate(rs.getString("date"));
				paintings.setStyle(rs.getString("style"));
				if (rs.getTimestamp("upload_date") != null) {
					paintings.setUploadDate(rs.getTimestamp("upload_date").toLocalDateTime());
				}
				paintings.setPeriod(rs.getString("period"));
				paintings.setGenre(rs.getString("genre"));
				paintings.setMedia(rs.getString("media"));
				paintings.setDimensions(rs.getString("dimensions"));
				paintings.setDelicated(rs.getInt("delicated"));

				paintingsList.add(paintings);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return paintingsList;

	}
	
    public Long getPaintingsTotalCount() {
    	PreparedStatement ps = null;
		String sql = "Select count(*) From paintings";
		
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return (Long)rs.getObject(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
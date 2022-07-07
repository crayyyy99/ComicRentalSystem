package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.DatabaseConnection;
import exception.InputException;
import model.ComicGenre;
import other.CheckInput;


public class ComicGenreController {

	public int getCount() throws ClassNotFoundException, SQLException {
		String sql = "select count(id) as count from comic_genre";
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		rs.next();

		return (rs.getInt("count"));
	}

	public void resetTable() throws ClassNotFoundException, SQLException {
		String sql = "truncate table comic_genre";

		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

		ps.executeUpdate();

		conn.close();

	}

	private int last_id;
	
	public int insert(ComicGenre comicGenre) throws ClassNotFoundException, SQLException, InputException {
		String sql = "insert into comic_genre (genre) values (?);";

		String genre = comicGenre.getGenre();
		
		CheckInput check = new CheckInput();
		if (check.isNull(genre))
			throw new InputException("Empty Field");
		if (check.isOverLimit(genre, 50))
			throw new InputException("Over Limit");
		if (genreExist(genre))
			throw new InputException("Duplicate Genre");
		
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, comicGenre.getGenre());

		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			last_id = rs.getInt(1);
		}
		conn.close();	
		return last_id;
	}

	public ArrayList<ComicGenre> selectWhere(String condition, int count, int offset)
			throws ClassNotFoundException, SQLException {
		ArrayList<ComicGenre> list = new ArrayList<ComicGenre>();
		String sql = "select * from comic_genre where %s ORDER BY id ASC LIMIT %d,%d;";
		sql = String.format(sql, condition, count, offset);

		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ComicGenre comicGenre = new ComicGenre();
			comicGenre.setId(rs.getInt("id"));
			comicGenre.setGenre(rs.getString("genre"));
			
			list.add(comicGenre);
		}
		conn.close();
		return list;
	}

	public int update(ComicGenre comicGenre) throws ClassNotFoundException, SQLException, InputException {
		String sql = "update comic_genre set genre = ? where id = ?";

		String genre = comicGenre.getGenre();

		CheckInput check = new CheckInput();
		if (check.isNull(genre))
			throw new InputException("Empty Field");
		if (check.isOverLimit(genre, 50))
			throw new InputException("Over Limit");
		if (genreExist(genre))
			throw new InputException("Duplicate Genre");
		
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setString(1, comicGenre.getGenre());
		ps.setInt(2, comicGenre.getId());

		int success = ps.executeUpdate();
		conn.close();

		return success;
	}

	public ArrayList<ComicGenre> selectAll(int count, int offset) throws ClassNotFoundException, SQLException {
		ArrayList<ComicGenre> list = new ArrayList<ComicGenre>();
		String sql = "select * from comic_genre ORDER BY id ASC LIMIT %d,%d;";
		sql = String.format(sql, count, offset);

		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ComicGenre comicGenre = new ComicGenre();
			comicGenre.setId(rs.getInt("id"));
			comicGenre.setGenre(rs.getString("genre"));
			
			list.add(comicGenre);
		}
		conn.close();
		return list;
	}

	public int delete(ComicGenre comicGenre) throws ClassNotFoundException, SQLException {
		String sql = "delete from comic_genre where id= ?;";
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, comicGenre.getId());

		int success = ps.executeUpdate();
		conn.close();
		return success;
	}

	public boolean genreExist(String genre) throws ClassNotFoundException, SQLException //passed
	{
		boolean found = false;
		String sql = "select exists (select * from comic_genre where genre = ?);";
		
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, genre);

		ResultSet rs = ps.executeQuery();
		rs.next();
		if(rs.getInt(1)==1)
			found = true;
		conn.close();
		return found;
	}
	
	public ArrayList<String> selectAllGenre(int count, int offset) throws ClassNotFoundException, SQLException {
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select * from comic_genre ORDER BY id ASC LIMIT %d,%d;";
		sql = String.format(sql, count, offset);

		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ComicGenre comicGenre = new ComicGenre();
			list.add(rs.getString("genre"));
			
		}
		conn.close();
		return list;
	}

}
	
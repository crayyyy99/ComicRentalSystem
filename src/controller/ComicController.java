package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.DatabaseConnection;
import exception.InputException;
import model.Comic;
import other.CheckInput;

public class ComicController {

	public int getCount() throws ClassNotFoundException, SQLException {
		String sql = "select count(id) as count from comic";
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		rs.next();

		return (rs.getInt("count"));
	}

	public void resetTable() throws ClassNotFoundException, SQLException {
		String sql = "truncate table comic";

		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

		ps.executeUpdate();

		conn.close();

	}

	private int last_id;
	
	public int insert(Comic comic) throws ClassNotFoundException, SQLException, InputException {
		String sql = "insert into comic (title, author, price, description, genre_id) values (?, ?, ?, ?, ?)";
		
		String title = comic.getTitle();
		String author = comic.getAuthor();
		String description = comic.getDescription();
		Double price = comic.getPrice();

		
		CheckInput check = new CheckInput();
		if(check.isNull(title)||check.isNull(author))
			throw new InputException("Empty Field");
		if(check.isOverLimit(title, 100)||check.isOverLimit(author, 100)||check.isOverLimit(description, 1000))
			throw new InputException("Over Limit");
		
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, comic.getTitle());
		ps.setString(2, comic.getAuthor());
		ps.setDouble(3, comic.getPrice());
		ps.setString(4, comic.getDescription());
		ps.setInt(5, comic.getGenreId());
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			last_id = rs.getInt(1);
		}
		conn.close();	
		return last_id;
	}

	public ArrayList<Comic> selectWhere(String condition, int count, int offset) throws ClassNotFoundException, SQLException
	{
		ArrayList<Comic> list = new ArrayList<Comic>();
        String sql = "select * from comic where %s ORDER BY id ASC LIMIT %d,%d;";
        sql = String.format(sql, condition, count, offset);
        
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	Comic comic = new Comic();
        	comic.setId(rs.getInt("id"));
        	comic.setTitle(rs.getString("title"));
        	comic.setAuthor(rs.getString("author"));
        	comic.setPrice(rs.getDouble("price"));
        	comic.setDescription(rs.getString("description"));
        	comic.setGenreId(rs.getInt("genre_id"));

        	list.add(comic);
        }
        conn.close();
		return list;
	}

	public int update(Comic comic) throws ClassNotFoundException, SQLException, InputException
	{
		String sql = "update comic set title = ?, author = ?, price = ?, description = ?, genre_id = ? where id = ?;";
		
		String title = comic.getTitle();
		String author = comic.getAuthor();
		String description = comic.getDescription();
		Double price = comic.getPrice();

		
		CheckInput check = new CheckInput();
		if(check.isNull(title)||check.isNull(author))
			throw new InputException("Empty Field");
		if(check.isOverLimit(title, 100)||check.isOverLimit(author, 100)||check.isOverLimit(description, 1000))
			throw new InputException("Over Limit");
		
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setString(1, comic.getTitle());
		ps.setString(2, comic.getAuthor());
		ps.setDouble(3, comic.getPrice());
		ps.setString(4, comic.getDescription());
		ps.setInt(5, comic.getGenreId());
		ps.setInt(6, comic.getId());
			
		int success = ps.executeUpdate();
		conn.close();
		
		return success;
	}
	
	public ArrayList<Comic> selectAll(int count, int offset) throws ClassNotFoundException, SQLException
	{
		ArrayList<Comic> list = new ArrayList<Comic>();
        String sql = "select * from comic ORDER BY id ASC LIMIT %d,%d;";
        sql = String.format(sql, count, offset);
        
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	Comic comic = new Comic();
        	comic.setId(rs.getInt("id"));
        	comic.setTitle(rs.getString("title"));
        	comic.setAuthor(rs.getString("author"));
        	comic.setPrice(rs.getDouble("price"));
        	comic.setDescription(rs.getString("description"));
        	comic.setGenreId(rs.getInt("genre_id"));

        	list.add(comic);
        }
        conn.close();
        return list;
	}
        
	public int delete(Comic comic) throws ClassNotFoundException, SQLException
	{
		String sql = "delete from comic where id= ?;";
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, comic.getId());
        
        int success = ps.executeUpdate();
        conn.close();
		return success;
	}
	
	public boolean idExist(int id) throws ClassNotFoundException, SQLException {
		boolean found = false;
		
		String sql = "select exists(select * from comic where id = ?)";

		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
		rs.next();
		if (rs.getInt(1) == 1)
			found = true;

		conn.close();
		return found;
	}
}

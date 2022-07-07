package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseConnection;
import exception.InputException;
import model.ComicCopy;

public class ComicCopyController {

	public int getCount() throws ClassNotFoundException, SQLException {
		String sql = "select count(id) as count from comic_copy";
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		rs.next();

		return (rs.getInt("count"));
	}

	public void resetTable() throws ClassNotFoundException, SQLException {
		String sql = "truncate table comic_copy";

		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

		ps.executeUpdate();

		conn.close();

	}

	public int insert(ComicCopy cp) throws ClassNotFoundException, SQLException, InputException {		
		String sql = "insert into comic_copy (`condition`, comic_id, copy_id) values (?, ?, ?);";
		
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		
		ps.setString(1, "Available");
		ps.setInt(2, cp.getComicId());
		ps.setInt(3, cp.getCopyId());
		
		int success = ps.executeUpdate();
		conn.close();
		
		return success;
	}

	public ArrayList<ComicCopy> selectWhere(String condition, int count, int offset) throws ClassNotFoundException, SQLException
	{
		ArrayList<ComicCopy> list = new ArrayList<ComicCopy>();
        String sql = "select * from comic_copy where %s ORDER BY id ASC LIMIT %d,%d;";
        sql = String.format(sql, condition, count, offset);
        
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	ComicCopy cp = new ComicCopy();
        	cp.setId(rs.getInt("id"));
        	cp.setCondition(rs.getString("condition"));
        	cp.setComicId(rs.getInt("comic_id"));
        	cp.setCopyId(rs.getInt("copy_id"));

        	list.add(cp);
        }
        conn.close();
		return list;
	}

	public int update(ComicCopy cp) throws ClassNotFoundException, SQLException, InputException
	{
		String sql = "update comic_copy set `condition` = ?, comic_id = ?, copy_id = ?  where id = ?;";
		
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setString(1, cp.getCondition());
		ps.setInt(2, cp.getComicId());
		ps.setInt(3,  cp.getCopyId());
		ps.setInt(4, cp.getId());
			
		int success = ps.executeUpdate();
		conn.close();
		
		return success;
	}
	
	public ArrayList<ComicCopy> selectAll(int count, int offset) throws ClassNotFoundException, SQLException
	{
		ArrayList<ComicCopy> list = new ArrayList<ComicCopy>();
        String sql = "select * from comic_copy ORDER BY id ASC LIMIT %d,%d;";
        sql = String.format(sql, count, offset);
        
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	ComicCopy cp = new ComicCopy();
        	cp.setId(rs.getInt("id"));
        	cp.setCondition(rs.getString("condition"));
        	cp.setComicId(rs.getInt("comic_id"));
        	cp.setCopyId(rs.getInt("copy_id"));

        	list.add(cp);
        }
        conn.close();
        return list;
	}
        
	public int delete(ComicCopy cp) throws ClassNotFoundException, SQLException
	{
		String sql = "delete from comic_copy where id= ?;";
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, cp.getId());
        
        int success = ps.executeUpdate();
        conn.close();
		return success;
	}

	public ArrayList<ComicCopy> selectWhereList(String condition, int count, int offset) throws ClassNotFoundException, SQLException
	{	
		ArrayList<ComicCopy> list = new ArrayList<ComicCopy>();
        String sql = "select comic_copy.id, comic_copy.comic_id as comicId, comic_copy.copy_id as copyId, comic_genre.id as genreId, comic.title,comic.description, comic_genre.genre, comic.author, comic_copy.`condition`, comic.price"
        		+ " from comic inner join comic_copy on (comic.id = comic_copy.comic_id)  "
        		+ "inner join comic_genre on (comic.genre_id = comic_genre.id) where %s order by comic_copy.comic_id LIMIT %d,%d;";
        sql = String.format(sql, condition, count, offset);
        
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	ComicCopy cp = new ComicCopy();
        	cp.setId(rs.getInt("comic_copy.id"));
        	cp.setCopyId(rs.getInt("copyId"));
        	cp.setTitle(rs.getString("title"));
        	cp.setGenre(rs.getString("genre"));
        	cp.setGenreId(rs.getInt("genreId"));
        	cp.setAuthor(rs.getString("author"));
        	cp.setCondition(rs.getString("condition"));
        	cp.setDescription(rs.getString("description"));
        	cp.setPrice(rs.getDouble("price"));
        	cp.setComicId(rs.getInt("comicId"));
        	
        	list.add(cp);
        }
        conn.close();
		return list;
	}
	
	public ArrayList<ComicCopy> selectAllList(int count, int offset) throws ClassNotFoundException, SQLException
	{
		ArrayList<ComicCopy> list = new ArrayList<ComicCopy>();
		
        String sql = "select comic_copy.id, comic_copy.comic_id as comicId, comic_copy.copy_id as copyId, comic_genre.id as genreId, comic.title,comic.description, comic_genre.genre, comic.author, comic_copy.`condition`, comic.price"
        		+ " from comic inner join comic_copy on (comic.id = comic_copy.comic_id)  "
        		+ "inner join comic_genre on (comic.genre_id = comic_genre.id) order by comic_copy.comic_id LIMIT %d,%d;";

        sql = String.format(sql, count, offset);
        
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	ComicCopy cp = new ComicCopy();
        	cp.setId(rs.getInt("comic_copy.id"));
        	cp.setCopyId(rs.getInt("copyId"));
        	cp.setTitle(rs.getString("title"));
        	cp.setGenre(rs.getString("genre"));
        	cp.setGenreId(rs.getInt("genreId"));
        	cp.setAuthor(rs.getString("author"));
        	cp.setCondition(rs.getString("condition"));
        	cp.setDescription(rs.getString("description"));
        	cp.setPrice(rs.getDouble("price"));
        	cp.setComicId(rs.getInt("comicId"));

        	list.add(cp);
        }
        conn.close();
        return list;
	}
	
	public ArrayList<ComicCopy> selectWhereCombine(String condition, int count, int offset) throws ClassNotFoundException, SQLException
	{	
		ArrayList<ComicCopy> list = new ArrayList<ComicCopy>();
        String sql = "select * from comic inner join comic_copy on (comic.id = comic_copy.comic_id) inner join "
        		+ "comic_genre on (comic.genre_id = comic_genre.id) where %s LIMIT %d,%d;";
        sql = String.format(sql, condition, count, offset);
        
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	ComicCopy cc = new ComicCopy();
        	cc.setId(rs.getInt("id1"));
        	cc.setCopyId(rs.getInt("copy_id"));
        	cc.setTitle(rs.getString("title"));
        	cc.setGenre(rs.getString("genre"));
        	cc.setGenreId(rs.getInt("id2"));
        	cc.setAuthor(rs.getString("author"));
        	cc.setCondition(rs.getString("condition"));
        	cc.setDescription(rs.getString("description"));
        	cc.setPrice(rs.getDouble("price"));
        	cc.setComicId(rs.getInt("id"));
        	
        	list.add(cc);
        }
        conn.close();
		return list;
	}
	
	public ArrayList<ComicCopy> selectWhereFindRentalList(String condition, int count, int offset) throws ClassNotFoundException, SQLException
	{	
		ArrayList<ComicCopy> list = new ArrayList<ComicCopy>();
        String sql = "select * from rental_copy inner join comic_copy on (rental_copy.comic_copy_id = comic_copy.id)"
        		+ "inner join comic  on (comic.id = comic_copy.comic_id)inner join comic_genre on (comic.genre_id = comic_genre.id)"
        		+ "inner join rental on (rental.id = rental_copy.rental_id) inner join user on (rental.user_id = `user`.id)"
        		+ "where %s LIMIT %d,%d;";
        sql = String.format(sql, condition, count, offset);
        
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	ComicCopy cc = new ComicCopy();
        	cc.setId(rs.getInt("comic_copy_id"));
        	cc.setCopyId(rs.getInt("copy_id"));
        	cc.setTitle(rs.getString("title"));
        	cc.setGenre(rs.getString("genre"));
        	cc.setGenreId(rs.getInt("genre_id"));
        	cc.setAuthor(rs.getString("author"));
        	cc.setCondition(rs.getString("condition"));
        	cc.setDescription(rs.getString("description"));
        	cc.setPrice(rs.getDouble("price"));
        	cc.setComicId(rs.getInt("comic_id"));
        	
        	list.add(cc);
        }
        conn.close();
		return list;
	}
	
	public int getCopyCount(int comicId) throws ClassNotFoundException, SQLException {
		String sql = "select count(comic_copy.comic_id) as count from comic_copy where comic_copy.comic_id = ?";
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
        ps.setInt(1, comicId);
        
		ResultSet rs = ps.executeQuery();
		rs.next();

		return (rs.getInt("count"));
	}

	public ArrayList<ComicCopy> selectBestSeller(int count, int offset) throws ClassNotFoundException, SQLException
	{
		ArrayList<ComicCopy> list = new ArrayList<ComicCopy>();
        String sql = "select comic_copy.comic_id as comicId, comic.title as comicTitle, count(*)as quantity, comic.author, genre "
        		+ "from comic inner join comic_copy on (comic.id = comic_copy.comic_id) "
        		+ "inner join comic_genre on (comic.genre_id = comic_genre.id) GROUP BY comic_copy.comic_id order by quantity DESC;";
        sql = String.format(sql, count, offset);
        
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	ComicCopy cp = new ComicCopy();
        	cp.setComicId(rs.getInt("comicId"));
        	cp.setTitle(rs.getString("comicTitle"));
        	cp.setGenre(rs.getString("genre"));
        	cp.setAuthor(rs.getString("author"));
        	cp.setId(rs.getInt("quantity"));
        	list.add(cp);
        }
        conn.close();
        return list;
	}
	
}

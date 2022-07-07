package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import database.DatabaseConnection;
import exception.InputException;
import model.ComicCopy;
import model.RentalCopy;

public class RentalCopyController {

	public int getCount() throws ClassNotFoundException, SQLException
	{
		String sql = "select count(id) as count from rental_copy";
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return (rs.getInt("count"));
	}

	public void resetTable() throws ClassNotFoundException, SQLException {
		String sql = "truncate table rental_copy";

		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

		ps.executeUpdate();

		conn.close();

	}

	public int insert(RentalCopy rental_copy) throws ClassNotFoundException, SQLException, InputException
	{
		String sql = "insert into rental_copy (id, comic_copy_id, rental_id) values (?, ?, ?)";
		
		int Id = rental_copy.getId();
		int comicCopyId = rental_copy.getComicCopyId();
		int rentalId = rental_copy.getRentalId();

		
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		
		ps.setInt(1, rental_copy.getId());
		ps.setInt(2, rental_copy.getComicCopyId());
		ps.setInt(3, rental_copy.getRentalId());
		int success = ps.executeUpdate();
		conn.close();
		
		return success;
	}

	public ArrayList<RentalCopy> selectWhere(String condition, int count, int offset) throws ClassNotFoundException, SQLException
	{
		ArrayList<RentalCopy> list = new ArrayList<RentalCopy>();
	    String sql = "select * from rental_copy where %s ORDER BY id ASC LIMIT %d,%d;";
	    sql = String.format(sql, condition, count, offset);
	    
	    Connection conn = DatabaseConnection.doConnection();
	    PreparedStatement ps = conn.prepareStatement(sql);
	    
	    ResultSet rs = ps.executeQuery();
	    while(rs.next())
	    {
	    	RentalCopy rental_Copy = new RentalCopy();
	    	rental_Copy.setId(rs.getInt("id"));
	    	rental_Copy.setComicCopyId(rs.getInt("comic_copy_id"));
	    	rental_Copy.setRentalId(rs.getInt("rental_id"));
	    	
	    	list.add(rental_Copy);
	    }
	    conn.close();
		return list;

	}

	public int update(RentalCopy rental_copy) throws ClassNotFoundException, SQLException, InputException
	{
		String sql = "update rental_copy set comicCopyId = ?, rental_id = ? where id = ?";
		
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		
		ps.setInt(1, rental_copy.getComicCopyId());
		ps.setInt(2, rental_copy.getRentalId());
		ps.setInt(3, rental_copy.getId());
		
		int success = ps.executeUpdate();
		conn.close();
		
		return success;
	}
	
	public ArrayList<RentalCopy> selectAll(int count, int offset) throws ClassNotFoundException, SQLException
	{
		ArrayList<RentalCopy> list = new ArrayList<RentalCopy>();
        String sql = "select * from rental_copy ORDER BY id ASC LIMIT %d,%d;";
        sql = String.format(sql, count, offset);
        
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	RentalCopy rental_Copy = new RentalCopy();
	    	rental_Copy.setId(rs.getInt("id"));
	    	rental_Copy.setComicCopyId(rs.getInt("comic_copy_id"));
	    	rental_Copy.setRentalId(rs.getInt("rental_id"));

        	list.add(rental_Copy);
        }
        conn.close();
        return list;
	}
        
	public int delete(RentalCopy rental_copy) throws ClassNotFoundException, SQLException
	{
		String sql = "delete from rental_copy where id= ?;";
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, rental_copy.getId());
        
        int success = ps.executeUpdate();
        conn.close();
		return success;
	}
	
	public ArrayList<RentalCopy> selectAllCusRental(int count, int offset) throws ClassNotFoundException, SQLException
	{
		ArrayList<RentalCopy> list = new ArrayList<RentalCopy>();
	    String sql = "select rental_copy.id, rental_copy.rental_id, rental_copy.comic_copy_id,  rental.user_id, `user`.`name`, rent_date, count(comic_copy_id) as quantity"
	    		+ ", expected_date, return_date, rent_fee, overdue_fee, `status` from rental inner join rental_copy on (rental_copy.rental_id = rental.id)"
	    		+ "inner join `user`on (rental.user_id = `user`.id) inner join comic_copy on (rental_copy.comic_copy_id = comic_copy.id) "
	    		+ "inner join comic on (comic.id = comic_copy.comic_id) inner join comic_genre on (comic.genre_id = comic_genre.id) "
	    		+ " group by rental_copy.rental_id order by status DESC, rental_id ASC LIMIT %d, %d";
	    sql = String.format(sql, count, offset);
	    
	    Connection conn = DatabaseConnection.doConnection();
	    PreparedStatement ps = conn.prepareStatement(sql);
	    
	    ResultSet rs = ps.executeQuery();
	    while(rs.next())
	    {
	      	RentalCopy rp = new RentalCopy();
        	rp.setId(rs.getInt("rental_copy.id"));
        	rp.setRentalId(rs.getInt("rental_id"));
        	rp.setUserId(rs.getInt("rental.user_id"));      	
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        	rp.setRentDate(LocalDateTime.parse(rs.getString("rent_date"), formatter));
        	rp.setExpectedDate(LocalDateTime.parse(rs.getString("expected_date"), formatter));
        	rp.setReturnDate(LocalDateTime.parse(rs.getString("return_date"), formatter));
			rp.setQuantity(rs.getInt("quantity"));
        	rp.setRentFee(rs.getDouble("rent_fee"));
        	rp.setOverdueFee(rs.getDouble("overdue_fee"));
        	rp.setStatus(rs.getString("status"));
		   	
	    	list.add(rp);
	    }
	    conn.close();
		return list;

	}
	
	public ArrayList<RentalCopy> selectWhereCusRental(String condition, int count, int offset) throws ClassNotFoundException, SQLException
	{
		ArrayList<RentalCopy> list = new ArrayList<RentalCopy>();
	    String sql = "select rental_copy.id, rental_copy.rental_id, rental_copy.comic_copy_id,  rental.user_id, `user`.`name`, rent_date, count(comic_copy_id) as quantity"
	    		+ ", expected_date, return_date, rent_fee, overdue_fee, `status` from rental inner join rental_copy on (rental_copy.rental_id = rental.id)"
	    		+ "inner join `user`on (rental.user_id = `user`.id) inner join comic_copy on (rental_copy.comic_copy_id = comic_copy.id) "
	    		+ "inner join comic on (comic.id = comic_copy.comic_id) inner join comic_genre on (comic.genre_id = comic_genre.id) where %s"
	    		+ " group by rental_copy.rental_id order by status DESC, rental_id ASC LIMIT %d, %d";
	    sql = String.format(sql, condition, count, offset);
	    
	    Connection conn = DatabaseConnection.doConnection();
	    PreparedStatement ps = conn.prepareStatement(sql);
	    
	    ResultSet rs = ps.executeQuery();
	    while(rs.next())
	    {
	      	RentalCopy rp = new RentalCopy();
        	rp.setId(rs.getInt("rental_copy.id"));
        	rp.setRentalId(rs.getInt("rental_id"));
        	rp.setUserId(rs.getInt("rental.user_id"));      	
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        	rp.setRentDate(LocalDateTime.parse(rs.getString("rent_date"), formatter));
        	rp.setExpectedDate(LocalDateTime.parse(rs.getString("expected_date"), formatter));
        	rp.setReturnDate(LocalDateTime.parse(rs.getString("return_date"), formatter));
			rp.setQuantity(rs.getInt("quantity"));
        	rp.setRentFee(rs.getDouble("rent_fee"));
        	rp.setOverdueFee(rs.getDouble("overdue_fee"));
        	rp.setStatus(rs.getString("status"));
		   	
	    	list.add(rp);
	    }
	    conn.close();
		return list;

	}
	
	public ArrayList<RentalCopy> selectDailySales(int count, int offset, String year, String month, String day) throws ClassNotFoundException, SQLException
	{
		ArrayList<RentalCopy> list = new ArrayList<RentalCopy>();
		
        String sql = "Select rent_date, SUM(rent_fee + overdue_fee) As Sales From rental Where rent_date LIKE '%" + year + "-" + month  + "-" + day + 
        		"%' GROUP BY rent_date";
        
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	RentalCopy rp = new RentalCopy();
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        	rp.setRentDate(LocalDateTime.parse(rs.getString("rent_date"), formatter));
        	rp.setRentFee(rs.getDouble("Sales"));

        	list.add(rp);
        }
        conn.close();
        return list;
	}
	
	public double getTotalDailySales(int count, int offset, String year, String month, String day) throws ClassNotFoundException, SQLException {
		  String sql = "Select SUM(rent_fee + overdue_fee) As Sales From rental Where rent_date LIKE '%" + year + "-" + month  + "-" + day + 
					"%' group by date(rent_date)";
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
       
		ResultSet rs = ps.executeQuery();
		double sales = 0.0;
		while(rs.next())
			sales = rs.getDouble("Sales");

		return sales;

	}
	
	
	public ArrayList<RentalCopy> selectMonthlySales(int count, int offset, String year, String month) throws ClassNotFoundException, SQLException
	{
		ArrayList<RentalCopy> list = new ArrayList<RentalCopy>();
		
        String sql = "Select rent_date, SUM(rent_fee + overdue_fee) As Sales, count(rent_date) as count From rental Where rent_date LIKE '%" + year + "-" + month + "-%' "
        		+ "group by day(rent_date)";
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	RentalCopy rp = new RentalCopy();
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        	rp.setRentDate(LocalDateTime.parse(rs.getString("rent_date"), formatter));
        	rp.setRentFee(rs.getDouble("Sales"));
        	rp.setQuantity(rs.getInt("count"));

        	list.add(rp);
        }
        conn.close();
        return list;
	}

	public double getTotalMonthlySales(int count, int offset, String year, String month) throws ClassNotFoundException, SQLException {
		  String sql ="Select SUM(rent_fee + overdue_fee) As Sales From rental Where rent_date LIKE '%" +  year + "-" + month  + "%' "
				  + "group by month(rent_date)";
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
     
		ResultSet rs = ps.executeQuery();
		rs.next();

		return (rs.getDouble("Sales"));
	}
	
	public ArrayList<RentalCopy> selectAnnuallySales(int count, int offset, String year) throws ClassNotFoundException, SQLException
	{
		ArrayList<RentalCopy> list = new ArrayList<RentalCopy>();
		
        String sql = "Select rent_date, SUM(rent_fee + overdue_fee) As Sales, count(rent_date) as count From rental Where rent_date LIKE '%" + year + "%' "
        		+ "group by month(rent_date)";
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	RentalCopy rp = new RentalCopy();
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        	rp.setRentDate(LocalDateTime.parse(rs.getString("rent_date"), formatter));
        	rp.setRentFee(rs.getDouble("Sales"));
        	rp.setQuantity(rs.getInt("count"));

        	list.add(rp);
        }
        conn.close();
        return list;
	}
	

	public double getTotalAnnuallySales(String year) throws ClassNotFoundException, SQLException {
		  String sql ="Select SUM(rent_fee + overdue_fee) As Sales From rental Where rent_date LIKE '%" +  year + "%' "
				  + "group by year(rent_date)";
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
     
		ResultSet rs = ps.executeQuery();
		rs.next();

		return (rs.getDouble("Sales"));
	}
	
	public int getMonthlySalesCount(String year, String month) throws ClassNotFoundException, SQLException
	{
		String sql = "select count(rent_date) as count from rental where rent_date like '%" +  year + "-" + month  + "%'";
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return (rs.getInt("count"));
	}
	
	public int getAnnuallySalesCount(String year) throws ClassNotFoundException, SQLException
	{
		String sql = "select count(rent_date) as count from rental where rent_date like '%" +  year + "%' group by year(rent_date)";
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return (rs.getInt("count"));
	}



}

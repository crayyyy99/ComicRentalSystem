package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import database.DatabaseConnection;
import exception.InputException;
import model.Comic;
import model.Rental;
import other.CheckInput;

public class RentalController {

	public int getCount() throws ClassNotFoundException, SQLException {
		String sql = "select count(id) as count from rental";
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		rs.next();

		return (rs.getInt("count"));
	}

	public void resetTable() throws ClassNotFoundException, SQLException {
		String sql = "truncate table rental";

		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

		ps.executeUpdate();

		conn.close();

	}

	private int last_id;
	
	public int insert(Rental rental) throws ClassNotFoundException, SQLException, InputException {
		String sql = "insert into rental (user_id, rent_date, expected_Date, return_date, rent_fee, overdue_fee, "
				+ " status) values (?, ?, ?, ?, ?, ?, ?)";
		rental.setStatus("Renting");
		
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);		
		
		ps.setInt(1, rental.getUserId());
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		rental.setRentDate(currentDateTime);
		rental.setExpectedDate(currentDateTime.plusDays(7));
		rental.setReturnDate(currentDateTime.minusYears(1));
		
		ps.setString(2, rental.getRentDate().format(formatter));
		ps.setString(3, rental.getExpectedDate().format(formatter));
		ps.setString(4, rental.getReturnDate().format(formatter));
		ps.setDouble(5, rental.getRentFee());
		ps.setDouble(6, rental.getOverdueFee());
		ps.setString(7, rental.getStatus());
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			last_id = rs.getInt(1);
		}
		conn.close();	
		return last_id;
	}

	public ArrayList<Rental> selectWhere(String condition, int count, int offset) throws ClassNotFoundException, SQLException
	{
		ArrayList<Rental> list = new ArrayList<Rental>();
	    String sql = "select * from rental where %s ORDER BY id ASC LIMIT %d,%d;";
	    sql = String.format(sql, condition, count, offset);
	    
	    Connection conn = DatabaseConnection.doConnection();
	    PreparedStatement ps = conn.prepareStatement(sql);
	    
	    ResultSet rs = ps.executeQuery();
	    while(rs.next())
	    {
	    	Rental rental = new Rental();
	    	rental.setId(rs.getInt("id"));
	    	rental.setUserId(rs.getInt("user_id")); 
	    	
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    	rental.setRentDate(LocalDateTime.parse(rs.getString("rent_date"), formatter));
	    	rental.setExpectedDate(LocalDateTime.parse(rs.getString("expected_date"), formatter));
	    	rental.setReturnDate(LocalDateTime.parse(rs.getString("return_date"), formatter));
	    	rental.setRentFee(rs.getDouble("rent_fee"));
	    	rental.setOverdueFee(rs.getDouble("overdue_fee"));
	    	rental.setStatus(rs.getString("status"));

	    	list.add(rental);
	    }
	    conn.close();
		return list;

	}

	public int update(Rental rental) throws ClassNotFoundException, SQLException, InputException
	{
		String sql = "update rental set user_id = ?, rent_date = ?, expected_date = ?, return_date = ?, rent_fee = ?, "
				+ "overdue_fee = ?, status = ? where id = ? ";
		
		rental.setStatus("Completed");
		
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		
		ps.setInt(1, rental.getUserId());
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		rental.setReturnDate(currentDateTime);
		ps.setString(2, rental.getRentDate().format(formatter));
		ps.setString(3, rental.getExpectedDate().format(formatter));
		ps.setString(4, rental.getReturnDate().format(formatter));
		ps.setDouble(5, rental.getRentFee());
		ps.setDouble(6, rental.getOverdueFee());
		ps.setString(7, rental.getStatus());
		ps.setInt(8, rental.getId());
		int success = ps.executeUpdate();

		conn.close();
		
		return success;
	}
	
	public ArrayList<Rental> selectAll(int count, int offset) throws ClassNotFoundException, SQLException
	{
		ArrayList<Rental> list = new ArrayList<Rental>();
        String sql = "select * from rental ORDER BY id ASC LIMIT %d,%d;";
        sql = String.format(sql, count, offset);
        
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	Rental rental = new Rental();
        	rental.setId(rs.getInt("id"));
        	rental.setUserId(rs.getInt("userId")); 
        	
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        	rental.setRentDate(LocalDateTime.parse(rs.getString("rent_date"), formatter));
        	rental.setExpectedDate(LocalDateTime.parse(rs.getString("expected_date"), formatter));
	    	rental.setReturnDate(LocalDateTime.parse(rs.getString("return_date"), formatter));
        	rental.setRentFee(rs.getDouble("rent_fee"));
        	rental.setOverdueFee(rs.getDouble("overdue_fee"));
        	rental.setStatus(rs.getString("status"));

        	list.add(rental);
        }
        conn.close();
        return list;
	}
        
	public int delete(Rental rental) throws ClassNotFoundException, SQLException
	{
		String sql = "delete from rental where id= ?;";
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, rental.getId());
        
        int success = ps.executeUpdate();
        conn.close();
		return success;
	}
	

	
}

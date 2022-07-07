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
import model.User;
import other.CheckInput;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserController {

	public int getCount() throws ClassNotFoundException, SQLException {
		String sql = "select count(id) as count from user";
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		rs.next();

		return (rs.getInt("count"));
	}

	public void resetTable() throws ClassNotFoundException, SQLException {
		String sql = "truncate table user";

		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

		ps.executeUpdate();

		conn.close();

	}

	public int insert(User user) throws ClassNotFoundException, SQLException, InputException {
		String sql = "insert into user (username, password, name, icNo, email, phoneNo, "
				+ " regDate, permission) values (?, ?, ?, ?, ?, ?, ?, ?)";

		String username = user.getUsername();
		String password = user.getPassword();
		String name = user.getName();
		String icNo = user.getIcNo();
		String email = user.getEmail();
		String phoneNo = user.getPhoneNo();

		CheckInput check = new CheckInput();
		if (check.isNull(username) || check.isNull(password) || check.isNull(name) || check.isNull(icNo)
				|| check.isNull(email) || check.isNull(phoneNo))
			throw new InputException("Empty Field");
		if (check.isOverLimit(username, 25) || check.isOverLimit(password, 25) || check.isOverLimit(name, 100)
				|| check.isOverLimit(icNo, 12) || check.isOverLimit(email, 100) || check.isOverLimit(phoneNo, 20))
			throw new InputException("Over Limit");
		if (check.isValidPassword(password))
			throw new InputException("Invalid Password");
		if (check.isValidIc(icNo))
			throw new InputException("Invalid ICNo");
		if (check.isValidEmail(email))
			throw new InputException("Invalid Email");
		if (check.isValidPhoneNo(phoneNo))
			throw new InputException("Invalid PhoneNo");
		if (usernameExist(username))
			throw new InputException("Duplicate Username");

		if (getCount() == 0)
			user.setPermission("Admin");
		else
			user.setPermission("Customer");

		user.setPassword(getSHA(user.getPassword()));
		
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);

		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getName());
		ps.setString(4, user.getIcNo());
		ps.setString(5, user.getEmail());
		ps.setString(6, user.getPhoneNo());

		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		user.setRegDate(currentDateTime);
		ps.setString(7, user.getRegDate().format(formatter));

		ps.setString(8, user.getPermission());

		int success = ps.executeUpdate();
		conn.close();

		return success;
	}

	public ArrayList<User> selectWhere(String condition, int count, int offset)
			throws ClassNotFoundException, SQLException {
		ArrayList<User> list = new ArrayList<User>();
		String sql = "select * from user where %s ORDER BY id ASC LIMIT %d,%d;";
		sql = String.format(sql, condition, count, offset);

		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setIcNo(rs.getString("icNo"));
			user.setEmail(rs.getString("email"));
			user.setPhoneNo(rs.getString("phoneNo"));

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			user.setRegDate(LocalDateTime.parse(rs.getString("regDate"), formatter));
			user.setPermission(rs.getString("permission"));

			list.add(user);
		}
		conn.close();
		return list;
	}

	public int update(User user) throws ClassNotFoundException, SQLException, InputException {
		String sql = "update user set username = ?, password = ?, name = ?, icNo = ?, email = ?, phoneNo = ?, "
				+ "regDate = ?, permission = ? where id = ?";

		String username = user.getUsername();
		String password = user.getPassword();
		String name = user.getName();
		String icNo = user.getIcNo();
		String email = user.getEmail();
		String phoneNo = user.getPhoneNo();

		CheckInput check = new CheckInput();
		if (check.isNull(username) || check.isNull(name) || check.isNull(icNo)
				|| check.isNull(email) || check.isNull(phoneNo))
			throw new InputException("Empty Field");
		if (check.isOverLimit(username, 25) || check.isOverLimit(password, 256) || check.isOverLimit(name, 100)
				|| check.isOverLimit(icNo, 12) || check.isOverLimit(email, 100) || check.isOverLimit(phoneNo, 20))
			throw new InputException("Over Limit");

		if (check.isValidIc(icNo))
			throw new InputException("Invalid ICNo");
		if (check.isValidPhoneNo(phoneNo))
			throw new InputException("Invalid PhoneNo");
		if (check.isValidEmail(email))
			throw new InputException("Invalid Email");

		if(!check.isNull(user.getPassword()))
		{
			if (check.isValidPassword(password))
			throw new InputException("Invalid Password");
		user.setPassword(getSHA(user.getPassword()));
		}	
		else
			user.setPassword(getOldPassword(user.getId()));
		
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getName());
		ps.setString(4, user.getIcNo());
		ps.setString(5, user.getEmail());
		ps.setString(6, user.getPhoneNo());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ps.setString(7, user.getRegDate().format(formatter));
		ps.setString(8, user.getPermission());
		ps.setInt(9, user.getId());

		int success = ps.executeUpdate();
		conn.close();

		return success;
	}

	public ArrayList<User> selectAll(int count, int offset) throws ClassNotFoundException, SQLException {
		ArrayList<User> list = new ArrayList<User>();
		String sql = "select * from user ORDER BY id ASC LIMIT %d,%d;";
		sql = String.format(sql, count, offset);

		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setName(rs.getString("name"));
			user.setIcNo(rs.getString("icNo"));
			user.setEmail(rs.getString("email"));
			user.setPhoneNo(rs.getString("phoneNo"));

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			user.setRegDate(LocalDateTime.parse(rs.getString("regDate"), formatter));
			user.setPermission(rs.getString("permission"));

			list.add(user);
		}
		conn.close();
		return list;
	}

	public int delete(User user) throws ClassNotFoundException, SQLException {
		String sql = "delete from user where id= ?;";
		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, user.getId());

		int success = ps.executeUpdate();
		conn.close();
		return success;
	}

	public boolean usernameExist(String username) throws ClassNotFoundException, SQLException {
		boolean found = false;
		
		String sql = "select exists(select * from user where username = ?)";

		Connection conn = DatabaseConnection.doConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);

		ResultSet rs = ps.executeQuery();
		rs.next();
		if (rs.getInt(1) == 1)
			found = true;

		conn.close();
		return found;
	}

	public static String getSHA(String input) {
		try {
			// Static getInstance method is called with hashing SHA
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			// digest() method called
			// to calculate message digest of an input
			// and return array of byte
			byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));

			// Convert byte array into signum representation
			BigInteger number = new BigInteger(1, hash);

			// Convert message digest into hex value
			StringBuilder hashtext = new StringBuilder(number.toString(16));

			// Pad with leading zeros
			while (hashtext.length() < 32) {
				hashtext.insert(0, '0');
			}

			return hashtext.toString();
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			System.out.println("Exception thrown for incorrect algorithm: " + e);
			return null;
		}
	}

	public boolean validateUserLogin(String username, String password) throws ClassNotFoundException, SQLException {
		boolean found = false;
		
		String hashedPassword = getSHA(password);

		String sql = "select exists(select id from user where username = ? and password = ?);";

		Connection con = DatabaseConnection.doConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, hashedPassword);

		ResultSet rs = ps.executeQuery();
		rs.next();
		if (rs.getInt(1) == 1)
			found = true;

		con.close();
		return found;
	}
	
	public boolean validateAdminLogin(String username, String password) throws ClassNotFoundException, SQLException {
		boolean found = false;
		String hashedPassword = getSHA(password);
		String sql = "select exists(select id from user where username = ? and password = ? and permission = ?);";

		Connection con = DatabaseConnection.doConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, hashedPassword);
		ps.setString(3, "admin");
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		if (rs.getInt(1) == 1)
			found = true;

		con.close();
		return found;
	}

	public String getOldPassword(int id) throws ClassNotFoundException, SQLException {
		String hashedPassword = "";
		String sql = "select password from user where id = ?;";

		Connection con = DatabaseConnection.doConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			hashedPassword = rs.getString("password");
		}
		con.close();
		return hashedPassword;
	}
}


	// testing purpose
//	public static void main(String[] args) {
//		UserController controller = new UserController();
//		try {
//			controller.resetTable();
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	
//}
//		try {
////			//test add user
////			User data1 = new User();
////			data1.setId(1);
////			data1.setUsername("test");
////			data1.setPassword("12345678");
////			data1.setName("Test1");	
////			data1.setIcNo("575463443354");			
////			data1.setEmail("testavc@");
////			data1.setPhoneNo("25257858");
////			data1.setPermission("Admin");
////			int number = controller.insert(data1);
////			System.out.println(number);
////			
////			//test update user
////
////			User data2 = controller.selectWhere("id = 2", 0, 1).get(0);
////			System.out.println(data2.getName());
////			data2.setName("AA");	
////			data2.setPermission("b");
////			int number2 = controller.update(data2);
////			System.out.println(number2);
////			
////			//test view all user
////			for(User data3 : controller.selectAll(0,10))
////			{		
////				System.out.println("ID: "+data3.getId());
////				System.out.println("Username: "+data3.getUsername());
////				System.out.println("Password: "+data3.getPassword());
////				System.out.println("Name: "+data3.getName());
////				System.out.println("IC: "+data3.getIcNo());
////				System.out.println("Email: "+data3.getEmail());
////				System.out.println("Contact No: "+data3.getPhoneNo());
////				System.out.println("Reg Date: "+data3.getRegDate());
////				System.out.println("Permission: "+data3.getPermission());
////				System.out.println("");
////			}
////			
//////			//test delete user
//////			//controller.delete(data1);
////			
//			//test count
//			controller.resetTable();
//			//System.out.println(controller.getCount());
//		}
//		catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InputException e) {
//			// TODO Auto-generated catch block
//			e.displayMessage();

//		}
//	}
//}

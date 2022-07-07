package model;

import java.time.LocalDateTime;

public class Rental {
	private int id;
	protected int userId;
	protected LocalDateTime rentDate;
	protected LocalDateTime expectedDate;
	protected LocalDateTime returnDate;
	protected double rentFee;
	protected double overdueFee;
	protected String status;
	protected int quantity;
	
	public Rental() {}
			
	
	public int getId() {
    	return id;
	}
	public int getUserId() {
    	return userId;
	}
	public LocalDateTime getRentDate() {
    	return rentDate;
	}
	public LocalDateTime getExpectedDate() {
    	return expectedDate;
	}
	public LocalDateTime getReturnDate() {
    	return returnDate;
	}
	public double getRentFee() {
    	return rentFee;
	}
	public double getOverdueFee() {
    	return overdueFee;
	}
	public String getStatus() {
    	return status;
	}
	public int getQuantity() {
    	return quantity;
	}

	public void setId(int id) {
    	this.id = id;
	}
	public void setUserId(int userId) {
    	this.userId = userId;
	}
	public void setRentDate(LocalDateTime rentDate) {
    	this.rentDate = rentDate;
	}
	public void setExpectedDate(LocalDateTime expectedDate) {
    	this.expectedDate = expectedDate;
	}
	public void setReturnDate(LocalDateTime returnDate) {
    	this.returnDate = returnDate;
	}
	public void setRentFee(double rentFee) {
    	this.rentFee = rentFee;
	}
	public void setOverdueFee(double overdueFee) {
    	this.overdueFee = overdueFee;
	}
	public void setStatus(String status) {
    	this.status = status;
	}
	public void setQuantity(int quantity) {
    	this.quantity = quantity;
	}
}

package model;

public class RentalCopy extends Rental {
	private int id;
	private int comicCopyId;
	private int rentalId;
	
	public RentalCopy(){super();}
	
	public int getId() {
		return id;
	}
	
	
	public int getComicCopyId() {
		return comicCopyId;
	}
	public int getRentalId() {
		return rentalId;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setComicCopyId(int comicCopyId) {
		this.comicCopyId = comicCopyId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}


}

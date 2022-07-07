package model;

public class Comic extends ComicGenre {
	
	private int id;
	protected String title;
	protected String author;
	protected double price;
	protected String description;
	protected int genreId;
	
	public Comic() {super();}
	
	public int getId() {
    	return id;
	}	
	public String getTitle() {
    	return title;
	}	
	public String getAuthor() {
    	return author;
	}	
	public double getPrice() {
    	return price;
	}
	public String getDescription() {
    	return description;
	}
	public int getGenreId() {
		return genreId;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setDescription(String description) {
    	this.description = description;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

}

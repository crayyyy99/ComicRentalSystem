package model;

public class ComicGenre {
	private int id;
	protected String genre;
	
	public ComicGenre() {}
	
	public int getId() {
		return id;
	}	
	public String getGenre() {
		return genre;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
}

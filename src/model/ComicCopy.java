package model;

public class ComicCopy extends Comic {

	private int id;
	private String condition;
	private int comicId;
	private int copyId;
	
	public ComicCopy() {super();}
	
	public int getId() {
		return id;
	}
	public String getCondition() {
		return condition;
	}
	public int getComicId() {
		return comicId;
	}
	public int getCopyId() {
		return copyId;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public void setComicId(int comicId) {
		this.comicId = comicId;
	}
	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}
}

package kr.co.mlec.library.vo;

public class RentalVO {
	private int renNo;
	private String id;
	private int bNo;
	private String renDate;
	private String returnDate;
	
	private String title;
	private String author;
	
	
	
	
	public RentalVO(String id) {
		super();
		this.id = id;
	}
	public RentalVO(int bNo, String title, String author, String renDate, String returnDate) {
		super();
		this.bNo = bNo;
		this.renDate = renDate;
		this.returnDate = returnDate;
		this.title = title;
		this.author = author;
	}
	public RentalVO(String id, int bNo, String title, String author, String renDate, String returnDate ) {
		super();
		this.id = id;
		this.bNo = bNo;
		this.renDate = renDate;
		this.returnDate = returnDate;
		this.title = title;
		this.author = author;
	}
	public RentalVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RentalVO(int renNo, String id, int bNo, String renDate, String returnDate) {
		super();
		this.renNo = renNo;
		this.id = id;
		this.bNo = bNo;
		this.renDate = renDate;
		this.returnDate = returnDate;
	}
	public int getRenNo() {
		return renNo;
	}
	public void setRenNo(int renNo) {
		this.renNo = renNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getRenDate() {
		return renDate;
	}
	public void setRenDate(String renDate) {
		this.renDate = renDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "RentalVO [renNo=" + renNo + ", id=" + id + ", bNo=" + bNo + ", renDate=" + renDate + ", returnDate="
				+ returnDate + ", title=" + title + ", author=" + author + "]";
	}
	
	
}

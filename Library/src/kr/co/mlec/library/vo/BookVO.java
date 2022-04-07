package kr.co.mlec.library.vo;

public class BookVO {

	private int bno;
	private String title;
	private String author;
	private String pub;
	private String pubDate;
	private int status;
	
	
	public BookVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public BookVO(int bno, String title, String author, String pub, String pubDate) {
		super();
		this.bno = bno;
		this.title = title;
		this.author = author;
		this.pub = pub;
		this.pubDate = pubDate;
	}


	public BookVO(int bno, String title, String author, String pub, String pubDate, int status) {
		super();
		this.bno = bno;
		this.title = title;
		this.author = author;
		this.pub = pub;
		this.pubDate = pubDate;
		this.status = status;
	}
	

	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
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
	public String getPub() {
		return pub;
	}
	public void setPub(String pub) {
		this.pub = pub;
	}
	
	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}




	@Override
	public String toString() {
		return "BookVO [bno=" + bno + ", title=" + title + ", author=" + author + ", pub=" + pub + ", pubDate="
				+ pubDate + ", status=" + status + "]";
	}
	
	
	
}

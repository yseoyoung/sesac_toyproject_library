package kr.co.mlec.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.mlec.library.util.ConnectionFactory;
import kr.co.mlec.library.util.JDBCClose;
import kr.co.mlec.library.vo.BookVO;
import kr.co.mlec.library.vo.LoginVO;
import kr.co.mlec.library.vo.RentalVO;

public class MemberDAO {

	LoginVO login = new LoginVO();

	
	
	// 1.대출
	public RentalVO borrowBook(RentalVO rental) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		try {

			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into t_rental(ren_no, id, b_no)");
			sql.append(" values(seq_t_ren_no.nextval, ?, ?)");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, rental.getId());
			pstmt.setInt(2, rental.getbNo());

			pstmt.executeUpdate();

			// update 메서드 분리
			StringBuilder sql2 = new StringBuilder();
			sql2.append("update t_books ");
			sql2.append(" set status = 0 ");
			sql2.append(" where b_no = ? ");

			pstmt2 = conn.prepareStatement(sql2.toString());

			pstmt2.setInt(1, rental.getbNo());

			pstmt2.executeUpdate();

			// select 메서드 분리
			StringBuilder sql3 = new StringBuilder();
			sql3.append(
					" select r.id, r.b_no, b.title, b.author, to_char(r.ren_d, 'yyyy-mm-dd') as ren_d, to_char(r.return_d, 'yyyy-mm-dd') as return_d ");
			sql3.append(" from t_rental r, t_books b");
			sql3.append(" where ? = b.b_no ");

			pstmt3 = conn.prepareStatement(sql3.toString());

			pstmt3.setInt(1, rental.getbNo());

			ResultSet rs = pstmt3.executeQuery();
			if (rs.next()) {
				String id = rs.getString("id");
				int bno = rs.getInt("b_no");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String renDate = rs.getString("ren_d");
				String returnDate = rs.getString("return_d");
				RentalVO rentalBook = new RentalVO(id, bno, title, author, renDate, returnDate);
				return rentalBook;
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
			JDBCClose.close(pstmt2, conn);
			JDBCClose.close(pstmt3, conn);
		}

		return null;
	}

	// 1-1. 대출 도서 중복 체크
	public boolean checkRental(RentalVO rental) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select b_no, status ");
			sql.append(" from t_books");
			sql.append(" where b_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, rental.getbNo());

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getInt("status") == 0) {
					return false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return true;
	}

	// 1-2. 대출할 도서 검색
	public List<BookVO> rentalSelect(BookVO book) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		List<BookVO> BookList = new ArrayList<>();

		try {

			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select b_no, title, author, pub, to_char(pub_d,'yyyy-mm-dd') as pub_d, status ");
			sql.append(" from t_books ");
			sql.append(" where instr(title, ?) != 0 ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, book.getTitle());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int bno = rs.getInt("b_no");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub = rs.getString("pub");
				String pubDate = rs.getString("pub_d");
				int status = rs.getInt("status");

				BookVO books = new BookVO(bno, title, author, pub, pubDate, status);

				BookList.add(books);
			}

			return BookList;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return BookList;
	}

	
	//1. 한 아이디당 대출한 책의 권수 체크 (3권 초과 시, 대출 불가)
	public int selectNumber(String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;


		try {

			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" select count(*) ");
			sql.append(" from t_rental ");
			sql.append(" where id = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				
				int books = rs.getInt("count(*)");
				
				return books;
				

			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return 0;
	}
	
	
	
	
	
	
	
	
	
	// 2-0. 대여중인 도서 목록

	public List<RentalVO> rentalBookList(RentalVO rental) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		List<RentalVO> returnBookList = new ArrayList<>();

		try {

			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select r.b_no, title,author, to_char(ren_d, 'yyyy-mm-dd') as ren_d, to_char(return_d,'yyyy-mm-dd') as return_d");
			sql.append(" from t_rental r, t_books b ");
			sql.append(" where r.id = ? ");
			sql.append("     and r.b_no = b.b_no ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, rental.getId());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int bno = rs.getInt("b_no");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String renDate = rs.getString("ren_d");
				String returnDate = rs.getString("return_d");

				RentalVO returnBook = new RentalVO(bno, title, author, renDate, returnDate);
				returnBookList.add(returnBook);

			}

			return returnBookList;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return returnBookList;
	}

	
	
	
	
	// 2.반납
	public List<RentalVO> returnList(RentalVO rental) {
		Connection conn = null;
		PreparedStatement pstmt3 = null;
		

		try {

			List<RentalVO> returnBookList = new ArrayList<>();
			conn = new ConnectionFactory().getConnection();
			
			
			//select 대여중인 도서 목록 가져오기
			StringBuilder sql3 = new StringBuilder();
			sql3.append(
					" select r.id, r.b_no, b.title, b.author, to_char(r.ren_d, 'yyyy-mm-dd') as ren_d, to_char(r.return_d, 'yyyy-mm-dd') as return_d ");
			sql3.append(" from t_rental r, t_books b");
			sql3.append(" where r.b_no = b.b_no ");
			
			pstmt3 = conn.prepareStatement(sql3.toString());
			
			ResultSet rs = pstmt3.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				int bno = rs.getInt("b_no");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String renDate = rs.getString("ren_d");
				String returnDate = rs.getString("return_d");
				RentalVO rentalBook = new RentalVO(id, bno, title, author, renDate, returnDate);
				
				returnBookList.add(rentalBook);
			}
			return returnBookList;
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt3, conn);
			
		}
		return null;
	}
	
	
	
	
	public void returnBook(RentalVO rental) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		

		try {

			List<RentalVO> returnBookList = new ArrayList<>();
			conn = new ConnectionFactory().getConnection();
	
			//delete t_rental 목록에서 삭제
			StringBuilder sql = new StringBuilder();
			sql.append(" delete ");
			sql.append(" from t_rental ");
			sql.append(" where b_no = ? ");
		
			pstmt = conn.prepareStatement(sql.toString());
		
			pstmt.setInt(1, rental.getbNo());
		
			pstmt.executeUpdate();
		
			
			// update 메서드 분리
			StringBuilder sql2 = new StringBuilder();
			sql2.append("update t_books ");
			sql2.append(" set status = 1 ");
			sql2.append(" where b_no = ? ");
		
			pstmt2 = conn.prepareStatement(sql2.toString());
		
			pstmt2.setInt(1, rental.getbNo());
		
			pstmt2.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
			JDBCClose.close(pstmt2, conn);
			
		}
	}
	
	
	
	
	
	
	
	

	// 3.전체 도서 목록 조회
		public List<BookVO> selectAllBook() {

			Connection conn = null;
			PreparedStatement pstmt = null;

			// 전체 게시글을 저장하기 위한 list
			List<BookVO> allBookList = new ArrayList<>();

			try {

				conn = new ConnectionFactory().getConnection();

				StringBuilder sql = new StringBuilder();
				sql.append("select b_no, title, author, pub, to_char(pub_d, 'yyyy-mm-dd') as pub_d ");
				sql.append(" from t_books ");

				pstmt = conn.prepareStatement(sql.toString());
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					int bno = rs.getInt("b_no");
					String title = rs.getString("title");
					String author = rs.getString("author");
					String pub = rs.getString("pub");
					String pubDate = rs.getString("pub_d");
					BookVO bookList = new BookVO(bno, title, author, pub, pubDate);

					allBookList.add(bookList);

				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCClose.close(pstmt, conn);
			}

			return allBookList;
		}

		  // 4-1 개별 도서 제목 검색

		   public List<BookVO> selectByTitleBook(String bTitle) {

		      Connection conn = null;
		      PreparedStatement pstmt = null;

		      List<BookVO> selectTitleList = new ArrayList<>();

		      try {

		         conn = new ConnectionFactory().getConnection();

		         StringBuilder sql = new StringBuilder();
		         sql.append("select b_no, title, author, pub, to_char(pub_d,'yyyy-mm-dd') as pub_d, status ");
		         sql.append(" from t_books ");
		         sql.append(" where instr(title, ?) != 0");

		         pstmt = conn.prepareStatement(sql.toString());
		         pstmt.setString(1, bTitle);

		         ResultSet rs = pstmt.executeQuery();

		         while (rs.next()) {
		            int bno = rs.getInt("b_no");
		            String title = rs.getString("title");
		            String author = rs.getString("author");
		            String pub = rs.getString("pub");
		            String pubDate = rs.getString("pub_d");
		            int status = rs.getInt("status");
		            BookVO selectBook = new BookVO(bno, title, author, pub, pubDate, status);
		            selectTitleList.add(selectBook);
		         }

		      } catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         JDBCClose.close(pstmt, conn);
		      }

		      return selectTitleList;
		   }
		
	// 4-2.개별 도서 번호 조회
	public List<BookVO> selectByNo(BookVO book) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		List<BookVO> BookList = new ArrayList<>();

		try {

			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select b_no, title, author, pub, to_char(pub_d,'yyyy-mm-dd') as pub_d, status ");
			sql.append(" from t_books ");
			sql.append(" where instr(b_no, ?) != 0 ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, book.getBno());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int bno = rs.getInt("b_no");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String pub = rs.getString("pub");
				String pubDate = rs.getString("pub_d");
				int status = rs.getInt("status");

				BookVO books = new BookVO(bno, title, author, pub, pubDate, status);

				BookList.add(books);
			}

			return BookList;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return null;
	}
	// 4-3 저자 검색
	   public List<BookVO> selectByAuthor(BookVO authorB) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;

	      List<BookVO> AuthorBookList = new ArrayList<>();
	      
	      try {
	         conn = new ConnectionFactory().getConnection();
	         
	         StringBuilder sql = new StringBuilder();
	         sql.append("select b_no, title, author, pub, to_char(pub_d,'yyyy-mm-dd') as pub_d, status ");
	         sql.append(" from t_books ");
	         sql.append(" where instr(author, ?) != 0 ");

	         pstmt = conn.prepareStatement(sql.toString());
	         pstmt.setString(1, authorB.getAuthor());

	         ResultSet rs = pstmt.executeQuery();
	         
	         while (rs.next()) {
	            int bno = rs.getInt("b_no");
	            String title = rs.getString("title");
	            String author = rs.getString("author");
	            String pub = rs.getString("pub");
	            String pubDate = rs.getString("pub_d");
	            int status = rs.getInt("status");

	            BookVO books = new BookVO(bno, title, author, pub, pubDate, status);

	            AuthorBookList.add(books);
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         JDBCClose.close(pstmt, conn);
	      }
	      
	      return AuthorBookList;
	   }

	// 4.회원 정보 수정

}

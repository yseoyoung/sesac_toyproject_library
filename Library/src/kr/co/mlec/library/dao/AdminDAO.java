package kr.co.mlec.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.mlec.library.util.ConnectionFactory;
import kr.co.mlec.library.util.JDBCClose;
import kr.co.mlec.library.vo.BookVO;
import kr.co.mlec.library.vo.LoginVO;

public class AdminDAO {
	public static LoginVO logInfo = new LoginVO();
	
	
	// 1.회원 목록 확인
	public List<LoginVO> selectMember() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		// 전체 게시글을 저장하기 위한 list
		List<LoginVO> MemberList = new ArrayList<>();

		try {

			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select id, name, email, to_char(joindate, 'yyyy-mm-dd') as joindate ");
			sql.append(" from t_member ");
			sql.append(" where isAdmin = 1");

			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String joindate = rs.getString("joindate");

				LoginVO member = new LoginVO(id, name, email, joindate);

				MemberList.add(member);
			}

			return MemberList;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return null;
	}

	// 2.전체 도서 목록 조회
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

		// 3.도서 번호 검색
		public BookVO selectBook(int bNo) {

			Connection conn = null;
			PreparedStatement pstmt = null;

			try {

				conn = new ConnectionFactory().getConnection();

				StringBuilder sql = new StringBuilder();
				sql.append("select b_no, title, author, pub, to_char(pub_d, 'yyyy-mm-dd') as pub_d, status ");
				sql.append(" from t_books ");
				sql.append(" where b_no = ? ");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, bNo);

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					int bno = rs.getInt("b_no");
					String title = rs.getString("title");
					String author = rs.getString("author");
					String pub = rs.getString("pub");
					String pubDate = rs.getString("pub_d");
					int status = rs.getInt("status");
					BookVO book = new BookVO(bno, title, author, pub, pubDate, status);
					return book;
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCClose.close(pstmt, conn);
			}

			return null;
		}

	// 4.도서 등록
	public int addBook(BookVO library) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {

			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append(" insert into t_books(b_no, title, author, pub, pub_d) ");
			sql.append(" values(seq_t_books_no.nextval, ?, ?, ?, ?) ");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, library.getTitle());
			pstmt.setString(2, library.getAuthor());
			pstmt.setString(3, library.getPub());
			pstmt.setString(4, library.getPubDate());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return result;
	}

	// 5.도서 정보 수정
		public int updateBook(BookVO updateB) {

			Connection conn = null;
			PreparedStatement pstmt = null;
			int cnt = 0;

			try {
				conn = new ConnectionFactory().getConnection();
				StringBuilder sql = new StringBuilder();

				sql.append("update t_books ");
				sql.append("set title = ?, author = ?, pub = ?, pub_d = ? ");
				sql.append("where b_no = ?");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, updateB.getTitle());
				pstmt.setString(2, updateB.getAuthor());
				pstmt.setString(3, updateB.getPub());
				pstmt.setString(4, updateB.getPubDate());
				pstmt.setInt(5, updateB.getBno());

				cnt = pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCClose.close(pstmt, conn);
			}

			return cnt;
		}

	// 6.도서 삭제
	public int deleteBook(BookVO deleteBook) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from t_books ");
			sql.append("where b_no = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, deleteBook.getBno());

			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}

		return cnt;
	}
}

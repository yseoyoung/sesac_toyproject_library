package kr.co.mlec.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.co.mlec.library.adminui.AdminLibraryUI;
import kr.co.mlec.library.memberui.MemberLibraryUI;
import kr.co.mlec.library.util.ConnectionFactory;
import kr.co.mlec.library.util.JDBCClose;
import kr.co.mlec.library.vo.LoginVO;

public class LoginDAO {
	
	//1.로그인
	public void MemberLogin(LoginVO library) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select id, password, isAdmin ");
			sql.append(" from t_member ");
			sql.append(" where id = ? and password = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, library.getId());
			pstmt.setString(2, library.getPassword());
			
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				//관리자 계정 check
				if(rs.getInt("isAdmin") == 2) {
					AdminLibraryUI ui2 = new AdminLibraryUI();
					ui2.execute();
				} else if (rs.getInt("isAdmin") == 1) {
					
					MemberLibraryUI ui = new MemberLibraryUI();
					ui.execute();
				}
			
			} else {
				System.out.println("아이디 혹은 비밀번호를 잘못 입력하였습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
	}
	
	//2.회원 가입(관리자용)
	public boolean createAdmin(LoginVO library) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into t_member(id, password, name, email, isAdmin) ");
			sql.append(" values(?, ?, ?, ?, 2) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, library.getId());
			pstmt.setString(2, library.getPassword());
			pstmt.setString(3, library.getName());
			pstmt.setString(4, library.getEmail());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return true;
		
	}
	
	
	//2.회원 가입 (회원용)
	public boolean createMember(LoginVO library) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into t_member(id, password, name, email) ");
			sql.append(" values(?, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, library.getId());
			pstmt.setString(2, library.getPassword());
			pstmt.setString(3, library.getName());
			pstmt.setString(4, library.getEmail());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return true;
	}
	
	
	//3.ID 중복 체크 메서드 
	public int CheckID(LoginVO library) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int id = 0;
		
		try {
			
			conn = new ConnectionFactory().getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select id ");
			sql.append(" from t_member ");
			sql.append(" where id = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, library.getId());
			
			id = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(pstmt, conn);
		}
		return id;
	}
	
//	//4.로그인 된 정보 저장할 메서드
//	public static LoginVO getLog(LoginVO library) {
//		LoginVO log = new LoginVO();
//		String id = library.getId();
//		String password = library.getPassword();
//		
//		log.setId(id);
//		log.setPassword(password);
//		
//		return log;
//	}
	
	
	

}

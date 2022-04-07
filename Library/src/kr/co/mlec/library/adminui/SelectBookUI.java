package kr.co.mlec.library.adminui;

import kr.co.mlec.library.dao.AdminDAO;
import kr.co.mlec.library.loginui.BaseUI;
import kr.co.mlec.library.vo.BookVO;

public class SelectBookUI extends BaseUI {

	@Override
	public void execute() {
		
		int bno = scanInt("조회할 책 번호를 입력하세요 : ");
		
		AdminDAO dao = new AdminDAO();
		BookVO result = dao.selectBook(bno);
		
		if(result == null) {
			System.out.println();
			System.out.println("[조회된 책이 없습니다.]\n");
		} else {
			System.out.println("=====================================");
			System.out.println("\t번   호  : " + result.getBno());
			System.out.println("\t제   목  : " + result.getTitle());
			System.out.println("\t저   자  : " + result.getAuthor());
			System.out.println("\t출판사  : " + result.getPub());
			System.out.println("\t출판일  : " + result.getPubDate());
			
			if(result.getStatus() == 1) {
				System.out.println("\n    ==>현재 대출 [가능]한 도서입니다.");
			} else {
				System.out.println("\n   ==>현재 대출 [불가능]한 도서입니다.");
			}
		
			System.out.println("=====================================\n");
		}
	}
}

package kr.co.mlec.library.adminui;

import kr.co.mlec.library.dao.AdminDAO;
import kr.co.mlec.library.loginui.BaseUI;
import kr.co.mlec.library.vo.BookVO;

public class UpdateBookUI extends BaseUI {

	@Override
	public void execute() {
		int bno = scanInt("수정할 도서 번호를 입력하세요 : ");
		String title = scanStr("수정할 도서명을 입력하세요 : ");
		String author = scanStr("수정할 저자명을 입력하세요 : ");
		String pub = scanStr("수정할 출판사명을 입력하세요 : ");
		String pubDate = scanStr("수정할 출판일을 입력하세요 : ");
		
		AdminDAO dao = new AdminDAO();
		
		BookVO updateB = new BookVO();
		updateB.setBno(bno);
		updateB.setTitle(title);
		updateB.setAuthor(author);
		updateB.setPub(pub);
		updateB.setPubDate(pubDate);
		
		int cnt = dao.updateBook(updateB);
		if(cnt == 1) {
			System.out.println();
			System.out.println("[ " + bno + " ]번 도서 정보가 수정되었습니다");
		} else {
			System.out.println();
			System.out.println("[입력하신 번호의 도서는 존재하지 않는 도서입니다.]");
			System.out.println("도서 번호 확인 후, 다시 입력해주세요.");
		}
	}
}

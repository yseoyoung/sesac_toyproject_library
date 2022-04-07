package kr.co.mlec.library.memberui;

import java.util.List;

import kr.co.mlec.library.dao.MemberDAO;
import kr.co.mlec.library.loginui.BaseUI;
import kr.co.mlec.library.vo.BookVO;

public class SelectByTitleUI extends BaseUI {

	@Override
	public void execute() {
		// TODO Auto-generated method stub

		String bTitle = scanStr("조회할 도서의 제목을 입력하세요 : ");

		MemberDAO dao = new MemberDAO();
		List<BookVO> list = dao.selectByTitleBook(bTitle);

		if (list.isEmpty()) {
			System.out.println();
			System.out.println("[조회된 책이 없습니다.]\n");
		} else {
			System.out.println("\n");
			System.out.println(">>> [ " + bTitle + " ] 로 검색한 도서 목록");
			System.out.println("================================================================================");
			System.out.println("번호\t제목\t\t저자\t\t출판사\t\t출판일\t\t대출 여부");
			System.out.println("================================================================================");
			for (BookVO books : list) {
				System.out.printf("%-6s\t%-10s\t%-10s\t%-8s\t%-8s", books.getBno(), books.getTitle(), books.getAuthor(),
						books.getPub(), books.getPubDate());

				if (books.getStatus() == 1) {
					System.out.println("\t   가능");
				} else {
					System.out.println("\t   불가");
				}
			}
			System.out.println("================================================================================\n");
		}
	}

}

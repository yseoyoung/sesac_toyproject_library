package kr.co.mlec.library.memberui;

import java.util.List;

import kr.co.mlec.library.dao.MemberDAO;
import kr.co.mlec.library.loginui.BaseUI;
import kr.co.mlec.library.vo.BookVO;

public class SelectByAuthorUI extends BaseUI {

	@Override
	public void execute() {

		while (true) {
			MemberDAO dao = new MemberDAO();
			BookVO book = new BookVO();

			String authorB = scanStr("조회할 도서의 저자명을 입력하세요 : ");
			book.setAuthor(authorB);

			List<BookVO> list = dao.selectByAuthor(book);

			if (list.isEmpty()) {
				System.out.println();
				System.out.println("[조회된 책이 없습니다.]\n");
			} else {
				System.out.println();
				System.out.println(">>> [ " + authorB + " ] (으)로 검색한 도서 목록");
				System.out.println("================================================================================");
				System.out.println("번호\t제목\t\t저자\t\t출판사\t\t출판일\t\t대출 여부");
				System.out.println("================================================================================");
				for (BookVO books : list) {
					System.out.printf("%-6s\t%-10s\t%-10s\t%-8s\t%-8s", books.getBno(), books.getTitle(),
							books.getAuthor(), books.getPub(), books.getPubDate());

					if (books.getStatus() == 1) {
						System.out.println("\t   가능");
					} else {
						System.out.println("\t   불가");
					}
				}
				System.out.println("================================================================================\n");
				break;
			}
		}

	}
}

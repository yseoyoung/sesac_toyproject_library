package kr.co.mlec.library.memberui;

import java.util.List;

import kr.co.mlec.library.dao.AdminDAO;
import kr.co.mlec.library.dao.MemberDAO;
import kr.co.mlec.library.loginui.BaseUI;
import kr.co.mlec.library.vo.BookVO;

public class SelectByNoUI extends BaseUI{
	
	@Override
	public void execute() {
		
		while(true) {
		MemberDAO dao = new MemberDAO();
		BookVO book = new BookVO();
		
		int bno = scanInt("조회할 책 번호를 입력하세요(일부 검색 가능) : ");
		book.setBno(bno);
		
		List<BookVO> list = dao.selectByNo(book);
		
		
		if(list.isEmpty()) {
			System.out.println();
			System.out.println("[조회된 책이 없습니다.]\n");
		} else {
			System.out.println();
			System.out.println(">>> [ " + bno + " ] (으)로 검색한 도서 목록");
			System.out.println("================================================================================");
	        System.out.println("번호\t제목\t\t저자\t\t출판사\t\t출판일\t\t대출 여부");
	        System.out.println("================================================================================");
			for(BookVO books : list){
				
				 System.out.printf("%-6s\t%-10s\t%-10s\t%-8s\t%-8s", 
		            		books.getBno(), books.getTitle(), books.getAuthor(), books.getPub(), books.getPubDate());
				
				
				if(books.getStatus() == 1) {
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

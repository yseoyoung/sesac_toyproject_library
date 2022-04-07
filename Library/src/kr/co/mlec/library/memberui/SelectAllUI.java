package kr.co.mlec.library.memberui;

import java.util.List;

import kr.co.mlec.library.dao.AdminDAO;
import kr.co.mlec.library.loginui.BaseUI;
import kr.co.mlec.library.vo.BookVO;

public class SelectAllUI extends BaseUI{

	@Override
	   public void execute() {
	      
	      AdminDAO dao = new AdminDAO();
	      List<BookVO> list = dao.selectAllBook();
	      
	      if(list.isEmpty()) {
	    	  System.out.println();
				System.out.println("[조회된 책이 없습니다.]\n");
	      } else {
	    	 System.out.println();
	    		System.out.println(">>> [ 전체 도서 목록 ]");
				System.out.println("===================================================================");
				System.out.println("번호\t제목\t\t저자\t\t출판사\t\t출판일");
				System.out.println("===================================================================");
	         //도서 등록할 때, 무조건 한글로만 등록하기! -> 영,한 섞이면 정렬 꼬임
	         
	         
	         //foreach문 사용하여 출력
	         for(BookVO book : list) {
	            System.out.printf("%-6s\t%-10s\t%-10s\t%-8s\t%-8s\n", 
	            		book.getBno(), book.getTitle(), book.getAuthor(), book.getPub(), book.getPubDate());

	         } //BoardVO라는 형 자체가 getter를 가지고 있기 때문에 사용 가능함
	         //list가 <BoardVO> 형태의 객체를 가리킬 주소를 갖고 있음
	         

	         System.out.println("===================================================================\n");
	      }
	   }
	
}

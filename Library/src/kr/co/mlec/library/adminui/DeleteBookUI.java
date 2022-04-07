package kr.co.mlec.library.adminui;

import kr.co.mlec.library.dao.AdminDAO;
import kr.co.mlec.library.loginui.BaseUI;
import kr.co.mlec.library.vo.BookVO;

public class DeleteBookUI extends BaseUI{
	
	@Override
	   public void execute() {
	      
	      AdminDAO dao = new AdminDAO();
	      
	      int no = scanInt("삭제할 도서 번호를 입력하세요 : ");
	      
	      BookVO deleteBook = new BookVO();
	      deleteBook.setBno(no);
	      
	      int cnt = dao.deleteBook(deleteBook);
	      if(cnt == 1) {
	    	 System.out.println();
	         System.out.println("[ " + no + " ]번 도서가 삭제되었습니다.");
	      } else {
	    	 System.out.println();
	         System.out.println("[삭제할 도서가 없습니다.]");
	      }
	      
	   }
}

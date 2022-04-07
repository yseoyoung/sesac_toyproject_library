package kr.co.mlec.library.memberui;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import kr.co.mlec.library.dao.MemberDAO;
import kr.co.mlec.library.loginui.BaseUI;
import kr.co.mlec.library.loginui.MemberLoginUI;
import kr.co.mlec.library.vo.LoginVO;
import kr.co.mlec.library.vo.RentalVO;

public class ReturnBookUI extends BaseUI {
	//2.반납
	//로그인 정보를 가지고 있는 loginVO에서 아이디를 가져오기
	//해당 아이디로 대출중인 책 정보 출력
	//반납 하시겠습니까?
	//Y -> 해당 열을 t_rental에서 삭제 -> 해당 도서의 t_books에서의 status update
	///완료 안내 출력
	public static LoginVO login;

	@Override
	public void execute() {
		
		/*LoginVO id=null;
		LoginVO login = new LoginVO();
		MemberLoginUI.getLogin(id);
		
		MemberDAO dao = new MemberDAO();
		RentalVO rental = new RentalVO();
		String logID = login.getId();
		rental.setId(logID);*/
		
		MemberDAO dao = new MemberDAO();
		RentalVO rental = new RentalVO();
		String logID = MemberLoginUI.logInfo.getId();
		System.out.println("회원 ID : [ "+logID + " ] 님 ღ'ᴗ'ღ \n");
		
		List<RentalVO> returnBookList = dao.returnList(rental);
	
		
		if(returnBookList == null || returnBookList.isEmpty()) {
			System.out.println();
			System.out.println("[대여중인 도서가 없습니다.]\n");
			MemberLibraryUI ui = new MemberLibraryUI();
			ui.execute();
		}else {
			System.out.println(">>> [ 대출중인 도서 목록 ]");
			System.out.println("================================================================================");
			System.out.println("번호\t제목\t\t저자\t\t대출일\t\t반납 기한");
			System.out.println("================================================================================");
			
			List<Integer> bookNos = new ArrayList<>();
			for(RentalVO returnBook : returnBookList ) {
				System.out.printf("%-6s\t%-10s\t%-10s\t%-8s\t%-8s\n",returnBook.getbNo(), 
						returnBook.getTitle() ,returnBook.getAuthor(),
						returnBook.getRenDate(),returnBook.getReturnDate());
				
						bookNos.add(returnBook.getbNo());
			}
			
			System.out.println("================================================================================");
			
			while(true) {
				
				int bNo = scanInt("반납하실 도서 <번호>를 입력하세요 : ");
				rental.setbNo(bNo);
				
					if(!bookNos.contains(bNo)) {
					System.out.println("[ 현재 대여중인 도서가 아닙니다. ]");
					System.out.println("도서 코드를 다시 확인해주세요.");
					} else
						break;
			}
			dao.returnBook(rental);
			System.out.println();
			System.out.println("===========================");
			System.out.println("\n        반납이 [완료]되었습니다.  \n");
			System.out.println("     다음에 또 오세요.( •◡-)✧˖° ♡ \n");
			System.out.println("===========================");

			
			
			
			/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			Date rentalDate = null;
			Date returnDate = null;
			
			try {
				rentalDate = dateFormat.parse(returnBookList.get(3))
			} catch (Exception e) {
				// TODO: handle exception
			}*/
			
				
		}
		
	}
}


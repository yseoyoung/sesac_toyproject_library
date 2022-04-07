package kr.co.mlec.library.memberui;

import java.util.List;

import kr.co.mlec.library.dao.LoginDAO;
import kr.co.mlec.library.dao.MemberDAO;
import kr.co.mlec.library.loginui.BaseUI;
import kr.co.mlec.library.loginui.LoginUI;
import kr.co.mlec.library.loginui.MemberLoginUI;
import kr.co.mlec.library.vo.BookVO;
import kr.co.mlec.library.vo.LoginVO;
import kr.co.mlec.library.vo.RentalVO;

public class RentalBookUI extends BaseUI{
	
	public static LoginVO login;
	
	@Override
	public void execute() {
		
		//1.대출
		//로그인 정보 값을 가지고 있는 loginVO에서 값을 가져오기
		//로그인 기록 + 대출할 도서의 제목 -> 도서 번호 + 대출일자 + 반납일자 정보를 포함한 행을 t_rental 에 삽입
		//반납 기일 출력 "반납 기일은 returnDate 입니다."
		//해당 도서의 상태를 t_books에서 검색한 후, 남은 권수 -1 update
		//회원 정보 -> 현재 대출중인 책 목록 테이블 update
		
		//LoginVO id=null;
		//LoginVO login = new LoginVO();
		//MemberLoginUI.getLogin(id);
		
		MemberDAO dao = new MemberDAO();
		RentalVO rental = new RentalVO();
		//String logID1 = login.getId();
		
		
		String logID = MemberLoginUI.logInfo.getId();
		
		System.out.println("회원 ID : [ "+logID + " ] 님 ღ'ᴗ'ღ \n");
		
			
			
			while(true) {
				BookVO book = new BookVO();
				String title = scanStr("대출할 도서의 <제목>을 입력하세요 : ");
				book.setTitle(title);
				
				List<BookVO> list = dao.rentalSelect(book);
				
				
				if(list.isEmpty()) {
					System.out.println();
					System.out.println("[조회된 책이 없습니다.]\n");
					
				} else {
					System.out.println(">>> [ " + title + " ] (으)로 검색한 도서 목록");
					System.out.println("================================================================================");
					System.out.println("번호\t제목\t\t저자\t\t출판사\t\t출판일\t\t대출 여부");
					System.out.println("================================================================================");
					for(BookVO books : list){
						
						System.out.printf("%-6s\t%-10s\t%-10s\t%-8s\t%-8s", 
								books.getBno(), books.getTitle(), books.getAuthor(),
								books.getPub(), books.getPubDate());
						
						if(books.getStatus() == 1) {
							System.out.println("\t   가능");
						} else {
							System.out.println("\t   불가");
						}
					}
				
					System.out.println("================================================================================");
						break;
				}
			}

				

		int bNo = scanInt("대출할 도서의 <번호>를 입력하세요 : ");
		
		rental.setbNo(bNo);
		rental.setId(logID);
		
		RentalVO rentalBook = new RentalVO();
		boolean status = dao.checkRental(rental);
		if(!status) {
			System.out.println("현재 대출 [ 불가능 ]한 도서입니다. (수량 : 0)");
		}else {
			
			if(dao.selectNumber(logID) >= 3) {
				System.out.println("대여 가능한 도서 권수를 초과하였습니다.\n반납 후 다시 이용해 주세요.☺");
			} else {
			
				rentalBook = dao.borrowBook(rental);
				System.out.println();
				System.out.println("\t>> 대출을 완료했습니다.☻ <<");
				System.out.println("======================================");
				System.out.println("\t대출자 : "+rentalBook.getId());
				System.out.println("\t대출 도서 코드 : "+rentalBook.getbNo());
				System.out.println("\t대출 도서명 : "+rentalBook.getTitle());
				System.out.println("\t대출 도서 저자 : "+rentalBook.getAuthor());
				System.out.println("\t대출 날짜 : " +rentalBook.getRenDate()+"\n");
				System.out.println(" ==> 반납 일자는 " +rentalBook.getReturnDate() +" 일 입니다.");
				System.out.println("======================================");
		}
		}

	}

}

package kr.co.mlec.library.memberui;

import kr.co.mlec.library.loginui.BaseUI;
import kr.co.mlec.library.loginui.ILibraryUI;

public class SelectBookUI extends BaseUI{

	
	private String selectMenu() {
		System.out.println("\t < 도서 검색 >");
		System.out.println("===========================");
		System.out.println("\t 1. 제목 검색");
		System.out.println("\t 2. 번호 검색");
		System.out.println("\t 3. 저자 검색");
		System.out.println("\t 0. 돌아 가기");
		System.out.println("===========================");
		String type = scanStr(">> ");
		
		return type;
	}

	@Override
	public void execute() {
		
		while(true) {
			
			ILibraryUI ui = null;
			String type = selectMenu();
			
			switch(type) {
				case"1":
					ui = new SelectByTitleUI();
					break;
				case"2":
					ui = new SelectByNoUI();
					break;
				case"3":
					ui = new SelectByAuthorUI();
					break;
				case"0":
					ui = new MemberLibraryUI();
					break;
			}
			
			if(ui != null) {
				ui.execute();
			} else {
				System.out.println(">> 잘못 입력하였습니다. 다시 입력해주세요.");
			}
			
		}
		
	}
	
}

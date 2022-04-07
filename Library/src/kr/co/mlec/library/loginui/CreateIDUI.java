package kr.co.mlec.library.loginui;

public class CreateIDUI extends BaseUI {
	
	private String createIDMenu() {
		System.out.println();
		System.out.println("\t < 회원 가입 >");
		System.out.println("===========================");
		System.out.println("    1. 관리자용     2. 회원용");
		System.out.println("===========================");
		String type = scanStr(">> ");
		
		return type;
	}

	@Override
	public void execute() {
		
			ILibraryUI ui = null;
			String type = createIDMenu();
			
			switch(type) {
				case "1":
					ui = new CreateAdminUI();
					break;
				case "2":
					ui = new CreateMemberUI();
					break;
			}
			
			if(ui != null) {
				ui.execute();
			} else {
				System.out.println(">> 잘못 입력하였습니다. 다시 입력해주세요.");
			}
	}
}

package kr.co.mlec.library.loginui;

import java.util.Scanner;

import kr.co.mlec.library.vo.LoginVO;

public class LoginUI extends BaseUI {
	
	//public static LoginVO logInfo = new LoginVO();
	
	
	
	private String loginMenu() {
		System.out.println();
		System.out.println("\tπ± μμΉ λμκ΄ π±");
		System.out.println("===========================");
		System.out.println("\t 1. λ‘κ·ΈμΈ");
		System.out.println("\t 2. νμ κ°μ");
		System.out.println("\t 0. μ’λ£");
		System.out.println("===========================");
		
		String type = scanStr(">> ");
		return type;
		}
	
	
		@Override
		public void execute() {
			
			while(true) {
				
				ILibraryUI ui = null;
				String type = loginMenu();
				
				switch(type) {
					case "1":
						ui = new MemberLoginUI();
						break;
					case "2":
						ui = new CreateIDUI();
						break;
					case "0":
						ui = new ExitUI();
						break;
				}
				
				if(ui != null) {
					ui.execute();
					
				} else {
					System.out.println(">> μλͺ» μλ ₯νμμ΅λλ€. λ€μ μλ ₯ν΄ μ£ΌμΈμ.");
				}
			}
		}
}	

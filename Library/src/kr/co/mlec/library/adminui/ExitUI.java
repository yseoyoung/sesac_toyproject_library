package kr.co.mlec.library.adminui;

import kr.co.mlec.library.loginui.BaseUI;

public class ExitUI extends BaseUI {

	@Override
	public void execute() {
		System.out.println("===========================");
		System.out.println("\n          프로그램을 종료합니다👋\n");
		System.out.println("===========================");
		
		System.exit(0);
	}
	
}

package kr.co.mlec.library.memberui;

import kr.co.mlec.library.loginui.BaseUI;

public class ExitUI extends BaseUI{
	
	@Override
	public void execute() {
		System.out.println("===============================");
		System.out.println("\n\t프로그램을 종료합니다\n");
		System.out.println("===============================");
		System.exit(0);
	}
}

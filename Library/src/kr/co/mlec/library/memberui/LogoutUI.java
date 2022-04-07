package kr.co.mlec.library.memberui;

import kr.co.mlec.library.loginui.BaseUI;
import kr.co.mlec.library.loginui.LoginUI;
import kr.co.mlec.library.loginui.MemberLoginUI;
import kr.co.mlec.library.vo.LoginVO;

public class LogoutUI extends BaseUI {

	public static LoginVO login = new LoginVO();

	@Override
	public void execute() {

		String loginID = MemberLoginUI.logInfo.getId();
		System.out.println("===========================");
		System.out.println("\n  [ " + loginID + " ] 님 로그 아웃 완료. \n");
		System.out.println("       다음에 또 방문해주세요. ^_^ \n");
		System.out.println("===========================");
		String id = null;
		String password = null;

		login.setId(id);
		login.setPassword(password);
		LoginUI ui = new LoginUI();
		ui.execute();

	}

}

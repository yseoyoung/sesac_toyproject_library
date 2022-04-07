package kr.co.mlec.library.loginui;

import kr.co.mlec.library.dao.LoginDAO;
import kr.co.mlec.library.vo.LoginVO;

public class MemberLoginUI extends BaseUI{

	public static LoginVO logInfo = new LoginVO();
	
	@Override
	public void execute() {
		String id = scanStr("아이디를 입력하세요 : ");
		String password = scanStr("비밀번호를 입력하세요 : ");
		
		LoginDAO dao = new LoginDAO();
		
		logInfo.setId(id); 
		logInfo.setPassword(password);
		
		dao.MemberLogin(logInfo);
		
	}
	
	
	//로그인한 값을 저장 (ID만 알면 회원 조회할 수 있음)
//	public static void getLogin(LoginVO id) {
//		MemberLoginUI.login = id;
//	}
	
}

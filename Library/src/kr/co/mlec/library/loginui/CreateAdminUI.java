package kr.co.mlec.library.loginui;

import kr.co.mlec.library.dao.LoginDAO;
import kr.co.mlec.library.vo.LoginVO;

public class CreateAdminUI extends BaseUI{

	@Override
	public void execute() {
		System.out.println();
		System.out.println("[ 관리자 ]");
		String id = scanStr("아이디를 입력하세요 : ");
		String password = scanStr("비밀번호를 입력하세요 : ");
		String name = scanStr("이름을 입력하세요 : ");
		String email = scanStr("이메일을 입력하세요 : ");
		
		LoginDAO dao = new LoginDAO();
		LoginVO library = new LoginVO();
		
		library.setId(id);
		library.setPassword(password);
		library.setName(name);
		library.setEmail(email);
		
		// 중복 id 체크
		int cnt = dao.CheckID(library);
		if(cnt > 0) {
			System.out.println("중복된 관리자용 아이디가 존재합니다.");
		} else {
			dao.createAdmin(library);
			System.out.println("관리자 계정 생성이 [완료]되었습니다.  \n");
		}
		
	}
	
}

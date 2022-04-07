package kr.co.mlec.library.loginui;

import java.util.Scanner;

import kr.co.mlec.library.dao.LoginDAO;

public abstract class BaseUI implements ILibraryUI{
	
	private Scanner sc;
	
	public BaseUI() {
		sc = new Scanner(System.in);
	}
	
	public String scanStr(String msg) {
		System.out.print(msg);
		String str = sc.nextLine();
		
		return str;
	}
	
	public int scanInt(String msg) {
		System.out.print(msg);
		int num = Integer.parseInt(sc.nextLine());
		
		return num;
	}
}

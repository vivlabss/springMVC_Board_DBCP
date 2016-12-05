package com.isoo.Service;

import org.springframework.ui.Model;

public interface MvcBoardService {
	
	// HomeController에 있는 model 에 담긴 데이터 받기
	void execute(Model model);
	
	// 모든 서비스는 이 인터페이스를 구현받아서 execute 를 오버라이드 하여 사용할 예정 ==> 다형성!

}

package com.isoo.Service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.isoo.DAO.MvcBoardDAO;
import com.isoo.VO.MvcboardVO;

public class ListService implements MvcBoardService {


	//한페이지 분량 불러오기
	
	@Override
	public void execute(Model model) {
		System.out.println("ListService execute()"); //로그(생략가능)
		
		MvcBoardDAO dao = new MvcBoardDAO(); //디비 커넥터 갖고옴?!
		
		// MvcBoardDAO클래스 내 list()실행
		// 		그럼 디비에서 리턴된 값(ArrayList)을 담아줌
		ArrayList<MvcboardVO> list = dao.list();
		
		// 데이터가 담긴 list를 model에 담아서 넘겨줌
		model.addAttribute("list", list);
		
		// model에 담긴채로 controller로 넘어감. (컨트롤러에서 list() 내에서 service.execute(model);  를 했으므로)
	}
}

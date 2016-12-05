package com.isoo.Service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.isoo.DAO.MvcBoardDAO;

public class IncrementService implements MvcBoardService {

	@Override
	public void execute(Model model) {
		System.out.println("IncrementService execute()"); //로그(생략가능)

		
		Map<String, Object> map = model.asMap();
		// key는 String 타입. 넘어오는 value는 어떤타입이 넘어올지 모르므로 object!
		
		HttpServletRequest request = (HttpServletRequest) map.get("request"); //형변환(addcast)해줌(object -> http~)
		
		int idx = Integer.parseInt(request.getParameter("idx")); 
		//request 를 getParameter로 받아오면 무조건 String 타입이 되므로, 형변환해줘야함.
		
		MvcBoardDAO dao = new MvcBoardDAO();
		
		dao.upHit(idx); // 조회수 증가해줄 메소드
	
		
		model.addAttribute("idx", idx); // model 에다가 idx를 담아, 이걸 애초에 호출한 컨트롤러 내 ...??? 
		
	}

}

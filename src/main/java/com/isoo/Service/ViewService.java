package com.isoo.Service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.isoo.DAO.MvcBoardDAO;
import com.isoo.VO.MvcboardVO;

public class ViewService implements MvcBoardService {

	@Override
	public void execute(Model model) {
		System.out.println("ViewService execute()"); //로그(생략가능)

		
		Map<String, Object> map = model.asMap();
		// key는 String 타입. 넘어오는 value는 어떤타입이 넘어올지 모르므로 object!
		
		HttpServletRequest request = (HttpServletRequest) map.get("request"); //형변환(addcast)해줌
		
		int idx = Integer.parseInt(request.getParameter("idx")); 
		//request 를 getParameter로 받아오면 무조건 String 타입이 되므로, 형변환해줘야함.
		
		MvcBoardDAO dao = new MvcBoardDAO();
		
		MvcboardVO vo = dao.view(idx); // idx가 DAO클래스 내 view()로 넘어감
		// map에 담긴 글번호를???, dao에 넣어 리턴받은 결과를, vo에 넣어 저장. view 실행.(MvcBoardDAO에 있는)==> ????????????????????
		
		model.addAttribute("view", vo); // model 에다가, 넘어온 글 1건을 담아, 이걸 애초에 호출한 컨트롤러 내  service.execute(model);  로 가게됨! 
		
	}
}

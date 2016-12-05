package com.isoo.Service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.isoo.DAO.MvcBoardDAO;

public class WriteService implements MvcBoardService {

	@Override
	public void execute(Model model) {
		System.out.println("WriteService execute()"); // model에 담긴 데이터가 잘 넘어왔는지 확인차(생략가능)
		
		// model 에 담겨오는거 받는법
		Map<String, Object> map = model.asMap(); // model에 담겨온 모든 내용이 map에 들어감
		HttpServletRequest request = (HttpServletRequest) map.get("request"); // 형변환시킴
		
		// 잘 받아왔는지 확인(생략가능)
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("title"));
		System.out.println(request.getParameter("content"));
		
		// 잘 넘어온 데이터들을 변수에 담음
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 변수들을 메소드에 넣어 실행? (dao에)
		MvcBoardDAO dao = new MvcBoardDAO();
		
		// 
		dao.insert(name, title, content);
		
		
	}
}

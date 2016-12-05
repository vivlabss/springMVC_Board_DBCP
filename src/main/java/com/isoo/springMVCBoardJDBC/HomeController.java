package com.isoo.springMVCBoardJDBC;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isoo.DAO.Constant;
import com.isoo.Service.DeleteService;
import com.isoo.Service.IncrementService;
import com.isoo.Service.ListService;
import com.isoo.Service.MvcBoardService;
import com.isoo.Service.ReplyOKService;
import com.isoo.Service.ReplyService;
import com.isoo.Service.UpdateService;
import com.isoo.Service.ViewService;
import com.isoo.Service.WriteService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	MvcBoardService service; // 인터페이스 객체 선언
	
	
	public JdbcTemplate template;
	
	
	public JdbcTemplate getTemplate() {
		return template;
	}

	// HomeController가 최초로 JDBCtemplate 객체가 사용될 때 자동을 setter가 실행되게하기 위해 @Autowired 어노테이션을 붙인다
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}


	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	
// 글쓰기 페이지를 띄우는 메소드	
	@RequestMapping("/write")
	public String write() {
		System.out.println("write()"); //어떤 메소드가 실행중인지 로그 확인차 
		return "write";
	}
	
	
// 글 1건을 테이블에 저장하는 메소드	
	@RequestMapping("/writeOK")
	public String writeOK(HttpServletRequest request, Model model) { // write에서 넘어온건 request에 담기고, writeOK로 넘겨줄 데이터가 model에 담겨있
		System.out.println("writeOK()");
		
		// writeOK로 데이터 잘 넘어왔는지 확인(생략가능)
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("title"));
		System.out.println(request.getParameter("content"));
		
		// write.jsp 에 담겨온 내용을 model에 담는 과정
		model.addAttribute("request", request);
		
		service = new WriteService();
		service.execute(model);  // WriteService.java 에 execute 메소드로 model에 있는 정보가 넘어감
		
		// writeOK() 에서 작성된 글까지 꺼내와서 리스트를 가져와야하므로, 이 메소드에서는 곧바로 list()로 가야함
		//	그래서 redirect를 사용하여 @RequestMapping("/list") 어노테이션이 붙어있는 메소드를 호출!!
		return "redirect:list";
	}
	

// 테이블에 저장된 글 전체를 얻어오는 메소드
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list()"); //로그(생략가능)
		service = new ListService();
		service.execute(model);
		return "list";
	}
	
	
// list.jsp 에서 클릭된 글 1건을 얻어오는 메소드
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Model model) {  // list에서 클릭된 제목에 해당하는 idx를 가지고 넘어가야함 => 그리서 request로 받고 model로.? 
		System.out.println("view()"); //로그(생략가능)
		model.addAttribute("request", request); // ViewService로 넘어가기
		service = new ViewService();
		service.execute(model); // list.jsp 에서 넘겨받은 내용(여기서는 글번호)를 request로 넘겨받아 model에 담은후 ViewService클래스의 execute()에 넘어감.
		return "view";
	}


// list.jsp 에서 클릭된 글 1건의 조회수를 증가시키는 메소드
	@RequestMapping("/increment")
	public String increment(HttpServletRequest request, Model model) {  // list에서 클릭된 제목에 해당하는 idx를 가지고 넘어가야함 => 그리서 request로 받고 model에 담아서 IncrementService로 넘어감!  
		System.out.println("increment()"); //로그(생략가능)
		model.addAttribute("request", request); // IncrementService로 넘어가기
		service = new IncrementService();
		service.execute(model); 
		return "redirect:view";  // 조회수 증가시키고 view로 넘어가기.
	}
	
// view.jsp 에서 넘어온 글을 삭제하는 메소드	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {   
		System.out.println("delete()"); //로그(생략가능)
		model.addAttribute("request", request); 
		service = new DeleteService(); 
		service.execute(model);  // request로 넘어온 글번호 받아서 model에 담은후 DeleteService 로 넘겨줌!
		return "redirect:list";  // 지워지고나서 annotation에 list가 붙은 아이에게로 가라!
	}
	
// view.jsp 에서 넘어온 글을 수정하는 메소드	
	@RequestMapping("/update")
	public String update(HttpServletRequest request, Model model) {   
		System.out.println("update()"); //로그(생략가능)
		
		model.addAttribute("request", request); 
		service = new UpdateService(); 
		service.execute(model);  // request로 넘어온 글번호, 내용, 제목을 받아서 model에 담은후 UpdateService 로 넘겨줌!
		return "redirect:list";  // 수정되고나서 annotation에 list가 붙은 아이에게로 가라!
	}

	
// view.jsp 에서 넘어온 글의, 답글을 달아주는 뷰페이지로 이동하는 메소드	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {   
		System.out.println("reply()"); //로그(생략가능)
		
		model.addAttribute("request", request); 
		service = new ReplyService(); 
		service.execute(model);  // request로 넘어온 글번호를 받아서 model에 담은후 ReplyService 로 넘겨줌!
		return "reply";  // ReplyService 갔다온 후, reply 페이지로 이동!
	}
	
	
// view.jsp 에서 넘어온 글의, 답글을 달아주는 메소드
	@RequestMapping("/replyOK")
	public String replyOK(HttpServletRequest request, Model model) {   
		System.out.println("replyOK()"); //로그(생략가능)
		
		model.addAttribute("request", request); 
		service = new ReplyOKService(); 
		service.execute(model);  // request로 넘어온 글번호를 받아서 model에 담은후 ReplyOKService 로 넘겨줌!
		return "redirect:list";  // ReplyOKService 갔다온 후, annotation에 list가 붙은 아이에게로!
	}
	
	
	
}
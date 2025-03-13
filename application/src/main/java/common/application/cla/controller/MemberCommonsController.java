package common.application.cla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.application.cla.dto.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/class")
public record MemberCommonsController() {

    @GetMapping("/loginForm")
	public String login(@ModelAttribute(name = "retUrl") String retUrl,HttpServletResponse response) {		
		String url = "/cla/common/login";
		response.setStatus(302);
		return url;
	}

	@GetMapping("/logout")
	public String logout(){
		String url = "/cla/common/logout";
		return url;
	}

    @GetMapping("/sessionCheck")
	@ResponseBody
	public String sessionCheck(HttpSession session) {
		String loginUser_id=null;		
		
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		if(loginUser!=null) loginUser_id=loginUser.getMid();
		
		return loginUser_id;
	}

	@GetMapping("/loginTimeOut")
	public String loginTimeOut(HttpServletRequest request, Model model) throws Exception{
		String url = "/cla/common/loginSessionOut";
		model.addAttribute("message","세션이 만료되었습니다.\\n다시 로그인 해 주세요");
		model.addAttribute("contextPath", request.getContextPath());
		return url;
	}
	@GetMapping("/loginExpired")
	public String loginExpired(Model model)throws Exception{
		String url = "/cla/common/loginSessionOut";
		model.addAttribute("message", "다른 장치에서 중복 로그인이 확인되었습니다.");
		return url;
	}
}
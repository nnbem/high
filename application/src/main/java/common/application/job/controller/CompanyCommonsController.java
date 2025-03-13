package common.application.job.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import common.application.job.dto.CompanyVO;
import common.application.job.request.CompanyRegistRequest;
import common.application.job.service.CompanyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;




@Controller
@RequestMapping("/job")
public record CompanyCommonsController(CompanyService companyService) {
    @GetMapping("/loginForm")
    public String login(@ModelAttribute(name = "retUrl") String retUrl,HttpServletResponse response) {		
        String url = "/job/common/login";
        response.setStatus(302);
        return url;
    }
    
	@GetMapping("/logout")
	public String logout(){
		String url = "/job/common/logout";
		return url;
	}    

    @GetMapping("/sessionCheck")
	@ResponseBody
	public String sessionCheck(HttpSession session) {
		String loginUser_id=null;		
		
		CompanyVO loginUser = (CompanyVO)session.getAttribute("loginUser");
		if(loginUser!=null) loginUser_id=loginUser.getCno();
		
		return loginUser_id;
	}

	@GetMapping("/loginTimeOut")
	public String loginTimeOut(HttpServletRequest request, Model model) throws Exception{
		String url = "/job/common/loginSessionOut";
		model.addAttribute("message","세션이 만료되었습니다.\\n다시 로그인 해 주세요");
		model.addAttribute("contextPath", request.getContextPath());
		return url;
	}
	@GetMapping("/loginExpired")
	public String loginExpired(Model model)throws Exception{
		String url = "/job/common/loginSessionOut";
		model.addAttribute("message", "다른 장치에서 중복 로그인이 확인되었습니다.");
		return url;
	}

    @GetMapping("/login/findpwd")
    public String findPwd() {
        String url = "/job/common/findpwd";
        return url;
    }
    
    @GetMapping("/join")
    public String join(){
        String url="/job/common/join";
        return url;
    }

    @PostMapping(value = "/join/post", produces = "text/plain;charset=utf-8")
	public ModelAndView regist(CompanyRegistRequest regRequest, ModelAndView mnv)throws Exception {
		String url = "/cla/common/join_success";

        CompanyVO company = regRequest.toComponyVO();
        companyService.registCompany(company);

		mnv.setViewName(url);
		return mnv;
	}

    @GetMapping("/cnoCheck")
	@ResponseBody
	public String idCheck(String cno) throws Exception {
		String result = "duplicated";
		CompanyVO company = companyService.company(cno);
		if (company == null)
			result = "";

		return result;
	}

    @GetMapping("/mypage/info")
    public String info() {
        String url = "job/mypage/info/info";
        return url;
    }
    
    @GetMapping("/mypage/info/modify")
    public String infoModify(){
        String url="job/mypage/info/modify";
        return url;
    }
}

package common.application.cla.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import common.application.cla.dto.ClassMenuVO;
import common.application.cla.dto.ClassMypageMenuVO;
import common.application.cla.service.ClassMenuService;
import common.application.cla.service.ClassMypageMenuService;


@Controller
@RequestMapping("/class")
public record MainController(ClassMenuService classMenuService, ClassMypageMenuService classMypageMenuService) {

	@GetMapping("/main")
	public String main(){
		String url="cla/Main";
		return url;
	}
	
    @GetMapping("/main/ifr")
	public String main(String cCode,Model model) throws Exception{
	String url="cla/Cmain";
		
	List<ClassMenuVO> classMenuList = classMenuService.getMainClassMenuList();
    model.addAttribute("classMenuList",classMenuList);
		
    List<ClassMypageMenuVO> classMypageMenuList = classMypageMenuService.getClassMypageList();
    model.addAttribute("classMypageMenuList", classMypageMenuList);
    
	if (cCode != null) {
	    ClassMenuVO classMenu = classMenuService.getClassMenuByCcode(cCode);
		model.addAttribute("classMenu", classMenu);
	}
		
		
	return url;
	}


}

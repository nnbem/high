package common.application.cla.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.application.cla.dto.ClassMenuVO;
import common.application.cla.service.ClassMenuService;

@RestController
@RequestMapping("/class/menu")
public record ClassMenuController(ClassMenuService classMenuService) {

    @GetMapping("/subMenu")
	public List<ClassMenuVO> subMenuToJSON(String cCode) throws Exception {
		List<ClassMenuVO> classMenuList = classMenuService.getSubClassMenuList(cCode);
		return classMenuList;
	}

}

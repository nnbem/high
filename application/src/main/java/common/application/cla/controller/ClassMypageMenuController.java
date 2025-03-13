package common.application.cla.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.application.cla.dto.ClassMypageMenuVO;
import common.application.cla.service.ClassMypageMenuService;

@RestController
@RequestMapping("/class/mypagemenu")
public record ClassMypageMenuController(ClassMypageMenuService classMypageMenuService) {

    @GetMapping("/subMypageMenu")
    public List<ClassMypageMenuVO> subMenuToJSON(String cmCode) throws Exception{
        List<ClassMypageMenuVO> classMypageMenuList = classMypageMenuService.getSubMypageMenuList(cmCode);
        return classMypageMenuList;
    }
}

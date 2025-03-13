package common.application.job.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.application.job.dto.MypageMenuVO;
import common.application.job.service.MypageMenuService;

@RestController
@RequestMapping("/mypagemenu")
public record MypageMenuController(MypageMenuService mypageMenuService) {

    @GetMapping("/job/menu/mypageSubMenu")
    public List<MypageMenuVO> subMenuToJSON(String mCode) throws Exception {
        List<MypageMenuVO> mypageMenuList = mypageMenuService.getSubMypageMenuList(mCode);
        return mypageMenuList;
    }
}

package common.application.job.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import common.application.job.dto.JobMenuVO;
import common.application.job.dto.MypageMenuVO;
import common.application.job.service.JobMenuService;
import common.application.job.service.MypageMenuService;

@Controller
@RequestMapping("/job")
public record MainMenuController(JobMenuService jobMenuService, MypageMenuService mypageMenuService) {

    @GetMapping("/main")
    public String jmain(String jCode, Model model) throws Exception {
        String url = "job/Jmain";
        
        // 메인 메뉴
        List<JobMenuVO> jobMenuList = jobMenuService.getMainJobMenuList();
        model.addAttribute("jobMenuList", jobMenuList);

        List<MypageMenuVO> mypageMenuList = mypageMenuService.getMainMypageList();
        model.addAttribute("mypageMenuList", mypageMenuList);

        if (jCode != null) {
            JobMenuVO jobMenu = jobMenuService.getJobMenuByJcode(jCode);
            model.addAttribute("jobMenu", jobMenu);
        }

        return url;
    }

}

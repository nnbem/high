package common.application.job.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import common.application.job.dto.JobMenuVO;
import common.application.job.service.JobMenuService;

@RestController
@RequestMapping("/job/menu")
public record JobMenuController(JobMenuService jobMenuService) {

    @GetMapping("/subMenu")
    public List<JobMenuVO> subMenuToJSON(String jCode) throws Exception{
        List<JobMenuVO> jobMenuList = jobMenuService.getSubJobMenuList(jCode);
        return jobMenuList;
    }

}

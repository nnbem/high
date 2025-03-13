package common.application.job.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import common.application.job.dto.Skill_StackVO;
import common.application.job.service.Skill_StackService;
import common.application.request.PageMaker;

@Controller
@RequestMapping("/job/test")
public record Skill_StackController (Skill_StackService skill_StackService) {

    @GetMapping
    public String Test(@ModelAttribute PageMaker pageMaker, Model model) throws Exception {
        List<Skill_StackVO> skill_StackList = skill_StackService.list(pageMaker);
        model.addAttribute("skill_StackList", skill_StackList);
        return "job/test/test";
    }
}
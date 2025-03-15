package common.application.cla.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import common.application.cla.dto.ClassListVO;
import common.application.cla.service.ClassListService;
import common.application.request.PageMaker;

@Controller
@RequestMapping("/class")
public record AiController(ClassListService classListService) {

    @GetMapping("/ai")
    public String ai() {
        String url = "/cla/ai/ai";
        return url;
    }

    @GetMapping("/ai/curriculum")
    public String curriculum() {
        String url = "cla/ai/curriculum";
        return url;
    }

    @GetMapping("/ai/enroll")
    public String showEnrollPage(@ModelAttribute PageMaker pageMaker, Model model) throws SQLException {

        pageMaker.setFno(4);

        List<ClassListVO> classList = classListService.selectClassListByField(pageMaker);
        model.addAttribute("classList", classList);

        int totalCount = classListService.selectClassListCountByField(pageMaker);
        pageMaker.setTotalCount(totalCount);
        model.addAttribute("pageMaker", pageMaker);

        return "cla/ai/enroll";
    }

    @PostMapping("/ai/enroll")
    public ResponseEntity<String> enrollCourse(@RequestParam String clno) {
        System.out.println("수강 신청 시도: clno = " + clno);
        return ResponseEntity.ok("강의 " + clno + " 수강 신청 완료");
    }

}

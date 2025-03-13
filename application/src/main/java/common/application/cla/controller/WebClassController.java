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
@RequestMapping("class")
public record WebClassController(ClassListService classListService) {

    @GetMapping("/web")
    public String web() {
        String url = "cla/web/web";
        return url;
    }

    @GetMapping("/web/info")
    public String web_info() {
        String url = "cla/web/web_info";
        return url;
    }

    @GetMapping("/web/curriculum")
    public String curriculum() {
        String url = "cla/web/curriculum";
        return url;
    }


    @GetMapping("/web/enroll")
    public String showEnrollPage(@ModelAttribute PageMaker pageMaker, Model model) throws SQLException {

    pageMaker.setFno(1);
    
    // 강의 목록 조회 (페이징 적용된 목록)
    List<ClassListVO> classList = classListService.selectClassListByField(pageMaker);
    model.addAttribute("classList", classList);
    
    // 총 강의 개수 조회 (페이지네이션을 위한 값)
    int totalCount = classListService.selectClassListCountByField(pageMaker);
    pageMaker.setTotalCount(totalCount);
    model.addAttribute("pageMaker", pageMaker);
    
    return "cla/web/enroll";
    }

    @PostMapping("/web/enroll") // ✅ URL 변경
    public ResponseEntity<String> enrollCourse(@RequestParam String clno) {
        System.out.println("수강 신청 시도: clno = " + clno);
        return ResponseEntity.ok("강의 " + clno + " 수강 신청 완료");
    }

}

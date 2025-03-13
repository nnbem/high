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
public record IoTController(ClassListService classListService) {

    @GetMapping("/iot")
    public String iot() {
        String url = "cla/IoT/IoT";
        return url;
    }
    @GetMapping("/iot/curriculum")
    public String curriculum() {
        String url = "cla/iot/curriculum";
        return url;
    }

    @GetMapping("/iot/enroll")
    public String showEnrollPage(@ModelAttribute PageMaker pageMaker, Model model) throws SQLException {

    pageMaker.setFno(2);
    
    // 강의 목록 조회 (페이징 적용된 목록)
    List<ClassListVO> classList = classListService.selectClassListByField(pageMaker);
    model.addAttribute("classList", classList);
    
    // 총 강의 개수 조회 (페이지네이션을 위한 값)
    int totalCount = classListService.selectClassListCountByField(pageMaker);
    pageMaker.setTotalCount(totalCount);
    model.addAttribute("pageMaker", pageMaker);
    
    return "cla/iot/enroll";
    }

    @PostMapping("/iot/enroll") // ✅ URL 변경
    public ResponseEntity<String> enrollCourse(@RequestParam String clno) {
        System.out.println("수강 신청 시도: clno = " + clno);
        return ResponseEntity.ok("강의 " + clno + " 수강 신청 완료");
    }

}

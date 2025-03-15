package common.application.cla.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import common.application.cla.dto.ClassHistoryVO;
import common.application.cla.dto.ClassListVO;
import common.application.cla.dto.MemberVO;
import common.application.cla.service.ClassHistoryService;
import common.application.cla.service.ClassListService;
import common.application.request.PageMaker;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("class")
public record WebClassController(ClassListService classListService, ClassHistoryService classHistoryService) {

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

        List<ClassListVO> classList = classListService.selectClassListByField(pageMaker);
        model.addAttribute("classList", classList);

        int totalCount = classListService.selectClassListCountByField(pageMaker);
        pageMaker.setTotalCount(totalCount);
        model.addAttribute("pageMaker", pageMaker);

        return "cla/web/enroll";
    }

    @PostMapping("/web/enroll")
    public ResponseEntity<String> enrollCourse(@RequestParam String clno) {
        System.out.println("수강 신청 시도: clno = " + clno);
        return ResponseEntity.ok("강의 " + clno + " 수강 신청 완료");
    }

    @PostMapping("/myclassroom/enroll/regist")
    public ResponseEntity<String> enrollCourse(@RequestParam String clno, HttpSession session) {
        MemberVO member = (MemberVO) session.getAttribute("loginUser");

        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("로그인이 필요합니다!");
        }

        String mid = member.getMid();

        try {
            ClassHistoryVO classHistory = new ClassHistoryVO();
            classHistory.setMid(mid);
            classHistory.setClno(Integer.parseInt(clno));

            classHistoryService.insertEnroll(classHistory);

            return ResponseEntity.ok("강의 " + clno + " 수강 신청이 완료되었습니다!");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("이미 수강신청된 강의입니다!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류 발생: " + e.getMessage());
        }
    }

}

package common.application.cla.controller;

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
import common.application.cla.service.ClassHistoryService;
import common.application.request.PageMaker;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/class")
public record ClassHistoryController(ClassHistoryService classHistoryService) {

    @GetMapping("/myclassroom/list")
    public void list(HttpSession session, Model model) throws Exception {
        String mid = (String) session.getAttribute("loginUserId");

        if (mid == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }

        List<ClassHistoryVO> historyList = classHistoryService.getEnrolledCoursesByMid(mid);

        model.addAttribute("historyList", historyList);
    }

    // 수강신청 폼 페이지
    @GetMapping("/enrollForm")
    public void enrollForm() {
    }

    // 수강 진행도 업데이트 (예: 시청시간 업데이트)
    @PostMapping("/myclassroom/update")
    public String update(@ModelAttribute ClassHistoryVO classHistory) throws Exception {
        classHistoryService.updateHistory(classHistory);
        return "redirect:/classHistory/list";
    }

    // 수강신청 취소 처리 (삭제)
    @GetMapping("/myclassroom/remove")
    public String remove(int clno) throws Exception {
        classHistoryService.deleteEnroll(clno);
        return "redirect:/classHistory/list";
    }

    // 인기 강의 조회 (메인 화면 등에 표시)
    @GetMapping("/popular")
    public void popular(Model model) throws Exception {
        List<ClassHistoryVO> popularLectures = classHistoryService.selectPopularLectures();
        model.addAttribute("popularLectures", popularLectures);
    }

    @PostMapping("/myclassroom/enroll/regist")
    public ResponseEntity<String> enrollCourse(@RequestParam String clno, HttpSession session) {
        String mid = (String) session.getAttribute("loginUserId");

        if (mid == null) {
            mid = "test77";
            session.setAttribute("loginUserId", mid);
        }

        // 🔥 로그인되지 않은 사용자는 요청을 거부
        // if (mid == null) {
        // return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        // .body("로그인이 필요합니다!");
        // }

        try {
            ClassHistoryVO classHistory = new ClassHistoryVO();
            classHistory.setMid(mid);
            classHistory.setClno(Integer.parseInt(clno));

            classHistoryService.insertEnroll(classHistory);

            return ResponseEntity.ok("✅ 강의 " + clno + " 수강 신청이 완료되었습니다!");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("이미 수강신청된된 강의입니다!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(": " + e.getMessage());
        }
    }

}
